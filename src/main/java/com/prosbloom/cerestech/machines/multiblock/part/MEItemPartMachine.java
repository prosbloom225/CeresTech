package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.*;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.StorageHelper;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MEItemPartMachine extends ItemBusPartMachine implements IInWorldGridNodeHost, IGridConnectedBlockEntity {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEItemPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    private TickableSubscription aeSubs;

    private IManagedGridNode mainNode;

    //private final IActionSource actionSource = IActionSource.ofMachine(mainNode::getNode);

    public MEItemPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
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
    public void onLoad() {
        super.onLoad();
        aeSubs = subscribeServerTick(aeSubs, this::createMainNode);
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


    @Override
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
    public void onUnload() {
        // need to unload node when block is destroyed
        super.onUnload();
        if (mainNode != null)
            mainNode.destroy();
    }

    @Override
    public IManagedGridNode getMainNode() {
        return mainNode;
    }

    @Override
    public void securityBreak() {

    }

    @Override
    public void saveChanges() {
    }


    @Nullable
    @Override
    public IGridNode getActionableNode() {
        return mainNode.getNode();
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction direction) {
        return getActionableNode();
    }
}