package com.gildedgames.the_aether.world.dungeon;

import java.util.Random;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.world.AetherWorld;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public final class PalladiumDungeonFinder {

    private static final boolean DEBUG = false;

    //8192 blocks in every direction from thrown
    private static final int SEARCH_RADIUS_CHUNKS = 512;
    private static final long PALLADIUM_SEED_SALT = 0x50414C4C41444955L;

    private PalladiumDungeonFinder() {
    }

    public static Random createChunkRandom(long worldSeed, int chunkX, int chunkZ) {
        long seed = worldSeed;

        seed ^= (long)chunkX * 341873128712L;
        seed ^= (long)chunkZ * 132897987541L;
        seed ^= PALLADIUM_SEED_SALT;
        seed ^= seed >>> 13;
        seed *= 1274126177L;
        seed ^= seed >>> 16;

        return new Random(seed);
    }

    public static ChunkCoordinates getNearestPalladiumDungeon(World world, int playerX, int playerZ) {
        if (world == null || world.provider == null) {
            return null;
        }

        if (world.provider.dimensionId != AetherConfig.getAetherDimensionID()) {
            return null;
        }

        if (!AetherConfig.palladium_dungeon_enable) {
            return null;
        }

        if (AetherConfig.palladium_dungeon_rarity <= 0) {
            return null;
        }

        int playerChunkX = floorDiv(playerX, 16);
        int playerChunkZ = floorDiv(playerZ, 16);

        ChunkCoordinates closest = null;
        long closestDistanceSq = Long.MAX_VALUE;

        for (int radius = 0; radius <= SEARCH_RADIUS_CHUNKS; radius++) {
            if (radius == 0) {

                ChunkCoordinates candidate = getDungeonInChunk(world, playerChunkX, playerChunkZ);
                if (candidate != null) {
                    closest = candidate;
                    closestDistanceSq = getDistanceSq(playerX, playerZ, candidate.posX, candidate.posZ);
                }
            } else {
                for (int offsetX = -radius; offsetX <= radius; offsetX++) {

                    ChunkCoordinates north = getDungeonInChunk(world, playerChunkX + offsetX, playerChunkZ - radius);
                    if (north != null) {
                        long distanceSq = getDistanceSq(playerX, playerZ, north.posX, north.posZ);
                        if (distanceSq < closestDistanceSq) {
                            closestDistanceSq = distanceSq;
                            closest = north;
                        }
                    }

                    ChunkCoordinates south = getDungeonInChunk(world, playerChunkX + offsetX, playerChunkZ + radius);
                    if (south != null) {

                        long distanceSq = getDistanceSq(playerX, playerZ, south.posX, south.posZ);
                        if (distanceSq < closestDistanceSq) {
                            closestDistanceSq = distanceSq;
                            closest = south;
                        }
                    }
                }

                for (int offsetZ = -radius + 1; offsetZ <= radius - 1; offsetZ++) {
                    ChunkCoordinates west = getDungeonInChunk(world, playerChunkX - radius, playerChunkZ + offsetZ);

                    if (west != null) {

                        long distanceSq = getDistanceSq(playerX, playerZ, west.posX, west.posZ);
                        if (distanceSq < closestDistanceSq) {
                            closestDistanceSq = distanceSq;
                            closest = west;
                        }
                    }

                    ChunkCoordinates east = getDungeonInChunk(world, playerChunkX + radius, playerChunkZ + offsetZ);
                    if (east != null) {

                        long distanceSq = getDistanceSq(playerX, playerZ, east.posX, east.posZ);
                        if (distanceSq < closestDistanceSq) {
                            closestDistanceSq = distanceSq;
                            closest = east;
                        }
                    }
                }
            }

            if (closest != null) {
                long minimumOutsideDistance = Math.max(0L, (long)radius * 16L - 23L);
                if (closestDistanceSq <= minimumOutsideDistance * minimumOutsideDistance) {
                    if (DEBUG) {
                        System.out.println("Palladium Dungeon: X=" + closest.posX + ", Y=" + closest.posY + ", Z=" + closest.posZ);
                    }
                    return closest;
                }
            }
        }

        return closest;
    }

    private static ChunkCoordinates getDungeonInChunk(World world, int chunkX, int chunkZ) {
        int blockX = chunkX * 16;
        int blockZ = chunkZ * 16;

        if (Math.abs(blockX) < 3000 || Math.abs(blockZ) < 3000) {
            return null;
        }

        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(blockX + 8, blockZ + 8);
        if (biome != AetherWorld.divine_island) {
            return null;
        }

        int rarity = AetherConfig.palladium_dungeon_rarity;
        if (!AetherConfig.palladium_dungeon_enable || rarity <= 0) {
            return null;
        }

        Random random = createChunkRandom(world.getSeed(), chunkX, chunkZ);
        if (random.nextInt(rarity) != 0) {
            return null;
        }

        int unusedL3 = random.nextInt(3) + 2;

        if (random.nextInt(35) != 0) {
            return null;
        }

        int dungeonX = blockX + random.nextInt(16) + 8;
        int dungeonY = random.nextInt(16) + 225;
        int dungeonZ = blockZ + random.nextInt(16) + 8;

        if (DEBUG) {
            System.out.println("[Palladium Finder] Candidate at X=" + dungeonX + ", Y=" + (dungeonY + 1) + ", Z=" + dungeonZ + " from chunk " + chunkX + ", " + chunkZ + "; l3=" + unusedL3);
        }

        return new ChunkCoordinates(dungeonX, dungeonY + 1, dungeonZ);
    }

    private static long getDistanceSq(int x1, int z1, int x2, int z2) {
        long deltaX = (long)x2 - x1;
        long deltaZ = (long)z2 - z1;
        return deltaX * deltaX + deltaZ * deltaZ;
    }

    private static int floorDiv(int value, int divisor) {
        if (divisor <= 0) {
            throw new IllegalArgumentException("divisor must be greater than zero");
        }

        return value >= 0 ? value / divisor : -((-value + divisor - 1) / divisor);
    }
}
