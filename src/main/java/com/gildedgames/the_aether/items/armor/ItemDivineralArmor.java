package com.gildedgames.the_aether.items.armor;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDivineralArmor extends ItemArmor {

	private String[] defualt_location = new String[]{"textures/models/armor/iron_layer_1.png", "textures/models/armor/iron_layer_2.png"};

	public boolean shouldDefualt = false;

	private int colorization = -1;

	private String armorName;

	private Item source = null;

	public ItemDivineralArmor(int armorType, ArmorMaterial material, String name, Item repair) {
		super(material, 0, armorType);

		this.source = repair;
		this.armorName = name;
		this.setCreativeTab(AetherCreativeTabs.armor);
		setMaxDamage(4477);
	}

	public ItemDivineralArmor(int armorType, ArmorMaterial material, String name, Item repair, int hex) {
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
		boolean hasDivineralHelmet = false;
        boolean hasDivineralChest = false;
        boolean hasDivineralLegs = false;
        boolean hasDivineralBoots = false;
        final ItemStack helmet = player.getCurrentArmor(3);
        final ItemStack chest = player.getCurrentArmor(2);
        final ItemStack legs = player.getCurrentArmor(1);
        final ItemStack boots = player.getCurrentArmor(0);
        {
        if (helmet != null) {
            hasDivineralHelmet = (helmet.getItem() == ItemsAether.divineral_helmet);
        }
        if (chest != null) {
            hasDivineralChest = (chest.getItem() == ItemsAether.divineral_chestplate);
        }
        if (legs != null) {
            hasDivineralLegs = (legs.getItem() == ItemsAether.divineral_leggings);
        }
        if (boots != null) {
            hasDivineralBoots = (boots.getItem() == ItemsAether.divineral_boots);
        }
        if (hasDivineralHelmet && hasDivineralChest && hasDivineralLegs && hasDivineralBoots) {
        	
    		player.triggerAchievement(AchievementsAether.divineral_set);    			   		
        	}
		}
        boolean hasObsidianHelmet = false;
        boolean hasObsidianChest = false;
        boolean hasObsidianLegs = false;
        boolean hasObsidianBoots = false;
        {
        if (helmet != null) {
            hasObsidianHelmet = (helmet.getItem() == ItemsAether.amplified_obsidian_helmet);
            }
        if (chest != null) {
            hasObsidianChest = (chest.getItem() == ItemsAether.amplified_obsidian_chestplate);
            }
        if (legs != null) {
            hasObsidianLegs = (legs.getItem() == ItemsAether.amplified_obsidian_leggings);
            }
        if (boots != null) {
            hasObsidianBoots = (boots.getItem() == ItemsAether.amplified_obsidian_boots);
            }
        if (hasObsidianHelmet && hasObsidianChest && hasObsidianLegs && hasObsidianBoots) {
            	
        	player.triggerAchievement(AchievementsAether.obsidian_set);    			   		
            }
    	}
        boolean hasNeptuneHelmet = false;
        boolean hasNeptuneChest = false;
        boolean hasNeptuneLegs = false;
        boolean hasNeptuneBoots = false;
        {
        if (helmet != null) {
        	hasNeptuneHelmet = (helmet.getItem() == ItemsAether.amplified_neptune_helmet);
            }
        if (chest != null) {
            hasNeptuneChest = (chest.getItem() == ItemsAether.amplified_neptune_chestplate);
            }
        if (legs != null) {
            hasNeptuneLegs = (legs.getItem() == ItemsAether.amplified_neptune_leggings);
            }
        if (boots != null) {
            hasNeptuneBoots = (boots.getItem() == ItemsAether.amplified_neptune_boots);
            }
        if (hasNeptuneHelmet && hasNeptuneChest && hasNeptuneLegs && hasNeptuneBoots) {
            	
        	player.triggerAchievement(AchievementsAether.neptune_set);    			   		
            }
    	}
        boolean hasValkyrieHelmet = false;
        boolean hasValkyrieChest = false;
        boolean hasValkyrieLegs = false;
        boolean hasValkyrieBoots = false;
        {
        if (helmet != null) {
        	hasValkyrieHelmet = (helmet.getItem() == ItemsAether.amplified_valkyrie_helmet);
            }
        if (chest != null) {
            hasValkyrieChest = (chest.getItem() == ItemsAether.amplified_valkyrie_chestplate);
            }
        if (legs != null) {
            hasValkyrieLegs = (legs.getItem() == ItemsAether.amplified_valkyrie_leggings);
            }
        if (boots != null) {
            hasValkyrieBoots = (boots.getItem() == ItemsAether.amplified_valkyrie_boots);
            }
        if (hasValkyrieHelmet && hasValkyrieChest && hasValkyrieLegs && hasValkyrieBoots) {
            	
        	player.triggerAchievement(AchievementsAether.valkyrie_set);    			   		
            }
    	}
        boolean hasSentryBoots = false;
        {
        if (boots != null) {
        	hasSentryBoots = (boots.getItem() == ItemsAether.amplified_sentry_boots);
            }
        if (hasSentryBoots) {
            	
        	player.triggerAchievement(AchievementsAether.amplified_sentry_boots);    			   		
            }
    	}
        boolean hasAgilityBoots = false;
        {
        if (boots != null) {
        	hasAgilityBoots = (boots.getItem() == ItemsAether.amplified_agility_boots);
            }
        if (hasAgilityBoots) {
            	
        	player.triggerAchievement(AchievementsAether.amplified_agility_boots);    			   		
            }
    	}
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