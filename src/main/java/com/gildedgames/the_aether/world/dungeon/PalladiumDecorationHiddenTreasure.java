package com.gildedgames.the_aether.world.dungeon;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.init.*;

public class PalladiumDecorationHiddenTreasure extends WorldGenAbstractTree
{
    Block leaves;
    int randHeight;
    boolean branches;
    
    public PalladiumDecorationHiddenTreasure(final Block leafID, final int heightWeight, final boolean branchFlag) {
        super(true);
        this.leaves = leafID;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }
    
    public boolean generate(final World world, final Random random, final int x, final int y, final int z) {
    	boolean cangen = true;
        	world.setBlock(x, y + 1, z, BlocksAether.crystallized_genesis_stone);
        	
        	world.setBlock(x, y + 1, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x, y + 1, z - 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y + 1, z, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y + 1, z, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 1, y + 1, z + 1, BlocksAether.light_genesis_stone);
        	world.setBlock(x - 1, y + 1, z - 1, BlocksAether.light_genesis_stone);
        	world.setBlock(x - 1, y + 1, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y + 1, z - 1, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 2, y, z, BlocksAether.genesis_stone);       	
        	world.setBlock(x + 2, y, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y, z - 1, BlocksAether.genesis_stone);        	
        	world.setBlock(x + 2, y, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y, z - 2, BlocksAether.genesis_stone);
        	
        	world.setBlock(x - 2, y, z, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y, z + 1, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y, z - 1, BlocksAether.genesis_stone);        	
        	world.setBlock(x - 2, y, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y, z - 2, BlocksAether.genesis_stone);
        	
        	world.setBlock(x, y, z + 2, BlocksAether.genesis_stone);       	
        	world.setBlock(x - 1, y, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y, z + 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y, z + 2, BlocksAether.genesis_stone);
        	
        	world.setBlock(x, y, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 1, y, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 1, y, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x - 2, y, z - 2, BlocksAether.genesis_stone);
        	world.setBlock(x + 2, y, z - 2, BlocksAether.genesis_stone);
        	
        	world.setBlock(x + 1, y, z, BlocksAether.divineral_pile);
        	world.setBlock(x - 1, y, z, BlocksAether.divineral_pile);
        	world.setBlock(x, y, z - 1, BlocksAether.divineral_pile);
        	world.setBlock(x, y, z + 1, BlocksAether.divineral_pile);        	
        	world.setBlock(x + 1, y, z - 1, BlocksAether.divineral_pile);
        	world.setBlock(x + 1, y, z + 1, BlocksAether.divineral_pile);
        	world.setBlock(x - 1, y, z + 1, BlocksAether.divineral_pile);
        	world.setBlock(x - 1, y, z - 1, BlocksAether.divineral_pile);
        	world.setBlock(x, y, z, BlocksAether.divineral_pile);
        	
        	int random2 = (int)(1 + Math.random() * 10);
    		int random3 = (int)(0 + Math.random() * 8);
    		switch (random2) {
    		case 1: world.setBlock(x + 1, y, z, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x - 1, y, z, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x, y, z - 1, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x, y, z + 1, BlocksAether.zanite_pile, random3, random3);        	
        	world.setBlock(x + 1, y, z - 1, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x + 1, y, z + 1, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x - 1, y, z + 1, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x - 1, y, z - 1, BlocksAether.zanite_pile, random3, random3);
        	world.setBlock(x, y, z, BlocksAether.zanite_pile, random3, random3);
    			break;
    		case 2: world.setBlock(x + 1, y, z, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x - 1, y, z, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x, y, z - 1, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x, y, z + 1, BlocksAether.continuum_pile, random3, random3);        	
			world.setBlock(x + 1, y, z - 1, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x + 1, y, z + 1, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x - 1, y, z + 1, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x - 1, y, z - 1, BlocksAether.continuum_pile, random3, random3);
			world.setBlock(x, y, z, BlocksAether.continuum_pile, random3, random3);
    			break;
    		case 3: world.setBlock(x + 1, y, z, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x - 1, y, z, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x, y, z - 1, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x, y, z + 1, BlocksAether.gravitite_pile, random3, random3);        	
			world.setBlock(x + 1, y, z - 1, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x + 1, y, z + 1, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x - 1, y, z + 1, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x - 1, y, z - 1, BlocksAether.gravitite_pile, random3, random3);
			world.setBlock(x, y, z, BlocksAether.gravitite_pile, random3, random3);
    			break;
    		case 4: world.setBlock(x + 1, y, z, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x - 1, y, z, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x, y, z - 1, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x, y, z + 1, BlocksAether.arkenium_pile, random3, random3);        	
			world.setBlock(x + 1, y, z - 1, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x + 1, y, z + 1, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x - 1, y, z + 1, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x - 1, y, z - 1, BlocksAether.arkenium_pile, random3, random3);
			world.setBlock(x, y, z, BlocksAether.arkenium_pile, random3, random3);
				break;
    		case 5: world.setBlock(x + 1, y, z, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x - 1, y, z, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x, y, z - 1, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x, y, z + 1, BlocksAether.divineral_pile, random3, random3);        	
			world.setBlock(x + 1, y, z - 1, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x + 1, y, z + 1, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x - 1, y, z + 1, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x - 1, y, z - 1, BlocksAether.divineral_pile, random3, random3);
			world.setBlock(x, y, z, BlocksAether.divineral_pile, random3, random3);
    			break;
    		default: world.setBlock(x + 1, y, z, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x - 1, y, z, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x, y, z - 1, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x, y, z + 1, BlocksAether.zanite_pile, random3, random3);        	
			world.setBlock(x + 1, y, z - 1, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x + 1, y, z + 1, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x - 1, y, z + 1, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x - 1, y, z - 1, BlocksAether.zanite_pile, random3, random3);
			world.setBlock(x, y, z, BlocksAether.zanite_pile, random3, random3);
    			break;
    		}
    		
    		world.setBlock(x, y + 2, z, BlocksAether.aceninum_cluster, 1, 1);
        	
        return true;
    }
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
}
