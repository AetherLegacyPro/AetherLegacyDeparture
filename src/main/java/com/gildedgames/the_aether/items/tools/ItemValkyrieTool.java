package com.gildedgames.the_aether.items.tools;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemValkyrieTool extends ItemAetherTool {

	public ItemValkyrieTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemsAether.valkyrie_ingot;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.arkenium_tools.desc"));
    }

}