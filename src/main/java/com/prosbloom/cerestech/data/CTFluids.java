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

public class CTFluids {
    public static void init() {

    }

    // TODO - nitrate solution to nitride
    /*
    public static Material Uranium238FuelNitrate = new Material.Builder("uranium_238_fuel_nitrate")
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(100))
            .color(0x32F032).iconSet(MaterialIconSet.FLUID)
            .element(GTElements.U238)
            .buildAndRegister();

     */

}
