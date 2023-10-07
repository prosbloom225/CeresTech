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

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT_METAL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Neptunium;

public class CTMaterials {
    public static void init() {
        // TODO - removing these is a hack until they get used in the main mod
        GTRegistries.MATERIALS.remove("neptunium");
        GTMaterials.Neptunium = new Material.Builder("neptunium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1405))
                .color(0x284D7B).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Np)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("curium");
        GTMaterials.Curium = new Material.Builder("curium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1406))
                .color(0x7B544E).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Cm)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("berkelium");
        GTMaterials.Berkelium = new Material.Builder("berkelium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1407))
                .color(0x645A88).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Bk)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("californium");
        GTMaterials.Californium = new Material.Builder("californium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1408))
                .color(0xA85A12).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Cf)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("einsteinium");
        GTMaterials.Einsteinium = new Material.Builder("einsteinium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1409))
                .color(0xCE9F00).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Es)
                .buildAndRegister();

        GTRegistries.MATERIALS.remove("fermium");
        GTMaterials.Fermium = new Material.Builder("fermium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1410))
                .color(0x984ACF).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Fm)
                .buildAndRegister();


        GTRegistries.MATERIALS.remove("mendelevium");
        GTMaterials.Mendelevium= new Material.Builder("mendelevium")
                .ingot(3)
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().temperature(1411))
                .color(0x1D4ACF).iconSet(METALLIC)
                .appendFlags(EXT_METAL)
                .element(GTElements.Fm)
                .buildAndRegister();
    }

}
