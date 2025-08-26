package com.gildedgames.the_aether.items.weapons;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemAetherMultiTool;
import com.gildedgames.the_aether.items.util.EnumAetherMultiToolType;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBattleSentryHammer extends ItemAetherMultiTool {

	public float[] level = new float[]{10F, 10F, 10F, 10F, 10F};

	public ItemBattleSentryHammer(float damage, EnumAetherMultiToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		this.setCreativeTab(AetherCreativeTabs.tools);
		setMaxDamage(1691);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 590)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 591, current, tool.getMaxDamage() - 1790)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 1791, current, tool.getMaxDamage() - 3790)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 3791, current, tool.getMaxDamage() - 5791)) {
				return level[1];
			} else {
				return level[0];
			}
		} else {
			return 1.0F;
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.battle_sentry_hammer.desc"));
    }

}