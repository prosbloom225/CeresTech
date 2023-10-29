package com.prosbloom.cerestech.data;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.prosbloom.cerestech.data.recipes.*;
import com.prosbloom.cerestech.machines.multiblock.part.QuadFluidHatchPartMachine;
import com.prosbloom.cerestech.machines.multiblock.part.RedoxPartMachine;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import javax.swing.*;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.*;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.CABLE;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.PUMP;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static com.prosbloom.cerestech.data.CTBlocks.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTTagPrefixes.dustOxide;
import static com.prosbloom.cerestech.data.recipes.HotCoolantTurbineRecipes.registerHotCoolantTurbineRecipes;
import static com.prosbloom.cerestech.machines.CTMachines.*;

public class CTRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CraftingComponent.initializeComponents();
        registerManualRecipes(provider);
        registerMixerRecipes(provider);
        registerAssemblerRecipes(provider);
        registerFormingPressRecipes(provider);
        registerChemicalReactorRecipes(provider);
        registerHotCoolantTurbineRecipes(provider);
        registerCentrifugeRecipes(provider);
        registerElectrolyzerRecipes(provider);
        registerBlastFurnaceRecipes(provider);
        registerAssemblyLineRecipes(provider);

        IndustrialGreenhouseRecipes.registerIndustrialGreenhouseRecipes(provider);
        NuclearReactorRecipes.registerNuclearReactorRecipes(provider);
        NuclearCycleRecipes.registerNuclearCycleRecipes(provider);
        NaquadahReactorRecipes.registerNaquadahReactorRecipes(provider);
        HeatExchangerRecipes.registerHeatExchangerRecipes(provider);
    }

    private static void registerManualRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "industrial_greenhouse", INDUSTRIAL_GREENHOUSE.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.MV_CIRCUITS, 'P', new UnificationEntry(plate, Aluminium), 'B', ELECTRIC_PISTON_MV.asStack(), 'M', ELECTRIC_MOTOR_MV.asStack(), 'X', MACERATOR[MV].asStack(), 'K', new UnificationEntry(cableGtSingle, Aluminium));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "nuclear_reactor", NUCLEAR_REACTOR.asStack(),
                "GDG", "RHR", "GDG",
                'G', new UnificationEntry(plateDouble, HastelloyX),
                'D', new UnificationEntry(plateDouble, Thorium),
                'R', ROBOT_ARM_EV.asStack(),
                'H', HULL[EV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "hot_coolant_turbine", HOT_COOLANT_TURBINE.asStack(),
                "CGC", "GHG", "PGP",
                'G', new UnificationEntry(plate, HastelloyX),
                'C', CustomTags.IV_CIRCUITS,
                'H', HULL[IV].asStack(),
                'P', new UnificationEntry(pipeLargeFluid, TungstenSteel));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "gas_centrifuge", GAS_CENTRIFUGE.asStack(),
                "PMP", "MHM", "PMP",
                'P', ELECTRIC_PUMP_EV.asStack(),
                'M', ELECTRIC_MOTOR_EV.asStack(),
                'H', HULL[EV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "large_heat_exchanger", LARGE_HEAT_EXCHANGER.asStack(),
                "PWP", "WHW", "PWP",
                'P', ELECTRIC_PUMP_EV.asStack(),
                'W', new UnificationEntry(pipeLargeFluid, Titanium),
                'H', HULL[EV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "industrial_coke_oven", INDUSTRIAL_COKE_OVEN.asStack(),
                "PCP", "WHW", "PCP",
                'P', new UnificationEntry(plate, TantalumCarbide),
                'C', CustomTags.EV_CIRCUITS,
                'W', PYROLYSE_OVEN.get(),
                'H', HULL[EV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "volcanus", VOLCANUS.asStack(),
                "GCG", "RHR", "PCP",
                'P', new UnificationEntry(plateDouble, HastelloyX),
                'G', new UnificationEntry(gear, HastelloyX),
                'C', CustomTags.EV_CIRCUITS,
                'R', ROBOT_ARM_IV,
                'H', CASING_VOLCANUS.get());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "volcanus_casing", CASING_VOLCANUS.asStack(),
                "PCP", "CBC", "PGP",
                'P', new UnificationEntry(plateDouble, HastelloyX),
                'G', new UnificationEntry(gear, HastelloyX),
                'B', new UnificationEntry(frameGt, HastelloyX),
                'C', CustomTags.EV_CIRCUITS);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_power_station", CASING_POWER_STATION.asStack(),
                "SPS", "PFP", "SPS",
                'P', new UnificationEntry(plate, IncoloyMA956),
                'S', new UnificationEntry(screw, Titanium),
                'F', new UnificationEntry(frameGt, IncoloyMA956));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "power_station", POWER_STATION.asStack(),
                "PCP", "BRB", "PPP",
                'P', new UnificationEntry(plate, IncoloyMA956),
                'B', CASING_POWER_STATION.get(),
                'R', REDOX_CELL[EV].get(),
                'C', CustomTags.EV_CIRCUITS);


        // TODO - not working
        registerMachineRecipe(provider, DEHYDRATOR, "WCW", "WMW", "PRP", 'M', CraftingComponent.HULL, 'R', ROBOT_ARM, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(provider, DECAY_CHAMBER, "RCR", "EHE", "WCW", 'R', STICK_RADIOACTIVE, 'E', EMITTER, 'H', CraftingComponent.HULL, 'C', CIRCUIT, 'W', CABLE);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_ev", NAQUADAH_REACTOR[EV].asStack(), "RCR", "FHF", "WCW", 'R',
                new UnificationEntry(rod, Uranium238), 'F', FIELD_GENERATOR_EV, 'H', HULL[EV].asStack(), 'C', CustomTags.IV_CIRCUITS, 'W', new UnificationEntry(cableGtQuadruple, Aluminium));
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_iv", NAQUADAH_REACTOR[IV].asStack(), "RCR", "FHF", "WCW", 'R',
                new UnificationEntry(rod, Plutonium241), 'F', FIELD_GENERATOR_IV, 'H', HULL[IV].asStack(), 'C', CustomTags.LuV_CIRCUITS, 'W', new UnificationEntry(cableGtQuadruple, Tungsten));
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_luv", NAQUADAH_REACTOR[LuV].asStack(), "RCR", "FHF", "WCW", 'R',
                new UnificationEntry(rod, Europium), 'F', FIELD_GENERATOR_LuV, 'H', HULL[LuV].asStack(), 'C', CustomTags.ZPM_CIRCUITS, 'W', new UnificationEntry(cableGtQuadruple, HSSG));
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_zpm", NAQUADAH_REACTOR[ZPM].asStack(), "RCR", "FHF", "WCW", 'R',
                new UnificationEntry(rod, Americium), 'F', FIELD_GENERATOR_ZPM, 'H', HULL[ZPM].asStack(), 'C', CustomTags.UV_CIRCUITS, 'W', new UnificationEntry(cableGtQuadruple, Naquadah));



    }

    private static void registerMixerRecipes(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("coolant_dust")
                .inputItems(dust, LithiumFluoride, 2)
                .inputItems(dust, BerylliumFluoride, 3)
                .outputItems(dust, Coolant, 5)
                .duration(600).EUt(VA[MV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("pyrotheum_dust")
                .inputItems(dust, Coal, 1)
                .inputItems(dust, Sulfur, 1)
                .inputItems(dust, Redstone, 1)
                .inputItems(Items.BLAZE_POWDER, 1)
                .outputItems(dust, Pyrotheum, 1)
                .duration(160).EUt(VA[MV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("piranha_solution")
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(PiranhaSolution.getFluid(2000))
                .duration(160).EUt(VA[MV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("uranium_dioxide_output")
                .inputItems(dust, UraniumOxideThoriumNitrateMixture, 18)
                .inputFluids(DistilledWater.getFluid(1000))
                .outputItems(dustOxide, Uranium238, 3)
                .outputFluids(ThoriumNitrateSolution.getFluid(1000))
                .duration(600).EUt(VA[LV])
                .save(provider);

    }

    private static void registerAssemblerRecipes(Consumer<FinishedRecipe> provider) {
        for (int i = 0; i < QUAD_INPUT_HATCH.length; i++)
            if (QUAD_INPUT_HATCH[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("quad_input_hatch_" + QUAD_INPUT_HATCH[i].getTier())
                        .inputItems(pipeQuadrupleFluid, Titanium, 1)
                        .circuitMeta(1)
                        .inputItems(HULL[i].asStack())
                        .outputItems(QUAD_INPUT_HATCH[i].asStack())
                        .duration(600).EUt(VA[EV])
                        .save(provider);

        for (int i = 0; i < QUAD_OUTPUT_HATCH.length; i++)
            if (QUAD_OUTPUT_HATCH[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("quad_output_hatch_" + QUAD_OUTPUT_HATCH[i].getTier())
                        .inputItems(pipeQuadrupleFluid, Titanium, 1)
                        .circuitMeta(2)
                        .inputItems(HULL[i].asStack())
                        .outputItems(QUAD_OUTPUT_HATCH[i].asStack())
                        .duration(600).EUt(VA[EV])
                        .save(provider);

        for (int i = 0; i < NONUPLE_INPUT_HATCH.length; i++)
            if (NONUPLE_INPUT_HATCH[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("nonuple_input_hatch_" + NONUPLE_INPUT_HATCH[i].getTier())
                        .inputItems(pipeNonupleFluid, Titanium, 1)
                        .circuitMeta(1)
                        .inputItems(HULL[i].asStack())
                        .outputItems(NONUPLE_INPUT_HATCH[i].asStack())
                        .duration(600).EUt(VA[EV])
                        .save(provider);

        for (int i = 0; i < NONUPLE_OUTPUT_HATCH.length; i++)
            if (NONUPLE_OUTPUT_HATCH[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("nonuple_output_hatch_" + NONUPLE_OUTPUT_HATCH[i].getTier())
                        .inputItems(pipeNonupleFluid, Titanium, 1)
                        .circuitMeta(2)
                        .inputItems(HULL[i].asStack())
                        .outputItems(NONUPLE_OUTPUT_HATCH[i].asStack())
                        .duration(600).EUt(VA[EV])
                        .save(provider);

        for (int i = 0; i < ME_OUTPUT_BUS.length; i++)
            if (ME_OUTPUT_BUS[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("me_output_bus_"+ME_OUTPUT_BUS[i].getTier())
                        .inputItems(ITEM_EXPORT_BUS[i])
                        .inputItems(AEBlocks.INTERFACE.asItem())
                        .outputItems(ME_OUTPUT_BUS[i].asStack())
                        .duration(600).EUt(VA[i])
                        .save(provider);

        for (int i = 0; i < ME_OUTPUT_HATCH.length; i++)
            if (ME_OUTPUT_HATCH[i] != null)
                ASSEMBLER_RECIPES.recipeBuilder("me_output_hatch"+ME_OUTPUT_HATCH[i].getTier())
                        .inputItems(QUAD_OUTPUT_HATCH[i])
                        .inputItems(AEBlocks.INTERFACE.asItem())
                        .outputItems(ME_OUTPUT_HATCH[i].asStack())
                        .duration(600).EUt(VA[i])
                        .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("dual_input_bus"+DUAL_INPUT_BUS[IV].getTier())
                    .inputItems(ITEM_IMPORT_BUS[IV])
                    .inputItems(FLUID_IMPORT_HATCH[IV])
                    .inputItems(CustomTags.IV_CIRCUITS, 1)
                    .inputItems(ELECTRIC_PUMP_IV)
                    .inputItems(CONVEYOR_MODULE_IV)
                    .outputItems(DUAL_INPUT_BUS[IV].asStack())
                    .duration(600).EUt(VA[IV])
                    .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("luv_stocking_item_bus")
                .inputItems(ITEM_IMPORT_BUS[LuV])
                .inputItems(AEBlocks.INTERFACE.asItem())
                .inputItems(AEItems.SPEED_CARD.asItem(), 4)
                .circuitMeta(1)
                .outputItems(ME_INPUT_BUS[LuV])
                .duration(300).EUt(VA[HV])
                .save(provider);

        // --- Redox Cells
        ASSEMBLER_RECIPES.recipeBuilder("redox_cell_ev")
                .inputItems(frameGt, Vanadium, 1)
                .inputItems(plateDense, Lead,4)
                .inputItems(CustomTags.HV_CIRCUITS, 4)
                // TODO - add recipes for superconductor wires
                .inputItems(wireGtSingle, MercuryBariumCalciumCuprate)
                .inputFluids(Oxygen.getFluid(16000))
                .outputItems(REDOX_CELL[EV].asStack())
                .duration(200).EUt(VA[HV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("redox_cell_iv")
                .inputItems(REDOX_CELL[EV])
                .inputItems(plateDense, Titanium,4)
                .inputItems(CustomTags.EV_CIRCUITS, 4)
                // TODO - add recipes for superconductor wires
                .inputItems(wireGtSingle, UraniumTriplatinum)
                .inputFluids(Nitrogen.getFluid(16000))
                .outputItems(REDOX_CELL[IV].asStack())
                .duration(200).EUt(VA[EV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("redox_cell_luv")
                .inputItems(REDOX_CELL[IV])
                .inputItems(plateDense, TungstenSteel,4)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                // TODO - add recipes for superconductor wires
                .inputItems(wireGtSingle, SamariumIronArsenicOxide)
                .inputFluids(Helium.getFluid(8000))
                .outputItems(REDOX_CELL[LuV].asStack())
                .duration(200).EUt(VA[LuV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("redox_cell_zpm")
                .inputItems(REDOX_CELL[LuV])
                .inputItems(plateDense, Iridium,4)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                // TODO - add recipes for superconductor wires
                .inputItems(wireGtSingle, IndiumTinBariumTitaniumCuprate)
                .inputFluids(Argon.getFluid(8000))
                .outputItems(REDOX_CELL[ZPM].asStack())
                .duration(200).EUt(VA[ZPM])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("redox_cell_uv")
                .inputItems(REDOX_CELL[ZPM])
                .inputItems(plateDense, Naquadah,4)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                // TODO - add recipes for superconductor wires
                .inputItems(wireGtSingle, UraniumRhodiumDinaquadide)
                .inputFluids(Radon.getFluid(8000))
                .outputItems(REDOX_CELL[UV].asStack())
                .duration(200).EUt(VA[UV])
                .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("ev_energy_output_hatch_16a")
                    .inputItems(TRANSFORMER[EV])
                    .inputItems(ENERGY_OUTPUT_HATCH_4A[EV])
                    .inputItems(wireGtOctal, Aluminium, 8)
                    .inputItems(plate, Titanium, 4)
                    .inputFluids(Electrum.getFluid(144))
                    .outputItems(ENERGY_OUTPUT_HATCH_16A[EV].asStack())
                    .duration(200).EUt(VA[HV])
                    .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("iv_energy_output_hatch_16a")
                .inputItems(TRANSFORMER[IV])
                .inputItems(ENERGY_OUTPUT_HATCH_4A[IV])
                .inputItems(wireGtOctal, Tungsten, 8)
                .inputItems(plate, TungstenSteel, 4)
                .inputFluids(Electrum.getFluid(144))
                .outputItems(ENERGY_OUTPUT_HATCH_16A[IV].asStack())
                .duration(200).EUt(VA[EV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("luv_energy_output_hatch_16a")
                .inputItems(TRANSFORMER[LuV])
                .inputItems(ENERGY_OUTPUT_HATCH_4A[LuV])
                .inputItems(wireGtOctal, VanadiumGallium, 8)
                .inputItems(plate, RhodiumPlatedPalladium, 4)
                .inputFluids(Electrum.getFluid(288))
                .outputItems(ENERGY_OUTPUT_HATCH_16A[LuV].asStack())
                .duration(200).EUt(VA[IV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("zpm_energy_output_hatch_16a")
                .inputItems(TRANSFORMER[ZPM])
                .inputItems(ENERGY_OUTPUT_HATCH_4A[ZPM])
                .inputItems(wireGtOctal, Naquadah, 8)
                .inputItems(plate, Iridium, 4)
                .inputFluids(Electrum.getFluid(576))
                .outputItems(ENERGY_OUTPUT_HATCH_16A[ZPM].asStack())
                .duration(200).EUt(VA[LuV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("uv_energy_output_hatch_16a")
                .inputItems(TRANSFORMER[UV])
                .inputItems(ENERGY_OUTPUT_HATCH_4A[UV])
                .inputItems(wireGtOctal, NaquadahAlloy, 8)
                .inputItems(plate, Osmium, 4)
                .inputFluids(Electrum.getFluid(1152))
                .outputItems(ENERGY_OUTPUT_HATCH_16A[UV].asStack())
                .duration(200).EUt(VA[ZPM])
                .save(provider);
    }

    private static void registerChemicalReactorRecipes(Consumer<FinishedRecipe> provider) {
        CHEMICAL_RECIPES.recipeBuilder("lithium_fluoried")
                .inputItems(dust, Lithium)
                .inputFluids(HydrofluoricAcid.getFluid(1000))
                .outputItems(dust, LithiumFluoride)
                .outputFluids(Hydrogen.getFluid(1000))
                .duration(120).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("beryllium_fluoried")
                .inputItems(dust, Beryllium)
                .inputFluids(HydrofluoricAcid.getFluid(2000))
                .outputItems(dust, BerylliumFluoride)
                .outputFluids(Hydrogen.getFluid(2000))
                .duration(120).EUt(VA[MV])
                .save(provider);
        // cheater H2O2 recipe
        CHEMICAL_RECIPES.recipeBuilder("hydrogen_peroxide")
                .inputFluids(Oxygen.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .inputFluids(Dimethylbenzene.getFluid(100))
                .circuitMeta(1)
                .outputFluids(HydrogenPeroxide.getFluid(1000))
                .duration(600).EUt(VA[HV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uraninite_chloride_solution")
                .inputItems(dust, Uraninite, 3)
                .inputFluids(Water.getFluid(1000))
                .inputFluids(Chlorine.getFluid(2000))
                .outputFluids(UranylChlorideSolution.getFluid(1000))
                .duration(50).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uranyl_nitrate_solution")
                .inputFluids(UranylChlorideSolution.getFluid(1000))
                .inputFluids(NitricAcid.getFluid(2000))
                .outputFluids(UranylNitrateSolution.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .duration(50).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("purified_uranyl_nitrate_solution")
                .inputFluids(UranylNitrateSolution.getFluid(1000))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(PurifiedUranylNitrateSolution.getFluid(1000))
                .outputFluids(UraniumSulfateWasteSolution.getFluid(1000))
                .duration(50).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("ammonia_diuranate_solution")
                .inputFluids(PurifiedUranylNitrateSolution.getFluid(1000))
                .inputFluids(Water.getFluid(1001))
                .inputItems(dust, Carbon, 8)
                .outputFluids(AmmoniaDiuranateSolution.getFluid(1000))
                .outputFluids(NitricAcid.getFluid(2000))
                .outputFluids(CarbonMonoxide.getFluid(8000))
                .duration(140).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uranyl_tricarbonate")
                .inputFluids(AmmoniaDiuranateSolution.getFluid(1000))
                .inputItems(dust, Potassium, 8)
                .outputItems(dust, UranylTricarbonate, 10)
                .outputFluids(Ammonia.getFluid(2000))
                .outputFluids(Water.getFluid(1000))
                .duration(160).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uranium_peroxide_thorium_oxide_mixture")
                .inputItems(dust, UranylTricarbonate, 5)
                .inputFluids(PiranhaSolution.getFluid(2000))
                .outputItems(dust, UraniumPeroxideThoriumOxideMixture, 8)
                .outputFluids(UraniumRefinementWasteSolution.getFluid(1000))
                .duration(200).EUt(VA[HV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uranyl_sulfate_thorium_oxide_mixture")
                .inputItems(dust, UraniumDioxideThoriumOxideMixture, 6)
                .inputItems(dust, Sulfur, 1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputItems(dust, UranylSulfateThoriumOxideMixture, 11)
                .outputFluids(HydrogenSulfide.getFluid(1000))
                .duration(110).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("uranyl_nitrate_thorium_nitrate_mixture")
                .inputItems(dust, UranylSulfateThoriumOxideMixture, 11)
                .inputFluids(NitricAcid.getFluid(6000))
                .outputItems(dust, UranylNitrateThoriumNitrateMixture, 26)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(Water.getFluid(2000))
                .duration(120).EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("rare_earth_hydroxides")
                .inputItems(dust, RareEarth, 1)
                .inputFluids(Water.getFluid(3000))
                .inputItems(dust, SodiumHydroxide, 3)
                .outputFluids(RareEarthHydroxides.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(3000))
                .duration(200).EUt(VA[HV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("rare_earth_chlorides")
                .inputFluids(RareEarthHydroxides.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(3000))
                .outputItems(dust, ThoriumUraniumSludge, 2)
                .outputItems(dust, SodiumHydroxide, 3)
                .outputFluids(RareEarthChlorides.getFluid(3000))
                .duration(200).EUt(VA[HV])
                .save(provider);

    }
    private static void registerCentrifugeRecipes(Consumer<FinishedRecipe> provider) {
        CENTRIFUGE_RECIPES.recipeBuilder("pahoehoe_small")
                .inputFluids(PahoehoeLava.getFluid(100))
                .circuitMeta(10)
                .chancedOutput(nugget, Copper, 2000, 0)
                .chancedOutput(nugget, Tin, 1000, 0)
                .chancedOutput(nugget, Silver, 250, 0)
                .chancedOutput(dustSmall, Phosphorus, 5, 0)
                .chancedOutput(dustSmall, Scheelite, 25, 0)
                .chancedOutput(dustSmall, Bauxite, 50, 0)
                .duration(40).EUt(VA[EV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("pahoehoe_large")
                .inputFluids(PahoehoeLava.getFluid(3600))
                .circuitMeta(20)
                .chancedOutput(ingot, Copper, 8000, 0)
                .chancedOutput(ingot, Tin, 4000, 0)
                .chancedOutput(ingot, Silver, 1000, 0)
                .chancedOutput(dust, Phosphorus, 450, 0)
                .chancedOutput(dust, Scheelite, 225, 0)
                .chancedOutput(dust, Bauxite, 450, 0)
                .duration(40).EUt(VA[IV])
                .save(provider);
        // TODO - gtnh monazite/bastnasite lines are better, using tj rare earth for now
        CENTRIFUGE_RECIPES.recipeBuilder("thorium_uranium_sludge_output")
                .inputItems(dust, ThoriumUraniumSludge, 4)
                .inputFluids(Oxygen.getFluid(500))
                .outputItems(dust, Thorium)
                .chancedOutput(dustTiny, Thorium, 2000, 150)
                .chancedOutput(dustTiny, Uranium238, 2000, 150)
                .duration(250).EUt(VA[MV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("rare_earth_output")
                .inputFluids(RareEarthChlorides.getFluid(6000))
                .outputFluids(HydrochloricAcid.getFluid(6000))
                .outputFluids(LaNdOxidesSolution.getFluid(250))
                .outputFluids(SmGdOxidesSolution.getFluid(250))
                .outputFluids(TbHoOxidesSolution.getFluid(250))
                .outputFluids(ErLuOxidesSolution.getFluid(250))
                .duration(600).EUt(VA[HV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("la_nd_oxides_solution_output")
                .inputFluids(LaNdOxidesSolution.getFluid(4000))
                .outputItems(dust, Lanthanum, 5)
                .outputItems(dust, Praseodymium, 5)
                .outputItems(dust, Neodymium, 5)
                .outputItems(dust, Cerium, 5)
                .outputFluids(Oxygen.getFluid(3000))
                .duration(220).EUt(VA[HV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("sm_gd_oxides_solution_output")
                .inputFluids(SmGdOxidesSolution.getFluid(4000))
                .outputItems(dust, Scandium, 5)
                .outputItems(dust, Europium, 5)
                .outputItems(dust, Gadolinium, 5)
                .outputItems(dust, Samarium, 5)
                .outputFluids(Oxygen.getFluid(3000))
                .duration(220).EUt(VA[HV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("tb_ho_oxides_solution_output")
                .inputFluids(TbHoOxidesSolution.getFluid(4000))
                .outputItems(dust, Yttrium, 5)
                .outputItems(dust, Terbium, 5)
                .outputItems(dust, Dysprosium, 5)
                .outputItems(dust, Holmium, 5)
                .outputFluids(Oxygen.getFluid(3000))
                .duration(220).EUt(VA[HV])
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("er_lu_oxides_solution_output")
                .inputFluids(ErLuOxidesSolution.getFluid(4000))
                .outputItems(dust, Erbium, 5)
                .outputItems(dust, Thulium, 5)
                .outputItems(dust, Ytterbium, 5)
                .outputItems(dust, Lutetium, 5)
                .outputFluids(Oxygen.getFluid(3000))
                .duration(220).EUt(VA[HV])
                .save(provider);
    }

    private static void registerBlastFurnaceRecipes(Consumer<FinishedRecipe> provider) {
        BLAST_RECIPES.recipeBuilder("uranium_oxide_thorium_nitrate_mixture")
                .inputItems(dust, UranylNitrateThoriumNitrateMixture, 26)
                .inputFluids(Hydrogen.getFluid(2000))
                .outputItems(dust, UraniumOxideThoriumNitrateMixture, 18)
                .outputFluids(NitricAcid.getFluid(2000))
                .blastFurnaceTemp(500)
                .duration(200).EUt(VA[MV])
                .save(provider);
        // initial fuel recipe for nuclear
        BLAST_RECIPES.recipeBuilder("uranium_oxide")
                .inputItems(dustOxide, Uranium238, 3)
                .outputItems(ingot, Uranium238, 1)
                .outputFluids(Oxygen.getFluid(2000))
                .blastFurnaceTemp(600)
                .duration(1000).EUt(VA[MV])
                .save(provider);
    }
    private static void registerElectrolyzerRecipes(Consumer<FinishedRecipe> provider) {
        ELECTROLYZER_RECIPES.recipeBuilder("uranium_refinement_waste_solution_output")
                .inputFluids(UraniumRefinementWasteSolution.getFluid(20000))
                .outputItems(dust, Caesium, 3)
                .outputItems(dust, Molybdenum, 4)
                .outputItems(dust, Vanadium, 7)
                .outputFluids(SulfuricAcid.getFluid(20000))
                .outputFluids(Oxygen.getFluid(15000))
                .duration(20).EUt(VA[EV])
                .save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder("uranium_dioxide_thorium_mixture")
                .inputItems(dust, UraniumPeroxideThoriumOxideMixture, 8)
                .outputItems(dust, UraniumDioxideThoriumOxideMixture, 6)
                .outputFluids(HydrogenPeroxide.getFluid(1000))
                .outputFluids(Oxygen.getFluid(1000))
                .duration(200).EUt(VA[LV])
                .save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder("thorium_nitrate_output")
                .inputFluids(ThoriumNitrateSolution.getFluid(1000))
                .outputItems(dust, Thorium, 2)
                .outputFluids(NitricAcid.getFluid(2000))
                .duration(120).EUt(VA[MV])
                .save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder("uranyl_sulfate_waste_solution_output")
                .inputFluids(UraniumSulfateWasteSolution.getFluid(1000))
                .outputItems(dust, Lead, 1)
                .outputItems(dustTiny, Radium, 1)
                .outputItems(dustTiny, Strontium, 1)
                .outputItems(dustTiny, Barium, 1)
                .outputFluids(SulfuricAcid.getFluid(1000))
                .duration(120).EUt(VA[MV])
                .save(provider);

    }
    private static void registerFormingPressRecipes(Consumer<FinishedRecipe> provider) {
        FORMING_PRESS_RECIPES.recipeBuilder("casing_shielded_reactor")
                .inputItems(plateDense, Lead, 9)
                .inputItems(plateDense, Lead, 9)
                .inputItems(plateDense, HastelloyX, 4)
                .inputItems(plateDense, StainlessSteel, 2)
                .outputItems(CASING_SHIELDED_REACTOR.asStack(4))
                .duration(1500).EUt(500)
                .save(provider);
    }

    private static void registerAssemblyLineRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("bedrock_miner_mv")
                .inputItems(LARGE_MINER[LuV])
                .inputItems(frameGt, Tritanium, 9)
                .inputItems(plate, Europium, 3)
                .inputItems(ELECTRIC_MOTOR_LuV, 9)
                .inputItems(SENSOR_LuV, 9)
                .inputItems(FIELD_GENERATOR_LuV, 9)
                .inputItems(screw, HSSS, 36)
                .inputFluids(Neon.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(BEDROCK_ORE_MINER[MV])
                .duration(6000).EUt(VA[LuV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("bedrock_miner_hv")
                .inputItems(BEDROCK_ORE_MINER[MV])
                .inputItems(frameGt, Plutonium240, 9)
                .inputItems(plate, Plutonium240, 3)
                .inputItems(ELECTRIC_MOTOR_ZPM, 9)
                .inputItems(SENSOR_ZPM, 9)
                .inputItems(FIELD_GENERATOR_ZPM, 9)
                .inputItems(screw, Tritanium, 36)
                .inputFluids(Krypton.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(BEDROCK_ORE_MINER[HV])
                .duration(6000).EUt(VA[ZPM])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("bedrock_miner_ev")
                .inputItems(BEDROCK_ORE_MINER[HV])
                .inputItems(frameGt, Neutronium, 9)
                .inputItems(plate, Neutronium, 3)
                .inputItems(ELECTRIC_MOTOR_UV, 9)
                .inputItems(SENSOR_UV, 9)
                .inputItems(FIELD_GENERATOR_UV, 9)
                .inputItems(screw, Neutronium, 36)
                .inputFluids(Xenon.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(BEDROCK_ORE_MINER[EV])
                .duration(6000).EUt(VA[UV])
                .save(provider);
    }
}
