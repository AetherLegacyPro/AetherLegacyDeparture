package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVoidPlanks extends Block {

	public BlockVoidPlanks() {
		super(Material.wood);

		this.setHardness(1F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 0);
		this.setBlockTextureName("aether_legacy:void_planks");
	}
}