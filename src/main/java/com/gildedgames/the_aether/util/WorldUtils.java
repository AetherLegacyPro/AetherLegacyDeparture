package com.gildedgames.the_aether.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WorldUtils {

    public static void ensureBlockExists(World world, int x, int y, int z) {
        if (world instanceof WorldServer worldServer) {
            ensureBlockExists(worldServer, x, y, z);
        }
    }

    public static void ensureBlockExists(WorldServer world, int x, int y, int z) {
        if (world != null && !world.blockExists(x, y, z)) {
            world.getChunkProvider().loadChunk(x >> 4, z >> 4);
        }
    }

	public static Block getBlockSafe(World world, int x, int y, int z) {
        ensureBlockExists(world, x, y, z);
		return world.getBlock(x, y, z);
	}

	public static boolean setBlockSafe(World world, int x, int y, int z, Block blockIn, int metadataIn, int flags) {
		ensureBlockExists(world, x, y, z);
		return world.setBlock(x, y, z, blockIn, metadataIn, flags);
	}
}