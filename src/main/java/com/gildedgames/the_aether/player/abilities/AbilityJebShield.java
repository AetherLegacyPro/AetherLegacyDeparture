package com.gildedgames.the_aether.player.abilities;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.entities.projectile.EntityProjectileBase;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.IThrowableEntity;

public class AbilityJebShield implements IAetherAbility {

	private Random rand = new Random();

	private final IPlayerAether player;

	public AbilityJebShield(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return this.player.getAccessoryInventory().wearingAccessory(ItemsAether.jeb_shield);
	}

	@Override
	public void onUpdate() {
		EntityLivingBase playerEntity = player.getEntity();
		if (playerEntity.worldObj.isRemote) {
			return;
		}

		List<?> entities = playerEntity.worldObj.getEntitiesWithinAABBExcludingEntity(playerEntity, playerEntity.boundingBox.expand(3.0D, 3.0D, 3.0D));

		for (int size = 0; size < entities.size(); ++size) {
			Entity projectile = (Entity) entities.get(size);

			if (isProjectile(projectile) && this.getShooter(projectile) != playerEntity) {
				double x, y, z;

				Entity shooter = this.getShooter(projectile);

				if (shooter == null) {
					return;
				}

				x = playerEntity.posX - shooter.posX;
				y = playerEntity.boundingBox.minY - shooter.boundingBox.minY;
				z = playerEntity.posZ - shooter.posZ;

				double difference = -Math.sqrt((x * x) + (y * y) + (z * z));

				x /= difference;
				y /= difference;
				z /= difference;

				projectile.setDead();

				double packX, packY, packZ;
				packX = (-projectile.motionX * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);
				packY = (-projectile.motionY * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);
				packZ = (-projectile.motionZ * 0.15F) + ((this.rand.nextFloat() - 0.5F) * 0.05F);

				((WorldServer) playerEntity.worldObj).func_147487_a("flame", projectile.posX, projectile.posY, projectile.posZ, 12, packX, packY, packZ, 0.625F);

				playerEntity.worldObj.playSoundAtEntity(playerEntity, "note.snare", 1.0F, 1.0F);
				this.player.getAccessoryInventory().damageWornItem(1, ItemsAether.jeb_shield);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entityLiving);

			if (playerAether.getAccessoryInventory().wearingAccessory(ItemsAether.jeb_shield)) {
				float original = event.ammount;

				event.ammount = original / 2;
			}
		}
	}

	public boolean onPlayerAttacked(DamageSource source) {
		return isProjectile(source.getEntity());
	}

	private Entity getShooter(Entity ent) {
		return ent instanceof EntityArrow ? ((EntityArrow) ent).shootingEntity : ent instanceof EntityThrowable ? ((EntityThrowable) ent).getThrower() : ent instanceof EntityProjectileBase ? ((EntityProjectileBase) ent).getThrower() : ent instanceof EntityFireball ? ((EntityFireball) ent).shootingEntity : null;
	}

	public static boolean isProjectile(Entity entity) {
		return entity instanceof IProjectile || entity instanceof IThrowableEntity;
	}

}