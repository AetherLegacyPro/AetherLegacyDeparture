package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;


public class AbilityHasteRing implements IAetherAbility {

	private final IPlayerAether player;

	private boolean stepUpdate;

	public AbilityHasteRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
	

	@Override
	public void onUpdate() {
		
	   if (Loader.isModLoaded("etfuturum")) {
		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.copper_ring)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.copper_pendant))) {
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.id, 40, 0));
		  }
		}
	
		if (this.player.getAccessoryInventory().isWearingHasteRing()) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.id, 40, 0));
			
			}
		
		if (this.player.getAccessoryInventory().isWearingHasteRingAndArkenium()) {
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.id, 40, 1));
		
		}
		
		if (this.player.getAccessoryInventory().isWearingHasteRingAndArkeniumCombo()) {
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.id, 40, 1));
		
		}
		
		if (this.player.getAccessoryInventory().isWearingHasteRingAmpilifedArkenium()) {
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.id, 40, 1));
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 1));
		
		}
		
		}
	}
