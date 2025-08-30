package com.gildedgames.the_aether.world.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.slider.EntityMythicSlider;
import com.gildedgames.the_aether.world.dungeon.util.AetherDungeon;
import com.gildedgames.the_aether.world.dungeon.util.PositionData;

public class MythicBronzeDungeon extends AetherDungeon {
	private boolean needsCorridor;
	private int roomMaximum;
	private int roomCount;

	public MythicBronzeDungeon() {
		needsCorridor = false;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		replaceAir = true;
		replaceSolid = true;

		roomMaximum = random.nextInt(3) + 1;
		roomCount = 0;

		generateBossRoom(world, random, i, j, k);

		return true;
	}

	public boolean generateBossRoom(World world, Random random, int i, int j, int k)
	{
		if (!isBoxSolid(world, new PositionData(i, j - 3, k), new PositionData(16, 18, 16)) || !isBoxSolid(world, new PositionData(i + 20, j, k + 2), new PositionData(12, 12, 12))) {
			return false;													
		}

		RandomTracker randomTracker = new RandomTracker();

		if (randomTracker.testRandom(random,25) != 0)
		{
			if (randomTracker.testRandom(random,50) != 0)
			{
				return false;
			}
		}

		setBlocks(this.lockedBlock(), this.lockedLightBlock(), 35);
		
		addHollowBox(world, random, new PositionData(i - 1, j - 1, k - 1), new PositionData(17, 13, 17));
		addHollowBox(world, random, new PositionData(i, j, k), new PositionData(17, 13, 17));
		addHollowBox(world, random, new PositionData(i, j, k), new PositionData(16, 12, 16));

		addHollowBox(world, random, new PositionData(i + 6, j - 2, k + 6), new PositionData(4, 3, 4));

		EntityMythicSlider slider = new EntityMythicSlider(world);
		slider.setPosition(i + 8, j + 1, k + 8);
		slider.setDungeon(slider.posX - 8, slider.posY - 1, slider.posZ - 8);

		if (!world.isRemote) {
			world.spawnEntityInWorld(slider);
		}

		world.setBlock(i + 7, j - 1, k + 7, BlocksAether.treasure_chest);
        world.setBlock(i + 8, j - 1, k + 8, BlocksAether.treasure_chest);
        world.setBlock(i + 7, j - 1, k + 8, BlocksAether.ancient_enchanter);
        
        world.setBlock(i + 1, j + 10, k + 1, BlocksAether.mythic_carved_stone_wall);
        world.setBlock(i + 6, j + 9, k + 9, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner5 = (TileEntityMobSpawner)world.getTileEntity(i + 6, j + 9, k + 9);
          
         if (tileentitymobspawner5 != null)
         {
          tileentitymobspawner5.func_145881_a().setEntityName("aether_legacy.aercenturion");
         }
        
        int rand = (int)(1 + Math.random() * 7);
		switch (rand)
        {
        case 1:
        	world.setBlock(i + 1, j + 10, k + 1, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 1, j + 9, k + 1, Blocks.mob_spawner);
            TileEntityMobSpawner tileentitymobspawner3 = (TileEntityMobSpawner)world.getTileEntity(i + 1, j + 9, k + 1);
              
             if (tileentitymobspawner3 != null)
             {
              tileentitymobspawner3.func_145881_a().setEntityName("aether_legacy.cyro");
             }
        	break;
        case 2:
        	world.setBlock(i + 1, j + 10, k + 1, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 9, j + 9, k + 9, Blocks.mob_spawner);
            TileEntityMobSpawner tileentitymobspawner4 = (TileEntityMobSpawner)world.getTileEntity(i + 9, j + 9, k + 9);
              
             if (tileentitymobspawner4 != null)
             {
              tileentitymobspawner4.func_145881_a().setEntityName("aether_legacy.aercenturion");
             }
        	break;
        case 3:
        	world.setBlock(i + 9, j + 10, k + 9, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 1, j + 9, k + 1, BlocksAether.aerogel);           
        	break;
        case 4:
        	world.setBlock(i + 9, j + 10, k + 9, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 9, j + 9, k + 9, BlocksAether.aerogel);
			break;
        case 5:
        	world.setBlock(i + 9, j + 10, k + 9, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 9, j + 9, k + 9, BlocksAether.zanite_block);
			break;
        case 6:
        	world.setBlock(i + 9, j + 10, k + 9, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 9, j + 9, k + 9, BlocksAether.zanite_block);
			break;
        case 7:
        	world.setBlock(i + 9, j + 10, k + 9, BlocksAether.mythic_carved_stone_wall);
        	world.setBlock(i + 9, j + 9, k + 9, BlocksAether.gravitite_ore);
			break;
		
        } 
        
		world.setBlock(i, j, k, setRandomBlock(world, random));
		
		generateEmptyRoom(world, random, i, j, k);

		return true;
	}

	public boolean generateEmptyRoom(World world, Random random, int i, int j, int k)
	{
		int x = i;
		int y = j;
		int z = k;

		int rooms = random.nextInt(4);

		switch (rooms)
		{
			case 0:
			{
				//EAST
				x = i + 20;
				y = j;
				z = k + 2;

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 12, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 12, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x - 5, y, z + 3), new PositionData(6, 6, 6), 0);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.locked_mythic_carved_stone);
					}
				}

				break;
			}
			case 1:
			{
				//WEST
				x = i - 16;
				y = j;
				z = k + 2;

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 12, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 12, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 11, y, z + 3), new PositionData(6, 6, 6), 0);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3)   {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.divine_carved_trap);
					}
				}
				
				for (int p = x + 3; p < x + 7; p += 4) {
					for (int q = z + 3; q < z + 9; q += 2)   {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.divine_carved_trap);
					}
				}

				break;
			}
			case 2:
			{
				//SOUTH
				x = i + 2;
				y = j;
				z = k + 20;

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 12, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 12, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 3, y, z - 5), new PositionData(6, 6, 6), 2);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.divine_carved_trap);
					}
				}

				break;
			}
			case 3:
			{
				//NORTH
				x = i + 2;
				y = j;
				z = k - 16;

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 12, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 12, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 3, y, z + 11), new PositionData(6, 6, 6), 2);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.divine_carved_trap);
					}
				}
				break;
			}
		}

		if ((!determineRoomPosition(world, random, new PositionData(x, y, z)) && roomCount == 0))
		{
			return false;
		}

		if (needsCorridor)
		{
			endCorridor(world, random, new PositionData(x, y, z));
		}

		return true;
	}

	public boolean determineRoomPosition(World world, Random random, PositionData pos)
	{
		if (roomCount >= roomMaximum)
		{
			this.needsCorridor = true;
			return true;
		}

		ArrayList<Integer> sides = new ArrayList<>();
		sides.add(1);
		sides.add(2);
		sides.add(3);
		sides.add(4);

		Collections.shuffle(sides);

		if (generateRoomWithSide(world, random, pos, sides.get(0)))
		{
			return true;
		}
		else if (generateRoomWithSide(world, random, pos, sides.get(1)))
		{
			return true;
		}
		else if (generateRoomWithSide(world, random, pos, sides.get(2)))
		{
			return true;
		}
		else if (generateRoomWithSide(world, random, pos, sides.get(3)))
		{
			return true;
		}
		else
		{
			this.needsCorridor = true;
			return false;
		}
	}

	public boolean generateRoomWithSide(World world, Random random, PositionData pos, int switchCase)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int dir = 0;

		switch (switchCase)
		{
			case 1:
			{
				x += 16;
				z += 0;
				dir = 0;
				break;
			}
			case 2:
			{
				x += 0;
				z += 16;
				dir = 1;
				break;
			}
			case 3:
			{
				x -= 16;
				z += 0;
				dir = 2;
				break;
			}
			case 4:
			{
				x += 0;
				z -= 16;
				dir = 3;
				break;
			}
		}

		return generateNextRoom(world, random, new PositionData(x, y, z), dir);
	}

	public boolean generateNextRoom(World world, Random random, PositionData pos, int dir) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 8, 12))) {
			return false;
		}

		setBlocks(this.mainBlock(), this.mainLightBlock(), 20);

		addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 8, 12));

		for (int p = x; p < x + 12; p++) {
			for (int q = y; q < y + 8; q++) {
				for (int r = z; r < z + 12; r++) {
					if (world.getBlock(p, q, r) == this.mainBlock() && random.nextInt(100) == 0) {
						world.setBlock(p, q, r, setRandomBlock(world, random));
						world.setBlock(p, q, r, BlocksAether.divine_carved_trap);
					}
				}
			}
		}

		for (int p = x + 2; p < x + 10; p += 7) {
			for (int q = z + 2; q < z + 10; q += 7) {
				world.setBlock(p, pos.getY(), q, setRandomBlock(world, random));
				world.setBlock(p, pos.getY(), q, BlocksAether.divine_carved_trap);
			}
		}

		addPlaneY(world, random, new PositionData(x + 4, y + 1, z + 4), new PositionData(4, 0, 4));
		int type = random.nextInt(3);
		int p = x + 5 + random.nextInt(2);
		int q = z + 5 + random.nextInt(2);

		switch (type) {
			case 0: {
				world.setBlock(p, y + 2, q, BlocksAether.chest_mimic);
				world.setBlock(p, y + 2, q + 1, BlocksAether.skyroot_chest);
				TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q + 1);
				
				// - -	
				world.setBlock(x + 4, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
									
				world.setBlock(x + 3, y + 1, z + 3, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 4, y + 1, z + 3, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 3, y + 1, z + 4, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 3, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 4, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 3, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
				
				// + -
				world.setBlock(x + 7, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 8, y + 1, z + 3, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 7, y + 1, z + 3, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 8, y + 1, z + 4, BlocksAether.mythic_carved_stone);				
				world.setBlock(x + 8, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 7, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 8, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
				
				// - +
				world.setBlock(x + 4, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 3, y + 1, z + 8, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 3, y + 1, z + 7, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 4, y + 1, z + 8, BlocksAether.mythic_carved_stone);					
				world.setBlock(x + 3, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 3, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 4, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
				
				// + +
				world.setBlock(x + 7, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 8, y + 1, z + 8, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 7, y + 1, z + 8, BlocksAether.mythic_carved_stone);
				world.setBlock(x + 8, y + 1, z + 7, BlocksAether.mythic_carved_stone);					
				world.setBlock(x + 8, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 7, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 8, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 1, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 11, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);					
				world.setBlock(x + 11, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);	
				
				world.setBlock(x + 11, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 11, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);	
				
				world.setBlock(x + 1, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
				world.setBlock(x + 1, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);
				
				world.setBlock(x + 7, y + 5, z + 7, Blocks.mob_spawner);
		          TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 5, z + 7);
		            
		           if (tileentitymobspawner != null)
		           {
		            tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.aercenturion");
		           }
		           
		          world.setBlock(x + 4, y + 5, z + 4, Blocks.mob_spawner);
			        TileEntityMobSpawner tileentitymobspawner2 = (TileEntityMobSpawner)world.getTileEntity(x + 4, y + 5, z + 4);
			            
			       if (tileentitymobspawner2 != null)
			        {
			         tileentitymobspawner2.func_145881_a().setEntityName("aether_legacy.cyro");
			        }
				
				for (p = 0; p < 3 + random.nextInt(10); p++) {
					chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
				}
				break;
			}
			case 1: {
				if (world.getBlock(p, y + 2, q) == Blocks.air) {
					world.setBlock(p, y + 2, q, BlocksAether.skyroot_chest);
					world.setBlock(p, y + 2, q + 1, BlocksAether.skyroot_chest);
					TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q);
					TileEntitySkyrootChest chestt = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q + 1);

					// - -	
					world.setBlock(x + 4, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
										
					world.setBlock(x + 3, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 4, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 1, z + 4, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 4, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 3, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					// + -
					world.setBlock(x + 7, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 7, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 8, y + 1, z + 4, BlocksAether.mythic_carved_stone);				
					world.setBlock(x + 8, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 7, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 8, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					// - +
					world.setBlock(x + 4, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 3, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 1, z + 7, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 4, y + 1, z + 8, BlocksAether.mythic_carved_stone);					
					world.setBlock(x + 3, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 3, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 4, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					
					// + +
					world.setBlock(x + 7, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 7, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 8, y + 1, z + 7, BlocksAether.mythic_carved_stone);					
					world.setBlock(x + 8, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 7, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 8, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 1, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 11, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);					
					world.setBlock(x + 11, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);	
					
					world.setBlock(x + 11, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);	
					
					world.setBlock(x + 1, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 7, y + 5, z + 7, Blocks.mob_spawner);
			          TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 5, z + 7);
			            
			           if (tileentitymobspawner != null)
			           {
			            tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cockatrice");
			           }
			           
			          world.setBlock(x + 4, y + 5, z + 4, Blocks.mob_spawner);
				        TileEntityMobSpawner tileentitymobspawner2 = (TileEntityMobSpawner)world.getTileEntity(x + 4, y + 5, z + 4);
				            
				       if (tileentitymobspawner2 != null)
				        {
				         tileentitymobspawner2.func_145881_a().setEntityName("aether_legacy.cyro");
				        }
					
					for (p = 0; p < 3 + random.nextInt(7); p++) {
						chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
					}
					for (p = 0; p < 3 + random.nextInt(7); p++) {
						chestt.setInventorySlotContents(random.nextInt(chestt.getSizeInventory()), this.getNormalLoot(random));
					}				
				}
				break;
			}
			case 2: {
				if (world.getBlock(p, y + 2, q) == Blocks.air) {
					world.setBlock(p, y + 2, q + 1, BlocksAether.skyroot_chest);
					world.setBlock(p, y + 2, q, BlocksAether.chest_mimic);
					TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q + 1);

					// - -	
					world.setBlock(x + 4, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
										
					world.setBlock(x + 3, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 4, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 1, z + 4, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 4, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 3, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					// + -
					world.setBlock(x + 7, y + 6, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 7, y + 1, z + 3, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 8, y + 1, z + 4, BlocksAether.mythic_carved_stone);				
					world.setBlock(x + 8, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 7, y + 2, z + 3, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 8, y + 2, z + 4, BlocksAether.mythic_carved_stone_wall);
					
					// - +
					world.setBlock(x + 4, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 3, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 3, y + 1, z + 7, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 4, y + 1, z + 8, BlocksAether.mythic_carved_stone);					
					world.setBlock(x + 3, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 3, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 4, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					
					// + +
					world.setBlock(x + 7, y + 6, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 7, y + 1, z + 8, BlocksAether.mythic_carved_stone);
					world.setBlock(x + 8, y + 1, z + 7, BlocksAether.mythic_carved_stone);					
					world.setBlock(x + 8, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 7, y + 2, z + 8, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 8, y + 2, z + 7, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 1, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 11, y + 1, z + 1, BlocksAether.mythic_carved_stone_wall);					
					world.setBlock(x + 11, y + 2, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 3, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 4, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 5, z + 1, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 6, z + 1, BlocksAether.mythic_carved_stone_wall);	
					
					world.setBlock(x + 11, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 11, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);	
					
					world.setBlock(x + 1, y + 1, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 2, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 3, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 4, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 5, z + 11, BlocksAether.mythic_carved_stone_wall);
					world.setBlock(x + 1, y + 6, z + 11, BlocksAether.mythic_carved_stone_wall);
					
					world.setBlock(x + 7, y + 5, z + 7, Blocks.mob_spawner);
			          TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 5, z + 7);
			            
			           if (tileentitymobspawner != null)
			           {
			            tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.aercenturion");
			           }
			           
			          world.setBlock(x + 4, y + 5, z + 4, Blocks.mob_spawner);
				        TileEntityMobSpawner tileentitymobspawner2 = (TileEntityMobSpawner)world.getTileEntity(x + 4, y + 5, z + 4);
				            
				       if (tileentitymobspawner2 != null)
				        {
				         tileentitymobspawner2.func_145881_a().setEntityName("aether_legacy.aercenturion");
				        }
					
					for (p = 0; p < 3 + random.nextInt(8); p++) {
						chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
					}
					
				}
				break;
			}
		}

		setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

		switch (dir) {
			case 0: {
				addSquareTube(world, random, new PositionData(x - 5, y, z + 3), new PositionData(6, 6, 6), 0);
				break;
			}
			case 1: {
				addSquareTube(world, random, new PositionData(x + 3, y, z - 5), new PositionData(6, 6, 6), 2);
				break;
			}
			case 2: {
				addSquareTube(world, random, new PositionData(x + 11, y, z + 3), new PositionData(6, 6, 6), 0);
				break;
			}
			case 3: {
				addSquareTube(world, random, new PositionData(x + 3, y, z + 11), new PositionData(6, 6, 6), 2);
				break;
			}
		}

		roomCount++;

		if(!determineRoomPosition(world, random,  new PositionData(x, y, z)))
		{
			return false;
		}

		return determineRoomPosition(world, random, new PositionData(x, y, z));
	}

	public boolean endCorridor(World world, Random random, PositionData pos)
	{
		ArrayList<Integer> sides = new ArrayList<>();
		sides.add(1);
		sides.add(2);
		sides.add(3);
		sides.add(4);

		Collections.shuffle(sides);

		if (generateEndCorridor(world, random, pos, sides.get(0)))
		{
			return true;
		}
		else if (generateEndCorridor(world, random, pos, sides.get(1)))
		{
			return true;
		}
		else if (generateEndCorridor(world, random, pos, sides.get(2)))
		{
			return true;
		}
		else if (generateEndCorridor(world, random, pos, sides.get(3)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean generateEndCorridor(World world, Random random, PositionData pos, int switchCase)
	{
		if (!this.needsCorridor)
		{
			return false;
		}

		replaceAir = false;

		switch (switchCase)
		{
			case 1:
			{
				//EAST

				boolean tunnelling = true;
				boolean maxLength = false;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				x += 11;
				z += 3;

				if (!isBoxSolid(world, new PositionData(x + 1, y, z), new PositionData(2, 8, 6)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(1, 8, 6)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x + 1, y, z), new PositionData(2, 8, 6), BlocksAether.mythic_carved_stone)
							|| hasBlock(world, new PositionData(x + 1, y, z), new PositionData(2, 8, 6), BlocksAether.locked_mythic_carved_stone))
					{
						tunnelling = false;
					}

					if (x - pos.getX() > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneX(world, random, new PositionData(x, y, z), new PositionData(0, 8, 6));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneX(world, random, new PositionData(x, y + 1, z + 1), new PositionData(0, 6, 4));

					x++;
				}

				if (maxLength)
				{
					return false;
				}

				this.needsCorridor = false;

				return true;
			}
			case 2:
			{
				//WEST

				boolean tunnelling = true;
				boolean maxLength = false;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				x -= 0;
				z += 3;

				if (!isBoxSolid(world, new PositionData(x - 1, y, z), new PositionData(1, 8, 6)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(1, 8, 6)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x - 1, y, z), new PositionData(1, 8, 6), BlocksAether.mythic_carved_stone)
							|| hasBlock(world, new PositionData(x - 1, y, z), new PositionData(1, 8, 6), BlocksAether.locked_mythic_carved_stone))
					{
						tunnelling = false;
					}

					if (pos.getX() - x > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneX(world, random, new PositionData(x, y, z), new PositionData(0, 8, 6));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneX(world, random, new PositionData(x, y + 1, z + 1), new PositionData(0, 6, 4));

					x--;
				}

				if (maxLength)
				{
					return false;
				}

				this.needsCorridor = false;

				return true;
			}
			case 3:
			{
				//SOUTH
				// BUGGED

				boolean tunnelling = true;
				boolean maxLength = false;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				x += 3;
				z += 11;

				if (!isBoxSolid(world, new PositionData(x, y, z + 1), new PositionData(6, 8, 2)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(6, 8, 1)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x, y, z + 1), new PositionData(6, 8, 2), BlocksAether.mythic_carved_stone)
							|| hasBlock(world, new PositionData(x, y, z + 1), new PositionData(6, 8, 2), BlocksAether.locked_mythic_carved_stone))
					{
						tunnelling = false;
					}

					if (z - pos.getZ() > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneZ(world, random, new PositionData(x, y, z), new PositionData(6, 8, 0));
					

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneZ(world, random, new PositionData(x + 1, y + 1, z), new PositionData(4, 6, 0));

					z++;
				}

				if (maxLength)
				{
					return false;
				}

				this.needsCorridor = false;

				return true;
			}
			case 4:
			{
				//NORTH

				boolean tunnelling = true;
				boolean maxLength = false;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				x += 3;
				z -= 0;

				if (!isBoxSolid(world, new PositionData(x, y, z - 1), new PositionData(6, 8, 1)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(6, 8, 1)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x, y, z - 1), new PositionData(6, 8, 1), BlocksAether.mythic_carved_stone)
							|| hasBlock(world, new PositionData(x, y, z - 1), new PositionData(6, 8, 1), BlocksAether.locked_mythic_carved_stone))
					{
						tunnelling = false;
					}

					if (pos.getZ() - z > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneZ(world, random, new PositionData(x, y, z), new PositionData(6, 8, 0));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneZ(world, random, new PositionData(x + 1, y + 1, z), new PositionData(4, 6, 0));

					z--;
				}

				if (maxLength)
				{
					return false;
				}

				this.needsCorridor = false;

				return true;
			}
		}

		return false;
	}

	private ItemStack getNormalLoot(Random random) {
		int item = random.nextInt(25);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.arkenium_pickaxe);
			case 1:
				return new ItemStack(ItemsAether.arkenium_axe);
			case 2:
				return new ItemStack(ItemsAether.arkenium_sword);
			case 3:
				return new ItemStack(ItemsAether.arkenium_shovel);
			case 4:
				return new ItemStack(ItemsAether.swet_cape);
			case 5:
				return new ItemStack(ItemsAether.resistance_stone, random.nextInt(12) + 1);
			case 6:
				return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(6) + 2);
			case 7: {
				if (random.nextInt(20) == 0) {
					return new ItemStack(ItemsAether.aether_tune);
				}

				break;
			}
			case 8: {
					return new ItemStack(ItemsAether.raw_gravitite, random.nextInt(3) + 1);

			}
			case 9: {
					return new ItemStack(ItemsAether.continuum_gemstone);
			}
			case 10: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(ItemsAether.ascending_dawn);
				}

				break;
			}
			case 11: {
				if (random.nextInt(4) == 0) {
					return new ItemStack(ItemsAether.auralite_ring);
				}
				break;
			}
			case 12: {
				if (random.nextInt(3) == 0) {
					return new ItemStack(ItemsAether.auralite_pendant);
				}
				break;
			}
			case 13:
				if (random.nextInt(5) == 0) {
					
				return new ItemStack(ItemsAether.gravitite_pickaxe);
				}
				
				break;
		   
		}
		return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(20) + 8);
	}

	public static ItemStack getMythicBronzeLoot(Random random) {
		int item = random.nextInt(17);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.gummy_swet, random.nextInt(4) + 1, 1);
			case 1:
				return new ItemStack(ItemsAether.jeb_shield);
			case 2:
				return new ItemStack(ItemsAether.tipped_flaming_sword);
			case 3:
				return new ItemStack(ItemsAether.amplified_jeb_hammer);
			case 4:
				return new ItemStack(ItemsAether.builder_slayer);
			case 5:
				return new ItemStack(ItemsAether.tipped_valkyrie_lance);
			case 6:
				return new ItemStack(ItemsAether.haste_ring);
			case 7:
				return new ItemStack(ItemsAether.scaled_sentry_boots);
			case 8:
				return new ItemStack(ItemsAether.repulsion_shield);
			case 9:
				return new ItemStack(BlocksAether.enchanted_gravitite, random.nextInt(4) + 1);
			case 10:
				return new ItemStack(ItemsAether.sentry_shield);
			case 11:
            case 13:
                return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(25) + 20);
			case 12:
				return new ItemStack(ItemsAether.arkenium_fragement, random.nextInt(15) + 20);
            case 14:
				return new ItemStack(ItemsAether.continuum_gemstone, random.nextInt(10) + 5);
			case 15:
				return new ItemStack(BlocksAether.primeval_artifact, random.nextInt(8) + 2);
		}

		return new ItemStack(BlocksAether.primeval_artifact, random.nextInt(15) + 4);
	}
	
	protected Block setRandomBlock(World world, Random random) {
		int nextInt = random.nextInt(15);

		if (nextInt == 0) {
			return BlocksAether.locked_mythic_carved_stone;
		}

		return BlocksAether.mythic_carved_stone;
	}
	

	public Block lockedLightBlock() {
		return BlocksAether.locked_mythic_sentry_stone;
	}
	
	public Block TrapBlock() {
		return BlocksAether.divine_carved_trap;
	}

	public Block lockedBlock() {
		return BlocksAether.locked_mythic_carved_stone;
	}

	public Block mainLightBlock() {
		return BlocksAether.locked_mythic_sentry_stone;
	}

	public Block mainBlock() {
		return BlocksAether.locked_mythic_carved_stone;
	}

	public Block fillerBlock() {
		return BlocksAether.holystone;
	}

	public Block fillerBlock1() {
		return BlocksAether.notch_holystone;
	}
	

}
