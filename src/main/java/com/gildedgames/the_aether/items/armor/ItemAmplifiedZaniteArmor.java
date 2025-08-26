package com.gildedgames.the_aether.items.armor;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ItemAmplifiedZaniteArmor extends ItemZaniteArmorBase implements ISpecialArmor {

	private final int[][] damageReductionAmountArray = new int[][]{new int[]{1, 2, 3, 1}, new int[]{1, 4, 5, 2}, new int[]{2, 5, 6, 2}, new int[]{3, 6, 8, 3}, new int[]{4, 8, 10, 4}};

	public ItemAmplifiedZaniteArmor(int armorType, ArmorMaterial material, String name, Item repair) {
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
			if (isBetween(tool, 0, 117)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 118, 335)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 336, 544)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 545, 823)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 824, 1245)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 1) {
			if (isBetween(tool, 0, 197)) {
				return this.getDamageReductionAmount(5);
			} else if (isBetween(tool, 198, 400)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 401, 624)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 625, 803)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 804, 1265)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 2) {
			if (isBetween(tool, 0, 250)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 251, 457)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 458, 644)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 645, 822)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 823, 1245)) {
				return this.getDamageReductionAmount(1);
			}
		} else if (this.armorType == 3) {
			if (isBetween(tool, 0, 140)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 141, 253)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 254, 444)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 445, 874)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 875, 1275)) {
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
        return new EntityFireProofItemAether(world, location, itemstack);
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "" + StatCollector.translateToLocal("tooltip.amplified_zanite_armor.desc"));
    }

}
