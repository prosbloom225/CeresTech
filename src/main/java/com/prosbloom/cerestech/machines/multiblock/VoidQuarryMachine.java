package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.api.machine.trait.VoidQuarryLogic;
import net.minecraft.world.level.block.Block;

import static com.gregtechceu.gtceu.common.data.GTMaterials.Titanium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.TungstenSteel;
import static com.prosbloom.cerestech.data.CTMaterials.*;

public class VoidQuarryMachine extends VoidMinerMachine {

    public VoidQuarryMachine(IMachineBlockEntity holder, Integer tier) {
        super(holder, tier);
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new VoidQuarryLogic(this);
    }

    public static Block getFrameState(int tier) {
        if (tier == GTValues.EV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, Titanium).get();
        if (tier == GTValues.IV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, TungstenSteel).get();
        if (tier == GTValues.LuV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, Adamantium).get();
        if (tier == GTValues.ZPM)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Americium).get();
        if (tier == GTValues.UV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Neutronium).get();
        if (tier == GTValues.UHV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, FluxedElectrum).get();
        if (tier == GTValues.UEV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, CosmicNeutronium).get();
        if (tier == GTValues.UIV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, AttunedTengam).get();
        return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Steel).get();
    }
}
