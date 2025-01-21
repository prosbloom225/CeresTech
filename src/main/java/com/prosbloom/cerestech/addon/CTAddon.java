package com.prosbloom.cerestech.addon;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.mojang.logging.LogUtils;
import com.prosbloom.cerestech.CTMod;
import com.prosbloom.cerestech.registry.CTRegistries;
import com.prosbloom.cerestech.compat.CTRecipeCompat;
import com.prosbloom.cerestech.data.*;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.util.function.Consumer;

@GTAddon
public class CTAddon implements IGTAddon{

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public GTRegistrate getRegistrate() {
        return CTRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        CTItems.init();
        CTRecipeTypes.init();
        CTBlocks.init();
        CTMachines.init();
        LOGGER.info("CTAddon init!");
    }

    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
        CTElements.init();
    }

    @Override
    public void registerTagPrefixes() {
        CTTagPrefixes.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CTRecipes.init(provider);
        CTRecipeCompat.init(provider);

    }

    @Override
    public String addonModId() {
        return CTMod.MODID;
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }
}
