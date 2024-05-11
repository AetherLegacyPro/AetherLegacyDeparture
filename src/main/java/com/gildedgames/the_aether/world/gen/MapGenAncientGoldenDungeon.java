package com.gildedgames.the_aether.world.gen;

import java.util.Random;

import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import com.gildedgames.the_aether.world.gen.AetherStructure;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenIsland;
import com.gildedgames.the_aether.world.gen.components.ComponentAncientGoldenIslandStub;

public class MapGenAncientGoldenDungeon extends MapGenStructure {

	public MapGenAncientGoldenDungeon() {
	}

	@Override
	public String func_143025_a() {
		return "aether_legacy:ancient_golden_dungeon";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		RandomTracker randomTracker = new RandomTracker();

		if (randomTracker.testRandom(this.rand, 310) != 0)
		{
			if (randomTracker.testRandom(this.rand, 340) != 0)
			{
				return false;
			}
		}

		return chunkX % 6 == 0 && chunkZ % 6 == 0;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new Start(this.worldObj, this.rand, chunkX, chunkZ);
	}

	public static class Start extends StructureStart {
		private int dungeonDirection;
		private int stubIslandCount;

		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			super(chunkX, chunkZ);
			this.create(worldIn, random, chunkX, chunkZ);
		}

		@SuppressWarnings("unchecked")
		private void create(World worldIn, Random random, int chunkX, int chunkZ) {
			random.setSeed(worldIn.getSeed());
			long i = random.nextLong();
			long j = random.nextLong();
			long k = (long) chunkX * i;
			long l = (long) chunkZ * j;
			random.setSeed(k ^ l ^ worldIn.getSeed());

			ComponentAncientGoldenIsland dungeon = new ComponentAncientGoldenIsland((chunkX << 4) + 2, (chunkZ << 4) + 2);

			this.dungeonDirection = random.nextInt(4);
			this.stubIslandCount = 8 + random.nextInt(5);

			this.components.add(dungeon);

			for (int stubIslands = 0; stubIslands < this.stubIslandCount; ++stubIslands) {
				float f1 = 0.01745329F;
				float f2 = random.nextFloat() * 360F;
				float f3 = ((random.nextFloat() * 0.125F) + 0.7F) * 24.0F;
				int l4 = MathHelper.floor_double(Math.cos(f1 * f2) * (double) f3);
				int k5 = -MathHelper.floor_double(24.0D * (double) random.nextFloat() * 0.29999999999999999D);
				int i6 = MathHelper.floor_double(-Math.sin(f1 * f2) * (double) f3);

				this.components.add(new ComponentAncientGoldenIslandStub((chunkX << 4) + 2, (chunkZ << 4) + 2, l4, k5, i6, 8));
			}

			this.components.add(new ComponentAncientGoldenDungeon((chunkX << 4) + 2, (chunkZ << 4) + 2, this.dungeonDirection));

			this.customOffset(random);
			this.updateBoundingBox();
		}

		private void customOffset(Random random)
		{
			int offset = random.nextInt(64);

			for (Object object : this.components)
			{
				AetherStructure component = (AetherStructure) object;

				component.getBoundingBox().offset(0, offset, 0);
			}
		}

		@Override
		public void func_143022_a(NBTTagCompound tagCompound) {
			super.func_143022_a(tagCompound);

			tagCompound.setInteger("stubIslandCount", this.stubIslandCount);
			tagCompound.setInteger("dungeonDirection", this.dungeonDirection);
		}

		@Override
		public void func_143017_b(NBTTagCompound tagCompound) {
			super.func_143017_b(tagCompound);

			this.stubIslandCount = tagCompound.getInteger("stubIslandCount");
			this.dungeonDirection = tagCompound.getInteger("dungeonDirection");
		}

	}

}

