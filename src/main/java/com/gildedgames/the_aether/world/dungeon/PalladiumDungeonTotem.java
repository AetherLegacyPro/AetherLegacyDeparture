package com.gildedgames.the_aether.world.dungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class PalladiumDungeonTotem extends WorldGenerator {

	public PalladiumDungeonTotem() {

	}

	public boolean generate(World world, Random random, int x, int y, int z) {

		world.setBlock(x, y, z, BlocksAether.genesis_stone);
		world.setBlock(x, y + 1, z, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x, y + 2, z, BlocksAether.elysian_totem, 0, 0);
		world.setBlock(x, y + 3, z, BlocksAether.elysian_totem, 1, 1);
		
		world.setBlock(x + 1, y, z + 1, BlocksAether.light_genesis_stone);
		world.setBlock(x - 1, y, z - 1, BlocksAether.light_genesis_stone);
		world.setBlock(x + 1, y, z - 1, BlocksAether.light_genesis_stone);
		world.setBlock(x - 1, y, z + 1, BlocksAether.light_genesis_stone);		
		world.setBlock(x + 1, y, z, BlocksAether.genesis_stone);
		world.setBlock(x - 1, y, z, BlocksAether.genesis_stone);
		world.setBlock(x, y, z + 1, BlocksAether.genesis_stone);
		world.setBlock(x, y, z - 1, BlocksAether.genesis_stone);
		
		world.setBlock(x + 2, y, z, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x + 2, y, z + 1, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x + 2, y, z - 1, BlocksAether.reinforced_arkenium_block);
		
		world.setBlock(x - 2, y, z, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x - 2, y, z + 1, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x - 2, y, z - 1, BlocksAether.reinforced_arkenium_block);
				
		world.setBlock(x, y, z + 2, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x + 1, y, z + 2, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x - 1, y, z + 2, BlocksAether.reinforced_arkenium_block);
				
		world.setBlock(x, y, z - 2, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x + 1, y, z - 2, BlocksAether.reinforced_arkenium_block);
		world.setBlock(x - 1, y, z - 2, BlocksAether.reinforced_arkenium_block);
		
		world.setBlock(x - 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 4, z - 2, BlocksAether.block_of_aceninum);
		
		world.setBlock(x + 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 4, z - 2, BlocksAether.block_of_aceninum);
		
		world.setBlock(x - 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 4, z + 2, BlocksAether.block_of_aceninum);
		
		world.setBlock(x + 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 4, z + 2, BlocksAether.block_of_aceninum);
		
		
		

		return true;
	}

}
