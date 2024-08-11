package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAmbrosium extends Block {

	public BlockAmbrosium() {
		super(Material.rock);

		this.setHardness(0.5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("aether_legacy:ambrosium_block");
		setBlockName("Ambrosium Block");
	}

}
