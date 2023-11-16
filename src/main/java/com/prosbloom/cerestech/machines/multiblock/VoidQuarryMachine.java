package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.prosbloom.cerestech.api.machine.trait.VoidQuarryLogic;
import net.minecraft.world.level.block.Block;

import static com.prosbloom.cerestech.data.CTMaterials.Adamantium;

public class VoidQuarryMachine extends VoidMinerMachine {

    public VoidQuarryMachine(IMachineBlockEntity holder, Integer tier) {
        super(holder, tier);
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new VoidQuarryLogic(this);
    }

    public static Block getFrameState(int tier) {
        if (tier == GTValues.LuV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, Adamantium).get();
        if (tier == GTValues.ZPM)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Americium).get();
        if (tier == GTValues.UV)
            return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Neutronium).get();
        return GTBlocks.MATERIAL_BLOCKS.get(TagPrefix.frameGt, GTMaterials.Steel).get();
    }
}
