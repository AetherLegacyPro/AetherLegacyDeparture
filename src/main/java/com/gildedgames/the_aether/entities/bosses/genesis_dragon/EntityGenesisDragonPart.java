package com.gildedgames.the_aether.entities.bosses.genesis_dragon;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityGenesisDragonPart extends Entity {
    public final GIEntityMultiPart entityDragonObj;
    public final String field_146032_b;

    public EntityGenesisDragonPart(GIEntityMultiPart multiPart, String p_i1697_2_, float p_i1697_3_, float p_i1697_4_) {
        super(multiPart.func_82194_d());
        this.setSize(p_i1697_3_, p_i1697_4_);
        this.entityDragonObj = multiPart;
        this.field_146032_b = p_i1697_2_;
    }

    protected void entityInit() {
    }

    protected void readEntityFromNBT(NBTTagCompound compound) {
    }

    protected void writeEntityToNBT(NBTTagCompound compound) {
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource source, float p_70097_2_) {
        return this.isEntityInvulnerable() ? false : this.entityDragonObj.aattackEntityFromPart(this, source, p_70097_2_);
    }

    public boolean isEntityEqual(Entity entity) {
        return this == entity || this.entityDragonObj == entity;
    }
}
