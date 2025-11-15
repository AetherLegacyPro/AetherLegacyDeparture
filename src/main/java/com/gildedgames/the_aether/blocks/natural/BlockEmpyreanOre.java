package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEmpyreanOre extends Block {

	public BlockEmpyreanOre() {
		super(Material.rock);

		this.setHardness(3.2F);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
	}

	@Override
	public void onBlockAdded(World worldIn, int xIn, int yIn, int zIn) {
		for (int x = xIn - 1; x <= (xIn + 1); ++x) {
			for (int y = yIn - 1; y <= (yIn + 1); ++y) {
				for (int z = zIn - 1; z <= (zIn + 1); ++z) {
					Block block = worldIn.getBlock(x, y, z);
					Block block2 = worldIn.getBlock(x, y + 1, z);

					if ((block == BlocksAether.aether_grass || block == BlocksAether.arctic_grass) && (block2 == Blocks.air)) {
						worldIn.setBlock(x, y, z, BlocksAether.enchanted_aether_grass);
					} 
				}
			}
		}
	}
	
	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
		Random random = new Random();

		if (this.getItemDropped(p_149690_5_, random, p_149690_7_) != Item.getItemFromBlock(this)) {
			int amount = 0;

			if (this == BlocksAether.empyrean_ore) {
				amount = MathHelper.getRandomIntegerInRange(random, 2, 3);
			} else if (this == BlocksAether.empyrean_ore_2) {
				amount = MathHelper.getRandomIntegerInRange(random, 3, 5);
			}

			return amount;
		}

		return 0;
	}
	
	protected boolean canSilkHarvest() {
	    return true;
	 }

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ItemsAether.empyrean_gemstone;
    }

}