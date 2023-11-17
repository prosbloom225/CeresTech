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
import static java.util.Map.ofEntries;

public class StoneCentrifugeRecipes {
    private record StoneCentrifuge(Item stone, Material[] materials, FluidStack fluid, int duration, int tier){}

    public static List<StoneCentrifuge> stones = List.of(
            new StoneCentrifuge(STONE_MOON.get(), new Material[]{SiliconDioxide, Manganese, Olivine, RareEarth, Magnesite, Tungstate}, null, 3240, MV),
            new StoneCentrifuge(STONE_DEIMOS.get(), new Material[]{Magnesite, Lapis, Pitchblende, Cooperite, Neodymium, Tungstate}, SulfuricAcid.getFluid(1800), 2430, HV),
            new StoneCentrifuge(STONE_PHOBOS.get(), new Material[]{Iron, Bauxite, Molybdenite, Carbon, Uranium238, Plutonium239}, null, 2430, HV),
            new StoneCentrifuge(STONE_MARS.get(), new Material[]{Iron, Magnesium, Aluminium, Ruby, Diamond, IridiumMetalResidue}, null, 2430, HV),
            new StoneCentrifuge(STONE_ASTEROID.get(), new Material[]{Gold, Lead, Titanium, Ruby, Chromium, RarestMetalMixture}, null, 6480, HV),
            new StoneCentrifuge(STONE_GANYMEDE.get(), new Material[]{Titanium, Chromite, Galena, Diamond, Uranium238, PalladiumRaw}, null, 6480, HV),
            new StoneCentrifuge(STONE_CERES.get(), new Material[]{Iron, Beryllium, PlatinumRaw, Titanium, Tungsten, Naquadah}, null, 6480, HV),
            new StoneCentrifuge(STONE_CALLISTO.get(), new Material[]{Ice, Galena, Topaz, BlueTopaz, Lithium, Tungsten}, null, 6480, HV),
            new StoneCentrifuge(STONE_EUROPA.get(), new Material[]{SiliconDioxide, Iron, Lapis, Sodalite, Barium, Uranium238}, Nitrogen.getFluid(3600), 6480, HV)
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
