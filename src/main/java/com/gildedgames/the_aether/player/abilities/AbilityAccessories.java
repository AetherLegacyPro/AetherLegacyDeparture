package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
		final float ticksExisted = player.getEntity().ticksExisted;
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

		if (!player.getEntity().worldObj.isRemote && (accessoryInventory.wearingAccessory(ItemsAether.ice_ring) || accessoryInventory.wearingAccessory(ItemsAether.ice_pendant))) {
			int i = MathHelper.floor_double(player.getEntity().posX);
			int j = MathHelper.floor_double(player.getEntity().boundingBox.minY);
			int k = MathHelper.floor_double(player.getEntity().posZ);

			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					for (int z = k - 1; z <= k + 1; z++) {
						Block block = player.getEntity().worldObj.getBlock(x, y, z);
						Block setBlock = (block == Blocks.water || block == Blocks.flowing_water) ? Blocks.ice : (block == Blocks.lava || block == Blocks.flowing_lava) ? Blocks.obsidian : null;

						if (setBlock != null) {
							player.getEntity().worldObj.setBlock(x, y, z, setBlock);
							accessoryInventory.damageWornItem(1, ItemsAether.ice_ring);
							accessoryInventory.damageWornItem(1, ItemsAether.ice_pendant);
						}
					}
				}
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.iron_bubble) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_iron_bubble) || accessoryInventory.wearingAccessory(ItemsAether.amplified_iron_bubble)) {
			player.getEntity().setAir(0);
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.agility_cape)) {
			if (!player.getEntity().isSneaking()) {
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

		if (accessoryInventory.wearingAccessory(ItemsAether.invisibility_cape)) {
			this.player.getEntity().setInvisible(true);
			this.invisibilityUpdate = true;
		} else if (!accessoryInventory.wearingAccessory(ItemsAether.invisibility_cape) && !player.getEntity().isPotionActive(Potion.invisibility)) {
			if (this.invisibilityUpdate) {
				this.player.getEntity().setInvisible(false);
				this.invisibilityUpdate = false;
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.regeneration_stone) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_regeneration_stone)) {
			if (this.player.getEntity().getHealth() < this.player.getEntity().getMaxHealth() && this.player.getEntity().getActivePotionEffect(Potion.regeneration) == null) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 0));
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.phoenix_gloves) && player.getEntity().isWet()) {
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0) {
				ItemStack stack = accessoryInventory.getStackInSlot(AccessoryType.GLOVES);

				accessoryInventory.damageWornItem(1, stack.getItem());

				if (accessoryInventory.getStackInSlot(AccessoryType.GLOVES) == null) {
					ItemStack outcomeStack = new ItemStack(ItemsAether.obsidian_gloves);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					accessoryInventory.setAccessorySlot(AccessoryType.GLOVES, outcomeStack);
				}
			}
		}
		
		if (accessoryInventory.wearingAccessory(ItemsAether.amplified_phoenix_gloves) && this.player.getEntity().isWet()) {
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0) {
				ItemStack stack = accessoryInventory.getStackInSlot(AccessoryType.GLOVES);

				accessoryInventory.damageWornItem(1, stack.getItem());

				if (accessoryInventory.getStackInSlot(AccessoryType.GLOVES) == null) {
					ItemStack outcomeStack = new ItemStack(ItemsAether.amplified_obsidian_gloves);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					accessoryInventory.setAccessorySlot(AccessoryType.GLOVES, outcomeStack);
				}
			}
		}

		if (accessoryInventory.wearingAccessory(ItemsAether.golden_feather) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_golden_feather) || accessoryInventory.wearingAccessory(ItemsAether.amplified_golden_feather) || this.player.getAccessoryInventory().wearingAccessory(ItemsAether.valkyrie_cape)) {
			if (!this.player.getEntity().onGround && this.player.getEntity().motionY < 0.0D && !this.player.getEntity().isInWater() && !this.player.getEntity().isSneaking()) {
				player.getEntity().motionY *= 0.59999999999999998D;
			}

			player.getEntity().fallDistance = -1F;
		}
	}

}
