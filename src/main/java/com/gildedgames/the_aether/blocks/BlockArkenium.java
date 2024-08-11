package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockArkenium extends Block {

	public BlockArkenium() {
		super(Material.iron);

		this.setHardness(0.5F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:arkenium_block");
		setBlockName("Arkenium Block");
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

}
