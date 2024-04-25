package com.gildedgames.the_aether.items.weapons;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemOverworldSlayer extends ItemSword {
	
	public boolean newWorld;
	public Random random;

	public float[] level = new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
	
	public float[] level2 = new float[]{12.0F, 12.0F, 12.0F, 12.0F, 12.0F};

	public ItemOverworldSlayer() {
		super(ToolMaterial.EMERALD);
		this.setMaxDamage(2501);
		this.setCreativeTab(AetherCreativeTabs.weapons);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (stack.getItem() instanceof ItemOverworldSlayer) {
			if (newWorld == true) {
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease2(stack), 0));
			}
			else {
				multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease(stack), 0));	
			}
		}		

		return multimap;
	}
	
	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
		if (p_77663_2_.provider.dimensionId == 0) {
				newWorld = true;
			}
		else {
				newWorld = false;
		}
	}
	
	private float calculateIncrease2(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
			return level2[4];
		} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 110)) {
			return level2[3];
		} else if (isBetween(tool.getMaxDamage() - 111, current, tool.getMaxDamage() - 200)) {
			return level2[2];
		} else if (isBetween(tool.getMaxDamage() - 201, current, tool.getMaxDamage() - 2501)) {
			return level2[1];
		} else {
			return level2[0];
		}
	}
	
	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 110)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 111, current, tool.getMaxDamage() - 200)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 201, current, tool.getMaxDamage() - 2501)) {
			return level[1];
		} else {
			return level[0];
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass) {
		if (newWorld == true) {
			return true;
		}
		else {
			return false;	
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (newWorld == true) {
			return ItemsAether.powered;
		}
		else {
			return ItemsAether.aether_loot;	
		}
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	protected String getChargedHitSound() {
        return "aether_legacy:projectile.charged_hit";
    }
	
	protected float getSoundVolume()
    {
        return 0.8F;
    }

}
