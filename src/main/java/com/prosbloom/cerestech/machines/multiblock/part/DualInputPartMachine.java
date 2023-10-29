package com.prosbloom.cerestech.machines.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDistinctPart;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.side.fluid.FluidTransferHelper;
import com.lowdragmc.lowdraglib.side.item.ItemTransferHelper;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.prosbloom.cerestech.api.machine.trait.NotifiableFluidTankMulti;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;

public class DualInputPartMachine extends TieredIOPartMachine implements IDistinctPart {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(DualInputPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    private final int numTanks = 4;
    private final int tankCapacity = 16000;
    @Persisted
    private NotifiableFluidTankMulti tanks;

    @Persisted
    public final NotifiableItemStackHandler inventory;
    private ISubscription tankSubs;
    private ISubscription inventorySubs;
    protected TickableSubscription autoIOSubs;


    public DualInputPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io);
        tanks = new NotifiableFluidTankMulti(this, getTankSize(), 16000L * tier, io);
        inventory = new NotifiableItemStackHandler(this, getInventorySize(), io);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    protected int getInventorySize() {
        return 4 * (getTier() - 3);
    }
    protected int getTankSize() {
        return 4 * (getTier() - 4);
    }

    public void updateSubscriptions() {
        if (isWorkingEnabled() && (io == IO.IN
                && (FluidTransferHelper.getFluidTransfer(getLevel(), getPos().relative(getFrontFacing()), getFrontFacing().getOpposite()) != null
                || ItemTransferHelper.getItemTransfer(getLevel(), getPos().relative(getFrontFacing()), getFrontFacing().getOpposite()) != null))) {
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        super.setWorkingEnabled(workingEnabled);
        updateSubscriptions();
    }

    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled()) {
                if (io == IO.OUT) {
                    tanks.exportToNearby(getFrontFacing());
                    inventory.exportToNearby(getFrontFacing());
                } else if (io == IO.IN){
                    tanks.importFromNearby(getFrontFacing());
                    inventory.importFromNearby(getFrontFacing());
                }
            }
            updateSubscriptions();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateSubscriptions));
        }
        tankSubs = tanks.addChangedListener(this::updateSubscriptions);
    }

    @Override
    public Widget createUIWidget() {
        int rows = getTankSize()/4;
        var group = new WidgetGroup(0, 0, 110, 65 + (32 * rows +1));
        group.addWidget(new ImageWidget(4, 4, 101, 56 + (32 * rows+1), GuiTextures.DISPLAY));
        int index = 0;
        for (int i = 0; i < rows; i++)
            for (int x = 0; x < 4; x++)
                group.addWidget(new TankWidget(tanks.storages[index++], 15 + 20 * x, (i + 1) * 18, true, io.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT));
        index = 0;
        for (int y = 0; y < rows+1; y++)
            for (int x = 0; x < 4; x++)
                group.addWidget(new SlotWidget(inventory.storage, index++, 18 + x * 18, 18 * rows + 24 + y * 18, true, io.support(IO.IN))
                        .setBackgroundTexture(GuiTextures.SLOT));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
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
