package com.gildedgames.the_aether.blocks;

import net.minecraft.client.renderer.texture.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.particle.*;
import net.minecraft.init.*;
import net.minecraftforge.common.util.*;
import java.util.*;

import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import com.gildedgames.the_aether.entities.hostile.EntityCyroGuardian;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;

public class BlockColdFire extends BlockFire
{
    private IIcon[] field_149850_M;
    
    public BlockColdFire() {
        this.setLightLevel(0.20f);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.015625f, 1.0f);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
        return null;
    }
    
    public boolean isCollidable() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister p_149651_1_) {
    	this.field_149850_M = new IIcon[] { p_149651_1_.registerIcon("aether_legacy:coldfire_0"), p_149651_1_.registerIcon("aether_legacy:coldfire_1") };    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int i1, final int i2) {
        return this.field_149850_M[0];
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getFireIcon(final int i1) {
        return this.field_149850_M[i1];
    }
    
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(final World world, final int x, final int y, final int z, final int meta, final EffectRenderer effectRenderer) {
        return true;
    }
    
    private boolean canNeighborBurn(final World world, final int x, final int y, final int z) {
        return Blocks.fire.canCatchFire((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST) || Blocks.fire.canCatchFire((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST) || Blocks.fire.canCatchFire((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP) || Blocks.fire.canCatchFire((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN) || Blocks.fire.canCatchFire((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH) || Blocks.fire.canCatchFire((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH);
    }
    
    private void tryCatchFire(final World world, final int x, final int y, final int z, final int i1, final Random rand, final int i2, final ForgeDirection face) {
        final int j1 = world.getBlock(x, y, z).getFlammability((IBlockAccess)world, x, y, z, face);
        if (rand.nextInt(i1) < j1) {
            final boolean flag = world.getBlock(x, y, z) == Blocks.tnt;
            int k1 = i2 + rand.nextInt(5) / 4;
            if (k1 > 15) {
                k1 = 15;
            }
            world.setBlock(x, y, z, (Block)Blocks.fire, k1, 3);
            if (flag) {
                Blocks.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
            }
        }
    }
    
    public ItemStack getPickBlock(final MovingObjectPosition target, final World world, final int x, final int y, final int z, final EntityPlayer player) {
        return null;
    }
    
    @Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			
			entity.attackEntityFrom(DamageSource.magic, 1.0F);
			((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 0));
			((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 140, 0));
		
		}
		if (entity instanceof EntityCyro) {
			EntityCyro player = (EntityCyro) entity;
			
			((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
		
		}
		if (entity instanceof EntityCyroGuardian) {
			EntityCyroGuardian player = (EntityCyroGuardian) entity;
			
			((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 3));
		
		}
		else  {
			entity.attackEntityFrom(DamageSource.magic, 1.0F);
		}
		
	}
    
    private int getChanceOfNeighborsEncouragingFire(final World world, final int x, final int y, final int z) {
        final byte b0 = 0;
        if (!world.isAirBlock(x, y, z)) {
            return 0;
        }
        int l = b0;
        l = this.getChanceToEncourageFire((IBlockAccess)world, x + 1, y, z, l, ForgeDirection.WEST);
        l = this.getChanceToEncourageFire((IBlockAccess)world, x - 1, y, z, l, ForgeDirection.EAST);
        l = this.getChanceToEncourageFire((IBlockAccess)world, x, y - 1, z, l, ForgeDirection.UP);
        l = this.getChanceToEncourageFire((IBlockAccess)world, x, y + 1, z, l, ForgeDirection.DOWN);
        l = this.getChanceToEncourageFire((IBlockAccess)world, x, y, z - 1, l, ForgeDirection.SOUTH);
        l = this.getChanceToEncourageFire((IBlockAccess)world, x, y, z + 1, l, ForgeDirection.NORTH);
        return l;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_, final int p_149734_4_, final Random p_149734_5_) {
        super.randomDisplayTick(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_, p_149734_5_);
        if (p_149734_5_.nextInt(15) == 0) {
        	
        	float f;
            float f1;
            float f2;
            
        	 for (int i = 0; i < 2; ++i)
        	    {       		 
        		 f = (float)p_149734_2_ + p_149734_5_.nextFloat() * 0.1F;
                 f1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                 f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                 p_149734_1_.spawnParticle("snowshovel", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
             }        	    
        }
    }
    
    public void updateTick(final World world, final int x, final int y, final int z, final Random rand) {
        if (world.getGameRules().getGameRuleBooleanValue("doFireTick")) {
            final boolean flag = world.getBlock(x, y - 1, z).isFireSource(world, x, y - 1, z, ForgeDirection.UP) || world.getBlock(x, y - 1, z) == BlocksAether.coldfire;
            if (!this.canPlaceBlockAt(world, x, y, z)) {
                world.setBlockToAir(x, y, z);
            }
            if (!flag && world.isRaining() && (world.canLightningStrikeAt(x, y, z) || world.canLightningStrikeAt(x - 1, y, z) || world.canLightningStrikeAt(x + 1, y, z) || world.canLightningStrikeAt(x, y, z - 1) || world.canLightningStrikeAt(x, y, z + 1))) {
                world.setBlockToAir(x, y, z);
            }
            else {
                final int l = world.getBlockMetadata(x, y, z);
                if (l < 15) {
                    world.setBlockMetadataWithNotify(x, y, z, l + rand.nextInt(3) / 2, 4);
                }
                world.scheduleBlockUpdate(x, y, z, (Block)this, this.tickRate(world) + rand.nextInt(10));
                if (!flag && !this.canNeighborBurn(world, x, y, z)) {
                    if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)world, x, y - 1, z) || l > 3) {
                        world.setBlockToAir(x, y, z);
                    }
                }
                else {
                    final boolean flag2 = world.isBlockHighHumidity(x, y, z);
                    byte b0 = 0;
                    if (flag2) {
                        b0 = -50;
                    }
                    this.tryCatchFire(world, x + 1, y, z, 300 + b0, rand, l, ForgeDirection.WEST);
                    this.tryCatchFire(world, x - 1, y, z, 300 + b0, rand, l, ForgeDirection.EAST);
                    this.tryCatchFire(world, x, y - 1, z, 250 + b0, rand, l, ForgeDirection.UP);
                    this.tryCatchFire(world, x, y + 1, z, 250 + b0, rand, l, ForgeDirection.DOWN);
                    this.tryCatchFire(world, x, y, z - 1, 300 + b0, rand, l, ForgeDirection.SOUTH);
                    this.tryCatchFire(world, x, y, z + 1, 300 + b0, rand, l, ForgeDirection.NORTH);
                    for (int i1 = x - 1; i1 <= x + 1; ++i1) {
                        for (int j1 = z - 1; j1 <= z + 1; ++j1) {
                            for (int k1 = y - 1; k1 <= y + 4; ++k1) {
                                if (i1 != x || k1 != y || j1 != z) {
                                    int l2 = 100;
                                    if (k1 > y + 1) {
                                        l2 += (k1 - (y + 1)) * 100;
                                    }
                                    final int i2 = this.getChanceOfNeighborsEncouragingFire(world, i1, k1, j1);
                                    if (i2 > 0) {
                                        int j2 = (i2 + 40 + world.difficultySetting.getDifficultyId() * 7) / (l + 30);
                                        if (flag2) {
                                            j2 /= 2;
                                        }
                                        if (j2 > 0 && rand.nextInt(l2) <= j2 && (!world.isRaining() || !world.canLightningStrikeAt(i1, k1, j1)) && !world.canLightningStrikeAt(i1 - 1, k1, z) && !world.canLightningStrikeAt(i1 + 1, k1, j1) && !world.canLightningStrikeAt(i1, k1, j1 - 1) && !world.canLightningStrikeAt(i1, k1, j1 + 1)) {
                                            int k2 = l + rand.nextInt(5) / 4;
                                            if (k2 > 15) {
                                                k2 = 15;
                                            }
                                            world.setBlock(i1, k1, j1, (Block)Blocks.fire, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
