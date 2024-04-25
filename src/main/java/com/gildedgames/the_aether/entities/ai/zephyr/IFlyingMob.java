package com.gildedgames.the_aether.entities.ai.zephyr;

public interface IFlyingMob
{
    boolean fly();
    
    boolean hostileMob();
    
    void setAttacking(final boolean p0);
    
    float getFlySpeed();
    
    boolean isAttacking();
    
    void setSinage(final float p0);
}
