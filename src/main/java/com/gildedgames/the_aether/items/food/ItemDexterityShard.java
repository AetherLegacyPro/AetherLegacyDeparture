package com.gildedgames.the_aether.items.food;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemDexterityShard extends Item {

	public ItemDexterityShard() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {
		PlayerAether playerAether = PlayerAether.get(player);
		ItemStack heldItem = player.getHeldItem();

		if (!worldIn.isRemote)
		{
			playerAether.updateDexShardCount(0);

			if (playerAether.getDexShardsUsed() < playerAether.getMaxDexShardCount())
			{
				playerAether.updateDexShardCount(1);
				--heldItem.stackSize;
			}

			return heldItem;
		}
		else
		{
			if (playerAether.getDexShardsUsed() >= playerAether.getMaxDexShardCount())
			{
				Aether.proxy.sendMessage(player, StatCollector.translateToLocalFormatted("gui.item.dex_shard.maxshards", playerAether.getMaxDexShardCount()));
			}
		}

		return heldItem;
	}

}
