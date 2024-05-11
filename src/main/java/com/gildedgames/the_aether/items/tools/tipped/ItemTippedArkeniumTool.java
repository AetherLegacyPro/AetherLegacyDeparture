package com.gildedgames.the_aether.items.tools.tipped;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemAetherTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTippedArkeniumTool extends ItemAetherTool {

	public float[] level = new float[]{7F, 7F, 7F, 7F, 7F};

	public ItemTippedArkeniumTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.IRON, toolType);
		setMaxDamage(1471);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if (AetherConfig.RepairMaterialTipped() == true) {
			return repair.getItem() == ItemsAether.auralite_crystal;
			}
			else {
			return repair.getItem() == ItemsAether.arkenium_ingot;
		}
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 500)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 501, current, tool.getMaxDamage() - 970)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 971, current, tool.getMaxDamage() - 1471)) {
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

}
