package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockContinuum extends Block {

	public BlockContinuum() {
		super(Material.iron);

		this.setHardness(1.2F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockTextureName("aether_legacy:continuum_block");
		setBlockName("Continuum Block");
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

}
