package com.gildedgames.the_aether.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IThrowableEntity;

import java.util.List;


public class EntityAercenturionProjectile extends EntityArrow implements IThrowableEntity {

	private int timeInGround;

	private boolean hitGround;
	
	public int ticksInAir;

	public EntityAercenturionProjectile(World worldIn) {
		super(worldIn);
		setSize(2F, 2F);
		this.noClip = true;
	}

	public EntityAercenturionProjectile(World worldIn, EntityLivingBase shooter, float distance) {
		super(worldIn, shooter, distance);
	}

	@Override
	public void onUpdate() {

		if (this.hitGround) {
			++this.timeInGround;

			//if (this.timeInGround % 5 == 0) {
			//	 ParticleHandler.VIBRATION.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);      
			//}
		} else {
			//for (int j = 0; j < 2; ++j) {
			//	 ParticleHandler.VIBRATION.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);      
			//}
		}
		
		if (this.ticksInAir > 200) {
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

		if (movingobjectposition != null && movingobjectposition.entityHit instanceof EntityPlayer entityplayer)
		{

			if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
			{
				movingobjectposition = null;
			}
		}

		super.onUpdate();
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
