package com.gildedgames.the_aether.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Central registry for every Aether biome.
 *
 * <p>
 * Add new biomes here — the biome noise resolver and chunk provider both read
 * from this class, so adding a biome here is the only registration step required
 * beyond creating the biome class and adding a config entry for its ID.
 *
 * <h3>Adding a new biome</h3>
 * <ol>
 * <li>Create a new class extending {@link AetherBiome}.</li>
 * <li>Add a config entry in {@link com.gildedgames.the_aether.AetherConfig}
 * for its biome ID.</li>
 * <li>Instantiate it in the {@code BIOMES} array below.</li>
 * </ol>
 *
 * <p>
 * The noise-based biome system in {@link com.gildedgames.the_aether.world.AetherBiomeNoise}
 * divides its output range equally among however many biomes are in {@code BIOMES},
 * so no other code needs changing when a biome is added.
 */
public final class AetherBiomesRegistry {

    /**
     * All registered Aether biomes in the order they are assigned by
     * {@link com.gildedgames.the_aether.world.AetherBiomeNoise}.
     * Index 0 occupies the lowest noise-value band, the last index the highest.
     */
    public static final BiomeGenBase[] BIOMES = new BiomeGenBase[5];

    /** The standard grassy highlands biome — noise band 0. */
    public static BiomeGenBase AETHER_HIGHLANDS;

    /** The arctic highlands biome — noise band 1. */
    public static BiomeGenBase AETHER_ARCTIC;

    public static BiomeGenBase AETHER_ENCHANTED_ISLAND;

    public static BiomeGenBase AETHER_PEAKS;

    public static BiomeGenBase AETHER_AERCLOUD_FIELDS;

    /**
     * Returns an array of the biome IDs for all registered Aether biomes.
     * Kept for source compatibility — delete {@code GenLayerAetherBiome} from
     * your project, as that file is superseded by {@link com.gildedgames.the_aether.world.AetherBiomeNoise}.
     */
    public static int[] getBiomeIds() {
        int[] ids = new int[BIOMES.length];
        for (int i = 0; i < BIOMES.length; i++) {
            ids[i] = BIOMES[i].biomeID;
        }
        return ids;
    }

    private AetherBiomesRegistry() {}

    /**
     * Called from {@link com.gildedgames.the_aether.world.AetherWorld#initialization()}
     * after {@code AetherConfig} has read biome IDs from the config file.
     */
    public static void register() {
        AETHER_HIGHLANDS = new AetherBiome();
        AETHER_ARCTIC = new AetherBiomeArctic();
        AETHER_ENCHANTED_ISLAND = new AetherBiomeEnchantedIsland();
        AETHER_PEAKS = new AetherBiomeAetherPeaks();
        AETHER_AERCLOUD_FIELDS = new AetherBiomeCloudyFields();

        BIOMES[0] = AETHER_HIGHLANDS;
        BIOMES[1] = AETHER_ARCTIC;
        BIOMES[2] = AETHER_ENCHANTED_ISLAND;
        BIOMES[3] = AETHER_PEAKS;
        BIOMES[4] = AETHER_AERCLOUD_FIELDS;
    }
}
