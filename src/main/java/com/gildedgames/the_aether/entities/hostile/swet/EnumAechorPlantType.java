package com.gildedgames.the_aether.entities.hostile.swet;

public enum EnumAechorPlantType {

    CYAN(), CYAN2(), CYAN3(), CYAN4(), CYAN5(), CYAN6(), BLUE(), DARKBLUE(), LIME(), GREEN(), PURPLE(), MAGENTA(), GOLD();

	EnumAechorPlantType() {

    }

    public int getId() {
        return this.ordinal();
    }

    public static EnumAechorPlantType get(int id) {
        return values()[id];
    }
}
