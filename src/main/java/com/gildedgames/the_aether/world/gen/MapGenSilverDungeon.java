package com.gildedgames.the_aether.world.gen;

import java.util.Random;
import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.world.gen.components.ComponentSilverDungeon;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenSilverDungeon extends MapGenStructure {

    private final int requiredDungeonType;
    private static final int MIN_SILVER_DUNGEON_Y = 136;
    private static final int MAX_SILVER_DUNGEON_Y = 184;
    private static final int MIN_HIGH_TERRAIN_SILVER_DUNGEON_Y = 200;
    private static final int MAX_HIGH_TERRAIN_SILVER_DUNGEON_Y = 221;

    public MapGenSilverDungeon() {
        this.requiredDungeonType = AetherDungeonTypeHelper.TYPE_REGULAR;
    }

    @Override
    public String func_143025_a() {
        return "AetherSilverDungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        long seed = this.worldObj.getSeed();

        if (!AetherDungeonTypeHelper.canSilverDungeonSpawnAt(seed, chunkX, chunkZ)) {
            return false;
        }

        int type = AetherDungeonTypeHelper.getSilverDungeonType(seed, chunkX, chunkZ);

        if (type != this.requiredDungeonType) {
            return false;
        }

        if (!this.isValidSilverDungeonArea(chunkX, chunkZ)) {
            return false;
        }

        return true;
    }

    private boolean isHighTerrainBiomeAt(int worldX, int worldZ) {
        if (this.worldObj == null) {
            return false;
        }

        BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(worldX, worldZ);

        if (biome == null) {
            return false;
        }

        if (AetherWorld.aether_peaks != null && biome.biomeID == AetherWorld.aether_peaks.biomeID) {
            return true;
        }

        if (AetherWorld.divine_island != null && biome.biomeID == AetherWorld.divine_island.biomeID) {
            return true;
        }

        return false;
    }

    private boolean isValidSilverDungeonArea(int chunkX, int chunkZ) {
        if (this.worldObj == null) {
            return true;
        }

        int baseX = chunkX * 16;
        int baseZ = chunkZ * 16;

        return this.isValidSilverDungeonBiomeAt(baseX + 8, baseZ + 8) && this.isValidSilverDungeonBiomeAt(baseX + 2, baseZ + 2)
            && this.isValidSilverDungeonBiomeAt(baseX + 14, baseZ + 2) && this.isValidSilverDungeonBiomeAt(baseX + 2, baseZ + 14)
            && this.isValidSilverDungeonBiomeAt(baseX + 14, baseZ + 14);
    }

    private boolean isValidSilverDungeonBiomeAt(int worldX, int worldZ) {
        BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(worldX, worldZ);

        if (biome == null) {
            return false;
        }

        return true;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        int worldX = chunkX * 16 + 8;
        int worldZ = chunkZ * 16 + 8;

        boolean highTerrain = this.isHighTerrainBiomeAt(worldX, worldZ);

        return new Start(this.worldObj, this.rand, chunkX, chunkZ, highTerrain);
    }

    public static class Start extends StructureStart {

        private int firstStaircaseZ;
        private int secondStaircaseZ;
        private int finalStaircaseZ;
        private int xTendency;
        private int zTendency;
        private int dungeonY;

        public Start() {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ, boolean highTerrain) {
            super(chunkX, chunkZ);
            this.create(worldIn, random, chunkX, chunkZ, highTerrain);
        }

        @SuppressWarnings("unchecked")
        private void create(World worldIn, Random random, int chunkX, int chunkZ, boolean highTerrain) {
            random.setSeed(worldIn.getSeed());
            long i = random.nextLong();
            long j = random.nextLong();

            long k = (long) chunkX * i;
            long l = (long) chunkZ * j;

            random.setSeed(k ^ l ^ worldIn.getSeed());
            ComponentSilverDungeon dungeon = new ComponentSilverDungeon((chunkX << 4) + 2, (chunkZ << 4) + 2);

            this.firstStaircaseZ = random.nextInt(3);
            this.secondStaircaseZ = random.nextInt(3);
            this.finalStaircaseZ = random.nextInt(3);

            this.xTendency = random.nextInt(3) - 1;
            this.zTendency = random.nextInt(3) - 1;

            dungeon.setStaircasePosition(this.firstStaircaseZ, this.secondStaircaseZ, this.finalStaircaseZ);
            dungeon.setCloudTendencies(this.xTendency, this.zTendency);

            if (highTerrain) {
                this.dungeonY = MIN_HIGH_TERRAIN_SILVER_DUNGEON_Y + random.nextInt(MAX_HIGH_TERRAIN_SILVER_DUNGEON_Y - MIN_HIGH_TERRAIN_SILVER_DUNGEON_Y + 1);
            } else {
                this.dungeonY = MIN_SILVER_DUNGEON_Y + random.nextInt(MAX_SILVER_DUNGEON_Y - MIN_SILVER_DUNGEON_Y + 1);
            }

            int yOffset = this.dungeonY - dungeon.getBoundingBox().minY;
            dungeon.getBoundingBox().offset(0, yOffset, 0);

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
            tagCompound.setInteger("dungeonY", this.dungeonY);
        }

        @Override
        public void func_143017_b(NBTTagCompound tagCompound) {
            super.func_143017_b(tagCompound);
            this.firstStaircaseZ = tagCompound.getInteger("firstStaircaseZ");
            this.secondStaircaseZ = tagCompound.getInteger("secondStaircaseZ");
            this.finalStaircaseZ = tagCompound.getInteger("finalStaircaseZ");
            this.xTendency = tagCompound.getInteger("xTendency");
            this.zTendency = tagCompound.getInteger("zTendency");
            this.dungeonY = tagCompound.getInteger("dungeonY");
        }
    }
}
