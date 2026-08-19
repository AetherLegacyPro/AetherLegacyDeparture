package com.gildedgames.the_aether.world.biome;

import java.util.ArrayList;
import java.util.Random;
import com.gildedgames.the_aether.world.biome.decoration.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.EntityAncientFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityDivineFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityEliteValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityFallenValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.hostile.EntityAerca;
import com.gildedgames.the_aether.entities.hostile.EntityBattleSentry;
import com.gildedgames.the_aether.entities.hostile.EntityCinerarium;
import com.gildedgames.the_aether.entities.hostile.EntityIrk;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;
import com.gildedgames.the_aether.entities.hostile.EntityYoungZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenAetherGrass;

public class AetherBiomeCloudyFields extends BiomeGenBase {

    @SuppressWarnings("unchecked")
    public AetherBiomeCloudyFields() {
        super(AetherConfig.getAercloudFieldsBiomeID());

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        ArrayList<SpawnListEntry> list = new ArrayList<>();

        addMobEntry(list);
        this.spawnableMonsterList.addAll(list);
        list.clear();

        addCreatureEntry(list);
        this.spawnableCreatureList.addAll(list);

        this.topBlock = BlocksAether.aercloud;
        this.fillerBlock = BlocksAether.stratos_aercloud;

        this.setBiomeName("Aercloud Fields");
        this.setDisableRain();
        this.setColor(0);
    }

    public WorldGenerator getRandomWorldGenForGrass(Random random) {
        return new WorldGenAetherGrass(BlocksAether.aercloud_layer, 0);
    }

    @Override
    public void addDefaultFlowers() {
        this.flowers.add(new FlowerEntry(BlocksAether.white_flower, 0, 10));
        this.flowers.add(new FlowerEntry(BlocksAether.white_rose, 0, 6));
        this.flowers.add(new FlowerEntry(BlocksAether.arctic_tallgrass, 0, 12));
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0x00ffdd;
    }

    @Override
    public int getSkyColorByTemp(float t) {
        return 0xddddfe;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 0xd4f0ff;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 0xd4f0ff;
    }

    private void addCreatureEntry(ArrayList<SpawnListEntry> list) {
        list.add(new SpawnListEntry(EntityAerwhale.class, 4, 1, 2));
    }

    private void addMobEntry(ArrayList<SpawnListEntry> list) {
        list.add(new SpawnListEntry(EntityAerwhale.class, 4, 1, 2));
        list.add(new SpawnListEntry(EntityZephyr.class, 100, 1, 4));
        list.add(new SpawnListEntry(EntityTempest.class, 75, 1, 4));
        list.add(new SpawnListEntry(EntityAerca.class, 100, 1, 2));
        list.add(new SpawnListEntry(EntityYoungZephyr.class, 100, 1, 4));
        list.add(new SpawnListEntry(EntitySentry.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityBattleSentry.class, 81, 4, 4));
        list.add(new SpawnListEntry(EntityValkyrie.class, 20, 1, 1));
        list.add(new SpawnListEntry(EntityEliteValkyrie.class, 20, 1, 1));
        list.add(new SpawnListEntry(EntityZojz.class, 80, 1, 1));
        list.add(new SpawnListEntry(EntityCinerarium.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityAncientFireMinion.class, 25, 1, 2));
        list.add(new SpawnListEntry(EntityDivineFireMinion.class, 35, 1, 2));
        list.add(new SpawnListEntry(EntityUligo.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityFallenValkyrie.class, 20, 1, 1));
        list.add(new SpawnListEntry(EntityIrk.class, 20, 1, 1));
    }
}
