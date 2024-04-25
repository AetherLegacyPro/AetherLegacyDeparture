package com.gildedgames.the_aether.blocks.dungeon;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.container.BlockElysianChest;
import com.gildedgames.the_aether.entities.bosses.EntityElysianGuardian;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.tileentity.TileEntityElysianChestMimic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockMimicElysian extends BlockElysianChest {

	public BlockMimicElysian() {
		super(13);
		this.setResistance(100F);
		this.setHardness(8.5F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityElysianChestMimic();
	}

	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
		this.spawnMimic(worldIn, playerIn, x, y, z);

		worldIn.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "ambient.cave.cave", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
		
		if (!worldIn.isRemote) {
			EntityItem entityItem = new EntityItem(worldIn, x, y, z, new ItemStack(BlocksAether.elysian_chest, 1));

			worldIn.spawnEntityInWorld(entityItem);
		}
		
		return true;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		this.spawnMimic(worldIn, player, x, y, z);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(BlocksAether.elysian_chest);
	}
	
	private void explode(World world, EntityPlayer player, int x, int y, int z) {
	    final float f = 3.5f;
	    world.createExplosion(player, x, y, z, f, true);
	}

	private void spawnMimic(World world, EntityPlayer player, int x, int y, int z) {
		if (!world.isRemote) {
			
			this.explode(world, player, x, y, z);
			
			EntityElysianGuardian guardian = new EntityElysianGuardian(world);
			if (!player.capabilities.isCreativeMode) {
				guardian.setAttackTarget(player);
			}
				guardian.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(guardian);

		world.setBlockToAir(x, y, z);
	   }
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.elysian_chest);
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        super.randomDisplayTick(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_, p_149734_5_);

        if (p_149734_5_.nextInt(15) == 0)
        {
            NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.1f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f, new Object[0]);
            NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.4f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f, new Object[0]);
            NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.6f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f, new Object[0]);
            NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.9f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f, new Object[0]);
            NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 1.1f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f, new Object[0]);

        }
    }

}
