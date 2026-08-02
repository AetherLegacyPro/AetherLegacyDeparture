package com.gildedgames.the_aether.world.gen;

import java.util.Random;
import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenDungeon;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenDungeonIsland;
import com.gildedgames.the_aether.world.gen.components.ComponentDivineGoldenIslandStub;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenDivineGoldenDungeon extends MapGenStructure {

    private final int requiredDungeonType;
    private static final int MIN_GOLDEN_DUNGEON_Y = 112;
    private static final int MAX_GOLDEN_DUNGEON_Y = 194;
    private static final int MIN_HIGH_TERRAIN_GOLDEN_DUNGEON_Y = 200;
    private static final int MAX_HIGH_TERRAIN_GOLDEN_DUNGEON_Y = 221;

    public MapGenDivineGoldenDungeon() {
        this.requiredDungeonType = AetherDungeonTypeHelper.TYPE_DIVINE;
    }

    @Override
    public String func_143025_a() {
        return "aether_legacy:divine_golden_dungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        long seed = this.worldObj.getSeed();

        if (!AetherDungeonTypeHelper.canGoldenDungeonSpawnAt(seed, chunkX, chunkZ)) {
            return false;
        }

        int type = AetherDungeonTypeHelper.getGoldenDungeonType(seed, chunkX, chunkZ);

        if (type != this.requiredDungeonType) {
            return false;
        }

        if (!this.isValidGoldenDungeonArea(chunkX, chunkZ)) {
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

        if (AetherWorld.divine_island != null && biome.biomeID == AetherWorld.divine_island.biomeID) {
            return true;
        }

        return false;
    }

    private boolean isValidGoldenDungeonArea(int chunkX, int chunkZ) {
        if (this.worldObj == null) {
            return true;
        }

        int baseX = chunkX * 16;
        int baseZ = chunkZ * 16;

        return this.isValidGoldenDungeonBiomeAt(baseX + 8, baseZ + 8) && this.isValidGoldenDungeonBiomeAt(baseX + 2, baseZ + 2)
            && this.isValidGoldenDungeonBiomeAt(baseX + 14, baseZ + 2) && this.isValidGoldenDungeonBiomeAt(baseX + 2, baseZ + 14)
            && this.isValidGoldenDungeonBiomeAt(baseX + 14, baseZ + 14);
    }

    private boolean isValidGoldenDungeonBiomeAt(int worldX, int worldZ) {
        BiomeGenBase biome = this.worldObj.getWorldChunkManager().getBiomeGenAt(worldX, worldZ);

        if (biome == null) {
            return false;
        }

        if (AetherWorld.divine_island != null && biome.biomeID == AetherWorld.divine_island.biomeID) {
            return true;
        } else if (AetherWorld.enchanted_island != null && biome.biomeID == AetherWorld.enchanted_island.biomeID) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        int worldX = chunkX * 16 + 8;
        int worldZ = chunkZ * 16 + 8;

        boolean highTerrain = this.isHighTerrainBiomeAt(worldX, worldZ);

        return new Start(this.worldObj, this.rand, chunkX, chunkZ, highTerrain);
    }

    public static class Start extends StructureStart {

        private int dungeonDirection;
        private int stubIslandCount;
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
            ComponentDivineGoldenDungeonIsland dungeon = new ComponentDivineGoldenDungeonIsland((chunkX << 4) + 2, (chunkZ << 4) + 2);

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

                this.components.add(new ComponentDivineGoldenIslandStub((chunkX << 4) + 2, (chunkZ << 4) + 2, l4, k5, i6, 8));
            }

            this.components.add(new ComponentDivineGoldenDungeon((chunkX << 4) + 2, (chunkZ << 4) + 2, this.dungeonDirection));

            if (highTerrain) {
                this.dungeonY = MIN_HIGH_TERRAIN_GOLDEN_DUNGEON_Y + random.nextInt(MAX_HIGH_TERRAIN_GOLDEN_DUNGEON_Y - MIN_HIGH_TERRAIN_GOLDEN_DUNGEON_Y + 1);
            } else {
                this.dungeonY = MIN_GOLDEN_DUNGEON_Y + random.nextInt(MAX_GOLDEN_DUNGEON_Y - MIN_GOLDEN_DUNGEON_Y + 1);
            }

            this.customOffset(this.dungeonY);

            this.updateBoundingBox();
        }

        private void customOffset(int targetY) {
            if (this.components.isEmpty()) {
                return;
            }

            AetherStructure first = (AetherStructure) this.components.get(0);
            int currentMinY = first.getBoundingBox().minY;
            int offset = targetY - currentMinY;

            for (Object object : this.components) {
                AetherStructure component = (AetherStructure) object;
                component.getBoundingBox().offset(0, offset, 0);
            }
        }

        @Override
        public void func_143022_a(NBTTagCompound tagCompound) {
            super.func_143022_a(tagCompound);
            tagCompound.setInteger("stubIslandCount", this.stubIslandCount);
            tagCompound.setInteger("dungeonDirection", this.dungeonDirection);
            tagCompound.setInteger("dungeonY", this.dungeonY);
        }

        @Override
        public void func_143017_b(NBTTagCompound tagCompound) {
            super.func_143017_b(tagCompound);
            this.stubIslandCount = tagCompound.getInteger("stubIslandCount");
            this.dungeonDirection = tagCompound.getInteger("dungeonDirection");
            this.dungeonY = tagCompound.getInteger("dungeonY");
        }
    }
}

