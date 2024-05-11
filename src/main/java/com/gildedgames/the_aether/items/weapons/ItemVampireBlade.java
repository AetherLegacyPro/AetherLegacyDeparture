package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemVampireBlade extends ItemSword {

	public ItemVampireBlade() {
		super(ToolMaterial.EMERALD);

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
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		EntityPlayer player = (EntityPlayer) attacker;
		
		int rand = (int)(1 + Math.random() * 4);
		switch (rand)
        {
        case 1:
        	if (player.getHealth() < player.getMaxHealth()) {
        		player.heal(1.0F);
				}
        	break;
        case 2:        	
        	break;
        case 3:
        	break;
        case 4:	
			break;
		
        }

		return super.hitEntity(stack, target, attacker);
	}

}