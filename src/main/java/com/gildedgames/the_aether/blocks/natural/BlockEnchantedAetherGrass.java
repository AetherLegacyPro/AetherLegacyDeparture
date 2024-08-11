package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnchantedAetherGrass extends BlockAetherGrass {
	@SideOnly(Side.CLIENT)
	private IIcon blockIconTop;

	public BlockEnchantedAetherGrass() {
		super();

		this.setHardness(0.45F);
		this.setResistance(0.45F);
		this.setStepSound(soundTypeGrass);
		this.setHarvestLevel("shovel", 0);
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.blockIcon = registry.registerIcon("aether_legacy:enchanted_aether_grass_side");
		this.blockIconTop = registry.registerIcon("aether_legacy:enchanted_aether_grass_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.blockIconTop : (side == 0 ? BlocksAether.aether_dirt.getBlockTextureFromSide(side) : this.blockIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side == 1) {
			return this.blockIconTop;
		} else if (side == 0) {
			return BlocksAether.aether_dirt.getBlockTextureFromSide(side);
		}

		return this.blockIcon;
	}
	
	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		int l = 0;

		while (l < 128) {
			int i1 = p_149853_3_;
			int j1 = p_149853_4_ + 1;
			int k1 = p_149853_5_;
			int l1 = 0;

			while (true) {
				if (l1 < l / 16) {
					i1 += p_149853_2_.nextInt(3) - 1;
					j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
					k1 += p_149853_2_.nextInt(3) - 1;

					if (p_149853_1_.getBlock(i1, j1 - 1, k1) == BlocksAether.enchanted_aether_grass && !p_149853_1_.getBlock(i1, j1, k1).isNormalCube()) {
						++l1;
						continue;
					}
				} else if (p_149853_1_.isAirBlock(i1, j1, k1)) {
					if (p_149853_2_.nextInt(8) != 0) {
						if (BlocksAether.enchanted_aether_tallgrass.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.enchanted_aether_tallgrass, 1, 3);
						}
					}
					else if (p_149853_2_.nextInt(7) != 0) {
						if (BlocksAether.enchanted_aether_tulips.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.enchanted_aether_tulips, 1, 3);
						}
					} 
					else if (p_149853_2_.nextInt(6) != 0) {
						if (BlocksAether.enchanted_quickshoot.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.enchanted_quickshoot, 1, 3);
						}
					} 				
				}

				++l;
				break;
			}
		}
	}

}