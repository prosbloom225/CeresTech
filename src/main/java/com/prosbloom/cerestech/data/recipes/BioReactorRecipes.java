package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.DragonEggBlock;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTItems.STEM_CELLS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTMaterials.Draconium;
import static com.prosbloom.cerestech.data.CTRecipeTypes.BIO_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HEAT_EXCHANGER_RECIPES;

public class BioReactorRecipes {
    public static void registerBioReactorRecipes(Consumer<FinishedRecipe> provider) {
        BIO_REACTOR_RECIPES.recipeBuilder("draconic_stem_cell_fluid")
                .inputItems(STEM_CELLS, 10)
                // TODO - change this to dragon heart when draconic added
                .inputItems(dust, Draconium, 2)
                .inputFluids(SterileGrowthMedium.getFluid(2000))
                .outputFluids(DraconicStemCell.getFluid(2000))
                .EUt(VA[UHV])
                .duration(600)
                .save(provider);
    }
}
