package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.V;

public class PowerStationMachine extends MultiblockControllerMachine implements IFancyUIMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(PowerStationMachine.class, MultiblockControllerMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    @DescSynced
    private NotifiableEnergyContainer energyContainer;
    List<IEnergyContainer> dynamos = new ArrayList<>();
    List<IEnergyContainer> hatches = new ArrayList<>();
    private int tier = EV;

    private Queue<Integer> avgIn = new SizeLimitedQueue<>(100);
    private Queue<Integer> avgOut = new SizeLimitedQueue<>(100);

    protected TickableSubscription energyOutputSubs;
    protected TickableSubscription energyInputSubs;

    public PowerStationMachine(IMachineBlockEntity holder) {
        super(holder);
        energyContainer = NotifiableEnergyContainer.emitterContainer(this, V[tier]* 100000, tier, 64);
        dynamos = new ArrayList<>();
        hatches = new ArrayList<>();
    }

    @Override
    public ManagedFieldHolder getFieldHolder() { return MANAGED_FIELD_HOLDER; }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        dynamos = new ArrayList<>();
        hatches = new ArrayList<>();
        for (IMultiPart part : getParts())
            for (var handler : part.getRecipeHandlers()) {
                if (handler.getHandlerIO() == IO.OUT && handler.getCapability() == EURecipeCapability.CAP) {
                    dynamos.add((IEnergyContainer) handler);
                } else if (handler.getHandlerIO() == IO.IN && handler.getCapability() == EURecipeCapability.CAP) {
                    hatches.add((IEnergyContainer) handler);
                }
            }
        energyOutputSubs = subscribeServerTick(this::outputEnergy);
        energyInputSubs = subscribeServerTick(this::inputEnergy);
        outputEnergy();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        dynamos = new ArrayList<>();
        hatches = new ArrayList<>();
        unsubscribe(energyInputSubs);
        unsubscribe(energyOutputSubs);

    }

    private void inputEnergy() {
        int curr = 0;
        if (energyContainer.getEnergyCanBeInserted() > V[tier])
            if (hatches.stream().filter(h->h.getEnergyStored() >= V[tier]).findAny().orElse(null) != null) {
                List<IEnergyContainer> hatchesToPull = hatches.stream().filter(h -> h.getEnergyStored() >= V[tier]).toList();
                for (IEnergyContainer hatch : hatchesToPull) {
                    while (energyContainer.getEnergyCanBeInserted() >= V[tier] && hatch.getEnergyStored() >= V[tier]) {
                        hatch.removeEnergy(V[tier]);
                        energyContainer.addEnergy(V[tier]);
                        curr+=V[tier];
                    }
                }
            }
        avgIn.add(curr);

    }
    private void outputEnergy() {
        int curr = 0;
        if (energyContainer.getEnergyStored() > V[tier])
            if (dynamos.stream().filter(h->h.getEnergyCanBeInserted() >= V[tier]).findAny().orElse(null) != null){
                List<IEnergyContainer> dynamosToFill = dynamos.stream().filter(h->h.getEnergyCanBeInserted() >= V[tier]).toList();
                for (IEnergyContainer dynamo : dynamosToFill) {
                    while (energyContainer.getEnergyStored() > V[tier] && dynamo.getEnergyCanBeInserted() >= V[tier]) {
                        energyContainer.changeEnergy(-V[tier]);
                        dynamo.addEnergy(V[tier]);
                        curr+=V[tier];
                    }
                }
            }
        avgOut.add(curr);
    }


    protected static EditableUI<ProgressWidget, PowerStationMachine> createEnergyBar() {
        return new EditableUI<>("energy_container", ProgressWidget.class, () -> {
            var progressBar = new ProgressWidget(ProgressWidget.JEIProgress, 0, 15, 18, 60,
                    new ProgressTexture(IGuiTexture.EMPTY, GuiTextures.ENERGY_BAR_BASE));
            progressBar.setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP);
            progressBar.setBackground(GuiTextures.ENERGY_BAR_BACKGROUND);
            return progressBar;
        }, (progressBar, machine) -> {
            progressBar.setProgressSupplier(() -> machine.energyContainer.getEnergyStored() * 1d / machine.energyContainer.getEnergyCapacity());
        });
    }

    @Override
    public Widget createUIWidget() {
        var editableUI = createEnergyBar();
        var energyBar = editableUI.createDefault();

        var group = new WidgetGroup(0, 0, 180 + 8, 80 + 8);
        var container = new WidgetGroup(22, 4, 150, 80);
        container.addWidget(new DraggableScrollableWidgetGroup(4, 4, 142, 72).setBackground(GuiTextures.DISPLAY)
                        .addWidget(new LabelWidget(4, 4, self().getBlockState().getBlock().getDescriptionId()))
                        .addWidget(new LabelWidget(4, 14, () -> String.format("Max Energy:     %s", energyContainer.getEnergyCapacity())))
                        .addWidget(new LabelWidget(4, 24, () -> String.format("Energy Stored: %s\nAvg Out: %s\nAvg In:  %s",
                                energyContainer.getEnergyStored(),
                                Math.round(avgOut.stream().mapToDouble(i->i).average().orElse(0)),
                                Math.round(avgIn.stream().mapToDouble(i->i).average().orElse(0))))));
        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(energyBar);
        group.addWidget(container);

        editableUI.setupUI(group, this);
        return group;
    }

    public static class SizeLimitedQueue<E> extends ArrayDeque<E> {
        private int maxSize;
        public SizeLimitedQueue(int maxSize) {
            this.maxSize = maxSize;
        }
        @Override
        public boolean add(E e) {
            while (this.size() == maxSize)
                super.remove();
            super.add(e);
            return true;
        }
    }
}
