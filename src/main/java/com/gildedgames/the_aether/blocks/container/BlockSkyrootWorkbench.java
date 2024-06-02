package com.gildedgames.the_aether.blocks.container;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.*;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;

public class BlockSkyrootWorkbench extends BlockWorkbench
{
    @SideOnly(Side.CLIENT)
    private IIcon field_94385_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_94384_b;
    
    public BlockSkyrootWorkbench() {
		super();

		this.setHardness(1.5F);
		this.setResistance(3.0F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", 0);
	}
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.field_94385_a : ((par1 == 0) ? BlocksAether.skyroot_planks.getBlockTextureFromSide(par1) : ((par1 != 2 && par1 != 4) ? this.blockIcon : this.field_94384_b));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("aether:crafting_tables/skyroot_crafting_table_side");
        this.field_94385_a = par1IIconRegister.registerIcon("aether:crafting_tables/skyroot_crafting_table_top");
        this.field_94384_b = par1IIconRegister.registerIcon("aether:crafting_tables/skyroot_crafting_table_front");
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        if (world.isRemote) {
            return true;
        }
        final int guiID = 8;
        player.openGui((Object)Aether.instance, guiID, world, x, y, z);
        return true;
    }
}
