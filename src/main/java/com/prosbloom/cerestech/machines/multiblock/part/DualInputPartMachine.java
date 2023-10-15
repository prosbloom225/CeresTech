package com.prosbloom.cerestech.machines.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
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

public class DualInputPartMachine extends TieredIOPartMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(DualInputPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    private final int numTanks = 4;
    private final int tankCapacity = 16000;
    private final int inventorySize = 8;
    @Persisted
    private NotifiableFluidTankMulti tanks;

    @Persisted
    public final NotifiableItemStackHandler inventory;
    private ISubscription tankSubs;
    private ISubscription inventorySubs;
    protected TickableSubscription autoIOSubs;


    public DualInputPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io);
        tanks = createTank();
        inventory = new NotifiableItemStackHandler(this, inventorySize, io);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    protected NotifiableFluidTankMulti createTank() {
        return new NotifiableFluidTankMulti(this, numTanks, tankCapacity, io);
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
        var group = new WidgetGroup(0, 0, 110, 79);
        group.addWidget(new ImageWidget(4, 4, 101, 70, GuiTextures.DISPLAY))
                .addWidget(new TankWidget(tanks.storages[0], 15, 10, true, io.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[1], 35, 10, true, io.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[2], 55, 10, true, io.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[3], 75, 10, true, io.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT));
        int rowSize = 4;
        int colSize = 2;
        int index = 0;
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                group.addWidget(new SlotWidget(inventory.storage, index++, 18 + x * 18, 32 + y * 18, true, io.support(IO.IN))
                        .setBackgroundTexture(GuiTextures.SLOT));
            }
        }
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

}
