package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.prosbloom.cerestech.machines.multiblock.VoidQuarryMachine;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.tools.obfuscation.validation.TargetValidator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.prosbloom.cerestech.data.CTItems.*;
import static java.util.Map.entry;

public class VoidQuarryLogic extends VoidMinerLogic {

    private final static int BASE_TIME = 720;
    @Nullable
    private static final List<Map.Entry<Integer, Item>> veinMaterials = List.<Map.Entry<Integer, Item>>of(
            entry(2, STONE_MOON.get()),
            // T2
            entry(2, STONE_DEIMOS.get()),
            entry(2, STONE_PHOBOS.get()),
            entry(2, STONE_MARS.get())
            /*
            // T3
            entry(2, AsteroidStone),
            entry(2, GanymedeStone),
            entry(2, CeresStone),
            entry(2, CallistoStone),
            entry(2, EuropaStone),
            // T4
            entry(2, IoStone),
            entry(2, MercuryStone),
            entry(2, VenusStone),
            // T5
            entry(2, EnceladusStone),
            entry(2, MirandaStone),
            entry(2, TitanStone),
            entry(2, OberonStone),
            // T6
            entry(2, TritonStone),
            entry(2, ProteusStone),
            // T7
            entry(2, HaumeaStone),
            entry(2, MakeMakeStone),
            entry(2, PlutoStone),
            // T8
            entry(2, CentauriBStone),
            entry(2, BarnardaFStone),
            entry(2, VegaBStone),
            entry(2, TCetiEStone),
            entry(2, BarnardaEStone)

             */

            );

    public VoidQuarryLogic(IRecipeLogicMachine machine) {
        super(machine);
    }

    @Override
    public VoidQuarryMachine getMachine() {
        return (VoidQuarryMachine) super.getMachine();
    }

    @Nullable
    @Override
    protected GTRecipe getOreMinerRecipe() {
        if (getMachine().getLevel() instanceof ServerLevel && veinMaterials != null) {
            ItemStack stack = new ItemStack(veinMaterials.get(GTUtil.getRandomItem(veinMaterials, veinMaterials.size())).getValue());
            if (!stack.isEmpty()) {
                var recipe = GTRecipeBuilder.ofRaw()
                        .duration((int) (BASE_TIME/(Math.pow(getMachine().getTier(), 2))))
                        .EUt(GTValues.VA[getMachine().getTier()])
                        .outputItems(stack)
                        .buildRawRecipe();
                if (recipe.matchRecipe(getMachine()).isSuccess() && recipe.matchTickRecipe(getMachine()).isSuccess()) {
                    return recipe;
                }
            }
        }
        return null;
    }

}
