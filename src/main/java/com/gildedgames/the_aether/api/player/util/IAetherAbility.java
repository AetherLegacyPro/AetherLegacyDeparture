package com.gildedgames.the_aether.api.player.util;

import net.minecraft.util.DamageSource;

public interface IAetherAbility {

	void onUpdate();

	boolean shouldExecute();

	default boolean onPlayerAttacked(DamageSource source) {
		return false;
	}

}