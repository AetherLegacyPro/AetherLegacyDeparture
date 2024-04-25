package com.gildedgames.the_aether.items.othermods;

import net.minecraft.block.*;
import net.minecraft.item.*;

import com.gildedgames.the_aether.registry.OtherModBlocks;

import cpw.mods.fml.common.registry.*;

public class OtherModItems
{
	//Et Futurum
	public static Item netherite_ingot;
	public static Item netherite_scrap;
	public static Item copper_ingot;
	public static Item raw_ore;
	
	//Netherlicious
	public static Item Ingot; //effrine ingot
	public static Item Materials; //shield scraps
	public static Item Nugget;
	
	//Divine RPG
    public static Item rupeeIngot;
    public static Item arlemiteIngot;
    public static Item realmiteIngot;
    
    //HEE
    public static Item endium_ingot;
	
	static {
	   OtherModItems.netherite_ingot = GameRegistry.findItem("etfuturum", "netherite_ingot");
	   OtherModItems.netherite_scrap = GameRegistry.findItem("etfuturum", "netherite_scrap");
	   OtherModItems.copper_ingot = GameRegistry.findItem("etfuturum", "copper_ingot");
	   OtherModItems.raw_ore = GameRegistry.findItem("etfuturum", "raw_ore");
	   
	   OtherModItems.Ingot = GameRegistry.findItem("netherlicious", "Ingot");
	   OtherModItems.Materials = GameRegistry.findItem("netherlicious", "Materials");
	   OtherModItems.Nugget = GameRegistry.findItem("netherlicious", "Nugget");
	   
	   OtherModItems.rupeeIngot = GameRegistry.findItem("divinerpg", "rupeeIngot");
	   OtherModItems.arlemiteIngot = GameRegistry.findItem("divinerpg", "arlemiteIngot");
	   OtherModItems.realmiteIngot = GameRegistry.findItem("divinerpg", "realmiteIngot");
	   
	   OtherModItems.endium_ingot = GameRegistry.findItem("divinerpg", "endium_ingot");
	}
}
