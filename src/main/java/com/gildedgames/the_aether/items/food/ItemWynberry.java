package com.gildedgames.the_aether.items.food;

import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;






import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.client.models.entities.TempestModel;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;

import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

public class ItemWynberry extends ItemAetherFood {

	@SideOnly(Side.CLIENT)
	private IIcon alternativeIcon;

	public ItemWynberry() {
		super(3);

		this.setHasSubtypes(false);
		this.setCreativeTab(AetherCreativeTabs.food);
		//this.setTextureName(Aether.findII("../consumables/wyndberry"));
		this.setTextureName(("aether:consumables/wyndberry"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		super.registerIcons(registry);

		this.alternativeIcon = registry.registerIcon("aether:consumables/wyndberry");
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 305, 2));
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
	}
}
