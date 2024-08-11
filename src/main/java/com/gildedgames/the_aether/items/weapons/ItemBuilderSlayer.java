package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;

public class ItemBuilderSlayer extends ItemSword {

	public ItemBuilderSlayer() {
		super(ToolMaterial.IRON);
		this.setMaxDamage(654);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if (entityliving == null || entityliving1 == null) {
			return false;
		}

		String s = EntityList.getEntityString(entityliving);

		if (s != null && (s.toLowerCase().contains("player") || s.toLowerCase().contains("valkyrie") || s.toLowerCase().contains("fallen_valkyrie") || s.toLowerCase().contains("elite_valkyrie") || s.toLowerCase().contains("villager") || s.toLowerCase().contains("pillager") || s.toLowerCase().contains("piglin") || s.toLowerCase().contains("iceologer"))) {
			if (entityliving.getHealth() > 0) {
				entityliving.attackEntityFrom(DamageSource.causeMobDamage(entityliving1), 30);
			}
		}

		return super.hitEntity(itemstack, entityliving, entityliving1);
	}

}
