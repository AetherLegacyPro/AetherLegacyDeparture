package com.gildedgames.the_aether.blocks;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;

import com.gildedgames.the_aether.Aether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAerScalesBlock extends BlockBreakable {

	public BlockAerScalesBlock() {
		super(Aether.find("cer_scales_block"), Material.rock, false);

		this.setHardness(15.0F);
		this.setResistance(5000F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
	}


}