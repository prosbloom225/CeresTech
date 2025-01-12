package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterialBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.api.machine.trait.VoidMinerLogic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.DrillingFluid;

public class VoidMinerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {
    private final int tier;
    private static FluidStack coolant;// = DrillingFluid.getFluid(20);

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
        coolant = DrillingFluid.getFluid(20);
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
            return GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.NaquadahAlloy).get();
        if (tier == GTValues.ZPM)
            return GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Americium).get();
        if (tier == GTValues.UV)
            return GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Neutronium).get();
        return GTMaterialBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Steel).get();
    }

    @Override
    public boolean onWorking() {
        if (getOffsetTimer() %20 ==0) {
            List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
            if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
            if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP))
                inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
            var fluidDrained = FluidStack.EMPTY;
            for (IRecipeHandler<?> tank : inputTanks)
                if (tank instanceof NotifiableFluidTank) {
                    for (int i=0;i<((NotifiableFluidTank) tank).getStorages().length;i++){
                        fluidDrained = ((NotifiableFluidTank) tank).getStorages()[i].drain(coolant, IFluidHandler.FluidAction.EXECUTE);
                        if (!fluidDrained.isEmpty())
                            break;
                    }
                }
            // TODO - probably a more elegant way of doing this so you dont have to manually restart machines when out of cryo..
            if (fluidDrained.isEmpty() && fluidDrained.getAmount() != coolant.getAmount()) {
                this.recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
            } else
                this.recipeLogic.setStatus(RecipeLogic.Status.WORKING);
        }
        super.onWorking();
        return true;
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
