package com.prosbloom.cerestech.data;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.prosbloom.cerestech.data.recipes.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_LAMINATED_GLASS;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static com.prosbloom.cerestech.data.CTBlocks.*;
import static com.prosbloom.cerestech.data.CTFluids.*;
import static com.prosbloom.cerestech.data.CTItems.*;
import static com.prosbloom.cerestech.data.CTMaterials.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.BACTERIAL_VAT_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.STELLAR_FORGE_RECIPES;
import static com.prosbloom.cerestech.data.CTTagPrefixes.dustOxide;
import static com.prosbloom.cerestech.data.CTTagPrefixes.fuelPure;
import static com.prosbloom.cerestech.data.recipes.HotCoolantTurbineRecipes.registerHotCoolantTurbineRecipes;
import static com.prosbloom.cerestech.machines.CTMachines.*;

public class CTRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CraftingComponent.initializeComponents();
        registerManualRecipes(provider);
        registerMixerRecipes(provider);
        registerAssemblerRecipes(provider);
        registerCircuitAssemblerRecipes(provider);
        registerFormingPressRecipes(provider);
        registerChemicalReactorRecipes(provider);
        registerHotCoolantTurbineRecipes(provider);
        registerCentrifugeRecipes(provider);
        registerElectrolyzerRecipes(provider);
        registerBlastFurnaceRecipes(provider);
        registerMaceratorRecipes(provider);
        registerAssemblyLineRecipes(provider);
        registerChemicalBathRecipes(provider);
        registerDistillationTowerRecipes(provider);
        registerOilCrackerRecipes(provider);
        registerAutoclaveRecipes(provider);
        registerPyrolyseOvenRecipes(provider);
        registerBacterialVatRecipes(provider);
        registerFusionCoilRecipes(provider);
        registerImplosionCompressorRecipes(provider);
        registerFluidHeaterRecipes(provider);
        registerExtractorRecipes(provider);

        IndustrialGreenhouseRecipes.registerIndustrialGreenhouseRecipes(provider);
        NuclearReactorRecipes.registerNuclearReactorRecipes(provider);
        NuclearCycleRecipes.registerNuclearCycleRecipes(provider);
        NaquadahReactorRecipes.registerNaquadahReactorRecipes(provider);
        HeatExchangerRecipes.registerHeatExchangerRecipes(provider);
        PcbFactoryRecipes.registerPcbFactoryRecipes(provider);
        NeutronActivatorRecipes.registerNeutronActivatorRecipes(provider);
        StellarForgeRecipes.registerStellarForgeRecipes(provider);
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

        VanillaRecipeHelper.addShapedRecipe(provider, true, "cryogenic_freezer", CRYOGENIC_FREEZER.asStack(),
                "GCG", "IHI", "PCP",
                'G', new UnificationEntry(gear, IncoloyMA956),
                'P', new UnificationEntry(plateDouble, TungstenSteel),
                'I', ELECTRIC_PISTON_IV,
                'H', CASING_CRYOGENIC.get(),
                'C', CustomTags.LuV_CIRCUITS);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "cryogenic_casing", CASING_CRYOGENIC.asStack(),
                "PGP", "OFO", "PGP",
                'G', new UnificationEntry(gear, IncoloyMA956),
                'P', new UnificationEntry(plateDouble, TungstenSteel),
                'F', new UnificationEntry(frameGt, Lafium),
                'O', new UnificationEntry(plateDense, RhodiumPlatedPalladium));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "bacterial_vat", BACTERIAL_VAT.asStack(),
                "PCP", "WHW", "PCP",
                'P', ELECTRIC_PUMP_EV,
                'C', CustomTags.EV_CIRCUITS,
                'W', new UnificationEntry(wireGtOctal, Silver),
                'H', HULL[HV]);


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
        MIXER_RECIPES.recipeBuilder("ae2_fluix_crystal")
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(dust, Redstone, 1)
                .inputItems(dust, NetherQuartz, 1)
                .outputItems(AEItems.FLUIX_CRYSTAL.asItem())
                .duration(20).EUt(VA[LV])
                .save(provider);
        MIXER_RECIPES.recipeBuilder("enriched_holmium")
                .inputItems(dust, Holmium, 1)
                .inputItems(dust, NaquadahEnriched, 4)
                .outputItems(dust, EnrichedHolmium, 5)
                .duration(300).EUt(VA[ZPM])
                .save(provider);
        MIXER_RECIPES.recipeBuilder("gelid_cryotheum")
                .inputItems(dust, Ice, 1)
                .inputItems(dust, Saltpeter, 1)
                .inputItems(dust, Redstone, 1)
                .inputItems(dust, Blizz, 1)
                .outputItems(dust, GelidCryotheum, 1)
                .duration(300).EUt(VA[LV])
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

        ASSEMBLER_RECIPES.recipeBuilder("luv_stocking_item_bus")
                .inputItems(ITEM_IMPORT_BUS[LuV])
                .inputItems(AEBlocks.INTERFACE.asItem())
                .inputItems(AEItems.SPEED_CARD.asItem(), 4)
                .circuitMeta(1)
                .outputItems(ME_INPUT_BUS[LuV])
                .duration(300).EUt(VA[HV])
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
        ASSEMBLER_RECIPES.recipeBuilder("dual_input_bus"+DUAL_INPUT_BUS[LuV].getTier())
                .inputItems(ITEM_IMPORT_BUS[LuV])
                .inputItems(FLUID_IMPORT_HATCH[LuV])
                .inputItems(CustomTags.LuV_CIRCUITS, 1)
                .inputItems(ELECTRIC_PUMP_LuV)
                .inputItems(CONVEYOR_MODULE_LuV)
                .outputItems(DUAL_INPUT_BUS[LuV].asStack())
                .duration(600).EUt(VA[LuV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("dual_input_bus"+DUAL_INPUT_BUS[ZPM].getTier())
                .inputItems(ITEM_IMPORT_BUS[ZPM])
                .inputItems(FLUID_IMPORT_HATCH[ZPM])
                .inputItems(CustomTags.ZPM_CIRCUITS, 1)
                .inputItems(ELECTRIC_PUMP_ZPM)
                .inputItems(CONVEYOR_MODULE_ZPM)
                .outputItems(DUAL_INPUT_BUS[ZPM].asStack())
                .duration(600).EUt(VA[ZPM])
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

        ASSEMBLER_RECIPES.recipeBuilder("ae2_logic_processor")
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .inputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .inputFluids(Redstone.getFluid(144))
                .outputItems(AEItems.LOGIC_PROCESSOR.asItem())
                .duration(64).EUt(VA[LV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("ae2_engineering_processor")
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .inputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .inputFluids(Redstone.getFluid(144))
                .outputItems(AEItems.ENGINEERING_PROCESSOR.asItem())
                .duration(64).EUt(VA[LV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("ae2_calculation_processor")
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .inputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .inputFluids(Redstone.getFluid(144))
                .outputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .duration(64).EUt(VA[LV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("casing_photolithographic")
                .inputItems(frameGt, NaquadahAlloy, 1)
                .inputItems(plate, ArtheriumSn, 1)
                .outputItems(CASING_PHOTOLITHOGRAPHIC, 1)
                .duration(600).EUt(VA[ZPM])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("neutron_activator")
                .inputItems(EMITTER_EV, 2)
                .inputItems(plateDense, Steel, 4)
                .inputItems(fuelPure, Uranium238, 1)
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputFluids(StainlessSteel.getFluid(576))
                .outputItems(NEUTRON_ACTIVATOR)
                .duration(600).EUt(VA[IV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("speeding_pipe_casing")
                .inputItems(pipeLargeFluid, StainlessSteel, 1)
                .inputItems(frameGt, BlueAlloy, 1)
                .inputItems(wireGtSingle, MercuryBariumCalciumCuprate, 32)
                .inputItems(plate, Beryllium, 32)
                .inputItems(CustomTags.IV_CIRCUITS, 1)
                .outputItems(CASING_SPEEDING_PIPE)
                .duration(300).EUt(VA[EV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("mega_chemical_reactor")
                .inputItems(LARGE_CHEMICAL_REACTOR.getItem(), 64)
                .inputFluids(SolderingAlloy.getFluid(9216))
                .circuitMeta(2)
                .outputItems(MEGA_CHEMICAL_REACTOR)
                .duration(72000).EUt(VA[HV])
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("mega_multi_smelter")
                .inputItems(MULTI_SMELTER.getItem(), 64)
                .inputFluids(SolderingAlloy.getFluid(9216))
                .circuitMeta(2)
                .outputItems(MEGA_MULTI_SMELTER)
                .duration(72000).EUt(VA[HV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("neutronium_charge")
                .inputItems(bolt, Titanium, 4)
                .inputItems(GELLED_TOLUENE, 4)
                .inputItems(dust, Neutronium)
                .inputItems(plate, Americium)
                .inputItems(bolt, MolybdenumDisilicide)
                .inputFluids(GlycerylTrinitrate.getFluid(2500))
                .outputItems(NEUTRONIUM_CHARGE, 1)
                .duration(200).EUt(VA[UV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("enriched_naquadah_alloy_casing")
                .inputItems(plate, EnrichedNaquadahAlloy, 6)
                .inputItems(frameGt, EnrichedNaquadahAlloy, 1)
                .circuitMeta(6)
                .outputItems(CASING_ENRICHED_NAQUADAH)
                .duration(200).EUt(VA[UV])
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("stellar_containment_casing")
                .inputItems(screw, Trinium, 8)
                .inputItems(plate, HSLASteel, 6)
                .inputItems(frameGt, HSLASteel, 1)
                .inputItems(rod, EnrichedNaquadahAlloy, 1)
                .inputFluids(SolderingAlloy.getFluid(576))
                .circuitMeta(6)
                .outputItems(CASING_STELLAR_CONTAINMENT, 4)
                .duration(150).EUt(VA[UV])
                .save(provider);
    }

    private static void registerCircuitAssemblerRecipes(Consumer<FinishedRecipe> provider) {
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("bio_circuit_board")
                .inputItems(WETWARE_CIRCUIT_BOARD, 32)
                .inputItems(PETRI_DISH, 8)
                .inputItems(ELECTRIC_PUMP_UV)
                .inputItems(SENSOR_LuV, 2)
                .inputItems(CustomTags.UV_CIRCUITS)
                .inputItems(foil, Neutronium, 32)
                // TODO - should be Sterilized Bio Catalyst Medium
                .inputFluids(SterileGrowthMedium.getFluid(16000))
                .outputItems(BIO_CIRCUIT_BOARD, 32)
                .duration(1200).EUt(VA[UV])
                .save(provider);
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("bio_processor")
                .inputItems(BIO_PROCESSING_UNIT, 6)
                .inputItems(RAW_CRYSTAL_CHIP, 6)
                .inputItems(NANO_CENTRAL_PROCESSING_UNIT, 12)
                .inputItems(ADVANCED_SMD_CAPACITOR, 64)
                .inputItems(ADVANCED_SMD_TRANSISTOR, 64)
                .inputItems(wireFine, NiobiumTitanium, 64)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(BIO_PROCESSOR_ZPM, 1)
                .duration(1200).EUt(VA[UV])
                .save(provider);
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("bioware_assembly")
                .inputItems(ULTRA_BIO_MUTATED_CIRCUIT_BOARD, 6)
                .inputItems(BIO_PROCESSOR_ZPM, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 64)
                .inputItems(ADVANCED_SMD_CAPACITOR, 64)
                .inputItems(RANDOM_ACCESS_MEMORY, 64)
                .inputItems(wireFine, NiobiumTitanium, 64)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(BIOWARE_PROCESSOR_ASSEMBLY_UV, 1)
                .duration(1600).EUt(VA[UV])
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
        CHEMICAL_RECIPES.recipeBuilder("ae2_charged_certus_quartz")
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem())
                .inputItems(dust, Redstone, 1)
                .outputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .duration(600).EUt(VA[LV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("molten_radox_polymer")
                .inputFluids(RadoxGas.getFluid(2160))
                .inputFluids(Oxygen.getFluid(FluidStorageKeys.PLASMA, 7500))
                .inputFluids(Titanium.getFluid(FluidStorageKeys.PLASMA, 100))
                .circuitMeta(2)
                .outputFluids(Radox.getFluid(720))
                .duration(600).EUt(VA[UV])
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder("ultra_bio_mutated_circuit_board")
                .inputItems(BIO_CIRCUIT_BOARD)
                .inputItems(foil, Neutronium, 24)
                .inputFluids(SodiumPersulfate.getFluid(15000))
                .outputItems(ULTRA_BIO_MUTATED_CIRCUIT_BOARD)
                .duration(3600).EUt(VA[EV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("ultra_bio_mutated_circuit_board")
                .inputItems(BIO_CIRCUIT_BOARD)
                .inputItems(foil, Neutronium, 24)
                .inputFluids(Iron3Chloride.getFluid(7500))
                .outputItems(ULTRA_BIO_MUTATED_CIRCUIT_BOARD)
                .duration(3600).EUt(VA[EV])
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder("bio_cells")
                .inputItems(STEM_CELLS, 32)
                .inputItems(dust, CosmicNeutronium, 4)
                .inputFluids(SterilizedBioCatalystMedium.getFluid(2000))
                .outputItems(BIO_CELLS, 32)
                .outputFluids(Mutagen.getFluid(2000))
                .duration(3600).EUt(VA[EV])
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
        CENTRIFUGE_RECIPES.recipeBuilder("super_heavy_to_heavy_radox")
                .inputFluids(SuperHeavyRadox.getFluid(1))
                .outputFluids(HeavyRadox.getFluid(2))
                .duration(60).EUt(VA[UV])
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder("black_plutonium_to_cosmic_neutronium")
                .inputItems(dust, BlackPlutonium, 1)
                .chancedOutput(dustTiny, CosmicNeutronium, 5000, 100)
                .chancedOutput(dustTiny, CosmicNeutronium, 2000, 100)
                .chancedOutput(dustTiny, CosmicNeutronium, 2000, 100)
                .duration(1200).EUt(VA[EV])
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
        FORMING_PRESS_RECIPES.recipeBuilder("ae2_printed_silicon")
                .inputItems(plate, Silicon, 1)
                .notConsumable(AEItems.SILICON_PRESS.stack())
                .outputItems(AEItems.SILICON_PRINT.asItem())
                .duration(200).EUt(VA[LV])
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder("ae2_printed_logic")
                .inputItems(plate, Gold, 1)
                .notConsumable(AEItems.LOGIC_PROCESSOR_PRESS.stack())
                .outputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .duration(200).EUt(VA[LV])
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder("ae2_printed_engineering")
                .inputItems(plate, Diamond, 1)
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.stack())
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .duration(200).EUt(VA[LV])
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder("ae2_printed_calculation")
                .inputItems(plate, CertusQuartz, 1)
                .notConsumable(AEItems.CALCULATION_PROCESSOR_PRESS.stack())
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .duration(200).EUt(VA[LV])
                .save(provider);
    }

    private static void registerAssemblyLineRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("void_miner_luv")
                .inputItems(LARGE_MINER[LuV])
                .inputItems(frameGt, Tritanium, 9)
                .inputItems(plate, Europium, 3)
                .inputItems(ELECTRIC_MOTOR_LuV, 9)
                .inputItems(SENSOR_LuV, 9)
                .inputItems(FIELD_GENERATOR_LuV, 9)
                .inputItems(screw, HSSS, 36)
                .inputFluids(Neon.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(VOID_MINER[LuV])
                .duration(6000).EUt(VA[LuV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("void_miner_zpm")
                .inputItems(VOID_MINER[LuV])
                .inputItems(frameGt, Plutonium240, 9)
                .inputItems(plate, Plutonium240, 3)
                .inputItems(ELECTRIC_MOTOR_ZPM, 9)
                .inputItems(SENSOR_ZPM, 9)
                .inputItems(FIELD_GENERATOR_ZPM, 9)
                .inputItems(screw, Tritanium, 36)
                .inputFluids(Krypton.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(VOID_MINER[ZPM])
                .duration(6000).EUt(VA[ZPM])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("void_miner_uv")
                .inputItems(VOID_MINER[ZPM])
                .inputItems(frameGt, Neutronium, 9)
                .inputItems(plate, Neutronium, 3)
                .inputItems(ELECTRIC_MOTOR_UV, 9)
                .inputItems(SENSOR_UV, 9)
                .inputItems(FIELD_GENERATOR_UV, 9)
                .inputItems(screw, Neutronium, 36)
                .inputFluids(Xenon.getFluid(20000))
                .inputFluids(SolderingAlloy.getFluid(1440))
                .outputItems(VOID_MINER[UV])
                .duration(6000).EUt(VA[UV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("pcb_factory")
                .inputItems(frameGt, Neutronium, 32)
                .inputItems(CIRCUIT_ASSEMBLER[ZPM], 4)
                .inputItems(CustomTags.LuV_CIRCUITS, 16)
                .inputItems(ROBOT_ARM_ZPM,8)
                .inputFluids(Naquadah.getFluid(2592))
                .inputFluids(SolderingAlloy.getFluid(5184))
                .outputItems(PCB_FACTORY)
                .duration(6000).EUt(VA[UV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("motor_uhv")
                .inputItems(rod, NeodymiumMagnetic, 64)
                .inputItems(rod, NeodymiumMagnetic, 64)
                .inputItems(wireFine, TitaniumTungstenCarbide, 64)
                .inputItems(wireFine, TitaniumTungstenCarbide, 64)
                .inputItems(wireFine, TitaniumTungstenCarbide, 64)
                .inputItems(wireFine, TitaniumTungstenCarbide, 64)
                .inputItems(round, HSLASteel, 16)
                .inputItems(ring, HSLASteel, 16)
                .inputItems(rodLong, HSLASteel, 2)
                .inputItems(cableGtQuadruple, Europium, 2)
                .inputFluids(Lubricant.getFluid(3000))
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(ELECTRIC_MOTOR_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("conveyor_uhv")
                .inputItems(plate, HSLASteel, 8)
                .inputItems(gear, HSLASteel, 4)
                .inputItems(rod, HSLASteel, 4)
                .inputItems(ELECTRIC_MOTOR_UHV, 2)
                .inputFluids(Polybenzimidazole.getFluid(144))
                .inputFluids(Lubricant.getFluid(3000))
                .outputItems(CONVEYOR_MODULE_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("piston_uhv")
                .inputItems(plate, HSLASteel, 8)
                .inputItems(gearSmall, HSLASteel, 8)
                .inputItems(rod, HSLASteel, 4)
                .inputItems(ingot, HSLASteel, 2)
                .inputItems(cableGtSingle, Europium, 2)
                .inputItems(ELECTRIC_MOTOR_UHV, 1)
                .inputFluids(Polybenzimidazole.getFluid(144))
                .inputFluids(Lubricant.getFluid(3000))
                .outputItems(ELECTRIC_PISTON_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("pump_uhv")
                .inputItems(pipeLargeFluid, Europium, 32)
                .inputItems(ring, SiliconeRubber, 16)
                .inputItems(screw, HSLASteel, 8)
                .inputItems(rotor, HSLASteel, 2)
                .inputItems(wireGtSingle, Europium, 2)
                .inputItems(ELECTRIC_MOTOR_UHV, 1)
                .inputFluids(Polybenzimidazole.getFluid(144))
                .inputFluids(Lubricant.getFluid(3000))
                .outputItems(ELECTRIC_PUMP_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("emitter_uhv")
                .inputItems(foil, Osmiridium, 64)
                .inputItems(dust, Caesium, 16)
                .inputItems(cableGtSingle, Europium, 8)
                .inputItems(GRAVI_STAR, 2)
                .inputItems(CustomTags.UHV_CIRCUITS, 4)
                .inputItems(frameGt, Adamantium, 1)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(EMITTER_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("sensor_uhv")
                .inputItems(foil, Osmiridium, 64)
                .inputItems(dust, Scandium, 16)
                .inputItems(cableGtSingle, Europium, 8)
                .inputItems(GRAVI_STAR, 2)
                .inputItems(CustomTags.UHV_CIRCUITS, 4)
                .inputItems(frameGt, Adamantium, 1)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(SENSOR_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("field_generator_uhv")
                .inputItems(wireFine, Osmiridium, 64)
                .inputItems(cableGtSingle, Europium, 4)
                .inputItems(CustomTags.UHV_CIRCUITS, 4)
                .inputItems(frameGt, Adamantium, 1)
                .inputItems(GRAVI_STAR, 1)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(FIELD_GENERATOR_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("robot_arm_uhv")
                .inputItems(cableGtSingle, Europium, 16)
                .inputItems(screw, HSLASteel, 16)
                .inputItems(rod, HSLASteel, 16)
                .inputItems(CustomTags.UHV_CIRCUITS, 8)
                .inputItems(ELECTRIC_MOTOR_UHV, 2)
                .inputItems(ingot, HSLASteel, 1)
                .inputItems(ELECTRIC_PISTON_UHV, 1)
                .inputFluids(SolderingAlloy.getFluid(288))
                .inputFluids(Lubricant.getFluid(3000))
                .outputItems(ROBOT_ARM_UHV)
                .duration(600).EUt(VA[UHV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("stellar_forge")
                .inputItems(bolt, EnrichedNaquadahAlloy, 64)
                .inputItems(plate, Trinium, 32)
                .inputItems(screw, IncoloyMA956, 32)
                .inputItems(rod, HSLASteel, 16)
                .inputItems(gear, TitaniumTungstenCarbide, 16)
                .inputItems(SENSOR_UV, 4)
                .inputItems(EMITTER_UV, 4)
                .inputItems(FIELD_GENERATOR_UV, 4)
                .inputItems(CustomTags.UHV_CIRCUITS, 2)
                .inputFluids(SolderingAlloy.getFluid(20736))
                .outputItems(STELLAR_FORGE)
                .duration(500).EUt(VA[UHV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("bio_processing_unit")
                .inputItems(ULTRA_BIO_MUTATED_CIRCUIT_BOARD, 1)
                .inputItems(BIO_CELLS, 16)
                .inputItems(GLASS_TUBE, 16)
                .inputItems(pipeTinyFluid, Polybenzimidazole, 16)
                .inputItems(plate, FluxedElectrum)
                .inputItems(foil, StyreneButadieneRubber, 64)
                .inputFluids(Coolant.getFluid(2000))
                .inputFluids(SterilizedBioCatalystMedium.getFluid(500))
                .inputFluids(UUMatter.getFluid(500))
                .outputItems(BIO_PROCESSING_UNIT)
                .duration(600).EUt(VA[UV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("bioware_supercomputer")
                .inputItems(ULTRA_BIO_MUTATED_CIRCUIT_BOARD, 2)
                .inputItems(BIOWARE_PROCESSOR_ASSEMBLY_UV, 2)
                .inputItems(ADVANCED_SMD_TRANSISTOR, 16)
                .inputItems(ADVANCED_SMD_RESISTOR, 16)
                .inputItems(ADVANCED_SMD_CAPACITOR, 16)
                .inputItems(ADVANCED_SMD_DIODE, 16)
                .inputItems(NOR_MEMORY_CHIP, 32)
                .inputItems(RANDOM_ACCESS_MEMORY, 64)
                .inputItems(wireFine, NiobiumTitanium, 32)
                .inputItems(foil, StyreneButadieneRubber, 64)
                .inputFluids(SolderingAlloy.getFluid(1440))
                .inputFluids(Coolant.getFluid(10000))
                .inputFluids(SterilizedBioCatalystMedium.getFluid(1440))
                .outputItems(BIOWARE_SUPERCOMPUTER_UHV)
                .duration(4000).EUt(VA[UV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("bio_mainframe")
                .inputItems(frameGt, Tritanium, 4)
                .inputItems(BIOWARE_SUPERCOMPUTER_UHV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 24)
                .inputItems(ADVANCED_SMD_TRANSISTOR, 24)
                .inputItems(ADVANCED_SMD_RESISTOR, 24)
                .inputItems(ADVANCED_SMD_CAPACITOR, 24)
                .inputItems(ADVANCED_SMD_DIODE, 24)
                .inputItems(RANDOM_ACCESS_MEMORY, 64)
                .inputItems(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 64)
                .inputItems(foil, StyreneButadieneRubber, 64)
                .inputItems(foil, Polybenzimidazole, 64)
                .inputFluids(SolderingAlloy.getFluid(2880))
                .inputFluids(Coolant.getFluid(20000))
                .inputFluids(SterilizedBioCatalystMedium.getFluid(2880))
                .outputItems(BIO_MAINFRAME_UEV)
                .duration(6000).EUt(VA[UHV])
                .save(provider);
    }

    private static void registerMaceratorRecipes(Consumer<FinishedRecipe> provider) {
        MACERATOR_RECIPES.recipeBuilder("ae2_certus_dust")
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem())
                .outputItems(AEItems.CERTUS_QUARTZ_DUST.asItem())
                .duration(1500).EUt(VA[ULV])
                .save(provider);
        MACERATOR_RECIPES.recipeBuilder("ae2_fluix_dust")
                .inputItems(AEItems.FLUIX_CRYSTAL.asItem())
                .outputItems(AEItems.FLUIX_DUST.asItem())
                .duration(1500).EUt(VA[ULV])
                .save(provider);
        MACERATOR_RECIPES.recipeBuilder("ae2_sky_ston_dust")
                .inputItems(AEBlocks.SKY_STONE_BLOCK.asItem())
                .outputItems(AEItems.SKY_DUST.asItem())
                .duration(1500).EUt(VA[ULV])
                .save(provider);
    }

    private static void registerChemicalBathRecipes(Consumer<FinishedRecipe> provider) {

        CHEMICAL_BATH_RECIPES.recipeBuilder("blizz_powder")
                .inputItems(new ItemStack(Items.SNOWBALL, 4))
                .inputFluids(Blaze.getFluid(1440))
                .outputItems(dust, Blizz, 1)
                .duration(400).EUt(VA[HV])
                .save(provider);
    }

    private static void registerDistillationTowerRecipes(Consumer<FinishedRecipe> provider) {
        DISTILLATION_RECIPES.recipeBuilder("distill_cracked_radox")
                .inputFluids(CrackedRadox.getFluid(1000))
                .outputFluids(RadoxGas.getFluid(100))
                .outputFluids(LightRadox.getFluid(200))
                .outputItems(dust, Ash, 1)
                .duration(600).EUt(VA[UV])
                .save(provider);

        DISTILLATION_RECIPES.recipeBuilder("distill_raw_radox")
                .inputFluids(RawRadox.getFluid(5000))
                .outputItems(dust, Ash, 5)
                .outputFluids(Xenoxene.getFluid(50))
                .outputFluids(LightRadox.getFluid(300))
                .outputFluids(SuperLightRadox.getFluid(500))
                .outputFluids(HeavyRadox.getFluid(150))
                .outputFluids(SuperHeavyRadox.getFluid(100))
                .outputFluids(FermentedBiomass.getFluid(50))
                .outputFluids(Creosote.getFluid(1000))
                .outputFluids(Water.getFluid(1400))
                .outputFluids(FermentedBacterialSludge.getFluid(50))
                .outputFluids(OilHeavy.getFluid(600))
                .outputFluids(Oil.getFluid(600))
                .duration(800).EUt(VA[UHV])
                .save(provider);

        DISTILLATION_RECIPES.recipeBuilder("distill_fermented_bacterial_sludge")
                .inputFluids(FermentedBacterialSludge.getFluid(10))
                .outputFluids(Mutagen.getFluid(1))
                .duration(60).EUt(VA[EV])
                .save(provider);
    }
    private static void registerPyrolyseOvenRecipes(Consumer<FinishedRecipe> provider) {
        PYROLYSE_RECIPES.recipeBuilder("raw_radox")
                .inputItems(Items.OAK_LOG.asItem(), 64)
                .inputFluids(Xenoxene.getFluid(1000))
                .circuitMeta(24)
                .outputItems(dust, Ash, 8)
                .outputFluids(RawRadox.getFluid(1000))
                .duration(3600).EUt(VA[UV])
                .save(provider);

    }

    private static void registerOilCrackerRecipes(Consumer<FinishedRecipe> provider) {
        CRACKING_RECIPES.recipeBuilder("heavy_radox_cracking")
                .inputFluids(SilverPlasma.getFluid(FluidStorageKeys.PLASMA, 1))
                .inputFluids(HeavyRadox.getFluid(100))
                .circuitMeta(24)
                .outputFluids(LightRadox.getFluid(20))
                .duration(500).EUt(VA[UV])
                .save(provider);
        CRACKING_RECIPES.recipeBuilder("light_radox_cracking")
                .inputFluids(SilverPlasma.getFluid(FluidStorageKeys.PLASMA, 1))
                .inputFluids(LightRadox.getFluid(100))
                .circuitMeta(24)
                .outputFluids(SuperLightRadox.getFluid(50))
                .duration(500).EUt(VA[UV])
                .save(provider);

        CRACKING_RECIPES.recipeBuilder("super_light_radox_cracking")
                .inputFluids(SilverPlasma.getFluid(FluidStorageKeys.PLASMA, 1))
                .inputFluids(SuperLightRadox.getFluid(100))
                .circuitMeta(24)
                .outputFluids(CrackedRadox.getFluid(100))
                .duration(500).EUt(VA[UV])
                .save(provider);
    }

    private static void registerAutoclaveRecipes(Consumer<FinishedRecipe> provider) {
        AUTOCLAVE_RECIPES.recipeBuilder("radox_naquadah_to_enriched")
                .inputItems(dust, Naquadah, 1)
                .inputFluids(LightRadox.getFluid(2000))
                .outputItems(dust, NaquadahEnriched, 3)
                .duration(350).EUt(VA[IV])
                .save(provider);
    }

    private static void registerBacterialVatRecipes(Consumer<FinishedRecipe> provider) {
        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_xenoxene")
                .inputItems(dust, AntimonyTrifluoride, 16)
                .inputItems(dust, Osmium, 16)
                .inputFluids(Oil.getFluid(20))
                .circuitMeta(12)
                .outputFluids(Xenoxene.getFluid(20))
                .duration(3600).EUt(VA[UEV])
                .save(provider);

        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_bio_catalyst_medium")
                .inputItems(STEM_CELLS, 64)
                .inputItems(foil, Osmium, 16)
                .inputItems(SEAWEED_EXTRACT, 4)
                .inputItems(dust, Tritanium, 4)
                .inputFluids(RawGrowthCatalystMedium.getFluid(8000))
                .outputFluids(RawBioCatalystMedium.getFluid(2000))
                .circuitMeta(14)
                .duration(3600).EUt(VA[LuV])
                .save(provider);
        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_bio_catalyst_medium_1")
                .inputItems(STEM_CELLS, 16)
                .inputItems(foil, Osmium, 16)
                .inputItems(SEAWEED_EXTRACT, 8)
                .inputItems(dustTiny, InfinityCatalyst, 4)
                .inputFluids(RawGrowthCatalystMedium.getFluid(5000))
                .outputFluids(RawBioCatalystMedium.getFluid(5000))
                .circuitMeta(14)
                .duration(3600).EUt(VA[ZPM])
                .save(provider);
        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_bio_catalyst_medium_2")
                .inputItems(BIO_CELLS, 8)
                .inputItems(SEAWEED_EXTRACT, 16)
                .inputItems(dust, InfinityCatalyst, 4)
                .inputFluids(RawGrowthCatalystMedium.getFluid(5000))
                .outputFluids(RawBioCatalystMedium.getFluid(10000))
                .circuitMeta(13)
                .duration(3600).EUt(VA[UV])
                .save(provider);

        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_growth_catalyst_medium")
                .inputItems(dust, Meat, 4)
                .inputItems(dust, Salt, 4)
                .inputItems(dust, Calcium, 4)
                .inputItems(dust, Agar, 4)
                .inputFluids(Bacteria.getFluid(4000))
                .outputFluids(RawGrowthCatalystMedium.getFluid(1000))
                .circuitMeta(14)
                .duration(1200).EUt(VA[IV])
                .save(provider);
        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_growth_catalyst_medium_1")
                .inputItems(dust, Meat, 8)
                .inputItems(dust, Salt, 8)
                .inputItems(dust, Calcium, 8)
                .inputItems(dust, Agar, 4)
                .inputFluids(BacterialSludge.getFluid(4000))
                .outputFluids(RawGrowthCatalystMedium.getFluid(2000))
                .circuitMeta(14)
                .duration(1200).EUt(VA[LuV])
                .save(provider);
        BACTERIAL_VAT_RECIPES.recipeBuilder("bacterial_vat_raw_growth_catalyst_medium_2")
                .inputItems(dust, Meat, 12)
                .inputItems(dust, Salt, 12)
                .inputItems(dust, Calcium, 12)
                .inputItems(dust, Agar, 4)
                .inputFluids(Mutagen.getFluid(4000))
                .outputFluids(RawGrowthCatalystMedium.getFluid(4000))
                .circuitMeta(14)
                .duration(1200).EUt(VA[ZPM])
                .save(provider);
    }

    private static void registerFusionCoilRecipes(Consumer<FinishedRecipe> provider) {
        FUSION_RECIPES.recipeBuilder("silver_plasma")
                .inputFluids(Gold.getFluid(144))
                .inputFluids(Arsenic.getFluid(144))
                .outputFluids(SilverPlasma.getFluid(FluidStorageKeys.PLASMA, 144))
                .duration(16).EUt(VA[ZPM])
                .fusionStartEU(350000000)
                .save(provider);

    }

    private static void registerImplosionCompressorRecipes(Consumer<FinishedRecipe> provider) {
        IMPLOSION_RECIPES.recipeBuilder("cosmic_neutronium_nugget")
                .inputItems(dustTiny, CosmicNeutronium, 9)
                .outputItems(nugget, CosmicNeutronium, 1)
                .duration(1).EUt(VA[UV])
                .save(provider);
    }

    private static void registerFluidHeaterRecipes(Consumer<FinishedRecipe> provider) {
        FLUID_HEATER_RECIPES.recipeBuilder("sterilized_bio_catalyst_medium")
                .inputFluids(RawBioCatalystMedium.getFluid(100))
                .circuitMeta(1)
                .outputFluids(SterilizedBioCatalystMedium.getFluid(100))
                .duration(20).EUt(VA[LuV])
                .save(provider);
    }

    private static void registerExtractorRecipes(Consumer<FinishedRecipe> provider) {
        EXTRACTOR_RECIPES.recipeBuilder("seaweed_extract")
                .inputItems(Items.KELP, 64)
                .outputItems(SEAWEED_EXTRACT, 1)
                .duration(3600).EUt(VA[UV])
                .save(provider);
    }
}
