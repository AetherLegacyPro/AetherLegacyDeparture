package com.gildedgames.the_aether.world.biome;

import java.util.ArrayList;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;
import com.gildedgames.the_aether.entities.hostile.EntityIrk;

import net.minecraft.world.biome.BiomeGenBase;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class GenesisIslands extends BiomeGenBase {

    @SuppressWarnings("unchecked")
    public GenesisIslands() {
        super(AetherConfig.getGenesisIslandBiomeID());

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        ArrayList<SpawnListEntry> list = new ArrayList<>();

        this.addMobEntry(list);

        this.spawnableMonsterList.addAll(list);

        list.clear();

        this.topBlock = BlocksAether.genesis_stone;
        this.fillerBlock = BlocksAether.genesis_stone;

        this.setBiomeName("Genesis Islands");
        this.setDisableRain();
        this.setColor(0);
    }

    private void addMobEntry(ArrayList<SpawnListEntry> list) {
        list.add(new SpawnListEntry(EntityIrk.class, 40, 1, 4));
        list.add(new SpawnListEntry(EntityCrystalDragon.class, 20, 1, 4));
    }

    @Override
    public int getWaterColorMultiplier() {
        return 16777215;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xBCBCFA; // Lavender Blue
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 0xb1ffcb;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 0xb1ffcb;
    }
}
