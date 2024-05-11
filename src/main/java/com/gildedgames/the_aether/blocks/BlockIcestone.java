package com.gildedgames.the_aether.blocks;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockIcestone extends Block {

	public BlockIcestone() {
		super(Material.ice);

		this.setHardness(1.2F);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeGlass);
		this.setHarvestLevel("pickaxe", 1);
		this.setBlockTextureName("aether_legacy:icestone");
	}

	@Override
	public void onBlockAdded(World worldIn, int xIn, int yIn, int zIn) {
		for (int x = xIn - 3; x <= (xIn + 3); ++x) {
			for (int y = yIn - 3; y <= (yIn + 3); ++y) {
				for (int z = zIn - 3; z <= (zIn + 3); ++z) {
					Block block = worldIn.getBlock(x, y, z);

					if (block == Blocks.water || block == Blocks.flowing_water) {
						worldIn.setBlock(x, y, z, Blocks.ice);
					} else if (block == Blocks.lava || block == Blocks.flowing_lava) {
						worldIn.setBlock(x, y, z, Blocks.obsidian);
					}
				}
			}
		}
	}
	
	protected boolean canSilkHarvest() {
	    return true;
	 }
	
	/**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return MathHelper.clamp_int(this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 2 + p_149745_1_.nextInt(3);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ItemsAether.icestone_crystal;
    }

}