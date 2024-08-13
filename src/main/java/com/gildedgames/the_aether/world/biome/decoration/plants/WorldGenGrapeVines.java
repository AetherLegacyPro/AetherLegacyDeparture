package com.gildedgames.the_aether.world.biome.decoration.plants;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;

import java.util.*;

public class WorldGenGrapeVines extends WorldGenerator
{
    private Block plantBlockId;
    private int placementChance;
    
    public WorldGenGrapeVines(final Block i, final int chance) {
        this.plantBlockId = i;
        this.placementChance = chance;
    }
    
    public boolean generate(final World world, final Random random, final int i, final int j, final int k) {
        for (int l = 0; l < this.placementChance; ++l) {
            final int i2 = i + random.nextInt(6) - random.nextInt(6);
            final int j2 = j + random.nextInt(4) - random.nextInt(4);
            final int k2 = k + random.nextInt(6) - random.nextInt(6);
            if (world.isAirBlock(i2, j2, k2) && this.plantBlockId.canBlockStay(world, i2, j2, k2)) {
                world.setBlock(i2, j2, k2, this.plantBlockId);
            }
        }
        return true;
    }
}
