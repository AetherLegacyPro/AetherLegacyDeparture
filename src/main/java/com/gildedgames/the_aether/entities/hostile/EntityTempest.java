package com.gildedgames.the_aether.entities.hostile;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.*;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.projectile.EntityTempestSnowball;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class EntityTempest extends EntityAetherMob implements IMob
{
    private int heightOffsetUpdateTime;
    private float heightOffset;
    private int attackTimer;
    public float sinage;
    public int timeUntilShoot;
    
    public int courseChangeCooldown;

	public double waypointX, waypointY, waypointZ;

	public int prevAttackCounter;

	public int attackCounter;
	
	private final float base;
    
    public EntityTempest(final World world) {
        super(world);
        this.heightOffset = 1.5f;
        this.timeUntilShoot = 30;
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(55.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
        this.setHealth(15.0f);
        this.setSize(1.25f, 1.5f);
        this.isImmuneToFire = true;
        this.attackTime = this.timeUntilShoot;
        this.base = (this.getRNG().nextFloat() - this.getRNG().nextFloat()) * 0.2F + 1.0F;
        this.setHealth(25);
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
        if (this.entityToAttack != null) {
            this.attackEntity(this.entityToAttack, this.getDistanceToEntity(this.entityToAttack));
        }
    }
    
    @Override
   	public boolean getCanSpawnHere() {
   		return this.rand.nextInt(AetherConfig.getTempestSpawnrate()) == 0 && super.getCanSpawnHere();
   	}
    
    @Override
	protected void updateEntityActionState()
	{
	
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			this.setDead();
		}
                                
		this.despawnEntity();
		this.prevAttackCounter = this.attackCounter;
		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;
		
		
		if (d3 < 1.0D || d3 > 3600.0D)
		{
			this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		}

		if (this.courseChangeCooldown-- <= 0)
		{
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = (double)MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
			{
				this.motionX += d0 / d3 * 0.1D;
				this.motionY += d1 / d3 * 0.1D;
				this.motionZ += d2 / d3 * 0.1D;
			}
			else
			{
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}

		this.prevAttackCounter = this.attackCounter;

		if (this.getAttackTarget() == null) {
			if (this.attackCounter > 0) {
				this.attackCounter--;
			}

			this.setAttackTarget(this.worldObj.getClosestVulnerablePlayerToEntity(this, 40D));
		} else {
			if (this.getAttackTarget() instanceof EntityPlayer && (((EntityPlayer) this.getAttackTarget()).capabilities.isCreativeMode)) {
				this.setAttackTarget(null);
				return;
			}

			if (this.getAttackTarget().getDistanceSqToEntity(this) < 4096.0D && this.canEntityBeSeen(this.getAttackTarget())) {
				double x = this.getAttackTarget().posX - this.posX;
				double y = (this.getAttackTarget().boundingBox.minY + (this.getAttackTarget().height / 2.0F)) - (this.posY + (this.height / 2.0F));
				double z = this.getAttackTarget().posZ - this.posZ;

				this.rotationYaw = (-(float) Math.atan2(x, z) * 180F) / 3.141593F;

				++this.attackCounter;

				if (this.attackCounter == 20) {
					this.playSound("aether_legacy:aemob.zephyr.call", 3F, this.base);
				} else if (this.attackCounter == 40) {
					this.playSound("aether_legacy:aemob.zephyr.call", 3F, this.base);

					EntityTempestSnowball projectile = new EntityTempestSnowball(this.worldObj, this, x, y, z);
			            
					Vec3 lookVector = this.getLook(1.0F);

					projectile.posX = this.posX + lookVector.xCoord * 4D;
					projectile.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
					projectile.posZ = this.posZ + lookVector.zCoord * 4D;

					if (!this.worldObj.isRemote) {
						projectile.setThrowableHeading(x, y, z, 1.2F, 1.0F);
						this.worldObj.spawnEntityInWorld(projectile);
					}

					this.attackCounter = -40;
				}
			} else if (this.attackCounter > 0) {
				this.attackCounter--;
					}           
			
		}
	}
    
    private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_)
	{
		double d4 = (this.waypointX - this.posX) / p_70790_7_;
		double d5 = (this.waypointY - this.posY) / p_70790_7_;
		double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; (double)i < p_70790_7_; ++i)
		{
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
			{
				return false;
			}
		}

		return true;
	}
    
    public void onLivingUpdate() {
    	if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
            this.damageEntity(DamageSource.drown, 1.0f);
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
    
    protected void attackEntity(final Entity entity, final float f) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)entity;
            if (f < 10.0f) {
                final double d = entity.posX - this.posX;
                final double d2 = entity.posZ - this.posZ;
                if (target != null) {
                    if (target.isDead || target.getDistanceToEntity((Entity)this) > 12.0 || target instanceof EntityZephyr || target instanceof EntityTempest) {
                        target = null;
                        this.entityToAttack = null;
                        return;
                    }
                    if (this.attackTime >= this.timeUntilShoot) {
                        this.shootTarget(target);
                    }
                    if (this.attackTime >= this.timeUntilShoot && this.canEntityBeSeen((Entity)target)) {
                        this.attackTime = -10;
                    }
                    if (this.attackTime < this.timeUntilShoot) {
                        ++this.attackTime;
                    }
                }
                this.rotationYaw = (float)(Math.atan2(d2, d) * 180.0 / 3.1415927410125732) - 90.0f;
            }
        }
    }
    
    public void shootTarget(final EntityLivingBase target) {
        if (this.worldObj.difficultySetting.getDifficultyId() == 0) {
            return;
        }
        final double d5 = target.posX - this.posX;
        final double d6 = target.boundingBox.minY + target.height / 2.0f - (this.posY + this.height / 2.0f);
        final double d7 = target.posZ - this.posZ;
        final EntityTempestSnowball snowball = new EntityTempestSnowball(this.worldObj, (EntityLiving)this, d5, d6, d7);
        snowball.posY = this.posY + 1.0;
        this.worldObj.playSoundAtEntity((Entity)this, "aether_legacy:aemob.zephyr.call", 2.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        if (!this.worldObj.isRemote) {
            this.worldObj.spawnEntityInWorld((Entity)snowball);
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
