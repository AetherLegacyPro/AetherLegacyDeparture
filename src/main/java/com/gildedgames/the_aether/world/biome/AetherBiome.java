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

import net.minecraft.world.biome.BiomeDecorator;
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
import com.gildedgames.the_aether.world.biome.decoration.AetherGenSkyrootTreeNew;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenFruitTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenGreatrootTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenLargeTree;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenMassiveTree;

public class AetherBiome extends BiomeGenBase {

	@SuppressWarnings("unchecked")
	public AetherBiome() {
		super(AetherConfig.getAetherBiomeID());

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

		this.topBlock = BlocksAether.aether_grass;
		this.fillerBlock = BlocksAether.holystone;

		this.setBiomeName("Aether");
		this.setDisableRain();
		this.setColor(0);
	}
	
	public WorldGenAbstractTree getRandomTreeFeature(final Random random) {
        final int ratio = random.nextInt(88);
        if (ratio <= 35) {
            return (WorldGenAbstractTree)new AetherGenSkyrootTreeNew(BlocksAether.skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 35 && ratio <= 45) {
            return (WorldGenAbstractTree)new AetherGenSkyrootTreeNew(BlocksAether.blue_skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 45 && ratio <= 50) {
            return (WorldGenAbstractTree)new AetherGenSkyrootTreeNew(BlocksAether.purple_skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 50 && ratio <= 52) {
            return (WorldGenAbstractTree)new AetherGenSkyrootTreeNew(BlocksAether.dark_blue_skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 52 && ratio <= 57) {
            return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.skyroot_leaves, 8, false);
        }
        else if (ratio > 57 && ratio <= 63) {
            return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.dark_blue_skyroot_leaves, 8, false);
        }
        else if (ratio > 63 && ratio <= 68) {
            return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.purple_skyroot_leaves, 8, false);
        }
        else if (ratio > 68 && ratio <= 69) {
            return (WorldGenAbstractTree)new AetherGenLargeTree(BlocksAether.golden_oak_leaves, BlocksAether.golden_oak_new_log, 0);
        }
        else if (ratio > 69 && ratio <= 70) {
            return (WorldGenAbstractTree)new AetherGenLargeTree(BlocksAether.skyroot_leaves, BlocksAether.skyroot_log, 0); 
        }
        else if (ratio > 70 && ratio <= 71) {
            return (WorldGenAbstractTree)new AetherGenLargeTree(BlocksAether.skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 71 && ratio <= 73) {
        	return (WorldGenAbstractTree)new AetherGenFruitTree(BlocksAether.dark_blue_skyroot_leaves, BlocksAether.dark_blue_skyroot_leaves, 50, 5, true);
        }
        else if (ratio > 73 && ratio <= 75) {
        	return (WorldGenAbstractTree)new AetherGenFruitTree(BlocksAether.purple_skyroot_leaves, BlocksAether.purple_skyroot_fruit_leaves, 50, 5, true);
        }
        else if (ratio > 75 && ratio <= 77) {
            return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.blue_skyroot_leaves, 18, false);
        }
        else if (ratio > 77 && ratio <= 78) {
        	return (WorldGenAbstractTree)new AetherGenFruitTree(BlocksAether.blue_skyroot_leaves, BlocksAether.blue_skyroot_leaves, 35, 5, true);
        }
        else if (ratio > 78 && ratio <= 86) {
            return (WorldGenAbstractTree)new AetherGenSkyrootTreeNew(BlocksAether.skyroot_leaves, BlocksAether.skyroot_log, 0);
        }
        else if (ratio > 86 && ratio <= 87) {
        	int random1 = (int)(1 + Math.random() * 8);
        	if (random1 > 2) {
        	return (WorldGenAbstractTree)new AetherGenGreatrootTree(BlocksAether.green_light_skyroot_leaves, 40, true);
        	}
        	else {
        		return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.dark_blue_skyroot_leaves, 35, true);
        	}
        }
        
        return (WorldGenAbstractTree)new AetherGenMassiveTree(BlocksAether.dark_blue_skyroot_leaves, 35, true);
    }
	
	public WorldGenerator getRandomWorldGenForGrass(final Random par1Random) {
        return (WorldGenerator)new WorldGenAetherGrass(BlocksAether.aether_tallgrass, 1);
    }

	private void addCreatureEntry(ArrayList<SpawnListEntry> list)
	{
		list.add(new SpawnListEntry(EntityAerwhale.class, 6, 4, 4));
		list.add(new SpawnListEntry(EntityAerwhaleMount.class, 4, 1, 2));
		list.add(new SpawnListEntry(EntityPhyg.class, 12, 4, 4));
		list.add(new SpawnListEntry(EntityAechorPlant.class, 11, 2, 3));
		list.add(new SpawnListEntry(EntityCarrionSprout.class, 7, 2, 3));
		list.add(new SpawnListEntry(EntityZephyroo.class, 6, 1, 2));
		list.add(new SpawnListEntry(EntitySheepuff.class, 10, 4, 4));
		list.add(new SpawnListEntry(EntityFlyingCow.class, 8, 4, 4));
		list.add(new SpawnListEntry(EntityAerbunny.class, 6, 2, 3));
		list.add(new SpawnListEntry(EntityMoa.class, 5, 2, 3));
		list.add(new SpawnListEntry(EntityThunderlo.class, 5, 3, 3));
		list.add(new SpawnListEntry(EntitySwet.class, 5, 4, 4));
		list.add(new SpawnListEntry(EntityFlynx.class, 4, 1, 4));
	}

	private void addMobEntry(ArrayList<SpawnListEntry> list)
	{
		list.add(new SpawnListEntry(EntityAerwhale.class, 1, 1, 2));
		list.add(new SpawnListEntry(EntityCockatrice.class, 80, 1, 4));
		list.add(new SpawnListEntry(EntityRaptor.class, 60, 1, 4));
		list.add(new SpawnListEntry(EntityWhirlwind.class, 10, 1, 2));
		list.add(new SpawnListEntry(EntityZephyr.class, 95, 1, 4));
		list.add(new SpawnListEntry(EntityTempest.class, 75, 1, 4));
		list.add(new SpawnListEntry(EntityAerca.class, 100, 1, 2));
		list.add(new SpawnListEntry(EntityYoungZephyr.class, 65, 1, 2));
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
		this.flowers.add(new FlowerEntry(BlocksAether.white_flower, 0, 12));
		this.flowers.add(new FlowerEntry(BlocksAether.purple_flower, 0, 14));
		this.flowers.add(new FlowerEntry(BlocksAether.white_rose, 0, 2));
		this.flowers.add(new FlowerEntry(BlocksAether.aechor_sprout, 0, 3));
		this.flowers.add(new FlowerEntry(BlocksAether.neverbloom, 0, 3));
		this.flowers.add(new FlowerEntry(BlocksAether.white_rose, 0, 4));	
		this.flowers.add(new FlowerEntry(BlocksAether.burstblossom, 0, 6));
		this.flowers.add(new FlowerEntry(BlocksAether.carrion_flower, 0, 8));
		this.flowers.add(new FlowerEntry(BlocksAether.moonlit_bloom, 0, 9));
		this.flowers.add(new FlowerEntry(BlocksAether.aether_tulips, 0, 7));
		this.flowers.add(new FlowerEntry(BlocksAether.quickshoot, 0, 8));
		
		this.flowers.add(new FlowerEntry(BlocksAether.arctic_tallgrass, 0, 7));
		
		this.flowers.add(new FlowerEntry(BlocksAether.enchanted_aether_tulips, 0, 7));
		this.flowers.add(new FlowerEntry(BlocksAether.enchanted_quickshoot, 0, 8));
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

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new AetherBiomeDecorator();
	}
	
}