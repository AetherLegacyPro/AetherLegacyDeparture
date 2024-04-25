package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenPinkClouds extends WorldGenerator {

	private int PinkCloudAmount;

	private int PinkCloudMeta;

	public AetherGenPinkClouds() {

	}

	public void setPinkCloudAmount(int amount) {
		this.PinkCloudAmount = amount;
	}

	public void setPinkCloudMeta(int meta) {
		this.PinkCloudMeta = meta;
	}

	@Override
	public boolean generate(World worldIn, Random randIn, int xIn, int yIn, int zIn) {
		
		yIn += randIn.nextInt(16);

		for (int amount = 0; amount < this.PinkCloudAmount; ++amount) {
			boolean offsetY = randIn.nextBoolean();

			int xOffset = randIn.nextInt(2);
			int yOffset = (offsetY ? randIn.nextInt(3) - 1 : 0);
			int zOffset = randIn.nextInt(2);

			xIn += xOffset;
			yIn += yOffset;
			zIn += zOffset;

			for (int x = xIn; x < xIn + randIn.nextInt(2) + 3; ++x) {
				for (int y = yIn; y < yIn + randIn.nextInt(1) + 2; ++y) {
					for (int z = zIn; z < zIn + randIn.nextInt(2) + 3; ++z) {
						if (worldIn.isAirBlock(x, y, z)) {
							if (Math.abs(x - xIn) + Math.abs(y - yIn) + Math.abs(z - zIn) < 4 + randIn.nextInt(2)) {
								this.setBlockAndNotifyAdequately(worldIn, x, y, z, BlocksAether.pink_aercloud, this.PinkCloudMeta);
							}
						}
					}
				}
			}
		}

		return true;
	}

}