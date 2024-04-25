package com.gildedgames.the_aether.entities.hostile.swet;

public enum EnumMimicType {

    RED(), ORANGE(), YELLOW(), GREEN(), PURPLE();

	EnumMimicType() {

    }

    public int getId() {
        return this.ordinal();
    }

    public static EnumMimicType get(int id) {
        return values()[id];
    }
}
