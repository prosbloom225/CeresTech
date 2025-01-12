package com.prosbloom.cerestech.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.prosbloom.cerestech.CTMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class CTRegistries {

    public static GTRegistrate REGISTRATE = GTRegistrate.create(CTMod.MODID);

    static {
        CTRegistries.REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    }

    public CTRegistries() {
    }
}

