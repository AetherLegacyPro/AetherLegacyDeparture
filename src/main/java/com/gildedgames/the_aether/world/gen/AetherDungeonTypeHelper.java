package com.gildedgames.the_aether.world.gen;

import java.util.Random;
import com.gildedgames.the_aether.world.util.RandomTracker;

public class AetherDungeonTypeHelper {

    public static final int TYPE_REGULAR = 0;
    public static final int TYPE_ANCIENT = 1;
    public static final int TYPE_DIVINE = 2;

    private static final long SILVER_SPAWN = 10001L;
    private static final long SILVER_TYPE = 10002L;

    private static final long GOLDEN_SPAWN = 20001L;
    private static final long GOLDEN_TYPE = 20002L;

    public static boolean canSilverDungeonSpawnAt(long worldSeed, int chunkX, int chunkZ) {
        if (chunkX % 8 != 0 || chunkZ % 8 != 0) {
            return false;
        }

        Random random = new Random(getSeed(worldSeed, chunkX, chunkZ, SILVER_SPAWN));
        RandomTracker randomTracker = new RandomTracker();
        if (randomTracker.testRandom(random, 110) != 0) {
            if (randomTracker.testRandom(random, 140) != 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean canGoldenDungeonSpawnAt(long worldSeed, int chunkX, int chunkZ) {
        if (chunkX % 8 != 0 || chunkZ % 8 != 0) {
            return false;
        }

        Random random = new Random(getSeed(worldSeed, chunkX, chunkZ, GOLDEN_SPAWN));
        RandomTracker randomTracker = new RandomTracker();
        if (randomTracker.testRandom(random, 80) != 0) {
            if (randomTracker.testRandom(random, 120) != 0) {
                return false;
            }
        }

        return true;
    }

    public static int getSilverDungeonType(long worldSeed, int chunkX, int chunkZ) {
        return getDungeonType(worldSeed, chunkX, chunkZ, SILVER_TYPE);
    }

    public static int getGoldenDungeonType(long worldSeed, int chunkX, int chunkZ) {
        return getDungeonType(worldSeed, chunkX, chunkZ, GOLDEN_TYPE);
    }

    private static int getDungeonType(long worldSeed, int chunkX, int chunkZ, long mix) {
        Random random = new Random(getSeed(worldSeed, chunkX, chunkZ, mix));

        int roll = random.nextInt(20) + 1;
        if (roll <= 10) {
            return TYPE_REGULAR;
        }

        if (roll <= 17) {
            return TYPE_ANCIENT;
        }

        return TYPE_DIVINE;
    }

    private static long getSeed(long worldSeed, int chunkX, int chunkZ, long mix) {
        long value = worldSeed;
        value ^= (long)chunkX * 341873128712L;
        value ^= (long)chunkZ * 132897987541L;
        value ^= mix * 42317861L;
        value ^= value >> 13;
        value *= 1274126177L;
        value ^= value >> 16;

        return value;
    }
}
