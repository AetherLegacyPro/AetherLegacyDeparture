package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReinforcedArkenium extends Block {

	public BlockReinforcedArkenium() {
		super(Material.iron);

		this.setHardness(6.2F);
		this.setResistance(35F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

}
