package com.gildedgames.the_aether.items.armor;

import java.util.Iterator;
import java.util.List;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.passive.EntityAetherAnimal;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemElysianArmor extends ItemArmor {

	private String[] defualt_location = new String[]{"textures/models/armor/iron_layer_1.png", "textures/models/armor/iron_layer_2.png"};

	public boolean shouldDefualt = false;

	private int colorization = -1;

	private String armorName;

	private Item source = null;

	public ItemElysianArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(material, 0, armorType);

		this.source = repair;
		this.armorName = name;
		this.setCreativeTab(AetherCreativeTabs.armor);
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		boolean hasElysianHelmet = false;
        boolean hasElysianChest = false;
        boolean hasElysianLegs = false;
        boolean hasElysianBoots = false;
        final ItemStack helmet = player.getCurrentArmor(3);
        final ItemStack chest = player.getCurrentArmor(2);
        final ItemStack legs = player.getCurrentArmor(1);
        final ItemStack boots = player.getCurrentArmor(0);
        if (helmet != null) {
            hasElysianHelmet = (helmet.getItem() == ItemsAether.amplified_elysian_helmet || helmet.getItem() == ItemsAether.scaled_elysian_helmet || helmet.getItem() == ItemsAether.elysian_helmet);
        }
        if (chest != null) {
            hasElysianChest = (chest.getItem() == ItemsAether.amplified_elysian_chestplate || chest.getItem() == ItemsAether.scaled_elysian_chestplate || chest.getItem() == ItemsAether.elysian_chestplate);
        }
        if (legs != null) {
            hasElysianLegs = (legs.getItem() == ItemsAether.amplified_elysian_leggings || legs.getItem() == ItemsAether.scaled_elysian_leggings || legs.getItem() == ItemsAether.elysian_leggings);
        }
        if (boots != null) {
            hasElysianBoots = (boots.getItem() == ItemsAether.amplified_elysian_boots || boots.getItem() == ItemsAether.scaled_elysian_boots || boots.getItem() == ItemsAether.elysian_boots || boots.getItem() == ItemsAether.amplified_sentry_boots || boots.getItem() == ItemsAether.amplified_agility_boots);
        }
        if (hasElysianHelmet && hasElysianChest && hasElysianLegs && hasElysianBoots) {
        	if(!world.isRemote) {
        		AxisAlignedBB axisalignedbb = player.boundingBox;
        		List<EntityLivingBase> volume = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb.expand(2, 3, 2));
        		int rand = (int)(1 + Math.random() * 96);
        		 for(Entity entity : volume) {
        			 if(!(entity instanceof EntityPlayer) && !(entity instanceof EntityAetherAnimal) && !(entity instanceof EntityAnimal) 
        					 && rand == 1) {
        				 entity.attackEntityFrom(DamageSource.causeThornsDamage(player), 1F);
        			 }
        		  }
        		 }
			}
		}

	public ItemElysianArmor(int armorType, ArmorMaterial material, String name, Item repair, int hex) {
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
		return ItemsAether.aether_loot;
	}

}
