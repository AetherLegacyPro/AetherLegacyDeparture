package com.gildedgames.the_aether.blocks.decorative;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockAetherTrapdoor extends BlockTrapDoor {

	public BlockAetherTrapdoor(Material material) {
		super(Material.wood);
		this.setLightOpacity(0);
	}

}
