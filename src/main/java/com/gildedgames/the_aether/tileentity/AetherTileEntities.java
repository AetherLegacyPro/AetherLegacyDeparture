package com.gildedgames.the_aether.tileentity;

import com.gildedgames.the_aether.blocks.elysian_totem.TileEntityElysianTotem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AetherTileEntities {

	public static void initialization() {
		GameRegistry.registerTileEntity(TileEntityEnchanter.class, "enchanter");
		GameRegistry.registerTileEntity(TileEntityFreezer.class, "freezer");
		GameRegistry.registerTileEntity(TileEntityIncubator.class, "aether_incubator");
		GameRegistry.registerTileEntity(TileEntityAmplifier.class, "amplifier");
		GameRegistry.registerTileEntity(TileEntityTreasureChest.class, "treasure_chest");
		GameRegistry.registerTileEntity(TileEntityTreasureChestBreakable.class, "treasure_chest_breakable");
		GameRegistry.registerTileEntity(TileEntityChestMimic.class, "chest_mimic");
		GameRegistry.registerTileEntity(TileEntityElysianChestMimic.class, "elysian_mimic");
		GameRegistry.registerTileEntity(TileEntityAncientEnchanter.class, "ancient_enchanter");
		GameRegistry.registerTileEntity(TileEntitySkyrootChest.class, "skyroot_chest");
		GameRegistry.registerTileEntity(TileEntityElysianChest.class, "elysian_chest");
		GameRegistry.registerTileEntity(TileEntityAetherEnchantmentTable.class, "aether_enchantment_table");
		GameRegistry.registerTileEntity(TileEntityDivineEnchantmentTable.class, "divine_enchantment_table");
		GameRegistry.registerTileEntity(TileEntityElysianTotem.class, "elysian_totem");
	}

}