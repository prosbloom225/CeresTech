package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GTItems.SHAPE_MOLD_INGOT;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTMaterials.DubniumPlasma;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HEAT_EXCHANGER_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.PLASMA_CONDENSER_RECIPES;

public class PlasmaCondenserRecipes {
    public static void registerPlasmaCondenserRecipes(Consumer<FinishedRecipe> provider) {
        PLASMA_CONDENSER_RECIPES.recipeBuilder("dubnium_plasma_to_ingot")
                .inputFluids(DubniumPlasma.getFluid(FluidStorageKeys.PLASMA, 144))
                .inputFluids(Helium.getFluid(1000))
                .notConsumable(SHAPE_MOLD_INGOT)
                .outputItems(ingot, Dubnium, 1)
                .EUt(VA[EV])
                .duration(40)
                .save(provider);
    }
}
