package com.gildedgames.the_aether.items.tools;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemAmplifiedZaniteTool extends ItemAetherTool {

	public float[] level = new float[]{6F, 8F, 12F, 14F, 16F};

	public ItemAmplifiedZaniteTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		setMaxDamage(3561);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemsAether.divineral_ingot;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 250)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 251, current, tool.getMaxDamage() - 1499)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 1500, current, tool.getMaxDamage() - 2260)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 2261, current, tool.getMaxDamage() - 3561)) {
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
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "" + StatCollector.translateToLocal("tooltip.amplified_zanite_tools.desc"));
    }

}
