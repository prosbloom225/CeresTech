package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.prosbloom.cerestech.data.CTFluids.Coolant;
import static com.prosbloom.cerestech.data.CTFluids.CoolantHot;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HOT_COOLANT_TURBINE_RECIPES;

public class HotCoolantTurbineRecipes {
    public static void registerHotCoolantTurbineRecipes(Consumer<FinishedRecipe> provider) {
        HOT_COOLANT_TURBINE_RECIPES.recipeBuilder("hot_flibe_to_flibe")
                .inputFluids(CoolantHot.getFluid(55))
                .outputFluids(Coolant.getFluid(55))
                .EUt(-V[IV])
                .duration(2)
                .save(provider);
    }
}
