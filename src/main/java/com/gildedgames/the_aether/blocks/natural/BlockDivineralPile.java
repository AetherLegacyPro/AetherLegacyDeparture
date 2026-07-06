package com.gildedgames.the_aether.blocks.natural;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDivineralPile extends Block {

	public BlockDivineralPile() {
        super(Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setTickRandomly(true);
        this.setCreativeTab(AetherCreativeTabs.blocks);
        this.func_150154_b(0);
        this.setHardness(1.3F);
		this.setResistance(2.3F);
        this.setHarvestLevel("shovel", 3);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("aether_legacy:divineral_pile");
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        int l = world.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_) & 7;
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (float)p_149668_3_ + (float)l * f, (double)p_149668_4_ + this.maxZ);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void setBlockBoundsForItemRender() {
        this.func_150154_b(0);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        this.func_150154_b(iBlockAccess.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
    }

    protected void func_150154_b(int p_150154_1_) {
        int j = p_150154_1_ & 7;
        float f = (float)(2 * (1 + j)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public boolean canPlaceBlockAt(World world, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        Block block = world.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_);
        return block != Blocks.ice && block != Blocks.packed_ice ? (block.isLeaves(world, p_149742_2_, p_149742_3_ - 1, p_149742_4_) ? true : (block == this && (world.getBlockMetadata(p_149742_2_, p_149742_3_ - 1, p_149742_4_) & 7) == 7 ? true : block.isOpaqueCube())) : false;
    }

    public void onNeighborBlockChange(World world, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block block) {
        this.func_150155_m(world, p_149695_2_, p_149695_3_, p_149695_4_);
    }

    private boolean func_150155_m(World world, int p_150155_2_, int p_150155_3_, int p_150155_4_) {
        if (!this.canPlaceBlockAt(world, p_150155_2_, p_150155_3_, p_150155_4_)) {
            world.setBlockToAir(p_150155_2_, p_150155_3_, p_150155_4_);
            return false;
        }
        else {
            return true;
        }
    }

    public void harvestBlock(World world, EntityPlayer entityPlayer, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        super.harvestBlock(world, entityPlayer, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
        world.setBlockToAir(p_149636_3_, p_149636_4_, p_149636_5_);
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return ItemsAether.divineral_nugget;
    }

    public int quantityDropped(World world, EntityPlayer entityPlayer, int x, int y, int z, Random random) {
    	int meta = world.getBlockMetadata(x, y, z);
        switch (meta) {
            case 1: return 2 + 2 * (1 + random.nextInt(2));//4~6
            case 2: return 3 + 2 * (2 + random.nextInt(2));//7~9
            case 3: return 4 + 2 * (3 + random.nextInt(3));//10~14
            case 4: return 6 + 3 * (4 + random.nextInt(3));//18~24
            case 5: return 8 + 4 * (5 + random.nextInt(4));//28~40
            case 6: return 12 + 5 * (6 +random.nextInt(5));//42~62
            case 7: return 22 + 6 * (7 +random.nextInt(7));//64~100

            default: return 4;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        return p_149646_5_ == 1 ? true : super.shouldSideBeRendered(iBlockAccess, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return (meta & 7) + 1;
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return meta >= 7 ? false : blockMaterial.isReplaceable();
    }
}
