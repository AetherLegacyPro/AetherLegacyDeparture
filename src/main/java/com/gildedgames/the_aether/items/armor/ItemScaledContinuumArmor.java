package com.gildedgames.the_aether.items.armor;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.armor.base.ItemZaniteArmorBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;

public class ItemScaledContinuumArmor extends ItemZaniteArmorBase implements ISpecialArmor {

	private final int[][] damageReductionAmountArray = new int[][]{new int[]{1, 2, 3, 1}, new int[]{1, 4, 5, 2}, new int[]{2, 5, 6, 2}, new int[]{3, 6, 8, 3}, new int[]{4, 8, 10, 4}};

	public ItemScaledContinuumArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(armorType, material, name, repair);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, this.calculateIncrease(armor) / 26D, Integer.MAX_VALUE);
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
			if (isBetween(tool, 0, 32)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 33, 65)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 66, 98)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 99, 131)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 132, 500)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 1) {
			if (isBetween(tool, 0, 47)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 48, 95)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 96, 143)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 144, 191)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 192, 600)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 2) {
			if (isBetween(tool, 0, 44)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 45, 89)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 90, 134)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 135, 179)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 180, 500)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 3) {
			if (isBetween(tool, 0, 38)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 39, 77)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 78, 116)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 117, 155)) {
				return this.getDamageReductionAmount(1);
			} else if (isBetween(tool, 156, 500)) {
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
		return ItemsAether.scaled_aether_loot;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.continuum_tools.desc"));
    }
	

}
