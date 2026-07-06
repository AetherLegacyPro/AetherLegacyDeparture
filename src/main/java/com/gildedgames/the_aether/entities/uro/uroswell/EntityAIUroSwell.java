package com.gildedgames.the_aether.entities.uro.uroswell;

import com.gildedgames.the_aether.entities.hostile.EntityUro;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIUroSwell extends EntityAIBase {
    EntityUro swellingUro;
    EntityLivingBase uroAttackTarget;

    public EntityAIUroSwell(EntityUro entityUro) {
        this.swellingUro = entityUro;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.swellingUro.getAttackTarget();
        return this.swellingUro.getCreeperState() > 0 || entitylivingbase != null && this.swellingUro.getDistanceSqToEntity(entitylivingbase) < 9.0D;
    }

    public void startExecuting() {
        this.swellingUro.getNavigator().clearPathEntity();
        this.uroAttackTarget = this.swellingUro.getAttackTarget();
    }

    public void resetTask() {
        this.uroAttackTarget = null;
    }

    public void updateTask() {
        if (this.uroAttackTarget == null) {
            this.swellingUro.setCreeperState(-1);
        }
        else if (this.swellingUro.getDistanceSqToEntity(this.uroAttackTarget) > 64.0D) {
            this.swellingUro.setCreeperState(-1);
        }
        else if (!this.swellingUro.getEntitySenses().canSee(this.uroAttackTarget)) {
            this.swellingUro.setCreeperState(-1);
        }
        else {
            this.swellingUro.setCreeperState(1);
        }
    }
}
