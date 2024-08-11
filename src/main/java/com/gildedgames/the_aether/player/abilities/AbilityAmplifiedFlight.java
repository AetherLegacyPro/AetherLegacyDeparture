package com.gildedgames.the_aether.player.abilities;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbilityAmplified;
import com.gildedgames.the_aether.entities.projectile.EntityProjectileBase;

import cpw.mods.fml.common.registry.IThrowableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;

public class AbilityAmplifiedFlight implements IAetherAbilityAmplified {

	private int flightCount2;

	private int maxFlightCount2 = 104;

	private double flightMod2 = 1.5D;

	private double maxFlightMod2 = 25.0D;
	
	private Random rand = new Random();

	private final IPlayerAether playerr;

	public AbilityAmplifiedFlight(IPlayerAether playerr) {
		this.playerr = playerr;
	}

	@Override
	public boolean shouldExecute() {
		return this.playerr.getAccessoryInventory().isWearingAmplifiedValkyrieSet();
	}

	@Override
	public void onUpdate() {
		if (this.playerr.isJumping()) {
			if (this.flightMod2 >= this.maxFlightMod2) {
				this.flightMod2 = this.maxFlightMod2;
			}

			if (this.flightCount2 > 2) {
				if (this.flightCount2 < this.maxFlightCount2) {
					this.flightMod2 += 0.25D;
					this.playerr.getEntity().motionY = 0.035D * this.flightMod2;
					this.flightCount2++;
				}
			} else {
				this.flightCount2++;
			}

		} else {
			this.flightMod2 = 1.0D;
		}

		if (this.playerr.getEntity().onGround) {
			this.flightCount2 = 0;
			this.flightMod2 = 2.0D;
		}
	


		if (this.playerr.getEntity().worldObj.isRemote) {
			return;
		}

		List<?> entities = this.playerr.getEntity().worldObj.getEntitiesWithinAABBExcludingEntity(this.playerr.getEntity(), this.playerr.getEntity().boundingBox.expand(3.0D, 3.0D, 3.0D));

		for (int size = 0; size < entities.size(); ++size) {
			Entity projectile = (Entity) entities.get(size);

			if (isProjectile(projectile) && this.getShooter(projectile) != this.playerr.getEntity()) {
				double x, y, z;

				Entity shooter = this.getShooter(projectile);

				if (shooter == null)
				{
					return;
				}

				x = this.playerr.getEntity().posX - shooter.posX;
				y = this.playerr.getEntity().boundingBox.minY - shooter.boundingBox.minY;
				z = this.playerr.getEntity().posZ - shooter.posZ;

				double difference = -Math.sqrt((x * x) + (y * y) + (z * z));

				x /= difference;
				y /= difference;
				z /= difference;

				projectile.setDead();

				double packX, packY, packZ;
				packX = (-projectile.motionX * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);
				packY = (-projectile.motionY * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);
				packZ = (-projectile.motionZ * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);		
			}
		}
	}

	
	public boolean onPlayerAttacked(DamageSource source) {
		if (isProjectile(source.getEntity())) {
			return true;
		}

		return false;
	}

	private Entity getShooter(Entity ent) {
		return ent instanceof EntityArrow ? ((EntityArrow) ent).shootingEntity : ent instanceof EntityThrowable ? ((EntityThrowable) ent).getThrower() : ent instanceof EntityProjectileBase ? ((EntityProjectileBase) ent).getThrower() : ent instanceof EntityFireball ? ((EntityFireball) ent).shootingEntity : null;
	}

	public static boolean isProjectile(Entity entity) {
		return entity instanceof IProjectile || entity instanceof IThrowableEntity;
	}

}


