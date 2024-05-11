package com.gildedgames.the_aether.player;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.inventory.InventoryAccessories;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.achievements.AetherAchievement;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;

import com.gildedgames.the_aether.entities.util.EntityHook;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketAccessory;
import com.gildedgames.the_aether.network.packets.PacketAchievement;
import com.gildedgames.the_aether.player.abilities.AbilityRepulsion;
import com.gildedgames.the_aether.player.abilities.AbilityZaniteShield;
import com.gildedgames.the_aether.player.abilities.AbilityGravititeShield;
import com.gildedgames.the_aether.player.abilities.AbilityJebShield;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class PlayerAetherEvents {
	
	@SubscribeEvent
	public void onPlayerAetherConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			event.entity.registerExtendedProperties("aether_legacy:player_aether", new PlayerAether());
		} else if (event.entity instanceof EntityLivingBase) {
			event.entity.registerExtendedProperties("aether_legacy:entity_hook", new EntityHook());
		}
	}

	@SubscribeEvent
	public void onPlayerAetherLoggedIn(PlayerLoggedInEvent event) {
		if (!event.player.worldObj.isRemote) {
			PlayerAether playerAether = PlayerAether.get(event.player);

			AetherNetwork.sendTo(new PacketAccessory(playerAether), (EntityPlayerMP) event.player);
			playerAether.updateShardCount(0);
			playerAether.updatePowerShardCount(0);
			playerAether.updateDexShardCount(0);

			if (!AetherConfig.shouldAetherStart())
			{
				playerAether.shouldGetPortal = false;
			}
			else
			{
				playerAether.givePortalFrame();
			}
		}
	}

	@SubscribeEvent
	public void onPlayerAetherClone(Clone event) {
		PlayerAether original = PlayerAether.get(event.original);
		PlayerAether originall = PlayerAether.get(event.original);
		PlayerAether playerAether = PlayerAether.get(event.entityPlayer);

		playerAether.shardCount = original.shardCount;
		playerAether.powerCount = original.powerCount;
		playerAether.dexCount = original.dexCount;

		if (!event.wasDeath || event.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			playerAether.setAccessoryInventory(original.getAccessoryInventory());
		}

		if (event.wasDeath)
		{
			playerAether.setBedLocation(original.getBedLocation());
		}
	}

	@SubscribeEvent
	public void onPlayerAetherRespawn(PlayerRespawnEvent event) {
		if (!event.player.worldObj.isRemote) {
			PlayerAether playerAether = PlayerAether.get(event.player);

			AetherNetwork.sendTo(new PacketAccessory(playerAether), (EntityPlayerMP) event.player);
			
			playerAether.updateShardCount(0);
			event.player.setHealth(event.player.getMaxHealth());
			
			playerAether.updatePowerShardCount(0);
			
			playerAether.updateDexShardCount(0);
			event.player.setAIMoveSpeed(event.player.getAIMoveSpeed());
			
			playerAether.isPoisoned = false;
			playerAether.poisonTime = 0;
			playerAether.isCured = false;
			playerAether.cureTime = 0;
		}
	}

	@SubscribeEvent
	public void onPlayerAetherChangedDimension(PlayerChangedDimensionEvent event) {
		if (!event.player.worldObj.isRemote) {
			PlayerAether playerAether = PlayerAether.get(event.player);

			AetherNetwork.sendTo(new PacketAccessory(playerAether), (EntityPlayerMP) event.player);
			playerAether.updateShardCount(0);
			playerAether.updatePowerShardCount(0);
			playerAether.updateDexShardCount(0);
		}
	}

	@SubscribeEvent
	public void onPlayerAetherDrops(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntityPlayer && !event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			PlayerAether.get((EntityPlayer) event.entityLiving).getAccessoryInventory().dropAccessories();
		}
	}

	@SubscribeEvent
	public void onPlayerAetherUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether.get((EntityPlayer) event.entityLiving).onUpdate();
			;
		} else if (event.entityLiving instanceof EntityLivingBase) {
			((EntityHook) event.entityLiving.getExtendedProperties("aether_legacy:entity_hook")).onUpdate();
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().isWearingObsidianSet()) {
				float original = event.ammount;

				event.ammount = original / 3;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt5(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().isWearingAmplifiedObsidianSet()) {
				float original = event.ammount;

				event.ammount = original / 4;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt6(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().isWearingObsidianComboSet()) {
				float original = event.ammount;

				event.ammount = original / 3;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt7(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer && Loader.isModLoaded("etfuturum")) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.netherite_ring)) || playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.netherite_pendant))) {
				float original = event.ammount;

				event.ammount = (float) (original / 1.05);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt8(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer && Loader.isModLoaded("etfuturum")) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.netherite_ring)) && playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.netherite_pendant))) {
				float original = event.ammount;

				event.ammount = (float) (original / 1.1);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt9(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.divineral_ring)) || playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.divineral_pendant))) {
				float original = event.ammount;

				event.ammount = (float) (original / 1.1);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt10(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.divineral_ring)) && playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.divineral_pendant))) {
				float original = event.ammount;

				event.ammount = (float) (original / 1.2);
			}
		}
	}
		
	@SubscribeEvent
	public void onLivingHurt2(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.jeb_shield))) {
				float original = event.ammount;

				event.ammount = original / 2;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt3(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.zanite_shield))) {
				float original = event.ammount;

				event.ammount = original - 1;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt4(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.gravitite_shield))) {
				float original = event.ammount;

				event.ammount = original - 2;
			}
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.sentry_shield)) && event.source.isExplosion()) {              
                event.setCanceled(true);
                playerAether.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.sentry_shield));             
             }
			
			if (playerAether.getAccessoryInventory().isWearingPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} if (playerAether.getAccessoryInventory().isWearingAmplifiedPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} if (playerAether.getAccessoryInventory().isWearingPhoenixComboSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {			
				 event.setCanceled(((AbilityRepulsion) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source));
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {			
				 event.setCanceled(((AbilityGravititeShield) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source));
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {			
				 event.setCanceled(((AbilityZaniteShield) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source));
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {			
				 event.setCanceled(((AbilityJebShield) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source));
				 
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingAmplifiedPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixComboSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {
				event.setCanceled(((AbilityGravititeShield) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source)); 
				event.setCanceled(true);
			
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingAmplifiedPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixComboSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAbilities().get(3).shouldExecute()) {			 	 
				 event.setCanceled(((AbilityZaniteShield) playerAether.getAbilities().get(3)).onPlayerAttacked(event.source));
				 event.setCanceled(true);
				 
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingAmplifiedPhoenixSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			} else if (playerAether.getAccessoryInventory().isWearingPhoenixComboSet() && event.source.isFireDamage()) {
				event.setCanceled(true);
			}
		}
	}
	
	

	@SubscribeEvent
	public void onUpdateBreakSpeed(BreakSpeed event) {
		((InventoryAccessories) PlayerAether.get(event.entityPlayer).getAccessoryInventory()).getCurrentPlayerStrVsBlock(event.newSpeed);
	}

	@SubscribeEvent
	public void onAchievementGet(AchievementEvent event) {
		Achievement achievement = event.achievement;
		EntityPlayer player = event.entityPlayer;

		if (!(achievement instanceof AetherAchievement)) {
			return;
		}

		int achievementType = achievement == AchievementsAether.defeat_bronze ? 1 : achievement == AchievementsAether.defeat_silver ? 2 : 0;

		if (!player.worldObj.isRemote && ((EntityPlayerMP) player).func_147099_x().canUnlockAchievement(achievement) && !((EntityPlayerMP) player).func_147099_x().hasAchievementUnlocked(achievement)) {
			if (event.achievement == AchievementsAether.enter_aether) {
				if (!player.inventory.addItemStackToInventory(new ItemStack(ItemsAether.lore_book))) {
					player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(ItemsAether.lore_book)));
				}
				if (AetherConfig.disableParachutes() == false) {
				if (!player.inventory.addItemStackToInventory(new ItemStack(ItemsAether.golden_parachute))) {
					player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(ItemsAether.golden_parachute)));
					}
				}	
			}

			AetherNetwork.sendTo(new PacketAchievement(achievementType), (EntityPlayerMP) player);
		}
	}

}