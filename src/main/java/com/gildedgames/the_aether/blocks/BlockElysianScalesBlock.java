package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElysianScalesBlock extends Block {

	@SideOnly(Side.CLIENT)
    private IIcon blockTop;
    @SideOnly(Side.CLIENT)
    private IIcon blockBottom;
	
	public BlockElysianScalesBlock() {
		super(Material.iron);

		this.setHardness(15.0F);
		this.setResistance(5000F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.blockTop : (p_149691_1_ == 0 ? this.blockBottom : this.blockIcon);
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
	    this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
	    this.blockTop = p_149651_1_.registerIcon(this.getTextureName() + "_top");
	    this.blockBottom = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
	}


}
