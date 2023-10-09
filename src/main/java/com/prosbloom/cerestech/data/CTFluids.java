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
            // TODO - need to make intermediate dust chains... components wont work with liquid
            .components(Lithium, 2, Fluorine, 7, Beryllium, 3)
            .color(0x328BA8).iconSet(MaterialIconSet.FLUID)
            .components()
            .buildAndRegister();

    public static Material CoolantHot = new Material.Builder("coolant_hot")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .ingot()
            .color(0x6124BD).iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

}
