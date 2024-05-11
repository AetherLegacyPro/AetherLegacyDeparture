package com.gildedgames.the_aether.blocks.dungeon;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.container.BlockSkyrootChest;
import com.gildedgames.the_aether.entities.hostile.EntityMimic;
import com.gildedgames.the_aether.tileentity.TileEntityChestMimic;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockMimicChest extends BlockSkyrootChest {

	public BlockMimicChest() {
		super(13);

		this.setHardness(2.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityChestMimic();
	}

	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
		this.spawnMimic(worldIn, playerIn, x, y, z);

		worldIn.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.chestopen", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

		return true;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		this.spawnMimic(worldIn, player, x, y, z);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(BlocksAether.skyroot_chest);
	}

	private void spawnMimic(World world, EntityPlayer player, int x, int y, int z) {
		if (!world.isRemote) {
			int rand = (int)(1 + Math.random() * 5);
			switch (rand)
	        {
	        case 1:
			EntityMimic mimic = new EntityMimic(world);
			if (!player.capabilities.isCreativeMode) {
				mimic.setAttackTarget(player);
			}
				mimic.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(mimic);
				mimic.setType(0);
			break;
	        case 2:
			EntityMimic mimic2 = new EntityMimic(world);
			if (!player.capabilities.isCreativeMode) {
				mimic2.setAttackTarget(player);
			}
				mimic2.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(mimic2);
				mimic2.setType(1);
			break;
	        case 3:
			EntityMimic mimic3 = new EntityMimic(world);
			if (!player.capabilities.isCreativeMode) {
				mimic3.setAttackTarget(player);
			}
				mimic3.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(mimic3);
				mimic3.setType(2);
			break;
	        case 4:
			EntityMimic mimic4 = new EntityMimic(world);
			if (!player.capabilities.isCreativeMode) {
				mimic4.setAttackTarget(player);
			}
				mimic4.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(mimic4);
				mimic4.setType(3);
			break;
			case 5:
			EntityMimic mimic5 = new EntityMimic(world);
			if (!player.capabilities.isCreativeMode) {
				mimic5.setAttackTarget(player);
			}
				mimic5.setPosition((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D);
				world.spawnEntityInWorld(mimic5);
				mimic5.setType(4);
			break;
	        }
		}

		world.setBlockToAir(x, y, z);
	}

}