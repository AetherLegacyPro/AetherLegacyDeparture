package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenCactus extends WorldGenerator {

    public boolean generate(World world, Random random, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        for (int l = 0; l < 10; ++l) {
            int i1 = p_76484_3_ + random.nextInt(8) - random.nextInt(8);
            int j1 = p_76484_4_ + random.nextInt(4) - random.nextInt(4);
            int k1 = p_76484_5_ + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1)) {
                int l1 = 1 + random.nextInt(random.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2) {
                    if (BlocksAether.aether_cactus.canBlockStay(world, i1, j1 + i2, k1)) {
                        world.setBlock(i1, j1 + i2, k1, BlocksAether.aether_cactus, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}
