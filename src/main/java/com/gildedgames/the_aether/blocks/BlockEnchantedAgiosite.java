package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEnchantedAgiosite extends Block {

	public BlockEnchantedAgiosite() {
		super(Material.rock);

		this.setHardness(4.0F);
		this.setResistance(6.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("aether_legacy:enchanted_agiosite");
		setBlockName("Enchanted Agiosite");
	}

}
