package com.gildedgames.the_aether.compatibility;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import com.gildedgames.the_aether.api.accessories.AccessoryType;

public class BaublesExpandedCompatibility {

	public static String accessoryTypeToBaubleType(AccessoryType accessoryType) {
		return switch (accessoryType) {
			case RING, EXTRA_RING -> BaubleExpandedSlots.ringType;
			case PENDANT -> BaubleExpandedSlots.amuletType;
			case CAPE -> BaubleExpandedSlots.capeType;
			case SHIELD -> BaubleExpandedSlots.shieldType;
			case GLOVES -> BaubleExpandedSlots.gauntletType;
			case MISC, EXTRA_MISC -> BaubleExpandedSlots.charmType;
		};
	}

	public static BaubleType accessoryTypeToLegacyBaubleType(AccessoryType accessoryType) {
		return switch (accessoryType) {
			case RING, EXTRA_RING -> BaubleType.RING;
			case PENDANT -> BaubleType.AMULET;
			default -> null;
		};
	}

	public static void tryAssignSlots() {
		BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.capeType, 1);
		BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.charmType, 2);
		BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.gauntletType, 1);
		BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.shieldType, 1);
	}

	public static void initializeSlotTypeData() {
		ringTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.ringType);
		amuletTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.amuletType);
		capeTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.capeType);
		shieldTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.shieldType);
		gauntletTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.gauntletType);
		charmTypeSlots = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.charmType);
	}

	public static int[] baubleSlotsFromAccessoryType(AccessoryType type) {
		return switch (type) {
			case RING, EXTRA_RING -> ringTypeSlots;
			case PENDANT -> amuletTypeSlots;
			case CAPE -> capeTypeSlots;
			case SHIELD -> shieldTypeSlots;
			case GLOVES -> gauntletTypeSlots;
			case MISC, EXTRA_MISC -> charmTypeSlots;
		};
	}

	public static int[] baubleSlotsFromBaubleType(String[] types) {
		if(types.length == 0) {
			return new int[0];
		}
		return switch (types[0]) {
			case BaubleExpandedSlots.ringType -> ringTypeSlots;
			case BaubleExpandedSlots.amuletType -> amuletTypeSlots;
			case BaubleExpandedSlots.capeType -> capeTypeSlots;
			case BaubleExpandedSlots.shieldType -> shieldTypeSlots;
			case BaubleExpandedSlots.gauntletType -> gauntletTypeSlots;
			case BaubleExpandedSlots.charmType -> charmTypeSlots;
			default -> new int[0];
		};
	}

	private static int[] ringTypeSlots;
	private static int[] amuletTypeSlots;
	private static int[] capeTypeSlots;
	private static int[] shieldTypeSlots;
	private static int[] gauntletTypeSlots;
	private static int[] charmTypeSlots;

}
