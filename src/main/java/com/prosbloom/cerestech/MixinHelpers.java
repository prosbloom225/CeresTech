package com.prosbloom.cerestech;

import com.prosbloom.cerestech.data.CTRecipes;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.addon.AddonFinder;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.data.pack.GTDynamicDataPack;
import net.minecraft.server.packs.PackResources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MixinHelpers {
    public static List<PackResources> addDynamicData(Collection<PackResources> packs) {
        List<PackResources> packResources = new ArrayList<>(packs);
        // Clear old data
        GTDynamicDataPack.clearServer();

        // Register recipes & unification data again
        long startTime = System.currentTimeMillis();
        CTRecipes.recipeAddition(GTDynamicDataPack::addRecipe);
        GTCEu.LOGGER.info("CeresTech loading took {}ms", System.currentTimeMillis() - startTime);

        // Load the data
        packResources.add(new GTDynamicDataPack("ct:dynamic_data", AddonFinder.getAddons().stream().map(IGTAddon::addonModId).collect(Collectors.toSet())));
        return packResources;
    }
}
