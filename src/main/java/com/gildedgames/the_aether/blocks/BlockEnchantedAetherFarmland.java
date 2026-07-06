package com.gildedgames.the_aether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnchantedAetherFarmland extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon field_149824_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_149823_b;

    protected BlockEnchantedAetherFarmland() {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setStepSound(soundTypeGravel);
        this.setLightOpacity(255);
        this.setHardness(0.45F);
        this.setResistance(0.45F);
        this.setHarvestLevel("shovel", 0);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return AxisAlignedBB.getBoundingBox(p_149668_2_, p_149668_3_, p_149668_4_, p_149668_2_ + 1, p_149668_3_ + 1, p_149668_4_ + 1);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? (p_149691_2_ > 0 ? this.field_149824_a : this.field_149823_b) : BlocksAether.enchanted_aether_grass.getBlockTextureFromSide(p_149691_1_);
    }

    public void updateTick(World world, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random random) {
        if (!this.func_149821_m(world, p_149674_2_, p_149674_3_, p_149674_4_) && !world.canLightningStrikeAt(p_149674_2_, p_149674_3_ + 1, p_149674_4_)) {
            int l = world.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

            if (l > 0) {
                world.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, l - 1, 2);
            }
            else if (!this.func_149822_e(world, p_149674_2_, p_149674_3_, p_149674_4_)) {
                world.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, BlocksAether.aether_dirt);
            }
        }
        else {
            world.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 7, 2);
        }
    }

    private boolean func_149822_e(World world, int p_149822_2_, int p_149822_3_, int p_149822_4_) {
        byte b0 = 0;

        for (int l = p_149822_2_ - b0; l <= p_149822_2_ + b0; ++l) {
            for (int i1 = p_149822_4_ - b0; i1 <= p_149822_4_ + b0; ++i1) {
                Block block = world.getBlock(l, p_149822_3_ + 1, i1);

                if (block instanceof IPlantable && canSustainPlant(world, p_149822_2_, p_149822_3_, p_149822_4_, ForgeDirection.UP, (IPlantable)block)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean func_149821_m(World world, int p_149821_2_, int p_149821_3_, int p_149821_4_) {
        for (int l = p_149821_2_ - 4; l <= p_149821_2_ + 4; ++l) {
            for (int i1 = p_149821_3_; i1 <= p_149821_3_ + 1; ++i1) {
                for (int j1 = p_149821_4_ - 4; j1 <= p_149821_4_ + 4; ++j1) {
                    if (world.getBlock(l, i1, j1).getMaterial() == Material.water) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void onNeighborBlockChange(World world, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block block) {
        super.onNeighborBlockChange(world, p_149695_2_, p_149695_3_, p_149695_4_, block);
        Material material = world.getBlock(p_149695_2_, p_149695_3_ + 1, p_149695_4_).getMaterial();

        if (material.isSolid()) {
            world.setBlock(p_149695_2_, p_149695_3_, p_149695_4_, BlocksAether.aether_dirt);
        }
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return BlocksAether.aether_dirt.getItemDropped(0, random, p_149650_3_);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.getItemFromBlock(BlocksAether.aether_dirt);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.field_149824_a = iconRegister.registerIcon("aether_legacy:enchanted_aether_farmland_wet");
        this.field_149823_b = iconRegister.registerIcon("aether_legacy:enchanted_aether_farmland_dry");
    }
}
