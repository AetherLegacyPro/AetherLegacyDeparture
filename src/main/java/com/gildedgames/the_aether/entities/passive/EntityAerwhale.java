package com.gildedgames.the_aether.entities.passive;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.*;

public class EntityAerwhale extends EntityFlying implements IMob
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
    
    public EntityAerwhale(final World p_i1731_1_) {
        super(p_i1731_1_);
        this.targetObstructedTicks = 0;
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(1, new EntityAILookIdle(this));
        this.setSize(1.5f, 1.0f);
        this.isImmuneToFire = false;
        this.experienceValue = 1;
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
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
                    if (!this.getEntitySenses().canSee(this.targetedEntity)) {
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
		
		super.onLivingUpdate();
	}
    
    public boolean attackEntityFrom(DamageSource ds, float i) {
		if (ds == DamageSource.inWall)
        {
            return false;
        }
		
		boolean flag = super.attackEntityFrom(ds, i);

		return flag;
	}
    
    protected void updateEntityActionState() {
        super.updateEntityActionState();
        
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
                this.motionX += distanceX / distanceScaled * 0.2;
                this.motionY += distanceY / distanceScaled * 0.2;
                this.motionZ += distanceZ / distanceScaled * 0.2;
            }
            else if (this.targetedEntity != null) {
                this.motionX += distanceX / distanceScaled * 0.2;
                this.motionY += distanceY / distanceScaled * 0.2;
                this.motionZ += distanceZ / distanceScaled * 0.2;
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
    
    private boolean isCourseTraversable(final double x, final double y, final double z, final double distance) {
        final double boxX = (this.waypointX - this.posX) / distance;
        final double boxY = (this.waypointY - this.posY) / distance;
        final double boxZ = (this.waypointZ - this.posZ) / distance;
        final AxisAlignedBB axisalignedbb = this.boundingBox.copy();
        for (int i = 1; i < distance; ++i) {
            axisalignedbb.offset(boxX, boxY, boxZ);
            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected void dropFewItems(boolean recentlyHit, int lootLevel) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootLevel);
        int k;

        for (k = 0; k < j; ++k) {
            this.dropItem(ItemsAether.raw_aerwhale, 3);
        }

        j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootLevel);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.dropItem(ItemsAether.enchanted_aerwhale, 3);
            } else {
                this.dropItem(ItemsAether.raw_aerwhale, 3);
            }
        }

        super.dropFewItems(recentlyHit, lootLevel);
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0);
    }
    
    protected void fall(final float p_70069_1_) {
    }
    
    protected void updateFallState(final double p_70064_1_, final boolean p_70064_3_) {
    }
    
    public boolean isOnLadder() {
        return false;
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
	public boolean canDespawn() {
		return true;
	}
    
	@Override
	public boolean getCanSpawnHere() {
	      final int i = MathHelper.floor_double(this.posX);
	      final int j = MathHelper.floor_double(this.boundingBox.minY);
	      final int k = MathHelper.floor_double(this.posZ);
	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn && this.rand.nextInt(AetherConfig.getAerwhaleSpawnrate()) == 0 && super.getCanSpawnHere();
	                       
	}
}