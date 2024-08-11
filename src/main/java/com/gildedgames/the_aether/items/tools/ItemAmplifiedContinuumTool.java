package com.gildedgames.the_aether.items.tools;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.entities.block.EntityFloatingBlock;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemAmplifiedContinuumTool extends ItemAetherTool {

	public float[] level = new float[]{6F, 7F, 8F, 9F, 10F};

	public ItemAmplifiedContinuumTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.EMERALD, toolType);
		setMaxDamage(5471);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemsAether.divineral_ingot;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return this.calculateIncrease(stack, this.toolType.getStrVsBlock(stack, block));
	}

	private float calculateIncrease(ItemStack tool, float original) {
		boolean AllowedCalculations = original != 4.0F ? false : true;
		int current = tool.getItemDamage();

		if (AllowedCalculations) {
			if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 1000)) {
				return level[4];
			} else if (isBetween(tool.getMaxDamage() - 1001, current, tool.getMaxDamage() - 1800)) {
				return level[3];
			} else if (isBetween(tool.getMaxDamage() - 1801, current, tool.getMaxDamage() - 3470)) {
				return level[2];
			} else if (isBetween(tool.getMaxDamage() - 3471, current, tool.getMaxDamage() - 5471)) {
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
		if (!world.isRemote && world.rand.nextInt(100) <= 5) {
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

			heldItem.damageItem(8, player);
		}

		return true;
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }

}
