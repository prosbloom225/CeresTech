package com.prosbloom.cerestech.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.prosbloom.cerestech.data.CTRecipeTypes;
import com.prosbloom.cerestech.machines.multiblock.ReactorMachine;
import com.prosbloom.cerestech.machines.multiblock.part.QuadFluidHatchPartMachine;
import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.Predicates.controller;
import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NAQUADAH_REACTOR_RECIPES;

public class CTMachines {

    public static void init(){
    }
    public final static MachineDefinition[] DEHYDRATOR= registerSimpleMachines("dehydrator", CTRecipeTypes.DEHYDRATOR_RECIPES);
    public final static MachineDefinition[] DECAY_CHAMBER= registerSimpleMachines("decay_chamber", CTRecipeTypes.DECAY_CHAMBER_RECIPES);
    public final static MachineDefinition[] NAQUADAH_REACTOR = registerSimpleGenerator("naquadah_reactor", NAQUADAH_REACTOR_RECIPES, defaultTankSizeFunction,
            GTValues.IV, GTValues.LuV, GTValues.ZPM);
    public static MultiblockMachineDefinition INDUSTRIAL_GREENHOUSE = REGISTRATE.multiblock("industrial_greenhouse", WorkableElectricMultiblockMachine::new)
            .langValue("Industrial Greenhouse")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTRecipeTypes.INDUSTRIAL_GREENHOUSE_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_ALUMINIUM_FROSTPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXX", "TTTTT", "TTTTT", "XXXXX")
                    .aisle("XXXXX", "T###T", "T###T", "XXXXX")
                    .aisle("XXXXX", "T###T", "T###T", "XXXXX")
                    .aisle("XXXXX", "T###T", "T###T", "XXXXX")
                    .aisle("XXSXX", "TTTTT", "TTTTT", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('T', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('X', blocks(CASING_ALUMINIUM_FROSTPROOF.get()).setMinGlobalLimited(14)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false)))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_frost_proof"),
                    GTCEu.id("block/multiblock/industrial_greenhouse"), false)
            .compassSections(GTCompassSections.TIER[HV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition NUCLEAR_REACTOR = REGISTRATE.multiblock("nuclear_reactor", holder-> new ReactorMachine(holder, 1, 1))
            .langValue("Nuclear Reactor")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTRecipeTypes.NUCLEAR_REACTOR_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_TITANIUM_STABLE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX")
                    .aisle("XXX", "X#X", "XXX")
                    .aisle("XXX", "XSX", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_TITANIUM_STABLE.get()).setMinGlobalLimited(14)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false)))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                    GTCEu.id("block/multiblock/nuclear_reactor"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();


    public final static MultiblockMachineDefinition HOT_COOLANT_TURBINE = GTMachines.registerLargeTurbine("hot_coolant_turbine", EV,
            CTRecipeTypes.HOT_COOLANT_TURBINE_RECIPES,
            CASING_TUNGSTENSTEEL_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
            GTCEu.id("block/multiblock/generator/large_plasma_turbine"));

    public static MultiblockMachineDefinition GAS_CENTRIFUGE = REGISTRATE.multiblock("gas_centrifuge", WorkableElectricMultiblockMachine::new)
            .langValue("Gas Centrifuge")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTRecipeTypes.GAS_CENTRIFUGE_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX")
                    .aisle("XXX", "X#X", "XXX")
                    .aisle("XXX", "XSX", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(14)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false)))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/gas_centrifuge"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public final static MachineDefinition[] QUAD_INPUT_HATCH = registerTieredMachines("quad_input_hatch",
            (holder, tier) -> new QuadFluidHatchPartMachine(holder, tier, IO.IN),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " Quad Input Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_FLUIDS)
                    .overlayTieredHullRenderer("fluid_hatch.import")
                    .tooltips(Component.translatable("gtceu.machine.fluid_hatch.import.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                    .compassNode("fluid_hatch")
                    .register(),
            ALL_TIERS);

    public final static MachineDefinition[] QUAD_OUTPUT_HATCH = registerTieredMachines("quad_output_hatch",
            (holder, tier) -> new QuadFluidHatchPartMachine(holder, tier, IO.OUT),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " Quad Output Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_FLUIDS)
                    .overlayTieredHullRenderer("fluid_hatch.export")
                    .tooltips(Component.translatable("gtceu.machine.fluid_hatch.export.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                    .compassNode("fluid_hatch")
                    .register(),
            ALL_TIERS);
}
