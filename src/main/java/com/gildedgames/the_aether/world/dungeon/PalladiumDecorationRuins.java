package com.gildedgames.the_aether.world.dungeon;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.init.*;

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
        	world.setBlock(x, y + 1, z, BlocksAether.elysian_mimic);
        	
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
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
}
