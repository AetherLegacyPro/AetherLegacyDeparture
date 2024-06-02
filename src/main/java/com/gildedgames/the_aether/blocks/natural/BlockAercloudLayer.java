package com.gildedgames.the_aether.blocks.natural;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.block.IColoredBlock;
import com.gildedgames.the_aether.items.block.INamedBlock;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAercloudLayer extends Block implements IColoredBlock, INamedBlock
{

	
    public BlockAercloudLayer()
    {
        super(Material.cloth);
        this.setHardness(0.1F);
        this.setStepSound(soundTypeCloth);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setCreativeTab(AetherCreativeTabs.blocks);
        this.setBlockTextureName("aether:aercloud/cold_aercloud");
        this.func_150154_b(0);
    }
    
    protected void func_150154_b(int p_150154_1_)
    {
        int j = p_150154_1_ & 7;
        float f = (float)(2 * (1 + j)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    @SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		p_149666_3_.add(new ItemStack(this, 1, 0));
		p_149666_3_.add(new ItemStack(this, 1, 1));
		p_149666_3_.add(new ItemStack(this, 1, 2));
		p_149666_3_.add(new ItemStack(this, 1, 3));
		p_149666_3_.add(new ItemStack(this, 1, 4));
	}
    
    @Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block soild = world.getBlock(x, y - 1, z);
		return soild == BlocksAether.aether_grass || soild == BlocksAether.aether_dirt || soild == BlocksAether.arctic_grass || soild == BlocksAether.enchanted_aether_grass || soild == BlocksAether.holystone || soild == BlocksAether.aercloud;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soild = world.getBlock(x, y - 1, z);
		return (soild != null && this.canPlaceBlockAt(world, x, y, z));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.fallDistance = 0;
		
		//blue aercloud
		if (world.getBlockMetadata(x, y, z) == 1) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;

				if (player.isSneaking()) {
					if (entity.motionY < 0) {
						entity.motionY *= 0.005D;
					}

					return;
				}

				entity.motionY = 0.25D;
			} else {
				if (entity instanceof EntityArrow)
				{
					if (entity.ticksExisted >= 1200)
					{
						entity.setDead();
					}
				}
				
				entity.motionY = 0.25D;
			}
			
		}
		
		else if (world.getBlockMetadata(x, y, z) == 3) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;

				if (player.isSneaking()) {
					if (entity.motionZ < 0) {
						entity.motionZ *= 0.005D;
					}

					return;
				}

				entity.motionZ = 0.5D;
			} else {
				if (entity instanceof EntityArrow)
				{
					if (entity.ticksExisted >= 1200)
					{
						entity.setDead();
					}
				}
				
				entity.motionZ = 0.5D;
			}
			
		}
		
		else if (world.getBlockMetadata(x, y, z) == 4) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;

				if (player.isSneaking()) {
					if (entity.motionX < 0) {
						entity.motionX *= 0.005D;
					}

					return;
				}

				entity.motionX = 0.5D;
			} else {
				if (entity instanceof EntityArrow)
				{
					if (entity.ticksExisted >= 1200)
					{
						entity.setDead();
					}
				}
				
				entity.motionX = 0.5D;
			}
			
		}
		
			//gold aercloud
		else if (world.getBlockMetadata(x, y, z) == 2) {
				if (entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;

					if (player.isSneaking()) {
						if (entity.motionY < 0) {
							entity.motionY *= 0.005D;
						}

						return;
					}					
							entity.motionY = -0.25D;
				} else {
					if (entity instanceof EntityArrow)
					{
						if (entity.ticksExisted >= 1200)
						{
							entity.setDead();
						}
					}
					
					entity.motionY = -0.25D;
				}

			if (world.isRemote) {
				if (!(entity instanceof net.minecraft.client.particle.EntityFX)) {
					for (int count = 0; count < 50; count++) {
						double xOffset = x + world.rand.nextDouble();
						double yOffset = y + world.rand.nextDouble();
						double zOffset = z + world.rand.nextDouble();

						world.spawnParticle("splash", xOffset, yOffset, zOffset, 0, 0, 0);
					}
				}
			}
		} else if (entity.motionY < 0) {
			entity.motionY *= 0.005D;
			}	
		}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	protected boolean canSilkHarvest() {
        return true;
    }
	

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		if (meta == 1) {
			return 0xCCFFFF;
		} else if (meta == 2) {
			return 0xFFFF80;
		} else if (meta == 3) {
			return 0xbb88f3;
		} else if (meta == 4) {
			return 0xb6ea78;
		}

		return this.getBlockColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		return this.getRenderColor(meta);
	}

	public String getUnlocalizedName(ItemStack stack) {
		return stack.getItemDamage() == 1 ? "blue_aercloud_layer" : stack.getItemDamage() == 2 ? "golden_aercloud_layer" : stack.getItemDamage() == 3 ? "purple_aercloud_layer" : stack.getItemDamage() == 4 ? "green_aercloud_layer" : "cold_aercloud_layer";
	}

	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (stack.getItemDamage() == 1) {
			return 0xCCFFFF;
		} else if (stack.getItemDamage() == 2) {
			return 0xFFFF80;
		} else if (stack.getItemDamage() == 3) {
			return 0xbb88f3;
		} else if (stack.getItemDamage() == 4) {
			return 0xb6ea78;
		}

		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

		if (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_])) {
			return true;
		}

		if (block == this) {
			return false;
		}

		return super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) != 1 ? AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.01D, z + 1.0D) : AxisAlignedBB.getBoundingBox(x, y, z, x, y, z);
	}
	

}
