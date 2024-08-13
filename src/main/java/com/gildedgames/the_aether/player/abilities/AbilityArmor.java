package com.gildedgames.the_aether.player.abilities;

import java.util.Random;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.movement.AetherAmplifiedLiquidMovement;
import com.gildedgames.the_aether.player.movement.AetherLiquidMovement;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

public class AbilityArmor implements IAetherAbility {

	private final AetherLiquidMovement player_movement;
	private final AetherAmplifiedLiquidMovement amplified_player_movement;

	private final Random random = new Random();

	private final IPlayerAether player;

	private boolean jumpBoosted;

	public AbilityArmor(IPlayerAether player) {
		this.player = player;
		this.player_movement = new AetherLiquidMovement(player);
		this.amplified_player_movement = new AetherAmplifiedLiquidMovement(player);
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {

		IAccessoryInventory accessoryInventory = player.getAccessoryInventory();

		EntityPlayer playerEntity = player.getEntity();

		if (accessoryInventory.isWearingNeptuneSet()) {
			this.player_movement.onUpdate();
		}
		
		if (accessoryInventory.isWearingComboNeptuneSet()) {
			this.player_movement.onUpdate();
		}
		
		if (accessoryInventory.isWearingAmplifiedNeptuneSet()) {
			this.amplified_player_movement.onUpdate();
		}
		
		//Gravitite
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape) && accessoryInventory.isWearingGravititeSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1.5D;
			this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		
		}
		
		else if (accessoryInventory.isWearingDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1.5D;
				this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		}

		else if (accessoryInventory.isWearingGravititeSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1D;
				this.jumpBoosted = true;
				playerEntity.fallDistance = 0F;
			}
			
		}
		
		else if (accessoryInventory.isWearingGravititeAndDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1D;
				this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		}
		
		//Divineral
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape) && accessoryInventory.isWearingDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 2D;
				playerEntity.fallDistance = 0F;
			this.jumpBoosted = true;
			}
		}
				
		//Continuum
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape) && accessoryInventory.isWearingContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1D;
			this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		}
		
		//Continuum Combo
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape) && accessoryInventory.isWearingContinuumComboSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1D;
			this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		}
		
		//Amplified Continuum
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape) && accessoryInventory.isWearingAmplifiedContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = 1D;
			this.jumpBoosted = true;
			}

			playerEntity.fallDistance = 0F;
		}
		
		else if (accessoryInventory.isWearingContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (accessoryInventory.isWearingContinuumComboSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (accessoryInventory.isWearingAmplifiedContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .75D;
				this.jumpBoosted = true;
			}
		}
		
		else if (accessoryInventory.isWearingAgilityBoots()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (accessoryInventory.isWearingAmplifiedAgilityBoots()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		//Aer Cape
		else if (accessoryInventory.wearingAccessory(ItemsAether.aer_cape)) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				playerEntity.motionY = .5D;
				this.jumpBoosted = true;
										
			}
		}

		if (playerEntity.isWet()) {
			if (accessoryInventory.wearingArmor(ItemsAether.phoenix_boots)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.obsidian_boots, 0);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.phoenix_leggings)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.obsidian_leggings, 1);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.phoenix_chestplate)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.obsidian_chestplate, 2);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.phoenix_helmet)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.obsidian_helmet, 3);
			}
					
			if (accessoryInventory.wearingArmor(ItemsAether.amplified_phoenix_boots)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.amplified_obsidian_boots, 0);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.amplified_phoenix_leggings)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.amplified_obsidian_leggings, 1);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.amplified_phoenix_chestplate)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.amplified_obsidian_chestplate, 2);
			}

			if (accessoryInventory.wearingArmor(ItemsAether.amplified_phoenix_helmet)) {
				this.damagePhoenixArmor(playerEntity, ItemsAether.amplified_obsidian_helmet, 3);
			}
		}

		if (this.player.getAccessoryInventory().isWearingPhoenixSet()) {
			playerEntity.extinguish();
			this.player_movement.onUpdate();

			if (!playerEntity.worldObj.isRemote) {
				((WorldServer) playerEntity.worldObj).func_147487_a("flame", playerEntity.posX + (this.random.nextGaussian() / 5D), playerEntity.posY + (this.random.nextGaussian() / 5D), playerEntity.posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedPhoenixSet()) {
			playerEntity.extinguish();
			this.player_movement.onUpdate();

			if (!playerEntity.worldObj.isRemote) {
				((WorldServer) playerEntity.worldObj).func_147487_a("flame", playerEntity.posX + (this.random.nextGaussian() / 5D), playerEntity.posY + (this.random.nextGaussian() / 5D), playerEntity.posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
				((WorldServer) playerEntity.worldObj).func_147487_a("flame", playerEntity.posX + (this.random.nextGaussian() / 3D), playerEntity.posY + (this.random.nextGaussian() / 3D), playerEntity.posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedObsidianSet()) {
			playerEntity.extinguish();
			this.player_movement.onUpdate();
		}
		
		if (this.player.getAccessoryInventory().isWearingElysianSet()) {
			
		}
		
		/*if (this.player.getAccessoryInventory().isWearingAgilityBoots() || this.player.getAccessoryInventory().isWearingAgilityBootsAndCape()
			|| this.player.getAccessoryInventory().isWearingAmplifiedAgilityBoots() || this.player.getAccessoryInventory().isWearingAmplifiedAgilityBootsAndCape()) {						
			int rand = (int)(1 + Math.random() * 150);
			if (!playerEntity.worldObj.isRemote && rand == 5) {
				((WorldServer) playerEntity.worldObj).func_147487_a("explode", playerEntity.posX + (this.random.nextGaussian() / 5D), playerEntity.posY + (this.random.nextGaussian() / 5D), playerEntity.posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}*/
		
		if (this.player.getAccessoryInventory().wearingAccessory(ItemsAether.phoenix_cape)) {
			playerEntity.extinguish();
			this.player_movement.onUpdate();

			if (!playerEntity.worldObj.isRemote) {
				((WorldServer) playerEntity.worldObj).func_147487_a("flame", playerEntity.posX + (this.random.nextGaussian() / 5D), playerEntity.posY + (this.random.nextGaussian() / 5D), playerEntity.posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (!this.player.isJumping() && playerEntity.onGround) {
			this.jumpBoosted = false;
		}

	}

	public void damagePhoenixArmor(Entity entity, Item outcome, int slot) {
		if (entity instanceof EntityLivingBase) {
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0) {
				EntityLivingBase entityLiving = (EntityLivingBase) entity;
				ItemStack stack = entityLiving.getEquipmentInSlot(slot + 1);

				stack.damageItem(1, entityLiving);

				if (stack.stackSize <= 0) {
					ItemStack outcomeStack = new ItemStack(outcome);

					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					entityLiving.setCurrentItemOrArmor(slot + 1, outcomeStack);
				}
			}
		}
	}

}