package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.ParticleDivineGrass;
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
import cpw.mods.fml.client.FMLClientHandler;
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
		if (side == 1)
		{
			return this.blockIconTop;
		}
		else if (side == 0)
		{
			return BlocksAether.aether_dirt.getBlockTextureFromSide(side);
		}
		else
		{
			Material material = world.getBlock(x, y + 1, z).getMaterial();
			return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.blockIconSnowy;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		
		super.randomDisplayTick(world, x, y, z, rand);
		if (!world.isRemote) {
			return;
		}

		if (net.minecraft.client.Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
			return;
		}

		if (this == BlocksAether.divine_grass) {
			for (int ammount = 0; ammount < 6; ammount++) {
				double d = x + (rand.nextFloat() - 0.5D) * 10;
				double d1 = y + (rand.nextFloat() - 0.5D) * 10;
				double d2 = z + (rand.nextFloat() - 0.5D) * 10;
				double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
				double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
				double d5 = (rand.nextFloat() - 0.5D) * 0.5D;

				ParticleDivineGrass obj = new ParticleDivineGrass(world, d, d1, d2, d3, d4, d5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(obj);
			}
		}
	
	}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
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

					if (p_149853_1_.getBlock(i1, j1 - 1, k1) == BlocksAether.divine_grass && !p_149853_1_.getBlock(i1, j1, k1).isNormalCube()) {
						++l1;
						continue;
					}
				} else if (p_149853_1_.isAirBlock(i1, j1, k1)) {
					if (p_149853_2_.nextInt(8) != 0) {
						if (BlocksAether.divine_aether_tallgrass.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.divine_aether_tallgrass, 1, 3);
						}
					}
					else if (p_149853_2_.nextInt(7) != 0) {
						if (BlocksAether.divine_stalk.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.divine_stalk, 1, 3);
						}
					} 
					else if (p_149853_2_.nextInt(6) != 0) {
						if (BlocksAether.divine_bloom.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.divine_bloom, 1, 3);
						}
					}
					else if (p_149853_2_.nextInt(6) != 1) {
						if (BlocksAether.divine_lily.canBlockStay(p_149853_1_, i1, j1, k1)) {
							p_149853_1_.setBlock(i1, j1, k1, BlocksAether.divine_lily, 1, 3);
						}
					}
				}

				++l;
				break;
			}
		}
	}

}