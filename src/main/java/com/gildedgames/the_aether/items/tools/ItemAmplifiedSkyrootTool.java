package com.gildedgames.the_aether.items.tools;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAmplifiedSkyrootTool extends ItemSkyrootTool {

	public float[] level = new float[]{2F, 2F, 2F, 2F, 2F};

	public ItemAmplifiedSkyrootTool(float damage, EnumAetherToolType toolType) {
		super(damage, toolType);
		setMaxDamage(2961);
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
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {		
		
		if (!world.isRemote && world.rand.nextInt(100) <= 90) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.holystone, 1, 1));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100) <= 40) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.agiosite, 1, 1));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100) <= 30) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.aetheral_stone, 1, 1));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100) <= 20) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.deific, 1, 1));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100) <= 10) {
				EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.aether_dirt, 1, 1));

				world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100) <= 10) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.skyroot_log, 1, 1));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(500) <= 1) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.zanite_gemstone));

			world.spawnEntityInWorld(entityItem);
		}
		if (!world.isRemote && world.rand.nextInt(100000) <= 1) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(BlocksAether.primeval_artifact));

			world.spawnEntityInWorld(entityItem);
	}

		return super.onBlockDestroyed(stack, world, block, x, y, z, entityLiving);
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
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }

}