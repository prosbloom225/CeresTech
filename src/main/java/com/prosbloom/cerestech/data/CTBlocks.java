package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.createCasingBlock;

public class CTBlocks {

    public static void init(){}

    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR= createCasingBlock("shielded_reactor_casing", GTCEu.id("block/casings/solid/machine_casing_shielded_reactor"));

    public static final BlockEntry<Block> CASING_VOLCANUS = createCasingBlock("volcanus_casing", GTCEu.id("block/casings/solid/machine_casing_volcanus"));
    public static final BlockEntry<Block> CASING_POWER_STATION = createCasingBlock("power_station_casing", GTCEu.id("block/casings/solid/machine_casing_power_station"));
}