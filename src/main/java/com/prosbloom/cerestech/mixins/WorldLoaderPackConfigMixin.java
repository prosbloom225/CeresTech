package com.prosbloom.cerestech.mixins;

import com.gregtechceu.gtceu.forge.GTCEuForge;
import com.mojang.logging.LogUtils;
import com.prosbloom.cerestech.MixinHelpers;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(WorldLoader.PackConfig.class)
public class WorldLoaderPackConfigMixin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @ModifyArg(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/resources/MultiPackResourceManager;<init>(Lnet/minecraft/server/packs/PackType;Ljava/util/List;)V"), index = 1)
    public List<PackResources> ct$injectDynamicData(PackType type, List<PackResources> packs) {
        return MixinHelpers.addDynamicData(packs);
    }

}

