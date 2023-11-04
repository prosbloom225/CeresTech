package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.prosbloom.cerestech.data.CTFluids;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.prosbloom.cerestech.data.CTFluids.Pyrotheum;
import static com.prosbloom.cerestech.data.CTMaterials.GelidCryotheum;

public class VolcanusMachine extends CoilWorkableElectricMultiblockMachine {
    private final static FluidStack coolant = Pyrotheum.getFluid(20);
    public VolcanusMachine(IMachineBlockEntity holder) {
        super(holder);
    }
    @Override
    public void onWorking() {
        super.onWorking();
        if (getOffsetTimer() %20 ==0) {
            List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
            if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
            if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
            var fluidDrained = FluidStack.empty();
            for (IRecipeHandler<?> tank : inputTanks)
                if (tank instanceof NotifiableFluidTank) {
                    for (int i=0;i<((NotifiableFluidTank) tank).storages.length;i++){
                        fluidDrained = ((NotifiableFluidTank) tank).storages[i].drain(coolant, false);
                        if (!fluidDrained.isEmpty())
                            return;
                    }
                }
            // TODO - probably a more elegant way of doing this so you dont have to manually restart machines when out of cryo..
            if (fluidDrained.isEmpty() && fluidDrained.getAmount() != coolant.getAmount()) {
                this.recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
            } else
                this.recipeLogic.setStatus(RecipeLogic.Status.WORKING);
        }
    }
}
