package com.gildedgames.the_aether.world.gen;

import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.util.MathHelper;
import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.world.gen.MapGenBase;

public class MapGenAetherCaves extends MapGenBase
{
    private static final String __OBFID = "CL_00000393";
    
    protected void func_151542_a(final long p_151542_1_, final int p_151542_3_, final int p_151542_4_, final Block[] p_151542_5_, final byte[] metadata, final double p_151542_6_, final double p_151542_8_, final double p_151542_10_) {
        this.func_151541_a(p_151542_1_, p_151542_3_, p_151542_4_, p_151542_5_, metadata, p_151542_6_, p_151542_8_, p_151542_10_, 1.0f + this.rand.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5);
    }
    
    protected void func_151541_a(final long p_151541_1_, final int p_151541_3_, final int p_151541_4_, final Block[] p_151541_5_, final byte[] metadata, double p_151541_6_, double p_151541_8_, double p_151541_10_, final float p_151541_12_, float p_151541_13_, float p_151541_14_, int p_151541_15_, int p_151541_16_, final double p_151541_17_) {
        final double d4 = p_151541_3_ * 16 + 8;
        final double d5 = p_151541_4_ * 16 + 8;
        float f3 = 0.0f;
        float f4 = 0.0f;
        final Random random = new Random(p_151541_1_);
        if (p_151541_16_ <= 0) {
            final int j1 = this.range * 16 - 16;
            p_151541_16_ = j1 - random.nextInt(j1 / 4);
        }
        boolean flag2 = false;
        if (p_151541_15_ == -1) {
            p_151541_15_ = p_151541_16_ / 2;
            flag2 = true;
        }
        final int k1 = random.nextInt(p_151541_16_ / 2) + p_151541_16_ / 4;
        final boolean flag3 = random.nextInt(6) == 0;
        while (p_151541_15_ < p_151541_16_) {
            final double d6 = 1.5 + MathHelper.sin(p_151541_15_ * 3.1415927f / p_151541_16_) * p_151541_12_ * 1.0f;
            final double d7 = d6 * p_151541_17_;
            final float f5 = MathHelper.cos(p_151541_14_);
            final float f6 = MathHelper.sin(p_151541_14_);
            p_151541_6_ += MathHelper.cos(p_151541_13_) * f5;
            p_151541_8_ += f6;
            p_151541_10_ += MathHelper.sin(p_151541_13_) * f5;
            if (flag3) {
                p_151541_14_ *= 0.92f;
            }
            else {
                p_151541_14_ *= 0.7f;
            }
            p_151541_14_ += f4 * 0.1f;
            p_151541_13_ += f3 * 0.1f;
            f4 *= 0.9f;
            f3 *= 0.75f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (!flag2 && p_151541_15_ == k1 && p_151541_12_ > 1.0f && p_151541_16_ > 0) {
                this.func_151541_a(random.nextLong(), p_151541_3_, p_151541_4_, p_151541_5_, metadata, p_151541_6_, p_151541_8_, p_151541_10_, random.nextFloat() * 0.5f + 0.5f, p_151541_13_ - 1.5707964f, p_151541_14_ / 3.0f, p_151541_15_, p_151541_16_, 1.0);
                this.func_151541_a(random.nextLong(), p_151541_3_, p_151541_4_, p_151541_5_, metadata, p_151541_6_, p_151541_8_, p_151541_10_, random.nextFloat() * 0.5f + 0.5f, p_151541_13_ + 1.5707964f, p_151541_14_ / 3.0f, p_151541_15_, p_151541_16_, 1.0);
                return;
            }
            if (flag2 || random.nextInt(4) != 0) {
                final double d8 = p_151541_6_ - d4;
                final double d9 = p_151541_10_ - d5;
                final double d10 = p_151541_16_ - p_151541_15_;
                final double d11 = p_151541_12_ + 2.0f + 16.0f;
                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
                    return;
                }
                if (p_151541_6_ >= d4 - 16.0 - d6 * 2.0 && p_151541_10_ >= d5 - 16.0 - d6 * 2.0 && p_151541_6_ <= d4 + 16.0 + d6 * 2.0 && p_151541_10_ <= d5 + 16.0 + d6 * 2.0) {
                    int i4 = MathHelper.floor_double(p_151541_6_ - d6) - p_151541_3_ * 16 - 1;
                    int l1 = MathHelper.floor_double(p_151541_6_ + d6) - p_151541_3_ * 16 + 1;
                    int j2 = MathHelper.floor_double(p_151541_8_ - d7) - 1;
                    int i5 = MathHelper.floor_double(p_151541_8_ + d7) + 1;
                    int k2 = MathHelper.floor_double(p_151541_10_ - d6) - p_151541_4_ * 16 - 1;
                    int j3 = MathHelper.floor_double(p_151541_10_ + d6) - p_151541_4_ * 16 + 1;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    if (l1 > 16) {
                        l1 = 16;
                    }
                    if (j2 < 1) {
                        j2 = 1;
                    }
                    if (i5 > 120) {
                        i5 = 120;
                    }
                    if (k2 < 0) {
                        k2 = 0;
                    }
                    if (j3 > 16) {
                        j3 = 16;
                    }
                    boolean flag4 = false;
                    for (int k3 = i4; !flag4 && k3 < l1; ++k3) {
                        for (int l2 = k2; !flag4 && l2 < j3; ++l2) {
                            for (int i6 = i5 + 1; !flag4 && i6 >= j2 - 1; --i6) {
                                final int j4 = (k3 * 16 + l2) * 128 + i6;
                                if (i6 >= 0 && i6 < 128) {
                                    final Block block = p_151541_5_[j4];
                                    if (this.isOceanBlock(p_151541_5_, j4, k3, i6, l2, p_151541_3_, p_151541_4_)) {
                                        flag4 = true;
                                    }
                                    if (i6 != j2 - 1 && k3 != i4 && k3 != l1 - 1 && l2 != k2 && l2 != j3 - 1) {
                                        i6 = j2;
                                    }
                                }
                            }
                        }
                    }
                    if (!flag4) {
                        for (int k3 = i4; k3 < l1; ++k3) {
                            final double d12 = (k3 + p_151541_3_ * 16 + 0.5 - p_151541_6_) / d6;
                            for (int j4 = k2; j4 < j3; ++j4) {
                                final double d13 = (j4 + p_151541_4_ * 16 + 0.5 - p_151541_10_) / d6;
                                int k4 = (k3 * 16 + j4) * 128 + i5;
                                boolean flag5 = false;
                                if (d12 * d12 + d13 * d13 < 1.0) {
                                    for (int l3 = i5 - 1; l3 >= j2; --l3) {
                                        final double d14 = (l3 + 0.5 - p_151541_8_) / d7;
                                        if (d14 > -0.7 && d12 * d12 + d14 * d14 + d13 * d13 < 1.0) {
                                            final Block block2 = p_151541_5_[k4];
                                            if (this.isTopBlock(p_151541_5_, k4, k3, l3, j4, p_151541_3_, p_151541_4_)) {
                                                flag5 = true;
                                            }
                                            this.digBlock(p_151541_5_, metadata, k4, k3, l3, j4, p_151541_3_, p_151541_4_, flag5);
                                            this.worldObj.getBiomeGenForCoords(k3 + p_151541_3_ * 16, j4 + p_151541_4_ * 16);
                                        }
                                        --k4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ++p_151541_15_;
        }
    }
    
    protected void func_151538_a(final World p_151538_1_, final int p_151538_2_, final int p_151538_3_, final int p_151538_4_, final int p_151538_5_, final Block[] p_151538_6_, final byte[] metadata) {
        int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
        if (this.rand.nextInt(7) != 0) {
            i1 = 0;
        }
        for (int j1 = 0; j1 < i1; ++j1) {
            final double d0 = p_151538_2_ * 16 + this.rand.nextInt(16);
            final double d2 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            final double d3 = p_151538_3_ * 16 + this.rand.nextInt(16);
            int k1 = 1;
            if (this.rand.nextInt(4) == 0) {
                this.func_151542_a(this.rand.nextLong(), p_151538_4_, p_151538_5_, p_151538_6_, metadata, d0, d2, d3);
                k1 += this.rand.nextInt(4);
            }
            for (int l1 = 0; l1 < k1; ++l1) {
                final float f = this.rand.nextFloat() * 3.1415927f * 2.0f;
                final float f2 = (this.rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float f3 = this.rand.nextFloat() * 2.0f + this.rand.nextFloat();
                if (this.rand.nextInt(10) == 0) {
                    f3 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0f + 1.0f;
                }
                this.func_151541_a(this.rand.nextLong(), p_151538_4_, p_151538_5_, p_151538_6_, metadata, d0, d2, d3, f3, f, f2, 0, 0, 1.0);
            }
        }
    }
    
    protected boolean isOceanBlock(final Block[] data, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ) {
        return data[index] == Blocks.flowing_water || data[index] == Blocks.water;
    }
    
    private boolean isTopBlock(final Block[] data, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ) {
        final BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return data[index] == biome.topBlock;
    }
    
    protected void digBlock(final Block[] data, final byte[] metadata, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ, final boolean foundTop) {
        final BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        final Block top = biome.topBlock;
        final Block filler = biome.fillerBlock;
        final Block block = data[index];
        if (block == BlocksAether.holystone || block == BlocksAether.mossy_holystone || block == BlocksAether.aether_dirt || block == BlocksAether.aether_grass || block == filler || block == top) {
            data[index] = null;
            if (foundTop && (data[index - 1] == filler || data[index - 1] == BlocksAether.holystone)) {
                if (Math.abs(chunkX * 16) < 150 && Math.abs(chunkZ * 16) < 150) {
                    data[index - 1] = BlocksAether.holystone;
                    metadata[index - 1] = 0;
                }
                else {
                    data[index - 1] = top;
                    metadata[index - 1] = (byte)biome.field_150604_aj;
                }
            }
        }
    }
    
    public void generate(final IChunkProvider p_151539_1_, final World p_151539_2_, final int p_151539_3_, final int p_151539_4_, final Block[] p_151539_5_, final byte[] metadata) {
        final int k = this.range;
        this.worldObj = p_151539_2_;
        this.rand.setSeed(p_151539_2_.getSeed());
        final long l = this.rand.nextLong();
        final long i1 = this.rand.nextLong();
        for (int j1 = p_151539_3_ - k; j1 <= p_151539_3_ + k; ++j1) {
            for (int k2 = p_151539_4_ - k; k2 <= p_151539_4_ + k; ++k2) {
                final long l2 = j1 * l;
                final long i2 = k2 * i1;
                this.rand.setSeed(l2 ^ i2 ^ p_151539_2_.getSeed());
                this.func_151538_a(p_151539_2_, j1, k2, p_151539_3_, p_151539_4_, p_151539_5_, metadata);
            }
        }
    }
}
    


