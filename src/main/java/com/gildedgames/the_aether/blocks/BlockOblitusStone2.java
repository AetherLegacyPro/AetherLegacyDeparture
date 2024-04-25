package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockOblitusStone2 extends Block {

	public BlockOblitusStone2() {
		super(Material.rock);
		
		this.setHardness(3F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:oblitus_stone");
		setBlockName("Oblitus Stone");
	}

}
