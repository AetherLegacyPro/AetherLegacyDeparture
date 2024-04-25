package com.gildedgames.the_aether.items.armor;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.armor.base.ItemZaniteArmorBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ItemAmplifiedContinuumArmor extends ItemZaniteArmorBase implements ISpecialArmor {

	private final int[][] damageReductionAmountArray = new int[][]{new int[]{1, 2, 3, 1}, new int[]{1, 4, 5, 2}, new int[]{2, 5, 6, 2}, new int[]{3, 6, 8, 3}, new int[]{4, 8, 10, 4}};

	public ItemAmplifiedContinuumArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(armorType, material, name, repair);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, this.calculateIncrease(armor) / 25D, Integer.MAX_VALUE);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return (int) this.calculateIncrease(armor);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		stack.damageItem(damage, entity);
	}

	private float calculateIncrease(ItemStack tool) {
		if (this.armorType == 0) {
			if (isBetween(tool, 0, 433)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 434, 785)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 786, 1075)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1076, 1444)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 1445, 1845)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 1) {
			if (isBetween(tool, 0, 515)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 516, 1020)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 1021, 1344)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1345, 1755)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1756, 2128)) {
				return this.getDamageReductionAmount(2);
			}
		} else if (this.armorType == 2) {
			if (isBetween(tool, 0, 430)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 431, 866)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 867, 1234)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1235, 1543)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1544, 1945)) {
				return this.getDamageReductionAmount(2);
			}
		} else if (this.armorType == 3) {
			if (isBetween(tool, 0, 450)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 451, 777)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 778, 1100)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 1101, 1470)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 1471, 1875)) {
				return this.getDamageReductionAmount(1);
			}
		}

		return 0.0F;
	}

	public boolean isBetween(ItemStack tool, int max, int min) {
		int origin = tool.getItemDamage();
		int maxDamage = tool.getMaxDamage();

		return origin <= maxDamage - max && origin >= maxDamage - min ? true : false;
	}

	public int getDamageReductionAmount(int level) {
		return this.damageReductionAmountArray[level][this.armorType];
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }

}
