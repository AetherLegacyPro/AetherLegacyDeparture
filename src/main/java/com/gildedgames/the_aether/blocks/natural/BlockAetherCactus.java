package com.gildedgames.the_aether.blocks.natural;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import com.gildedgames.the_aether.entities.effects.PotionInebriation;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerwhaleMount;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockAetherCactus extends BlockCactus implements IPlantable {

    @SideOnly(Side.CLIENT)
    private IIcon blockTop;
    @SideOnly(Side.CLIENT)
    private IIcon blockBottom;

    public BlockAetherCactus() {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public void updateTick(World world, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random random) {
        if (world.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_)) {
            int l;

            for (l = 1; world.getBlock(p_149674_2_, p_149674_3_ - l, p_149674_4_) == this; ++l) {
                ;
            }

            if (l < 3) {
                int i1 = world.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

                if (i1 == 15) {
                    world.setBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                    world.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 0, 4);
                    this.onNeighborBlockChange(world, p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                }
                else {
                    world.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, i1 + 1, 4);
                }
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)((float)(p_149668_2_ + 1) - f), (double)((float)(p_149668_3_ + 1) - f), (double)((float)(p_149668_4_ + 1) - f));
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)p_149633_2_ + f), (double)p_149633_3_, (double)((float)p_149633_4_ + f), (double)((float)(p_149633_2_ + 1) - f), (double)(p_149633_3_ + 1), (double)((float)(p_149633_4_ + 1) - f));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.blockTop : (p_149691_1_ == 0 ? this.blockBottom : this.blockIcon);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public int getRenderType()
    {
        return 13;
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.isAirBlock(x, y, z) && this.canBlockStay(world, x, y, z);
    }

    public void onNeighborBlockChange(World world, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block block) {
        if (!this.canBlockStay(world, p_149695_2_, p_149695_3_, p_149695_4_)) {
            world.func_147480_a(p_149695_2_, p_149695_3_, p_149695_4_, true);
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        if (world.getBlock(x - 1, y, z).getMaterial().isSolid()) {
            return false;
        } else if (world.getBlock(x + 1, y, z).getMaterial().isSolid()) {
            return false;
        } else if (world.getBlock(x, y, z - 1).getMaterial().isSolid()) {
            return false;
        } else if (world.getBlock(x, y, z + 1).getMaterial().isSolid()) {
            return false;
        } else {
            Block blockBelow = world.getBlock(x, y - 1, z);

            return blockBelow == this || blockBelow == BlocksAether.quicksoil || blockBelow == BlocksAether.frozen_quicksoil;
        }
    }

    public void onEntityCollidedWithBlock(World world, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
        entity.attackEntityFrom(DamageSource.cactus, 1.0F);

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            player.addPotionEffect(new EffectInebriation(PotionInebriation.inebriation.id, 180, 0));
        }
        if (entity instanceof EntityAerwhale) {
            EntityAerwhale aerwhale = (EntityAerwhale) entity;
            aerwhale.addPotionEffect(new EffectInebriation(Potion.moveSpeed.id, 1200, 2));
        }
        if (entity instanceof EntityAerwhaleMount) {
            EntityAerwhaleMount aerwhale_mount = (EntityAerwhaleMount) entity;
            aerwhale_mount.addPotionEffect(new EffectInebriation(Potion.moveSpeed.id, 1200, 2));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("aether_legacy:aether_cactus_side");
        this.blockTop = iconRegister.registerIcon("aether_legacy:aether_cactus_top");
        this.blockBottom = iconRegister.registerIcon("aether_legacy:aether_cactus_bottom");
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return -1;
    }
}
