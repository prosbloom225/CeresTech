package com.prosbloom.cerestech.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class CTLangHandler {
    public static void init(RegistrateLangProvider provider) {
        // Prefixes
        provider.add("tagprefix.depleted_fuel", "Depleted %s Fuel");
        provider.add("tagprefix.depleted_fuel_oxide", "%s Depleted Fuel Oxide");
        provider.add("tagprefix.depleted_fuel_nitride", "%s Depleted Fuel Nitride");
        provider.add("tagprefix.waste", "%s Waste");
        provider.add("tagprefix.fuel_pure", "%s Fuel Pure");
        provider.add("tagprefix.fuel_oxide", "%s Fuel Oxide");
        provider.add("tagprefix.dust_oxide", "%s Oxide");
        provider.add("tagprefix.dust_nitrite", "%s Nitrite");
        provider.add("tagprefix.dust_dioxide", "%s Dioxide");
        provider.add("tagprefix.hexachloride", "%s Hexachloride");
        provider.add("tagprefix.hexafluoride", "%s Hexafluoride");

        // Machine Text
        provider.add("gtceu.multiblock.nuclear_reactor.fuel", "Fuel: %s");
        provider.add("gtceu.multiblock.nuclear_reactor.coolant_output", "Coolant Output: %d");
        provider.add("cerestech.machine.volcanus.tooltip", "Requires up to 8 Programmable Circuits");
        provider.add("cerestech.machine.cryogenic_freezer.tooltip", "Requires up to 8 Programmable Circuits");
        provider.add("cerestech.machine.cryogenic_freezer.tooltip.1", "Requires 20mb/s of Gelid Cryotheum");
        provider.add("cerestech.machine.void_miner.tooltip", "Requires 20mb/s of Drilling Fluid");
        provider.add("cerestech.machine.naquadah_reactor.tooltip", "Requires Enriched Naquadah Rods");

        // Recipe Types
        // TODO - these recipes should probably register with CT_REGISTRY, not the gtceu one
        provider.add("gtceu.naquadah_reactor", "Naquadah Reactor");
        provider.add("gtceu.dehydrator", "Dehydrator");
        provider.add("gtceu.decay_chamber", "Decay Chamber");
        provider.add("gtceu.nuclear_reactor", "Nuclear Reactor");
        provider.add("gtceu.gas_centrifuge", "Gas Centrifuge");
        provider.add("gtceu.industrial_greenhouse", "Industrial Greenhouse");
        provider.add("gtceu.hot_coolant_turbine", "Hot Coolant Turbine");
        provider.add("gtceu.heat_exchanger", "Heat Exchanger");
        provider.add("gtceu.coke_oven", "Coke Oven");
        provider.add("gtceu.pcb_factory", "PCB Factory");
        provider.add("gtceu.bacterial_vat", "Bacterial Vat");
        provider.add("gtceu.plasma_condenser", "Bacterial Vat");
        provider.add("gtceu.neutron_activator", "Neutron Activator");
        provider.add("gtceu.stellar_forge", "Stellar Forge");
        provider.add("gtceu.quarry", "Stone Quarry");
        provider.add("gtceu.bio_reactor", "Bio-Reactor");
        provider.add("gtceu.matter_fabricator", "Matter Fabricator");
        provider.add("gtceu.matter_amplifier", "Matter Amplifier");
        provider.add("gtceu.mass_fabricator", "Mass Fabricator");
    }
}
