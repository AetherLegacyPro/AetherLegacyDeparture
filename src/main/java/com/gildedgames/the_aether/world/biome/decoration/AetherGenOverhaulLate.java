package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class AetherGenOverhaulLate implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
			generateAetherLate(world, random, chunkX, chunkZ);
		}
		
	}
		
		public void generateAetherLate(World world, Random rand, int x, int z) {			
			//ores
			if (AetherConfig.enableAetheralStone() == true) {	
			this.generateOre(BlocksAether.aetheral_ambrosium_ore, world, rand, x, z, 12, 13, 11, 32, 196,
					BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_zanite_ore, world, rand, x, z, 8, 9, 10, 32, 72,
					BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_arkenium_ore, world, rand, x, z, 4, 5, 6, 32, 64,
					BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_continuum_ore, world, rand, x, z, 2, 4, 8, 32, 128,
					BlocksAether.aetheral_stone);
			this.generateOre(BlocksAether.aetheral_gravitite_ore, world, rand, x, z, 3, 4, 5, 32, 35,
					BlocksAether.aetheral_stone);
			}
			
			if (AetherConfig.enableAgisoite() == true) {
			this.generateOre(BlocksAether.agiosite_ambrosium_ore, world, rand, x, z, 12, 13, 11, 24, 128,
					BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_zanite_ore, world, rand, x, z, 8, 9, 10, 24, 72,
					BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_arkenium_ore, world, rand, x, z, 4, 5, 6, 24, 64,
					BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_continuum_ore, world, rand, x, z, 2, 4, 8, 24, 128,
					BlocksAether.agiosite);
			this.generateOre(BlocksAether.agiosite_gravitite_ore, world, rand, x, z, 3, 4, 5, 24, 35,
					BlocksAether.agiosite);
			}
			
			if (AetherConfig.enableDeific() == true) {
			this.generateOre(BlocksAether.deific_ambrosium_ore, world, rand, x, z, 12, 13, 11, 8, 76,
					BlocksAether.deific);
			this.generateOre(BlocksAether.deific_zanite_ore, world, rand, x, z, 8, 9, 10, 8, 72,
					BlocksAether.deific);
			this.generateOre(BlocksAether.deific_arkenium_ore, world, rand, x, z, 4, 5, 6, 8, 64,
					BlocksAether.deific);
			this.generateOre(BlocksAether.deific_continuum_ore, world, rand, x, z, 2, 4, 8, 8, 76,
					BlocksAether.deific);
			this.generateOre(BlocksAether.deific_gravitite_ore, world, rand, x, z, 3, 4, 5, 8, 35,
					BlocksAether.deific);
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
