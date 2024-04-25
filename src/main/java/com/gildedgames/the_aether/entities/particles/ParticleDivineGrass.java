package com.gildedgames.the_aether.entities.particles;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

public class ParticleDivineGrass extends EntityPortalFX {

	public ParticleDivineGrass(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);

		this.particleBlue = 0.25F;
		this.particleRed = 0.15F;
		this.particleGreen = 0.25F;
	}

}
