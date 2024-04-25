package com.gildedgames.the_aether.items.tools;

import com.gildedgames.the_aether.entities.passive.mountable.EntityParachute;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAetherBlueParachute extends Item {

	public ItemAetherBlueParachute() {
		this.setMaxDamage(10);
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer entityplayer) {
		ItemStack heldItem = entityplayer.getHeldItem();

		if (EntityParachute.entityHasRoomForCloud(world, entityplayer)) {
			if (this == ItemsAether.blue_parachute) {
				heldItem.damageItem(1, entityplayer);
			} else {
				--heldItem.stackSize;
			}

			world.spawnEntityInWorld(new EntityParachute(world, entityplayer, this == ItemsAether.blue_parachute));

			return heldItem;
		}

		return super.onItemRightClick(stack, world, entityplayer);
	}
	
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (this == ItemsAether.blue_parachute) return 0xCCCCFF;

		return 0xCCCCFF;
	}

}