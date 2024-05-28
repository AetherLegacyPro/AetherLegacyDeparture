package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.items.accessories.ItemAccessoryDyed;
import com.gildedgames.the_aether.items.armor.ItemAetherArmor;
import com.gildedgames.the_aether.items.armor.ItemAetherDungeonArmor;
import com.gildedgames.the_aether.items.armor.ItemAmplifiedArkeniumArmor;
import com.gildedgames.the_aether.items.armor.ItemAmplifiedContinuumArmor;
import com.gildedgames.the_aether.items.armor.ItemAmplifiedElysianArmor;
import com.gildedgames.the_aether.items.armor.ItemAmplifiedPhoenixArmor;
import com.gildedgames.the_aether.items.armor.ItemAmplifiedZaniteArmor;
import com.gildedgames.the_aether.items.armor.ItemZaniteArmor;
import com.gildedgames.the_aether.items.armor.scaled.ItemScaledArkeniumArmor;
import com.gildedgames.the_aether.items.armor.scaled.ItemScaledElysianArmor;
import com.gildedgames.the_aether.items.armor.scaled.ItemScaledPhoenixArmor;
import com.gildedgames.the_aether.items.crops.ItemBlackberrySeeds;
import com.gildedgames.the_aether.items.crops.ItemBlueberrySeeds;
import com.gildedgames.the_aether.items.crops.ItemGrapeSeeds;
import com.gildedgames.the_aether.items.crops.ItemOrangeSeeds;
import com.gildedgames.the_aether.items.crops.ItemRaspberrySeeds;
import com.gildedgames.the_aether.items.crops.ItemStrawberrySeeds;
import com.gildedgames.the_aether.items.armor.ItemArkeniumArmor;
import com.gildedgames.the_aether.items.armor.ItemContinuumArmor;
import com.gildedgames.the_aether.items.armor.ItemDivineralArmor;
import com.gildedgames.the_aether.items.armor.ItemElysianArmor;
import com.gildedgames.the_aether.items.armor.ItemPhoenixArmor;
import com.gildedgames.the_aether.items.armor.ItemScaledContinuumArmor;
import com.gildedgames.the_aether.items.armor.ItemScaledDungeonArmor;
import com.gildedgames.the_aether.items.armor.ItemScaledGravititeArmor;
import com.gildedgames.the_aether.items.armor.ItemScaledZaniteArmor;
import com.gildedgames.the_aether.items.dungeon.ItemDungeonKey;
import com.gildedgames.the_aether.items.dungeon.ItemElysianCore;
import com.gildedgames.the_aether.items.dungeon.ItemElysianOffering;
import com.gildedgames.the_aether.items.dungeon.ItemVictoryMedal;
import com.gildedgames.the_aether.items.food.ItemAetherFood;
import com.gildedgames.the_aether.items.food.ItemAgilityStone;
import com.gildedgames.the_aether.items.food.ItemAmbrosiumShard;
import com.gildedgames.the_aether.items.food.ItemArkeniumApple;
import com.gildedgames.the_aether.items.food.ItemDexterityShard;
import com.gildedgames.the_aether.items.food.ItemDexterityStone;
import com.gildedgames.the_aether.items.food.ItemElysianApple;
import com.gildedgames.the_aether.items.food.ItemEnchantedFruitStew;
import com.gildedgames.the_aether.items.food.ItemFlameStone;
import com.gildedgames.the_aether.items.food.ItemGummySwet;
import com.gildedgames.the_aether.items.food.ItemHealingStone;
import com.gildedgames.the_aether.items.food.ItemLifeShard;
import com.gildedgames.the_aether.items.food.ItemPoisonGummySwet;
import com.gildedgames.the_aether.items.food.ItemPowerShard;
import com.gildedgames.the_aether.items.food.ItemRainbowStrawberry;
import com.gildedgames.the_aether.items.food.ItemResistanceStone;
import com.gildedgames.the_aether.items.food.ItemStrengthStone;
import com.gildedgames.the_aether.items.food.ItemUncookedFruitStewBowl;
import com.gildedgames.the_aether.items.food.ItemWhiteApple;
import com.gildedgames.the_aether.items.food.ItemWynberry;
import com.gildedgames.the_aether.items.staffs.ItemAgnesShears;
import com.gildedgames.the_aether.items.staffs.ItemCloudStaff;
import com.gildedgames.the_aether.items.staffs.ItemConfractusStaff;
import com.gildedgames.the_aether.items.staffs.ItemNatureStaff;
import com.gildedgames.the_aether.items.staffs.ItemZaniteAndCinerarium;
import com.gildedgames.the_aether.items.staffs.ItemZaniteAndCyro;
import com.gildedgames.the_aether.items.staffs.ItemZaniteShears;
import com.gildedgames.the_aether.items.tools.ItemAetherBlueParachute;
import com.gildedgames.the_aether.items.tools.ItemAetherParachute;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedContinuumTool;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedHolystoneTool;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedNotchedPickaxe;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedSkyrootTool;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedValkyrieTool;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedZaniteTool;
import com.gildedgames.the_aether.items.tools.ItemArkeniumHoe;
import com.gildedgames.the_aether.items.tools.ItemArkeniumTool;
import com.gildedgames.the_aether.items.tools.ItemAscensiteTool;
import com.gildedgames.the_aether.items.tools.ItemContinuumHoe;
import com.gildedgames.the_aether.items.tools.ItemContinuumTool;
import com.gildedgames.the_aether.items.tools.ItemDivineralHoe;
import com.gildedgames.the_aether.items.tools.ItemDivineralTool;
import com.gildedgames.the_aether.items.tools.ItemGravititeHoe;
import com.gildedgames.the_aether.items.tools.ItemGravititeTool;
import com.gildedgames.the_aether.items.tools.ItemHolystoneHoe;
import com.gildedgames.the_aether.items.tools.ItemHolystoneTool;
import com.gildedgames.the_aether.items.tools.ItemNotchedPickaxe;
import com.gildedgames.the_aether.items.tools.ItemSkyrootBucket;
import com.gildedgames.the_aether.items.tools.ItemSkyrootHoe;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;
import com.gildedgames.the_aether.items.tools.ItemValkyrieHoe;
import com.gildedgames.the_aether.items.tools.ItemValkyrieTool;
import com.gildedgames.the_aether.items.tools.ItemZaniteHoe;
import com.gildedgames.the_aether.items.tools.ItemZaniteTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedArkeniumTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedBattleSentryHammer;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedContinuumTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedGravititeTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedHolystoneTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedNotchedPickaxe;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedSkyrootTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedValkyrieTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedZaniteTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import com.gildedgames.the_aether.items.util.EnumAetherMultiToolType;

import com.gildedgames.the_aether.items.util.ItemAether;
import com.gildedgames.the_aether.items.util.ItemDeveloperStick;
import com.gildedgames.the_aether.items.util.ItemSwettyBall;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedBattleSentryHammer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedContinuumSword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedDragonSlayer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedEnderSlayer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedFlamingSword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedHolySword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedHolystoneSword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedJebHammer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedLightningSword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedNetherSlayer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedNotchHammer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedOverworldSlayer;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedSkyrootSword;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedValkyrieLance;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedVampireBlade;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedZaniteSword;
import com.gildedgames.the_aether.items.weapons.ItemArkeniumSword;
import com.gildedgames.the_aether.items.weapons.ItemAscensiteSword;
import com.gildedgames.the_aether.items.weapons.ItemBattleSentryHammer;
import com.gildedgames.the_aether.items.weapons.ItemBuilderSlayer;
import com.gildedgames.the_aether.items.weapons.ItemCandyCaneSword;
import com.gildedgames.the_aether.items.weapons.ItemContinuumSword;
import com.gildedgames.the_aether.items.weapons.ItemDivineralSword;
import com.gildedgames.the_aether.items.weapons.ItemDragonSlayer;
import com.gildedgames.the_aether.items.weapons.ItemElementalSword;
import com.gildedgames.the_aether.items.weapons.ItemEnderSlayer;
import com.gildedgames.the_aether.items.weapons.ItemGravititeSword;
import com.gildedgames.the_aether.items.weapons.ItemHolystoneSword;
import com.gildedgames.the_aether.items.weapons.ItemJebHammer;
import com.gildedgames.the_aether.items.weapons.ItemLightningKnife;
import com.gildedgames.the_aether.items.weapons.ItemNetherSlayer;
import com.gildedgames.the_aether.items.weapons.ItemNotchHammer;
import com.gildedgames.the_aether.items.weapons.ItemOverworldSlayer;
import com.gildedgames.the_aether.items.weapons.ItemPigSlayer;
import com.gildedgames.the_aether.items.weapons.ItemSkyrootSword;
import com.gildedgames.the_aether.items.weapons.ItemTippedDivineralSword;
import com.gildedgames.the_aether.items.weapons.ItemTippedGravititeSword;
import com.gildedgames.the_aether.items.weapons.ItemValkyrieLance;
import com.gildedgames.the_aether.items.weapons.ItemVampireBlade;
import com.gildedgames.the_aether.items.weapons.ItemZaniteSword;
import com.gildedgames.the_aether.items.weapons.projectile.ItemAmplifiedCyroBow;
import com.gildedgames.the_aether.items.weapons.projectile.ItemAmplifiedPhoenixBow;
import com.gildedgames.the_aether.items.weapons.projectile.ItemCyroBow;
import com.gildedgames.the_aether.items.weapons.projectile.ItemDart;
import com.gildedgames.the_aether.items.weapons.projectile.ItemDartShooter;
import com.gildedgames.the_aether.items.weapons.projectile.ItemPhoenixBow;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedArkeniumSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedContinuumSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedCyroBow;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedDragonSlayer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedEnderSlayer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedFlamingSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedHolySword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedHolystoneSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedJebHammer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedLightningSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedNetherSlayer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedNotchHammer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedOverworldSlayer;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedPhoenixBow;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedSkyrootSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedValkyrieLance;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedVampireBlade;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedZaniteSword;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsAether {

	public static EnumRarity aether_loot = EnumHelper.addRarity("aether_legacy_loot", EnumChatFormatting.GREEN, "Aether Loot");
	public static EnumRarity scaled_aether_loot = EnumHelper.addRarity("aether_legacy_scaled_loot", EnumChatFormatting.AQUA, "Scaled Aether Loot");
	public static EnumRarity divine_aether_loot = EnumHelper.addRarity("aether_legacy_divine_loot", EnumChatFormatting.LIGHT_PURPLE, "Divine Aether Loot");

	public static EnumRarity powered = EnumHelper.addRarity("aether_legacy_powered", EnumChatFormatting.GOLD, "Powered Weapon");
	
	//Misc Items
	public static Item skyroot_stick, zanite_gemstone, ambrosium_shard, mimicry_ambrosium_shard, arkenium_fragement, arkenium_chunk, arkenium_ingot, continuum_orb, continuum_gemstone;
	public static Item raw_gravitite, empyrean_gemstone, cracked_empyrean_gemstone, charged_empyrean_gemstone;
	public static Item valkyrie_ingot, valkyrie_nugget, enchanted_divineral, divineral_ingot, golden_amber, icestone_crystal, auralite_crystal;	
	public static Item zanite_nugget, arkenium_nugget, continuum_nugget, gravitite_nugget, divineral_nugget;
	public static Item swet_ball, golden_swet_ball, purple_swet_ball, uligo_swet_ball;
	public static Item aercloud_globule, blue_aercloud_globule, golden_aercloud_globule, purple_aercloud_globule, green_aercloud_globule;
	public static Item dungeon_key, skyroot_bucket, cloud_parachute, blue_parachute, golden_parachute;
	public static Item lore_book, skyroot_bed_item, aether_portal_frame, developer_stick;
	public static Item aether_tune, ascending_dawn, welcoming_skies, legacy;
	public static Item moa_egg, aether_spawn_egg;
	
	public static Item black_dye, white_dye, blue_dye;
	
	//Mob Drops
	public static Item aechor_petal, blue_aechor_petal, golden_aechor_petal, cockatrice_feather, tempest_core, zarnillys_scales, thunderlo_horn, divine_essence;
	public static Item cyro_rod, cinerarium_rod, zephyroo_leather, zephyroo_saddle, thunderlo_leather;
	public static Item aerca_tooth, aerca_powder, charged_tempest_core;
	public static Item victory_medal, osmium_insignia;
	public static Item aceninum_shard, elysian_core, crystal_dragon_scales, notched_core, elysian_offering;
	public static Item elysian_dragon_scales;
	
	//Aether Tools
	public static Item skyroot_pickaxe, skyroot_axe, skyroot_shovel, skyroot_sword, skyroot_hoe;
	public static Item tipped_skyroot_sword, tipped_skyroot_pickaxe, tipped_skyroot_axe, tipped_skyroot_shovel;
	public static Item amplified_skyroot_pickaxe, amplified_skyroot_axe, amplified_skyroot_shovel, amplified_skyroot_sword;

	public static Item holystone_pickaxe, holystone_axe, holystone_shovel, holystone_sword, holystone_hoe;
	public static Item tipped_holystone_sword, tipped_holystone_pickaxe, tipped_holystone_axe, tipped_holystone_shovel;
	public static Item amplified_holystone_pickaxe, amplified_holystone_axe, amplified_holystone_shovel, amplified_holystone_sword;

	public static Item zanite_pickaxe, zanite_axe, zanite_shovel, zanite_sword, zanite_hoe;
	public static Item tipped_zanite_sword, tipped_zanite_pickaxe, tipped_zanite_axe, tipped_zanite_shovel;
	public static Item amplified_zanite_pickaxe, amplified_zanite_axe, amplified_zanite_shovel, amplified_zanite_sword;

	public static Item gravitite_pickaxe, gravitite_axe, gravitite_shovel, gravitite_sword, gravitite_hoe;
	public static Item tipped_gravitite_sword, tipped_gravitite_pickaxe, tipped_gravitite_axe, tipped_gravitite_shovel;
	public static Item divineral_pickaxe, divineral_axe, divineral_shovel, divineral_sword, divineral_hoe;
	public static Item tipped_divineral_sword;
	
	public static Item continuum_pickaxe, continuum_axe, continuum_shovel, continuum_sword, continuum_hoe;
	public static Item tipped_continuum_sword, tipped_continuum_pickaxe, tipped_continuum_axe, tipped_continuum_shovel;
	public static Item amplified_continuum_pickaxe, amplified_continuum_axe, amplified_continuum_shovel, amplified_continuum_sword;
	
	public static Item arkenium_pickaxe, arkenium_axe, arkenium_shovel, arkenium_sword, arkenium_hoe;
	public static Item tipped_arkenium_sword, tipped_arkenium_pickaxe, tipped_arkenium_axe, tipped_arkenium_shovel;
	
	public static Item valkyrie_pickaxe, valkyrie_axe, valkyrie_shovel, valkyrie_hoe;
	public static Item tipped_valkyrie_pickaxe, tipped_valkyrie_axe, tipped_valkyrie_shovel;
	public static Item amplified_valkyrie_pickaxe, amplified_valkyrie_axe, amplified_valkyrie_shovel;
	
	public static Item notched_pickaxe, tipped_notched_pickaxe, amplified_notched_pickaxe;
	
	public static Item ascensite_sword, ascensite_pickaxe, ascensite_axe, ascensite_shovel;
	
	public static Item nature_staff, cloud_staff, confractus_staff, zanite_shears, zanite_and_cinerarium, zanite_and_cyro, shears_of_agnes;

	//Aether Armor
	public static Item zanite_helmet, zanite_chestplate, zanite_leggings, zanite_boots, zanite_gloves;	
	public static Item scaled_zanite_helmet, scaled_zanite_chestplate, scaled_zanite_leggings, scaled_zanite_boots;	
	public static Item amplified_zanite_helmet, amplified_zanite_chestplate, amplified_zanite_leggings, amplified_zanite_boots, amplified_zanite_gloves;

	public static Item gravitite_helmet, gravitite_chestplate, gravitite_leggings, gravitite_boots, gravitite_gloves;
	public static Item scaled_gravitite_helmet, scaled_gravitite_chestplate, scaled_gravitite_leggings, scaled_gravitite_boots;	
	public static Item divineral_helmet, divineral_chestplate, divineral_leggings, divineral_boots, divineral_gloves; 
	
	public static Item arkenium_helmet, arkenium_chestplate, arkenium_leggings, arkenium_boots, arkenium_gloves;
	public static Item scaled_arkenium_helmet, scaled_arkenium_chestplate, scaled_arkenium_leggings, scaled_arkenium_boots;	
	public static Item amplified_arkenium_helmet, amplified_arkenium_chestplate, amplified_arkenium_leggings, amplified_arkenium_boots, amplified_arkenium_gloves;
	
	public static Item continuum_helmet, continuum_chestplate, continuum_leggings, continuum_boots, continuum_gloves;	
	public static Item scaled_continuum_helmet, scaled_continuum_chestplate, scaled_continuum_leggings, scaled_continuum_boots;	
	public static Item amplified_continuum_helmet, amplified_continuum_chestplate, amplified_continuum_leggings, amplified_continuum_boots, amplified_continuum_gloves;

	public static Item neptune_helmet, neptune_chestplate, neptune_leggings, neptune_boots, neptune_gloves;
	public static Item scaled_neptune_helmet, scaled_neptune_chestplate, scaled_neptune_leggings, scaled_neptune_boots;	
	public static Item amplified_neptune_helmet, amplified_neptune_chestplate, amplified_neptune_leggings, amplified_neptune_boots, amplified_neptune_gloves;

	public static Item phoenix_helmet, phoenix_chestplate, phoenix_leggings, phoenix_boots, phoenix_gloves;
	public static Item scaled_phoenix_helmet, scaled_phoenix_chestplate, scaled_phoenix_leggings, scaled_phoenix_boots;
	public static Item amplified_phoenix_helmet, amplified_phoenix_chestplate, amplified_phoenix_leggings, amplified_phoenix_boots, amplified_phoenix_gloves;

	public static Item obsidian_helmet, obsidian_chestplate, obsidian_leggings, obsidian_boots, obsidian_gloves;	
	public static Item scaled_obsidian_helmet, scaled_obsidian_chestplate, scaled_obsidian_leggings, scaled_obsidian_boots;	
	public static Item amplified_obsidian_helmet, amplified_obsidian_chestplate, amplified_obsidian_leggings, amplified_obsidian_boots, amplified_obsidian_gloves;

	public static Item valkyrie_helmet, valkyrie_chestplate, valkyrie_leggings, valkyrie_boots, valkyrie_gloves;
	public static Item scaled_valkyrie_helmet, scaled_valkyrie_chestplate, scaled_valkyrie_leggings, scaled_valkyrie_boots;
	public static Item amplified_valkyrie_helmet, amplified_valkyrie_chestplate, amplified_valkyrie_leggings, amplified_valkyrie_boots, amplified_valkyrie_gloves;
	
	public static Item elysian_helmet, elysian_chestplate, elysian_leggings, elysian_boots, elysian_gloves;
	public static Item scaled_elysian_helmet, scaled_elysian_chestplate, scaled_elysian_leggings, scaled_elysian_boots;
	public static Item amplified_elysian_helmet, amplified_elysian_chestplate, amplified_elysian_leggings, amplified_elysian_boots, amplified_elysian_gloves;

	public static Item ascensite_helmet, ascensite_chestplate, ascensite_leggings, ascensite_boots, ascensite_gloves;
	
	public static Item agility_boots, scaled_agility_boots, amplified_agility_boots;
	public static Item sentry_boots, scaled_sentry_boots, amplified_sentry_boots;
	
	public static Item leather_gloves, iron_gloves, golden_gloves, chain_gloves, diamond_gloves;
	public static Item netherite_gloves, copper_gloves, effrine_gloves, heavy_blaze_gloves;
	public static Item vanite_gloves, pherithium_gloves, klangite_gloves;
	
	//Aether Food
	public static Item blueberry, gummy_swet, poison_gummy_swet, white_apple, arkenium_apple, elysian_apple;
	public static Item enchanted_blueberry, enchanted_grapes, enchanted_orange, enchanted_raspberry, enchanted_strawberry;
	public static Item gingerbread_man, candy_cane, orange, raspberry, strawberry, wynberry, rainbow_strawberry, enchanted_blackberry;
	public static Item void_tomato, raw_aerwhale, enchanted_aerwhale, raw_thunderlo, enchanted_thunderlo, grapes, blackberry;
	public static Item blueberry_seeds, raspberry_seeds, strawberry_seeds, grape_seeds, orange_seeds, blackberry_seeds;
	public static Item healing_stone, resistance_stone, strength_stone, dexterity_stone, agility_stone, flame_stone;
	public static Item holystone_bowl, uncooked_fruit_stew_bowl, enchanted_fruit_stew;

	//Aether Weapons
	public static Item phoenix_bow, tipped_phoenix_bow, amplified_phoenix_bow, dart_shooter, dart;
	public static Item cyro_bow, tipped_cyro_bow, amplified_cyro_bow;
	public static Item flaming_sword, tipped_flaming_sword, amplified_flaming_sword; 
	public static Item lightning_sword, tipped_lightning_sword, amplified_lightning_sword;
	public static Item holy_sword, tipped_holy_sword, amplified_holy_sword;
	public static Item vampire_blade, tipped_vampire_blade, amplified_vampire_blade;
	public static Item pig_slayer, candy_cane_sword, lightning_knife, builder_slayer;
	public static Item notch_hammer, tipped_notch_hammer, amplified_notch_hammer; 
	public static Item valkyrie_lance, tipped_valkyrie_lance, amplified_valkyrie_lance;
	public static Item jeb_hammer, tipped_jeb_hammer, amplified_jeb_hammer;
	public static Item battle_sentry_hammer, tipped_battle_sentry_hammer, amplified_battle_sentry_hammer;
	public static Item overworld_slayer, tipped_overworld_slayer, amplified_overworld_slayer;
	public static Item nether_slayer, tipped_nether_slayer, amplified_nether_slayer; 
	public static Item ender_slayer, tipped_ender_slayer, amplified_ender_slayer;
	public static Item dragon_bane, tipped_dragon_bane, amplified_dragon_bane;

	//Accessories
	public static Item iron_ring, golden_ring, zanite_ring, ice_ring;
	public static Item valkyrie_ring, reinforced_valkyrie_ring, amplified_valkyrie_ring;
	public static Item bone_ring, reinforced_bone_ring, amplified_bone_ring;
	public static Item haste_ring, reinforced_haste_ring, amplified_haste_ring; 
	public static Item copper_ring, netherite_ring, diamond_ring, continuum_ring, arkenium_ring, auralite_ring, gravitite_ring, divineral_ring;
	public static Item iron_pendant, golden_pendant, zanite_pendant, ice_pendant, false_wings;
	public static Item copper_pendant, netherite_pendant, diamond_pendant, continuum_pendant, arkenium_pendant;
	public static Item auralite_pendant, reinforced_auralite_pendant, amplified_auralite_pendant;
	public static Item gravitite_pendant, divineral_pendant;
	public static Item elysian_ring, reinforced_elysian_ring, amplified_elysian_ring;

	public static Item white_cape, red_cape, blue_cape, yellow_cape, black_cape, green_cape, purple_cape, orange_cape, lime_cape; 
	public static Item pink_cape, gray_cape, cyan_cape, light_gray_cape, light_blue_cape, magenta_cape, brown_cape;	
	public static Item swet_cape, invisibility_cape, agility_cape, valkyrie_cape, phoenix_cape, aer_cape, discharge_cape;

	public static Item golden_feather, reinforced_golden_feather, amplified_golden_feather;
	public static Item regeneration_stone, reinforced_regeneration_stone, healing_matrix;
	public static Item iron_bubble, reinforced_iron_bubble, amplified_iron_bubble;
	public static Item life_shard, dexterity_shard, power_shard, flaming_stone;
	public static Item repulsion_shield, zanite_shield, gravitite_shield, jeb_shield, sentry_shield;

	public static void initialization() {
		skyroot_stick = register("skyroot_stick", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("skyroot_stick")));
		zanite_gemstone = register("zanite_gemstone", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/zanite_gemstone")));
		zanite_nugget = register("zanite_nugget", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/zanite_nugget")));
		ambrosium_shard = register("ambrosium_shard", new ItemAmbrosiumShard().setTextureName(Aether.find("misc/ambrosium_shard")));
		mimicry_ambrosium_shard = register("mimicry_ambrosium_shard", new ItemAmbrosiumShard().setTextureName(Aether.find("misc/mimicry_ambrosium_shard")));
		golden_amber = register("golden_amber", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/golden_amber")));
		aechor_petal = register("aechor_petal", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/aechor_petal")));		
		blue_aechor_petal = register("blue_aechor_petal", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/blue_aechor_petal")));
		golden_aechor_petal = register("golden_aechor_petal", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/golden_aechor_petal")));		
		swet_ball = register("swet_ball", new ItemSwettyBall(AetherCreativeTabs.material).setTextureName(Aether.find("misc/blue_swet")));
		golden_swet_ball = register("golden_swet_ball", new ItemSwettyBall(AetherCreativeTabs.material).setTextureName(Aether.find("misc/golden_swet")));
		purple_swet_ball = register("purple_swet_ball", new ItemSwettyBall(AetherCreativeTabs.material).setTextureName(Aether.find("misc/purple_swet")));
		uligo_swet_ball = register("uligo_swet_ball", new ItemSwettyBall(AetherCreativeTabs.material).setTextureName(Aether.find("misc/uligo_swet")));
		icestone_crystal = register("icestone_crystal", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/icestone_crystal")));
		arkenium_fragement = register("arkenium_fragement", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/arkenium_fragment")));
		arkenium_chunk = register("arkenium_chunk", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/arkenium_chunk")));
		arkenium_ingot = register("arkenium_ingot", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/arkenium_ingot")));
		arkenium_nugget = register("arkenium_nugget", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/arkenium_nugget")));
		continuum_orb = register("continuum_orb", new ItemContinuumOrb().setTextureName(Aether.find("misc/continuum_orb")));
		continuum_gemstone = register("continuum_gemstone", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/continuum_gemstone")));
		continuum_nugget = register("continuum_nugget", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/continuum_nugget")));
		valkyrie_ingot = register("valkyrie_ingot", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/valkyrie_ingot")));
		valkyrie_nugget = register("valkyrie_nugget", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/valkyrie_nugget")));
		raw_gravitite = register("raw_gravitite", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/raw_gravitite")));
		gravitite_nugget = register("gravitite_nugget", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/gravitite_nugget")));
		divineral_nugget = register("divineral_nugget", new ItemDivineItem().setTextureName(Aether.find("misc/divineral_nugget")));
		divineral_ingot = register("divineral_ingot", new ItemDivineItem().setTextureName(Aether.find("misc/divineral_ingot")));
		enchanted_divineral = register("enchanted_divineral", new ItemDivineItem().setTextureName(Aether.find("misc/enchanted_divineral")));
		cyro_rod = register("cyro_rod", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/cyro_rod")));
		cinerarium_rod = register("cinerarium_rod", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/cinerarium_rod")));	
		zephyroo_leather = register("zephyroo_leather", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/zephyroo_leather")));
		zephyroo_saddle = register("zephyroo_saddle", new ItemAetherSaddle().setTextureName(Aether.find("misc/zephyroo_saddle")));
		thunderlo_leather = register("thunderlo_leather", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/thunderlo_leather")));
		thunderlo_horn = register("thunderlo_horn", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/thunderlo_horn")));		
		cockatrice_feather = register("cockatrice_feather", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/cockatrice_feather")));
		tempest_core = register("tempest_core", new ItemTempestCore().setTextureName(Aether.find("misc/tempest_core")));
		zarnillys_scales = register("zarnillys_scales", new ItemZarnillysScales().setTextureName(Aether.find("misc/zarnillys_scales")));
		auralite_crystal = register("auralite_crystal", new ItemZarnillysScales().setTextureName(Aether.find("misc/auralite_crystal")));	
		empyrean_gemstone = register("empyrean_gemstone", new ItemZarnillysScales().setTextureName(Aether.find("misc/empyrean_gemstone")));
		cracked_empyrean_gemstone = register("cracked_empyrean_gemstone", new ItemZarnillysScales().setTextureName(Aether.find("misc/cracked_empyrean_gemstone")));
		charged_empyrean_gemstone = register("charged_empyrean_gemstone", new ItemZarnillysScales().setTextureName(Aether.find("misc/charged_empyrean_gemstone")));
		divine_essence = register("divine_essence", new ItemDivineEssence().setTextureName(Aether.find("misc/aetherium_essence")));
		aerca_tooth = register("aerca_tooth", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/aerca_tooth")));
		aerca_powder = register("aerca_powder", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/aerca_powder")));
		charged_tempest_core = register("charged_tempest_core", new ItemChargedTempestCore().setTextureName(Aether.find("misc/charged_tempest_core")));
		aceninum_shard = register("aceninum_shard", new ItemAceninumShard().setTextureName(Aether.find("misc/aceninum_shard")));
		crystal_dragon_scales = register("crystal_dragon_scales", new ItemAether(AetherCreativeTabs.material).setTextureName(Aether.find("misc/crystal_dragon_scales")));
		elysian_dragon_scales = register("elysian_dragon_scales", new ItemAceninumShard().setTextureName(Aether.find("misc/elysian_dragon_scales")));
		
		aercloud_globule = register("aercloud_globule", new ItemAercloudGlobule().setTextureName(Aether.find("misc/cold_aercloud_item")));
		blue_aercloud_globule = register("blue_aercloud_globule", new ItemBlueAercloudGlobule().setTextureName(Aether.find("misc/blue_aercloud_item")));
		golden_aercloud_globule = register("golden_aercloud_globule", new ItemBlueAercloudGlobule().setTextureName(Aether.find("misc/golden_aercloud_item")));
		purple_aercloud_globule = register("purple_aercloud_globule", new ItemBlueAercloudGlobule().setTextureName(Aether.find("misc/purple_aercloud_item")));
		green_aercloud_globule = register("green_aercloud_globule", new ItemBlueAercloudGlobule().setTextureName(Aether.find("misc/green_aercloud_item")));
		
		black_dye = register("black_dye", new ItemAether(AetherCreativeTabs.material).setCreativeTab(null).setTextureName(Aether.find("misc/dyes/black_dye")));
		white_dye = register("white_dye", new ItemAether(AetherCreativeTabs.material).setCreativeTab(null).setTextureName(Aether.find("misc/dyes/white_dye")));
		blue_dye = register("blue_dye", new ItemAether(AetherCreativeTabs.material).setCreativeTab(null).setTextureName(Aether.find("misc/dyes/blue_dye")));
		
		skyroot_pickaxe = register("skyroot_pickaxe", new ItemSkyrootTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/skyroot_pickaxe")));
		skyroot_axe = register("skyroot_axe", new ItemSkyrootTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/skyroot_axe")));
		skyroot_shovel = register("skyroot_shovel", new ItemSkyrootTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/skyroot_shovel")));
		skyroot_hoe = register("skyroot_hoe", new ItemSkyrootHoe().setTextureName(Aether.find("tools/skyroot_hoe")));
		
		tipped_skyroot_pickaxe = register("tipped_skyroot_pickaxe", new ItemTippedSkyrootTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_skyroot_pickaxe")));
		tipped_skyroot_axe = register("tipped_skyroot_axe", new ItemTippedSkyrootTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_skyroot_axe")));
		tipped_skyroot_shovel = register("tipped_skyroot_shovel", new ItemTippedSkyrootTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_skyroot_shovel")));
		
		amplified_skyroot_pickaxe = register("amplified_skyroot_pickaxe", new ItemAmplifiedSkyrootTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_skyroot_pickaxe")));
		amplified_skyroot_axe = register("amplified_skyroot_axe", new ItemAmplifiedSkyrootTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/amplified_skyroot_axe")));
		amplified_skyroot_shovel = register("amplified_skyroot_shovel", new ItemAmplifiedSkyrootTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/amplified_skyroot_shovel")));
		
		holystone_pickaxe = register("holystone_pickaxe", new ItemHolystoneTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/holystone_pickaxe")));
		holystone_axe = register("holystone_axe", new ItemHolystoneTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/holystone_axe")));
		holystone_shovel = register("holystone_shovel", new ItemHolystoneTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/holystone_shovel")));
		holystone_hoe = register("holystone_hoe", new ItemHolystoneHoe().setTextureName(Aether.find("tools/holystone_hoe")));
		
		tipped_holystone_pickaxe = register("tipped_holystone_pickaxe", new ItemTippedHolystoneTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_holystone_pickaxe")));
		tipped_holystone_axe = register("tipped_holystone_axe", new ItemTippedHolystoneTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_holystone_axe")));
		tipped_holystone_shovel = register("tipped_holystone_shovel", new ItemTippedHolystoneTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_holystone_shovel")));
		
		amplified_holystone_pickaxe = register("amplified_holystone_pickaxe", new ItemAmplifiedHolystoneTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_holystone_pickaxe")));
		amplified_holystone_axe = register("amplified_holystone_axe", new ItemAmplifiedHolystoneTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/amplified_holystone_axe")));
		amplified_holystone_shovel = register("amplified_holystone_shovel", new ItemAmplifiedHolystoneTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/amplified_holystone_shovel")));

		zanite_pickaxe = register("zanite_pickaxe", new ItemZaniteTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/zanite_pickaxe")));
		zanite_axe = register("zanite_axe", new ItemZaniteTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/zanite_axe")));
		zanite_shovel = register("zanite_shovel", new ItemZaniteTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/zanite_shovel")));
		zanite_hoe = register("zanite_hoe", new ItemZaniteHoe().setTextureName(Aether.find("tools/zanite_hoe")));
		
		tipped_zanite_pickaxe = register("tipped_zanite_pickaxe", new ItemTippedZaniteTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_zanite_pickaxe")));
		tipped_zanite_axe = register("tipped_zanite_axe", new ItemTippedZaniteTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_zanite_axe")));
		tipped_zanite_shovel = register("tipped_zanite_shovel", new ItemTippedZaniteTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_zanite_shovel")));
		
		amplified_zanite_pickaxe = register("amplified_zanite_pickaxe", new ItemAmplifiedZaniteTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_zanite_pickaxe")));
		amplified_zanite_axe = register("amplified_zanite_axe", new ItemAmplifiedZaniteTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/amplified_zanite_axe")));
		amplified_zanite_shovel = register("amplified_zanite_shovel", new ItemAmplifiedZaniteTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/amplified_zanite_shovel")));
		
		arkenium_pickaxe = register("arkenium_pickaxe", new ItemArkeniumTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/arkenium_pickaxe")));
		arkenium_axe = register("arkenium_axe", new ItemArkeniumTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/arkenium_axe")));
		arkenium_shovel = register("arkenium_shovel", new ItemArkeniumTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/arkenium_shovel")));
		arkenium_hoe = register("arkenium_hoe", new ItemArkeniumHoe().setTextureName(Aether.find("tools/arkenium_hoe")));
		
		tipped_arkenium_pickaxe = register("tipped_arkenium_pickaxe", new ItemTippedArkeniumTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_arkenium_pickaxe")));
		tipped_arkenium_axe = register("tipped_arkenium_axe", new ItemTippedArkeniumTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_arkenium_axe")));
		tipped_arkenium_shovel = register("tipped_arkenium_shovel", new ItemTippedArkeniumTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_arkenium_shovel")));
		
		continuum_pickaxe = register("continuum_pickaxe", new ItemContinuumTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/continuum_pickaxe")));
		continuum_axe = register("continuum_axe", new ItemContinuumTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/continuum_axe")));
		continuum_shovel = register("continuum_shovel", new ItemContinuumTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/continuum_shovel")));
		continuum_hoe = register("continuum_hoe", new ItemContinuumHoe().setTextureName(Aether.find("tools/continuum_hoe")));
		
		tipped_continuum_pickaxe = register("tipped_continuum_pickaxe", new ItemTippedContinuumTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_continuum_pickaxe")));
		tipped_continuum_axe = register("tipped_continuum_axe", new ItemTippedContinuumTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_continuum_axe")));
		tipped_continuum_shovel = register("tipped_continuum_shovel", new ItemTippedContinuumTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_continuum_shovel")));
		
		amplified_continuum_pickaxe = register("amplified_continuum_pickaxe", new ItemAmplifiedContinuumTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_continuum_pickaxe")));
		amplified_continuum_axe = register("amplified_continuum_axe", new ItemAmplifiedContinuumTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/amplified_continuum_axe")));
		amplified_continuum_shovel = register("amplified_continuum_shovel", new ItemAmplifiedContinuumTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/amplified_continuum_shovel")));
		
		gravitite_pickaxe = register("gravitite_pickaxe", new ItemGravititeTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/gravitite_pickaxe")));
		gravitite_axe = register("gravitite_axe", new ItemGravititeTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/gravitite_axe")));
		gravitite_shovel = register("gravitite_shovel", new ItemGravititeTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/gravitite_shovel")));
		gravitite_hoe = register("gravitite_hoe", new ItemGravititeHoe().setTextureName(Aether.find("tools/gravitite_hoe")));
		
		tipped_gravitite_pickaxe = register("tipped_gravitite_pickaxe", new ItemTippedGravititeTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_gravitite_pickaxe")));
		tipped_gravitite_axe = register("tipped_gravitite_axe", new ItemTippedGravititeTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_gravitite_axe")));
		tipped_gravitite_shovel = register("tipped_gravitite_shovel", new ItemTippedGravititeTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_gravitite_shovel")));
		
		divineral_pickaxe = register("divineral_pickaxe", new ItemDivineralTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/divineral_pickaxe")));
		divineral_axe = register("divineral_axe", new ItemDivineralTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/divineral_axe")));
		divineral_shovel = register("divineral_shovel", new ItemDivineralTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/divineral_shovel")));
		divineral_hoe = register("divineral_hoe", new ItemDivineralHoe().setTextureName(Aether.find("tools/divineral_hoe")));
		
		valkyrie_pickaxe = register("valkyrie_pickaxe", new ItemValkyrieTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/valkyrie_pickaxe")));
		valkyrie_axe = register("valkyrie_axe", new ItemValkyrieTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/valkyrie_axe")));
		valkyrie_shovel = register("valkyrie_shovel", new ItemValkyrieTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/valkyrie_shovel")));
		valkyrie_hoe = register("valkyrie_hoe", new ItemValkyrieHoe().setTextureName(Aether.find("tools/valkyrie_hoe")));
		
		tipped_valkyrie_pickaxe = register("tipped_valkyrie_pickaxe", new ItemTippedValkyrieTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_valkyrie_pickaxe")));
		tipped_valkyrie_axe = register("tipped_valkyrie_axe", new ItemTippedValkyrieTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/tipped_valkyrie_axe")));
		tipped_valkyrie_shovel = register("tipped_valkyrie_shovel", new ItemTippedValkyrieTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/tipped_valkyrie_shovel")));
		
		amplified_valkyrie_pickaxe = register("amplified_valkyrie_pickaxe", new ItemAmplifiedValkyrieTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_valkyrie_pickaxe")));
		amplified_valkyrie_axe = register("amplified_valkyrie_axe", new ItemAmplifiedValkyrieTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/amplified_valkyrie_axe")));
		amplified_valkyrie_shovel = register("amplified_valkyrie_shovel", new ItemAmplifiedValkyrieTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/amplified_valkyrie_shovel")));

		notched_pickaxe = register("notched_pickaxe", new ItemNotchedPickaxe(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/notched_pickaxe")));
		tipped_notched_pickaxe = register("tipped_notched_pickaxe", new ItemTippedNotchedPickaxe(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/tipped_notched_pickaxe")));
		amplified_notched_pickaxe = register("amplified_notched_pickaxe", new ItemAmplifiedNotchedPickaxe(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/amplified_notched_pickaxe")));
		
		if (Loader.isModLoaded("nova_craft") && AetherConfig.enable_ascensite) {
		ascensite_pickaxe = register("ascensite_pickaxe", new ItemAscensiteTool(2.0F, EnumAetherToolType.PICKAXE).setTextureName(Aether.find("tools/ascensite_pickaxe")));
		ascensite_axe = register("ascensite_axe", new ItemAscensiteTool(3.0F, EnumAetherToolType.AXE).setTextureName(Aether.find("tools/ascensite_axe")));
		ascensite_shovel = register("ascensite_shovel", new ItemAscensiteTool(1.0F, EnumAetherToolType.SHOVEL).setTextureName(Aether.find("tools/ascensite_shovel")));
		}
		
		zanite_helmet = register("zanite_helmet", new ItemZaniteArmor(0, ArmorMaterial.IRON, "zanite", zanite_gemstone).setTextureName(Aether.findII("armor/zanite_helmet")));
		zanite_chestplate = register("zanite_chestplate", new ItemZaniteArmor(1, ArmorMaterial.IRON, "zanite", zanite_gemstone).setTextureName(Aether.findII("armor/zanite_chestplate")));
		zanite_leggings = register("zanite_leggings", new ItemZaniteArmor(2, ArmorMaterial.IRON, "zanite", zanite_gemstone).setTextureName(Aether.findII("armor/zanite_leggings")));
		zanite_boots = register("zanite_boots", new ItemZaniteArmor(3, ArmorMaterial.IRON, "zanite", zanite_gemstone).setTextureName(Aether.findII("armor/zanite_boots")));
		
		scaled_zanite_helmet = register("scaled_zanite_helmet", new ItemScaledZaniteArmor(0, ArmorMaterial.IRON, "scaled_zanite", zanite_gemstone).setMaxDamage(155).setTextureName(Aether.find("armor/scaled_zanite_helmet")));
		scaled_zanite_chestplate = register("scaled_zanite_chestplate", new ItemScaledZaniteArmor(1, ArmorMaterial.IRON, "scaled_zanite", zanite_gemstone).setMaxDamage(220).setTextureName(Aether.find("armor/scaled_zanite_chestplate")));
		scaled_zanite_leggings = register("scaled_zanite_leggings", new ItemScaledZaniteArmor(2, ArmorMaterial.IRON, "scaled_zanite", zanite_gemstone).setMaxDamage(205).setTextureName(Aether.find("armor/scaled_zanite_leggings")));
		scaled_zanite_boots = register("scaled_zanite_boots", new ItemScaledZaniteArmor(3, ArmorMaterial.IRON, "scaled_zanite", zanite_gemstone).setMaxDamage(175).setTextureName(Aether.find("armor/scaled_zanite_boots")));
		
		amplified_zanite_helmet = register("amplified_zanite_helmet", new ItemAmplifiedZaniteArmor(0, ArmorMaterial.DIAMOND, "amplified_zanite", divineral_ingot).setMaxDamage(1245).setTextureName(Aether.find("armor/amplified_zanite_helmet")));
		amplified_zanite_chestplate = register("amplified_zanite_chestplate", new ItemAmplifiedZaniteArmor(1, ArmorMaterial.DIAMOND, "amplified_zanite", divineral_ingot).setMaxDamage(1265).setTextureName(Aether.find("armor/amplified_zanite_chestplate")));
		amplified_zanite_leggings = register("amplified_zanite_leggings", new ItemAmplifiedZaniteArmor(2, ArmorMaterial.DIAMOND, "amplified_zanite", divineral_ingot).setMaxDamage(1245).setTextureName(Aether.find("armor/amplified_zanite_leggings")));
		amplified_zanite_boots = register("amplified_zanite_boots", new ItemAmplifiedZaniteArmor(3, ArmorMaterial.DIAMOND, "amplified_zanite", divineral_ingot).setMaxDamage(1275).setTextureName(Aether.find("armor/amplified_zanite_boots")));
		
		arkenium_helmet = register("arkenium_helmet", new ItemArkeniumArmor(0, ArmorMaterial.IRON, "arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(363).setTextureName(Aether.find("armor/arkenium_helmet")));
		arkenium_chestplate = register("arkenium_chestplate", new ItemArkeniumArmor(1, ArmorMaterial.IRON, "arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(528).setTextureName(Aether.find("armor/arkenium_chestplate")));
		arkenium_leggings = register("arkenium_leggings", new ItemArkeniumArmor(2, ArmorMaterial.IRON, "arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(495).setTextureName(Aether.find("armor/arkenium_leggings")));
		arkenium_boots = register("arkenium_boots", new ItemArkeniumArmor(3, ArmorMaterial.IRON, "arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(429).setTextureName(Aether.find("armor/arkenium_boots")));
		
		scaled_arkenium_helmet = register("scaled_arkenium_helmet", new ItemScaledArkeniumArmor(0, ArmorMaterial.IRON, "scaled_arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(323).setTextureName(Aether.find("armor/scaled_arkenium_helmet")));
		scaled_arkenium_chestplate = register("scaled_arkenium_chestplate", new ItemScaledArkeniumArmor(1, ArmorMaterial.IRON, "scaled_arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(488).setTextureName(Aether.find("armor/scaled_arkenium_chestplate")));
		scaled_arkenium_leggings = register("scaled_arkenium_leggings", new ItemScaledArkeniumArmor(2, ArmorMaterial.IRON, "scaled_arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(455).setTextureName(Aether.find("armor/scaled_arkenium_leggings")));
		scaled_arkenium_boots = register("scaled_arkenium_boots", new ItemScaledArkeniumArmor(3, ArmorMaterial.IRON, "scaled_arkenium", arkenium_ingot, 0xcdc8d7).setMaxDamage(389).setTextureName(Aether.find("armor/scaled_arkenium_boots")));
		
		amplified_arkenium_helmet = register("amplified_arkenium_helmet", new ItemAmplifiedArkeniumArmor(0, ArmorMaterial.DIAMOND, "amplified_arkenium", divineral_ingot, 0xa6cac3).setMaxDamage(963).setTextureName(Aether.find("armor/amplified_arkenium_helmet")));
		amplified_arkenium_chestplate = register("amplified_arkenium_chestplate", new ItemAmplifiedArkeniumArmor(1, ArmorMaterial.DIAMOND, "amplified_arkenium", divineral_ingot, 0xa6cac3).setMaxDamage(1128).setTextureName(Aether.find("armor/amplified_arkenium_chestplate")));
		amplified_arkenium_leggings = register("amplified_arkenium_leggings", new ItemAmplifiedArkeniumArmor(2, ArmorMaterial.DIAMOND, "amplified_arkenium", divineral_ingot, 0xa6cac3).setMaxDamage(1095).setTextureName(Aether.find("armor/amplified_arkenium_leggings")));
		amplified_arkenium_boots = register("amplified_arkenium_boots", new ItemAmplifiedArkeniumArmor(3, ArmorMaterial.DIAMOND, "amplified_arkenium", divineral_ingot, 0xa6cac3).setMaxDamage(1029).setTextureName(Aether.find("armor/amplified_arkenium_boots")));

		continuum_helmet = register("continuum_helmet", new ItemContinuumArmor(0, ArmorMaterial.DIAMOND, "continuum", continuum_gemstone).setTextureName(Aether.find("armor/continuum_helmet")));
		continuum_chestplate = register("continuum_chestplate", new ItemContinuumArmor(1, ArmorMaterial.DIAMOND, "continuum", continuum_gemstone).setTextureName(Aether.find("armor/continuum_chestplate")));
		continuum_leggings = register("continuum_leggings", new ItemContinuumArmor(2, ArmorMaterial.DIAMOND, "continuum", continuum_gemstone).setTextureName(Aether.find("armor/continuum_leggings")));
		continuum_boots = register("continuum_boots", new ItemContinuumArmor(3, ArmorMaterial.DIAMOND, "continuum", continuum_gemstone).setTextureName(Aether.find("armor/continuum_boots")));
		
		scaled_continuum_helmet = register("scaled_continuum_helmet", new ItemScaledContinuumArmor(0, ArmorMaterial.DIAMOND, "scaled_continuum", continuum_gemstone).setTextureName(Aether.find("armor/scaled_continuum_helmet")));
		scaled_continuum_chestplate = register("scaled_continuum_chestplate", new ItemScaledContinuumArmor(1, ArmorMaterial.DIAMOND, "scaled_continuum", continuum_gemstone).setTextureName(Aether.find("armor/scaled_continuum_chestplate")));
		scaled_continuum_leggings = register("scaled_continuum_leggings", new ItemScaledContinuumArmor(2, ArmorMaterial.DIAMOND, "scaled_continuum", continuum_gemstone).setTextureName(Aether.find("armor/scaled_continuum_leggings")));
		scaled_continuum_boots = register("scaled_continuum_boots", new ItemScaledContinuumArmor(3, ArmorMaterial.DIAMOND, "scaled_continuum", continuum_gemstone).setTextureName(Aether.find("armor/scaled_continuum_boots")));
		
		amplified_continuum_helmet = register("amplified_continuum_helmet", new ItemAmplifiedContinuumArmor(0, ArmorMaterial.DIAMOND, "amplified_continuum", divineral_ingot).setMaxDamage(1845).setTextureName(Aether.find("armor/amplified_continuum_helmet")));
		amplified_continuum_chestplate = register("amplified_continuum_chestplate", new ItemAmplifiedContinuumArmor(1, ArmorMaterial.DIAMOND, "amplified_continuum", divineral_ingot).setMaxDamage(2128).setTextureName(Aether.find("armor/amplified_continuum_chestplate")));
		amplified_continuum_leggings = register("amplified_continuum_leggings", new ItemAmplifiedContinuumArmor(2, ArmorMaterial.DIAMOND, "amplified_continuum", divineral_ingot).setMaxDamage(1945).setTextureName(Aether.find("armor/amplified_continuum_leggings")));
		amplified_continuum_boots = register("amplified_continuum_boots", new ItemAmplifiedContinuumArmor(3, ArmorMaterial.DIAMOND, "amplified_continuum", divineral_ingot).setMaxDamage(1875).setTextureName(Aether.find("armor/amplified_continuum_boots")));
		
		gravitite_helmet = register("gravitite_helmet", new ItemAetherArmor(0, ArmorMaterial.DIAMOND, "gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(545).setTextureName(Aether.find("armor/new_gravitite_helmet")));
		gravitite_chestplate = register("gravitite_chestplate", new ItemAetherArmor(1, ArmorMaterial.DIAMOND, "gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(792).setTextureName(Aether.find("armor/new_gravitite_chestplate")));
		gravitite_leggings = register("gravitite_leggings", new ItemAetherArmor(2, ArmorMaterial.DIAMOND, "gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(743).setTextureName(Aether.find("armor/new_gravitite_leggings")));
		gravitite_boots = register("gravitite_boots", new ItemAetherArmor(3, ArmorMaterial.DIAMOND, "gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(644).setTextureName(Aether.find("armor/new_gravitite_boots")));
		
		scaled_gravitite_helmet = register("scaled_gravitite_helmet", new ItemScaledGravititeArmor(0, ArmorMaterial.DIAMOND, "scaled_gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(545).setTextureName(Aether.find("armor/scaled_gravitite_helmet")));
		scaled_gravitite_chestplate = register("scaled_gravitite_chestplate", new ItemScaledGravititeArmor(1, ArmorMaterial.DIAMOND, "scaled_gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(792).setTextureName(Aether.find("armor/scaled_gravitite_chestplate")));
		scaled_gravitite_leggings = register("scaled_gravitite_leggings", new ItemScaledGravititeArmor(2, ArmorMaterial.DIAMOND, "scaled_gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(743).setTextureName(Aether.find("armor/scaled_gravitite_leggings")));
		scaled_gravitite_boots = register("scaled_gravitite_boots", new ItemScaledGravititeArmor(3, ArmorMaterial.DIAMOND, "scaled_gravitite", Item.getItemFromBlock(BlocksAether.enchanted_gravitite)).setMaxDamage(644).setTextureName(Aether.find("armor/scaled_gravitite_boots")));
		
		divineral_helmet = register("divineral_helmet", new ItemDivineralArmor(0, ArmorMaterial.DIAMOND, "divineral", null).setTextureName(Aether.find("armor/divineral_helmet")));
		divineral_chestplate = register("divineral_chestplate", new ItemDivineralArmor(1, ArmorMaterial.DIAMOND, "divineral", null).setTextureName(Aether.find("armor/divineral_chestplate")));
		divineral_leggings = register("divineral_leggings", new ItemDivineralArmor(2, ArmorMaterial.DIAMOND, "divineral", null).setTextureName(Aether.find("armor/divineral_leggings")));
		divineral_boots = register("divineral_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "divineral", null).setTextureName(Aether.find("armor/divineral_boots")));

		neptune_helmet = register("neptune_helmet", new ItemAetherDungeonArmor(0, ArmorMaterial.DIAMOND, "neptune", null).setTextureName(Aether.findII("armor/neptune_helmet")));
		neptune_chestplate = register("neptune_chestplate", new ItemAetherDungeonArmor(1, ArmorMaterial.DIAMOND, "neptune", null).setTextureName(Aether.findII("armor/neptune_chestplate")));
		neptune_leggings = register("neptune_leggings", new ItemAetherDungeonArmor(2, ArmorMaterial.DIAMOND, "neptune", null).setTextureName(Aether.findII("armor/neptune_leggings")));
		neptune_boots = register("neptune_boots", new ItemAetherDungeonArmor(3, ArmorMaterial.DIAMOND, "neptune", null).setTextureName(Aether.findII("armor/neptune_boots")));
		
		scaled_neptune_helmet = register("scaled_neptune_helmet", new ItemScaledDungeonArmor(0, ArmorMaterial.DIAMOND, "scaled_neptune", null).setTextureName(Aether.find("armor/scaled_neptune_helmet")));
		scaled_neptune_chestplate = register("scaled_neptune_chestplate", new ItemScaledDungeonArmor(1, ArmorMaterial.DIAMOND, "scaled_neptune", null).setTextureName(Aether.find("armor/scaled_neptune_chestplate")));
		scaled_neptune_leggings = register("scaled_neptune_leggings", new ItemScaledDungeonArmor(2, ArmorMaterial.DIAMOND, "scaled_neptune", null).setTextureName(Aether.find("armor/scaled_neptune_leggings")));
		scaled_neptune_boots = register("scaled_neptune_boots", new ItemScaledDungeonArmor(3, ArmorMaterial.DIAMOND, "scaled_neptune", null).setTextureName(Aether.find("armor/scaled_neptune_boots")));
		
		amplified_neptune_helmet = register("amplified_neptune_helmet", new ItemDivineralArmor(0, ArmorMaterial.DIAMOND, "amplified_neptune", null).setTextureName(Aether.find("armor/amplified_neptune_helmet")));
		amplified_neptune_chestplate = register("amplified_neptune_chestplate", new ItemDivineralArmor(1, ArmorMaterial.DIAMOND, "amplified_neptune", null).setTextureName(Aether.find("armor/amplified_neptune_chestplate")));
		amplified_neptune_leggings = register("amplified_neptune_leggings", new ItemDivineralArmor(2, ArmorMaterial.DIAMOND, "amplified_neptune", null).setTextureName(Aether.find("armor/amplified_neptune_leggings")));
		amplified_neptune_boots = register("amplified_neptune_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "amplified_neptune", null).setTextureName(Aether.find("armor/amplified_neptune_boots")));
		
		agility_boots = register("agility_boots", new ItemAetherDungeonArmor(3, ArmorMaterial.DIAMOND, "agility", agility_boots).setTextureName(Aether.find("armor/agility_boots")));
		scaled_agility_boots = register("scaled_agility_boots", new ItemScaledDungeonArmor(3, ArmorMaterial.DIAMOND, "scaled_agility", scaled_agility_boots).setTextureName(Aether.find("armor/scaled_agility_boots")));
		amplified_agility_boots = register("amplified_agility_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "amplified_agility", amplified_agility_boots).setTextureName(Aether.find("armor/amplified_agility_boots")));
		
		phoenix_helmet = register("phoenix_helmet", new ItemPhoenixArmor(0, ArmorMaterial.DIAMOND, "phoenix", null).setTextureName(Aether.find("armor/phoenix_helmet")));
		phoenix_chestplate = register("phoenix_chestplate", new ItemPhoenixArmor(1, ArmorMaterial.DIAMOND, "phoenix", null).setTextureName(Aether.find("armor/phoenix_chestplate")));
		phoenix_leggings = register("phoenix_leggings", new ItemPhoenixArmor(2, ArmorMaterial.DIAMOND, "phoenix", null).setTextureName(Aether.find("armor/phoenix_leggings")));
		phoenix_boots = register("phoenix_boots", new ItemPhoenixArmor(3, ArmorMaterial.DIAMOND, "phoenix", null).setTextureName(Aether.find("armor/phoenix_boots")));
		
		scaled_phoenix_helmet = register("scaled_phoenix_helmet", new ItemScaledPhoenixArmor(0, ArmorMaterial.DIAMOND, "scaled_phoenix", null).setTextureName(Aether.find("armor/scaled_phoenix_helmet")));
		scaled_phoenix_chestplate = register("scaled_phoenix_chestplate", new ItemScaledPhoenixArmor(1, ArmorMaterial.DIAMOND, "scaled_phoenix", null).setTextureName(Aether.find("armor/scaled_phoenix_chestplate")));
		scaled_phoenix_leggings = register("scaled_phoenix_leggings", new ItemScaledPhoenixArmor(2, ArmorMaterial.DIAMOND, "scaled_phoenix", null).setTextureName(Aether.find("armor/scaled_phoenix_leggings")));
		scaled_phoenix_boots = register("scaled_phoenix_boots", new ItemScaledPhoenixArmor(3, ArmorMaterial.DIAMOND, "scaled_phoenix", null).setTextureName(Aether.find("armor/scaled_phoenix_boots")));
		
		amplified_phoenix_helmet = register("amplified_phoenix_helmet", new ItemAmplifiedPhoenixArmor(0, ArmorMaterial.DIAMOND, "amplified_phoenix", null).setTextureName(Aether.find("armor/amplified_phoenix_helmet")));
		amplified_phoenix_chestplate = register("amplified_phoenix_chestplate", new ItemAmplifiedPhoenixArmor(1, ArmorMaterial.DIAMOND, "amplified_phoenix", null).setTextureName(Aether.find("armor/amplified_phoenix_chestplate")));
		amplified_phoenix_leggings = register("amplified_phoenix_leggings", new ItemAmplifiedPhoenixArmor(2, ArmorMaterial.DIAMOND, "amplified_phoenix", null).setTextureName(Aether.find("armor/amplified_phoenix_leggings")));
		amplified_phoenix_boots = register("amplified_phoenix_boots", new ItemAmplifiedPhoenixArmor(3, ArmorMaterial.DIAMOND, "amplified_phoenix", null).setTextureName(Aether.find("armor/amplified_phoenix_boots")));

		obsidian_helmet = register("obsidian_helmet", new ItemAetherDungeonArmor(0, ArmorMaterial.DIAMOND, "obsidian", null).setTextureName(Aether.findII("armor/obsidian_helmet")));
		obsidian_chestplate = register("obsidian_chestplate", new ItemAetherDungeonArmor(1, ArmorMaterial.DIAMOND, "obsidian", null).setTextureName(Aether.findII("armor/obsidian_chestplate")));
		obsidian_leggings = register("obsidian_leggings", new ItemAetherDungeonArmor(2, ArmorMaterial.DIAMOND, "obsidian", null).setTextureName(Aether.findII("armor/obsidian_leggings")));
		obsidian_boots = register("obsidian_boots", new ItemAetherDungeonArmor(3, ArmorMaterial.DIAMOND, "obsidian", null).setTextureName(Aether.findII("armor/obsidian_boots")));
		
		scaled_obsidian_helmet = register("scaled_obsidian_helmet", new ItemScaledDungeonArmor(0, ArmorMaterial.DIAMOND, "scaled_obsidian", null).setTextureName(Aether.find("armor/scaled_obsidian_helmet")));
		scaled_obsidian_chestplate = register("scaled_obsidian_chestplate", new ItemScaledDungeonArmor(1, ArmorMaterial.DIAMOND, "scaled_obsidian", null).setTextureName(Aether.find("armor/scaled_obsidian_chestplate")));
		scaled_obsidian_leggings = register("scaled_obsidian_leggings", new ItemScaledDungeonArmor(2, ArmorMaterial.DIAMOND, "scaled_obsidian", null).setTextureName(Aether.find("armor/scaled_obsidian_leggings")));
		scaled_obsidian_boots = register("scaled_obsidian_boots", new ItemScaledDungeonArmor(3, ArmorMaterial.DIAMOND, "scaled_obsidian", null).setTextureName(Aether.find("armor/scaled_obsidian_boots")));

		amplified_obsidian_helmet = register("amplified_obsidian_helmet", new ItemDivineralArmor(0, ArmorMaterial.DIAMOND, "amplified_obsidian", null).setTextureName(Aether.find("armor/amplified_obsidian_helmet")));
		amplified_obsidian_chestplate = register("amplified_obsidian_chestplate", new ItemDivineralArmor(1, ArmorMaterial.DIAMOND, "amplified_obsidian", null).setTextureName(Aether.find("armor/amplified_obsidian_chestplate")));
		amplified_obsidian_leggings = register("amplified_obsidian_leggings", new ItemDivineralArmor(2, ArmorMaterial.DIAMOND, "amplified_obsidian", null).setTextureName(Aether.find("armor/amplified_obsidian_leggings")));
		amplified_obsidian_boots = register("amplified_obsidian_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "amplified_obsidian", null).setTextureName(Aether.find("armor/amplified_obsidian_boots")));
		
		valkyrie_helmet = register("valkyrie_helmet", new ItemAetherDungeonArmor(0, ArmorMaterial.DIAMOND, "valkyrie", null).setTextureName(Aether.find("armor/valkyrie_helmet")));
		valkyrie_chestplate = register("valkyrie_chestplate", new ItemAetherDungeonArmor(1, ArmorMaterial.DIAMOND, "valkyrie", null).setTextureName(Aether.find("armor/valkyrie_chestplate")));
		valkyrie_leggings = register("valkyrie_leggings", new ItemAetherDungeonArmor(2, ArmorMaterial.DIAMOND, "valkyrie", null).setTextureName(Aether.find("armor/valkyrie_leggings")));
		valkyrie_boots = register("valkyrie_boots", new ItemAetherDungeonArmor(3, ArmorMaterial.DIAMOND, "valkyrie", null).setTextureName(Aether.find("armor/valkyrie_boots")));
		
		scaled_valkyrie_helmet = register("scaled_valkyrie_helmet", new ItemScaledDungeonArmor(0, ArmorMaterial.DIAMOND, "scaled_valkyrie", null).setTextureName(Aether.find("armor/scaled_valkyrie_helmet")));
		scaled_valkyrie_chestplate = register("scaled_valkyrie_chestplate", new ItemScaledDungeonArmor(1, ArmorMaterial.DIAMOND, "scaled_valkyrie", null).setTextureName(Aether.find("armor/scaled_valkyrie_chestplate")));
		scaled_valkyrie_leggings = register("scaled_valkyrie_leggings", new ItemScaledDungeonArmor(2, ArmorMaterial.DIAMOND, "scaled_valkyrie", null).setTextureName(Aether.find("armor/scaled_valkyrie_leggings")));
		scaled_valkyrie_boots = register("scaled_valkyrie_boots", new ItemScaledDungeonArmor(3, ArmorMaterial.DIAMOND, "scaled_valkyrie", null).setTextureName(Aether.find("armor/scaled_valkyrie_boots")));
		
		amplified_valkyrie_helmet = register("amplified_valkyrie_helmet", new ItemDivineralArmor(0, ArmorMaterial.DIAMOND, "amplified_valkyrie", null).setTextureName(Aether.find("armor/amplified_valkyrie_helmet")));
		amplified_valkyrie_chestplate = register("amplified_valkyrie_chestplate", new ItemDivineralArmor(1, ArmorMaterial.DIAMOND, "amplified_valkyrie", null).setTextureName(Aether.find("armor/amplified_valkyrie_chestplate")));
		amplified_valkyrie_leggings = register("amplified_valkyrie_leggings", new ItemDivineralArmor(2, ArmorMaterial.DIAMOND, "amplified_valkyrie", null).setTextureName(Aether.find("armor/amplified_valkyrie_leggings")));
		amplified_valkyrie_boots = register("amplified_valkyrie_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "amplified_valkyrie", null).setTextureName(Aether.find("armor/amplified_valkyrie_boots")));

		elysian_helmet = register("elysian_helmet", new ItemElysianArmor(0, ArmorMaterial.DIAMOND, "elysian", null).setMaxDamage(745).setTextureName(Aether.find("armor/elysian_helmet")));
		elysian_chestplate = register("elysian_chestplate", new ItemElysianArmor(1, ArmorMaterial.DIAMOND, "elysian", null).setMaxDamage(992).setTextureName(Aether.find("armor/elysian_chestplate")));
		elysian_leggings = register("elysian_leggings", new ItemElysianArmor(2, ArmorMaterial.DIAMOND, "elysian", null).setMaxDamage(943).setTextureName(Aether.find("armor/elysian_leggings")));
		elysian_boots = register("elysian_boots", new ItemElysianArmor(3, ArmorMaterial.DIAMOND, "elysian", null).setMaxDamage(844).setTextureName(Aether.find("armor/elysian_boots")));
		
		scaled_elysian_helmet = register("scaled_elysian_helmet", new ItemScaledElysianArmor(0, ArmorMaterial.DIAMOND, "scaled_elysian", null).setMaxDamage(945).setTextureName(Aether.find("armor/scaled_elysian_helmet")));
		scaled_elysian_chestplate = register("scaled_elysian_chestplate", new ItemScaledElysianArmor(1, ArmorMaterial.DIAMOND, "scaled_elysian", null).setMaxDamage(1192).setTextureName(Aether.find("armor/scaled_elysian_chestplate")));
		scaled_elysian_leggings = register("scaled_elysian_leggings", new ItemScaledElysianArmor(2, ArmorMaterial.DIAMOND, "scaled_elysian", null).setMaxDamage(1143).setTextureName(Aether.find("armor/scaled_elysian_leggings")));
		scaled_elysian_boots = register("scaled_elysian_boots", new ItemScaledElysianArmor(3, ArmorMaterial.DIAMOND, "scaled_elysian", null).setMaxDamage(1044).setTextureName(Aether.find("armor/scaled_elysian_boots")));
		
		amplified_elysian_helmet = register("amplified_elysian_helmet", new ItemAmplifiedElysianArmor(0, ArmorMaterial.DIAMOND, "amplified_elysian", null).setMaxDamage(1945).setTextureName(Aether.find("armor/amplified_elysian_helmet")));
		amplified_elysian_chestplate = register("amplified_elysian_chestplate", new ItemAmplifiedElysianArmor(1, ArmorMaterial.DIAMOND, "amplified_elysian", null).setMaxDamage(2292).setTextureName(Aether.find("armor/amplified_elysian_chestplate")));
		amplified_elysian_leggings = register("amplified_elysian_leggings", new ItemAmplifiedElysianArmor(2, ArmorMaterial.DIAMOND, "amplified_elysian", null).setMaxDamage(2143).setTextureName(Aether.find("armor/amplified_elysian_leggings")));
		amplified_elysian_boots = register("amplified_elysian_boots", new ItemAmplifiedElysianArmor(3, ArmorMaterial.DIAMOND, "amplified_elysian", null).setMaxDamage(2044).setTextureName(Aether.find("armor/amplified_elysian_boots")));
		
		if (Loader.isModLoaded("nova_craft") && AetherConfig.enable_ascensite) {
			ascensite_helmet = register("ascensite_helmet", new ItemAmplifiedElysianArmor(0, ArmorMaterial.DIAMOND, "ascensite", null).setMaxDamage(8745).setTextureName(Aether.find("armor/ascensite_helmet")));
			ascensite_chestplate = register("ascensite_chestplate", new ItemAmplifiedElysianArmor(1, ArmorMaterial.DIAMOND, "ascensite", null).setMaxDamage(8992).setTextureName(Aether.find("armor/ascensite_chestplate")));
			ascensite_leggings = register("ascensite_leggings", new ItemAmplifiedElysianArmor(2, ArmorMaterial.DIAMOND, "ascensite", null).setMaxDamage(8943).setTextureName(Aether.find("armor/ascensite_leggings")));
			ascensite_boots = register("ascensite_boots", new ItemAmplifiedElysianArmor(3, ArmorMaterial.DIAMOND, "ascensite", null).setMaxDamage(8844).setTextureName(Aether.find("armor/ascensite_boots")));	
		}
		
		blueberry = register("blueberry", new ItemAetherFood(1).setTextureName(Aether.find("food/blueberry")));
		enchanted_blueberry = register("enchanted_blueberry", new ItemAetherFood(6).setTextureName(Aether.find("food/enchanted_blueberry")));
		grapes = register("grapes", new ItemAetherFood(1).setTextureName(Aether.find("food/grapes")));
		enchanted_grapes = register("enchanted_grapes", new ItemAetherFood(6).setTextureName(Aether.find("food/enchanted_grapes")));
		raw_thunderlo = register("raw_thunderlo", new ItemAetherFood(2).setTextureName(Aether.find("food/raw_thunderlo")));
		enchanted_thunderlo = register("enchanted_thunderlo", new ItemAetherFood(6).setTextureName(Aether.find("food/enchanted_thunderlo")));
		raw_aerwhale = register("raw_aerwhale", new ItemAetherFood(3).setTextureName(Aether.find("food/raw_aerwhale")));
		enchanted_aerwhale = register("enchanted_aerwhale", new ItemAetherFood(9).setTextureName(Aether.find("food/cooked_aerwhale")));
		wynberry = register("wynberry", new ItemWynberry().setTextureName(Aether.find("food/wynberry")));
		rainbow_strawberry = register("rainbow_strawberry", new ItemRainbowStrawberry().setTextureName(Aether.find("food/rainbow_strawberry")));
		strawberry = register("strawberry", new ItemAetherFood(1).setTextureName(Aether.find("food/strawberry")));
		enchanted_strawberry = register("enchanted_strawberry", new ItemAetherFood(6).setTextureName(Aether.find("food/enchanted_strawberry")));
		orange = register("orange", new ItemAetherFood(2).setTextureName(Aether.find("food/orange")));
		enchanted_orange = register("enchanted_orange", new ItemAetherFood(7).setTextureName(Aether.find("food/enchanted_orange")));
		raspberry = register("raspberry", new ItemAetherFood(1).setTextureName(Aether.find("food/raspberry")));
		enchanted_raspberry = register("enchanted_raspberry", new ItemAetherFood(5).setTextureName(Aether.find("food/enchanted_raspberry")));
		blackberry = register("blackberry", new ItemAetherFood(1).setTextureName(Aether.find("food/blackberry")));
		enchanted_blackberry = register("enchanted_blackberry", new ItemAetherFood(5).setTextureName(Aether.find("food/enchanted_blackberry")));
		void_tomato = register("void_tomato", new ItemAetherFood(3).setTextureName(Aether.find("food/void_tomato")));
		white_apple = register("white_apple", new ItemWhiteApple().setTextureName(Aether.find("food/white_apple")));
		arkenium_apple = register("arkenium_apple", new ItemArkeniumApple().setTextureName(Aether.find("food/arkenium_apple")));
		elysian_apple = register("elysian_apple", new ItemElysianApple().setTextureName(Aether.find("food/elysian_apple")));		
		gummy_swet = register("gummy_swet", new ItemGummySwet());
		poison_gummy_swet = register("poison_gummy_swet", new ItemPoisonGummySwet());
		healing_stone = register("healing_stone", new ItemHealingStone().setTextureName(Aether.find("food/healing_stone")));
		resistance_stone = register("resistance_stone", new ItemResistanceStone().setTextureName(Aether.find("food/resistance_stone")));
		strength_stone = register("strength_stone", new ItemStrengthStone().setTextureName(Aether.find("food/strength_stone")));
		dexterity_stone = register("dexterity_stone", new ItemDexterityStone().setTextureName(Aether.find("food/dexterity_stone")));
		agility_stone = register("agility_stone", new ItemAgilityStone().setTextureName(Aether.find("food/agility_stone")));
		flame_stone = register("flame_stone", new ItemFlameStone().setTextureName(Aether.find("food/flame_stone")));
		candy_cane = register("candy_cane", new ItemAetherFood(1).setTextureName(Aether.find("food/candycane")));
		gingerbread_man = register("gingerbread_man", new ItemAetherFood(1).setTextureName(Aether.find("food/gingerbread_man")));		
		holystone_bowl = register("holystone_bowl", new ItemAether(AetherCreativeTabs.food).setTextureName(Aether.find("food/holystone_bowl")));
		uncooked_fruit_stew_bowl = register("uncooked_fruit_stew_bowl", new ItemUncookedFruitStewBowl(5).setTextureName(Aether.find("food/uncooked_fruit_stew_bowl")));
		enchanted_fruit_stew = register("enchanted_fruit_stew", new ItemEnchantedFruitStew(20).setTextureName(Aether.find("food/enchanted_fruit_stew")));		
		blueberry_seeds = register("blueberry_seeds", new ItemBlueberrySeeds().setTextureName(Aether.find("food/blueberry_seeds")));
		raspberry_seeds = register("raspberry_seeds", new ItemRaspberrySeeds().setTextureName(Aether.find("food/raspberry_seeds")));
		strawberry_seeds = register("strawberry_seeds", new ItemStrawberrySeeds().setTextureName(Aether.find("food/strawberry_seeds")));
		grape_seeds = register("grape_seeds", new ItemGrapeSeeds().setTextureName(Aether.find("food/grape_seeds")));
		orange_seeds = register("orange_seeds", new ItemOrangeSeeds().setTextureName(Aether.find("food/orange_seeds")));
		blackberry_seeds = register("blackberry_seeds", new ItemBlackberrySeeds().setTextureName(Aether.find("food/blackberry_seeds")));
		
		osmium_insignia = register("osmium_insignia", new Item().setCreativeTab(AetherCreativeTabs.misc).setTextureName(Aether.find("osmium_insignia")));
		victory_medal = register("victory_medal", new ItemVictoryMedal().setTextureName(Aether.find("victory_medal")));
		elysian_core = register("elysian_core", new ItemElysianCore().setCreativeTab(AetherCreativeTabs.misc).setTextureName(Aether.find("misc/elysian_core")));
		notched_core = register("notched_core", new ItemNotchedCore().setCreativeTab(AetherCreativeTabs.misc).setTextureName(Aether.find("misc/notched_core")));
		elysian_offering = register("elysian_offering", new ItemElysianOffering().setCreativeTab(AetherCreativeTabs.misc).setTextureName(Aether.find("misc/elysian_offering")));
		
		dungeon_key = register("dungeon_key", new ItemDungeonKey());
		skyroot_bucket = register("skyroot_bucket", new ItemSkyrootBucket());
		cloud_parachute = register("cold_parachute", new ItemAetherParachute().setTextureName(Aether.find("misc/parachutes/cold_parachute")));
		blue_parachute = register("blue_parachute", new ItemAetherBlueParachute().setTextureName(Aether.find("misc/parachutes/blue_parachute")));
		golden_parachute = register("golden_parachute", new ItemAetherParachute().setTextureName(Aether.find("misc/parachutes/golden_parachute")));

		moa_egg = register("moa_egg", new ItemMoaEgg());

		dart_shooter = register("dart_shooter", new ItemDartShooter());
		phoenix_bow = register("phoenix_bow", new ItemPhoenixBow());
		tipped_phoenix_bow = register("tipped_phoenix_bow", new ItemTippedPhoenixBow());
		amplified_phoenix_bow = register("amplified_phoenix_bow", new ItemAmplifiedPhoenixBow());
		cyro_bow = register("cyro_bow", new ItemCyroBow());
		tipped_cyro_bow = register("tipped_cyro_bow", new ItemTippedCyroBow());
		amplified_cyro_bow = register("amplified_cyro_bow", new ItemAmplifiedCyroBow());
		dart = register("dart", new ItemDart());	

		skyroot_sword = register("skyroot_sword", new ItemSkyrootSword().setTextureName(Aether.find("weapons/skyroot_sword")));
		tipped_skyroot_sword = register("tipped_skyroot_sword", new ItemTippedSkyrootSword().setTextureName(Aether.find("weapons/tipped_skyroot_sword")));
		amplified_skyroot_sword = register("amplified_skyroot_sword", new ItemAmplifiedSkyrootSword().setTextureName(Aether.find("weapons/amplified_skyroot_sword")));
		holystone_sword = register("holystone_sword", new ItemHolystoneSword().setTextureName(Aether.find("weapons/holystone_sword")));
		tipped_holystone_sword = register("tipped_holystone_sword", new ItemTippedHolystoneSword().setTextureName(Aether.find("weapons/tipped_holystone_sword")));
		amplified_holystone_sword = register("amplified_holystone_sword", new ItemAmplifiedHolystoneSword().setTextureName(Aether.find("weapons/amplified_holystone_sword")));
		zanite_sword = register("zanite_sword", new ItemZaniteSword().setTextureName(Aether.find("weapons/zanite_sword")));
		tipped_zanite_sword = register("tipped_zanite_sword", new ItemTippedZaniteSword().setTextureName(Aether.find("weapons/tipped_zanite_sword")));
		amplified_zanite_sword = register("amplified_zanite_sword", new ItemAmplifiedZaniteSword().setTextureName(Aether.find("weapons/amplified_zanite_sword")));
		arkenium_sword = register("arkenium_sword", new ItemArkeniumSword().setTextureName(Aether.find("weapons/arkenium_sword")));
		tipped_arkenium_sword = register("tipped_arkenium_sword", new ItemTippedArkeniumSword().setTextureName(Aether.find("weapons/tipped_arkenium_sword")));
		continuum_sword = register("continuum_sword", new ItemContinuumSword(0, null).setTextureName(Aether.find("weapons/continuum_sword")));
		tipped_continuum_sword = register("tipped_continuum_sword", new ItemTippedContinuumSword(0, null).setTextureName(Aether.find("weapons/tipped_continuum_sword")));
		amplified_continuum_sword = register("amplified_continuum_sword", new ItemAmplifiedContinuumSword(0, null).setTextureName(Aether.find("weapons/amplified_continuum_sword")));
		gravitite_sword = register("gravitite_sword", new ItemGravititeSword().setTextureName(Aether.find("weapons/gravitite_sword")));
		tipped_gravitite_sword = register("tipped_gravitite_sword", new ItemTippedGravititeSword().setTextureName(Aether.find("weapons/tipped_gravitite_sword")));
		divineral_sword = register("divineral_sword", new ItemDivineralSword().setTextureName(Aether.find("weapons/divineral_sword")));
		tipped_divineral_sword = register("tipped_divineral_sword", new ItemTippedDivineralSword().setTextureName(Aether.find("weapons/tipped_divineral_sword")));
		

		flaming_sword = register("flaming_sword", new ItemElementalSword().setTextureName(Aether.find("weapons/flaming_sword")));
		tipped_flaming_sword = register("tipped_flaming_sword", new ItemTippedFlamingSword().setTextureName(Aether.find("weapons/tipped_flaming_sword")));
		amplified_flaming_sword = register("amplified_flaming_sword", new ItemAmplifiedFlamingSword().setTextureName(Aether.find("weapons/amplified_flaming_sword")));
		lightning_sword = register("lightning_sword", new ItemElementalSword().setTextureName(Aether.find("weapons/lightning_sword")));
		tipped_lightning_sword = register("tipped_lightning_sword", new ItemTippedLightningSword().setTextureName(Aether.find("weapons/tipped_lightning_sword")));
		amplified_lightning_sword = register("amplified_lightning_sword", new ItemAmplifiedLightningSword().setTextureName(Aether.find("weapons/amplified_lightning_sword")));
		holy_sword = register("holy_sword", new ItemElementalSword().setTextureName(Aether.find("weapons/holy_sword")));
		tipped_holy_sword = register("tipped_holy_sword", new ItemTippedHolySword().setTextureName(Aether.find("weapons/tipped_holy_sword")));
		amplified_holy_sword = register("amplified_holy_sword", new ItemAmplifiedHolySword().setTextureName(Aether.find("weapons/amplified_holy_sword")));
		vampire_blade = register("vampire_blade", new ItemVampireBlade().setTextureName(Aether.find("weapons/vampire_blade")));
		tipped_vampire_blade = register("tipped_vampire_blade", new ItemTippedVampireBlade().setTextureName(Aether.find("weapons/tipped_vampire_blade")));
		amplified_vampire_blade = register("amplified_vampire_blade", new ItemAmplifiedVampireBlade().setTextureName(Aether.find("weapons/amplified_vampire_blade")));
		pig_slayer = register("pig_slayer", new ItemPigSlayer().setTextureName(Aether.find("weapons/pig_slayer")));
		candy_cane_sword = register("candy_cane_sword", new ItemCandyCaneSword().setTextureName(Aether.find("weapons/new_candycane_sword")));
		notch_hammer = register("notch_hammer", new ItemNotchHammer().setTextureName(Aether.find("weapons/notch_hammer")));
		tipped_notch_hammer = register("tipped_notch_hammer", new ItemTippedNotchHammer().setTextureName(Aether.find("weapons/tipped_notch_hammer")));
		amplified_notch_hammer = register("amplified_notch_hammer", new ItemAmplifiedNotchHammer().setTextureName(Aether.find("weapons/amplified_notch_hammer")));
		jeb_hammer = register("jeb_hammer", new ItemJebHammer().setTextureName(Aether.find("weapons/jeb_hammer")));
		tipped_jeb_hammer = register("tipped_jeb_hammer", new ItemTippedJebHammer().setTextureName(Aether.find("weapons/tipped_jeb_hammer")));
		amplified_jeb_hammer = register("amplified_jeb_hammer", new ItemAmplifiedJebHammer().setTextureName(Aether.find("weapons/amplified_jeb_hammer")));
		battle_sentry_hammer = register("battle_sentry_hammer", new ItemBattleSentryHammer(4.0F, EnumAetherMultiToolType.MULTI).setTextureName(Aether.find("tools/hammer_battle_sentry")));
		tipped_battle_sentry_hammer = register("tipped_battle_sentry_hammer", new ItemTippedBattleSentryHammer(4.0F, EnumAetherMultiToolType.MULTI).setTextureName(Aether.find("tools/tipped_hammer_battle_sentry")));
		amplified_battle_sentry_hammer = register("amplified_battle_sentry_hammer", new ItemAmplifiedBattleSentryHammer(4.0F, EnumAetherMultiToolType.MULTI).setTextureName(Aether.find("tools/amplified_hammer_battle_sentry")));
		builder_slayer = register("builder_slayer", new ItemBuilderSlayer().setTextureName(Aether.find("weapons/builder_slayer")));
		overworld_slayer = register("overworld_slayer", new ItemOverworldSlayer().setTextureName(Aether.find("weapons/overworld_slayer")));
		tipped_overworld_slayer = register("tipped_overworld_slayer", new ItemTippedOverworldSlayer().setTextureName(Aether.find("weapons/tipped_overworld_slayer")));
		amplified_overworld_slayer = register("amplified_overworld_slayer", new ItemAmplifiedOverworldSlayer().setTextureName(Aether.find("weapons/amplified_overworld_slayer")));		
		nether_slayer = register("nether_slayer", new ItemNetherSlayer().setTextureName(Aether.find("weapons/nether_slayer")));
		tipped_nether_slayer = register("tipped_nether_slayer", new ItemTippedNetherSlayer().setTextureName(Aether.find("weapons/tipped_nether_slayer")));
		amplified_nether_slayer = register("amplified_nether_slayer", new ItemAmplifiedNetherSlayer().setTextureName(Aether.find("weapons/amplified_nether_slayer")));		
		ender_slayer = register("ender_slayer", new ItemEnderSlayer().setTextureName(Aether.find("weapons/ender_slayer")));
		tipped_ender_slayer = register("tipped_ender_slayer", new ItemTippedEnderSlayer().setTextureName(Aether.find("weapons/tipped_ender_slayer")));
		amplified_ender_slayer = register("amplified_ender_slayer", new ItemAmplifiedEnderSlayer().setTextureName(Aether.find("weapons/amplified_ender_slayer")));		
		dragon_bane = register("dragon_bane", new ItemDragonSlayer().setTextureName(Aether.find("weapons/dragon_bane")));
		tipped_dragon_bane = register("tipped_dragon_bane", new ItemTippedDragonSlayer().setTextureName(Aether.find("weapons/tipped_dragon_bane")));
		amplified_dragon_bane = register("amplified_dragon_bane", new ItemAmplifiedDragonSlayer().setTextureName(Aether.find("weapons/amplified_dragon_bane")));	
		
		if (Loader.isModLoaded("nova_craft") && AetherConfig.enable_ascensite) {
		ascensite_sword = register("ascensite_sword", new ItemAscensiteSword().setTextureName(Aether.find("weapons/ascensite_sword")));
		}
		
		leather_gloves = register("leather_gloves", new ItemAccessoryDyed(AccessoryType.GLOVES).setMaxDamage(82).setTextureName(Aether.find("accessories/leather_gloves")));
		iron_gloves = register("iron_gloves", new ItemAccessory(AccessoryType.GLOVES).setMaxDamage(152).setTextureName(Aether.find("accessories/solid_gloves")));
		golden_gloves = register("golden_gloves", new ItemAccessory(AccessoryType.GLOVES).setColor(0xFBF424).setMaxDamage(42).setTextureName(Aether.find("accessories/solid_gloves")));
		chain_gloves = register("chain_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("chain").setMaxDamage(122).setTextureName(Aether.find("accessories/chain_gloves")));
		diamond_gloves = register("diamond_gloves", new ItemAccessory(AccessoryType.GLOVES).setColor(0x33ebcb).setMaxDamage(352).setTextureName(Aether.find("accessories/solid_gloves")));
		
		if (Loader.isModLoaded("etfuturum")) {
		copper_gloves = register("copper_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("copper").setMaxDamage(92).setTextureName(Aether.find("accessories/copper_gloves")));
		netherite_gloves = register("netherite_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("netherite").setMaxDamage(552).setTextureName(Aether.find("accessories/netherite_gloves")));
		}
			
		if (Loader.isModLoaded("netherlicious")) {
		effrine_gloves = register("effrine_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("effrine").setMaxDamage(132).setTextureName(Aether.find("accessories/effrine_gloves")));
		heavy_blaze_gloves = register("heavy_blaze_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("heavy_blaze").setMaxDamage(352).setTextureName(Aether.find("accessories/heavy_blaze_gloves")));
		}
			
		if (Loader.isModLoaded("nova_craft")) {
		pherithium_gloves = register("pherithium_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("pherithium").setMaxDamage(182).setTextureName(Aether.find("accessories/pherithium_gloves")));
		vanite_gloves = register("vanite_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("vanite").setMaxDamage(122).setTextureName(Aether.find("accessories/vanite_gloves")));
		klangite_gloves = register("klangite_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("klangite").setMaxDamage(2022).setTextureName(Aether.find("accessories/klangite_gloves")));
		}
		
		if (Loader.isModLoaded("nova_craft") && AetherConfig.enable_ascensite) {
		ascensite_gloves = register("ascensite_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setTexture("ascensite").setMaxDamage(8182).setTextureName(Aether.find("accessories/ascensite_gloves")));	
		}
		
		zanite_gloves = register("zanite_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("zanite").setMaxDamage(152).setTextureName(Aether.findII("accessories/zanite_gloves")));
		amplified_zanite_gloves = register("amplified_zanite_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setTexture("amplified_zanite").setMaxDamage(752).setTextureName(Aether.find("accessories/amplified_zanite_gloves")));
		arkenium_gloves = register("arkenium_gloves", new ItemAccessory(AccessoryType.GLOVES).setColor(0x4C4A50).setMaxDamage(252).setTextureName(Aether.find("accessories/arkenium_gloves")));
		amplified_arkenium_gloves = register("amplified_arkenium_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setColor(0x475855).setMaxDamage(752).setTextureName(Aether.find("accessories/amplified_arkenium_gloves")));		
		continuum_gloves = register("continuum_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("continuum").setMaxDamage(352).setTextureName(Aether.find("accessories/continuum_gloves")));
		amplified_continuum_gloves = register("amplified_continuum_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setTexture("amplified_continuum").setMaxDamage(752).setTextureName(Aether.find("accessories/amplified_continuum_gloves")));
		
		gravitite_gloves = register("gravitite_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("gravitite").setMaxDamage(452).setTextureName(Aether.find("accessories/gravitite_gloves")));
		divineral_gloves = register("divineral_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("divineral").setAmplifiedDungeonLoot().setMaxDamage(952).setTextureName(Aether.find("accessories/divineral_gloves")));
		neptune_gloves = register("neptune_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("neptune").setDungeonLoot().setMaxDamage(372).setTextureName(Aether.findII("accessories/neptune_gloves")));
		amplified_neptune_gloves = register("amplified_neptune_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("amplified_neptune").setAmplifiedDungeonLoot().setMaxDamage(972).setTextureName(Aether.find("accessories/amplified_neptune_gloves")));
		phoenix_gloves = register("phoenix_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("phoenix").setDungeonLoot().setMaxDamage(372).setTextureName(Aether.find("accessories/phoenix_gloves")));
		amplified_phoenix_gloves = register("amplified_phoenix_gloves", new ItemAccessory(AccessoryType.GLOVES).setTexture("amplified_phoenix").setAmplifiedDungeonLoot().setMaxDamage(972).setTextureName(Aether.find("accessories/amplified_phoenix_gloves")));
		obsidian_gloves = register("obsidian_gloves", new ItemAccessory(AccessoryType.GLOVES).setDungeonLoot().setColor(0x1b1447).setMaxDamage(672).setTextureName(Aether.findII("accessories/solid_gloves")));
		amplified_obsidian_gloves = register("amplified_obsidian_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setColor(0x5b2c63).setMaxDamage(972).setTextureName(Aether.find("accessories/solid_gloves")));
		valkyrie_gloves = register("valkyrie_gloves", new ItemAccessory(AccessoryType.GLOVES).setDungeonLoot().setTexture("valkyrie").setMaxDamage(372).setTextureName(Aether.find("accessories/valkyrie_gloves")));
		amplified_valkyrie_gloves = register("amplified_valkyrie_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setTexture("amplified_valkyrie").setMaxDamage(972).setTextureName(Aether.find("accessories/amplified_valkyrie_gloves")));		
		elysian_gloves = register("elysian_gloves", new ItemAccessory(AccessoryType.GLOVES).setDungeonLoot().setTexture("elysian").setMaxDamage(472).setTextureName(Aether.find("accessories/elysian_gloves")));
		amplified_elysian_gloves = register("amplified_elysian_gloves", new ItemAccessory(AccessoryType.GLOVES).setAmplifiedDungeonLoot().setTexture("amplified_elysian").setMaxDamage(1872).setTextureName(Aether.find("accessories/amplified_elysian_gloves")));
		
		iron_ring = register("iron_ring", new ItemAccessory(AccessoryType.RING).setTextureName(Aether.find("accessories/iron_ring")));
		golden_ring = register("golden_ring", new ItemAccessory(AccessoryType.RING).setTextureName(Aether.find("accessories/golden_ring")));
		diamond_ring = register("diamond_ring", new ItemAccessory(AccessoryType.RING).setMaxDamage(323).setTextureName(Aether.find("accessories/diamond_ring")));
		
		if (Loader.isModLoaded("etfuturum")) {
		copper_ring = register("copper_ring", new ItemAccessory(AccessoryType.RING).setMaxDamage(56).setTextureName(Aether.find("accessories/copper_ring")));
		netherite_ring = register("netherite_ring", new ItemAccessory(AccessoryType.RING).setMaxDamage(493).setTextureName(Aether.find("accessories/netherite_ring")));
		}
		
		zanite_ring = register("zanite_ring", new ItemAccessory(AccessoryType.RING).setMaxDamage(98).setTextureName(Aether.find("accessories/zanite_ring")));
		arkenium_ring = register("arkenium_ring", new ItemAccessory(AccessoryType.RING).setTextureName(Aether.find("accessories/arkenium_ring")));
		continuum_ring = register("continuum_ring", new ItemAccessory(AccessoryType.RING).setTextureName(Aether.find("accessories/continuum_ring")));
		gravitite_ring = register("gravitite_ring", new ItemAccessory(AccessoryType.RING).setTextureName(Aether.find("accessories/gravitite_ring")));
		divineral_ring = register("divineral_ring", new ItemAccessory(AccessoryType.RING).setAmplifiedDungeonLoot().setMaxDamage(803).setTextureName(Aether.find("accessories/divineral_ring")));		
		auralite_ring = register("auralite_ring", new ItemAccessory(AccessoryType.RING).setDungeonLoot().setMaxDamage(178).setTextureName(Aether.find("accessories/auralite_ring")));
		ice_ring = register("ice_ring", new ItemAccessory(AccessoryType.RING).setColor(0x95e6e7).setMaxDamage(125).setTextureName(Aether.find("accessories/ring_base")));
		
		valkyrie_ring = register("valkyrie_ring", new ItemAccessory(AccessoryType.RING).setDungeonLoot().setMaxDamage(476).setTextureName(Aether.find("accessories/valkyrie_ring")));
		reinforced_valkyrie_ring = register("reinforced_valkyrie_ring", new ItemAccessory(AccessoryType.RING).setReinforcedDungeonLoot().setMaxDamage(876).setTextureName(Aether.find("accessories/ref_valkyrie_ring")));
		amplified_valkyrie_ring = register("amplified_valkyrie_ring", new ItemAccessory(AccessoryType.RING).setAmplifiedDungeonLoot().setMaxDamage(2076).setTextureName(Aether.find("accessories/amplified_valkyrie_ring")));
		
		bone_ring = register("bone_ring", new ItemAccessory(AccessoryType.RING).setDungeonLoot().setMaxDamage(201).setTextureName(Aether.find("accessories/bone_ring")));
		reinforced_bone_ring = register("reinforced_bone_ring", new ItemAccessory(AccessoryType.RING).setReinforcedDungeonLoot().setMaxDamage(601).setTextureName(Aether.find("accessories/ref_bone_ring")));
		amplified_bone_ring = register("amplified_bone_ring", new ItemAccessory(AccessoryType.RING).setAmplifiedDungeonLoot().setMaxDamage(1001).setTextureName(Aether.find("accessories/amplified_bone_ring")));
		
		haste_ring = register("haste_ring", new ItemAccessory(AccessoryType.RING).setDungeonLoot().setMaxDamage(324).setTextureName(Aether.find("accessories/haste_ring")));
		reinforced_haste_ring = register("reinforced_haste_ring", new ItemAccessory(AccessoryType.RING).setReinforcedDungeonLoot().setMaxDamage(824).setTextureName(Aether.find("accessories/ref_haste_ring")));
		amplified_haste_ring = register("amplified_haste_ring", new ItemAccessory(AccessoryType.RING).setAmplifiedDungeonLoot().setMaxDamage(1624).setTextureName(Aether.find("accessories/amplified_haste_ring")));
		
		elysian_ring = register("elysian_ring", new ItemAccessory(AccessoryType.RING).setDungeonLoot().setMaxDamage(524).setTextureName(Aether.find("accessories/elysian_ring")));
		reinforced_elysian_ring = register("reinforced_elysian_ring", new ItemAccessory(AccessoryType.RING).setReinforcedDungeonLoot().setMaxDamage(1067).setTextureName(Aether.find("accessories/ref_elysian_ring")));
		amplified_elysian_ring = register("amplified_elysian_ring", new ItemAccessory(AccessoryType.RING).setAmplifiedDungeonLoot().setMaxDamage(2107).setTextureName(Aether.find("accessories/amplified_elysian_ring")));
		
		iron_pendant = register("iron_pendant", new ItemAccessory(AccessoryType.PENDANT).setTextureName(Aether.find("accessories/pendant_base")));
		golden_pendant = register("golden_pendant", new ItemAccessory(AccessoryType.PENDANT).setColor(0xeaee57).setTextureName(Aether.find("accessories/pendant_base")));
		diamond_pendant = register("diamond_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("diamond").setMaxDamage(363).setTextureName(Aether.find("accessories/diamond_pendant")));
		
		if (Loader.isModLoaded("etfuturum")) {
		copper_pendant = register("copper_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("copper").setMaxDamage(94).setTextureName(Aether.find("accessories/copper_pendant")));
		netherite_pendant = register("netherite_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("netherite").setMaxDamage(503).setTextureName(Aether.find("accessories/netherite_pendant")));
		}
		
		zanite_pendant = register("zanite_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("zanite").setMaxDamage(196).setTextureName(Aether.find("accessories/zanite_pendant")));
		arkenium_pendant = register("arkenium_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("arkenium").setTextureName(Aether.find("accessories/arkenium_pendant")));
		continuum_pendant = register("continuum_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("continuum").setTextureName(Aether.find("accessories/continuum_pendant")));
		gravitite_pendant = register("gravitite_pendant", new ItemAccessory(AccessoryType.PENDANT).setTexture("gravitite").setTextureName(Aether.find("accessories/gravitite_pendant")));
		divineral_pendant = register("divineral_pendant", new ItemAccessory(AccessoryType.PENDANT).setAmplifiedDungeonLoot().setTexture("divineral").setAmplifiedDungeonLoot().setMaxDamage(803).setTextureName(Aether.find("accessories/divineral_pendant")));
		
		auralite_pendant = register("auralite_pendant", new ItemAccessory(AccessoryType.PENDANT).setDungeonLoot().setTexture("auralite").setMaxDamage(203).setTextureName(Aether.find("accessories/auralite_pendant")));		
		reinforced_auralite_pendant = register("reinforced_auralite_pendant", new ItemAccessory(AccessoryType.PENDANT).setReinforcedDungeonLoot().setTexture("ref_auralite").setMaxDamage(603).setTextureName(Aether.find("accessories/ref_auralite_pendant")));		
		amplified_auralite_pendant = register("amplified_auralite_pendant", new ItemAccessory(AccessoryType.PENDANT).setAmplifiedDungeonLoot().setTexture("amplified_auralite").setMaxDamage(1903).setTextureName(Aether.find("accessories/amplified_auralite_pendant")));		
		
		ice_pendant = register("ice_pendant", new ItemAccessory(AccessoryType.PENDANT).setColor(0x95e6e7).setMaxDamage(250).setTextureName(Aether.find("accessories/pendant_base")));
		false_wings = register("false_wings", new ItemAccessory(AccessoryType.PENDANT).setDungeonLoot().setTextureName(Aether.find("accessories/false_wings")));
		
		red_cape = register("red_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xae3e38).setTextureName(Aether.find("accessories/cape_color_base")));
		blue_cape = register("blue_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x323e9a).setTextureName(Aether.find("accessories/cape_color_base")));
		yellow_cape = register("yellow_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xcfc231).setTextureName(Aether.find("accessories/cape_color_base")));
		white_cape = register("white_cape", new ItemAccessory(AccessoryType.CAPE).setTextureName(Aether.find("accessories/cape_color_base")));
		black_cape = register("black_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x242020).setTextureName(Aether.find("accessories/cape_color_base")));
		green_cape = register("green_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x3c511f).setTextureName(Aether.find("accessories/cape_color_base")));
		purple_cape = register("purple_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x9152c6).setTextureName(Aether.find("accessories/cape_color_base")));
		orange_cape = register("orange_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xdb7b3b).setTextureName(Aether.find("accessories/cape_color_base")));
		lime_cape = register("lime_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x53c347).setTextureName(Aether.find("accessories/cape_color_base")));
		pink_cape = register("pink_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xdea6b6).setTextureName(Aether.find("accessories/cape_color_base")));
		gray_cape = register("gray_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x464646).setTextureName(Aether.find("accessories/cape_color_base")));
		cyan_cape = register("cyan_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x36809e).setTextureName(Aether.find("accessories/cape_color_base")));
		light_blue_cape = register("light_blue_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x7e99d0).setTextureName(Aether.find("accessories/cape_color_base")));
		light_gray_cape = register("light_gray_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xa9afaf).setTextureName(Aether.find("accessories/cape_color_base")));
		magenta_cape = register("magenta_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0xc16dc9).setTextureName(Aether.find("accessories/cape_color_base")));
		brown_cape = register("brown_cape", new ItemAccessory(AccessoryType.CAPE).setColor(0x563822).setTextureName(Aether.find("accessories/cape_color_base")));
			
		swet_cape = register("swet_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("swet_cape").setDungeonLoot().setTextureName(Aether.find("accessories/swet_cape")));
		invisibility_cape = register("invisibility_cape", new ItemAccessory(AccessoryType.CAPE).setDungeonLoot().setTextureName(Aether.find("accessories/invisibility_cape")));
		agility_cape = register("agility_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("agility_cape").setDungeonLoot().setTextureName(Aether.find("accessories/agility_cape")));
		valkyrie_cape = register("valkyrie_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("valkyrie_cape").setDungeonLoot().setTextureName(Aether.find("accessories/valkyrie_cape")));
		phoenix_cape = register("phoenix_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("phoenix_cape").setDungeonLoot().setTextureName(Aether.find("accessories/phoenix_cape")));
		aer_cape = register("aer_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("aer_cape").setDungeonLoot().setTextureName(Aether.find("accessories/aer_cape")));
		discharge_cape = register("discharge_cape", new ItemAccessory(AccessoryType.CAPE).setTexture("discharge_cape").setDungeonLoot().setTextureName(Aether.find("accessories/discharge_cape")));
		
		golden_feather = register("golden_feather", new ItemAccessory(AccessoryType.MISC).setDungeonLoot().setMaxDamage(120).setTextureName(Aether.find("accessories/new_golden_feather")));
		reinforced_golden_feather = register("reinforced_golden_feather", new ItemAccessory(AccessoryType.MISC).setReinforcedDungeonLoot().setMaxDamage(480).setTextureName(Aether.find("accessories/ref_golden_feather")));
		amplified_golden_feather = register("amplified_golden_feather", new ItemAccessory(AccessoryType.MISC).setPoweredDungeonLoot().setMaxDamage(1800).setTextureName(Aether.find("accessories/amplified_golden_feather")));
		
		flaming_stone = register("flaming_stone", new ItemAccessory(AccessoryType.MISC).setDungeonLoot().setTextureName(Aether.find("accessories/flaming_stone")));
		
		regeneration_stone = register("regeneration_stone", new ItemAccessory(AccessoryType.MISC).setDungeonLoot().setMaxDamage(120).setTextureName(Aether.find("accessories/regeneration_stone")));
		reinforced_regeneration_stone = register("reinforced_regeneration_stone", new ItemAccessory(AccessoryType.MISC).setReinforcedDungeonLoot().setMaxDamage(480).setTextureName(Aether.find("accessories/ref_regeneration_stone")));
		healing_matrix = register("healing_matrix", new ItemAccessory(AccessoryType.MISC).setAmplifiedDungeonLoot().setMaxDamage(1800).setTextureName(Aether.find("accessories/healing_matrix")));
		
		iron_bubble = register("iron_bubble", new ItemAccessory(AccessoryType.MISC).setDungeonLoot().setMaxDamage(120).setTextureName(Aether.find("accessories/iron_bubble")));
		reinforced_iron_bubble = register("reinforced_iron_bubble", new ItemAccessory(AccessoryType.MISC).setReinforcedDungeonLoot().setMaxDamage(480).setTextureName(Aether.find("accessories/ref_iron_bubble")));
		amplified_iron_bubble = register("amplified_iron_bubble", new ItemAccessory(AccessoryType.MISC).setAmplifiedDungeonLoot().setMaxDamage(1800).setTextureName(Aether.find("accessories/amplified_iron_bubble")));
		
		life_shard = register("life_shard", new ItemLifeShard().setTextureName(Aether.find("misc/life_shard")));
		dexterity_shard = register("dexterity_shard", new ItemDexterityShard().setTextureName(Aether.find("misc/dexterity_shard")));
		power_shard = register("power_shard", new ItemPowerShard().setTextureName(Aether.find("misc/power_shard")));
		cloud_staff = register("cloud_staff", new ItemCloudStaff().setTextureName(Aether.find("staff/cloud_staff")));
		nature_staff = register("nature_staff", new ItemNatureStaff().setTextureName(Aether.find("staff/nature_staff")));
		confractus_staff = register("confractus_staff", new ItemConfractusStaff().setTextureName(Aether.find("staff/confractus_staff")));		
		zanite_and_cinerarium = register("zanite_and_cinerarium", new ItemZaniteAndCinerarium().setTextureName(Aether.find("tools/zanite_and_cinerarium")));
		zanite_and_cyro = register("zanite_and_cyro", new ItemZaniteAndCyro().setTextureName(Aether.find("tools/zanite_and_cyro")));
		zanite_shears = register("zanite_shears", new ItemZaniteShears().setTextureName(Aether.find("tools/zanite_shears")));
		shears_of_agnes = register("shears_of_agnes", new ItemAgnesShears().setTextureName(Aether.find("tools/shears_of_agnes")));
		
		lightning_knife = register("lightning_knife", new ItemLightningKnife().setTextureName(Aether.find("weapons/lightning_knife")));
		valkyrie_lance = register("valkyrie_lance", new ItemValkyrieLance().setTextureName(Aether.find("weapons/valkyrie_lance")));
		tipped_valkyrie_lance = register("tipped_valkyrie_lance", new ItemTippedValkyrieLance().setTextureName(Aether.find("weapons/tipped_valkyrie_lance")));
		amplified_valkyrie_lance = register("amplified_valkyrie_lance", new ItemAmplifiedValkyrieLance().setTextureName(Aether.find("weapons/amplified_valkyrie_lance")));
		sentry_boots = register("sentry_boots", new ItemAetherDungeonArmor(3, ArmorMaterial.DIAMOND, "sentry", null).setTextureName(Aether.find("armor/sentry_boots")));
		scaled_sentry_boots = register("scaled_sentry_boots", new ItemScaledDungeonArmor(3, ArmorMaterial.DIAMOND, "scaled_sentry", null).setTextureName(Aether.find("armor/scaled_sentry_boots")));
		amplified_sentry_boots = register("amplified_sentry_boots", new ItemDivineralArmor(3, ArmorMaterial.DIAMOND, "amplified_sentry", null).setTextureName(Aether.find("armor/amplified_sentry_boots")));

		aether_tune = register("aether_tune", new ItemAetherDisc("aether_tune", "Noisestorm").setTextureName(Aether.find("music/aether_tune")));
		ascending_dawn = register("ascending_dawn", new ItemAetherDisc("ascending_dawn", "Emile van Krieken").setTextureName(Aether.find("music/ascending_dawn")));
		welcoming_skies = register("welcoming_skies", new ItemAetherDisc("welcoming_skies", "Voyed").setTextureName(Aether.find("music/welcoming_skies")));
		legacy = register("legacy", new ItemAetherDisc("legacy", "Jon Lachney").setTextureName(Aether.find("music/legacy")));

		repulsion_shield = register("repulsion_shield", new ItemAccessory(AccessoryType.SHIELD).setTexture("repulsion").setInactiveTexture("repulsion_movement").setDungeonLoot().setMaxDamage(152).setTextureName(Aether.find("accessories/repulsion_shield")));
		jeb_shield = register("jeb_shield", new ItemAccessory(AccessoryType.SHIELD).setTexture("jebrepulsion").setInactiveTexture("jebrepulsion_movement").setDungeonLoot().setMaxDamage(625).setTextureName(Aether.find("accessories/jeb_shield")));
		sentry_shield = register("sentry_shield", new ItemAccessory(AccessoryType.SHIELD).setTexture("sentry").setInactiveTexture("sentry_movement").setDungeonLoot().setMaxDamage(124).setTextureName(Aether.find("accessories/sentry_shield")));
		zanite_shield = register("zanite_shield", new ItemAccessory(AccessoryType.SHIELD).setTexture("zaniterepulsion").setInactiveTexture("zaniterepulsion_movement").setDungeonLoot().setMaxDamage(202).setTextureName(Aether.find("accessories/zanite_shield")));
		gravitite_shield = register("gravitite_shield", new ItemAccessory(AccessoryType.SHIELD).setTexture("gravititerepulsion").setInactiveTexture("gravitierepulsion_movement").setDungeonLoot().setMaxDamage(322).setTextureName(Aether.find("accessories/gravitite_shield")));
			
		lore_book = register("lore_book", new ItemLoreBook().setTextureName(Aether.find("misc/lore_book")));

		developer_stick = register("developer_stick", new ItemDeveloperStick().setTextureName(Aether.find("skyroot_stick")));

		aether_spawn_egg = register("aether_spawn_egg", new ItemAetherSpawnEgg().setTextureName("spawn_egg"));

		skyroot_bed_item = register("skyroot_bed_item", new ItemSkyrootBed().setTextureName(Aether.find("skyroot_bed_item")));

		aether_portal_frame = register("aether_portal_frame", new ItemAetherPortalFrame().setTextureName(Aether.find("aether_portal_frame")));
	}

	private static Item extend(String string, Item setTextureName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Item register(String name, Item item) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name, Aether.MOD_ID);

		return item;
	}

}
