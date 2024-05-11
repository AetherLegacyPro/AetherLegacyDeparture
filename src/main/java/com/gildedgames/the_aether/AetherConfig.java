package com.gildedgames.the_aether;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

public class AetherConfig {

	public static Configuration config;

	private static int max_life_shards, max_power_shards, max_dex_shards;

	private static boolean christmas_content, tallgrass, seasonal_christmas;

	private static int aether_biome_id, aether_dimension_id;

	private static boolean disable_trivia, old_mobs;

	private static boolean skyrootBucketOnly, valkyrie_cape, golden_feather;

	private static boolean floating_block_collision;

	private static int travel_dimension;

	private static boolean menu_enabled, menu_button, install_resourcepack;

	private static boolean legacy_altar_name;

	private static int inebriation_id;

	private static boolean sun_altar_multiplayer, repeat_sun_spirit_dialog;

	private static boolean aether_start;

	private static boolean disable_eternal_day;
	
	private static boolean disable_pink_aercloud_regen, enable_coldfire_notInAether, enable_hellfire_notInAether;
	
	private static boolean blaze_rod_recipe, budding_auralite_recipe, diamond_recipe;
	
	private static boolean disable_purple_aercloud, disable_violet_aercloud, disable_dark_purple_aercloud;
	private static boolean disable_green_aercloud, disable_lime_aercloud, disable_light_green_aercloud;
	private static boolean disable_pink_aercloud, disable_magenta_aercloud, disable_orange_aercloud;	
	private static boolean disable_aetheral_stone, disable_deific, disable_agiosite;
	private static boolean disable_void_tree, disable_auarlite_geos;
	
	private static boolean disable_parachutes;
	
	private static boolean aether_II_dungeons;
	
	private static boolean repair_material_tippedtool, misc_items_damageable, gloves_damageable, redstone_recipes, brewing_recipes;
	
	private static boolean divineral_recipe_hardmore, netherite_required_divineral_ingot;

	private static int phyg_spawnrate, flyingcow_spawnrate, sheepuff_spawnrate, aerbunny_spawnrate, moa_spawnrate, aerwhale_spawnrate, raptor_spawnrate, carrion_sprout_spawnrate, zephyroo_spawnrate, thunderlo_spawnrate, flynx_spawnrate;

	private static int zephyr_spawnrate, vulturnus_spawnrate, tempest_spawnrate, cockatrice_spawnrate, swet_spawnrate, aechorplant_spawnrate, whirlwind_spawnrate, cyro_spawnrate, uro_spawnrate, aerca_spawnrate;

	public static boolean arctic_island_enable, golden_island_enable, divine_island_enable, palladium_dungeon_enable;
	
	public static final String catMisc = "New Misc Options";
	public static final String catWorld = "New World Gen Options";
	
	public static void init(File location) {
		File newFile = new File(location + "/aether" + "/AetherI.cfg");

		try {
			newFile.createNewFile();
		} catch (IOException e) {

		}

		config = new Configuration(newFile);

		config.load();

		christmas_content = config.get("Aether World Generation", "Christmas Content", false).getBoolean(false);
		seasonal_christmas = config.get("Aether World Generation", "Spawns Holiday Trees during December and January automatically. Christmas Content overrides this.", true).getBoolean(true);
		tallgrass = config.get("Aether World Generation", "Enable Tall Grass", true).getBoolean(true);

		aether_dimension_id = config.get("World Identification", "Aether Dimension ID", 4).getInt(4);
		aether_biome_id = config.get("World Identification", "Aether Biome ID", 127).getInt(127);

		skyrootBucketOnly = config.get("Misc", "Activate portal with only Skyroot bucket", false).getBoolean(false);
		valkyrie_cape = config.get("Misc", "Enables the Valkyrie Cape in dungeon loot", true).getBoolean(true);
		golden_feather = config.get("Misc", "Enables the Golden Feather in dungeon loot", false).getBoolean(false);
		travel_dimension = config.get("Misc", "Dimension below aether", 0).getInt(0);
		floating_block_collision = config.get("Misc", "Floating block collision", true).getBoolean(true);
		
		disable_pink_aercloud_regen = config.getBoolean("enablePinkAercloudRegen", catMisc, true, "Should Pink Aerclouds heal the player if walked through?");
		enable_coldfire_notInAether = config.getBoolean("enableColdfireExistNotInAether", catMisc, false, "Should Coldfire be able to exist in dimensions other than the Aether?");
		enable_hellfire_notInAether = config.getBoolean("enableHellfireExistNotInAether", catMisc, false, "Should Hellfire be able to exist in dimensions other than the Aether?");
		
		disable_purple_aercloud = config.getBoolean("enablePurpleAercloud", catWorld, true, "Should Purple Aerclouds Generate?");
		disable_violet_aercloud = config.getBoolean("enableVioletAercloud", catWorld, true, "Should Violet Aerclouds Generate?");
		disable_dark_purple_aercloud = config.getBoolean("enableDarkPurpleAercloud", catWorld, true, "Should Dark Purple Aerclouds Generate?");
		
		disable_light_green_aercloud = config.getBoolean("enableLightGreenAercloud", catWorld, true, "Should Light Green Aerclouds Generate?");
		disable_green_aercloud = config.getBoolean("enableGreenAercloud", catWorld, true, "Should Green Aerclouds Generate?");
		disable_lime_aercloud = config.getBoolean("enableLimeAercloud", catWorld, true, "Should Lime Aerclouds Generate?");
		
		disable_pink_aercloud = config.getBoolean("enablePinkAercloud", catWorld, true, "Should Pink Aerclouds Generate?");
		disable_magenta_aercloud = config.getBoolean("enableMagentaAercloud", catWorld, true, "Should Magenta Aerclouds Generate?");
		disable_orange_aercloud = config.getBoolean("enableOrangeAercloud", catWorld, true, "Should Orange Aerclouds Generate?");
		
		disable_aetheral_stone = config.getBoolean("enableAetheralStone", catWorld, true, "Should Aetheral Stone Generate?");
		disable_deific = config.getBoolean("enableDeific", catWorld, true, "Should Deific Generate?");
		disable_agiosite = config.getBoolean("enableAgiosite", catWorld, true, "Should Agiosite Generate?");
		
		disable_void_tree = config.getBoolean("enableVoidTree", catWorld, true, "Should Void Trees Generate?");
		disable_auarlite_geos = config.getBoolean("enableAuraliteGeos", catWorld, true, "Should Auralite Geos Generate?");
		
		blaze_rod_recipe = config.getBoolean("enableBlazeRodFreezableRecipe", catMisc, true, "Should cinerarium rods output blaze rods or cyro rods when put in the Freezer?");
		diamond_recipe = config.getBoolean("enableDiamondFreezableRecipe", catMisc, true, "Should Diamond Aerclouds output diamonds when put in the Freezer?");

		budding_auralite_recipe = config.getBoolean("enableBuddingAuraliteRecipe", catMisc, true, "Should budding auralite be craftable?");		
		repair_material_tippedtool = config.getBoolean("repairItemAuraliteCrystal", catMisc, true, "The repair item for tipped tools is auralite crystal, if false it will be the material the tool/sword is made from.");
		divineral_recipe_hardmore = config.getBoolean("divineralRecipeHardmore", catMisc, true, "Should divineral ingots have a difficult or easier recipe?");
		netherite_required_divineral_ingot = config.getBoolean("netheriteRequiredInDivineralIngot", catMisc, false, "If Et Futurum Requiem is installed should netherite ingots be required in the divineral ingot recipe?");
		redstone_recipes = config.getBoolean("enableRedstoneRecipes", catMisc, true, "Should the player be able to craft redstone items and blocks from materials in the aether?");
		brewing_recipes = config.getBoolean("enableBrewingRecipes", catMisc, true, "Should the player be able to craft the cauldron, brewing stand, and glass bottles from materials in the aether?");
		disable_parachutes = config.getBoolean("disableParachutes", catMisc, false, "Should parachutes be removed from being obtainable in survivial? This option is mainly for servers due to the crash they cause if the player falls out of the aether while on one and tries to enter the aether portal.");		
		misc_items_damageable = config.getBoolean("miscItemsDamageable", catMisc, true, "Should misc items such as the Healing Stone or the Golden Feather degrate slowly while worn?");
		gloves_damageable = config.getBoolean("glovesDamageable", catMisc, true, "Should gloves degrate slowly while worn?");
		
		aether_II_dungeons = config.getBoolean("enableAetherIIDungeon", catWorld, false, "If aether II is installed, the entrances to the Slider's Labyrinth can be found in structures similar to geos underground.");
		
		disable_trivia = config.get("Trivia", "Disable random trivia", false).getBoolean(false);
		
		arctic_island_enable = config.getBoolean("arctic_island_enable", catWorld, true, "Should Arctic Islands Generate?");
		golden_island_enable = config.getBoolean("golden_island_enable", catWorld, true, "Should Golden Islands Generate?");
		divine_island_enable = config.getBoolean("divine_island_enable", catWorld, true, "Should Divine Islands Generate?");
		palladium_dungeon_enable = config.getBoolean("palladium_dungeon_enable", catWorld, true, "Should the Palladium Dungeon Generate?");

		old_mobs = config.get("Misc", "Enable Legacy Visuals", false).getBoolean(false);

		aether_start = config.get("Gameplay", "Spawns Player with Aether Portal Frame", false).getBoolean(false);

		max_life_shards = config.get("Gameplay", "Max Life Shards", 10).getInt(10);
		max_dex_shards = config.get("Gameplay", "Max Dexterity Shards", 10).getInt(10);
		max_power_shards = config.get("Gameplay", "Max Power Shards - (If player dies they loose this)", 20).getInt(20);

		menu_enabled = config.get("Misc", "Enables the Aether Menu", false).getBoolean(false);
		menu_button = config.get("Misc", "Enables the Aether Menu toggle button", true).getBoolean(true);

		install_resourcepack = config.get("Misc", "Determines whether the Aether b1.7.3 resource pack should be generated.", true).getBoolean(true);

		legacy_altar_name = config.get("Misc", "Changes whether the Altar should be named Enchanter or not.", false).getBoolean(false);

		inebriation_id = config.get("Misc", "Sets the id for the Inebriation effect.", 31).getInt(31);

		sun_altar_multiplayer = config.get("Gameplay", "Removes the requirement for a player to be an operator to use the Sun Altar in multiplayer.", false).getBoolean(false);

		repeat_sun_spirit_dialog = config.get("Misc", "If disabed, the Sun Spirit's dialog will only show once per world.", true).getBoolean(true);

		disable_eternal_day = config.get("Misc", "Enables eternal day making time cycle in the Aether base on if having killed the Sun Spirit. This is mainly intended for use in modpacks.", false).getBoolean(false);

		//Spawnrates
		phyg_spawnrate = config.get("Spawnrates", "Phyg Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		flyingcow_spawnrate = config.get("Spawnrates", "Flying Cow Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		sheepuff_spawnrate = config.get("Spawnrates", "Sheepuff Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		aerbunny_spawnrate = config.get("Spawnrates", "Aerbunny Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		moa_spawnrate = config.get("Spawnrates", "Moa Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		carrion_sprout_spawnrate = config.get("Spawnrates", "Carrion Sprout Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		aerwhale_spawnrate = config.get("Spawnrates", "Aerwhale Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		zephyroo_spawnrate = config.get("Spawnrates", "Zephyroo Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		thunderlo_spawnrate = config.get("Spawnrates", "Thunderlo Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		flynx_spawnrate = config.get("Spawnrates", "Flynx Spawnrate. 1 is always, higher numbers decrease chances.", 1).getInt(1);
		zephyr_spawnrate = config.get("Spawnrates", "Zephyr Spawnrate. 1 is always, higher numbers decrease chances.", 85).getInt(65);
		tempest_spawnrate = config.get("Spawnrates", "Tempest Spawnrate. 1 is always, higher numbers decrease chances.", 85).getInt(65);
		vulturnus_spawnrate = config.get("Spawnrates", "Vulturnus Spawnrate. 1 is always, higher numbers decrease chances.", 85).getInt(65);
		cockatrice_spawnrate = config.get("Spawnrates", "Cockatrice Spawnrate. 1 is always, higher numbers decrease chances.", 45).getInt(45);
		raptor_spawnrate = config.get("Spawnrates", "Anzu Spawnrate. 1 is always, higher numbers decrease chances.", 45).getInt(45);
		cyro_spawnrate = config.get("Spawnrates", "Cyro Spawnrate. 1 is always, higher numbers decrease chances.", 45).getInt(45);
		uro_spawnrate = config.get("Spawnrates", "Uro Spawnrate. 1 is always, higher numbers decrease chances.", 45).getInt(45);
		aerca_spawnrate = config.get("Spawnrates", "Aerca Spawnrate. 1 is always, higher numbers decrease chances.", 75).getInt(75);
		
		swet_spawnrate = config.get("Spawnrates", "Swet Spawnrate. 1 is always, higher numbers decrease chances.", 20).getInt(20);
		aechorplant_spawnrate = config.get("Spawnrates", "Aechor Plant Spawnrate. 1 is always, higher numbers decrease chances.", 10).getInt(10);
		whirlwind_spawnrate = config.get("Spawnrates", "Whirlwind Spawnrate. 1 is always, higher numbers decrease chances.", 55).getInt(55);
		
		config.save();
	}
	
	public static boolean enableAetherIIDungeon() {
		return AetherConfig.aether_II_dungeons;
	}
	
	public static boolean disableParachutes() {
		return AetherConfig.disable_parachutes;
	}
	
	public static boolean enableAetheralStone() {
		return AetherConfig.disable_aetheral_stone;
	}
	
	public static boolean enableDeific() {
		return AetherConfig.disable_deific;
	}
	
	public static boolean enableAgisoite() {
		return AetherConfig.disable_agiosite;
	}
	
	public static boolean enablePinkAercloud() {
		return AetherConfig.disable_pink_aercloud;
	}
	
	public static boolean enableMagentaAercloud() {
		return AetherConfig.disable_magenta_aercloud;
	}
	
	public static boolean enableOrangeAercloud() {
		return AetherConfig.disable_orange_aercloud;
	}
	
	public static boolean enableLightGreenAercloud() {
		return AetherConfig.disable_light_green_aercloud;
	}
	
	public static boolean enableGreenAercloud() {
		return AetherConfig.disable_green_aercloud;
	}
	
	public static boolean enableLimeAercloud() {
		return AetherConfig.disable_lime_aercloud;
	}
	
	public static boolean enablePurpleAercloud() {
		return AetherConfig.disable_purple_aercloud;
	}
	
	public static boolean enableVioletAercloud() {
		return AetherConfig.disable_violet_aercloud;
	}
	
	public static boolean enableDarkPurpleAercloud() {
		return AetherConfig.disable_dark_purple_aercloud;
	}
	
	public static boolean enableVoidTree() {
		return AetherConfig.disable_void_tree;
	}
	
	public static boolean enableAuraliteGeos() {
		return AetherConfig.disable_auarlite_geos;
	}

	public static int getAetherDimensionID() {
		return AetherConfig.aether_dimension_id;
	}

	public static int getAetherBiomeID() {
		return AetherConfig.aether_biome_id;
	}

	public static int getMaxLifeShards() {
		return AetherConfig.max_life_shards;
	}
	
	public static int getMaxPowerShards() {
		return AetherConfig.max_power_shards;
	}
	
	public static int getMaxDexShards() {
		return AetherConfig.max_dex_shards;
	}
	
	public static int getTravelDimensionID() {
		return AetherConfig.travel_dimension;
	}

	public static boolean shouldFloatWithBlock() {
		return AetherConfig.floating_block_collision;
	}
	
	public static boolean shouldPinkAercloudRegen() {
		return AetherConfig.disable_pink_aercloud_regen;
	}
	
	public static boolean shouldColdfireExistNotInAether() {
		return AetherConfig.enable_coldfire_notInAether;
	}
	
	public static boolean shouldHellfireExistNotInAether() {
		return AetherConfig.enable_hellfire_notInAether;
	}
	
	public static boolean BlazeRodRecipe() {
		return AetherConfig.blaze_rod_recipe;
	}
	
	public static boolean DiamondFreezableRecipe() {
		return AetherConfig.diamond_recipe;
	}
	
	public static boolean BuddingAuraliteRecipe() {
		return AetherConfig.budding_auralite_recipe;
	}
	
	public static boolean RepairMaterialTipped() {
		return AetherConfig.repair_material_tippedtool;
	}
	
	public static boolean DivineralRecipeHardmore() {
		return AetherConfig.divineral_recipe_hardmore;
	}
	
	public static boolean NetheriteRequiredInDivineralIngot() {
		return AetherConfig.netherite_required_divineral_ingot;
	}
	
	public static boolean MiscItemDamageable() {
		return AetherConfig.misc_items_damageable;
	}
	
	public static boolean GlovesDamageable() {
		return AetherConfig.gloves_damageable;
	}
	
	public static boolean BrewingItemsCraftable() {
		return AetherConfig.brewing_recipes;
	}
	
	public static boolean RedstoneItemsCraftable() {
		return AetherConfig.redstone_recipes;
	}

	public static boolean triviaDisabled() {
		return AetherConfig.disable_trivia;
	}

	public static boolean oldMobsEnabled() {
		return AetherConfig.old_mobs;
	}

	public static boolean shouldLoadHolidayContent() {
		return AetherConfig.christmas_content;
	}

	public static boolean tallgrassEnabled() {
		return AetherConfig.tallgrass;
	}

	public static boolean activateOnlyWithSkyroot() {
		return AetherConfig.skyrootBucketOnly;
	}

	public static boolean valkyrieCapeEnabled()
	{
		return AetherConfig.valkyrie_cape;
	}

	public static boolean goldenFeatherEnabled()
	{
		return AetherConfig.golden_feather;
	}

	public static boolean menuEnabled()
	{
		return AetherConfig.menu_enabled;
	}

	public static boolean menuButtonEnabled()
	{
		return AetherConfig.menu_button;
	}

	public static boolean installResourcepack()
	{
		return AetherConfig.install_resourcepack;
	}

	public static boolean legacyAltarName() {
		return AetherConfig.legacy_altar_name;
	}

	public static int getInebriationId()
	{
		return AetherConfig.inebriation_id;
	}

	public static boolean sunAltarMultiplayer() {
		return AetherConfig.sun_altar_multiplayer;
	}

	public static boolean repeatSunSpiritDialogue() {
		return repeat_sun_spirit_dialog;
	}

	public static boolean shouldAetherStart()
	{
		return aether_start;
	}

	public static boolean eternalDayDisabled()
	{
		return disable_eternal_day;
	}

	public static boolean allowSeasonalChristmas() {
		return seasonal_christmas;
	}
	
	public static int getFlynxSpawnrate() {
		return flynx_spawnrate;
	}
	public static int getPhygSpawnrate() {
		return phyg_spawnrate;
	}
	public static int getFlyingCowSpawnrate() {
		return flyingcow_spawnrate;
	}
	public static int getSheepuffSpawnrate() {
		return sheepuff_spawnrate;
	}
	public static int getAerbunnySpawnrate() {
		return aerbunny_spawnrate;
	}
	public static int getMoaSpawnrate() {
		return moa_spawnrate;
	}
	public static int getAerwhaleSpawnrate() {
		return aerwhale_spawnrate;
	}
	public static int getAercaSpawnrate() {
		return aerca_spawnrate;
	}
	public static int getZephyrSpawnrate() {
		return zephyr_spawnrate;
	}
	public static int getVulturnusSpawnrate() {
		return vulturnus_spawnrate;
	}
	public static int getTempestSpawnrate() {
		return tempest_spawnrate;
	}
	public static int getCockatriceSpawnrate() {
		return cockatrice_spawnrate;
	}
	public static int getCyroSpawnrate() {
		return cyro_spawnrate;
	}
	public static int getUroSpawnrate() {
		return uro_spawnrate;
	}
	public static int getThunderloSpawnrate() {
		return thunderlo_spawnrate;
	}
	public static int getRaptorSpawnrate() {
		return cockatrice_spawnrate;
	}
	public static int getCarrionSproutSpawnrate() {
		return carrion_sprout_spawnrate;
	}
	public static int getZephyrooSpawnrate() {
		return zephyroo_spawnrate;
	}
	public static int getSwetSpawnrate() {
		return swet_spawnrate;
	}
	public static int getAechorPlantSpawnrate() {
		return aechorplant_spawnrate;
	}
	public static int getWhirlwindSpawnrate() {
		return whirlwind_spawnrate;
	}
}