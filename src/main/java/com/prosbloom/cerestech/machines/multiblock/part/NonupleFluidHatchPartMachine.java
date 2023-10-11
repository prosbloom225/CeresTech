package com.prosbloom.cerestech.machines.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.side.fluid.FluidTransferHelper;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.prosbloom.cerestech.api.machine.trait.NotifiableFluidTankMulti;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;

public class NonupleFluidHatchPartMachine extends FluidHatchPartMachine  {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NonupleFluidHatchPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    // TODO - New Texture
    private final int numTanks = 9;
    @Persisted
    private NotifiableFluidTankMulti tanks;

    public NonupleFluidHatchPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    protected NotifiableFluidTank createTank(Object... args) {
        tanks = new NotifiableFluidTankMulti(this, numTanks, getTankCapacity(), io);
        // dummy tank
        return new NotifiableFluidTank(this, 0, getTankCapacity(), io);
    }

    @Override
    public void updateTankSubscription() {
        if (isWorkingEnabled() && ((io == IO.OUT && !tanks.isEmpty()) || io == IO.IN)
                && FluidTransferHelper.getFluidTransfer(getLevel(), getPos().relative(getFrontFacing()), getFrontFacing().getOpposite()) != null) {
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
    }

    @Override
    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled()) {
                if (io == IO.OUT) {
                    tanks.exportToNearby(getFrontFacing());
                } else if (io == IO.IN){
                    tanks.importFromNearby(getFrontFacing());
                }
            }
            updateTankSubscription();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTankSubscription));
        }
        tankSubs = tanks.addChangedListener(this::updateTankSubscription);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 89, 79);
        group.addWidget(new ImageWidget(4, 4, 81, 70, GuiTextures.DISPLAY))
                .addWidget(new TankWidget(tanks.storages[0], 15, 10, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[1], 35, 10, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[2], 55, 10, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[3], 15, 30, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[4], 35, 30, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[5], 55, 30, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[6], 15, 50, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[7], 35, 50, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[8], 55, 50, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT));
                //.addWidget(new TankWidget(tanks.storages[3], 57, 28, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }


}
