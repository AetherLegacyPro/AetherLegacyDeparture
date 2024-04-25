package com.gildedgames.the_aether.items.food;

import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGummySwet extends ItemAetherFood {

	@SideOnly(Side.CLIENT)
	private IIcon alternativeIcon;

	public ItemGummySwet() {
		super(20);
		
		this.setMaxStackSize(8);
		this.setHasSubtypes(true);
		this.setCreativeTab(AetherCreativeTabs.food);
		this.setTextureName(Aether.find("food/golden_gummy_swet"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String type = stack.getItemDamage() == 0 ? "blue" : "golden";

		return "item.gummy_swet_" + type;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		super.registerIcons(registry);

		this.alternativeIcon = registry.registerIcon(Aether.find("food/blue_gummy_swet"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return meta == 1 ? this.itemIcon : this.alternativeIcon;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}
}