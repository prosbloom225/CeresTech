package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.*;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.StorageHelper;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.side.fluid.FluidTransferHelper;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.prosbloom.cerestech.api.machine.trait.NotifiableFluidTankMulti;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.core.Direction;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MEFluidHatchPartMachine extends MEPartMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEFluidHatchPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    // TODO - New Texture
    private final int numTanks = 9;
    @Persisted
    private NotifiableFluidTankMulti tanks;

    protected ISubscription tankSubs;

    private static final long INITIAL_TANK_CAPACITY = 8 * FluidHelper.getBucket();

    protected long getTankCapacity() {
        return INITIAL_TANK_CAPACITY * (1L << Math.min(9, getTier()));
    }
    public MEFluidHatchPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        this.tanks = createTank();
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public void createMainNode() {
        // need to delay node creation till chunk is fully loaded
        if (mainNode != null)
            mainNode.destroy();
        if (getLevel() != null && !this.getLevel().isClientSide && getPos() != null && getLevel().isLoaded(getPos())) {
            mainNode = GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE)
                    .setFlags(GridFlags.REQUIRE_CHANNEL)
                    .setIdlePowerUsage(1)
                    .setVisualRepresentation(CTMachines.ME_OUTPUT_BUS[getTier()].getItem())
                    .setExposedOnSides(EnumSet.of(this.getFrontFacing()))
                    .setInWorldNode(true);
            mainNode.create(getLevel(), getPos());
            unsubscribe(aeSubs);
        }
    }

    @Override
    protected InteractionResult onWrenchClick(Player playerIn, InteractionHand hand, Direction gridSide, BlockHitResult hitResult) {
        // TODO - node scanning cant be triggered by remote entities... GridHelper:68 checks the blockEntity for IInWorldGridNodeHost, but we are implementing in the child.. i have no idea how to implement in the super blockEntity without implementing the whole BlockEntity->This stack
        // luckily we can trigger the scan from this side by creating the node.
        // for now the node will connect on block place if an adjacent block has a node, or onWrenchClick()
        InteractionResult ir = super.onWrenchClick(playerIn, hand, gridSide, hitResult);
        createMainNode();
        //getLevel().sendBlockUpdated(getPos(), getBlockState(), getBlockState(), Block.UPDATE_NEIGHBORS);
        return ir;
    }

    protected NotifiableFluidTankMulti createTank() {
        return new NotifiableFluidTankMulti(this, numTanks, getTankCapacity(), io);
    }

    public void updateTankSubscription() {
        if (isWorkingEnabled() && ((io == IO.OUT && !tanks.isEmpty()) || io == IO.IN)){
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
    }

    protected void autoIO() {
        if (getOffsetTimer() %5 ==0) {
            if (isWorkingEnabled() && ((io == IO.OUT && !tanks.isEmpty()) || io == IO.IN) && mainNode != null) {
                var grid = getMainNode().getGrid();
                if (grid == null || !getMainNode().isActive())
                    return;
                var storage = grid.getStorageService().getInventory();

                for (int j = 0; j < tanks.getTanks(); j++) {
                    FluidStack fs = tanks.getFluidInTank(j);
                    if (fs != null && !fs.isEmpty()) {
                        boolean didInsert = StorageHelper.poweredInsert(grid.getEnergyService(), storage, AEFluidKey.of(fs.getFluid()), fs.getAmount(), IActionSource.ofMachine(mainNode::getNode)) != 0;
                        if (didInsert) {
                            tanks.drain(j, fs, false, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(0, this::updateTankSubscription));
        }
        tankSubs = tanks.addChangedListener(this::updateTankSubscription);
        aeSubs = subscribeServerTick(aeSubs, this::createMainNode);
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
