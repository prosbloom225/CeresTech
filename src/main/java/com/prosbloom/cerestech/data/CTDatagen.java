package com.prosbloom.cerestech.data;

import com.prosbloom.cerestech.data.lang.CTLangHandler;
import com.tterrag.registrate.providers.ProviderType;

import static com.prosbloom.cerestech.registry.CTRegistries.REGISTRATE;

public class CTDatagen {
    public static void init() {
        /*
        CT_REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ItemTagLoader::init);
        CT_REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, BlockTagLoader::init);
        CT_REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTagLoader::init);

         */
        REGISTRATE.addDataGenerator(ProviderType.LANG, CTLangHandler::init);
    }
}
