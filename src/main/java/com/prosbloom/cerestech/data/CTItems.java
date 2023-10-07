package com.prosbloom.cerestech.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;

public class CTItems {

    public static void init(){}
    public static ItemEntry<Item> WASTE_NUCLEAR = REGISTRATE.item("waste_nuclear", Item::new).lang("Waste Nuclear").register();
}
