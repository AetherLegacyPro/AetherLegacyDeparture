package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.entities.projectile.EntityHammerProjectile;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemNotchHammer extends ItemSword {

	public ItemNotchHammer() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.capabilities.isCreativeMode) {
			world.playSound(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F), false);

			if (!world.isRemote) {
				EntityHammerProjectile hammerProjectile = new EntityHammerProjectile(world, entityplayer);
				hammerProjectile.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.spawnEntityInWorld(hammerProjectile);
			}
		} else if (PlayerAether.get(entityplayer).setHammerCooldown(120, itemstack.getDisplayName())) {
			itemstack.damageItem(2, entityplayer);

			world.playSound(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F), false);

			if (!world.isRemote) {
				EntityHammerProjectile hammerProjectile = new EntityHammerProjectile(world, entityplayer);
				hammerProjectile.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.spawnEntityInWorld(hammerProjectile);
			}
		}

		return itemstack;
	}

}