package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOblitusStone extends Block {

	public BlockOblitusStone() {
		super(Material.rock);
		
		this.slipperiness = 1.008F;

		this.setHardness(3F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:oblitus_stone");
		setBlockName("Oblitus Stone");
	}

}
