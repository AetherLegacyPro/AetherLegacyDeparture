package com.gildedgames.the_aether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gildedgames.the_aether.items.block.IColoredBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVioletAercloud extends Block implements IColoredBlock {

    public BlockVioletAercloud() {
        super(Material.ice);

        this.setHardness(0.2F);
        this.setStepSound(soundTypeCloth);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return 0xCCFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);

        return this.getRenderColor(meta);
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        return 0xCCFFFF;
    }

    private IIcon backTexture;
    private IIcon frontTexture;
    private IIcon leftArrow;
    private IIcon rightArrow;
    private IIcon upArrow;
    private IIcon downArrow;

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!(entity instanceof EntityPlayer) || !((EntityPlayer) entity).capabilities.isFlying) {
            entity.fallDistance = 0.0F;
            int meta = world.getBlockMetadata(x, y, z);
            if (!entity.isRiding()) {
                if (entity instanceof EntityPlayer player) {

                    if (player.isSneaking()) {
                        if (entity.motionZ < 0) {
                            entity.motionZ *= 0.005D;
                        }

                        return;
                    }

                } else {
                    if (entity instanceof EntityArrow) {
                        if (entity.ticksExisted >= 1200) {
                            entity.setDead();
                        }
                    }

                }

                if (meta >= 0 && entity.posY <= (double) y + 1.6 && entity.posY >= (double) y - 0.2) {
                    entity.motionY = 0.1;
                }

                switch (meta) {
                    case 3, 7:
                        entity.motionX = (double) -2.5F;
                        break;
                    case 2, 6:
                        entity.motionZ = (double) 2.5F;
                        break;
                    case 1, 5:
                        entity.motionX = (double) 2.5F;
                        break;
                    case 0, 4:
                        entity.motionZ = (double) -2.5F;
                        break;
                }

                if (!(entity instanceof EntityPlayer)) {
                    entity.fallDistance = -20.0F;
                }
            }

        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        int facing = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + (double) 0.5F) & 3;
        switch (facing) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 4, 16);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 16);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 6, 16);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 7, 16);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (meta) {
            case 3, 7:
                switch (side) {
                    case 0, 1, 3:
                        return this.leftArrow;
                    case 2:
                        return this.rightArrow;
                    case 4:
                        return this.frontTexture;
                    case 5:
                        return this.backTexture;
                }
            case 2, 6:
                switch (side) {
                    case 0, 1:
                        return this.downArrow;
                    case 2:
                        return this.backTexture;
                    case 3:
                        return this.frontTexture;
                    case 4:
                        return this.rightArrow;
                    case 5:
                        return this.leftArrow;
                }
            case 1, 5:
                switch (side) {
                    case 0, 1, 3:
                        return this.rightArrow;
                    case 2:
                        return this.leftArrow;
                    case 4:
                        return this.backTexture;
                    case 5:
                        return this.frontTexture;
                }
            case 0, 4:
                switch (side) {
                    case 0, 1:
                        return this.upArrow;
                    case 2:
                        return this.frontTexture;
                    case 3:
                        return this.backTexture;
                    case 4:
                        return this.leftArrow;
                    case 5:
                        return this.rightArrow;
                }
        }
        return null;
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        this.frontTexture = iconRegister.registerIcon("aether:aercloud/purple_aercloud_front");
        this.backTexture = iconRegister.registerIcon("aether:aercloud/purple_aercloud_back");
        this.upArrow = iconRegister.registerIcon("aether:aercloud/purple_aercloud_up");
        this.downArrow = iconRegister.registerIcon("aether:aercloud/purple_aercloud_down");
        this.leftArrow = iconRegister.registerIcon("aether:aercloud/purple_aercloud_left");
        this.rightArrow = iconRegister.registerIcon("aether:aercloud/purple_aercloud_right");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        int neighborMetadata = world.getBlockMetadata(
            x - Facing.offsetsXForSide[side],
            y - Facing.offsetsYForSide[side],
            z - Facing.offsetsZForSide[side]);

        if (metadata != neighborMetadata) {
            return true;
        }

        if (block == this) {
            return false;
        }

        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.01D, z + 1.0D);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        float x1 = (float) x + 0.5F;
        float y1 = (float) y + 0.0F + random.nextFloat() * 10.0F / 16.0F;
        float z1 = (float) z + 0.5F;
        int meta = world.getBlockMetadata(x, y, z);
        float i = random.nextFloat() * 0.9F;
        float j = random.nextFloat() * 0.2F;
        if (meta != 0 && meta != 4) {
            if (meta == 5) {
                world.spawnParticle(
                    "reddust",
                    (double) (x1 + i),
                    (double) y1,
                    (double) (z1 + j),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
                world.spawnParticle(
                    "reddust",
                    (double) (x1 + i * random.nextFloat() / 0.4F),
                    (double) y1,
                    (double) (z1 + j),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
            } else if (meta == 6) {
                world.spawnParticle(
                    "reddust",
                    (double) (x1 + j),
                    (double) y1,
                    (double) (z1 + i),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
                world.spawnParticle(
                    "reddust",
                    (double) (x1 + j * random.nextFloat() / 0.4F),
                    (double) y1,
                    (double) (z1 + i),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
            } else if (meta == 7) {
                world.spawnParticle(
                    "reddust",
                    (double) (x1 - i),
                    (double) y1,
                    (double) (z1 + j),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
                world.spawnParticle(
                    "reddust",
                    (double) (x1 - i * random.nextFloat() / 0.4F),
                    (double) y1,
                    (double) (z1 + j),
                    (double) 1.0F,
                    (double) 1.0F,
                    (double) 1.0F);
            }
        } else {
            world.spawnParticle(
                "reddust",
                (double) (x1 + j),
                (double) y1,
                (double) (z1 - i),
                (double) 1.0F,
                (double) 1.0F,
                (double) 1.0F);
            world.spawnParticle(
                "reddust",
                (double) (x1 + j * random.nextFloat() / 0.4F),
                (double) y1,
                (double) (z1 - i),
                (double) 1.0F,
                (double) 1.0F,
                (double) 1.0F);
        }

    }
}
