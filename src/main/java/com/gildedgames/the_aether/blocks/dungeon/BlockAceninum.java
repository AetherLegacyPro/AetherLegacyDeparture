package com.gildedgames.the_aether.blocks.dungeon;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockAceninum extends Block {
	
	public BlockAceninum() {
		this(Material.glass);
	}
	public BlockAceninum(Material material) {
		super(material);
		setHardness(12.5F);
		setResistance(30F);
		setHarvestLevel("pickaxe", 3);
		this.setStepSound(soundTypeGlass);
	}
	
	public int quantityDropped(Random p_149745_1_)
	{
	  return 2 + p_149745_1_.nextInt(2);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
	  return ItemsAether.aceninum_shard;
	}

}
