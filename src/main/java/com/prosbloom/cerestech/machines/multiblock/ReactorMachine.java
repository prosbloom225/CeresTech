package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.forge.SizedIngredientImpl;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.LargeBoilerMachine;
import com.prosbloom.cerestech.data.CTFluids;
import com.prosbloom.cerestech.data.NuclearReactorRecipes;
import net.minecraft.nbt.IntTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.prosbloom.cerestech.data.NuclearReactorRecipes.reactorFuels;

public class ReactorMachine extends LargeBoilerMachine {
    public ReactorMachine(IMachineBlockEntity holder, int maxTemperature, int heatSpeed, Object... args) {
        super(holder, maxTemperature, heatSpeed, args);
    }

    public void generateCoolant(){
        if (recipeLogic.isWorking())
        {
            if (getOffsetTimer() %10 == 0)
            {
                var maxDrain = 0;
                // Determine the fuel ingredient
                // TODO - hardcoding reactor to only use fuel_oxides
                String ingredient = ((SizedIngredientImpl)recipeLogic.getLastRecipe().getInputContents(ItemRecipeCapability.CAP).get(0).content).getInner().getItems()[0].getItem().toString();
                int count = ((IntTag)((SizedIngredientImpl)recipeLogic.getLastRecipe().getInputContents(ItemRecipeCapability.CAP).get(1).content).getInner().getItems()[0].getTag().get("Configuration")).getAsInt();
                NuclearReactorRecipes.ReactorFuel rf = reactorFuels.stream()
                        .filter(fuel-> ingredient.equals(fuel.getName() + "_fuel_oxide"))
                        .findAny().orElse(null);
                if (rf != null) {
                    // TODO - coolant scaler for nuclear reactor - 1000 atm
                    maxDrain = 1000 * rf.getHeat() * count;
                }

                // TODO - Dynamically pull from hct recipeList
                var drainCoolant = List.of(FluidIngredient.of(55, CTFluids.Coolant.getFluid()));
                List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
                if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP)) {
                    inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
                }
                if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                    inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
                }
                for (IRecipeHandler<?> tank : inputTanks) {
                    drainCoolant = (List<FluidIngredient>) tank.handleRecipe(IO.IN, null, drainCoolant, null, false);
                    if (drainCoolant == null) break;
                }
                var drained = (drainCoolant == null || drainCoolant.isEmpty()) ? maxDrain : maxDrain - drainCoolant.get(0).getAmount();

                boolean hasDrainedCoolant = drained > 0;

                if (hasDrainedCoolant) {
                    // fill coolant
                    var fillHotCoolant = List.of(FluidIngredient.of(CTFluids.CoolantHot.getFluid(drained)));
                    List<IRecipeHandler<?>> outputTanks = new ArrayList<>();
                    if (getCapabilitiesProxy().contains(IO.OUT, FluidRecipeCapability.CAP)) {
                        outputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.OUT, FluidRecipeCapability.CAP)));
                    }
                    if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                        outputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
                    }
                    for (IRecipeHandler<?> tank : outputTanks) {
                        fillHotCoolant = (List<FluidIngredient>) tank.handleRecipe(IO.OUT, null, fillHotCoolant, null, false);
                        if (fillHotCoolant == null) break;
                    }
                }
            }
        }
    }

    @Override
    public void onWorking() {
        generateCoolant();
    }
}
