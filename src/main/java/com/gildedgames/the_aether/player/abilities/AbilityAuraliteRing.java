package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.entity.EntityLivingBase;

public class AbilityAuraliteRing implements IAetherAbility {

	private final IPlayerAether player;

	public AbilityAuraliteRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
	

	@Override
	public void onUpdate() {
	
		EntityLivingBase entityLiving = this.player.getEntity();

		if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.auralite_ring) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.auralite_pendant) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.reinforced_auralite_pendant)) {

			float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
			float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);			
			
			if (entityLiving.isInWater()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.005F);
			}
			
			if (entityLiving.isSprinting()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.015F);
			}
			
			if (entityLiving.isEntityAlive()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.006F);
					}
			
				}
		else if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.auralite_ring) && (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.auralite_pendant) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.reinforced_auralite_pendant))) {

			float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
			float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);			
						
			if (entityLiving.isInWater()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.005F);
			}
			
			if (entityLiving.isSprinting()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.025F);
			}
			
			if (entityLiving.isEntityAlive()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.01F);
					}
			
				}
		
		else if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.amplified_auralite_pendant)) {

			float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
			float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);			
						
			if (entityLiving.isInWater()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.01F);
			}
			
			if (entityLiving.isSprinting()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.035F);
			}
			
			if (entityLiving.isEntityAlive()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.02F);
					}
			
				}
		
			}
		
	public float negativeDifference(EntityLivingBase entity, float number) {
		if (number < 0.0F) {
			return number + 0.15F;
		} else if (number > 0.0F) {
			return number - 0.15F;
		} else {
			return 0.0F;
		}
	 }
				

	}
