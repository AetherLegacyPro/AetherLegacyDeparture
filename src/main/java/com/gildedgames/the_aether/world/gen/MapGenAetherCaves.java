package com.gildedgames.the_aether.world.gen;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;

public class MapGenAetherCaves extends MapGenBase {

    private static final int CHUNK_HEIGHT = 256;
    private static final int MAX_CAVE_Y_NORMAL = 88;
    private static final int MAX_CAVE_Y_PEAKS = 220;
    private static final int SURFACE_PROTECTION_DEPTH = 10;

    private int getBlockIndex(int localX, int y, int localZ) {
        return (localX * 16 + localZ) * CHUNK_HEIGHT + y;
    }

    private boolean isAirBlock(Block block) {
        return block == null || block == Blocks.air;
    }

    private int getTopSolidY(Block[] data, int localX, int localZ) {
        for (int y = CHUNK_HEIGHT - 1; y >= 0; y--) {
            int index = this.getBlockIndex(localX, y, localZ);
            Block block = data[index];

            if (!this.isAirBlock(block)) {
                return y;
            }
        }

        return -1;
    }

    private boolean isTooCloseToSurface(Block[] data, int localX, int y, int localZ) {
        int topY = this.getTopSolidY(data, localX, localZ);

        if (topY < 0) {
            return true;
        }

        return y >= topY - SURFACE_PROTECTION_DEPTH;
    }

    private boolean isHighTerrainAt(int worldX, int worldZ) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(worldX, worldZ);

        if (biome == null) {
            return false;
        }

        if (AetherWorld.aether_peaks != null && biome.biomeID == AetherWorld.aether_peaks.biomeID) {
            return true;
        }

        if (AetherWorld.divine_island != null && biome.biomeID == AetherWorld.divine_island.biomeID) {
            return true;
        }

        return false;
    }

    private boolean isAercloudFieldsAt(int worldX, int worldZ) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(worldX, worldZ);

        return biome != null && AetherWorld.aercloud_fields != null && biome.biomeID == AetherWorld.aercloud_fields.biomeID;
    }

    private int getMaxCaveYAt(int worldX, int worldZ) {
        if (this.isHighTerrainAt(worldX, worldZ)) {
            return MAX_CAVE_Y_PEAKS;
        }

        return MAX_CAVE_Y_NORMAL;
    }

    protected void func_151542_a(final long seed, final int chunkX, final int chunkZ, final Block[] blocks, final byte[] metadata, final double x, final double y, final double z) {
        this.func_151541_a(seed, chunkX, chunkZ, blocks, metadata, x, y, z, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void func_151541_a(final long seed, final int chunkX, final int chunkZ, final Block[] blocks, final byte[] metadata, double caveX, double caveY, double caveZ, final float caveScale, float yaw, float pitch, int step, int maxSteps, final double verticalScale) {
        final double chunkCenterX = chunkX * 16 + 8;
        final double chunkCenterZ = chunkZ * 16 + 8;

        float yawVelocity = 0.0F;
        float pitchVelocity = 0.0F;

        final Random random = new Random(seed);

        if (maxSteps <= 0) {
            final int rangeBlocks = this.range * 16 - 16;
            maxSteps = rangeBlocks - random.nextInt(rangeBlocks / 4);
        }

        boolean largeNode = false;

        if (step == -1) {
            step = maxSteps / 2;
            largeNode = true;
        }

        final int branchStep = random.nextInt(maxSteps / 2) + maxSteps / 4;
        final boolean gentlePitch = random.nextInt(6) == 0;

        while (step < maxSteps) {
            final double horizontalRadius = 1.5D + MathHelper.sin(step * 3.1415927F / maxSteps) * caveScale;
            final double verticalRadius = horizontalRadius * verticalScale;

            final float cosPitch = MathHelper.cos(pitch);
            final float sinPitch = MathHelper.sin(pitch);

            caveX += MathHelper.cos(yaw) * cosPitch;
            caveY += sinPitch;
            caveZ += MathHelper.sin(yaw) * cosPitch;

            if (gentlePitch) {
                pitch *= 0.92F;
            } else {
                pitch *= 0.7F;
            }

            pitch += pitchVelocity * 0.1F;
            yaw += yawVelocity * 0.1F;

            pitchVelocity *= 0.9F;
            yawVelocity *= 0.75F;

            pitchVelocity += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            yawVelocity += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!largeNode && step == branchStep && caveScale > 1.0F && maxSteps > 0) {
                this.func_151541_a(random.nextLong(), chunkX, chunkZ, blocks, metadata, caveX, caveY, caveZ, random.nextFloat() * 0.5F + 0.5F, yaw - 1.5707964F, pitch / 3.0F, step, maxSteps, 1.0D);
                this.func_151541_a(random.nextLong(), chunkX, chunkZ, blocks, metadata, caveX, caveY, caveZ, random.nextFloat() * 0.5F + 0.5F, yaw + 1.5707964F, pitch / 3.0F, step, maxSteps, 1.0D);
                return;
            }

            if (largeNode || random.nextInt(4) != 0) {
                final double dxFromCenter = caveX - chunkCenterX;
                final double dzFromCenter = caveZ - chunkCenterZ;
                final double stepsLeft = maxSteps - step;
                final double maxDistance = caveScale + 2.0F + 16.0F;

                if (dxFromCenter * dxFromCenter + dzFromCenter * dzFromCenter - stepsLeft * stepsLeft > maxDistance * maxDistance) {
                    return;
                }

                if (caveX >= chunkCenterX - 16.0D - horizontalRadius * 2.0D && caveZ >= chunkCenterZ - 16.0D - horizontalRadius * 2.0D && caveX <= chunkCenterX + 16.0D + horizontalRadius * 2.0D && caveZ <= chunkCenterZ + 16.0D + horizontalRadius * 2.0D) {
                    int minX = MathHelper.floor_double(caveX - horizontalRadius) - chunkX * 16 - 1;
                    int maxX = MathHelper.floor_double(caveX + horizontalRadius) - chunkX * 16 + 1;

                    int minY = MathHelper.floor_double(caveY - verticalRadius) - 1;
                    int maxY = MathHelper.floor_double(caveY + verticalRadius) + 1;

                    int minZ = MathHelper.floor_double(caveZ - horizontalRadius) - chunkZ * 16 - 1;
                    int maxZ = MathHelper.floor_double(caveZ + horizontalRadius) - chunkZ * 16 + 1;

                    if (minX < 0) {
                        minX = 0;
                    }

                    if (maxX > 16) {
                        maxX = 16;
                    }

                    if (minY < 1) {
                        minY = 1;
                    }

                    int centerWorldX = MathHelper.floor_double(caveX);
                    int centerWorldZ = MathHelper.floor_double(caveZ);
                    int maxCaveY = this.getMaxCaveYAt(centerWorldX, centerWorldZ);

                    if (maxY > maxCaveY) {
                        maxY = maxCaveY;
                    }

                    if (maxY > CHUNK_HEIGHT - 1) {
                        maxY = CHUNK_HEIGHT - 1;
                    }

                    if (minZ < 0) {
                        minZ = 0;
                    }

                    if (maxZ > 16) {
                        maxZ = 16;
                    }

                    boolean hitWater = false;

                    for (int localX = minX; !hitWater && localX < maxX; ++localX) {
                        for (int localZ = minZ; !hitWater && localZ < maxZ; ++localZ) {
                            for (int y = maxY + 1; !hitWater && y >= minY - 1; --y) {
                                if (y >= 0 && y < CHUNK_HEIGHT) {
                                    int index = this.getBlockIndex(localX, y, localZ);

                                    if (this.isWater(blocks, index, localX, y, localZ, chunkX, chunkZ)) {
                                        hitWater = true;
                                    }

                                    if (y != minY - 1 && localX != minX && localX != maxX - 1 && localZ != minZ && localZ != maxZ - 1) {
                                        y = minY;
                                    }
                                }
                            }
                        }
                    }

                    if (!hitWater) {
                        for (int localX = minX; localX < maxX; ++localX) {
                            final double normX = (localX + chunkX * 16 + 0.5D - caveX) / horizontalRadius;

                            for (int localZ = minZ; localZ < maxZ; ++localZ) {
                                final double normZ = (localZ + chunkZ * 16 + 0.5D - caveZ) / horizontalRadius;

                                int index = this.getBlockIndex(localX, maxY, localZ);
                                boolean foundTop = false;

                                if (normX * normX + normZ * normZ < 1.0D) {
                                    for (int y = maxY - 1; y >= minY; --y) {
                                        final double normY = (y + 0.5D - caveY) / verticalRadius;

                                        if (normY > -0.7D && normX * normX + normY * normY + normZ * normZ < 1.0D) {
                                            int worldX = localX + chunkX * 16;
                                            int worldZ = localZ + chunkZ * 16;

                                            if (!this.isAercloudFieldsAt(worldX, worldZ)) {
                                                if (this.isTopBlock(blocks, index, localX, y, localZ, chunkX, chunkZ)) {
                                                    foundTop = true;
                                                }

                                                this.digBlock(blocks, metadata, index, localX, y, localZ, chunkX, chunkZ, foundTop);
                                            }
                                        }

                                        --index;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            ++step;
        }
    }

    protected void func_151538_a(final World world, final int sourceChunkX, final int sourceChunkZ, final int targetChunkX, final int targetChunkZ, final Block[] blocks, final byte[] metadata) {
        int caveCount = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(12) != 0) {
            caveCount = 0;
        }

        for (int i = 0; i < caveCount; ++i) {
            final double caveX = sourceChunkX * 16 + this.rand.nextInt(16);
            final double caveZ = sourceChunkZ * 16 + this.rand.nextInt(16);

            int startWorldX = MathHelper.floor_double(caveX);
            int startWorldZ = MathHelper.floor_double(caveZ);

            if (this.isAercloudFieldsAt(startWorldX, startWorldZ)) {
                continue;
            }

            int maxStartY = this.getMaxCaveYAt(startWorldX, startWorldZ);
            final double caveY = this.rand.nextInt(this.rand.nextInt(Math.max(1, maxStartY - 8)) + 8);
            int branchCount = 1;

            if (this.rand.nextInt(4) == 0) {this.func_151542_a(this.rand.nextLong(), targetChunkX, targetChunkZ, blocks, metadata, caveX, caveY, caveZ);
                branchCount += this.rand.nextInt(4);
            }

            for (int branch = 0; branch < branchCount; ++branch) {
                final float yaw = this.rand.nextFloat() * 3.1415927F * 2.0F;
                final float pitch = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;

                float scale = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    scale *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }

                this.func_151541_a(this.rand.nextLong(), targetChunkX, targetChunkZ, blocks, metadata, caveX, caveY, caveZ, scale, yaw, pitch, 0, 0, 1.0D);
            }
        }
    }

    protected boolean isWater(final Block[] data, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ) {
        return data[index] == Blocks.flowing_water || data[index] == Blocks.water;
    }

    private boolean isTopBlock(final Block[] data, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ) {
        final BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return data[index] == biome.topBlock;
    }

    protected void digBlock(final Block[] data, final byte[] metadata, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ, final boolean foundTop) {
        final int worldX = x + chunkX * 16;
        final int worldZ = z + chunkZ * 16;

        if (this.isAercloudFieldsAt(worldX, worldZ)) {
            return;
        }

        if (this.isTooCloseToSurface(data, x, y, z)) {
            return;
        }

        final BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(worldX, worldZ);
        final Block top = biome.topBlock;
        final Block filler = biome.fillerBlock;
        final Block block = data[index];

        if (block == BlocksAether.holystone || block == BlocksAether.mossy_holystone || block == BlocksAether.aether_dirt
                || block == BlocksAether.aether_grass || block == BlocksAether.arctic_grass || block == BlocksAether.enchanted_aether_grass
                || block == BlocksAether.divine_grass || block == filler || block == top) {

            data[index] = Blocks.air;

            if (foundTop && y > 0) {
                int belowIndex = index - 1;

                if (data[belowIndex] == filler || data[belowIndex] == BlocksAether.holystone) {
                    if (Math.abs(chunkX * 16) < 150 && Math.abs(chunkZ * 16) < 150) {
                        data[belowIndex] = BlocksAether.holystone;
                        metadata[belowIndex] = 0;
                    } else {
                        data[belowIndex] = top;
                        metadata[belowIndex] = (byte) biome.field_150604_aj;
                    }
                }
            }
        }
    }

    public void generate(final IChunkProvider chunkProvider, final World world, final int chunkX, final int chunkZ, final Block[] blocks, final byte[] metadata) {
        final int range = this.range;
        this.worldObj = world;
        this.rand.setSeed(world.getSeed());

        final long seedX = this.rand.nextLong();
        final long seedZ = this.rand.nextLong();

        for (int sourceChunkX = chunkX - range; sourceChunkX <= chunkX + range; ++sourceChunkX) {
            for (int sourceChunkZ = chunkZ - range; sourceChunkZ <= chunkZ + range; ++sourceChunkZ) {
                final long mixedX = sourceChunkX * seedX;
                final long mixedZ = sourceChunkZ * seedZ;
                this.rand.setSeed(mixedX ^ mixedZ ^ world.getSeed());
                this.func_151538_a(world, sourceChunkX, sourceChunkZ, chunkX, chunkZ, blocks, metadata);
            }
        }
    }
}
