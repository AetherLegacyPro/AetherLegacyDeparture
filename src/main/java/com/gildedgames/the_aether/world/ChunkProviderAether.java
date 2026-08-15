package com.gildedgames.the_aether.world;

import com.gildedgames.the_aether.world.biome.*;
import com.gildedgames.the_aether.world.biome.decoration.*;
import cpw.mods.fml.common.Loader;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AetherCloudsGenNew;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenBerryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenBlackberryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenGrapeVines;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenOrangeTree;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenRaspberryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenStrawberryBush;
import com.gildedgames.the_aether.world.dungeon.BronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.CobaltDungeon;
import com.gildedgames.the_aether.world.dungeon.DivineBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.LargeBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.MythicBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.ZarnillysDen;
import com.gildedgames.the_aether.world.dungeon.util.AetherDungeon;
import com.gildedgames.the_aether.world.gen.MapGenAetherCaves;
import com.gildedgames.the_aether.world.gen.MapGenAncientGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenAncientSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenLargeColdAercloud;
import com.gildedgames.the_aether.world.gen.MapGenQuicksoil;
import com.gildedgames.the_aether.world.gen.MapGenSilverDungeon;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderAether implements IChunkProvider {

    private static final int WORLD_HEIGHT = 256;
    private static final int NORMAL_TERRAIN_LOW_Y = 92;
    private static final int NORMAL_TERRAIN_COMMON_Y = 108;
    private static final int NORMAL_TERRAIN_HIGH_Y = 120;
    private static final int NORMAL_TERRAIN_RARE_Y = 128;

    private static final int PEAKS_TERRAIN_MAX_Y = 240;

    private static final int PEAKS_EDGE_BLEND_DISTANCE = 128;
    private static final int PEAKS_OUTER_FOOTHILL_DISTANCE = 64;
    private static final int PEAKS_EDGE_SAMPLE_STEP = 2;
    private static final int PEAKS_EDGE_MIN_Y = 96;
    private static final int PEAKS_FOOTHILL_MAX_Y = 112;

    private static final int QUICKSOIL_DUNES_LOW_Y = 78;
    private static final int QUICKSOIL_DUNES_COMMON_Y = 88;
    private static final int QUICKSOIL_DUNES_HILL_Y = 102;
    private static final int QUICKSOIL_DUNES_RARE_HILL_Y = 116;

    private static final int AERCLOUD_FIELDS_LOW_Y = 72;
    private static final int AERCLOUD_FIELDS_COMMON_Y = 82;
    private static final int AERCLOUD_FIELDS_HILL_Y = 92;
    private static final int AERCLOUD_FIELDS_RARE_HILL_Y = 102;

    private static final int AERCLOUD_FIELDS_PULL_DISTANCE = 96;
    private static final int AERCLOUD_FIELDS_PULL_SAMPLE_STEP = 2;
    private static final int AERCLOUD_FIELDS_PULL_EXTRA_Y = 4;

    private static final int STORMY_SKIES_LOW_Y = 82;
    private static final int STORMY_SKIES_COMMON_Y = 96;
    private static final int STORMY_SKIES_HILL_Y = 112;
    private static final int STORMY_SKIES_RARE_HILL_Y = 124;

    private static final int NOISE_Y_SIZE = WORLD_HEIGHT / 4 + 1;

    private int getBlockIndex(int localX, int y, int localZ) {
        return (localX * 16 + localZ) * WORLD_HEIGHT + y;
    }

    private Random rand;
    public static int placementFlagType;
    private World worldObj;
    private World aetherWorld;
    private BiomeGenBase[] biomesForGeneration;
    private NoiseGeneratorOctaves noiseGen1, perlinNoise1;
    private double[] buffer;
    double[] pnr, ar, br;

    private MapGenBase aetherCaveGenerator;

    protected AetherDungeon dungeon_bronze = new BronzeDungeon();
    protected AetherDungeon large_dungeon_bronze = new LargeBronzeDungeon();
    protected AetherDungeon divine_dungeon_bronze = new DivineBronzeDungeon();
    protected AetherDungeon mythic_dungeon_bronze = new MythicBronzeDungeon();

    protected AetherDungeon cobalt_dungeon = new CobaltDungeon();
    protected AetherDungeon zarnillys_den = new ZarnillysDen();
    private MapGenQuicksoil quicksoilGen = new MapGenQuicksoil();
    private MapGenAetherCaves aether_caves = new MapGenAetherCaves();

    private MapGenSilverDungeon silverDungeonStructure = new MapGenSilverDungeon();
    private MapGenAncientSilverDungeon ancientsilverDungeonStructure = new MapGenAncientSilverDungeon();
    private MapGenDivineSilverDungeon divinesilverDungeonStructure = new MapGenDivineSilverDungeon();

    private MapGenGoldenDungeon goldenDungeonStructure = new MapGenGoldenDungeon();
    private MapGenAncientGoldenDungeon ancientGoldenDungeonStructure = new MapGenAncientGoldenDungeon();
    private MapGenDivineGoldenDungeon divineGoldenDungeonStructure = new MapGenDivineGoldenDungeon();

    private MapGenLargeColdAercloud largeColdAercloudStructure = new MapGenLargeColdAercloud();
    public AetherGenDungeonOakTree golden_oak_tree_dungeon = new AetherGenDungeonOakTree();
    public AetherGenFloatingIsland crystal_island = new AetherGenFloatingIsland();
    public AetherGenVoidFloatingIsland void_island = new AetherGenVoidFloatingIsland();
    public AetherGenHolidayTree holiday_tree = new AetherGenHolidayTree();

    private boolean isEndlessIdsLoaded() {
        return Loader.isModLoaded("endlessids") || Loader.isModLoaded("EndlessIDs") || Loader.isModLoaded("endlessIds");
    }

    //Endless IDS compat
    private void populateChunkBiomeDataSafely(Chunk chunk) {
        if (this.isEndlessIdsLoaded()) {
            for (int localZ = 0; localZ < 16; localZ++) {
                for (int localX = 0; localX < 16; localX++) {
                    chunk.getBiomeGenForWorldCoords(localX, localZ, this.worldObj.getWorldChunkManager());
                }
            }

            return;
        }

        byte[] biomeArray = chunk.getBiomeArray();
        for (int localZ = 0; localZ < 16; localZ++) {
            for (int localX = 0; localX < 16; localX++) {
                int index = localX + localZ * 16;

                BiomeGenBase biome = this.biomesForGeneration[index];
                if (biome != null) {
                    biomeArray[index] = (byte)(biome.biomeID & 255);
                }
            }
        }
    }

    public ChunkProviderAether(World world, long seed) {
        this.worldObj = world;
        this.aetherCaveGenerator = new MapGenAetherCaves();
        this.rand = new Random(seed);
        this.aetherWorld = world;
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
    }

    public void setBlocksInChunk(int x, int z, Block[] blocks, double[] terrainFactors, BiomeGenBase[] biomes, int[] maxTerrainYByColumn) {
        this.buffer = this.setupNoiseGenerators(this.buffer, x * 2, z * 2);

        for (int i1 = 0; i1 < 2; i1++) {
            for (int j1 = 0; j1 < 2; j1++) {
                for (int k1 = 0; k1 < WORLD_HEIGHT / 4; k1++) {
                    double d1 = this.buffer[(i1 * 3 + j1) * NOISE_Y_SIZE + k1];
                    double d2 = this.buffer[(i1 * 3 + (j1 + 1)) * NOISE_Y_SIZE + k1];
                    double d3 = this.buffer[((i1 + 1) * 3 + j1) * NOISE_Y_SIZE + k1];
                    double d4 = this.buffer[((i1 + 1) * 3 + (j1 + 1)) * NOISE_Y_SIZE + k1];

                    double d5 = (this.buffer[(i1 * 3 + j1) * NOISE_Y_SIZE + (k1 + 1)] - d1) * 0.25D;
                    double d6 = (this.buffer[(i1 * 3 + (j1 + 1)) * NOISE_Y_SIZE + (k1 + 1)] - d2) * 0.25D;
                    double d7 = (this.buffer[((i1 + 1) * 3 + j1) * NOISE_Y_SIZE + (k1 + 1)] - d3) * 0.25D;
                    double d8 = (this.buffer[((i1 + 1) * 3 + (j1 + 1)) * NOISE_Y_SIZE + (k1 + 1)] - d4) * 0.25D;

                    for (int l1 = 0; l1 < 4; l1++) {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for (int i2 = 0; i2 < 8; i2++) {
                            int localXStart = i2 + i1 * 8;
                            int localZStart = j1 * 8;
                            int yStart = k1 * 4 + l1;

                            int j2 = this.getBlockIndex(localXStart, yStart, localZStart);
                            int c = WORLD_HEIGHT;

                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for (int k2 = 0; k2 < 8; k2++) {
                                int localX = i2 + i1 * 8;
                                int localZ = j1 * 8 + k2;
                                int y = k1 * 4 + l1;

                                int columnIndex = localX + localZ * 16;

                                BiomeGenBase biome = biomes[columnIndex];
                                double terrainFactor = terrainFactors[columnIndex];
                                int maxTerrainY = maxTerrainYByColumn[columnIndex];

                                Block filler = Blocks.air;

                                if (y <= maxTerrainY && terrainFactor > 0.02D) {
                                    double density = d15;

                                    double fadeAmount = 1.0D - terrainFactor;
                                    fadeAmount = Math.pow(fadeAmount, 1.45D);
                                    density -= fadeAmount * 10.0D;

                                    int topFadeDistance;
                                    double topFadeStrength;

                                    if (this.isHighTerrainBiome(biome)) {
                                        double peakT = (double)(maxTerrainY - PEAKS_EDGE_MIN_Y) / (double)(PEAKS_TERRAIN_MAX_Y - PEAKS_EDGE_MIN_Y);

                                        if (peakT < 0.0D) {
                                            peakT = 0.0D;
                                        }

                                        if (peakT > 1.0D) {
                                            peakT = 1.0D;
                                        }

                                        peakT = this.smootherstep(peakT);

                                        topFadeDistance = this.lerpInt(40, 72, peakT);
                                        topFadeStrength = this.lerp(24.0D, 10.0D, peakT);
                                    } else if (this.isAercloudFieldsBiome(biome)) {
                                        topFadeDistance = 40;
                                        topFadeStrength = 36.0D;
                                    } else if (this.isStormySkiesBiome(biome)) {
                                        topFadeDistance = 44;
                                        topFadeStrength = 30.0D;
                                    } else if (this.isQuicksoilDunesBiome(biome)) {
                                        topFadeDistance = 36;
                                        topFadeStrength = 34.0D;
                                    } else {
                                        if (maxTerrainY > NORMAL_TERRAIN_RARE_Y) {
                                            topFadeDistance = 56;
                                            topFadeStrength = 22.0D;
                                        } else {
                                            topFadeDistance = 48;
                                            topFadeStrength = 30.0D;
                                        }
                                    }

                                    int fadeTopStart = maxTerrainY - topFadeDistance;
                                    if (y > fadeTopStart) {
                                        double topFade = (double) (y - fadeTopStart) / (double) topFadeDistance;

                                        if (topFade > 1.0D) {
                                            topFade = 1.0D;
                                        }

                                        topFade = topFade * topFade * (3.0D - 2.0D * topFade);

                                        density -= topFade * topFadeStrength;
                                    }

                                    if (density > 0.0D) {
                                        filler = this.getBaseTerrainBlockForBiome(biome);
                                    }
                                }

                                blocks[j2] = filler;
                                j2 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void buildSurfaces(int chunkX, int chunkZ, Block[] blocks, BiomeGenBase[] biomes) {
        this.rand.setSeed(this.worldObj.getSeed());

        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                int depth = -1;
                int surfaceDepth = (int) (3.0D + this.rand.nextDouble() * 0.5D);

                BiomeGenBase biome = biomes[localX + localZ * 16];

                Block top = BlocksAether.aether_grass;
                Block filler = BlocksAether.aether_dirt;

                if (biome == AetherWorld.arctic_biome) {
                    top = BlocksAether.arctic_grass;
                    filler = BlocksAether.holystone;
                } else if (biome == AetherWorld.aercloud_fields) {
                    top = BlocksAether.aercloud;
                    filler = BlocksAether.aercloud;
                } else if (biome == AetherWorld.aether_biome || biome == AetherWorld.aether_peaks || biome == AetherWorld.aether_forest) {
                    top = BlocksAether.aether_grass;
                    filler = BlocksAether.aether_dirt;
                } else if (biome == AetherWorld.enchanted_island) {
                    top = BlocksAether.enchanted_aether_grass;
                    filler = BlocksAether.aether_dirt;
                } else if (biome == AetherWorld.divine_island) {
                    top = BlocksAether.divine_grass;
                    filler = BlocksAether.aether_dirt;
                } else if (biome == AetherWorld.quicksoil_dunes) {
                    top = BlocksAether.quicksoil;
                    filler = BlocksAether.quicksoil;
                } else if (biome == AetherWorld.stormy_skies) {
                    top = BlocksAether.aether_grass;
                    filler = BlocksAether.aether_dirt;
                }
               // if (biome != AetherWorld.aercloud_fields) {
                    for (int y = WORLD_HEIGHT - 1; y >= 0; y--) {
                        int index = this.getBlockIndex(localX, y, localZ);
                        Block block = blocks[index];

                        if (block == Blocks.air) {
                            depth = -1;
                        } else if (block == BlocksAether.holystone || block == BlocksAether.stratos_aercloud) {
                            if (depth == -1) {
                                depth = surfaceDepth;
                                blocks[index] = top;
                            } else if (depth > 0) {
                                --depth;
                                blocks[index] = filler;
                            }
                        }
                    }
               //}
            }
        }
    }

    private Block getBaseTerrainBlockForBiome(BiomeGenBase biome) {
        if (this.isAercloudFieldsBiome(biome)) {
            return BlocksAether.stratos_aercloud;
        }

        return BlocksAether.holystone;
    }

    private double[] setupNoiseGenerators(double[] buffer, int x, int z) {
        int expectedSize = 3 * NOISE_Y_SIZE * 3;

        if (buffer == null || buffer.length < expectedSize) {
            buffer = new double[expectedSize];
        }

        double d = 1368.824D;
        double d1 = 684.41200000000003D;

        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, x, 0, z, 3, NOISE_Y_SIZE, 3, d / 80D, d1 / 160D, d / 80D);
        this.ar = this.noiseGen1.generateNoiseOctaves(this.ar, x, 0, z, 3, NOISE_Y_SIZE, 3, d, d1, d);
        this.br = this.noiseGen1.generateNoiseOctaves(this.br, x, 0, z, 3, NOISE_Y_SIZE, 3, d, d1, d);

        int id = 0;

        for (int noiseX = 0; noiseX < 3; noiseX++) {
            for (int noiseZ = 0; noiseZ < 3; noiseZ++) {
                for (int noiseY = 0; noiseY < NOISE_Y_SIZE; noiseY++) {
                    double d8;

                    double d10 = this.ar[id] / 768D;
                    double d11 = this.br[id] / 768D;
                    double d12 = (this.pnr[id] / 10D + 1.0D) / 2D;

                    if (d12 < 0.0D) {
                        d8 = d10;
                    } else if (d12 > 1.0D) {
                        d8 = d11;
                    } else {
                        d8 = d10 + (d11 - d10) * d12;
                    }

                    d8 -= 10D;

                    int topFadeStart = NOISE_Y_SIZE - 24;
                    if (noiseY > topFadeStart) {
                        double fade = (double) (noiseY - topFadeStart) / 23.0D;

                        if (fade > 1.0D) {
                            fade = 1.0D;
                        }

                        d8 = d8 * (1.0D - fade) + -22D * fade;
                    }

                    if (noiseY < 8) {
                        double bottomFade = (double) (8 - noiseY) / 7.0D;

                        if (bottomFade > 1.0D) {
                            bottomFade = 1.0D;
                        }

                        d8 = d8 * (1.0D - bottomFade) + -22D * bottomFade;
                    }

                    buffer[id] = d8;

                    id++;
                }
            }
        }

        return buffer;
    }

    private boolean isStormySkiesBiome(BiomeGenBase biome) {
        return biome != null
            && AetherWorld.stormy_skies != null
            && biome.biomeID == AetherWorld.stormy_skies.biomeID;
    }

    private boolean isQuicksoilDunesBiome(BiomeGenBase biome) {
        return biome != null
            && AetherWorld.quicksoil_dunes != null
            && biome.biomeID == AetherWorld.quicksoil_dunes.biomeID;
    }

    private int getQuicksoilDunesMaxTerrainY(int worldX, int worldZ) {
        double broad = this.regionalNoise01(worldX, worldZ, 256, 8101L);
        double medium = this.regionalNoise01(worldX + 44119, worldZ - 19231, 128, 8102L);
        double small = this.regionalNoise01(worldX - 9917, worldZ + 55123, 64, 8103L);

        double n = broad * 0.65D + medium * 0.25D + small * 0.10D;

        n = this.smootherstep(n);

        if (n < 0.72D) {
            double t = n / 0.72D;
            return this.lerpInt(QUICKSOIL_DUNES_LOW_Y, QUICKSOIL_DUNES_COMMON_Y, t);
        }

        if (n < 0.94D) {
            double t = (n - 0.72D) / (0.94D - 0.72D);
            return this.lerpInt(QUICKSOIL_DUNES_COMMON_Y, QUICKSOIL_DUNES_HILL_Y, t);
        }

        double t = (n - 0.94D) / (1.0D - 0.94D);
        return this.lerpInt(QUICKSOIL_DUNES_HILL_Y, QUICKSOIL_DUNES_RARE_HILL_Y, t);
    }

    private int getStormySkiesMaxTerrainY(int worldX, int worldZ) {
        /*
         * Hybrid between regular Aether and Aercloud Fields.
         * Lower and smoother than normal terrain, but not totally flat.
         */
        double broad = this.regionalNoise01(worldX, worldZ, 256, 10101L);
        double medium = this.regionalNoise01(worldX + 33119, worldZ - 17731, 128, 10102L);
        double small = this.regionalNoise01(worldX - 12117, worldZ + 44123, 80, 10103L);

        double n = broad * 0.68D + medium * 0.24D + small * 0.08D;

        n = this.smootherstep(n);

        if (n < 0.72D) {
            double t = n / 0.72D;
            return this.lerpInt(STORMY_SKIES_LOW_Y, STORMY_SKIES_COMMON_Y, t);
        }

        if (n < 0.94D) {
            double t = (n - 0.72D) / (0.94D - 0.72D);
            return this.lerpInt(STORMY_SKIES_COMMON_Y, STORMY_SKIES_HILL_Y, t);
        }

        double t = (n - 0.94D) / (1.0D - 0.94D);
        return this.lerpInt(STORMY_SKIES_HILL_Y, STORMY_SKIES_RARE_HILL_Y, t);
    }

    private int getAercloudFieldsMaxTerrainY(int worldX, int worldZ) {
        double broad = this.regionalNoise01(worldX, worldZ, 288, 9101L);
        double medium = this.regionalNoise01(worldX + 55119, worldZ - 28231, 144, 9102L);
        double small = this.regionalNoise01(worldX - 19917, worldZ + 65123, 80, 9103L);

        double n = broad * 0.70D + medium * 0.22D + small * 0.08D;

        n = this.smootherstep(n);

        if (n < 0.76D) {
            double t = n / 0.76D;
            return this.lerpInt(AERCLOUD_FIELDS_LOW_Y, AERCLOUD_FIELDS_COMMON_Y, t);
        }

        if (n < 0.95D) {
            double t = (n - 0.76D) / (0.95D - 0.76D);
            return this.lerpInt(AERCLOUD_FIELDS_COMMON_Y, AERCLOUD_FIELDS_HILL_Y, t);
        }

        double t = (n - 0.95D) / (1.0D - 0.95D);
        return this.lerpInt(AERCLOUD_FIELDS_HILL_Y, AERCLOUD_FIELDS_RARE_HILL_Y, t);
    }

    private boolean isAetherPeaksBiome(BiomeGenBase biome) {
        return biome != null
            && AetherWorld.aether_peaks != null
            && biome.biomeID == AetherWorld.aether_peaks.biomeID;
    }

    private boolean isDivineIslandBiome(BiomeGenBase biome) {
        return biome != null
            && AetherWorld.divine_island != null
            && biome.biomeID == AetherWorld.divine_island.biomeID;
    }

    private boolean isHighTerrainBiome(BiomeGenBase biome) {
        return this.isAetherPeaksBiome(biome) || this.isDivineIslandBiome(biome);
    }

    private int getNormalMaxTerrainY(int worldX, int worldZ) {
        double n = this.regionalHeightNoise01(worldX, worldZ);

        if (n < 0.72D) {
            double t = n / 0.72D;
            return this.lerpInt(NORMAL_TERRAIN_LOW_Y, NORMAL_TERRAIN_COMMON_Y, t);
        }

        if (n < 0.94D) {
            double t = (n - 0.72D) / (0.94D - 0.72D);
            return this.lerpInt(NORMAL_TERRAIN_COMMON_Y, NORMAL_TERRAIN_HIGH_Y, t);
        }

        double t = (n - 0.94D) / (1.0D - 0.94D);
        return this.lerpInt(NORMAL_TERRAIN_HIGH_Y, NORMAL_TERRAIN_RARE_Y, t);
    }

    private int lerpInt(int a, int b, double t) {
        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        return (int) Math.round(a + (b - a) * t);
    }

    private double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    private double smoothstep(double t) {
        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        return t * t * (3.0D - 2.0D * t);
    }

    private int floorDivInt(int x, int y) {
        int r = x / y;

        if ((x ^ y) < 0 && r * y != x) {
            r--;
        }

        return r;
    }

    private int floorModInt(int x, int y) {
        return x - this.floorDivInt(x, y) * y;
    }

    private double hashCellNoise01(int cellX, int cellZ, long salt) {
        long seed = this.worldObj.getSeed();

        long value = seed;
        value ^= (long) cellX * 341873128712L;
        value ^= (long) cellZ * 132897987541L;
        value ^= salt * 42317861L;
        value ^= value >> 13;
        value *= 1274126177L;
        value ^= value >> 16;

        return (double) (value & 16777215L) / 16777215.0D;
    }

    private double regionalNoise01(int worldX, int worldZ, int cellSize, long salt) {
        int cellX = this.floorDivInt(worldX, cellSize);
        int cellZ = this.floorDivInt(worldZ, cellSize);

        double localX = (double) this.floorModInt(worldX, cellSize) / (double) cellSize;
        double localZ = (double) this.floorModInt(worldZ, cellSize) / (double) cellSize;

        double sx = this.smoothstep(localX);
        double sz = this.smoothstep(localZ);

        double v00 = this.hashCellNoise01(cellX, cellZ, salt);
        double v10 = this.hashCellNoise01(cellX + 1, cellZ, salt);
        double v01 = this.hashCellNoise01(cellX, cellZ + 1, salt);
        double v11 = this.hashCellNoise01(cellX + 1, cellZ + 1, salt);

        double x0 = this.lerp(v00, v10, sx);
        double x1 = this.lerp(v01, v11, sx);

        return this.lerp(x0, x1, sz);
    }

    private double regionalHeightNoise01(int worldX, int worldZ) {
        double broad = this.regionalNoise01(worldX, worldZ, 192, 1001L);
        double medium = this.regionalNoise01(worldX + 91423, worldZ - 43117, 96, 2002L);
        double huge = this.regionalNoise01(worldX - 28177, worldZ + 77131, 384, 3003L);
        double n = broad * 0.60D + medium * 0.25D + huge * 0.15D;

        if (n < 0.0D) {
            n = 0.0D;
        }

        if (n > 1.0D) {
            n = 1.0D;
        }

        return n;
    }


    private boolean isAercloudFieldsBiome(BiomeGenBase biome) {
        return biome != null && AetherWorld.aercloud_fields != null
            && biome.biomeID == AetherWorld.aercloud_fields.biomeID;
    }

    private double[] makeAercloudTerrainFactorsForChunk(int chunkX, int chunkZ) {
        double[] factors = new double[256];

        for (int i = 0; i < factors.length; i++) {
            factors[i] = 1.0D;
        }

        return factors;
    }

    private double smootherstep(double t) {
        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        return t * t * t * (t * (t * 6.0D - 15.0D) + 10.0D);
    }

    private boolean chunkHasNonAercloudFieldsBiome(BiomeGenBase[] biomes) {
        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                BiomeGenBase biome = biomes[localX + localZ * 16];

                if (!this.isAercloudFieldsBiome(biome)) {
                    return true;
                }
            }
        }

        return false;
    }

    private int[] makeMaxTerrainYForChunk(int chunkX, int chunkZ, BiomeGenBase[] biomes) {
        int[] result = new int[256];

        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;

        int radius = Math.max(Math.max(PEAKS_EDGE_BLEND_DISTANCE, PEAKS_OUTER_FOOTHILL_DISTANCE), AERCLOUD_FIELDS_PULL_DISTANCE);
        int sampleStep = PEAKS_EDGE_SAMPLE_STEP;

        int sampleMinX = worldBaseX - radius;
        int sampleMinZ = worldBaseZ - radius;
        int sampleSize = 16 + radius * 2;

        BiomeGenBase[] nearbyBiomes = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(null, sampleMinX, sampleMinZ, sampleSize, sampleSize);

        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                int index = localX + localZ * 16;

                int worldX = worldBaseX + localX;
                int worldZ = worldBaseZ + localZ;

                BiomeGenBase biome = biomes[index];
                int height;

                if (this.isAercloudFieldsBiome(biome)) {
                    result[index] = this.getAercloudFieldsMaxTerrainY(worldX, worldZ);
                    continue;
                }

                if (this.isStormySkiesBiome(biome)) {
                    height = this.getStormySkiesMaxTerrainY(worldX, worldZ);
                } else if (this.isQuicksoilDunesBiome(biome)) {
                    height = this.getQuicksoilDunesMaxTerrainY(worldX, worldZ);
                } else {
                    int normalHeight = this.getNormalMaxTerrainY(worldX, worldZ);

                    if (this.isHighTerrainBiome(biome)) {
                        int distanceToNonHighTerrain = this.getApproxDistanceToHighTerrainBiomeTypeFromArray(
                            worldX,
                            worldZ,
                            sampleMinX,
                            sampleMinZ,
                            sampleSize,
                            nearbyBiomes,
                            radius,
                            sampleStep,
                            false
                        );

                        height = this.getHighTerrainMaxYFromDistance(
                            worldX,
                            worldZ,
                            normalHeight,
                            distanceToNonHighTerrain
                        );
                    } else {
                        int distanceToHighTerrain = this.getApproxDistanceToHighTerrainBiomeTypeFromArray(
                            worldX,
                            worldZ,
                            sampleMinX,
                            sampleMinZ,
                            sampleSize,
                            nearbyBiomes,
                            radius,
                            sampleStep,
                            true
                        );

                        height = this.getNormalMaxTerrainYNearHighTerrain(
                            worldX,
                            worldZ,
                            normalHeight,
                            distanceToHighTerrain
                        );
                    }
                }

                /*
                 * Pull non-Aercloud terrain down near Aercloud Fields.
                 */
                int distanceToAercloudFields = this.getApproxDistanceToAercloudFieldsFromArray(
                    worldX,
                    worldZ,
                    sampleMinX,
                    sampleMinZ,
                    sampleSize,
                    nearbyBiomes,
                    AERCLOUD_FIELDS_PULL_DISTANCE,
                    AERCLOUD_FIELDS_PULL_SAMPLE_STEP
                );

                height = this.applyAercloudFieldsPullDown(
                    worldX,
                    worldZ,
                    height,
                    distanceToAercloudFields
                );

                result[index] = height;
            }
        }

        return result;
    }

    private int getApproxDistanceToAercloudFieldsFromArray(
        int worldX,
        int worldZ,
        int sampleMinX,
        int sampleMinZ,
        int sampleSize,
        BiomeGenBase[] nearbyBiomes,
        int maxRadius,
        int step
    ) {
        int localCenterX = worldX - sampleMinX;
        int localCenterZ = worldZ - sampleMinZ;

        int bestDistanceSq = maxRadius * maxRadius + 1;

        for (int dz = -maxRadius; dz <= maxRadius; dz += step) {
            int sampleZ = localCenterZ + dz;

            if (sampleZ < 0 || sampleZ >= sampleSize) {
                continue;
            }

            for (int dx = -maxRadius; dx <= maxRadius; dx += step) {
                int sampleX = localCenterX + dx;

                if (sampleX < 0 || sampleX >= sampleSize) {
                    continue;
                }

                int distanceSq = dx * dx + dz * dz;

                if (distanceSq >= bestDistanceSq) {
                    continue;
                }

                BiomeGenBase sampleBiome = nearbyBiomes[sampleX + sampleZ * sampleSize];

                if (this.isAercloudFieldsBiome(sampleBiome)) {
                    bestDistanceSq = distanceSq;
                }
            }
        }

        if (bestDistanceSq > maxRadius * maxRadius) {
            return maxRadius + 1;
        }

        return (int)Math.sqrt((double)bestDistanceSq);
    }

    private int applyAercloudFieldsPullDown(
        int worldX,
        int worldZ,
        int currentHeight,
        int distanceToAercloudFields
    ) {
        if (distanceToAercloudFields > AERCLOUD_FIELDS_PULL_DISTANCE) {
            return currentHeight;
        }

        /*
         * t = 1 near Aercloud Fields
         * t = 0 far away
         */
        double t = 1.0D - ((double)distanceToAercloudFields / (double)AERCLOUD_FIELDS_PULL_DISTANCE);

        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        t = this.smootherstep(t);

        /*
         * Target height near Aercloud Fields.
         * Uses the Aercloud Fields height profile so the transition matches.
         */
        int aercloudHeight = this.getAercloudFieldsMaxTerrainY(worldX, worldZ);
        int targetHeight = aercloudHeight + AERCLOUD_FIELDS_PULL_EXTRA_Y;

        /*
         * Never raise terrain here; only pull down.
         */
        if (currentHeight <= targetHeight) {
            return currentHeight;
        }

        /*
         * Pull most strongly near Aercloud Fields.
         */
        int pulledHeight = this.lerpInt(currentHeight, targetHeight, t);

        if (pulledHeight > currentHeight) {
            pulledHeight = currentHeight;
        }

        return pulledHeight;
    }

    private int getAetherPeaksMaxTerrainYFromDistance(int worldX, int worldZ, int normalHeight, int distanceToEdge) {
        int peakInteriorHeight = this.getAetherPeaksInteriorHeight(worldX, worldZ);
        double t = (double) distanceToEdge / (double) PEAKS_EDGE_BLEND_DISTANCE;

        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        t = this.smootherstep(t);

        int edgeStartY = Math.max(PEAKS_EDGE_MIN_Y, normalHeight + 8);
        double edgeNoise = this.regionalNoise01(worldX + 33117, worldZ - 7713, 128, 9191L);
        edgeStartY += (int) Math.round((edgeNoise - 0.5D) * 8.0D);

        int y = this.lerpInt(edgeStartY, peakInteriorHeight, t);

        if (y < edgeStartY) {
            y = edgeStartY;
        }

        if (y > PEAKS_TERRAIN_MAX_Y) {
            y = PEAKS_TERRAIN_MAX_Y;
        }

        return y;
    }

    private int getHighTerrainMaxYFromDistance(int worldX, int worldZ, int normalHeight, int distanceToEdge) {
        return this.getAetherPeaksMaxTerrainYFromDistance(worldX, worldZ, normalHeight, distanceToEdge);
    }

    private int getApproxDistanceToHighTerrainBiomeTypeFromArray(int worldX, int worldZ, int sampleMinX, int sampleMinZ, int sampleSize, BiomeGenBase[] nearbyBiomes, int maxRadius, int step, boolean lookingForHighTerrain) {
        int localCenterX = worldX - sampleMinX;
        int localCenterZ = worldZ - sampleMinZ;
        int bestDistanceSq = maxRadius * maxRadius + 1;

        for (int dz = -maxRadius; dz <= maxRadius; dz += step) {
            int sampleZ = localCenterZ + dz;

            if (sampleZ < 0 || sampleZ >= sampleSize) {
                continue;
            }

            for (int dx = -maxRadius; dx <= maxRadius; dx += step) {
                int sampleX = localCenterX + dx;

                if (sampleX < 0 || sampleX >= sampleSize) {
                    continue;
                }

                int distanceSq = dx * dx + dz * dz;

                if (distanceSq >= bestDistanceSq) {
                    continue;
                }

                BiomeGenBase sampleBiome = nearbyBiomes[sampleX + sampleZ * sampleSize];
                boolean sampleIsHighTerrain = this.isHighTerrainBiome(sampleBiome);

                if (sampleIsHighTerrain == lookingForHighTerrain) {
                    bestDistanceSq = distanceSq;
                }
            }
        }

        if (bestDistanceSq > maxRadius * maxRadius) {
            return maxRadius + 1;
        }

        return (int)Math.sqrt((double)bestDistanceSq);
    }

    private int getNormalMaxTerrainYNearHighTerrain(int worldX, int worldZ, int normalHeight, int distanceToHighTerrain) {
        return this.getNormalMaxTerrainYNearPeaks(worldX, worldZ, normalHeight, distanceToHighTerrain);
    }

    private int getAetherPeaksInteriorHeight(int worldX, int worldZ) {
        double broad = this.regionalNoise01(worldX, worldZ, 192, 4411L);
        double huge = this.regionalNoise01(worldX - 88231, worldZ + 19211, 384, 5512L);
        double medium = this.regionalNoise01(worldX + 3319, worldZ - 7751, 96, 6613L);

        double n = broad * 0.55D + huge * 0.30D + medium * 0.15D;
        n = this.smootherstep(n);

        return this.lerpInt(184, PEAKS_TERRAIN_MAX_Y, n);
    }

    private int getNormalMaxTerrainYNearPeaks(int worldX, int worldZ, int normalHeight, int distanceToPeaks) {
        if (distanceToPeaks > PEAKS_OUTER_FOOTHILL_DISTANCE) {
            return normalHeight;
        }

        double t = 1.0D - ((double) distanceToPeaks / (double) PEAKS_OUTER_FOOTHILL_DISTANCE);

        if (t < 0.0D) {
            t = 0.0D;
        }

        if (t > 1.0D) {
            t = 1.0D;
        }

        t = this.smootherstep(t);

        double n = this.regionalNoise01(worldX + 11913, worldZ - 44291, 96, 7717L);

        int foothillTarget = PEAKS_FOOTHILL_MAX_Y + (int) Math.round((n - 0.5D) * 12.0D);
        if (foothillTarget < normalHeight) {
            foothillTarget = normalHeight;
        }

        return this.lerpInt(normalHeight, foothillTarget, t);
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);

        Block[] ablock = new Block[16 * 16 * WORLD_HEIGHT];
        final byte[] metadata = new byte[16 * 16 * WORLD_HEIGHT];

        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);

        double[] terrainFactors = this.makeAercloudTerrainFactorsForChunk(x, z);
        int[] maxTerrainYByColumn = this.makeMaxTerrainYForChunk(x, z, this.biomesForGeneration);
        this.setBlocksInChunk(x, z, ablock, terrainFactors, this.biomesForGeneration, maxTerrainYByColumn);
        this.buildSurfaces(x, z, ablock, this.biomesForGeneration);

        if (this.chunkHasNonAercloudFieldsBiome(this.biomesForGeneration)) {
            ((MapGenAetherCaves) this.aetherCaveGenerator).generate(this, this.aetherWorld, x, z, ablock, metadata);
        }

        this.quicksoilGen.func_151539_a(this, this.worldObj, x, z, ablock);
        this.largeColdAercloudStructure.func_151539_a(this, this.worldObj, x, z, ablock);

        if (AetherConfig.silver_dungeon_enable) {
            this.silverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        if (AetherConfig.tier2_silver_dungeon_enable) {
            this.ancientsilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        if (AetherConfig.tier3_silver_dungeon_enable) {
            this.divinesilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        if (AetherConfig.gold_dungeon_enable) {
            this.goldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        if (AetherConfig.tier2_gold_dungeon_enable) {
            this.ancientGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        if (AetherConfig.tier3_gold_dungeon_enable) {
            this.divineGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
        }

        Chunk chunk = new Chunk(this.worldObj, ablock, metadata, x, z);
        this.populateChunkBiomeDataSafely(chunk);

        chunk.generateSkylightMap();

        return chunk;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        return this.worldObj.getBiomeGenForCoords(x, z).getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(int x, int z) {
        this.largeColdAercloudStructure.func_151539_a(this, this.worldObj, x, z, null);

        this.aether_caves.func_151539_a(this, this.worldObj, x, z, null);

        if (AetherConfig.silver_dungeon_enable) {
            this.silverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

        if (AetherConfig.tier2_silver_dungeon_enable) {
            this.ancientsilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

        if (AetherConfig.tier3_silver_dungeon_enable) {
            this.divinesilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

        if (AetherConfig.gold_dungeon_enable) {
            this.goldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

        if (AetherConfig.tier2_gold_dungeon_enable) {
            this.ancientGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

        if (AetherConfig.tier3_gold_dungeon_enable) {
            this.divineGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
        }

    }

    @Override
    public ChunkPosition func_147416_a(World worldIn, String structureName, int x, int y, int z) {
        return null;
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + 8, z + 8);

        this.rand.setSeed(this.worldObj.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) x * k + (long) z * l ^ this.worldObj.getSeed());

        this.largeColdAercloudStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        biome.decorate(this.worldObj, this.rand, x, z);

        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biome, x + 8, z + 8, 16, 16, this.rand);
        final BiomeGenBase biomegenbase = biome;

        if (biomegenbase instanceof AetherBiome) {
            for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiome) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeAetherForest) {
            for (int numberoftreegen = 39, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeAetherForest) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeArctic) {
            for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeArctic) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeEnchantedIsland) {
            for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeEnchantedIsland) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeAetherPeaks) {
            for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeAetherPeaks) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeDivineIsland) {
            for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeDivineIsland) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (biomegenbase instanceof AetherBiomeStormySkies) {
            for (int numberoftreegen = 4, i2 = 0; i2 < numberoftreegen; ++i2) {
                final int k2 = x + this.rand.nextInt(8) + 8;
                final int j2 = z + this.rand.nextInt(8) + 8;
                final WorldGenerator worldgenerator = ((AetherBiomeStormySkies) biomegenbase).getRandomTreeFeature(this.rand);
                worldgenerator.setScale(1.0, 1.0, 1.0);
                worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
            }
        }

        if (AetherConfig.silver_dungeon_enable) {
            this.silverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        if (AetherConfig.tier2_silver_dungeon_enable) {
            this.ancientsilverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        if (AetherConfig.tier3_silver_dungeon_enable) {
            this.divinesilverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        if (AetherConfig.gold_dungeon_enable) {
            this.goldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        if (AetherConfig.tier2_gold_dungeon_enable) {
            this.ancientGoldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        if (AetherConfig.tier3_gold_dungeon_enable) {
            this.divineGoldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        }

        //Standard 3 Aerclouds
        if (biome != AetherWorld.stormy_skies) {
            if (this.rand.nextInt(50) == 0) {
                new AetherCloudsGenNew(BlocksAether.aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 32, z);

                if (biome == AetherWorld.aercloud_fields && this.rand.nextInt(14) == 0) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 32, z);
                }

                if (biome == AetherWorld.divine_island || biome == AetherWorld.aether_peaks) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(128) + 96, z);
                }
            }

            if (this.rand.nextInt(6) == 0) {
                new AetherCloudsGenNew(BlocksAether.aercloud, 0, 64, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(128), z);

                if (biome == AetherWorld.aercloud_fields && this.rand.nextInt(14) == 0) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 0, 64, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(128), z);
                }

                if (biome == AetherWorld.divine_island || biome == AetherWorld.aether_peaks) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(110) + 128, z);
                }
            }

            if (this.rand.nextInt(20) == 0) {
                new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64), z);

                if (biome == AetherWorld.aercloud_fields && this.rand.nextInt(14) == 0) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64), z);
                }

                if (biome == AetherWorld.divine_island || biome == AetherWorld.aether_peaks) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 128, z);
                }
            }

            if (this.rand.nextInt(12) == 0) {
                new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 64, z);

                if (biome == AetherWorld.aercloud_fields && this.rand.nextInt(14) == 0) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 64, z);
                }

                if (biome == AetherWorld.divine_island || biome == AetherWorld.aether_peaks) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 128, z);
                }
            }

            if (this.rand.nextInt(30) == 0) {
                new AetherCloudsGenNew(BlocksAether.aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 128, z);

                if (biome == AetherWorld.aercloud_fields && this.rand.nextInt(14) == 0) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 128, z);
                }

                if (biome == AetherWorld.divine_island || biome == AetherWorld.aether_peaks) {
                    new AetherCloudsGenNew(BlocksAether.aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(32) + 196, z);
                }
            }

        } else {
            if (this.rand.nextInt(30) == 0) {
                new AetherCloudsGenNew(BlocksAether.storm_aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 32, z);
            }

            if (this.rand.nextInt(27) == 0) {
                new AetherCloudsGenNew(BlocksAether.storm_aercloud, 0, 64, false).generate(this.worldObj, this.rand, x, this.rand.nextInt(128), z);
            }
        }

        if (this.rand.nextInt(48) == 0) {
            if (biome == AetherWorld.aercloud_fields) {
                for (int k3 = 0; k3 < 6; ++k3) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = this.rand.nextInt(this.rand.nextInt(48) + 48);
                    final int l5 = z + this.rand.nextInt(8) + 8;
                    new AetherGenLakes().generate(this.worldObj, this.rand, j3, l4, l5);
                }
            } else if (biome != AetherWorld.quicksoil_dunes)  {
                for (int k3 = 0; k3 < 6; ++k3) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = this.rand.nextInt(this.rand.nextInt(200) + 48);
                    final int l5 = z + this.rand.nextInt(8) + 8;
                    new AetherGenLakes().generate(this.worldObj, this.rand, j3, l4, l5);
                }
            }
        }

        //--------------------EXCLUSIVE GEN AETHER BIOME--------------------
        if (biome == AetherWorld.aether_biome) {
            if (AetherConfig.enableLightGreenAercloud() && this.rand.nextInt(70) == 0) {
                final int y = this.rand.nextInt(64) + 16;
                new AetherCloudsGenNew(BlocksAether.green_aercloud, 0, 9, false).generate(this.worldObj, this.rand, x, y, z);
            }

            if (AetherConfig.enableGreenAercloud() && this.rand.nextInt(14) == 0) {
                final int y = this.rand.nextInt(64) + 20;
                new AetherCloudsGenNew(BlocksAether.green_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
            }

            for (int n4 = 0; n4 < 3; ++n4) {
                final int x4 = x + this.rand.nextInt(8) + 8;
                final int y4 = this.rand.nextInt(128);
                final int z4 = z + this.rand.nextInt(8) + 8;
                new WorldGenStrawberryBush(BlocksAether.strawberry_bush, 4).generate(this.worldObj, this.rand, x4, y4, z4);
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.purple_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 4; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.burstblossom, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.quickshoot, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.white_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aercloud_layer, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aether_tulips, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }
        }

        //--------------------EXCLUSIVE GEN AETHER FOREST BIOME--------------------
        if (biome == AetherWorld.aether_forest) {
            if (AetherConfig.enableLimeAercloud() && this.rand.nextInt(17) == 0) {
                final int y = this.rand.nextInt(64) + 50;
                new AetherCloudsGenNew(BlocksAether.green_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
            }

            for (int n3 = 0; n3 < 3; ++n3) {
                final int x3 = x + this.rand.nextInt(8) + 8;
                final int y3 = this.rand.nextInt(128);
                final int z3 = z + this.rand.nextInt(8) + 8;
                new WorldGenRaspberryBush(BlocksAether.raspberry_bush, 5).generate(this.worldObj, this.rand, x3, y3, z3);
            }

            for (int n = 0; n < 4; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.burstblossom, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.purple_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.quickshoot, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.white_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aercloud_layer, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aether_tulips, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }
        }

        //--------------------EXCLUSIVE GEN AETHER PEAKS BIOME--------------------
        if (biome == AetherWorld.aether_peaks) {
            if (AetherConfig.enablePinkAercloud()) {
                if (this.rand.nextInt(52) == 0) {
                    final int y = this.rand.nextInt(64) + 148;
                    new AetherCloudsGenNew(BlocksAether.pink_aercloud, 0, 9, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }
            if (AetherConfig.enableMagentaAercloud()) {
                if (this.rand.nextInt(54) == 0) {
                    final int y = this.rand.nextInt(64) + 148;
                    new AetherCloudsGenNew(BlocksAether.pink_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }

            for (int n4 = 0; n4 < 2; ++n4) {
                final int x4 = x + this.rand.nextInt(8) + 8;
                final int y4 = this.rand.nextInt(256);
                final int z4 = z + this.rand.nextInt(8) + 8;
                new WorldGenBlackberryBush(BlocksAether.blackberry_bush, 3).generate(this.worldObj, this.rand, x4, y4, z4);
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.white_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aercloud_layer, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aether_tulips, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }
        }

        //--------------------EXCLUSIVE GEN QUICKSOIL DUNES BIOME--------------------
        if (biome == AetherWorld.quicksoil_dunes) {
            if (AetherConfig.enableOrangeAercloud()) {
                if (this.rand.nextInt(79) == 0) {
                    final int y = this.rand.nextInt(64) + 64;
                    new AetherCloudsGenNew(BlocksAether.pink_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }

            for (int m = 0; m < 5; m++) {
                if (this.rand.nextInt(123) == 0) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = z + this.rand.nextInt(8) + 8;
                    final int l5 = this.worldObj.getHeightValue(j3, l4);
                    new AetherGenHolystoneMounts().generate(this.worldObj, this.rand, j3, l5, l4);
                }
            }

            for (int m = 0; m < 5; m++) {
                if (this.rand.nextInt(47) == 0) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = z + this.rand.nextInt(8) + 8;
                    final int l5 = this.worldObj.getHeightValue(j3, l4);
                    new AetherGenQuicksoilMounts().generate(this.worldObj, this.rand, j3, l5, l4);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.carrion_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int m = 0; m < 5; m++) {
                if (this.rand.nextInt(3) == 0) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = z + this.rand.nextInt(8) + 8;
                    final int l5 = this.worldObj.getHeightValue(j3, l4);
                    new AetherGenCactus().generate(this.worldObj, this.rand, j3, l5, l4);
                }
            }
        }


        //--------------------EXCLUSIVE GEN ARCTIC HIGHLANDS BIOME--------------------
        if (biome == AetherWorld.arctic_biome) {
            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.white_rose, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 2; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenSnowLayer(Blocks.snow_layer, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 5; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.blue_swingtip, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int m = 0; m < 3; m++) {
                if (this.rand.nextInt(11) == 0) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = z + this.rand.nextInt(8) + 8;
                    final int l5 = this.rand.nextInt(20) + 4;
                    new WorldGenArcticGlowstone().generate(this.worldObj, this.rand, j3, l5, l4);
                }
            }
        }

        //--------------------EXCLUSIVE GEN DIVINE ISLAND BIOME--------------------
        if (biome == AetherWorld.divine_island) {
            if (AetherConfig.enablePurpleAercloud()) {
                if (this.rand.nextInt(60) == 0) {
                    final int y = this.rand.nextInt(64) + 32;
                    new AetherCloudsGenNew(BlocksAether.purple_aercloud, 0, 8, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }

            if (AetherConfig.enableVioletAercloud()) {
                if (this.rand.nextInt(14) == 0) {
                    final int y = this.rand.nextInt(64);
                    new AetherCloudsGenNew(BlocksAether.purple_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }

            if (AetherConfig.enableDarkPurpleAercloud()) {
                if (this.rand.nextInt(17) == 0) {
                    final int y = this.rand.nextInt(64) + 64;
                    new AetherCloudsGenNew(BlocksAether.purple_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(256);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.divine_bloom, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 2; ++n) {
                if (this.rand.nextInt(5) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(256);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.divine_stalk, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(7) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(256);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.divine_lily, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 6; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(256);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aechor_sprout, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n6 = 0; n6 < 2; ++n6) {
                final int x6 = x + this.rand.nextInt(8) + 8;
                final int y6 = this.rand.nextInt(128);
                final int z6 = z + this.rand.nextInt(8) + 8;
                new WorldGenGrapeVines(BlocksAether.grape_tree_mature, 3).generate(this.worldObj, this.rand, x6, y6, z6);
            }

            for (int m = 0; m < 5; m++) {
                if (this.rand.nextInt(3) == 0) {
                    final int j3 = x + this.rand.nextInt(8) + 8;
                    final int l4 = z + this.rand.nextInt(8) + 8;
                    final int l5 = this.rand.nextInt(128) + 8;
                    new WorldGenAmethystGlowstone().generate(this.worldObj, this.rand, j3, l5, l4);
                }
            }
        }

        //--------------------EXCLUSIVE GEN ENCHANTED ISLAND BIOME--------------------
        if (biome == AetherWorld.enchanted_island) {
            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.enchanted_aether_tulips, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 2; ++n) {
                if (this.rand.nextInt(5) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.enchanted_bloom, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(7) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.enchanted_quickshoot, 32).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n5 = 0; n5 < 2; ++n5) {
                final int x5 = x + this.rand.nextInt(8) + 8;
                final int y5 = this.rand.nextInt(128);
                final int z5 = z + this.rand.nextInt(8) + 8;
                new WorldGenOrangeTree(BlocksAether.mature_orange_tree, 2).generate(this.worldObj, this.rand, x5, y5, z5);
            }
        }

        //--------------------EXCLUSIVE GEN AETHER BIOME--------------------
        if (biome == AetherWorld.stormy_skies) {
            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(96);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aether_tulips, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(3) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(96);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.white_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(3) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(96);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.purple_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(3) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(96);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.quickshoot, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }

            for (int n = 0; n < 3; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(96);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.aether_tallgrass, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }
        }

        //--------------------EXCLUSIVE GEN AERCLOUD FIELD BIOME--------------------
        if (biome == AetherWorld.aercloud_fields) {
            if (this.rand.nextInt(35) == 0) {
                final int j3 = x + this.rand.nextInt(8) + 8;
                final int l4 = this.rand.nextInt(this.rand.nextInt(64) + 32);
                final int l5 = z + this.rand.nextInt(8) + 8;
                this.crystal_island.generate(this.worldObj, this.rand, j3, l4, l5);
            }

            if (this.rand.nextInt(60) == 0) {
                final int j3 = x + this.rand.nextInt(8) + 8;
                final int l4 = this.rand.nextInt(this.rand.nextInt(8) + 16);
                final int l5 = z + this.rand.nextInt(8) + 8;
                this.void_island.generate(this.worldObj, this.rand, j3, l4, l5);
            }
        }

        //All Biomes
        for (int n2 = 0; n2 < 2; ++n2) {
            final int x2 = x + this.rand.nextInt(8) + 8;
            final int y2 = this.rand.nextInt(128);
            final int z2 = z + this.rand.nextInt(8) + 8;
            new WorldGenBerryBush(BlocksAether.berry_bush, 3).generate(this.worldObj, this.rand, x2, y2, z2);
        }

        if (this.rand.nextInt(4) == 0) {
            for (int k3 = 0; k3 < 10; ++k3) {
                final int j3 = x + this.rand.nextInt(8) + 8;
                final int l4 = this.rand.nextInt(this.rand.nextInt(120) + 8);
                final int l5 = z + this.rand.nextInt(8) + 8;
                new AetherGenLiquids(Blocks.water).generate(this.worldObj, this.rand, j3, l4, l5);
            }
        }

        if (biome != AetherWorld.enchanted_island && biome != AetherWorld.stormy_skies) {
            for (int n = 0; n < 4; ++n) {
                if (this.rand.nextInt(2) == 0) {
                    final int x2 = x + this.rand.nextInt(8) + 8;
                    final int y2 = this.rand.nextInt(128);
                    final int z2 = z + this.rand.nextInt(8) + 8;
                    new AetherGenFlowers(BlocksAether.neverbloom, 64).generate(this.worldObj, this.rand, x2, y2, z2);
                }
            }
        }

        //Structures
        int den_chance = (int) (1 + Math.random() * 5);
        if (AetherConfig.zarnyllis_den_gen && den_chance == 1) {
            if (biome == AetherWorld.arctic_biome || biome == AetherWorld.quicksoil_dunes) {
                this.zarnillys_den.generate(this.worldObj, this.rand, x, this.rand.nextInt(6) + 50, z);
            }
            if (biome == AetherWorld.aether_peaks || biome == AetherWorld.divine_island) {
                this.zarnillys_den.generate(this.worldObj, this.rand, x, this.rand.nextInt(6) + 50, z);
                this.zarnillys_den.generate(this.worldObj, this.rand, x, this.rand.nextInt(64) + 128, z);
            }
        }

        if (biome == AetherWorld.divine_island) {
            int bronze_type_divine = (int) (1 + Math.random() * 20);
            if (AetherConfig.tier3_bronze_dungeon_enable && bronze_type_divine < 12) {
                this.divine_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(12) + 24, z);
                this.divine_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(50) + 128, z);
            } else if (AetherConfig.tier4_bronze_dungeon_enable && bronze_type_divine >= 12) {
                this.mythic_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(4) + 24, z);
                this.mythic_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(34) + 128, z);
            }
        } else {
            int bronze_type = (int) (1 + Math.random() * 20);
            if (biome != AetherWorld.aercloud_fields) {
                if (AetherConfig.bronze_dungeon_enable && bronze_type < 10) {
                    this.dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(28) + 24, z);
                    if (biome == AetherWorld.aether_peaks) {
                        this.dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(50) + 128, z);
                    }
                } else if (AetherConfig.tier2_bronze_dungeon_enable && bronze_type < 14) {
                    this.large_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(18) + 26, z);
                    if (biome == AetherWorld.aether_peaks) {
                        this.large_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(50) + 128, z);
                    }
                } else if (AetherConfig.tier3_bronze_dungeon_enable && bronze_type < 19) {
                    this.divine_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(12) + 24, z);
                    if (biome == AetherWorld.aether_peaks) {
                        this.divine_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(50) + 128, z);
                    }
                } else if (AetherConfig.tier4_bronze_dungeon_enable && bronze_type >= 19) {
                    this.mythic_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(4) + 24, z);
                    if (biome == AetherWorld.aether_peaks) {
                        this.mythic_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(34) + 128, z);
                    }
                }
            }
        }

        if (AetherConfig.cobalt_dungeon_enable && (biome == AetherWorld.quicksoil_dunes || biome == AetherWorld.arctic_biome)) {
            this.cobalt_dungeon.generate(this.worldObj, this.rand, x, this.rand.nextInt(12) + 24, z);
        }

        for (int k3 = 0; k3 < 25; k3++) {
            final int j3 = x + this.rand.nextInt(8) + 8;
            final int l5 = z + this.rand.nextInt(8) + 8;
            final int l4 = this.worldObj.getHeightValue(j3, l5);
            this.golden_oak_tree_dungeon.generate(this.worldObj, this.rand, j3, l4, l5);
        }

        if (this.rand.nextInt(30) == 0 ) {
            if ((AetherConfig.shouldLoadHolidayContent()) || (AetherConfig.allowSeasonalChristmas() && (Calendar.getInstance().get(Calendar.MONTH) + 1 == 12 || Calendar.getInstance().get(Calendar.MONTH) + 1 == 1))) {
                final int j3 = x + this.rand.nextInt(8) + 8;
                final int l5 = z + this.rand.nextInt(8) + 8;
                final int l4 = this.worldObj.getHeightValue(j3, l5);
                this.holiday_tree.generate(this.worldObj, this.rand, j3, l4, l5);
            }
        }
	}

	@Override
	public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
	}

	@Override
	public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
        return true;
	}

	@Override
	public boolean canSave() {
        return true;
	}

	@Override
	public String makeString() {
        return "AetherRandomLevelSource";
	}

	@Override
	public int getLoadedChunkCount() {
        return 0;
	}

	@Override
	public void saveExtraData() {
	}

	static {
        ChunkProviderAether.placementFlagType = 2;
    }

}
