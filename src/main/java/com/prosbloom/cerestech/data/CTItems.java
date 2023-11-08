package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.compassNode;

public class CTItems {

    public static void init(){}
    public static ItemEntry<Item> WASTE_NUCLEAR = REGISTRATE.item("waste_nuclear", Item::new).lang("Waste Nuclear").register();
    public static ItemEntry<Item> WASTE_LANTHANIDE_GROUP_A = REGISTRATE.item("waste_lanthanide_group_a", Item::new).lang("Lanthanide Waste Group A").register();
    public static ItemEntry<Item> WASTE_LANTHANIDE_GROUP_B = REGISTRATE.item("waste_lanthanide_group_b", Item::new).lang("Lanthanide Waste Group B").register();
    public static ItemEntry<Item> WASTE_ALKALINE = REGISTRATE.item("waste_alkaline", Item::new).lang("Alkaline Waste").register();
    public static ItemEntry<Item> WASTE_HEAVY_METAL = REGISTRATE.item("waste_heavy_metal", Item::new).lang("Heavy Metal Waste").register();
    public static ItemEntry<Item> WASTE_METAL_GROUP_A = REGISTRATE.item("waste_metal_group_a", Item::new).lang("Metal Group A Waste").register();
    public static ItemEntry<Item> WASTE_METAL_GROUP_B = REGISTRATE.item("waste_metal_group_b", Item::new).lang("Metal Group B Waste").register();
    public static ItemEntry<Item> WASTE_METAL_GROUP_C = REGISTRATE.item("waste_metal_group_c", Item::new).lang("Metal Group C Waste").register();
    public static ItemEntry<Item> WASTE_NONMETAL = REGISTRATE.item("waste_nonmetal", Item::new).lang("Nonmetal Waste").register();
    public static ItemEntry<Item> WASTE_METALOID = REGISTRATE.item("waste_metaloid", Item::new).lang("Metaloid Waste").register();
    public static ItemEntry<Item> ELECTRIC_MOTOR_UHV = REGISTRATE.item("uhv_electric_motor", Item::new).lang("UHV Electric Motor").register();
    public static ItemEntry<Item> EMITTER_UHV = REGISTRATE.item("uhv_emitter", Item::new).lang("UHV Emitter").register();

    public static ItemEntry<Item> BIO_PROCESSOR_ZPM = REGISTRATE.item("bio_processor", Item::new).lang("BioProcessor").tag(CustomTags.ZPM_CIRCUITS).register();
    public static ItemEntry<Item> BIOWARE_PROCESSOR_ASSEMBLY_UV = REGISTRATE.item("bioware_processor_assembly", Item::new).lang("Bioware Processor Assembly").tag(CustomTags.UV_CIRCUITS).register();
    public static ItemEntry<Item> BIOWARE_SUPERCOMPUTER_UHV = REGISTRATE.item("bioware_supercomputer", Item::new).lang("Bioware Supercomputer").tag(CustomTags.UHV_CIRCUITS).register();
    public static ItemEntry<Item> BIO_MAINFRAME_UEV = REGISTRATE.item("bio_mainframe", Item::new).lang("Bio Mainframe").tag(CustomTags.UEV_CIRCUITS).register();
    public static ItemEntry<Item> BIO_PROCESSING_UNIT = REGISTRATE.item("bio_processing_unit", Item::new).lang("Bio Processing Unit").register();
    public static ItemEntry<Item> ULTRA_BIO_MUTATED_CIRCUIT_BOARD = REGISTRATE.item("ultra_bio_mutated_circuit_board", Item::new).lang("Ultra Bio Mutated Circuit Board").register();
    public static ItemEntry<Item> BIO_CIRCUIT_BOARD = REGISTRATE.item("bio_circuit_board", Item::new).lang("Bio Circuit Board").register();
    public static ItemEntry<Item> BIO_CELLS = REGISTRATE.item("bio_cells", Item::new).lang("Biocells").register();

}
