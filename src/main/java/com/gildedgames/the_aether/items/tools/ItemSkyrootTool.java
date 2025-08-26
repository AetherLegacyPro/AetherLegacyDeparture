package com.gildedgames.the_aether.items.tools;

import com.gildedgames.the_aether.items.util.EnumAetherToolType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;

public class ItemSkyrootTool extends ItemAetherTool {

	public ItemSkyrootTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.STONE, toolType);
		setMaxDamage(72);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Item.getItemFromBlock(BlocksAether.skyroot_planks);
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GRAY + "" + StatCollector.translateToLocal("tooltip.skyroot_tools.desc"));
    }
}