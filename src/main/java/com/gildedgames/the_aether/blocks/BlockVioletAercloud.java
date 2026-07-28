package com.gildedgames.the_aether.blocks;

import com.gildedgames.the_aether.items.block.IColoredBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

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
    private IIcon backTextureOpaque;
    private IIcon frontTextureOpaque;
    private IIcon leftArrowOpaque;
    private IIcon rightArrowOpaque;
    private IIcon upArrowOpaque;
    private IIcon downArrowOpaque;

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).capabilities.isFlying) {
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

                if (meta >= 0 && entity.posY <= (double)y + 1.6 && entity.posY >= (double)y - 0.2) {
                    entity.motionY = 0.1;
                }

                if (meta != 0 && meta != 4) {
                    if (meta != 1 && meta != 5) {
                        if (meta != 2 && meta != 6) {
                            if (meta == 3 || meta == 7) {
                                entity.motionX = (double)-2.5F;
                            }
                        } else {
                            entity.motionZ = (double)2.5F;
                        }
                    } else {
                        entity.motionX = (double)2.5F;
                    }
                } else {
                    entity.motionZ = (double)-2.5F;
                }

                if (!(entity instanceof EntityPlayer)) {
                    entity.fallDistance = -20.0F;
                }
            }

        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        int facing = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + (double)0.5F) & 3;
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
        boolean isFancy = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        if (meta != 0 && meta != 4) {
            if (meta != 1 && meta != 5) {
                if (meta != 2 && meta != 6) {
                    if (meta == 3 || meta == 7) {
                        switch (side) {
                            case 0:
                                return isFancy ? this.leftArrow : this.leftArrowOpaque;
                            case 1:
                                return isFancy ? this.leftArrow : this.leftArrowOpaque;
                            case 2:
                                return isFancy ? this.rightArrow : this.rightArrowOpaque;
                            case 3:
                                return isFancy ? this.leftArrow : this.leftArrowOpaque;
                            case 4:
                                return isFancy ? this.frontTexture : this.frontTextureOpaque;
                            case 5:
                                return isFancy ? this.backTexture : this.backTextureOpaque;
                        }
                    }
                } else {
                    switch (side) {
                        case 0:
                            return isFancy ? this.downArrow : this.downArrowOpaque;
                        case 1:
                            return isFancy ? this.downArrow : this.downArrowOpaque;
                        case 2:
                            return isFancy ? this.backTexture : this.backTextureOpaque;
                        case 3:
                            return isFancy ? this.frontTexture : this.frontTextureOpaque;
                        case 4:
                            return isFancy ? this.rightArrow : this.rightArrowOpaque;
                        case 5:
                            return isFancy ? this.leftArrow : this.leftArrowOpaque;
                    }
                }
            } else {
                switch (side) {
                    case 0:
                        return isFancy ? this.rightArrow : this.rightArrowOpaque;
                    case 1:
                        return isFancy ? this.rightArrow : this.rightArrowOpaque;
                    case 2:
                        return isFancy ? this.leftArrow : this.leftArrowOpaque;
                    case 3:
                        return isFancy ? this.rightArrow : this.rightArrowOpaque;
                    case 4:
                        return isFancy ? this.backTexture : this.backTextureOpaque;
                    case 5:
                        return isFancy ? this.frontTexture : this.frontTextureOpaque;
                }
            }
        } else {
            switch (side) {
                case 0:
                    return isFancy ? this.upArrow : this.upArrowOpaque;
                case 1:
                    return isFancy ? this.upArrow : this.upArrowOpaque;
                case 2:
                    return isFancy ? this.frontTexture : this.frontTextureOpaque;
                case 3:
                    return isFancy ? this.backTexture : this.backTextureOpaque;
                case 4:
                    return isFancy ? this.leftArrow : this.leftArrowOpaque;
                case 5:
                    return isFancy ? this.rightArrow : this.rightArrowOpaque;
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
        this.frontTextureOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Front_Opaque");
        this.backTextureOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Back_Opaque");
        this.upArrowOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Up_Opaque");
        this.downArrowOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Down_Opaque");
        this.leftArrowOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Left_Opaque");
        this.rightArrowOpaque = iconRegister.registerIcon("aetherii:Purple Aercloud Right_Opaque");
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)x, (double)y, (double)z);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        float x1 = (float)x + 0.5F;
        float y1 = (float)y + 0.0F + random.nextFloat() * 10.0F / 16.0F;
        float z1 = (float)z + 0.5F;
        int meta = world.getBlockMetadata(x, y, z);
        float i = random.nextFloat() * 0.9F;
        float j = random.nextFloat() * 0.2F;
        if (meta != 0 && meta != 4) {
            if (meta == 5) {
                world.spawnParticle("reddust", (double)(x1 + i), (double)y1, (double)(z1 + j), (double)1.0F, (double)1.0F, (double)1.0F);
                world.spawnParticle("reddust", (double)(x1 + i * random.nextFloat() / 0.4F), (double)y1, (double)(z1 + j), (double)1.0F, (double)1.0F, (double)1.0F);
            } else if (meta == 6) {
                world.spawnParticle("reddust", (double)(x1 + j), (double)y1, (double)(z1 + i), (double)1.0F, (double)1.0F, (double)1.0F);
                world.spawnParticle("reddust", (double)(x1 + j * random.nextFloat() / 0.4F), (double)y1, (double)(z1 + i), (double)1.0F, (double)1.0F, (double)1.0F);
            } else if (meta == 7) {
                world.spawnParticle("reddust", (double)(x1 - i), (double)y1, (double)(z1 + j), (double)1.0F, (double)1.0F, (double)1.0F);
                world.spawnParticle("reddust", (double)(x1 - i * random.nextFloat() / 0.4F), (double)y1, (double)(z1 + j), (double)1.0F, (double)1.0F, (double)1.0F);
            }
        } else {
            world.spawnParticle("reddust", (double)(x1 + j), (double)y1, (double)(z1 - i), (double)1.0F, (double)1.0F, (double)1.0F);
            world.spawnParticle("reddust", (double)(x1 + j * random.nextFloat() / 0.4F), (double)y1, (double)(z1 - i), (double)1.0F, (double)1.0F, (double)1.0F);
        }

    }
}
