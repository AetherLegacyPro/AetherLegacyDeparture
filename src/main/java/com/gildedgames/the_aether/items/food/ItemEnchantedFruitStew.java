package com.gildedgames.the_aether.items.food;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEnchantedFruitStew extends ItemFood
{

	 public ItemEnchantedFruitStew(int p_i45330_1_)
	    {
	        super(p_i45330_1_, false);
	        this.setMaxStackSize(16);
	        this.setCreativeTab(AetherCreativeTabs.food);
	    }
	 
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}

	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	    {
	        super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
	        p_77654_3_.triggerAchievement(AchievementsAether.fruit_stew);
	        
	        if (p_77654_1_.stackSize >= 1) {	    	
	    	  p_77654_3_.inventory.addItemStackToInventory(new ItemStack(ItemsAether.holystone_bowl));
	    	    }
	    	return p_77654_1_.stackSize <= 0 ? new ItemStack(ItemsAether.holystone_bowl) : p_77654_1_;
	    }
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 0));
	}
}
