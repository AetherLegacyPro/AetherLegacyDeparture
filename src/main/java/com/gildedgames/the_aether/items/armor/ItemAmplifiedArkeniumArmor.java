package com.gildedgames.the_aether.items.armor;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.armor.base.ItemAmplifiedArkeniumArmorBase;
import com.gildedgames.the_aether.player.PlayerAether;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemAmplifiedArkeniumArmor extends ItemAmplifiedArkeniumArmorBase implements ISpecialArmor {

	private final int[][] damageReductionAmountArray = new int[][]{new int[]{1, 2, 3, 1}, new int[]{1, 4, 5, 2}, new int[]{2, 5, 6, 2}, new int[]{3, 6, 8, 3}, new int[]{4, 8, 10, 4}};

	public ItemAmplifiedArkeniumArmor(int armorType, ArmorMaterial material, String name, Item repair, int hex) {
		super(armorType, material, name, repair, hex);
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
			if (isBetween(tool, 0, 32)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 33, 388)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 389, 583)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 584, 761)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 762, 963)) {
				return this.getDamageReductionAmount(2);
			}
		} else if (this.armorType == 1) {
			if (isBetween(tool, 0, 47)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 48, 95)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 96, 143)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 144, 191)) {
				return this.getDamageReductionAmount(4);
			} else if (isBetween(tool, 192, 1128)) {
				return this.getDamageReductionAmount(4);
			}
		} else if (this.armorType == 2) {
			if (isBetween(tool, 0, 44)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 45, 89)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 90, 134)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 135, 179)) {
				return this.getDamageReductionAmount(3);
			} else if (isBetween(tool, 180, 1095)) {
				return this.getDamageReductionAmount(3);
			}
		} else if (this.armorType == 3) {
			if (isBetween(tool, 0, 38)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 39, 77)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 78, 116)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 117, 155)) {
				return this.getDamageReductionAmount(2);
			} else if (isBetween(tool, 156, 1029)) {
				return this.getDamageReductionAmount(2);
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
