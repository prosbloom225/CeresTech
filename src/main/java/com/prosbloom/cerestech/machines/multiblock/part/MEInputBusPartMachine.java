package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.StorageHelper;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import net.minecraft.world.item.ItemStack;

public class MEInputBusPartMachine extends MEItemPartMachine implements IDistinctPart {

    @Persisted
    public final NotifiableItemStackHandler meInventory;
    public MEInputBusPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        meInventory = new NotifiableItemStackHandler(this, getInventorySize(), io);
    }

    @Override
    protected void autoIO() {
        if (getOffsetTimer() % 100 == 0) {
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
                        is.setCount(is.getCount() - meInventory.storage.getStackInSlot(j).getCount());
                        if (is.getCount() > 0) {
                            boolean didExtract = StorageHelper.poweredExtraction(grid.getEnergyService(), storage, AEItemKey.of(is), is.getCount(), IActionSource.ofMachine(mainNode::getNode)) != 0;
                            if (didExtract) {
                                meInventory.storage.setStackInSlot(j, is);
                            }
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
        var group = new WidgetGroup(0, 0, 18 * rowSize + 16, 18 * colSize + 16);
        var container = new WidgetGroup(4, 4, 18 * rowSize + 8, 18 * colSize + 8);
        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                container.addWidget(new SlotWidget(inventory.storage, index++, 4 + x * 18, 4 + y * 18, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT));
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
