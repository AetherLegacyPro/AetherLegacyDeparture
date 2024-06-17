package com.gildedgames.the_aether.world.biome.decoration.overhaul;
	
import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class GoldenIslandWorldGen implements IWorldGenerator {
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
	         this.generateAether(world, rand, chunkX * 16, chunkZ * 16);
	      }
	   }
	
	int aether_random = AetherConfig.golden_island_rarity; 
		
	public void generateAether(World world, Random rand, int x, int z) {
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x + 16, z + 16);
	      if ((biome == AetherWorld.aether_biome) && AetherConfig.golden_island_rarity != 0 && rand.nextInt(aether_random) == 0 && AetherConfig.golden_island_enable == true) {
	         byte byte0 = 20;
	         byte byte1 = 20;
	         byte byte2 = 35;
	         byte byte3 = 35;
	         byte byte4 = 2;
	         byte byte5 = 5;
	         byte byte6 = 3;
	         byte byte7 = 7;
	         byte bumpHeightMinTop1 = 2;
	         byte bumpHeightMinBottom1 = 2;
	         byte byte10 = 2;
	         int l3 = rand.nextInt(3) + 2;
	         byte byte11 = 5;
	         byte byte12 = 5;
	         int i4 = 1;
	         byte byte13 = 2;
	         int j4 = 1;
	         byte byte14 = 4;
	         byte blurPassesTop1 = 5;
	         byte blurPassesBottom1 = 5;
	         int spikeHeightVarTop1 = 1;
	         byte spikeHeightVarBottom1 = 3;
	         byte radialDistanceSamples1 = 18;
	         double radialDistanceMin1 = 0.0D;
	         double radialDistanceVar1 = 1.0D;
	         byte radialDistanceScaling1 = 5;
	         byte radialDistanceBlurPasses1 = 5;
	         byte byte21 = 35;
	         if (rand.nextInt(byte21) == 0) {
	            int x1 = x + rand.nextInt(16) + 8;
	            int y1 = rand.nextInt(16) + 150;
	            int z1 = z + rand.nextInt(16) + 8;
	            int bumpsWide1;
	            if (byte2 <= byte0) {
	               bumpsWide1 = byte0;
	            } else {
	               bumpsWide1 = rand.nextInt(byte2 - byte0) + byte0;
	            }

	            int bumpsLong1;
	            if (byte3 <= byte1) {
	               bumpsLong1 = byte1;
	            } else {
	               bumpsLong1 = rand.nextInt(byte3 - byte1) + byte1;
	            }

	            int bumpHeightVarTop1;
	            if (byte6 <= byte4) {
	               bumpHeightVarTop1 = byte4;
	            } else {
	               bumpHeightVarTop1 = rand.nextInt(byte6 - byte4) + byte4;
	            }

	            int bumpHeightVarBottom1;
	            if (byte7 <= byte5) {
	               bumpHeightVarBottom1 = byte5;
	            } else {
	               bumpHeightVarBottom1 = rand.nextInt(byte7 - byte5) + byte5;
	            }

	            int blocksPerBumpX1;
	            if (byte11 <= byte10) {
	               blocksPerBumpX1 = byte10;
	            } else {
	               blocksPerBumpX1 = rand.nextInt(byte11 - byte10) + byte10;
	            }

	            int blocksPerBumpZ1;
	            if (byte12 <= l3) {
	               blocksPerBumpZ1 = l3;
	            } else {
	               blocksPerBumpZ1 = rand.nextInt(byte12 - l3) + l3;
	            }

	            int blocksPerBumpTopY1;
	            if (j4 <= i4) {
	               blocksPerBumpTopY1 = i4;
	            } else {
	               blocksPerBumpTopY1 = rand.nextInt(j4 - i4) + i4;
	            }

	            int blocksPerBumpBottomY1;
	            if (byte14 <= byte13) {
	               blocksPerBumpBottomY1 = byte13;
	            } else {
	               blocksPerBumpBottomY1 = rand.nextInt(byte14 - byte13) + byte13;
	            }
	            
	            if (AetherConfig.enableLogReporting() == true) {
	            System.out.println("Enchanted island generated at (X:" + x1 + ", Z:" + z1 + ")");
	            System.out.println("May take a moment be patient!");
	            }
	            
	            Block genOre = BlocksAether.gravitite_ore;
	            (new GoldenIsland(bumpsWide1, bumpsLong1, bumpHeightVarTop1, bumpHeightVarBottom1, bumpHeightMinTop1, bumpHeightMinBottom1, blocksPerBumpX1, blocksPerBumpZ1, blocksPerBumpTopY1, blocksPerBumpBottomY1, blurPassesTop1, blurPassesBottom1, spikeHeightVarTop1, spikeHeightVarBottom1, radialDistanceSamples1, radialDistanceMin1, radialDistanceVar1, radialDistanceScaling1, radialDistanceBlurPasses1, genOre)).generate(world, rand, x1, y1, z1);
	            (new GoldenIsland(bumpsWide1, bumpsLong1, bumpHeightVarTop1, bumpHeightVarBottom1, bumpHeightMinTop1, bumpHeightMinBottom1, blocksPerBumpX1, blocksPerBumpZ1, blocksPerBumpTopY1, blocksPerBumpBottomY1, blurPassesTop1, blurPassesBottom1, spikeHeightVarTop1, spikeHeightVarBottom1, radialDistanceSamples1, radialDistanceMin1, radialDistanceVar1, radialDistanceScaling1, radialDistanceBlurPasses1, genOre)).generate(world, rand, x1 - 32, y1, z1 + 32);
	            (new GoldenIsland(bumpsWide1, bumpsLong1, bumpHeightVarTop1, bumpHeightVarBottom1, bumpHeightMinTop1, bumpHeightMinBottom1, blocksPerBumpX1, blocksPerBumpZ1, blocksPerBumpTopY1, blocksPerBumpBottomY1, blurPassesTop1, blurPassesBottom1, spikeHeightVarTop1, spikeHeightVarBottom1, radialDistanceSamples1, radialDistanceMin1, radialDistanceVar1, radialDistanceScaling1, radialDistanceBlurPasses1, genOre)).generate(world, rand, x1 + 32, y1, z1 - 32);	            
	            (new GoldenIsland(bumpsWide1, bumpsLong1, bumpHeightVarTop1, bumpHeightVarBottom1, bumpHeightMinTop1, bumpHeightMinBottom1, blocksPerBumpX1, blocksPerBumpZ1, blocksPerBumpTopY1, blocksPerBumpBottomY1, blurPassesTop1, blurPassesBottom1, spikeHeightVarTop1, spikeHeightVarBottom1, radialDistanceSamples1, radialDistanceMin1, radialDistanceVar1, radialDistanceScaling1, radialDistanceBlurPasses1, genOre)).generate(world, rand, x1 - 32, y1, z1 - 32);
	            (new GoldenIsland(bumpsWide1, bumpsLong1, bumpHeightVarTop1, bumpHeightVarBottom1, bumpHeightMinTop1, bumpHeightMinBottom1, blocksPerBumpX1, blocksPerBumpZ1, blocksPerBumpTopY1, blocksPerBumpBottomY1, blurPassesTop1, blurPassesBottom1, spikeHeightVarTop1, spikeHeightVarBottom1, radialDistanceSamples1, radialDistanceMin1, radialDistanceVar1, radialDistanceScaling1, radialDistanceBlurPasses1, genOre)).generate(world, rand, x1 + 32, y1, z1 + 32);

	         }
	       }							
		 }
		    	   	   
}
