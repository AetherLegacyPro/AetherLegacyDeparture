package com.gildedgames.the_aether.items.weapons.tipped;

import com.gildedgames.the_aether.entities.projectile.EntityHammerProjectile;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedNotchHammer extends ItemSword {

	public float[] level = new float[] {10.0F, 10.0F, 10.0F, 10.0F, 10.0F};

	public ItemTippedNotchHammer() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		setMaxDamage(2431);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (stack.getItem() instanceof ItemTippedNotchHammer) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 516)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 517, current, tool.getMaxDamage() - 1790)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 1791, current, tool.getMaxDamage() - 3790)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 3791, current, tool.getMaxDamage() - 4791)) {
			return level[1];
		} else {
			return level[0];
		}
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		return material.getItem() == ItemsAether.divineral_ingot;
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.capabilities.isCreativeMode) {
            world.playSound(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F), false);

            if (!world.isRemote) {
            	EntityHammerProjectile hammerProjectile = new EntityHammerProjectile(world, entityplayer);
                hammerProjectile.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                world.spawnEntityInWorld(hammerProjectile);
            }
        } else if (PlayerAether.get(entityplayer).setHammerCooldown(120, itemstack.getDisplayName())) {
            itemstack.damageItem(3, entityplayer);

            world.playSound(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F), false);

            if (!world.isRemote) {
            	EntityHammerProjectile hammerProjectile = new EntityHammerProjectile(world, entityplayer);
                hammerProjectile.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                world.spawnEntityInWorld(hammerProjectile);
            }
        }

        return itemstack;
    }

	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

}
