package com.gildedgames.the_aether;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerbunny;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketSendEternalDay;
import com.gildedgames.the_aether.network.packets.PacketSendShouldCycle;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.world.AetherData;
import com.gildedgames.the_aether.world.AetherWorldProvider;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.portal.BlockAetherPortal;
import com.gildedgames.the_aether.entities.EntitiesAether;
import com.gildedgames.the_aether.entities.bosses.EntityEliteValkyrie;
import com.gildedgames.the_aether.entities.bosses.EntityElysianGuardian;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragonPart;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityDivineValkyrieQueen;
import com.gildedgames.the_aether.entities.hostile.EntityIrk;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;
import com.gildedgames.the_aether.entities.passive.mountable.EntityFlyingCow;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.dungeon.ItemDungeonKey;
import com.gildedgames.the_aether.items.util.EnumSkyrootBucketType;
import com.gildedgames.the_aether.items.weapons.ItemAmplifiedSkyrootSword;
import com.gildedgames.the_aether.items.weapons.ItemSkyrootSword;
import com.gildedgames.the_aether.items.weapons.tipped.ItemTippedSkyrootSword;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.Random;

public class
AetherEventHandler {

	@SubscribeEvent
	public void checkBlockBannedEvent(PlayerInteractEvent event) {
		if(event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) return;
		EntityPlayer player = event.entityPlayer;
		ItemStack currentStack = player.getCurrentEquippedItem();

		if (player.dimension == AetherConfig.getAetherDimensionID()) {
			if (currentStack != null) {
				if (currentStack.getItem() == Items.flint_and_steel || currentStack.getItem() == Item.getItemFromBlock(Blocks.torch) || currentStack.getItem() == Items.fire_charge) {
					for (int i = 0; i < 10; ++i) {
						event.world.spawnParticle("smoke", event.x, event.y, event.z, 0.0D, 0.0D, 0.0D);
					}

					event.setCanceled(true);
				}
			} else if (event.world.getBlock(event.x, event.y, event.z) == Blocks.bed) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		if (event.target instanceof EntityAgeable) {
			ItemStack itemstack = event.entityPlayer.inventory.getCurrentItem();

			if (itemstack != null && itemstack.getItem() == ItemsAether.aether_spawn_egg) {
				if (!event.entityPlayer.worldObj.isRemote) {
					Class<?> oclass = EntitiesAether.getClassFromID(itemstack.getItemDamage());

					if (oclass != null && oclass.isAssignableFrom(this.getClass())) {
						EntityAgeable entityageable = ((EntityAgeable) event.target).createChild((EntityAgeable) event.target);

						if (entityageable != null) {
							entityageable.setGrowingAge(-24000);
							entityageable.setLocationAndAngles(event.target.posX, event.target.posY, event.target.posZ, 0.0F, 0.0F);
							event.entityPlayer.worldObj.spawnEntityInWorld(entityageable);

							if (itemstack.hasDisplayName()) {
								entityageable.setCustomNameTag(itemstack.getDisplayName());
							}

							if (!event.entityPlayer.capabilities.isCreativeMode) {
								--itemstack.stackSize;

								if (itemstack.stackSize <= 0) {
									event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, (ItemStack) null);
								}
							}
						}
					}
				}
			}
		}

		if (event.target instanceof EntityCow || event.target instanceof EntityFlyingCow) {
			EntityPlayer player = event.entityPlayer;
			ItemStack heldItem = player.getCurrentEquippedItem();

			if (heldItem != null && heldItem.getItem() == ItemsAether.skyroot_bucket && EnumSkyrootBucketType.getType(heldItem.getItemDamage()) == EnumSkyrootBucketType.Empty) {
				if (!player.capabilities.isCreativeMode) {
					--heldItem.stackSize;
				}

				if (heldItem.stackSize <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ItemsAether.skyroot_bucket, 1, EnumSkyrootBucketType.Milk.meta));
				} else if (!player.inventory.addItemStackToInventory(new ItemStack(ItemsAether.skyroot_bucket, 1, EnumSkyrootBucketType.Milk.meta))) {
					player.dropPlayerItemWithRandomChoice(new ItemStack(ItemsAether.skyroot_bucket, 1, EnumSkyrootBucketType.Milk.meta), false);
				}
			}
		}
	}

	@SubscribeEvent
	public void onFillBucket(FillBucketEvent event) {
		World worldObj = event.world;
		MovingObjectPosition target = event.target;
		ItemStack stack = event.current;
		EntityPlayer player = event.entityPlayer;

		boolean isWater = (!AetherConfig.activateOnlyWithSkyroot() && stack.getItem() == Items.water_bucket) || (stack.getItem() == ItemsAether.skyroot_bucket && stack.getItemDamage() == 1);
		boolean isLava = stack.getItem() == Items.lava_bucket;

		boolean validDimension = (player.dimension == AetherConfig.getTravelDimensionID() || player.dimension == AetherConfig.getAetherDimensionID());

		if (target != null && target.typeOfHit == MovingObjectType.BLOCK && validDimension) {
			int i = target.blockX;
			int j = target.blockY;
			int k = target.blockZ;

			if (target.sideHit == 0) {
				--j;
			}

			if (target.sideHit == 1) {
				++j;
			}

			if (target.sideHit == 2) {
				--k;
			}

			if (target.sideHit == 3) {
				++k;
			}

			if (target.sideHit == 4) {
				--i;
			}

			if (isWater) {
				if (((BlockAetherPortal) BlocksAether.aether_portal).trySpawnPortal(worldObj, i, j, k)) {
					if (!player.capabilities.isCreativeMode) {
						if (stack.getItem() == ItemsAether.skyroot_bucket && stack.getItemDamage() == 1) {
							event.result = new ItemStack(ItemsAether.skyroot_bucket);
						}

						if (stack.getItem() == Items.water_bucket) {
							event.result = new ItemStack(Items.bucket);
						}
					}

					event.setResult(Event.Result.ALLOW);
				}
			}

			if (isLava && player.dimension == AetherConfig.getAetherDimensionID()) {
				if (player.capabilities.isCreativeMode && player.isSneaking()) {
					return;
				}

				if (worldObj.isAirBlock(i, j, k)) {
					worldObj.setBlock(i, j, k, BlocksAether.aerogel);

					if (!player.capabilities.isCreativeMode) {
						event.result = new ItemStack(Items.bucket);
					}
				}

				event.setResult(Event.Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		if (this.isGravititeTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.grav_tools);
		} else if (this.isSkyrootTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.skyroot_tools);
		} else if (this.isHolystoneTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.holystone_tools);
		} else if (this.isZaniteTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.zanite_tools);
		} else if (this.isArkeniumTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.arkenium_tools);
		} else if (this.isContinuumTool(event.crafting.getItem())) {
			event.player.triggerAchievement(AchievementsAether.continuum_tools);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(BlocksAether.enchanter)) {
			event.player.triggerAchievement(AchievementsAether.enchanter);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(BlocksAether.amplifier)) {
			event.player.triggerAchievement(AchievementsAether.amplifier);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(BlocksAether.freezer)) {
			event.player.triggerAchievement(AchievementsAether.freezer);
		} else if (event.crafting.getItem() == (ItemsAether.divineral_ingot)) {
			event.player.triggerAchievement(AchievementsAether.divineral);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(BlocksAether.aether_enchantment_table)) {
			event.player.triggerAchievement(AchievementsAether.aether_enchantment_table);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(BlocksAether.divineral_block)) {
			event.player.triggerAchievement(AchievementsAether.divineral_block);
		}
	}

	@SubscribeEvent
	public void onEntityDropLoot(LivingDropsEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityLivingBase entity = event.entityLiving;
			EntityDamageSource source = (EntityDamageSource) event.source;

			if (source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) source.getEntity();
				ItemStack currentItem = player.inventory.getCurrentItem();

				if (currentItem != null && currentItem.getItem() instanceof ItemSkyrootSword && currentItem.getItem() instanceof ItemTippedSkyrootSword && !(entity instanceof EntityPlayer) && !(entity instanceof EntityWither) && !(entity instanceof EntityValkyrie)) {
					for (EntityItem items : event.drops) {
						ItemStack stack = items.getEntityItem();

						if (!(stack.getItem() instanceof ItemDungeonKey) && stack.getItem() != ItemsAether.victory_medal && stack.getItem() != Items.skull) {
							EntityItem item = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, items.getEntityItem());

							entity.worldObj.spawnEntityInWorld(item);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityDropLoott(LivingDropsEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityLivingBase entity = event.entityLiving;
			EntityDamageSource source = (EntityDamageSource) event.source;

			if (source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) source.getEntity();
				ItemStack currentItem = player.inventory.getCurrentItem();

				if (currentItem != null && currentItem.getItem() instanceof ItemAmplifiedSkyrootSword && !(entity instanceof EntityPlayer) && !(entity instanceof EntityWither) && !(entity instanceof EntityValkyrie)) {
					for (EntityItem items : event.drops) {
						ItemStack stack = items.getEntityItem();

						if (!(stack.getItem() instanceof ItemDungeonKey) && stack.getItem() != ItemsAether.victory_medal && stack.getItem() != Items.skull) {
							EntityItem item = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, items.getEntityItem());

							entity.worldObj.spawnEntityInWorld(item);
						}
					} 
					
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityStruckByLightning(EntityStruckByLightningEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			if (player.inventory.getCurrentItem() != null)
			{
				if (player.inventory.getCurrentItem().getItem() == ItemsAether.lightning_sword || player.inventory.getCurrentItem().getItem() == ItemsAether.tipped_lightning_sword || player.inventory.getCurrentItem().getItem() == ItemsAether.lightning_knife || player.inventory.getCurrentItem().getItem() == ItemsAether.amplified_lightning_sword)
				{
					event.setCanceled(true);
				}
			}
		}
		
		else if (event.entity instanceof EntityZojz || event.entity instanceof EntityTempest || event.entity instanceof EntityEliteValkyrie 
			|| event.entity instanceof EntityDivineValkyrieQueen || event.entity instanceof EntityElysianGuardian 
			|| event.entity instanceof EntityIrk || event.entity instanceof EntityGenesisDragon 
			|| event.entity instanceof EntityGenesisDragonPart) {
			event.setCanceled(true);
		}
		
		if (event.entity instanceof EntityPlayer)
		{
			IPlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entity);

			if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.discharge_cape)))				
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onEntityDamage(LivingAttackEvent event)
	{
		if (event.entityLiving instanceof EntityAerbunny)
		{
			EntityAerbunny aerbunny = (EntityAerbunny) event.entityLiving;

			if (aerbunny.isRiding() && aerbunny.ridingEntity instanceof EntityPlayer)
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(AttackEntityEvent event)
	{
		if (event.entityPlayer.getHeldItem() != null)
		{
			if (event.entityPlayer.getHeldItem().getItem() == ItemsAether.flaming_sword)
			{
				if (event.target.canAttackWithItem())
				{
					if (!event.target.hitByEntity(event.entityPlayer))
					{
						if (event.target instanceof EntityLivingBase)
						{
							int defaultTime = 30;
							int fireAspectModifier = EnchantmentHelper.getFireAspectModifier(event.entityPlayer);
							if (fireAspectModifier > 0)
							{
								defaultTime += (fireAspectModifier * 4);
							}
							event.target.setFire(defaultTime);
						}
					}
				}
			}
			else if (event.entityPlayer.getHeldItem().getItem() == ItemsAether.pig_slayer)
			{
				String s = EntityList.getEntityString((Entity) event.target);

				if (s != null && (s.toLowerCase().contains("pig") || s.toLowerCase().contains("phyg") || s.toLowerCase().contains("taegore") || event.target.getUniqueID().toString().equals("1d680bb6-2a9a-4f25-bf2f-a1af74361d69"))) {
					if (event.target.worldObj.isRemote)
					{
						for (int j = 0; j < 20; j++) {
							Random itemRand = new Random();
							double d = itemRand.nextGaussian() * 0.02D;
							double d1 = itemRand.nextGaussian() * 0.02D;
							double d2 = itemRand.nextGaussian() * 0.02D;
							double d3 = 5D;
							event.target.worldObj.spawnParticle("flame", (event.target.posX + (double) (itemRand.nextFloat() * event.target.width * 2.0F)) - (double) event.target.width - d * d3, (event.target.posY + (double) (itemRand.nextFloat() * event.target.height)) - d1 * d3, (event.target.posZ + (double) (itemRand.nextFloat() * event.target.width * 2.0F)) - (double) event.target.width - d2 * d3, d, d1, d2);
						}
					}
				}
			}
			else if (event.entityPlayer.getHeldItem().getItem() == ItemsAether.dragon_bane || event.entityPlayer.getHeldItem().getItem() == ItemsAether.tipped_dragon_bane || event.entityPlayer.getHeldItem().getItem() == ItemsAether.amplified_dragon_bane)
			{
				String s = EntityList.getEntityString((Entity) event.target);

				if (s != null && (s.toLowerCase().contains("dragon") || s.toLowerCase().contains("EnderDragon") || s.toLowerCase().contains("crystal_dragon") || s.toLowerCase().contains("genesis_dragon") || s.toLowerCase().contains("reddragonrediscovered") || s.toLowerCase().contains("deepoid_dragon"))) {
					if (event.target.worldObj.isRemote)
					{
						for (int j = 0; j < 20; j++) {
							Random itemRand = new Random();
							double d = itemRand.nextGaussian() * 0.02D;
							double d1 = itemRand.nextGaussian() * 0.02D;
							double d2 = itemRand.nextGaussian() * 0.02D;
							double d3 = 5D;
							event.target.worldObj.spawnParticle("flame", (event.target.posX + (double) (itemRand.nextFloat() * event.target.width * 2.0F)) - (double) event.target.width - d * d3, (event.target.posY + (double) (itemRand.nextFloat() * event.target.height)) - d1 * d3, (event.target.posZ + (double) (itemRand.nextFloat() * event.target.width * 2.0F)) - (double) event.target.width - d2 * d3, d, d1, d2);
						}
					}
				}
			}
		}
	}

	public boolean isGravititeTool(Item stackID) {
		return stackID == ItemsAether.gravitite_shovel || stackID == ItemsAether.gravitite_axe || stackID == ItemsAether.gravitite_pickaxe || stackID == ItemsAether.gravitite_hoe;
	}
	
	public boolean isSkyrootTool(Item stackID) {
		return stackID == ItemsAether.skyroot_shovel || stackID == ItemsAether.skyroot_axe || stackID == ItemsAether.skyroot_pickaxe || stackID == ItemsAether.skyroot_hoe;
	}
	
	public boolean isHolystoneTool(Item stackID) {
		return stackID == ItemsAether.holystone_shovel || stackID == ItemsAether.holystone_axe || stackID == ItemsAether.holystone_pickaxe || stackID == ItemsAether.holystone_hoe;
	}
	
	public boolean isZaniteTool(Item stackID) {
		return stackID == ItemsAether.zanite_shovel || stackID == ItemsAether.zanite_axe || stackID == ItemsAether.zanite_pickaxe || stackID == ItemsAether.zanite_hoe;
	}
	
	public boolean isArkeniumTool(Item stackID) {
		return stackID == ItemsAether.arkenium_shovel || stackID == ItemsAether.arkenium_axe || stackID == ItemsAether.arkenium_pickaxe || stackID == ItemsAether.arkenium_hoe;
	}
	
	public boolean isContinuumTool(Item stackID) {
		return stackID == ItemsAether.continuum_shovel || stackID == ItemsAether.continuum_axe || stackID == ItemsAether.continuum_pickaxe || stackID == ItemsAether.continuum_hoe;
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		if (!event.world.isRemote)
		{
			AetherData data = AetherData.getInstance(event.world);

			WorldProvider provider = event.world.provider;

			if (provider instanceof AetherWorldProvider)
			{
				AetherWorldProvider providerAether = (AetherWorldProvider) provider;

				providerAether.setIsEternalDay(data.isEternalDay());
				AetherNetwork.sendToAll(new PacketSendEternalDay(providerAether.getIsEternalDay()));

				providerAether.setShouldCycleCatchup(data.isShouldCycleCatchup());
				AetherNetwork.sendToAll(new PacketSendShouldCycle(providerAether.getShouldCycleCatchup()));
			}
		}

		for (Object entity : event.world.loadedEntityList)
		{
			if (entity instanceof EntityItem)
			{
				EntityItem entityItem = (EntityItem) entity;

				if (entityItem.getEntityItem().getItem() == ItemsAether.dungeon_key)
				{
					ObfuscationReflectionHelper.setPrivateValue(Entity.class, entityItem, true, "invulnerable", "field_83001_bt");
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerWakeUpEvent event)
	{
		final World world = event.entityPlayer.worldObj;

		if (!world.isRemote && event.entityPlayer.dimension == AetherConfig.getAetherDimensionID())
		{
			final MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

			final WorldServer worldServer = server.worldServerForDimension(0);

			if (worldServer.playerEntities.size() > 0)
			{
				if (worldServer.areAllPlayersAsleep())
				{
					performTimeSet(event, world, worldServer);
				}
			}
			else
			{
				performTimeSet(event, world, worldServer);
			}
		}
	}

	@SubscribeEvent
	public void onFall(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			IPlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.sentry_boots))
					|| playerAether.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.scaled_sentry_boots)) 
					|| playerAether.getAccessoryInventory().wearingArmor(new ItemStack(ItemsAether.amplified_sentry_boots)) 
					|| (playerAether.getAccessoryInventory().isWearingGravititeSet()) 
					|| (playerAether.getAccessoryInventory().isWearingValkyrieSet())
					|| (playerAether.getAccessoryInventory().isWearingValkyrieComboSet())
					|| (playerAether.getAccessoryInventory().isWearingValkyrieRingAndAmplifiedArmor())
					|| (playerAether.getAccessoryInventory().isWearingAmplifiedValkyrieRingAndAmplifiedArmor())
					|| (playerAether.getAccessoryInventory().isWearingAmplifiedValkyrieSet()))
			{
				event.setCanceled(true);
			}
		}
	}

	private void performTimeSet(PlayerWakeUpEvent event, World world, WorldServer worldServer)
	{
		if (world.getGameRules().getGameRuleBooleanValue("doDaylightCycle") && event.entityPlayer.isPlayerFullyAsleep())
		{
			final long i = worldServer.getWorldInfo().getWorldTime() + 24000L;

			worldServer.getWorldInfo().setWorldTime(i - i % 24000L);

			PlayerAether.get(event.entityPlayer).setBedLocation(event.entityPlayer.getBedLocation(AetherConfig.getAetherDimensionID()));
		}
	}
}
