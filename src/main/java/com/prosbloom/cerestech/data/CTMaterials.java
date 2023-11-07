package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.data.recipes.NaquadahReactorRecipes;

import java.util.Objects;

import static com.gregtechceu.gtceu.api.GTValues.MV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty.GasTier.HIGHEST;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.DUST;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.INGOT;
import static com.gregtechceu.gtceu.common.data.GTElements.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTTagPrefixes.dustOxide;

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

    public static Material UranylTricarbonate = new Material.Builder("uranyl_tricarbonate")
            .dust()
            .color(0xCCCC00).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumPeroxideThoriumOxideMixture = new Material.Builder("uranium_peroxide_thorium_oxide_mixture")
            .dust()
            .color(0x282828).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumDioxideThoriumOxideMixture = new Material.Builder("uranium_dioxide_thorium_oxide_mixture")
            .dust()
            .color(0x282828).iconSet(FINE)
            .buildAndRegister();

    public static Material UranylSulfateThoriumOxideMixture = new Material.Builder("uranyl_sulfate_thorium_oxide_mixture")
            .dust()
            .color(0xC8C850).iconSet(FINE)
            .buildAndRegister();

    public static Material UranylNitrateThoriumNitrateMixture = new Material.Builder("uranyl_nitrate_thorium_nitrate_mixture")
            .dust()
            .color(0xCCCC00).iconSet(FINE)
            .buildAndRegister();

    public static Material UraniumOxideThoriumNitrateMixture= new Material.Builder("uranium_oxide_thorium_nitrate_mixture")
            .dust()
            .color(0x32F032).iconSet(FINE)
            .buildAndRegister();

    // Isotopes
    public static Material Uranium234 = new Material.Builder("uranium_234")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x32F032).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(GTElements.U238)
            .buildAndRegister();
    public static Material Neptunium235 = new Material.Builder("neptunium_235")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np235)
            .buildAndRegister();
    public static Material Neptunium237 = new Material.Builder("neptunium_237")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np237)
            .buildAndRegister();
    public static Material Neptunium239 = new Material.Builder("neptunium_239")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x284D7B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Np239)
            .buildAndRegister();
    public static Material Plutonium244 = new Material.Builder("plutonium_244")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xF03232).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Pu244)
            .buildAndRegister();
    public static Material Plutonium240 = new Material.Builder("plutonium_240")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xF03232).iconSet(METALLIC)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .element(CTElements.Pu240)
            .buildAndRegister();
    public static Material Americium241 = new Material.Builder("americium_241")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am241)
            .buildAndRegister();
    public static Material Americium243 = new Material.Builder("americium_243")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am243)
            .buildAndRegister();
    public static Material Americium245 = new Material.Builder("americium_245")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x287869).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Am245)
            .buildAndRegister();
    public static Material Curium246 = new Material.Builder("curium_246")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm246)
            .buildAndRegister();
    public static Material Curium247 = new Material.Builder("curium_247")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm247)
            .buildAndRegister();
    public static Material Curium250 = new Material.Builder("curium_250")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7B544E).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cm250)
            .buildAndRegister();
    public static Material Berkelium247 = new Material.Builder("berkelium_247")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk247)
            .buildAndRegister();
    public static Material Berkelium249 = new Material.Builder("berkelium_249")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk249)
            .buildAndRegister();
    public static Material Berkelium251= new Material.Builder("berkelium_251")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x645A88).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Bk251)
            .buildAndRegister();
    public static Material Californium252 = new Material.Builder("californium_252")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf252)
            .buildAndRegister();
    public static Material Californium253 = new Material.Builder("californium_253")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf253)
            .buildAndRegister();
    public static Material Californium256 = new Material.Builder("californium_256")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xA85A12).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Cf256)
            .buildAndRegister();
    public static Material Einsteinium253 = new Material.Builder("einsteinium_253")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es253)
            .buildAndRegister();
    public static Material Einsteinium255 = new Material.Builder("einsteinium_255")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es255)
            .buildAndRegister();
    public static Material Einsteinium257 = new Material.Builder("einsteinium_257")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xCE9F00).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Es257)
            .buildAndRegister();
    public static Material Fermium258 = new Material.Builder("fermium_258")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm258)
            .buildAndRegister();
    public static Material Fermium259 = new Material.Builder("fermium_259")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm259)
            .buildAndRegister();
    public static Material Fermium262= new Material.Builder("fermium_262")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x984ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Fm262)
            .buildAndRegister();
    public static Material Mendelevium259 = new Material.Builder("mendelevium_259")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md259)
            .buildAndRegister();
    public static Material Mendelevium261= new Material.Builder("mendelevium_261")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md261)
            .buildAndRegister();
    public static Material Mendelevium263 = new Material.Builder("mendelevium_263")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x1D4ACF).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .element(CTElements.Md263)
            .buildAndRegister();

    public static Material ThoriumUraniumSludge = new Material.Builder("thorium_uranium_sludge")
            .dust()
            .color(0x000000).iconSet(FINE)
            .buildAndRegister();

    public static Material EnrichedHolmium = new Material.Builder("enriched_holmium")
            .ingot()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x0F55DA).iconSet(METALLIC)
            .blastTemp(9000, BlastProperty.GasTier.HIGH, VA[MV], 6000)
            .appendFlags(EXT_METAL, GENERATE_FOIL)
            .buildAndRegister();

    public static Material Kevlar = new Material.Builder("kevlar")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xBBBB5E).iconSet(DULL)
            .appendFlags(EXT_METAL)
            .buildAndRegister();
    public static Material Adamantium = new Material.Builder("adamantium")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0xE2E2E2).iconSet(METALLIC)
            .blastTemp(7200, BlastProperty.GasTier.HIGH, VA[MV], 14400)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static Material ArtheriumSn = new Material.Builder("artherium_sn")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x5D34EE).iconSet(METALLIC)
            .blastTemp(6500, BlastProperty.GasTier.HIGH, VA[GTValues.ZPM], 500)
            .components(Adamantium, 12, Tin, 8, Arsenic, 7, Caesium, 4, Osmiridium, 3)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material Lafium = new Material.Builder("lafium")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x7A9085).iconSet(METALLIC)
            .blastTemp(7200, BlastProperty.GasTier.HIGH, VA[GTValues.LuV], 7200)
            .components(HastelloyC276, 4, Naquadah, 2, Samarium, 1, Tungsten, 2, Aluminium, 3, Nickel, 4 )
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static Material GelidCryotheum = new Material.Builder("gelid_cryotheum")
            .dust()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2BB1FA) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Blizz = new Material.Builder("blizz")
            .dust()
            .color(0xFFFFFF) .iconSet(MaterialIconSet.FLUID)
            .buildAndRegister();

    public static Material Radox = new Material.Builder("radox")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x680064).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material BlackPlutonium = new Material.Builder("black_plutonium")
            .ingot(3)
            .ore()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(METALLIC)
            .appendFlags(EXT_METAL)
            .buildAndRegister();

    public static Material CosmicNeutronium = new Material.Builder("cosmic_neutronium")
            .ingot()
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(OPAL)
            .appendFlags(STD_METAL)
            .buildAndRegister();

    public static Material EnrichedNaquadahAlloy = new Material.Builder("enriched_naquadah_alloy")
            .ingot(3)
            .fluid(FluidStorageKeys.LIQUID, new FluidBuilder())
            .color(0x2B2B2B).iconSet(METALLIC)
            .blastTemp(10000, HIGHEST, VA[MV], 24800)
            .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Rubidium, 2, Dubnium, 1, Einsteinium255, 1)
            .appendFlags(EXT_METAL, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .buildAndRegister();

    // Plasma
    // TODO - dont see a way to add plasma to fluids like you can with addFlags..making a plasma specific element for the time
    public static Material SilverPlasma = new Material.Builder("silver_plasma")
            .plasma()
            .color(0xDCDCFF).iconSet(SHINY)
            .element(GTElements.Ag)
            .buildAndRegister();

    public static void init() {

        // Isotopes
        Objects.requireNonNull(GTRegistries.MATERIALS.get("neptunium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("curium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("berkelium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("californium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("einsteinium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("fermium")).setProperty(INGOT, new IngotProperty());
        Objects.requireNonNull(GTRegistries.MATERIALS.get("mendelevium")).setProperty(INGOT, new IngotProperty());


        // Lanthanides
        Objects.requireNonNull(GTRegistries.MATERIALS.get("dysprosium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("holmium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("erbium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("thulium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("ytterbium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("praseodymium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("promethium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("gadolinium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("terbium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("rubidium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("strontium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("francium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("radium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("thallium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("hafnium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("polonium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("technetium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("zirconium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("iodine")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("selenium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("germanium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("tellurium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("astatine")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("actinium")).setProperty(DUST, new DustProperty(1,0));
        Objects.requireNonNull(GTRegistries.MATERIALS.get("scandium")).setProperty(DUST, new DustProperty(1,0));

        Objects.requireNonNull(GTRegistries.MATERIALS.get("lead")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("stainless_steel")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("hastelloy_x")).addFlags(GENERATE_DENSE, GENERATE_GEAR);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("vanadium")).addFlags(GENERATE_ROD, GENERATE_FRAME);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("titanium")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("iridium")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("naquadah")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("damascus_steel")).addFlags(GENERATE_FRAME);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("steel")).addFlags(GENERATE_DENSE);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("blue_alloy")).addFlags(GENERATE_FRAME);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("incoloy_ma_956")).addFlags(GENERATE_GEAR);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("americium")).addFlags(GENERATE_FRAME);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("hsla_steel")).addFlags(GENERATE_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_BOLT_SCREW, GENERATE_ROTOR);
        Objects.requireNonNull(GTRegistries.MATERIALS.get("titanium_tungsten_carbide")).addFlags(GENERATE_FINE_WIRE, GENERATE_GEAR);


    }
}
