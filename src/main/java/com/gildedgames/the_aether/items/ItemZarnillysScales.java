package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemZarnillysScales extends Item {

	public ItemZarnillysScales() {
        this.setCreativeTab(AetherCreativeTabs.material);
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}
	
}

