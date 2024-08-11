package com.gildedgames.the_aether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockLightGenesisStone extends Block {

	public BlockLightGenesisStone() {
		super(Material.rock);

		this.setHardness(3.5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(null);
		this.setBlockTextureName("aether_legacy:light_genesis_stone");
		setBlockName("Light Genesis Stone");
	}
	
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.light_genesis_stone_2);
	}

}

