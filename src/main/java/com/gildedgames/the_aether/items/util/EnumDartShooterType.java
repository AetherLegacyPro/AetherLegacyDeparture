package com.gildedgames.the_aether.items.util;

public enum EnumDartShooterType {

	Golden(0, "golden"), Poison(1, "poison"), Enchanted(2, "enchanted"), Phoenix(3, "phoenix");

	public int meta;

	public String name;

	EnumDartShooterType(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}

	public static EnumDartShooterType getType(int meta) {
		return meta == 1 ? Poison : meta == 2 ? Enchanted : meta == 3 ? Phoenix : Golden;
	}

	public int getMeta() {
		return this.meta;
	}

	public String toString() {
		return this.name;
	}

}