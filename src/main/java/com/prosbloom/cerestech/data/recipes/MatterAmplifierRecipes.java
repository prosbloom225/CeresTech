package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.prosbloom.cerestech.data.CTFluids.UUAMatter;
import static com.prosbloom.cerestech.data.CTRecipeTypes.MATTER_AMPLIFIER_RECIPES;

public class MatterAmplifierRecipes {
    public static void registerMatterAmplifierRecipes(Consumer<FinishedRecipe> provider) {
        MATTER_AMPLIFIER_RECIPES.recipeBuilder("cobble_to_uua")
                .circuitMeta(9)
                .inputItems(Blocks.COBBLESTONE.asItem(), 9)
                .outputFluids(UUAMatter.getFluid(1))
                .EUt(VA[LV])
                .duration(180)
                .save(provider);
    }
}
