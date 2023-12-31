package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.*;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEFluidKey;
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
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.side.fluid.FluidTransferHelper;
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
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MEPartMachine extends TieredIOPartMachine implements IInWorldGridNodeHost, IGridConnectedBlockEntity  {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    protected IManagedGridNode mainNode;
    protected TickableSubscription aeSubs;
    protected TickableSubscription autoIOSubs;

    public MEPartMachine(IMachineBlockEntity holder, int tier, IO io) {
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
    protected InteractionResult onWrenchClick(Player playerIn, InteractionHand hand, Direction gridSide, BlockHitResult hitResult) {
        // TODO - node scanning cant be triggered by remote entities... GridHelper:68 checks the blockEntity for IInWorldGridNodeHost, but we are implementing in the child.. i have no idea how to implement in the super blockEntity without implementing the whole BlockEntity->This stack
        // luckily we can trigger the scan from this side by creating the node.
        // for now the node will connect on block place if an adjacent block has a node, or onWrenchClick()
        InteractionResult ir = super.onWrenchClick(playerIn, hand, gridSide, hitResult);
        createMainNode();
        //getLevel().sendBlockUpdated(getPos(), getBlockState(), getBlockState(), Block.UPDATE_NEIGHBORS);
        return ir;
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction direction) {
        if (mainNode != null)
            return getActionableNode();
        return null;
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
}
