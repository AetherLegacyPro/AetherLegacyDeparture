package com.gildedgames.the_aether.blocks;

import java.util.Random;

import com.gildedgames.the_aether.entities.hostile.EntityCyro;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockCrackedOblitusStone extends Block {

	
	public BlockCrackedOblitusStone() {
		super(Material.rock);

		this.setHardness(1F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:cracked_oblitus_stone");
		setBlockName("Cyro Stone");
	}
	
	public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
    {
        if (!p_149664_1_.isRemote && this == BlocksAether.cracked_oblitus_stone)
        {
            EntityCyro entitycyro = new EntityCyro(p_149664_1_);
            entitycyro.setLocationAndAngles((double)p_149664_2_ + 0.5D, p_149664_3_, (double)p_149664_4_ + 0.5D, 0.0F, 0.0F);
            p_149664_1_.spawnEntityInWorld(entitycyro);
            entitycyro.spawnExplosionParticle();
        }

        super.onBlockDestroyedByPlayer(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
    }
	
	public void onEntityWalking(World world, int x, int y, int z, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			if (this == BlocksAether.cracked_oblitus_stone) {
				EntityPlayer player = (EntityPlayer) entityIn;				
				
				}
			
			world.playSoundEffect(x, y, z, "random.door_close", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);

				entityIn.motionY = 1.5D;
				
			world.setBlock(x, y + 1, z, BlocksAether.coldfire);
			
			}
		}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.cracked_oblitus_stone_2);
	}

}
