package com.gildedgames.the_aether.api.player.util;

import net.minecraft.util.DamageSource;

public interface IAetherAbility {

	public void onUpdate();

	public boolean shouldExecute();

}