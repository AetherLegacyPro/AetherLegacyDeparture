package com.gildedgames.the_aether.blocks.decorative;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;

public class BlockAetherLadder extends BlockLadder {

	public BlockAetherLadder() {
		super();
		setHardness(0.4F);
		setStepSound(soundTypeLadder);
	}

}
