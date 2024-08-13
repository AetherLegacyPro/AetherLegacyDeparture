package com.gildedgames.the_aether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCrackedOblitusStone2 extends Block {

	
	public BlockCrackedOblitusStone2() {
		super(Material.rock);

		this.setHardness(1F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:cracked_oblitus_stone");
		setBlockName("Cyro Stone");
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.cracked_oblitus_stone_2);
	}

}
