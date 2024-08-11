package com.gildedgames.the_aether.entities.projectile;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.entities.util.EntitySaddleMount;

public class EntityBattleSentryHammerProjectile extends EntityProjectileBase {

	public ArrayList<Block> harvestBlockBans = new ArrayList<Block>();

	public EntityBattleSentryHammerProjectile(World worldIn) {
		super(worldIn);
	}

	public EntityBattleSentryHammerProjectile(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.worldObj.spawnParticle("reddust", this.posX, this.posY + 0.2F, this.posZ, 1.0D, 1.0D, 1.0D);

		if (this.ticksInAir > 50) {
			this.setDead();
		} else {
			this.ticksInAir++;
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void onImpact(MovingObjectPosition object) {
		if (object.typeOfHit == MovingObjectType.ENTITY) {
			if (object.entityHit instanceof EntitySaddleMount && ((EntitySaddleMount) object.entityHit).isSaddled()) {

			} else if (object.entityHit != this.getThrower() && !(object.entityHit instanceof IAetherBoss)) {
				object.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 1);
				object.entityHit.addVelocity(this.motionX, 0.2D, this.motionZ);
			}
		}

		for (int j = 0; j < 8; j++) {
			this.worldObj.spawnParticle("explode", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("explode", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("snowshovel", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("snowshovel", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected float getBoundingBoxExpansion() {
		return 3F;
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

}