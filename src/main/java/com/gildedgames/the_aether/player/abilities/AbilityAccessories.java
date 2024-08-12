package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

public class AbilityAccessories implements IAetherAbility {

	private final IPlayerAether player;

	private boolean invisibilityUpdate;

	private boolean stepUpdate;

	public AbilityAccessories(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		final IAccessoryInventory accessoryInventory = player.getAccessoryInventory();
		final EntityLivingBase playerEntity = player.getEntity();
		final float ticksExisted = playerEntity.ticksExisted;
		if (ticksExisted % 400 == 0) {
			accessoryInventory.damageWornItemsAtRate(DegradationRate.VERY_FAST);
		}
		if (ticksExisted % 1200 == 0) {
			accessoryInventory.damageWornItemsAtRate(DegradationRate.FAST);
		}
		if (ticksExisted % 2000 == 0) {
			accessoryInventory.damageWornItemsAtRate(DegradationRate.SLOW);
		}
		if (ticksExisted % 2500 == 0) {
			accessoryInventory.damageWornItemsAtRate(DegradationRate.VERY_SLOW);
		}

		if (!playerEntity.worldObj.isRemote && (accessoryInventory.wearingAccessory(ItemsAether.ice_ring) || accessoryInventory.wearingAccessory(ItemsAether.ice_pendant))) {
			int i = MathHelper.floor_double(playerEntity.posX);
			int j = MathHelper.floor_double(playerEntity.boundingBox.minY);
			int k = MathHelper.floor_double(playerEntity.posZ);

			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					for (int z = k - 1; z <= k + 1; z++) {
						Block block = playerEntity.worldObj.getBlock(x, y, z);
						Block setBlock = (block == Blocks.water || block == Blocks.flowing_water) ? Blocks.ice : (block == Blocks.lava || block == Blocks.flowing_lava) ? Blocks.obsidian : null;

						if (setBlock != null) {
							playerEntity.worldObj.setBlock(x, y, z, setBlock);
							accessoryInventory.damageWornItem(1, ItemsAether.ice_ring);
							accessoryInventory.damageWornItem(1, ItemsAether.ice_pendant);
						}
					}
				}
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.iron_bubble) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_iron_bubble) || accessoryInventory.wearingAccessory(ItemsAether.amplified_iron_bubble)) {
			playerEntity.setAir(0);
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.agility_cape)) {
			if (!playerEntity.isSneaking()) {
				playerEntity.stepHeight = 1.0F;
				stepUpdate = true;
			} else {
				if (stepUpdate) {
					playerEntity.stepHeight = 0.5F;
					stepUpdate = false;
				}
			}
		} else {
			if (stepUpdate) {
				playerEntity.stepHeight = 0.5F;
				stepUpdate = false;
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.invisibility_cape)) {
			playerEntity.setInvisible(true);
			invisibilityUpdate = true;
		} else if (!accessoryInventory.wearingAccessory(ItemsAether.invisibility_cape) && !playerEntity.isPotionActive(Potion.invisibility)) {
			if (invisibilityUpdate) {
				playerEntity.setInvisible(false);
				invisibilityUpdate = false;
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.regeneration_stone) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_regeneration_stone)) {
			if (playerEntity.getHealth() < playerEntity.getMaxHealth() && playerEntity.getActivePotionEffect(Potion.regeneration) == null) {
				playerEntity.addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 0));
			}
		}

		if(ticksExisted % 5 == 0 && playerEntity.isWet()) {
			accessoryInventory.damageWornItem(1, ItemsAether.phoenix_gloves, ItemsAether.obsidian_gloves);
			accessoryInventory.damageWornItem(1, ItemsAether.amplified_phoenix_gloves, ItemsAether.amplified_obsidian_gloves);
		}


		if (accessoryInventory.wearingAccessory(ItemsAether.golden_feather) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_golden_feather) || accessoryInventory.wearingAccessory(ItemsAether.amplified_golden_feather) || player.getAccessoryInventory().wearingAccessory(ItemsAether.valkyrie_cape)) {
			if (!playerEntity.onGround && playerEntity.motionY < 0.0D && !playerEntity.isInWater() && !playerEntity.isSneaking()) {
				playerEntity.motionY *= 0.59999999999999998D;
			}

			playerEntity.fallDistance = -1F;
		}
	}

}
