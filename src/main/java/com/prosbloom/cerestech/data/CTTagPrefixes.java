package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.LoaderType.FABRIC;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.LoaderType.FORGE;

public class CTTagPrefixes {
    public static void init() {}

    public static final TagPrefix depletedRod = new TagPrefix("depleted_rod")
            .defaultTagPath(FORGE, "depleted_rod/%s")
            .defaultTagPath(FABRIC, "%s_depleted_rod")
            .unformattedTagPath(FORGE, "depleted_rod")
            .unformattedTagPath(FABRIC, "depleted_rod")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.stick)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->NuclearCycleRecipes.fissileMaterials.contains(mat.getName()) ||
                    NuclearCycleRecipes.fertileMaterials.contains(mat.getName()));
    public static final TagPrefix depletedOxideRod = new TagPrefix("depleted_oxide_rod")
            .defaultTagPath(FORGE, "depleted_oxide_rod/%s")
            .defaultTagPath(FABRIC, "%s_depleted_oxide_rod")
            .unformattedTagPath(FORGE, "depleted_oxide_rod")
            .unformattedTagPath(FABRIC, "depleted_oxide_rod")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.stick)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->NuclearCycleRecipes.fissileMaterials.contains(mat.getName()) ||
                    NuclearCycleRecipes.fertileMaterials.contains(mat.getName()));

    public static final TagPrefix depletedFuelNitride = new TagPrefix("depleted_fuel_nitride")
            .defaultTagPath(FORGE, "depleted_fuel_nitride/%s")
            .defaultTagPath(FABRIC, "%s_depleted_fuel_nitride")
            .unformattedTagPath(FORGE, "depleted_fuel_nitride")
            .unformattedTagPath(FABRIC, "depleted_fuel_nitride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.stick)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->NuclearCycleRecipes.fissileMaterials.contains(mat.getName()) ||
                    NuclearCycleRecipes.fertileMaterials.contains(mat.getName()));

    public static final TagPrefix waste = new TagPrefix("waste")
            .defaultTagPath(FORGE, "waste/%s")
            .defaultTagPath(FABRIC, "%s_waste")
            .unformattedTagPath(FORGE, "waste")
            .unformattedTagPath(FABRIC, "waste")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dustImpure)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat->NuclearCycleRecipes.fissileMaterials.contains(mat.getName()) ||
                    NuclearCycleRecipes.fertileMaterials.contains(mat.getName()));
}
