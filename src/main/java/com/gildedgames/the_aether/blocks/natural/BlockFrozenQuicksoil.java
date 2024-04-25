package com.gildedgames.the_aether.blocks.natural;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.util.DoubleDropHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockFrozenQuicksoil extends Block {

	public BlockFrozenQuicksoil() {
		super(Material.sand);

		this.slipperiness = 1.23F;

		this.setHardness(1.2F);
		this.setStepSound(soundTypeSand);
		this.setHarvestLevel("shovel", 0);
		this.setBlockTextureName("aether_legacy:frozen_quicksoil");
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		DoubleDropHelper.dropBlock(player, x, y, z, BlocksAether.frozen_quicksoil, 1);
	}

	@Override
	public int damageDropped(int meta) {
		return 1;
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }

}