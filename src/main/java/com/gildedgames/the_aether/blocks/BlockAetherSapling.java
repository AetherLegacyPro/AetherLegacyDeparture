package com.gildedgames.the_aether.blocks;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.gildedgames.the_aether.blocks.natural.BlockAetherFlower;
import com.gildedgames.the_aether.items.ItemsAether;

public class BlockAetherSapling extends BlockAetherFlower implements IGrowable {

	public WorldGenerator treeGenObject = null;

	public BlockAetherSapling(WorldGenerator treeGen) {
		super();

		float f = 0.4F;
		this.treeGenObject = treeGen;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, random);

			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(30) == 0) {
				this.growTree(world, x, y, z, random);
			}
		}
	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		world.setBlock(x, y, z, BlocksAether.skyroot_log, 0, 0);

		if (!this.treeGenObject.generate(world, world.rand, x, y, z)) {
			world.setBlock(x, y, z, this);
		}
	}
	
	public void growTree(World world, int x, int y, int z) {
		world.setBlock(x, y, z, BlocksAether.skyroot_log, 0, 0);

		if (!this.treeGenObject.generate(world, world.rand, x, y, z)) {
			world.setBlock(x, y, z, this);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.inventory.getCurrentItem();
        if(stack == null)
            return true;
        if (stack.getItem() == ItemsAether.swet_ball || stack.getItem() == ItemsAether.golden_swet_ball || stack.getItem() == ItemsAether.purple_swet_ball) {
        	
        	int rand = (int)(1 + Math.random() * 4);
        	if (world.getBlockLightValue(x, y + 1, z) >= 9 && rand == 4) {
        		world.setBlock(x, y, z, BlocksAether.skyroot_log, 0, 0);
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }

				this.growTree(world, x, y, z);
			}
        }
        

        
    	return true;
	}

	@Override
	public boolean func_149851_a(World worldIn, int x, int y, int z, boolean isClient) {
		return true;
	}

	@Override
	public boolean func_149852_a(World worldIn, Random rand, int x, int y, int z) {
		return true;
	}

	@Override
	public void func_149853_b(World worldIn, Random rand, int x, int y, int z) {
		if (worldIn.rand.nextFloat() < 0.45D) {
			this.growTree(worldIn, x, y, z, rand);
		}

	}

}