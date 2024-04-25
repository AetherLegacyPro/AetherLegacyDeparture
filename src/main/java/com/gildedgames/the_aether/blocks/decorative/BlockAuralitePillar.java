package com.gildedgames.the_aether.blocks.decorative;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
