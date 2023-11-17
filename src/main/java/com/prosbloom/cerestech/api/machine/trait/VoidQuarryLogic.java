package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.prosbloom.cerestech.data.recipes.StoneCentrifugeRecipes;
import com.prosbloom.cerestech.machines.multiblock.VoidQuarryMachine;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.tools.obfuscation.validation.TargetValidator;

import javax.annotation.Nullable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTItems.*;
import static java.util.Map.entry;

public class VoidQuarryLogic extends VoidMinerLogic {

    private final static int BASE_TIME = 2160;
    @Nullable
    private List<Map.Entry<Integer, Item>> veinMaterials;

    public VoidQuarryLogic(IRecipeLogicMachine machine) {
        super(machine);
    }

    private void initVeinMaterials() {
        veinMaterials = new ArrayList<>();
        StoneCentrifugeRecipes.stones.stream()
                .filter(s->getMachine().getTier()-2 > s.tier())
                .forEach(s->veinMaterials.add(new AbstractMap.SimpleEntry<>(10-s.tier(), s.stone())));
    }
    @Override
    public VoidQuarryMachine getMachine() {
        return (VoidQuarryMachine) super.getMachine();
    }

    @Nullable
    @Override
    protected GTRecipe getOreMinerRecipe() {
        if (veinMaterials == null)
            initVeinMaterials();
        if (getMachine().getLevel() instanceof ServerLevel && veinMaterials != null) {
            if (!veinMaterials.isEmpty()) {
                ItemStack stack = new ItemStack(veinMaterials.get(GTUtil.getRandomItem(veinMaterials, veinMaterials.size())).getValue());
                if (!stack.isEmpty()) {
                    var recipe = GTRecipeBuilder.ofRaw()
                            .duration((int) (BASE_TIME / (Math.pow(getMachine().getTier(), 3))))
                            .EUt(GTValues.VA[getMachine().getTier()])
                            .outputItems(stack)
                            .buildRawRecipe();
                    if (recipe.matchRecipe(getMachine()).isSuccess() && recipe.matchTickRecipe(getMachine()).isSuccess()) {
                        return recipe;
                    }
                }
            }
        }
        return null;
    }

}
