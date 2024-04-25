package com.gildedgames.the_aether.items.food;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemElysianApple extends ItemAetherFood {

	public ItemElysianApple() {
		super(20);
		this.setMaxStackSize(1);
		this.setAlwaysEdible();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 9600, 1));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 19200, 1));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 19200, 1));
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 9600, 0));
		if (!worldIn.isRemote) {
			 player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 9600, 10));
	        }
		player.heal(8);
	}

}
