package com.gildedgames.the_aether.entities.particles;

import net.minecraft.world.World;

public class ParticlePassiveWhirly extends AetherParticle {

	public ParticlePassiveWhirly(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

		this.motionX = xSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
		this.motionY = ySpeedIn + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
		this.motionZ = zSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
		float f = this.rand.nextFloat() * 0.3F + 0.7F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F;
		this.particleMaxAge = (int) (16.0D / ((double) this.rand.nextFloat() * 0.8D + 0.2D)) + 2;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.motionY += 0.004D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.8999999761581421D;
		this.motionY *= 0.8999999761581421D;
		this.motionZ *= 0.8999999761581421D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

}