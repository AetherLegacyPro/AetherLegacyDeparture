package com.gildedgames.the_aether.world.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.gildedgames.the_aether.entities.bosses.slider.EntitySlider;
import com.gildedgames.the_aether.entities.hostile.EntityCyroGuardian;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.dungeon.util.AetherDungeon;
import com.gildedgames.the_aether.world.dungeon.util.PositionData;

public class CobaltDungeon extends AetherDungeon {
	private boolean needsCorridor;
	private int roomMaximum;
	private int roomCount;

	public CobaltDungeon() {
		needsCorridor = false;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		replaceAir = true;
		replaceSolid = true;

		roomMaximum = random.nextInt(2) + 2;
		roomCount = 0;

		generateBossRoom(world, random, i, j, k);

		return true;
	}

	public boolean generateBossRoom(World world, Random random, int i, int j, int k)
	{
		if (!isBoxSolid(world, new PositionData(i, j - 3, k), new PositionData(16, 16, 16)) || !isBoxSolid(world, new PositionData(i + 20, j, k + 2), new PositionData(12, 10, 12))) {
			return false;													
		}

		RandomTracker randomTracker = new RandomTracker();

		if (randomTracker.testRandom(random,15) != 0)
		{
			if (randomTracker.testRandom(random,40) != 0)
			{
				return false;
			}
		}

		setBlocks(this.lockedBlock(), this.lockedLightBlock(), 20);
		
		addHollowBox(world, random, new PositionData(i - 1, j - 1, k - 1), new PositionData(17, 10, 17));
		addHollowBox(world, random, new PositionData(i, j, k), new PositionData(17, 11, 17));
		addHollowBox(world, random, new PositionData(i, j, k), new PositionData(16, 10, 16));

		addHollowBox(world, random, new PositionData(i + 6, j - 2, k + 6), new PositionData(4, 3, 4));

		EntityCyroGuardian cyro = new EntityCyroGuardian(world);
		cyro.setPosition(i + 8, j + 4, k + 8);

		if (!world.isRemote) {
			world.spawnEntityInWorld(cyro);
		}
		world.setBlock(i + 1, j + 1, k + 1, BlocksAether.zanite_block);
		world.setBlock(i + 14, j + 1, k + 7, BlocksAether.zanite_block);
		world.setBlock(i + 7, j + 1, k + 14, BlocksAether.zanite_block);
		world.setBlock(i + 14, j + 1, k + 14, BlocksAether.zanite_block);

		//world.setBlock(i + 7, j + 1, k + 7, BlocksAether.treasure_chest);
		//world.setBlock(i + 8, j + 1, k + 8, BlocksAether.treasure_chest);
		
		world.setBlock(i + 1, j + 8, k + 1, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner3 = (TileEntityMobSpawner)world.getTileEntity(i + 1, j + 8, k + 1);
          
         if (tileentitymobspawner3 != null)
         {
          tileentitymobspawner3.func_145881_a().setEntityName("aether_legacy.cyro");
         }
         
         world.setBlock(i + 9, j + 8, k + 9, Blocks.mob_spawner);
         TileEntityMobSpawner tileentitymobspawner4 = (TileEntityMobSpawner)world.getTileEntity(i + 9, j + 8, k + 9);
           
          if (tileentitymobspawner4 != null)
          {
           tileentitymobspawner4.func_145881_a().setEntityName("aether_legacy.cyro");
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

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 10, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 10, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x - 5, y, z + 3), new PositionData(6, 4, 6), 0);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.cracked_oblitus_stone);
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

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 10, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 10, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 11, y, z + 3), new PositionData(6, 4, 6), 0);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3)   {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.cracked_oblitus_stone);
					}
				}
				
				for (int p = x + 3; p < x + 7; p += 4) {
					for (int q = z + 3; q < z + 9; q += 2)   {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.cracked_oblitus_stone);
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

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 10, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 10, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 3, y, z - 5), new PositionData(6, 4, 6), 2);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.cracked_oblitus_stone);
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

				if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 10, 12))) {
					return true;
				}

				setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
				addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 10, 12));

				setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

				addSquareTube(world, random, new PositionData(x + 3, y, z + 11), new PositionData(6, 4, 6), 2);

				for (int p = x + 2; p < x + 10; p += 3) {
					for (int q = z + 2; q < z + 10; q += 3) {
						world.setBlock(p, j, q, setRandomBlock(world, random));
						world.setBlock(p, j, q, BlocksAether.cracked_oblitus_stone);
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

		if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 6, 12))) {
			return false;
		}

		setBlocks(this.mainBlock(), this.mainLightBlock(), 20);

		addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 6, 12));

		for (int p = x; p < x + 12; p++) {
			for (int q = y; q < y + 8; q++) {
				for (int r = z; r < z + 12; r++) {
					if (world.getBlock(p, q, r) == this.mainBlock() && random.nextInt(100) == 0) {
						world.setBlock(p, q, r, setRandomBlock(world, random));
						world.setBlock(p, q, r, BlocksAether.cracked_oblitus_stone);
					}
				}
			}
		}

		for (int p = x + 2; p < x + 10; p += 7) {
			for (int q = z + 2; q < z + 10; q += 7) {
				world.setBlock(p, pos.getY(), q, setRandomBlock(world, random));
				world.setBlock(p, pos.getY(), q, BlocksAether.cracked_oblitus_stone);
			}
		}

		addPlaneY(world, random, new PositionData(x + 4, y + 1, z + 4), new PositionData(4, 0, 4));
		int type = random.nextInt(2);
		int p = x + 5 + random.nextInt(2);
		int q = z + 5 + random.nextInt(2);

		switch (type) {
			case 0: {
				if (world.getBlock(p, y + 2, q) == Blocks.air) {
					world.setBlock(p, y + 2, q, BlocksAether.skyroot_chest);
					TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q);

					for (p = 0; p < 3 + random.nextInt(3); p++) {
						chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
					}
					
					// - -	
					world.setBlock(x + 4, y + 6, z + 4, BlocksAether.carved_caelestia_stone_wall);
										
					world.setBlock(x + 3, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 4, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 1, z + 4, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 4, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 3, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					// + -
					world.setBlock(x + 7, y + 6, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 7, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 8, y + 1, z + 4, BlocksAether.carved_caelestia_stone);				
					world.setBlock(x + 8, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 7, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 8, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					// - +
					world.setBlock(x + 4, y + 6, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 3, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 1, z + 7, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 4, y + 1, z + 8, BlocksAether.carved_caelestia_stone);					
					world.setBlock(x + 3, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 3, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 4, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					
					// + +
					world.setBlock(x + 7, y + 6, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 7, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 8, y + 1, z + 7, BlocksAether.carved_caelestia_stone);					
					world.setBlock(x + 8, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 7, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 8, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 1, y + 1, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 2, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 3, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 4, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 5, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 6, z + 1, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 9, y + 1, z + 1, BlocksAether.carved_caelestia_stone_wall);					
					world.setBlock(x + 9, y + 2, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 3, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 4, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 5, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 6, z + 1, BlocksAether.carved_caelestia_stone_wall);	
					
					world.setBlock(x + 9, y + 1, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 2, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 3, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 4, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 5, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 6, z + 9, BlocksAether.carved_caelestia_stone_wall);	
					
					world.setBlock(x + 1, y + 1, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 2, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 3, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 4, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 5, z + 9, BlocksAether.carved_caelestia_stone_wall);
					
					int rand = (int)(1 + Math.random() * 4);
					switch (rand)
			        {
			        case 1:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.primeval_artifact);
			        	break;
			        case 2:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.gravitite_ore);
			        	break;
			        case 3:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.aerogel);
			        	break;
			        case 4:	
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.aerogel);
						break;
					
			        }
					
					world.setBlock(x + 7, y + 4, z + 7, Blocks.mob_spawner);
			          TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 4, z + 7);
			            
			           if (tileentitymobspawner != null)
			           {
			            tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cyro");
			           }
				}
				break;
			}
			case 1: {
				if (world.getBlock(p, y + 2, q) == Blocks.air) {
					world.setBlock(p, y + 2, q, BlocksAether.skyroot_chest);
					TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(p, y + 2, q);

					for (p = 0; p < 3 + random.nextInt(3); p++) {
						chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
					}
					
					// - -	
					world.setBlock(x + 4, y + 6, z + 4, BlocksAether.carved_caelestia_stone_wall);
										
					world.setBlock(x + 3, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 4, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 1, z + 4, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 4, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 3, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					// + -
					world.setBlock(x + 7, y + 6, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 7, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 8, y + 1, z + 4, BlocksAether.carved_caelestia_stone);				
					world.setBlock(x + 8, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 7, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 8, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);
					
					// - +
					world.setBlock(x + 4, y + 6, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 3, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 3, y + 1, z + 7, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 4, y + 1, z + 8, BlocksAether.carved_caelestia_stone);					
					world.setBlock(x + 3, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 3, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 4, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					
					// + +
					world.setBlock(x + 7, y + 6, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 8, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 7, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
					world.setBlock(x + 8, y + 1, z + 7, BlocksAether.carved_caelestia_stone);					
					world.setBlock(x + 8, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 7, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 8, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 1, y + 1, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 2, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 3, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 4, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 5, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 6, z + 1, BlocksAether.carved_caelestia_stone_wall);
					
					world.setBlock(x + 9, y + 1, z + 1, BlocksAether.carved_caelestia_stone_wall);					
					world.setBlock(x + 9, y + 2, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 3, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 4, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 5, z + 1, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 6, z + 1, BlocksAether.carved_caelestia_stone_wall);	
					
					world.setBlock(x + 9, y + 1, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 2, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 3, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 4, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 5, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 9, y + 6, z + 9, BlocksAether.carved_caelestia_stone_wall);	
					
					world.setBlock(x + 1, y + 1, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 2, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 3, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 4, z + 9, BlocksAether.carved_caelestia_stone_wall);
					world.setBlock(x + 1, y + 5, z + 9, BlocksAether.carved_caelestia_stone_wall);

					int rand = (int)(1 + Math.random() * 4);
					switch (rand)
			        {
			        case 1:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.primeval_artifact);
			        	break;
			        case 2:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.gravitite_ore);
			        	break;
			        case 3:
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.aerogel);
			        	break;
			        case 4:	
			        	world.setBlock(x + 7, y + 5, z + 7, BlocksAether.aerogel);
						break;
					
			        }
					
					world.setBlock(x + 7, y + 4, z + 7, Blocks.mob_spawner);
			          TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 4, z + 7);
			            
			           if (tileentitymobspawner != null)
			           {
			            tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cyro");
			           }
				}
				break;
			}
		}

		setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

		switch (dir) {
			case 0: {
				addSquareTube(world, random, new PositionData(x - 5, y, z + 3), new PositionData(6, 4, 6), 0);
				break;
			}
			case 1: {
				addSquareTube(world, random, new PositionData(x + 3, y, z - 5), new PositionData(6, 4, 6), 2);
				break;
			}
			case 2: {
				addSquareTube(world, random, new PositionData(x + 11, y, z + 3), new PositionData(6, 4, 6), 0);
				break;
			}
			case 3: {
				addSquareTube(world, random, new PositionData(x + 3, y, z + 11), new PositionData(6, 4, 6), 2);
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

				if (!isBoxSolid(world, new PositionData(x + 1, y, z), new PositionData(2, 6, 6)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(1, 6, 6)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x + 1, y, z), new PositionData(2, 6, 6), BlocksAether.caelestia_stone)
							|| hasBlock(world, new PositionData(x + 1, y, z), new PositionData(2, 6, 6), BlocksAether.oblitus_stone))
					{
						tunnelling = false;
					}

					if (x - pos.getX() > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneX(world, random, new PositionData(x, y, z), new PositionData(0, 6, 6));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneX(world, random, new PositionData(x, y + 1, z + 1), new PositionData(0, 4, 4));

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

				if (!isBoxSolid(world, new PositionData(x - 1, y, z), new PositionData(1, 6, 6)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(1, 6, 6)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x - 1, y, z), new PositionData(1, 6, 6), BlocksAether.caelestia_stone)
							|| hasBlock(world, new PositionData(x - 1, y, z), new PositionData(1, 6, 6), BlocksAether.oblitus_stone))
					{
						tunnelling = false;
					}

					if (pos.getX() - x > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneX(world, random, new PositionData(x, y, z), new PositionData(0, 6, 6));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneX(world, random, new PositionData(x, y + 1, z + 1), new PositionData(0, 4, 4));

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

				if (!isBoxSolid(world, new PositionData(x, y, z + 1), new PositionData(6, 6, 2)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(6, 6, 1)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x, y, z + 1), new PositionData(6, 4, 2), BlocksAether.caelestia_stone)
							|| hasBlock(world, new PositionData(x, y, z + 1), new PositionData(6, 4, 2), BlocksAether.oblitus_stone))
					{
						tunnelling = false;
					}

					if (z - pos.getZ() > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneZ(world, random, new PositionData(x, y, z), new PositionData(6, 6, 0));
					

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneZ(world, random, new PositionData(x + 1, y + 1, z), new PositionData(4, 4, 0));

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

				if (!isBoxSolid(world, new PositionData(x, y, z - 1), new PositionData(6, 6, 1)))
				{
					return false;
				}

				while(tunnelling)
				{
					if(isBoxEmpty(world, new PositionData(x, y, z), new PositionData(6, 6, 1)))
					{
						tunnelling = false;
					}

					if (hasBlock(world, new PositionData(x, y, z - 1), new PositionData(6, 6, 1), BlocksAether.caelestia_stone)
							|| hasBlock(world, new PositionData(x, y, z - 1), new PositionData(6, 6, 1), BlocksAether.oblitus_stone))
					{
						tunnelling = false;
					}

					if (pos.getZ() - z > 100)
					{
						maxLength = true;
						tunnelling = false;
					}

					setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);
					addPlaneZ(world, random, new PositionData(x, y, z), new PositionData(6, 6, 0));

					setBlocks(Blocks.air, Blocks.air, 1);
					addPlaneZ(world, random, new PositionData(x + 1, y + 1, z), new PositionData(4, 4, 0));

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
		int item = random.nextInt(13);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.golden_amber, random.nextInt(10) + 1);
			case 1:
				return new ItemStack(ItemsAether.continuum_sword);
			case 2:
				return new ItemStack(ItemsAether.wynberry, random.nextInt(3) + 1);
			case 3:
				return new ItemStack(ItemsAether.arkenium_shovel);
			case 4:
				return new ItemStack(ItemsAether.swet_cape);
			case 5:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(25) + 1);
			case 6:
				return new ItemStack(ItemsAether.gummy_swet, random.nextInt(5) + 1);
			case 7:
				return new ItemStack(ItemsAether.dexterity_stone, random.nextInt(3) + 1);
			case 8:
				return new ItemStack(BlocksAether.gravitite_ore, random.nextInt(1) + 1);
			case 9: {
				if (random.nextInt(20) == 0) {
					return new ItemStack(ItemsAether.arkenium_ingot);
				}

				break;
			}
			case 10:
				return new ItemStack(ItemsAether.skyroot_bucket, 1, 2);
			case 11: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(ItemsAether.ice_pendant);
				}

				break;
			}
			case 12: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(ItemsAether.ice_ring);
				}
				break;
			}
		}
		return new ItemStack(BlocksAether.oblitus_stone_2, random.nextInt(2) + 3);
	}

	public static ItemStack getCobaltLoot(Random random) {
		int item = random.nextInt(12);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.arkenium_pickaxe);
			case 1:
				return new ItemStack(BlocksAether.gravitite_ore, random.nextInt(2) + 1);
			case 2:
				return new ItemStack(ItemsAether.arkenium_sword);
			case 3:
				return new ItemStack(ItemsAether.confractus_staff);
			case 4:
				return new ItemStack(ItemsAether.confractus_staff);
			case 5:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(8) + 1);
			case 6: {
				return new ItemStack(ItemsAether.arkenium_fragement, random.nextInt(3) + 2);
			}
			case 7:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(3));
			case 8: {
				return new ItemStack(BlocksAether.aerogel);
			}
			case 9: {
				return new ItemStack(ItemsAether.shears_of_agnes, 1);
			}
			case 10: {
				if (random.nextInt(15) == 0) {
					return new ItemStack(ItemsAether.enchanted_divineral, 1);
				}
				break;
			}
			case 11: {
				return new ItemStack(ItemsAether.cyro_bow, 1);
			}
		}
		return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(15) + 2);
	}
	
	protected Block setRandomBlock(World world, Random random) {
		int nextInt = random.nextInt(15);

		if (nextInt == 0) {
			return BlocksAether.cracked_oblitus_stone;
		}

		return BlocksAether.oblitus_stone;
	}
	

	public Block lockedLightBlock() {
		return BlocksAether.cracked_oblitus_stone;
	}
	
	public Block TrapBlock() {
		return BlocksAether.cracked_oblitus_stone;
	}

	public Block lockedBlock() {
		return BlocksAether.oblitus_stone;
	}

	public Block mainLightBlock() {
		return BlocksAether.cracked_oblitus_stone;
	}

	public Block mainBlock() {
		return BlocksAether.oblitus_stone;
	}

	public Block fillerBlock() {
		return BlocksAether.frozen_quicksoil;
	}

	public Block fillerBlock1() {
		return BlocksAether.quicksoil;
	}
	

}