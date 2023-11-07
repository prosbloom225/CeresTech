package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.UHV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ore;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Plutonium239;
import static com.prosbloom.cerestech.data.CTBlocks.NEUTRONIUM_CHARGE;
import static com.prosbloom.cerestech.data.CTMaterials.BlackPlutonium;
import static com.prosbloom.cerestech.data.CTRecipeTypes.STELLAR_FORGE_RECIPES;

public class StellarForgeRecipes {
    public static void registerStellarForgeRecipes(Consumer<FinishedRecipe> provider) {
        STELLAR_FORGE_RECIPES.recipeBuilder("black_plutonium_ore")
                .inputItems(ore, Plutonium239)
                .inputItems(NEUTRONIUM_CHARGE.asStack())
                .outputItems(ore, BlackPlutonium, 1)
                .EUt(VA[UHV])
                .duration(5)
                .save(provider);
    }
}
