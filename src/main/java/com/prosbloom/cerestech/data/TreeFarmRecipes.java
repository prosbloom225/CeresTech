package com.prosbloom.cerestech.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.TREE_FARM_RECIPES;

public class TreeFarmRecipes {
    public static void registerTreeFarmRecipes(Consumer<FinishedRecipe> provider) {
        TREE_FARM_RECIPES.recipeBuilder("sapling_to_logs")
                .inputItems(Items.OAK_SAPLING, 1)
                .inputFluids(Water.getFluid(1000))
                .outputItems(new ItemStack(Items.OAK_LOG, 4), new ItemStack(Items.OAK_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
    }
}
