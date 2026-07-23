package com.gildedgames.the_aether.world.genlayer;

import com.gildedgames.the_aether.world.AetherWorld;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAetherBiomes extends GenLayer {

    private static final BiomeGenBase[] AETHER_MAIN_BIOMES = new BiomeGenBase[] {
        AetherWorld.aether_biome,
        AetherWorld.aether_forest,
        AetherWorld.enchanted_island,
        AetherWorld.aercloud_fields,
        AetherWorld.arctic_biome,
        AetherWorld.aether_peaks,
        AetherWorld.divine_island,
        AetherWorld.quicksoil_dunes
    };

    public GenLayerAetherBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int[] result = IntCache.getIntCache(width * height);

        for (int dz = 0; dz < height; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed((long)(x + dx), (long)(z + dz));

                BiomeGenBase biome = AETHER_MAIN_BIOMES[this.nextInt(AETHER_MAIN_BIOMES.length)];
                result[dx + dz * width] = biome.biomeID;
            }
        }

        return result;
    }
}
