package com.gildedgames.the_aether.world.dungeon.osmium;

import java.util.Random;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.world.AetherWorld;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public final class OsmiumDungeonFinder {

    private static final boolean DEBUG = false;

    //8,192 blocks in every direction from thrown
    private static final int SEARCH_RADIUS_CHUNKS = 512;

    private OsmiumDungeonFinder() {
    }

    private static Random createForgeChunkRandom(long worldSeed, int chunkX, int chunkZ) {
        Random forgeRandom = new Random(worldSeed);
        long xSeed = forgeRandom.nextLong() >> 3;
        long zSeed = forgeRandom.nextLong() >> 3;
        long chunkSeed = (xSeed * (long) chunkX + zSeed * (long) chunkZ) ^ worldSeed;
        forgeRandom.setSeed(chunkSeed);

        return forgeRandom;
    }

    private static ChunkCoordinates getDungeonInChunk(World world, int chunkX, int chunkZ) {
        int chunkBlockX = chunkX * 16;
        int chunkBlockZ = chunkZ * 16;

        if (Math.abs(chunkBlockX) < 1200 || Math.abs(chunkBlockZ) < 1200) {
            return null;
        }

        if (!AetherConfig.osmium_dungeon_enable) {
            return null;
        }

        int rarity = AetherConfig.osmium_dungeon_rarity;
        if (rarity <= 0) {
            return null;
        }

        Random random = createForgeChunkRandom(world.getSeed(), chunkX, chunkZ);

        if (random.nextInt(rarity) != 0) {
            return null;
        }

        BiomeGenBase biome = world.getBiomeGenForCoords(chunkBlockX, chunkBlockZ);
        if (biome != AetherWorld.stormy_skies) {
            return null;
        }

        int dungeonOriginX = chunkBlockX + random.nextInt(8) + 8;
        int dungeonOriginY = 128;
        int dungeonOriginZ = chunkBlockZ + random.nextInt(8) + 8;

        int targetX = dungeonOriginX + 36;
        int targetZ = dungeonOriginZ + 36;

        return new ChunkCoordinates(targetX, dungeonOriginY, targetZ);
    }

    public static ChunkCoordinates getNearestOsmiumDungeon(World world, int playerX, int playerZ) {
        if (world == null || world.provider == null) {
            return null;
        }

        if (world.provider.dimensionId != AetherConfig.getAetherDimensionID()) {
            return null;
        }

        if (!AetherConfig.osmium_dungeon_enable) {
            return null;
        }

        if (AetherConfig.osmium_dungeon_rarity <= 0) {
            return null;
        }

        int playerChunkX = floorDiv(playerX, 16);
        int playerChunkZ = floorDiv(playerZ, 16);

        ChunkCoordinates closest = null;
        long closestDistanceSquared = Long.MAX_VALUE;

        for (int radius = 0; radius <= SEARCH_RADIUS_CHUNKS; radius++) {
            if (radius == 0) {

                ChunkCoordinates candidate = getDungeonInChunk(world, playerChunkX, playerChunkZ);
                if (candidate != null) {
                    closest = candidate;
                    closestDistanceSquared = getDistanceSquared(playerX, playerZ, candidate.posX, candidate.posZ);
                }
            } else {

                for (int offsetX = -radius; offsetX <= radius; offsetX++) {

                    ChunkCoordinates north = getDungeonInChunk(world, playerChunkX + offsetX, playerChunkZ - radius);
                    if (north != null) {
                        long distanceSquared = getDistanceSquared(playerX, playerZ, north.posX, north.posZ);
                        if (distanceSquared < closestDistanceSquared) {
                            closestDistanceSquared = distanceSquared;
                            closest = north;
                        }
                    }

                    ChunkCoordinates south = getDungeonInChunk(world, playerChunkX + offsetX, playerChunkZ + radius);
                    if (south != null) {

                        long distanceSquared = getDistanceSquared(playerX, playerZ, south.posX, south.posZ);
                        if (distanceSquared < closestDistanceSquared) {
                            closestDistanceSquared = distanceSquared;
                            closest = south;
                        }
                    }
                }

                for (int offsetZ = -radius + 1; offsetZ <= radius - 1; offsetZ++) {

                    ChunkCoordinates west = getDungeonInChunk(world, playerChunkX - radius, playerChunkZ + offsetZ);
                    if (west != null) {
                        long distanceSquared = getDistanceSquared(playerX, playerZ, west.posX, west.posZ);

                        if (distanceSquared < closestDistanceSquared) {
                            closestDistanceSquared = distanceSquared;
                            closest = west;
                        }
                    }

                    ChunkCoordinates east = getDungeonInChunk(world, playerChunkX + radius, playerChunkZ + offsetZ);
                    if (east != null) {
                        long distanceSquared = getDistanceSquared(playerX, playerZ, east.posX, east.posZ);

                        if (distanceSquared < closestDistanceSquared) {
                            closestDistanceSquared = distanceSquared;
                            closest = east;
                        }
                    }
                }
            }

            if (closest != null) {
                long minimumOutsideDistance = Math.max(0L, (long) radius * 16L - 68L);
                if (closestDistanceSquared <= minimumOutsideDistance * minimumOutsideDistance) {
                    if (DEBUG) {
                        System.out.println("Osmium Dungeon: X=" + closest.posX + ", Y=" + closest.posY + ", Z=" + closest.posZ);
                    }

                    return closest;
                }
            }
        }

        return closest;
    }

    private static long getDistanceSquared(int firstX, int firstZ, int secondX, int secondZ) {
        long differenceX = (long) secondX - firstX;
        long differenceZ = (long) secondZ - firstZ;
        return differenceX * differenceX + differenceZ * differenceZ;
    }

    private static int floorDiv(int value, int divisor) {
        if (divisor <= 0) {
            throw new IllegalArgumentException("divisor must be greater than zero");
        }

        return value >= 0 ? value / divisor : -((-value + divisor - 1) / divisor);
    }

}
