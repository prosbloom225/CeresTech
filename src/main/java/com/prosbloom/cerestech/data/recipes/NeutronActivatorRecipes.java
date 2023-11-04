package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTMaterials.Adamantium;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HEAT_EXCHANGER_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NEUTRON_ACTIVATOR_RECIPES;

public class NeutronActivatorRecipes {
    public static void registerNeutronActivatorRecipes(Consumer<FinishedRecipe> provider) {
        // TODO - remove these from gtceu centrifuge recipes
        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("acidic_enriched_naquadah_to_sulfate")
                .inputFluids(AcidicEnrichedNaquadahSolution.getFluid(3000))
                .outputItems(dust, Adamantium, 4)
                .outputItems(dust, EnrichedNaquadahSulfate, 6)
                .outputFluids(EnrichedNaquadahWaste.getFluid(2000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(VA[LuV])
                .duration(200)
                .save(provider);
        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("acidic_naquadria_to_sulfate")
                .inputFluids(AcidicNaquadriaSolution.getFluid(3000))
                .outputItems(dust, Adamantium, 4)
                .outputItems(dust, NaquadriaSulfate, 6)
                .outputFluids(NaquadriaWaste.getFluid(2000))
                .outputFluids(Fluorine.getFluid(2000))
                .EUt(VA[LuV])
                .duration(200)
                .save(provider);
    }
}
