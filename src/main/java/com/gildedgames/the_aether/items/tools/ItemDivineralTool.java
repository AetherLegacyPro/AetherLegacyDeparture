package com.gildedgames.the_aether.items.tools;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.entities.block.EntityFloatingBlock;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemDivineralTool extends ItemAetherTool {

	public float[] level = new float[]{9F, 9F, 9F, 9F, 9F};

	public ItemDivineralTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		this.setCreativeTab(AetherCreativeTabs.tools);
		setMaxDamage(5791);
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
	
	@Override
	public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		EntityFloatingBlock entity = new EntityFloatingBlock(world, x, y, z, block, meta);
		player.triggerAchievement(AchievementsAether.strengthened_levitation);

		if ((this.getDigSpeed(heldItem, block, meta) == this.efficiencyOnProperMaterial || ForgeHooks.isToolEffective(heldItem, block, meta)) && world.isAirBlock(x, y + 1, z)) {
			if (world.getTileEntity(x, y, z) != null || world.getBlock(x, y, z).getBlockHardness(world, x, y, z) == -1.0F) {
				return false;
			}

			if (!world.isRemote) {
				world.spawnEntityInWorld(entity);
				world.setBlockToAir(x, y, z);
			}

			heldItem.damageItem(1, player);
		}

		return true;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "" + StatCollector.translateToLocal("tooltip.divineral_tools.desc"));
    }

}