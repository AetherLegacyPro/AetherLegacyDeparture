package com.gildedgames.the_aether.world.biome.decoration.overhaul;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.registry.ExternalContentNovaCraft;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherIIDungeonGen extends WorldGenerator {
	//Credit Goes to Roadhog360
    
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		final float size = (random.nextInt(3) + 4) + random.nextFloat(); //6
	    final float DISTANCE_COBBLESTONE_SQ = (size * size);
	    final float DISTANCE_GRIMSTONE_SQ = ((size - 1) * (size - 1));
	    final float DISTANCE_AMETHYST_SQ = ((size - 2) * (size - 2));
	    final float DISTANCE_INNER_SQ = ((size - 3) * (size - 3));
	    final int sizeInt = MathHelper.floor_float(size);
	    
	    if(!canGeodeGenerateHere(world, x, y, z, sizeInt)) {
	    	return false;
	    }
	    
		int holeX = -1;
		int holeY = -1;
		int holeZ = -1;
		float radius = -1;
		int holeSize = random.nextFloat() < .95F ? random.nextInt(3) + 5 : -1;
		float holeTheta = 0;
		float holePhi = 0;
		int attempts = 1;
		if(holeSize != -1) {
			holeTheta = (float) (Math.acos(1 - 2 * random.nextFloat()) - Math.PI/2);
			holePhi = (float) (random.nextFloat() * Math.PI * 2);
			radius = size - (random.nextFloat() * 2 + 0.5F) + (random.nextFloat() * 0);
			holeX = Math.round(radius * MathHelper.cos(holePhi) * MathHelper.cos(holeTheta));
			holeY = Math.round(radius * MathHelper.sin(holeTheta));
			holeZ = Math.round(radius * MathHelper.sin(holePhi) * MathHelper.cos(holeTheta));
			attempts++;
		}
		
        for (int i = -sizeInt; i <= sizeInt; i++) {
            for (int j = -sizeInt; j <= sizeInt; j++) {
                for (int k = -sizeInt; k <= sizeInt; k++) {
                    int distSq = i * i + j * j + k * k;
                    Block block = world.getBlock(x + i, y + j, z + k);
                    if(block.getBlockHardness(world, x + i, y + j, z + k) == -1) continue;

                    if(holeSize > -1) {
                    	double deltaX = Math.abs(i - holeX);
                    	double deltaY = Math.abs(j - holeY);
                    	double deltaZ = Math.abs(k - holeZ);
                    	
                    	if(deltaX + deltaY + deltaZ < holeSize && distSq <= DISTANCE_COBBLESTONE_SQ) {
                            world.setBlockToAir(x + i, y + j, z + k);
                            continue;
                    	}
                    }
                    
                    if (distSq <= DISTANCE_INNER_SQ) {
                        world.setBlockToAir(x + i, y + j, z + k);
                    } else if (distSq <= DISTANCE_COBBLESTONE_SQ && distSq > DISTANCE_GRIMSTONE_SQ) {
                        world.setBlock(x + i, y + j, z + k, BlocksAether.holystone, 0, 2);
                	} else if (distSq <= DISTANCE_GRIMSTONE_SQ && distSq > DISTANCE_AMETHYST_SQ) {
                        world.setBlock(x + i, y + j, z + k, BlocksAether.carved_stone, 0, 2);
                	} else if (distSq <= DISTANCE_AMETHYST_SQ) {
                        placeAmethyst(world, random, x + i, y + j, z + k);
                    }
                }
            }
            
            {
            	world.setBlock(x, y - 2, z, ExternalContentNovaCraft.sliderLabyrinthTotem, 1, 2);
            	world.setBlock(x, y - 3, z, ExternalContentNovaCraft.sliderLabyrinthTotem, 0, 2);
            	world.setBlock(x, y - 4, z, BlocksAether.divine_carved_stone, 0, 2);
            }
        }        
            return true;
         
        
	}
	
	private boolean canGeodeGenerateHere(World world, int x, int y, int z, int size) {
		int air = 0;
		if(isAirOrFluid(world, x + size, y + size, z + size)) {
			air++;
		}
		if(isAirOrFluid(world, x + size, y + size, z - size)) { 
			air++;
		}
		if(isAirOrFluid(world, x - size, y + size, z + size)) {
			air++;
		}
		if(isAirOrFluid(world, x - size, y + size, z - size)) {
			air++;
		}
		if(isAirOrFluid(world, x + size, y - size, z + size)) {
			air++;
		}
		if(isAirOrFluid(world, x + size, y - size, z - size)) {
			air++;
		}
		if(isAirOrFluid(world, x - size, y - size, z + size)) {
			air++;
		}
		if(isAirOrFluid(world, x - size, y - size, z - size)) {
			air++;
		}
		return air < 4;
	}
	
	private boolean isAirOrFluid(World world, int x, int y, int z) {
		return world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isLiquid();
	}
	
	private void placeAmethyst(World world, Random random, int x, int y, int z) {
		if(random.nextInt(12) == 0) {
			world.setBlock(x, y, z, BlocksAether.sentry_stone);			
		} else {
			world.setBlock(x, y, z, BlocksAether.divine_carved_stone);
		}
	}
	
}
