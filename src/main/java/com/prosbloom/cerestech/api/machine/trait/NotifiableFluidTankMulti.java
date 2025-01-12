package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorage;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NotifiableFluidTankMulti extends NotifiableFluidTank {
    public NotifiableFluidTankMulti(MetaMachine machine, int slots, int capacity, IO io) {
        super(machine, slots, capacity, io);
    }

    /*
    @Override
    public long fill(int tank, FluidStack resource, boolean simulate, boolean notifyChanges) {
        if (tank >= 0 && tank < getStorages().length && canCapInput()) {
            if(!Arrays.stream(getStorages())
                    .filter(s->!s.equals(getStorages()[tank]))
                    .map(FluidStorage::getFluid)
                    .anyMatch(f->f.isFluidEqual(resource)))
                return getStorages()[tank].fill(resource, simulate, notifyChanges);
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
                for (var storage : getStorages()) {
                    if (!storage.getFluid().isEmpty() && storage.getFluid().isFluidEqual(resource)) {
                        existingStorage = storage;
                        break;
                    }
                }
            }
            if (existingStorage == null) {
                for (var storage : getStorages()) {
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
        getStorages()[tank].setFluid(fluidStack);
    }


    @Override
    public List<FluidIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<FluidIngredient> left, @Nullable String slotName, boolean simulate) {
        if (io != this.handlerIO) return left;
        var capabilities = simulate ? Arrays.stream(getStorages()).map(FluidStorage::copy).toArray(FluidStorage[]::new) : getStorages();


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
                // simulate filling tanks
                for (FluidIngredient i : left) {
                    if (fillInternal(i.getStacks()[0], true) == 0)
                        return left;
                }
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

     */
}
