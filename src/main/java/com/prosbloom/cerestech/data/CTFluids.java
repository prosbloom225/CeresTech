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

}
