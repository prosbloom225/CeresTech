package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.lowdragmc.lowdraglib.misc.FluidStorage;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NotifiableFluidTankMulti extends NotifiableFluidTank {
    public NotifiableFluidTankMulti(MetaMachine machine, int slots, long capacity, IO io) {
        super(machine, slots, capacity, io);
    }


    @Override
    public long fill(int tank, FluidStack resource, boolean simulate, boolean notifyChanges) {
        if (tank >= 0 && tank < storages.length && canCapInput()) {
            if(!Arrays.stream(storages)
                    .filter(s->!s.equals(storages[tank]))
                    .map(FluidStorage::getFluid)
                    .anyMatch(f->f.isFluidEqual(resource)))
                return storages[tank].fill(resource, simulate, notifyChanges);
        }
        return 0;
    }

    @Override
    public long fill(FluidStack resource, boolean simulate) {
        if (canCapInput()) {
            return fillInternal(resource, simulate);
        }
        return 0;
    }

    @Override
    public long fillInternal(FluidStack resource, boolean simulate) {
        if (!resource.isEmpty()) {
            var copied = resource.copy();
            FluidStorage existingStorage = null;
            if (!allowSameFluids) {
                for (var storage : storages) {
                    if (!storage.getFluid().isEmpty() && storage.getFluid().isFluidEqual(resource)) {
                        existingStorage = storage;
                        break;
                    }
                }
            }
            if (existingStorage == null) {
                for (var storage : storages) {
                    var filled = storage.fill(copied.copy(), simulate);
                    if (filled > 0) {
                        copied.shrink(filled);
                        if (!allowSameFluids) {
                            break;
                        }
                    }
                    if (copied.isEmpty()) break;
                }
            } else {
                copied.shrink(existingStorage.fill(copied.copy(), simulate));
            }
            return resource.getAmount() - copied.getAmount();
        }
        return 0;
    }

    @Override
    public void setFluidInTank(int tank, @NotNull FluidStack fluidStack) {
        storages[tank].setFluid(fluidStack);
    }


    @Override
    public List<FluidIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<FluidIngredient> left, @Nullable String slotName, boolean simulate) {
        if (io != this.handlerIO) return left;
        var capabilities = simulate ? Arrays.stream(storages).map(FluidStorage::copy).toArray(FluidStorage[]::new) : storages;

        // simulate filling tanks
        for (FluidIngredient i : left) {
            if (fillInternal(i.getStacks()[0], true) == 0)
                return left;
        }

        for (FluidStorage capability : capabilities) {
            Iterator<FluidIngredient> iterator = left.iterator();
            if (io == IO.IN) {
                while (iterator.hasNext()) {
                    FluidIngredient fluidStack = iterator.next();
                    if (fluidStack.isEmpty()) {
                        iterator.remove();
                        continue;
                    }
                    boolean found = false;
                    FluidStack foundStack = null;
                    for (int i = 0; i < capability.getTanks(); i++) {
                        FluidStack stored = capability.getFluidInTank(i);
                        if (!fluidStack.test(stored)) {
                            continue;
                        }
                        found = true;
                        foundStack = stored;
                    }
                    if (!found) continue;
                    FluidStack drained = capability.drain(foundStack.copy(fluidStack.getAmount()), false);

                    fluidStack.setAmount(fluidStack.getAmount() - drained.getAmount());
                    if (fluidStack.getAmount() <= 0) {
                        iterator.remove();
                    }
                }
            } else if (io == IO.OUT) {
                while (iterator.hasNext()) {
                    FluidIngredient fluidStack = iterator.next();
                    if (fluidStack.isEmpty()) {
                        iterator.remove();
                        continue;
                    }
                    var fluids = fluidStack.getStacks();
                    if (fluids.length == 0) {
                        iterator.remove();
                        continue;
                    }
                    FluidStack output = fluids[0];
                    long filled = capability.fill(output.copy(), false);
                    if (!fluidStack.isEmpty()) {
                        fluidStack.setAmount(fluidStack.getAmount() - filled);
                    }
                    if (fluidStack.getAmount() <= 0) {
                        iterator.remove();
                    }
                }
            }
            if (left.isEmpty()) break;
        }
        return left.isEmpty() ? null : left;
    }
}
