package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenLiquids extends WorldGenerator
{
    public AetherGenLiquids(final Block i) {
    }
    
    public boolean generate(final World world, final Random random, final int i, final int j, final int k) {
        if (world.getBlock(i, j + 1, k) != BlocksAether.holystone) {
            return false;
        }
        if (world.getBlock(i, j - 1, k) != BlocksAether.holystone) {
            return false;
        }
        if (world.getBlock(i, j, k) != Blocks.air && (world.getBlock(i, j, k) != BlocksAether.holystone)) {
            return false;
        }
        int l = 0;
        if (world.getBlock(i - 1, j, k) == BlocksAether.holystone) {
            ++l;
        }
        if (world.getBlock(i + 1, j, k) == BlocksAether.holystone) {
            ++l;
        }
        if (world.getBlock(i, j, k - 1) == BlocksAether.holystone) {
            ++l;
        }
        if (world.getBlock(i, j, k + 1) == BlocksAether.holystone) {
            ++l;
        }
        int i2 = 0;
        if (world.isAirBlock(i - 1, j, k)) {
            ++i2;
        }
        if (world.isAirBlock(i + 1, j, k)) {
            ++i2;
        }
        if (world.isAirBlock(i, j, k - 1)) {
            ++i2;
        }
        if (world.isAirBlock(i, j, k + 1)) {
            ++i2;
        }
        if (l == 3 && i2 == 1) {
            world.setBlock(i, j, k, (Block)Blocks.flowing_water);
        }
        return true;
    }
}