package com.gildedgames.the_aether.items.tools;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.block.EntityFloatingBlock;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemContinuumTool extends ItemAetherTool {

	public float[] level = new float[]{5F, 6F, 7F, 8F, 9F};

	public ItemContinuumTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		setMaxDamage(2471);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemsAether.continuum_gemstone;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 500)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 501, current, tool.getMaxDamage() - 800)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 801, current, tool.getMaxDamage() - 1470)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 1471, current, tool.getMaxDamage() - 2471)) {
				return level[1];
			} else {
				return level[0];
			}
		} else {
			return 1.0F;
		}
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
		if (!world.isRemote && world.rand.nextInt(100) <= 3) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.ambrosium_shard, 1));

			world.spawnEntityInWorld(entityItem);
		}

		return super.onBlockDestroyed(stack, world, block, x, y, z, entityLiving);
	}
	
	@Override
	public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		EntityFloatingBlock entity = new EntityFloatingBlock(world, x, y, z, block, meta);

		if ((this.getDigSpeed(heldItem, block, meta) == this.efficiencyOnProperMaterial || ForgeHooks.isToolEffective(heldItem, block, meta)) && world.isAirBlock(x, y + 1, z)) {
			if (world.getTileEntity(x, y, z) != null || world.getBlock(x, y, z).getBlockHardness(world, x, y, z) == -1.0F) {
				return false;
			}

			if (!world.isRemote) {
				world.spawnEntityInWorld(entity);
				world.setBlockToAir(x, y, z);
			}

			heldItem.damageItem(10, player);
		}

		return true;
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GRAY + "" + StatCollector.translateToLocal("tooltip.continuum_tools.desc"));
    }

}