package com.gildedgames.the_aether.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAuralitePillar extends Block {
	
	public BlockAuralitePillar() {
		this(Material.glass);
	}
	public BlockAuralitePillar(Material material) {
		super(material);
		setHardness(1.5F);
		setResistance(1.5F);
		this.setStepSound(soundTypeGlass);
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
