package com.gildedgames.the_aether.entities.uro.uroswell;

import com.gildedgames.the_aether.entities.hostile.EntityUro;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIUroSwell extends EntityAIBase
{
    /** The uro that is swelling. */
    EntityUro swellingUro;
    /** The uro's attack target. This is used for the changing of the uro's state. */
    EntityLivingBase uroAttackTarget;

    public EntityAIUroSwell(EntityUro p_i1655_1_)
    {
        this.swellingUro = p_i1655_1_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.swellingUro.getAttackTarget();
        return this.swellingUro.getCreeperState() > 0 || entitylivingbase != null && this.swellingUro.getDistanceSqToEntity(entitylivingbase) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.swellingUro.getNavigator().clearPathEntity();
        this.uroAttackTarget = this.swellingUro.getAttackTarget();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.uroAttackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.uroAttackTarget == null)
        {
            this.swellingUro.setCreeperState(-1);
        }
        else if (this.swellingUro.getDistanceSqToEntity(this.uroAttackTarget) > 64.0D)
        {
            this.swellingUro.setCreeperState(-1);
        }
        else if (!this.swellingUro.getEntitySenses().canSee(this.uroAttackTarget))
        {
            this.swellingUro.setCreeperState(-1);
        }
        else
        {
            this.swellingUro.setCreeperState(1);
        }
    }
}
