package com.gildedgames.the_aether.world.biome.decoration.plants;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAetherGrass extends WorldGenerator
{
    private Block field_150522_a;
    private int tallGrassMetadata;

    public WorldGenAetherGrass(Block p_i45466_1_, int p_i45466_2_)
    {
        this.field_150522_a = p_i45466_1_;
        this.tallGrassMetadata = p_i45466_2_;
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        Block block;

        do
        {
            block = p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_);
            if (!(block.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_) || block.isAir(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_)))
            {
                break;
            }
            --p_76484_4_;
        } while (p_76484_4_ > 0);

        for (int l = 0; l < 128; ++l)
        {
            int i1 = p_76484_3_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);
            int j1 = p_76484_4_ + p_76484_2_.nextInt(4) - p_76484_2_.nextInt(4);
            int k1 = p_76484_5_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);

            if (p_76484_1_.isAirBlock(i1, j1, k1) && this.field_150522_a.canBlockStay(p_76484_1_, i1, j1, k1))
            {
                p_76484_1_.setBlock(i1, j1, k1, this.field_150522_a, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }
}
