package com.gildedgames.the_aether.world.biome;

import java.util.ArrayList;
import java.util.Random;
import com.gildedgames.the_aether.world.biome.decoration.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.EntityAncientFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityDivineFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityEliteValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityFallenValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.hostile.EntityAechorPlant;
import com.gildedgames.the_aether.entities.hostile.EntityAerca;
import com.gildedgames.the_aether.entities.hostile.EntityBattleSentry;
import com.gildedgames.the_aether.entities.hostile.EntityCinerarium;
import com.gildedgames.the_aether.entities.hostile.EntityCockatrice;
import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import com.gildedgames.the_aether.entities.hostile.EntityIrk;
import com.gildedgames.the_aether.entities.hostile.EntityRaptor;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;
import com.gildedgames.the_aether.entities.hostile.EntityUro;
import com.gildedgames.the_aether.entities.hostile.EntityWhirlwind;
import com.gildedgames.the_aether.entities.hostile.EntityYoungZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.entities.passive.EntityCarrionSprout;
import com.gildedgames.the_aether.entities.passive.EntitySheepuff;
import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerbunny;
import com.gildedgames.the_aether.entities.passive.mountable.EntityFlyingCow;
import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;
import com.gildedgames.the_aether.entities.passive.mountable.EntityPhyg;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenAetherGrass;

public class AetherBiomeEnchantedIsland extends BiomeGenBase {

    @SuppressWarnings("unchecked")
    public AetherBiomeEnchantedIsland() {
        super(AetherConfig.getEnchantedIslandBiomeID());

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

        this.topBlock = BlocksAether.enchanted_aether_grass;
        this.fillerBlock = BlocksAether.aether_dirt;

        this.setBiomeName("Enchanted Island");
        this.setDisableRain();
        this.setColor(0);
    }

    public WorldGenerator getRandomWorldGenForGrass(Random random) {
        return new WorldGenAetherGrass(BlocksAether.enchanted_aether_tallgrass, 0);
    }

    @Override
    public void addDefaultFlowers() {
        this.flowers.add(new FlowerEntry(BlocksAether.enchanted_aether_tallgrass, 0, 10));
        this.flowers.add(new FlowerEntry(BlocksAether.enchanted_aether_tulips, 0, 6));
        this.flowers.add(new FlowerEntry(BlocksAether.enchanted_bloom, 0, 5));
        this.flowers.add(new FlowerEntry(BlocksAether.enchanted_quickshoot, 0, 12));
    }

    public WorldGenAbstractTree getRandomTreeFeature(final Random random) {
        final int ratio = random.nextInt(50);
        if (ratio <= 25) {
            return new AetherGenLargeTree(BlocksAether.golden_oak_leaves, BlocksAether.golden_oak_new_log, 0);
        } else if (ratio <= 45) {
            return new AetherGenGoldenFruitTree(BlocksAether.golden_oak_leaves, BlocksAether.golden_oak_leaves, 35, 5, true);
        } else if (ratio <= 47) {
            return new AetherGenGoldenFruitTree(BlocksAether.golden_oak_leaves, BlocksAether.golden_oak_fruit_leaves, 50, 5, true);
        }

        return new AetherGenLargeTree(BlocksAether.golden_oak_leaves, BlocksAether.golden_oak_new_log, 0);
    }

    @Override
    public int getWaterColorMultiplier() {
        return 16777215;
    }

    @Override
    public int getSkyColorByTemp(float t) {
        return 0xBCBCFA;
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
        list.add(new SpawnListEntry(EntityPhyg.class, 12, 4, 4));
        list.add(new SpawnListEntry(EntityAechorPlant.class, 11, 2, 3));
        list.add(new SpawnListEntry(EntityCarrionSprout.class, 7, 2, 3));
        list.add(new SpawnListEntry(EntitySheepuff.class, 10, 4, 4));
        list.add(new SpawnListEntry(EntityFlyingCow.class, 8, 4, 4));
        list.add(new SpawnListEntry(EntityAerbunny.class, 6, 2, 3));
        list.add(new SpawnListEntry(EntityMoa.class, 5, 2, 3));
        list.add(new SpawnListEntry(EntitySwet.class, 5, 4, 4));
        list.add(new SpawnListEntry(EntityThunderlo.class, 3, 1, 3));
    }

    private void addMobEntry(ArrayList<SpawnListEntry> list) {
        list.add(new SpawnListEntry(EntityAerwhale.class, 4, 1, 2));
        list.add(new SpawnListEntry(EntityCockatrice.class, 80, 1, 4));
        list.add(new SpawnListEntry(EntityRaptor.class, 60, 1, 4));
        list.add(new SpawnListEntry(EntityWhirlwind.class, 10, 1, 2));
        list.add(new SpawnListEntry(EntityZephyr.class, 95, 1, 4));
        list.add(new SpawnListEntry(EntityTempest.class, 75, 1, 4));
        list.add(new SpawnListEntry(EntityAerca.class, 100, 1, 2));
        list.add(new SpawnListEntry(EntityYoungZephyr.class, 85, 1, 4));
        list.add(new SpawnListEntry(EntityCyro.class, 75, 1, 4));
        list.add(new SpawnListEntry(EntityUro.class, 80, 1, 4));
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
