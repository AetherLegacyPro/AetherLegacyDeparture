package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AetherIIDungeonGen;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AuraliteGeoGen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class AetherGenStoneOverhaul implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
			generateAether(world, random, chunkX, chunkZ);
		}
		
	}
		
		public void generateAether(World world, Random rand, int x, int z) {
						
			//aether stones
			if (AetherConfig.enableAetheralStone() == true) {	
			this.generateOre(BlocksAether.aetheral_stone, world, rand, x, z, 1, 100, 1, 32, 196,
					BlocksAether.holystone);
			}
			if (AetherConfig.enableAgisoite() == true) {	
			this.generateOre(BlocksAether.agiosite, world, rand, x, z, 1, 90, 1, 24, 128,
					BlocksAether.holystone);
			}
			if (AetherConfig.enableDeific() == true) {
			this.generateOre(BlocksAether.deific, world, rand, x, z, 2, 75, 1, 8, 76,
					BlocksAether.holystone);
			}
			this.generateOre(BlocksAether.icestone, world, rand, x, z, 3, 50, 1, 40, 256,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.aether_dirt, world, rand, x, z, 3, 30, 1, 60, 128,
					BlocksAether.holystone);
			
			//ores
			this.generateOre(BlocksAether.ambrosium_ore, world, rand, x, z, 12, 14, 18, 0, 128,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.zanite_ore, world, rand, x, z, 8, 9, 10, 0, 72,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.arkenium_ore, world, rand, x, z, 3, 5, 6, 0, 64,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.continuum_ore, world, rand, x, z, 1, 5, 8, 0, 128,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.gravitite_ore, world, rand, x, z, 1, 3, 6, 8, 35,
					BlocksAether.holystone);
			this.generateOre(BlocksAether.primeval_artifact, world, rand, x, z, 1, 5, 6, 0, 32,
					BlocksAether.holystone);
			
			if(AetherConfig.enableAuraliteGeos() == true) {
				int x8;
				int z8;
				x8 = x * 16 + rand.nextInt(16) + 8;
				z8 = z * 16 + rand.nextInt(16) + 8;
				if(rand.nextInt(80) == 0) {
					new AuraliteGeoGen().generate(world, rand, x8, rand.nextInt(10) + 25, z8);
				}
		   	   
		   }
			
			if(Loader.isModLoaded("aether") && AetherConfig.enableAetherIIDungeon() == true) {
				int x8;
				int z8;
				x8 = x * 16 + rand.nextInt(16) + 8;
				z8 = z * 16 + rand.nextInt(16) + 8;
				if(rand.nextInt(40) == 0) {
					new AetherIIDungeonGen().generate(world, rand, x8, rand.nextInt(12) + 30, z8);
			 }
		   	   
		   }
		}
	
	    
	 	public void generateOre(Block block, World world, Random random,
				int chunkX, int chunkZ, int minVienSize, int maxVienSize,
				int chance, int minY, int maxY, Block generateIn) {
			int VienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
			int hightRange = maxY - minY;
			WorldGenMinable gen = new WorldGenMinable(block, VienSize, generateIn);
			for (int i = 0; i < chance; i++) {
				int xRand = chunkX * 16 + random.nextInt(16);
				int yRand = random.nextInt(hightRange) + minY;
				int zRand = chunkZ * 16 + random.nextInt(16);
				gen.generate(world, random, xRand, yRand, zRand);
			}
		}
	   	   

}
