package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HEAT_EXCHANGER_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HOT_COOLANT_TURBINE_RECIPES;

public class HeatExchangerRecipes {
    public static void registerHeatExchangerRecipes(Consumer<FinishedRecipe> provider) {
        HEAT_EXCHANGER_RECIPES.recipeBuilder("lava_to_steam")
                .inputFluids(Lava.getFluid(100))
                .inputFluids(DistilledWater.getFluid(100))
                .outputFluids(PahoehoeLava.getFluid(100))
                .outputFluids(Steam.getFluid(800))
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        HEAT_EXCHANGER_RECIPES.recipeBuilder("hot_flibe_to_steam")
                .inputFluids(CoolantHot.getFluid(600))
                .inputFluids(DistilledWater.getFluid(400))
                .outputFluids(Coolant.getFluid(600))
                .outputFluids(Steam.getFluid(64000))
                //.EUt(VA[LV])
                .duration(20)
                .save(provider);
    }
}
