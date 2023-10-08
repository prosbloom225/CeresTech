package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.LoaderType.FABRIC;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.LoaderType.FORGE;
import static com.prosbloom.cerestech.data.NuclearReactorRecipes.fertileMaterials;
import static com.prosbloom.cerestech.data.NuclearReactorRecipes.fissileMaterials;

public class CTTagPrefixes {
    public static void init() {}

    public static final MaterialIconType materialIconFuelPure = new MaterialIconType("fuel_pure");
    public static final MaterialIconType materialIconFuelDepleted = new MaterialIconType("fuel_depleted");
    public static final MaterialIconType materialIconWaste = new MaterialIconType("waste");

    public static final TagPrefix fuelPure = new TagPrefix("fuel_pure")
            .defaultTagPath(FORGE, "fuel_pure/%s")
            .defaultTagPath(FABRIC, "%s_fuel_pure")
            .unformattedTagPath(FORGE, "fuel_pure")
            .unformattedTagPath(FABRIC, "fuel_pure")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelPure)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));
    public static final TagPrefix dustOxide = new TagPrefix("dust_oxide")
            .defaultTagPath(FORGE, "dust_oxide/%s")
            .defaultTagPath(FABRIC, "%s_dust_oxide")
            .unformattedTagPath(FORGE, "dust_oxide")
            .unformattedTagPath(FABRIC, "dust_oxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));

    public static final TagPrefix fuelOxide = new TagPrefix("fuel_oxide")
            .defaultTagPath(FORGE, "fuel_oxide/%s")
            .defaultTagPath(FABRIC, "%s_fuel_oxide")
            .unformattedTagPath(FORGE, "fuel_oxide")
            .unformattedTagPath(FABRIC, "fuel_oxide")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelPure)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));

    public static final TagPrefix depletedRod = new TagPrefix("depleted_rod")
            .defaultTagPath(FORGE, "depleted_rod/%s")
            .defaultTagPath(FABRIC, "%s_depleted_rod")
            .unformattedTagPath(FORGE, "depleted_rod")
            .unformattedTagPath(FABRIC, "depleted_rod")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));
    public static final TagPrefix depletedOxideRod = new TagPrefix("depleted_oxide_rod")
            .defaultTagPath(FORGE, "depleted_oxide_rod/%s")
            .defaultTagPath(FABRIC, "%s_depleted_oxide_rod")
            .unformattedTagPath(FORGE, "depleted_oxide_rod")
            .unformattedTagPath(FABRIC, "depleted_oxide_rod")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));

    public static final TagPrefix depletedFuelNitride = new TagPrefix("depleted_fuel_nitride")
            .defaultTagPath(FORGE, "depleted_fuel_nitride/%s")
            .defaultTagPath(FABRIC, "%s_depleted_fuel_nitride")
            .unformattedTagPath(FORGE, "depleted_fuel_nitride")
            .unformattedTagPath(FABRIC, "depleted_fuel_nitride")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));

    public static final TagPrefix waste = new TagPrefix("waste")
            .defaultTagPath(FORGE, "waste/%s")
            .defaultTagPath(FABRIC, "%s_waste")
            .unformattedTagPath(FORGE, "waste")
            .unformattedTagPath(FABRIC, "waste")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconWaste)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->fissileMaterials.contains(mat.getName()) ||
                    fertileMaterials.contains(mat.getName()));
}
