package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.rod;
import static com.gregtechceu.gtceu.common.data.GTMaterials.NaquadahEnriched;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static com.prosbloom.cerestech.data.CTRecipeTypes.INDUSTRIAL_GREENHOUSE_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NAQUADAH_REACTOR_RECIPES;

public class NaquadahReactorRecipes {
    public static void registerNaquadahReactorRecipes(Consumer<FinishedRecipe> provider) {
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("enriched_naquadah_rod")
                .inputItems(rod, NaquadahEnriched, 1)
                .EUt(-V[EV])
                .duration(200)
                .save(provider);
    }
}
