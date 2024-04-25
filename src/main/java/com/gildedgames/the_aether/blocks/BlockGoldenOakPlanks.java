package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.gildedgames.the_aether.Aether;

public class BlockGoldenOakPlanks extends Block {

	public BlockGoldenOakPlanks() {
		super(Material.wood);

		this.setHardness(3F);
		this.setResistance(6F);
		this.setStepSound(soundTypeWood);
		this.setBlockTextureName(Aether.find("golden_oak_planks"));
	}

}
