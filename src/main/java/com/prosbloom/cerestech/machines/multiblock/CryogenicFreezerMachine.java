package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.prosbloom.cerestech.data.CTMaterials.GelidCryotheum;

public class CryogenicFreezerMachine extends WorkableElectricMultiblockMachine {
    private final static FluidStack coolant = GelidCryotheum.getFluid(20);
    public CryogenicFreezerMachine(IMachineBlockEntity holder) {
        super(holder);
    }
    @Override
    public boolean onWorking() {
        super.onWorking();
        if (getOffsetTimer() %20 ==0) {
            List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
            if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
            if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
            var fluidDrained = FluidStack.EMPTY;
            for (IRecipeHandler<?> tank : inputTanks)
                if (tank instanceof NotifiableFluidTank) {
                    for (int i=0;i<((NotifiableFluidTank) tank).getStorages().length;i++){
                        fluidDrained = ((NotifiableFluidTank) tank).getStorages()[i].drain(coolant, IFluidHandler.FluidAction.EXECUTE);
                        if (!fluidDrained.isEmpty())
                            return false;
                    }
                }
            // TODO - probably a more elegant way of doing this so you dont have to manually restart machines when out of cryo..
            if (fluidDrained.isEmpty() && fluidDrained.getAmount() != coolant.getAmount()) {
                this.recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
            } else
                this.recipeLogic.setStatus(RecipeLogic.Status.WORKING);
        }
        return true;
    }
}
