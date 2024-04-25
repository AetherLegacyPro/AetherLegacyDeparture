package com.gildedgames.the_aether.items.food;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUncookedFruitStewBowl extends ItemFood
{
	 private static final String __OBFID = "CL_00001778";

	 public ItemUncookedFruitStewBowl(int p_i45330_1_)
	    {
	        super(p_i45330_1_, false);
	        this.setMaxStackSize(4);
	        this.setCreativeTab(AetherCreativeTabs.food);
	    }

	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	    {
	        super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
	        return new ItemStack(ItemsAether.holystone_bowl);
	    }
}
