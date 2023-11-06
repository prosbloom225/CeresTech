package com.prosbloom.cerestech.api.machine.trait;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.BedrockOreVeinSavedData;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.OreVeinWorldEntry;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.prosbloom.cerestech.machines.multiblock.VoidMinerMachine;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static java.util.Map.entry;

public class VoidMinerLogic extends RecipeLogic {

    private final static int MAX_PROGRESS = 20;
    @Nullable
    private static List<Map.Entry<Integer, Material>> veinMaterials = List.of(
            entry(6, Bauxite),
            entry(2, Ilmenite),
            entry(1, Aluminium),
            entry(8, Magnetite),
            entry(4, VanadiumMagnetite),
            entry(2, Chromite),
            entry(3, Gold),
            entry(3, Naquadah),
            entry(1, Plutonium239),
            entry(3, Pitchblende),
            entry(2, Uraninite),
            entry(3, Scheelite),
            entry(2, Tungstate),
            entry(1, Lithium),
            entry(4, Bornite),
            entry(2, Cooperite),
            entry(2, Platinum),
            entry(1, Palladium),
            entry(3, Goethite),
            entry(4, YellowLimonite),
            entry(4, Hematite),
            entry(3, Beryllium),
            entry(2, Emerald),
            entry(4, Quartzite),
            entry(2, CertusQuartz),
            entry(1, Barite),
            entry(6, Grossular),
            entry(4, Pyrolusite),
            entry(2, Tantalite),
            entry(3, Wulfenite),
            entry(2, Molybdenite),
            entry(2, Molybdenum),
            entry(1, Powellite),
            entry(3, Bastnasite),
            entry(3, NetherQuartz),
            entry(6, Redstone),
            entry(4, Ruby),
            entry(2, Cinnabar),
            entry(3, Saltpeter),
            entry(3, Diatomite),
            entry(2, Electrotine),
            entry(1, Alunite),
            entry(3, Sulfur),
            entry(4, Pyrite),
            entry(1, Sphalerite),
            entry(3, Tetrahedrite),
            entry(4, Copper),
            entry(1, Stibnite),
            entry(3, BlueTopaz),
            entry(2, Topaz),
            entry(7, Chalcocite),
            entry(3, Apatite),
            entry(2, TricalciumPhosphate),
            entry(1, Pyrochlore),
            entry(4, Tin),
            entry(4, Cassiterite),
            entry(7, Coal),
            entry(5, Chalcopyrite),
            entry(2, Zeolite),
            entry(1, Realgar),
            entry(3, Galena),
            entry(2, Silver),
            entry(1, Lead),
            entry(3, CassiteriteSand),
            entry(2, GarnetSand),
            entry(2, Asbestos),
            entry(3, GarnetRed),
            entry(2, GarnetYellow),
            entry(2, Amethyst),
            entry(1, Opal),
            entry(5, Goethite),
            entry(1, Malachite),
            entry(3, Soapstone),
            entry(2, Talc),
            entry(3, GlauconiteSand),
            entry(2, Pentlandite),
            entry(3, BasalticMineralSand),
            entry(2, GraniticMineralSand),
            entry(2, FullersEarth),
            entry(1, Gypsum),
            entry(3, Garnierite),
            entry(2, Nickel),
            entry(3, RockSalt),
            entry(2, Salt),
            entry(1, Lepidolite),
            entry(1, Spodumene),
            entry(2, Iron),
            entry(3, Graphite),
            entry(2, Diamond),
            entry(3, Lazurite),
            entry(2, Sodalite),
            entry(2, Lapis),
            entry(1, Calcite),
            entry(2, Spessartine),
            entry(3, Kyanite),
            entry(2, Mica),
            entry(1, Pollucite),
            entry(3, Bentonite),
            entry(2, Olivine),
            entry(3, Almandine),
            entry(2, Pyrope),
            entry(1, Sapphire),
            entry(1, GreenSapphire)
    );

    public VoidMinerLogic(IRecipeLogicMachine machine) {
        super(machine);
    }

    @Override
    public VoidMinerMachine getMachine() {
        return (VoidMinerMachine) super.getMachine();
    }


    @Override
    public void findAndHandleRecipe() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel) {
            lastRecipe = null;
            var data = BedrockOreVeinSavedData.getOrCreate(serverLevel);
            if (veinMaterials == null) {
                this.veinMaterials = data.getOreInChunk(getChunkX(), getChunkZ());
                if (this.veinMaterials == null) {
                    if (subscription != null) {
                        subscription.unsubscribe();
                        subscription = null;
                    }
                    return;
                }
            }
            var match = getOreMinerRecipe();
            if (match != null) {
                var copied = match.copy(new ContentModifier(match.duration, 0));
                if (match.matchRecipe(this.machine).isSuccess() && copied.matchTickRecipe(this.machine).isSuccess()) {
                    setupRecipe(match);
                }
            }
        }
    }

    @Nullable
    private GTRecipe getOreMinerRecipe() {
        if (getMachine().getLevel() instanceof ServerLevel serverLevel && veinMaterials != null) {
            var data = BedrockOreVeinSavedData.getOrCreate(serverLevel);
            Material material = veinMaterials.get(GTUtil.getRandomItem(veinMaterials, veinMaterials.size())).getValue();
            ItemStack stack = ChemicalHelper.get(TagPrefix.get(ConfigHolder.INSTANCE.machines.bedrockOreDropTagPrefix), material, getOreToProduce(data.getOreVeinWorldEntry(getChunkX(), getChunkZ())));
            if (stack.isEmpty()) stack = ChemicalHelper.get(TagPrefix.crushed, material, getOreToProduce(data.getOreVeinWorldEntry(getChunkX(), getChunkZ()))); // backup 1: crushed; if raw ore doesn't exist
            if (stack.isEmpty()) stack = ChemicalHelper.get(TagPrefix.gem, material, getOreToProduce(data.getOreVeinWorldEntry(getChunkX(), getChunkZ()))); // backup 2: gem; if crushed ore doesn't exist
            if (stack.isEmpty()) stack = ChemicalHelper.get(TagPrefix.ore, material, getOreToProduce(data.getOreVeinWorldEntry(getChunkX(), getChunkZ()))); // backup 3: just fallback to normal ore...
            var recipe = GTRecipeBuilder.ofRaw()
                    .duration(MAX_PROGRESS)
                    .EUt(GTValues.VA[getMachine().getTier()])
                    .outputItems(stack)
                    .buildRawRecipe();
            if (recipe.matchRecipe(getMachine()).isSuccess() && recipe.matchTickRecipe(getMachine()).isSuccess()) {
                return recipe;
            }
        }
        return null;
    }

    private int getOreToProduce(OreVeinWorldEntry entry) {
        return getMachine().getTier();
    }


    @Override
    public void onRecipeFinish() {
        machine.afterWorking();
        if (lastRecipe != null) {
            lastRecipe.postWorking(this.machine);
            lastRecipe.handleRecipeIO(IO.OUT, this.machine);
        }
        // try it again
        var match = getOreMinerRecipe();
        if (match != null) {
            var copied = match.copy(new ContentModifier(match.duration, 0));
            if (match.matchRecipe(this.machine).isSuccess() && copied.matchTickRecipe(this.machine).isSuccess()) {
                setupRecipe(match);
                return;
            }
        }
        setStatus(Status.IDLE);
        progress = 0;
        duration = 0;
    }


    private int getChunkX() {
        return SectionPos.blockToSectionCoord(getMachine().getPos().getX());
    }

    private int getChunkZ() {
        return SectionPos.blockToSectionCoord(getMachine().getPos().getZ());
    }

}
