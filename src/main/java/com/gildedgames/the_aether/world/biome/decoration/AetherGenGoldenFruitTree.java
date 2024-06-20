package com.gildedgames.the_aether.world.biome.decoration;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.init.*;

public class AetherGenGoldenFruitTree extends WorldGenAbstractTree
{
    Block leaves;
    Block fruit;
    int fruitChance;
    int randHeight;
    boolean branches;
    
    public AetherGenGoldenFruitTree(final Block leafID, final Block fruitMeta, final int fruitChance, final int heightWeight, final boolean branchFlag) {
        super(true);
        this.leaves = leafID;
        this.fruit = fruitMeta;
        this.fruitChance = fruitChance;
        this.randHeight = heightWeight;
        this.branches = branchFlag;
    }
    
    public boolean generate(final World world, final Random random, final int x, final int y, final int z) {
        boolean cangen = true;
        final int height = random.nextInt(this.randHeight) + (this.branches ? 8 : 4);
        for (int x2 = x - 3; x2 < x + 3; ++x2) {
            for (int y2 = y + 1; y2 < y + (height + 2); ++y2) {
                for (int z2 = z - 3; z2 < z + 3; ++z2) {
                    if (world.getBlock(x2, y2, z2) != Blocks.air) {
                        cangen = false;
                    }
                }
            }
        }
        if (y + (height + 2) > world.getHeight() || !cangen) {
            return false;
        }
        final Block y3 = world.getBlock(x, y - 1, z);
        if (y3 != BlocksAether.aether_grass && y3 != BlocksAether.aether_dirt && y3 != BlocksAether.enchanted_aether_grass) {
            return false;
        }
        world.setBlock(x, y - 1, z, BlocksAether.aether_dirt);
        for (int y4 = y; y4 <= y + height; ++y4) {
            world.setBlock(x, y4, z, BlocksAether.golden_oak_log_wall);
        }
        this.setBlockAirCheck(world, x, y + (height + 1), z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
        this.setBlockAirCheck(world, x, y + (height + 2), z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
        for (int y4 = y + 2; y4 <= y + (height + 1); ++y4) {
            this.setBlockAirCheck(world, x + 1, y4, z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x - 1, y4, z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x, y4, z + 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x, y4, z - 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
        }
        for (int y4 = y + 3; y4 <= y + height; y4 += 2) {
            this.setBlockAirCheck(world, x + 1, y4, z + 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x - 1, y4, z - 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x + 1, y4, z - 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x - 1, y4, z + 1, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x + 2, y4, z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x - 2, y4, z, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x, y4, z + 2, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
            this.setBlockAirCheck(world, x, y4, z - 2, this.leaves, (random.nextInt(this.fruitChance) == 0) ? this.fruit : Blocks.air);
        }
        if (this.branches) {
            int side = random.nextInt(3);
            for (int y5 = y + (random.nextInt(2) + 3); y5 <= y + height - 2; y5 += 1 + random.nextInt(3)) {
                final int chance = Math.max(1, Math.max(1, y * (y5 - y)) / y5);
                final int branchLength = random.nextInt(2) + 1;
                switch (side) {
                    case 0: {
                        for (int x3 = x; x3 <= x + branchLength; ++x3) {
                            world.setBlock(x3, y5, z, BlocksAether.golden_oak_new_log);
                            this.setBlockAirCheck(world, x3, y5 + 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x3, y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x3 + 1, y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x3 - 1, y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        }
                        world.setBlock(x + (branchLength + 1), y5 + 1, z, BlocksAether.golden_oak_new_log);
                        world.setBlock(x + (branchLength + 2), y5 + 2, z, BlocksAether.golden_oak_new_log);
                        world.setBlock(x + (branchLength + 2), y5 + 3, z, BlocksAether.golden_oak_new_log);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 2, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 3, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 4, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 3), y5 + 2, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 3), y5 + 3, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 2, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 3, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 2, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 3, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 1, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 2, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 1, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5 + 2, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + branchLength, y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + branchLength, y5 + 1, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + branchLength, y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + branchLength, y5 + 1, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength - 1), y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength - 1), y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength - 2), y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength - 1), y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + branchLength, y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 1), y5, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + (branchLength + 2), y5 + 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        break;
                    }
                    case 1: {
                        for (int x4 = x; x4 >= x - branchLength; --x4) {
                            world.setBlock(x4, y5, z, BlocksAether.golden_oak_new_log);
                            this.setBlockAirCheck(world, x4, y5 + 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x4, y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x4 + 1, y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x4 - 1, y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        }
                        world.setBlock(x - (branchLength + 1), y5 + 1, z, BlocksAether.golden_oak_new_log);
                        world.setBlock(x - (branchLength + 2), y5 + 2, z, BlocksAether.golden_oak_new_log);
                        world.setBlock(x - (branchLength + 2), y5 + 3, z, BlocksAether.golden_oak_new_log);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 2, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 3, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 4, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 3), y5 + 2, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 3), y5 + 3, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 2, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 3, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 2, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 3, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 1, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 2, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 1, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5 + 2, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - branchLength, y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - branchLength, y5 + 1, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - branchLength, y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - branchLength, y5 + 1, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength - 1), y5, z - 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength - 1), y5, z + 1, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength - 2), y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength - 1), y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - branchLength, y5 - 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 1), y5, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - (branchLength + 2), y5 + 1, z, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        break;
                    }
                    case 2: {
                        for (int z3 = z; z3 <= z + branchLength; ++z3) {
                            world.setBlock(x, y5, z3, BlocksAether.golden_oak_new_log);
                            this.setBlockAirCheck(world, x, y5 + 1, z3, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x, y5 - 1, z3, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x + 1, y5, z3, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x - 1, y5, z3, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        }
                        world.setBlock(x, y5 + 1, z + (branchLength + 1), BlocksAether.golden_oak_new_log);
                        world.setBlock(x, y5 + 2, z + (branchLength + 2), BlocksAether.golden_oak_new_log);
                        world.setBlock(x, y5 + 3, z + (branchLength + 2), BlocksAether.golden_oak_new_log);
                        this.setBlockAirCheck(world, x, y5 + 2, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 3, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 4, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 2, z + (branchLength + 3), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 3, z + (branchLength + 3), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 2, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 3, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 2, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 3, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 1, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 2, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 1, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 2, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5, z + branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 1, z + branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5, z + branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 1, z + branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5, z + (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5, z + (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z + (branchLength - 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z + (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z + branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5, z + (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 1, z + (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        break;
                    }
                    case 3: {
                        for (int z4 = z; z4 >= z - branchLength; --z4) {
                            world.setBlock(x, y5, z4, BlocksAether.golden_oak_new_log);
                            this.setBlockAirCheck(world, x, y5 + 1, z4, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x, y5 - 1, z4, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x + 1, y5, z4, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                            this.setBlockAirCheck(world, x - 1, y5, z4, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        }
                        world.setBlock(x, y5 + 1, z - (branchLength + 1), BlocksAether.golden_oak_new_log);
                        world.setBlock(x, y5 + 2, z - (branchLength + 2), BlocksAether.golden_oak_new_log);
                        world.setBlock(x, y5 + 3, z - (branchLength + 2), BlocksAether.golden_oak_new_log);
                        this.setBlockAirCheck(world, x, y5 + 2, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 3, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 4, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 2, z - (branchLength + 3), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 3, z - (branchLength + 3), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 2, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 3, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 2, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 3, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 1, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 2, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 1, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 2, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5, z - branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5 + 1, z - branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5, z - branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5 + 1, z - branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x - 1, y5, z - (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x + 1, y5, z - (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z - (branchLength - 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z - (branchLength - 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 - 1, z - branchLength, this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5, z - (branchLength + 1), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        this.setBlockAirCheck(world, x, y5 + 1, z - (branchLength + 2), this.leaves, (random.nextInt(chance) == 0) ? this.fruit : Blocks.air);
                        break;
                    }
                }
                if (++side > 3) {
                    side = 0;
                }
            }
        }
        return true;
    }
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            world.setBlock(x, y, z, blockID);
        }
    }
    
    public void setBlockAirCheck(final World world, final int x, final int y, final int z, final Block blockID, final Block leaveID) {
        if (world.getBlock(x, y, z) == Blocks.air) {
            if (leaveID == Blocks.air) {
                world.setBlock(x, y, z, blockID);
            }
            else {
                world.setBlock(x, y, z, leaveID);
            }
        }
    }
}
