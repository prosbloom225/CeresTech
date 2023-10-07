package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.IV;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.cableGtSingle;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTItems.ELECTRIC_MOTOR_IV;
import static com.gregtechceu.gtceu.common.data.GTItems.ELECTRIC_PISTON_IV;
import static com.gregtechceu.gtceu.common.data.GTMachines.MACERATOR;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.WoodGas;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.PYROLYSE_RECIPES;
import static com.prosbloom.cerestech.data.CTRecipeTypes.TREE_FARM_RECIPES;
import static com.prosbloom.cerestech.machines.CTMachines.NUCLEAR_REACTOR;
import static com.prosbloom.cerestech.machines.CTMachines.TREE_FARM;

public class CTRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CraftingComponent.initializeComponents();
        registerManualRecipes(provider);

        TreeFarmRecipes.registerTreeFarmRecipes(provider);
        NuclearReactorRecipes.registerNuclearReactorRecipes(provider);
        NuclearCycleRecipes.registerNuclearCycleRecipes(provider);
    }

    private static void registerManualRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "tree_farm", TREE_FARM.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.IV_CIRCUITS, 'P', new UnificationEntry(plate, Aluminium), 'B', ELECTRIC_PISTON_IV.asStack(), 'M', ELECTRIC_MOTOR_IV.asStack(), 'X', MACERATOR[IV].asStack(), 'K', new UnificationEntry(cableGtSingle, Platinum));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "nuclear_reactor", NUCLEAR_REACTOR.asStack(),
                "PCP", "BXB", "MKM",
                'C', CustomTags.IV_CIRCUITS, 'P', new UnificationEntry(plate, Titanium), 'B', ELECTRIC_PISTON_IV.asStack(), 'M', ELECTRIC_MOTOR_IV.asStack(), 'X', MACERATOR[IV].asStack(), 'K', new UnificationEntry(cableGtSingle, Platinum));
    }


}
