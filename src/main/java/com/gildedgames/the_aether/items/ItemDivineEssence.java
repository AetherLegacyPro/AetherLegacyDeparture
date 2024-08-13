package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDivineEssence extends Item {
	
	private IIcon[] icon;

	public ItemDivineEssence() {
		this.icon = new IIcon[1];
		maxStackSize = 4;
        this.setCreativeTab(AetherCreativeTabs.material);
    }
	
	public void registerBlockIcons(final IIconRegister iconRegister) {
        this.icon[0] = iconRegister.registerIcon("aether_legacy:aetherium_essence");
    }
	
	public IIcon getIcon(final int side, int meta) {
        if (meta < 0 || meta >= this.icon.length) {
            meta = 1;
        }
        return this.icon[meta];
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
}
