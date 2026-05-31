package com.gildedgames.the_aether.entities.ai.zephyr;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public abstract class EntityAetherIIMob extends EntityMob implements IMob {

    Random random;

    public EntityAetherIIMob(final World world) {
        super(world);
        this.random = new Random();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(10.0);
        this.setHealth(10.0f);
    }

}
