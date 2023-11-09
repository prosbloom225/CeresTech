package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.lowdragmc.lowdraglib.utils.LDLItemGroup;
import com.prosbloom.cerestech.CTMod;
import com.prosbloom.cerestech.data.CTItems;

public class CTCreativeModeTab {

    public static LDLItemGroup CT = new LDLItemGroup(CTMod.MODID, CTMod.MODID, () -> CTItems.BIO_PROCESSOR_ZPM.asStack());
}
