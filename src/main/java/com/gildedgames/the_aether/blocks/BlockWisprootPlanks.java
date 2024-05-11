package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockWisprootPlanks extends Block {

	public BlockWisprootPlanks() {
		super(Material.wood);

		this.setHardness(1F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 0);
		this.setBlockTextureName("aether_legacy:wisproot_planks");
	}

}