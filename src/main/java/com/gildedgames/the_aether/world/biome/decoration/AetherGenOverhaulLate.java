package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AetherIIDungeonGen;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AuraliteGeoGen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class AetherGenOverhaulLate implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
			generateAetherLate(world, random, chunkX, chunkZ);
		}
		
	}
		
		public void generateAetherLate(World world, Random rand, int x, int z) {
			//Holystone Ores
			this.generateOre(BlocksAether.ambrosium_ore, world, rand, x, z, 12, 14, 18, 0, 128, BlocksAether.holystone);
			this.generateOre(BlocksAether.zanite_ore, world, rand, x, z, 9, 10, 12, 0, 72, BlocksAether.holystone);
			this.generateOre(BlocksAether.arkenium_ore, world, rand, x, z, 4, 6, 7, 0, 64, BlocksAether.holystone);
			this.generateOre(BlocksAether.continuum_ore, world, rand, x, z, 1, 4, 7, 32, 128, BlocksAether.holystone);
			this.generateOre(BlocksAether.gravitite_ore, world, rand, x, z, 2, 6, 7, 0, 38, BlocksAether.holystone);
			this.generateOre(BlocksAether.primeval_artifact, world, rand, x, z, 1, 4, 7, 0, 32, BlocksAether.holystone);
			
			//Aetheral Stone Ores
			if (AetherConfig.enableAetheralStone()) {	
			this.generateOre(BlocksAether.aetheral_ambrosium_ore, world, rand, x, z, 12, 13, 11, 32, 196, BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_zanite_ore, world, rand, x, z, 8, 9, 10, 32, 196, BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_arkenium_ore, world, rand, x, z, 4, 5, 6, 32, 196, BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_continuum_ore, world, rand, x, z, 2, 4, 8, 32, 196, BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_gravitite_ore, world, rand, x, z, 3, 4, 5, 32, 40, BlocksAether.aetheral_stone);
			}
			
			//Agiosite Ores
			if (AetherConfig.enableAgisoite()) {
			this.generateOre(BlocksAether.agiosite_ambrosium_ore, world, rand, x, z, 12, 13, 11, 16, 66, BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_zanite_ore, world, rand, x, z, 8, 9, 10, 16, 66, BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_arkenium_ore, world, rand, x, z, 4, 5, 6, 16, 66, BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_continuum_ore, world, rand, x, z, 2, 4, 8, 16, 66, BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_gravitite_ore, world, rand, x, z, 3, 4, 5, 16, 34, BlocksAether.agiosite);
			}
			
			//Deific Ores
			if (AetherConfig.enableDeific()) {
			this.generateOre(BlocksAether.deific_ambrosium_ore, world, rand, x, z, 12, 13, 11, 0, 24, BlocksAether.deific);
			this.generateOre(BlocksAether.deific_zanite_ore, world, rand, x, z, 8, 9, 10, 0, 24, BlocksAether.deific);
			this.generateOre(BlocksAether.deific_arkenium_ore, world, rand, x, z, 4, 5, 6, 0, 24, BlocksAether.deific);
			this.generateOre(BlocksAether.deific_continuum_ore, world, rand, x, z, 2, 4, 8, 0, 24, BlocksAether.deific);
			this.generateOre(BlocksAether.deific_gravitite_ore, world, rand, x, z, 3, 4, 5, 0, 24, BlocksAether.deific);
			}
			
			if(AetherConfig.enableAuraliteGeos()) {
				int x8;
				int z8;
				x8 = x * 16 + rand.nextInt(16) + 8;
				z8 = z * 16 + rand.nextInt(16) + 8;
				if(rand.nextInt(80) == 0) {
					new AuraliteGeoGen().generate(world, rand, x8, rand.nextInt(10) + 25, z8);
				}
		   	   
		   }
			
			if(Loader.isModLoaded("aether") && AetherConfig.enableAetherIIDungeon()) {
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
