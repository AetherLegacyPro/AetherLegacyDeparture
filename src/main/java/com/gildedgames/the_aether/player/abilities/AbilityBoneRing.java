package com.gildedgames.the_aether.player.abilities;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AbilityBoneRing implements IAetherAbility {

	private final IPlayerAether player;

	public AbilityBoneRing(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		EntityLivingBase playerEntity = player.getEntity();
		//if(!playerEntity.isEating()) {
			//return;
		//}
		IAccessoryInventory accessoryInventory = player.getAccessoryInventory();

		if (accessoryInventory.wearingAccessory(ItemsAether.bone_ring)||accessoryInventory.wearingAccessory(ItemsAether.reinforced_bone_ring)) {
			if (accessoryInventory.wearingAccessory(ItemsAether.elysian_ring) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_elysian_ring)) {
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 1));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}else if (accessoryInventory.wearingAccessory(ItemsAether.amplified_elysian_ring)) {
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 2));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}else{
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}


		} else if (accessoryInventory.wearingAccessory(ItemsAether.amplified_bone_ring)) {
			if (accessoryInventory.wearingAccessory(ItemsAether.elysian_ring) || accessoryInventory.wearingAccessory(ItemsAether.reinforced_elysian_ring)) {
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 2));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}else if (accessoryInventory.wearingAccessory(ItemsAether.amplified_elysian_ring)) {
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 3));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}else{
				playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 1));
				playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1));
				playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));
			}

		}
	}

}
