package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.world.ChunkProviderAether;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class AetherGenHolystoneMounts extends WorldGenAbstractTree {

    private static final int WORLD_HEIGHT = 128;
    private Block leafBlock;
    private Block logBlock;
    private int logMetadata;
    private boolean moundOnly;

    public AetherGenHolystoneMounts(final Block leafID, final Block logID, final int logMeta) {
        super(true);

        this.leafBlock = leafID;
        this.logBlock = logID;
        this.logMetadata = logMeta;
        this.moundOnly = false;
    }

    public AetherGenHolystoneMounts() {
        super(false);

        this.leafBlock = Blocks.air;
        this.logBlock = Blocks.air;
        this.logMetadata = 0;
        this.moundOnly = true;
    }

    @Override
    public boolean generate(final World world, final Random random, final int i, final int j, final int k) {
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);

        if (this.moundOnly || this.isQuicksoilDunesBiome(biome)) {
            return this.generateHolystoneMound(world, random, i, j, k);
        }

        return this.generateSkyrootTree(world, random, i, j, k);
    }

    private boolean isQuicksoilDunesBiome(BiomeGenBase biome) {
        return biome != null
            && AetherWorld.quicksoil_dunes != null
            && biome.biomeID == AetherWorld.quicksoil_dunes.biomeID;
    }

    private boolean generateSkyrootTree(final World world, final Random random, final int i, final int j, final int k) {
        final int l = random.nextInt(3) + 4;
        boolean flag = true;

        if (j < 1 || j + l + 1 > WORLD_HEIGHT) {
            return false;
        }

        for (int i2 = j; i2 <= j + 1 + l; ++i2) {
            byte byte0 = 1;

            if (i2 == j) {
                byte0 = 0;
            }

            if (i2 >= j + 1 + l - 2) {
                byte0 = 2;
            }

            for (int i3 = i - byte0; i3 <= i + byte0 && flag; ++i3) {
                for (int l2 = k - byte0; l2 <= k + byte0 && flag; ++l2) {
                    if (i2 >= 0 && i2 < WORLD_HEIGHT) {
                        final Block j2 = world.getBlock(i3, i2, l2);

                        if (j2 != Blocks.air && j2 != this.leafBlock) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }

        if (!flag) {
            return false;
        }

        final Block j3 = world.getBlock(i, j - 1, k);

        if ((j3 != BlocksAether.aether_grass && j3 != BlocksAether.arctic_grass && j3 != BlocksAether.enchanted_aether_grass
            && j3 != BlocksAether.divine_grass && j3 != BlocksAether.aether_dirt && j3 != BlocksAether.quicksoil) || j >= WORLD_HEIGHT - l - 1) {
            return false;
        }

        world.setBlock(i, j - 1, k, BlocksAether.aether_dirt);

        for (int k2 = j - 3 + l; k2 <= j + l; ++k2) {
            final int j4 = k2 - (j + l);

            for (int i4 = 1 - j4 / 2, k3 = i - i4; k3 <= i + i4; ++k3) {
                final int l3 = k3 - i;

                for (int i5 = k - i4; i5 <= k + i4; ++i5) {
                    final int j5 = i5 - k;

                    if ((Math.abs(l3) != i4 || Math.abs(j5) != i4 || (random.nextInt(2) != 0 && j4 != 0)) && !world.getBlock(k3, k2, i5).isOpaqueCube()) {
                        world.setBlock(k3, k2, i5, this.leafBlock);
                    }
                }
            }
        }

        for (int l4 = 0; l4 < l; ++l4) {
            final Block k4 = world.getBlock(i, j + l4, k);
            if (k4 == Blocks.air || k4 == this.leafBlock) {
                world.setBlock(i, j + l4, k, this.logBlock, this.logMetadata, ChunkProviderAether.placementFlagType);
            }
        }

        return true;
    }

    private boolean generateHolystoneMound(final World world, final Random random, final int x, final int y, final int z) {
        int surfaceY = this.findSurfaceY(world, x, y, z);
        if (surfaceY < 1 || surfaceY >= WORLD_HEIGHT - 8) {
            return false;
        }

        Block ground = world.getBlock(x, surfaceY - 1, z);
        if (!this.canGenerateMoundOn(ground)) {
            return false;
        }

        int radiusX = random.nextInt(4) + 6;
        int radiusZ = random.nextInt(4) + 6;
        int moundHeight = random.nextInt(8) + 6;

        boolean generatedAny = false;

        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                double nx = (double)dx / (double)radiusX;
                double nz = (double)dz / (double)radiusZ;
                double distance = nx * nx + nz * nz;

                if (distance > 1.0D) {
                    continue;
                }

                double dome = 1.0D - distance;
                int columnHeight = 1 + (int)Math.round(dome * moundHeight);

                if (random.nextInt(5) == 0) {
                    columnHeight--;
                }

                if (columnHeight < 1) {
                    columnHeight = 1;
                }

                int worldX = x + dx;
                int worldZ = z + dz;

                int localSurfaceY = this.findSurfaceY(world, worldX, surfaceY, worldZ);
                if (localSurfaceY < 1 || localSurfaceY >= WORLD_HEIGHT - 8) {
                    continue;
                }

                Block localGround = world.getBlock(worldX, localSurfaceY - 1, worldZ);
                if (!this.canGenerateMoundOn(localGround)) {
                    continue;
                }

                for (int dy = 0; dy < columnHeight; dy++) {
                    int placeY = localSurfaceY + dy;

                    if (placeY <= 0 || placeY >= WORLD_HEIGHT) {
                        continue;
                    }

                    Block current = world.getBlock(worldX, placeY, worldZ);
                    if (!this.canReplaceForMound(current)) {
                        continue;
                    }

                    Block moundBlock = BlocksAether.holystone;

                    world.setBlock(worldX, placeY, worldZ, moundBlock, 0, ChunkProviderAether.placementFlagType);
                    generatedAny = true;
                }
            }
        }

        return generatedAny;
    }

    private int findSurfaceY(final World world, final int x, final int startY, final int z) {
        int y = startY;

        if (y <= 1 || y >= WORLD_HEIGHT) {
            y = world.getHeightValue(x, z);
        }

        if (y >= WORLD_HEIGHT) {
            y = WORLD_HEIGHT - 1;
        }

        if (y < 1) {
            y = 1;
        }

        for (int scanY = y; scanY > 1; scanY--) {
            Block below = world.getBlock(x, scanY - 1, z);
            Block current = world.getBlock(x, scanY, z);

            if (below != Blocks.air && current == Blocks.air) {
                return scanY;
            }
        }

        return -1;
    }

    private boolean canGenerateMoundOn(Block block) {
        return block == BlocksAether.quicksoil || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.holystone || block == BlocksAether.mossy_holystone;
    }

    private boolean canReplaceForMound(Block block) {
        return block == Blocks.air || block == BlocksAether.quicksoil || block == BlocksAether.holystone;
    }
}
