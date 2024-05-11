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
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.zarnillys_scales))) {
	            event.player.addStat((StatBase)AchievementsAether.zarnillys_scales, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemsAether.auralite_crystal))) {
	            event.player.addStat((StatBase)AchievementsAether.auralite, 1);
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
	            event.player.addStat((StatBase)AchievementsAether.wisproot_wood, 1);
	        }
	        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlocksAether.void_log, 1, 1))) {
	            event.player.addStat((StatBase)AchievementsAether.getting_wood_again, 1);
	            event.player.addStat((StatBase)AchievementsAether.void_wood, 1);
	        }
	    }

}
