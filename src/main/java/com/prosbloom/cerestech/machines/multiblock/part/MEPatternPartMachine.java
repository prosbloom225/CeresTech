package com.prosbloom.cerestech.machines.multiblock.part;

import appeng.api.networking.*;
import appeng.block.AEBaseEntityBlock;
import appeng.helpers.iface.PatternProviderLogic;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.prosbloom.cerestech.machines.CTMachines;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MEPatternPartMachine extends TieredIOPartMachine {//implements IInWorldGridNodeHost, IGridConnectedBlockEntity {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MEPatternPartMachine.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);


    @Persisted
    public final NotifiableItemStackHandler inventory;

    /*
    public IManagedGridNode mainNode = createMainNode()
            .setFlags(GridFlags.REQUIRE_CHANNEL)
            .setIdlePowerUsage(1)
            .setVisualRepresentation(CTMachines.ME_OUTPUT_BUS[getTier()].getItem())
            .setExposedOnSides(EnumSet.of(this.getFrontFacing()))
            .setInWorldNode(true);

     */

    protected int getInventorySize() {
        int sizeRoot = 1 + Math.min(9, getTier());
        return sizeRoot * sizeRoot;
    }

    public MEPatternPartMachine(IMachineBlockEntity holder, int tier, IO io) {
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
        //mainNode.create(getLevel(), getPos());
        //markForUpdate();
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

    public void markForUpdate() {
            boolean alreadyUpdated = false;
            // Let the block update its own state with our internal state changes
            BlockState currentState = getBlockState();
            if (currentState.getBlock() instanceof AEBaseEntityBlock<?> block) {
                BlockState newState = block.getBlockEntityBlockState(currentState, getLevel().getBlockEntity(getPos()));
                if (currentState != newState) {
                    getLevel().setBlockAndUpdate(getPos(), newState);
                    alreadyUpdated = true;
                }
            }
            if (!alreadyUpdated) {
                getLevel().sendBlockUpdated(getPos(), currentState, currentState, Block.UPDATE_NEIGHBORS);
        }
    }
}