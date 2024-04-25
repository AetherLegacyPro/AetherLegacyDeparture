package com.gildedgames.the_aether.blocks.dungeon;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockDungeonTrap2 extends Block {

	private Block pickBlock;

	public BlockDungeonTrap2(Block pickBlock) {
		super(Material.rock);

		this.pickBlock = pickBlock;
		this.setHardness(this.pickBlock != null ? -1F : -1F);
		
		if (pickBlock != null) {
			this.pickBlock = pickBlock;
			this.setResistance(6000000.0F);
		}
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			world.setBlock(x, y, z, this.pickBlock);

			EntityUligo minion = new EntityUligo(world);
			minion.setPosition(x + 0.5D, y + 1D, z + 0.5D);

			if (!world.isRemote) {
				world.spawnEntityInWorld(minion);
			}

			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
		}
				
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (world.getBlockMetadata(x, y, z) == 0) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;

				if (player.isSneaking()) {
					if (entity.motionY < 0) {
						entity.motionY *= 0.005D;
					}

					return;
				}					
					entity.motionY = -4.0D;
			} else {
				if (entity instanceof EntityArrow)
				{
					if (entity.ticksExisted >= 1200)
					{
						entity.setDead();
					}
				}
				
				entity.motionY = -4.0D;
			}

	} else if (entity.motionY < 0) {
		entity.motionY *= 0.005D;
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

}
