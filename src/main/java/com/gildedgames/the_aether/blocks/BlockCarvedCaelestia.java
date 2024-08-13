package com.gildedgames.the_aether.blocks;

import com.gildedgames.the_aether.Aether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockCarvedCaelestia extends Block {
	
	public BlockCarvedCaelestia() {
		super(Material.rock);

		this.setHardness(0.5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		setBlockName("Carved Caelestia");
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	private IIcon blockIconBottom;
	private IIcon blockIconTop;
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.blockIcon = registry.registerIcon(Aether.find("carved_caelestia_stone_side"));
		this.blockIconTop = registry.registerIcon(Aether.find("carved_caelestia_stone_top"));
		this.blockIconBottom = registry.registerIcon(Aether.find("carved_caelestia_stone_top"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.blockIconTop : (side == 0 ? this.blockIconBottom : this.blockIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side == 1) {
			return this.blockIconTop;
		} else if (side == 0) {
			return this.blockIconBottom;
		}

		return this.blockIcon;
	}
	
}
