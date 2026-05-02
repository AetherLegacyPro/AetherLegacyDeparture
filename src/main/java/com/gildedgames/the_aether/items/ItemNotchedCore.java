package com.gildedgames.the_aether.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

public class ItemNotchedCore extends Item {

    public ItemNotchedCore() {
        super();

        this.setMaxStackSize(1);
        this.setCreativeTab(AetherCreativeTabs.misc);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ItemsAether.divine_aether_loot;
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return true;
    }

}
