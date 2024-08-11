package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import com.gildedgames.the_aether.entities.effects.PotionInebriation;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityIrk extends EntityMob
{		
	public EntityIrk(final World p_i1745_1_) {
		super(p_i1745_1_);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(0.6F, 1.8F);
		this.experienceValue = 3;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.75D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2D);
		this.setHealth(6);
	}
	
	@Override
	public boolean attackEntityAsMob(final Entity entity) {
		final boolean flag = super.attackEntityAsMob(entity);
		if (flag) {
			final int i = this.worldObj.difficultySetting.getDifficultyId();
			if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < i * 0.3f) {
				entity.setFire(0 * i);
			}
		}
		
		this.heal(1);     
        int rand = (int)(1 + Math.random() * 24);
		switch (rand)
        {
        	case 1:
        		((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 150, 0));
        		break;
            case 2:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
                break;
            case 3:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 0));
                break;
            case 4:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 150, 1));
                break;
            case 5:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 0));
            	break;
            case 6:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
            	break;
            case 7:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200, 1));
            	break;
            case 8:
            	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.harm.id, 1, 0));
            	break;
            default: 
            	((EntityLivingBase) entity).addPotionEffect(new EffectInebriation(PotionInebriation.inebriation.id, 150, 0));
            	break;
        }
        
                
		return flag;
	}
	
	public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.genesis_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.light_genesis_stone) && canSpawn;
                       
    }
	
	public int getTotalArmorValue()
    {
        return 13;
    }
	
	@Override
	public void fall(float distance) {
	}
	
	public float getBrightness(float p_70013_1_)
	{
		return super.getBrightness(p_70013_1_);
	}
	
	 protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{	   
	   this.dropItem(ItemsAether.zanite_nugget, 1);	        	     
	        
	 }
	 
	protected void dropRareDrop(int p_70600_1_)
	{		 
		this.dropItem(ItemsAether.aceninum_shard, 1);
		 
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    protected String getHurtSound() {
        return "aether_legacy:aemob.urk.hurt";
    }
    
    protected String getDeathSound() {
        return "aether_legacy:aemob.urk.death";
    }
    
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("aether_legacy:aemob.urk.step", 0.85F, 1.0F);
    }
	
	@Override
    protected float getSoundVolume() {
        return 0.80F;
    }

}
