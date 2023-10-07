package com.prosbloom.cerestech.machines;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.MV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.cableGtSingle;
import static com.gregtechceu.gtceu.common.data.GTItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT;
import static com.gregtechceu.gtceu.common.data.GTItems.VOLTAGE_COIL_MV;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Platinum;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.data.recipe.CraftingComponent.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static com.prosbloom.cerestech.machines.CTMachines.TREE_FARM;

public class MetaTileEntityLoader {
    public static void init(Consumer<FinishedRecipe> provider) {

        CraftingComponent.initializeComponents();

        VanillaRecipeHelper.addShapedRecipe(provider, true, "tree_farm", TREE_FARM.asStack(),
                "FFF", "FFF", "WCW", 'M', GTBlocks.CASING_INVAR_HEATPROOF.asStack(), 'F', Blocks.FURNACE.asItem(), 'C', CustomTags.LV_CIRCUITS, 'W', new UnificationEntry(TagPrefix.cableGtSingle, GTMaterials.Tin));
    }
}
