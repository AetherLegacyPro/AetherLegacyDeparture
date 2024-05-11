package com.gildedgames.the_aether.world.gen;

import java.util.Random;

import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import com.gildedgames.the_aether.world.gen.components.ComponentDivineSilverDungeon;

public class MapGenDivineSilverDungeon extends MapGenStructure {

	public MapGenDivineSilverDungeon() {
	}

	@Override
	public String func_143025_a() {
		return "aether_legacy:divine_silver_dungeon";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		RandomTracker randomTracker = new RandomTracker();

		if (randomTracker.testRandom(this.rand, 250) != 0)
		{
			if (randomTracker.testRandom(this.rand, 290) != 0)
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
		private int firstStaircaseZ, secondStaircaseZ, finalStaircaseZ;
		private int xTendency, zTendency;

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

			ComponentDivineSilverDungeon dungeon = new ComponentDivineSilverDungeon((chunkX << 4) + 2, (chunkZ << 4) + 2);

			this.firstStaircaseZ = random.nextInt(3);
			this.secondStaircaseZ = random.nextInt(3);
			this.finalStaircaseZ = random.nextInt(3);

			this.xTendency = random.nextInt(3) - 1;
			this.zTendency = random.nextInt(3) - 1;

			dungeon.setStaircasePosition(this.firstStaircaseZ, this.secondStaircaseZ, this.finalStaircaseZ);
			dungeon.setCloudTendencies(this.xTendency, this.zTendency);

			dungeon.getBoundingBox().offset(0, random.nextInt(64), 0);
			this.components.add(dungeon);
			this.updateBoundingBox();
		}

		@Override
		public void func_143022_a(NBTTagCompound tagCompound) {
			super.func_143022_a(tagCompound);

			tagCompound.setInteger("firstStaircaseZ", this.firstStaircaseZ);
			tagCompound.setInteger("secondStaircaseZ", this.secondStaircaseZ);
			tagCompound.setInteger("finalStaircaseZ", this.finalStaircaseZ);
			tagCompound.setInteger("xTendency", this.xTendency);
			tagCompound.setInteger("zTendency", this.zTendency);
		}

		@Override
		public void func_143017_b(NBTTagCompound tagCompound) {
			super.func_143017_b(tagCompound);

			this.firstStaircaseZ = tagCompound.getInteger("firstStaircaseZ");
			this.secondStaircaseZ = tagCompound.getInteger("secondStaircaseZ");
			this.finalStaircaseZ = tagCompound.getInteger("finalStaircaseZ");
			this.xTendency = tagCompound.getInteger("zTendency");
			this.zTendency = tagCompound.getInteger("zTendency");
		}

	}

}
