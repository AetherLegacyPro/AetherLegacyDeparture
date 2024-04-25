package com.gildedgames.the_aether.api.player.util;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.gildedgames.the_aether.api.accessories.AccessoryType;

public interface IAccessoryInventory extends IInventory {

	public void dropAccessories();

	public void damageAccessory(int damage, AccessoryType type);

	public void damageWornStack(int damage, ItemStack stack);

	public void setAccessorySlot(AccessoryType type, ItemStack stack);

	public ItemStack getStackInSlot(AccessoryType type);

	public ItemStack removeStackFromAccessorySlot(AccessoryType type);

	public boolean setAccessorySlot(ItemStack stack);

	public boolean wearingAccessory(ItemStack stack);

	public boolean wearingArmor(ItemStack stack);

	public NBTTagList writeToNBT(NBTTagCompound compound);

	public void readFromNBT(NBTTagList list);

	public void writeData(ByteBuf buf);

	public void readData(ByteBuf buf);

	public boolean isWearingZaniteSet();

	//---------------- Phoenix Armor
	public boolean isWearingPhoenixSet();
	
	public boolean isWearingPhoenixComboSet();
	
	public boolean isWearingAmplifiedPhoenixSet();
	
	//public boolean isWearingScaledPhoenixSet();

	//---------------- Obsidian Armor
	public boolean isWearingObsidianSet();
	
	public boolean isWearingObsidianComboSet();
	
	public boolean isWearingAmplifiedObsidianSet();
	
	//public boolean isWearingScaledObsidianSet();
	
	//--------------- Arkenium Armor
	public boolean isWearingArkeniumSet();
	
	public boolean isWearingArkeniumComboSet();
	
	public boolean isWearingAmplifiedArkeniumSet();
	
	//public boolean isWearingScaledArkeniumSet();
	
	//--------------- Continuum Armor
	public boolean isWearingContinuumSet();
	
	public boolean isWearingContinuumComboSet();
	
	public boolean isWearingAmplifiedContinuumSet();
	
	//public boolean isWearingScaledContinuumSet();
	
	//---------------- Gravitite & Divineral CDFS
	public boolean isWearingGravititeSet();
	
	public boolean isWearingGravititeAndDivineralSet();
	
	public boolean isWearingDivineralSet();
	
	//public boolean isWearingScaledGravititeSet();
	
	//---------------- Valkyrie Armor CDFS
	public boolean isWearingValkyrieSet();
	
	public boolean isWearingValkyrieComboSet();
	
	public boolean isWearingAmplifiedValkyrieSet();
	
	//---------------- Neptune Armor CDFS
	public boolean isWearingNeptuneSet();
	
	public boolean isWearingAmplifiedNeptuneSet();
	
	public boolean isWearingComboNeptuneSet();
	
	//---------------- Elysian Armor CDFS
	public boolean isWearingElysianSet();
	
	//---------------- Agility Boots CDFS
	public boolean isWearingAgilityBootsAndCape();
	
	public boolean isWearingAgilityBoots();
	
	public boolean isWearingAmplifiedAgilityBoots();
	
	public boolean isWearingAmplifiedAgilityBootsAndCape();
	
	public boolean isWearingPureSpeed();
	
	//---------------- Valkyrie Ring CDFS
	public boolean isWearingValkyrieRing();
	
	public boolean isWearingValkyrieRingAndAmplifiedArmor();
	
	//---------------- Haste Ring CDFS
	public boolean isWearingHasteRing();
	
	public boolean isWearingHasteRingAndArkenium();
	
	public boolean isWearingHasteRingAndArkeniumCombo();
	
	public boolean isWearingHasteRingAmpilifedArkenium();

	public List<ItemStack> getAccessories();

	public int getAccessoryCount(ItemStack stack);

}