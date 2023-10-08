package com.prosbloom.cerestech.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.prosbloom.cerestech.data.CTItems.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.DEHYDRATOR_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.*;
import static com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes.fertileMaterials;
import static com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes.fissileMaterials;

public class NuclearCycleRecipes {
    public static void registerNuclearCycleRecipes(Consumer<FinishedRecipe> provider){
        registerMainCycle(provider, Uranium238, Neptunium, VA[MV]);
        registerMainCycle(provider, Neptunium, Plutonium241, VA[MV]);
        registerMainCycle(provider, Plutonium241, Americium, VA[HV]);
        registerMainCycle(provider, Americium, Curium, VA[HV]);
        registerMainCycle(provider, Curium, Berkelium, VA[EV]);
        registerMainCycle(provider, Berkelium, Californium, VA[EV]);
        registerMainCycle(provider, Californium, Einsteinium, VA[IV]);
        registerMainCycle(provider, Einsteinium, Fermium, VA[IV]);
        registerMainCycle(provider, Fermium, Mendelevium, VA[LuV]);
        registerMainCycle(provider, Mendelevium, Mendelevium, VA[LuV]);
        // TODO - mendelevium waste cycle doesnt have uptier output -- duping for now
    }
    public static void registerNuclearFuelCycleRecipes(Consumer<FinishedRecipe> provider) {
        for (String s: fertileMaterials)
            registerFuelCycle(provider, GTRegistries.MATERIALS.get(s));
        for (String s: fissileMaterials)
            registerFuelCycle(provider, GTRegistries.MATERIALS.get(s));
    }

    // Reactor - Depleted Uranium 238 Rod
    // LCR + Oxygen = Uranium 238 Oxide Rod
    // LCR + Nitric Acid + Boron Dust(noconsume) = Uranium 238 Depleted Fuel Nitrate Solution
    // LCR + Ferrite Mixture(noconsume) = Uranium 238 Depleted Fuel Nitride
    // Electrolyzer = Uranium Waste + Oxygen
    // Thermal Centrifuge = Neptunium Dust + Nuclear Waste(7.6%) + Uranium 238 Dust(30%)
    // Dehydrator +  Nuclear Waste = Lanthanide Group A Waste, Alkaline Waste, Metaloid Waste, etc

    public static void registerMainCycle(Consumer<FinishedRecipe> provider, Material fissile, Material output, int thermalVoltage) {
        CHEMICAL_RECIPES.recipeBuilder(fissile.getName() + "_oxide_rod")
                .inputItems(depletedFuel, fissile, 1)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(depletedFuelOxide, fissile, 1)
                .duration(300).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(fissile.getName() + "_depleted_fuel_nitrate")
                .inputItems(depletedFuelOxide, fissile, 1)
                .inputFluids(NitricAcid.getFluid(1000))
                .notConsumable(dust, GTMaterials.FerriteMixture)
                .notConsumable(dust, GTMaterials.Boron)
                .outputItems(depletedFuelNitride, fissile, 1)
                .duration(1000).EUt(30)
                .save(provider);
        ELECTROLYZER_RECIPES.recipeBuilder(fissile.getName() + "_waste")
                .inputItems(depletedFuelNitride, fissile, 1)
                .outputItems(waste, fissile, 1)
                .outputFluids(Nitrogen.getFluid(1000))
                .duration(1000).EUt(30)
                .save(provider);
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(fissile.getName() + "_thermal")
                .inputItems(waste, fissile, 1)
                .outputItems(dust, output, 1)
                .chancedOutput(WASTE_NUCLEAR.asStack(), 1000, 0)
                .chancedOutput(dust, fissile, 1, 3000, 0)
                .duration(300).EUt(thermalVoltage)
                .save(provider);
    }

    public static void registerFuelCycle(Consumer<FinishedRecipe> provider, Material fissile) {
        EXTRUDER_RECIPES.recipeBuilder(fissile.getName() + "_fuel_pure")
                .inputItems(ingot, fissile, 1)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .outputItems(fuelPure, fissile, 1)
                .duration(200).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(fissile.getName() + "_dust_oxide")
                .inputItems(ingot, fissile, 1)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dustOxide, fissile, 1)
                .duration(300).EUt(30)
                .save(provider);
        EXTRUDER_RECIPES.recipeBuilder(fissile.getName() + "_fuel_oxide")
                .inputItems(dustOxide,  fissile, 1)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .outputItems(fuelOxide, fissile, 1)
                .duration(200).EUt(30)
                .save(provider);
    }

    public static void registerWasteCycle(Consumer<FinishedRecipe> provider) {
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_nuclear_waste")
                .inputItems(WASTE_NUCLEAR, 1)
                .chancedOutput(WASTE_LANTHANIDE_GROUP_A.asStack(), 1000, 1)
                .chancedOutput(WASTE_LANTHANIDE_GROUP_B.asStack(), 1000, 1)
                .chancedOutput(WASTE_ALKALINE.asStack(), 1000, 1)
                .chancedOutput(WASTE_HEAVY_METAL.asStack(), 1000, 1)
                .chancedOutput(WASTE_METAL_GROUP_A.asStack(), 1000, 1)
                .chancedOutput(WASTE_METAL_GROUP_B.asStack(), 1000, 1)
                .chancedOutput(WASTE_METAL_GROUP_C.asStack(), 1000, 1)
                .chancedOutput(WASTE_NONMETAL.asStack(), 1000, 1)
                .chancedOutput(WASTE_METALOID.asStack(), 1000, 1)
                .duration(300).EUt(32)
                .save(provider);

        // TODO - missing waste outputs ...commenting for now.. also need to take a look at rates, gcy is even higher than these
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_lanthanide_a")
                .inputItems(WASTE_LANTHANIDE_GROUP_A, 9)
                //.chancedOutput(dust, Dysprosium, 2, 4000, 3000)
                //.chancedOutput(dust, Holmium, 2, 4000, 3000)
                //.chancedOutput(dust, Erbium, 2, 4000, 3000)
                //.chancedOutput(dust, Thulium, 2, 4000, 3000)
                //.chancedOutput(dust, Ytterbium, 2, 4000, 3000)
                .chancedOutput(dust, Lutetium, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_lanthanide_b")
                .inputItems(WASTE_LANTHANIDE_GROUP_B, 9)
                .chancedOutput(dust, Lanthanum, 2, 4000, 3000)
                .chancedOutput(dust, Cerium, 2, 4000, 3000)
                //.chancedOutput(dust, Praseodymium, 2, 4000, 3000)
                .chancedOutput(dust, Neodymium, 2, 4000, 3000)
                //.chancedOutput(dust, Promethium, 2, 4000, 3000)
                .chancedOutput(dust, Samarium, 2, 4000, 3000)
                .chancedOutput(dust, Europium, 2, 4000, 3000)
                //.chancedOutput(dust, Gadolinium, 2, 4000, 3000)
                //.chancedOutput(dust, Terbium, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_alkaline")
                .inputItems(WASTE_ALKALINE, 9)
                .chancedOutput(WASTE_ALKALINE.asStack(), 1000, 1)
                //.chancedOutput(dust, Rubidium, 2, 4000, 3000)
                //.chancedOutput(dust, Strontium, 2, 4000, 3000)
                .chancedOutput(dust, Caesium, 2, 4000, 3000)
                .chancedOutput(dust, Barium, 2, 4000, 3000)
                //.chancedOutput(dust, Francium, 2, 4000, 3000)
                //.chancedOutput(dust, Radium, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_heavy_metal")
                .inputItems(WASTE_HEAVY_METAL, 9)
                .chancedOutput(dust, Zinc, 2, 4000, 3000)
                .chancedOutput(dust, Gallium, 2, 4000, 3000)
                .chancedOutput(dust, Cadmium, 2, 4000, 3000)
                .chancedOutput(dust, Indium, 2, 4000, 3000)
                .chancedOutput(dust, Tin, 2, 4000, 3000)
                //.chancedOutput(dust, Thallium, 2, 4000, 3000)
                .chancedOutput(dust, Lead, 2, 4000, 3000)
                .chancedOutput(dust, Bismuth, 2, 4000, 3000)
                //.chancedOutput(dust, Polonium, 2, 4000, 3000)
                .chancedOutput(Mercury.getFluid(2250), 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_metal_group_a")
                .inputItems(WASTE_METAL_GROUP_A, 9)
                //.chancedOutput(dust, Hafnium, 2, 4000, 3000)
                .chancedOutput(dust, Tantalum, 2, 4000, 3000)
                .chancedOutput(dust, Tungsten, 2, 4000, 3000)
                .chancedOutput(dust, Osmium, 2, 4000, 3000)
                .chancedOutput(dust, Iridium, 2, 4000, 300)
                .chancedOutput(dust, Platinum, 2, 4000, 300)
                .chancedOutput(dust, Gold, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_metal_group_b")
                .inputItems(WASTE_METAL_GROUP_B, 9)
                .chancedOutput(dust, Yttrium, 2, 4000, 3000)
                //.chancedOutput(dust, Zirconium, 2, 4000, 3000)
                .chancedOutput(dust, Niobium, 2, 4000, 3000)
                .chancedOutput(dust, Molybdenum, 2, 4000, 3000)
                //.chancedOutput(dust, Technetium, 2, 4000, 300)
                .chancedOutput(dust, Ruthenium, 2, 4000, 300)
                .chancedOutput(dust, Rhodium, 2, 4000, 3000)
                .chancedOutput(dust, Palladium, 2, 4000, 3000)
                .chancedOutput(dust, Silver, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_metal_group_c")
                .inputItems(WASTE_METAL_GROUP_C, 9)
                .chancedOutput(dust, Iron, 2, 4000, 300)
                .chancedOutput(dust, Cobalt, 2, 4000, 3000)
                .chancedOutput(dust, Nickel, 2, 4000, 3000)
                .chancedOutput(dust, Copper, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_nonmetal")
                .inputItems(WASTE_NONMETAL, 9)
                //.chancedOutput(dust, Selenium, 2, 4000, 3000)
                //.chancedOutput(dust, Iodine, 2, 4000, 3000)
                .chancedOutput(Krypton.getFluid(2250), 4000, 3000)
                .chancedOutput(Xenon.getFluid(4500), 4000, 3000)
                .chancedOutput(Radon.getFluid(9000), 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
        DEHYDRATOR_RECIPES.recipeBuilder("dehydrator_metaloid")
                .inputItems(WASTE_METALOID, 9)
                //.chancedOutput(dust, Germanium, 2, 4000, 3000)
                .chancedOutput(dust, Arsenic, 2, 4000, 3000)
                .chancedOutput(dust, Antimony, 2, 4000, 3000)
                //.chancedOutput(dust, Tellurium, 2, 4000, 3000)
                //.chancedOutput(dust, Astatine, 2, 4000, 3000)
                //.chancedOutput(dust, Actinium, 2, 4000, 3000)
                .duration(300).EUt(32)
                .save(provider);
    }

}
