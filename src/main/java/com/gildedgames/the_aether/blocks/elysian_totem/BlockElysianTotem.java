package com.gildedgames.the_aether.blocks.elysian_totem;

import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.ancient.enchanter.BlockMultiTileEntity;
import com.gildedgames.the_aether.blocks.ancient.enchanter.TileEntityMultiBlock;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;

import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.texture.*;

public class BlockElysianTotem extends BlockMultiTileEntity
{
    private Random rand;
    private IIcon sideIcon;
    
    public BlockElysianTotem() {
        super(Material.rock);
        this.useDefaultCollision = true;
        this.rand = new Random();
        this.setHardness(-1.0f);
        this.setResistance(1000000.0f);
        this.setBlockSize(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
    }
    
    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer entityplayer, final int par6, final float par7, final float par8, final float par9) {
    	
    	return false;
    }
    
    public IIcon getIcon(final int i, final int meta) {
        return this.sideIcon;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return BlocksAether.ElysianTotemRenderId;
    }
    
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        this.sideIcon = par1IIconRegister.registerIcon("aether_legacy:genesis_stone");
    }
    
    @Override
    public TileEntityMultiBlock createMultiTileEntity(final World world) {
        try {
            return new TileEntityElysianTotem();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {		
		if (!p_149734_1_.isRemote) {
			return;
		}

		if (net.minecraft.client.Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
			return;
		}
		
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (double)((float)p_149734_2_ + 0.5F + (p_149734_5_.nextFloat()) - (p_149734_5_.nextFloat()));
        double d1 = (double)((float)p_149734_3_ - 1.0F);
        double d2 = (double)((float)p_149734_4_ + 0.5F + (p_149734_5_.nextFloat()) - (p_149734_5_.nextFloat()));
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 2)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 3)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 4)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
    }
}
