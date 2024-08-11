package com.gildedgames.the_aether.items.weapons.tipped;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedHolySword extends ItemSword {

	public float[] level = new float[]{8.0F, 8.0F, 8.0F, 8.0F, 8.0F};

	public ItemTippedHolySword() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(1891);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (stack.getItem() instanceof ItemTippedHolySword) {
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
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairStack) {
		return false;
	}
	
	@Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
    
        if (this == ItemsAether.tipped_holy_sword && (entityliving.isEntityUndead() || entityliving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) || (entityliving.getCreatureAttribute() == EnumCreatureAttribute.UNDEFINED) || (entityliving.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)) {

            float damage = 20.0F;

            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, itemstack);

            if (level > 0)
            {
                damage += (level * 2.5);
            }
            
            float damagee = 7.0F;
            
            int level1 = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, itemstack);

            if (level1 > 0)
            {
                damagee += (level1 * 1.2);
            }
            
            float damageee = 15F;
            
            int level2 = EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, itemstack);

            if (level2 > 0)
            {
                damageee += (level2 * 4);
            }

            //entityliving.attackEntityFrom(DamageSource.drown, damage);
            //itemstack.damageItem(10, entityliving1);
        }
        
        return super.hitEntity(itemstack, entityliving, entityliving1);
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

}
