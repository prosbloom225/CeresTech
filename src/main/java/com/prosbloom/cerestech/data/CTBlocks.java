package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.prosbloom.cerestech.CTMod;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static com.prosbloom.cerestech.machines.BlockHelper.createCasingBlock;
import static com.prosbloom.cerestech.registry.CTRegistries.REGISTRATE;

@SuppressWarnings("deprecated")
public class CTBlocks {

    public static void init(){
    }
    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR= createCasingBlock("shielded_reactor_casing", CTMod.id("block/casings/solid/machine_casing_shielded_reactor"));
    public static final BlockEntry<Block> CASING_VOLCANUS = createCasingBlock("volcanus_casing", CTMod.id("block/casings/solid/machine_casing_volcanus"));
    public static final BlockEntry<Block> CASING_CRYOGENIC = createCasingBlock("cryogenic_casing", CTMod.id("block/casings/solid/machine_casing_cryogenic"));
    public static final BlockEntry<Block> CASING_PHOTOLITHOGRAPHIC = createCasingBlock("photolithographic_casing", CTMod.id("block/casings/solid/machine_casing_photolithographic"));
    public static final BlockEntry<Block> CASING_SPEEDING_PIPE = createCasingBlock("speeding_pipe_casing", CTMod.id("block/casings/solid/machine_casing_speeding_pipe"));
    public static final BlockEntry<Block> CASING_ENRICHED_NAQUADAH = createCasingBlock("enriched_naquadah_casing", CTMod.id("block/casings/solid/machine_casing_enriched_naquadah"));
    public static final BlockEntry<Block> CASING_STELLAR_CONTAINMENT = createCasingBlock("stellar_containment_casing", CTMod.id("block/casings/solid/machine_casing_enriched_naquadah"));
    public static final BlockEntry<Block> CASING_BIOLOGICALLY_STERILE = createCasingBlock("biologically_sterile_casing", CTMod.id("block/casings/solid/machine_casing_enriched_naquadah"));

    public static final BlockEntry<Block> NEUTRONIUM_CHARGE = REGISTRATE
            .block("neutronium_charge", Block::new)
            .lang("Neutronium Charge")
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();


}