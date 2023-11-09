package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.RendererBlock;
import com.gregtechceu.gtceu.api.item.RendererBlockItem;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.client.renderer.block.TextureOverrideRenderer;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.prosbloom.cerestech.CTMod;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.prosbloom.cerestech.api.machine.trait.CTRegistries.REGISTRATE;
import static com.prosbloom.cerestech.machines.BlockHelper.createCasingBlock;

@SuppressWarnings("deprecated")
public class CTBlocks {

    public static void init(){
    }

    // TODO - casing blocks are unbreakable
    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR= createCasingBlock("shielded_reactor_casing", CTMod.id("block/casings/solid/machine_casing_shielded_reactor"));

    public static final BlockEntry<Block> CASING_VOLCANUS = createCasingBlock("volcanus_casing", CTMod.id("block/casings/solid/machine_casing_volcanus"));
    public static final BlockEntry<Block> CASING_CRYOGENIC = createCasingBlock("cryogenic_casing", CTMod.id("block/casings/solid/machine_casing_cryogenic"));
    public static final BlockEntry<Block> CASING_POWER_STATION = createCasingBlock("power_station_casing", CTMod.id("block/casings/solid/machine_casing_power_station"));
    public static final BlockEntry<Block> CASING_PHOTOLITHOGRAPHIC = createCasingBlock("photolithographic_casing", CTMod.id("block/casings/solid/machine_casing_photolithographic"));

    public static final BlockEntry<Block> CASING_SPEEDING_PIPE = createCasingBlock("speeding_pipe_casing", CTMod.id("block/casings/solid/machine_casing_speeding_pipe"));
    public static final BlockEntry<Block> CASING_ENRICHED_NAQUADAH = createCasingBlock("enriched_naquadah_casing", CTMod.id("block/casings/solid/machine_casing_enriched_naquadah"));
    public static final BlockEntry<Block> CASING_STELLAR_CONTAINMENT = createCasingBlock("stellar_containment_casing", GTCEu.id("block/casings/solid/machine_casing_solid_steel"));

    public static final BlockEntry<Block> NEUTRONIUM_CHARGE = REGISTRATE
            .block("neutronium_charge", Block::new)
            .lang("Neutronium Charge")
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(GTToolType.WRENCH.harvestTag, BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();


}