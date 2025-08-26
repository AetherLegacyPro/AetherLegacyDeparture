package com.gildedgames.the_aether.items.armor;

import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemAetherDungeonArmor extends ItemArmor {

	private String[] defualt_location = new String[]{"textures/models/armor/iron_layer_1.png", "textures/models/armor/iron_layer_2.png"};

	public boolean shouldDefualt = false;

	private int colorization = -1;

	private String armorName;

	private Item source = null;

	public ItemAetherDungeonArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(material, 0, armorType);

		this.source = repair;
		this.armorName = name;
		this.setCreativeTab(AetherCreativeTabs.armor);
		setMaxDamage(1591);
	}

	public ItemAetherDungeonArmor(int armorType, ArmorMaterial material, String name, Item repair, int hex) {
		this(armorType, material, name, repair);

		this.source = repair;
		this.armorName = name;
		this.colorization = hex;
		this.shouldDefualt = true;
	}

	@Override
	public int getColor(ItemStack stack) {
		return this.colorization;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return this.colorization != -1 ? this.colorization : 16777215;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		boolean leggings = this.getUnlocalizedName().contains("leggings");
		String type1 = leggings ? "layer_2" : "layer_1";

		return this.shouldDefualt ? (leggings ? defualt_location[1] : defualt_location[0]) : Aether.modAddress() + "textures/armor/" + this.armorName + "_" + type1 + ".png";
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.source == null ? false : repair.getItem() == this.source;
	}
	
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		
        boolean hasDivineralBoots = false;
        final ItemStack boots = player.getCurrentArmor(0);
        {
        if (boots != null) {
            hasDivineralBoots = (boots.getItem() == ItemsAether.agility_boots);
        }
        if (hasDivineralBoots) {
        	
    		player.triggerAchievement(AchievementsAether.agility_boots);    			   		
        	}
		}
        
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips()) {
			if (stack.getItem() == ItemsAether.neptune_helmet || stack.getItem() == ItemsAether.neptune_chestplate || stack.getItem() == ItemsAether.neptune_leggings || stack.getItem() == ItemsAether.neptune_boots) {
				tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.neptune_armor.desc"));
			}
			else if (stack.getItem() == ItemsAether.agility_boots) {
				tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.agility_boots.desc"));
			}
			else if (stack.getItem() == ItemsAether.obsidian_helmet || stack.getItem() == ItemsAether.obsidian_chestplate || stack.getItem() == ItemsAether.obsidian_leggings || stack.getItem() == ItemsAether.obsidian_boots) {
				tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.obsidian_armor.desc"));
			}
			else if (stack.getItem() == ItemsAether.valkyrie_helmet || stack.getItem() == ItemsAether.valkyrie_chestplate || stack.getItem() == ItemsAether.valkyrie_leggings || stack.getItem() == ItemsAether.valkyrie_boots) {
				tooltip.add(EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal("tooltip.valkyrie_armor.desc"));
			}
		}
    }
}