package com.gildedgames.the_aether.registry.achievements;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.common.FMLCommonHandler;

public class AchievementsAether {

	public static Achievement enter_aether, getting_wood_again; 
	public static Achievement defeat_bronze, defeat_cobalt, defeat_silver, defeat_gold, defeat_osmium, defeat_palladium;
	public static Achievement ancient_defeat_bronze, ancient_defeat_silver, ancient_defeat_gold; 
	public static Achievement divine_defeat_bronze, divine_defeat_silver, divine_defeat_gold;
	public static Achievement mythic_defeat_bronze, skyroot_crafting, aether_sword, kill_thunderlo, kill_zephyroo;
	public static Achievement freezer, continuum_orb, electrified, aether_hunter, shrine_mender;
	public static Achievement enchanter, divineral, arkenium_helmet, bringing_out_colors;
	public static Achievement confractus_staff, auralite, zarnillys_scales, divineral_block;
	public static Achievement kill_elysian_guardian, kill_aercenturion;
	public static Achievement aether_florist, arctic_island, golden_island, divine_island;
	public static Achievement incubator, amplifier, amplifier_structure, ancient_enchanter, sun_altar, aether_enchantment_table;
	public static Achievement skyroot_tools, holystone_tools, zanite_tools, arkenium_tools, continuum_tools, grav_tools, artifact;
	public static Achievement blue_cloud, golden_cloud, flying_pig, flying_aerwhale, loreception, fruit_stew;
	public static Achievement divineral_set, phoenix_set, obsidian_set, valkyrie_set, neptune_set, agility_boots, amplified_agility_boots, amplified_sentry_boots; 
	public static Achievement zanite_gemstone, arkenium_fragment, continuum_gemstone;
	public static Achievement getting_scaled, getting_tipped, true_thorns, discharged;
	public static Achievement powering_up, iron_skin, agility_shard;
	public static Achievement strengthened_levitation, godly_reach, builders_beware, notch_restored, realm_conquer, lucky;
	public static Achievement newnew_ore, charging_the_gemstone, even_shinier, balanced_flight, not_balanced_flight, next_level_enchantments;
	
	public static AchievementPage ACpage;

	public static void initialization() {
		enter_aether = new AetherAchievement("advancement.aether_legacy.enter_aether", "enter_aether", 0, 7, Blocks.glowstone, (Achievement) null).registerStat();
		getting_wood_again = new AetherAchievement("advancement.aether_legacy.getting_wood_again", "getting_wood_again", 0, 3, BlocksAether.skyroot_log, enter_aether).registerStat();
		skyroot_crafting = new AetherAchievement("advancement.aether_legacy.skyroot_crafting", "skyroot_crafting", 0, 1, BlocksAether.skyroot_workbench, getting_wood_again).registerStat();
		
		loreception = new AetherAchievement("advancement.aether_legacy.loreception", "loreception", 2, 7, ItemsAether.lore_book, enter_aether).registerStat();
		
		blue_cloud = new AetherAchievement("advancement.aether_legacy.blue_aercloud", "blue_aercloud", 2, 5, new ItemStack(BlocksAether.aercloud, 1, 1), enter_aether).registerStat();
		golden_cloud = new AetherAchievement("advancement.aether_legacy.golden_aercloud", "golden_aercloud", 2, 3, new ItemStack(BlocksAether.aercloud, 1, 2), blue_cloud).registerStat();
		
		aether_sword = new AetherAchievement("advancement.aether_legacy.aether_sword", "aether_sword", -2, 1, ItemsAether.skyroot_sword, skyroot_crafting).registerStat();
		kill_thunderlo = new AetherAchievement("advancement.aether_legacy.kill_thunderlo", "kill_thunderlo", -2, -1, ItemsAether.thunderlo_leather, aether_sword).registerStat();
		kill_zephyroo = new AetherAchievement("advancement.aether_legacy.kill_zephyroo", "kill_zephyroo", -2, 3, ItemsAether.zephyroo_leather, aether_sword).registerStat();		
		aether_hunter = new AetherAchievement("advancement.aether_legacy.aether_hunter", "aether_hunter", -4, 1, ItemsAether.zanite_sword, aether_sword).registerStat();
				
		kill_elysian_guardian = new AetherAchievement("advancement.aether_legacy.kill_elysian_guardian", "kill_elysian_guardian", -6, 1, new ItemStack(ItemsAether.elysian_core), aether_hunter).registerStat();
		kill_aercenturion = new AetherAchievement("advancement.aether_legacy.kill_aercenturion", "kill_aercenturion", -4, 3, new ItemStack(BlocksAether.mythic_carved_stone), aether_hunter).registerStat();
		zarnillys_scales = new AetherAchievement("advancement.aether_legacy.zarnillys_scales", "zarnillys_scales", -4, -1, new ItemStack(ItemsAether.zarnillys_scales), aether_hunter).registerStat();
		getting_scaled = new AetherAchievement("advancement.aether_legacy.getting_scaled", "getting_scaled", -4, -3, new ItemStack(ItemsAether.scaled_gravitite_chestplate), zarnillys_scales).registerStat();			

		shrine_mender = new AetherAchievement("advancement.aether_legacy.shrine_mender", "shrine_mender", -8, 1, new ItemStack(ItemsAether.elysian_offering), kill_elysian_guardian).registerStat();
		
		flying_pig = new AetherAchievement("advancement.aether_legacy.mount_phyg", "mount_phyg", 0, -1, Items.saddle, skyroot_crafting).registerStat();		
		flying_aerwhale = new AetherAchievement("advancement.aether_legacy.flying_aerwhale", "flying_aerwhale", -2, 5, ItemsAether.zephyroo_saddle, kill_zephyroo).registerStat();
		
		defeat_bronze = new AetherAchievement("advancement.aether_legacy.bronze_dungeon", "bronze_dungeon", -2, 13, new ItemStack(ItemsAether.dungeon_key, 1, 0), enter_aether).registerStat();
		defeat_cobalt = new AetherAchievement("advancement.aether_legacy.defeat_cobalt", "defeat_cobalt", 2, 11, new ItemStack(ItemsAether.dungeon_key, 1, 10), enter_aether).registerStat();
		defeat_silver = new AetherAchievement("advancement.aether_legacy.silver_dungeon", "silver_dungeon", -1, 14, new ItemStack(ItemsAether.dungeon_key, 1, 1), enter_aether).registerStat();
		defeat_gold = new AetherAchievement("advancement.aether_legacy.gold_dungeon", "gold_dungeon", 1, 14, new ItemStack(ItemsAether.dungeon_key, 1, 2), enter_aether).registerStat();
		defeat_osmium = new AetherAchievement("advancement.aether_legacy.defeat_osmium", "defeat_osmium", -2, 11, new ItemStack(ItemsAether.dungeon_key, 1, 11), enter_aether).registerStat();
		defeat_palladium = new AetherAchievement("advancement.aether_legacy.defeat_palladium", "defeat_palladium", 6, 13, new ItemStack(ItemsAether.dungeon_key, 1, 14), enter_aether).registerStat();
		
		electrified = new AetherAchievement("advancement.aether_legacy.electrified", "electrified", -4, 11, new ItemStack(ItemsAether.lightning_knife), defeat_osmium).registerStat();
		
		confractus_staff = new AetherAchievement("advancement.aether_legacy.confractus_staff", "confractus_staff", 4, 11, new ItemStack(ItemsAether.confractus_staff), defeat_cobalt).registerStat();
		
		sun_altar = new AetherAchievement("advancement.aether_legacy.sun_altar", "sun_altar", 3, 14, new ItemStack(BlocksAether.sun_altar), defeat_gold).registerStat();
		
		ancient_defeat_bronze = new AetherAchievement("advancement.aether_legacy.ancient_defeat_bronze", "ancient_defeat_bronze", -4, 13, new ItemStack(ItemsAether.dungeon_key, 1, 3), defeat_bronze).registerStat();
		ancient_defeat_silver = new AetherAchievement("advancement.aether_legacy.ancient_defeat_silver", "ancient_defeat_silver", -1, 16, new ItemStack(ItemsAether.dungeon_key, 1, 5), defeat_silver).registerStat();
		ancient_defeat_gold = new AetherAchievement("advancement.aether_legacy.ancient_defeat_gold", "ancient_defeat_gold", 1, 16, new ItemStack(ItemsAether.dungeon_key, 1, 7), defeat_gold).registerStat();
		
		divine_defeat_bronze = new AetherAchievement("advancement.aether_legacy.divine_defeat_bronze", "divine_defeat_bronze", -6, 13, new ItemStack(ItemsAether.dungeon_key, 1, 4), ancient_defeat_bronze).registerStat();
		divine_defeat_silver = new AetherAchievement("advancement.aether_legacy.divine_defeat_silver", "divine_defeat_silver", -1, 18, new ItemStack(ItemsAether.dungeon_key, 1, 6), ancient_defeat_silver).registerStat();
		divine_defeat_gold = new AetherAchievement("advancement.aether_legacy.divine_defeat_gold", "divine_defeat_gold", 1, 18, new ItemStack(ItemsAether.dungeon_key, 1, 8), ancient_defeat_gold).registerStat();
		
		mythic_defeat_bronze = new AetherAchievement("advancement.aether_legacy.mythic_defeat_bronze", "mythic_defeat_bronze", -8, 13, new ItemStack(ItemsAether.dungeon_key, 1, 9), divine_defeat_bronze).registerStat();
		
		agility_boots = new AetherAchievement("advancement.aether_legacy.agility_boots", "agility_boots", 3, 18, new ItemStack(ItemsAether.agility_boots), divine_defeat_gold).registerStat();
		
		aether_florist = new AetherAchievement("advancement.aether_legacy.aether_florist", "aether_florist", -6, 7, BlocksAether.aether_tulips, enter_aether).registerStat();
		arctic_island = new AetherAchievement("advancement.aether_legacy.arctic_island", "arctic_island", -6, 5, BlocksAether.wisproot_log, aether_florist).registerStat();
		golden_island = new AetherAchievement("advancement.aether_legacy.golden_island", "golden_island", -6, 9, BlocksAether.golden_oak_log, aether_florist).registerStat();
		divine_island = new AetherAchievement("advancement.aether_legacy.divine_island", "divine_island", -8, 7, BlocksAether.divine_oak_log, aether_florist).registerStat();
		
		skyroot_tools = new AetherAchievement("advancement.aether_legacy.skyroot_tools", "skyroot_tools", 2, 1, ItemsAether.skyroot_pickaxe, skyroot_crafting).registerStat();
		holystone_tools = new AetherAchievement("advancement.aether_legacy.holystone_tools", "holystone_tools", 4, 1, ItemsAether.holystone_pickaxe, skyroot_tools).registerStat();
		
		incubator = new AetherAchievement("advancement.aether_legacy.incubator", "incubator", 4, 3, BlocksAether.incubator, holystone_tools).registerStat();
		
		zanite_gemstone = new AetherAchievement("advancement.aether_legacy.zanite_gemstone", "zanite_gemstone", 6, 1, ItemsAether.zanite_gemstone, holystone_tools).registerStat();						
		ancient_enchanter = new AetherAchievement("advancement.aether_legacy.ancient_enchanter", "ancient_enchanter", 4, 5, BlocksAether.ancient_enchanter, zanite_gemstone).registerStat();
		
		continuum_gemstone = new AetherAchievement("advancement.aether_legacy.continuum_gemstone", "continuum_gemstone", 8, 5, ItemsAether.continuum_gemstone, zanite_gemstone).registerStat();			
		continuum_tools = new AetherAchievement("advancement.aether_legacy.continuum_tools", "continuum_tools", 10, 5, ItemsAether.continuum_pickaxe, continuum_gemstone).registerStat();			
		
		auralite = new AetherAchievement("advancement.aether_legacy.auralite", "auralite", 6, 7, new ItemStack(ItemsAether.auralite_crystal), zanite_gemstone).registerStat();
		getting_tipped = new AetherAchievement("advancement.aether_legacy.getting_tipped", "getting_tipped", 6, 9, new ItemStack(ItemsAether.tipped_gravitite_sword), auralite).registerStat();
		
		
		enchanter = new AetherAchievement("advancement.aether_legacy.enchanter", "enchanter", 8, 1, BlocksAether.enchanter, zanite_gemstone).registerStat();
		grav_tools = new AetherAchievement("advancement.aether_legacy.gravitite_tools", "gravitite_tools", 10, 1, ItemsAether.gravitite_pickaxe, enchanter).registerStat();
		aether_enchantment_table = new AetherAchievement("advancement.aether_legacy.aether_enchantment_table", "aether_enchantment_table", 10, -1, BlocksAether.aether_enchantment_table, enchanter).registerStat();
		fruit_stew = new AetherAchievement("advancement.aether_legacy.fruit_stew", "fruit_stew", 6, -1, ItemsAether.enchanted_fruit_stew, enchanter).registerStat();
		artifact = new AetherAchievement("advancement.aether_legacy.artifact", "artifact", 12, 1, BlocksAether.primeval_artifact, grav_tools).registerStat();		
		divineral = new AetherAchievement("advancement.aether_legacy.divineral", "divineral", 14, 1, ItemsAether.divineral_ingot, artifact).registerStat();
		
		newnew_ore = new AetherAchievement("advancement.aether_legacy.newnew_ore", "newnew_ore", 10, 3, BlocksAether.empyrean_ore, grav_tools).registerStat();
		charging_the_gemstone = new AetherAchievement("advancement.aether_legacy.charging_the_gemstone", "charging_the_gemstone", 12, 3, ItemsAether.charged_empyrean_gemstone, newnew_ore).registerStat();
		even_shinier = new AetherAchievement("advancement.aether_legacy.even_shinier", "even_shinier", 12, 5, ItemsAether.reinforced_regeneration_stone, charging_the_gemstone).registerStat();		
		balanced_flight = new AetherAchievement("advancement.aether_legacy.balanced_flight", "balanced_flight", 12, 7, ItemsAether.amplified_valkyrie_ring, even_shinier).registerStat();
		not_balanced_flight = new AetherAchievement("advancement.aether_legacy.not_balanced_flight", "not_balanced_flight", 12, 9, ItemsAether.false_wings, balanced_flight).registerStat();
		
		bringing_out_colors = new AetherAchievement("advancement.aether_legacy.bringing_out_colors", "bringing_out_colors", 8, 3, BlocksAether.enchanted_holystone, enchanter).registerStat();
		
		divineral_block = new AetherAchievement("advancement.aether_legacy.divineral_block", "divineral_block", 14, -1, BlocksAether.divineral_block, divineral).registerStat();
		amplifier = new AetherAchievement("advancement.aether_legacy.amplifier", "amplifier", 16, 1, BlocksAether.amplifier, divineral).registerStat();
		amplifier_structure = new AetherAchievement("advancement.aether_legacy.amplifier_structure", "amplifier_structure", 18, 1, BlocksAether.golden_oak_bookshelf, amplifier).registerStat();
				
		strengthened_levitation = new AetherAchievement("advancement.aether_legacy.strengthened_levitation", "strengthened_levitation", 18, -3, ItemsAether.divineral_pickaxe, amplifier_structure).registerStat();
		godly_reach = new AetherAchievement("advancement.aether_legacy.godly_reach", "godly_reach", 20, -2, ItemsAether.amplified_valkyrie_lance, amplifier_structure).registerStat();
		builders_beware = new AetherAchievement("advancement.aether_legacy.builders_beware", "builders_beware", 16, -2, ItemsAether.builder_slayer, amplifier_structure).registerStat();
		
		notch_restored = new AetherAchievement("advancement.aether_legacy.notch_restored", "notch_restored", 16, 0, ItemsAether.amplified_notch_hammer, amplifier_structure).registerStat();
		realm_conquer = new AetherAchievement("advancement.aether_legacy.realm_conquer", "realm_conquer", 20, 0, ItemsAether.amplified_overworld_slayer, amplifier_structure).registerStat();
		lucky = new AetherAchievement("advancement.aether_legacy.lucky", "lucky", 20, 1, ItemsAether.amplified_notched_pickaxe, amplifier_structure).registerStat();
		
		phoenix_set = new AetherAchievement("advancement.aether_legacy.phoenix_set", "phoenix_set", 20, 3, ItemsAether.amplified_phoenix_helmet, amplifier_structure).registerStat();
		obsidian_set = new AetherAchievement("advancement.aether_legacy.obsidian_set", "obsidian_set", 16, 3, ItemsAether.amplified_obsidian_helmet, amplifier_structure).registerStat();
		valkyrie_set = new AetherAchievement("advancement.aether_legacy.valkyrie_set", "valkyrie_set", 20, 5, ItemsAether.amplified_valkyrie_helmet, amplifier_structure).registerStat();
		neptune_set = new AetherAchievement("advancement.aether_legacy.neptune_set", "neptune_set", 16, 5, ItemsAether.amplified_neptune_helmet, amplifier_structure).registerStat();
		amplified_agility_boots = new AetherAchievement("advancement.aether_legacy.amplified_agility_boots", "amplified_agility_boots", 20, 7, ItemsAether.amplified_agility_boots, amplifier_structure).registerStat();
		amplified_sentry_boots = new AetherAchievement("advancement.aether_legacy.amplified_sentry_boots", "amplified_sentry_boots", 16, 7, ItemsAether.amplified_sentry_boots, amplifier_structure).registerStat();		
		true_thorns = new AetherAchievement("advancement.aether_legacy.true_thorns", "true_thorns", 20, 9, ItemsAether.amplified_elysian_helmet, amplifier_structure).registerStat();		
		divineral_set = new AetherAchievement("advancement.aether_legacy.divineral_set", "divineral_set", 16, 9, ItemsAether.divineral_helmet, amplifier_structure).registerStat();
		
		discharged = new AetherAchievement("advancement.aether_legacy.discharged", "discharged", -2, 9, ItemsAether.discharge_cape, defeat_osmium).registerStat();		
		
		iron_skin = new AetherAchievement("advancement.aether_legacy.iron_skin", "iron_skin", 3, 16, ItemsAether.life_shard, ancient_defeat_gold).registerStat();		
		powering_up = new AetherAchievement("advancement.aether_legacy.powering_up", "powering_up", 6, 15, ItemsAether.power_shard, defeat_palladium).registerStat();		
		next_level_enchantments = new AetherAchievement("advancement.aether_legacy.next_level_enchantments", "next_level_enchantments", 8, 13, new ItemStack(BlocksAether.divine_enchantment_table), defeat_palladium).registerStat();
		
		arkenium_fragment = new AetherAchievement("advancement.aether_legacy.arkenium_fragment", "arkenium_fragment", 8, -3, ItemsAether.arkenium_fragement, enchanter).registerStat();
		arkenium_tools = new AetherAchievement("advancement.aether_legacy.arkenium_tools", "arkenium_tools", 6, -3, ItemsAether.arkenium_pickaxe, arkenium_fragment).registerStat();
		arkenium_helmet = new AetherAchievement("advancement.aether_legacy.arkenium_helmet", "arkenium_helmet", 10, -3, ItemsAether.arkenium_helmet, arkenium_fragment).registerStat();
		
		freezer = new AetherAchievement("advancement.aether_legacy.freezer", "freezer", 4, -1, BlocksAether.freezer, holystone_tools).registerStat();
		continuum_orb = new AetherAchievement("advancement.aether_legacy.continuum_orb", "continuum_orb", 4, -3, ItemsAether.continuum_orb, freezer).registerStat();
		
		ACpage = new AchievementPage("Aether Legacy Departure", enter_aether, getting_wood_again, skyroot_crafting,
		skyroot_tools, holystone_tools, zanite_gemstone, arkenium_fragment, arkenium_tools, arkenium_helmet,
		continuum_gemstone, continuum_tools, grav_tools, aether_enchantment_table, artifact, divineral, ancient_enchanter,
		enchanter, freezer, continuum_orb, defeat_bronze, defeat_silver, defeat_gold, defeat_cobalt, defeat_osmium, ancient_defeat_bronze,
		divine_defeat_bronze, mythic_defeat_bronze, ancient_defeat_silver, divine_defeat_silver, ancient_defeat_gold, divine_defeat_gold,
		electrified, confractus_staff, sun_altar, agility_boots, incubator, aether_sword, kill_thunderlo, kill_zephyroo, fruit_stew, loreception,
		flying_aerwhale, flying_pig, zarnillys_scales, auralite, aether_hunter, blue_cloud, golden_cloud, kill_elysian_guardian, kill_aercenturion,
		shrine_mender, aether_florist, arctic_island, golden_island, divine_island, getting_scaled, getting_tipped, divineral_block, amplifier,
		amplifier_structure, divineral_set, phoenix_set, obsidian_set, valkyrie_set, neptune_set, amplified_agility_boots, amplified_sentry_boots,
		bringing_out_colors, discharged, true_thorns, defeat_palladium, powering_up, iron_skin, strengthened_levitation, godly_reach, builders_beware,
		notch_restored, realm_conquer, lucky, newnew_ore, charging_the_gemstone, even_shinier, balanced_flight, not_balanced_flight, next_level_enchantments);

		AchievementPage.registerAchievementPage(ACpage);
		FMLCommonHandler.instance().bus().register((Object)new PickUpAchievement());
	}

}