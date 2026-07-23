package com.gildedgames.the_aether.world.gen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class MapGenQuicksoil extends MapGenBase {

    private static final int CHUNK_HEIGHT = 256;

    private int getBlockIndex(int localX, int y, int localZ) {
        return (localX * 16 + localZ) * CHUNK_HEIGHT + y;
    }

    @Override
    public void func_151539_a(IChunkProvider provider, World world, int chunkX, int chunkZ, Block[] blocks) {
        if (this.rand.nextInt(10) != 0) {
            return;
        }

        for (int x = 3; x < 12; x++) {
            for (int z = 3; z < 12; z++) {
                for (int y = 3; y < CHUNK_HEIGHT - 2; y++) {
                    int pos = this.getBlockIndex(x, y, z);
                    int above1 = this.getBlockIndex(x, y + 1, z);
                    int above2 = this.getBlockIndex(x, y + 2, z);

                    if (blocks[pos] == Blocks.air && (blocks[above1] == BlocksAether.aether_grass || blocks[above1] == BlocksAether.arctic_grass
                        || blocks[above1] == BlocksAether.enchanted_aether_grass || blocks[above1] == BlocksAether.divine_grass)
                        && blocks[above2] == Blocks.air) {
                        this.generate(blocks, x, y, z);
                        break;
                    }
                }
            }
        }
    }

    private void generate(Block[] blocks, int posX, int posY, int posZ) {
        for (int x = posX - 3; x < posX + 4; x++) {
            for (int z = posZ - 3; z < posZ + 4; z++) {
                if (x < 0 || x >= 16 || z < 0 || z >= 16) {
                    continue;
                }

                if (((x - posX) * (x - posX) + (z - posZ) * (z - posZ)) >= 12) {
                    continue;
                }

                int position = this.getBlockIndex(x, posY, z);

                if (blocks[position] == Blocks.air) {
                    blocks[position] = BlocksAether.quicksoil;
                }
            }
        }
    }
}
