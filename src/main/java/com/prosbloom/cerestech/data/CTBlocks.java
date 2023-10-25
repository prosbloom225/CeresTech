package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.DustProperty;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;

import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.DUST;
import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.createCasingBlock;

public class CTBlocks {

    public static void init(){
    }

    // TODO - casing blocks are unbreakable
    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR= createCasingBlock("shielded_reactor_casing", GTCEu.id("block/casings/solid/machine_casing_shielded_reactor"));

    public static final BlockEntry<Block> CASING_VOLCANUS = createCasingBlock("volcanus_casing", GTCEu.id("block/casings/solid/machine_casing_volcanus"));
    public static final BlockEntry<Block> CASING_POWER_STATION = createCasingBlock("power_station_casing", GTCEu.id("block/casings/solid/machine_casing_power_station"));



    /*
    public static final BlockEntry<Block> CASING_POWER_STATION = REGISTRATE
            .block("power_station_casing", Block::new)
            .lang("Power Station Casing")
            .initialProperties(() -> Blocks.DIRT)
            //.tag(GTToolType.WRENCH.harvestTag, BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

     */


}