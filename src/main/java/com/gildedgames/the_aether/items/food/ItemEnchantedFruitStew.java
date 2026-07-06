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

public class ItemEnchantedFruitStew extends ItemFood {

	 public ItemEnchantedFruitStew(int p_i45330_1_) {
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

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer entityPlayer) {
         super.onEaten(stack, world, entityPlayer);
         entityPlayer.triggerAchievement(AchievementsAether.fruit_stew);
         if (stack.stackSize >= 1) {
             entityPlayer.inventory.addItemStackToInventory(new ItemStack(ItemsAether.holystone_bowl));
         }
        return stack.stackSize <= 0 ? new ItemStack(ItemsAether.holystone_bowl) : stack;
     }

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 0));
	}
}
