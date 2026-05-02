package com.gildedgames.the_aether.world.dungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class PalladiumDecorationGemstonePiles extends WorldGenAbstractTree {

    Block leaves;
    int randHeight;
    boolean branches;

    public PalladiumDecorationGemstonePiles(final Block leafID, final int heightWeight, final boolean branchFlag) {
        super(true);
        this.leaves = leafID;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }

    public boolean generate(final World world, final Random random, final int x, final int y, final int z) {
        boolean cangen = true;

        int random2 = (int) (1 + Math.random() * 30);
        int random3 = (int) (0 + Math.random() * 8);
        switch (random2) {
            case 5:
            case 9:
            case 10:
                world.setBlock(x, y, z, BlocksAether.arkenium_pile, random3, random3);
                break;
            case 6:
            case 11:
                world.setBlock(x, y, z, BlocksAether.continuum_pile, random3, random3);
                break;
            case 7:
            case 12:
                world.setBlock(x, y, z, BlocksAether.gravitite_pile, random3, random3);
                break;
            case 8:
                world.setBlock(x, y, z, BlocksAether.divineral_pile, random3, random3);
                break;
            case 13:
                world.setBlock(x, y, z, BlocksAether.diamond_aercloud, 1, 1);
                break;
            case 14:
                world.setBlock(x, y, z, BlocksAether.diamond_aercloud, 2, 2);
                break;
            case 4:
            case 3:
            case 2:
            case 1:
            default:
                world.setBlock(x, y, z, BlocksAether.zanite_pile, random3, random3);
                break;
        }

        return true;
    }

    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
}
