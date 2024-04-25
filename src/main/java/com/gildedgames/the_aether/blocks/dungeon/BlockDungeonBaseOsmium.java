package com.gildedgames.the_aether.blocks.dungeon;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityBattleSentry;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDungeonBaseOsmium extends Block {

	private Block pickBlock;

	private boolean isLit;

	public BlockDungeonBaseOsmium(boolean isLit) {
		this(null, isLit);
	}

	public BlockDungeonBaseOsmium(Block pickBlock, boolean isLit) {
		super(Material.rock);

		if (pickBlock != null) {
			this.pickBlock = pickBlock;
			this.setResistance(6000000.0F);
		}

		this.isLit = isLit;
		this.setStepSound(soundTypeStone);
		this.setHardness(this.pickBlock != null ? -1F : 0.6F);
		this.setCreativeTab(this.pickBlock != null ? null : AetherCreativeTabs.blocks);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);

		if (block != this) {
			return block.getLightValue(world, x, y, z);
		}

		if (this.isLit) {
			return (int) (15.0F * 0.75f);
		}

		return super.getLightValue(world, x, y, z);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		if (this.pickBlock != null) {
			return new ItemStack(this.pickBlock);
		}

		return super.getPickBlock(target, world, x, y, z, player);
	}

	public Block getUnlockedBlock() {
		return this.pickBlock == null ? this : this.pickBlock;
	}
	
	public void onBlockDestroyedByExplosion(final World world, final int x, final int y, final int z, final Explosion explosion) {
		world.setBlockToAir(x, y, z);		
	}
	
	protected boolean canSilkHarvest() {
	    return true;
	 }
		
 	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	  {
	    return null;
	  }

}
