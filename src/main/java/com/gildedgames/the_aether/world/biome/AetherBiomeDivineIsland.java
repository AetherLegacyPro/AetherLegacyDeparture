package com.gildedgames.the_aether.world.biome;

import java.util.ArrayList;
import java.util.Random;
import com.gildedgames.the_aether.AetherConfig;
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
import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.passive.EntitySheepuff;
import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.world.biome.decoration.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerbunny;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerwhaleMount;
import com.gildedgames.the_aether.entities.passive.mountable.EntityFlyingCow;
import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;
import com.gildedgames.the_aether.entities.passive.mountable.EntityPhyg;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;
import com.gildedgames.the_aether.entities.passive.mountable.EntityZephyroo;
import com.gildedgames.the_aether.world.biome.decoration.plants.WorldGenAetherGrass;

public class AetherBiomeDivineIsland extends BiomeGenBase {

    @SuppressWarnings("unchecked")
    public AetherBiomeDivineIsland() {
        super(AetherConfig.getDivineIslandBiomeID());
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        ArrayList<SpawnListEntry> list = new ArrayList<>();
        this.addMobEntry(list);
        this.spawnableMonsterList.addAll(list);

        list.clear();
        this.addCreatureEntry(list);
        this.spawnableCreatureList.addAll(list);
        list.clear();

        this.topBlock = BlocksAether.divine_grass;
        this.fillerBlock = BlocksAether.aether_dirt;

        this.setBiomeName("Divine Island");
        this.setDisableRain();
        this.setColor(0);
    }

    public WorldGenAbstractTree getRandomTreeFeature(final Random random) {
        final int ratio = random.nextInt(60);
        if (ratio <= 35) {
            return new AetherGenDivineTree(BlocksAether.divine_oak_leaves, 8, true);
        } else if (ratio <= 45) {
            return new AetherGenDivineTree(BlocksAether.divine_oak_leaves, 12, false);
        } else if (ratio <= 50) {
            return new AetherGenLargeTree(BlocksAether.divine_oak_leaves, BlocksAether.divine_oak_log, 0);
        }

        return new AetherGenDivineTree(BlocksAether.divine_oak_leaves, 40, true);
    }

    public WorldGenerator getRandomWorldGenForGrass(final Random par1Random) {
        return new WorldGenAetherGrass(BlocksAether.divine_aether_tallgrass, 1);
    }

    private void addCreatureEntry(ArrayList<SpawnListEntry> list) {
        list.add(new SpawnListEntry(EntityAerwhale.class, 6, 4, 4));
        list.add(new SpawnListEntry(EntityAerwhaleMount.class, 4, 1, 2));
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
        list.add(new SpawnListEntry(EntityAerwhale.class, 1, 1, 2));
        list.add(new SpawnListEntry(EntityCockatrice.class, 80, 1, 4));
        list.add(new SpawnListEntry(EntityRaptor.class, 60, 1, 4));
        list.add(new SpawnListEntry(EntityWhirlwind.class, 10, 1, 2));
        list.add(new SpawnListEntry(EntityZephyr.class, 95, 1, 4));
        list.add(new SpawnListEntry(EntityTempest.class, 75, 1, 4));
        list.add(new SpawnListEntry(EntityAerca.class, 100, 1, 2));
        list.add(new SpawnListEntry(EntityYoungZephyr.class, 85, 1, 4));
        list.add(new SpawnListEntry(EntityCyro.class, 75, 1, 4));
        list.add(new SpawnListEntry(EntityUro.class, 80, 1, 4));

        //Mobs that spawn only within dungeons-----------------------

        //Bronze
        list.add(new SpawnListEntry(EntitySentry.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityBattleSentry.class, 81, 4, 4));

        //Silver
        list.add(new SpawnListEntry(EntityValkyrie.class, 20, 1, 1));
        list.add(new SpawnListEntry(EntityEliteValkyrie.class, 20, 1, 1));
        list.add(new SpawnListEntry(EntityZojz.class, 80, 1, 1));

        //Gold
        list.add(new SpawnListEntry(EntityCinerarium.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityAncientFireMinion.class, 25, 1, 2));
        list.add(new SpawnListEntry(EntityDivineFireMinion.class, 35, 1, 2));

        //Osmium
        list.add(new SpawnListEntry(EntityUligo.class, 80, 4, 4));
        list.add(new SpawnListEntry(EntityFallenValkyrie.class, 20, 1, 1));

        //Palladium
        list.add(new SpawnListEntry(EntityIrk.class, 20, 1, 1));
    }

    @Override
    public void addDefaultFlowers() {
        this.flowers.add(new FlowerEntry(BlocksAether.divine_grass, 0, 12));
        this.flowers.add(new FlowerEntry(BlocksAether.divine_bloom, 0, 14));
        this.flowers.add(new FlowerEntry(BlocksAether.divine_stalk, 0, 6));
        this.flowers.add(new FlowerEntry(BlocksAether.divine_lily, 0, 9));
    }

    @Override
    public int getWaterColorMultiplier() {
        return 16777215;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0x9e9eff;
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
