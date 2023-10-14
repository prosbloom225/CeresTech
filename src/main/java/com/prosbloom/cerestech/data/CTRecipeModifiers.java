package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class CTRecipeModifiers {
    public static GTRecipe volcanusParallel(MetaMachine machine, @Nonnull GTRecipe recipe, int maxParallel, boolean modifyDuration) {
        recipe = ebfOverclock(machine, recipe);
        // TODO - clean this up, shouldnt have to override these to nest.. Also need to set programmable_circuit stacksize to 1 on parallelized recipes
        if (machine instanceof IRecipeCapabilityHolder holder) {
            return tryParallel(holder, recipe, 1, maxParallel, modifyDuration);
        }
        return null;
    }

    private static GTRecipe tryParallel(IRecipeCapabilityHolder holder, GTRecipe original, int min, int max, boolean modifyDuration) {
        if (min > max) return null;
        int mid = (min + max) / 2;
        GTRecipe copied = original.copy(ContentModifier.multiplier(mid), modifyDuration);
        // set euT back to original
        Map.Entry<RecipeCapability<?>, List<Content>> newEu =  copied.tickInputs.entrySet().iterator().next();
        Map.Entry<RecipeCapability<?>, List<Content>> originalEu =  original.tickInputs.entrySet().iterator().next();
        copied.tickInputs.put(newEu.getKey(), originalEu.getValue());
        if (!copied.matchRecipe(holder).isSuccess() || !copied.matchTickRecipe(holder).isSuccess()) {
            // tried too many
            return tryParallel(holder, original, min, mid - 1, modifyDuration);
        } else {
            // at max parallels
            if (mid == max) {
                return copied;
            }
            // matches, but try to do more
            var tryMore = tryParallel(holder, original, mid + 1, max, modifyDuration);
            return tryMore != null ? tryMore : copied;
        }
    }

    public static GTRecipe ebfOverclock(MetaMachine machine, @Nonnull GTRecipe recipe) {
        if (machine instanceof CoilWorkableElectricMultiblockMachine coilMachine) {
            var blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() + 100 * Math.max(0, coilMachine.getTier() - GTValues.MV);
            if (!recipe.data.contains("ebf_temp") || recipe.data.getInt("ebf_temp") > blastFurnaceTemperature) {
                return null;
            }
            if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) {
                return null;
            }
            return RecipeHelper.applyOverclock(new OverclockingLogic((recipe1, recipeEUt, maxVoltage, duration, amountOC) -> OverclockingLogic.heatingCoilOverclockingLogic(
                    Math.abs(recipeEUt),
                    maxVoltage,
                    duration,
                    amountOC,
                    blastFurnaceTemperature,
                    recipe.data.contains("ebf_temp") ? 0 : recipe.data.getInt("ebf_temp")
            )), recipe, coilMachine.getMaxVoltage());
        }
        return null;
    }
}
