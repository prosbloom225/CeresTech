package com.prosbloom.cerestech.data;

import appeng.block.misc.MysteriousCubeBlock;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.DustProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.prosbloom.cerestech.CTMod;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier.HIGHEST;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.DUST;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.INGOT;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CTMaterials {
    public static Material LithiumFluoride= new Material.Builder(CTMod.id("lithium_fluoride"))
            .dust()
                .color(0xDEDEFA).iconSet(FINE)
                .components(Lithium, 1, Fluorine, 1)
                .buildAndRegister();
    public static Material BerylliumFluoride = new Material.Builder(CTMod.id("beryllium_fluoride"))
            .dust()
            .color(0xDEDEFA).iconSet(FINE)
            .components(Beryllium, 1, Fluorine, 2)
            .buildAndRegister();

    public static Material UranylTricarbonate = new Material.Builder(CTMod.id("uranyl_tricarbonate"))
            .dust()
            .color(0xCCCC00).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumPeroxideThoriumOxideMixture = new Material.Builder(CTMod.id("uranium_peroxide_thorium_oxide_mixture"))
            .dust()
            .color(0x282828).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumDioxideThoriumOxideMixture = new Material.Builder(CTMod.id("uranium_dioxide_thorium_oxide_mixture"))
            .dust()
            .color(0x282828).iconSet(FINE)
            .buildAndRegister();

    public static Material UranylSulfateThoriumOxideMixture = new Material.Builder(CTMod.id("uranyl_sulfate_thorium_oxide_mixture"))
            .dust()
            .color(0xC8C850).iconSet(FINE)
            .buildAndRegister();

    public static Material UranylNitrateThoriumNitrateMixture = new Material.Builder(CTMod.id("uranyl_nitrate_thorium_nitrate_mixture"))
            .dust()
            .color(0xCCCC00).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumOxideThoriumNitrateMixture= new Material.Builder(CTMod.id("uranium_oxide_thorium_nitrate_mixture"))
            .dust()
            .color(0x32F032).iconSet(FINE)
            .buildAndRegister();

    // Isotopes
    public static Material Uranium234 = new Material.Builder(CTMod.id("uranium_234"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x32F032).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(GTElements.U238)
            .buildAndRegister();
    public static Material Neptunium235 = new Material.Builder(CTMod.id("neptunium_235"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np235)
            .buildAndRegister();
    public static Material Neptunium237 = new Material.Builder(CTMod.id("neptunium_237"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np237)
            .buildAndRegister();
    public static Material Neptunium239 = new Material.Builder(CTMod.id("neptunium_239"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np239)
            .buildAndRegister();
    public static Material Plutonium244 = new Material.Builder(CTMod.id("plutonium_244"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xF03232).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Pu244)
            .buildAndRegister();
    public static Material Plutonium240 = new Material.Builder(CTMod.id("plutonium_240"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xF03232).iconSet(METALLIC)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .element(CTElements.Pu240)
            .buildAndRegister();
    public static Material Americium241 = new Material.Builder(CTMod.id("americium_241"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am241)
            .buildAndRegister();
    public static Material Americium243 = new Material.Builder(CTMod.id("americium_243"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am243)
            .buildAndRegister();
    public static Material Americium245 = new Material.Builder(CTMod.id("americium_245"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am245)
            .buildAndRegister();
    public static Material Curium246 = new Material.Builder(CTMod.id("curium_246"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm246)
            .buildAndRegister();
    public static Material Curium247 = new Material.Builder(CTMod.id("curium_247"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm247)
            .buildAndRegister();
    public static Material Curium250 = new Material.Builder(CTMod.id("curium_250"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm250)
            .buildAndRegister();
    public static Material Berkelium247 = new Material.Builder(CTMod.id("berkelium_247"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk247)
            .buildAndRegister();
    public static Material Berkelium249 = new Material.Builder(CTMod.id("berkelium_249"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk249)
            .buildAndRegister();
    public static Material Berkelium251= new Material.Builder(CTMod.id("berkelium_251"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk251)
            .buildAndRegister();
    public static Material Californium252 = new Material.Builder(CTMod.id("californium_252"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf252)
            .buildAndRegister();
    public static Material Californium253 = new Material.Builder(CTMod.id("californium_253"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf253)
            .buildAndRegister();
    public static Material Californium256 = new Material.Builder(CTMod.id("californium_256"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf256)
            .buildAndRegister();
    public static Material Einsteinium253 = new Material.Builder(CTMod.id("einsteinium_253"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es253)
            .buildAndRegister();
    public static Material Einsteinium255 = new Material.Builder(CTMod.id("einsteinium_255"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es255)
            .buildAndRegister();
    public static Material Einsteinium257 = new Material.Builder(CTMod.id("einsteinium_257"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es257)
            .buildAndRegister();
    public static Material Fermium258 = new Material.Builder(CTMod.id("fermium_258"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm258)
            .buildAndRegister();
    public static Material Fermium259 = new Material.Builder(CTMod.id("fermium_259"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm259)
            .buildAndRegister();
    public static Material Fermium262= new Material.Builder(CTMod.id("fermium_262"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm262)
            .buildAndRegister();
    public static Material Mendelevium259 = new Material.Builder(CTMod.id("mendelevium_259"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md259)
            .buildAndRegister();
    public static Material Mendelevium261= new Material.Builder(CTMod.id("mendelevium_261"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md261)
            .buildAndRegister();
    public static Material Mendelevium263 = new Material.Builder(CTMod.id("mendelevium_263"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md263)
            .buildAndRegister();

    public static Material ThoriumUraniumSludge = new Material.Builder(CTMod.id("thorium_uranium_sludge"))
            .dust()
            .color(0x000000).iconSet(FINE)
            .buildAndRegister();

    public static Material EnrichedHolmium = new Material.Builder(CTMod.id("enriched_holmium"))
            .ingot()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x0F55DA).iconSet(METALLIC)
            .blastTemp(9000, BlastProperty.GasTier.HIGH, VA[MV], 6000)
            .appendFlags(EXT_METAL, GENERATE_FOIL)
            .buildAndRegister();

    public static Material Kevlar = new Material.Builder(CTMod.id("kevlar"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBBBB5E).iconSet(DULL)
            .appendFlags(EXT_METAL)
            .buildAndRegister();
    public static Material Adamantium = new Material.Builder(CTMod.id("adamantium"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xE2E2E2).iconSet(METALLIC)
            .blastTemp(7200, BlastProperty.GasTier.HIGH, VA[MV], 14400)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static Material ArtheriumSn = new Material.Builder(CTMod.id("artherium_sn"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x5D34EE).iconSet(METALLIC)
            .blastTemp(6500, BlastProperty.GasTier.HIGH, VA[GTValues.ZPM], 500)
            .components(Adamantium, 12, Tin, 8, Arsenic, 7, Caesium, 4, Osmiridium, 3)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material Lafium = new Material.Builder(CTMod.id("lafium"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7A9085).iconSet(METALLIC)
            .blastTemp(7200, BlastProperty.GasTier.HIGH, VA[GTValues.LuV], 7200)
            .components(HastelloyC276, 4, Naquadah, 2, Samarium, 1, Tungsten, 2, Aluminium, 3, Nickel, 4 )
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static Material Nitinol60 = new Material.Builder(CTMod.id("nitinol_60"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x9985B3).iconSet(METALLIC)
            .blastTemp(1941, BlastProperty.GasTier.HIGH, VA[GTValues.LuV], 7200)
            .components(Titanium, 3, Nickel, 2)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static Material GelidCryotheum = new Material.Builder(CTMod.id("gelid_cryotheum"))
            .dust()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2BB1FA) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Blizz = new Material.Builder(CTMod.id("blizz"))
            .dust()
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Radox = new Material.Builder(CTMod.id("radox"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x680064).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material BlackPlutonium = new Material.Builder(CTMod.id("black_plutonium"))
            .ingot(3)
            // TODO - .ore() seems to have changed/broken - this breaks stellar forge outputs
            //.ore()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material CosmicNeutronium = new Material.Builder(CTMod.id("cosmic_neutronium"))
            .dust()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(OPAL)
            .appendFlags(STD_METAL, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .buildAndRegister();

    public static Material EnrichedNaquadahAlloy = new Material.Builder(CTMod.id("enriched_naquadah_alloy"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(METALLIC)
            .blastTemp(10000, HIGHEST, VA[IV], 24800)
            .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Rubidium, 2, Dubnium, 1, Einsteinium255, 1)
            .appendFlags(EXT_METAL, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .buildAndRegister();

    public static Material FluxedElectrum = new Material.Builder(CTMod.id("fluxed_electrum"))
            .ingot()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xF6F674).iconSet(METALLIC)
            .blastTemp(9000, BlastProperty.GasTier.HIGH, VA[ZPM], 1080)
            .components(RoseGold, 1, Gold, 1, Silver, 1, SterlingSilver, 1, SolderingAlloy, 1, RedSteel, 1, BlueSteel, 1, Naquadah, 1)
            .appendFlags(EXT_METAL, GENERATE_FRAME, GENERATE_FOIL)
            .cableProperties(V[UV], 3, 2)
            .buildAndRegister();

    public static Material InfinityCatalyst = new Material.Builder(CTMod.id("infinity_catalyst"))
            .dust()
            //.ore()
            .color(0xFFFFFF).iconSet(BRIGHT)
            .buildAndRegister();

    public static Material AttunedTengam = new Material.Builder(CTMod.id("attuned_tengam"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x9FBF60).iconSet(BRIGHT)
            .appendFlags(EXT_METAL, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .buildAndRegister();

    public static Material Infinity = new Material.Builder(CTMod.id("infinity"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF).iconSet(BRIGHT)
            .appendFlags(EXT_METAL, GENERATE_FRAME, GENERATE_BOLT_SCREW, GENERATE_FOIL)
            .cableProperties(V[MAX], 8192, 0, true)
            .buildAndRegister();
    public static Material Draconium = new Material.Builder(CTMod.id("draconium"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF).iconSet(BRIGHT)
            .appendFlags(EXT_METAL)
            .cableProperties(V[UEV], 8, 32)
            .buildAndRegister();

    public static Material AwakenedDraconium = new Material.Builder(CTMod.id("awakened_draconium"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xDF4E00).iconSet(BRIGHT)
            .appendFlags(EXT2_METAL)
            .cableProperties(V[MAX], 1, 16)
            .buildAndRegister();

    public static Material Hypogen = new Material.Builder(CTMod.id("hypogen"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xAE6342).iconSet(BRIGHT)
            .appendFlags(EXT2_METAL, GENERATE_FOIL)
            .cableProperties(V[UIV], 8, 0, true)
            .buildAndRegister();

    public static Material DragonBlood = new Material.Builder(CTMod.id("dragon_blood"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBE2A1A).iconSet(BRIGHT)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material Rhugnor = new Material.Builder(CTMod.id("rhugnor"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA509DB).iconSet(BRIGHT)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material MysteriousCrystal = new Material.Builder(CTMod.id("mysterious_crystal"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x158068).iconSet(BRIGHT)
            .appendFlags(EXT_METAL)
            .buildAndRegister();
    public static Material Quantum = new Material.Builder(CTMod.id("quantum"))
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xFFFFFF).iconSet(BRIGHT)
            .components(Stellite100, 3, MysteriousCrystal, 1, Silicon, 1, Gallium, 1, Americium, 1, Palladium, 1, Bismuth, 1, Germanium, 1)
            .blastTemp(9000, BlastProperty.GasTier.HIGH, VA[UEV], 1080)
            .appendFlags(EXT_METAL)
            .buildAndRegister();


    public static Material ParaPhenylenediamine = new Material.Builder(CTMod.id("para_phenylenediamine"))
            .dust()
            .color(0xE5D855).iconSet(BRIGHT)
            .buildAndRegister();
    public static Material TerephthaloylChloride = new Material.Builder(CTMod.id("terephthaloyl_chloride"))
            .dust()
            .color(0x00E60B).iconSet(BRIGHT)
            .buildAndRegister();

    public static Material PolyurethaneCatalyst = new Material.Builder(CTMod.id("polyurethane_catalyst"))
            .dust()
            .color(0x000000).iconSet(METALLIC)
            .buildAndRegister();

    public static Material Pentaerythritol = new Material.Builder(CTMod.id("pentaerythritol"))
            .dust()
            .color(0xFFFFFF).iconSet(METALLIC)
            .buildAndRegister();

    public static Material DiphenylmethaneDiisocyanate = new Material.Builder(CTMod.id("diphenylmethane_diisocyanate"))
            .dust()
            .color(0xBEAC29).iconSet(METALLIC)
            .buildAndRegister();

    // Plasma
    // TODO - dont see a way to add plasma to fluids like you can with addFlags..making a plasma specific element for the time
    public static Material SilverPlasma = new Material.Builder(CTMod.id("silver_plasma"))
            .plasma()
            .color(0xDCDCFF).iconSet(SHINY)
            .element(GTElements.Ag)
            .buildAndRegister();

    // TODO - really need to figure out how to add plasma to an existing gt material
    public static Material DubniumPlasma = new Material.Builder(CTMod.id("dubnium_plasma"))
            .plasma()
            .color(0x9CB9BB).iconSet(SHINY)
            .element(GTElements.Db)
            .buildAndRegister();

    public static void init() {
        // Isotopes
        Neptunium.setProperty(INGOT, new IngotProperty());
        Curium.setProperty(INGOT, new IngotProperty());
        Berkelium.setProperty(INGOT, new IngotProperty());
        Californium.setProperty(INGOT, new IngotProperty());
        Einsteinium.setProperty(INGOT, new IngotProperty());
        Fermium.setProperty(INGOT, new IngotProperty());
        Mendelevium.setProperty(INGOT, new IngotProperty());


        // Lanthanides
        Dysprosium.setProperty(DUST, new DustProperty(1,0));
        Holmium.setProperty(DUST, new DustProperty(1,0));
        Erbium.setProperty(DUST, new DustProperty(1,0));
        Thulium.setProperty(DUST, new DustProperty(1,0));
        Ytterbium.setProperty(DUST, new DustProperty(1,0));
        Praseodymium.setProperty(DUST, new DustProperty(1,0));
        Promethium.setProperty(DUST, new DustProperty(1,0));
        Gadolinium.setProperty(DUST, new DustProperty(1,0));
        Terbium.setProperty(DUST, new DustProperty(1,0));
        Rubidium.setProperty(DUST, new DustProperty(1,0));
        Strontium.setProperty(DUST, new DustProperty(1,0));
        Francium.setProperty(DUST, new DustProperty(1,0));
        Radium.setProperty(DUST, new DustProperty(1,0));
        Thallium.setProperty(DUST, new DustProperty(1,0));
        Hafnium.setProperty(DUST, new DustProperty(1,0));
        Polonium.setProperty(DUST, new DustProperty(1,0));
        Technetium.setProperty(DUST, new DustProperty(1,0));
        Zirconium.setProperty(DUST, new DustProperty(1,0));
        //Iodine.setProperty(DUST, new DustProperty(1,0));
        Selenium.setProperty(DUST, new DustProperty(1,0));
        Germanium.setProperty(DUST, new DustProperty(1,0));
        Tellurium.setProperty(DUST, new DustProperty(1,0));
        Astatine.setProperty(DUST, new DustProperty(1,0));
        Actinium.setProperty(DUST, new DustProperty(1,0));
        Scandium.setProperty(DUST, new DustProperty(1,0));
        Dubnium.setProperty(INGOT, new IngotProperty());

        Lead.addFlags(GENERATE_DENSE);
        StainlessSteel.addFlags(GENERATE_DENSE);
        HastelloyX.addFlags(GENERATE_DENSE, GENERATE_GEAR);
        Vanadium.addFlags(GENERATE_ROD, GENERATE_FRAME);
        Titanium.addFlags(GENERATE_DENSE);
        Iridium.addFlags(GENERATE_DENSE);
        Naquadah.addFlags(GENERATE_DENSE);
        DamascusSteel.addFlags(GENERATE_FRAME);
        Steel.addFlags(GENERATE_DENSE);
        BlueAlloy.addFlags(GENERATE_FRAME);
        IncoloyMA956.addFlags(GENERATE_GEAR);
        Americium.addFlags(GENERATE_FRAME);
        HSLASteel.addFlags(GENERATE_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_ROTOR);
        TitaniumTungstenCarbide.addFlags(GENERATE_FINE_WIRE, GENERATE_GEAR);
        Neutronium.addFlags(GENERATE_FOIL);
        MolybdenumDisilicide.addFlags(GENERATE_BOLT_SCREW);
        IncoloyMA956.addFlags(GENERATE_BOLT_SCREW);


    }
}
