package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType.plasma;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTBlocks.NEUTRONIUM_CHARGE;
import static com.prosbloom.cerestech.data.CTFluids.DraconicStemCell;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.STELLAR_FORGE_RECIPES;

public class StellarForgeRecipes {
    public static void registerStellarForgeRecipes(Consumer<FinishedRecipe> provider) {
        STELLAR_FORGE_RECIPES.recipeBuilder("black_plutonium_dust")
                .inputItems(ore, Plutonium239)
                .inputItems(NEUTRONIUM_CHARGE.asStack())
                .outputItems(dust, BlackPlutonium, 1)
                .EUt(VA[UHV])
                .duration(5)
                .save(provider);
        STELLAR_FORGE_RECIPES.recipeBuilder("infinity_catalyst_dust")
                .inputItems(ore, Naquadah)
                .inputItems(NEUTRONIUM_CHARGE.asStack())
                .outputItems(dust, InfinityCatalyst, 1)
                .EUt(VA[UHV])
                .duration(5)
                .save(provider);
        STELLAR_FORGE_RECIPES.recipeBuilder("awakened_draconium_fluid")
                .inputItems(block, Draconium, 3)
                //.inputItems(WyvernCore.asStack())
                .inputFluids(DraconicStemCell.getFluid(2000))
                .outputFluids(AwakenedDraconium.getFluid( 3888))
                .outputFluids(RawGrowthMedium.getFluid(2000))
                .EUt(VA[UEV])
                .duration(200)
                .save(provider);
    }
}
