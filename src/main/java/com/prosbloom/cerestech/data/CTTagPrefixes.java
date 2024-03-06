package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import static com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes.*;

public class CTTagPrefixes {
    public static void init() {}

    public static final MaterialIconType materialIconFuelPure = new MaterialIconType("fuel_pure");
    public static final MaterialIconType materialIconFuelDepleted = new MaterialIconType("fuel_depleted");
    public static final MaterialIconType materialIconWaste = new MaterialIconType("waste");

    public static final TagPrefix fuelPure = new TagPrefix("fuel_pure")
            .defaultTagPath("fuel_pure/%s")
            .unformattedTagPath( "fuel_pure")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelPure)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.isotopeFuelPure.getName())).findAny().orElse(null) != null);
    public static final TagPrefix dustOxide = new TagPrefix("dust_oxide")
            .defaultTagPath( "dust_oxide/%s")
            .unformattedTagPath( "dust_oxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->
                            (mat.getName().equals(rf.baseElement.getName())
                                    || mat.getName().equals(rf.isotopeFuelOxide.getName())
                                    || mat.getName().equals(rf.isotopeFuelPure.getName())
                                    || mat.getName().equals(rf.isotopeDecay.getName()))
                    ).findAny().orElse(null) != null);

    public static final TagPrefix fuelOxide = new TagPrefix("fuel_oxide")
            .defaultTagPath("fuel_oxide/%s")
            .unformattedTagPath("fuel_oxide")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelPure)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->
                                    (mat.getName().equals(rf.baseElement.getName())
                                            || mat.getName().equals(rf.isotopeFuelOxide.getName())
                                            || mat.getName().equals(rf.isotopeFuelPure.getName())
                                            || mat.getName().equals(rf.isotopeDecay.getName()))
                    ).findAny().orElse(null) != null);

    public static final TagPrefix depletedFuel = new TagPrefix("depleted_fuel")
            .defaultTagPath("depleted_fuel/%s")
            .unformattedTagPath("depleted_fuel")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->
                            (mat.getName().equals(rf.baseElement.getName())
                                    || mat.getName().equals(rf.isotopeFuelOxide.getName())
                                    || mat.getName().equals(rf.isotopeFuelPure.getName())
                                    || mat.getName().equals(rf.isotopeFuelPure.getName())
                            )).findAny().orElse(null) != null);
    public static final TagPrefix depletedFuelOxide = new TagPrefix("depleted_fuel_oxide")
            .defaultTagPath("depleted_fuel_oxide/%s")
            .unformattedTagPath("depleted_fuel_oxide")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.getIsotopeFuelOxide().getName())
                            || mat.getName().equals(rf.isotopeFuelPure.getName())
                    ).findAny().orElse(null) != null);

    public static final TagPrefix depletedFuelNitride = new TagPrefix("depleted_fuel_nitride")
            .defaultTagPath("depleted_fuel_nitride/%s")
            .unformattedTagPath("depleted_fuel_nitride")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconFuelDepleted)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.isotopeFuelOxide.getName())
                            || mat.getName().equals(rf.isotopeFuelPure.getName())
                    ).findAny().orElse(null) != null);

    public static final TagPrefix waste = new TagPrefix("waste")
            .defaultTagPath("waste/%s")
            .unformattedTagPath("waste")
            .materialAmount(GTValues.M)
            .materialIconType(materialIconWaste)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.getIsotopeFuelOxide().getName())
                            || mat.getName().equals(rf.isotopeFuelPure.getName())
                    ).findAny().orElse(null) != null);

    public static final TagPrefix dustNitrite = new TagPrefix("dust_nitrite")
            .defaultTagPath("dust_nitrite/%s")
            .unformattedTagPath("dust_nitrite")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.baseElement.getName())).findAny().orElse(null) != null);

    public static final TagPrefix dustDioxide = new TagPrefix("dust_dioxide")
            .defaultTagPath("dust_dioxide/%s")
            .unformattedTagPath("dust_dioxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->
                                    (mat.getName().equals(rf.baseElement.getName())
                                            || mat.getName().equals(rf.isotopeFuelOxide.getName())
                                            || mat.getName().equals(rf.isotopeFuelPure.getName())
                                            || mat.getName().equals(rf.isotopeDecay.getName()))
                    ).findAny().orElse(null) != null);

    public static final TagPrefix hexachloride = new TagPrefix("hexachloride")
            .defaultTagPath("hexachloride/%s")
            .unformattedTagPath("hexachloride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->mat.getName().equals(rf.baseElement.getName())).findAny().orElse(null) != null);
    public static final TagPrefix hexafluoride = new TagPrefix("hexafluoride")
            .defaultTagPath("hexafluoride/%s")
            .unformattedTagPath("hexafluoride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat-> reactorFuels.stream()
                    .filter(rf->
                            (mat.getName().equals(rf.baseElement.getName())
                                    || mat.getName().equals(rf.isotopeFuelOxide.getName())
                                    || mat.getName().equals(rf.isotopeFuelPure.getName())
                                    || mat.getName().equals(rf.isotopeDecay.getName()))
                    ).findAny().orElse(null) != null);
}
