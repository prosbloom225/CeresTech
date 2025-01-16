package com.prosbloom.cerestech.machines.multiblock;

import appeng.api.config.Actionable;
import appeng.api.networking.IGrid;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.MEStorage;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.integration.ae2.machine.MEInputBusPartMachine;
import com.gregtechceu.gtceu.integration.ae2.machine.MEStockingBusPartMachine;
import com.gregtechceu.gtceu.integration.ae2.slot.ExportOnlyAEItemSlot;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.syncdata.managed.IManagedVar;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2LongMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class MESortedStockingBusPartMachine extends MEStockingBusPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            MESortedStockingBusPartMachine.class, MEInputBusPartMachine.MANAGED_FIELD_HOLDER);
    @DescSynced
    @Persisted
    private boolean autoPull;

    private Predicate<GenericStack> autoPullTest;
    public MESortedStockingBusPartMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }
    @Override
    public void autoIO() {
        super.autoIO();
        if (isAutoPull() && getOffsetTimer() % 100 == 0) {
            refreshList();
            syncME();
        }
    }

    private void refreshList() {
        IGrid grid = this.getMainNode().getGrid();
        if (grid == null) {
            aeItemHandler.clearInventory(0);
            return;
        }
        MEStorage networkStorage = grid.getStorageService().getInventory();
        var counter = networkStorage.getAvailableStacks();
        Map<AEKey, Long> sorted = new HashMap<>();
        for (Object2LongMap.Entry<AEKey> entry : counter)
            sorted.put(entry.getKey(), entry.getLongValue());
        sorted = sorted.entrySet().stream()
                .sorted(Map.Entry.<AEKey, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int index = 0;
        for (AEKey what : sorted.keySet()) {
            if (index >= CONFIG_SIZE) break;
            long amount = sorted.get(what);

            if (amount <= 0) continue;
            if (!(what instanceof AEItemKey itemKey)) continue;

            long request = networkStorage.extract(what, amount, Actionable.SIMULATE, actionSource);
            if (request == 0) continue;

            // Ensure that it is valid to configure with this stack
            if (autoPullTest != null && !autoPullTest.test(new GenericStack(itemKey, amount))) continue;

            var slot = this.aeItemHandler.getInventory()[index];
            slot.setConfig(new GenericStack(what, 1));
            slot.setStock(new GenericStack(what, request));
            index++;
        }


        aeItemHandler.clearInventory(index);
        Comparator<ExportOnlyAEItemSlot> comparator = Comparator.comparing(h->h.getStackInSlot(0).getCount());
        List<ExportOnlyAEItemSlot> l = Arrays.stream(aeItemHandler.getInventory()).sorted(comparator).toList();
        for (int i =0;i < l.size();i++) {
            aeItemHandler.getInventory();
        }

        for (ExportOnlyAEItemSlot slot : aeItemHandler.getInventory()) {
            int cnt = slot.getStackInSlot(0).getCount();
        }

    }
}
