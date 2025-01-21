package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTItems.STEM_CELLS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SterileGrowthMedium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.UUMatter;
import static com.prosbloom.cerestech.data.CTFluids.DraconicStemCell;
import static com.prosbloom.cerestech.data.CTFluids.UUAMatter;
import static com.prosbloom.cerestech.data.CTMaterials.Draconium;
import static com.prosbloom.cerestech.data.CTRecipeTypes.BIO_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.MASS_FABRICATOR_RECIPES;

public class MassFabricatorRecipes {
    public static void registerMassFabricatorRecipes(Consumer<FinishedRecipe> provider) {
        MASS_FABRICATOR_RECIPES.recipeBuilder("null_to_uum")
                .circuitMeta(1)
                .outputFluids(UUMatter.getFluid(1))
                .EUt(VA[LV])
                .duration(3215)
                .save(provider);
        MASS_FABRICATOR_RECIPES.recipeBuilder("uua_to_uum")
                .inputFluids(UUAMatter.getFluid(1))
                .outputFluids(UUMatter.getFluid(1))
                .EUt(VA[LV])
                .duration(803)
                .save(provider);
    }
}
