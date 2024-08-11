package com.gildedgames.the_aether.world.gen.components;

import java.util.Random;

import com.gildedgames.the_aether.entities.bosses.EntityElderZarnillys;
import com.gildedgames.the_aether.entities.bosses.lurker.EntityLurker;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.world.gen.AetherGenUtils;
import com.gildedgames.the_aether.world.gen.AetherStructure;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class ComponentOsmiumDungeon extends AetherStructure {

	private static final Block LOCKED_FUSE_STONE = BlocksAether.locked_fuse_stone;

	private static final Block LOCKED_CREEPING_STONE = BlocksAether.locked_creeping_stone;
	
	private static final Block FUSE_TRAP = BlocksAether.fuse_trap;
	
	private static final Block FUSE_TRAP_2 = BlocksAether.fuse_trap_2;

	private int[][][] rooms = new int[3][3][3];

	private int firstStaircaseZ, secondStaircaseZ, finalStaircaseZ;

	private int xTendency, zTendency;

	public ComponentOsmiumDungeon() {

	}

	public ComponentOsmiumDungeon(int chunkX, int chunkZ) {	 //80							//220
		this.coordBaseMode = 0;
		this.boundingBox = new StructureBoundingBox(chunkX, 80, chunkZ, chunkX + 200, 40, chunkZ + 200);
	}

	public void setStaircasePosition(int first, int second, int third) {
		this.firstStaircaseZ = first;
		this.secondStaircaseZ = second;
		this.finalStaircaseZ = third;
	}

	public void setCloudTendencies(int xTendency, int zTendency) {
		this.xTendency = xTendency;
		this.zTendency = zTendency;
	}

	@Override
	public boolean generate() {
		this.replaceAir = true;

		this.setStructureOffset(42, 34, 40);

		for (int tries = 0; tries < 100; tries++) {
			AetherGenUtils.generateStormClouds(this, BlocksAether.storm_aercloud, 0, false, 10, this.random.nextInt(32), 0, this.random.nextInt(25), this.xTendency, this.zTendency);
		}

		this.setStructureOffset(62, 48, 60);

		this.replaceSolid = true;

		this.setBlocks(BlocksAether.caelestia_stone, BlocksAether.agiosite, 45);

		this.addSolidBox(0, -10, 0, 110, 10, 60);

		for (int x = 0; x < 110; x += 8) {
			this.generateColumn(x, 0, 0, 28);
			this.generateColumn2(x - 1, 0, 0, 28); //new
			this.generateColumn2(x + 1, 0, 0, 28); //new
			this.generateColumn(x, 0, 54, 28);
			this.generateColumn2(x - 1, 0, 54, 28); //new
			this.generateColumn2(x + 1, 0, 54, 28); //new
		}

		for (int z = 0; z < 24; z += 8) {
			this.generateColumn(0, 0, z, 28);
			this.generateColumn2(0, 0, z - 1, 28); //new
			this.generateColumn2(0, 0, z + 1, 28); //new
			this.generateColumn(104, 0, z, 28);
			this.generateColumn2(104, 0, z + 1, 28); //new 
			this.generateColumn2(104, 0, z - 1, 28); //new
		}

		for (int z = 38; z < 60; z += 8) {
			this.generateColumn(0, 0, z, 28);
			this.generateColumn2(0, 0, z - 1, 28); //new
			this.generateColumn2(0, 0, z + 1, 28); //new
			this.generateColumn(104, 0, z, 28);
			this.generateColumn2(104, 0, z - 1, 28); //new
			this.generateColumn2(104, 0, z + 1, 28); //new
		}
		
		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 40);
		this.addHollowBox(9, -1, 9, 92, 32, 42); //-2 94 44
		this.addPlaneX(22, 0, 10, 30, 40); // walls in the rooms
		this.addPlaneX(36, 0, 10, 30, 40); //
		this.addPlaneX(50, 0, 10, 30, 40); //
		this.addPlaneZ(10, 0, 22, 40, 30); //
		this.addPlaneZ(10, 0, 36, 40, 30); //

		this.setBlocks(LOCKED_FUSE_STONE, BlocksAether.fuse_trap, 40); //30
		this.addPlaneY(10, 18, 10, 40, 40);
		
		this.setBlocks(LOCKED_FUSE_STONE, BlocksAether.fuse_trap_2, 40);
		this.addPlaneY(10, -1, 10, 40, 40);
		this.addPlaneY(10, 8, 10, 40, 40);

		for (int y = 0; y < 4; y++) { 
			for (int z = 28; z < 32; z++) {
				this.setBlockWithOffset(8, y, z, Blocks.air, 0);
			}
		}

		this.setBlocks(Blocks.air, Blocks.air, 1);
		this.addSolidBox(0, -8, 28, 2, 8, 4);
	    this.addSolidBox(2, -6, 28, 2, 6, 4);
		this.addSolidBox(4, -4, 28, 2, 4, 4);
		this.addSolidBox(6, -2, 28, 2, 2, 4);
		
		this.addSolidBox(6, -2, 28, 2, 20, 4);

		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 30);
		
		for (int y = 0; y < 14; y++) { //roof
			this.addPlaneY(-1, 30 + y, -1 + 2 * y, 114, 64 - 4 * y);
		}

		this.rooms[2][0][this.finalStaircaseZ] = 2;
		this.rooms[2][1][this.finalStaircaseZ] = 2;
		this.rooms[2][2][this.finalStaircaseZ] = 1;

		int x = 50;
		int y;
		int z;

		for (y = 0; y < 4; y++) {
			for (z = 14 + 14 * this.finalStaircaseZ; z < 18 + 14 * this.finalStaircaseZ; z++) {
				this.setBlockWithOffset(x, y, z, Blocks.air, 0);
			}
		}

		this.rooms[1][0][this.firstStaircaseZ] = 1;
		this.rooms[1][1][this.firstStaircaseZ] = 1;

		this.rooms[0][1][this.secondStaircaseZ] = 1;
		this.rooms[0][2][this.secondStaircaseZ] = 1;

		for (int p = 0; p < 3; p++) {
			for (int q = 0; q < 3; q++) {
				for (int r = 0; r < 3; r++) {
					if (p == 0 && q != 0 && this.secondStaircaseZ == r) { 
						if (r == 0) {
							this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
							this.generateDoorZ(-6 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
						} else if (r == 1) {
							this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
							this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
						} else if (r == 2) {
							this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
							this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
						}
					} else if (p == 1 && q != 2 && this.firstStaircaseZ == r) { 
						if (this.firstStaircaseZ != this.finalStaircaseZ) {
							this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
						}

						if (r == 0) {
							this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
						} else if (r == 1) {
							this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
						} else if (r == 2) {
							this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
						}
					} else if (p == 2 && this.finalStaircaseZ == r) {
						if (q == 0) {
							this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
						} else if (q == 2) {
							if (r == 0) {
								this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
								this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							} else if (r == 1) {
								this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
								this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
								this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							} else if (r == 2) {
								this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
								this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4); 
							}
						}
						
					} else {
						int type = this.rooms[p][q][r];

						if (p + 1 < 3) {
							int newType = this.rooms[p + 1][q][r];

							if (newType != 2 && !(newType == 1 && type == 1)) {
								this.rooms[p][q][r] = 3;
								type = 3;

								this.generateDoorX(22 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
							}
						}

						if (p - 1 > 0) {
							int newType = this.rooms[p - 1][q][r];

							if (newType != 2 && !(newType == 1 && type == 1)) {
								this.rooms[p][q][r] = 4;
								type = 4;

								this.generateDoorX(8 + 14 * p, 10 * q, 14 + 14 * r, 4, 4);
							}
						}

						if (r + 1 < 3) {
							int newType = this.rooms[p][q][r + 1];

							if (newType != 2 && !(newType == 1 && type == 1)) {
								this.rooms[p][q][r] = 5;
								type = 5;

								this.generateDoorZ(22 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							}
						}

						if (r - 1 > 0) {
							int newType = this.rooms[p][q][r - 1];

							if (newType != 2 && !(newType == 1 && type == 1)) {
								this.rooms[p][q][r] = 6;
								type = 6;

								this.generateDoorZ(8 + 14 * r, 14 + 14 * p, 10 * q, 4, 4);
							}
						}

						int roomType = this.random.nextInt(7);

						if (type >= 3) { //3
							this.setBlockWithOffset(14 + p * 14, -2 + q * 10, 14 + r * 14, BlocksAether.fuse_trap, 0);

							switch (roomType) {
								case 1: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2); // 4 4

									int u = 14 + 14 * p + this.random.nextInt(2);
									int v = 14 + 14 * r + this.random.nextInt(2);

									if (this.getBlockState(u, (10 * q) + 1, v).getMaterial() == Material.air) {
										this.setBlockWithOffset(u, (10 * q) + 1, v, BlocksAether.skyroot_chest, 0);
										this.setBlockWithOffset(u, (10 * q), v, BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(u, (10 * q) - 1, v, BlocksAether.locked_fuse_stone, 0);

										TileEntity tileEntity = this.getTileEntityFromPosWithOffset(u, (10 * q) + 1, v);

										if (tileEntity instanceof TileEntitySkyrootChest) {
											for (u = 0; u < 3 + random.nextInt(3); u++) {
												((TileEntitySkyrootChest) tileEntity).setInventorySlotContents(this.random.nextInt(((TileEntitySkyrootChest) tileEntity).getSizeInventory()), this.getNormalLoot(this.random));
											}
										}
									}

									break;
								}
								case 2: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.chest_mimic, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);

									if (this.random.nextBoolean()) {
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.chest_mimic, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									}

									break;
								}
								
								case 3: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2); // 4 4

									int u = 14 + 14 * p + this.random.nextInt(2);
									int v = 14 + 14 * r + this.random.nextInt(2);

									if (this.getBlockState(u, (10 * q) + 1, v).getMaterial() == Material.air) {
										this.setBlockWithOffset(u, (10 * q) + 1, v, BlocksAether.skyroot_chest, 0);
										this.setBlockWithOffset(u, (10 * q) + 2, v, BlocksAether.skyroot_chest, 0);
										this.setBlockWithOffset(u, (10 * q), v, BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(u, (10 * q) - 1, v, BlocksAether.locked_fuse_stone, 0);

										TileEntity tileEntity = this.getTileEntityFromPosWithOffset(u, (10 * q) + 1, v);
										TileEntity tileEntity2 = this.getTileEntityFromPosWithOffset(u, (10 * q) + 2, v);

										if (tileEntity instanceof TileEntitySkyrootChest) {
											for (u = 0; u < 3 + random.nextInt(3); u++) {
												((TileEntitySkyrootChest) tileEntity).setInventorySlotContents(this.random.nextInt(((TileEntitySkyrootChest) tileEntity).getSizeInventory()), this.getNormalLoot(this.random));
											}
										}
										
										if (tileEntity2 instanceof TileEntitySkyrootChest) {
											for (u = 0; u < 3 + random.nextInt(5); u++) {
												((TileEntitySkyrootChest) tileEntity2).setInventorySlotContents(this.random.nextInt(((TileEntitySkyrootChest) tileEntity2).getSizeInventory()), this.getNormalLoot(this.random));
											}
										}
									}

									break;
								}
								
								case 4: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.zanite_block, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);

									if (this.random.nextBoolean()) {
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.chest_mimic, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									}

									break;
								}
								
								case 5: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.arkenium_block, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);

									if (this.random.nextBoolean()) {
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.block_of_auralite, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									}

									break;
								}
								
								case 6: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.zanite_block, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);

									if (this.random.nextBoolean()) {
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.ambrosium_block, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q), 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(14 + 14 * p + this.random.nextInt(2), (10 * q) - 1, 14 + 14 * r + this.random.nextInt(2), BlocksAether.locked_fuse_stone, 0);
									}

									break;
								}
								
								case 7: {
									this.addPlaneY(14 + 14 * p, (10 * q), 14 + 14 * r, 2, 2); // 4 4

									int u = 14 + 14 * p + this.random.nextInt(2);
									int v = 14 + 14 * r + this.random.nextInt(2);

									if (this.getBlockState(u, (10 * q) + 1, v).getMaterial() == Material.air) {
										this.setBlockWithOffset(u, (10 * q) + 1, v, BlocksAether.chest_mimic, 0);
										this.setBlockWithOffset(u, (10 * q), v, BlocksAether.locked_fuse_stone, 0);
										this.setBlockWithOffset(u, (10 * q) - 1, v, BlocksAether.locked_fuse_stone, 0);
									

										
																				
									}
																		
									EntityElderZarnillys zar = new EntityElderZarnillys(worldObj);
									zar.setPosition(14 + 14 * p + this.random.nextInt(2), (10 * q) + 1, 14 + 14 * r + this.random.nextInt(2));

									if (!worldObj.isRemote) {
										worldObj.spawnEntityInWorld(zar);
									}

									break;
								}
							}
						}
					}
				}
			}
		}

		for (x = 0; x < 48; x++) { 
			for (z = 0; z < 40; z++) {
				int distance = (int) (Math.sqrt(x * x + (z - 14) * (z - 14)) + Math.sqrt(x * x + (z - 24) * (z - 24)));

				if (distance == 42) {
					this.setBlockWithOffset(52 + x, 0, 10 + z, LOCKED_CREEPING_STONE, 0);
				} else if (distance > 42) {
					this.setBlockWithOffset(52 + x, 0, 10 + z, LOCKED_FUSE_STONE, 0);
				}
			}
		} 

		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 40);
		this.addPlaneY(176, 4, 44, 24, 32); 
		this.addSolidBox(184, 8, 52, 16, 8, 16); 
		this.addLineX(184, 16, 52, 16);
		this.addLineX(184, 16, 64, 16);
		this.addPlaneX(196, 16, 52, 16, 16);


		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 40);
		this.addPlaneY(70, 1, 10, 12, 6); //left
		this.addPlaneY(70, 1, 44, 12, 6); //right

		//left
		this.addLineZ(68, 1, 10, 4);
		this.addLineZ(82, 1, 10, 4);
		this.addLineX(72, 1, 16, 8);
		//right
		this.addLineZ(68, 1, 46, 4);
		this.addLineZ(82, 1, 46, 4);
		this.addLineX(72, 1, 42, 8);
		
		this.generateChandelier(56, 0, 20, 16);
		this.generateChandelier(86, 0, 20, 16);
		this.generateChandelier(86, 0, 38, 16);
		this.generateChandelier(56, 0, 38, 16);

		this.generateVoidSapling(90, 1, 12);
		this.generateVoidSapling(90, 1, 42); 

		EntityLurker lurker = new EntityLurker(this.worldObj, (double) this.getActualX(40, 15), (double) this.getActualY(1) + 0.5D, (double) this.getActualZ(40, 15));

		lurker.setPosition(this.getActualX(40, 15), this.getActualY(2), this.getActualZ(40, 15));
		lurker.setDungeon(this.getActualX(26, 24), this.getActualY(0), this.getActualZ(26, 24));

		this.spawnEntity(lurker, 80, 2, 30);
		
		//Elder Zarnillys Boss Room Spawns
		EntityElderZarnillys zar1 = new EntityElderZarnillys(this.worldObj);
		zar1.setPosition(this.getActualX(40, 15), this.getActualY(2), this.getActualZ(40, 15));
		this.spawnEntity(zar1, 80, 2, 30);
		
		EntityElderZarnillys zar2 = new EntityElderZarnillys(this.worldObj);
		zar2.setPosition(this.getActualX(40, 15), this.getActualY(2), this.getActualZ(40, 15));
		this.spawnEntity(zar2, 80, 2, 30);
		
		//Elder Zarnillys Room Spawns
		EntityElderZarnillys zar3 = new EntityElderZarnillys(this.worldObj);
		zar3.setPosition(this.getActualX(25, 30), this.getActualY(5), this.getActualZ(25, 30));
		this.spawnEntity(zar3, 25, 5, 30);
		
		EntityElderZarnillys zar4 = new EntityElderZarnillys(this.worldObj);
		zar4.setPosition(this.getActualX(25, 30), this.getActualY(15), this.getActualZ(25, 30));
		this.spawnEntity(zar4, 25, 15, 30);
		
		EntityElderZarnillys zar5 = new EntityElderZarnillys(this.worldObj);
		zar5.setPosition(this.getActualX(25, 30), this.getActualY(25), this.getActualZ(25, 30));
		this.spawnEntity(zar5, 25, 25, 30);
		
		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 40);
		this.addHollowBox(82, -2, 26, 8, 8, 8); //-4

		x = 84 + this.random.nextInt(2);
		z = 28 + this.random.nextInt(2);
		
		this.setBlockWithOffset(x - 1, -1, z, BlocksAether.treasure_chest, 0);
		this.setBlockWithOffset(x + 1, -1, z, BlocksAether.ancient_enchanter, 0);
		//Decoration Coding Hell, It just works
		//________________________________________________________________________________________
		//Boss Room decoration
		//this.setBlockWithOffset(x - 3, +28, z + 10, BlocksAether.void_fence, 0);
		//this.setBlockWithOffset(x - 3, +29, z + 10, BlocksAether.void_fence, 0);
		
		//this.setBlockWithOffset(x - 3, +28, z - 8, BlocksAether.void_fence, 0);
		//this.setBlockWithOffset(x - 3, +29, z - 8, BlocksAether.void_fence, 0);
		
		//this.setBlockWithOffset(x - 29, +28, z - 8, BlocksAether.void_fence, 0);
		//this.setBlockWithOffset(x - 29, +29, z - 8, BlocksAether.void_fence, 0);
		
		//this.setBlockWithOffset(x - 29, +28, z + 10, BlocksAether.void_fence, 0);
		//this.setBlockWithOffset(x - 29, +29, z + 10, BlocksAether.void_fence, 0);
		
		//Coldfire Lights
		this.setBlockWithOffset(x - 33, +26, z - 17, BlocksAether.locked_fuse_stone, 0); 
		this.setBlockWithOffset(x - 33, +27, z - 17, Blocks.netherrack, 0);
		this.setBlockWithOffset(x - 33, +28, z - 17, BlocksAether.coldfire, 0);
		
		this.setBlockWithOffset(x - 32, +27, z - 17, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 33, +27, z - 16, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 33, +27, z - 18, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 34, +27, z - 17, BlocksAether.locked_fuse_stone, 0);
		
		this.setBlockWithOffset(x + 14, +26, z - 18, BlocksAether.locked_fuse_stone, 0);  
		this.setBlockWithOffset(x + 14, +27, z - 18, Blocks.netherrack, 0);
		this.setBlockWithOffset(x + 14, +28, z - 18, BlocksAether.coldfire, 0);
		
		this.setBlockWithOffset(x + 15, +27, z - 18, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 13, +27, z - 18, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 14, +27, z - 19, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 14, +27, z - 17, BlocksAether.locked_fuse_stone, 0);
		
		this.setBlockWithOffset(x + 13, +26, z + 20, BlocksAether.locked_fuse_stone, 0); 
		this.setBlockWithOffset(x + 13, +27, z + 20, Blocks.netherrack, 0);
		this.setBlockWithOffset(x + 13, +28, z + 20, BlocksAether.coldfire, 0);
		
		this.setBlockWithOffset(x + 14, +27, z + 20, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 12, +27, z + 20, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 13, +27, z + 21, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x + 13, +27, z + 19, BlocksAether.locked_fuse_stone, 0);
		
		this.setBlockWithOffset(x - 32, +26, z + 20, BlocksAether.locked_fuse_stone, 0); 
		this.setBlockWithOffset(x - 32, +27, z + 20, Blocks.netherrack, 0);
		this.setBlockWithOffset(x - 32, +28, z + 20, BlocksAether.coldfire, 0);
		
		this.setBlockWithOffset(x - 31, +27, z + 20, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 33, +27, z + 20, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 32, +27, z + 21, BlocksAether.locked_fuse_stone, 0);
		this.setBlockWithOffset(x - 32, +27, z + 19, BlocksAether.locked_fuse_stone, 0);
		
		//floor 1 room --X --- ---  ______________________________________________________________
		this.setBlockWithOffset(x - 44, +0, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 12, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +0, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
				
		//floor 1 room -X- --- ---
		this.setBlockWithOffset(x - 44, +0, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z - 2, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +0, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		
		//floor 1 room X-- --- ---   
		this.setBlockWithOffset(x - 38, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 15, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +0, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +0, z - 10, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 38, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 10, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +0, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +0, z - 14, BlocksAether.inebriation_aercloud, 0);
		
		//floor 2 room --X --- ---____________________________________________________________________
		this.setBlockWithOffset(x - 44, +9, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 12, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +9, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
				
		//floor 2 room -X- --- ---
		this.setBlockWithOffset(x - 44, +9, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z - 2, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z + 4, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 39, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +9, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		
		//floor 2 room X-- --- ---   
		this.setBlockWithOffset(x - 38, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z - 15, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 44, +9, z - 15, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +9, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +9, z - 10, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 38, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +9, z - 10, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 42, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z - 10, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +9, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +9, z - 14, BlocksAether.inebriation_aercloud, 0);
		
		//floor 3 room --X --- ---____________________________________________________________________
		this.setBlockWithOffset(x - 44, +19, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 16, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 44, +19, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 12, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +19, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		
		//floor 3 room -X- --- --- __________________________________________________________________
		this.setBlockWithOffset(x - 44, +19, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +19, z - 2, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 43, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z - 2, BlocksAether.inebriation_aercloud, 2);		
		this.setBlockWithOffset(x - 44, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +19, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 2, BlocksAether.inebriation_aercloud, 2);
		this.setBlockWithOffset(x - 38, +19, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		
		//floor 3 room X-- --- ---   
		this.setBlockWithOffset(x - 38, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 39, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 15, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 38, +19, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 38, +19, z - 10, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 38, +19, z - 10, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 39, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 40, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 41, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 42, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 43, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 10, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 44, +19, z - 10, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 44, +19, z - 14, BlocksAether.inebriation_aercloud, 0);
		
		//________________________________________________________________________________________
		//floor 1 room --- -X- ---  ______________________________________________________________Middle Rooms
		this.setBlockWithOffset(x - 58, +0, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z - 2, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 52, +0, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +0, z + 4, BlocksAether.inebriation_aercloud, 0);
		
		//floor 1 room --- X-- ---   
		this.setBlockWithOffset(x - 53, +0, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +0, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +0, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +0, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +0, z - 16, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 58, +0, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 16, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 53, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z - 11, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 53, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +0, z - 11, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 58, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 11, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 59, +0, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z - 15, BlocksAether.inebriation_aercloud, 0);
		
		//floor 1 room --- --X --- 
		this.setBlockWithOffset(x - 59, +0, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +0, z + 18, BlocksAether.inebriation_aercloud, 0);	
		
		this.setBlockWithOffset(x - 59, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 12, BlocksAether.inebriation_aercloud, 0);
		
		this.setBlockWithOffset(x - 59, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 58, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 57, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 56, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 55, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 54, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		this.setBlockWithOffset(x - 53, +0, z + 18, BlocksAether.inebriation_aercloud, 1);
		
		this.setBlockWithOffset(x - 53, +0, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +0, z + 18, BlocksAether.inebriation_aercloud, 0);
		
		//floor 2 room --- -X- ---
		this.setBlockWithOffset(x - 58, +9, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z - 2, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 52, +9, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +9, z + 4, BlocksAether.inebriation_aercloud, 0);
		
		//floor 2 room --- X-- ---   
		this.setBlockWithOffset(x - 53, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 16, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 53, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z - 11, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 53, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 11, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 59, +9, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z - 15, BlocksAether.inebriation_aercloud, 0);
		
		//floor 2 room --- --X --- 
		this.setBlockWithOffset(x - 59, +9, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +9, z + 18, BlocksAether.inebriation_aercloud, 0);			
		this.setBlockWithOffset(x - 59, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 12, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 59, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 53, +9, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +9, z + 18, BlocksAether.inebriation_aercloud, 0);
		
		//floor 3 room --- -X- ---
		this.setBlockWithOffset(x - 58, +19, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z - 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z - 2, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 58, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z + 4, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 52, +19, z - 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z + 1, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z + 2, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z + 3, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 52, +19, z + 4, BlocksAether.inebriation_aercloud, 0);
				
		//floor 3 room --- X-- ---   
		this.setBlockWithOffset(x - 53, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z - 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 16, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 53, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z - 11, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 53, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 11, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 59, +19, z - 11, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z - 15, BlocksAether.inebriation_aercloud, 0);
				
		//floor 3 room --- --X --- 
		this.setBlockWithOffset(x - 59, +19, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 59, +19, z + 18, BlocksAether.inebriation_aercloud, 0);			
		this.setBlockWithOffset(x - 59, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z + 12, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 12, BlocksAether.inebriation_aercloud, 0);	
		this.setBlockWithOffset(x - 59, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 58, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 57, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 56, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 55, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 54, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 18, BlocksAether.inebriation_aercloud, 0);		
		this.setBlockWithOffset(x - 53, +19, z + 13, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 14, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 15, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 16, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 17, BlocksAether.inebriation_aercloud, 0);
		this.setBlockWithOffset(x - 53, +19, z + 18, BlocksAether.inebriation_aercloud, 0);
		
		//________________________________________________________________________________________
		//floor 1 room --- --- -X-  ______________________________________________________________-X Rooms
		
		return true;	
					
	}

	public void generateVoidSapling(int x, int y, int z) {
		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 2);
		this.addPlaneY(x, y, z, 3, 3); 

		this.setBlockWithOffset(x + 1, y, z + 1, BlocksAether.budding_auralite, 0);
		this.setBlockWithOffset(x + 1, y + 1, z + 1, BlocksAether.auralite_cluster_2, 7);

		for (int lineX = x; lineX < x + 3; lineX += 4) {
			for (int lineZ = z; lineZ < z + 6; lineZ += 4) {
				this.setBlockWithOffset(lineX, y + 1, lineZ, BlocksAether.ethereal_torch, 0);
			}
		}
	}

	public void generateChandelier(int x, int y, int z, int height) {
		for (int lineY = y + (height + 3); lineY < y + (height + 12); lineY++) {
			this.setBlockWithOffset(x, lineY + 2, z, BlocksAether.auralite_pillar, 0);
		}

		for (int lineX = (x - 1); lineX < (x + 2); lineX++) {
			this.setBlockWithOffset(lineX, y + (height + 3), z, BlocksAether.arctic_glowstone, 0);
		}

		for (int lineY = (y + height); lineY < y + (height + 3); lineY++) {
			this.setBlockWithOffset(x, lineY + 2, z, BlocksAether.arctic_glowstone, 0);
		}

		for (int lineZ = (z - 1); lineZ < (z + 2); lineZ++) {
			this.setBlockWithOffset(x, y + (height + 3), lineZ, BlocksAether.arctic_glowstone, 0);
		}
	}

	public void generateColumn(int x, int y, int z, int yRange) {
		this.setBlocks(LOCKED_FUSE_STONE, LOCKED_CREEPING_STONE, 40);
		this.addPlaneY(x, y, z, 6, 6); 
		this.addPlaneY(x, y + 1 + yRange, z, 6, 6); 
		this.setBlocks(BlocksAether.carved_caelestia_stone_wall, BlocksAether.carved_caelestia_stone_wall, 1);
		this.addLineY(x + 2, y, z + 2, yRange);
		this.setBlockWithOffset(x + 2, y + (yRange), z + 2, BlocksAether.ambrosium_block, 0);
	}
	
	public void generateColumn2(int x, int y, int z, int yRange) {
		this.setBlocks(LOCKED_FUSE_STONE, FUSE_TRAP_2, 40);
		this.addPlaneY(x, y, z, 6, 6); 
		this.addPlaneY(x, y + 1 + yRange, z, 6, 6); 
		this.setBlocks(BlocksAether.carved_caelestia_stone, BlocksAether.carved_caelestia_stone, 1);
		this.addLineY(x + 2, y, z + 2, yRange);
		this.setBlockWithOffset(x + 2, y + (yRange), z + 2, BlocksAether.caelestia_stone, 0);
	}

	public void generateDoorX(int x, int y, int z, int yF, int zF) {
		for (int yFinal = y; yFinal < y + yF; yFinal++) {
			for (int zFinal = z; zFinal < z + zF; zFinal++) {
				this.setBlockWithOffset(x, yFinal, zFinal, Blocks.air, 0);
			}
		}
	}

	public void generateDoorZ(int z, int x, int y, int xF, int yF) {
		for (int xFinal = x; xFinal < x + xF; xFinal++) {
			for (int yFinal = y; yFinal < y + yF; yFinal++) {
				this.setBlockWithOffset(xFinal, yFinal, z, Blocks.air, 0);
			}
		}
	}

	//Get loot for normal chests scattered around
	private ItemStack getNormalLoot(Random random) {
		int item = random.nextInt(16);
		switch (item) {
			case 0:
				return new ItemStack(ItemsAether.arkenium_pickaxe);
			case 1:
				return new ItemStack(ItemsAether.skyroot_bucket, 1, 3);
			case 2:
				return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(10) + 1);
			case 3:
				return new ItemStack(ItemsAether.lightning_knife, random.nextInt(4) + 1);
			case 4:
				return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(20) + 4);
			case 5:
				return new ItemStack(ItemsAether.arkenium_sword);
			case 6:
				return new ItemStack(ItemsAether.arkenium_fragement, random.nextInt(3) + 2);
			case 7: {
				if (random.nextInt(20) == 0)
					return new ItemStack(ItemsAether.gravitite_pickaxe);
				break;
			}
			case 8:
				return new ItemStack(BlocksAether.enchanted_holystone, random.nextInt(30) + 20);
			case 9: {
				if (random.nextInt(10) == 0)
					return new ItemStack(ItemsAether.ascending_dawn);
				break;
			}			
			case 10: {
				if (random.nextInt(4) == 0)
					return new ItemStack(ItemsAether.gravitite_pendant);
			}
			case 11: {
				if (random.nextInt(10) == 0)
					return new ItemStack(BlocksAether.enchanted_agiosite, random.nextInt(10) + 20);
			}
			case 12: {
				if (random.nextInt(10) == 0)
					return new ItemStack(BlocksAether.aerogel, random.nextInt(1) + 1);
			}
		}

		return new ItemStack(ItemsAether.arkenium_fragement, random.nextInt(3) + 2);
	}

	public static ItemStack getOsmiumLoot(Random random) {
		int item = random.nextInt(17);

		switch (item) {
			case 0:
				return new ItemStack(BlocksAether.enchanted_gravitite, random.nextInt(4) + 1, random.nextInt(2));
			case 1:
				return new ItemStack(ItemsAether.valkyrie_ring);
			case 2: {
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_axe);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_shovel);
				if (random.nextBoolean())
					return new ItemStack(ItemsAether.tipped_valkyrie_pickaxe);
				break;
			}
			case 3:
				return new ItemStack(ItemsAether.dart_shooter, 0, 3);
			case 4:
				return new ItemStack(ItemsAether.bone_ring);
			case 5:
				return new ItemStack(ItemsAether.reinforced_regeneration_stone);
			case 6: {
				return new ItemStack(ItemsAether.aer_cape);
			}
			case 7: {
				return new ItemStack(ItemsAether.false_wings);
			}
			case 8:
				return new ItemStack(ItemsAether.haste_ring);
			case 9:
				return new ItemStack(ItemsAether.invisibility_cape);
			case 10:
				return new ItemStack(ItemsAether.divine_essence, random.nextInt(3) + 4);
			case 11:
				return new ItemStack(ItemsAether.divineral_pendant);
			case 12:
				return new ItemStack(ItemsAether.auralite_crystal, random.nextInt(3) + 4);
			case 13:
				return new ItemStack(ItemsAether.agility_boots);
			case 14:
				return new ItemStack(ItemsAether.dexterity_shard);
			case 15:
				return new ItemStack(ItemsAether.discharge_cape);
		}
		return new ItemStack(ItemsAether.arkenium_chunk, random.nextInt(12) + 10);
	}
}