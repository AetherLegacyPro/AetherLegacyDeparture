package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAmethystGlowstone extends WorldGenerator {

    public boolean generate(World world, Random random, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        if (!world.isAirBlock(p_76484_3_, p_76484_4_, p_76484_5_)) {
            return false;
        }
        else if (world.getBlock(p_76484_3_, p_76484_4_ + 1, p_76484_5_) != BlocksAether.holystone) {
            return false;
        }
        else {
            world.setBlock(p_76484_3_, p_76484_4_, p_76484_5_, BlocksAether.amethyst_glowstone, 0, 2);

            for (int l = 0; l < 1500; ++l) {
                int i1 = p_76484_3_ + random.nextInt(8) - random.nextInt(8);
                int j1 = p_76484_4_ - random.nextInt(12);
                int k1 = p_76484_5_ + random.nextInt(8) - random.nextInt(8);

                if (world.getBlock(i1, j1, k1).getMaterial() == Material.air) {
                    int l1 = 0;

                    for (int i2 = 0; i2 < 6; ++i2) {
                        Block block = null;

                        if (i2 == 0) {
                            block = world.getBlock(i1 - 1, j1, k1);
                        }

                        if (i2 == 1) {
                            block = world.getBlock(i1 + 1, j1, k1);
                        }

                        if (i2 == 2) {
                            block = world.getBlock(i1, j1 - 1, k1);
                        }

                        if (i2 == 3) {
                            block = world.getBlock(i1, j1 + 1, k1);
                        }

                        if (i2 == 4) {
                            block = world.getBlock(i1, j1, k1 - 1);
                        }

                        if (i2 == 5) {
                            block = world.getBlock(i1, j1, k1 + 1);
                        }

                        if (block == BlocksAether.amethyst_glowstone) {
                            ++l1;
                        }
                    }

                    if (l1 == 1) {
                        world.setBlock(i1, j1, k1, BlocksAether.amethyst_glowstone, 0, 2);
                    }
                }
            }

            return true;
        }
    }
}
