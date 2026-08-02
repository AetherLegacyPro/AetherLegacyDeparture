package com.gildedgames.the_aether.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.world.genlayer.GenLayerAether;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerAether extends WorldChunkManager {

    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    public WorldChunkManagerAether(long seed) {
        super();

        this.biomeCache = new BiomeCache(this);

        this.biomesToSpawnIn = Arrays.asList(new BiomeGenBase[] {
            AetherWorld.aether_biome,
            AetherWorld.arctic_biome,
            AetherWorld.enchanted_island,
            AetherWorld.aether_peaks,
            AetherWorld.aether_forest,
            AetherWorld.divine_island,
            AetherWorld.stormy_skies,
        });

        GenLayer[] layers = GenLayerAether.initializeAllBiomeGenerators(seed);

        this.genBiomes = layers[0];
        this.biomeIndexLayer = layers[1];
    }

    public WorldChunkManagerAether(World world) {
        this(world.getSeed());
    }

    @Override
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawnIn;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z) {
        return this.biomeCache.getBiomeGenAt(x, z);
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new BiomeGenBase[width * height];
        }

        int[] ids = this.genBiomes.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i) {
            biomes[i] = this.getBiomeFromId(ids[i]);
        }

        return biomes;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomes, int x, int z, int width, int height) {
        return this.getBiomeGenAt(biomes, x, z, width, height, true);
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomes, int x, int z, int width, int height, boolean useCache) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new BiomeGenBase[width * height];
        }

        if (useCache && width == 16 && height == 16 && (x & 15) == 0 && (z & 15) == 0) {
            BiomeGenBase[] cachedBiomes = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(cachedBiomes, 0, biomes, 0, width * height);
            return biomes;
        }

        int[] ids = this.biomeIndexLayer.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i) {
            biomes[i] = this.getBiomeFromId(ids[i]);
        }

        return biomes;
    }

    @Override
    public float[] getRainfall(float[] rainfall, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (rainfall == null || rainfall.length < width * height) {
            rainfall = new float[width * height];
        }

        int[] ids = this.biomeIndexLayer.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i) {
            BiomeGenBase biome = this.getBiomeFromId(ids[i]);

            float rain = biome.getIntRainfall() / 65536.0F;

            if (rain > 1.0F) {
                rain = 1.0F;
            }

            rainfall[i] = rain;
        }

        return rainfall;
    }

    @Override
    public boolean areBiomesViable(int x, int z, int radius, List allowedBiomes) {
        IntCache.resetIntCache();

        int minX = x - radius >> 2;
        int minZ = z - radius >> 2;
        int maxX = x + radius >> 2;
        int maxZ = z + radius >> 2;

        int width = maxX - minX + 1;
        int height = maxZ - minZ + 1;

        int[] ids = this.genBiomes.getInts(minX, minZ, width, height);

        for (int i = 0; i < width * height; ++i) {
            BiomeGenBase biome = this.getBiomeFromId(ids[i]);

            if (!allowedBiomes.contains(biome)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ChunkPosition findBiomePosition(int x, int z, int radius, List targetBiomes, Random random) {
        IntCache.resetIntCache();

        int minX = x - radius >> 2;
        int minZ = z - radius >> 2;
        int maxX = x + radius >> 2;
        int maxZ = z + radius >> 2;

        int width = maxX - minX + 1;
        int height = maxZ - minZ + 1;

        int[] ids = this.genBiomes.getInts(minX, minZ, width, height);

        ChunkPosition result = null;
        int found = 0;

        for (int dz = 0; dz < height; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                int biomeX = minX + dx;
                int biomeZ = minZ + dz;

                BiomeGenBase biome = this.getBiomeFromId(ids[dx + dz * width]);

                if (targetBiomes.contains(biome)) {
                    if (result == null || random.nextInt(found + 1) == 0) {
                        result = new ChunkPosition(biomeX << 2, 0, biomeZ << 2);
                    }

                    ++found;
                }
            }
        }

        return result;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }

    private BiomeGenBase getBiomeFromId(int id) {
        if (AetherWorld.aether_biome != null && id == AetherWorld.aether_biome.biomeID) {
            return AetherWorld.aether_biome;
        }

        if (AetherWorld.arctic_biome != null && id == AetherWorld.arctic_biome.biomeID) {
            return AetherWorld.arctic_biome;
        }

        if (AetherWorld.enchanted_island != null && id == AetherWorld.enchanted_island.biomeID) {
            return AetherWorld.enchanted_island;
        }

        if (AetherWorld.aether_peaks != null && id == AetherWorld.aether_peaks.biomeID) {
            return AetherWorld.aether_peaks;
        }

        if (AetherWorld.divine_island != null && id == AetherWorld.divine_island.biomeID) {
            return AetherWorld.divine_island;
        }

        if (AetherWorld.quicksoil_dunes != null && id == AetherWorld.quicksoil_dunes.biomeID) {
            return AetherWorld.quicksoil_dunes;
        }

        if (AetherWorld.aether_forest != null && id == AetherWorld.aether_forest.biomeID) {
            return AetherWorld.aether_forest;
        }

        if (AetherWorld.stormy_skies != null && id == AetherWorld.stormy_skies.biomeID) {
            return AetherWorld.stormy_skies;
        }

        if (AetherWorld.aercloud_fields != null && id == AetherWorld.aercloud_fields.biomeID) {
            return AetherWorld.aercloud_fields;
        }

        return AetherWorld.aether_biome;
    }
}
