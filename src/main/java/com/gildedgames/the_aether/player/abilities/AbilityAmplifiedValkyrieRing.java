package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbilityAmplified;

public class AbilityAmplifiedValkyrieRing implements IAetherAbilityAmplified {

	private int flightCount;

	private int maxFlightCount = 256;

	private double flightMod = 1.8D;

	private double maxFlightMod = 45.0D;

	private final IPlayerAether player;

	public AbilityAmplifiedValkyrieRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return this.player.getAccessoryInventory().isWearingAmplifiedValkyrieRingAndAmplifiedArmor();
	}

	@Override
	public void onUpdate() {
		if (this.player.isJumping()) {
			if (this.flightMod >= this.maxFlightMod) {
				this.flightMod = this.maxFlightMod;
			}

			if (this.flightCount > 2) {
				if (this.flightCount < this.maxFlightCount) {
					this.flightMod += 0.5D;
					this.player.getEntity().motionY = 0.035D * this.flightMod;
					this.flightCount++;
				}
			} else {
				this.flightCount++;
			}

		} else {
			this.flightMod = 1.0D;
		}

		if (this.player.getEntity().onGround) {
			this.flightCount = 0;
			this.flightMod = 1.0D;
		}
	}

}
