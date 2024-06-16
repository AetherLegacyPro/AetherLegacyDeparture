package com.gildedgames.the_aether.entities;

import java.util.HashMap;
import java.util.Map;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityAncientValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityDivineValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import com.gildedgames.the_aether.entities.effects.PotionInebriation;
import com.gildedgames.the_aether.entities.fake.item.EntityFakeItem;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.entities.passive.EntityCarrionSprout;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.passive.EntityMiniCloud;
import com.gildedgames.the_aether.entities.passive.EntitySheepuff;
import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.entities.projectile.crystals.EntityCrystal;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartEnchanted;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartGolden;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartPhoenix;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartPoison;
import com.gildedgames.the_aether.items.ItemAetherSpawnEgg;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gildedgames.the_aether.entities.block.EntityAetherTNT;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.entities.block.EntityFloatingBlock;
import com.gildedgames.the_aether.entities.block.EntityTNTPresent;
import com.gildedgames.the_aether.entities.bosses.EntityAncientFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityDivineFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityElderZarnillys;
import com.gildedgames.the_aether.entities.bosses.EntityEliteValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityElysianGuardian;
import com.gildedgames.the_aether.entities.bosses.EntityFallenValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityMythicFireMinion;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;
import com.gildedgames.the_aether.entities.bosses.slider.EntityDivineSlider;
import com.gildedgames.the_aether.entities.bosses.slider.EntityEnhancedSlider;
import com.gildedgames.the_aether.entities.bosses.slider.EntityMythicSlider;
import com.gildedgames.the_aether.entities.bosses.slider.EntitySlider;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityAncientSunSpirit;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityDivineSunSpirit;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntitySunSpirit;
import com.gildedgames.the_aether.entities.hostile.EntityAechorPlant;
import com.gildedgames.the_aether.entities.hostile.EntityAerca;
import com.gildedgames.the_aether.entities.hostile.EntityAercenturion;
import com.gildedgames.the_aether.entities.hostile.EntityBattleSentry;
import com.gildedgames.the_aether.entities.hostile.EntityCinerarium;
import com.gildedgames.the_aether.entities.hostile.EntityCockatrice;
import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import com.gildedgames.the_aether.entities.hostile.EntityCyroGuardian;
import com.gildedgames.the_aether.entities.hostile.EntityHellfireCinder;
import com.gildedgames.the_aether.entities.hostile.EntityIrk;
import com.gildedgames.the_aether.entities.hostile.EntityMimic;
import com.gildedgames.the_aether.entities.hostile.EntityRaptor;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;
import com.gildedgames.the_aether.entities.hostile.EntityUro;
import com.gildedgames.the_aether.entities.hostile.EntityWhirlwind;
import com.gildedgames.the_aether.entities.hostile.EntityYoungZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZarnillys;
import com.gildedgames.the_aether.entities.hostile.EntityZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerbunny;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerwhaleMount;
import com.gildedgames.the_aether.entities.passive.mountable.EntityFlyingCow;
import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;
import com.gildedgames.the_aether.entities.passive.mountable.EntityParachute;
import com.gildedgames.the_aether.entities.passive.mountable.EntityPhyg;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;
import com.gildedgames.the_aether.entities.passive.mountable.EntityZephyroo;
import com.gildedgames.the_aether.entities.projectile.EntityAercenturionProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityAmplifiedBattleSentryHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityAmplifiedHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityAmplifiedJebHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityBattleSentryHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityCinerariumProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityCyroArrow;
import com.gildedgames.the_aether.entities.projectile.EntityCyroGuardianProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityElysianGuardianLaser;
import com.gildedgames.the_aether.entities.projectile.EntityHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityJebHammerProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityLightningKnife;
import com.gildedgames.the_aether.entities.projectile.EntityPhoenixArrow;
import com.gildedgames.the_aether.entities.projectile.EntityPoisonNeedle;
import com.gildedgames.the_aether.entities.projectile.EntityPoisonSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityTempestSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityValkyrieProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityZephyrSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityZojzSnowball;
import com.gildedgames.the_aether.entities.util.AetherMoaTypes;
import com.gildedgames.the_aether.entities.util.EntityAetherItem;

import cpw.mods.fml.common.registry.EntityRegistry;

public class EntitiesAether {

	public static Map<Class<?>, String> classToStringMapping = new HashMap<Class<?>, String>();

	public static Map<Integer, Class<?>> IDtoClassMapping = new HashMap<Integer, Class<?>>();

	private static Map<Class<?>, Integer> classToIDMapping = new HashMap<Class<?>, Integer>();

	private static Map<String, Integer> stringToIDMapping = new HashMap<String, Integer>();

	private static final Logger logger = LogManager.getLogger();

	public static void initialization() {
		register(EntityMoa.class, "moa", 0, 0x87bfef, 0x7a7a7a);
		register(EntityPhyg.class, "phyg", 1, 0xffc1d0, 0xffd939);
		register(EntityFlyingCow.class, "flying_cow", 2, 0xd8d8d8, 0xffd939);
		register(EntitySheepuff.class, "sheepuff", 3, 0xe2fcff, 0xcb9090);
		register(EntityAerbunny.class, "aerbunny", 4, 0xe2fcff, 0xffdff9);
		register(EntityAerwhale.class, "aerwhale", 5, 0x79b7d1, 0xe0d25c);
		register(EntityAerwhaleMount.class, "aerwhale_mount", 40, 0x79b7d1, 0xe0d25c);
		register(EntitySwet.class, "swet", 6, 0xcdda4f, 0x4fb1da);
		register(EntityCockatrice.class, "cockatrice", 7, 0x6cb15c, 0x6c579d);
		register(EntitySentry.class, "sentry", 8, 0x838c9a, 0x2561ba);
		register(EntityBattleSentry.class, "battle_sentry", 52, 0x838c9a, 0x286c78);
		register(EntityZephyr.class, "zephyr", 9, 0xdfdfdf, 0x99cfe8);
		register(EntityYoungZephyr.class, "young_zephyr", 35, 0xb8b5ab, 0x988c65);
		register(EntityTempest.class, "tempest", 53, 0x2a2838, 0x494089);
		register(EntityAechorPlant.class, "aechor_plant", 10, 0x076178, 0x4bc69e);
		register(EntityFlynx.class, "flynx", 49, 0xbff1f3, 0x7c68e2);
		register(EntityMimic.class, "mimic", 11, 0xb18132, 0x605a4e);
		register(EntityRaptor.class, "raptor", 41, 0x0000FF, 0x0047AB);
		register(EntityCyro.class, "cyro", 45, 0x1fdfdf, 0x19cfe8);
		register(EntityUro.class, "uro", 58, 0xc1c1c1, 0xffd311);
		register(EntityCinerarium.class, "cinerarium", 59, 0xe4903d, 0x7e1a00);
		register(EntityCarrionSprout.class, "carrion_sprout", 60, 0xc7d7e7, 0x57769b);
		register(EntityZephyroo.class, "zephyroo", 61, 0x86a4c8, 0x6184b0);
		register(EntityThunderlo.class, "thunderlo", 65, 0x9199ac, 0x99ca8f);
		register(EntityUligo.class, "uligo", 66, 0xd8e2d6, 0xa9e0cc);
		register(EntityAercenturion.class, "aercenturion", 69, 0x54545f, 0x75638b);
		register(EntityZarnillys.class, "zarnillys", 72, 0x474965, 0x5ca0ff);
		register(EntityElderZarnillys.class, "elder_zarnillys", 78, 0x474965, 0x54597f);
		register(EntityAerca.class, "aerca", 81, 0x361064, 0xa98ad0);
		register(EntityElysianGuardian.class, "elysian_guardian", 82, 0x3e3e3e, 0x00a8ec);
		register(EntityIrk.class, "irk", 84, 0x032d24, 0x039893);
		register(EntityCrystalDragon.class, "crystal_dragon", 85, 0xd8e4f8, 0x1bb7df);
		register(EntityZojz.class, "zojz", 87, 0x2a4552, 0x72c8c9);
		
		register(EntityCyroGuardian.class, "cyro_guardian", 48, 80, 3, true);
		register(EntitySlider.class, "slider", 12, 80, 3, true);
		register(EntityEnhancedSlider.class, "ancient_slider", 43, 80, 3, true);
		register(EntityDivineSlider.class, "divine_slider", 44, 80, 3, true);
		register(EntityMythicSlider.class, "mythic_slider", 55, 80, 3, true);
		register(EntityValkyrieQueen.class, "valkyrie_queen", 13, 80, 3, true);
		register(EntityAncientValkyrieQueen.class, "ancient_valkyrie_queen", 50, 80, 3, true);
		register(EntityDivineValkyrieQueen.class, "divine_valkyrie_queen", 51, 80, 3, true);
		register(EntitySunSpirit.class, "sun_spirit", 14, 80, 3, true);
		register(EntityAncientSunSpirit.class, "ancient_sun_spirit", 56, 80, 3, true);
		register(EntityDivineSunSpirit.class, "divine_sun_spirit", 63, 80, 3, true);
		register(EntityGenesisDragon.class, "genesis_dragon", 42, 80, 3, true);
		register(EntityLurker.class, "aer_lurker", 64, 80, 3, true);

		register(EntityDartGolden.class, "golden_dart", 15, 64, 20, false);
		register(EntityDartPoison.class, "poison_dart", 16, 64, 20, false);
		register(EntityDartEnchanted.class, "enchanted_dart", 17, 64, 20, false);
		register(EntityDartPhoenix.class, "phoenix_dart", 36, 64, 20, false);
		register(EntityPoisonNeedle.class, "poison_needle", 18, 64, 20, false);

		register(EntityFireProofItemAether.class, "EntityFireProofItemAether", 80, 64, 20, true);
		register(EntityCrystal.class, "crystal", 19, 64, 4, false);
		register(EntityWhirlwind.class, "whirlwind", 20, 80, 3, false);
		register(EntityValkyrie.class, "valkyrie", 22, 0xebd1ae, 0xf4eaaf);
		register(EntityFallenValkyrie.class, "fallen_valkyrie", 67, 0xd8e2d6, 0xa9e0cc);
		register(EntityEliteValkyrie.class, "elite_valkyrie", 89, 0xe7c79c, 0xb3faf6);
		register(EntityFireMinion.class, "fire_minion", 23, 0xe69426, 0xe5111e);
		register(EntityAncientFireMinion.class, "ancient_fire_minion", 74, 0xd9001d, 0xeb4c25);
		register(EntityDivineFireMinion.class, "divine_fire_minion", 75, 0xd20070, 0xd6006c);
		register(EntityMythicFireMinion.class, "mythic_fire_minion", 76, 0xd20070, 0xd6006c);
		register(EntityHellfireCinder.class, "hellfire_cinder", 77, 0xe5903d, 0x7e2a00);
		register(EntityMiniCloud.class, "mini_cloud", 24, 80, 3, false);
		register(EntityFakeItem.class, "fake_item_legacy", 62, 64, 20, true);

		register(EntityFloatingBlock.class, "floating_block", 25, 160, 20, true);
		register(EntityTNTPresent.class, "tnt_present", 26, 160, 20, true);
		register(EntityAetherTNT.class, "aether_tnt", 71, 160, 20, false);

		register(EntityPhoenixArrow.class, "phoenix_arrow", 27, 64, 20, true);
		register(EntityCyroArrow.class, "cyro_arrow", 86, 64, 20, true);
		register(EntityZephyrSnowball.class, "zephyr_snowball", 28, 64, 20, true);
		register(EntityTempestSnowball.class, "tempest_snowball", 54, 64, 20, true);
		register(EntityZojzSnowball.class, "zojz_snowball", 88, 64, 20, true);
		register(EntityPoisonSnowball.class, "poison_snowball", 73, 64, 20, true);
		register(EntityHammerProjectile.class, "hammer_projectile", 29, 64, 20, true);
		register(EntityJebHammerProjectile.class, "jeb_hammer_projectile", 33, 64, 20, true);
		register(EntityValkyrieProjectile.class, "valkyrie_projectile", 37, 64, 20, true);
		register(EntityAmplifiedHammerProjectile.class, "amplified_notch_hammer", 38, 64, 20, true);
		register(EntityAmplifiedJebHammerProjectile.class, "amplified_jeb_hammer", 39, 64, 20, true);
		register(EntityBattleSentryHammerProjectile.class, "battle_sentry_hammer", 46, 64, 20, true);
		register(EntityAmplifiedBattleSentryHammerProjectile.class, "amplified_battle_sentry_hammer", 47, 64, 20, true);
		register(EntityAercenturionProjectile.class, "aercenturion_projectile", 70, 64, 20, true);
		register(EntityCyroGuardianProjectile.class, "cyro_projectile", 68, 64, 20, true);
		register(EntityCinerariumProjectile.class, "cinerarium_projectile", 79, 64, 20, true);
		register(EntityLightningKnife.class, "lightning_knife", 30, 64, 10, true);
		register(EntityElysianGuardianLaser.class, "elysian_laser", 83, 64, 10, true);
		register(EntityParachute.class, "parachute", 31, 160, 20, true);
		register(EntityAetherItem.class, "aether_item", 32, 160, 20, true);

		AetherMoaTypes.initialization();
		PotionInebriation.initialization();
	}

	public static void register(Class<? extends Entity> entityClass, String entityName, int entityID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, entityID, Aether.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public static void register(Class<? extends Entity> entityClass, String entityName, int entityID, int primaryEggColor, int secondaryEggColor) {
		addMapping(entityClass, entityName, entityID, primaryEggColor, secondaryEggColor);
		EntityRegistry.registerModEntity(entityClass, entityName, entityID, Aether.instance, 80, 3, true);
	}

	private static void addMapping(Class<?> entityClass, String entityName, int entityID, int primaryEggColor, int secondaryEggColor) {
		if (IDtoClassMapping.containsKey(Integer.valueOf(entityID))) {
			throw new IllegalArgumentException("ID is already registered: " + entityID);
		} else {
			classToStringMapping.put(entityClass, entityName);
			IDtoClassMapping.put(Integer.valueOf(entityID), entityClass);
			classToIDMapping.put(entityClass, Integer.valueOf(entityID));
			stringToIDMapping.put(entityName, Integer.valueOf(entityID));
			ItemAetherSpawnEgg.entityEggs.put(Integer.valueOf(entityID), new AetherEggInfo(entityID, primaryEggColor, secondaryEggColor));
		}
	}

	public static Entity createEntityByID(int id, World p_75616_1_) {
		Entity entity = null;

		try {
			Class<?> oclass = getClassFromID(id);

			if (oclass != null) {
				entity = (Entity) oclass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{p_75616_1_});
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		if (entity == null) {
			logger.warn("Skipping Aether Entity with id " + id);
		}

		return entity;
	}

	public static int getEntityID(Entity p_75619_0_) {
		Class<?> oclass = p_75619_0_.getClass();
		return classToIDMapping.containsKey(oclass) ? ((Integer) classToIDMapping.get(oclass)).intValue() : -1;
	}

	public static Class<?> getClassFromID(int p_90035_0_) {
		return (Class<?>) IDtoClassMapping.get(Integer.valueOf(p_90035_0_));
	}

	public static String getStringFromID(int p_75617_0_) {
		Class<?> oclass = getClassFromID(p_75617_0_);

		return oclass != null ? (String) classToStringMapping.get(oclass) : null;
	}

	public static class AetherEggInfo
	{
		public final int spawnedID;
		public final int primaryColor;
		public final int secondaryColor;

		public AetherEggInfo(int spawnedID, int primaryColor, int secondaryColor)
		{
			this.spawnedID = spawnedID;
			this.primaryColor = primaryColor;
			this.secondaryColor = secondaryColor;
		}
	}

}