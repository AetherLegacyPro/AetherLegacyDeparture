package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockDivinePlanks extends Block {

	public BlockDivinePlanks() {
		super(Material.wood);
		this.setLightLevel(0.3375F);
		this.setHardness(1F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 0);
		this.setBlockTextureName("aether_legacy:divine_oak_planks");
	}
}
