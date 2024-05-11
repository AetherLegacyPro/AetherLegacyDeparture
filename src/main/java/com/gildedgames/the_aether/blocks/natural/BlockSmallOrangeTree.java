package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.CommonProxy;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSmallOrangeTree extends BlockAetherFlower implements IGrowable {

	public BlockSmallOrangeTree() {
		this.setHardness(0.2F);
		this.setStepSound(soundTypeGrass);
		this.setCreativeTab(null);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.8F, 0.35F, 0.8F);
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote) {
			return;
		}

		super.updateTick(world, x, y, z, random);

		if (world.getBlockLightValue(x, y + 1, z) >= 8 && random.nextInt(80) == 0) {
			world.setBlock(x, y, z, BlocksAether.medium_orange_tree);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
		return true;
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean isClient) {
		return true;
	}

	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z) {
		return (double) random.nextFloat() < 0.45D;
	}

	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z) {
		world.setBlock(x, y, z, BlocksAether.medium_orange_tree);
	}

}