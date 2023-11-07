package com.prosbloom.cerestech.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;

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
}
