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
import net.minecraft.world.level.material.Fluid;
import org.checkerframework.checker.units.qual.Temperature;

import java.awt.*;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CTFluids {
    public static void init() {

    }

    public static Material Coolant = new Material.Builder("coolant")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .components(Lithium, 2, Fluorine, 7, Beryllium, 3)
            .color(0x328BA8).iconSet(MaterialIconSet.FLUID)
            .components()
            .buildAndRegister();

    public static Material CoolantHot = new Material.Builder("coolant_hot")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x6124BD).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PahoehoeLava = new Material.Builder("pahoehoe_lava")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0XFCBA03).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Pyrotheum = new Material.Builder("pyrotheum")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xFF9D00).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UranylChlorideSolution = new Material.Builder("uranyl_chloride_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UranylNitrateSolution = new Material.Builder("uranyl_nitrate_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PurifiedUranylNitrateSolution = new Material.Builder("purified_uranyl_nitrate_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UraniumSulfateWasteSolution = new Material.Builder("uranium_sulfate_waste_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material AmmoniaDiuranateSolution = new Material.Builder("ammonia_diuranate_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material PiranhaSolution = new Material.Builder("piranha_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x3A1D81) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material HydrogenPeroxide = new Material.Builder("hydrogen_peroxide")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xA9D0F5) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material UraniumRefinementWasteSolution = new Material.Builder("uranium_refinement_waste_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0xCCCC00) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material ThoriumNitrateSolution = new Material.Builder("thorium_nitrate_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x32F032) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material RareEarthHydroxides = new Material.Builder("rare_earth_hydroxides")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBDB78C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RareEarthChlorides = new Material.Builder("rare_earth_chlorides")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x246E57) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material LaNdOxidesSolution = new Material.Builder("la_nd_oxides_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x78ACA6) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material SmGdOxidesSolution = new Material.Builder("sm_gd_oxides_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBDBD73) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material TbHoOxidesSolution = new Material.Builder("tb_ho_oxides_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7DCE7D) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material ErLuOxidesSolution = new Material.Builder("er_lu_oxides_solution")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBF88BF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RadoxGas = new Material.Builder("radox_gas")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x680064) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material RawRadox = new Material.Builder("raw_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x3F1B3F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material SuperHeavyRadox = new Material.Builder("super_heavy_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x4D064D) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material HeavyRadox = new Material.Builder("heavy_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x590559) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material LightRadox= new Material.Builder("light_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x6F046F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material SuperLightRadox = new Material.Builder("super_light_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x6F046F) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material CrackedRadox = new Material.Builder("cracked_radox")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x906990) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Xenoxene = new Material.Builder("xenoxene")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x979390) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material FermentedBacterialSludge = new Material.Builder("fermented_bacterial_sludge")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x009414) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material SterilizedBioCatalystMedium = new Material.Builder("sterilized_bio_catalyst_medium")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7CBE2C) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RawBioCatalystMedium = new Material.Builder("raw_bio_catalyst_medium")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x4C7027) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();
    public static Material RawGrowthCatalystMedium= new Material.Builder("raw_growth_catalyst_medium")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA26E4B) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();



}
