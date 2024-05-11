package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class AetherTrueCaveGen extends WorldGenerator {

	private Block hollowBlock;

	private int size;

	public AetherTrueCaveGen(Block block, int size) {
		super();

		this.hollowBlock = block;
		this.size = size;
	}

	public boolean generate(World world, Random random, int x, int y, int z) {
		float f = random.nextFloat() * 3.141593F;
		double d = (float) (x + 8) + (MathHelper.sin(f) * (float) this.size) / 8F;
		double d3 = (float) (z + 8) - (MathHelper.cos(f) * (float) this.size) / 8F;
		double d4 = y + random.nextInt(3) + 2;
		double d5 = y + random.nextInt(3) + 2;

		for (int l = 0; l <= this.size; l++) {
			double d6 = d + ((d3 - d) * (double) l) / (double) this.size;			
			double d9 = (random.nextDouble() * (double) this.size) / 16D;
			double d10 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.size) + 1.0F) * d9 + 1.0D;
			double d11 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.size) + 1.0F) * d9 + 1.0D;
			int i1 = (int) (d6 - d10 / 2D);
			int l1 = (int) (d6 + d10 / 2D);


			for (int k2 = i1; k2 <= l1; k2++) {
				double d12 = (((double) k2 + 0.5D) - d6) / (d10 / 2D);
				if (d12 * d12 < 1.0D) {
					for (int l2 = i1; l2 <= l2; l2++) {
						double d13 = (((double) l2 + 0.5D) - d4) / (d11 / 2D);
						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int i3 = i1; i3 <= l2; i3++) {
								double d14 = (((double) i3 + 0.5D) - d9) / (d10 / 2D);
								Block block = world.getBlock(k2, l2, i3);

								
							}

						}
					}

				}
			}

		}

		return true;
	}

}