package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemAmplifiedLightningSword extends ItemSword {

	public float[] level = new float[]{9.0F, 9.0F, 9.0F, 9.0F, 9.0F};

	public ItemAmplifiedLightningSword() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(4791);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (stack.getItem() instanceof ItemAmplifiedLightningSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 789)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 790, current, tool.getMaxDamage() - 2790)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 2791, current, tool.getMaxDamage() - 4791)) {
			return level[1];
		} else {
			return level[0];
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairStack) {
		return false;
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
	
	if (this == ItemsAether.amplified_lightning_sword) {
		EntityLightningBolt lightning = new EntityLightningBolt(entityliving1.worldObj, entityliving.posX, entityliving.posY, entityliving.posZ);
		
		EntityLightningBolt lightning1 = new EntityLightningBolt(entityliving1.worldObj, entityliving.posX + 3, entityliving.posY, entityliving.posZ + 3);
		
		EntityLightningBolt lightning2 = new EntityLightningBolt(entityliving1.worldObj, entityliving.posX - 3, entityliving.posY, entityliving.posZ - 3);
		
		EntityLightningBolt lightning3 = new EntityLightningBolt(entityliving1.worldObj, entityliving.posX + 3, entityliving.posY, entityliving.posZ - 3);
		
		EntityLightningBolt lightning4 = new EntityLightningBolt(entityliving1.worldObj, entityliving.posX - 3, entityliving.posY, entityliving.posZ + 3);
		
		entityliving1.worldObj.spawnEntityInWorld(lightning);
		entityliving1.worldObj.spawnEntityInWorld(lightning1);
		entityliving1.worldObj.spawnEntityInWorld(lightning2);
		entityliving1.worldObj.spawnEntityInWorld(lightning3);
		entityliving1.worldObj.spawnEntityInWorld(lightning4);
	}
		return super.hitEntity(itemstack, entityliving, entityliving1);
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
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