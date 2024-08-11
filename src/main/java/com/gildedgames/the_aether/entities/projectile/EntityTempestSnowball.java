package com.gildedgames.the_aether.entities.projectile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTempestSnowball extends EntityProjectileBase {
	
	public EntityTempestSnowball(World world) {
		super(world);
	}

	public EntityTempestSnowball(World world, EntityLivingBase thrower, double x, double y, double z) {
		super(world, thrower);

		this.setPosition(x, y, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.worldObj.spawnParticle("enchantmenttable", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		
		if (this.ticksInAir > 300) {
			this.setDead();
		} else {
			this.ticksInAir++;
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition object) {
		if (object.entityHit instanceof EntityLivingBase) {			
			if (object.entityHit instanceof EntityPlayer && PlayerAether.get((EntityPlayer) object.entityHit).getAccessoryInventory().wearingArmor(ItemsAether.amplified_sentry_boots)) {
				this.setDead();

				return;
			}
			
			object.entityHit.motionX += this.motionX * 1.1F;
			object.entityHit.motionY += 0.5D;
			object.entityHit.motionZ += this.motionZ * 1.1F;

			if (object.entityHit instanceof EntityPlayerMP) {
				((EntityPlayerMP) object.entityHit).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(object.entityHit));
			}

			this.setDead();
		}
		if (!this.worldObj.isRemote)
		{
			if (object.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				
				((EntityLivingBase)object.entityHit).attackEntityFrom(DamageSource.generic, 3.0F);
				((EntityLivingBase)object.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
				((EntityLivingBase)object.entityHit).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 120, 1));
				
				int rand = (int)(1 + Math.random() * 3);
				if(rand == 1 ) {					
				summonLightning();
				}
				
				if (this.worldObj.getBlock(object.blockX, object.blockY, object.blockZ).getCollisionBoundingBoxFromPool(this.worldObj, object.blockX, object.blockY, object.blockZ) != null)
				{
					 summonLightning();
					 int i = object.blockX;
		             int j = object.blockY;
		             int k = object.blockZ;
					if (this.worldObj.isAirBlock(i, j, k))
	                {
	                    this.worldObj.setBlock(i, j, k, BlocksAether.coldfire);	                
	                	}
					}
				
				}
			}
			else if (object.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				if (this.worldObj.getBlock(object.blockX, object.blockY, object.blockZ).getCollisionBoundingBoxFromPool(this.worldObj, object.blockX, object.blockY, object.blockZ) != null)
				{
					 summonLightning();
					 int i = object.blockX;
		             int j = object.blockY;
		             int k = object.blockZ;
					if (this.worldObj.isAirBlock(i, j, k))
	                {
	                    this.worldObj.setBlock(i, j, k, BlocksAether.coldfire);
	                
				}
			}
		}
	}
	
	private void summonLightning()
	{
		this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
		this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

}
