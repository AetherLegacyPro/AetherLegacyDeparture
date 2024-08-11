package com.gildedgames.the_aether.api.enchantments;

import com.gildedgames.the_aether.api.RegistryEntry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AetherAmplifier extends RegistryEntry {

	public int timeRequired;

	public ItemStack input, output;

	public AetherAmplifier(ItemStack input, Block output, int timeRequired) {
		this(input, new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(Block input, ItemStack output, int timeRequired) {
		this(new ItemStack(input), output, timeRequired);
	}

	public AetherAmplifier(Block input, Block output, int timeRequired) {
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(ItemStack input, Item output, int timeRequired) {
		this(input, new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(Item input, ItemStack output, int timeRequired) {
		this(new ItemStack(input), output, timeRequired);
	}

	public AetherAmplifier(Item input, Item output, int timeRequired) {
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(Block input, Item output, int timeRequired) {
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(Item input, Block output, int timeRequired) {
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherAmplifier(Item result, int timeRequired) {
		this(new ItemStack(result), new ItemStack(result), timeRequired);
	}

	public AetherAmplifier(ItemStack input, ItemStack output, int timeRequired) {
		this.input = input;
		this.output = output;
		this.timeRequired = timeRequired;

		this.setRegistryName(input.getItem().getUnlocalizedName() + "_meta_" + (input.isItemStackDamageable() ? 0 : input.getItemDamage()));
	}

	public int getTimeRequired() {
		return this.timeRequired;
	}

	public ItemStack getInput() {
		return this.input;
	}

	public ItemStack getOutput() {
		return this.output;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AetherAmplifier) {
			AetherAmplifier freezable = (AetherAmplifier) obj;

			boolean inputCheck = this.getInput().getItem() == freezable.getInput().getItem() && this.getInput().getItemDamage() == freezable.getInput().getItemDamage();
			boolean outputCheck = this.getOutput().getItem() == freezable.getOutput().getItem() && this.getOutput().getItemDamage() == freezable.getOutput().getItemDamage();

			return inputCheck && outputCheck;
		}

		return false;
	}

}
