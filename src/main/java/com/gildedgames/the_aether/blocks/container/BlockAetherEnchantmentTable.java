package com.gildedgames.the_aether.blocks.container;

import net.minecraft.block.*;
import net.minecraft.util.*;

import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.tileentity.TileEntityAetherEnchantmentTable;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class BlockAetherEnchantmentTable extends BlockEnchantmentTable
{
    
	@SideOnly(Side.CLIENT)
    private IIcon field_94385_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_94384_b;
    
    public BlockAetherEnchantmentTable() {
		super();

		this.setHardness(5.5F);
		this.setResistance(8.0F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 1);
	}
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        return (par1 == 1) ? this.field_94385_a : ((par1 == 0) ? BlocksAether.aerogel.getBlockTextureFromSide(par1): ((par1 != 2 && par1 != 4) ? this.blockIcon : this.field_94384_b));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("aether_legacy:aether_enchanting_table_side");
        this.field_94385_a = par1IIconRegister.registerIcon("aether_legacy:aether_enchanting_table_top");
        this.field_94384_b = par1IIconRegister.registerIcon("aether_legacy:aether_enchanting_table_side");
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        super.randomDisplayTick(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_, p_149734_5_);

        for (int l = p_149734_2_ - 2; l <= p_149734_2_ + 2; ++l)
        {
            for (int i1 = p_149734_4_ - 2; i1 <= p_149734_4_ + 2; ++i1)
            {
                if (l > p_149734_2_ - 2 && l < p_149734_2_ + 2 && i1 == p_149734_4_ - 1)
                {
                    i1 = p_149734_4_ + 2;
                }

                if (p_149734_5_.nextInt(16) == 0)
                {
                    for (int j1 = p_149734_3_; j1 <= p_149734_3_ + 1; ++j1)
                    {
                        if (p_149734_1_.getBlock(l, j1, i1) == BlocksAether.skyroot_bookshelf || p_149734_1_.getBlock(l, j1, i1) == BlocksAether.golden_oak_bookshelf)
                        {
                            if (!p_149734_1_.isAirBlock((l - p_149734_2_) / 2 + p_149734_2_, j1, (i1 - p_149734_4_) / 2 + p_149734_4_))
                            {
                                break;
                            }

                            p_149734_1_.spawnParticle("enchantmenttable", (double)p_149734_2_ + 0.5D, (double)p_149734_3_ + 2.0D, (double)p_149734_4_ + 0.5D, (double)((float)(l - p_149734_2_) + p_149734_5_.nextFloat()) - 0.5D, (double)((float)(j1 - p_149734_3_) - p_149734_5_.nextFloat() - 1.0F), (double)((float)(i1 - p_149734_4_) + p_149734_5_.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        if (world.isRemote) {
            return true;
        }
        final int guiID = 9;
        player.openGui((Object)Aether.instance, guiID, world, x, y, z);
        return true;
    }
    
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityAetherEnchantmentTable();
    }
    
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        super.onBlockPlacedBy(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityAetherEnchantmentTable)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145920_a(p_149689_6_.getDisplayName());
        }
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}
}
