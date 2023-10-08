package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CTMaterials {
    public static Material LithiumFluoride= new Material.Builder("lithium_fluoride")
            .dust()
                .color(0xDEDEFA).iconSet(FINE)
                .components(Lithium, 1, Fluorine, 1)
                .buildAndRegister();
    public static Material BerylliumFluoride = new Material.Builder("beryllium_fluoride")
            .dust()
            .color(0xDEDEFA).iconSet(FINE)
            .components(Beryllium, 1, Fluorine, 2)
            .buildAndRegister();
    public static void init() {
        // TODO - removing these is a hack until they get used in the main mod
        GTRegistries.MATERIALS.remove("neptunium");
        GTMaterials.Neptunium = new Material.Builder("neptunium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1405))
                .color(0x284D7B).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Np)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("curium");
        GTMaterials.Curium = new Material.Builder("curium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1406))
                .color(0x7B544E).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Cm)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("berkelium");
        GTMaterials.Berkelium = new Material.Builder("berkelium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1407))
                .color(0x645A88).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Bk)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("californium");
        GTMaterials.Californium = new Material.Builder("californium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1408))
                .color(0xA85A12).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Cf)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("einsteinium");
        GTMaterials.Einsteinium = new Material.Builder("einsteinium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1409))
                .color(0xCE9F00).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Es)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("fermium");
        GTMaterials.Fermium = new Material.Builder("fermium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1410))
                .color(0x984ACF).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Fm)
                .buildAndRegister();


        GTRegistries.MATERIALS.remove("mendelevium");
        GTMaterials.Mendelevium= new Material.Builder("mendelevium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1411))
                .color(0x1D4ACF).iconSet(SHINY)
                .appendFlags(EXT_METAL)
                .element(GTElements.Fm)
                .buildAndRegister();




    }
}