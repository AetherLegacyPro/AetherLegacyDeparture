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

public class AbilityInvisibilityGem implements IAetherAbility {

	private final IPlayerAether player;

	private boolean invisibilityUpdate;

	private boolean stepUpdate;

	public AbilityInvisibilityGem(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.invisibility_gem))) {
			this.player.getEntity().setInvisible(true);
			this.invisibilityUpdate = true;
		} else if (!this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.invisibility_gem)) && !this.player.getEntity().isPotionActive(Potion.invisibility)) {
			if (this.invisibilityUpdate) {
				this.player.getEntity().setInvisible(false);
				this.invisibilityUpdate = false;
			}
		}
	}
	
}
