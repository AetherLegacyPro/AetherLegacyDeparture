package com.gildedgames.the_aether.world.dungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

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
		
		int rand = (int)(1 + Math.random() * 13);
		switch (rand)
        {
		case 1:
		world.setBlock(x + 2, y, z, BlocksAether.reinforced_arkenium_block);
		break;
		case 2:
		world.setBlock(x + 2, y, z + 1, BlocksAether.reinforced_arkenium_block);
		break;
		case 3:
		world.setBlock(x + 2, y, z - 1, BlocksAether.reinforced_arkenium_block);
		break;
		case 4:
		world.setBlock(x - 2, y, z, BlocksAether.reinforced_arkenium_block);
		break;
		case 5:
		world.setBlock(x - 2, y, z + 1, BlocksAether.reinforced_arkenium_block);
		break;
		case 6:
		world.setBlock(x - 2, y, z - 1, BlocksAether.reinforced_arkenium_block);
		break;
		case 7:		
		world.setBlock(x, y, z + 2, BlocksAether.reinforced_arkenium_block);
		break;
		case 8:
		world.setBlock(x + 1, y, z + 2, BlocksAether.reinforced_arkenium_block);
		break;
		case 10:
		world.setBlock(x - 1, y, z + 2, BlocksAether.reinforced_arkenium_block);
		break;
		case 11:		
		world.setBlock(x, y, z - 2, BlocksAether.reinforced_arkenium_block);
		break;
		case 12:
		world.setBlock(x + 1, y, z - 2, BlocksAether.reinforced_arkenium_block);
		break;
		case 13:
		world.setBlock(x - 1, y, z - 2, BlocksAether.reinforced_arkenium_block);
		break;
        }
		
		world.setBlock(x - 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
		
		int rand2 = (int)(1 + Math.random() * 8);
		if (rand2 == 2) {
		world.setBlock(x - 2, y + 4, z - 2, BlocksAether.block_of_aceninum);
		}
		
		world.setBlock(x + 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
		
		int rand3 = (int)(1 + Math.random() * 8);
		if (rand3 == 3) {
		world.setBlock(x + 2, y + 4, z - 2, BlocksAether.block_of_aceninum);
		}
		
		world.setBlock(x - 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x - 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
		
		int rand4 = (int)(1 + Math.random() * 8);
		if (rand4 == 4) {
		world.setBlock(x - 2, y + 4, z + 2, BlocksAether.block_of_aceninum);
		}
		
		world.setBlock(x + 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
		world.setBlock(x + 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
		
		int rand5 = (int)(1 + Math.random() * 8);
		if (rand5 == 5) {
		world.setBlock(x + 2, y + 4, z + 2, BlocksAether.block_of_aceninum);
		}
		
		

		return true;
	}
	
	public static ItemStack getPalladiumLoot(Random random) {
		int item = random.nextInt(25);

		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.divineral_nugget, random.nextInt(7) + 4);
			case 1:
				return new ItemStack(ItemsAether.overworld_slayer);
			case 2: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.divineral_ingot, random.nextInt(1) + 1);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.arkenium_ingot, random.nextInt(5) + 2);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.continuum_gemstone, random.nextInt(2) + 1);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(20) + 18);
				break;
			}
			case 3:
				return new ItemStack(ItemsAether.nether_slayer);
			case 4:
				return new ItemStack(ItemsAether.elysian_helmet);
			case 5:
				return new ItemStack(ItemsAether.power_shard);
			case 6: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_helmet);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_leggings);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_chestplate);
				break;
			}
			case 7: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_boots);
				return new ItemStack(ItemsAether.elysian_gloves);
			}
			case 8: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_leggings);
				return new ItemStack(ItemsAether.elysian_gloves);
			}
			case 9:
				return new ItemStack(ItemsAether.elysian_leggings);
			case 10:
				return new ItemStack(ItemsAether.enchanted_divineral, random.nextInt(5) + 2);
			case 11:
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_chestplate);
			case 12:
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_boots);
				return new ItemStack(ItemsAether.elysian_gloves);
			case 13:
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_ring);
			case 14:
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.elysian_apple);
			case 15:
				return new ItemStack(ItemsAether.notched_pickaxe);
			case 16:
				return new ItemStack(ItemsAether.notched_core);
			case 17:
				return new ItemStack(ItemsAether.notched_core);
			case 18:
				return new ItemStack(ItemsAether.ender_slayer);
			case 19:
				return new ItemStack(ItemsAether.dragon_bane);
		}
		return new ItemStack(ItemsAether.divineral_nugget, random.nextInt(7) + 4);
	}

}
