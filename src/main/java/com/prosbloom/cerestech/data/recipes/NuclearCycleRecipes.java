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
import static com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes.*;

public class NuclearCycleRecipes {
    public static void registerNuclearCycleRecipes(Consumer<FinishedRecipe> provider) {
        for (ReactorFuel rf : reactorFuels) {
            registerFuelCycle(provider, rf);
            registerMainCycle(provider, rf);
            registerCentrifugeCycle(provider, rf);
        }
        registerWasteProducts(provider);
    }

    // TODO  - Centrifuge cycle
    // --- Centrifuge Cycle
    // LCR + Dust + Nitric Acid*2000 = Uranium Nitrate*3 + Hydrogen*2000
    // EBf *3 = Uranium Dioxide + Nitric Oxide*2000
    // LCR + Chlorine*6000 = Uranium Hexachloride + Oxygen*2000
    // LCR + Hydrofluoric Acid*6000 = Uranium Hexafluoride*1000 + Hydrochloric Acid*6000
    // Gas Centrifuge 20000 = 234*29,235*200,238*19780 Hexafluoride
    // Cracker + Steam*3000 = Steam Cracked 234,234,235 Hexafluoride*1000
    // EBF + 0 = Uranium 234,235,238 Dioxide*3 + Hydrofluoric Acid*6000
    // EBF = Uranium 234,235,238 Ingot + Oxygen*2000
    private static void registerCentrifugeCycle(Consumer<FinishedRecipe> provider,ReactorFuel rf) {

    }




    // --- Depleted Cycle
    // Reactor - Depleted Uranium 238 Rod
    // LCR + Oxygen = Uranium 238 Oxide Rod
    // LCR + Nitric Acid + Boron Dust(noconsume) = Uranium 238 Depleted Fuel Nitrate Solution
    // LCR + Ferrite Mixture(noconsume) = Uranium 238 Depleted Fuel Nitride
    // Electrolyzer = Uranium Waste + Oxygen
    // Thermal Centrifuge = Neptunium Dust + Nuclear Waste(7.6%) + Uranium 238 Dust(30%)
    // Dehydrator +  Nuclear Waste = Lanthanide Group A Waste, Alkaline Waste, Metaloid Waste, etc
    private static void registerMainCycle(Consumer<FinishedRecipe> provider, ReactorFuel rf){
        CHEMICAL_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_depleted_fuel")
                .inputItems(depletedFuel, rf.isotopeFuelOxide, 1)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(depletedFuelOxide, rf.isotopeFuelOxide, 1)
                .duration(300).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_depleted_fuel_nitrate")
                .inputItems(depletedFuelOxide, rf.isotopeFuelOxide, 1)
                .inputFluids(NitricAcid.getFluid(1000))
                .notConsumable(dust, FerriteMixture)
                .notConsumable(dust, Boron)
                .outputItems(depletedFuelNitride, rf.isotopeFuelOxide, 1)
                .duration(1000).EUt(30)
                .save(provider);
        ELECTROLYZER_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_waste")
                .inputItems(depletedFuelNitride, rf.isotopeFuelOxide, 1)
                .outputItems(waste, rf.isotopeFuelOxide, 1)
                .outputFluids(Nitrogen.getFluid(1000))
                .duration(1000).EUt(30)
                .save(provider);
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_thermal")
                .inputItems(waste, rf.isotopeFuelOxide, 1)
                .outputItems(dust, rf.uptierMaterial, 1)
                .chancedOutput(WASTE_NUCLEAR.asStack(), 1000, 0)
                .chancedOutput(dust, rf.isotopeFuelOxide, 1, 3000, 0)
                .duration(300).EUt(rf.voltage)
                .save(provider);
    }

    private static void registerFuelCycle(Consumer<FinishedRecipe> provider, ReactorFuel rf) {
        EXTRUDER_RECIPES.recipeBuilder(rf.isotopeFuelPure.getName() + "_fuel_pure")
                .inputItems(ingot, rf.isotopeFuelPure, 1)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .outputItems(fuelPure, rf.isotopeFuelPure, 1)
                .duration(200).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_dust_oxide")
                .inputItems(ingot, rf.isotopeFuelOxide, 1)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dustOxide, rf.isotopeFuelOxide, 1)
                .duration(300).EUt(30)
                .save(provider);
        EXTRUDER_RECIPES.recipeBuilder(rf.isotopeFuelOxide.getName() + "_fuel_oxide")
                .inputItems(dustOxide,  rf.isotopeFuelOxide, 1)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .outputItems(fuelOxide, rf.isotopeFuelOxide, 1)
                .duration(200).EUt(30)
                .save(provider);
    }

    private static void registerWasteProducts(Consumer<FinishedRecipe> provider) {
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
