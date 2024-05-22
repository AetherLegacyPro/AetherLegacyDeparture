package com.gildedgames.the_aether.items.food;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPowerShard extends Item {

	public ItemPowerShard() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {
		PlayerAether playerAether = PlayerAether.get(player);
		ItemStack heldItem = player.getHeldItem();

		player.triggerAchievement(AchievementsAether.powering_up);
		if (!worldIn.isRemote)
		{
			playerAether.updatePowerShardCount(0);

			if (playerAether.getPowerShardsUsed() < playerAether.getMaxPowerShardCount())
			{
				playerAether.updatePowerShardCount(1);
				--heldItem.stackSize;
			}

			return heldItem;
		}
		else
		{
			if (playerAether.getPowerShardsUsed() >= playerAether.getMaxPowerShardCount())
			{
				Aether.proxy.sendMessage(player, StatCollector.translateToLocalFormatted("gui.item.power_shard.maxshards", playerAether.getMaxPowerShardCount()));
			}
		}

		return heldItem;
	}

}
