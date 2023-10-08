package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.prosbloom.cerestech.data.CTRecipeTypes.NUCLEAR_REACTOR_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.depletedFuel;
import static com.prosbloom.cerestech.data.CTTagPrefixes.fuelOxide;

public class NuclearReactorRecipes {

    private static final List<ReactorFuel> reactorFuels = Stream.of(
            new ReactorFuel("uranium_235", 23, 22),
            new ReactorFuel("plutonium_241", 29, 28),
            new ReactorFuel("americium", 31, 30),
            new ReactorFuel("neptunium", 25, 24),
            new ReactorFuel("curium", 35, 34),
            new ReactorFuel("berkelium", 37, 36),
            new ReactorFuel("californium", 42, 40),
            new ReactorFuel("einsteinium", 44, 42),
            new ReactorFuel("fermium", 48, 46),
            new ReactorFuel("mendelevium", 50, 48)
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
                NUCLEAR_REACTOR_RECIPES.recipeBuilder(r.material.getName() + "_reactor_" +i)
                        .inputItems(fuelOxide, r.material, i)
                        .circuitMeta(i)
                        .outputItems(depletedFuel, r.material, i)
                        .duration(100)
                        // TODO - when done testing
                        //.duration(16000)
                        // TODO - scale heat according to i
                        .EUt(r.voltage*i)
                        .save(provider);
        }
    }

    private static class ReactorFuel{
        public Material material;
        public int voltage;
        public int heat;

        public ReactorFuel(String name, int voltage, int heat) {
            this.voltage = voltage;
            this.heat = heat;
            this.material = GTRegistries.MATERIALS.get(name);
        }
    }
}
