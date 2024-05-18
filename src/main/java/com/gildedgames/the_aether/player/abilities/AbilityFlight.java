package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;

import net.minecraft.util.DamageSource;

public class AbilityFlight implements IAetherAbility {

	private int flightCount;

	private int maxFlightCount = 70;

	private double flightMod = 1.0D;

	private double maxFlightMod = 15.0D;
	
	
	private int flightCount2;

	private int maxFlightCount2 = 104;

	private double flightMod2 = 1.5D;

	private double maxFlightMod2 = 25.0D;
	
	
	private int flightCount3;

	private int maxFlightCount3 = 144;

	private double flightMod3 = 1.8D;

	private double maxFlightMod3 = 35.0D;

	private final IPlayerAether player;

	public AbilityFlight(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return (this.player.getAccessoryInventory().isWearingValkyrieSet() ||
			   this.player.getAccessoryInventory().isWearingValkyrieComboSet() ||
			   this.player.getAccessoryInventory().isWearingAmplifiedValkyrieSet() ||
			   this.player.getAccessoryInventory().isWearingValkyrieRingAndAmplifiedArmor() ||
			   this.player.getAccessoryInventory().isWearingAmplifiedValkyrieRingAndAmplifiedArmor() || 
			   this.player.getAccessoryInventory().isWearingAscensiteSet());
	}

	@Override
	public void onUpdate() {
		 if (this.player.getAccessoryInventory().isWearingValkyrieSet() || this.player.getAccessoryInventory().isWearingValkyrieComboSet()) {
			 if (this.player.isJumping()) {
			if (this.flightMod >= this.maxFlightMod) {
				this.flightMod = this.maxFlightMod;
			}

			if (this.flightCount > 2) {
				if (this.flightCount < this.maxFlightCount) {
					this.flightMod += 0.25D;
					this.player.getEntity().motionY = 0.025D * this.flightMod;
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
		 if (this.player.getAccessoryInventory().isWearingAmplifiedValkyrieSet()) {
			 if (this.player.isJumping()) {
			if (this.flightMod2 >= this.maxFlightMod2) {
				this.flightMod2 = this.maxFlightMod2;
			}

			if (this.flightCount2 > 2) {
				if (this.flightCount2 < this.maxFlightCount2) {
					this.flightMod2 += 0.3D;
					this.player.getEntity().motionY = 0.035D * this.flightMod2;
					this.flightCount2++;
				}
			} else {
				this.flightCount2++;
			}

		} else {
			this.flightMod2 = 1.0D;
		}

		if (this.player.getEntity().onGround) {
			this.flightCount2 = 0;
			this.flightMod2 = 2.0D;
			}
		 }
		 
		 if (this.player.getAccessoryInventory().isWearingValkyrieRingAndAmplifiedArmor()) {
			 if (this.player.isJumping()) {
				if (this.flightMod3 >= this.maxFlightMod3) {
					this.flightMod3 = this.maxFlightMod3;
				}

				if (this.flightCount3 > 2) {
					if (this.flightCount3 < this.maxFlightCount3) {
						this.flightMod3 += 0.45D;
						this.player.getEntity().motionY = 0.045D * this.flightMod3;
						this.flightCount3++;
					}
				} else {
					this.flightCount3++;
				}

			} else {
				this.flightMod3 = 1.0D;
			}

			if (this.player.getEntity().onGround) {
				this.flightCount3 = 0;
				this.flightMod3 = 2.0D;
			}
		}
		 
		if (this.player.getAccessoryInventory().isWearingAmplifiedValkyrieRingAndAmplifiedArmor()) {
			 if (this.player.isJumping()) {
				if (this.flightMod3 >= this.maxFlightMod3) {
					this.flightMod3 = this.maxFlightMod3;
				}

				if (this.flightCount3 > 2) {
					if (this.flightCount3 < this.maxFlightCount3) {
						this.flightMod3 += 0.65D;
						this.player.getEntity().motionY = 0.065D * this.flightMod3;
						this.flightCount3++;
					}
				} else {
					this.flightCount3++;
				}

			} else {
				this.flightMod3 = 1.0D;
			}

			if (this.player.getEntity().onGround) {
				this.flightCount3 = 0;
				this.flightMod3 = 2.0D;
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAscensiteSet()) {
			 if (this.player.isJumping()) {
				if (this.flightMod3 >= this.maxFlightMod3) {
					this.flightMod3 = this.maxFlightMod3;
				}

				if (this.flightCount3 > 2) {
					if (this.flightCount3 < this.maxFlightCount3) {
						this.flightMod3 += 0.68D;
						this.player.getEntity().motionY = 0.065D * this.flightMod3;
						this.flightCount3++;
					}
				} else {
					this.flightCount3++;
				}

			} else {
				this.flightMod3 = 1.0D;
			}

			if (this.player.getEntity().onGround) {
				this.flightCount3 = 0;
				this.flightMod3 = 2.0D;
			}
		}
	}

}