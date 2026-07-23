package com.gildedgames.the_aether.world;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import com.gildedgames.the_aether.world.biome.AetherBiomesRegistry;

/**
 * Assigns Aether biomes by gradient-ascending the same density noise that
 * {@link ChunkProviderAether} uses to carve islands, using the correct
 * evaluation scale so adjacent chunks are properly correlated.
 *
 * <h3>Root cause of previous failures</h3>
 *
 * The previous gradient-ascent attempt evaluated the noise as:
 *
 * <pre>
 * noiseGen.generateNoiseOctaves(buf, cx * 2, Y, cz * 2, 1, 1, 1, D = 1368, D1, D)
 * </pre>
 *
 * When {@code nx=1} (one sample), no stepping occurs — the {@code scaleX}
 * parameter is irrelevant for positioning. But internally Minecraft's
 * {@code NoiseGeneratorOctaves} still multiplies the start coordinate by
 * {@code scaleX} before evaluating, so the actual noise position was
 * {@code cx*2*1368 = cx*2736}. Adjacent chunks differ by 2736 noise units
 * while the Perlin period is 256 units, giving an autocorrelation of
 * {@code cos(2π × 2736/256) ≈ –0.37} — essentially uncorrelated random noise.
 * Gradient ascent on random noise is meaningless, which is why biomes appeared
 * independent of islands.
 *
 * <h3>Correct evaluation</h3>
 *
 * {@code ChunkProviderAether.setupNoiseGenerators} is called with
 * {@code x = chunkX*2}, and the 3 horizontal samples within a chunk are spaced
 * by the scale {@code D=1368} — they cover wide variance within the chunk for
 * terrain detail. But the <em>chunk-to-chunk</em> coordinate only advances by
 * 2 per chunk (from {@code cx*2} to {@code (cx+1)*2}).
 *
 * <p>
 * To get the density at just one point per chunk — the value that
 * characterises "which island cluster am I in" — we sample at
 * {@code (cx*2, SAMPLE_Y, cz*2)} with {@code scaleX=scaleZ=1} (no offset
 * multiplication). This evaluates the noise at integer position {@code cx*2},
 * and adjacent chunks differ by exactly 2 noise units. With Perlin period 256
 * the correlation is {@code cos(2π × 2/256) ≈ 0.9995} — extremely high.
 * The noise changes smoothly, completing one full cycle every
 * {@code 256/2 = 128 chunks = 2048 blocks}.
 *
 * <h3>Island alignment via gradient ascent</h3>
 *
 * This slow-varying density field has local maxima at each island cluster
 * centre. Gradient ascent (move to the highest-valued neighbour, up to
 * {@link #MAX_STEPS} steps) converges every chunk on the same island to the
 * same peak position. Hashing that peak position with the world seed selects
 * the biome, so all chunks of the same island naturally receive the same biome.
 *
 * <h3>Replicating the noise generator</h3>
 *
 * {@code ChunkProviderAether} builds its generators as:
 *
 * <pre>
 * Random rand = new Random(worldSeed);
 * noiseGen1 = new NoiseGeneratorOctaves(rand, 16); // consumes 16 nextLong()s
 * perlinNoise1 = new NoiseGeneratorOctaves(rand, 8); // consumes 8 more
 * </pre>
 *
 * We replicate {@code noiseGen1} by advancing a fresh {@code Random(worldSeed)}
 * in the same way, producing bit-for-bit identical noise values.
 */
public class AetherBiomeNoise {

    /**
     * Y grid level used for sampling. Grid level 12 = blocks Y 48–51, squarely
     * in the middle of the island height band and away from the floor/ceiling
     * boundary conditions that pull density strongly negative.
     */
    private static final int SAMPLE_Y = 12;

    /**
     * Maximum gradient-ascent steps. 8 easily covers the half-width of the
     * largest islands (~7 chunks).
     */
    private static final int MAX_STEPS = 8;

    private final NoiseGeneratorOctaves noiseGen;
    private final long worldSeed;
    private final double[] buf = new double[1];

    public AetherBiomeNoise(long worldSeed) {
        this.worldSeed = worldSeed;
        // Replicate ChunkProviderAether's noiseGen1 exactly.
        // new NoiseGeneratorOctaves(rand, n) consumes exactly n nextLong() calls.
        Random r = new Random(worldSeed);
        this.noiseGen = new NoiseGeneratorOctaves(r, 16);
    }

    // -------------------------------------------------------------------------
    // Public API
    // -------------------------------------------------------------------------

    /** Returns the biome for the chunk at {@code (chunkX, chunkZ)}. */
    public BiomeGenBase getBiomeForChunk(int chunkX, int chunkZ) {
        int peakX = chunkX;
        int peakZ = chunkZ;
        double peakD = density(peakX, peakZ);

        for (int step = 0; step < MAX_STEPS; step++) {
            int bestX = peakX, bestZ = peakZ;
            double bestD = peakD;

            double d;
            d = density(peakX + 1, peakZ);
            if (d > bestD) {
                bestD = d;
                bestX = peakX + 1;
                bestZ = peakZ;
            }
            d = density(peakX - 1, peakZ);
            if (d > bestD) {
                bestD = d;
                bestX = peakX - 1;
                bestZ = peakZ;
            }
            d = density(peakX, peakZ + 1);
            if (d > bestD) {
                bestD = d;
                bestX = peakX;
                bestZ = peakZ + 1;
            }
            d = density(peakX, peakZ - 1);
            if (d > bestD) {
                bestD = d;
                bestX = peakX;
                bestZ = peakZ - 1;
            }

            if (bestX == peakX && bestZ == peakZ) break;
            peakX = bestX;
            peakZ = bestZ;
            peakD = bestD;
        }

        return selectBiome(hash(peakX, peakZ));
    }

    /** Returns the biome for the block at {@code (blockX, blockZ)}. */
    public BiomeGenBase getBiomeForBlock(int blockX, int blockZ) {
        return getBiomeForChunk(blockX >> 4, blockZ >> 4);
    }

    // -------------------------------------------------------------------------
    // Internals
    // -------------------------------------------------------------------------

    /**
     * Samples the terrain density noise at chunk position {@code (cx, cz)}.
     *
     * <p>
     * Coordinates are scaled by 2 to match {@code ChunkProviderAether}
     * ({@code setupNoiseGenerators} is called with {@code x = chunkX*2}).
     * {@code scaleX = scaleZ = 1} so the noise is evaluated at integer position
     * {@code cx*2} — adjacent chunks differ by exactly 2 noise units, giving
     * a correlation of ~0.9995 and a natural period of 128 chunks (2048 blocks).
     */
    private double density(int cx, int cz) {
        // scaleX=1, scaleZ=1: evaluate at integer coords cx*2, SAMPLE_Y, cz*2.
        // Do NOT pass D=1368 here — that was the bug in the previous attempt.
        noiseGen.generateNoiseOctaves(buf, cx * 2, SAMPLE_Y, cz * 2, 1, 1, 1, 1.0, 1.0, 1.0);
        return buf[0];
    }

    private static BiomeGenBase selectBiome(int hash) {
        BiomeGenBase[] biomes = AetherBiomesRegistry.BIOMES;
        return biomes[(hash & 0x7FFFFFFF) % biomes.length];
    }

    private int hash(int peakX, int peakZ) {
        long h = worldSeed;
        h ^= (long) peakX * 0x9e3779b97f4a7c15L;
        h ^= (long) peakZ * 0x6c62272e07bb0142L;
        h ^= h >>> 30;
        h *= 0xbf58476d1ce4e5b9L;
        h ^= h >>> 27;
        h *= 0x94d049bb133111ebL;
        h ^= h >>> 31;
        return (int) h;
    }
}
