package com.gildedgames.the_aether.blocks.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import static net.minecraftforge.common.EnumPlantType.*;

public class BlockUpsideDownFlowerBase extends Block implements IPlantable {

    protected BlockUpsideDownFlowerBase(Material material) {
        super(material);
        this.setTickRandomly(true);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    protected BlockUpsideDownFlowerBase() {
        this(Material.plants);
    }

    public boolean canPlaceBlockAt(World world, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return super.canPlaceBlockAt(world, p_149742_2_, p_149742_3_, p_149742_4_) && this.canBlockStay(world, p_149742_2_, p_149742_3_, p_149742_4_);
    }

    public void onNeighborBlockChange(World world, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block block) {
        super.onNeighborBlockChange(world, p_149695_2_, p_149695_3_, p_149695_4_, block);
        this.checkAndDropBlock(world, p_149695_2_, p_149695_3_, p_149695_4_);
    }

    public void updateTick(World world, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random random) {
        this.checkAndDropBlock(world, p_149674_2_, p_149674_3_, p_149674_4_);
    }

    protected void checkAndDropBlock(World world, int p_149855_2_, int p_149855_3_, int p_149855_4_) {
        if (!this.canBlockStay(world, p_149855_2_, p_149855_3_, p_149855_4_)) {
            this.dropBlockAsItem(world, p_149855_2_, p_149855_3_, p_149855_4_, world.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
            world.setBlock(p_149855_2_, p_149855_3_, p_149855_4_, getBlockById(0), 0, 2);
        }
    }

    public boolean canBlockStay(World world, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
        return  world.getBlock(p_149718_2_, p_149718_3_ + 1, p_149718_4_).canSustainPlant(world, p_149718_2_, p_149718_3_ + 1, p_149718_4_, ForgeDirection.UP, this);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }
}
