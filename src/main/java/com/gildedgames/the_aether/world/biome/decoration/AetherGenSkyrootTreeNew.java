package com.gildedgames.the_aether.world.biome.decoration;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.ChunkProviderAether;

import net.minecraft.init.*;

public class AetherGenSkyrootTreeNew extends WorldGenAbstractTree
{
    private Block leafBlock;
    private Block logBlock;
    private int logMetadata;
    
    public AetherGenSkyrootTreeNew(final Block leafID, final Block logID, final int logMeta) {
        super(true);
        this.leafBlock = leafID;
        this.logBlock = logID;
        this.logMetadata = logMeta;
    }
    
    public boolean generate(final World world, final Random random, final int i, final int j, final int k) {
        final int l = random.nextInt(3) + 4;
        boolean flag = true;
        if (j < 1 || j + l + 1 > 128) {
            return false;
        }
        for (int i2 = j; i2 <= j + 1 + l; ++i2) {
            byte byte0 = 1;
            if (i2 == j) {
                byte0 = 0;
            }
            if (i2 >= j + 1 + l - 2) {
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
        final Block j3 = world.getBlock(i, j - 1, k);
        if ((j3 != BlocksAether.aether_grass && j3 != BlocksAether.aether_dirt) || j >= 128 - l - 1) {
            return false;
        }
        world.setBlock(i, j - 1, k, BlocksAether.aether_dirt);
        for (int k2 = j - 3 + l; k2 <= j + l; ++k2) {
            final int j4 = k2 - (j + l);
            for (int i4 = 1 - j4 / 2, k3 = i - i4; k3 <= i + i4; ++k3) {
                final int l3 = k3 - i;
                for (int i5 = k - i4; i5 <= k + i4; ++i5) {
                    final int j5 = i5 - k;
                    if ((Math.abs(l3) != i4 || Math.abs(j5) != i4 || (random.nextInt(2) != 0 && j4 != 0)) && !world.getBlock(k3, k2, i5).isOpaqueCube()) {
                        world.setBlock(k3, k2, i5, this.leafBlock);
                    }
                }
            }
        }
        for (int l4 = 0; l4 < l; ++l4) {
            final Block k4 = world.getBlock(i, j + l4, k);
            if (k4 == Blocks.air || k4 == this.leafBlock) {
                world.setBlock(i, j + l4, k, this.logBlock, this.logMetadata, ChunkProviderAether.placementFlagType);
            }
        }
        return true;
    }
}
