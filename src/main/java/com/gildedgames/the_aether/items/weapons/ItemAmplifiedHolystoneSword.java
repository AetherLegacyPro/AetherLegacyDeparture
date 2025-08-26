package com.gildedgames.the_aether.items.weapons;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;

public class ItemAmplifiedHolystoneSword extends ItemSword {

	public ItemAmplifiedHolystoneSword() {
		super(ToolMaterial.IRON);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(3671);
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack mateiral) {
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		if ((new Random()).nextInt(20) == 0 && entityliving1 instanceof EntityPlayer && entityliving.hurtTime > 0 && entityliving.deathTime <= 0) {
			if (!entityliving.worldObj.isRemote) {
				entityliving.dropItem(ItemsAether.ambrosium_shard, 10);
			}
		}

		itemstack.damageItem(1, entityliving1);
		return true;
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
        tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "" + StatCollector.translateToLocal("tooltip.amplified_holystone_tools.desc"));
    }
}
