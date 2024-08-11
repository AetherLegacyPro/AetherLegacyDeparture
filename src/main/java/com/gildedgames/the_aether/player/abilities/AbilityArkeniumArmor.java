package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import net.minecraft.potion.Potion;

public class AbilityArkeniumArmor implements IAetherAbility {

	private final IPlayerAether player;

	public AbilityArkeniumArmor(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
			
		if (this.player.getAccessoryInventory().isWearingArkeniumSet()) {
			
			this.player.getEntity().addPotionEffect(new EffectInebriation(Potion.nightVision.id, 300, 0));
			
			}
		
		if (this.player.getAccessoryInventory().isWearingArkeniumComboSet()) {
			
			this.player.getEntity().addPotionEffect(new EffectInebriation(Potion.nightVision.id, 300, 0));
			
			}
		
	    if (this.player.getAccessoryInventory().isWearingAmplifiedArkeniumSet()) {
			
			this.player.getEntity().addPotionEffect(new EffectInebriation(Potion.nightVision.id, 300, 0));
			this.player.getEntity().addPotionEffect(new EffectInebriation(Potion.digSpeed.id, 20, 0));
			
			}
				
		}
	}
