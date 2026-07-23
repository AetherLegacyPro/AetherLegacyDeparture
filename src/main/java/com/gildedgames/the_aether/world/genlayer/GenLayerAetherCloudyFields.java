package com.gildedgames.the_aether.world.genlayer;

import com.gildedgames.the_aether.world.AetherWorld;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAetherCloudyFields extends GenLayer {

    public GenLayerAetherCloudyFields(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int border = 2;

        int parentX = x - border;
        int parentZ = z - border;
        int parentWidth = width + border * 2;
        int parentHeight = height + border * 2;

        int[] parentInts = this.parent.getInts(parentX, parentZ, parentWidth, parentHeight);
        int[] result = IntCache.getIntCache(width * height);

        int cloudyId = AetherWorld.aercloud_fields.biomeID;

        for (int dz = 0; dz < height; dz++) {
            for (int dx = 0; dx < width; dx++) {
                int centerX = dx + border;
                int centerZ = dz + border;

                int center = parentInts[centerX + centerZ * parentWidth];

                boolean nearDifferentBiome = false;

                for (int oz = -border; oz <= border; oz++) {
                    for (int ox = -border; ox <= border; ox++) {
                        if (ox == 0 && oz == 0) {
                            continue;
                        }

                        int neighbor = parentInts[(centerX + ox) + (centerZ + oz) * parentWidth];

                        if (neighbor != center) {
                            nearDifferentBiome = true;
                            break;
                        }
                    }

                    if (nearDifferentBiome) {
                        break;
                    }
                }

                if (nearDifferentBiome) {
                    result[dx + dz * width] = cloudyId;
                } else {
                    result[dx + dz * width] = center;
                }
            }
        }

        return result;
    }
}
