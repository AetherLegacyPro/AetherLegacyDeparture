package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenClouds extends WorldGenerator {

	private int cloudAmount;

	private int cloudMeta;

	public AetherGenClouds() {

	}

	public void setCloudAmount(int amount) {
		this.cloudAmount = amount;
	}

	public void setCloudMeta(int meta) {
		this.cloudMeta = meta;
	}

	@Override
	public boolean generate(World worldIn, Random randIn, int xIn, int yIn, int zIn) {
		
		yIn += randIn.nextInt(16);

		for (int amount = 0; amount < this.cloudAmount; ++amount) {
			boolean offsetY = randIn.nextBoolean();

			int xOffset = randIn.nextInt(6);
			int yOffset = (offsetY ? randIn.nextInt(6) - 1 : 0);
			int zOffset = randIn.nextInt(6);

			xIn += xOffset;
			yIn += yOffset;
			zIn += zOffset;

			for (int x = xIn; x < xIn + randIn.nextInt(6) + 3; ++x) {
				for (int y = yIn; y < yIn + randIn.nextInt(6) + 2; ++y) {
					for (int z = zIn; z < zIn + randIn.nextInt(6) + 3; ++z) {
						if (worldIn.isAirBlock(x, y, z)) {
							if (Math.abs(x - xIn) + Math.abs(y - yIn) + Math.abs(z - zIn) < 4 + randIn.nextInt(4)) {
								this.setBlockAndNotifyAdequately(worldIn, x, y, z, BlocksAether.aercloud, this.cloudMeta);
							}
							
							}
						}
					}
				}
			}
		   

		return true;
	}
		

}