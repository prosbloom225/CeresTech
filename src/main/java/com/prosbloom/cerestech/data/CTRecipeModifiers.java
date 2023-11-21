package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
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
        if (machine instanceof IRecipeCapabilityHolder holder && recipe != null) {
            recipe = tryParallel(holder, recipe, 1, maxParallel, modifyDuration, false);
            if (recipe != null)
                recipe = RecipeHelper.applyOverclock(OverclockingLogic.PERFECT_OVERCLOCK, recipe,
                        ((IOverclockMachine)machine).getOverclockVoltage());
            return recipe;
        }
        return null;
    }
    public static GTRecipe parallelOverclock(MetaMachine machine, @Nonnull GTRecipe recipe, int maxParallel, boolean modifyDuration) {
        if (machine instanceof IRecipeCapabilityHolder holder) {
            recipe = tryParallel(holder, recipe, 1, maxParallel, modifyDuration, false);
            if (recipe != null)
                return RecipeHelper.applyOverclock(OverclockingLogic.PERFECT_OVERCLOCK, recipe, ((IOverclockMachine)machine).getOverclockVoltage());
        }
        return null;
    }

    private static GTRecipe tryParallel(IRecipeCapabilityHolder holder, @Nonnull GTRecipe original, int min, int max, boolean modifyDuration, boolean modifyVoltage) {
        if (min > max) return null;
        int mid = (min + max) / 2;
        GTRecipe copied = original.copy(ContentModifier.multiplier(mid), modifyDuration);
        // set euT back to original
        if (!modifyVoltage) {
            Map.Entry<RecipeCapability<?>, List<Content>> newEu = copied.tickInputs.entrySet().iterator().next();
            Map.Entry<RecipeCapability<?>, List<Content>> originalEu = original.tickInputs.entrySet().iterator().next();
            copied.tickInputs.put(newEu.getKey(), originalEu.getValue());
        }
        // set duration properly
        if (!copied.matchRecipe(holder).isSuccess() || !copied.matchTickRecipe(holder).isSuccess()) {
            // tried too many
            return tryParallel(holder, original, min, mid - 1, modifyDuration, modifyVoltage);
        } else {
            // at max parallels
            if (mid == max) {
                return copied;
            }
            // matches, but try to do more
            var tryMore = tryParallel(holder, original, mid + 1, max, modifyDuration, modifyVoltage);
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
