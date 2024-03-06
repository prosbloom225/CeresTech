package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.CTMod;
import net.minecraft.world.level.material.Fluid;
import org.checkerframework.checker.units.qual.Temperature;

import java.awt.*;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CTFluids {
    public static void init() {

    }

    public static Material Coolant = new Material.Builder(CTMod.id("coolant"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .components(Lithium, 2, Fluorine, 7, Beryllium, 3)
            .color(0x328BA8).iconSet(MaterialIconSet.FLUID)
            .components()
            .buildAndRegister();

    public static Material CoolantHot = new Material.Builder(CTMod.id("coolant_hot"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x6124BD).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PahoehoeLava = new Material.Builder(CTMod.id("pahoehoe_lava"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0XFCBA03).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Pyrotheum = new Material.Builder(CTMod.id("pyrotheum"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xFF9D00).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UranylChlorideSolution = new Material.Builder(CTMod.id("uranyl_chloride_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UranylNitrateSolution = new Material.Builder(CTMod.id("uranyl_nitrate_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PurifiedUranylNitrateSolution = new Material.Builder(CTMod.id("purified_uranyl_nitrate_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UraniumSulfateWasteSolution = new Material.Builder(CTMod.id("uranium_sulfate_waste_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material AmmoniaDiuranateSolution = new Material.Builder(CTMod.id("ammonia_diuranate_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PiranhaSolution = new Material.Builder(CTMod.id("piranha_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x3A1D81) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material HydrogenPeroxide = new Material.Builder(CTMod.id("hydrogen_peroxide"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xA9D0F5) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UraniumRefinementWasteSolution = new Material.Builder(CTMod.id("uranium_refinement_waste_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material ThoriumNitrateSolution = new Material.Builder(CTMod.id("thorium_nitrate_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x32F032) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material RareEarthHydroxides = new Material.Builder(CTMod.id("rare_earth_hydroxides"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBDB78C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RareEarthChlorides = new Material.Builder(CTMod.id("rare_earth_chlorides"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x246E57) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material LaNdOxidesSolution = new Material.Builder(CTMod.id("la_nd_oxides_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x78ACA6) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material SmGdOxidesSolution = new Material.Builder(CTMod.id("sm_gd_oxides_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBDBD73) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material TbHoOxidesSolution = new Material.Builder(CTMod.id("tb_ho_oxides_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7DCE7D) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material ErLuOxidesSolution = new Material.Builder(CTMod.id("er_lu_oxides_solution"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBF88BF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RadoxGas = new Material.Builder(CTMod.id("radox_gas"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x680064) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material RawRadox = new Material.Builder(CTMod.id("raw_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x3F1B3F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material SuperHeavyRadox = new Material.Builder(CTMod.id("super_heavy_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x4D064D) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material HeavyRadox = new Material.Builder(CTMod.id("heavy_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x590559) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material LightRadox= new Material.Builder(CTMod.id("light_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x6F046F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material SuperLightRadox = new Material.Builder(CTMod.id("super_light_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x6F046F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material CrackedRadox = new Material.Builder(CTMod.id("cracked_radox"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x906990) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Xenoxene = new Material.Builder(CTMod.id("xenoxene"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x979390) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material FermentedBacterialSludge = new Material.Builder(CTMod.id("fermented_bacterial_sludge"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x009414) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material SterilizedBioCatalystMedium = new Material.Builder(CTMod.id("sterilized_bio_catalyst_medium"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7CBE2C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RawBioCatalystMedium = new Material.Builder(CTMod.id("raw_bio_catalyst_medium"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x4C7027) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RawGrowthCatalystMedium= new Material.Builder(CTMod.id("raw_growth_catalyst_medium"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA26E4B) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material LiquidCrystalKevlar = new Material.Builder(CTMod.id("liquid_crystal_kevlar"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xB3B35C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material PolyurethaneResin = new Material.Builder(CTMod.id("polyurethane_resin"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xB3B35C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material NMethyl2Pyrrolidone = new Material.Builder(CTMod.id("n_methyl_2_pyrrolidone"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Aniline = new Material.Builder(CTMod.id("aniline"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x4D4D1B) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material DimethylTerephthalate = new Material.Builder(CTMod.id("dimethyl_terephthalate"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material ThionylChloride = new Material.Builder(CTMod.id("thionyl_chloride"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material SulfurDichloride = new Material.Builder(CTMod.id("sulfur_dichloride"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x970505) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PropionicAcid = new Material.Builder(CTMod.id("propionic_acid"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material NickelTetracarbonyl = new Material.Builder(CTMod.id("nickel_tetracarbonyl"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Formaldehyde = new Material.Builder(CTMod.id("formaldehyde"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7A447A) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material DiphenylmethaneDiisocyanateMixture= new Material.Builder(CTMod.id("diphenylmethane_diisocyanate_mixture"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBEAC29) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material DiaminodiphenylmethaneMixture = new Material.Builder(CTMod.id("diaminodiphenylmethane_mixture"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBEAC29) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Phosgene = new Material.Builder(CTMod.id("phosgene"))
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x157A18) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

}
