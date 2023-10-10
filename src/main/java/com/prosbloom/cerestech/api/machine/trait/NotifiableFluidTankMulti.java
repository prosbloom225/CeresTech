package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.lowdragmc.lowdraglib.misc.FluidStorage;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraft.world.level.material.Fluid;

import java.util.Arrays;
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

}
