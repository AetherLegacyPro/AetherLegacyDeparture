package com.gildedgames.the_aether;

import com.gildedgames.the_aether.events.AetherEntityEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.ancient.enchanter.AetherEnchantmentsAncientEnchanter;
import com.gildedgames.the_aether.entities.EntitiesAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.player.PlayerAetherEvents;
import com.gildedgames.the_aether.player.perks.AetherRankings;
import com.gildedgames.the_aether.registry.AetherRegistries;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.tileentity.AetherTileEntities;
import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenOverhaulLate;
import com.gildedgames.the_aether.world.biome.decoration.AetherGenStoneOverhaul;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.ArcticIslandWorldGen;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.DivineIslandWorldGen;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.GoldenIslandWorldGen;
import com.gildedgames.the_aether.world.biome.decoration.overhaul.PalladiumDungeonWorldGen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Aether.MOD_ID, version = "v1.05")
public class Aether {

	public static final String MOD_ID = "aether_legacy";

	@Instance(Aether.MOD_ID)
	public static Aether instance;
	public static final Logger LOG = LogManager.getLogger("aether_legacy");

	@SidedProxy(clientSide = "com.gildedgames.the_aether.client.ClientProxy", serverSide = "com.gildedgames.the_aether.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		AetherRankings.initialization();
		AetherNetwork.preInitialization();
		AetherConfig.init(event.getModConfigurationDirectory());
		if(AetherConfig.UseBaublesExpandedMenu()) {
			com.gildedgames.the_aether.compatibility.BaublesExpandedCompatibility.tryAssignSlots();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		BlocksAether.initialization();
		BlocksAether.initializeHarvestLevels();
		ItemsAether.initialization();
		AetherRegistries.register();
		EntitiesAether.initialization();
		AetherCreativeTabs.initialization();
		AetherTileEntities.initialization();
		AetherEnchantmentsAncientEnchanter.init();
		
		GameRegistry.registerWorldGenerator(new ArcticIslandWorldGen(), Integer.MAX_VALUE);
		GameRegistry.registerWorldGenerator(new GoldenIslandWorldGen(), Integer.MAX_VALUE);
		GameRegistry.registerWorldGenerator(new DivineIslandWorldGen(), Integer.MAX_VALUE);
		GameRegistry.registerWorldGenerator(new PalladiumDungeonWorldGen(), Integer.MAX_VALUE);
		
		AetherWorld.initialization();
		AchievementsAether.initialization();

		proxy.init();

		CommonProxy.registerEvent(new PlayerAetherEvents());
		CommonProxy.registerEvent(new AetherEventHandler());
		CommonProxy.registerEvent(new AetherEntityEvents());

		if(AetherConfig.UseBaublesExpandedMenu()) {
			com.gildedgames.the_aether.compatibility.BaublesExpandedCompatibility.initializeSlotTypeData();
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new AetherGenStoneOverhaul(), 0);
		GameRegistry.registerWorldGenerator(new AetherGenOverhaulLate(), 1);
	}

	public static ResourceLocation locate(String location) {
		return new ResourceLocation(MOD_ID, location);
	}

	public static ResourceLocation locateII(String location) {
		return new ResourceLocation("aetherii", location);
	}
	
	public static ResourceLocation locateHighlands(String location) {
		return new ResourceLocation("aether", location);
	}
	
	public static boolean isGamePaused() {
        final Minecraft mc = Minecraft.getMinecraft();
        return mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
    }

	public static String find(String location) {
		return modAddress() + location;
	}

	public static String findII(String location) {
		return "aetherii:" + location;
	}
	
	public static String findHighlands(String location) {
		return "aether:" + location;
	}

	public static String modAddress() {
		return MOD_ID + ":";
	}
	
	public static String getUnlocalisedName(String name) {
		return MOD_ID + "." + name;
	}
}
