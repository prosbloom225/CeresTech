package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.rod;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NUCLEAR_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.depletedRod;

public class NuclearReactorRecipes {
    public static void registerNuclearReactorRecipes(Consumer<FinishedRecipe> provider) {
        NUCLEAR_REACTOR_RECIPES.recipeBuilder("u238_pu239")
                .inputItems(rod, GTMaterials.Uranium238, 9)
                .inputItems(rod, GTMaterials.Plutonium241, 1)
                .outputItems(depletedRod, GTMaterials.Uranium238, 9)
                .outputItems(depletedRod, GTMaterials.Plutonium241, 1)
                .duration(100).EUt(96)
                .save(provider);
    }
}
