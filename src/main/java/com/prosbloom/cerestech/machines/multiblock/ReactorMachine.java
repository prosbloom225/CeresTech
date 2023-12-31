package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.forge.SizedIngredientImpl;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.LargeBoilerMachine;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.prosbloom.cerestech.data.CTFluids;
import com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.prosbloom.cerestech.data.recipes.NuclearReactorRecipes.reactorFuels;

public class ReactorMachine extends LargeBoilerMachine {
    private String fuel;
    private int maxDrain;
    private int coolantScaler = 30;
    public ReactorMachine(IMachineBlockEntity holder, int maxTemperature, int heatSpeed, Object... args) {
        super(holder, maxTemperature, heatSpeed, args);
    }

    public void generateCoolant() {
        if (recipeLogic.isWorking()) {
            if (getOffsetTimer() % 10 == 0) {
                // Determine the fuel ingredient
                if (recipeLogic.getLastRecipe() != null) {
                    fuel = ((SizedIngredientImpl) recipeLogic.getLastRecipe().getInputContents(ItemRecipeCapability.CAP).get(0).content).getInner().getItems()[0].getItem().toString();
                    // find the circuit value
                    ItemStack circuit = (recipeLogic.getLastRecipe().getInputContents(ItemRecipeCapability.CAP))
                            .stream()
                            .filter(i->(IntCircuitBehaviour.isIntegratedCircuit(((SizedIngredientImpl)i.content).getItems()[0])))
                            .map(i->((SizedIngredientImpl) i.content).getItems()[0])
                            .findAny().orElse(null);
                    if (circuit == null)
                        return;
                    int count = circuit.getTag().getInt("Configuration");
                    if (count >10)
                        count=count-10;

                    NuclearReactorRecipes.ReactorFuel rf = reactorFuels.stream()
                            .filter(f -> fuel.equals(f.isotopeFuelOxide.getName() + "_fuel_oxide"))
                            .findAny().orElse(null);
                    if (rf != null) {
                        maxDrain = coolantScaler * rf.getHeat() * count;
                    }
                    // TODO - the fuel pures are just waste, not really adding anything to coolant output

                    var drainCoolant = List.of(FluidIngredient.of(maxDrain, CTFluids.Coolant.getFluid()));
                    List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
                    if (getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP)) {
                        inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP)));
                    }
                    if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                        inputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
                    }
                    for (IRecipeHandler<?> tank : inputTanks) {
                        drainCoolant = (List<FluidIngredient>) tank.handleRecipe(IO.IN, null, drainCoolant, null, false);
                        if (drainCoolant == null) break;
                    }
                    var drained = (drainCoolant == null || drainCoolant.isEmpty()) ? maxDrain : maxDrain - drainCoolant.get(0).getAmount();

                    boolean hasDrainedCoolant = drained > 0;

                    if (hasDrainedCoolant) {
                        // fill coolant
                        var fillHotCoolant = List.of(FluidIngredient.of(CTFluids.CoolantHot.getFluid(drained)));
                        List<IRecipeHandler<?>> outputTanks = new ArrayList<>();
                        if (getCapabilitiesProxy().contains(IO.OUT, FluidRecipeCapability.CAP)) {
                            outputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.OUT, FluidRecipeCapability.CAP)));
                        }
                        if (getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                            outputTanks.addAll(Objects.requireNonNull(getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
                        }
                        for (IRecipeHandler<?> tank : outputTanks) {
                            fillHotCoolant = (List<FluidIngredient>) tank.handleRecipe(IO.OUT, null, fillHotCoolant, null, false);
                            if (fillHotCoolant == null) break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void addDisplayText(List<Component> textList) {
        if (!recipeLogic.isWorking()) {
            fuel = "";
            maxDrain = 0;
        }
        if (isFormed()) {
            textList.add(Component.translatable("gtceu.multiblock.nuclear_reactor.fuel", fuel));
            textList.add(Component.translatable("gtceu.multiblock.nuclear_reactor.coolant_output", maxDrain));

        }
    }

    @Override
    public void onWorking() {
        generateCoolant();
    }

    @Override
    public IGuiTexture getScreenTexture() {
        if (!recipeLogic.isWorking()) {
            fuel = "";
            maxDrain = 0;
        }
        return GuiTextures.DISPLAY_STEAM.get(recipeLogic.isWorking());
    }
}
