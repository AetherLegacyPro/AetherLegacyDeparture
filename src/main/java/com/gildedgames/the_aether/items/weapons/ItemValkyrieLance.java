package com.gildedgames.the_aether.items.weapons;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemValkyrieLance extends ItemSword {

	public ItemValkyrieLance() {
		super(ToolMaterial.EMERALD);

		this.setCreativeTab(AetherCreativeTabs.weapons);
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.none;
    }

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		return false;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.arkenium_tools.desc"));
    }

}