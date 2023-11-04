package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

import static com.gregtechceu.gtceu.common.data.GTBlocks.createCasingBlock;

@SuppressWarnings("deprecated")
public class CTBlocks {

    public static void init(){
    }

    // TODO - casing blocks are unbreakable
    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR= createCasingBlock("shielded_reactor_casing", GTCEu.id("block/casings/solid/machine_casing_shielded_reactor"));

    public static final BlockEntry<Block> CASING_VOLCANUS = createCasingBlock("volcanus_casing", GTCEu.id("block/casings/solid/machine_casing_volcanus"));
    public static final BlockEntry<Block> CASING_POWER_STATION = createCasingBlock("power_station_casing", GTCEu.id("block/casings/solid/machine_casing_power_station"));
    public static final BlockEntry<Block> CASING_PHOTOLITHOGRAPHIC = createCasingBlock("photolithographic_casing", GTCEu.id("block/casings/solid/machine_casing_photolithographic"));

    public static final BlockEntry<Block> CASING_SPEEDING_PIPE = createCasingBlock("speeding_pipe_casing", GTCEu.id("block/casings/solid/machine_casing_speeding_pipe"));


}