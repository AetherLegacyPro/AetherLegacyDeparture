package com.gildedgames.the_aether.blocks.container;

import com.gildedgames.the_aether.items.util.DoubleDropHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockProtector extends Block {

	public BlockProtector() {
		super(Material.glass);

		this.setHardness(0.5F);
		this.setStepSound(soundTypeGlass);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("aether_legacy:protector_block");
	}

	@Override
	public int damageDropped(int meta) {
		return 1;
	}
}
