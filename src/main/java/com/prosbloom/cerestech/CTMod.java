package com.prosbloom.cerestech;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.LDLib;
import com.mojang.logging.LogUtils;
import com.prosbloom.cerestech.addon.CTAddon;
import com.prosbloom.cerestech.api.CTValues;
import com.prosbloom.cerestech.data.CTFluids;
import com.prosbloom.cerestech.data.CTItems;
import com.prosbloom.cerestech.data.CTMaterials;
import com.prosbloom.cerestech.data.CTRecipeTypes;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.prosbloom.cerestech.registry.CTRegistries.REGISTRATE;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CTMod.MODID)
public class CTMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cerestech";
    public static final String NAME = "CeresTech";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public CTAddon ctAddon = new CTAddon();

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path));
    }

    public CTMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.register(this);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        modEventBus.addGenericListener(CoverDefinition.class, this::registerCovers);

        // TODO - datagen
        //CTDatagen.init();

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {}
    private void clientSetup(final FMLClientSetupEvent event) {}


    @SubscribeEvent
    public void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(CTMod.MODID);
    }
    @SubscribeEvent
    public void addMaterials(MaterialEvent event) {
        CTFluids.init();
        CTMaterials.init();
    }
    @SubscribeEvent
    public void modifyMaterials(PostMaterialEvent event) {}

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CTRecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CTMachines.init();
    }
    private void registerCovers(GTCEuAPI.RegisterEvent<ResourceLocation, CoverDefinition> event) {
        CTItems.init();
    }


    public static boolean isAvariaLoaded() { return LDLib.isModLoaded(CTValues.MODID_AVARITIA);}
}
