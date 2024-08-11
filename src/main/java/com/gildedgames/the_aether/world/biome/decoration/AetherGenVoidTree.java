package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class AetherGenVoidTree extends WorldGenAbstractTree {

	public AetherGenVoidTree(boolean p_i45448_1_) {
		super(p_i45448_1_);
	}
	
	@Override
	public boolean generate(World world, Random random, int j, int k, int l) {
		boolean cangen = true;

		if (k - 11 <= world.getHeight() && k <= 30.0D) {	
			
			for (int x = 0; x < 1; ++x) {
				world.setBlock(x + j, k, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 1, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 2, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 3, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 4, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 5, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 6, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 7, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 8, l, BlocksAether.void_log);
				world.setBlock(x + j, k - 9, l, BlocksAether.void_log);
			}

			world.setBlock(j, k - 10, l, setRandomBlock(world, random));

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j, k - 5, l + z, BlocksAether.void_log);
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 5, l, BlocksAether.void_log);
			}

			for (int z = -2; z < 3; ++z) {
				if (z != 0 || z != 1)
					world.setBlock(j, k - 5, l + z, setRandomBlock(world, random));
			}

			for (int x = -2; x < 3; ++x) {
				if (x != 0 || x != 1)
					world.setBlock(j + x, k - 5, l, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 5, l - 2, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 5, l + 2, setRandomBlock(world, random));
			}

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j - 2, k - 5, l + z, setRandomBlock(world, random));
			}

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j + 2, k - 5, l + z, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				for (int z = 1; z > -2; --z) {
					if (x != 0 || z != 0) {
						world.setBlock(j + x, k - 5, l + z, setRandomBlock(world, random));
					}
				}
			}

			world.setBlock(j + 1, k - 5, l + 1, setRandomBlock(world, random));
			world.setBlock(j - 1, k - 5, l - 1, setRandomBlock(world, random));

			for (int z = -2; z < 3; ++z) {
				if (z != 0 || z != 1)
					world.setBlock(j, k - 6, l + z, setRandomBlock(world, random));
			}

			for (int x = -2; x < 3; ++x) {
				if (x != 0 || x != 1)
					world.setBlock(j + x, k - 6, l, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				for (int z = 1; z > -2; --z) {
					if (x != 0 || z != 0) {
						world.setBlock(j + x, k - 6, l + z, setRandomBlock(world, random));
					}
				}
			}

			world.setBlock(j + 1, k - 6, l + 1, setRandomBlock(world, random));
			world.setBlock(j - 1, k - 6, l - 1, setRandomBlock(world, random));

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j, k - 6, l + z, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 6, l, setRandomBlock(world, random));
			}

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j, k - 7, l + z, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 7, l, setRandomBlock(world, random));
			}

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j, k - 8, l + z, BlocksAether.void_log);
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 8, l, BlocksAether.void_log);
			}

			for (int z = -2; z < 3; ++z) {
				if (z != 0 || z != 1)
					world.setBlock(j, k - 8, l + z, setRandomBlock(world, random));
			}

			for (int x = -2; x < 3; ++x) {
				if (x != 0 || x != 1)
					world.setBlock(j + x, k - 8, l, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				for (int z = 1; z > -2; --z) {
					if (x != 0 || z != 0) {
						world.setBlock(j + x, k - 8, l + z, setRandomBlock(world, random));
					}
				}
			}

			world.setBlock(j + 1, k - 8, l + 1, setRandomBlock(world, random));
			world.setBlock(j - 1, k - 8, l - 1, setRandomBlock(world, random));

			for (int z = -1; z < 2; ++z) {
				if (z != 0)
					world.setBlock(j, k - 9, l + z, setRandomBlock(world, random));
			}

			for (int x = -1; x < 2; ++x) {
				if (x != 0)
					world.setBlock(j + x, k - 9, l, setRandomBlock(world, random));
			}

			for (int y = k - 3; y <= k - 9; y++) {
				world.setBlock(j, y, l, BlocksAether.void_log);
			}

			return true;
		}

		return true;
	}

	protected Block setRandomBlock(World world, Random random) {
		int nextInt = random.nextInt(3);

		if (nextInt == 0) {
			return BlocksAether.void_fruit_leaves;
		}

		return BlocksAether.void_leaves;
	}

}
