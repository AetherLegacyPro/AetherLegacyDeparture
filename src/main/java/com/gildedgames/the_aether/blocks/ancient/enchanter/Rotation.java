package com.gildedgames.the_aether.blocks.ancient.enchanter;

import net.minecraft.world.*;

public enum Rotation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public void rotate(final World world, final IRotatable rotatable, final int x, final int y, final int z) {
        int rotationAmount = this.indexDifference(rotatable.getCurrentRotation(world, x, y, z));
        boolean clockwise = rotationAmount > 0;
        if (rotationAmount == 3) {
            rotationAmount = 1;
            clockwise = false;
        }
        else if (rotationAmount == -3) {
            rotationAmount = 1;
            clockwise = true;
        }
        for (int count = 0; count < Math.abs(rotationAmount); ++count) {
            rotatable.rotate(world, clockwise, x, y, z);
        }
        rotatable.setCurrentRotation(world, this, x, y, z);
    }

    private int indexDifference(final Rotation rotation) {
        return this.ordinal() - rotation.ordinal();
    }

}

