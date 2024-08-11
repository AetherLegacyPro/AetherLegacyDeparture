package com.gildedgames.the_aether.items.weapons;

import java.util.Random;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemAmplifiedContinuumSword extends ItemSword {
	
	public float[] level = new float[] {6F, 7F, 9F, 9F, 10F};

	public ItemAmplifiedContinuumSword(float damage, EnumAetherToolType toolType) {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		setMaxDamage(5471);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (stack.getItem() instanceof ItemAmplifiedContinuumSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 1000)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 1001, current, tool.getMaxDamage() - 2800)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 2801, current, tool.getMaxDamage() - 4470)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 4471, current, tool.getMaxDamage() - 5471)) {
			return level[1];
		} else {
			return level[0];
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if ((new Random()).nextInt(12) == 0 && entityliving1 != null && entityliving1 instanceof EntityPlayer && entityliving.hurtTime > 0 && entityliving.deathTime <= 0) {
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
		return material.getItem() == ItemsAether.divineral_ingot;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }

}
