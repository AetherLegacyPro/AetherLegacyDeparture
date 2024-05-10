package com.gildedgames.the_aether.items.dungeon;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElysianOffering extends Item {

	public ItemElysianOffering() {
		super();

		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}
	
	@Override
	public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (world.getBlock(x, y, z) == BlocksAether.elysian_totem 
		&& world.getBlock(x, y - 1, z) == BlocksAether.crystallized_genesis_stone
		
		&& (world.getBlock(x + 1, y - 2, z) == BlocksAether.genesis_stone_2 || world.getBlock(x + 1, y - 2, z) == BlocksAether.genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z) == BlocksAether.genesis_stone_2 || world.getBlock(x - 1, y - 2, z) == BlocksAether.genesis_stone)
		&& (world.getBlock(x, y - 2, z + 1) == BlocksAether.genesis_stone_2 || world.getBlock(x, y - 2, z + 1) == BlocksAether.genesis_stone)
		&& (world.getBlock(x, y - 2, z - 1) == BlocksAether.genesis_stone_2 || world.getBlock(x, y - 2, z - 1) == BlocksAether.genesis_stone)
		&& (world.getBlock(x + 1, y - 2, z + 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x + 1, y - 2, z + 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z - 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x - 1, y - 2, z - 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x + 1, y - 2, z - 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x + 1, y - 2, z - 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z + 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x - 1, y - 2, z + 1) == BlocksAether.light_genesis_stone)

		&& world.getBlock(x + 2, y - 2, z + 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 2, y - 2, z) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 2, y - 2, z - 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z + 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z - 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 1, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 1, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 1, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 1, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block 

		&& world.getBlock(x - 2, y - 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y - 1, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y - 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y - 1, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y + 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y + 1, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y + 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y + 1, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y + 2, z - 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x - 2, y + 2, z + 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x + 2, y + 2, z - 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x + 2, y + 2, z + 2) == BlocksAether.block_of_aceninum)

		{
			world.setBlock(x, y, z, Blocks.air);
			world.setBlock(x, y - 1, z, BlocksAether.genesis_stone_2);
			
			world.setBlock(x + 1, y - 2, z, BlocksAether.carved_stone);
			world.setBlock(x - 1, y - 2, z, BlocksAether.carved_stone);
			world.setBlock(x, y - 2, z + 1, BlocksAether.carved_stone);
			world.setBlock(x, y - 2, z - 1, BlocksAether.carved_stone);
			world.setBlock(x + 1, y - 2, z + 1, BlocksAether.carved_stone);
			world.setBlock(x - 1, y - 2, z - 1, BlocksAether.carved_stone);
			world.setBlock(x + 1, y - 2, z - 1, BlocksAether.carved_stone);
			world.setBlock(x - 1, y - 2, z + 1, BlocksAether.carved_stone);
			
			world.setBlock(x + 2, y - 2, z + 1, Blocks.air);
			world.setBlock(x + 2, y - 2, z, Blocks.air);
			world.setBlock(x + 2, y - 2, z - 1, Blocks.air);
			world.setBlock(x - 2, y - 2, z + 1, Blocks.air);
			world.setBlock(x - 2, y - 2, z, Blocks.air);
			world.setBlock(x - 2, y - 2, z - 1, Blocks.air);
			world.setBlock(x + 1, y - 2, z + 2, Blocks.air);
			world.setBlock(x, y - 2, z + 2, Blocks.air);
			world.setBlock(x - 1, y - 2, z + 2, Blocks.air);
			world.setBlock(x + 1, y - 2, z - 2, Blocks.air);
			world.setBlock(x, y - 2, z - 2, Blocks.air);
			world.setBlock(x - 1, y - 2, z - 2, Blocks.air);
			
			world.setBlock(x - 2, y - 1, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x - 2, y - 1, z + 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y - 1, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y - 1, z + 2, BlocksAether.genesis_stone_2);
			
			world.setBlock(x - 2, y, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x - 2, y, z + 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y, z + 2, BlocksAether.genesis_stone_2);

			world.setBlock(x - 2, y + 1, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x - 2, y + 1, z + 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y + 1, z - 2, BlocksAether.genesis_stone_2);
			world.setBlock(x + 2, y + 1, z + 2, BlocksAether.genesis_stone_2);
			
			world.playSoundEffect(x, y, z, "ambient.cave.cave", 3.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
		
			if (!world.isRemote)
        	{
				EntityGenesisDragon dragon = new EntityGenesisDragon(world);
				dragon.setLocationAndAngles((double)x + 0.5D, (double)y + 20D, (double)z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(dragon);
				dragon.spawnExplosionParticle();
        	}
			
			world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z - 2));
			world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z + 2));
			world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z - 2));
			world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z + 2));
			
			world.setBlock(x - 2, y + 2, z - 2, BlocksAether.hellfire);
			world.setBlock(x - 2, y + 2, z + 2, BlocksAether.hellfire);
			world.setBlock(x + 2, y + 2, z - 2, BlocksAether.hellfire);
			world.setBlock(x + 2, y + 2, z + 2, BlocksAether.hellfire);
			
			world.setBlock(x, y, z, BlocksAether.treasure_chest, 0, 2);
		}
		return true;
    }
}
