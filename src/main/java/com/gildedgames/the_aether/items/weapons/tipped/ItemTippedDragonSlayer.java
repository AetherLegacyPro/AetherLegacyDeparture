package com.gildedgames.the_aether.items.weapons.tipped;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedDragonSlayer extends ItemSword {

	public float[] level = new float[]{8.0F, 8.0F, 8.0F, 8.0F, 8.0F};

	public ItemTippedDragonSlayer() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(3391);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (stack.getItem() instanceof ItemTippedDragonSlayer) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 789)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 790, current, tool.getMaxDamage() - 2790)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 2791, current, tool.getMaxDamage() - 4791)) {
			return level[1];
		} else {
			return level[0];
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairStack) {
		return false;
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if (entityliving == null || entityliving1 == null) {
			return false;
		}

		String s = EntityList.getEntityString(entityliving);

		if (s.toLowerCase().contains("dragon")) {
			if (entityliving.getHealth() > 0) {
				entityliving.attackEntityFrom(DamageSource.causeMobDamage(entityliving1), 30);
			}
		}

		return super.hitEntity(itemstack, entityliving, entityliving1);
	}
	
	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }

}
