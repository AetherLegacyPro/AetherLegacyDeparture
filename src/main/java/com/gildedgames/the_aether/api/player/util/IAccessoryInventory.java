package com.gildedgames.the_aether.api.player.util;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.gildedgames.the_aether.api.accessories.AccessoryType;

public interface IAccessoryInventory extends IInventory {

	void dropAccessories();

	void damageAccessory(int damage, AccessoryType type);

	@Deprecated
	void damageWornStack(int damage, ItemStack stack);
	void damageWornItem(int damage, Item item);

	void setAccessorySlot(AccessoryType type, ItemStack stack);

	ItemStack getStackInSlot(AccessoryType type);

	ItemStack removeStackFromAccessorySlot(AccessoryType type);

	boolean setAccessorySlot(ItemStack stack);

	@Deprecated
	boolean wearingAccessory(ItemStack stack);
	boolean wearingAccessory(Item accessory);

	@Deprecated
	boolean wearingArmor(ItemStack stack);
	boolean wearingArmor(Item armor);

	NBTTagList writeToNBT(NBTTagCompound compound);

	void readFromNBT(NBTTagList list);

	void writeData(ByteBuf buf);

	void readData(ByteBuf buf);

	boolean isWearingZaniteSet();

	//---------------- Phoenix Armor
	boolean isWearingPhoenixSet();
	
	boolean isWearingPhoenixComboSet();
	
	boolean isWearingAmplifiedPhoenixSet();
	
	//boolean isWearingScaledPhoenixSet();

	//---------------- Obsidian Armor
	boolean isWearingObsidianSet();
	
	boolean isWearingObsidianComboSet();
	
	boolean isWearingAmplifiedObsidianSet();
	
	//boolean isWearingScaledObsidianSet();
	
	//--------------- Arkenium Armor
	boolean isWearingArkeniumSet();
	
	boolean isWearingArkeniumComboSet();
	
	boolean isWearingAmplifiedArkeniumSet();
	
	//boolean isWearingScaledArkeniumSet();
	
	//--------------- Continuum Armor
	boolean isWearingContinuumSet();
	
	boolean isWearingContinuumComboSet();
	
	boolean isWearingAmplifiedContinuumSet();
	
	//boolean isWearingScaledContinuumSet();
	
	//---------------- Gravitite & Divineral CDFS
	boolean isWearingGravititeSet();
	
	boolean isWearingGravititeAndDivineralSet();
	
	boolean isWearingDivineralSet();
	
	//boolean isWearingScaledGravititeSet();
	
	//---------------- Valkyrie Armor CDFS
	boolean isWearingValkyrieSet();
	
	boolean isWearingValkyrieComboSet();
	
	boolean isWearingAmplifiedValkyrieSet();
	
	//---------------- Neptune Armor CDFS
	boolean isWearingNeptuneSet();
	
	boolean isWearingAmplifiedNeptuneSet();
	
	boolean isWearingComboNeptuneSet();
	
	//---------------- Elysian Armor CDFS
	boolean isWearingElysianSet();
	
	//---------------- Agility Boots CDFS
	boolean isWearingAgilityBootsAndCape();
	
	boolean isWearingAgilityBoots();
	
	boolean isWearingAmplifiedAgilityBoots();
	
	boolean isWearingAmplifiedAgilityBootsAndCape();
	
	boolean isWearingPureSpeed();
	
	//---------------- Valkyrie Ring CDFS
	boolean isWearingValkyrieRing();
	
	boolean isWearingValkyrieRingAndAmplifiedArmor();
	
	boolean isWearingAmplifiedValkyrieRingAndAmplifiedArmor();
	
	//---------------- Haste Ring CDFS
	boolean isWearingHasteRing();
	
	boolean isWearingHasteRingAndArkenium();
	
	boolean isWearingHasteRingAndArkeniumCombo();
	
	boolean isWearingHasteRingAmpilifedArkenium();
	
	//--------------- Discharge Cape
	boolean isWearingDischargeCape();
	
	boolean isWearingAscensiteSet();

	List<ItemStack> getAccessories();

	int getAccessoryCount(ItemStack stack);

}