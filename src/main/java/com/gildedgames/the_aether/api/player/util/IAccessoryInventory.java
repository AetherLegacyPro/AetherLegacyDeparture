package com.gildedgames.the_aether.api.player.util;

import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.items.ItemsAether;
import io.netty.buffer.ByteBuf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.gildedgames.the_aether.api.accessories.AccessoryType;

public interface IAccessoryInventory {

	float getCurrentPlayerStrVsBlock(float original);

	void dropAccessories();

	void damageWornItem(int damage, Item item);

	void damageWornItemsAtRate(DegradationRate degradationrate);

	void setAccessorySlot(AccessoryType type, ItemStack stack);

	ItemStack getStackInSlot(AccessoryType type);

	boolean setAccessorySlot(ItemStack stack);

	boolean wearingAccessory(Item item);

	boolean wearingArmor(Item armor);

	int getAccessoryCount(Item item);

	NBTTagList writeToNBT(NBTTagCompound compound);

	void readFromNBT(NBTTagList list);

	void writeData(ByteBuf buf);

	void readData(ByteBuf buf);

	default boolean isWearingZaniteSet() {
		return ((wearingArmor(ItemsAether.zanite_helmet) || wearingArmor(ItemsAether.scaled_zanite_helmet))
				&& (wearingArmor(ItemsAether.zanite_chestplate) || wearingArmor(ItemsAether.scaled_zanite_chestplate))
				&& (wearingArmor(ItemsAether.zanite_leggings) || wearingArmor(ItemsAether.scaled_zanite_leggings))
				&& (wearingArmor(ItemsAether.zanite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_zanite_boots))
				&& wearingAccessory(ItemsAether.zanite_gloves));
	}

	//---------------- Phoenix Armor
	default boolean isWearingPhoenixSet() {
		return (wearingArmor(ItemsAether.phoenix_helmet) || wearingArmor(ItemsAether.scaled_phoenix_helmet))
				&& (wearingArmor(ItemsAether.phoenix_chestplate) || wearingArmor(ItemsAether.scaled_phoenix_chestplate))
				&& (wearingArmor(ItemsAether.phoenix_leggings) || wearingArmor(ItemsAether.scaled_phoenix_leggings))
				&& (wearingArmor(ItemsAether.phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_phoenix_boots))
				&& wearingAccessory(ItemsAether.phoenix_gloves);
	}

	default boolean isWearingPhoenixComboSet() {
		return (wearingArmor(ItemsAether.phoenix_helmet) || wearingArmor(ItemsAether.amplified_phoenix_helmet) || wearingArmor(ItemsAether.scaled_phoenix_helmet))
				&& (wearingArmor(ItemsAether.phoenix_chestplate) || wearingArmor(ItemsAether.amplified_phoenix_chestplate) || wearingArmor(ItemsAether.scaled_phoenix_chestplate))
				&& (wearingArmor(ItemsAether.phoenix_leggings) || wearingArmor(ItemsAether.amplified_phoenix_leggings) || wearingArmor(ItemsAether.scaled_phoenix_leggings))
				&& (wearingArmor(ItemsAether.phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_phoenix_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_phoenix_boots))
				&& (wearingAccessory(ItemsAether.phoenix_gloves) || wearingAccessory(ItemsAether.amplified_phoenix_gloves));
	}

	default boolean isWearingAmplifiedPhoenixSet() {
		return wearingArmor(ItemsAether.amplified_phoenix_helmet)
				&& wearingArmor(ItemsAether.amplified_phoenix_chestplate)
				&& wearingArmor(ItemsAether.amplified_phoenix_leggings)
				&& (wearingArmor(ItemsAether.amplified_phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_phoenix_gloves);
	}
	
	//boolean isWearingScaledPhoenixSet();

	//---------------- Obsidian Armor
	default boolean isWearingObsidianSet() {
		return (wearingArmor(ItemsAether.obsidian_helmet) || wearingArmor(ItemsAether.scaled_obsidian_helmet))
				&& (wearingArmor(ItemsAether.obsidian_chestplate) || wearingArmor(ItemsAether.scaled_obsidian_chestplate))
				&& (wearingArmor(ItemsAether.obsidian_leggings) || wearingArmor(ItemsAether.scaled_obsidian_leggings))
				&& (wearingArmor(ItemsAether.obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_obsidian_boots))
				&& wearingAccessory(ItemsAether.obsidian_gloves);
	}

	default boolean isWearingObsidianComboSet() {
		return (wearingArmor(ItemsAether.obsidian_helmet) || wearingArmor(ItemsAether.amplified_obsidian_helmet) || wearingArmor(ItemsAether.scaled_obsidian_helmet))
				&& (wearingArmor(ItemsAether.obsidian_chestplate) || wearingArmor(ItemsAether.amplified_obsidian_chestplate) || wearingArmor(ItemsAether.scaled_obsidian_chestplate))
				&& (wearingArmor(ItemsAether.obsidian_leggings) || wearingArmor(ItemsAether.amplified_obsidian_leggings) || wearingArmor(ItemsAether.scaled_obsidian_leggings))
				&& (wearingArmor(ItemsAether.obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_obsidian_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_obsidian_boots))
				&& (wearingAccessory(ItemsAether.obsidian_gloves) || wearingAccessory(ItemsAether.amplified_obsidian_gloves));
	}

	default boolean isWearingAmplifiedObsidianSet() {
		return wearingArmor(ItemsAether.amplified_obsidian_helmet)
				&& wearingArmor(ItemsAether.amplified_obsidian_chestplate)
				&& wearingArmor(ItemsAether.amplified_obsidian_leggings)
				&& (wearingArmor(ItemsAether.amplified_obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_obsidian_gloves);
	}
	
	//boolean isWearingScaledObsidianSet();
	
	//--------------- Arkenium Armor
	default boolean isWearingArkeniumSet() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.scaled_arkenium_helmet))
				&& (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.scaled_arkenium_chestplate))
				&& (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.scaled_arkenium_leggings))
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_arkenium_boots))
				&& wearingAccessory(ItemsAether.arkenium_gloves);
	}

	default boolean isWearingArkeniumComboSet() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.amplified_arkenium_helmet) || wearingArmor(ItemsAether.scaled_arkenium_helmet))
				&& (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.amplified_arkenium_chestplate) || wearingArmor(ItemsAether.scaled_arkenium_chestplate))
				&& (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.amplified_arkenium_leggings) || wearingArmor(ItemsAether.scaled_arkenium_leggings))
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_arkenium_boots))
				&& (wearingAccessory(ItemsAether.arkenium_gloves) || wearingAccessory(ItemsAether.amplified_arkenium_gloves));
	}

	default boolean isWearingAmplifiedArkeniumSet() {
		return wearingArmor(ItemsAether.amplified_arkenium_helmet)
				&& wearingArmor(ItemsAether.amplified_arkenium_chestplate)
				&& wearingArmor(ItemsAether.amplified_arkenium_leggings)
				&& (wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_arkenium_gloves);
	}
	
	//boolean isWearingScaledArkeniumSet();
	
	//--------------- Continuum Armor
	default boolean isWearingContinuumSet() {
		return (wearingArmor(ItemsAether.continuum_helmet) || wearingArmor(ItemsAether.scaled_continuum_helmet))
				&& (wearingArmor(ItemsAether.continuum_chestplate) || wearingArmor(ItemsAether.scaled_continuum_chestplate))
				&& (wearingArmor(ItemsAether.continuum_leggings) || wearingArmor(ItemsAether.scaled_continuum_leggings))
				&& (wearingArmor(ItemsAether.continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_continuum_boots))
				&& wearingAccessory(ItemsAether.continuum_gloves);
	}

	default boolean isWearingContinuumComboSet() {
		return (wearingArmor(ItemsAether.continuum_helmet) || wearingArmor(ItemsAether.amplified_continuum_helmet) || wearingArmor(ItemsAether.scaled_continuum_helmet))
				&& (wearingArmor(ItemsAether.continuum_chestplate) || wearingArmor(ItemsAether.amplified_continuum_chestplate) || wearingArmor(ItemsAether.scaled_continuum_chestplate))
				&& (wearingArmor(ItemsAether.continuum_leggings) || wearingArmor(ItemsAether.amplified_continuum_leggings) || wearingArmor(ItemsAether.scaled_continuum_leggings))
				&& (wearingArmor(ItemsAether.continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_continuum_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_continuum_boots))
				&& (wearingAccessory(ItemsAether.continuum_gloves) || wearingAccessory(ItemsAether.amplified_continuum_gloves));
	}

	default boolean isWearingAmplifiedContinuumSet() {
		return wearingArmor(ItemsAether.amplified_continuum_helmet)
				&& wearingArmor(ItemsAether.amplified_continuum_chestplate)
				&& wearingArmor(ItemsAether.amplified_continuum_leggings)
				&& (wearingArmor(ItemsAether.amplified_continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_continuum_gloves);
	}
	
	//boolean isWearingScaledContinuumSet();
	
	//---------------- Gravitite & Divineral CDFS
	default boolean isWearingGravititeSet() {
		return (wearingArmor(ItemsAether.gravitite_helmet)) || wearingArmor(ItemsAether.scaled_gravitite_helmet)
				&& (wearingArmor(ItemsAether.gravitite_chestplate) || wearingArmor(ItemsAether.scaled_gravitite_chestplate))
				&& (wearingArmor(ItemsAether.gravitite_leggings) || wearingArmor(ItemsAether.scaled_gravitite_leggings))
				&& (wearingArmor(ItemsAether.gravitite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_gravitite_boots))
				&& wearingAccessory(ItemsAether.gravitite_gloves);
	}

	default boolean isWearingGravititeAndDivineralSet() {
		return (wearingArmor(ItemsAether.gravitite_helmet) || wearingArmor(ItemsAether.divineral_helmet) || wearingArmor(ItemsAether.scaled_gravitite_helmet))
				&& (wearingArmor(ItemsAether.gravitite_chestplate) || wearingArmor(ItemsAether.divineral_chestplate) || wearingArmor(ItemsAether.scaled_gravitite_chestplate))
				&& (wearingArmor(ItemsAether.gravitite_leggings) || wearingArmor(ItemsAether.divineral_leggings) || wearingArmor(ItemsAether.scaled_gravitite_leggings))
				&& (wearingArmor(ItemsAether.gravitite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.divineral_boots) || wearingArmor(ItemsAether.scaled_gravitite_boots))
				&& (wearingAccessory(ItemsAether.gravitite_gloves) || wearingAccessory(ItemsAether.divineral_gloves));
	}

	default boolean isWearingDivineralSet() {
		return wearingArmor(ItemsAether.divineral_helmet)
				&& wearingArmor(ItemsAether.divineral_chestplate)
				&& wearingArmor(ItemsAether.divineral_leggings)
				&& (wearingArmor(ItemsAether.divineral_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.divineral_gloves);
	}
	
	//boolean isWearingScaledGravititeSet();
	
	//---------------- Valkyrie Armor CDFS
	default boolean isWearingValkyrieSet() {
		return (wearingArmor(ItemsAether.valkyrie_helmet) || wearingArmor(ItemsAether.scaled_valkyrie_helmet))
				&& (wearingArmor(ItemsAether.valkyrie_chestplate) || wearingArmor(ItemsAether.scaled_valkyrie_chestplate))
				&& (wearingArmor(ItemsAether.valkyrie_leggings) || wearingArmor(ItemsAether.scaled_valkyrie_leggings))
				&& (wearingArmor(ItemsAether.valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
				&& wearingAccessory(ItemsAether.valkyrie_gloves);
	}

	default boolean isWearingValkyrieComboSet() {
		return (wearingArmor(ItemsAether.valkyrie_helmet) || wearingArmor(ItemsAether.amplified_valkyrie_helmet) || wearingArmor(ItemsAether.scaled_valkyrie_helmet))
				&& (wearingArmor(ItemsAether.valkyrie_chestplate) || wearingArmor(ItemsAether.amplified_valkyrie_chestplate) || wearingArmor(ItemsAether.scaled_valkyrie_chestplate))
				&& (wearingArmor(ItemsAether.valkyrie_leggings) || wearingArmor(ItemsAether.amplified_valkyrie_leggings) || wearingArmor(ItemsAether.scaled_valkyrie_leggings))
				&& (wearingArmor(ItemsAether.valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
				&& (wearingAccessory(ItemsAether.valkyrie_gloves) || wearingAccessory(ItemsAether.amplified_valkyrie_gloves));
	}

	default boolean isWearingAmplifiedValkyrieSet() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}
	
	//---------------- Neptune Armor CDFS
	default boolean isWearingNeptuneSet() {
		return (wearingArmor(ItemsAether.scaled_neptune_helmet) || wearingArmor(ItemsAether.neptune_helmet))
				&& (wearingArmor(ItemsAether.scaled_neptune_chestplate) || wearingArmor(ItemsAether.neptune_chestplate))
				&& (wearingArmor(ItemsAether.scaled_neptune_leggings) || wearingArmor(ItemsAether.neptune_leggings))
				&& (wearingArmor(ItemsAether.scaled_neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.neptune_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.neptune_gloves);
	}

	default boolean isWearingAmplifiedNeptuneSet() {
		return wearingArmor(ItemsAether.amplified_neptune_helmet)
				&& wearingArmor(ItemsAether.amplified_neptune_chestplate)
				&& wearingArmor(ItemsAether.amplified_neptune_leggings)
				&& (wearingArmor(ItemsAether.amplified_neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_neptune_gloves);
	}

	default boolean isWearingComboNeptuneSet() {
		return (wearingArmor(ItemsAether.neptune_helmet) || wearingArmor(ItemsAether.amplified_neptune_helmet) || wearingArmor(ItemsAether.scaled_neptune_helmet))
				&& (wearingArmor(ItemsAether.neptune_chestplate) || wearingArmor(ItemsAether.amplified_neptune_chestplate) || wearingArmor(ItemsAether.scaled_neptune_chestplate))
				&& (wearingArmor(ItemsAether.neptune_leggings) || wearingArmor(ItemsAether.amplified_neptune_leggings) || wearingArmor(ItemsAether.scaled_neptune_leggings))
				&& (wearingArmor(ItemsAether.neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_neptune_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_neptune_boots))
				&& (wearingAccessory(ItemsAether.neptune_gloves) || wearingAccessory(ItemsAether.amplified_neptune_gloves));
	}
	
	//---------------- Elysian Armor CDFS
	default boolean isWearingElysianSet() {
		return (wearingArmor(ItemsAether.elysian_helmet) || wearingArmor(ItemsAether.elysian_helmet))
				&& (wearingArmor(ItemsAether.elysian_chestplate) || wearingArmor(ItemsAether.elysian_chestplate))
				&& (wearingArmor(ItemsAether.elysian_leggings) || wearingArmor(ItemsAether.elysian_leggings))
				&& (wearingArmor(ItemsAether.elysian_boots) || wearingArmor(ItemsAether.elysian_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
				&& wearingAccessory(ItemsAether.elysian_gloves);
	}
	
	//---------------- Agility Boots CDFS
	default boolean isWearingAgilityBootsAndCape() {
		return (wearingArmor(ItemsAether.agility_boots) || wearingArmor(ItemsAether.scaled_agility_boots)) && wearingAccessory(ItemsAether.agility_cape);
	}

	default boolean isWearingAgilityBoots() {
		return wearingArmor(ItemsAether.agility_boots) || wearingArmor(ItemsAether.scaled_agility_boots);
	}

	default boolean isWearingAmplifiedAgilityBoots() {
		return wearingArmor(ItemsAether.amplified_agility_boots);
	}
	
	default boolean isWearingAmplifiedAgilityBootsAndCape() {
		return wearingArmor(ItemsAether.amplified_agility_boots) && wearingAccessory(ItemsAether.agility_cape);
	}

	default boolean isWearingPureSpeed() {
		return (wearingArmor(ItemsAether.amplified_agility_boots)) && wearingAccessory(ItemsAether.agility_cape)
				&& (wearingAccessory(ItemsAether.auralite_pendant) || wearingAccessory(ItemsAether.reinforced_auralite_pendant) || wearingAccessory(ItemsAether.amplified_auralite_pendant))
				&& wearingAccessory(ItemsAether.auralite_ring);
	}
	
	//---------------- Valkyrie Ring CDFS
	default boolean isWearingValkyrieRing() {
		return wearingAccessory(ItemsAether.valkyrie_ring) || wearingAccessory(ItemsAether.reinforced_valkyrie_ring) || wearingAccessory(ItemsAether.amplified_valkyrie_ring);
	}

	default boolean isWearingValkyrieRingAndAmplifiedArmor() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& (wearingAccessory(ItemsAether.valkyrie_ring) || wearingAccessory(ItemsAether.reinforced_valkyrie_ring))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}

	default boolean isWearingAmplifiedValkyrieRingAndAmplifiedArmor() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_ring)
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}
	
	//---------------- Haste Ring CDFS
	default boolean isWearingHasteRing() {
		return (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}

	default boolean isWearingHasteRingAndArkenium() {
		return wearingArmor(ItemsAether.arkenium_helmet)
				&& wearingArmor(ItemsAether.arkenium_chestplate)
				&& wearingArmor(ItemsAether.arkenium_leggings)
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.arkenium_gloves)
				&& (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}
	
	default boolean isWearingHasteRingAndArkeniumCombo() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.amplified_arkenium_helmet))
				&& (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.amplified_arkenium_chestplate))
				&& (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.amplified_arkenium_leggings))
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& (wearingAccessory(ItemsAether.arkenium_gloves) || wearingAccessory(ItemsAether.amplified_arkenium_gloves))
				&& (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}

	default boolean isWearingHasteRingAmpilifedArkenium() {
		return wearingArmor(ItemsAether.amplified_arkenium_helmet)
				&& wearingArmor(ItemsAether.amplified_arkenium_chestplate)
				&& wearingArmor(ItemsAether.amplified_arkenium_leggings)
				&& (wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_arkenium_gloves)
				&& wearingAccessory(ItemsAether.amplified_haste_ring);
	}

	//--------------- Discharge Cape
	default boolean isWearingDischargeCape(){
		return wearingAccessory(ItemsAether.discharge_cape);
	}

	default boolean isWearingAscensiteSet() {
		return wearingArmor(ItemsAether.ascensite_helmet)
				&& wearingArmor(ItemsAether.ascensite_chestplate)
				&& wearingArmor(ItemsAether.ascensite_leggings)
				&& (wearingArmor(ItemsAether.ascensite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.ascensite_gloves);
	}

}