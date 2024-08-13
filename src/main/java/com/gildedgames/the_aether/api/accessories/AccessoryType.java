package com.gildedgames.the_aether.api.accessories;

import com.gildedgames.the_aether.AetherConfig;
import net.minecraft.util.ObjectIntIdentityMap;

public enum AccessoryType {
	RING("ring", 11),
	EXTRA_RING("ring", 11),
	PENDANT("pendant", 16),
	CAPE("cape", 15),
	SHIELD("shield", 13),
	GLOVES("gloves", 10) {
		@Override
		public boolean degrades() {
			return AetherConfig.GlovesDamageable();
		}
	},
	MISC("misc", 10) {
		@Override
		public boolean degrades() {
			return AetherConfig.MiscItemDamageable();
		}
	},
	EXTRA_MISC("misc", 10);

	private final int maxDamage;

	private final String displayName;

	AccessoryType(String displayName, int maxDamage) {
		this.displayName = displayName;
		this.maxDamage = maxDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public String getDisplayName() {
		return displayName;
	}

	public boolean degrades() {
		return true;
	}

	public static ObjectIntIdentityMap createCompleteList() {
		ObjectIntIdentityMap identityMap = new ObjectIntIdentityMap();

		identityMap.func_148746_a(PENDANT, 0);
		identityMap.func_148746_a(CAPE, 1);
		identityMap.func_148746_a(SHIELD, 2);
		identityMap.func_148746_a(MISC, 3);
		identityMap.func_148746_a(RING, 4);
		identityMap.func_148746_a(EXTRA_RING, 5);
		identityMap.func_148746_a(GLOVES, 6);
		identityMap.func_148746_a(EXTRA_MISC, 7);

		return identityMap;

	}

}