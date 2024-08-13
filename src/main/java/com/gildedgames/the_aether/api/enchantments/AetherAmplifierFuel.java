package com.gildedgames.the_aether.api.enchantments;

import com.gildedgames.the_aether.api.RegistryEntry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AetherAmplifierFuel extends RegistryEntry {

	public int timeGiven;

	public ItemStack fuelStack;

	public AetherAmplifierFuel(Block fuelBlock, int timeGiven) {
		this(new ItemStack(fuelBlock), timeGiven);
	}

	public AetherAmplifierFuel(Item fuelItem, int timeGiven) {
		this(new ItemStack(fuelItem), timeGiven);
	}

	public AetherAmplifierFuel(ItemStack fuelStack, int timeGiven) {
		this.timeGiven = timeGiven;
		this.fuelStack = fuelStack;

		this.setRegistryName(fuelStack.getItem().getUnlocalizedName() + "_meta_" + (fuelStack.isItemStackDamageable() ? 0 : fuelStack.getItemDamage()));
	}

	public int getTimeGiven() {
		return this.timeGiven;
	}

	public ItemStack getFuelStack() {
		return this.fuelStack;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AetherAmplifierFuel fuel) {

			return this.getFuelStack().getItem() == fuel.getFuelStack().getItem() && this.getFuelStack().getItemDamage() == fuel.getFuelStack().getItemDamage();
		}

		return false;
	}

}