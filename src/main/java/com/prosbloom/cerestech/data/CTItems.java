package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.prosbloom.cerestech.api.machine.trait.CTCreativeModeTab;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.prosbloom.cerestech.api.machine.trait.CTRegistries.REGISTRATE;


public class CTItems {

    static {
        REGISTRATE.creativeModeTab(()-> CTCreativeModeTab.CT);
    }

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

    // TODO - uhv motor/emitter missing from gtceu...remove these when upstream implements
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
    public static ItemEntry<Item> SEAWEED_EXTRACT = REGISTRATE.item("seaweed_extract", Item::new).lang("Seaweed Extract").register();
    public static ItemEntry<Item> KEVLAR_FIBER = REGISTRATE.item("kevlar_fiber", Item::new).lang("Kevlar Fiber").register();
    public static ItemEntry<Item> WOVEN_KEVLAR = REGISTRATE.item("woven_kevlar", Item::new).lang("Woven Kevlar").register();
    public static ItemEntry<Item> SPINNERET = REGISTRATE.item("spinneret", Item::new).lang("Spinneret").register();
    // Stone
    public static ItemEntry<Item> STONE_MOON = REGISTRATE.item("stone_moon", Item::new).lang("Moon Stone").register();
    public static ItemEntry<Item> STONE_DEIMOS = REGISTRATE.item("stone_deimos", Item::new).lang("Deimos Stone").register();
    public static ItemEntry<Item> STONE_PHOBOS= REGISTRATE.item("stone_phobos", Item::new).lang("Phobos Stone").register();
    public static ItemEntry<Item> STONE_MARS = REGISTRATE.item("stone_mars", Item::new).lang("Mars Stone").register();
    public static ItemEntry<Item> STONE_ASTEROID = REGISTRATE.item("stone_asteroid", Item::new).lang("Asteroid Stone").register();
    public static ItemEntry<Item> STONE_GANYMEDE = REGISTRATE.item("stone_ganymede", Item::new).lang("Ganymede Stone").register();
    public static ItemEntry<Item> STONE_CERES = REGISTRATE.item("stone_ceres", Item::new).lang("Ceres Stone").register();
    public static ItemEntry<Item> STONE_CALLISTO = REGISTRATE.item("stone_callisto", Item::new).lang("Callisto Stone").register();
    public static ItemEntry<Item> STONE_EUROPA = REGISTRATE.item("stone_europa", Item::new).lang("Europa Stone").register();
    public static ItemEntry<Item> STONE_IO= REGISTRATE.item("stone_io", Item::new).lang("Io Stone").register();
    public static ItemEntry<Item> STONE_MERCURY= REGISTRATE.item("stone_mercury", Item::new).lang("Mercury Stone").register();
    public static ItemEntry<Item> STONE_VENUS= REGISTRATE.item("stone_venus", Item::new).lang("Venus Stone").register();
    public static ItemEntry<Item> STONE_ENCELADUS= REGISTRATE.item("stone_enceladus", Item::new).lang("Venus Stone").register();
    public static ItemEntry<Item> STONE_MIRANDA= REGISTRATE.item("stone_miranda", Item::new).lang("Miranda Stone").register();
    public static ItemEntry<Item> STONE_TITAN= REGISTRATE.item("stone_titan", Item::new).lang("Titan Stone").register();
    public static ItemEntry<Item> STONE_OBERON= REGISTRATE.item("stone_oberon", Item::new).lang("Oberon Stone").register();
    public static ItemEntry<Item> STONE_TRITON = REGISTRATE.item("stone_triton", Item::new).lang("Triton Stone").register();
    public static ItemEntry<Item> STONE_PROTEUS = REGISTRATE.item("stone_proteus", Item::new).lang("Proteus Stone").register();
    public static ItemEntry<Item> STONE_HAUMEA= REGISTRATE.item("stone_haumea", Item::new).lang("Haumea Stone").register();
    public static ItemEntry<Item> STONE_MAKEMAKE= REGISTRATE.item("stone_makemake", Item::new).lang("Makemake Stone").register();
    public static ItemEntry<Item> STONE_PLUTO = REGISTRATE.item("stone_pluto", Item::new).lang("Pluto Stone").register();
    public static ItemEntry<Item> STONE_CENTAURI_B= REGISTRATE.item("stone_centauri_b", Item::new).lang("Centauri-B Stone").register();
    public static ItemEntry<Item> STONE_BARNARDA_F = REGISTRATE.item("stone_barnarda_f", Item::new).lang("Barnarda-F Stone").register();
    public static ItemEntry<Item> STONE_VEGA_B = REGISTRATE.item("stone_vega_b", Item::new).lang("Vega-B Stone").register();
    public static ItemEntry<Item> STONE_TCETI_E = REGISTRATE.item("stone_tceti_e", Item::new).lang("TCeti-E Stone").register();
    public static ItemEntry<Item> STONE_BARNARDA_E = REGISTRATE.item("stone_barnarda_e", Item::new).lang("Barnarda-E Stone").register();
}
