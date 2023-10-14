package com.prosbloom.cerestech.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.prosbloom.cerestech.data.CTRecipeTypes;
import com.prosbloom.cerestech.machines.multiblock.ReactorMachine;
import com.prosbloom.cerestech.machines.multiblock.part.*;
import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.Predicates.controller;
import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.HEAT_VENT;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.PYROLYSE_RECIPES;
import static com.prosbloom.cerestech.data.CTBlocks.CASING_SHIELDED_REACTOR;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NAQUADAH_REACTOR_RECIPES;
import static net.minecraft.world.level.block.Blocks.DIRT;

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
                    .aisle("XXXXX", "TDDDT", "TDDDT", "XXXXX")
                    .aisle("XXXXX", "TDDDT", "TDDDT", "XXXXX")
                    .aisle("XXXXX", "TDDDT", "TDDDT", "XXXXX")
                    .aisle("XXSXX", "TTTTT", "TTTTT", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('T', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('D', blocks(DIRT))
                    .where('X', blocks(CASING_ALUMINIUM_FROSTPROOF.get()).setMinGlobalLimited(14)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, false, false)))
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
            .appearanceBlock(CASING_SHIELDED_REACTOR)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XGX", "XGX", "XGX", "XGX", "XGX", "XGX", "XGX", "XXX")
                    .aisle("XXX", "GTG", "GTG", "GTG", "GTG", "GTG", "GTG", "GTG", "XXX")
                    .aisle("XSX", "XGX", "XGX", "XGX", "XGX", "XGX", "XGX", "XGX", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('T', blocks(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Thorium)))
                    .where('G', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('X', blocks(CASING_SHIELDED_REACTOR.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, false, false)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_shielded_reactor"),
                    GTCEu.id("block/multiblock/nuclear_reactor"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();


    public final static MultiblockMachineDefinition HOT_COOLANT_TURBINE = GTMachines.registerLargeTurbine("hot_coolant_turbine", IV,
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
                    .aisle("#XXX#", "#XXX#", "#####", "#####", "#####", "#####", "#####")
                    .aisle("XXXXX", "XXXXX", "#TCT#", "#T#T#", "#T#T#", "#T#T#", "#T#T#")
                    .aisle("XXXXX", "XXXXX", "#CCC#", "#####", "#####", "#####", "#####")
                    .aisle("XXXXX", "XXXXX", "#TCT#", "#T#T#", "#T#T#", "#T#T#", "#T#T#")
                    .aisle("#XSX#", "#XXX#", "#####", "#####", "#####", "#####", "#####")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('C', blocks(CASING_STEEL_PIPE.get()))
                    .where('T', blocks(CASING_STEEL_SOLID.get()))
                    .where('X', blocks(CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false)))
                    .where('#', Predicates.any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/gas_centrifuge"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition LARGE_HEAT_EXCHANGER = REGISTRATE.multiblock("large_heat_exchanger", WorkableElectricMultiblockMachine::new)
            .langValue("Large Heat Exchanger")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CTRecipeTypes.HEAT_EXCHANGER_RECIPES)
            .appearanceBlock(CASING_TITANIUM_STABLE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX", "XXX")
                    .aisle("XXX", "XPX", "XPX", "XXX")
                    .aisle("XSX", "XXX", "XXX", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('P', blocks(CASING_TITANIUM_GEARBOX.get()))
                    .where('X', blocks(CASING_TITANIUM_STABLE.get()).setMinGlobalLimited(14)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, false)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                    GTCEu.id("block/multiblock/large_heat_exchanger"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition INDUSTRIAL_COKE_OVEN = REGISTRATE.multiblock("industrial_coke_oven", WorkableElectricMultiblockMachine::new)
            .langValue("Industrial Coke Oven")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(PYROLYSE_RECIPES)
            // TODO - parallel isnt working, seems like upstream issue
            .recipeModifier(GTRecipeModifiers.PARALLEL_HATCH.apply(OverclockingLogic.PERFECT_OVERCLOCK, GTRecipeModifiers.ELECTRIC_OVERCLOCK))
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_INVAR_HEATPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "VVV", "XXX")
                    .aisle("XXX", "V#V", "XXX")
                    .aisle("XSX", "VVV", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('V', blocks(HEAT_VENT.get()))
                    .where('X', blocks(CASING_INVAR_HEATPROOF.get())
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, true, true)))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_heatproof"),
                    GTCEu.id("block/multiblock/industrial_coke_oven"), false)
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

    public final static MachineDefinition[] NONUPLE_INPUT_HATCH = registerTieredMachines("nonuple_input_hatch",
            (holder, tier) -> new NonupleFluidHatchPartMachine(holder, tier, IO.IN),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " Nonuple Input Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_FLUIDS)
                    .overlayTieredHullRenderer("fluid_hatch.import")
                    .tooltips(Component.translatable("gtceu.machine.fluid_hatch.import.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                    .compassNode("fluid_hatch")
                    .register(),
            ALL_TIERS);
    public final static MachineDefinition[] NONUPLE_OUTPUT_HATCH = registerTieredMachines("nonuple_output_hatch",
            (holder, tier) -> new NonupleFluidHatchPartMachine(holder, tier, IO.OUT),
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " Nonuple Output Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_FLUIDS)
                    .overlayTieredHullRenderer("fluid_hatch.export")
                    .tooltips(Component.translatable("gtceu.machine.fluid_hatch.export.tooltip"),
                            Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                    .compassNode("fluid_hatch")
                    .register(),
            ALL_TIERS);


    public final static MachineDefinition[] ME_OUTPUT_BUS = registerTieredMachines("me_output_bus",
            (holder, tier) -> new MEItemPartMachine(holder, tier, IO.OUT),
            (tier, builder) -> builder
                    .langValue("ME Output Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.EXPORT_ITEMS)
                    .overlayTieredHullRenderer("item_bus.export")
                    .compassNode("me_output_bus")
                    .register(),
            HIGH_TIERS);

    public final static MachineDefinition[] ME_OUTPUT_HATCH = registerTieredMachines("me_output_hatch",
            (holder, tier) -> new MEFluidHatchPartMachine(holder, tier, IO.OUT),
            (tier, builder) -> builder
                    .langValue("ME Output Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.EXPORT_FLUIDS)
                    .overlayTieredHullRenderer("fluid_hatch.export")
                    .compassNode("me_output_hatch")
                    .register(),
            HIGH_TIERS);


}
