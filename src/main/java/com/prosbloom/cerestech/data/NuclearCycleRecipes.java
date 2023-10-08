package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.prosbloom.cerestech.data.CTItems.WASTE_NUCLEAR;
import static com.prosbloom.cerestech.data.CTTagPrefixes.*;

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

    // Reactor - Depleted Uranium 238 Rod
    // LCR + Oxygen = Uranium 238 Oxide Rod
    // LCR + Nitric Acid + Boron Dust(noconsume) = Uranium 238 Depleted Fuel Nitrate Solution
    // LCR + Ferrite Mixture(noconsume) = Uranium 238 Depleted Fuel Nitride
    // Electrolyzer = Uranium Waste + Oxygen
    // Thermal Centrifuge = Neptunium Dust + Nuclear Waste(7.6%) + Uranium 238 Dust(30%)
    // Dehydrator +  Nuclear Waste = Lanthanide Group A Waste, Alkaline Waste, Metaloid Waste, etc

    public static void registerMainCycle(Consumer<FinishedRecipe> provider, Material fissile, Material output, int thermalVoltage) {
        CHEMICAL_RECIPES.recipeBuilder(fissile.getName() + "_oxide_rod")
                .inputItems(depletedRod, fissile, 1)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(depletedOxideRod, fissile, 1)
                .duration(300).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(fissile.getName() + "_depleted_fuel_nitrate")
                .inputItems(depletedOxideRod, fissile, 1)
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

    public static void registerWasteCycle(Consumer<FinishedRecipe> provider) {
        /*
        CHEMICAL_DEHYDRATOR.recipeBuilder("thermal_nuclear_waste")
                .inputItems(WASTE_NUCLEAR, 1)
                .outputItems(dust, output, 1)
                .chancedOutput(WASTE_NUCLEAR.asStack(), 1000, 0)
                .chancedOutput(dust, fissile, 1, 3000, 0)
                .duration(300).EUt(60)
                .save(provider);

         */
    }

}
