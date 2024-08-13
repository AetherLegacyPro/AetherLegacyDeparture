package com.gildedgames.the_aether.items.crops;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrangeSeeds extends Item
{
    public ItemOrangeSeeds()
    {
    	maxStackSize = 64;
        this.setCreativeTab(AetherCreativeTabs.food);
    }

    @Override
	public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		
		if ((world.getBlock(x, y, z) == BlocksAether.aether_farmland || world.getBlock(x, y, z) == BlocksAether.enchanted_aether_farmland) && world.getBlock(x, y + 1, z) == Blocks.air) {
			
			world.setBlock(x, y + 1, z, BlocksAether.small_orange_tree);
			world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, Blocks.tallgrass.stepSound.func_150496_b(), (Blocks.tallgrass.stepSound.getVolume() + 1.0f) / 2.0f, Blocks.tallgrass.stepSound.getPitch() * 0.8f);
			--heldItem.stackSize;
	}
		
		return true;
    }
}
