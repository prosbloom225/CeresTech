package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;
import static com.prosbloom.cerestech.data.CTItems.*;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static java.util.Map.ofEntries;

public class StoneCentrifugeRecipes {
    public record StoneCentrifuge(Item stone, Material[] materials, FluidStack fluid, int duration, int tier){}

    public static List<StoneCentrifuge> stones = List.of(
            // TODO - need to only allow certain dust at certain tiers/voltages
            // T1 - EV
            new StoneCentrifuge(STONE_MOON.get(), new Material[]{SiliconDioxide, Manganese, Olivine, RareEarth, Magnesite, Tungstate}, null, 3240, LV),
            // T2 - IV
            new StoneCentrifuge(STONE_DEIMOS.get(), new Material[]{Magnesite, Lapis, Pitchblende, Cooperite, Neodymium, Tungstate}, SulfuricAcid.getFluid(1800), 2430, MV),
            new StoneCentrifuge(STONE_PHOBOS.get(), new Material[]{Iron, Bauxite, Molybdenite, Carbon, Uranium238, Plutonium239}, null, 2430, MV),
            new StoneCentrifuge(STONE_MARS.get(), new Material[]{Iron, Magnesium, Aluminium, Ruby, Diamond, IridiumMetalResidue}, null, 2430, MV),
            // T3 - LuV
            new StoneCentrifuge(STONE_ASTEROID.get(), new Material[]{Gold, Lead, Titanium, Ruby, Chromium, RarestMetalMixture}, null, 6480, HV),
            new StoneCentrifuge(STONE_GANYMEDE.get(), new Material[]{Titanium, Chromite, Galena, Diamond, Uranium238, PalladiumRaw}, null, 6480, HV),
            new StoneCentrifuge(STONE_CERES.get(), new Material[]{Iron, Beryllium, PlatinumRaw, Titanium, Tungsten, Naquadah}, null, 6480, HV),
            new StoneCentrifuge(STONE_CALLISTO.get(), new Material[]{Ice, Galena, Topaz, BlueTopaz, Lithium, Tungsten}, null, 6480, HV),
            new StoneCentrifuge(STONE_EUROPA.get(), new Material[]{SiliconDioxide, Iron, Lapis, Sodalite, Barium, Uranium238}, Nitrogen.getFluid(3600), 6480, HV),
            // T4 - UV
            new StoneCentrifuge(STONE_IO.get(), new Material[]{Sulfur, Tantalite, Tungsten, Ruby, Gold, IridiumMetalResidue}, RefineryGas.getFluid(3600), 4320, EV),
            new StoneCentrifuge(STONE_MERCURY.get(), new Material[]{Vanadium, Tungstate, Draconium, Titanium, Sodium, Diamond}, Helium3.getFluid(1800), 6480, EV),
            //new StoneCentrifuge(STONE_VENUS.get(), new Material[]{Carbon, Quantium, Mytryl, Amethyst, Mithril, Draconium}, CarbonDioxide.getFluid(18000), 6480, EV),
            // T5 - UHV
            new StoneCentrifuge(STONE_ENCELADUS.get(), new Material[]{Chromium, IridiumMetalResidue, RarestMetalMixture, MysteriousCrystal, Trinium, Naquadah}, Nitrogen.getFluid(5400), 111780, IV),
            new StoneCentrifuge(STONE_MIRANDA.get(), new Material[]{Tin, Tetrahedrite, IridiumMetalResidue, Carbon, Chromium, RarestMetalMixture}, Argon.getFluid(1800), 6480, IV),
            new StoneCentrifuge(STONE_TITAN.get(), new Material[]{Nickel, IridiumMetalResidue, RarestMetalMixture, Emerald, Gallium, Trinium}, Methane.getFluid(3600), 6480, IV),
            new StoneCentrifuge(STONE_OBERON.get(), new Material[]{IridiumMetalResidue, RarestMetalMixture, Naquadah, Uranium235, Plutonium241, Trinium}, Argon.getFluid(1800), 6480, IV),
            // T6 - UMV
            new StoneCentrifuge(STONE_TRITON.get(), new Material[]{Gold, Neodymium, RareEarth, Niobium, Yttrium, Gallium}, null, 6048, LuV),
            new StoneCentrifuge(STONE_PROTEUS.get(), new Material[]{Copper, Uraninite, Adamantium, Diamond, Uranium238, Plutonium239}, Radon.getFluid(360), 6480, LuV),
            // T7 - UEV
            new StoneCentrifuge(STONE_HAUMEA.get(), new Material[]{Gold, Naquadah, NetherStar, Lanthanum, Caesium, Cerium}, null, 12960, UV),
            new StoneCentrifuge(STONE_MAKEMAKE.get(), new Material[]{GarnetRed, GarnetYellow, BlackPlutonium, Gallium, Yttrium, Niobium}, null, 6480, UV),
            new StoneCentrifuge(STONE_PLUTO.get(), new Material[]{Thorium, Uranium238, BlackPlutonium, GarnetYellow, GarnetRed, Naquadah}, null, 6480, UV),
            // T8 - UIV
            new StoneCentrifuge(STONE_CENTAURI_B.get(), new Material[]{RarestMetalMixture, NaquadahEnriched, Neutronium, BlackPlutonium, CosmicNeutronium, InfinityCatalyst}, Mercury.getFluid(7200), 6480, UHV)
            //new StoneCentrifuge(STONE_BARNARDA_F.get(), new Material[]{Gallium, Yttrium, Niobium, Neutronium, Bedrockium, Unstable}, null, 7776, UHV),
            //new StoneCentrifuge(STONE_VEGA_B.get(), new Material[]{Uranium235, Plutonium241, Europium, Neutronium, Naquadah, AwakenedDraconium}, null, 6480, UHV),
            //new StoneCentrifuge(STONE_TCETI_E.get(), new Material[]{Lapis, Apatite, Bedrockium, Draconium, InfinityCatalyst, Trinium}, null, 6480, UHV),
            //new StoneCentrifuge(STONE_BARNARDA_E.get(), new Material[]{Niobium, Yttrium, Gallium, Neutronium, Bedrockium, Unstable}, null, 6480, UHV)
    );

    public static void registerStoneCentrifugeRecipes(Consumer<FinishedRecipe> provider) {
        for (StoneCentrifuge stone : stones){
            GTRecipeBuilder recipe = CENTRIFUGE_RECIPES.recipeBuilder(String.format("centrifuge_%s", stone.stone.toString()))
                    .inputItems(stone.stone)
                    .chancedOutput(dust, stone.materials[0], 9, 5000, 0)
                    .chancedOutput(dust, stone.materials[1], 9, 2000, 0)
                    .chancedOutput(dust, stone.materials[2], 9, 1000, 0)
                    .chancedOutput(dust, stone.materials[3], 4, 750, 0)
                    .chancedOutput(dust, stone.materials[4], 4, 500, 0)
                    .chancedOutput(dust, stone.materials[5], 4, 250, 0)
                    .duration(stone.duration).EUt(VA[stone.tier]);
            if (stone.fluid != null)
                recipe.outputFluids(stone.fluid);
            recipe.save(provider);
        }
    }
}
