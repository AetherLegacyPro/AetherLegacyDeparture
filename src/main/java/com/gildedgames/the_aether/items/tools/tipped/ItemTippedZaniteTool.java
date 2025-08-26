package com.gildedgames.the_aether.items.tools.tipped;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemAetherTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemTippedZaniteTool extends ItemAetherTool {

	public float[] level = new float[]{3F, 5F, 7F, 9F, 13F};

	public ItemTippedZaniteTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.IRON, toolType);
		setMaxDamage(771);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if (AetherConfig.RepairMaterialTipped() == true) {
			return repair.getItem() == ItemsAether.auralite_crystal;
			}
			else {
			return repair.getItem() == ItemsAether.zanite_gemstone;
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
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 150)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 151, current, tool.getMaxDamage() - 210)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 211, current, tool.getMaxDamage() - 500)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 501, current, tool.getMaxDamage() - 771)) {
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
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.zanite_tools.desc"));
    }

}
