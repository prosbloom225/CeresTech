package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.CTMod;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import static com.prosbloom.cerestech.machines.BlockHelper.createCasingBlock;
import static com.prosbloom.cerestech.machines.BlockHelper.createCoilBlock;
import static com.prosbloom.cerestech.registry.CTRegistries.REGISTRATE;

@SuppressWarnings("deprecated")
public class CTBlocks {

    public static void init() {
    }

    public static final BlockEntry<Block> CASING_SHIELDED_REACTOR = createCasingBlock("shielded_reactor_casing", CTMod.id("block/casings/solid/machine_casing_shielded_reactor"));
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

    public static enum CTCoilType implements StringRepresentable, ICoilType {
        ELECTRUM_FLUX("electrum_flux", 9901, 12, 8, CTMaterials.FluxedElectrum, CTMod.id("block/casings/coils/machine_coil_electrum_flux")),
        AWAKENED_DRACONIUM("awakened_draconium", 10801, 18, 12, CTMaterials.AwakenedDraconium, CTMod.id("block/casings/coils/machine_coil_awakened_draconium")),
        INFINITY("infinity", 11701, 22, 16, CTMaterials.Infinity, CTMod.id("block/casings/coils/machine_coil_infinity")),
        HYPOGEN("hypogen", 12601, 24, 18, CTMaterials.Hypogen, CTMod.id("block/casings/coils/machine_coil_hypogen"));

        private final @NotNull String name;
        private final int coilTemperature;
        private final int level;
        private final int energyDiscount;
        private final @NotNull Material material;
        private final @NotNull ResourceLocation texture;

        private CTCoilType(String name, int coilTemperature, int level, int energyDiscount, Material material, ResourceLocation texture) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
            this.texture = texture;
        }

        public int getTier() {
            return this.ordinal();
        }

        public @NotNull String toString() {
            return this.getName();
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }

        public @NotNull String getName() {
            return this.name;
        }

        public int getCoilTemperature() {
            return this.coilTemperature;
        }

        public int getLevel() {
            return this.level;
        }

        public int getEnergyDiscount() {
            return this.energyDiscount;
        }

        public @NotNull Material getMaterial() {
            return this.material;
        }

        public @NotNull ResourceLocation getTexture() {
            return this.texture;
        }

    }


    public static final BlockEntry<CoilBlock> COIL_ELECTRUM_FLUX = createCoilBlock(CTCoilType.ELECTRUM_FLUX);
    public static final BlockEntry<CoilBlock> COIL_AWAKENED_DRACONIUM = createCoilBlock(CTCoilType.AWAKENED_DRACONIUM);
    public static final BlockEntry<CoilBlock> COIL_INFINITY = createCoilBlock(CTCoilType.INFINITY);
    public static final BlockEntry<CoilBlock> COIL_HYPOGEN = createCoilBlock(CTCoilType.HYPOGEN);


}