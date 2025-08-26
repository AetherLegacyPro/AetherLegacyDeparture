package com.gildedgames.the_aether.items.weapons.tipped;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemTippedArkeniumSword extends ItemSword {

	public float[] level = new float[]{6.0F, 6.0F, 6.0F, 6.0F, 6.0F};

	public ItemTippedArkeniumSword() {
		super(ToolMaterial.IRON);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		setMaxDamage(1571);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		return null;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (stack.getItem() instanceof ItemTippedArkeniumSword) {
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.calculateIncrease(stack), 0));
		}

		return multimap;
	}

	private float calculateIncrease(ItemStack tool) {
		int current = tool.getItemDamage();

		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50)) {
			return level[4];
		} else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 110)) {
			return level[3];
		} else if (isBetween(tool.getMaxDamage() - 111, current, tool.getMaxDamage() - 200)) {
			return level[2];
		} else if (isBetween(tool.getMaxDamage() - 201, current, tool.getMaxDamage() - 1571)) {
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
		if (AetherConfig.RepairMaterialTipped() == true) {
			return material.getItem() == ItemsAether.auralite_crystal;
		}
		else {
			return material.getItem() == ItemsAether.arkenium_ingot;	
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
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.arkenium_tools.desc"));
    }

}