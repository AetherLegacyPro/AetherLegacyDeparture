package com.gildedgames.the_aether.items.weapons.tipped;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemTippedContinuumSword extends ItemSword {
	
	public float[] level = new float[] {6F, 7F, 8F, 8F, 9F};

	public ItemTippedContinuumSword(float damage, EnumAetherToolType toolType) {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		setMaxDamage(2571);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (stack.getItem() instanceof ItemTippedContinuumSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 500)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 501, current, tool.getMaxDamage() - 800)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 801, current, tool.getMaxDamage() - 1470)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 1471, current, tool.getMaxDamage() - 2571)) {
			return level[1];
		} else {
			return level[0];
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if ((new Random()).nextInt(5) == 0 && entityliving1 != null && entityliving1 instanceof EntityPlayer && entityliving.hurtTime > 0 && entityliving.deathTime <= 0) {
			if (!entityliving.worldObj.isRemote) {
				entityliving.dropItem(ItemsAether.ambrosium_shard, 1);
			}
		}

		itemstack.damageItem(1, entityliving1);
		return true;
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
		return material.getItem() == ItemsAether.continuum_gemstone;	
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
