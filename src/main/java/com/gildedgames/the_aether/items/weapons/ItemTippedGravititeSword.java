package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedGravititeSword extends ItemSword {

	public float[] level = new float[] {9.0F, 9.0F, 9.0F, 9.0F, 9.0F};

	public ItemTippedGravititeSword() {
		super(ToolMaterial.EMERALD);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		setMaxDamage(1891);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (stack.getItem() instanceof ItemTippedGravititeSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.calculateIncrease(stack), 0));
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
		} else if (isBetween(tool.getMaxDamage() - 3791, current, tool.getMaxDamage() - 5791)) {
			return level[1];
		} else {
			return level[0];
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitentity, EntityLivingBase player) {
		if ((hitentity.hurtTime > 0 || hitentity.deathTime > 0) && !(hitentity instanceof EntitySwet)) {
			hitentity.addVelocity(0.0D, 0.20D, 0.0D);
		}

		if (hitentity instanceof EntityPlayerMP) {
			((EntityPlayerMP) hitentity).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(hitentity));
		}

		return super.hitEntity(itemstack, hitentity, player);
	}

	private boolean isBetween(int max, int origin, int min) {
		return origin <= max && origin >= min ? true : false;
	}

	@Override
	public boolean getIsRepairable(ItemStack repairingItem, ItemStack material) {
		if (AetherConfig.RepairMaterialTipped() == true) {
		return material.getItem() == ItemsAether.auralite_crystal;
		}
		else {
		return material.getItem() == Item.getItemFromBlock(BlocksAether.enchanted_gravitite);
		}
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}
	
}
