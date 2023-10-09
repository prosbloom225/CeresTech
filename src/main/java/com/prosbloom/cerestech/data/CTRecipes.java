package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.prosbloom.cerestech.data.recipes.NuclearCycleRecipes;
import com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes;
import com.prosbloom.cerestech.data.recipes.TreeFarmRecipes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.MV;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.MACERATOR;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.*;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.CABLE;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static com.prosbloom.cerestech.data.CTFluids.Coolant;
import static com.prosbloom.cerestech.data.CTMaterials.BerylliumFluoride;
import static com.prosbloom.cerestech.data.CTMaterials.LithiumFluoride;
import static com.prosbloom.cerestech.data.recipes.HotCoolantTurbineRecipes.registerHotCoolantTurbineRecipes;
import static com.prosbloom.cerestech.machines.CTMachines.*;

public class CTRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CraftingComponent.initializeComponents();
        registerManualRecipes(provider);
        registerMixerRecipes(provider);
        registerChemicalReactorRecipes(provider);
        registerHotCoolantTurbineRecipes(provider);

        TreeFarmRecipes.registerTreeFarmRecipes(provider);
        NuclearReactorRecipes.registerNuclearReactorRecipes(provider);
        NuclearCycleRecipes.registerNuclearCycleRecipes(provider);
    }

    private static void registerManualRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "tree_farm", TREE_FARM.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.MV_CIRCUITS, 'P', new UnificationEntry(plate, Aluminium), 'B', ELECTRIC_PISTON_MV.asStack(), 'M', ELECTRIC_MOTOR_MV.asStack(), 'X', MACERATOR[MV].asStack(), 'K', new UnificationEntry(cableGtSingle, Aluminium));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "nuclear_reactor", NUCLEAR_REACTOR.asStack(),
                "GDG", "RHR", "GDG",
                'G', new UnificationEntry(plateDouble, HastelloyX),
                'D', new UnificationEntry(plateDouble, Thorium),
                'R', ROBOT_ARM_EV.asStack(),
                'H', GTMachines.HULL[GTValues.EV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "hot_coolant_turbine", HOT_COOLANT_TURBINE.asStack(),
                "CGC", "GHG", "PGP",
                'G', new UnificationEntry(plate, HastelloyX),
                'C', CustomTags.HV_CIRCUITS,
                'H', GTMachines.HULL[GTValues.EV].asStack(),
                'P', new UnificationEntry(pipeLargeFluid, Tungsten));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "gas_centrifuge", GAS_CENTRIFUGE.asStack(),
                "PMP", "MHM", "PMP",
                'P', ELECTRIC_PUMP_EV.asStack(),
                'M', ELECTRIC_MOTOR_EV.asStack(),
                'H', GTMachines.HULL[GTValues.EV].asStack());


        registerMachineRecipe(provider, DEHYDRATOR, "WCW", "WMW", "PRP", 'M', HULL, 'R', ROBOT_ARM, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, DECAY_CHAMBER, "RCR", "EHE", "WCW", 'R', new UnificationEntry(rod, Uranium238), 'E', EMITTER, 'H', HULL, 'C', CIRCUIT, 'W', CABLE);
    }

    private static void registerMixerRecipes(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("coolant_dust")
                .inputItems(dust, LithiumFluoride , 2)
                .inputItems(dust, BerylliumFluoride, 3)
                .outputItems(dust, Coolant, 5)
                .duration(600).EUt(120)
                .save(provider);

    }
    private static void registerChemicalReactorRecipes(Consumer<FinishedRecipe> provider) {
        CHEMICAL_RECIPES.recipeBuilder("beryllium_fluoride")
                .inputItems(dust, Beryllium, 1)
                .inputFluids(Fluorine.getFluid(2000))
                .outputItems(dust, BerylliumFluoride, 3)
                .duration(30).EUt(30)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("lithium_fluoride")
                .inputItems(dust, Lithium, 1)
                .inputFluids(Fluorine.getFluid(1000))
                .outputItems(dust, LithiumFluoride , 2)
                .duration(30).EUt(30)
                .save(provider);

    }


}
