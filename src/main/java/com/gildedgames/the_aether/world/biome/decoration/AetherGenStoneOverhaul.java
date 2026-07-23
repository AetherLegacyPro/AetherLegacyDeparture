package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class AetherGenStoneOverhaul implements IWorldGenerator {
	protected final WorldGenMinable AetheralStoneGen = new WorldGenMinable(BlocksAether.aetheral_stone, 32, BlocksAether.holystone);
	protected final WorldGenMinable AgiositeStoneGen = new WorldGenMinable(BlocksAether.agiosite, 32, BlocksAether.holystone);
	protected final WorldGenMinable DeificStoneGen = new WorldGenMinable(BlocksAether.deific, 32, BlocksAether.holystone);
	protected final WorldGenMinable AetherDirtGen = new WorldGenMinable(BlocksAether.aether_dirt, 32, BlocksAether.holystone);
	protected final WorldGenMinable IcestoneGen = new WorldGenMinable(BlocksAether.icestone, 24, BlocksAether.holystone);
    protected final WorldGenMinable EnchantedHolystoneGen = new WorldGenMinable(BlocksAether.enchanted_holystone, 24, BlocksAether.holystone);

	public AetherGenStoneOverhaul() {
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
            int x = chunkX * 16 + rand.nextInt(8) + 8;
            int z = chunkZ * 16 + rand.nextInt(8) + 8;
            BiomeGenBase biome;
            biome = world.getBiomeGenForCoords(x, z);
			if (AetherConfig.enableAetheralStone() && biome == AetherWorld.enchanted_island) {
				this.generateOre(AetheralStoneGen, world, rand, chunkX, chunkZ, 6, 32, 196);
			}

			if (AetherConfig.enableAgisoite() && (biome == AetherWorld.aether_biome || biome == AetherWorld.aether_forest)) {
				this.generateOre(AgiositeStoneGen, world, rand, chunkX, chunkZ, 6, 16, 66);
			}

			if (AetherConfig.enableDeific() && biome == AetherWorld.divine_island) {
				this.generateOre(DeificStoneGen, world, rand, chunkX, chunkZ, 6, 0, 48);
			}

            if (biome == AetherWorld.arctic_biome || biome == AetherWorld.aether_peaks) {
                this.generateOre(IcestoneGen, world, rand, chunkX, chunkZ, 6, 48, 256);
            }

            if (biome == AetherWorld.enchanted_island || biome == AetherWorld.divine_island) {
                this.generateOre(EnchantedHolystoneGen, world, rand, chunkX, chunkZ, 6, 64, 256);
            }

            if (biome != AetherWorld.quicksoil_dunes) {
                this.generateOre(AetherDirtGen, world, rand, chunkX, chunkZ, 6, 64, 128);
            }

		}

	}

	public void generateOre(WorldGenMinable gen, World world, Random random, int chunkX, int chunkZ, float chance, int minY, int maxY) {
		if (minY < 0 || maxY < minY || chance <= 0)
			return;

		for (int i = 0; i < (chance < 1 ? 1 : (int) chance); i++) {
			if (chance >= 1 || random.nextFloat() < chance) {
				int xRand = (chunkX << 4) + random.nextInt(16);
				int yRand = MathHelper.getRandomIntegerInRange(random, minY, maxY);
				int zRand = (chunkZ << 4) + random.nextInt(16);
				gen.generate(world, random, xRand, yRand, zRand);
			}
		}
	}

}
