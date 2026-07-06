package com.gildedgames.the_aether.world.biome.decoration.plants;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAetherGrass extends WorldGenerator {
    private Block field_150522_a;
    private int tallGrassMetadata;

    public WorldGenAetherGrass(Block block, int p_i45466_2_) {
        this.field_150522_a = block;
        this.tallGrassMetadata = p_i45466_2_;
    }

    public boolean generate(World world, Random random, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        Block block;

        do {
            block = world.getBlock(p_76484_3_, p_76484_4_, p_76484_5_);
            if (!(block.isLeaves(world, p_76484_3_, p_76484_4_, p_76484_5_) || block.isAir(world, p_76484_3_, p_76484_4_, p_76484_5_))) {
                break;
            }
            --p_76484_4_;
        } while (p_76484_4_ > 0);

        for (int l = 0; l < 128; ++l) {
            int i1 = p_76484_3_ + random.nextInt(8) - random.nextInt(8);
            int j1 = p_76484_4_ + random.nextInt(4) - random.nextInt(4);
            int k1 = p_76484_5_ + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && this.field_150522_a.canBlockStay(world, i1, j1, k1)) {
                world.setBlock(i1, j1, k1, this.field_150522_a, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }
}
