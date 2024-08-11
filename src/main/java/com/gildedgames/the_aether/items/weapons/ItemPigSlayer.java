package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;

public class ItemPigSlayer extends ItemSword {

	public ItemPigSlayer() {
		super(ToolMaterial.IRON);
		this.setMaxDamage(40);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if (entityliving == null || entityliving1 == null) {
			return false;
		}

		String s = EntityList.getEntityString(entityliving);

		if (s != null && (s.toLowerCase().contains("pig") || s.toLowerCase().contains("phyg") || s.toLowerCase().contains("taegore") || s.toLowerCase().contains("piglin") || entityliving.getUniqueID().toString().equals("1d680bb6-2a9a-4f25-bf2f-a1af74361d69"))) {
			if (entityliving.getHealth() > 0) {
				entityliving.attackEntityFrom(DamageSource.causeMobDamage(entityliving1), 20);
			}
		}

		return super.hitEntity(itemstack, entityliving, entityliving1);
	}

}