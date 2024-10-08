package com.gildedgames.the_aether.world.gen.components;

import java.util.Random;

import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityAncientSunSpirit;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.world.gen.AetherStructure;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class ComponentAncientGoldenDungeon extends AetherStructure {

	private int direction;

	public ComponentAncientGoldenDungeon() {

	}

	public ComponentAncientGoldenDungeon(int chunkX, int chunkZ, int direction) {
		this.coordBaseMode = 0;
		this.direction = direction;
		this.boundingBox = new StructureBoundingBox(chunkX, 80, chunkZ, chunkX + 100, 220, chunkZ + 100);
	}

	@Override
	public boolean generate() {
		this.replaceAir = true;
		this.replaceSolid = true;
		this.setStructureOffset(60, 0, 60);

		int r = 24;
		r = (int) Math.floor((double) r * 0.8D);
		int wid = (int) Math.sqrt((r * r) / 2);

		this.setBlocks(BlocksAether.locked_ancient_hellfire_stone, BlocksAether.locked_ancient_light_hellfire_stone, 10);

		for (int j = 4; j > -5; j--) {
			int a = wid;

			if (j >= 3 || j <= -3) {
				a--;
			}

			if (j == 4 || j == -4) {
				a--;
			}

			for (int i = -a; i <= a; i++) {
				for (int k = -a; k <= a; k++) {
					if (j == 4) {
						this.setBlockWithOffset(i, j, k);
					} else if (j > -4) {
						if (i == a || -i == a || k == a || -k == a) {
							this.setBlockWithOffset(i, j, k);
						} else {
							this.setBlockWithOffset(i, j, k, Blocks.air, 0);

							if (j == -2 && (i == (a - 1) || -i == (a - 1) || k == (a - 1) || -k == (a - 1)) && (i % 3 == 0 || k % 3 == 0)) {

							}
						}
					} else {
						this.setBlockWithOffset(i, j, k);

						if ((i == (a - 2) || -i == (a - 2)) && (k == (a - 2) || -k == (a - 2))) {
							this.setBlockWithOffset(i, j, k + 1, BlocksAether.locked_ancient_light_hellfire_stone, 0);
							this.setBlockWithOffset(i, j, k - 1, BlocksAether.locked_ancient_light_hellfire_stone, 0);
							this.setBlockWithOffset(i + 1, j, k, BlocksAether.locked_ancient_light_hellfire_stone, 0);
							this.setBlockWithOffset(i - 1, j, k, BlocksAether.locked_ancient_light_hellfire_stone, 0);
							
							this.setBlockWithOffset(i, j, k + 1, BlocksAether.hellfire_trap, 0);
							this.setBlockWithOffset(i, j, k - 1, BlocksAether.hellfire_trap, 0);
							this.setBlockWithOffset(i + 1, j, k, BlocksAether.hellfire_trap, 0);
							this.setBlockWithOffset(i - 1, j, k, BlocksAether.hellfire_trap, 0);
							
							this.setBlockWithOffset(i, j + 1, k, Blocks.netherrack, 0);
							this.setBlockWithOffset(i, j + 2, k, BlocksAether.hellfire, 0);
							
							this.setBlockWithOffset(i + 1, j + 7, k, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 7, k + 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 7, k - 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i - 1, j + 7, k, BlocksAether.ancient_hellfire_wall, 0);
							
							this.setBlockWithOffset(i + 1, j + 6, k, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 6, k + 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 6, k - 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i - 1, j + 6, k, BlocksAether.ancient_hellfire_wall, 0);
							
							this.setBlockWithOffset(i + 1, j + 5, k, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 5, k + 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i, j + 5, k - 1, BlocksAether.ancient_hellfire_wall, 0);
							this.setBlockWithOffset(i - 1, j + 5, k, BlocksAether.ancient_hellfire_wall, 0);
							
							this.setBlockWithOffset(i, j + 5, k, BlocksAether.locked_ancient_hellfire_stone, 0);							
							this.setBlockWithOffset(i, j + 7, k, BlocksAether.locked_ancient_hellfire_stone, 0);
							
							int rand = (int)(1 + Math.random() * 8);
							switch (rand)
					        {
					        case 1:
					        	this.setBlockWithOffset(i, j + 6, k, BlocksAether.zanite_block, 0);							
					        	break;
					        case 2:
								this.setBlockWithOffset(i, j + 6, k, BlocksAether.zanite_block, 0);							
								break;
					        case 3:
								this.setBlockWithOffset(i, j + 6, k, BlocksAether.luminous_stone, 0);							
								break;
					        case 4:
								this.setBlockWithOffset(i, j + 6, k, BlocksAether.luminous_stone, 0);							
								break;
					        case 5:
								this.setBlockWithOffset(i, j + 6, k, BlocksAether.luminous_stone, 0);							
								break;
					        case 6:							
								break;
					        case 7:						
								break;
					        case 8:						
								break;
					        }
							
						}
					}
				}
			}
		}

		for (int i = wid; i < wid + 32; i++) {
			for (int j = -3; j < 2; j++) {
				for (int k = -3; k < 4; k++) {
					int a, b;
					if (this.direction / 2 == 0) {
						a = i;
						b = k;
					} else {
						a = k;
						b = i;
					}

					if (this.direction % 2 == 0) {
						a = -a;
						b = -b;
					}

					if (!BlocksAether.isGood(this.getBlockStateWithOffset(a, j, b))) {
						this.setBlocks(BlocksAether.holystone, BlocksAether.bloodmoss_stone, 5);

						if (j == -3) {
							this.setBlockWithOffset(a, j, b);
						} else if (j < 1) {
							if (i == wid) {
								if (k < 2 && k > -2 && j < 0) {
									this.setBlockWithOffset(a, j, b, Blocks.air, 0);
								} else {
									this.setBlocks(BlocksAether.locked_ancient_hellfire_stone, BlocksAether.locked_ancient_light_hellfire_stone, 10);

									this.setBlockWithOffset(a, j, b);
								}
							} else {
								if (k == 3 || k == -3) {
									this.setBlockWithOffset(a, j, b);
								} else {
									this.setBlockWithOffset(a, j, b, Blocks.air, 0);

									if (j == -1 && (k == 2 || k == -2) && (i - wid - 2) % 3 == 0) {

									}
								}
							}
						} else if (i == wid) {
							this.setBlocks(BlocksAether.locked_ancient_hellfire_stone, BlocksAether.locked_ancient_light_hellfire_stone, 10);
							this.setBlockWithOffset(a, j, b);
						} else {
							this.setBlocks(BlocksAether.holystone, BlocksAether.bloodmoss_stone, 5);
							this.setBlockWithOffset(a, j, b);
						}
					}

					a = -a;
					b = -b;

					this.setBlocks(BlocksAether.locked_ancient_hellfire_stone, BlocksAether.locked_ancient_light_hellfire_stone, 10);

					if (i < wid + 6) {
						if (j == -3) {
							this.setBlockWithOffset(a, j, b);
						} else if (j < 1) {
							if (i == wid) {
								if (k < 2 && k > -2 && j < 0) {
									this.setBlockWithOffset(a, j, b);
								} else {
									this.setBlockWithOffset(a, j, b);
								}
							} else if (i == wid + 5) {
								this.setBlockWithOffset(a, j, b);
							} else {
								if (i == wid + 4 && k == 0 && j == -2) {
									this.setBlockWithOffset(a, j, b, BlocksAether.treasure_chest, 0);
									//this.setBlockWithOffset(a, j, b + 2, BlocksAether.treasure_chest, 0);
								}	else if (i == wid + 4 && k == 0 && j == -2) {
									this.setBlockWithOffset(a, j + 40, b, Blocks.chest, 0);
								} else if (k == 3 || k == -3) {
									this.setBlockWithOffset(a, j, b);
								} else {
									this.setBlockWithOffset(a, j, b, Blocks.air, 0);

									if (j == -1 && (k == 2 || k == -2) && (i - wid - 2) % 3 == 0) {

									}
								}
							}
						} else {
							this.setBlockWithOffset(a, j, b);
						}
					}
				}
			}

		}

		EntityAncientSunSpirit boss = new EntityAncientSunSpirit(this.worldObj, this.getActualX(0, 0), this.getActualY(-1), this.getActualZ(0, 0), this.direction);

		this.spawnEntity(boss, 0, -1, 0);

		return true;
	}

	public static ItemStack getAncientGoldLoot(Random random) {
		int item = random.nextInt(12);

		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.iron_bubble);
			case 1:
				return new ItemStack(ItemsAether.vampire_blade);
			case 2:
				return new ItemStack(ItemsAether.gravitite_shield);
			case 3: {
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.phoenix_helmet);
				}
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.phoenix_leggings);
				}
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.phoenix_boots);
				}
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.phoenix_chestplate);
				}

				break;
			}
			case 4: {
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.phoenix_boots);
				}

				return new ItemStack(ItemsAether.phoenix_gloves);
			}
			case 5: {
				return new ItemStack(ItemsAether.life_shard);
			}
		
			case 6: {
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.obsidian_boots);
				}

				return new ItemStack(ItemsAether.obsidian_gloves);
			}
			case 7: {
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.notch_hammer);
				}

				return new ItemStack(ItemsAether.flaming_stone);
			}
			case 8: {
				if (random.nextBoolean()) {
					return new ItemStack(ItemsAether.battle_sentry_hammer);
				}

				return new ItemStack(ItemsAether.phoenix_cape);
			}

			case 9: 
				return new ItemStack(ItemsAether.chain_gloves);
				
			case 10: 
				return new ItemStack(ItemsAether.divineral_gloves);
				
			
		}

		return new ItemStack(ItemsAether.amplified_holystone_pickaxe);
	}

}
