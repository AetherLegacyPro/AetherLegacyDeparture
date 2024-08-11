package com.gildedgames.the_aether.player.movement;

import com.gildedgames.the_aether.api.player.IPlayerAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AetherAmplifiedLiquidMovement {

	private IPlayerAether player;

	public AetherAmplifiedLiquidMovement(IPlayerAether player) {
		this.player = player;
	}

	public void onUpdate() {
		Entity entity = this.player.getEntity();

		if (entity instanceof EntityLivingBase entityLiving) {

			float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
			float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);

			if (entityLiving.isInWater()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.09F);
			}

			if (entityLiving.handleLavaMovement()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.07F);
			}
		}
	}

	public float negativeDifference(EntityLivingBase entity, float number) {
		if (number < 0.0F) {
			return number + 0.1F;
		} else if (number > 0.0F) {
			return number - 0.1F;
		} else {
			return 0.0F;
		}
	}

}
