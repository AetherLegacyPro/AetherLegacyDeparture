package com.gildedgames.the_aether.registry;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.registry.recipe.RecipeAccessoryDyes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.AetherAccessory;
import com.gildedgames.the_aether.api.enchantments.AetherAmplifier;
import com.gildedgames.the_aether.api.enchantments.AetherAmplifierFuel;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantmentFuel;
import com.gildedgames.the_aether.api.freezables.AetherFreezable;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.othermods.OtherModItems;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class AetherRegistries {

	public static void initializeAccessories() {
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.leather_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.iron_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.golden_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.chain_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.diamond_gloves, AccessoryType.GLOVES));
		
		if (Loader.isModLoaded("etfuturum")) {
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.netherite_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.copper_gloves, AccessoryType.GLOVES));
		}
		
		if (Loader.isModLoaded("netherlicious")) {
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.effrine_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.heavy_blaze_gloves, AccessoryType.GLOVES));
		}
		
		if (Loader.isModLoaded("nova_craft")) {
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.pherithium_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.vanite_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.klangite_gloves, AccessoryType.GLOVES));
		}

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.zanite_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.gravitite_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.neptune_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.phoenix_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.obsidian_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.valkyrie_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.arkenium_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.continuum_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.power_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.divineral_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.elysian_gloves, AccessoryType.GLOVES));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_zanite_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_arkenium_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_continuum_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_neptune_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_valkyrie_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_obsidian_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_phoenix_gloves, AccessoryType.GLOVES));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_elysian_gloves, AccessoryType.GLOVES));

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.iron_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.golden_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.diamond_ring, AccessoryType.RING));		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.zanite_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.arkenium_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.continuum_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.gravitite_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.divineral_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.auralite_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.ice_ring, AccessoryType.RING));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.valkyrie_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_valkyrie_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_valkyrie_ring, AccessoryType.RING));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.bone_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_bone_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_bone_ring, AccessoryType.RING));
				
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.haste_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_haste_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_haste_ring, AccessoryType.RING));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.elysian_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_elysian_ring, AccessoryType.RING));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_elysian_ring, AccessoryType.RING));
		
		if (Loader.isModLoaded("etfuturum")) {
			AetherAPI.instance().register(new AetherAccessory(ItemsAether.copper_ring, AccessoryType.RING));
			AetherAPI.instance().register(new AetherAccessory(ItemsAether.netherite_ring, AccessoryType.RING));
			}

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.iron_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.golden_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.diamond_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.zanite_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.arkenium_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.continuum_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.gravitite_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.divineral_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.ice_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.false_wings, AccessoryType.PENDANT));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.auralite_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_auralite_pendant, AccessoryType.PENDANT));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_auralite_pendant, AccessoryType.PENDANT));
		
		if (Loader.isModLoaded("etfuturum")) {
			AetherAPI.instance().register(new AetherAccessory(ItemsAether.copper_pendant, AccessoryType.PENDANT));
			AetherAPI.instance().register(new AetherAccessory(ItemsAether.netherite_pendant, AccessoryType.PENDANT));
		}

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.red_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.blue_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.yellow_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.white_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.black_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.green_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.purple_cape, AccessoryType.CAPE));	
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.orange_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.lime_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.pink_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.gray_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.cyan_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.light_gray_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.light_blue_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.magenta_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.brown_cape, AccessoryType.CAPE));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.swet_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.invisibility_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.agility_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.valkyrie_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.phoenix_cape, AccessoryType.CAPE));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.aer_cape, AccessoryType.CAPE));

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.golden_feather, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_golden_feather, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_golden_feather, AccessoryType.MISC));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.regeneration_stone, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_regeneration_stone, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.healing_matrix, AccessoryType.MISC));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.iron_bubble, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.reinforced_iron_bubble, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.amplified_iron_bubble, AccessoryType.MISC));
		
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.invisibility_gem, AccessoryType.MISC));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.flaming_stone, AccessoryType.MISC));

		AetherAPI.instance().register(new AetherAccessory(ItemsAether.repulsion_shield, AccessoryType.SHIELD));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.jeb_shield, AccessoryType.SHIELD));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.sentry_shield, AccessoryType.SHIELD));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.zanite_shield, AccessoryType.SHIELD));
		AetherAPI.instance().register(new AetherAccessory(ItemsAether.gravitite_shield, AccessoryType.SHIELD));
	}

	public static void initializeEnchantments() {
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.skyroot_pickaxe, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.skyroot_axe, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.skyroot_shovel, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.skyroot_sword, 550));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_skyroot_pickaxe, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_skyroot_axe, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_skyroot_shovel, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_skyroot_sword, 1100));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.holystone_pickaxe, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.holystone_axe, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.holystone_shovel, 550));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.holystone_sword, 550));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_holystone_pickaxe, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_holystone_axe, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_holystone_shovel, 1100));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_holystone_sword, 1100));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_pickaxe, 2250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_axe, 2250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_shovel, 2250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_sword, 2250));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_zanite_pickaxe, 4500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_zanite_axe, 4500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_zanite_shovel, 4500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_zanite_sword, 4500));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_pickaxe, 5500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_axe, 5500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_shovel, 5500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_sword, 5500));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_gravitite_pickaxe, 11000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_gravitite_axe, 11000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_gravitite_shovel, 11000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_gravitite_sword, 11000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_pickaxe, 7500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_axe, 7500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_shovel, 7500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_sword, 7500));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_continuum_pickaxe, 15000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_continuum_axe, 15000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_continuum_shovel, 15000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.tipped_continuum_sword, 15000));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_helmet, 6000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_chestplate, 6000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_leggings, 6000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_boots, 6000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_zanite_helmet, 12000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_zanite_chestplate, 12000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_zanite_leggings, 12000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_zanite_boots, 12000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.arkenium_helmet, 10000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.arkenium_chestplate, 10000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.arkenium_leggings, 10000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.arkenium_boots, 10000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_arkenium_helmet, 20000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_arkenium_chestplate, 20000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_arkenium_leggings, 20000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_arkenium_boots, 20000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_helmet, 14000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_chestplate, 14000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_leggings, 14000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.continuum_boots, 14000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_continuum_helmet, 28000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_continuum_chestplate, 28000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_continuum_leggings, 28000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_continuum_boots, 28000));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_helmet, 13000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_chestplate, 13000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_leggings, 13000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.gravitite_boots, 13000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_gravitite_helmet, 26000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_gravitite_chestplate, 26000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_gravitite_leggings, 26000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.scaled_gravitite_boots, 26000));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_ring, 2250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.zanite_pendant, 2250));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.auralite_ring, 7250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.auralite_pendant, 7250));

		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.dart, new ItemStack(ItemsAether.dart, 1, 2), 250));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.dart_shooter, new ItemStack(ItemsAether.dart_shooter, 1, 2), 500));

		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(ItemsAether.skyroot_bucket, 1, 2), new ItemStack(ItemsAether.skyroot_bucket.setContainerItem(null), 1, 3), 1000));

		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.golden_oak_log, 1, 1), ItemsAether.mimicry_ambrosium_shard, 250));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.golden_oak_log, 1, 0), BlocksAether.enchanted_agiosite, 250));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.enchanted_holystone, ItemsAether.healing_stone, 750));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.enchanted_agiosite, ItemsAether.dexterity_stone, 750));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.enchanted_deific, ItemsAether.agility_stone, 750));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.hellfire_stone, ItemsAether.flame_stone, 1500));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.zanite_block, ItemsAether.strength_stone, 1500));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.aerogel, ItemsAether.resistance_stone, 3000));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.raw_gravitite, BlocksAether.enchanted_gravitite, 2000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.gravitite_ore, BlocksAether.enchanted_gravitite, 2000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.aetheral_gravitite_ore, BlocksAether.enchanted_gravitite, 2000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.agiosite_gravitite_ore, BlocksAether.enchanted_gravitite, 2000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.deific_gravitite_ore, BlocksAether.enchanted_gravitite, 2000));	
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.quicksoil, BlocksAether.quicksoil_glass, 250));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.agiosite, 1, 1), BlocksAether.enchanted_agiosite, 250));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.deific, 1, 1), BlocksAether.enchanted_deific, 300));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.aetheral_stone, 1, 1), BlocksAether.enchanted_aetheral_stone, 350));		
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.agiosite, 1, 0), BlocksAether.enchanted_agiosite, 250));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.deific, 1, 0), BlocksAether.enchanted_deific, 300));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.aetheral_stone, 1, 0), BlocksAether.enchanted_aetheral_stone, 350));		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.uncooked_fruit_stew_bowl, ItemsAether.enchanted_fruit_stew, 800));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.arkenium_ore, 1, 1), ItemsAether.arkenium_fragement, 1000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.arkenium_ore, ItemsAether.arkenium_fragement, 1000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.aetheral_arkenium_ore, ItemsAether.arkenium_fragement, 1000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.agiosite_arkenium_ore, ItemsAether.arkenium_fragement, 1000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.deific_arkenium_ore, ItemsAether.arkenium_fragement, 1000));		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.arkenium_chunk, ItemsAether.arkenium_ingot, 4000));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.continuum_ore, ItemsAether.continuum_gemstone, 1200));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.continuum_ore, 1, 1), ItemsAether.continuum_gemstone, 1200));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.continuum_ore, 1, 0), ItemsAether.continuum_gemstone, 1200));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.aetheral_continuum_ore, ItemsAether.continuum_gemstone, 1200));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.agiosite_continuum_ore, ItemsAether.continuum_gemstone, 1200));
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.deific_continuum_ore, ItemsAether.continuum_gemstone, 1200));		
		AetherAPI.instance().register(new AetherEnchantment(BlocksAether.primeval_artifact, ItemsAether.enchanted_divineral, 6000));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.holystone, 1, 1), BlocksAether.enchanted_holystone, 500));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.frozen_quicksoil, 1, 0), BlocksAether.frozen_quicksoil_glass, 300));
		AetherAPI.instance().register(new AetherEnchantment(new ItemStack(BlocksAether.frozen_quicksoil, 1, 1), BlocksAether.frozen_quicksoil_glass, 300));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.cracked_empyrean_gemstone, ItemsAether.charged_empyrean_gemstone, 2000));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.blueberry, ItemsAether.enchanted_blueberry, 600));		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.orange, ItemsAether.enchanted_orange, 600));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.grapes, ItemsAether.enchanted_grapes, 600));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.strawberry, ItemsAether.enchanted_strawberry, 600));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.raspberry, ItemsAether.enchanted_raspberry, 500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.raspberry, ItemsAether.enchanted_blackberry, 500));	
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.wynberry, ItemsAether.rainbow_strawberry, 800));
		AetherAPI.instance().register(new AetherEnchantment(Items.porkchop, Items.cooked_porkchop, 600));
		AetherAPI.instance().register(new AetherEnchantment(Items.beef, Items.cooked_beef, 600));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.raw_thunderlo, ItemsAether.enchanted_thunderlo, 500));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.raw_aerwhale, ItemsAether.enchanted_aerwhale, 700));
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.aechor_petal, ItemsAether.blue_aechor_petal, 1400));
		
		AetherAPI.instance().register(new AetherEnchantment(ItemsAether.aerca_tooth, ItemsAether.aerca_powder, 1500));

		AetherAPI.instance().register(new AetherEnchantment(Items.bow, 4000));
		AetherAPI.instance().register(new AetherEnchantment(Items.fishing_rod, 600));

		AetherAPI.instance().register(new AetherEnchantment(Items.record_11, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_13, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_blocks, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_cat, ItemsAether.legacy, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_far, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_mall, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_mellohi, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_stal, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_strad, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_wait, ItemsAether.aether_tune, 2500));
		AetherAPI.instance().register(new AetherEnchantment(Items.record_ward, ItemsAether.aether_tune, 2500));
		
		AetherAPI.instance().register(new AetherEnchantment(Items.wooden_pickaxe, 225));
		AetherAPI.instance().register(new AetherEnchantment(Items.wooden_axe, 225));
		AetherAPI.instance().register(new AetherEnchantment(Items.wooden_shovel, 225));
		AetherAPI.instance().register(new AetherEnchantment(Items.wooden_hoe, 225));
		AetherAPI.instance().register(new AetherEnchantment(Items.wooden_sword, 225));

		AetherAPI.instance().register(new AetherEnchantment(Items.stone_pickaxe, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.stone_axe, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.stone_shovel, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.stone_hoe, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.stone_sword, 550));

		AetherAPI.instance().register(new AetherEnchantment(Items.iron_pickaxe, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_axe, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_shovel, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_hoe, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_sword, 550));

		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_pickaxe, 5500));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_axe, 5500));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_shovel, 5500));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_hoe, 5500));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_sword, 5500));

		AetherAPI.instance().register(new AetherEnchantment(Items.leather_helmet, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.leather_chestplate, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.leather_leggings, 550));
		AetherAPI.instance().register(new AetherEnchantment(Items.leather_boots, 550));

		AetherAPI.instance().register(new AetherEnchantment(Items.iron_helmet, 6000));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_chestplate, 6000));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_leggings, 6000));
		AetherAPI.instance().register(new AetherEnchantment(Items.iron_boots, 6000));

		AetherAPI.instance().register(new AetherEnchantment(Items.golden_helmet, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.golden_chestplate, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.golden_leggings, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.golden_boots, 2250));

		AetherAPI.instance().register(new AetherEnchantment(Items.chainmail_helmet, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.chainmail_chestplate, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.chainmail_leggings, 2250));
		AetherAPI.instance().register(new AetherEnchantment(Items.chainmail_boots, 2250));

		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_helmet, 10000));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_chestplate, 10000));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_leggings, 10000));
		AetherAPI.instance().register(new AetherEnchantment(Items.diamond_boots, 10000));
	}

	public static void initializeEnchantmentFuel() {
		AetherAPI.instance().register(new AetherEnchantmentFuel(ItemsAether.ambrosium_shard, 900));
		AetherAPI.instance().register(new AetherEnchantmentFuel(ItemsAether.mimicry_ambrosium_shard, 700));
		AetherAPI.instance().register(new AetherEnchantmentFuel(BlocksAether.ambrosium_block, 8100));
		AetherAPI.instance().register(new AetherEnchantmentFuel(ItemsAether.empyrean_gemstone, 1000));
		AetherAPI.instance().register(new AetherEnchantmentFuel(BlocksAether.empyrean_block, 9000));
		AetherAPI.instance().register(new AetherEnchantmentFuel(ItemsAether.charged_empyrean_gemstone, 9000));
		AetherAPI.instance().register(new AetherEnchantmentFuel(BlocksAether.charged_empyrean_block, 81000));
		AetherAPI.instance().register(new AetherEnchantmentFuel(ItemsAether.flaming_stone, 150000));
	}

	public static void initializeFreezables() {
		AetherAPI.instance().register(new AetherFreezable(BlocksAether.aercloud, new ItemStack(BlocksAether.aercloud, 1, 1), 100));
		AetherAPI.instance().register(new AetherFreezable(BlocksAether.skyroot_leaves, BlocksAether.crystal_leaves, 150));
		AetherAPI.instance().register(new AetherFreezable(BlocksAether.golden_oak_leaves, BlocksAether.crystal_leaves, 150));
		AetherAPI.instance().register(new AetherFreezable(new ItemStack(BlocksAether.quicksoil, 1, 0), new ItemStack(BlocksAether.frozen_quicksoil, 1, 0), 350));
		AetherAPI.instance().register(new AetherFreezable(new ItemStack(BlocksAether.quicksoil, 1, 1), new ItemStack(BlocksAether.frozen_quicksoil, 1, 1), 350));
		AetherAPI.instance().register(new AetherFreezable(BlocksAether.luminous_stone, Blocks.glowstone, 500));
		AetherAPI.instance().register(new AetherFreezable(Blocks.glowstone, BlocksAether.arctic_glowstone, 500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.continuum_gemstone, ItemsAether.continuum_orb, 2000));
		AetherAPI.instance().register(new AetherFreezable(new ItemStack(ItemsAether.skyroot_bucket, 1, 1), Blocks.ice, 500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.ascending_dawn, ItemsAether.welcoming_skies, 2500));
		AetherAPI.instance().register(new AetherFreezable(Blocks.ice, Blocks.packed_ice, 750));
		AetherAPI.instance().register(new AetherFreezable(Items.water_bucket, Blocks.ice, 500));
		AetherAPI.instance().register(new AetherFreezable(Items.lava_bucket, Blocks.obsidian, 500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.iron_ring, ItemsAether.ice_ring, 2500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.golden_ring, ItemsAether.ice_ring, 2500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.iron_pendant, ItemsAether.ice_pendant, 2500));
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.golden_pendant, ItemsAether.ice_pendant, 2500));
		
		if (AetherConfig.DiamondFreezableRecipe() == true) {
		AetherAPI.instance().register(new AetherFreezable(BlocksAether.diamond_aercloud, new ItemStack(Items.diamond), 3000));
		}
		
		if (AetherConfig.BlazeRodRecipe() == true) {	
		AetherAPI.instance().register(new AetherFreezable(ItemsAether.cinerarium_rod, Items.blaze_rod, 1800));
		}		
		if (AetherConfig.BlazeRodRecipe() == false) {	
			AetherAPI.instance().register(new AetherFreezable(ItemsAether.cinerarium_rod, ItemsAether.cyro_rod, 1800));
		}
		
		AetherAPI.instance().register(new AetherFreezable(Items.blaze_rod, ItemsAether.cyro_rod, 1800));
		AetherAPI.instance().register(new AetherFreezable(new ItemStack(ItemsAether.poison_gummy_swet, 1, 0), new ItemStack(ItemsAether.poison_gummy_swet, 1, 1), 1000));
	}

	public static void initializeFreezableFuel() {
		AetherAPI.instance().register(new AetherFreezableFuel(BlocksAether.icestone, 4500));
		AetherAPI.instance().register(new AetherFreezableFuel(ItemsAether.icestone_crystal, 500));
		AetherAPI.instance().register(new AetherFreezableFuel(ItemsAether.cyro_rod, 2200));
	}
	
	public static void initializeAmplifiers() {
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_gravitite_sword, ItemsAether.divineral_sword, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_gravitite_pickaxe, ItemsAether.divineral_pickaxe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_gravitite_shovel, ItemsAether.divineral_shovel, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_gravitite_axe, ItemsAether.divineral_axe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.gravitite_hoe, ItemsAether.divineral_hoe, 1800));		
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_gravitite_helmet, ItemsAether.divineral_helmet, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_gravitite_chestplate, ItemsAether.divineral_chestplate, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_gravitite_leggings, ItemsAether.divineral_leggings, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_gravitite_boots, ItemsAether.divineral_boots, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.gravitite_gloves, ItemsAether.divineral_gloves, 900));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.gravitite_ring, ItemsAether.divineral_ring, 900));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.gravitite_pendant, ItemsAether.divineral_pendant, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_continuum_sword, ItemsAether.amplified_continuum_sword, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_continuum_pickaxe, ItemsAether.amplified_continuum_pickaxe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_continuum_shovel, ItemsAether.amplified_continuum_shovel, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_continuum_axe, ItemsAether.amplified_continuum_axe, 1800));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_continuum_helmet, ItemsAether.amplified_continuum_helmet, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_continuum_chestplate, ItemsAether.amplified_continuum_chestplate, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_continuum_leggings, ItemsAether.amplified_continuum_leggings, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_continuum_boots, ItemsAether.amplified_continuum_boots, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.continuum_gloves, ItemsAether.amplified_continuum_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_agility_boots, ItemsAether.amplified_agility_boots, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_sentry_boots, ItemsAether.amplified_sentry_boots, 3600));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_neptune_helmet, ItemsAether.amplified_neptune_helmet, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_neptune_chestplate, ItemsAether.amplified_neptune_chestplate, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_neptune_leggings, ItemsAether.amplified_neptune_leggings, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_neptune_boots, ItemsAether.amplified_neptune_boots, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.neptune_gloves, ItemsAether.amplified_neptune_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_valkyrie_helmet, ItemsAether.amplified_valkyrie_helmet, 4500));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_valkyrie_chestplate, ItemsAether.amplified_valkyrie_chestplate, 4500));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_valkyrie_leggings, ItemsAether.amplified_valkyrie_leggings, 4500));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_valkyrie_boots, ItemsAether.amplified_valkyrie_boots, 4500));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.valkyrie_gloves, ItemsAether.amplified_valkyrie_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_obsidian_helmet, ItemsAether.amplified_obsidian_helmet, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_obsidian_chestplate, ItemsAether.amplified_obsidian_chestplate, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_obsidian_leggings, ItemsAether.amplified_obsidian_leggings, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_obsidian_boots, ItemsAether.amplified_obsidian_boots, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.obsidian_gloves, ItemsAether.amplified_obsidian_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_elysian_helmet, ItemsAether.amplified_elysian_helmet, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_elysian_chestplate, ItemsAether.amplified_elysian_chestplate, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_elysian_leggings, ItemsAether.amplified_elysian_leggings, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_elysian_boots, ItemsAether.amplified_elysian_boots, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.elysian_gloves, ItemsAether.amplified_elysian_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_phoenix_helmet, ItemsAether.amplified_phoenix_helmet, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_phoenix_chestplate, ItemsAether.amplified_phoenix_chestplate, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_phoenix_leggings, ItemsAether.amplified_phoenix_leggings, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_phoenix_boots, ItemsAether.amplified_phoenix_boots, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.phoenix_gloves, ItemsAether.amplified_phoenix_gloves, 900));
			
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_skyroot_sword, ItemsAether.amplified_skyroot_sword, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_skyroot_pickaxe, ItemsAether.amplified_skyroot_pickaxe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_skyroot_shovel, ItemsAether.amplified_skyroot_shovel, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_skyroot_axe, ItemsAether.amplified_skyroot_axe, 1800));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_holystone_sword, ItemsAether.amplified_holystone_sword, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_holystone_pickaxe, ItemsAether.amplified_holystone_pickaxe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_holystone_axe, ItemsAether.amplified_holystone_axe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_holystone_shovel, ItemsAether.amplified_holystone_shovel, 1800));

		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_zanite_sword, ItemsAether.amplified_zanite_sword, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_zanite_pickaxe, ItemsAether.amplified_zanite_pickaxe, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_zanite_shovel, ItemsAether.amplified_zanite_shovel, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_zanite_axe, ItemsAether.amplified_zanite_axe, 1800));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_zanite_helmet, ItemsAether.amplified_zanite_helmet, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_zanite_chestplate, ItemsAether.amplified_zanite_chestplate, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_zanite_leggings, ItemsAether.amplified_zanite_leggings, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_zanite_boots, ItemsAether.amplified_zanite_boots, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.zanite_gloves, ItemsAether.amplified_zanite_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_arkenium_helmet, ItemsAether.amplified_arkenium_helmet, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_arkenium_chestplate, ItemsAether.amplified_arkenium_chestplate, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_arkenium_leggings, ItemsAether.amplified_arkenium_leggings, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.scaled_arkenium_boots, ItemsAether.amplified_arkenium_boots, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.arkenium_gloves, ItemsAether.amplified_arkenium_gloves, 900));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_flaming_sword, ItemsAether.amplified_flaming_sword, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_lightning_sword, ItemsAether.amplified_lightning_sword, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_holy_sword, ItemsAether.amplified_holy_sword, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_vampire_blade, ItemsAether.amplified_vampire_blade, 3600)); 
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_phoenix_bow, ItemsAether.amplified_phoenix_bow, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_cyro_bow, ItemsAether.amplified_cyro_bow, 3600));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_valkyrie_lance, ItemsAether.amplified_valkyrie_lance, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_valkyrie_pickaxe, ItemsAether.amplified_valkyrie_pickaxe, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_valkyrie_shovel, ItemsAether.amplified_valkyrie_shovel, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_valkyrie_axe, ItemsAether.amplified_valkyrie_axe, 3600));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_notch_hammer, ItemsAether.amplified_notch_hammer, 7200));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_jeb_hammer, ItemsAether.amplified_jeb_hammer, 3600));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_battle_sentry_hammer, ItemsAether.amplified_battle_sentry_hammer, 5400));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_overworld_slayer, ItemsAether.amplified_overworld_slayer, 5400));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_nether_slayer, ItemsAether.amplified_nether_slayer, 5400));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_ender_slayer, ItemsAether.amplified_ender_slayer, 5400));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_dragon_bane, ItemsAether.amplified_dragon_bane, 3600));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.tipped_notched_pickaxe, ItemsAether.amplified_notched_pickaxe, 7200));
		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_valkyrie_ring, ItemsAether.amplified_valkyrie_ring, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_bone_ring, ItemsAether.amplified_bone_ring, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_haste_ring, ItemsAether.amplified_haste_ring, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_auralite_pendant, ItemsAether.amplified_auralite_pendant, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_regeneration_stone, ItemsAether.healing_matrix, 2700));		
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_iron_bubble, ItemsAether.amplified_iron_bubble, 1800));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_golden_feather, ItemsAether.amplified_golden_feather, 2700));
		AetherAPI.instance().register(new AetherAmplifier(ItemsAether.reinforced_elysian_ring, ItemsAether.amplified_elysian_ring, 2700));

	}
	
	public static void initializeAmplifierFuel() {
		AetherAPI.instance().register(new AetherAmplifierFuel(BlocksAether.divineral_block, 8100));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_ingot, 900));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.notched_core, 900));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.enchanted_divineral, 100));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_sword, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_pickaxe, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_axe, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_hoe, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_shovel, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_helmet, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_chestplate, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_leggings, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_boots, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.divineral_gloves, 300));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_neptune_helmet, 600));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_neptune_chestplate, 600));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_neptune_leggings, 600));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_neptune_boots, 600));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_neptune_gloves, 600));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_valkyrie_helmet, 1100));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_valkyrie_chestplate, 1100));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_valkyrie_leggings, 1100));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_valkyrie_boots, 1100));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_valkyrie_gloves, 1100));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.amplified_agility_boots, 900));
		
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.lightning_knife, 36));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.jeb_hammer, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.flaming_sword, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_lance, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_bow, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.agility_cape, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.sentry_boots, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.repulsion_shield, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.cloud_staff, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.zanite_shield, 360));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_pickaxe, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_shovel, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_axe, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_helmet, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_chestplate, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_leggings, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_boots, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_gloves, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.valkyrie_cape, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.neptune_helmet, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.neptune_chestplate, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.neptune_leggings, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.neptune_boots, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.neptune_gloves, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.golden_feather, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.lightning_sword, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.holy_sword, 360));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.regeneration_stone, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.aer_cape, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.invisibility_cape, 300));
		
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.notch_hammer, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.vampire_blade, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.iron_bubble, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.vampire_blade, 450));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.pig_slayer, 180));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.life_shard, 450));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.flaming_stone, 450));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.battle_sentry_hammer, 450));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.iron_bubble, 450));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.healing_matrix, 600));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_cape, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_helmet, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_chestplate, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_leggings, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_boots, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.phoenix_gloves, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.obsidian_helmet, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.obsidian_chestplate, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.obsidian_leggings, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.obsidian_boots, 300));
		AetherAPI.instance().register(new AetherAmplifierFuel(ItemsAether.obsidian_gloves, 300));

	}

	public static void register() {
		initializeAccessories();
		initializeEnchantments();
		initializeEnchantmentFuel();
		initializeFreezables();
		initializeFreezableFuel();
		initializeAmplifiers();
		initializeAmplifierFuel();
		initializeRecipes();
		initializeShapelessRecipes();

		GameRegistry.addRecipe(new RecipeAccessoryDyes());
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(BlocksAether.skyroot_log, 1, 1), new ItemStack(Items.coal, 1, 1), 0.15F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(BlocksAether.golden_oak_log, 1, 1), new ItemStack(Items.coal, 1, 1), 0.15F);

		OreDictionary.registerOre("stickWood", ItemsAether.skyroot_stick);
		OreDictionary.registerOre("feather", ItemsAether.cockatrice_feather);
		OreDictionary.registerOre("dyeBlack", new ItemStack(ItemsAether.black_dye));
		OreDictionary.registerOre("dyeWhite", new ItemStack(ItemsAether.white_dye));
		OreDictionary.registerOre("dyeBlue", new ItemStack(ItemsAether.blue_dye));
	}

	private static void initializeShapelessRecipes() {
		registerShapeless("poison_dart_shooter", new ItemStack(ItemsAether.dart_shooter, 1, 1), new ItemStack(ItemsAether.dart_shooter, 1, 0), new ItemStack(ItemsAether.skyroot_bucket, 1, 2));
		registerShapeless("purple_dye", new ItemStack(Items.dye, 2, 5), BlocksAether.purple_flower);
		registerShapeless("lime_dye", new ItemStack(Items.dye, 2, 2), BlocksAether.carrion_flower);
		registerShapeless("light_blue_dye", new ItemStack(Items.dye, 2, 10), BlocksAether.burstblossom);
		registerShapeless("blue_dye", new ItemStack(ItemsAether.blue_dye), BlocksAether.blue_swingtip);
		registerShapeless("purple_dye", new ItemStack(Items.dye, 2, 5), BlocksAether.aechor_sprout);
		registerShapeless("magenta_dye", new ItemStack(Items.dye, 2, 13), BlocksAether.neverbloom);		
		registerShapeless("black_dye", new ItemStack(ItemsAether.black_dye), BlocksAether.moonlit_bloom);
		registerShapeless("white_dye", new ItemStack(ItemsAether.white_dye), BlocksAether.white_flower);	
		registerShapeless("white_dye", new ItemStack(ItemsAether.white_dye), BlocksAether.white_rose);
		registerShapeless("red_dye", new ItemStack(Items.dye, 2, 1), BlocksAether.aether_tulips);	
		registerShapeless("red_dye", new ItemStack(Items.dye, 3, 1), BlocksAether.enchanted_aether_tulips);
		registerShapeless("yellow_dye", new ItemStack(Items.dye, 2, 11), BlocksAether.quickshoot);	
		registerShapeless("yellow_dye", new ItemStack(Items.dye, 3, 11), BlocksAether.enchanted_quickshoot);
		registerShapeless("yellow_dye", new ItemStack(Items.dye, 1, 14), BlocksAether.enchanted_bloom);	
		
		registerShapeless("light_blue_dye", new ItemStack(Items.dye, 2, 10), BlocksAether.divine_bloom);	
		registerShapeless("purple_dye", new ItemStack(Items.dye, 2, 5), BlocksAether.divine_lily);	
		registerShapeless("blue_dye", new ItemStack(ItemsAether.blue_dye, 2, 0), BlocksAether.divine_stalk);	
		
		if (AetherConfig.RedstoneItemsCraftable() == true) {
		registerShapeless("wooden_button", new ItemStack(Blocks.wooden_button), BlocksAether.skyroot_planks);
		registerShapeless("wooden_button", new ItemStack(Blocks.wooden_button), BlocksAether.greatroot_planks);
		registerShapeless("wooden_button", new ItemStack(Blocks.wooden_button), BlocksAether.wisproot_planks);
		registerShapeless("wooden_button", new ItemStack(Blocks.wooden_button), BlocksAether.void_planks);
		
		registerShapeless("sticky_piston", new ItemStack(Blocks.sticky_piston), Blocks.piston, ItemsAether.swet_ball);
		registerShapeless("sticky_piston", new ItemStack(Blocks.sticky_piston), Blocks.piston, ItemsAether.golden_swet_ball);
		registerShapeless("sticky_piston", new ItemStack(Blocks.sticky_piston), Blocks.piston, ItemsAether.purple_swet_ball);	
		}
		
		registerShapeless("skyroot_planks", new ItemStack(BlocksAether.skyroot_planks, 4), new ItemStack(BlocksAether.skyroot_log, 1, 0));
		registerShapeless("skyroot_planks", new ItemStack(BlocksAether.skyroot_planks, 4), new ItemStack(BlocksAether.skyroot_log, 1, 1));
		registerShapeless("golden_oak_planks", new ItemStack(BlocksAether.golden_oak_planks, 4), new ItemStack(BlocksAether.golden_oak_log, 1, 0));
		registerShapeless("golden_oak_planks", new ItemStack(BlocksAether.golden_oak_planks, 4), new ItemStack(BlocksAether.golden_oak_log, 1, 1));
		registerShapeless("wisproot_planks", new ItemStack(BlocksAether.wisproot_planks, 4), new ItemStack(BlocksAether.wisproot_log, 1, 0));
		registerShapeless("wisproot_planks", new ItemStack(BlocksAether.wisproot_planks, 4), new ItemStack(BlocksAether.wisproot_log, 1, 1));
		registerShapeless("greatroot_planks", new ItemStack(BlocksAether.greatroot_planks, 4), new ItemStack(BlocksAether.greatroot_log, 1, 0));
		registerShapeless("greatroot_planks", new ItemStack(BlocksAether.greatroot_planks, 4), new ItemStack(BlocksAether.greatroot_log, 1, 1));
		registerShapeless("void_planks", new ItemStack(BlocksAether.void_planks, 4), new ItemStack(BlocksAether.void_log, 1, 0));	
		registerShapeless("void_planks", new ItemStack(BlocksAether.void_planks, 4), new ItemStack(BlocksAether.void_log, 1, 1));		
		registerShapeless("divine_oak_planks", new ItemStack(BlocksAether.divine_oak_planks, 4), new ItemStack(BlocksAether.divine_oak_log, 1, 0));
		registerShapeless("divine_oak_planks", new ItemStack(BlocksAether.divine_oak_planks, 4), new ItemStack(BlocksAether.divine_oak_log, 1, 1));		
		registerShapeless("book_of_lore", new ItemStack(ItemsAether.lore_book), new ItemStack(Items.book), new ItemStack(ItemsAether.ambrosium_shard));
		registerShapeless("book_of_lore", new ItemStack(ItemsAether.lore_book), new ItemStack(Items.book), new ItemStack(Items.flint));
		registerShapeless("book_of_lore", new ItemStack(ItemsAether.lore_book), new ItemStack(Items.book), new ItemStack(Items.glowstone_dust));		
		registerShapeless("blueberry_seeds", new ItemStack(ItemsAether.blueberry_seeds, 2), new ItemStack(ItemsAether.blueberry, 1));
		registerShapeless("raspberry_seeds", new ItemStack(ItemsAether.raspberry_seeds, 2), new ItemStack(ItemsAether.raspberry, 1));
		registerShapeless("strawberry_seeds", new ItemStack(ItemsAether.strawberry_seeds, 2), new ItemStack(ItemsAether.strawberry, 1));
		registerShapeless("orange_seeds", new ItemStack(ItemsAether.orange_seeds, 2), new ItemStack(ItemsAether.orange, 1));
		registerShapeless("grape_seeds", new ItemStack(ItemsAether.grape_seeds, 2), new ItemStack(ItemsAether.grapes, 1));
		registerShapeless("blackberry_seeds", new ItemStack(ItemsAether.blackberry_seeds, 2), new ItemStack(ItemsAether.blackberry, 1));
		registerShapeless("zanite_and_cinerarium", new ItemStack(ItemsAether.zanite_and_cinerarium), new ItemStack(ItemsAether.cinerarium_rod), new ItemStack(ItemsAether.zanite_gemstone));
		registerShapeless("zanite_and_cyro", new ItemStack(ItemsAether.zanite_and_cyro), new ItemStack(ItemsAether.cyro_rod), new ItemStack(ItemsAether.zanite_gemstone));
		registerShapeless("uncooked_fruit_stew_bowl", new ItemStack(ItemsAether.uncooked_fruit_stew_bowl), new ItemStack(ItemsAether.holystone_bowl), new ItemStack(ItemsAether.raw_aerwhale), new ItemStack(ItemsAether.rainbow_strawberry), new ItemStack(ItemsAether.gummy_swet), new ItemStack(ItemsAether.blackberry), new ItemStack(ItemsAether.raspberry), new ItemStack(ItemsAether.orange), new ItemStack(ItemsAether.grapes), new ItemStack(ItemsAether.blueberry));
		
		if (Loader.isModLoaded("etfuturum")) {
		registerShapeless("netherite_gloves", new ItemStack(ItemsAether.netherite_gloves), new ItemStack(ItemsAether.diamond_gloves), new ItemStack(OtherModItems.netherite_ingot));
		registerShapeless("netherite_ring", new ItemStack(ItemsAether.netherite_ring), new ItemStack(ItemsAether.diamond_ring), new ItemStack(OtherModItems.netherite_ingot));
		registerShapeless("netherite_pendent", new ItemStack(ItemsAether.netherite_pendant), new ItemStack(ItemsAether.diamond_pendant), new ItemStack(OtherModItems.netherite_ingot));
		}
		
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 8), new ItemStack(BlocksAether.aercloud, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 1), new ItemStack(BlocksAether.aercloud_layer, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 2), new ItemStack(BlocksAether.aercloud_layer_2, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 3), new ItemStack(BlocksAether.aercloud_layer_3, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 4), new ItemStack(BlocksAether.aercloud_layer_4, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 5), new ItemStack(BlocksAether.aercloud_layer_5, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 6), new ItemStack(BlocksAether.aercloud_layer_6, 1, 0));
		registerShapeless("aercloud_globule", new ItemStack(ItemsAether.aercloud_globule, 7), new ItemStack(BlocksAether.aercloud_layer_7, 1, 0));
		registerShapeless("aercloud_layer", new ItemStack(BlocksAether.aercloud_layer, 1, 0), ItemsAether.aercloud_globule);
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 8), new ItemStack(BlocksAether.aercloud, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 1), new ItemStack(BlocksAether.aercloud_layer, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 2), new ItemStack(BlocksAether.aercloud_layer_2, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 3), new ItemStack(BlocksAether.aercloud_layer_3, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 4), new ItemStack(BlocksAether.aercloud_layer_4, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 5), new ItemStack(BlocksAether.aercloud_layer_5, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 6), new ItemStack(BlocksAether.aercloud_layer_6, 1, 1));
		registerShapeless("blue_aercloud_globule", new ItemStack(ItemsAether.blue_aercloud_globule, 7), new ItemStack(BlocksAether.aercloud_layer_7, 1, 1));	
		registerShapeless("blue_aercloud_layer", new ItemStack(BlocksAether.aercloud_layer, 1, 1), ItemsAether.blue_aercloud_globule);
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 8), new ItemStack(BlocksAether.aercloud, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 1), new ItemStack(BlocksAether.aercloud_layer, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 2), new ItemStack(BlocksAether.aercloud_layer_2, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 3), new ItemStack(BlocksAether.aercloud_layer_3, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 4), new ItemStack(BlocksAether.aercloud_layer_4, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 5), new ItemStack(BlocksAether.aercloud_layer_5, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 6), new ItemStack(BlocksAether.aercloud_layer_6, 1, 2));
		registerShapeless("golden_aercloud_globule", new ItemStack(ItemsAether.golden_aercloud_globule, 7), new ItemStack(BlocksAether.aercloud_layer_7, 1, 2));
		registerShapeless("golden_aercloud_layer", new ItemStack(BlocksAether.aercloud_layer, 1, 2), ItemsAether.golden_aercloud_globule);
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 8), new ItemStack(BlocksAether.purple_aercloud));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 1), new ItemStack(BlocksAether.aercloud_layer, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 2), new ItemStack(BlocksAether.aercloud_layer_2, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 3), new ItemStack(BlocksAether.aercloud_layer_3, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 4), new ItemStack(BlocksAether.aercloud_layer_4, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 5), new ItemStack(BlocksAether.aercloud_layer_5, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 6), new ItemStack(BlocksAether.aercloud_layer_6, 1, 3));
		registerShapeless("purple_aercloud_globule", new ItemStack(ItemsAether.purple_aercloud_globule, 7), new ItemStack(BlocksAether.aercloud_layer_7, 1, 3));	
		registerShapeless("purple_aercloud_layer", new ItemStack(BlocksAether.aercloud_layer, 1, 3), ItemsAether.purple_aercloud_globule);
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 8), new ItemStack(BlocksAether.green_aercloud));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 1), new ItemStack(BlocksAether.aercloud_layer, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 2), new ItemStack(BlocksAether.aercloud_layer_2, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 3), new ItemStack(BlocksAether.aercloud_layer_3, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 4), new ItemStack(BlocksAether.aercloud_layer_4, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 5), new ItemStack(BlocksAether.aercloud_layer_5, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 6), new ItemStack(BlocksAether.aercloud_layer_6, 1, 4));
		registerShapeless("green_aercloud_globule", new ItemStack(ItemsAether.green_aercloud_globule, 7), new ItemStack(BlocksAether.aercloud_layer_7, 1, 4));
		registerShapeless("green_aercloud_layer", new ItemStack(BlocksAether.aercloud_layer, 1, 4), ItemsAether.green_aercloud_globule);
		
		registerShapeless("book", new ItemStack(Items.book, 1), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(ItemsAether.zephyroo_leather));
		registerShapeless("book", new ItemStack(Items.book, 1), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(ItemsAether.thunderlo_leather));

	}

	private static void initializeRecipes() {
		register("nature_staff", new ItemStack(ItemsAether.nature_staff), "Y", "X", 'Y', ItemsAether.zanite_gemstone, 'X', ItemsAether.skyroot_stick);
		register("holystone_lever", new ItemStack(BlocksAether.holystone_lever), "Y", "X", 'Y', BlocksAether.holystone, 'X', ItemsAether.skyroot_stick);
		register("ethereal_torch", new ItemStack(BlocksAether.ethereal_torch, 5), " Y ", "YZY", "YXY", 'Y', BlocksAether.ambrosium_torch, 'X', ItemsAether.cyro_rod, 'Z', ItemsAether.zanite_gemstone);
		register("inferno_torch", new ItemStack(BlocksAether.inferno_torch, 5), " Y ", "YZY", "YXY", 'Y', BlocksAether.ambrosium_torch, 'X', ItemsAether.cinerarium_rod, 'Z', ItemsAether.zanite_gemstone);
		register("skyroot_stick", new ItemStack(ItemsAether.skyroot_stick, 4), "X", "X", 'X', BlocksAether.skyroot_planks);
		register("skyroot_stick", new ItemStack(ItemsAether.skyroot_stick, 4), "X", "X", 'X', BlocksAether.wisproot_planks);
		register("skyroot_stick", new ItemStack(ItemsAether.skyroot_stick, 4), "X", "X", 'X', BlocksAether.greatroot_planks);
		register("skyroot_stick", new ItemStack(ItemsAether.skyroot_stick, 4), "X", "X", 'X', BlocksAether.void_planks);		
		register("skyroot_trapdoor", new ItemStack(BlocksAether.skyroot_trapdoor, 2), "XXX", "XXX", 'X', BlocksAether.skyroot_planks);
		register("void_trapdoor", new ItemStack(BlocksAether.void_trapdoor, 2), "XXX", "XXX", 'X', BlocksAether.void_planks);
		register("wisproot_trapdoor", new ItemStack(BlocksAether.wisproot_trapdoor, 2), "XXX", "XXX", 'X', BlocksAether.wisproot_planks);
		register("greatroot_trapdoor", new ItemStack(BlocksAether.greatroot_trapdoor, 2), "XXX", "XXX", 'X', BlocksAether.greatroot_planks);
		register("golden_oak_trapdoor", new ItemStack(BlocksAether.golden_oak_trapdoor, 2), "XXX", "XXX", 'X', BlocksAether.golden_oak_planks);		
		register("zanite_trapdoor", new ItemStack(BlocksAether.zanite_trapdoor, 2), "XXX", "XXX", 'X', ItemsAether.zanite_gemstone);		
		register("glowing_icestone", new ItemStack(BlocksAether.glowing_icestone, 4), "XYX", "YXY", "XYX", 'X', BlocksAether.icestone, 'Y', Blocks.glowstone);		
		register("aether_enchantment_table", new ItemStack(BlocksAether.aether_enchantment_table, 1), new Object[] {" B ", "D#D", "###", '#', BlocksAether.aerogel, 'B', Items.book, 'D', BlocksAether.enchanted_gravitite});
		
		register("auralite_pillar", new ItemStack(BlocksAether.auralite_pillar, 8), " X ", " X ", " X ", 'X', BlocksAether.block_of_auralite);
		register("luminous_stone", new ItemStack(BlocksAether.luminous_stone, 4), "XYX", "YZY", "XYX", 'X', BlocksAether.block_of_auralite, 'Y', Blocks.glowstone, 'Z', ItemsAether.arkenium_fragement);		
		
		if (AetherConfig.BuddingAuraliteRecipe() == true) {
		register("budding_auralite", new ItemStack(BlocksAether.budding_auralite, 2), "VXV", "XYX", "VXV", 'Y', ItemsAether.arkenium_ingot, 'X', BlocksAether.block_of_auralite, 'V', BlocksAether.icestone);
		}
		
		register("crystallized_genesis_stone", new ItemStack(BlocksAether.crystallized_genesis_stone, 3), "YXY", "YXY", "YXY", 'X', ItemsAether.aceninum_shard, 'Y', BlocksAether.genesis_stone_2);		
		register("block_of_auralite", new ItemStack(BlocksAether.block_of_auralite), "XX", "XX", 'X', ItemsAether.auralite_crystal);
		register("block_of_aceninum", new ItemStack(BlocksAether.block_of_aceninum), "XXX", "XXX", "XXX", 'X', ItemsAether.aceninum_shard);
		register("holystone_brick", new ItemStack(BlocksAether.holystone_brick, 4), "XX", "XX", 'X', BlocksAether.holystone);
		register("holystone_brick2", new ItemStack(BlocksAether.holystone_brick, 4), "XX", "XX", 'X', new ItemStack(BlocksAether.holystone, 1, 1));
		register("aetheral_stone_bricks", new ItemStack(BlocksAether.aetheral_stone_bricks, 4), "XX", "XX", 'X', BlocksAether.aetheral_stone);
		register("aetheral_stone_bricks2", new ItemStack(BlocksAether.aetheral_stone_bricks, 4), "XX", "XX", 'X', new ItemStack(BlocksAether.aetheral_stone, 1, 1));
		register("enchanted_aetheral_stone_bricks", new ItemStack(BlocksAether.enchanted_aetheral_stone_bricks, 4), "XX", "XX", 'X', BlocksAether.enchanted_aetheral_stone);
		register("agiosite_bricks", new ItemStack(BlocksAether.agiosite_bricks, 4), "XX", "XX", 'X', new ItemStack(BlocksAether.agiosite, 1, 1));
		register("agiosite_bricks2", new ItemStack(BlocksAether.agiosite_bricks, 4), "XX", "XX", 'X', BlocksAether.agiosite);
		register("carved_caelestia_stone", new ItemStack(BlocksAether.carved_caelestia_stone, 4), "XX", "XX", 'X', BlocksAether.caelestia_stone);
		register("carved_caelestia_stone2", new ItemStack(BlocksAether.carved_caelestia_stone, 4), "XX", "XX", 'X', new ItemStack(BlocksAether.caelestia_stone, 1, 1));
		register("deific_bricks", new ItemStack(BlocksAether.deific_bricks, 4), "XX", "XX", 'X', BlocksAether.deific);
		register("deific_bricks2", new ItemStack(BlocksAether.deific_bricks, 4), "XX", "XX", 'X', new ItemStack(BlocksAether.deific, 1, 1));
		register("enchanted_deific", new ItemStack(BlocksAether.enchanted_deific_bricks, 4), "XX", "XX", 'X', BlocksAether.enchanted_deific);
		register("enchanted_agiosite_bricks", new ItemStack(BlocksAether.enchanted_agiosite_bricks, 4), "XX", "XX", 'X', BlocksAether.enchanted_agiosite);
		register("enchanted_holystone_bricks", new ItemStack(BlocksAether.enchanted_holystone_bricks, 4), "XX", "XX", 'X', BlocksAether.enchanted_holystone);
		register("cer_scales_block", new ItemStack(BlocksAether.cer_scales_block), "XX", "XX", 'X', ItemsAether.crystal_dragon_scales);
		register("zanite_block", new ItemStack(BlocksAether.zanite_block), "XXX", "XXX", "XXX", 'X', ItemsAether.zanite_gemstone);
		register("ambrosium_block", new ItemStack(BlocksAether.ambrosium_block), "XXX", "XXX", "XXX", 'X', ItemsAether.ambrosium_shard);
		register("continuum_block", new ItemStack(BlocksAether.continuum_block), "XXX", "XXX", "XXX", 'X', ItemsAether.continuum_gemstone);
		register("arkenium_block", new ItemStack(BlocksAether.arkenium_block), "XXX", "XXX", "XXX", 'X', ItemsAether.arkenium_ingot);
		register("divineral_block", new ItemStack(BlocksAether.divineral_block), "XXX", "XXX", "XXX", 'X', ItemsAether.divineral_ingot);
		register("zanite_gemstone", new ItemStack(ItemsAether.zanite_gemstone, 9), "X", 'X', BlocksAether.zanite_block);
		register("ambrosium_shard", new ItemStack(ItemsAether.ambrosium_shard, 9), "X", 'X', BlocksAether.ambrosium_block);
		register("continuum_gemstone", new ItemStack(ItemsAether.continuum_gemstone, 9), "X", 'X', BlocksAether.continuum_block);
		register("valkyrie_nugget", new ItemStack(ItemsAether.valkyrie_nugget, 9), "X", 'X', ItemsAether.valkyrie_ingot);
		register("valkyrie_ingot", new ItemStack(ItemsAether.valkyrie_ingot), "XXX", "XXX", "XXX", 'X', ItemsAether.valkyrie_nugget);
		register("arkenium_chunk", new ItemStack(ItemsAether.arkenium_chunk), "XX", "XX", 'X', new ItemStack(ItemsAether.arkenium_fragement));		
		register("icestone_crystal", new ItemStack(ItemsAether.icestone_crystal, 9), "X", 'X', BlocksAether.icestone);
		register("icestone", new ItemStack(BlocksAether.icestone), "XXX", "XXX", "XXX", 'X', ItemsAether.icestone_crystal);		
		register("empyrean_gemstone", new ItemStack(ItemsAether.empyrean_gemstone, 9), "X", 'X', BlocksAether.empyrean_block);
		register("empyrean_block", new ItemStack(BlocksAether.empyrean_block), "XXX", "XXX", "XXX", 'X', ItemsAether.empyrean_gemstone);		
		register("charged_empyrean_gemstone", new ItemStack(ItemsAether.charged_empyrean_gemstone, 9), "X", 'X', BlocksAether.charged_empyrean_block);
		register("charged_empyrean_block", new ItemStack(BlocksAether.charged_empyrean_block), "XXX", "XXX", "XXX", 'X', ItemsAether.charged_empyrean_gemstone);
		
		register("divine_enchantment_table", new ItemStack(BlocksAether.divine_enchantment_table), "WUW", "XZX", "YVY", 'Z', new ItemStack(ItemsAether.notched_core), 'V', new ItemStack(BlocksAether.reinforced_arkenium_block), 'Y', new ItemStack(BlocksAether.aerogel), 'X', ItemsAether.divineral_ingot, 'W', ItemsAether.divine_essence, 'U', ItemsAether.lore_book);
		
		register("charged_tempest_core", new ItemStack(ItemsAether.charged_tempest_core), " Z ", "YXY", " Z ", 'X', new ItemStack(ItemsAether.swet_ball), 'Y', new ItemStack(ItemsAether.aerca_powder), 'Z', new ItemStack(ItemsAether.tempest_core));
		register("charged_tempest_core", new ItemStack(ItemsAether.charged_tempest_core), " Z ", "YXY", " Z ", 'X', new ItemStack(ItemsAether.golden_swet_ball), 'Y', new ItemStack(ItemsAether.aerca_powder), 'Z', new ItemStack(ItemsAether.tempest_core));
		register("charged_tempest_core", new ItemStack(ItemsAether.charged_tempest_core), " Z ", "YXY", " Z ", 'X', new ItemStack(ItemsAether.purple_swet_ball), 'Y', new ItemStack(ItemsAether.aerca_powder), 'Z', new ItemStack(ItemsAether.tempest_core));
		
		register("gunpowder", new ItemStack(Items.gunpowder), "XXX", "XXX", "XXX", 'X', ItemsAether.uligo_swet_ball);
		
		register("cracked_empyrean_gemstone", new ItemStack(ItemsAether.cracked_empyrean_gemstone), " X ", "XYX", " Z ", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.empyrean_gemstone, 'Z', ItemsAether.tempest_core);
		
		//Scaled Recipes
		register("scaled_zanite_helmet", new ItemStack(ItemsAether.scaled_zanite_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_zanite_chestplate", new ItemStack(ItemsAether.scaled_zanite_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_zanite_leggings", new ItemStack(ItemsAether.scaled_zanite_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_zanite_boots", new ItemStack(ItemsAether.scaled_zanite_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_gravitite_helmet", new ItemStack(ItemsAether.scaled_gravitite_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_gravitite_chestplate", new ItemStack(ItemsAether.scaled_gravitite_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_gravitite_leggings", new ItemStack(ItemsAether.scaled_gravitite_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_gravitite_boots", new ItemStack(ItemsAether.scaled_gravitite_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_arkenium_helmet", new ItemStack(ItemsAether.scaled_arkenium_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_arkenium_chestplate", new ItemStack(ItemsAether.scaled_arkenium_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_arkenium_leggings", new ItemStack(ItemsAether.scaled_arkenium_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_arkenium_boots", new ItemStack(ItemsAether.scaled_arkenium_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_continuum_helmet", new ItemStack(ItemsAether.scaled_continuum_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_continuum_chestplate", new ItemStack(ItemsAether.scaled_continuum_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_continuum_leggings", new ItemStack(ItemsAether.scaled_continuum_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_continuum_boots", new ItemStack(ItemsAether.scaled_continuum_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_phoenix_helmet", new ItemStack(ItemsAether.scaled_phoenix_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_phoenix_chestplate", new ItemStack(ItemsAether.scaled_phoenix_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_phoenix_leggings", new ItemStack(ItemsAether.scaled_phoenix_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_phoenix_boots", new ItemStack(ItemsAether.scaled_phoenix_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_obsidian_helmet", new ItemStack(ItemsAether.scaled_obsidian_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_obsidian_chestplate", new ItemStack(ItemsAether.scaled_obsidian_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_obsidian_leggings", new ItemStack(ItemsAether.scaled_obsidian_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_obsidian_boots", new ItemStack(ItemsAether.scaled_obsidian_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_boots, 'V', ItemsAether.zephyroo_leather);	
		register("scaled_neptune_helmet", new ItemStack(ItemsAether.scaled_neptune_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_neptune_chestplate", new ItemStack(ItemsAether.scaled_neptune_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_neptune_leggings", new ItemStack(ItemsAether.scaled_neptune_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_neptune_boots", new ItemStack(ItemsAether.scaled_neptune_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_valkyrie_helmet", new ItemStack(ItemsAether.scaled_valkyrie_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_valkyrie_chestplate", new ItemStack(ItemsAether.scaled_valkyrie_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_valkyrie_leggings", new ItemStack(ItemsAether.scaled_valkyrie_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_valkyrie_boots", new ItemStack(ItemsAether.scaled_valkyrie_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_boots, 'V', ItemsAether.zephyroo_leather);
		register("scaled_sentry_boots", new ItemStack(ItemsAether.scaled_sentry_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.sentry_boots, 'V', ItemsAether.zephyroo_leather);
		register("scaled_agility_boots", new ItemStack(ItemsAether.scaled_agility_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.agility_boots, 'V', ItemsAether.zephyroo_leather);		
		register("scaled_elysian_helmet", new ItemStack(ItemsAether.scaled_elysian_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_helmet, 'V', ItemsAether.zephyroo_leather);
		register("scaled_elysian_chestplate", new ItemStack(ItemsAether.scaled_elysian_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_chestplate, 'V', ItemsAether.zephyroo_leather);
		register("scaled_elysian_leggings", new ItemStack(ItemsAether.scaled_elysian_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_leggings, 'V', ItemsAether.zephyroo_leather);
		register("scaled_elysian_boots", new ItemStack(ItemsAether.scaled_elysian_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_boots, 'V', ItemsAether.zephyroo_leather);
		
		
		register("scaled_zanite_helmet", new ItemStack(ItemsAether.scaled_zanite_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_zanite_chestplate", new ItemStack(ItemsAether.scaled_zanite_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_zanite_leggings", new ItemStack(ItemsAether.scaled_zanite_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_zanite_boots", new ItemStack(ItemsAether.scaled_zanite_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.zanite_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_gravitite_helmet", new ItemStack(ItemsAether.scaled_gravitite_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_gravitite_chestplate", new ItemStack(ItemsAether.scaled_gravitite_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_gravitite_leggings", new ItemStack(ItemsAether.scaled_gravitite_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_gravitite_boots", new ItemStack(ItemsAether.scaled_gravitite_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.gravitite_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_arkenium_helmet", new ItemStack(ItemsAether.scaled_arkenium_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_arkenium_chestplate", new ItemStack(ItemsAether.scaled_arkenium_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_arkenium_leggings", new ItemStack(ItemsAether.scaled_arkenium_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_arkenium_boots", new ItemStack(ItemsAether.scaled_arkenium_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.arkenium_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_continuum_helmet", new ItemStack(ItemsAether.scaled_continuum_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_continuum_chestplate", new ItemStack(ItemsAether.scaled_continuum_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_continuum_leggings", new ItemStack(ItemsAether.scaled_continuum_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_continuum_boots", new ItemStack(ItemsAether.scaled_continuum_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.continuum_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_phoenix_helmet", new ItemStack(ItemsAether.scaled_phoenix_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_phoenix_chestplate", new ItemStack(ItemsAether.scaled_phoenix_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_phoenix_leggings", new ItemStack(ItemsAether.scaled_phoenix_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_phoenix_boots", new ItemStack(ItemsAether.scaled_phoenix_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.phoenix_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_obsidian_helmet", new ItemStack(ItemsAether.scaled_obsidian_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_obsidian_chestplate", new ItemStack(ItemsAether.scaled_obsidian_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_obsidian_leggings", new ItemStack(ItemsAether.scaled_obsidian_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_obsidian_boots", new ItemStack(ItemsAether.scaled_obsidian_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.obsidian_boots, 'V', ItemsAether.thunderlo_leather);
		register("scaled_neptune_helmet", new ItemStack(ItemsAether.scaled_neptune_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_neptune_chestplate", new ItemStack(ItemsAether.scaled_neptune_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_neptune_leggings", new ItemStack(ItemsAether.scaled_neptune_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_neptune_boots", new ItemStack(ItemsAether.scaled_neptune_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.neptune_boots, 'V', ItemsAether.thunderlo_leather);	
		register("scaled_valkyrie_helmet", new ItemStack(ItemsAether.scaled_valkyrie_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_valkyrie_chestplate", new ItemStack(ItemsAether.scaled_valkyrie_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_valkyrie_leggings", new ItemStack(ItemsAether.scaled_valkyrie_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_valkyrie_boots", new ItemStack(ItemsAether.scaled_valkyrie_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.valkyrie_boots, 'V', ItemsAether.thunderlo_leather);
		register("scaled_sentry_boots", new ItemStack(ItemsAether.scaled_sentry_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.sentry_boots, 'V', ItemsAether.thunderlo_leather);
		register("scaled_agility_boots", new ItemStack(ItemsAether.scaled_agility_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.agility_boots, 'V', ItemsAether.thunderlo_leather);		
		register("scaled_elysian_helmet", new ItemStack(ItemsAether.scaled_elysian_helmet), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_helmet, 'V', ItemsAether.thunderlo_leather);
		register("scaled_elysian_chestplate", new ItemStack(ItemsAether.scaled_elysian_chestplate), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_chestplate, 'V', ItemsAether.thunderlo_leather);
		register("scaled_elysian_leggings", new ItemStack(ItemsAether.scaled_elysian_leggings), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_leggings, 'V', ItemsAether.thunderlo_leather);
		register("scaled_elysian_boots", new ItemStack(ItemsAether.scaled_elysian_boots), "VXX", "XYX", "XXV", 'X', ItemsAether.zarnillys_scales, 'Y', ItemsAether.elysian_boots, 'V', ItemsAether.thunderlo_leather);
		
		//Reinforced Recipes
		register("reinforced_elysian_ring", new ItemStack(ItemsAether.reinforced_elysian_ring), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.elysian_ring, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);		
		register("reinforced_bone_ring", new ItemStack(ItemsAether.reinforced_bone_ring), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.bone_ring, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		register("reinforced_valkyrie_ring", new ItemStack(ItemsAether.reinforced_valkyrie_ring), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.valkyrie_ring, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);		
		register("reinforced_auralite_pendant", new ItemStack(ItemsAether.reinforced_auralite_pendant), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.auralite_pendant, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		register("reinforced_haste_ring", new ItemStack(ItemsAether.reinforced_haste_ring), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.haste_ring, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		register("reinforced_regeneration_stone", new ItemStack(ItemsAether.reinforced_regeneration_stone), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.regeneration_stone, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		register("reinforced_iron_bubble", new ItemStack(ItemsAether.reinforced_iron_bubble), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.iron_bubble, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		register("reinforced_golden_feather", new ItemStack(ItemsAether.reinforced_golden_feather), "VX ", "XZY", " YW", 'V', ItemsAether.charged_tempest_core, 'X', ItemsAether.aceninum_shard, 'Z', ItemsAether.golden_feather, 'Y', ItemsAether.charged_empyrean_gemstone, 'W', ItemsAether.zanite_gemstone);
		
		//Tipped Recipes
		register("tipped_skyroot_sword", new ItemStack(ItemsAether.tipped_skyroot_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.skyroot_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_skyroot_pickaxe", new ItemStack(ItemsAether.tipped_skyroot_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.skyroot_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_skyroot_axe", new ItemStack(ItemsAether.tipped_skyroot_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.skyroot_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_skyroot_shovel", new ItemStack(ItemsAether.tipped_skyroot_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.skyroot_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_holystone_sword", new ItemStack(ItemsAether.tipped_holystone_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.holystone_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_holystone_pickaxe", new ItemStack(ItemsAether.tipped_holystone_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.holystone_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_holystone_axe", new ItemStack(ItemsAether.tipped_holystone_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.holystone_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_holystone_shovel", new ItemStack(ItemsAether.tipped_holystone_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.holystone_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);	
		register("tipped_zanite_sword", new ItemStack(ItemsAether.tipped_zanite_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.zanite_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_zanite_pickaxe", new ItemStack(ItemsAether.tipped_zanite_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.zanite_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_zanite_axe", new ItemStack(ItemsAether.tipped_zanite_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.zanite_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_zanite_shovel", new ItemStack(ItemsAether.tipped_zanite_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.zanite_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_arkenium_sword", new ItemStack(ItemsAether.tipped_arkenium_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.arkenium_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_arkenium_pickaxe", new ItemStack(ItemsAether.tipped_arkenium_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.arkenium_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_arkenium_axe", new ItemStack(ItemsAether.tipped_arkenium_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.arkenium_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_arkenium_shovel", new ItemStack(ItemsAether.tipped_arkenium_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.arkenium_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_continuum_sword", new ItemStack(ItemsAether.tipped_continuum_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.continuum_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_continuum_pickaxe", new ItemStack(ItemsAether.tipped_continuum_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.continuum_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_continuum_axe", new ItemStack(ItemsAether.tipped_continuum_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.continuum_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_continuum_shovel", new ItemStack(ItemsAether.tipped_continuum_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.continuum_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_valkyrie_lance", new ItemStack(ItemsAether.tipped_valkyrie_lance), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.valkyrie_lance, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_valkyrie_pickaxe", new ItemStack(ItemsAether.tipped_valkyrie_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.valkyrie_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_valkyrie_axe", new ItemStack(ItemsAether.tipped_valkyrie_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.valkyrie_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_valkyrie_shovel", new ItemStack(ItemsAether.tipped_valkyrie_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.valkyrie_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_gravitite_sword", new ItemStack(ItemsAether.tipped_gravitite_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.gravitite_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_gravitite_pickaxe", new ItemStack(ItemsAether.tipped_gravitite_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.gravitite_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_gravitite_axe", new ItemStack(ItemsAether.tipped_gravitite_axe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.gravitite_axe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_gravitite_shovel", new ItemStack(ItemsAether.tipped_gravitite_shovel), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.gravitite_shovel, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);	
		
		register("tipped_battle_sentry_hammer", new ItemStack(ItemsAether.tipped_battle_sentry_hammer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.battle_sentry_hammer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_notch_hammer", new ItemStack(ItemsAether.tipped_notch_hammer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.notch_hammer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_jeb_hammer", new ItemStack(ItemsAether.tipped_jeb_hammer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.jeb_hammer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_vampire_blade", new ItemStack(ItemsAether.tipped_vampire_blade), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.vampire_blade, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_lightning_sword", new ItemStack(ItemsAether.tipped_lightning_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.lightning_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_holy_sword", new ItemStack(ItemsAether.tipped_holy_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.holy_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_flaming_sword", new ItemStack(ItemsAether.tipped_flaming_sword), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.flaming_sword, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_phoenix_bow", new ItemStack(ItemsAether.tipped_phoenix_bow), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.phoenix_bow, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		
		register("tipped_divineral_sword", new ItemStack(ItemsAether.tipped_divineral_sword), "XVX", "XYX", "XXX", 'X', BlocksAether.zanite_block, 'Y', ItemsAether.divineral_sword, 'V', BlocksAether.enchanted_gravitite);		
		register("tipped_overworld_slayer", new ItemStack(ItemsAether.tipped_overworld_slayer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.overworld_slayer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_nether_slayer", new ItemStack(ItemsAether.tipped_nether_slayer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.nether_slayer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_ender_slayer", new ItemStack(ItemsAether.tipped_ender_slayer), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.ender_slayer, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_dragon_bane", new ItemStack(ItemsAether.tipped_dragon_bane), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.dragon_bane, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);
		register("tipped_notched_pickaxe", new ItemStack(ItemsAether.tipped_notched_pickaxe), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.notched_pickaxe, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);	
		register("tipped_cyro_bow", new ItemStack(ItemsAether.tipped_cyro_bow), "VXZ", "XYX", "ZXX", 'X', ItemsAether.auralite_crystal, 'Y', ItemsAether.cyro_bow, 'V', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.arkenium_ingot);		

		
		register("arkenium_ingot", new ItemStack(ItemsAether.arkenium_ingot, 9), "X", 'X', BlocksAether.arkenium_block);
		register("divineral_ingot", new ItemStack(ItemsAether.divineral_ingot, 9), "X", 'X', BlocksAether.divineral_block);
		register("divineral_nugget", new ItemStack(ItemsAether.divineral_nugget, 9), "X", 'X', ItemsAether.enchanted_divineral);
		register("golden_dart_shooter", new ItemStack(ItemsAether.dart_shooter, 1), "X  ", " Y ", "  Y", 'X', ItemsAether.golden_amber, 'Y', BlocksAether.skyroot_planks);
		register("golden_dart", new ItemStack(ItemsAether.dart, 2), "X", "Y", "Z", 'X', Items.feather, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.golden_amber);
		register("golden_dart", new ItemStack(ItemsAether.dart, 4), "X", "Y", "Z", 'X', ItemsAether.cockatrice_feather, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.golden_amber);
		register("arrow", new ItemStack(Items.arrow, 4), "X", "Y", "Z", 'X', ItemsAether.cockatrice_feather, 'Y', Items.stick, 'Z', Items.flint);
		register("poison_dart", new ItemStack(ItemsAether.dart, 8, 1), "XXX", "XYX", "XXX", 'X', new ItemStack(ItemsAether.dart, 1), 'Y', new ItemStack(ItemsAether.skyroot_bucket, 1, 2));
		register("incubator", new ItemStack(BlocksAether.incubator), "XXX", "XZX", "XXX", 'X', BlocksAether.holystone, 'Z', BlocksAether.ambrosium_torch);
		register("freezer", new ItemStack(BlocksAether.freezer), "XXX", "XYX", "ZZZ", 'X', BlocksAether.holystone, 'Y', BlocksAether.icestone, 'Z', BlocksAether.skyroot_planks);	
		register("zephyroo_saddle", new ItemStack(ItemsAether.zephyroo_saddle), "XXX", "XYX", "ZZZ", 'X', ItemsAether.zephyroo_leather, 'Y', Items.saddle, 'Z', ItemsAether.zanite_gemstone);	
		register("enchanter", new ItemStack(BlocksAether.enchanter), "XXX", "XYX", "XXX", 'X', BlocksAether.holystone, 'Y', ItemsAether.zanite_gemstone);
		register("amplifier", new ItemStack(BlocksAether.amplifier), "XVX", "UYU", "XZX", 'X', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.divineral_ingot, 'Z', BlocksAether.enchanted_holystone, 'V', BlocksAether.hellfire_stone, 'U', BlocksAether.zanite_block);
		register("amplifier", new ItemStack(BlocksAether.amplifier), "XVX", "UYU", "XZX", 'X', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.divineral_ingot, 'Z', BlocksAether.enchanted_holystone, 'V', BlocksAether.divine_hellfire_stone, 'U', BlocksAether.zanite_block);
		register("furnace", new ItemStack(Blocks.furnace), "XXX", "X X", "XXX", 'X', BlocksAether.holystone);
		register("skyroot_ladder", new ItemStack(BlocksAether.skyroot_ladder, 4), "X X", "XXX", "X X", 'X', ItemsAether.skyroot_stick);
		register("jukebox", new ItemStack(Blocks.jukebox), "XXX", "XYX", "XXX", 'X', BlocksAether.skyroot_planks, 'Y', BlocksAether.enchanted_gravitite);
		register("oak_door", new ItemStack(Items.wooden_door, 3), "XX", "XX", "XX", 'X', BlocksAether.skyroot_planks);
		register("sign", new ItemStack(Items.sign, 3), "XXX", "XXX", " Y ", 'X', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("ambrosium_torch", new ItemStack(BlocksAether.ambrosium_torch, 3), "Z", "Y", 'Z', ItemsAether.ambrosium_shard, 'Y', ItemsAether.skyroot_stick);
		register("lead", new ItemStack(Items.lead, 2), "YY ", "YX ", "  Y", 'Y', Items.string, 'X', ItemsAether.swet_ball);		
		register("arkenium_apple", new ItemStack(ItemsAether.arkenium_apple), "XXX", "XYX", "XXX", 'X', ItemsAether.arkenium_ingot, 'Y', ItemsAether.white_apple);
		
		register("arkenium_nugget", new ItemStack(ItemsAether.arkenium_nugget, 9), "X", 'X', ItemsAether.arkenium_ingot);
		register("continuum_nugget", new ItemStack(ItemsAether.continuum_nugget, 9), "X", 'X', ItemsAether.continuum_gemstone);
		register("zanite_nugget", new ItemStack(ItemsAether.zanite_nugget, 9), "X", 'X', ItemsAether.zanite_gemstone);
		register("gravitite_nugget", new ItemStack(ItemsAether.gravitite_nugget, 9), "X", 'X', ItemsAether.raw_gravitite);
		
		register("arkenium_ingot", new ItemStack(ItemsAether.arkenium_ingot), "XXX", "XXX", "XXX", 'X', ItemsAether.arkenium_nugget);
		register("continuum_gemstone", new ItemStack(ItemsAether.continuum_gemstone), "XXX", "XXX", "XXX", 'X', ItemsAether.continuum_nugget);
		register("zanite_gemstone", new ItemStack(ItemsAether.zanite_gemstone), "XXX", "XXX", "XXX", 'X', ItemsAether.zanite_nugget);
		register("enchanted_gravitite", new ItemStack(BlocksAether.enchanted_gravitite), "XXX", "XXX", "XXX", 'X', ItemsAether.gravitite_nugget);
		register("enchanted_divineral", new ItemStack(ItemsAether.enchanted_divineral), "XXX", "XXX", "XXX", 'X', ItemsAether.divineral_nugget);
				
		if (AetherConfig.DivineralRecipeHardmore() == true && AetherConfig.NetheriteRequiredInDivineralIngot() == false) {
		register("divineral_ingot", new ItemStack(ItemsAether.divineral_ingot), "VXZ", "XYX", "ZXV", 'X', ItemsAether.enchanted_divineral, 'Y', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.charged_tempest_core,  'V', ItemsAether.divine_essence);
		}	
		if (AetherConfig.DivineralRecipeHardmore() == false && AetherConfig.NetheriteRequiredInDivineralIngot() == false) {
			register("divineral_ingot", new ItemStack(ItemsAether.divineral_ingot), " V ", "XYX", " Z ", 'X', ItemsAether.enchanted_divineral, 'Y', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.charged_tempest_core,  'V', ItemsAether.divine_essence);
		}
							
		if (AetherConfig.DivineralRecipeHardmore() == true && AetherConfig.NetheriteRequiredInDivineralIngot() == true && Loader.isModLoaded("etfuturum")) {
		register("divineral_ingot", new ItemStack(ItemsAether.divineral_ingot), "VXZ", "XWX", "YXV", 'X', ItemsAether.enchanted_divineral, 'W', OtherModItems.netherite_ingot, 'Y', BlocksAether.enchanted_gravitite, 'Z', ItemsAether.charged_tempest_core,  'V', ItemsAether.divine_essence);
		}
		if (AetherConfig.DivineralRecipeHardmore() == false && AetherConfig.NetheriteRequiredInDivineralIngot() == true && Loader.isModLoaded("etfuturum")) {
		register("divineral_ingot", new ItemStack(ItemsAether.divineral_ingot), " V ", "XYX", " Z ", 'X', ItemsAether.enchanted_divineral, 'Y', OtherModItems.netherite_ingot, 'Z', ItemsAether.charged_tempest_core,  'V', ItemsAether.divine_essence);
		}
		
		register("elysian_offering", new ItemStack(ItemsAether.elysian_offering), "XZX", "ZYZ", "XZX", 'X', BlocksAether.cer_scales_block, 'Y', ItemsAether.elysian_core, 'Z', ItemsAether.divineral_ingot);
		
		register("golden_oak_bookshelf", new ItemStack(BlocksAether.golden_oak_bookshelf), "XZX", "UYV", "XWX", 'X', BlocksAether.golden_oak_planks, 'Y', BlocksAether.skyroot_bookshelf, 'Z', ItemsAether.cinerarium_rod, 'W', ItemsAether.thunderlo_horn, 'V', ItemsAether.zanite_gemstone, 'U', ItemsAether.empyrean_gemstone);	
		
		if (AetherConfig.RedstoneItemsCraftable() == true) {
		register("repeater", new ItemStack(Items.repeater), "YZY", "XXX", 'Y', Blocks.redstone_torch, 'X', BlocksAether.enchanted_holystone, 'Z', Items.redstone);
		register("piston", new ItemStack(Blocks.piston), "WWW", "XZX", "XYX", 'X', BlocksAether.holystone, 'Y', Items.redstone, 'Z', ItemsAether.arkenium_ingot, 'W', BlocksAether.greatroot_planks);
		register("piston2", new ItemStack(Blocks.piston), "WWW", "XZX", "XYX", 'X', new ItemStack(BlocksAether.holystone, 1, 1), 'Y', Items.redstone, 'Z', ItemsAether.arkenium_ingot, 'W', BlocksAether.greatroot_planks);
		register("noteblock", new ItemStack(Blocks.noteblock), "XXX", "XZX", "XXX", 'X', BlocksAether.greatroot_planks, 'Z', Items.redstone);
		register("dispenser", new ItemStack(Blocks.dispenser), "XXX", "XZX", "XYX", 'X', new ItemStack(BlocksAether.holystone, 1, 1), 'Z', ItemsAether.dart_shooter, 'Y', Items.redstone);
		register("dispenser2", new ItemStack(Blocks.dispenser), "XXX", "XZX", "XYX", 'X', BlocksAether.holystone, 'Z', ItemsAether.dart_shooter, 'Y', Items.redstone);
		register("dropper", new ItemStack(Blocks.dropper), "XXX", "X X", "XYX", 'X', new ItemStack(BlocksAether.holystone, 1, 1), 'Y', Items.redstone);
		register("dropper2", new ItemStack(Blocks.dropper), "XXX", "X X", "XYX", 'X', BlocksAether.holystone, 'Y', Items.redstone);				
		register("tripwire_hook", new ItemStack(Blocks.tripwire_hook), "Z  ", "Y  ", "X  ", 'X', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.zanite_gemstone);		
		register("tripwire_hook2", new ItemStack(Blocks.tripwire_hook), "Z  ", "Y  ", "X  ", 'X', BlocksAether.greatroot_planks, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.zanite_gemstone);		
		register("tripwire_hook3", new ItemStack(Blocks.tripwire_hook), "Z  ", "Y  ", "X  ", 'X', BlocksAether.wisproot_planks, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.zanite_gemstone);		
		register("tripwire_hook4", new ItemStack(Blocks.tripwire_hook), "Z  ", "Y  ", "X  ", 'X', BlocksAether.void_planks, 'Y', ItemsAether.skyroot_stick, 'Z', ItemsAether.zanite_gemstone);		
		register("hopper", new ItemStack(Blocks.hopper), "X X", "XYX", " Z ", 'X', ItemsAether.zanite_gemstone, 'Y', BlocksAether.skyroot_chest, 'Z', ItemsAether.arkenium_ingot);				
		register("daylight_detector", new ItemStack(Blocks.daylight_detector), "XXX", "YYY", "ZZZ", 'X', BlocksAether.quicksoil_glass, 'Y', ItemsAether.auralite_crystal, 'Z', BlocksAether.skyroot_slab);		
		register("daylight_detector2", new ItemStack(Blocks.daylight_detector), "XXX", "YYY", "ZZZ", 'X', BlocksAether.quicksoil_glass, 'Y', ItemsAether.auralite_crystal, 'Z', BlocksAether.wisproot_slab);	
		register("daylight_detector3", new ItemStack(Blocks.daylight_detector), "XXX", "YYY", "ZZZ", 'X', BlocksAether.quicksoil_glass, 'Y', ItemsAether.auralite_crystal, 'Z', BlocksAether.greatroot_slab);	
		register("daylight_detector4", new ItemStack(Blocks.daylight_detector), "XXX", "YYY", "ZZZ", 'X', BlocksAether.quicksoil_glass, 'Y', ItemsAether.auralite_crystal, 'Z', BlocksAether.void_slab);			
		register("comparator", new ItemStack(Items.comparator), " X ", "XYX", "ZZZ", 'X', Blocks.redstone_torch, 'Y', ItemsAether.auralite_crystal, 'Z', BlocksAether.enchanted_holystone);	
		}
		
		if (AetherConfig.BrewingItemsCraftable() == true) {
		register("brewing_stand", new ItemStack(Items.brewing_stand), " Y ", "XXX", 'Y', ItemsAether.cinerarium_rod, 'X', BlocksAether.enchanted_holystone);	
		register("brewing_stand", new ItemStack(Items.brewing_stand), " Y ", "XXX", 'Y', Items.blaze_rod, 'X', BlocksAether.enchanted_holystone);
		register("cauldron", new ItemStack(Items.cauldron), "X X", "X X", "XXX", 'X', ItemsAether.zanite_gemstone);	
		register("glass_bottle", new ItemStack(Items.glass_bottle, 3), "Y Y", " Y ", 'Y', BlocksAether.frozen_quicksoil_glass);	
		}
		
		
		register("aether_tnt", new ItemStack(BlocksAether.aether_tnt), "XVX", "VYV", "XVX", 'X', Items.gunpowder, 'Y', ItemsAether.zanite_gemstone, 'V', new ItemStack(BlocksAether.quicksoil, 1, 1));		
		register("aether_tnt", new ItemStack(BlocksAether.aether_tnt), "XVX", "VYV", "XVX", 'X', Items.gunpowder, 'Y', ItemsAether.zanite_gemstone, 'V', new ItemStack(BlocksAether.quicksoil, 1, 1));
		
		if (AetherConfig.disableParachutes() == false) {
		register("cloud_parachute", new ItemStack(ItemsAether.cloud_parachute, 1), "XX", "XX", 'X', new ItemStack(BlocksAether.aercloud, 1));
		register("blue_parachute", new ItemStack(ItemsAether.blue_parachute, 1), "XX", "XX", 'X', new ItemStack(BlocksAether.aercloud, 1, 1));
		register("golden_parachute", new ItemStack(ItemsAether.golden_parachute, 1), "XX", "XX", 'X', new ItemStack(BlocksAether.aercloud, 1, 2));
		}
		
		register("reinforced_arkenium_block", new ItemStack(BlocksAether.reinforced_arkenium_block, 2), "ZXW", "XYX", "WXZ", 'X', BlocksAether.arkenium_block, 'Y', BlocksAether.enchanted_gravitite, 'Z', BlocksAether.zanite_block, 'W', BlocksAether.block_of_auralite);
		
		register("saddle", new ItemStack(Items.saddle, 1), "XXX", "XZX", 'X', Items.leather, 'Z', Items.string);
		register("skyroot_chest", new ItemStack(BlocksAether.skyroot_chest, 1), "XXX", "X X", "XXX", 'X', BlocksAether.skyroot_planks);
		register("skyroot_chest", new ItemStack(BlocksAether.skyroot_chest, 1), "XXX", "X X", "XXX", 'X', BlocksAether.golden_oak_planks);
		register("skyroot_chest", new ItemStack(BlocksAether.skyroot_chest, 1), "XXX", "X X", "XXX", 'X', BlocksAether.wisproot_planks);
		register("skyroot_chest", new ItemStack(BlocksAether.skyroot_chest, 1), "XXX", "X X", "XXX", 'X', BlocksAether.greatroot_planks);
		register("skyroot_chest", new ItemStack(BlocksAether.skyroot_chest, 1), "XXX", "X X", "XXX", 'X', BlocksAether.void_planks);
		register("holystone_bowl", new ItemStack(ItemsAether.holystone_bowl, 1, 0), "Z Z", " Z ", 'Z', new ItemStack(BlocksAether.holystone, 1, 0));
		register("holystone_bowl", new ItemStack(ItemsAether.holystone_bowl, 1, 0), "Z Z", " Z ", 'Z', new ItemStack(BlocksAether.holystone, 1, 1));
		register("skyroot_bucket", new ItemStack(ItemsAether.skyroot_bucket, 1, 0), "Z Z", " Z ", 'Z', BlocksAether.skyroot_planks);
		register("skyroot_bucket", new ItemStack(ItemsAether.skyroot_bucket, 1, 0), "Z Z", " Z ", 'Z', BlocksAether.wisproot_planks);
		register("skyroot_bucket", new ItemStack(ItemsAether.skyroot_bucket, 1, 0), "Z Z", " Z ", 'Z', BlocksAether.greatroot_planks);
		register("skyroot_bucket", new ItemStack(ItemsAether.skyroot_bucket, 1, 0), "Z Z", " Z ", 'Z', BlocksAether.void_planks);
		register("skyroot_bucket", new ItemStack(ItemsAether.skyroot_bucket, 1, 0), "Z Z", " Z ", 'Z', BlocksAether.golden_oak_planks);
		register("skyroot_workbench", new ItemStack(BlocksAether.skyroot_workbench, 1), "XX", "XX", 'X', BlocksAether.skyroot_planks);
		register("skyroot_workbench", new ItemStack(BlocksAether.skyroot_workbench, 1), "XX", "XX", 'X', BlocksAether.wisproot_planks);
		register("skyroot_workbench", new ItemStack(BlocksAether.skyroot_workbench, 1), "XX", "XX", 'X', BlocksAether.greatroot_planks);
		register("skyroot_workbench", new ItemStack(BlocksAether.skyroot_workbench, 1), "XX", "XX", 'X', BlocksAether.void_planks);
		register("skyroot_workbench", new ItemStack(BlocksAether.skyroot_workbench, 1), "XX", "XX", 'X', BlocksAether.golden_oak_planks);
		register("gravitite_helmet", new ItemStack(ItemsAether.gravitite_helmet, 1), "XXX", "X X", 'X', BlocksAether.enchanted_gravitite);
		register("gravitite_chestplate", new ItemStack(ItemsAether.gravitite_chestplate, 1), "X X", "XXX", "XXX", 'X', BlocksAether.enchanted_gravitite);
		register("gravitite_leggings", new ItemStack(ItemsAether.gravitite_leggings, 1), "XXX", "X X", "X X", 'X', BlocksAether.enchanted_gravitite);
		register("gravitite_boots", new ItemStack(ItemsAether.gravitite_boots, 1), "X X", "X X", 'X', BlocksAether.enchanted_gravitite);
		register("zanite_helmet", new ItemStack(ItemsAether.zanite_helmet, 1), "XXX", "X X", 'X', ItemsAether.zanite_gemstone);
		register("zanite_chestplate", new ItemStack(ItemsAether.zanite_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsAether.zanite_gemstone);
		register("zanite_leggings", new ItemStack(ItemsAether.zanite_leggings, 1), "XXX", "X X", "X X", 'X', ItemsAether.zanite_gemstone);
		register("zanite_boots", new ItemStack(ItemsAether.zanite_boots, 1), "X X", "X X", 'X', ItemsAether.zanite_gemstone);
		register("arkenium_helmet", new ItemStack(ItemsAether.arkenium_helmet, 1), "XXX", "X X", 'X', ItemsAether.arkenium_ingot);
		register("arkenium_chestplate", new ItemStack(ItemsAether.arkenium_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsAether.arkenium_ingot);
		register("arkenium_leggings", new ItemStack(ItemsAether.arkenium_leggings, 1), "XXX", "X X", "X X", 'X', ItemsAether.arkenium_ingot);
		register("arkenium_boots", new ItemStack(ItemsAether.arkenium_boots, 1), "X X", "X X", 'X', ItemsAether.arkenium_ingot);
		register("continuum_helmet", new ItemStack(ItemsAether.continuum_helmet, 1), "XXX", "X X", 'X', ItemsAether.continuum_gemstone);
		register("continuum_chestplate", new ItemStack(ItemsAether.continuum_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsAether.continuum_gemstone);
		register("continuum_leggings", new ItemStack(ItemsAether.continuum_leggings, 1), "XXX", "X X", "X X", 'X', ItemsAether.continuum_gemstone);
		register("continuum_boots", new ItemStack(ItemsAether.continuum_boots, 1), "X X", "X X", 'X', ItemsAether.continuum_gemstone);
		

		register("skyroot_pickaxe", new ItemStack(ItemsAether.skyroot_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("holystone_pickaxe", new ItemStack(ItemsAether.holystone_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', BlocksAether.holystone, 'Y', ItemsAether.skyroot_stick);
		register("zanite_pickaxe", new ItemStack(ItemsAether.zanite_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsAether.zanite_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("arkenium_pickaxe", new ItemStack(ItemsAether.arkenium_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsAether.arkenium_ingot, 'Y', ItemsAether.skyroot_stick);
		register("continuum_pickaxe", new ItemStack(ItemsAether.continuum_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsAether.continuum_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("gravitite_pickaxe", new ItemStack(ItemsAether.gravitite_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.skyroot_stick);
		register("skyroot_axe", new ItemStack(ItemsAether.skyroot_axe, 1), "ZZ", "ZY", " Y", 'Z', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("holystone_axe", new ItemStack(ItemsAether.holystone_axe, 1), "ZZ", "ZY", " Y", 'Z', BlocksAether.holystone, 'Y', ItemsAether.skyroot_stick);
		register("zanite_axe", new ItemStack(ItemsAether.zanite_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsAether.zanite_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("arkenium_axe", new ItemStack(ItemsAether.arkenium_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsAether.arkenium_ingot, 'Y', ItemsAether.skyroot_stick);
		register("continuum_axe", new ItemStack(ItemsAether.continuum_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsAether.continuum_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("gravitite_axe", new ItemStack(ItemsAether.gravitite_axe, 1), "ZZ", "ZY", " Y", 'Z', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.skyroot_stick);
		register("skyroot_shovel", new ItemStack(ItemsAether.skyroot_shovel, 1), "Z", "Y", "Y", 'Z', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("holystone_shovel", new ItemStack(ItemsAether.holystone_shovel, 1), "Z", "Y", "Y", 'Z', BlocksAether.holystone, 'Y', ItemsAether.skyroot_stick);
		register("zanite_shovel", new ItemStack(ItemsAether.zanite_shovel, 1), "Z", "Y", "Y", 'Z', ItemsAether.zanite_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("arkenium_shovel", new ItemStack(ItemsAether.arkenium_shovel, 1), "Z", "Y", "Y", 'Z', ItemsAether.arkenium_ingot, 'Y', ItemsAether.skyroot_stick);
		register("continuum_shovel", new ItemStack(ItemsAether.continuum_shovel, 1), "Z", "Y", "Y", 'Z', ItemsAether.continuum_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("gravitite_shovel", new ItemStack(ItemsAether.gravitite_shovel, 1), "Z", "Y", "Y", 'Z', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.skyroot_stick);
		register("skyroot_sword", new ItemStack(ItemsAether.skyroot_sword, 1), "Z", "Z", "Y", 'Z', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("holystone_sword", new ItemStack(ItemsAether.holystone_sword, 1), "Z", "Z", "Y", 'Z', BlocksAether.holystone, 'Y', ItemsAether.skyroot_stick);
		register("zanite_sword", new ItemStack(ItemsAether.zanite_sword, 1), "Z", "Z", "Y", 'Z', ItemsAether.zanite_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("arkenium_sword", new ItemStack(ItemsAether.arkenium_sword, 1), "Z", "Z", "Y", 'Z', ItemsAether.arkenium_ingot, 'Y', ItemsAether.skyroot_stick);
		register("continuum_sword", new ItemStack(ItemsAether.continuum_sword, 1), "Z", "Z", "Y", 'Z', ItemsAether.continuum_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("gravitite_sword", new ItemStack(ItemsAether.gravitite_sword, 1), "Z", "Z", "Y", 'Z', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.skyroot_stick);

		register("skyroot_hoe", new ItemStack(ItemsAether.skyroot_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick);
		register("holystone_hoe", new ItemStack(ItemsAether.holystone_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', BlocksAether.holystone, 'Y', ItemsAether.skyroot_stick);
		register("zanite_hoe", new ItemStack(ItemsAether.zanite_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', ItemsAether.zanite_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("arkenium_hoe", new ItemStack(ItemsAether.arkenium_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', ItemsAether.arkenium_ingot, 'Y', ItemsAether.skyroot_stick);
		register("continuum_hoe", new ItemStack(ItemsAether.continuum_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', ItemsAether.continuum_gemstone, 'Y', ItemsAether.skyroot_stick);
		register("gravitite_hoe", new ItemStack(ItemsAether.gravitite_hoe, 1, 0),  "XX ", " Y ", " Y ", 'X', BlocksAether.enchanted_gravitite, 'Y', ItemsAether.skyroot_stick);
		
		register("white_cape", new ItemStack(ItemsAether.white_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 0));
		register("orange_cape", new ItemStack(ItemsAether.orange_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 1));
		register("magenta_cape", new ItemStack(ItemsAether.magenta_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 2));
		register("light_blue_cape", new ItemStack(ItemsAether.light_blue_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 3));
		register("yellow_cape", new ItemStack(ItemsAether.yellow_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 4));
		register("lime_cape", new ItemStack(ItemsAether.lime_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 5));
		register("pink_cape", new ItemStack(ItemsAether.pink_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 6));
		register("gray_cape", new ItemStack(ItemsAether.gray_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 7));
		register("light_gray_cape", new ItemStack(ItemsAether.light_gray_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 8));
		register("cyan_cape", new ItemStack(ItemsAether.cyan_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 9));
		register("purple_cape", new ItemStack(ItemsAether.purple_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 10));
		register("blue_cape", new ItemStack(ItemsAether.blue_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 11));
		register("brown_cape", new ItemStack(ItemsAether.brown_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 12));
		register("green_cape", new ItemStack(ItemsAether.green_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 13));
		register("red_cape", new ItemStack(ItemsAether.red_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 14));
		register("black_cape", new ItemStack(ItemsAether.black_cape), "ZZ", "ZZ", "ZZ", 'Z', new ItemStack(Blocks.wool, 1, 15));

		register("iron_pendant", new ItemStack(ItemsAether.iron_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(Items.iron_ingot), 'S', new ItemStack(Items.string));
		register("golden_pendant", new ItemStack(ItemsAether.golden_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(Items.gold_ingot), 'S', new ItemStack(Items.string));
		register("diamond_pendant", new ItemStack(ItemsAether.diamond_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(Items.diamond), 'S', new ItemStack(Items.string));
		register("zanite_pendant", new ItemStack(ItemsAether.zanite_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(ItemsAether.zanite_gemstone), 'S', new ItemStack(Items.string));		
		register("arkenium_pendant", new ItemStack(ItemsAether.arkenium_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(ItemsAether.arkenium_ingot), 'S', new ItemStack(Items.string));
		register("continuum_pendant", new ItemStack(ItemsAether.continuum_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(ItemsAether.continuum_gemstone), 'S', new ItemStack(Items.string));
		register("auralite_pendant", new ItemStack(ItemsAether.auralite_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(ItemsAether.auralite_crystal), 'S', new ItemStack(Items.string));
		register("gravitite_pendant", new ItemStack(ItemsAether.gravitite_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(BlocksAether.enchanted_gravitite), 'S', new ItemStack(Items.string));
		
		register("leather_gloves", new ItemStack(ItemsAether.leather_gloves), "C C", 'C', Items.leather);
		register("iron_gloves", new ItemStack(ItemsAether.iron_gloves), "C C", 'C', Items.iron_ingot);
		register("golden_gloves", new ItemStack(ItemsAether.golden_gloves), "C C", 'C', Items.gold_ingot);
		register("diamond_gloves", new ItemStack(ItemsAether.diamond_gloves), "C C", 'C', Items.diamond);
		register("zanite_gloves", new ItemStack(ItemsAether.zanite_gloves), "C C", 'C', ItemsAether.zanite_gemstone);
		register("continuum_gloves", new ItemStack(ItemsAether.continuum_gloves), "C C", 'C', ItemsAether.continuum_gemstone);
		register("arkenium_gloves", new ItemStack(ItemsAether.arkenium_gloves), "C C", 'C', ItemsAether.arkenium_ingot);
		register("gravitite_gloves", new ItemStack(ItemsAether.gravitite_gloves), "C C", 'C', BlocksAether.enchanted_gravitite);
		
		register("zanite_ring", new ItemStack(ItemsAether.zanite_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(ItemsAether.zanite_gemstone));
		register("arkenium_ring", new ItemStack(ItemsAether.arkenium_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(ItemsAether.arkenium_ingot));
		register("continuum_ring", new ItemStack(ItemsAether.continuum_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(ItemsAether.continuum_gemstone));
		register("gravitite_ring", new ItemStack(ItemsAether.gravitite_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(BlocksAether.enchanted_gravitite));
		register("auralite_ring", new ItemStack(ItemsAether.auralite_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(ItemsAether.auralite_crystal));
		register("golden_ring", new ItemStack(ItemsAether.golden_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(Items.gold_ingot));
		register("iron_ring", new ItemStack(ItemsAether.iron_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(Items.iron_ingot));
		register("diamond_ring", new ItemStack(ItemsAether.diamond_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(Items.diamond));
		
		if (Loader.isModLoaded("etfuturum")) {
		register("copper_gloves", new ItemStack(ItemsAether.copper_gloves), "C C", 'C', OtherModItems.copper_ingot);	
		register("copper_ring", new ItemStack(ItemsAether.copper_ring), " Z ", "Z Z", " Z ", 'Z', new ItemStack(OtherModItems.copper_ingot));
		register("copper_pendant", new ItemStack(ItemsAether.copper_pendant), " Z ", "Z Z", " ZS", 'Z', new ItemStack(OtherModItems.copper_ingot), 'S', new ItemStack(Items.string));		
		}
		
		if (Loader.isModLoaded("netherlicious")) {
		register("effrine_gloves", new ItemStack(ItemsAether.effrine_gloves), "C C", 'C', OtherModItems.Ingot);
		register("heavy_blaze_gloves", new ItemStack(ItemsAether.heavy_blaze_gloves), "C C", 'C', OtherModItems.Materials);	
		}
		
		register("zanite_shears", new ItemStack(ItemsAether.zanite_shears), " C ", "C  ", 'C', ItemsAether.zanite_gemstone);

		register("skyroot_fence", new ItemStack(BlocksAether.skyroot_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.skyroot_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));
		register("golden_oak_fence", new ItemStack(BlocksAether.golden_oak_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.golden_oak_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));			
		register("divine_oak_fence", new ItemStack(BlocksAether.divine_oak_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.divine_oak_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));			
		register("void_fence", new ItemStack(BlocksAether.void_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.void_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));
		register("skyroot_fence_gate", new ItemStack(BlocksAether.skyroot_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.skyroot_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));
		register("golden_fence_gate", new ItemStack(BlocksAether.golden_oak_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.golden_oak_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));
		register("void_fence_gate", new ItemStack(BlocksAether.void_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.void_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));		
		register("greatroot_fence", new ItemStack(BlocksAether.greatroot_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.greatroot_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));
		register("greatroot_fence_gate", new ItemStack(BlocksAether.greatroot_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.greatroot_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));
		register("wisproot_fence", new ItemStack(BlocksAether.wisproot_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksAether.wisproot_planks), 'X', new ItemStack(ItemsAether.skyroot_stick));
		register("wisproot_fence_gate", new ItemStack(BlocksAether.wisproot_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.wisproot_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));
		register("divine_oak_gate", new ItemStack(BlocksAether.divine_oak_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksAether.divine_oak_planks), 'Z', new ItemStack(ItemsAether.skyroot_stick));

		
		register("carved_stone_slab", new ItemStack(BlocksAether.carved_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.carved_stone));
		register("angelic_stone_slab", new ItemStack(BlocksAether.angelic_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.angelic_stone));
		register("hellfire_stone_slab", new ItemStack(BlocksAether.hellfire_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.hellfire_stone));
		register("fuse_slab", new ItemStack(BlocksAether.fuse_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.fuse_stone));
		register("holystone_slab", new ItemStack(BlocksAether.holystone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.holystone, 1, 1));
		register("mossy_holystone_slab", new ItemStack(BlocksAether.mossy_holystone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.mossy_holystone, 1, 1));
		register("holystone_brick_slab", new ItemStack(BlocksAether.holystone_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.holystone_brick, 1));
		register("skyroot_slab", new ItemStack(BlocksAether.skyroot_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_planks));
		register("wisproot_slab", new ItemStack(BlocksAether.wisproot_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_planks));
		register("greatroot_slab", new ItemStack(BlocksAether.greatroot_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.greatroot_planks));
		register("void_slab", new ItemStack(BlocksAether.void_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.void_planks));
		register("deific_slab", new ItemStack(BlocksAether.deific_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.deific, 1, 1));
		register("enchanted_deific_slab", new ItemStack(BlocksAether.enchanted_deific_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific));
		register("enchanted_holystone_slab", new ItemStack(BlocksAether.enchanted_holystone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone));
		register("agiosite_slab", new ItemStack(BlocksAether.agiosite_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite, 1, 1));
		register("agiosite_brick_slab", new ItemStack(BlocksAether.agiosite_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite_bricks, 1, 0));
		register("enchanted_agiosite_slab", new ItemStack(BlocksAether.enchanted_agiosite_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite));
		register("aetheral_stone_slab", new ItemStack(BlocksAether.aetheral_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone, 1, 1));
		register("enchanted_aetheral_stone_slab", new ItemStack(BlocksAether.enchanted_aetheral_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone));
		register("caelestia_stone_slab", new ItemStack(BlocksAether.caelestia_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.caelestia_stone));
		register("carved_caelestia_stone_slab", new ItemStack(BlocksAether.carved_caelestia_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.carved_caelestia_stone));
		register("enchanted_agiosite_brick_slab", new ItemStack(BlocksAether.enchanted_agiosite_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite_bricks));
		register("aerogel_slab", new ItemStack(BlocksAether.aerogel_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.aerogel));
		register("ancient_carved_stone_slab", new ItemStack(BlocksAether.ancient_carved_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.ancient_carved_stone));
		register("divine_carved_stone_slab", new ItemStack(BlocksAether.divine_carved_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.divine_carved_stone));
		register("mythic_carved_stone_slab", new ItemStack(BlocksAether.mythic_carved_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_carved_stone));		
		register("divine_angelic_stone_slab", new ItemStack(BlocksAether.divine_angelic_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.divine_angelic_stone));
		register("mythic_angelic_stone_slab", new ItemStack(BlocksAether.mythic_angelic_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_angelic_stone));		
		register("divine_hellfire_stone_slab", new ItemStack(BlocksAether.divine_hellfire_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.divine_hellfire_stone));
		register("golden_oak_slab", new ItemStack(BlocksAether.golden_oak_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.golden_oak_planks));
		register("deific_brick_slab", new ItemStack(BlocksAether.deific_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.deific_bricks));
		register("enchanted_holystone_brick_slab", new ItemStack(BlocksAether.enchanted_holystone_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone_bricks));
		register("oblitus_stone_slab", new ItemStack(BlocksAether.oblitus_stone_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.oblitus_stone_2));
		register("aetheral_stone_brick_slab", new ItemStack(BlocksAether.aetheral_stone_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone_bricks));
		register("enchanted_deific_brick_slab", new ItemStack(BlocksAether.enchanted_deific_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific_bricks));
		register("enchanted_aetheral_stone_brick_slab", new ItemStack(BlocksAether.enchanted_aetheral_stone_brick_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone_bricks));
		register("divine_oak_slab", new ItemStack(BlocksAether.divine_oak_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.divine_oak_planks));
		register("genesis_slab", new ItemStack(BlocksAether.genesis_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.genesis_stone_2));

		
		register("paper", new ItemStack(Items.paper, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_log, 1, 0));
		register("paper", new ItemStack(Items.paper, 6), "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_log, 1, 1));
		
		
		register("skyroot_log_wall", new ItemStack(BlocksAether.skyroot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_log));
		register("golden_oak_log_wall", new ItemStack(BlocksAether.golden_oak_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.golden_oak_log));
		register("divine_oak_log_wall", new ItemStack(BlocksAether.divine_oak_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_oak_log));
		register("wisproot_log_wall", new ItemStack(BlocksAether.wisproot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_log));
		register("greatroot_log_wall", new ItemStack(BlocksAether.greatroot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.greatroot_log));
		register("void_log_wall", new ItemStack(BlocksAether.void_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.void_log));		
		register("skyroot_log_wall", new ItemStack(BlocksAether.skyroot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_log, 1, 1));
		register("divine_oak_log_wall", new ItemStack(BlocksAether.divine_oak_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_oak_log, 1, 1));
		register("golden_oak_log_wall", new ItemStack(BlocksAether.golden_oak_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.golden_oak_log, 1, 1));
		register("wisproot_log_wall", new ItemStack(BlocksAether.wisproot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_log, 1, 1));
		register("greatroot_log_wall", new ItemStack(BlocksAether.greatroot_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.greatroot_log, 1, 1));
		register("void_log_wall", new ItemStack(BlocksAether.void_log_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.void_log, 1, 1));				
		register("carved_stone_wall", new ItemStack(BlocksAether.carved_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.carved_stone));
		register("angelic_stone_wall", new ItemStack(BlocksAether.angelic_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.angelic_stone));
		register("hellfire_stone_wall", new ItemStack(BlocksAether.hellfire_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.hellfire_stone));
		register("fuse_wall", new ItemStack(BlocksAether.fuse_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.fuse_stone));
		register("holystone_wall", new ItemStack(BlocksAether.holystone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.holystone, 1, 1));
		register("mossy_holystone_wall", new ItemStack(BlocksAether.mossy_holystone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.mossy_holystone, 1, 1));
		register("holystone_brick_wall", new ItemStack(BlocksAether.holystone_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.holystone_brick, 1));
		register("deific_wall", new ItemStack(BlocksAether.deific_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.deific, 1, 1));
		register("agiosite_wall", new ItemStack(BlocksAether.agiosite_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite, 1, 1));
		register("enchanted_agiosite_wall", new ItemStack(BlocksAether.enchanted_agiosite_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite));
		register("aetheral_stone_wall", new ItemStack(BlocksAether.aetheral_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone, 1, 1));
		register("enchanted_aetheral_stone", new ItemStack(BlocksAether.enchanted_aetheral_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone));
		register("enchanted_deific_wall", new ItemStack(BlocksAether.enchanted_deific_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific));
		register("enchanted_holystone_wall", new ItemStack(BlocksAether.enchanted_holystone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone));
		register("caelestia_stone_wall", new ItemStack(BlocksAether.caelestia_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.caelestia_stone, 1, 1));
		register("carved_caelestia_stone_wall", new ItemStack(BlocksAether.carved_caelestia_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.carved_caelestia_stone));
		register("enchanted_agiosite_brick_wall", new ItemStack(BlocksAether.enchanted_agiosite_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite_bricks));
		register("aerogel_wall", new ItemStack(BlocksAether.aerogel_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.aerogel));
		register("ancient_carved_stone_wall", new ItemStack(BlocksAether.ancient_carved_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.ancient_carved_stone));
		register("divine_carved_stone_wall", new ItemStack(BlocksAether.divine_carved_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_carved_stone));
		register("mythic_carved_stone_wall", new ItemStack(BlocksAether.mythic_carved_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_carved_stone));
		register("divine_hellfire_stone_wall", new ItemStack(BlocksAether.divine_hellfire_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_hellfire_stone));
		register("divine_angelic_stone_wall", new ItemStack(BlocksAether.divine_angelic_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_angelic_stone));
		register("mythic_angelic_stone_wall", new ItemStack(BlocksAether.mythic_angelic_stone_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_angelic_stone));
		register("agiosite_brick_wall", new ItemStack(BlocksAether.agiosite_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite_bricks));
		register("deific_brick_wall", new ItemStack(BlocksAether.deific_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.deific_bricks));
		register("enchanted_holystone_brick_wall", new ItemStack(BlocksAether.enchanted_holystone_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone_bricks));
		register("oblitus_wall", new ItemStack(BlocksAether.oblitus_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.oblitus_stone_2));
		register("aetheral_stone_brick_wall", new ItemStack(BlocksAether.aetheral_stone_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone_bricks));
		register("enchanted_deific_brick_wall", new ItemStack(BlocksAether.enchanted_deific_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific_bricks));
		register("enchanted_aetheral_stone_brick_wall", new ItemStack(BlocksAether.enchanted_aetheral_stone_brick_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone_bricks));
		register("genesis_wall", new ItemStack(BlocksAether.genesis_wall, 6), "ZZZ", "ZZZ", 'Z', new ItemStack(BlocksAether.genesis_stone_2));

		
		register("carved_stone_stairs", new ItemStack(BlocksAether.carved_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.carved_stone));
		register("angelic_stone_stairs", new ItemStack(BlocksAether.angelic_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.angelic_stone));
		register("hellfire_stone_stairs", new ItemStack(BlocksAether.hellfire_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.hellfire_stone));
		register("fuse_stairs", new ItemStack(BlocksAether.fuse_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.fuse_stone));
		register("holystone_stairs", new ItemStack(BlocksAether.holystone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.holystone, 1, 1));
		register("mossy_holystone_stairs", new ItemStack(BlocksAether.mossy_holystone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.mossy_holystone, 1, 1));
		register("holystone_brick_stairs", new ItemStack(BlocksAether.holystone_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.holystone_brick, 1));
		register("skyroot_stairs", new ItemStack(BlocksAether.skyroot_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_planks));
		register("void_stairs", new ItemStack(BlocksAether.void_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.void_planks));
		register("greatroot_stairs", new ItemStack(BlocksAether.greatroot_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.greatroot_planks));
		register("wisproot_stairs", new ItemStack(BlocksAether.wisproot_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_planks));		
		register("deific_stairs", new ItemStack(BlocksAether.deific_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.deific, 1, 1));
		register("deific_brick_stairs", new ItemStack(BlocksAether.deific_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.deific_bricks));	
		register("enchanted_deific_stairs", new ItemStack(BlocksAether.enchanted_deific_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific));
		register("aetheral_stone_stairs", new ItemStack(BlocksAether.aetheral_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone, 1, 1));
		register("aetheral_stone_brick_stairs", new ItemStack(BlocksAether.aetheral_stone_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.aetheral_stone_bricks));
		register("enchanted_aetheral_stone_stairs", new ItemStack(BlocksAether.enchanted_aetheral_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone));
		register("agisoite_stairs", new ItemStack(BlocksAether.agiosite_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite, 1, 1));
		register("enchanted_agiosite_stairs", new ItemStack(BlocksAether.enchanted_agiosite_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite));
		register("enchanted_holystone_stairs", new ItemStack(BlocksAether.enchanted_holystone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone));
		register("caelestia_stone_stairs", new ItemStack(BlocksAether.caelestia_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.caelestia_stone, 1, 1));
		register("carved_caelestia_stone_stairs", new ItemStack(BlocksAether.carved_caelestia_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.carved_caelestia_stone));
		register("enchanted_agiosite_brick_stairs", new ItemStack(BlocksAether.enchanted_agiosite_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_agiosite_bricks));
		register("aerogel_stairs", new ItemStack(BlocksAether.aerogel_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.aerogel));
		register("ancient_carved_stone_stairs", new ItemStack(BlocksAether.ancient_carved_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.ancient_carved_stone));
		register("divine_carved_stone_stairs", new ItemStack(BlocksAether.divine_carved_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_carved_stone));
		register("divine_angelic_stone_stairs", new ItemStack(BlocksAether.divine_angelic_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_angelic_stone));		
		register("mythic_carved_stone_stairs", new ItemStack(BlocksAether.mythic_carved_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_carved_stone));
		register("mythic_angelic_stone_stairs", new ItemStack(BlocksAether.mythic_angelic_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.mythic_angelic_stone));
		register("divine_hellfire_stone_stairs", new ItemStack(BlocksAether.divine_hellfire_stone_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_hellfire_stone));
		register("golden_oak_stairs", new ItemStack(BlocksAether.golden_oak_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.golden_oak_planks));
		register("agiosite_brick_stairs", new ItemStack(BlocksAether.agiosite_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.agiosite_bricks));
		register("enchanted_holystone_brick_stairs", new ItemStack(BlocksAether.enchanted_holystone_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_holystone_bricks));
		register("oblitus_stairs", new ItemStack(BlocksAether.oblitus_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.oblitus_stone_2));
		register("enchanted_deific_brick_stairs", new ItemStack(BlocksAether.enchanted_deific_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_deific_bricks));
		register("enchanted_aetheral_stone_brick_stairs", new ItemStack(BlocksAether.enchanted_aetheral_stone_brick_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.enchanted_aetheral_stone_bricks));
		register("divine_oak_stairs", new ItemStack(BlocksAether.divine_oak_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.divine_oak_planks));
		register("genesis_stairs", new ItemStack(BlocksAether.genesis_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksAether.genesis_stone_2));

		
		
		register("skyroot_bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf, 1),  "ZZZ", "XXX", "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_planks), 'X', new ItemStack(Items.book));
		register("skyroot_bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf, 1),  "ZZZ", "XXX", "ZZZ", 'Z', new ItemStack(BlocksAether.greatroot_planks), 'X', new ItemStack(Items.book));
		register("skyroot_bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf, 1),  "ZZZ", "XXX", "ZZZ", 'Z', new ItemStack(BlocksAether.wisproot_planks), 'X', new ItemStack(Items.book));
		register("skyroot_bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf, 1),  "ZZZ", "XXX", "ZZZ", 'Z', new ItemStack(BlocksAether.void_planks), 'X', new ItemStack(Items.book));
		
		
		register("skyroot_bed_item", new ItemStack(ItemsAether.skyroot_bed_item, 1),  "XXX", "ZZZ", 'Z', new ItemStack(BlocksAether.skyroot_planks), 'X', Blocks.wool);
	}

	private static void register(String name, ItemStack stack, Object... recipe) {
		GameRegistry.addRecipe(stack, recipe);
	}

	private static void registerShapeless(String name, ItemStack stack, Object... recipe) {
		GameRegistry.addShapelessRecipe(stack, recipe);
	}

}