package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.*;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MEItemPartMachineBlockEntity extends BlockEntity implements IInWorldGridNodeHost, IGridConnectedBlockEntity {

    private final IManagedGridNode mainNode = GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE)
            .setFlags(GridFlags.REQUIRE_CHANNEL)
            .setIdlePowerUsage(1)
            .setVisualRepresentation(Items.SPRUCE_SAPLING)
            .setInWorldNode(true);
    public MEItemPartMachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction direction) {
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
