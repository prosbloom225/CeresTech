package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.BedrockOreVeinSavedData;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.OreVeinWorldEntry;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import com.gregtechceu.gtceu.common.machine.trait.BedrockOreMinerLogic;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.prosbloom.cerestech.api.machine.trait.VoidMinerLogic;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.openjdk.nashorn.internal.objects.annotations.Getter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.DrillingFluid;
import static com.prosbloom.cerestech.data.CTFluids.Pyrotheum;
import static java.util.Map.entry;

public class VoidMinerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {
    private final int tier;
    private final static FluidStack coolant = DrillingFluid.getFluid(20);

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new VoidMinerLogic(this);
    }

    public VoidMinerMachine(IMachineBlockEntity holder, Integer tier) {
        super(holder);
        this.tier = tier;
    }

    public static Block getCasingState(int tier) {
        if (tier == GTValues.EV)
            return MACHINE_CASING_EV.get();
        if (tier == GTValues.IV)
            return MACHINE_CASING_IV.get();
        if (tier == GTValues.LuV)
            return MACHINE_CASING_LuV.get();
        if (tier == GTValues.ZPM)
            return MACHINE_CASING_ZPM.get();
        if (tier == GTValues.UV)
            return MACHINE_CASING_UV.get();
        if (tier == GTValues.UHV)
            return MACHINE_CASING_UHV.get();
        if (tier == GTValues.UEV)
            return MACHINE_CASING_UEV.get();
        if (tier == GTValues.UIV)
            return MACHINE_CASING_UEV.get();
        return GTBlocks.CASING_STEEL_SOLID.get();
    }

    public static Block getFrameState(int tier) {
        if (tier == GTValues.LuV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.NaquadahAlloy).get();
        if (tier == GTValues.ZPM)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Americium).get();
        if (tier == GTValues.UV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Neutronium).get();
        return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Steel).get();
    }

    @Override
    public void onWorking() {
        super.onWorking();
        if (getOffsetTimer() %20 ==0) {
            List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
            if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
            if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
            var fluidDrained = FluidStack.empty();
            for (IRecipeHandler<?> tank : inputTanks)
                if (tank instanceof NotifiableFluidTank) {
                    for (int i=0;i<((NotifiableFluidTank) tank).storages.length;i++){
                        fluidDrained = ((NotifiableFluidTank) tank).storages[i].drain(coolant, false);
                        if (!fluidDrained.isEmpty())
                            return;
                    }
                }
            // TODO - probably a more elegant way of doing this so you dont have to manually restart machines when out of cryo..
            if (fluidDrained.isEmpty() && fluidDrained.getAmount() != coolant.getAmount()) {
                this.recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
            } else
                this.recipeLogic.setStatus(RecipeLogic.Status.WORKING);
        }
    }

    public static ResourceLocation getBaseTexture(int tier) {
        if (tier == GTValues.EV)
            return GTCEu.id("block/casings/voltage/ev/side");
        if (tier == GTValues.IV)
            return GTCEu.id("block/casings/voltage/iv/side");
        if (tier == GTValues.LuV)
            return GTCEu.id("block/casings/voltage/luv/side");
        if (tier == GTValues.ZPM)
            return GTCEu.id("block/casings/voltage/zpm/side");
        if (tier == GTValues.UV)
            return GTCEu.id("block/casings/voltage/uv/side");
        if (tier == GTValues.UHV)
            return GTCEu.id("block/casings/voltage/uhv/side");
        if (tier == GTValues.UEV)
            return GTCEu.id("block/casings/voltage/uev/side");
        if (tier == GTValues.UIV)
            return GTCEu.id("block/casings/voltage/uIv/side");
        return GTCEu.id("block/casings/solid/machine_casing_solid_steel");
    }
}
