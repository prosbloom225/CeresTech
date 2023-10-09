package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NUCLEAR_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.depletedFuel;
import static com.prosbloom.cerestech.data.CTTagPrefixes.fuelOxide;

public class NuclearReactorRecipes {

    public static final List<ReactorFuel> reactorFuels = Stream.of(
            new ReactorFuel(Uranium238, Uranium238, Uranium235, Uranium234, Neptunium, VA[MV], 22),
            //237*10000, 239*6000, 235*4000
            new ReactorFuel(Neptunium, Neptunium237, Neptunium239, Neptunium235, Plutonium241, VA[MV], 24),
            //241*200, 244*19780, 240*20
            new ReactorFuel(Plutonium241, Plutonium241, Plutonium244, Plutonium240, Americium,VA[HV], 28),
            // 243*10000, 245*6000, 241*4000
            new ReactorFuel(Americium, Americium243, Americium245, Americium241, Curium, VA[HV], 30),
            //247*200, 250*19780, 246*20
            new ReactorFuel(Curium, Curium247, Curium250, Curium246, Berkelium, VA[HV], 34),
            // 249*10000, 251*6000, 247*4000
            new ReactorFuel(Berkelium, Berkelium249, Berkelium251, Berkelium247, Californium, VA[EV], 36),
            //253*200, 252*19780, 256*20
            new ReactorFuel(Californium, Californium253, Californium252, Californium256, Einsteinium, VA[EV], 40),
            // 255*10000, 257*6000, 253*4000
            new ReactorFuel(Einsteinium, Einsteinium255, Einsteinium257, Einsteinium253, Fermium, VA[IV], 42),
            // 259*200, 258*19780, 262*20
            new ReactorFuel(Fermium, Fermium259, Fermium258, Fermium262, Mendelevium, VA[IV], 46),
            // 261*10000, 263*6000, 259*4000
            new ReactorFuel(Mendelevium, Mendelevium261, Mendelevium263, Mendelevium259, Mendelevium, VA[LuV], 48)
            // TODO - mendelevium waste cycle doesnt have uptier output -- duping for now
    ).toList();

    // TODO - remove these static lists and pull from reactorFuels ... need to change CTTagPrefixes
    public static List<String> fertileMaterials= Stream.of("uranium", "thorium")
            .collect(Collectors.toList());
    public static List<String> fissileMaterials = Stream.of("uranium_235", "plutonium_241", "americium", "neptunium",
                    "curium", "berkelium", "californium", "einsteinium", "fermium", "mendelevium")

            .collect(Collectors.toList());
    // TODO - need to add reactor recipes dynamically - scale heat/steam output with tier
    // TODO - reactor power scales with tier
    // TODO - breeder reactor
    public static void registerNuclearReactorRecipes(Consumer<FinishedRecipe> provider) {
        for (ReactorFuel r: reactorFuels) {
            for (int i=1; i<9; i++)
                NUCLEAR_REACTOR_RECIPES.recipeBuilder(r.getName() + "_reactor_" +i)
                        .inputItems(fuelOxide, r.baseElement, i)
                        .circuitMeta(i)
                        .outputItems(depletedFuel, r.baseElement, i)
                        .duration(100)
                        // TODO - when done testing
                        //.duration(16000)
                        .EUt(VA[MV]*i)
                        .save(provider);
        }
    }

    public static class ReactorFuel{
        public String name;
        public Material baseElement;
        public Material isotopeFuelOxide;
        public Material isotopeFuelPure;
        public Material isotopeDecay;
        public Material uptierMaterial;
        public int voltage;
        public int heat;

        public ReactorFuel(Material baseElement,
                           Material isotopeFuelOxide,
                           Material isotopeFuelPure,
                           Material isotopeDecay,
                           Material uptierMaterial, int voltage, int heat) {
            this.name = baseElement.getName();
            this.baseElement = GTRegistries.MATERIALS.get(name);
            this.uptierMaterial = uptierMaterial;
            this.isotopeFuelOxide = isotopeFuelOxide;
            this.isotopeFuelPure = isotopeFuelPure;
            this.isotopeDecay = isotopeDecay;
            this.voltage = voltage;
            this.heat = heat;
        }
        public String getName() { return this.name;}
        public int getHeat() { return this.heat;}
    }
}
