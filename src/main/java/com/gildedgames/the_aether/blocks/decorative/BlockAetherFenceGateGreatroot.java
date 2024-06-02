package com.gildedgames.the_aether.blocks.decorative;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.IIcon;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAetherFenceGateGreatroot extends BlockFenceGate {

	public BlockAetherFenceGateGreatroot() {
		super();

		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
		this.setBlockTextureName(Aether.findII("dark_skyroot_planks/greatroot_planks"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return BlocksAether.greatroot_planks.getBlockTextureFromSide(p_149691_1_);
	}

}
