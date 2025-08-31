package com.gildedgames.the_aether.world.dungeon;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityZarnillys;
import com.gildedgames.the_aether.world.dungeon.util.AetherDungeon;
import com.gildedgames.the_aether.world.dungeon.util.PositionData;

public class ZarnillysDen extends AetherDungeon {
	private boolean needsCorridor;
	private int roomMaximum;
	private int roomCount;

	public ZarnillysDen() {
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
		
		if (!isBoxSolid(world, new PositionData(i, j - 3, k), new PositionData(8, 9, 8)) || !isBoxSolid(world, new PositionData(i + 10, j, k + 1), new PositionData(6, 6, 6))) {
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

		setBlocks(this.mainBlock(), this.mainLightBlock(), 30);

		addHollowBox(world, random, new PositionData(i, j, k), new PositionData(8, 6, 8));
		
		EntityZarnillys zarnillys = new EntityZarnillys(world);
		zarnillys.setPosition(i + 4, j + 1, k + 4);

		if (!world.isRemote) {
			world.spawnEntityInWorld(zarnillys);
		}
		
		EntityZarnillys zarnillys2 = new EntityZarnillys(world);
		zarnillys2.setPosition(i + 3, j + 1, k + 3);

		if (!world.isRemote) {
			world.spawnEntityInWorld(zarnillys2);
		}
		
		int rand = (int)(1 + Math.random() * 6);
		switch (rand)
        {
        case 1: 
        EntityZarnillys zarnillys5 = new EntityZarnillys(world);
		zarnillys5.setPosition(i + 3, j + 1, k + 3);
				
		if (!world.isRemote) {
			world.spawnEntityInWorld(zarnillys5);
		}
        case 2:
        EntityZarnillys zarnillys4 = new EntityZarnillys(world);
    	zarnillys4.setPosition(i + 4, j + 1, k + 3);

    	if (!world.isRemote) {
    		world.spawnEntityInWorld(zarnillys4);
    		}
    	
    	EntityZarnillys zarnillys6 = new EntityZarnillys(world);
    	zarnillys6.setPosition(i + 4, j + 1, k + 3);

    	if (!world.isRemote) {
    		world.spawnEntityInWorld(zarnillys6);
    		}
        
        case 3:
        	
        case 4:
        	
        case 5:
        	
        case 6:	
        }
			world.setBlock(i + 4, j + 1, k + 1, BlocksAether.skyroot_chest);
			world.setBlock(i + 1, j + 1, k + 3, BlocksAether.skyroot_chest);
			TileEntitySkyrootChest chest = (TileEntitySkyrootChest) world.getTileEntity(i + 4, j + 1, k + 1);
			TileEntitySkyrootChest chesttt = (TileEntitySkyrootChest) world.getTileEntity(i + 1, j + 1, k + 3);

			for (i = 0; i < 3 + random.nextInt(30); i++) {
				chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), this.getNormalLoot(random));
				chesttt.setInventorySlotContents(random.nextInt(chesttt.getSizeInventory()), this.getNormalLoot(random));
			}
		

		return true;
	}

	private ItemStack getNormalLoot(Random random) {
		int item = random.nextInt(15);
		switch (item) {
			case 0:
            case 3:
                return new ItemStack(ItemsAether.zarnillys_scales, random.nextInt(2) + 1);
			case 1:
				return new ItemStack(BlocksAether.enchanted_holystone, random.nextInt(4) + 1);
			case 2:
				return new ItemStack(ItemsAether.continuum_orb);
            case 4:
				return new ItemStack(ItemsAether.zanite_ring);
			case 5:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(8) + 1);
			case 6: {
				if (random.nextInt(20) == 0) {
					return new ItemStack(BlocksAether.enchanted_agiosite, random.nextInt(7) + 2);
				}

				break;
			}
			case 7:
				return new ItemStack(BlocksAether.enchanted_aetheral_stone, random.nextInt(8) + 1);
			case 8: {
				if (random.nextInt(10) == 0) {
					return new ItemStack(BlocksAether.enchanted_deific, random.nextInt(8) + 1);
				}

				break;
			}
			case 9: {
				if (random.nextInt(4) == 0) {
					return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(2) + 1);
				}
				break;
			}
			case 10: {
                return new ItemStack(BlocksAether.glowing_icestone, 4);
            }
		}
		return new ItemStack(BlocksAether.icestone, random.nextInt(9) + 2);
	}	

	public Block mainLightBlock() {
		return BlocksAether.glowing_icestone;
	}

	public Block mainBlock() {
		return BlocksAether.icestone;
	}
	
}