package com.gildedgames.the_aether.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class OtherModBlocks {
	
	//Et Futurum Requiem
	public static Block ancient_debris;
	public static Block copper_ore;
	
	public static Block deepslate_coal_ore;
	public static Block deepslate_iron_ore;
	public static Block deepslate_gold_ore;
	public static Block deepslate_copper_ore;
	public static Block deepslate_diamond_ore;
	public static Block deepslate_emerald_ore;
	public static Block deepslate_lapis_ore;
	public static Block deepslate_redstone_ore;
	public static Block deepslate_lit_redstone_ore;
	
	//Netherlicious
	public static Block EfrineOre;
	
	//NovaCraft
	public static Block stone_vanite_ore, deepslate_vanite_ore, grimstone_vanite_ore, nullstone_vanite_ore;
	public static Block stone_tophinite_ore, deepslate_tophinite_ore, nullstone_tophinite_ore, nether_tophinite_ore;
	public static Block stone_klangite_ore, deepslate_klangite_ore, klangite_ore, end_klangite_ore, frontierslate_klangite_ore;
	public static Block brimstone_ore, xancium_ore;
	public static Block grimstone_diamond, grimstone_emerald, grimstone_gold, grimstone_iron, grimstone_lapis;
	public static Block grimstone_redstone, lit_grimstone_redstone;	
	public static Block nullstone_diamond, nullstone_emerald, nullstone_gold, nullstone_iron, nullstone_lapis;
	public static Block nullstone_redstone, lit_nullstone_redstone;
	public static Block etherstone_coal, etherstone_emerald, etherstone_gold, etherstone_iron, etherstone_brimstone;
	public static Block large_pherithium_stalagmite, small_pherithium_stalagmite;
	
	//Divine RPG
	public static Block rupeeOre;
	public static Block arlemiteOre;
	public static Block realmiteOre;
	
	//Aether II
	//public static Block sliderLabyrinthTotem;
    
    static {
    	//Et Futurum Requiem
    	OtherModBlocks.ancient_debris = GameRegistry.findBlock("etfuturum", "ancient_debris");
    	OtherModBlocks.copper_ore = GameRegistry.findBlock("etfuturum", "copper_ore");
    	
    	OtherModBlocks.deepslate_coal_ore = GameRegistry.findBlock("etfuturum", "deepslate_coal_ore");
    	OtherModBlocks.deepslate_iron_ore = GameRegistry.findBlock("etfuturum", "deepslate_iron_ore");
    	OtherModBlocks.deepslate_gold_ore = GameRegistry.findBlock("etfuturum", "deepslate_gold_ore");
    	OtherModBlocks.deepslate_copper_ore = GameRegistry.findBlock("etfuturum", "deepslate_copper_ore");
    	OtherModBlocks.deepslate_diamond_ore = GameRegistry.findBlock("etfuturum", "deepslate_diamond_ore");
    	OtherModBlocks.deepslate_emerald_ore = GameRegistry.findBlock("etfuturum", "deepslate_emerald_ore");
    	OtherModBlocks.deepslate_lapis_ore = GameRegistry.findBlock("etfuturum", "deepslate_lapis_ore");
    	OtherModBlocks.deepslate_redstone_ore = GameRegistry.findBlock("etfuturum", "deepslate_redstone_ore");
    	OtherModBlocks.deepslate_lit_redstone_ore = GameRegistry.findBlock("etfuturum", "deepslate_lit_redstone_ore");
    	
    	OtherModBlocks.EfrineOre = GameRegistry.findBlock("netherlicious", "EfrineOre");
    	
    	//NovaCraft
    	OtherModBlocks.large_pherithium_stalagmite = GameRegistry.findBlock("nova_craft", "large_pherithium_stalagmite");
    	OtherModBlocks.small_pherithium_stalagmite = GameRegistry.findBlock("nova_craft", "small_pherithium_stalagmite");
    	
    	OtherModBlocks.stone_vanite_ore = GameRegistry.findBlock("nova_craft", "stone_vanite_ore");
    	OtherModBlocks.deepslate_vanite_ore = GameRegistry.findBlock("nova_craft", "deepslate_vanite_ore");
    	OtherModBlocks.grimstone_vanite_ore = GameRegistry.findBlock("nova_craft", "grimstone_vanite_ore");
    	OtherModBlocks.nullstone_vanite_ore = GameRegistry.findBlock("nova_craft", "nullstone_vanite_ore");
    	
    	OtherModBlocks.stone_tophinite_ore = GameRegistry.findBlock("nova_craft", "stone_tophinite_ore");
    	OtherModBlocks.deepslate_tophinite_ore = GameRegistry.findBlock("nova_craft", "deepslate_tophinite_ore");
    	OtherModBlocks.nullstone_tophinite_ore = GameRegistry.findBlock("nova_craft", "nullstone_tophinite_ore");
    	OtherModBlocks.nether_tophinite_ore = GameRegistry.findBlock("nova_craft", "nether_tophinite_ore");
    	
    	OtherModBlocks.stone_klangite_ore = GameRegistry.findBlock("nova_craft", "stone_klangite_ore");
    	OtherModBlocks.deepslate_klangite_ore = GameRegistry.findBlock("nova_craft", "deepslate_klangite_ore");
    	OtherModBlocks.klangite_ore = GameRegistry.findBlock("nova_craft", "klangite_ore");
    	OtherModBlocks.end_klangite_ore = GameRegistry.findBlock("nova_craft", "end_klangite_ore");
    	OtherModBlocks.frontierslate_klangite_ore = GameRegistry.findBlock("nova_craft", "frontierslate_klangite_ore");    	
    	OtherModBlocks.brimstone_ore = GameRegistry.findBlock("nova_craft", "brimstone_ore");
    	OtherModBlocks.xancium_ore = GameRegistry.findBlock("nova_craft", "xancium_ore");
    	
    	OtherModBlocks.grimstone_diamond = GameRegistry.findBlock("nova_craft", "grimstone_diamond");
    	OtherModBlocks.grimstone_emerald = GameRegistry.findBlock("nova_craft", "grimstone_emerald");
    	OtherModBlocks.grimstone_gold = GameRegistry.findBlock("nova_craft", "grimstone_gold");
    	OtherModBlocks.grimstone_iron = GameRegistry.findBlock("nova_craft", "grimstone_iron");
    	OtherModBlocks.grimstone_lapis = GameRegistry.findBlock("nova_craft", "grimstone_lapis");    	
    	OtherModBlocks.grimstone_redstone = GameRegistry.findBlock("nova_craft", "grimstone_redstone");
    	OtherModBlocks.lit_grimstone_redstone = GameRegistry.findBlock("nova_craft", "lit_grimstone_redstone");
    	
    	OtherModBlocks.nullstone_diamond = GameRegistry.findBlock("nova_craft", "nullstone_diamond");
    	OtherModBlocks.nullstone_emerald = GameRegistry.findBlock("nova_craft", "nullstone_emerald");
    	OtherModBlocks.nullstone_gold = GameRegistry.findBlock("nova_craft", "nullstone_gold");
    	OtherModBlocks.nullstone_iron = GameRegistry.findBlock("nova_craft", "nullstone_iron");
    	OtherModBlocks.nullstone_lapis = GameRegistry.findBlock("nova_craft", "nullstone_lapis");    	
    	OtherModBlocks.nullstone_redstone = GameRegistry.findBlock("nova_craft", "nullstone_redstone");
    	OtherModBlocks.lit_nullstone_redstone = GameRegistry.findBlock("nova_craft", "lit_nullstone_redstone");
    	
    	OtherModBlocks.etherstone_coal = GameRegistry.findBlock("nova_craft", "etherstone_coal");
    	OtherModBlocks.etherstone_emerald = GameRegistry.findBlock("nova_craft", "etherstone_emerald");
    	OtherModBlocks.etherstone_gold = GameRegistry.findBlock("nova_craft", "etherstone_gold");    	
    	OtherModBlocks.etherstone_iron = GameRegistry.findBlock("nova_craft", "etherstone_iron");
    	OtherModBlocks.etherstone_brimstone = GameRegistry.findBlock("nova_craft", "etherstone_brimstone");
    	
    	//Divine RPG
    	OtherModBlocks.rupeeOre = GameRegistry.findBlock("divinerpg", "rupeeOre");
    	OtherModBlocks.arlemiteOre = GameRegistry.findBlock("divinerpg", "arlemiteOre");
    	OtherModBlocks.realmiteOre = GameRegistry.findBlock("divinerpg", "realmiteOre");
    	
    	//Aether II
    	//OtherModBlocks.sliderLabyrinthTotem = GameRegistry.findBlock("aether", "sliderLabyrinthTotem");
    	
    }
}
