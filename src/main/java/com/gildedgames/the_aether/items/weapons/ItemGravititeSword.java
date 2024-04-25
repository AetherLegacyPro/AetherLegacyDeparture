package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;

public class ItemGravititeSword extends ItemSword {

	public ItemGravititeSword() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		return material.getItem() == Item.getItemFromBlock(BlocksAether.enchanted_gravitite);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitentity, EntityLivingBase player) {
		
		
		if ((hitentity.hurtTime > 0 || hitentity.deathTime > 0) && !(hitentity instanceof EntitySwet)) {
			hitentity.addVelocity(0.0D, 1.0D, 0.0D);
		}

		if (hitentity instanceof EntityPlayerMP) {
			((EntityPlayerMP) hitentity).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(hitentity));
		}

		return super.hitEntity(itemstack, hitentity, player);
	}

}