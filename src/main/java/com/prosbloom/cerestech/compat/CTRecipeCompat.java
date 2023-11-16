package com.prosbloom.cerestech.compat;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.BLAST_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.IMPLOSION_RECIPES;
import static com.prosbloom.cerestech.data.CTMaterials.CosmicNeutronium;
import static com.prosbloom.cerestech.data.CTMaterials.InfinityCatalyst;
import static committee.nova.mods.avaritia.init.registry.ModItems.infinity_catalyst;
import static committee.nova.mods.avaritia.init.registry.ModItems.neutron_nugget;

public class CTRecipeCompat {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerImplosionCompressorRecipes(provider);
    }
    private static void registerImplosionCompressorRecipes(Consumer<FinishedRecipe> provider) {
        IMPLOSION_RECIPES.recipeBuilder("cosmic_neutronium_nugget")
                .inputItems(dustTiny, CosmicNeutronium, 9)
                .outputItems(neutron_nugget)
                .duration(1).EUt(VA[UEV])
                .save(provider);
        IMPLOSION_RECIPES.recipeBuilder("infinity_catalyst")
                .inputItems(dust, InfinityCatalyst, 64)
                .outputItems(infinity_catalyst)
                .duration(1).EUt(VA[UHV])
                .save(provider);
    }
}
