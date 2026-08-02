package com.gildedgames.the_aether.blocks.natural;

import java.util.ArrayList;
import java.util.Random;
import com.gildedgames.the_aether.CommonProxy;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.IShearable;

public class BlockAetherTallGrass extends BlockBush implements IShearable {

    public BlockAetherTallGrass() {
        this.setHardness(0.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(0.5F - 0.2F, 0.0F, 0.5F - 0.2F, 0.5F + 0.2F, 0.2F * 3.0F, 0.5F + 0.2F);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        return soil == BlocksAether.aether_grass || soil == BlocksAether.aether_dirt || soil == BlocksAether.arctic_grass;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        return soil != null && this.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public int getRenderType() {
        return CommonProxy.aetherFlowerRenderID;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return null;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(BlocksAether.aether_tallgrass));
        return ret;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        if (this.isStormySkiesBiome(biome)) {
            return this.shiftTowardBlue(0xFFFFFF);
        }

        return 0xFFFFFF;
    }

    private boolean isStormySkiesBiome(BiomeGenBase biome) {
        return biome != null && AetherWorld.stormy_skies != null && biome.biomeID == AetherWorld.stormy_skies.biomeID;
    }

    private int shiftTowardBlue(int color) {
        int r = (color >> 16) & 255;
        int g = (color >> 8) & 255;
        int b = color & 255;
        r = (int)(r * 0.68F);
        g = (int)(g * 0.82F);
        b = Math.min(255, (int)(b * 1.18F) + 20);
        return (r << 16) | (g << 8) | b;
    }
}
