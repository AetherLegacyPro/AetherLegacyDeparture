package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

public class AbilityBoneRing implements IAetherAbility {

	private final IPlayerAether player;

	private boolean invisibilityUpdate;

	private boolean stepUpdate;

	public AbilityBoneRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		
		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.bone_ring))) {
			if (this.player.getEntity().isEating()) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
				
				int rand = (int)(1 + Math.random() * 100);
				if(rand == 10 ) {
				this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.bone_ring));
				}
			}
		}
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.reinforced_bone_ring))) {
			if (this.player.getEntity().isEating()) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
				
				int rand = (int)(1 + Math.random() * 130);
				if(rand == 10 ) {
				this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_bone_ring));
				}
			}
		}
		else if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.amplified_bone_ring))) {
			if (this.player.getEntity().isEating()) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 1));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1));
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
				
				int rand = (int)(1 + Math.random() * 150);
				if(rand == 10 ) {
				this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_bone_ring));
				}
			}
		}
				
		}
	}

