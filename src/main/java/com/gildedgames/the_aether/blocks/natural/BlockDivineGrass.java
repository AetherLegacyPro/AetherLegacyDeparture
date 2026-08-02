package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.util.DoubleDropHelper;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDivineGrass extends Block implements IGrowable {

	@SideOnly(Side.CLIENT)
	private IIcon blockIconTop;

	@SideOnly(Side.CLIENT)
	private IIcon blockIconSnowy;

	public BlockDivineGrass() {
		super(Material.grass);
		this.setHardness(0.45F);
		this.setResistance(0.45F);
		this.setStepSound(soundTypeGrass);
		this.setHarvestLevel("shovel", 0);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.aether_dirt);
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		DoubleDropHelper.dropBlock(player, x, y, z, this, meta);
	}

	@Override
	public int damageDropped(int meta) {
        return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.blockIcon = registry.registerIcon("aether_legacy:divine_grass_side");
		this.blockIconSnowy = registry.registerIcon("aether_legacy:aether_grass_side_snowy");
		this.blockIconTop = registry.registerIcon("aether_legacy:divine_grass_top");
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
		}
		else if (side == 0) {
			return BlocksAether.aether_dirt.getBlockTextureFromSide(side);
		}
        else {
			Material material = world.getBlock(x, y + 1, z).getMaterial();
			return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.blockIconSnowy;
		}
	}

	@Override
	public boolean func_149851_a(World world, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}

	@Override
	public boolean func_149852_a(World world, Random random, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	@Override
	public void func_149853_b(World world, Random random, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		int l = 0;

		while (l < 128) {
			int i1 = p_149853_3_;
			int j1 = p_149853_4_ + 1;
			int k1 = p_149853_5_;
			int l1 = 0;

			while (true) {
				if (l1 < l / 16) {
					i1 += random.nextInt(3) - 1;
					j1 += (random.nextInt(3) - 1) * random.nextInt(3) / 2;
					k1 += random.nextInt(3) - 1;

					if (world.getBlock(i1, j1 - 1, k1) == BlocksAether.divine_grass && !world.getBlock(i1, j1, k1).isNormalCube()) {
						++l1;
						continue;
					}
				} else if (world.isAirBlock(i1, j1, k1)) {
					if (random.nextInt(8) != 0) {
						if (BlocksAether.divine_aether_tallgrass.canBlockStay(world, i1, j1, k1)) {
							world.setBlock(i1, j1, k1, BlocksAether.divine_aether_tallgrass, 1, 3);
						}
					}
					else if (random.nextInt(7) != 0) {
						if (BlocksAether.divine_stalk.canBlockStay(world, i1, j1, k1)) {
							world.setBlock(i1, j1, k1, BlocksAether.divine_stalk, 1, 3);
						}
					}
					else if (random.nextInt(6) != 0) {
						if (BlocksAether.divine_bloom.canBlockStay(world, i1, j1, k1)) {
							world.setBlock(i1, j1, k1, BlocksAether.divine_bloom, 1, 3);
						}
					}
					else if (random.nextInt(6) != 1) {
						if (BlocksAether.divine_lily.canBlockStay(world, i1, j1, k1)) {
							world.setBlock(i1, j1, k1, BlocksAether.divine_lily, 1, 3);
						}
					}
				}

				++l;
				break;
			}
		}
	}
}
