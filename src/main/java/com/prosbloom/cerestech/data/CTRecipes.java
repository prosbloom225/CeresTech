package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.IV;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.ELECTRIC_MOTOR_IV;
import static com.gregtechceu.gtceu.common.data.GTItems.ELECTRIC_PISTON_IV;
import static com.gregtechceu.gtceu.common.data.GTMachines.MACERATOR;
import static com.gregtechceu.gtceu.common.data.GTMachines.MIXER;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.prosbloom.cerestech.data.CTFluids.Coolant;
import static com.prosbloom.cerestech.data.CTMaterials.BerylliumFluoride;
import static com.prosbloom.cerestech.data.CTMaterials.LithiumFluoride;
import static com.prosbloom.cerestech.data.CTTagPrefixes.dustOxide;
import static com.prosbloom.cerestech.data.CTTagPrefixes.fuelOxide;
import static com.prosbloom.cerestech.data.HotCoolantTurbineRecipes.registerHotCoolantTurbineRecipes;
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
        NuclearCycleRecipes.registerNuclearFuelCycleRecipes(provider);
    }

    private static void registerManualRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "tree_farm", TREE_FARM.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.IV_CIRCUITS, 'P', new UnificationEntry(plate, Aluminium), 'B', ELECTRIC_PISTON_IV.asStack(), 'M', ELECTRIC_MOTOR_IV.asStack(), 'X', MACERATOR[IV].asStack(), 'K', new UnificationEntry(cableGtSingle, Platinum));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "nuclear_reactor", NUCLEAR_REACTOR.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.IV_CIRCUITS, 'P', new UnificationEntry(plate, Titanium), 'B', ELECTRIC_PISTON_IV.asStack(), 'M', ELECTRIC_MOTOR_IV.asStack(), 'X', MACERATOR[IV].asStack(), 'K', new UnificationEntry(cableGtSingle, Platinum));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "hot_coolant_turbine", HOT_COOLANT_TURBINE.asStack(),
                "CGC", "GHG", "PGP",
                'G', new UnificationEntry(TagPrefix.gear, Stellite100),
                'C', CustomTags.HV_CIRCUITS,
                'H', GTMachines.HULL[GTValues.EV].asStack(),
                'P', new UnificationEntry(TagPrefix.pipeLargeFluid, Ultimet));

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
