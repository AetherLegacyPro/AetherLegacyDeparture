package com.gildedgames.the_aether.world.biome.decoration.overhaul;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenDivineTree;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DivineIsland extends WorldGenerator {
   int bumpsWide1;
   int bumpsLong1;
   int bumpHeightVarTop1;
   int bumpHeightVarBottom1;
   int bumpHeightMinTop1;
   int bumpHeightMinBottom1;
   int blocksPerBumpX1;
   int blocksPerBumpZ1;
   int blocksPerBumpTopY1;
   int blocksPerBumpBottomY1;
   int blurPassesTop1;
   int blurPassesBottom1;
   int spikeHeightVarTop1;
   int spikeHeightVarBottom1;
   int radialDistanceSamples1;
   double radialDistanceMin1;
   double radialDistanceVar1;
   int radialDistanceScaling1;
   int radialDistanceBlurPasses1;
   int centerX;
   int centerZ;
   Block genOre;
   int width;
   int length;

   public DivineIsland(int bumpsWide1, int bumpsLong1, int bumpHeightVarTop1, int bumpHeightVarBottom1, int bumpHeightMinTop1, int bumpHeightMinBottom1, int blocksPerBumpX1, int blocksPerBumpZ1, int blocksPerBumpTopY1, int blocksPerBumpBottomY1, int blurPassesTop1, int blurPassesBottom1, int spikeHeightVarTop1, int spikeHeightVarBottom1, int radialDistanceSamples1, double radialDistanceMin1, double radialDistanceVar1, int radialDistanceScaling1, int radialDistanceBlurPasses1, Block genOre) {
      this.bumpsWide1 = bumpsWide1;
      this.bumpsLong1 = bumpsLong1;
      this.bumpHeightVarTop1 = bumpHeightVarTop1;
      this.bumpHeightVarBottom1 = bumpHeightVarBottom1;
      this.bumpHeightMinTop1 = bumpHeightMinTop1;
      this.bumpHeightMinBottom1 = bumpHeightMinBottom1;
      this.blocksPerBumpX1 = blocksPerBumpX1;
      this.blocksPerBumpZ1 = blocksPerBumpZ1;
      this.blocksPerBumpTopY1 = blocksPerBumpTopY1;
      this.blocksPerBumpBottomY1 = blocksPerBumpBottomY1;
      this.blurPassesTop1 = blurPassesTop1;
      this.blurPassesBottom1 = blurPassesBottom1;
      this.spikeHeightVarTop1 = spikeHeightVarTop1;
      this.spikeHeightVarBottom1 = spikeHeightVarBottom1;
      this.radialDistanceSamples1 = radialDistanceSamples1;
      this.radialDistanceMin1 = radialDistanceMin1;
      this.radialDistanceVar1 = radialDistanceVar1;
      this.radialDistanceScaling1 = radialDistanceScaling1;
      this.radialDistanceBlurPasses1 = radialDistanceBlurPasses1;
      this.genOre = genOre;
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      double[] ad = this.createRadialDistanceMap(rand, this.radialDistanceSamples1, this.radialDistanceMin1, this.radialDistanceVar1, this.radialDistanceScaling1, this.radialDistanceBlurPasses1);
      this.generateHalf(world, rand, x, y, z, false, this.bumpsWide1, this.bumpsLong1, this.bumpHeightMinBottom1, this.bumpHeightVarBottom1, this.blocksPerBumpX1, this.blocksPerBumpZ1, this.blocksPerBumpBottomY1, this.blurPassesBottom1, this.spikeHeightVarBottom1, ad, this.radialDistanceSamples1 * this.radialDistanceScaling1);
      this.generateHalf(world, rand, x, y, z, true, this.bumpsWide1, this.bumpsLong1, this.bumpHeightMinTop1, this.bumpHeightVarTop1, this.blocksPerBumpX1, this.blocksPerBumpZ1, this.blocksPerBumpTopY1, this.blurPassesTop1, this.spikeHeightVarTop1, ad, this.radialDistanceSamples1 * this.radialDistanceScaling1);
      this.generateTrees(world, rand, x, y, z);
      this.generateOre(world, rand, x, y, z);
      return true;
   }

   private double[] createRadialDistanceMap(Random rand, int i, double d, double d1, int j, int k) {
      double[] ad = this.initRadialDistanceMap(rand, i, d, d1);
      double[] ad1 = this.scaleRadialDistanceMap(ad, i, j);
      double[] ad2 = ad1;

      for(int l = 0; l < k; ++l) {
         ad2 = this.blurRadialDistanceMap(ad2, i * j);
      }

      return ad2;
   }

   private double[] initRadialDistanceMap(Random random, int i, double d, double d1) {
      double[] ad = new double[i];

      for(int j = 0; j < i; ++j) {
         double d2 = d + random.nextDouble() * d1;
         ad[j] = d2;
      }

      return ad;
   }

   private double[] scaleRadialDistanceMap(double[] ad, int i, int j) {
      double[] ad1 = new double[i * j];

      for(int k = 0; k < i; ++k) {
         double d = ad[k];

         for(int l = 0; l < j; ++l) {
            ad1[k * j + l] = d;
         }
      }

      return ad1;
   }

   private double[] blurRadialDistanceMap(double[] ad, int i) {
      double[] ad1 = new double[i];

      for(int j = 0; j < i; ++j) {
         double d = ad[j];
         double d1;
         if (j == 0) {
            d1 = ad[i - 1];
         } else {
            d1 = ad[j - 1];
         }

         double d2;
         if (j == i - 1) {
            d2 = ad[0];
         } else {
            d2 = ad[j + 1];
         }

         double d3 = (d + d1 + d2) / 3.0D;
         ad1[j] = d3;
      }

      return ad1;
   }

   private void generateHalf(World world, Random rand, int x, int y, int z, boolean flag, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2, double[] ad, int i3) {
      int[][] ai = this.generateHeightMap(rand, l, i1, j1, k1, l1, i2, j2, k2, l2, ad, i3);
      this.addToWorld(world, rand, x, y, z, flag, ai, l * l1, i1 * i2);
      if (flag) {
         this.generateTrees(world, rand, x, y + j1, z);
         this.generateOre(world, rand, x, y, z);
      }

   }

   private int[][] generateHeightMap(Random rand, int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, double[] ad, int j2) {
      int k2 = i * i1;
      int l2 = j * j1;
      int[][] ai = this.createBumpGrid(rand, i, j, k, l);
      int[][] ai1 = this.scaleBumpGrid(ai, i, j, i1, j1, k1);
      int[][] ai2 = this.applyRadialDistanceMap(ai1, ad, j2, k2, l2);

      for(int i3 = 0; i3 < l1; ++i3) {
         ai2 = this.blurHeightMap(ai2, k2, l2);
      }

      this.addSpikes(rand, ai2, k2, l2, i2);
      return ai2;
   }

   private int[][] createBumpGrid(Random random, int i, int j, int k, int l) {
      int[][] ai = new int[i][j];

      for(int i1 = 0; i1 < i; ++i1) {
         for(int j1 = 0; j1 < j; ++j1) {
            int k1 = random.nextInt(l) + k;
            ai[i1][j1] = k1;
         }
      }

      return ai;
   }

   private int[][] scaleBumpGrid(int[][] ai, int i, int j, int k, int l, int i1) {
      int[][] ai1 = new int[i * k][j * l];

      for(int j1 = 0; j1 < k; ++j1) {
         for(int k1 = 0; k1 < l; ++k1) {
            for(int l1 = 0; l1 < i; ++l1) {
               for(int i2 = 0; i2 < j; ++i2) {
                  int j2 = ai[l1][i2];
                  j2 *= i1;
                  ai1[l1 * k + j1][i2 * l + k1] = j2;
               }
            }
         }
      }

      return ai1;
   }

   private int[][] applyRadialDistanceMap(int[][] ai, double[] ad, int i, int j, int k) {
      int[][] ai1 = new int[j][k];
      this.centerX = j / 2;
      this.centerZ = k / 2;
      this.width = j;
      this.length = k;

      for(int l = 0; l < j; ++l) {
         for(int i1 = 0; i1 < k; ++i1) {
            int j1 = l - this.centerX;
            int k1 = i1 - this.centerZ;
            double d = Math.sqrt(j1 * j1 + k1 * k1);
            if (d == 0.0D) {
               d = 0.001D;
            }

            double d1 = (double)j1 / d;
            double d2 = (double)k1 / d;
            if (d1 == 0.0D) {
               d1 = 0.001D;
            }

            double d3 = (double)this.centerX * d1 * d1 + (double)this.centerZ * d2 * d2;
            double d4 = d / d3;

            double d5;
            for(d5 = Math.atan2(d2, d1); d5 < 0.0D; d5 += 6.283185307179586D) {
            }

            d5 %= 6.283185307179586D;
            double d6 = d5 / 6.283185307179586D;
            int l1 = (int)(d6 * (double)i);
            double d7 = ad[l1];
            if (d4 > d7) {
               ai1[l][i1] = 0;
            } else {
               ai1[l][i1] = ai[l][i1];
            }
         }
      }

      return ai1;
   }

   private int[][] blurHeightMap(int[][] ai, int i, int j) {
      int[][] ai1 = new int[i][j];

      for(int k = 0; k < i; ++k) {
         for(int l = 0; l < j; ++l) {
            int i1 = ai[k][l];
            int j1;
            if (k == 0) {
               j1 = 0;
            } else {
               j1 = ai[k - 1][l];
            }

            int k1;
            if (k == i - 1) {
               k1 = 0;
            } else {
               k1 = ai[k + 1][l];
            }

            int l1;
            if (l == 0) {
               l1 = 0;
            } else {
               l1 = ai[k][l - 1];
            }

            int i2;
            if (l == j - 1) {
               i2 = 0;
            } else {
               i2 = ai[k][l + 1];
            }

            int j2 = (i1 + j1 + k1 + i2 + l1) / 5;
            ai1[k][l] = j2;
         }
      }

      return ai1;
   }

   private void addSpikes(Random random, int[][] ai, int i, int j, int k) {
      for(int l = 0; l < i; ++l) {
         for(int i1 = 0; i1 < j; ++i1) {
            int j1 = ai[l][i1];
            if (j1 > 0) {
               int k1 = random.nextInt(k);
               int l1 = j1 + k1;
               ai[l][i1] = l1;
            }
         }
      }

   }

   private void addToWorld(World world, Random rand, int x, int y, int z, boolean flag, int[][] ai, int l, int i1) {
	    for (int j1 = 0; j1 < l; ++j1) {
	        for (int k1 = 0; k1 < i1; ++k1) {
	            int l1 = ai[j1][k1];
	            int i2 = x + j1;
	            int j2 = z + k1;

	            for (int k2 = 0; k2 < l1; ++k2) {
	                int l2 = flag ? y + k2 : y - k2 - 1;
	                boolean surface = flag && k2 == l1 - 1 || !flag && k2 == 0;

	                Block block = this.materialAtLevel(rand, l2 - y, surface);
	                world.setBlock(i2, l2, j2, block, 0, 2);
	            }
	        }
	    }
	}

   private Block materialAtLevel(Random rand, int i, boolean flag) {
      byte byte0 = -1;
      byte byte1 = 4;
      int j = byte0 - rand.nextInt(byte1);
      if (i < j) {
         return rand.nextInt(10) == 0 ? BlocksAether.holystone : BlocksAether.enchanted_holystone;
      	}
      if (i == j) {
    	  int rand3 = (int)(1 + Math.random() * 3);
          if (rand3 == 1 ) {
        	  return rand.nextInt(14) == 0 ? BlocksAether.empyrean_ore_2 : BlocksAether.enchanted_holystone;   
          }
          if (rand3 == 2 ) {
        	  return rand.nextInt(12) == 0 ? BlocksAether.ambrosium_block : BlocksAether.enchanted_holystone;   
          }
        	  return rand.nextInt(10) == 0 ? BlocksAether.amethyst_glowstone : BlocksAether.enchanted_holystone;  
        }
      else {
         return flag ? BlocksAether.divine_grass : BlocksAether.aether_dirt;
      }
   }

   private void generateOre(World world, Random rand, int x, int y, int z) {
      int radius = this.bumpsLong1 > this.bumpsWide1 ? this.bumpsLong1 : this.bumpsWide1;

      for(int i = 0; i < 8 + rand.nextInt(radius); ++i) {
         int x1 = x + this.centerX + rand.nextInt(this.width) - this.centerX;
         int y1 = y - rand.nextInt(10);
         int z1 = z + this.centerZ + rand.nextInt(this.length) - this.centerZ;
         if (world.getBlock(x1, y1, z1).isReplaceableOreGen(world, x, y, z, BlocksAether.enchanted_holystone)) {
            world.setBlock(x1, y1, z1, BlocksAether.empyrean_ore_2);

            for(int yTest = y1; yTest < 128; ++yTest) {
               if (world.getBlock(x1, yTest, z1).isReplaceable(world, x1, yTest, z1) && (world.getBlock(x1, yTest - 1, z1) == BlocksAether.divine_grass || world.getBlock(x1, yTest - 1, z1) == BlocksAether.aether_dirt)) {
                  world.setBlock(x1, yTest, z1, BlocksAether.divine_lily);
                  break;
               }             
            }
         }
      }

   }

   private static AetherGenDivineTree tree = new AetherGenDivineTree(BlocksAether.divine_oak_leaves, 40, true);
   
   private void generateTrees(World world, Random rand, int x, int y, int z) {
      int radius = this.bumpsLong1 > this.bumpsWide1 ? this.bumpsLong1 : this.bumpsWide1;

      int i6;
      int j1;
      int k1;
      int l1;
      for(i6 = 0; i6 < 5 + rand.nextInt(radius); ++i6) {
         j1 = x + this.centerX + rand.nextInt(this.width) - this.centerX;
         k1 = z + this.centerZ + rand.nextInt(this.length) - this.centerZ;
         l1 = world.getHeightValue(j1, k1);
         if (l1 > 100) {
            if (rand.nextInt(2) != 0) {
            	tree.generate(world, rand, j1, l1, k1);
            } 
            else {
               tree.generate(world, rand, j1, l1, k1);
            }
         }
      }
     
      for(i6 = 0; i6 < 10 + rand.nextInt(20); ++i6) {
         j1 = x + this.centerX + rand.nextInt(this.width) - this.centerX;
         k1 = z + this.centerZ + rand.nextInt(this.length) - this.centerZ;
         l1 = world.getHeightValue(j1, k1);
         if (l1 > 100) {
           tree.generate(world, rand, j1, l1, k1);
         }
      }

      this.generateTallGrass(world, rand, x, y, z);
   }

   private void generateTallGrass(World world, Random rand, int x, int y, int z) {
      for(int i1 = 0; i1 < 16; ++i1) {
         int j1 = x + this.centerX + rand.nextInt(this.width) - this.centerX;
         int k1 = z + this.centerZ + rand.nextInt(this.length) - this.centerZ;
         int l1 = world.getHeightValue(j1, k1);
         if (l1 > 100) {
        	 
        int chance = (int)(1 + Math.random() * 5);
     	switch (chance)
        {
        case 1: (new WorldGenTallGrass(BlocksAether.divine_aether_tallgrass, 1)).generate(world, rand, j1, l1, k1);
        	break;
        case 2: (new WorldGenTallGrass(BlocksAether.divine_stalk, 1)).generate(world, rand, j1, l1, k1);
        	break;
        case 3: (new WorldGenTallGrass(BlocksAether.divine_bloom, 1)).generate(world, rand, j1, l1, k1);
        	break;
        case 4: (new WorldGenTallGrass(BlocksAether.divine_lily, 1)).generate(world, rand, j1, l1, k1);
        	break;
        case 5: (new WorldGenTallGrass(BlocksAether.divine_aether_tallgrass, 1)).generate(world, rand, j1, l1, k1);
        	break;
        }
     	
         }
      }

   }
}
