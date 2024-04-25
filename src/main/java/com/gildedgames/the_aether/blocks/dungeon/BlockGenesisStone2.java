package com.gildedgames.the_aether.blocks.dungeon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockGenesisStone2 extends Block {

	public BlockGenesisStone2() {
		super(Material.rock);

		this.setHardness(6.5F);
		this.setResistance(25.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockTextureName("aether_legacy:genesis_stone");
		setBlockName("Genesis Stone");
	}

}
