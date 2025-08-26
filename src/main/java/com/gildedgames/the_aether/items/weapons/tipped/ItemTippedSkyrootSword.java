package com.gildedgames.the_aether.items.weapons.tipped;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

public class ItemTippedSkyrootSword extends ItemSword {

	public ItemTippedSkyrootSword() {
		super(ToolMaterial.IRON);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		if (AetherConfig.RepairMaterialTipped() == true) {
			return material.getItem() == ItemsAether.auralite_crystal;
		}
		else {
			return material.getItem() == Item.getItemFromBlock(BlocksAether.golden_oak_log) || material.getItem() == Item.getItemFromBlock(BlocksAether.skyroot_log) || material.getItem() == Item.getItemFromBlock(BlocksAether.skyroot_planks);
		}
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.skyroot_sword.desc"));
    }

}
