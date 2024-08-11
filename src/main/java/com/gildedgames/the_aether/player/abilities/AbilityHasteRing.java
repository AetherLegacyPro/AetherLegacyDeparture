package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.common.Loader;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AbilityHasteRing implements IAetherAbility {

	private final IPlayerAether player;

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
			if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.copper_ring) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.copper_pendant)) {
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
