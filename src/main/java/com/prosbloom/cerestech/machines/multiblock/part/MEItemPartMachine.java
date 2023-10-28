package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.StorageHelper;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MEItemPartMachine extends MEPartMachine{

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEItemPartMachine.class, MEPartMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    public final NotifiableItemStackHandler inventory;

    @Nullable
    protected ISubscription inventorySubs;

    protected int getInventorySize() {
        int sizeRoot = 1 + Math.min(9, getTier());
        return sizeRoot * sizeRoot;
    }

    public MEItemPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        inventory = new NotifiableItemStackHandler(this, getInventorySize(), io);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateInventorySubscription));
        }
        inventorySubs = inventory.addChangedListener(this::updateInventorySubscription);
        aeSubs = subscribeServerTick(aeSubs, this::createMainNode);
    }


    protected void autoIO() {
        if (getOffsetTimer() %5 ==0) {
            if (isWorkingEnabled() && ((io == IO.OUT && !inventory.isEmpty()) || io == IO.IN) && mainNode != null) {
                var grid = getMainNode().getGrid();
                if (grid == null || !getMainNode().isActive())
                    return;
                var storage = grid.getStorageService().getInventory();

                for (int j = 0; j < inventory.getSlots(); j++) {
                    ItemStack is = inventory.storage.getStackInSlot(j);
                    if (is != null && !is.isEmpty()) {
                        boolean didInsert = StorageHelper.poweredInsert(grid.getEnergyService(), storage, AEItemKey.of(is), is.getCount(), IActionSource.ofMachine(mainNode::getNode)) != 0;
                        if (didInsert) {
                            inventory.storage.setStackInSlot(j, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
    }

    protected void updateInventorySubscription() {
        if (isWorkingEnabled() && ((io == IO.OUT && !inventory.isEmpty()) || io == IO.IN) && mainNode != null && mainNode.getGrid() != null){
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
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
                container.addWidget(new SlotWidget(inventory.storage, index++, 4 + x * 18, 4 + y * 18, true, io.support(IO.IN))
                        .setBackgroundTexture(GuiTextures.SLOT));
            }
        }

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }
}