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

		if (accessoryInventory.wearingAccessory(ItemsAether.bone_ring)) {
			playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
			playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
			playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));

			int rand = (int)(1 + Math.random() * 100);
			if(rand == 10 ) {
				accessoryInventory.damageWornItem(1, ItemsAether.bone_ring);
			}
		} else if (accessoryInventory.wearingAccessory(ItemsAether.reinforced_bone_ring)) {
			playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
			playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
			playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));

			int rand = (int)(1 + Math.random() * 130);
			if(rand == 10 ) {
				accessoryInventory.damageWornItem(1, ItemsAether.reinforced_bone_ring);
			}
		} else if (accessoryInventory.wearingAccessory(ItemsAether.amplified_bone_ring)) {
			playerEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 1));
			playerEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1));
			playerEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 0));

			int rand = (int)(1 + Math.random() * 150);
			if(rand == 10 ) {
				player.getAccessoryInventory().damageWornItem(1, ItemsAether.amplified_bone_ring);
			}
		}
	}

}
