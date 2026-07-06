package com.gildedgames.the_aether.entities.uro.uroswell;

import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILurkerSwell extends EntityAIBase {

    EntityLurker swellingLurker;
    EntityLivingBase uroAttackTarget;

    public EntityAILurkerSwell(EntityLurker entityLurker) {
        this.swellingLurker = entityLurker;
        this.setMutexBits(1);
    }
    public boolean shouldExecute() {
    	int rand = (int)(1 + Math.random() * 50);
		if(rand == 10 ) {
        EntityLivingBase entitylivingbase = this.swellingLurker.getAttackTarget();
        return this.swellingLurker.getCreeperState() > 0 || entitylivingbase != null && this.swellingLurker.getDistanceSqToEntity(entitylivingbase) < 12.0D;
        }
       return false;
    }

    public void startExecuting() {
        this.swellingLurker.getNavigator().clearPathEntity();
        this.uroAttackTarget = this.swellingLurker.getAttackTarget();
    }

    public void resetTask() {
        this.uroAttackTarget = null;
    }

    public void updateTask() {
        if (this.uroAttackTarget == null) {
            this.swellingLurker.setCreeperState(-1);
        }
        else if (this.swellingLurker.getDistanceSqToEntity(this.uroAttackTarget) > 36.0D) {
            this.swellingLurker.setCreeperState(-1);
        }
        else if (!this.swellingLurker.getEntitySenses().canSee(this.uroAttackTarget)) {
            this.swellingLurker.setCreeperState(-1);
        }
        else {
            this.swellingLurker.setCreeperState(1);
        }
    }
}

