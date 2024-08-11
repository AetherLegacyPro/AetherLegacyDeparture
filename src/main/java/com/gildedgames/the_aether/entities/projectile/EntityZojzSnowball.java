package com.gildedgames.the_aether.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IThrowableEntity;

import java.util.List;

public class EntityZojzSnowball extends EntityArrow implements IThrowableEntity {

	private int timeInGround;

	private boolean hitGround;
	
	public int ticksInAir;

	public EntityZojzSnowball(World worldIn) {
		super(worldIn);
		setSize(0.5F, 0.5F);
	}

	public EntityZojzSnowball(World worldIn, EntityLivingBase shooter, float distance) {
		super(worldIn, shooter, distance);
	}

	@Override
	public void onUpdate() {

		if (this.arrowShake == 7) {
			this.hitGround = false;
		}

		if (this.hitGround) {
			++this.timeInGround;

			if (this.timeInGround % 5 == 0) {
				this.worldObj.spawnParticle("enchantmenttable", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		} else {
			for (int j = 0; j < 2; ++j) {
				this.worldObj.spawnParticle("enchantmenttable", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
		
		if (this.ticksInAir > 100) {
			this.setDead();
		} else {
			this.ticksInAir++;
		}

		Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
		vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (movingobjectposition != null)
		{
			vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}

		Entity entity = null;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = 0.0D;
		int i;
		float f1;

		for (i = 0; i < list.size(); ++i)
		{
			Entity entity1 = (Entity)list.get(i);

			if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity))
			{
				f1 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, f1, f1);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

				if (movingobjectposition1 != null)
				{
					double d1 = vec31.distanceTo(movingobjectposition1.hitVec);

					if (d1 < d0 || d0 == 0.0D)
					{
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		if (entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}

		if (movingobjectposition != null && movingobjectposition.entityHit instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;					
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.generic, 6.0F);
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.magic, 2.0F);
			((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
			((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 120, 1));
			 summonLightning();
	        
			if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
			{
				movingobjectposition = null;
			}
		}

		super.onUpdate();
	}
	
	private void summonLightning()
	{
		this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
		this.setDead();
	}

	@Override
	public void setThrower(Entity entity) {
		this.shootingEntity = entity;
	}

	@Override
	public Entity getThrower() {
		return this.shootingEntity;
	}
	
	protected float getGravityVelocity() {
		return 0.0F;
	}

}
