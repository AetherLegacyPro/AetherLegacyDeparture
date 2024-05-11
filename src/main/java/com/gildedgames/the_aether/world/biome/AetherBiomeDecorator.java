package com.gildedgames.the_aether.world.biome;

import java.util.Calendar;
import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.world.biome.decoration.*;
import com.gildedgames.the_aether.world.gen.MapGenAetherCaves;
import com.gildedgames.the_aether.world.biome.decoration.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class AetherBiomeDecorator extends BiomeDecorator {

	public World world;

	public Random rand;

	public BiomeGenBase aetherBiome;
	
	public AetherGenFoilage foilage = new AetherGenFoilage();
	
	public AetherGenDungeonOakTree golden_oak_tree_dungeon = new AetherGenDungeonOakTree();

	public AetherGenFloatingIsland crystal_island = new AetherGenFloatingIsland();
	
	public AetherGenVoidFloatingIsland void_island = new AetherGenVoidFloatingIsland();

	public AetherGenLiquids liquid_overhang = new AetherGenLiquids();

	public AetherGenHolidayTree holiday_tree = new AetherGenHolidayTree();
	
	public AetherGenLakes aether_lakes = new AetherGenLakes();

	public AetherBiomeDecorator() {
		super();
	}

	@Override
	public void decorateChunk(World worldIn, Random random, BiomeGenBase biome, int x, int z) {
		if (this.world != null) {
			System.out.println("Already decorating");
		} else {
			this.world = worldIn;
			this.rand = random;
			this.chunk_X = x;
			this.chunk_Z = z;
			this.aetherBiome = biome;
			this.genDecorations(biome);
			this.world = null;
			this.rand = null;
		}
				
	}

	@Override
	protected void genDecorations(BiomeGenBase biome) {
		
		if (this.shouldSpawn(70)) {
			this.crystal_island.generate(this.world, this.rand, this.chunk_X + 8, this.nextInt(96) + 32, this.chunk_Z + 8);
		}
		
		if (AetherConfig.enableVoidTree() == true) {
			if (this.shouldSpawn(120)) {
				this.void_island.generate(this.world, this.rand, this.chunk_X + 20, this.nextInt(5) + 15, this.chunk_Z + 20);
				}
		}
		
		if ((AetherConfig.shouldLoadHolidayContent()) || (AetherConfig.allowSeasonalChristmas() &&
				(Calendar.getInstance().get(Calendar.MONTH) + 1 == 12 || Calendar.getInstance().get(Calendar.MONTH) + 1 == 1))) {
			if (this.shouldSpawn(15)) {
				int x = this.chunk_X + 8;
				int z = this.chunk_Z + 8;
				int y = this.world.getHeightValue(x, z);
				this.holiday_tree.generate(this.world, this.rand, x, y, z);
			}
		}

		for (int i = 0; i < 25; i++)
		{
			int x = this.chunk_X + this.nextInt(16);
			int z = this.chunk_Z + this.nextInt(16);
			int y = this.world.getHeightValue(x, z);
			this.golden_oak_tree_dungeon.generate(this.world, this.rand, x, y, z);
		}
	

		if (this.shouldSpawn(10)) {
			(new WorldGenLakes(Blocks.water)).generate(this.world, this.rand, this.chunk_X + this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.chunk_Z + this.rand.nextInt(16) + 8);
		}
	}

	public int nextInt(int max) {
		return this.rand.nextInt(max);
	}

	public boolean shouldSpawn(int chance) {
		return this.nextInt(chance) == 0;
	}	

}