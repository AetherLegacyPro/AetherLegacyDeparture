package com.gildedgames.the_aether.items.weapons.tipped;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedZaniteSword extends ItemSword {

	public float[] level = new float[]{4.0F, 5.0F, 6.0F, 7.0F, 8.0F};

	public ItemTippedZaniteSword() {
		super(ToolMaterial.IRON);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(891);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (stack.getItem() instanceof ItemTippedZaniteSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 199)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 200, current, tool.getMaxDamage() - 419)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 420, current, tool.getMaxDamage() - 690)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 691, current, tool.getMaxDamage() - 891)) {
			return level[1];
		} else {
			return level[0];
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		if (AetherConfig.RepairMaterialTipped() == true) {
		return material.getItem() == ItemsAether.auralite_crystal;
		}
		else {
		return material.getItem() == ItemsAether.zanite_gemstone;	
		}
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}
}
