package com.gildedgames.the_aether.items.tools.tipped;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemTippedSkyrootTool extends ItemSkyrootTool {

	public float[] level = new float[]{2F, 2F, 2F, 2F, 2F};

	public ItemTippedSkyrootTool(float damage, EnumAetherToolType toolType) {
		super(damage, toolType);
		setMaxDamage(561);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if (AetherConfig.RepairMaterialTipped() == true) {
			return repair.getItem() == ItemsAether.auralite_crystal;
			}
			else {
			return repair.getItem() == Item.getItemFromBlock(BlocksAether.skyroot_planks);
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 999)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 1000, current, tool.getMaxDamage() - 1260)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 1261, current, tool.getMaxDamage() - 1561)) {
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
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.skyroot_tools.desc"));
    }

}
