package com.gildedgames.the_aether.entities.uro.uroswell;



import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILurkerSwell extends EntityAIBase
{
    /** The lurker that is swelling. */
	EntityLurker swellingLurker;
    /** The uro's attack target. This is used for the changing of the uro's state. */
    EntityLivingBase uroAttackTarget;
    private static final String __OBFID = "CL_00001614";

    public EntityAILurkerSwell(EntityLurker p_i1655_1_)
    {
        this.swellingLurker = p_i1655_1_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	
    	int rand = (int)(1 + Math.random() * 50);
		if(rand == 10 ) {
        EntityLivingBase entitylivingbase = this.swellingLurker.getAttackTarget();
        return this.swellingLurker.getCreeperState() > 0 || entitylivingbase != null && this.swellingLurker.getDistanceSqToEntity(entitylivingbase) < 12.0D;
        }
		
       return false;

    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.swellingLurker.getNavigator().clearPathEntity();
        this.uroAttackTarget = this.swellingLurker.getAttackTarget();
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
            this.swellingLurker.setCreeperState(-1); //-1
        }
        else if (this.swellingLurker.getDistanceSqToEntity(this.uroAttackTarget) > 64.0D)
        {
            this.swellingLurker.setCreeperState(-1); //-1
        }
        else if (!this.swellingLurker.getEntitySenses().canSee(this.uroAttackTarget))
        {
            this.swellingLurker.setCreeperState(-1); //-1
        }
        else
        {
            this.swellingLurker.setCreeperState(1);
        }
    }
}

