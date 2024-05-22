package com.gildedgames.the_aether.registry.achievements;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class PickUpAchievement {
	
	 @SubscribeEvent
	    public void AncientEnchanter(final PlayerEvent.ItemPickupEvent event) {
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.ancient_enchanter))) {
	            event.player.addStat((StatBase)AchievementsAether.ancient_enchanter, 1);
	        } 
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.primeval_artifact))) {
	            event.player.addStat((StatBase)AchievementsAether.artifact, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.dungeon_key, 1, 10))) {
	            event.player.addStat((StatBase)AchievementsAether.defeat_cobalt, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.dungeon_key, 1, 14))) {
	            event.player.addStat((StatBase)AchievementsAether.defeat_palladium, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.zarnillys_scales))) {
	            event.player.addStat((StatBase)AchievementsAether.zarnillys_scales, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.auralite_crystal))) {
	            event.player.addStat((StatBase)AchievementsAether.auralite, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.zanite_gemstone))) {
	            event.player.addStat((StatBase)AchievementsAether.zanite_gemstone, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.arkenium_fragement))) {
	            event.player.addStat((StatBase)AchievementsAether.arkenium_fragment, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.continuum_gemstone))) {
	            event.player.addStat((StatBase)AchievementsAether.continuum_gemstone, 1);
	        }
	        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.enchanted_bloom))) {
	            event.player.addStat((StatBase)AchievementsAether.golden_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.arctic_glowstone))) {
	            event.player.addStat((StatBase)AchievementsAether.arctic_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.wisproot_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.arctic_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.wisproot_log, 0, 0))) {
	            event.player.addStat((StatBase)AchievementsAether.arctic_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_oak_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_oak_log, 0, 0))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_bloom, 0, 0))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_lily, 0, 0))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_stalk, 0, 0))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.amethyst_glowstone))) {
	            event.player.addStat((StatBase)AchievementsAether.divine_island, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.empyrean_gemstone))) {
	            event.player.addStat((StatBase)AchievementsAether.newnew_ore, 1);
	        }
	        
	        //Plants
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.purple_flower))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.white_flower))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.carrion_flower))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.aether_tallgrass))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.arctic_tallgrass))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.enchanted_aether_tallgrass))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_aether_tallgrass))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }	        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.white_rose))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.aechor_sprout))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.blue_swingtip))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.neverbloom))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.burstblossom))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.moonlit_bloom))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.quickshoot))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }	        	        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.enchanted_quickshoot))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.aether_tulips))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.enchanted_aether_tulips))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.enchanted_bloom))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }	        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_stalk))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_bloom))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.divine_lily))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.blackberry_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.blueberry_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.grape_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.orange_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.raspberry_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.strawberry_seeds))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }	        
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.blueberry))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.orange))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.grapes))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.raspberry))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.strawberry))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.blackberry))) {
	            event.player.addStat((StatBase)AchievementsAether.aether_florist, 1);
	        }
	    }
	 
	 @SubscribeEvent
	    public void GetWood(final PlayerEvent.ItemPickupEvent event) {
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.skyroot_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.getting_wood_again, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.greatroot_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.getting_wood_again, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.wisproot_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.getting_wood_again, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.void_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.getting_wood_again, 1);
	        }
	    }

}
