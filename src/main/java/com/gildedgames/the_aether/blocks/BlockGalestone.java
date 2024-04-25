package com.gildedgames.the_aether.blocks;

import com.gildedgames.the_aether.items.util.DoubleDropHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockGalestone extends Block {

	public BlockGalestone() {
		super(Material.rock);

		this.setHardness(0.5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("aether_legacy:gale_stone");
		setBlockName("Gale Stone");
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		DoubleDropHelper.dropBlock(player, x, y, z, this, meta);
	}

	@Override
	public int damageDropped(int meta) {
		return 1;
	}
}
