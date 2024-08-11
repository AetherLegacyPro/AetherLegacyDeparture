package com.gildedgames.the_aether.blocks;

import java.util.Random;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockGenesisStone extends Block {

	public BlockGenesisStone() {
		super(Material.rock);

		this.setHardness(6.5F);
		this.setResistance(25.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(null);
		this.setBlockTextureName("aether_legacy:genesis_stone");
		setBlockName("Genesis Stone");
	}
	
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.genesis_stone_2);
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
 		if (entity instanceof EntityPlayer player) {

		}
 		
 		if (entity instanceof EntityPlayer) {
 			int rand = (int)(1 + Math.random() * 750);
 			if (rand == 1 && world.provider.dimensionId == AetherConfig.getAetherDimensionID()) {
 			EntityCrystalDragon dragon = new EntityCrystalDragon(world);
 			dragon.setPosition(x + 0.5D, y + 1D, z + 0.5D);

				if (!world.isRemote) {
					world.spawnEntityInWorld(dragon);		
				}
				
				world.playSoundEffect(x, y, z, "random.door_close", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
 			}
 		}
 	}

}

