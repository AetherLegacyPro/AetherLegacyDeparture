package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;


public class AbilityAmplifiedAgilityBootsAndCape implements IAetherAbility {

	private final IPlayerAether player;

	private boolean invisibilityUpdate;

	private boolean stepUpdate;

	public AbilityAmplifiedAgilityBootsAndCape(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
	

	@Override
	public void onUpdate() {
	
		Entity entity = this.player.getEntity();

		if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_agility_boots)) 
			&& this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.agility_cape))) {
			EntityLivingBase entityLiving = (EntityLivingBase) entity;

			float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
			float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);

			if (entityLiving.isInWater()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.035F);
			}
			
			if (entityLiving.handleLavaMovement()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.065F);
			}
			
			if (entityLiving.isSprinting()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.10F);
			}
			
			if (entityLiving.isEntityAlive()) {
				entityLiving.moveFlying(movementLR, movementFB, 0.022F);
			}
			
		}
		
		if (this.player.getAccessoryInventory().isWearingPureSpeed()) {
				EntityLivingBase entityLiving = (EntityLivingBase) entity;

				float movementLR = this.negativeDifference(entityLiving, entityLiving.moveStrafing);
				float movementFB = this.negativeDifference(entityLiving, entityLiving.moveForward);

				if (entityLiving.isInWater()) {
					entityLiving.moveFlying(movementLR, movementFB, 0.035F);
				}
				
				if (entityLiving.handleLavaMovement()) {
					entityLiving.moveFlying(movementLR, movementFB, 0.065F);
				}
				
				if (entityLiving.isSprinting()) {
					entityLiving.moveFlying(movementLR, movementFB, 0.10F);
				}
				
				if (entityLiving.isEntityAlive()) {
					entityLiving.moveFlying(movementLR, movementFB, 0.022F);
				}
				
			}
		
		
	
		if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_agility_boots))) {
			if (!this.player.getEntity().isSneaking())
			{
				this.player.getEntity().stepHeight = 1.0F;
				this.stepUpdate = true;
			} else {
				if (this.stepUpdate) {
					this.player.getEntity().stepHeight = 0.5F;
					this.stepUpdate = false;
				}
			}
		} else {
			if (this.stepUpdate) {
				this.player.getEntity().stepHeight = 0.5F;
				this.stepUpdate = false;
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



