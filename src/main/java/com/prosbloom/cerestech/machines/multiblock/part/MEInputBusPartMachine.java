package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.StorageHelper;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.IRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MEInputBusPartMachine extends MEItemPartMachine implements IDistinctPart {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEInputBusPartMachine.class, MEItemPartMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    public final NotifiableItemStackHandler meInventory;

    @Override
    public List<MachineTrait> getTraits() {
        return super.getTraits();
    }

    @Override
    public List<IRecipeHandlerTrait> getRecipeHandlers() {
        // ignore meInventory for recipes
        // TODO - doesnt work at all
        return traits.stream().filter(IRecipeHandlerTrait.class::isInstance)
                .filter(t->t.equals(inventory))
                .map(IRecipeHandlerTrait.class::cast).toList();
    }

    public MEInputBusPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        meInventory = new NotifiableItemStackHandler(this, getInventorySize(), io);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() { return MANAGED_FIELD_HOLDER; }

    @Override
    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled() && ((io == IO.OUT && !inventory.isEmpty()) || io == IO.IN) && mainNode != null) {
                var grid = getMainNode().getGrid();
                if (grid == null || !getMainNode().isActive())
                    return;
                var storage = grid.getStorageService().getInventory();

                for (int j = 0; j < inventory.getSlots(); j++) {
                    ItemStack is = inventory.storage.getStackInSlot(j).copy();
                    if (is.getItem() != meInventory.storage.getStackInSlot(j).getItem() && !meInventory.storage.getStackInSlot(j).isEmpty()) {
                        StorageHelper.poweredInsert(grid.getEnergyService(), storage, AEItemKey.of(meInventory.storage.getStackInSlot(j).getItem()), meInventory.storage.getStackInSlot(j).getCount(), IActionSource.ofMachine(mainNode::getNode));
                        meInventory.storage.setStackInSlot(j, ItemStack.EMPTY);
                    }
                    if (is != null && !is.isEmpty()) {
                        int sizeDiff = is.getCount() - meInventory.storage.getStackInSlot(j).getCount();
                        if (sizeDiff > 0) {
                            boolean didExtract = StorageHelper.poweredExtraction(grid.getEnergyService(), storage, AEItemKey.of(is), Math.abs(sizeDiff), IActionSource.ofMachine(mainNode::getNode)) != 0;
                            if (didExtract) {
                                meInventory.storage.setStackInSlot(j, is);
                            }
                        } else {
                            StorageHelper.poweredInsert(grid.getEnergyService(), storage, AEItemKey.of(meInventory.storage.getStackInSlot(j).getItem()), Math.abs(sizeDiff), IActionSource.ofMachine(mainNode::getNode));
                            meInventory.storage.setStackInSlot(j, is);
                        }

                    }
                }
            }
        }
    }


    @Override
    public Widget createUIWidget() {
        int rowSize = (int) Math.sqrt(getInventorySize());
        int colSize = rowSize;
        if (getInventorySize() == 8) {
            rowSize = 4;
            colSize = 2;
        }
        var group = new WidgetGroup(0, 0, 18 * rowSize*2 + 20, 18 * colSize + 16);
        var container = new WidgetGroup(4, 4, 18 * rowSize*2 + 12, 18 * colSize + 8);
        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                // inv
                container.addWidget(new SlotWidget(inventory.storage, index, 4 + x * 18, 4 + y * 18, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT));
                // me mirror
                container.addWidget(new SlotWidget(meInventory.storage, index, (rowSize * 18) + 8 + x * 18, 4 + y * 18, false, false)
                        .setBackgroundTexture(GuiTextures.SLOT_DARKENED));
                index++;
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }

    @Override
    public boolean isDistinct() {
        return inventory.isDistinct();
    }

    @Override
    public void setDistinct(boolean isDistinct) {
        inventory.setDistinct(isDistinct);

    }
}
