package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.prosbloom.cerestech.CTMod;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import static com.gregtechceu.gtceu.common.data.GTMachines.CREATIVE_ENERGY;
import static com.prosbloom.cerestech.registry.CTRegistries.REGISTRATE;

public class CreativeModeTabs {
    public static final RegistryEntry<CreativeModeTab> CERESTECH_TAB = REGISTRATE
            .defaultCreativeTab("cerestech", builder -> builder
                    .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("cerestech", REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", CTMod.id("cerestech"), CTMod.NAME))
                    .icon(CREATIVE_ENERGY::asStack)
                    .build())
            .register();
}
