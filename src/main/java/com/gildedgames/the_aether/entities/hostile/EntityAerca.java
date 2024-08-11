package com.gildedgames.the_aether.entities.hostile;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.entity.*;

public class EntityAerca extends EntityFlying implements IMob
{
    public int flapSoundTime;
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private EntityLivingBase targetedEntity;
    private int targetObstructedTicks;
    public float animTime;
    public float prevAnimTime;
    
    public EntityAerca(final World p_i1731_1_) {
        super(p_i1731_1_);
        this.targetObstructedTicks = 0;
        this.tasks.addTask(0, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(1, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(1.5f, 1.5f);
        this.isImmuneToFire = false;
        this.experienceValue = 10;
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Byte((byte)0));
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            if (this.motionY < 0.0 && this.posX != this.waypointX && this.posZ != this.waypointZ && this.targetedEntity == null) {
                this.motionY *= 1.0;
                this.motionX *= 1.0;
                this.motionZ *= 1.0;
            }
            if (this.targetedEntity != null) {
                if (this.targetedEntity instanceof EntityPlayer) {
                    if (!this.getEntitySenses().canSee((Entity)this.targetedEntity)) {
                        ++this.targetObstructedTicks;
                    }
                    else {
                        this.targetObstructedTicks = 0;
                    }
                }
                if (((EntityPlayer)this.targetedEntity).capabilities.isCreativeMode || this.targetObstructedTicks > 100) {
                    this.targetObstructedTicks = 0;
                    this.targetedEntity = null;
                }
                else {
                    final double standOffX = this.targetedEntity.posX;
                    final double standOffZ = this.targetedEntity.posZ;
                    this.waypointX = standOffX;
                    this.waypointY = this.targetedEntity.posY + 1.0 - this.rand.nextFloat() * 0.3f;
                    this.waypointZ = standOffZ;
                }
            }
        }
    }
    
    public void onLivingUpdate()
	{		
    	if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
            this.damageEntity(DamageSource.drown, 2.0f);
        }
		
		super.onLivingUpdate();
	}
    
    @Override
    public boolean attackEntityAsMob(final Entity target) {			

        if (target instanceof EntityPlayer) {
                      
            target.attackEntityFrom(DamageSource.generic, 2.0F);

             
        }
        
        return true;
    }
    
    protected void updateEntityActionState() {
        super.updateEntityActionState();
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
            this.damageEntity(DamageSource.drown, 2.0f);
        }
        double distanceX = this.waypointX - this.posX;
        double distanceY = this.waypointY - this.posY;
        double distanceZ = this.waypointZ - this.posZ;
        double distanceScaled = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        if (distanceScaled < 1.0 || distanceScaled > 3600.0) {
            this.waypointX = this.posX + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointY = this.posY + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 10;
            distanceScaled = MathHelper.sqrt_double(distanceScaled);
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, distanceScaled)) {
                this.motionX += distanceX / distanceScaled * 0.45;
                this.motionY += distanceY / distanceScaled * 0.45;
                this.motionZ += distanceZ / distanceScaled * 0.45;
            }
            else if (this.targetedEntity != null) {
                this.motionX += distanceX / distanceScaled * 0.45;
                this.motionY += distanceY / distanceScaled * 0.45;
                this.motionZ += distanceZ / distanceScaled * 0.45;
            }
            else {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }
        this.getEntitySenses().clearSensingCache();
        if (this.targetedEntity == null) {
            this.targetedEntity = (EntityLivingBase)this.getClosestVulnerableVisiblePlayer(20.0);
        }
        if (this.targetedEntity != null) {
            distanceX = this.targetedEntity.posX - this.posX;
            distanceY = this.targetedEntity.boundingBox.minY + this.targetedEntity.height / 2.0f - (this.posY + this.height / 2.0f);
            distanceZ = this.targetedEntity.posZ - this.posZ;
            final float n = -(float)Math.atan2(distanceX, distanceZ) * 180.0f / 3.1415927f;
            this.rotationYaw = n;
            this.renderYawOffset = n;
        }
        else {
            final float n2 = -(float)Math.atan2(this.motionX, this.motionZ) * 180.0f / 3.1415927f;
            this.rotationYaw = n2;
            this.renderYawOffset = n2;
        }
    }
    
    public EntityPlayer getClosestVulnerableVisiblePlayer(final double p_72846_7_) {
        double d4 = -1.0;
        EntityPlayer entityplayer = null;
        for (int i = 0; i < this.worldObj.playerEntities.size(); ++i) {
            final EntityPlayer entityplayer2 = (EntityPlayer) this.worldObj.playerEntities.get(i);
            if (!entityplayer2.capabilities.disableDamage && entityplayer2.isEntityAlive() && this.getEntitySenses().canSee((Entity)entityplayer2)) {
                final double d5 = entityplayer2.getDistanceSq(this.posX, this.posY, this.posZ);
                double d6 = p_72846_7_;
                if (entityplayer2.isSneaking()) {
                    d6 = p_72846_7_ * 0.800000011920929;
                }
                if (entityplayer2.isInvisible()) {
                    float f = entityplayer2.getArmorVisibility();
                    if (f < 0.1f) {
                        f = 0.1f;
                    }
                    d6 *= 0.7f * f;
                }
                if ((p_72846_7_ < 0.0 || d5 < d6 * d6) && (d4 == -1.0 || d5 < d4)) {
                    d4 = d5;
                    entityplayer = entityplayer2;
                }
            }
        }
        return entityplayer;
    }
    
    private boolean isCourseTraversable(final double x, final double y, final double z, final double distance) {
        final double boxX = (this.waypointX - this.posX) / distance;
        final double boxY = (this.waypointY - this.posY) / distance;
        final double boxZ = (this.waypointZ - this.posZ) / distance;
        final AxisAlignedBB axisalignedbb = this.boundingBox.copy();
        for (int i = 1; i < distance; ++i) {
            axisalignedbb.offset(boxX, boxY, boxZ);
            if (!this.worldObj.getCollidingBoundingBoxes((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public void onCollideWithPlayer(final EntityPlayer player) {
        super.onCollideWithPlayer(player);
        if (!player.capabilities.isCreativeMode && !this.worldObj.isRemote && this.getEntitySenses().canSee((Entity)player) && this.getDistanceToEntity((Entity)player) <= 1.8f && player.boundingBox.maxY >= this.boundingBox.minY && player.boundingBox.minY <= this.boundingBox.maxY && this.attackTime <= 0 && this.attackEntityAsMob((player))) {
            this.attackTime = 20;
            player.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 2.0f);
            //this.playSound("nova_craft:phantom.hurt", 1.0f, 1.0f);
        }
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
    }
    
    protected void fall(final float p_70069_1_) {
    }
    
    protected void updateFallState(final double p_70064_1_, final boolean p_70064_3_) {
    }
    
    public boolean isOnLadder() {
        return false;
    }
    
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (this.worldObj.isRemote) {
            final float f = MathHelper.cos((48 + this.ticksExisted) * 0.13f + 3.1415927f);
            final float f2 = MathHelper.cos((48 + this.ticksExisted + 1) * 0.13f + 3.1415927f);
            //if (f > 0.0f && f2 <= 0.0f) {
               // this.worldObj.playSound(this.posX, this.posY, this.posZ, "nova_craft:phantom.flap", 0.95f + this.rand.nextFloat() * 0.05f, 0.95f + this.rand.nextFloat() * 0.05f, false);
            //}         
        }
    }
    
    @Override
   	public String getLivingSound() {
   		return "aether_legacy:aemob.aerwhale.call";
   	}

   	@Override
   	protected String getHurtSound() {
   		return "aether_legacy:aemob.aerwhale.death";
   	}

   	@Override
   	protected String getDeathSound() {
   		return "aether_legacy:aemob.aerwhale.death";
   	}
   	
   	@Override
    protected float getSoundVolume() {
        return 0.6F;
    }
   	
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.55f;
    }
    
   	@Override
    protected void dropFewItems(boolean recentlyHit, int lootLevel) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootLevel);
        int k;
        
        int rand = (int)(1 + Math.random() * 3);
		switch (rand)
        {
        case 1: this.dropItem(ItemsAether.aerca_tooth, 1 + lootLevel);
        	break;
        case 2: 
        	break;
        case 3: 
        	break;
        }

        for (k = 0; k < j; ++k) {
            this.dropItem(ItemsAether.raw_aerwhale, 1);
        }

        j = this.rand.nextInt(1) + 1 + this.rand.nextInt(1 + lootLevel);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.dropItem(ItemsAether.enchanted_aerwhale, 1);
            } else {
                this.dropItem(ItemsAether.raw_aerwhale, 1);
            }
        }

        super.dropFewItems(recentlyHit, lootLevel);
    }
   	
   	public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.getEntity();
            
            entityplayer.triggerAchievement(AchievementsAether.aether_hunter);
            
        }
            
    }
    
   	@Override
	public boolean getCanSpawnHere() {
	      final int i = MathHelper.floor_double(this.posX);
	      final int j = MathHelper.floor_double(this.boundingBox.minY);
	      final int k = MathHelper.floor_double(this.posZ);
	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);          
	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) < 8 && canSpawn && this.rand.nextInt(AetherConfig.getAercaSpawnrate()) == 0 && super.getCanSpawnHere();
	                       
	}
}
