package com.prosbloom.cerestech.data;

import com.prosbloom.cerestech.machines.MetaTileEntityLoader;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CTRecipes {
    public static void recipeAddition(Consumer<FinishedRecipe> consumer) {
        MetaTileEntityLoader.init(consumer);

    }
}
