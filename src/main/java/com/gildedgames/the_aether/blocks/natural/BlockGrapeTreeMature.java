package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrapeTreeMature extends BlockAetherFlower {

	public BlockGrapeTreeMature() {
		this.setHardness(0.2F);
		this.setHarvestLevel("axe", 0);
		this.setStepSound(soundTypeGrass);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.9F, 1.0F, 0.9F);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return true;
    }


	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
		int min, max;

		if (world.getBlock(x, y, z) == BlocksAether.enchanted_aether_grass) {
			min = 2;
			max = 3;
		}
		if (world.getBlock(x, y, z) == BlocksAether.aether_farmland) {
			min = 2;
			max = 3;
		}
		if (world.getBlock(x, y, z) == BlocksAether.enchanted_aether_farmland) {
			min = 4;
			max = 5;
		}
		else {
			min = 1;
			max = 2;
		}

		int randomNum = world.rand.nextInt(max - min + 1) + min;
		entityplayer.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
		entityplayer.addExhaustion(0.025F);

		world.setBlock(x, y, z, BlocksAether.grape_tree_stage_one);

		if (randomNum != 0) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(ItemsAether.grapes, randomNum, 0));
		}
	}

	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			int min, max;

			if (world.getBlock(x, y, z) == BlocksAether.enchanted_aether_grass) {
				min = 1;
				max = 4;
			} else {
				min = 1;
				max = 3;
			}

			int randomNum = world.rand.nextInt(max - min + 1) + min;
			this.dropBlockAsItem(world, x, y, z, new ItemStack(ItemsAether.grapes, randomNum, 0));
			world.setBlock(x, y, z, BlocksAether.grape_tree_stage_one);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
		return true;
	}


}
