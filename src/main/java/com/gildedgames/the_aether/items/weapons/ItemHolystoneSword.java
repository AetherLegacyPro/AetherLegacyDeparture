package com.gildedgames.the_aether.items.weapons;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;

public class ItemHolystoneSword extends ItemSword {

	public ItemHolystoneSword() {
		super(ToolMaterial.STONE);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack mateiral) {
		return mateiral.getItem() == Item.getItemFromBlock(BlocksAether.holystone);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if ((new Random()).nextInt(20) == 0 && entityliving1 instanceof EntityPlayer && entityliving.hurtTime > 0 && entityliving.deathTime <= 0) {
			if (!entityliving.worldObj.isRemote) {
				entityliving.dropItem(ItemsAether.ambrosium_shard, 1);
			}
		}

		itemstack.damageItem(1, entityliving1);
		return true;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.GRAY + "" + StatCollector.translateToLocal("tooltip.holystone_tools.desc"));
    }

}