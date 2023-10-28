package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NUCLEAR_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.depletedFuel;
import static com.prosbloom.cerestech.data.CTTagPrefixes.fuelOxide;

public class NuclearReactorRecipes {

    public static final List<ReactorFuel> reactorFuels = Stream.of(
            new ReactorFuel(Uranium238, Uranium235, Uranium238, Uranium234, Neptunium, VA[MV], 22, true),
            new ReactorFuel(Neptunium, Neptunium237, Neptunium239, Neptunium235, Plutonium241, VA[MV], 24, true),
            new ReactorFuel(Plutonium241, Plutonium241, Plutonium244, Plutonium240, Americium,VA[HV], 28, false),
            new ReactorFuel(Americium, Americium243, Americium245, Americium241, Curium, VA[HV], 30, true),
            new ReactorFuel(Curium, Curium247, Curium250, Curium246, Berkelium, VA[HV], 34, false),
            new ReactorFuel(Berkelium, Berkelium249, Berkelium251, Berkelium247, Californium, VA[EV], 36, true),
            new ReactorFuel(Californium, Californium253, Californium252, Californium256, Einsteinium, VA[EV], 40, false),
            new ReactorFuel(Einsteinium, Einsteinium255, Einsteinium257, Einsteinium253, Fermium, VA[IV], 42, true),
            new ReactorFuel(Fermium, Fermium259, Fermium258, Fermium262, Mendelevium, VA[IV], 46, false),
            new ReactorFuel(Mendelevium, Mendelevium261, Mendelevium263, Mendelevium259, Mendelevium, VA[LuV], 48, true)
            // TODO - mendelevium waste cycle doesnt have uptier output -- duping for now
    ).toList();

    // TODO - fuel pure additive recipes
    // TODO - breeder reactor
    public static void registerNuclearReactorRecipes(Consumer<FinishedRecipe> provider) {
        for (ReactorFuel r: reactorFuels) {
            for (int i=1; i<9; i++)
                NUCLEAR_REACTOR_RECIPES.recipeBuilder(r.isotopeFuelOxide.getName() + "_reactor_" +i)
                        .inputItems(fuelOxide, r.isotopeFuelOxide, i)
                        .circuitMeta(i)
                        .outputItems(depletedFuel, r.isotopeFuelOxide, i)
                        // TODO - testing
                        //.duration(100)
                        .duration(16000)
                        // TODO - reactor power req should probably scale with tier of fuel, for now setting to MV
                        .EUt(VA[MV]*i)
                        .save(provider);
        }
    }

    public static class ReactorFuel{
        public Material baseElement;

        public Material getIsotopeFuelOxide() {
            return isotopeFuelOxide;
        }

        // TODO - refactor isotopes to be a array instead of 3 separate fields
        public Material getIsotopeFuelPure() {
            return isotopeFuelPure;
        }

        // TODO - decay chamber and recipes for isotopeDecay
        public Material getIsotopeDecay() {
            return isotopeDecay;
        }

        public Material isotopeFuelOxide;
        public Material isotopeFuelPure;
        public Material isotopeDecay;
        public Material uptierMaterial;
        public int voltage;
        public int heat;
        public boolean largeFuelOxide;

        public ReactorFuel(Material baseElement,
                           Material isotopeFuelOxide,
                           Material isotopeFuelPure,
                           Material isotopeDecay,
                           Material uptierMaterial, int voltage, int heat, boolean largeFissile) {
            this.baseElement = baseElement;
            this.uptierMaterial = uptierMaterial;
            this.isotopeFuelOxide = isotopeFuelOxide;
            this.isotopeFuelPure = isotopeFuelPure;
            this.isotopeDecay = isotopeDecay;
            this.voltage = voltage;
            this.heat = heat;
            this.largeFuelOxide = largeFissile;
        }
        public int getHeat() { return this.heat;}
    }
}
