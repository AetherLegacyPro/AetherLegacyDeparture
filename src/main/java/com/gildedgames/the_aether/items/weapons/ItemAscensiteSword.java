package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemAscensiteSword extends ItemSword {
	
	private static ToolMaterial dragonMaterial = EnumHelper.addToolMaterial("ASCENSITE", 0, 8291, 2.0F, 40.0F, 17);

	public ItemAscensiteSword() {
		super(dragonMaterial);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		return false;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

}
