package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
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
        TREE_FARM_RECIPES.recipeBuilder("farm_oak")
                .inputItems(Items.OAK_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.OAK_LOG, 4), new ItemStack(Items.OAK_SAPLING, 1))
                .chancedOutput(new ItemStack(Items.APPLE, 1), 5000, 1000)
                .duration(100).EUt(96)
                .save(provider);
        TREE_FARM_RECIPES.recipeBuilder("farm_spruce")
                .inputItems(Items.SPRUCE_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 4), new ItemStack(Items.SPRUCE_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
        TREE_FARM_RECIPES.recipeBuilder("farm_acacia")
                .inputItems(Items.ACACIA_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 4), new ItemStack(Items.ACACIA_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
        TREE_FARM_RECIPES.recipeBuilder("farm_jungle")
                .inputItems(Items.JUNGLE_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 4), new ItemStack(Items.JUNGLE_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
        TREE_FARM_RECIPES.recipeBuilder("farm_birch")
                .inputItems(Items.BIRCH_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 4), new ItemStack(Items.BIRCH_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
        TREE_FARM_RECIPES.recipeBuilder("farm_dark_oak")
                .inputItems(Items.DARK_OAK_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 4), new ItemStack(Items.DARK_OAK_SAPLING, 1))
                .duration(100).EUt(96)
                .save(provider);
    }
}
