package com.gildedgames.the_aether.player;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.achievements.AetherAchievement;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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

			if (!AetherConfig.shouldAetherStart()) {
				playerAether.shouldGetPortal = false;
			} else {
				playerAether.givePortalFrame();
			}
			if (event.player.worldObj.isRemote) { 
		     if (AetherConfig.enable_assets_message) {
			  EntityPlayer player = event.player;
			  if(System.getProperty("os.name").toLowerCase().contains("windows"))
			  	player.addChatMessage(new ChatComponentText(I18n.format("gui.enable_assets")));
			  else
			  	player.addChatMessage(new ChatComponentText(I18n.format("gui.enable_assets2")));
			
			  AetherConfig.enable_assets_message = false;
		    } 
		  }
		}
	}

	@SubscribeEvent
	public void onPlayerAetherClone(Clone event) {
		PlayerAether original = PlayerAether.get(event.original);
		PlayerAether playerAether = PlayerAether.get(event.entityPlayer);

		playerAether.shardCount = original.shardCount;
		playerAether.powerCount = original.powerCount;
		playerAether.dexCount = original.dexCount;

		if (!event.wasDeath || event.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			playerAether.setAccessoryInventory(original.getAccessoryInventory());
		}

		if (event.wasDeath) {
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
		} else if (event.entityLiving != null) {
			((EntityHook) event.entityLiving.getExtendedProperties("aether_legacy:entity_hook")).onUpdate();
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if(!(event.entityLiving instanceof EntityPlayer player)) {
			return;
		}
		IAccessoryInventory accessoryInventory = PlayerAether.get(player).getAccessoryInventory();
		float reducedDamage = event.ammount;

		//Armor sets
		if(accessoryInventory.isWearingObsidianSet()) {
			reducedDamage = reducedDamage / 3f;
		}
		if (accessoryInventory.isWearingAmplifiedObsidianSet()) {
			reducedDamage = reducedDamage * 0.25f;
		}
		if (accessoryInventory.isWearingObsidianComboSet()) {
			reducedDamage = reducedDamage / 3f;
		}

		//Shields
		if (accessoryInventory.wearingAccessory(ItemsAether.jeb_shield)) {
			reducedDamage = reducedDamage * 0.5f;
		}
		if (accessoryInventory.wearingAccessory(ItemsAether.gravitite_shield)) {
			reducedDamage = reducedDamage - 2f;
		}
		if (accessoryInventory.wearingAccessory(ItemsAether.zanite_shield)) {
			reducedDamage = reducedDamage - 1f;
		}

		//Other accessories
		if (accessoryInventory.wearingAccessory(ItemsAether.divineral_ring) || accessoryInventory.wearingAccessory(ItemsAether.divineral_pendant)) {
			reducedDamage = reducedDamage * 0.9f;
		}
		if (accessoryInventory.wearingAccessory(ItemsAether.divineral_ring) && accessoryInventory.wearingAccessory(ItemsAether.divineral_pendant)) {
			reducedDamage = reducedDamage * 0.8f;
		}

		//Et Futurum
		if(Loader.isModLoaded("etfuturum")) {
			if(accessoryInventory.wearingAccessory(ItemsAether.netherite_ring) || accessoryInventory.wearingAccessory(ItemsAether.netherite_pendant)) {
				reducedDamage = reducedDamage / 1.05f;
			}
			if(accessoryInventory.wearingAccessory(ItemsAether.netherite_ring) && accessoryInventory.wearingAccessory(ItemsAether.netherite_pendant)) {
				reducedDamage = reducedDamage * 0.9f;
			}
		}

		event.ammount = reducedDamage;
	}


	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer player)) {
			return;
		}
		PlayerAether playerAether = PlayerAether.get(player);
		IAccessoryInventory accessoryInventory = playerAether.getAccessoryInventory();

		if(event.source.isFireDamage()) {
			if (accessoryInventory.isWearingPhoenixSet()
			|| accessoryInventory.isWearingAmplifiedPhoenixSet()
			|| accessoryInventory.isWearingPhoenixComboSet()) {
				event.setCanceled(true);
				return;
			}
		}

		if (event.source.isExplosion() && accessoryInventory.wearingAccessory(ItemsAether.sentry_shield)) {
			accessoryInventory.damageWornItem(1, ItemsAether.sentry_shield);
			event.setCanceled(true);
			return;
		}

		IAetherAbility ability = playerAether.getAbilities().get(3);
		if (ability.shouldExecute() && ability.onPlayerAttacked(event.source)) {
			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void onUpdateBreakSpeed(BreakSpeed event) {
		PlayerAether.get(event.entityPlayer).getAccessoryInventory().getCurrentPlayerStrVsBlock(event.newSpeed);
	}

	@SubscribeEvent
	public void onAchievementGet(AchievementEvent event) {
		Achievement achievement = event.achievement;
		if (!(achievement instanceof AetherAchievement)) {
			return;
		}
		EntityPlayer player = event.entityPlayer;

		int achievementType = achievement == AchievementsAether.defeat_bronze ? 1 : achievement == AchievementsAether.defeat_silver ? 2 : 0;

		if (!player.worldObj.isRemote && ((EntityPlayerMP) player).func_147099_x().canUnlockAchievement(achievement) && !((EntityPlayerMP) player).func_147099_x().hasAchievementUnlocked(achievement)) {
			if (event.achievement == AchievementsAether.enter_aether) {
				ItemStack loreBookStack = new ItemStack(ItemsAether.lore_book);
				if (!player.inventory.addItemStackToInventory(loreBookStack)) {
					player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, loreBookStack));
				}
				if (!AetherConfig.disableParachutes()) {
					ItemStack goldenParachuteStack = new ItemStack(ItemsAether.golden_parachute);
					if (!player.inventory.addItemStackToInventory(goldenParachuteStack)) {
						player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, goldenParachuteStack));
					}
				}	
			}

			AetherNetwork.sendTo(new PacketAchievement(achievementType), (EntityPlayerMP) player);
		}
	}

}