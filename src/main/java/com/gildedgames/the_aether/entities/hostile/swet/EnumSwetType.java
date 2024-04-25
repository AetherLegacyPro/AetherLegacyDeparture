package com.gildedgames.the_aether.entities.hostile.swet;

public enum EnumSwetType {

    BLUE(), PURPLE(), POISON(), GOLDEN();

    EnumSwetType() {

    }

    public int getId() {
        return this.ordinal();
    }

    public static EnumSwetType get(int id) {
        return values()[id];
    }
}