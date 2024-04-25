package com.gildedgames.the_aether.entities.util;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.moa.AetherMoaType;
import com.gildedgames.the_aether.api.moa.MoaProperties;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

public class AetherMoaTypes {

	public static AetherMoaType blue, pink, orange, violet, white, black;

	public static void initialization() {
		blue = register("blue", 0x7777FF, new MoaProperties(3, 0.3F));
		pink = register("pink", 0xFFC0CB, new MoaProperties(3, 0.3F));
		orange = register("orange", -0xC3D78, new MoaProperties(2, 0.6F));
		violet = register("violet", 0x8F00FF, new MoaProperties(4, 0.4F));
		white = register("white", 0xFFFFFF, new MoaProperties(5, 0.3F));
		black = register("black", 0x222222, new MoaProperties(8, 0.3F));
	}

	public static AetherMoaType register(String name, int hexColor, MoaProperties properties) {
		AetherMoaType moaType = new AetherMoaType(hexColor, properties, AetherCreativeTabs.misc);

		AetherAPI.instance().register(Aether.locate(name), moaType);

		return moaType;
	}

}