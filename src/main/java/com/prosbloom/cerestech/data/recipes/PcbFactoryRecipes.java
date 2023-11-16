package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.foil;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTFluids.SterilizedBioCatalystMedium;
import static com.prosbloom.cerestech.data.CTItems.ULTRA_BIO_MUTATED_CIRCUIT_BOARD;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.PCB_FACTORY_RECIPES;

public class PcbFactoryRecipes {
    private static final List<Material> boards = List.of(Polyethylene, PolyvinylChloride, Polytetrafluoroethylene, Epoxy, ReinforcedEpoxyResin, Kevlar, Radox);
    private static class CircuitBoard {
        public ItemEntry output;
        public List<Material> boards;
        public Material foil;
        public Material foil2;
        public int tier;
        public Material fluid;

        public CircuitBoard(ItemEntry output, List<Material> boards, Material foil, Material foil2, int tier, Material fluid) {
            this.output = output;
            this.boards = boards;
            this.foil = foil;
            this.foil2 = foil2;
            this.tier = tier;
            this.fluid = fluid;
        }


    }

    private static final List<CircuitBoard> CircuitBoards = List.of(
            new CircuitBoard(PLASTIC_CIRCUIT_BOARD, List.of(Polyethylene, PolyvinylChloride, Polytetrafluoroethylene, Epoxy, ReinforcedEpoxyResin, Polybenzimidazole, Kevlar, Radox), Copper, AnnealedCopper, LV, null),
            new CircuitBoard(ADVANCED_CIRCUIT_BOARD, List.of(Epoxy, ReinforcedEpoxyResin, Polybenzimidazole, Kevlar, Radox), Gold, Electrum, MV, null),
            new CircuitBoard(EXTREME_CIRCUIT_BOARD, List.of(Epoxy, ReinforcedEpoxyResin, Polybenzimidazole, Kevlar, Radox), Aluminium, Electrum, HV, null),
            new CircuitBoard(ELITE_CIRCUIT_BOARD, List.of(ReinforcedEpoxyResin, Polybenzimidazole, Kevlar, Radox), Palladium, Platinum, EV, null),
            new CircuitBoard(WETWARE_CIRCUIT_BOARD, List.of(ReinforcedEpoxyResin, Polybenzimidazole, Kevlar, Radox), EnrichedHolmium, NiobiumTitanium, IV, SterileGrowthMedium),
            new CircuitBoard(ULTRA_BIO_MUTATED_CIRCUIT_BOARD, List.of(Polybenzimidazole, Kevlar, Radox), Neutronium, EnrichedNaquadahTriniumEuropiumDuranide, LuV, SterilizedBioCatalystMedium)
    );
    public static void registerPcbFactoryRecipes(Consumer<FinishedRecipe> provider) {
        for (CircuitBoard circuit : CircuitBoards) {
            int count = 0;
            for (Material board : circuit.boards) {
                GTRecipeBuilder recipe = PCB_FACTORY_RECIPES.recipeBuilder(String.format("pcb_%s_%s_1", circuit.output.get().toString(), board.getUnlocalizedName()))
                        .inputItems(plate, board, 1)
                        .inputItems(foil, circuit.foil, 16 + count*8)
                        .inputItems(foil, circuit.foil2, 16 + count*8)
                        .inputFluids(Iron3Chloride.getFluid(250))
                        .inputFluids(SulfuricAcid.getFluid(500))
                        .circuitMeta(1)
                        .outputItems((Item)circuit.output.get(), 8 * ++count)
                        .EUt(VA[circuit.tier]).duration(500);
                if (circuit.fluid != null)
                    recipe.inputFluids(circuit.fluid.getFluid(2000));
                recipe.save(provider);
            }
        }
    }
}
