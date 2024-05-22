package com.gildedgames.the_aether.entities.hostile;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.*;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.projectile.EntityZojzSnowball;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.*;

public class EntityZojz extends EntityAetherMob implements IMob
{
    private int heightOffsetUpdateTime;
    private float heightOffset;
    private int attackTimer;
    public float sinage;
    public int timeUntilShoot;
    public int shootTime;
    
    public int courseChangeCooldown;

	public double waypointX, waypointY, waypointZ;

	public int prevAttackCounter;

	public int attackCounter;
	
	private final float base;
    
    public EntityZojz(final World world) {
        super(world);
        this.heightOffset = 1.5f;
        this.timeUntilShoot = 15;
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(55.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
        this.setSize(1.0f, 1.0f);
        this.isImmuneToFire = true;
        this.attackTime = this.timeUntilShoot;
        this.base = (this.getRNG().nextFloat() - this.getRNG().nextFloat()) * 0.2F + 1.0F;
        this.setHealth(40);
    }
    
    public void onUpdate() {
        super.onUpdate();
        this.jumpMovementFactor = 0.0f;
        if (this.getHealth() > 0.0f) {
            final double a = this.rand.nextFloat() - 0.5f;
            final double b = this.rand.nextFloat();
            final double c = this.rand.nextFloat() - 0.5f;
            final double d = this.posX + a * b;
            final double e = this.boundingBox.minY + b - 0.30000001192092896;
            final double f = this.posZ + c * b;
            this.worldObj.spawnParticle("reddust", d, e, f, 1.0, 1.0, 1.0);
        }
        if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack))
        {
			
		if (this.getEntityToAttack() != null) {
			if (this.getAttackTarget() instanceof EntityPlayer && ((EntityPlayer) this.getAttackTarget()).capabilities.isCreativeMode) {
				this.setAttackTarget(null);
			}
			else {
				double d = this.getEntityToAttack().posX - this.posX;
				double d1 = this.getEntityToAttack().posZ - this.posZ;

				this.getLookHelper().setLookPositionWithEntity(this.getEntityToAttack(), 30.0F, 30.0F);

				if (this.shootTime >= 10 && this.canEntityBeSeen(this.getEntityToAttack())) {
					this.shootTarget();
					this.shootTime = -20;
				}

				if (this.shootTime < 10) {
					this.shootTime += 5;
				}

				this.rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
				}
			
			}
				
		}   
    }
    
    public void shootTarget() {
   		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
   			return;
   		}  			 		
   			EntityZojzSnowball entityarrow2 = new EntityZojzSnowball(this.worldObj, this, 2.0F);
   			this.playSound("aether_legacy:aemob.zephyr.call", 2.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
   			this.worldObj.spawnEntityInWorld(entityarrow2);
   			
   		
   	}
       
       private boolean shouldAttackPlayer(EntityPlayer p_70821_1_)
       {
               Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
               Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
               double d0 = vec31.lengthVector();
               vec31 = vec31.normalize();
               double d1 = vec3.dotProduct(vec31);
               return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
       }
       
      @Override
      public boolean attackEntityFrom(DamageSource ds, float i) {
   	  Entity entity = ds.getEntity();
   	  if (entity instanceof EntityPlayer)
          {
   		 int random1 = (int)(1 + Math.random() * 2);
      	 if(random1 == 1 ) {
      		this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
      	   }
          }
   	  
   	  	return super.attackEntityFrom(ds, i);
       }
    
    @Override
    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);         
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_angelic_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_light_angelic_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_mythic_light_angelic_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_mythic_angelic_stone) && this.worldObj.getBlockLightValue(i, j, k) < 14 && canSpawn;
                       
    }
    
    public void onLivingUpdate() {
    	if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
            this.damageEntity(DamageSource.drown, 1.0f);
        }
    	if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			this.setDead();
		}
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (!this.worldObj.isRemote) {
            --this.heightOffsetUpdateTime;
            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 1.5f + (float)this.rand.nextGaussian() * 3.0f;
            }
            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + this.getEntityToAttack().getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
                this.motionY += (0.700000011920929 - this.motionY) * 0.700000011920929;
            }
        }
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.8;
        }
        if (this.worldObj.isRemote) {
            this.tickAnimation();
        }
        super.onLivingUpdate();
    }
    
    @SideOnly(Side.CLIENT)
    private void tickAnimation() {
        if (this.hurtTime > 0) {
            this.sinage += 0.9f;
        }
        else {
            this.sinage += 0.2f;
        }
        if (this.sinage > 6.283186f) {
            this.sinage -= 6.283186f;
        }
    }
    
    protected void fall(final float par1) {
    }
    
    protected void jump() {
    }
    
    @Override
	protected String getLivingSound() {
		return "aether_legacy:aemob.zephyr.call";
	}

	@Override
	protected String getHurtSound() {
		return "aether_legacy:aemob.zephyr.call";
	}

	@Override
	protected String getDeathSound() {
		return null;
	}
    
    public boolean canDespawn() {
        return true;
    }
    
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.65F;
    }
    
    @Override
	protected void dropFewItems(boolean var1, int var2) {
		int rand = (int)(1 + Math.random() * 3);
		switch (rand)
        {
        case 1: this.dropItem(ItemsAether.tempest_core, 1);
        break;
        case 2: this.dropItem(Item.getItemFromBlock(BlocksAether.storm_aercloud), 1);
        break;
        case 3:
        break;
        }
	}
    
}
