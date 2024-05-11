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

	public static Achievement enter_aether, getting_wood_again, void_wood, wisproot_wood; 
	public static Achievement defeat_bronze, defeat_cobalt, defeat_silver, defeat_gold, defeat_osmium;
	public static Achievement ancient_defeat_bronze, ancient_defeat_silver, ancient_defeat_gold; 
	public static Achievement divine_defeat_bronze, divine_defeat_silver, divine_defeat_gold;
	public static Achievement mythic_defeat_bronze;
	public static Achievement freezer, continuum_orb, electrified;
	public static Achievement enchanter, divineral, arkenium_helmet;
	public static Achievement confractus_staff, auralite, zarnillys_scales, divineral_block;
	public static Achievement incubator, amplifier, amplifier_structure, ancient_enchanter, sun_altar, aether_enchantment_table;
	public static Achievement skyroot_tools, holystone_tools, zanite_tools, arkenium_tools, continuum_tools, grav_tools, artifact;
	public static Achievement blue_cloud, golden_cloud, flying_pig, flying_aerwhale, loreception, fruit_stew;
	public static Achievement divineral_set, phoenix_set, obsidian_set, valkyrie_set, neptune_set, agility_boots, amplified_agility_boots, amplified_sentry_boots; 

	public static AchievementPage ACpage;

	public static void initialization() {
		enter_aether = new AetherAchievement("advancement.aether_legacy.enter_aether", "enter_aether", 0, 1, Blocks.glowstone, (Achievement) null).registerStat();
		getting_wood_again = new AetherAchievement("advancement.aether_legacy.getting_wood_again", "getting_wood_again", 0, -5, BlocksAether.skyroot_log, enter_aether).registerStat();
	
		defeat_bronze = new AetherAchievement("advancement.aether_legacy.bronze_dungeon", "bronze_dungeon", -2, 5, new ItemStack(ItemsAether.dungeon_key, 1, 0), enter_aether).registerStat();
		defeat_cobalt = new AetherAchievement("advancement.aether_legacy.defeat_cobalt", "defeat_cobalt", -1, 6, new ItemStack(ItemsAether.dungeon_key, 1, 10), enter_aether).registerStat();
		defeat_silver = new AetherAchievement("advancement.aether_legacy.silver_dungeon", "silver_dungeon", 1, 6, new ItemStack(ItemsAether.dungeon_key, 1, 1), enter_aether).registerStat();
		defeat_gold = new AetherAchievement("advancement.aether_legacy.gold_dungeon", "gold_dungeon", 2, 5, new ItemStack(ItemsAether.dungeon_key, 1, 2), enter_aether).registerStat();
		defeat_osmium = new AetherAchievement("advancement.aether_legacy.defeat_osmium", "defeat_osmium", -2, 3, new ItemStack(ItemsAether.dungeon_key, 1, 11), enter_aether).registerStat();
		
		electrified = new AetherAchievement("advancement.aether_legacy.electrified", "electrified", -4, 3, new ItemStack(ItemsAether.lightning_knife), defeat_osmium).registerStat();
		
		confractus_staff = new AetherAchievement("advancement.aether_legacy.confractus_staff", "confractus_staff", -1, 8, new ItemStack(ItemsAether.confractus_staff), defeat_cobalt).registerStat();
		
		sun_altar = new AetherAchievement("advancement.aether_legacy.sun_altar", "sun_altar", 2, 7, new ItemStack(BlocksAether.sun_altar), defeat_gold).registerStat();
		
		ancient_defeat_bronze = new AetherAchievement("advancement.aether_legacy.ancient_defeat_bronze", "ancient_defeat_bronze", -4, 5, new ItemStack(ItemsAether.dungeon_key, 1, 3), defeat_bronze).registerStat();
		ancient_defeat_silver = new AetherAchievement("advancement.aether_legacy.ancient_defeat_silver", "ancient_defeat_silver", 1, 8, new ItemStack(ItemsAether.dungeon_key, 1, 5), defeat_silver).registerStat();
		ancient_defeat_gold = new AetherAchievement("advancement.aether_legacy.ancient_defeat_gold", "ancient_defeat_gold", 4, 5, new ItemStack(ItemsAether.dungeon_key, 1, 7), defeat_gold).registerStat();
		
		divine_defeat_bronze = new AetherAchievement("advancement.aether_legacy.divine_defeat_bronze", "divine_defeat_bronze", -6, 5, new ItemStack(ItemsAether.dungeon_key, 1, 4), ancient_defeat_bronze).registerStat();
		divine_defeat_silver = new AetherAchievement("advancement.aether_legacy.divine_defeat_silver", "divine_defeat_silver", 1, 10, new ItemStack(ItemsAether.dungeon_key, 1, 6), ancient_defeat_silver).registerStat();
		divine_defeat_gold = new AetherAchievement("advancement.aether_legacy.divine_defeat_gold", "divine_defeat_gold", 6, 5, new ItemStack(ItemsAether.dungeon_key, 1, 8), ancient_defeat_gold).registerStat();
		
		agility_boots = new AetherAchievement("advancement.aether_legacy.agility_boots", "agility_boots", 6, 7, new ItemStack(ItemsAether.agility_boots), divine_defeat_gold).registerStat();
		
		enchanter = new AetherAchievement("advancement.aether_legacy.enchanter", "enchanter", 6, -1, BlocksAether.enchanter, enter_aether).registerStat();
		ancient_enchanter = new AetherAchievement("advancement.aether_legacy.ancient_enchanter", "ancient_enchanter", 8, -1, BlocksAether.ancient_enchanter, enchanter).registerStat();
		fruit_stew = new AetherAchievement("advancement.aether_legacy.fruit_stew", "fruit_stew", 6, 1, ItemsAether.enchanted_fruit_stew, enchanter).registerStat();
		
		divineral = new AetherAchievement("advancement.aether_legacy.divineral", "divineral", 6, -3, ItemsAether.divineral_ingot, enchanter).registerStat();
		divineral_block = new AetherAchievement("advancement.aether_legacy.divineral_block", "divineral_block", 6, -5, BlocksAether.divineral_block, divineral).registerStat();
		amplifier = new AetherAchievement("advancement.aether_legacy.amplifier", "amplifier", 8, -3, BlocksAether.amplifier, divineral).registerStat();
		amplifier_structure = new AetherAchievement("advancement.aether_legacy.amplifier_structure", "amplifier_structure", 10, -3, BlocksAether.golden_oak_bookshelf, amplifier).registerStat();
		divineral_set = new AetherAchievement("advancement.aether_legacy.divineral_set", "divineral_set", 10, 6, ItemsAether.divineral_helmet, amplifier_structure).registerStat();
		phoenix_set = new AetherAchievement("advancement.aether_legacy.phoenix_set", "phoenix_set", 12, 5, ItemsAether.amplified_phoenix_helmet, amplifier_structure).registerStat();
		obsidian_set = new AetherAchievement("advancement.aether_legacy.obsidian_set", "obsidian_set", 8, 5, ItemsAether.amplified_obsidian_helmet, amplifier_structure).registerStat();
		valkyrie_set = new AetherAchievement("advancement.aether_legacy.valkyrie_set", "valkyrie_set", 8, 3, ItemsAether.amplified_valkyrie_helmet, amplifier_structure).registerStat();
		neptune_set = new AetherAchievement("advancement.aether_legacy.neptune_set", "neptune_set", 12, 3, ItemsAether.amplified_neptune_helmet, amplifier_structure).registerStat();
		amplified_agility_boots = new AetherAchievement("advancement.aether_legacy.amplified_agility_boots", "amplified_agility_boots", 12, 1, ItemsAether.amplified_agility_boots, amplifier_structure).registerStat();
		amplified_sentry_boots = new AetherAchievement("advancement.aether_legacy.amplified_sentry_boots", "amplified_sentry_boots", 8, 1, ItemsAether.amplified_sentry_boots, amplifier_structure).registerStat();

		
		flying_pig = new AetherAchievement("advancement.aether_legacy.mount_phyg", "mount_phyg", -2, -1, Items.saddle, enter_aether).registerStat();		
		flying_aerwhale = new AetherAchievement("advancement.aether_legacy.flying_aerwhale", "flying_aerwhale", -2, 1, ItemsAether.zephyroo_saddle, flying_pig).registerStat();		
		incubator = new AetherAchievement("advancement.aether_legacy.incubator", "incubator", -4, -1, BlocksAether.incubator, flying_pig).registerStat();
		
		blue_cloud = new AetherAchievement("advancement.aether_legacy.blue_aercloud", "blue_aercloud", -2, -3, new ItemStack(BlocksAether.aercloud, 1, 1), enter_aether).registerStat();
		golden_cloud = new AetherAchievement("advancement.aether_legacy.golden_aercloud", "golden_aercloud", -4, -3, new ItemStack(BlocksAether.aercloud, 1, 2), blue_cloud).registerStat();

		zarnillys_scales = new AetherAchievement("advancement.aether_legacy.zarnillys_scales", "zarnillys_scales", 2, -3, new ItemStack(ItemsAether.zarnillys_scales), enter_aether).registerStat();
		
		skyroot_tools = new AetherAchievement("advancement.aether_legacy.skyroot_tools", "skyroot_tools", -2, -5, ItemsAether.skyroot_pickaxe, getting_wood_again).registerStat();
		holystone_tools = new AetherAchievement("advancement.aether_legacy.holystone_tools", "holystone_tools", -4, -5, ItemsAether.holystone_pickaxe, skyroot_tools).registerStat();
		zanite_tools = new AetherAchievement("advancement.aether_legacy.zanite_tools", "zanite_tools", -6, -5, ItemsAether.zanite_pickaxe, holystone_tools).registerStat();		
		
		auralite = new AetherAchievement("advancement.aether_legacy.auralite", "auralite", -4, -7, ItemsAether.auralite_crystal, zanite_tools).registerStat();		
		arkenium_tools = new AetherAchievement("advancement.aether_legacy.arkenium_tools", "arkenium_tools", -8, -7, ItemsAether.arkenium_pickaxe, zanite_tools).registerStat();
		arkenium_helmet = new AetherAchievement("advancement.aether_legacy.arkenium_helmet", "arkenium_helmet", -8, -9, ItemsAether.arkenium_helmet, zanite_tools).registerStat();
		
		continuum_tools = new AetherAchievement("advancement.aether_legacy.continuum_tools", "continuum_tools", -8, -3, ItemsAether.continuum_pickaxe, zanite_tools).registerStat();			
				
		grav_tools = new AetherAchievement("advancement.aether_legacy.gravitite_tools", "gravitite_tools", -10, -5, ItemsAether.gravitite_pickaxe, zanite_tools).registerStat();
		aether_enchantment_table = new AetherAchievement("advancement.aether_legacy.aether_enchantment_table", "aether_enchantment_table", -10, -7, BlocksAether.aether_enchantment_table, grav_tools).registerStat();
		artifact = new AetherAchievement("advancement.aether_legacy.artifact", "artifact", -12, -5, BlocksAether.primeval_artifact, grav_tools).registerStat();
		
		
		freezer = new AetherAchievement("advancement.aether_legacy.freezer", "freezer", 2, 1, BlocksAether.freezer, enter_aether).registerStat();
		continuum_orb = new AetherAchievement("advancement.aether_legacy.continuum_orb", "continuum_orb", 4, 1, ItemsAether.continuum_orb, freezer).registerStat();
		
		wisproot_wood = new AetherAchievement("advancement.aether_legacy.wisproot_wood", "wisproot_wood", 0, -7, new ItemStack(BlocksAether.wisproot_log), getting_wood_again).registerStat();
		void_wood = new AetherAchievement("advancement.aether_legacy.void_wood", "void_wood", 2, -5, new ItemStack(BlocksAether.void_sapling), getting_wood_again).registerStat();
		loreception = new AetherAchievement("advancement.aether_legacy.loreception", "loreception", 2, 3, ItemsAether.lore_book, enter_aether).registerStat();

		ACpage = new AchievementPage("Aether I", enter_aether, getting_wood_again, wisproot_wood, void_wood, divineral_block,
		defeat_bronze, defeat_silver, defeat_gold, defeat_cobalt,  defeat_osmium, fruit_stew, flying_aerwhale, arkenium_helmet,
		ancient_defeat_bronze, ancient_defeat_silver, ancient_defeat_gold, confractus_staff, agility_boots, zarnillys_scales, 
		divine_defeat_bronze, divine_defeat_silver, divine_defeat_gold, phoenix_set, neptune_set, valkyrie_set, auralite,
		freezer, continuum_orb, divineral, aether_enchantment_table, divineral_set, obsidian_set, amplified_agility_boots,
		enchanter, ancient_enchanter, incubator, amplifier, amplifier_structure, sun_altar, amplified_sentry_boots, electrified,
		golden_cloud, blue_cloud, flying_pig, skyroot_tools, holystone_tools, zanite_tools, arkenium_tools, continuum_tools,
		grav_tools, artifact, loreception);

		AchievementPage.registerAchievementPage(ACpage);
		FMLCommonHandler.instance().bus().register((Object)new PickUpAchievement());
	}

}