package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLuminousStone extends Block {

	public BlockLuminousStone() {
		super(Material.glass);

		this.setHardness(1F);
		this.setStepSound(soundTypeGlass);
		this.setLightLevel(1.0F);
		this.setHarvestLevel("pickaxe", 0);
	}
	
}
