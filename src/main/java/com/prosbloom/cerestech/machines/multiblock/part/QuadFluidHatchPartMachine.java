package com.prosbloom.cerestech.machines.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.prosbloom.cerestech.api.machine.trait.NotifiableFluidTankMulti;

public class QuadFluidHatchPartMachine extends FluidHatchPartMachine  {
    // TODO - autoIO
    // TODO - New Texture
    private final int numTanks = 4;
    private NotifiableFluidTankMulti tanks;

    public QuadFluidHatchPartMachine(IMachineBlockEntity holder, int tier, IO io, Object... args) {
        super(holder, tier, io, args);
    }

    @Override
    protected NotifiableFluidTank createTank(Object... args) {
        tanks = new NotifiableFluidTankMulti(this, numTanks, getTankCapacity(), io);
        // dummy tank
        return new NotifiableFluidTank(this, 0, getTankCapacity(), io);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 89, 63);
        group.addWidget(new ImageWidget(4, 4, 81, 55, GuiTextures.DISPLAY))
                .addWidget(new TankWidget(tanks.storages[0], 37, 12, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[1], 37, 28, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[2], 57, 12, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT))
                .addWidget(new TankWidget(tanks.storages[3], 57, 28, true, io.support(IO.IN)) .setBackground(GuiTextures.FLUID_SLOT));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }


}
