package com.gildedgames.the_aether.world.dungeon;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntityElysianChest;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;

import net.minecraft.init.*;
import net.minecraft.item.ItemStack;

public class PalladiumDecorationRuins extends WorldGenAbstractTree
{
    Block leaves;
    int randHeight;
    boolean branches;
    
    public PalladiumDecorationRuins(final Block leafID, final int heightWeight, final boolean branchFlag) {
        super(true);
        this.leaves = leafID;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }
    
    public boolean generate(final World world, final Random random, final int x, final int y, final int z) {
    	boolean cangen = true;
        	world.setBlock(x, y, z, BlocksAether.block_of_aceninum);
        	
        	int rand = (int)(1 + Math.random() * 5);
    		switch (rand)
            {
            case 1:
            	world.setBlock(x, y + 1, z, BlocksAether.elysian_mimic);
            	break;
            case 2:
            	world.setBlock(x, y + 1, z, BlocksAether.elysian_mimic);
            	break;
            case 3:
            	world.setBlock(x, y + 1, z, BlocksAether.elysian_mimic);
            	break;
            case 4:
            	world.setBlock(x, y + 1, z, BlocksAether.elysian_mimic);
            	break;
            case 5:
            	world.setBlock(x, y + 1, z, BlocksAether.elysian_chest);
            	TileEntityElysianChest chest = (TileEntityElysianChest) world.getTileEntity(x, y + 1, z);
            	
            	int start;
        		for (start = 0; start < 4 + random.nextInt(30); start++) {
    				chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getLoot(random));
        		}
        		break;
            }
        	
        	world.setBlock(x, y, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x, y, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y, z, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y, z, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 1, y, z + 1, BlocksAether.genesis_slab);
        	world.setBlock(x + 1, y, z - 1, BlocksAether.genesis_slab);
        	world.setBlock(x - 1, y, z + 1, BlocksAether.genesis_slab);
        	world.setBlock(x - 1, y, z - 1, BlocksAether.genesis_slab);
        	world.setBlock(x, y, z + 2, BlocksAether.genesis_slab);
        	world.setBlock(x, y, z - 2, BlocksAether.genesis_slab);
        	world.setBlock(x + 2, y, z, BlocksAether.genesis_slab);
        	world.setBlock(x - 2, y, z, BlocksAether.genesis_slab);
        	
        	//Walls
        	world.setBlock(x + 2, y, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 1, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 1, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 2, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 2, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 3, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 3, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 5, z + 1, BlocksAether.genesis_wall);
        	world.setBlock(x + 2, y + 5, z - 1, BlocksAether.genesis_wall);
        	world.setBlock(x - 2, y + 5, z + 1, BlocksAether.genesis_wall);
        	world.setBlock(x - 2, y + 5, z - 1, BlocksAether.genesis_wall);
        	
        	world.setBlock(x + 1, y + 5, z + 2, BlocksAether.genesis_wall);
        	world.setBlock(x + 1, y + 5, z - 2, BlocksAether.genesis_wall);
        	world.setBlock(x - 1, y + 5, z + 2, BlocksAether.genesis_wall);
        	world.setBlock(x - 1, y + 5, z - 2, BlocksAether.genesis_wall);
        	
        	world.setBlock(x, y + 5, z + 2, BlocksAether.genesis_wall);
        	world.setBlock(x, y + 5, z - 2, BlocksAether.genesis_wall);
        	world.setBlock(x - 2, y + 5, z, BlocksAether.genesis_wall);
        	world.setBlock(x + 2, y + 5, z, BlocksAether.genesis_wall);
        	
        	//Second Part
        	world.setBlock(x + 2, y + 4, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 4, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 4, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 4, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 5, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 5, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 5, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 5, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 6, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 6, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 6, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 6, z - 2, BlocksAether.crystallized_genesis_stone);
        	      	
        	world.setBlock(x + 2, y + 6, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x + 2, y + 6, z - 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 6, z + 2, BlocksAether.crystallized_genesis_stone);
        	world.setBlock(x - 2, y + 6, z - 2, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x + 2, y + 6, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y + 6, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y + 6, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y + 6, z - 1, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 1, y + 6, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y + 6, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 6, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 6, z - 2, BlocksAether.genesis_stone);
        	
        	world.setBlock(x, y + 6, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x, y + 6, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y + 6, z, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y + 6, z, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 1, y + 7, z, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 7, z, BlocksAether.genesis_stone);
        	world.setBlock(x, y + 7, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x, y + 7, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y + 7, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y + 7, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 7, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 7, z + 1, BlocksAether.genesis_stone);
        	
        	world.setBlock(x, y + 8, z, BlocksAether.light_genesis_stone);

        	
        return true;
    }
    
    public ItemStack getLoot(Random random) {
		int item = random.nextInt(22);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.zarnillys_scales, random.nextInt(2) + 1);
			case 1:
				if (random.nextInt(12) == 1) {
					return new ItemStack(ItemsAether.gravitite_pendant, 1);
				}
				break;
			case 2:
				return new ItemStack(ItemsAether.divineral_nugget, random.nextInt(2) + 1);
			case 3:
				return new ItemStack(ItemsAether.arkenium_nugget, random.nextInt(2) + 22);
			case 4:
				return new ItemStack(ItemsAether.arkenium_nugget, random.nextInt(8) + 23);
			case 5:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(20) + 25);
			case 6: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(BlocksAether.block_of_aceninum, random.nextInt(2) + 1);
				}

				break;
			}
			case 7:
				return new ItemStack(BlocksAether.aerogel, random.nextInt(3) + 1);
			case 8: {
				if (random.nextInt(5) == 0) {
					return new ItemStack(BlocksAether.block_of_auralite, random.nextInt(2) + 1);
				}

				break;
			}
			case 9: {
					return new ItemStack(ItemsAether.continuum_nugget, random.nextInt(12) + 15);
			}
			case 10: {
				return new ItemStack(ItemsAether.gravitite_nugget, random.nextInt(5) + 4);
			}
			case 11: {
				return new ItemStack(ItemsAether.auralite_crystal, random.nextInt(5) + 4);
			}
			case 12: {
				return new ItemStack(ItemsAether.crystal_dragon_scales, random.nextInt(1) + 1);
			}
			case 13: {
				return new ItemStack(ItemsAether.aceninum_shard, 1);
			}
			case 14: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(ItemsAether.auralite_ring, 1);
				}
				break;
			}
			case 15: {
				if (random.nextInt(10) == 1) {
						return new ItemStack(ItemsAether.divineral_pendant, 1);
					}
				break;
			}
			default: {
				return new ItemStack(ItemsAether.zanite_nugget, random.nextInt(16) + 32);
			}
		}
		return new ItemStack(ItemsAether.zanite_nugget, random.nextInt(16) + 32);
	}
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
}
