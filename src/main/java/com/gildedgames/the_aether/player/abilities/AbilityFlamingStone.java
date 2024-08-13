package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AbilityFlamingStone implements IAetherAbility {

	private final IPlayerAether player;

	public AbilityFlamingStone(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		
		if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.flaming_stone)) {
			if (this.player.getEntity().isBurning() && this.player.getEntity().getActivePotionEffect(Potion.fireResistance) == null) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.fireResistance.id, 100, 0));
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedObsidianSet()) {
			this.player.getEntity().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 0));
		}
	}

}
