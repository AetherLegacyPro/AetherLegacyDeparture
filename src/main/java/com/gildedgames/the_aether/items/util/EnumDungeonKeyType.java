package com.gildedgames.the_aether.items.util;

public enum EnumDungeonKeyType {
	Bronze(0, "bronze"), Silver(1, "silver"), Golden(2, "golden"), 
	AncientBronze(3, "ancientbronze"), DivineBronze(4, "divinebronze"),
	AncientSilver(5, "ancientsilver"), DivineSilver(6, "divinesilver"),
	AncientGolden(7, "ancientgolden"), DivineGolden(8, "divinegolden"), 
	MythicBronze(9, "mythicbronze"), Cobalt(10, "cobalt"), Osmium(11, "osmium"),
	MythicSilver(12, "mythicsilver"), MythicGolden(13, "mythicgolden"), Palladium(14, "palladium");

	private int meta;

	private String name;

	EnumDungeonKeyType(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}

	public static EnumDungeonKeyType getType(int meta) {
		return meta == 1 ? Silver 
		: meta == 2 ? Golden 
		: meta == 3 ? AncientBronze 
		: meta == 4 ? DivineBronze
		: meta == 5 ? AncientSilver
		: meta == 6 ? DivineSilver
		: meta == 7 ? AncientGolden
		: meta == 8 ? DivineGolden
		: meta == 9 ? MythicBronze 
		: meta == 10 ? Cobalt 
		: meta == 11 ? Osmium
		: meta == 12 ? MythicSilver
		: meta == 13 ? MythicGolden
		: meta == 14 ? Palladium:Bronze;
	}

	public int getMeta() {
		return this.meta;
	}

	public String toString() {
		return this.name;
	}

}