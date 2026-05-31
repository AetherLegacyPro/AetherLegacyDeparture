package com.gildedgames.the_aether.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

public class ItemZarnillysScales extends Item {

    public ItemZarnillysScales() {
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ItemsAether.scaled_aether_loot;
    }

}
