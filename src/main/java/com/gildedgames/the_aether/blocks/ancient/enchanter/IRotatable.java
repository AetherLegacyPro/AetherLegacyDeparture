package com.gildedgames.the_aether.blocks.ancient.enchanter;

import net.minecraft.world.*;

public interface IRotatable
{
    void rotate(final World p0, final Rotation p1, final int p2, final int p3, final int p4);
    
    void rotate(final World p0, final boolean p1, final int p2, final int p3, final int p4);
    
    Rotation getCurrentRotation(final World p0, final int p1, final int p2, final int p3);
    
    void setCurrentRotation(final World p0, final Rotation p1, final int p2, final int p3, final int p4);
}
