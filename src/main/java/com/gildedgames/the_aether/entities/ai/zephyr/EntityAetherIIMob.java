package com.gildedgames.the_aether.entities.ai.zephyr;

import net.minecraft.entity.monster.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public abstract class EntityAetherIIMob extends EntityMob implements IMob
{
    Random random;
    
    public EntityAetherIIMob(final World world) {
        super(world);
        this.random = new Random();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
        this.setHealth(10.0f);
    }
    
}
