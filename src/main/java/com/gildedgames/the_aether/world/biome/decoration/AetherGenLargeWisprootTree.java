package com.gildedgames.the_aether.world.biome.decoration;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.ChunkProviderAether;

import net.minecraft.init.*;

public class AetherGenLargeWisprootTree extends WorldGenAbstractTree
{
    private Block leafBlock;
    private Block logBlock;
    private int logMetadata;
    
    public AetherGenLargeWisprootTree(final Block leafID, final Block logID, final int logMeta) {
        super(true);
        this.leafBlock = leafID;
        this.logBlock = logID;
        this.logMetadata = logMeta;
    }
    
    public boolean branch(final World world, final Random random, int i, int j, int k, final int slant) {
        final int directionX = random.nextInt(2) - 1;
        final int directionY = slant;
        final int directionZ = random.nextInt(2) - 1;
        int x = i;
        int z = k;
        for (int n = 0; n < 2; ++n) {
            i += directionX;
            j += directionY;
            k += directionZ;
            x -= directionX;
            z -= directionZ;
            if (world.getBlock(i, j, k) == this.leafBlock) {
                world.setBlock(i, j, k, this.logBlock, this.logMetadata, ChunkProviderAether.placementFlagType);
                world.setBlock(x, j, z, this.logBlock, this.logMetadata, ChunkProviderAether.placementFlagType);
            }
        }
        return true;
    }
    
    public boolean generate(final World world, final Random random, final int i, final int j, final int k) {
        final int height = 11;
        boolean flag = true;
        if (j < 1 || j + height + 1 > 128) {
            return false;
        }
        for (int i2 = j; i2 <= j + 1 + height; ++i2) {
            byte byte0 = 1;
            if (i2 == j) {
                byte0 = 0;
            }
            if (i2 >= j + 1 + height - 2) {
                byte0 = 2;
            }
            for (int i3 = i - byte0; i3 <= i + byte0 && flag; ++i3) {
                for (int l2 = k - byte0; l2 <= k + byte0 && flag; ++l2) {
                    if (i2 >= 0 && i2 < 128) {
                        final Block j2 = world.getBlock(i3, i2, l2);
                        if (j2 != Blocks.air && j2 != this.leafBlock) {
                            flag = false;
                        }
                    }
                    else {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }
        for (int y = j; y < j + height; ++y) {
            if (!this.isReplaceable(world, i, y, k)) {
                return false;
            }
        }
        if (world.getBlock(i, j - 1, k) != BlocksAether.arctic_grass && world.getBlock(i, j - 1, k) != BlocksAether.aether_dirt) {
            return false;
        }
        for (int x = i - 3; x < i + 5; ++x) {
            for (int y2 = j + 5; y2 < j + 13; ++y2) {
                for (int z = k - 3; z < k + 5; ++z) {
                    if ((x - i) * (x - i) + (y2 - j - 8) * (y2 - j - 8) + (z - k) * (z - k) < 12 + random.nextInt(5) && world.getBlock(x, y2, z) == Blocks.air) {
                        world.setBlock(x, y2, z, this.leafBlock);
                    }
                }
            }
        }
        for (int n = 0; n < height - 2; ++n) {
            if (n > 4) {
                this.branch(world, random, i, j + n, k, n / 4 - 1);
            }
            if (this.isReplaceable(world, i, j + n, k)) {
                world.setBlock(i, j + n, k, this.logBlock, this.logMetadata, ChunkProviderAether.placementFlagType);
            }
        }
        return true;
    }
}
