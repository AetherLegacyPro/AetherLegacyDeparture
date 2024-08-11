package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AbilityElysianRing implements IAetherAbility {

	private final IPlayerAether player;

	public AbilityElysianRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		
		if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.elysian_ring) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.reinforced_elysian_ring)) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 0));
		}
		else if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.amplified_elysian_ring)) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 1));
		}
		
		
		}
	}
