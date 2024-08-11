package com.gildedgames.the_aether.items.armor;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAmplifiedPhoenixArmor extends ItemArmor {

	private String[] defualt_location = new String[]{"textures/models/armor/iron_layer_1.png", "textures/models/armor/iron_layer_2.png"};

	public boolean shouldDefualt = false;

	private int colorization = -1;

	private String armorName;

	private Item source = null;

	public ItemAmplifiedPhoenixArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(material, 0, armorType);

		this.source = repair;
		this.armorName = name;
		this.setCreativeTab(AetherCreativeTabs.armor);
		setMaxDamage(3477);
	}
	
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		boolean hasPhoenixHelmet = false;
        boolean hasPhoenixChest = false;
        boolean hasPhoenixLegs = false;
        boolean hasPhoenixBoots = false;
        final ItemStack helmet = player.getCurrentArmor(3);
        final ItemStack chest = player.getCurrentArmor(2);
        final ItemStack legs = player.getCurrentArmor(1);
        final ItemStack boots = player.getCurrentArmor(0);
        if (helmet != null) {
            hasPhoenixHelmet = (helmet.getItem() == ItemsAether.amplified_phoenix_helmet);
        }
        if (chest != null) {
            hasPhoenixChest = (chest.getItem() == ItemsAether.amplified_phoenix_chestplate);
        }
        if (legs != null) {
            hasPhoenixLegs = (legs.getItem() == ItemsAether.amplified_phoenix_leggings);
        }
        if (boots != null) {
            hasPhoenixBoots = (boots.getItem() == ItemsAether.amplified_phoenix_boots);
        }
        if (hasPhoenixHelmet && hasPhoenixChest && hasPhoenixLegs && hasPhoenixBoots) {
        	((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.nightVision.id, 220, 0));
    		
    		if (world.provider.dimensionId == -1) {
    		
    		((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20, 1));
    		((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 1));
    		player.triggerAchievement(AchievementsAether.phoenix_set);
    			
    		}

		}
    }

	public ItemAmplifiedPhoenixArmor(int armorType, ArmorMaterial material, String name, Item repair, int hex) {
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
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }

}
