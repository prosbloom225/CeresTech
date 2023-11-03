package com.prosbloom.cerestech.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTMaterials.EnrichedHolmium;
import static com.prosbloom.cerestech.data.CTMaterials.Kevlar;
import static com.prosbloom.cerestech.data.CTRecipeTypes.HEAT_EXCHANGER_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.PCB_FACTORY_RECIPES;

public class PcbFactoryRecipes {
    public static void registerPcbFactoryRecipes(Consumer<FinishedRecipe> provider) {
        PCB_FACTORY_RECIPES.recipeBuilder("pcb_plastic_1")
                .inputItems(plate, PolyvinylChloride, 1)
                .inputItems(foil, AnnealedCopper, 72)
                .inputItems(foil, Copper, 24)
                .inputFluids(SulfuricAcid.getFluid(700))
                .inputFluids(Iron3Chloride.getFluid(350))
                .circuitMeta(1)
                .outputItems(PLASTIC_CIRCUIT_BOARD, 12)
                .EUt(VA[MV])
                .duration(500)
                .save(provider);

        PCB_FACTORY_RECIPES.recipeBuilder("pcb_epoxy_1")
                .inputItems(plate, Epoxy, 1)
                .inputItems(foil, Electrum, 96)
                .inputItems(foil, Gold, 48)
                .inputFluids(SulfuricAcid.getFluid(700))
                .inputFluids(Iron3Chloride.getFluid(700))
                .circuitMeta(1)
                .outputItems(ADVANCED_CIRCUIT_BOARD, 12)
                .EUt(VA[HV])
                .duration(500)
                .save(provider);

        PCB_FACTORY_RECIPES.recipeBuilder("pcb_fiber_reinforced_1")
                .inputItems(plate, ReinforcedEpoxyResin, 1)
                .inputItems(foil, Electrum, 96)
                .inputItems(foil, Gold, 48)
                .inputFluids(SulfuricAcid.getFluid(700))
                .inputFluids(Iron3Chloride.getFluid(700))
                .circuitMeta(1)
                .outputItems(EXTREME_CIRCUIT_BOARD, 12)
                .EUt(VA[EV])
                .duration(500)
                .save(provider);

        PCB_FACTORY_RECIPES.recipeBuilder("pcb_multi_layer_1")
                .inputItems(plate, Polybenzimidazole, 1)
                .inputItems(foil, Platinum, 96)
                .inputItems(foil, Palladium, 48)
                .inputFluids(SulfuricAcid.getFluid(700))
                .inputFluids(Iron3Chloride.getFluid(700))
                .circuitMeta(1)
                .outputItems(ELITE_CIRCUIT_BOARD, 12)
                .EUt(VA[IV])
                .duration(500)
                .save(provider);

        PCB_FACTORY_RECIPES.recipeBuilder("pcb_wetware_1")
                .inputItems(plate, Kevlar, 1)
                .inputItems(foil, EnrichedHolmium, 96)
                .inputItems(foil, NiobiumTitanium, 48)
                .inputFluids(SterileGrowthMedium.getFluid(700))
                .inputFluids(SulfuricAcid.getFluid(700))
                .inputFluids(Iron3Chloride.getFluid(700))
                .circuitMeta(1)
                .outputItems(WETWARE_CIRCUIT_BOARD, 12)
                .EUt(VA[IV])
                .duration(500)
                .save(provider);

    }
}
