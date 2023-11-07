package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.sound.ExistingSoundEntry;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class CTRecipeTypes {
    public static void init() {
    }

    public final static GTRecipeType INDUSTRIAL_GREENHOUSE_RECIPES = register("industrial_greenhouse", MULTIBLOCK).setMaxIOSize(1, 2, 2, 1)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.MV]))
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType GAS_CENTRIFUGE_RECIPES = register("gas_centrifuge", MULTIBLOCK).setMaxIOSize(1, 3, 1, 1)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);

    public final static GTRecipeType NUCLEAR_REACTOR_RECIPES = register("nuclear_reactor", MULTIBLOCK).setMaxIOSize(3, 2, 1, 1)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSlotOverlay(false, false, false, GuiTextures.ATOMIC_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.ATOMIC_OVERLAY_1)
            .setSlotOverlay(true, false, false, GuiTextures.ATOMIC_OVERLAY_1)
            .setSlotOverlay(true, false, true, GuiTextures.ATOMIC_OVERLAY_1)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.EV]))
            .setSound(GTSoundEntries.BOILER);

    public final static GTRecipeType HOT_COOLANT_TURBINE_RECIPES = register("hot_coolant_turbine", MULTIBLOCK).setMaxIOSize(0, 0, 1, 1)
            .setEUIO(IO.OUT)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE);

    public final static GTRecipeType DEHYDRATOR_RECIPES= register("dehydrator", MULTIBLOCK).setMaxIOSize(2, 9, 0, 6)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, LEFT_TO_RIGHT)
            .setSound(new ExistingSoundEntry(SoundEvents.SAND_PLACE, SoundSource.BLOCKS));

    public final static GTRecipeType DECAY_CHAMBER_RECIPES= register("decay_chamber", MULTIBLOCK).setMaxIOSize(1, 1, 0, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setSound(new ExistingSoundEntry(SoundEvents.SAND_PLACE, SoundSource.BLOCKS));

    public final static GTRecipeType NAQUADAH_REACTOR_RECIPES = register("naquadah_reactor", MULTIBLOCK).setMaxIOSize(1, 1, 0, 0)
            .setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setSound(new ExistingSoundEntry(SoundEvents.BLAZE_BURN, SoundSource.BLOCKS));

    public final static GTRecipeType HEAT_EXCHANGER_RECIPES = register("heat_exchanger", MULTIBLOCK).setMaxIOSize(0, 0, 2, 2)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType PCB_FACTORY_RECIPES = register("pcb_factory", MULTIBLOCK).setMaxIOSize(6, 9, 3, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public final static GTRecipeType NEUTRON_ACTIVATOR_RECIPES = register("neutron_activator", MULTIBLOCK).setMaxIOSize(9, 9, 1, 2)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ARC);

    public final static GTRecipeType BACTERIAL_VAT_RECIPES = register("bacterial_vat", MULTIBLOCK).setMaxIOSize(6, 3, 1, 2)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);

    public final static GTRecipeType STELLAR_FORGE_RECIPES = register("stellar_forge", MULTIBLOCK).setMaxIOSize(3, 2, 3, 2)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.REPLICATOR);
}
