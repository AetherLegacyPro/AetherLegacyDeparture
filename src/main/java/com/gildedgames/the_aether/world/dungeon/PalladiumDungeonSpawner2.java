package com.gildedgames.the_aether.world.dungeon;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.init.*;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class PalladiumDungeonSpawner2 extends WorldGenAbstractTree
{
    Block leaves;
    int randHeight;
    boolean branches;
    
    public PalladiumDungeonSpawner2(final Block leafID, final int heightWeight, final boolean branchFlag) {
        super(true);
        this.leaves = leafID;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }
    
    public boolean generate(final World world, final Random random, final int x, final int y, final int z) {
        boolean cangen = true;                   	 
       	 	
        	world.setBlock(x - 1, y, z, BlocksAether.genesis_stone);
   	 		world.setBlock(x + 1, y, z, BlocksAether.genesis_stone);
   	 		world.setBlock(x, y, z - 1, BlocksAether.genesis_stone);
   	 		world.setBlock(x, y, z + 1, BlocksAether.genesis_stone);
   	 		world.setBlock(x + 1, y, z - 1, BlocksAether.light_genesis_stone);
   	 		world.setBlock(x - 1, y, z - 1, BlocksAether.genesis_stone);
   	 		world.setBlock(x + 1, y, z + 1, BlocksAether.genesis_stone);
   	 		world.setBlock(x - 1, y, z + 1, BlocksAether.light_genesis_stone);
   	 	
        	world.setBlock(x, y + 1, z, Blocks.mob_spawner);
        		 TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x, y + 1, z);
             
        		 if (tileentitymobspawner != null)
        		 {
        			 tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.crystal_dragon");
        		 }
        	 
        return true;
    }
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
}
