package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAceninumShard extends Item {

	public ItemAceninumShard() {
		super();
		
		this.setCreativeTab(AetherCreativeTabs.material);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

}
