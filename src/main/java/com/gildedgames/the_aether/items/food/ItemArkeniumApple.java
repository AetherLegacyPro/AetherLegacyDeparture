package com.gildedgames.the_aether.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArkeniumApple extends ItemAetherFood {

	public ItemArkeniumApple() {
		super(2);

		this.setAlwaysEdible();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 0));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 2400, 0));
		player.heal(4);
	}

}
