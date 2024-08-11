package com.gildedgames.the_aether.items.util;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.gildedgames.the_aether.blocks.BlocksAether;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class ItemSwettyBall extends Item {
	
	public ItemSwettyBall(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}

	@Override
	public boolean onItemUse(ItemStack stackIn, EntityPlayer playerIn, World worldIn, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem();

		if (worldIn.getBlock(x, y, z) == BlocksAether.aether_dirt) {
			worldIn.setBlock(x, y, z, BlocksAether.aether_grass);
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
		} else if (worldIn.getBlock(x, y, z) == Blocks.dirt) {
			worldIn.setBlock(x, y, z, Blocks.grass);
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
		} else if (worldIn.getBlock(x, y, z) == BlocksAether.aether_grass) {
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
                if (!worldIn.isRemote)
                {
                	worldIn.playAuxSFX(2005, x, y, z, 0);
                }
                return true;
		} else if (worldIn.getBlock(x, y, z) == BlocksAether.divine_grass) {
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
		} else if (worldIn.getBlock(x, y, z) == BlocksAether.arctic_grass) {
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
	  } else if (worldIn.getBlock(x, y, z) == BlocksAether.enchanted_aether_grass) {
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
	  } else if (worldIn.getBlock(x, y, z) == BlocksAether.enchanted_aether_grass) {
			applyBonemeal(stackIn, worldIn, x, y, z, playerIn);
            if (!worldIn.isRemote)
            {
            	worldIn.playAuxSFX(2005, x, y, z, 0);
            }
            return true;
	  } 

		if (!playerIn.capabilities.isCreativeMode) {
			--heldItem.stackSize;
		}

		return false;
	}
	
	public static boolean func_150919_a(ItemStack p_150919_0_, World p_150919_1_, int p_150919_2_, int p_150919_3_, int p_150919_4_)
    {
        if (p_150919_1_ instanceof WorldServer)
            return applyBonemeal(p_150919_0_, p_150919_1_, p_150919_2_, p_150919_3_, p_150919_4_, FakePlayerFactory.getMinecraft((WorldServer)p_150919_1_));
        return false;
    }

    public static boolean applyBonemeal(ItemStack p_150919_0_, World p_150919_1_, int p_150919_2_, int p_150919_3_, int p_150919_4_, EntityPlayer player)
    {
        Block block = p_150919_1_.getBlock(p_150919_2_, p_150919_3_, p_150919_4_);

        BonemealEvent event = new BonemealEvent(player, p_150919_1_, block, p_150919_2_, p_150919_3_, p_150919_4_);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Result.ALLOW)
        {
            if (!p_150919_1_.isRemote)
            {
                p_150919_0_.stackSize--;
            }
            return true;
        }

        if (block instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)block;

            if (igrowable.func_149851_a(p_150919_1_, p_150919_2_, p_150919_3_, p_150919_4_, p_150919_1_.isRemote))
            {
                if (!p_150919_1_.isRemote)
                {
                    if (igrowable.func_149852_a(p_150919_1_, p_150919_1_.rand, p_150919_2_, p_150919_3_, p_150919_4_))
                    {
                        igrowable.func_149853_b(p_150919_1_, p_150919_1_.rand, p_150919_2_, p_150919_3_, p_150919_4_);
                    }

                    --p_150919_0_.stackSize;
                }

                return true;
            }
        }

        return false;
    }

}