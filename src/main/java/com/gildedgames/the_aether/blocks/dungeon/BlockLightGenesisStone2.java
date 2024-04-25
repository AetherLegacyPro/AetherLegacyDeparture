package com.gildedgames.the_aether.blocks.dungeon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLightGenesisStone2 extends Block {

	public BlockLightGenesisStone2() {
		super(Material.rock);

		this.setHardness(3.5F);
		this.setLightLevel(0.8375F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockTextureName("aether_legacy:light_genesis_stone");
		setBlockName("Light Genesis Stone");
	}

}
