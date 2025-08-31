package com.gildedgames.the_aether.world;

import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.AetherBiome;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenDungeonOakTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenFloatingIsland;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenFlowers;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenHolidayTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenLakes;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenLiquids;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenVoidFloatingIsland;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.AetherCloudsGenNew;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenBerryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenBlackberryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenGrapeVines;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenOrangeTree;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenRaspberryBush;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenStrawberryBush;
import com.gildedgames.the_aether.world.dungeon.BronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.CobaltDungeon;
import com.gildedgames.the_aether.world.dungeon.DivineBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.LargeBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.MythicBronzeDungeon;
import com.gildedgames.the_aether.world.dungeon.ZarnillysDen;
import com.gildedgames.the_aether.world.dungeon.util.AetherDungeon;
import com.gildedgames.the_aether.world.gen.MapGenAetherCaves;
import com.gildedgames.the_aether.world.gen.MapGenAncientGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenAncientSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenDivineSilverDungeon;
import com.gildedgames.the_aether.world.gen.MapGenGoldenDungeon;
import com.gildedgames.the_aether.world.gen.MapGenLargeColdAercloud;
import com.gildedgames.the_aether.world.gen.MapGenQuicksoil;
import com.gildedgames.the_aether.world.gen.MapGenSilverDungeon;

import cpw.mods.fml.common.eventhandler.Event;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderAether implements IChunkProvider {

	private Random rand;	
	public static int placementFlagType;
	private World worldObj;	
	private World aetherWorld;
	private NoiseGeneratorOctaves noiseGen1, perlinNoise1;
	private double[] buffer;
	double[] pnr, ar, br;
	
	int ancient_silver_chance = (int)(1 + Math.random() * 12);
	int divine_silver_chance = (int)(1 + Math.random() * 10);
	int ancient_gold_chance = (int)(1 + Math.random() * 12);
	int divine_gold_chance = (int)(1 + Math.random() * 10);
	
	private MapGenBase aetherCaveGenerator;
	
	protected AetherDungeon dungeon_bronze = new BronzeDungeon();	
	protected AetherDungeon large_dungeon_bronze = new LargeBronzeDungeon();	
	protected AetherDungeon divine_dungeon_bronze = new DivineBronzeDungeon();	
	protected AetherDungeon mythic_dungeon_bronze = new MythicBronzeDungeon();
	
	protected AetherDungeon cyro_dungeon = new CobaltDungeon();	
	protected AetherDungeon zarnillys_den = new ZarnillysDen();	
	private MapGenQuicksoil quicksoilGen = new MapGenQuicksoil();	
	private MapGenAetherCaves aether_caves = new MapGenAetherCaves();
	
	private MapGenSilverDungeon silverDungeonStructure = new MapGenSilverDungeon();	
	private MapGenAncientSilverDungeon ancientsilverDungeonStructure = new MapGenAncientSilverDungeon();	
	private MapGenDivineSilverDungeon divinesilverDungeonStructure = new MapGenDivineSilverDungeon();

	private MapGenGoldenDungeon goldenDungeonStructure = new MapGenGoldenDungeon();	
	private MapGenAncientGoldenDungeon ancientGoldenDungeonStructure = new MapGenAncientGoldenDungeon();	
	private MapGenDivineGoldenDungeon divineGoldenDungeonStructure = new MapGenDivineGoldenDungeon();
	
	private MapGenLargeColdAercloud largeColdAercloudStructure = new MapGenLargeColdAercloud();	
	public AetherGenDungeonOakTree golden_oak_tree_dungeon = new AetherGenDungeonOakTree();
	public AetherGenFloatingIsland crystal_island = new AetherGenFloatingIsland();
	public AetherGenVoidFloatingIsland void_island = new AetherGenVoidFloatingIsland();
	public AetherGenHolidayTree holiday_tree = new AetherGenHolidayTree();

	public ChunkProviderAether(World world, long seed) {
		this.worldObj = world;
		this.aetherCaveGenerator = new MapGenAetherCaves();
		this.rand = new Random(seed);
		this.aetherWorld = world;
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
		

	}

	public void setBlocksInChunk(int x, int z, Block[] blocks) {
		this.buffer = this.setupNoiseGenerators(this.buffer, x * 2, z * 2);

		for (int i1 = 0; i1 < 2; i1++) {
			for (int j1 = 0; j1 < 2; j1++) {
				for (int k1 = 0; k1 < 32; k1++) {
					double d1 = this.buffer[(i1 * 3 + j1) * 33 + k1];
					double d2 = this.buffer[(i1 * 3 + (j1 + 1)) * 33 + k1];
					double d3 = this.buffer[((i1 + 1) * 3 + j1) * 33 + k1];
					double d4 = this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + k1];

					double d5 = (this.buffer[(i1 * 3 + j1) * 33 + (k1 + 1)] - d1) * 0.25D;
					double d6 = (this.buffer[(i1 * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d2) * 0.25D;
					double d7 = (this.buffer[((i1 + 1) * 3 + j1) * 33 + (k1 + 1)] - d3) * 0.25D;
					double d8 = (this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d4) * 0.25D;

					for (int l1 = 0; l1 < 4; l1++) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.125D;
						double d13 = (d4 - d2) * 0.125D;

						for (int i2 = 0; i2 < 8; i2++) {
							int j2 = i2 + i1 * 8 << 11 | j1 * 8 << 7 | k1 * 4 + l1;
							char c = '\200';
							double d15 = d10;
							double d16 = (d11 - d10) * 0.125D;

							for (int k2 = 0; k2 < 8; k2++) {
								Block filler = Blocks.air;

								if (d15 > 0.0D) {
									filler = BlocksAether.holystone;
								}

								blocks[j2] = filler;
								j2 += c;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}

				}

			}

		}			

	}

	public void buildSurfaces(int i, int j, Block[] blocks) {
		
		this.rand.setSeed(this.worldObj.getSeed());
		for (int k = 0; k < 16; k++) {
			for (int l = 0; l < 16; l++) {
				int j1 = -1;
				int i1 = (int) (3.0D + this.rand.nextDouble() * 0.5D);

				Block top = BlocksAether.aether_grass;
				Block filler = BlocksAether.aether_dirt;

				for (int k1 = 127; k1 >= 0; k1--) {
					int l1 = (l * 16 + k) * 128 + k1;

					Block block = blocks[l1];

					if (block == Blocks.air) {
						j1 = -1;
					} else if (block == BlocksAether.holystone) {
						if (j1 == -1) {
							if (i1 <= 0) {
								top = Blocks.air;
								filler = BlocksAether.holystone;
							}

							j1 = i1;

							if (k1 >= 0) {
								blocks[l1] = top;
							} else {
								blocks[l1] = filler;
							}
						} else if (j1 > 0) {
							--j1;
							blocks[l1] = filler;
						}
					}
				}
			}
		}       
	}

	private double[] setupNoiseGenerators(double[] buffer, int x, int z) {
		if (buffer == null) {
			buffer = new double[3366];
		}

		double d = 1368.824D;
		double d1 = 684.41200000000003D;

		this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, x, 0, z, 3, 33, 3, d / 80D, d1 / 160D, d / 80D);
		this.ar = this.noiseGen1.generateNoiseOctaves(this.ar, x, 0, z, 3, 33, 3, d, d1, d);
		this.br = this.noiseGen1.generateNoiseOctaves(this.br, x, 0, z, 3, 33, 3, d, d1, d);

		int id = 0;

		for (int j2 = 0; j2 < 3; j2++) {
			for (int l2 = 0; l2 < 3; l2++) {
				for (int j3 = 0; j3 < 33; j3++) {
					double d8;

					double d10 = this.ar[id] / 512D;
					double d11 = this.br[id] / 512D;
					double d12 = (this.pnr[id] / 10D + 1.0D) / 2D;

					if (d12 < 0.0D) {
						d8 = d10;
					} else if (d12 > 1.0D) {
						d8 = d11;
					} else {
						d8 = d10 + (d11 - d10) * d12;
					}

					d8 -= 8D;

					if (j3 > 33 - 32) {
						double d13 = (float) (j3 - (33 - 32)) / ((float) 32 - 1.0F);
						d8 = d8 * (1.0D - d13) + -30D * d13;
					}

					if (j3 < 8) {
						double d14 = (float) (8 - j3) / ((float) 8 - 1.0F);
						d8 = d8 * (1.0D - d14) + -30D * d14;
					}

					buffer[id] = d8;

					id++;
				}

			}

		}
		
		int id2 = 0;
		
		for (int j22 = 0; j22 < 3; j22++) {
			for (int l22 = 0; l22 < 3; l22++) {
				for (int j32 = 0; j32 < 33; j32++) {
					double d82;

					double d102 = this.ar[id2] / 768D;
					double d112 = this.br[id2] / 768D;
					double d122 = (this.pnr[id2] / 10D + 1.0D) / 2D;

					if (d122 < 0.0D) {
						d82 = d102;
					} else if (d122 > 4.0D) { //1.0
						d82 = d112;
					} else {
						d82 = d102 + (d112 - d102) * d122;
					}

					d82 -= 10D; //8D

					if (j32 > 24 - 23) {
						double d132 = (float) (j32 - (24 - 23)) / ((float) 24 - 1.0F);
						d82 = d82 * (2D - d132) + -22D * d132;
					}

					if (j32 < 8) {
						double d142 = (float) (8 - j32) / ((float) 8 - 1.0F);
						d82 = d82 * (2D - d142) + -22D * d142;
					}

					buffer[id2] = d82;

					id2++;
				}

			}

		}

		return buffer;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		Block[] ablock = new Block[32768];
		final byte[] metadata = new byte[32768];
		
		this.setBlocksInChunk(x, z, ablock);
		this.buildSurfaces(x, z, ablock);
		((MapGenAetherCaves) this.aetherCaveGenerator).generate(this, this.aetherWorld, x, z, ablock, metadata);
		this.quicksoilGen.func_151539_a(this, this.worldObj, x, z, ablock);


		this.largeColdAercloudStructure.func_151539_a(this, this.worldObj, x, z, ablock);

		if (AetherConfig.silver_dungeon_enable) {
			this.silverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		if (AetherConfig.tier2_silver_dungeon_enable && ancient_silver_chance > 3 && (Math.abs(x) > 1200 || Math.abs(z) > 1200)) {
			this.ancientsilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		if (AetherConfig.tier3_silver_dungeon_enable && divine_silver_chance >= 4 && (Math.abs(x) > 3000 || Math.abs(z) > 3000)) {
			this.divinesilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		if (AetherConfig.gold_dungeon_enable) {
			this.goldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		if (AetherConfig.tier2_gold_dungeon_enable && ancient_gold_chance > 3 && (Math.abs(x) > 2000 || Math.abs(z) > 2000)) {
			this.ancientGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		if (AetherConfig.tier3_gold_dungeon_enable && divine_gold_chance >= 4 && (Math.abs(x) > 5000 || Math.abs(z) > 5000)) {
			this.divineGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, ablock);
		}
		
		Chunk chunk = new Chunk(this.worldObj, ablock, x, z);
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		return this.worldObj.getBiomeGenForCoords(x, z).getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(int x, int z) {
		this.largeColdAercloudStructure.func_151539_a(this, this.worldObj, x, z, null);
		this.aether_caves.func_151539_a(this, this.worldObj, x, z, null);
		
		if (AetherConfig.silver_dungeon_enable) {
			this.silverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
		if (AetherConfig.tier2_silver_dungeon_enable && (Math.abs(x) > 1200 || Math.abs(z) > 1200)) {
			this.ancientsilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
		if (AetherConfig.tier3_silver_dungeon_enable && (Math.abs(x) > 3000 || Math.abs(z) > 3000)) {
			this.divinesilverDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
		if (AetherConfig.gold_dungeon_enable) {
			this.goldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
		if (AetherConfig.tier2_gold_dungeon_enable && (Math.abs(x) > 2000 || Math.abs(z) > 2000)) {
			this.ancientGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
		if (AetherConfig.tier3_gold_dungeon_enable && (Math.abs(x) > 5000 || Math.abs(z) > 5000)) {
			this.divineGoldenDungeonStructure.func_151539_a(this, this.worldObj, x, z, null);
		}
	}

	@Override
	public ChunkPosition func_147416_a(World worldIn, String structureName, int x, int y, int z) //getNearestStructurePos
	{
		return null;
	}

	@Override
	public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);

		this.rand.setSeed(this.worldObj.getSeed());
		long k = this.rand.nextLong() / 2L * 2L + 1L;
		long l = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) x * k + (long) z * l ^ this.worldObj.getSeed());

		this.largeColdAercloudStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		
		if (AetherConfig.silver_dungeon_enable) {
			this.silverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}
		
		if (AetherConfig.gold_dungeon_enable) {
			this.goldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}
		
		if(AetherConfig.tier2_silver_dungeon_enable && (Math.abs(x) > 1200 || Math.abs(z) > 1200)) {
			this.ancientsilverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}
		
		if(AetherConfig.tier3_silver_dungeon_enable && (Math.abs(x) > 3000 || Math.abs(z) > 3000)) {
			this.divinesilverDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}
		
		if(AetherConfig.tier2_gold_dungeon_enable && (Math.abs(x) > 2000 || Math.abs(z) > 2000)) {
			this.ancientGoldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}
		
		if(AetherConfig.tier3_gold_dungeon_enable && (Math.abs(x) > 5000 || Math.abs(z) > 5000)) {
			this.divineGoldenDungeonStructure.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
		}

		biome.decorate(this.worldObj, this.rand, x, z);
		
		int den_chance = (int)(1 + Math.random() * 5);
		if(AetherConfig.zarnyllis_den_gen && den_chance == 1) {
			this.zarnillys_den.generate(this.worldObj, this.rand, x, this.rand.nextInt(6) + 50, z);
		}
		
		if (AetherConfig.bronze_dungeon_enable) {
			this.dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(28) + 24, z);
		}
		
		if (AetherConfig.tier2_bronze_dungeon_enable && (Math.abs(x) > 750 || Math.abs(z) > 750)) {
			this.large_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(18) + 26, z);
		}
		
		if (AetherConfig.tier3_bronze_dungeon_enable && (Math.abs(x) > 2000 || Math.abs(z) > 2000)) {
			this.divine_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(12) + 24, z);
		}
		
		int mythic_bronze_chance = (int)(1 + Math.random() * 3);
		if(AetherConfig.tier4_bronze_dungeon_enable && mythic_bronze_chance == 1 && (Math.abs(x) > 5000 || Math.abs(z) > 5000)) {
			this.mythic_dungeon_bronze.generate(this.worldObj, this.rand, x, this.rand.nextInt(4) + 24, z);
		} else {
			if (AetherConfig.cobalt_dungeon_enable) {
				this.cyro_dungeon.generate(this.worldObj, this.rand, x, this.rand.nextInt(14) + 22, z);
			}
		}
		
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biome, x + 8, z + 8, 16, 16, this.rand);
		
		final BiomeGenBase biomegenbase = AetherWorld.aether_biome;
		
		//Standard 3 Aerclouds
		 if (this.rand.nextInt(50) == 0) {
	            final int x1 = x + this.rand.nextInt(16);
	            final int y = this.rand.nextInt(64) + 32;
	            final int z1 = z + this.rand.nextInt(16);
	            new AetherCloudsGenNew(BlocksAether.aercloud, 0, 16, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 
		 if (this.rand.nextInt(6) == 0) {
	            final int x1 = x + this.rand.nextInt(16);
	            final int y = this.rand.nextInt(128);
	            final int z1 = z + this.rand.nextInt(16);
	            new AetherCloudsGenNew(BlocksAether.aercloud, 0, 64, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 
		 if (this.rand.nextInt(20) == 0) {
	            final int x1 = x + this.rand.nextInt(16);
	            final int y = this.rand.nextInt(64);
	            final int z1 = z + this.rand.nextInt(16);
	            new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 
		 if (this.rand.nextInt(12) == 0) {
	            final int x1 = x + this.rand.nextInt(16);
	            final int y = this.rand.nextInt(64)+ 64;
	            final int z1 = z + this.rand.nextInt(16);
	            new AetherCloudsGenNew(BlocksAether.aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 
		 if (this.rand.nextInt(30) == 0) {
	            final int x1 = x + this.rand.nextInt(64);
	            final int y = this.rand.nextInt(64) + 128;
	            final int z1 = z + this.rand.nextInt(64);
	            new AetherCloudsGenNew(BlocksAether.aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 //Purple Aerclouds
	if (AetherConfig.enablePurpleAercloud() == true) {
		 if (this.rand.nextInt(60) == 0) {
	            final int x1 = x + this.rand.nextInt(64);
	            final int y = this.rand.nextInt(64) + 32;
	            final int z1 = z + this.rand.nextInt(64);
	            new AetherCloudsGenNew(BlocksAether.purple_aercloud, 0, 8, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		 }
	if (AetherConfig.enableVioletAercloud() == true) {
		 if (this.rand.nextInt(14) == 0) {
	            final int x1 = x + this.rand.nextInt(128);
	            final int y = this.rand.nextInt(64);
	            final int z1 = z + this.rand.nextInt(128);
	            new AetherCloudsGenNew(BlocksAether.purple_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	if (AetherConfig.enableDarkPurpleAercloud() == true) {
		 if (this.rand.nextInt(17) == 0) {
	            final int x1 = x + this.rand.nextInt(256);
	            final int y = this.rand.nextInt(64) + 64;
	            final int z1 = z + this.rand.nextInt(256);
	            new AetherCloudsGenNew(BlocksAether.purple_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
		//Green Aerclouds
	if (AetherConfig.enableLightGreenAercloud() == true) {
		 if (this.rand.nextInt(70) == 0) {
	            final int x1 = x + this.rand.nextInt(48);
	            final int y = this.rand.nextInt(64) + 16;
	            final int z1 = z + this.rand.nextInt(48);
	            new AetherCloudsGenNew(BlocksAether.green_aercloud, 0, 9, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	if (AetherConfig.enableGreenAercloud() == true) {
		 if (this.rand.nextInt(14) == 0) {
	            final int x1 = x + this.rand.nextInt(128);
	            final int y = this.rand.nextInt(64) + 20;
	            final int z1 = z + this.rand.nextInt(128);
	            new AetherCloudsGenNew(BlocksAether.green_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	if (AetherConfig.enableLimeAercloud() == true) {
		 if (this.rand.nextInt(17) == 0) {
	            final int x1 = x + this.rand.nextInt(128);
	            final int y = this.rand.nextInt(64) + 50;
	            final int z1 = z + this.rand.nextInt(128);
	            new AetherCloudsGenNew(BlocksAether.green_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
		//Pink Aerclouds
	if (AetherConfig.enablePinkAercloud() == true) {
		 if (this.rand.nextInt(52) == 0) {
	            final int x1 = x + this.rand.nextInt(1024);
	            final int y = this.rand.nextInt(64) + 148;
	            final int z1 = z + this.rand.nextInt(1024);
	            new AetherCloudsGenNew(BlocksAether.pink_aercloud, 0, 9, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	if (AetherConfig.enableMagentaAercloud() == true) {
		 if (this.rand.nextInt(54) == 0) {
	            final int x1 = x + this.rand.nextInt(1024);
	            final int y = this.rand.nextInt(64) + 148;
	            final int z1 = z + this.rand.nextInt(1024);
	            new AetherCloudsGenNew(BlocksAether.pink_aercloud, 1, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	if (AetherConfig.enableOrangeAercloud() == true) {
		 if (this.rand.nextInt(50) == 0) {
	            final int x1 = x + this.rand.nextInt(512);
	            final int y = this.rand.nextInt(64) + 148;
	            final int z1 = z + this.rand.nextInt(512);
	            new AetherCloudsGenNew(BlocksAether.pink_aercloud, 2, 4, false).generate(this.worldObj, this.rand, x, y, z);
	        }
		}
	        
		
		for (int numberoftreegen = 3, i2 = 0; i2 < numberoftreegen; ++i2) {
            final int k2 = x + this.rand.nextInt(16) + 8;
            final int j2 = z + this.rand.nextInt(16) + 8;
            final WorldGenerator worldgenerator = ((AetherBiome) biomegenbase).getRandomTreeFeature(this.rand);
            worldgenerator.setScale(1.0, 1.0, 1.0);
            worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
        }
		
		for (int n = 0; n < 6; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.purple_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 6; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.white_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 6; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.aercloud_layer, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 3; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.white_rose, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 6; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.aechor_sprout, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 5; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.blue_swingtip, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 4; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.neverbloom, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 4; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.burstblossom, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 3; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.carrion_flower, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 3; ++n) {
            if (this.rand.nextInt(2) == 0) {
                final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.blue_swingtip, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 3; ++n) {
            if (this.rand.nextInt(2) == 0) {
            	final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.quickshoot, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		for (int n = 0; n < 3; ++n) {
            if (this.rand.nextInt(2) == 0) {
            	final int x2 = x + this.rand.nextInt(16) + 8;
                final int y2 = this.rand.nextInt(128);
                final int z2 = z + this.rand.nextInt(16) + 8;
                new AetherGenFlowers(BlocksAether.aether_tulips, 64).generate(this.worldObj, this.rand, x2, y2, z2);
            }
        }
		
		if (AetherConfig.tallgrassEnabled()) {
		final int numberofgrassgen = 4;
		for (int i2 = 0; i2 < numberofgrassgen; ++i2) {
            final int k2 = x + this.rand.nextInt(16) + 8;
            final int j2 = z + this.rand.nextInt(16) + 8;
            final WorldGenerator worldgenerator = biomegenbase.getRandomWorldGenForGrass(this.rand);
            worldgenerator.setScale(1.0, 1.0, 1.0);
            worldgenerator.generate(this.worldObj, this.rand, k2, this.worldObj.getHeightValue(k2, j2), j2);
        }
	  }
		for (int n2 = 0; n2 < 2; ++n2) {
            final int x2 = x + this.rand.nextInt(16) + 8;
            final int y2 = this.rand.nextInt(128);
            final int z2 = z + this.rand.nextInt(16) + 8;
            new WorldGenBerryBush(BlocksAether.berry_bush, 3).generate(this.worldObj, this.rand, x2, y2, z2);
        }		
		for (int n3 = 0; n3 < 2; ++n3) {
            final int x3 = x + this.rand.nextInt(18) + 8;
            final int y3 = this.rand.nextInt(128);
            final int z3 = z + this.rand.nextInt(18) + 8;
            new WorldGenRaspberryBush(BlocksAether.raspberry_bush, 5).generate(this.worldObj, this.rand, x3, y3, z3);
        }
		for (int n4 = 0; n4 < 2; ++n4) {
            final int x4 = x + this.rand.nextInt(15) + 8;
            final int y4 = this.rand.nextInt(128);
            final int z4 = z + this.rand.nextInt(15) + 8;
            new WorldGenStrawberryBush(BlocksAether.strawberry_bush, 4).generate(this.worldObj, this.rand, x4, y4, z4);
        }
		for (int n4 = 0; n4 < 2; ++n4) {
            final int x4 = x + this.rand.nextInt(14) + 4;
            final int y4 = this.rand.nextInt(128);
            final int z4 = z + this.rand.nextInt(14) + 4;
            new WorldGenBlackberryBush(BlocksAether.blackberry_bush, 3).generate(this.worldObj, this.rand, x4, y4, z4);
        }
		for (int n5 = 0; n5 < 2; ++n5) {
            final int x5 = x + this.rand.nextInt(10) + 6;
            final int y5 = this.rand.nextInt(128);
            final int z5 = z + this.rand.nextInt(10) + 6;
            new WorldGenOrangeTree(BlocksAether.mature_orange_tree, 2).generate(this.worldObj, this.rand, x5, y5, z5);
        }
		for (int n6 = 0; n6 < 2; ++n6) {
            final int x6 = x + this.rand.nextInt(12) + 5;
            final int y6 = this.rand.nextInt(128);
            final int z6 = z + this.rand.nextInt(12) + 5;
            new WorldGenGrapeVines(BlocksAether.grape_tree_mature, 3).generate(this.worldObj, this.rand, x6, y6, z6);
        }
		if (this.rand.nextInt(48) == 0) {
		 for (int k3 = 0; k3 < 6; ++k3) {
             final int j3 = x + this.rand.nextInt(8) + 8;
             final int l4 = this.rand.nextInt(this.rand.nextInt(64) + 48);
             final int l5 = z + this.rand.nextInt(8) + 8;
             new AetherGenLakes().generate(this.worldObj, this.rand, j3, l4, l5);
          }
		}
		if (this.rand.nextInt(4) == 0) {
		 for (int k3 = 0; k3 < 10; ++k3) {
             final int j3 = x + this.rand.nextInt(8) + 8;
             final int l4 = this.rand.nextInt(this.rand.nextInt(120) + 8);
             final int l5 = z + this.rand.nextInt(8) + 8;
             new AetherGenLiquids(Blocks.water).generate(this.worldObj, this.rand, j3, l4, l5);
          }
		}		
		for (int k3 = 0; k3 < 25; k3++) {
			final int j3 = x + this.rand.nextInt(8) + 8;
			final int l5 = z + this.rand.nextInt(8) + 8;
			final int l4 = this.worldObj.getHeightValue(j3, l5);
			this.golden_oak_tree_dungeon.generate(this.worldObj, this.rand, j3, l4, l5);
		}
		if (this.rand.nextInt(50) == 0) {
	        final int j3 = x + this.rand.nextInt(8) + 8;
	        final int l4 = this.rand.nextInt(this.rand.nextInt(64) + 32);
	        final int l5 = z + this.rand.nextInt(8) + 8;
	        this.crystal_island.generate(this.worldObj, this.rand, j3, l4, l5);
		}
		if (this.rand.nextInt(70) == 0) {
	        final int j3 = x + this.rand.nextInt(8) + 8;
	        final int l4 = this.rand.nextInt(this.rand.nextInt(8) + 16);
	        final int l5 = z + this.rand.nextInt(8) + 8;
	        this.void_island.generate(this.worldObj, this.rand, j3, l4, l5);
		}
		if (this.rand.nextInt(30) == 0 ) {
			if ((AetherConfig.shouldLoadHolidayContent()) || (AetherConfig.allowSeasonalChristmas() &&
					(Calendar.getInstance().get(Calendar.MONTH) + 1 == 12 || Calendar.getInstance().get(Calendar.MONTH) + 1 == 1))) {
				final int j3 = x + this.rand.nextInt(8) + 8;
				final int l5 = z + this.rand.nextInt(8) + 8;
				final int l4 = this.worldObj.getHeightValue(j3, l5);
				this.holiday_tree.generate(this.worldObj, this.rand, j3, l4, l5);
			}
		}
	}	
	
	@Override
	public Chunk loadChunk(int chunkX, int chunkZ) {
		return this.provideChunk(chunkX, chunkZ);
	}

	@Override
	public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "AetherRandomLevelSource";
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void saveExtraData() {

	}
	
	static {
        ChunkProviderAether.placementFlagType = 2;
    }

}