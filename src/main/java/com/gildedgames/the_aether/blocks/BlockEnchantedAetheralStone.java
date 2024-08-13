package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEnchantedAetheralStone extends Block {

	public BlockEnchantedAetheralStone() {
		super(Material.rock);

		this.setHardness(3F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("aether_legacy:enchanted_aetheral_stone");
		setBlockName("Enchanted Aetheral Stone");
	}

}
