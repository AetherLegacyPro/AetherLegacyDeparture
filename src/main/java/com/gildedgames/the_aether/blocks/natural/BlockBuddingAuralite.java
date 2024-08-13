package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBuddingAuralite extends BlockAuralite {
	
	public BlockBuddingAuralite() {
		setHardness(1.5F);
		setResistance(1.5F);
		this.setStepSound(soundTypeGlass);
		setTickRandomly(true);
	}
	
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(10) == 1) {
			EnumFacing facing = EnumFacing.getFront(rand.nextInt(EnumFacing.values().length));
		Block block = world.getBlock(x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ());
		int meta = world.getBlockMetadata(x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ());
		
			if(block instanceof BlockAuraliteCluster && meta % 6 == facing.ordinal()) {
				if(meta < 6) {
					world.setBlockMetadataWithNotify(x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ(), meta + 6, 3);
				} else if(block == BlocksAether.auralite_cluster_1) {
					world.setBlock(x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ(), BlocksAether.auralite_cluster_2, meta - 6, 3);
				}
			} else if(canGrowIn(block)) {
				world.setBlock(x + facing.getFrontOffsetX(), y + facing.getFrontOffsetY(), z + facing.getFrontOffsetZ(), BlocksAether.auralite_cluster_1, facing.ordinal(), 3);
			}
		}
	}

	private boolean canGrowIn(Block state) {
		return state.getMaterial() == Material.air || state.getMaterial() == Material.water;
	}
	
	protected boolean canSilkHarvest() {
        return false;
    }
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }
	
	@Override
	public int tickRate(World world) {
		return 1000;
	}
}
