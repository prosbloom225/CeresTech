package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.INDUSTRIAL_GREENHOUSE_RECIPES;

public class IndustrialGreenhouseRecipes {
    public static void registerIndustrialGreenhouseRecipes(Consumer<FinishedRecipe> provider) {
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_oak")
                .inputItems(Items.OAK_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.OAK_LOG, 4), new ItemStack(Items.OAK_SAPLING, 1))
                .chancedOutput(new ItemStack(Items.APPLE, 1), 5000, 600)
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_spruce")
                .inputItems(Items.SPRUCE_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.SPRUCE_LOG, 4), new ItemStack(Items.SPRUCE_SAPLING, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_acacia")
                .inputItems(Items.ACACIA_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.ACACIA_LOG, 4), new ItemStack(Items.ACACIA_SAPLING, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_jungle")
                .inputItems(Items.JUNGLE_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.JUNGLE_LOG, 4), new ItemStack(Items.JUNGLE_SAPLING, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_birch")
                .inputItems(Items.BIRCH_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.BIRCH_LOG, 4), new ItemStack(Items.BIRCH_SAPLING, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("farm_dark_oak")
                .inputItems(Items.DARK_OAK_SAPLING, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.DARK_OAK_LOG, 4), new ItemStack(Items.DARK_OAK_SAPLING, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("wheat")
                .inputItems(Items.WHEAT_SEEDS, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.WHEAT, 4), new ItemStack(Items.WHEAT_SEEDS, 4))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("carrot")
                .inputItems(Items.CARROT, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.CARROT, 4))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("beetroot")
                .inputItems(Items.BEETROOT_SEEDS, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.BEETROOT, 4), new ItemStack(Items.BEETROOT_SEEDS, 1))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("melon")
                .inputItems(Items.MELON_SEEDS, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.MELON, 4))
                .duration(60).EUt(96)
                .save(provider);
        INDUSTRIAL_GREENHOUSE_RECIPES.recipeBuilder("pumpkin")
                .inputItems(Items.PUMPKIN_SEEDS, 1)
                .inputFluids(Water.getFluid(100))
                .outputItems(new ItemStack(Items.PUMPKIN, 4))
                .duration(60).EUt(96)
                .save(provider);
    }
}
