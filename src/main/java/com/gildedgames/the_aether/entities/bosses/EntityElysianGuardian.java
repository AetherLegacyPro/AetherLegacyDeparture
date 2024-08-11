package com.gildedgames.the_aether.entities.bosses;

import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.projectile.EntityElysianGuardianLaser;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityElysianGuardian extends EntityMob {

	private int attackTime;
	private int timeSinceIgnited;

	public int angerLevel;

	public int timeLeft, chatTime;

	public double safeX, safeY, safeZ;

	public float sinage;

	public double lastMotionY;

	public int teleTimer;

	public EntityElysianGuardian(World world) {
		super(world);
		setSize(0.8F, 1.6F);
		this.teleTimer = this.rand.nextInt(250);
		this.timeLeft = 1200;
		this.safeX = this.posX;
		this.safeY = this.posY;
		this.safeZ = this.posZ;
		this.tasks.addTask(2, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 0.65D, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityValkyrie.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(9.5D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(175.0D);
	}

	public void swingArm() {
		if (!this.isSwingInProgress) {
			this.isSwingInProgress = true;
		}
	}
	
	public boolean canBreatheUnderwater()
    {
        return true;
    }
	
	public int getTotalArmorValue()
    {
        return 15;
    }

	public void teleport(double x, double y, double z, int rad) {
		int a = this.rand.nextInt(rad + 1);
		int b = this.rand.nextInt(rad / 2);
		int c = rad - a;

		a *= ((this.rand.nextInt(2) * 2) - 1);
		b *= ((this.rand.nextInt(2) * 2) - 1);
		c *= ((this.rand.nextInt(2) * 2) - 1);

		x += a;
		y += b;
		z += c;

		int newX = (int) Math.floor(x - 0.5D);
		int newY = (int) Math.floor(y - 0.5D);
		int newZ = (int) Math.floor(z - 0.5D);

		boolean flag = false;

		for (int q = 0; q < 32 && !flag; q++) {
			this.rand.nextInt(rad / 2);
			this.rand.nextInt(rad / 2);
			int j = newY + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
			this.rand.nextInt(rad / 2);
			this.rand.nextInt(rad / 2);

			if (j > 124 || j < 5) {
				continue;
			}
		}

		if (!flag) {
			teleFail();
		} else {
			spawnExplosionParticle();
			setPosition((double) newX + 0.5D, (double) newY + 0.5D, (double) newZ + 0.5D);
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.moveForward = this.moveStrafing = this.rotationPitch = this.rotationYaw = 0.0F;
			this.isJumping = false;
			this.renderYawOffset = this.rand.nextFloat() * 360F;
			spawnExplosionParticle();
			this.teleTimer = this.rand.nextInt(40);
		}
	}

	public void teleFail() {
		this.teleTimer -= (this.rand.nextInt(40) + 40);

		if (this.posY <= 0D) {
			this.teleTimer = 446;
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected void onDeathUpdate()
    {
    	if (this.worldObj.isRemote) {
    		for (int i = 0; i < 3; ++i)
            {
            	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(worldObj, this.posX + (this.rand.nextDouble() - 1.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 0.25D, this.posZ + (this.rand.nextDouble() - 1.5D) * (double)this.width);
            	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(worldObj, this.posX + (this.rand.nextDouble() - 1.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 1.0D, this.posZ + (this.rand.nextDouble() - 1.5D) * (double)this.width);
            	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(worldObj, this.posX + (this.rand.nextDouble() - 1.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 2.0D, this.posZ + (this.rand.nextDouble() - 1.5D) * (double)this.width);
            	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(worldObj, this.posX + (this.rand.nextDouble() - 1.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 3.0D, this.posZ + (this.rand.nextDouble() - 1.5D) * (double)this.width);
            }
           }
    }

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.teleTimer++;
		--this.attackTime;

		if (this.teleTimer >= 450) {
			if (this.getAttackTarget() != null) {
				teleport(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 7);
			} else if (!this.onGround) {
				teleport(this.safeX, this.safeY, this.safeZ, 6);
			}
		} else if (this.teleTimer < 446 && (this.posY <= 0D || this.posY <= (this.safeY - 16D))) {
			this.teleTimer = 446;
		} else if ((this.teleTimer % 5) == 0 && this.getAttackTarget() != null && !canEntityBeSeen(this.getAttackTarget())) {
			this.teleTimer += 100;
		}

		if (this.onGround && this.teleTimer % 10 == 0) {
			this.safeX = this.posX;
			this.safeY = this.posY;
			this.safeZ = this.posZ;
		}

		if (this.chatTime > 0) {
			this.chatTime--;
		}
	}

	@Override
	public void onUpdate() {
		this.lastMotionY = motionY;
		super.onUpdate();

		if (!this.onGround && this.getAttackTarget() != null && this.lastMotionY >= 0.0D && this.motionY < 0.0D && getDistanceToEntity(this.getAttackTarget()) <= 30F && canEntityBeSeen(this.getAttackTarget())) {
			double a = this.getAttackTarget().posX - posX;
			double b = this.getAttackTarget().posZ - posZ;
			double angle = Math.atan2(a, b);
			this.motionX = Math.sin(angle) * 0.25D;
			this.motionZ = Math.cos(angle) * 0.25D;
		}

		if (!this.onGround && !isOnLadder() && Math.abs(this.motionY - this.lastMotionY) > 0.07D && Math.abs(this.motionY - this.lastMotionY) < 0.09D) {
			this.motionY += 0.055F;

			if (this.motionY < -0.275F) {
				this.motionY = -0.275F;
			}
		}

		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setAttackTarget(null);
		}

		if (!this.onGround) {
			this.sinage += 0.75F;
		} else {
			this.sinage += 0.15F;
		}

		if (this.sinage > 3.141593F * 2F) {
			this.sinage -= (3.141593F * 2F);
		}

		if (this.getAttackTarget() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) this.getAttackTarget();

			if (this.getHealth() <= 0) {			

				this.setDead();
			}

			if (player.getHealth() <= 0 && player.isDead) {
				this.setAttackTarget(null);
			}
		}
	}

	
	//protected Entity findPlayerToAttack() {
		//return entityToAttack;
		
	//}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		//compound.setInteger("angerLevel", this.angerLevel);
		compound.setInteger("teleTimer", this.teleTimer);
		compound.setInteger("timeLeft", this.timeLeft);
		compound.setDouble("safePosX", this.safeX);
		compound.setDouble("safePosY", this.safeY);
		compound.setDouble("safePosZ", this.safeZ);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		//this.angerLevel = compound.getInteger("angerLevel");
		this.teleTimer = compound.getInteger("teleTimer");
		this.timeLeft = compound.getInteger("timeLeft");
		this.safeX = compound.getInteger("safePosX");
		this.safeY = compound.getInteger("safePosY");
		this.safeZ = compound.getInteger("safePosZ");
	}

	public boolean attackEntityFrom(DamageSource ds, float i) {
		if (ds.getEntity() instanceof EntityPlayer && worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
			EntityPlayer player = (EntityPlayer) ds.getEntity();

			if (this.getAttackTarget() == null) {				

				this.setAttackTarget(player);
			} 
			 else {
				this.teleTimer -= 10;
			}
		}
		else if (ds == DamageSource.drown || ds == DamageSource.wither || ds == DamageSource.inWall)
        {
            return false;
        }
		else {
			teleport(this.posX, this.posY, this.posZ, 8);
			extinguish();
			return false;
		}

		boolean flag = super.attackEntityFrom(ds, i);

		if (flag && this.getHealth() <= 0) {
			spawnExplosionParticle();
			this.setDead();
		}

		return flag;
	}
	
	protected void attackEntity(final Entity entity1, final float f) {
		if (this.isCollidedHorizontally && !this.hasPath()) {
            final double d = entity1.posX - this.posX;
            final double d2 = entity1.posZ - this.posZ;
            final float f2 = MathHelper.sqrt_double(d * d + d2 * d2);
            this.worldObj.createExplosion(this, this.posX, this.posY + 1.0, this.posZ, 2.0f, this.hasAttacked);
            this.worldObj.createExplosion(this, this.posX, this.posY + 3.0, this.posZ, 2.0f, this.hasAttacked);
            this.worldObj.createExplosion(this, this.posX, this.posY + 4.0, this.posZ, 2.0f, this.hasAttacked);
        }
        else {
            super.attackEntity(entity1, f);
        }
        if (this.getHealth() <= 180.0f && this.getHealth() >= 160.0f) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.65D);
        }
        if (this.getHealth() <= 160.0f && this.getHealth() >= 140.0f) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.8D);
        }
        if (this.getHealth() <= 140.0f && this.getHealth() >= 120.0f) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.85D);
            if (!this.onGround) {
                final double d3 = entity1.posX - this.posX;
                final double d4 = entity1.posZ - this.posZ;
                final float f3 = MathHelper.sqrt_double(d3 * d3 + d4 * d4);
                this.motionX = d3 / f3 * 0.5 * 0.2150000011920929 + this.motionX * 2.98023224E-9;
                this.motionZ = d4 / f3 * 0.5 * 0.2150000011920929 + this.motionZ * 2.98023224E-9;
            }
        }
        if (this.getHealth() <= 120.0f && this.getHealth() >= 80.0f) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(7.9D);
            if (!this.onGround) {
                final double d5 = entity1.posX - this.posX;
                final double d6 = entity1.posZ - this.posZ;
                final float f4 = MathHelper.sqrt_double(d5 * d5 + d6 * d6);
                this.motionX = d5 / f4 * 0.5 * 0.4150000011920929 + this.motionX * 2.98023224E-9;
                this.motionZ = d6 / f4 * 0.5 * 0.4150000011920929 + this.motionZ * 2.98023224E-9;
            }
        }
        if (this.getHealth() <= 65.0f) {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
                if (this.timeSinceIgnited == 0) {
                    this.motionX = 0.0;
                    this.motionY = 0.0;
                    this.motionZ = 0.0;
                    this.worldObj.playSoundAtEntity(this, "random.fizz", 1.3f, 0.3f / (this.rand.nextFloat() * 0.4f + 0.8f));
                    //this.worldObj.playSoundAtEntity((Entity)this, "aether_legacy:projectile.laser.laser_fire", 1.0f, 0.5f);
                }
                ++this.timeSinceIgnited;            
            if (this.timeSinceIgnited >= 75) {
                final byte byte0 = 9;
                for (int j = 0; j < 9; ++j) {
                    final double d7 = entity1.posX - this.posX;
                    final double d8 = entity1.posZ - this.posZ;
                    final EntityElysianGuardianLaser xcEntityMobDivineMissile;
                    final EntityElysianGuardianLaser entityDivineMissile = xcEntityMobDivineMissile = new EntityElysianGuardianLaser(this.worldObj, this);
                    xcEntityMobDivineMissile.posY += 2.099999976158142;
                    final double d9 = entity1.posY + entity1.getEyeHeight() - 0.40000000298023225 - entityDivineMissile.posY;
                    final float f5 = MathHelper.sqrt_double(d7 * d7 + d8 * d8) * 0.0f;
                    this.worldObj.playSoundAtEntity(this, "aether_legacy:projectile.laser.laser_fire", 0.9f, 0.9f / (this.rand.nextFloat() * 0.4f + 0.8f));
                    //this.worldObj.playSoundAtEntity((Entity)this, "random.fizz", 0.9f, 0.9f / (this.rand.nextFloat() * 0.4f + 0.8f));
                    this.worldObj.spawnEntityInWorld(entityDivineMissile);
                    entityDivineMissile.setThrowableHeading(d7, d9 + f5, d8, 0.62f, -10.0f);
                    this.timeSinceIgnited = 0;
                }
            }
            int rand = (int)(1 + Math.random() * 30);
            if (this.getHealth() <= 65.0f && rand == 2) {
            	heal(4);
            }
        }
		
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean flag = false;

		if (this.attackTime <= 0 && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
			this.attackTime = 20;
			this.worldObj.playSoundAtEntity(this, "aether_legacy:projectile.charged_hit", 0.9f, 0.9f / (this.rand.nextFloat() * 0.4f + 0.8f));
			swingArm();
			flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 35);			
		}

		return flag;
	}
	
	public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.getEntity();
            
            entityplayer.triggerAchievement(AchievementsAether.kill_elysian_guardian);
            
        }
            
    }

	@Override
	protected void dropFewItems(boolean var1, int var2) {
		dropItem(ItemsAether.elysian_core, 1);
		dropItem(ItemsAether.aceninum_shard, 2);
	}

	@Override
	public void fall(float distance) {
	}
	
	protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.65F;
    }
	
	protected String getChargedHitSound() {
        return "aether_legacy:projectile.charged_hit";
    }
	
	protected String getLaserSound() {
		return "aether_legacy:projectile.laser.laser_fire";
	}

	@Override
	protected String getHurtSound() {
		return "mob.irongolem.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.irongolem.death";
	}
	
	@Override
    public boolean canDespawn() {
        return false;
    }
}
