package com.prosbloom.cerestech.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.client.renderer.machine.MachineRenderer;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.prosbloom.cerestech.CTMod;
import com.prosbloom.cerestech.data.CTRecipeModifiers;
import com.prosbloom.cerestech.data.CTRecipeTypes;
import com.prosbloom.cerestech.machines.multiblock.*;
import com.prosbloom.cerestech.machines.multiblock.part.*;
import com.prosbloom.cerestech.util.ColorUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.HEAT_VENT;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.PYROLYSE_RECIPES;
import static com.prosbloom.cerestech.api.machine.trait.CTRegistries.REGISTRATE;
import static com.prosbloom.cerestech.data.CTBlocks.*;
import static com.prosbloom.cerestech.data.CTRecipeTypes.NAQUADAH_REACTOR_RECIPES;
import static com.prosbloom.cerestech.machines.BlockHelper.registerTieredMachines;
import static net.minecraft.world.level.block.Blocks.DIRT;
import static net.minecraft.world.level.block.Blocks.WATER;

public class CTMachines {

    public static void init(){
    }
    public final static MachineDefinition[] DEHYDRATOR= BlockHelper.registerSimpleMachines("dehydrator", CTRecipeTypes.DEHYDRATOR_RECIPES);
    public final static MachineDefinition[] DECAY_CHAMBER= BlockHelper.registerSimpleMachines("decay_chamber", CTRecipeTypes.DECAY_CHAMBER_RECIPES);
    public final static MachineDefinition[] NAQUADAH_REACTOR = BlockHelper.registerSimpleGenerator("naquadah_reactor", NAQUADAH_REACTOR_RECIPES, defaultTankSizeFunction,
            GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);
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
                    CTMod.id("block/multiblock/industrial_greenhouse"), false)
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
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_shielded_reactor"),
                    CTMod.id("block/multiblock/nuclear_reactor"), false)
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
                    CTMod.id("block/multiblock/gas_centrifuge"), false)
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
                    CTMod.id("block/multiblock/large_heat_exchanger"), false)
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
                    CTMod.id("block/multiblock/industrial_coke_oven"), false)
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public final static MultiblockMachineDefinition VOLCANUS = REGISTRATE.multiblock("volcanus", VolcanusMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifier((machine, recipe) -> CTRecipeModifiers.volcanusParallel(machine, recipe, 8, false))
            .appearanceBlock(CASING_VOLCANUS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "CCC", "CCC", "XXX")
                    .aisle("XXX", "C#C", "C#C", "XMX")
                    .aisle("XSX", "CCC", "CCC", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_VOLCANUS.get()).setMinGlobalLimited(9)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .where('C', heatingCoils())
                    .where('#', air())
                    .build())
            // TODO - need to add shapeInfos for all multiblocks
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("ISO", "CCC", "CCC", "XMX")
                        .aisle("FXD", "C#C", "C#C", "XHX")
                        .aisle("EEX", "CCC", "CCC", "XXX")
                        .where('X', CASING_VOLCANUS.getDefaultState())
                        .where('S', definition, Direction.NORTH)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('F', FLUID_IMPORT_HATCH[GTValues.LV], Direction.WEST)
                        .where('D', FLUID_EXPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('H', MUFFLER_HATCH[GTValues.LV], Direction.UP)
                        .where('M', MAINTENANCE_HATCH, Direction.NORTH);
                ALL_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(coil -> shapeInfo.add(builder.where('C', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .recoveryItems(() -> new ItemLike[]{GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_volcanus"),
                    GTCEu.id("block/multiblock/electric_blast_furnace"), false)
            .tooltips(Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1",
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.3"),
                    Component.translatable("cerestech.machine.volcanus.tooltip")))
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component.translatable(FormattingUtil.formatNumbers(coilMachine.getCoilType().getCoilTemperature() + 100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) + "K").setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                }
            })
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public final static MultiblockMachineDefinition CRYOGENIC_FREEZER = REGISTRATE.multiblock("cryogenic_freezer", CryogenicFreezerMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.VACUUM_RECIPES)
            .recipeModifier((machine, recipe) -> CTRecipeModifiers.parallelOverclock(machine, recipe, 8, false))
            .appearanceBlock(CASING_VOLCANUS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", "XXX")
                    .aisle("XXX", "X#X", "XXX")
                    .aisle("XXX", "XSX", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_CRYOGENIC.get()).setMinGlobalLimited(9)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('#', air())
                    .build())
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_cryogenic"),
                    CTMod.id("block/multiblock/cryogenic_freezer"), false)
            .tooltips(Component.translatable("cerestech.machine.cryogenic_freezer.tooltip",
                    "cerestech.machine.cryogenic_freezer.tooltip.1"))
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition POWER_STATION = REGISTRATE.multiblock("power_station", PowerStationMachine::new)
            .langValue("Power Station")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_POWER_STATION)
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XSXX", "XXXX", "XXXX", "XXXX")
                    .aisle("XXXX", "X##X", "X##X", "XXXX").setRepeatable(1, 255)
                    .aisle("XXXX", "XXXX", "XXXX", "XXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_POWER_STATION.get())
                            .or(abilities(PartAbility.INPUT_ENERGY, PartAbility.OUTPUT_ENERGY, PartAbility.INPUT_LASER, PartAbility.OUTPUT_LASER))
                            .or(autoAbilities(true, false, false)))
                    .where('#', abilities(PartAbility.STEAM))
                    .build())
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_power_station"),
                    CTMod.id("block/multiblock/industrial_coke_oven"), false)
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

    public final static MachineDefinition[] ME_INPUT_BUS = registerTieredMachines("me_input_bus",
            (holder, tier) -> new MEInputBusPartMachine(holder, tier, IO.IN),
            (tier, builder) -> builder
                    .langValue("ME Input Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_ITEMS)
                    .overlayTieredHullRenderer("item_bus.import")
                    .compassNode("me_input_bus")
                    .register(),
            LuV);


    public final static MachineDefinition[] DUAL_INPUT_BUS= registerTieredMachines("dual_input_bus",
            (holder, tier) -> new DualInputPartMachine(holder, tier, IO.IN),
            (tier, builder) -> builder
                    .langValue("Dual Input Bus")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_ITEMS, PartAbility.IMPORT_FLUIDS)
                    .overlayTieredHullRenderer("item_bus.import")
                    .compassNode("dual_input_bus")
                    .register(),
            IV, LuV, ZPM);

    public final static MachineDefinition[] REDOX_CELL = registerTieredMachines("redox_cell",
            (holder, tier) -> new TieredIOPartMachine(holder, tier, IO.OUT),
            (tier, builder) -> builder
                    .langValue("Redox Cell")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.STEAM)
                    .renderer(() -> new MachineRenderer(CTMod.id("block/machine/redox_cell")))
                    .itemColor((s, t)-> ColorUtils.getRedoxColorFromTier(tier))
                    .paintingColor(ColorUtils.getRedoxColorFromTier(tier))
                    .register(),
            EV, IV, LuV, ZPM, UV);


    public static MultiblockMachineDefinition PCB_FACTORY = REGISTRATE.multiblock("pcb_factory", WorkableElectricMultiblockMachine::new)
            .langValue("PCB Factory")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_PHOTOLITHOGRAPHIC)
            .recipeType(CTRecipeTypes.PCB_FACTORY_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("FXXSXXF", "XPPPPPX", "XPPPPPX", "XPPPPPX", "XPPPPPX", "XPPPPPX", "FXXXXXF")
                    .aisle("FGGGGGF", "X#####X", "E#VVV#E", "E#VVV#E", "E#VVV#E", "X#####X", "FXXXXXF")
                    .aisle("FGGGGGF", "X#####X", "E#####E", "E#####E", "E#####E", "X#####X", "FXXXXXF")
                    .aisle("FGGGGGF", "X#####X", "X#####X", "X#####X", "X#####X", "X#####X", "FXXXXXF")
                    .aisle("FFFFFFF", "XGGGGGX", "XGGGGGX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "F#####F")
                    .aisle("#######", "F#####F", "F#####F", "FFFFFFF", "F#####F", "F#####F", "#######")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_PHOTOLITHOGRAPHIC.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('P', blocks(PLASTCRETE.get()))
                    .where('E', blocks(CASING_GRATE.get()))
                    .where('F', frames(GTMaterials.DamascusSteel))
                    .where('V', frames(GTMaterials.IncoloyMA956))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_photolithographic"),
                    CTMod.id("block/multiblock/pcb_factory"), false)
            .compassSections(GTCompassSections.TIER[ZPM])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition NEUTRON_ACTIVATOR = REGISTRATE.multiblock("neutron_activator", WorkableElectricMultiblockMachine::new)
            .langValue("Neutron Activator")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .recipeType(CTRecipeTypes.NEUTRON_ACTIVATOR_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XXSXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .aisle("F###F", "#GGG#", "#GPG#", "#GGG#", "F###F")
                    .aisle("F###F", "#GGG#", "#GPG#", "#GGG#", "F###F")
                    .aisle("F###F", "#GGG#", "#GPG#", "#GGG#", "F###F")
                    .aisle("F###F", "#GGG#", "#GPG#", "#GGG#", "F###F")
                    .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_STAINLESS_CLEAN.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('F', frames(GTMaterials.Steel))
                    .where('P', blocks(CASING_SPEEDING_PIPE.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    CTMod.id("block/multiblock/neutron_activator"), false)
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE.multiblock("chemical_plant", WorkableElectricMultiblockMachine::new)
            .langValue("Chemical Plant")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
            .recipeType(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .recipeModifier((machine, recipe) -> CTRecipeModifiers.parallelOverclock(machine, recipe, 8, false))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XXXSXXX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XXXXXXX")
                    .aisle("X#####X", "#HHHHH#", "#HCCCH#", "#HCCCH#", "#HCCCH#", "#HHHHH#", "X#####X")
                    .aisle("X#####X", "#######", "##PPP##", "##PPP##", "##PPP##", "#######", "X#####X")
                    .aisle("X#####X", "#######", "##CCC##", "##CCC##", "##CCC##", "#######", "X#####X")
                    .aisle("X#####X", "#######", "##PPP##", "##PPP##", "##PPP##", "#######", "X#####X")
                    .aisle("X#####X", "#HHHHH#", "#HCCCH#", "#HCCCH#", "#HCCCH#", "#HHHHH#", "X#####X")
                    .aisle("XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('H', blocks(MACHINE_CASING_LuV.get()))
                    .where('F', frames(GTMaterials.TungstenSteel))
                    .where('P', blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where('C', blocks(COIL_HSSG.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                    CTMod.id("block/multiblock/chemical_plant"), false)
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();


   public static  MultiblockMachineDefinition[] VOID_MINER = registerTieredMultis("void_miner", VoidMinerMachine::new, (tier, builder) -> builder
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("%s Void Ore Miner %s".formatted(VLVH[tier], VLVT[tier]))
            .recipeType(new GTRecipeType(GTCEu.id("void_miner"), "dummy"))
            .tooltips(
            Component.translatable("cerestech.machine.void_miner.tooltip"))
            .appearanceBlock(() -> VoidMinerMachine.getCasingState(tier))
            .pattern((definition) -> FactoryBlockPattern.start()
            .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                                    .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                                    .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                                    .where('S', controller(blocks(definition.get())))
            .where('X', blocks(VoidMinerMachine.getCasingState(tier)).setMinGlobalLimited(3)
                                            .or(abilities(PartAbility.INPUT_ENERGY, PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setMaxGlobalLimited(3))
            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1)))
            .where('C', blocks(VoidMinerMachine.getCasingState(tier)))
            .where('F', blocks(VoidMinerMachine.getFrameState(tier)))
            .where('#', any())
            .build())
            .workableCasingRenderer(VoidMinerMachine.getBaseTexture(tier), GTCEu.id("block/multiblock/bedrock_ore_miner"), false)
            .register(),
    LuV, ZPM, UV);

    public static MultiblockMachineDefinition BACTERIAL_VAT = REGISTRATE.multiblock("bacterial_vat", WorkableElectricMultiblockMachine::new)
            .langValue("Bacterial Vat")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .recipeType(CTRecipeTypes.BACTERIAL_VAT_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XXSXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .aisle("GGGGG", "GWWWG", "GWWWG", "GWWWG", "GGGGG")
                    .aisle("GGGGG", "GWWWG", "GWWWG", "GWWWG", "GGGGG")
                    .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_STAINLESS_CLEAN.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('W', blocks(WATER))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    CTMod.id("block/multiblock/bacterial_vat"), false)
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition MEGA_CHEMICAL_REACTOR = REGISTRATE.multiblock("mega_chemical_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Chemical Plant")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.chemical_reactor")))
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_PTFE_INERT)
            .recipeType(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .recipeModifier(GTRecipeModifiers.PARALLEL_HATCH.apply(OverclockingLogic.PERFECT_OVERCLOCK, GTRecipeModifiers.ELECTRIC_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XXXXX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XXXXX")
                    .aisle("XGGGX", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "XXXXX")
                    .aisle("XGSGX", "#GFG#", "#GFG#", "#GFG#", "#GFG#", "#GFG#", "#GFG#", "#GFG#", "XXXXX")
                    .aisle("XGGGX", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "#G#G#", "XXXXX")
                    .aisle("XXXXX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XPXPX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_PTFE_INERT.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('G', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('P', blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                    .where('F', blocks(FUSION_COIL.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    CTMod.id("block/multiblock/mega_chemical_reactor"), false)
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition MEGA_MULTI_SMELTER = REGISTRATE.multiblock("mega_multi_smelter", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Mega Multi Smelter")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_INVAR_HEATPROOF)
            .recipeTypes(GTRecipeTypes.FURNACE_RECIPES, GTRecipeTypes.ALLOY_SMELTER_RECIPES, GTRecipeTypes.ARC_FURNACE_RECIPES)
            .recipeModifier(GTRecipeModifiers.PARALLEL_HATCH.apply(OverclockingLogic.PERFECT_OVERCLOCK, (oc) -> GTRecipeModifiers::multiSmelterOverclock))
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("XXXSXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX")
                    .aisle("GGGGGGG", "GCCCCCG", "GC###CG", "GC###CG", "GC###CG", "GCCCCCG", "GGGGGGG")
                    .aisle("GGGGGGG", "GCCCCCG", "GC###CG", "GC###CG", "GC###CG", "GCCCCCG", "GGGGGGG")
                    .aisle("GGGGGGG", "GCCCCCG", "GC###CG", "GC###CG", "GC###CG", "GCCCCCG", "GGGGGGG")
                    .aisle("GGGGGGG", "GCCCCCG", "GC###CG", "GC###CG", "GC###CG", "GCCCCCG", "GGGGGGG")
                    .aisle("GGGGGGG", "GCCCCCG", "GC###CG", "GC###CG", "GC###CG", "GCCCCCG", "GGGGGGG")
                    .aisle("XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_INVAR_HEATPROOF.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('C', heatingCoils())
                    .where('G', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_heatproof"),
                    CTMod.id("block/multiblock/mega_multi_smelter"), false)
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.multi_furnace.heating_coil_level", coilMachine.getCoilType().getLevel()));
                    components.add(Component.translatable("gtceu.multiblock.multi_furnace.heating_coil_discount", coilMachine.getCoilType().getEnergyDiscount()));
                }
            })
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static MultiblockMachineDefinition STELLAR_FORGE = REGISTRATE.multiblock("stellar_forge", WorkableElectricMultiblockMachine::new)
            .langValue("Stellar Forge")
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_ENRICHED_NAQUADAH)
            .recipeType(CTRecipeTypes.STELLAR_FORGE_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("###############", "######CCC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                    .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                    .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                    .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                    .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                    .aisle("######C#C######", "#F###########F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F###########F#", "######C#C######")
                    .aisle("#CCCCCCMCCCCCC#", "CF####XXX####FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF####XXX####FC", "#CCCCCCMCCCCCC#")
                    .aisle("######MMM######", "CF####XXX####FC", "###FX#####XF###", "####X#####X####", "####X#####X####", "####X#####X####", "###FX#####XF###", "CF####XXX####FC", "######MMM######")
                    .aisle("#CCCCCCMCCCCCC#", "CF####XXX####FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF####XXX####FC", "#CCCCCCMCCCCCC#")
                    .aisle("######C#C######", "#F###########F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F###########F#", "######C#C######")
                    .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                    .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                    .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                    .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                    .aisle("###############", "######CSC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_STELLAR_CONTAINMENT.get()))
                    .where('M', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('C', blocks(CASING_ENRICHED_NAQUADAH.get())
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('F', blocks(FUSION_CASING_MK2.get()))
            .where('#', Predicates.any())
                    .build())
            .workableCasingRenderer(CTMod.id("block/casings/solid/machine_casing_enriched_naquadah"),
                    CTMod.id("block/multiblock/stellar_forge"), false)
            .compassSections(GTCompassSections.TIER[UHV])
            .compassNodeSelf()
            .register();
}
