package com.gildedgames.the_aether.client.renders;

import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityAncientValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityDivineValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import com.gildedgames.the_aether.entities.fake.item.EntityFakeItem;
import com.gildedgames.the_aether.entities.fake.item.RenderFakeItem;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.entities.passive.EntityCarrionSprout;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.passive.EntityMiniCloud;
import com.gildedgames.the_aether.entities.passive.EntitySheepuff;
import com.gildedgames.the_aether.entities.passive.EntityThunderlo;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.MinecraftForgeClient;

import com.gildedgames.the_aether.client.models.entities.AercenturionModel;
import com.gildedgames.the_aether.client.models.entities.CarrionSproutModel;
import com.gildedgames.the_aether.client.models.entities.ElysianGuardianModel;
import com.gildedgames.the_aether.client.models.entities.TempestModel;
import com.gildedgames.the_aether.client.models.entities.ThunderloModel;
import com.gildedgames.the_aether.client.models.entities.UligoModel;
import com.gildedgames.the_aether.client.models.entities.YoungZephyrModel;
import com.gildedgames.the_aether.client.models.entities.ZarnillysModel;
import com.gildedgames.the_aether.client.models.entities.ZephyrooModel;
import com.gildedgames.the_aether.client.models.entities.ZojzModel;
import com.gildedgames.the_aether.client.renders.block.AetherEnchantmentTableRenderer;
import com.gildedgames.the_aether.client.renders.block.AetherFlowerRenderer;
import com.gildedgames.the_aether.client.renders.block.AetherTNTRenderer;
import com.gildedgames.the_aether.client.renders.block.BerryBushRenderer;
import com.gildedgames.the_aether.client.renders.block.DivineEnchantmentTableRenderer;
import com.gildedgames.the_aether.client.renders.block.SkyrootChestRenderer;
import com.gildedgames.the_aether.client.renders.block.TreasureChestBlockRenderer;
import com.gildedgames.the_aether.client.renders.entity.AechorPlantRenderer;
import com.gildedgames.the_aether.client.renders.entity.AerbunnyRenderer;
import com.gildedgames.the_aether.client.renders.entity.AercaRenderer;
import com.gildedgames.the_aether.client.renders.entity.AercenturionProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.AercenturionRenderer;
import com.gildedgames.the_aether.client.renders.entity.AerwhaleMountRenderer;
import com.gildedgames.the_aether.client.renders.entity.AerwhaleRenderer;
import com.gildedgames.the_aether.client.renders.entity.AmplifiedBattleSentryHammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.AmplifiedJebHammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.AmplifiedNotchHammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.AncientFireMinionRenderer;
import com.gildedgames.the_aether.client.renders.entity.AncientSunSpiritRenderer;
import com.gildedgames.the_aether.client.renders.entity.AncientValkyrieQueenRenderer;
import com.gildedgames.the_aether.client.renders.entity.BattleSentryHammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.BattleSentryRenderer;
import com.gildedgames.the_aether.client.renders.entity.CarrionSproutRenderer;
import com.gildedgames.the_aether.client.renders.entity.CinerariumProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.CinerariumRenderer;
import com.gildedgames.the_aether.client.renders.entity.CockatriceRenderer;
import com.gildedgames.the_aether.client.renders.entity.CryoRenderer;
import com.gildedgames.the_aether.client.renders.entity.CrystalRenderer;
import com.gildedgames.the_aether.client.renders.entity.CyroArrowRenderer;
import com.gildedgames.the_aether.client.renders.entity.CyroGuardianProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.CyroGuardianRenderer;
import com.gildedgames.the_aether.client.renders.entity.DartBaseRenderer;
import com.gildedgames.the_aether.client.renders.entity.DivineFireMinionRenderer;
import com.gildedgames.the_aether.client.renders.entity.DivineSliderRenderer;
import com.gildedgames.the_aether.client.renders.entity.DivineSunSpiritRenderer;
import com.gildedgames.the_aether.client.renders.entity.DivineValkyrieQueenRenderer;
import com.gildedgames.the_aether.client.renders.entity.ElderZarnillysRenderer;
import com.gildedgames.the_aether.client.renders.entity.EliteValkyrieRenderer;
import com.gildedgames.the_aether.client.renders.entity.ElysianGuardianLaserRenderer;
import com.gildedgames.the_aether.client.renders.entity.ElysianGuardianRenderer;
import com.gildedgames.the_aether.client.renders.entity.EnhancedSliderRenderer;
import com.gildedgames.the_aether.client.renders.entity.FallenValkyrieRenderer;
import com.gildedgames.the_aether.client.renders.entity.FireMinionRenderer;
import com.gildedgames.the_aether.client.renders.entity.FloatingBlockRenderer;
import com.gildedgames.the_aether.client.renders.entity.FlyingCowRenderer;
import com.gildedgames.the_aether.client.renders.entity.FlynxRenderer;
import com.gildedgames.the_aether.client.renders.entity.HammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.HellfireCinderRenderer;
import com.gildedgames.the_aether.client.renders.entity.IrkRenderer;
import com.gildedgames.the_aether.client.renders.entity.JebHammerProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.LightningKnifeRenderer;
import com.gildedgames.the_aether.client.renders.entity.LurkerRenderer;
import com.gildedgames.the_aether.client.renders.entity.MimicRenderer;
import com.gildedgames.the_aether.client.renders.entity.MiniCloudRenderer;
import com.gildedgames.the_aether.client.renders.entity.MoaRenderer;
import com.gildedgames.the_aether.client.renders.entity.MythicFireMinionRenderer;
import com.gildedgames.the_aether.client.renders.entity.MythicSliderRenderer;
import com.gildedgames.the_aether.client.renders.entity.ParachuteRenderer;
import com.gildedgames.the_aether.client.renders.entity.PhoenixArrowRenderer;
import com.gildedgames.the_aether.client.renders.entity.PhygRenderer;
import com.gildedgames.the_aether.client.renders.entity.PoisonSnowballRenderer;
import com.gildedgames.the_aether.client.renders.entity.RaptorRenderer;
import com.gildedgames.the_aether.client.renders.entity.SentryRenderer;
import com.gildedgames.the_aether.client.renders.entity.SheepuffRenderer;
import com.gildedgames.the_aether.client.renders.entity.SliderRenderer;
import com.gildedgames.the_aether.client.renders.entity.SunSpiritRenderer;
import com.gildedgames.the_aether.client.renders.entity.SwetRenderer;
import com.gildedgames.the_aether.client.renders.entity.TNTPresentRenderer;
import com.gildedgames.the_aether.client.renders.entity.TempestRenderer;
import com.gildedgames.the_aether.client.renders.entity.TempestSnowballRenderer;
import com.gildedgames.the_aether.client.renders.entity.ThunderloRenderer;
import com.gildedgames.the_aether.client.renders.entity.UligoRenderer;
import com.gildedgames.the_aether.client.renders.entity.UroRenderer;
import com.gildedgames.the_aether.client.renders.entity.ValkyrieProjectileRenderer;
import com.gildedgames.the_aether.client.renders.entity.ValkyrieQueenRenderer;
import com.gildedgames.the_aether.client.renders.entity.ValkyrieRenderer;
import com.gildedgames.the_aether.client.renders.entity.WhirlwindRenderer;
import com.gildedgames.the_aether.client.renders.entity.YoungZephyrRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZarnillysRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZephyrRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZephyrSnowballRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZephyrooRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZojzRenderer;
import com.gildedgames.the_aether.client.renders.entity.ZojzSnowballRenderer;
import com.gildedgames.the_aether.client.renders.items.PhoenixBowRenderer;
import com.gildedgames.the_aether.entities.block.EntityAetherTNT;
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
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.CrystalDragonRenderer;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.GenesisDragonRenderer;
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
import com.gildedgames.the_aether.entities.hostile.EntityZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;
import com.gildedgames.the_aether.entities.hostile.EntityYoungZephyr;
import com.gildedgames.the_aether.entities.hostile.EntityZarnillys;
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
import com.gildedgames.the_aether.entities.projectile.EntityPoisonSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityTempestSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityValkyrieProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityZephyrSnowball;
import com.gildedgames.the_aether.entities.projectile.EntityZojzSnowball;
import com.gildedgames.the_aether.entities.projectile.crystals.EntityCrystal;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartBase;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntityAetherEnchantmentTable;
import com.gildedgames.the_aether.tileentity.TileEntityDivineEnchantmentTable;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.tileentity.TileEntityTreasureChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RendersAether {

	public static void initialization() {
		/* Misc */
		register(EntityHammerProjectile.class, new HammerProjectileRenderer());
		register(EntityJebHammerProjectile.class, new JebHammerProjectileRenderer());
		register(EntityValkyrieProjectile.class, new ValkyrieProjectileRenderer());
		register(EntityAmplifiedHammerProjectile.class, new AmplifiedNotchHammerProjectileRenderer());
		register(EntityAmplifiedJebHammerProjectile.class, new AmplifiedJebHammerProjectileRenderer());
		register(EntityBattleSentryHammerProjectile.class, new BattleSentryHammerProjectileRenderer());
		register(EntityAmplifiedBattleSentryHammerProjectile.class, new AmplifiedBattleSentryHammerProjectileRenderer());
		register(EntityAercenturionProjectile.class, new AercenturionProjectileRenderer());
		register(EntityFloatingBlock.class, new FloatingBlockRenderer());
		register(EntityAetherTNT.class, new AetherTNTRenderer());
		register(EntityParachute.class, new ParachuteRenderer());
		register(EntityZephyrSnowball.class, new ZephyrSnowballRenderer());
		register(EntityTempestSnowball.class, new TempestSnowballRenderer());
		register(EntityZojzSnowball.class, new ZojzSnowballRenderer());
		register(EntityPoisonSnowball.class, new PoisonSnowballRenderer());
		register(EntityPhoenixArrow.class, new PhoenixArrowRenderer());
		register(EntityCyroArrow.class, new CyroArrowRenderer());
		register(EntityLightningKnife.class, new LightningKnifeRenderer());
		register(EntityCyroGuardianProjectile.class, new CyroGuardianProjectileRenderer(0.5F));
		register(EntityCinerariumProjectile.class, new CinerariumProjectileRenderer(0.5F));
		register(EntityElysianGuardianLaser.class, new ElysianGuardianLaserRenderer());

		/* Darts */
		register(EntityDartBase.class, new DartBaseRenderer());
	
		/* Crystals */
		register(EntityCrystal.class, new CrystalRenderer());

		/* Bosses */
		register(EntitySlider.class, new SliderRenderer());
		register(EntityEnhancedSlider.class, new EnhancedSliderRenderer());
		register(EntityDivineSlider.class, new DivineSliderRenderer());
		register(EntityMythicSlider.class, new MythicSliderRenderer());
		
		register(EntityCyroGuardian.class, new CyroGuardianRenderer());
		
		register(EntityLurker.class, new LurkerRenderer());
		
		register(EntityValkyrieQueen.class, new ValkyrieQueenRenderer());
		register(EntityAncientValkyrieQueen.class, new AncientValkyrieQueenRenderer());
		register(EntityDivineValkyrieQueen.class, new DivineValkyrieQueenRenderer());
		
		register(EntitySunSpirit.class, new SunSpiritRenderer());
		register(EntityAncientSunSpirit.class, new AncientSunSpiritRenderer());
		register(EntityDivineSunSpirit.class, new DivineSunSpiritRenderer());
		
		register(EntityGenesisDragon.class, new GenesisDragonRenderer());

		/* Hostile */
		register(EntityRaptor.class, new RaptorRenderer());
		register(EntityCyro.class, new CryoRenderer());
		register(EntityMimic.class, new MimicRenderer());
		register(EntitySentry.class, new SentryRenderer());
		register(EntityBattleSentry.class, new BattleSentryRenderer());
		register(EntityAechorPlant.class, new AechorPlantRenderer());
		register(EntityFireMinion.class, new FireMinionRenderer());
		register(EntityAncientFireMinion.class, new AncientFireMinionRenderer());
		register(EntityDivineFireMinion.class, new DivineFireMinionRenderer());
		register(EntityMythicFireMinion.class, new MythicFireMinionRenderer());
		register(EntityTempest.class, new TempestRenderer(new TempestModel()));
		register(EntityZephyr.class, new ZephyrRenderer());
		register(EntityYoungZephyr.class, new YoungZephyrRenderer(new YoungZephyrModel()));
		register(EntityValkyrie.class, new ValkyrieRenderer());
		register(EntityEliteValkyrie.class, new EliteValkyrieRenderer());
		register(EntityCockatrice.class, new CockatriceRenderer());
		register(EntityUro.class, new UroRenderer());
		register(EntityCinerarium.class, new CinerariumRenderer());
		register(EntityUligo.class, new UligoRenderer(new UligoModel(16), new UligoModel(0), 0.25F));
		register(EntityFallenValkyrie.class, new FallenValkyrieRenderer());
		register(EntityAercenturion.class, new AercenturionRenderer((ModelBiped)new AercenturionModel(), 1.0f));
		register(EntityZarnillys.class, new ZarnillysRenderer());
		register(EntityElderZarnillys.class, new ElderZarnillysRenderer());
		register(EntityHellfireCinder.class, new HellfireCinderRenderer());
		register(EntityAerca.class, new AercaRenderer());
		register(EntityElysianGuardian.class, new ElysianGuardianRenderer(new ElysianGuardianModel()));
		register(EntityIrk.class, new IrkRenderer());
		register(EntityCrystalDragon.class, new CrystalDragonRenderer());
		register(EntityZojz.class, new ZojzRenderer(new ZojzModel()));
		
		/* Passive */
		register(EntityMoa.class, new MoaRenderer());
		register(EntityPhyg.class, new PhygRenderer());
		register(EntityFlyingCow.class, new FlyingCowRenderer());
		register(EntitySheepuff.class, new SheepuffRenderer());
		register(EntityAerwhale.class, new AerwhaleRenderer());
		register(EntityAerwhaleMount.class, new AerwhaleMountRenderer());
		register(EntityAerbunny.class, new AerbunnyRenderer());
		register(EntitySwet.class, new SwetRenderer());
		register(EntityMiniCloud.class, new MiniCloudRenderer());
		register(EntityTNTPresent.class, new TNTPresentRenderer());
		register(EntityWhirlwind.class, new WhirlwindRenderer());
		register(EntityFlynx.class, new FlynxRenderer());
		register(EntityCarrionSprout.class, new CarrionSproutRenderer());
		register(EntityZephyroo.class, new ZephyrooRenderer(new ZephyrooModel(), 0.5f));
		register(EntityThunderlo.class, new ThunderloRenderer(new ThunderloModel(), 0.5f));

		MinecraftForgeClient.registerItemRenderer(ItemsAether.phoenix_bow, new PhoenixBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemsAether.tipped_phoenix_bow, new PhoenixBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemsAether.amplified_phoenix_bow, new PhoenixBowRenderer());		
		MinecraftForgeClient.registerItemRenderer(ItemsAether.cyro_bow, new PhoenixBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemsAether.tipped_cyro_bow, new PhoenixBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemsAether.amplified_cyro_bow, new PhoenixBowRenderer());
		
		RenderingRegistry.registerBlockHandler(new BerryBushRenderer());
		RenderingRegistry.registerBlockHandler(new TreasureChestBlockRenderer());
		RenderingRegistry.registerBlockHandler(new AetherFlowerRenderer());
		RenderingRegistry.registerEntityRenderingHandler((Class)EntityFakeItem.class, (Render)new RenderFakeItem());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChest.class, new TreasureChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAetherEnchantmentTable.class, new AetherEnchantmentTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDivineEnchantmentTable.class, new DivineEnchantmentTableRenderer());
	}

	public static void register(Class<? extends Entity> entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
}