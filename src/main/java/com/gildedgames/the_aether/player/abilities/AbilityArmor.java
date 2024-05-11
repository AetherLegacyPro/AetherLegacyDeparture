package com.gildedgames.the_aether.player.abilities;

import java.util.Random;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.player.movement.AetherAmplifiedLiquidMovement;
import com.gildedgames.the_aether.player.movement.AetherLiquidMovement;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
		
		if (this.player.getAccessoryInventory().isWearingNeptuneSet()) {
			this.player_movement.onUpdate();
		}
		
		if (this.player.getAccessoryInventory().isWearingComboNeptuneSet()) {
			this.player_movement.onUpdate();
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedNeptuneSet()) {
			this.amplified_player_movement.onUpdate();
		}
		
		//Gravitite
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape)) && this.player.getAccessoryInventory().isWearingGravititeSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
			this.player.getEntity().motionY = 1.5D;
			this.jumpBoosted = true;			
			}
			
			this.player.getEntity().fallDistance = 0F;
		
		}
		
		else if (this.player.getAccessoryInventory().isWearingDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = 1.5D;
				this.jumpBoosted = true;
			}
			
			this.player.getEntity().fallDistance = 0F;
		}

		else if (this.player.getAccessoryInventory().isWearingGravititeSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = 1D;
				this.jumpBoosted = true;
				this.player.getEntity().fallDistance = 0F;
			}
			
		}
		
		else if (this.player.getAccessoryInventory().isWearingGravititeAndDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = 1D;
				this.jumpBoosted = true;
			}
			
			this.player.getEntity().fallDistance = 0F;
		}
		
		//Divineral
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape)) && this.player.getAccessoryInventory().isWearingDivineralSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
			this.player.getEntity().motionY = 2D;
			this.player.getEntity().fallDistance = 0F;
			this.jumpBoosted = true;
			}
		}
				
		//Continuum
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape)) && this.player.getAccessoryInventory().isWearingContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
			this.player.getEntity().motionY = 1D;
			this.jumpBoosted = true;
			}
			
			this.player.getEntity().fallDistance = 0F; 
		}
		
		//Continuum Combo
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape)) && this.player.getAccessoryInventory().isWearingContinuumComboSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
			this.player.getEntity().motionY = 1D;
			this.jumpBoosted = true;
			}
			
			this.player.getEntity().fallDistance = 0F; 
		}
		
		//Amplified Continuum
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape)) && this.player.getAccessoryInventory().isWearingAmplifiedContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
			this.player.getEntity().motionY = 1D;
			this.jumpBoosted = true;
			}
			
			this.player.getEntity().fallDistance = 0F; 
		}
		
		else if (this.player.getAccessoryInventory().isWearingContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingContinuumComboSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingAmplifiedContinuumSet()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .75D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingAgilityBoots()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingAmplifiedAgilityBoots()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingAgilityBootsAndCape()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		else if (this.player.getAccessoryInventory().isWearingAmplifiedAgilityBootsAndCape()) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
			}
		}
		
		//Aer Cape
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.aer_cape))) {
			if (this.player.isJumping() && !this.jumpBoosted) {
				this.player.getEntity().motionY = .5D;
				this.jumpBoosted = true;
										
			}
		}

		if (this.player.getEntity().isWet()) {
			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.phoenix_boots))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.obsidian_boots, 0);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.phoenix_leggings))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.obsidian_leggings, 1);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.phoenix_chestplate))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.obsidian_chestplate, 2);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.phoenix_helmet))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.obsidian_helmet, 3);
			}
					
			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_phoenix_boots))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.amplified_obsidian_boots, 0);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_phoenix_leggings))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.amplified_obsidian_leggings, 1);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_phoenix_chestplate))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.amplified_obsidian_chestplate, 2);
			}

			if (this.player.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_phoenix_helmet))) {
				this.damagePhoenixArmor(this.player.getEntity(), ItemsAether.amplified_obsidian_helmet, 3);
			}
		}

		if (this.player.getAccessoryInventory().isWearingPhoenixSet()) {
			this.player.getEntity().extinguish();
			this.player_movement.onUpdate();

			if (!this.player.getEntity().worldObj.isRemote) {
				((WorldServer) this.player.getEntity().worldObj).func_147487_a("flame", this.player.getEntity().posX + (this.random.nextGaussian() / 5D), this.player.getEntity().posY + (this.random.nextGaussian() / 5D), this.player.getEntity().posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedPhoenixSet()) {
			this.player.getEntity().extinguish();
			this.player_movement.onUpdate();

			if (!this.player.getEntity().worldObj.isRemote) {
				((WorldServer) this.player.getEntity().worldObj).func_147487_a("flame", this.player.getEntity().posX + (this.random.nextGaussian() / 5D), this.player.getEntity().posY + (this.random.nextGaussian() / 5D), this.player.getEntity().posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
				((WorldServer) this.player.getEntity().worldObj).func_147487_a("flame", this.player.getEntity().posX + (this.random.nextGaussian() / 3D), this.player.getEntity().posY + (this.random.nextGaussian() / 3D), this.player.getEntity().posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}
		
		if (this.player.getAccessoryInventory().isWearingAmplifiedObsidianSet()) {
			this.player.getEntity().extinguish();
			this.player_movement.onUpdate();
		}
		
		if (this.player.getAccessoryInventory().isWearingElysianSet()) {
			
		}
		
		/*if (this.player.getAccessoryInventory().isWearingAgilityBoots() || this.player.getAccessoryInventory().isWearingAgilityBootsAndCape()
			|| this.player.getAccessoryInventory().isWearingAmplifiedAgilityBoots() || this.player.getAccessoryInventory().isWearingAmplifiedAgilityBootsAndCape()) {						
			int rand = (int)(1 + Math.random() * 150);
			if (!this.player.getEntity().worldObj.isRemote && rand == 5) {
				((WorldServer) this.player.getEntity().worldObj).func_147487_a("explode", this.player.getEntity().posX + (this.random.nextGaussian() / 5D), this.player.getEntity().posY + (this.random.nextGaussian() / 5D), this.player.getEntity().posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}*/
		
		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.phoenix_cape))) {
			this.player.getEntity().extinguish();
			this.player_movement.onUpdate();

			if (!this.player.getEntity().worldObj.isRemote) {
				((WorldServer) this.player.getEntity().worldObj).func_147487_a("flame", this.player.getEntity().posX + (this.random.nextGaussian() / 5D), this.player.getEntity().posY + (this.random.nextGaussian() / 5D), this.player.getEntity().posZ + (this.random.nextGaussian() / 3D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		}

		if (!this.player.isJumping() && this.player.getEntity().onGround) {
			this.jumpBoosted = false;
		}
			

	}

	public void damagePhoenixArmor(Entity entity, Item outcome, int slot) {
		if (entity instanceof EntityLivingBase)
		{
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0)
			{
				EntityLivingBase entityLiving = (EntityLivingBase) entity;
				ItemStack stack = entityLiving.getEquipmentInSlot(slot + 1);

				stack.damageItem(1, entityLiving);

				if (stack.stackSize <= 0)
				{
					ItemStack outcomeStack = new ItemStack(outcome);

					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					entityLiving.setCurrentItemOrArmor(slot + 1, outcomeStack);
				}
			}
		}
	}

}