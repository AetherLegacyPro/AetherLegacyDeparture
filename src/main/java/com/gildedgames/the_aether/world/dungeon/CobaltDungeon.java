package com.gildedgames.the_aether.world.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.gildedgames.the_aether.entities.bosses.cyro_guardian.EntityCyroGuardian;
import com.gildedgames.the_aether.entities.hostile.EntityZarnillys;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.world.util.RandomTracker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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

    //Offset from the boss room the next room above it should be placed
    private static final int VERTICAL_ENTRY_ROOM_OFFSET_Y = 14;
    private static final int VERTICAL_NEXT_ROOM_OFFSET_Y = 14;

    //Vertical Shaft Sizes
    private static final int VERTICAL_SHAFT_SIZE = 4;
    private static final int VERTICAL_SHAFT_INNER_OFFSET = 1;
    private static final int VERTICAL_SHAFT_INNER_SIZE = 2;

    //Hallway Sizes
    private static final int HORIZONTAL_PATH_SIZE = 4;
    private static final int HORIZONTAL_PATH_INNER_OFFSET = 1;
    private static final int HORIZONTAL_PATH_INNER_SIZE = 2;
    private static final int HORIZONTAL_PATH_Y_OFFSET = 1;

    //Standard Room Heights
    private static final int ENTRY_ROOM_HEIGHT = 10;
    private static final int NEXT_ROOM_HEIGHT = 6;
    private static final int BOSS_ROOM_TOP_OFFSET_Y = 10;

    //Horizontal Rooms
    private static final int MAX_BRANCH_ROOMS_PER_FLOOR = 2;
    private static final int HORIZONTAL_ROOM_SPACING = 16;
    private static final int HORIZONTAL_PATH_LENGTH = 4;

    private static final Block ROOM_CONNECTION_BLOCK = BlocksAether.frozen_quicksoil_glass;

    //Direction constants for the placement of the hallways and chests and such
    private static final int DOOR_WEST = 0;
    private static final int DOOR_NORTH = 1;
    private static final int DOOR_EAST = 2;
    private static final int DOOR_SOUTH = 3;

    //Additional Chest Chances
    private static final int SECOND_CHEST_CHANCE = 3;
    private static final int THIRD_CHEST_CHANCE = 8;

    public CobaltDungeon() {
        this.needsCorridor = false;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        replaceAir = true;
        replaceSolid = true;

        this.roomMaximum = random.nextInt(3) + 3;
        this.roomCount = 0;
        this.needsCorridor = false;

        this.generateBossRoom(world, random, i, j, k);

        return true;
    }

    public boolean generateBossRoom(World world, Random random, int i, int j, int k) {
        if (!isBoxSolid(world, new PositionData(i, j - 3, k), new PositionData(16, 16, 16)) || !isBoxSolid(world, new PositionData(i + 2, j + VERTICAL_ENTRY_ROOM_OFFSET_Y, k + 2), new PositionData(12, 10, 12))) {
            return false;
        }

        RandomTracker randomTracker = new RandomTracker();
        if (randomTracker.testRandom(random, 15) != 0) {
            if (randomTracker.testRandom(random, 40) != 0) {
                return false;
            }
        }

        setBlocks(this.lockedBlock(), this.lockedLightBlock(), 20);

        addHollowBox(world, random, new PositionData(i - 1, j - 1, k - 1), new PositionData(17, 10, 17));
        addHollowBox(world, random, new PositionData(i, j, k), new PositionData(17, 11, 17));
        addHollowBox(world, random, new PositionData(i, j, k), new PositionData(16, 10, 16));

        addHollowBox(world, random, new PositionData(i + 6, j - 2, k + 6), new PositionData(4, 3, 4));

        //world.setBlock(i + 8, j + 1, k + 8, ROOM_CENTER_MARKER_BLOCK);

        EntityCyroGuardian cyro = new EntityCyroGuardian(world);

        double bossX = i + 8.0D;
        double bossY = j + 4.0D;
        double bossZ = k + 8.0D;

        cyro.setPosition(bossX, bossY, bossZ);
        cyro.setResetPosition(bossX, bossY, bossZ);
        cyro.setDungeonBounds(i - 1, j - 2, k - 1, i + 16, j + 10, k + 16);

        if (!world.isRemote) {
            world.spawnEntityInWorld(cyro);
        }

        world.setBlock(i + 1, j + 8, k + 1, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner3 = (TileEntityMobSpawner)world.getTileEntity(i + 1, j + 8, k + 1);

        if (tileentitymobspawner3 != null) {
            tileentitymobspawner3.func_145881_a().setEntityName("aether_legacy.cyro");
        }

        world.setBlock(i + 9, j + 8, k + 9, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner4 = (TileEntityMobSpawner)world.getTileEntity(i + 9, j + 8, k + 9);

        if (tileentitymobspawner4 != null) {
            tileentitymobspawner4.func_145881_a().setEntityName("aether_legacy.cyro");
        }

        world.setBlock(i, j, k, setRandomBlock(world, random));

        world.setBlock(i + 7, j, k + 7, BlocksAether.icestone);
        world.setBlock(i + 7, j + 1, k + 7, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 14, j, k + 7, BlocksAether.icestone);
        world.setBlock(i + 14, j + 1, k + 7, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 7, j, k + 14, BlocksAether.icestone);
        world.setBlock(i + 7, j + 1, k + 14, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 14, j, k + 14, BlocksAether.icestone);
        world.setBlock(i + 14, j + 1, k + 14, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 14, j, k + 1, BlocksAether.icestone);
        world.setBlock(i + 14, j + 1, k + 1, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 1, j, k + 14, BlocksAether.icestone);
        world.setBlock(i + 1, j + 1, k + 14, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 1, j, k + 7, BlocksAether.icestone);
        world.setBlock(i + 1, j + 1, k + 7, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 1, j, k + 1, BlocksAether.icestone);
        world.setBlock(i + 1, j + 1, k + 1, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 7, j, k + 1, BlocksAether.icestone);
        world.setBlock(i + 7, j + 1, k + 1, BlocksAether.coldfire, 0, 3);

        world.setBlock(i + 7, j + 5, k + 1, BlocksAether.treasure_chest, 3, 2);
        world.setBlock(i + 7, j + 4, k + 1, BlocksAether.oblitus_stone);
        world.setBlock(i + 7, j + 4, k + 2, BlocksAether.oblitus_stone_slab, 8, 2);
        world.setBlock(i + 8, j + 4, k + 1, BlocksAether.oblitus_stone_slab, 8, 2);
        world.setBlock(i + 6, j + 4, k + 1, BlocksAether.oblitus_stone_slab, 8, 2);

        this.generateEmptyRoom(world, random, i, j, k, cyro);

        return true;
    }

    //Main room before boss room
    public boolean generateEmptyRoom(World world, Random random, int i, int j, int k, EntityCyroGuardian cyro) {
        int x = i + 2;
        int y = j + VERTICAL_ENTRY_ROOM_OFFSET_Y;
        int z = k + 2;

        int bossDoorY = j + BOSS_ROOM_TOP_OFFSET_Y - 2;

        int[] shaft = this.getRandomRoomEdgeShaft(random, x, z);
        int shaftX = shaft[0];
        int shaftZ = shaft[1];
        int shaftSide = shaft[2];

        cyro.setDoorBounds(shaftX + VERTICAL_SHAFT_INNER_OFFSET, bossDoorY, shaftZ + VERTICAL_SHAFT_INNER_OFFSET, shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE - 1, bossDoorY, shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE - 1);

        for (int doorX = shaftX + VERTICAL_SHAFT_INNER_OFFSET; doorX < shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; doorX++) {
            for (int doorZ = shaftZ + VERTICAL_SHAFT_INNER_OFFSET; doorZ < shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; doorZ++) {
                world.setBlockToAir(doorX, bossDoorY, doorZ);
            }
        }

        if (!isBoxSolid(world, new PositionData(x, y, z), new PositionData(12, 10, 12))) {
            return true;
        }

        setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
        addHollowBox(world, random, new PositionData(x, y, z), new PositionData(12, 10, 12));

        EntityZarnillys zarnillys = new EntityZarnillys(world);
        zarnillys.setPosition(x + 8, y + 2, z + 8);

        if (!world.isRemote) {
            world.spawnEntityInWorld(zarnillys);
        }

        world.setBlock(x + 7, y + 8, z + 7, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 7, y + 7, z + 7, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 7, z + 7);

        if (tileentitymobspawner != null) {
            int mob_chance = (int) (1 + Math.random() * 5);
            if (mob_chance < 3) {
                tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cyro");
            } else {
                tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cockatrice");
            }
        }

        //Randomly replace regular oblitus stone with the trapped type
        for (int p = x + 2; p < x + 10; p += 3) {
            for (int q = z + 2; q < z + 10; q += 3) {
                world.setBlock(p, y, q, setRandomBlock(world, random));
                world.setBlock(p, y, q, BlocksAether.cracked_oblitus_stone);
            }
        }

        //This is the connection to the boss room below
        setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

        world.setBlock(x + 7, y, z + 7, BlocksAether.icestone);
        world.setBlock(x + 7, y + 1, z + 7, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 7, y, z + 4, BlocksAether.icestone);
        world.setBlock(x + 7, y + 1, z + 4, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 4, y, z + 7, BlocksAether.icestone);
        world.setBlock(x + 4, y + 1, z + 7, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 4, y, z + 4, BlocksAether.icestone);
        world.setBlock(x + 4, y + 1, z + 4, BlocksAether.coldfire, 0, 3);

        this.addVerticalPathBetweenRooms(world, random, shaftX, shaftZ, j + BOSS_ROOM_TOP_OFFSET_Y - 1, y, true);

        //Branch horizontal rooms on 2nd floor
        this.generateHorizontalBranchesForFloor(world, random, x, y, z, shaftSide);

        if ((!determineRoomPosition(world, random, new PositionData(x, y, z)) && this.roomCount == 0)) {
            return false;
        }

        if (this.needsCorridor) {
            this.endCorridor(world, random, new PositionData(x, y, z));
        }

        return true;
    }

    public boolean determineRoomPosition(World world, Random random, PositionData pos) {
        if (this.roomCount >= this.roomMaximum) {
            this.needsCorridor = true;
            return this.endCorridor(world, random, pos);
        }

        //Makes sure there is a path to the boss room
        if (this.generateRoomWithSide(world, random, pos, 0)) {
            return true;
        }

        this.needsCorridor = true;
        return this.endCorridor(world, random, pos);
    }

    public boolean generateRoomWithSide(World world, Random random, PositionData pos, int switchCase) {
        int x = pos.getX();
        int y = pos.getY() + VERTICAL_NEXT_ROOM_OFFSET_Y;
        int z = pos.getZ();

        return this.generateNextRoom(world, random, new PositionData(x, y, z), 4);
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

        int shaftX = x + 3;
        int shaftZ = z + 3;
        int shaftSide = DOOR_NORTH;

        if (dir == 4) {
            int[] shaft = this.getRandomRoomEdgeShaft(random, x, z);
            shaftX = shaft[0];
            shaftZ = shaft[1];
            shaftSide = shaft[2];
        }

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
                world.setBlock(p, y, q, setRandomBlock(world, random));
                world.setBlock(p, y, q, BlocksAether.cracked_oblitus_stone);
            }
        }

        //Places the chest and stand opposite side of the vertical path otherwise opposite the one that is horizontal
        int[] platform = this.getOppositePlatformStart(x, z, shaftSide);
        int platformX = platform[0];
        int platformZ = platform[1];

        this.addChestPlatform(world, random, platformX, y + 1, platformZ);

        this.placeRandomRoomChests(world, random, platformX, y + 1, platformZ, shaftSide);

        if (random.nextInt(2) == 0) {
            this.generateTreasureRoomDecor(world, random, x, y, z);
        }

        if (dir == 4) {
            setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

            int previousRoomY = y - VERTICAL_NEXT_ROOM_OFFSET_Y;
            int previousRoomHeight = this.roomCount == 0 ? ENTRY_ROOM_HEIGHT : NEXT_ROOM_HEIGHT;
            int previousRoomTopY = previousRoomY + previousRoomHeight - 1;

            this.addVerticalPathBetweenRooms(world, random, shaftX, shaftZ, previousRoomTopY, y, false);
        }

        this.generateHorizontalBranchesForFloor(world, random, x, y, z, shaftSide);

        this.roomCount++;

        if (!determineRoomPosition(world, random, new PositionData(x, y, z))) {
            return false;
        }

        return true;
    }

    private void generateHorizontalBranchesForFloor(World world, Random random, int roomX, int roomY, int roomZ, int shaftSide) {
        ArrayList<Integer> sides = new ArrayList<Integer>();
        sides.add(DOOR_WEST);
        sides.add(DOOR_NORTH);
        sides.add(DOOR_EAST);
        sides.add(DOOR_SOUTH);
        sides.remove(Integer.valueOf(shaftSide));

        Collections.shuffle(sides, random);

        int branchCount = random.nextInt(MAX_BRANCH_ROOMS_PER_FLOOR + 1);
        for (int index = 0; index < sides.size() && branchCount > 0; index++) {
            int side = sides.get(index);

            if (this.generateHorizontalBranchRoom(world, random, roomX, roomY, roomZ, side)) {
                branchCount--;
            }
        }
    }

    private boolean generateHorizontalBranchRoom(World world, Random random, int roomX, int roomY, int roomZ, int side) {
        int branchX = roomX;
        int branchZ = roomZ;

        if (side == DOOR_WEST) {
            branchX = roomX - HORIZONTAL_ROOM_SPACING;
        } else if (side == DOOR_EAST) {
            branchX = roomX + HORIZONTAL_ROOM_SPACING;
        } else if (side == DOOR_NORTH) {
            branchZ = roomZ - HORIZONTAL_ROOM_SPACING;
        } else if (side == DOOR_SOUTH) {
            branchZ = roomZ + HORIZONTAL_ROOM_SPACING;
        }

        if (!isBoxSolid(world, new PositionData(branchX, roomY, branchZ), new PositionData(12, 6, 12))) {
            return false;
        }

        setBlocks(this.mainBlock(), this.mainLightBlock(), 20);
        addHollowBox(world, random, new PositionData(branchX, roomY, branchZ), new PositionData(12, 6, 12));

        int spawner_chance = (int) (1 + Math.random() * 8);
        if (spawner_chance <= 4) {
            int chance_additional_loot = (int)(1 + Math.random() * 4);
            switch (chance_additional_loot) {
                case 1:
                    world.setBlock(branchX + 2, roomY + 5, branchZ + 2, BlocksAether.ambrosium_block);
                    break;
                case 2:
                    world.setBlock(branchX + 2, roomY + 5, branchZ + 2, BlocksAether.gravitite_ore);
                    break;
                case 3:
                    world.setBlock(branchX + 2, roomY + 5, branchZ + 2, BlocksAether.arkenium_ore);
                    break;
                case 4:
                    world.setBlock(branchX + 2, roomY + 5, branchZ + 2, BlocksAether.aerogel);
                    break;
            }
            world.setBlock(branchX + 2, roomY + 4, branchZ + 2, Blocks.mob_spawner);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(branchX + 2, roomY + 4, branchZ + 2);

            if (tileentitymobspawner != null) {
                int mob_chance = (int) (1 + Math.random() * 5);
                if (mob_chance < 3) {
                    tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cyro");
                } else {
                    tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cockatrice");
                }
            }
        }

        //The Branch room is connected from opposite side to center room
        int branchIncomingSide = this.getOppositeSide(side);

        //Adds the path between the rooms
        this.addHorizontalPathBetweenRooms(world, random, roomX, roomY, roomZ, branchX, branchZ, side);

        //Places Chest and Supporting blocks opposite to the entrance to the room
        this.generateSmallRoomLootAndPlatform(world, random, branchX, roomY, branchZ, branchIncomingSide);

        world.setBlock(branchX + 2, roomY, branchZ + 2, BlocksAether.icestone);
        world.setBlock(branchX + 2, roomY + 1, branchZ + 2, BlocksAether.coldfire, 0, 3);

        world.setBlock(branchX + 9, roomY, branchZ + 9, BlocksAether.icestone);
        world.setBlock(branchX + 9, roomY + 1, branchZ + 9, BlocksAether.coldfire, 0, 3);

        world.setBlock(branchX + 2, roomY, branchZ + 9, BlocksAether.icestone);
        world.setBlock(branchX + 2, roomY + 1, branchZ + 9, BlocksAether.coldfire, 0, 3);

        world.setBlock(branchX + 9, roomY, branchZ + 2, BlocksAether.icestone);
        world.setBlock(branchX + 9, roomY + 1, branchZ + 2, BlocksAether.coldfire, 0, 3);

        return true;
    }

    private int getOppositeSide(int side) {
        if (side == DOOR_WEST) {
            return DOOR_EAST;
        }

        if (side == DOOR_EAST) {
            return DOOR_WEST;
        }

        if (side == DOOR_NORTH) {
            return DOOR_SOUTH;
        }

        return DOOR_NORTH;
    }

    private void generateSmallRoomLootAndPlatform(World world, Random random, int x, int y, int z, int connectionSide) {
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
                world.setBlock(p, y, q, setRandomBlock(world, random));
                world.setBlock(p, y, q, BlocksAether.cracked_oblitus_stone);
            }
        }

        int[] platform = this.getOppositePlatformStart(x, z, connectionSide);
        int platformX = platform[0];
        int platformZ = platform[1];

        this.addChestPlatform(world, random, platformX, y + 1, platformZ);
        this.placeRandomRoomChests(world, random, platformX, y + 1, platformZ, connectionSide);
    }

    private void addHorizontalPathBetweenRooms(World world, Random random, int roomX, int roomY, int roomZ, int branchX, int branchZ, int side) {
        setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

        if (side == DOOR_EAST) {
            this.addHorizontalTubeX(world, random, new PositionData(roomX + 12, roomY + HORIZONTAL_PATH_Y_OFFSET, roomZ + 4), HORIZONTAL_PATH_LENGTH);

            this.clearHorizontalRoomOpening(world, roomX, roomY - 1, roomZ, DOOR_EAST);
            this.clearHorizontalRoomOpening(world, branchX, roomY - 1, branchZ, DOOR_WEST);
        } else if (side == DOOR_WEST) {
            this.addHorizontalTubeX(world, random, new PositionData(roomX - HORIZONTAL_PATH_LENGTH, roomY + HORIZONTAL_PATH_Y_OFFSET, roomZ + 4), HORIZONTAL_PATH_LENGTH);

            this.clearHorizontalRoomOpening(world, roomX, roomY - 1, roomZ, DOOR_WEST);
            this.clearHorizontalRoomOpening(world, branchX, roomY - 1, branchZ, DOOR_EAST);
        } else if (side == DOOR_SOUTH) {
            this.addHorizontalTubeZ(world, random, new PositionData(roomX + 4, roomY + HORIZONTAL_PATH_Y_OFFSET, roomZ + 12), HORIZONTAL_PATH_LENGTH);

            this.clearHorizontalRoomOpening(world, roomX, roomY - 1, roomZ, DOOR_SOUTH);
            this.clearHorizontalRoomOpening(world, branchX, roomY - 1, branchZ, DOOR_NORTH);
        } else if (side == DOOR_NORTH) {
            this.addHorizontalTubeZ(world, random, new PositionData(roomX + 4, roomY + HORIZONTAL_PATH_Y_OFFSET, roomZ - HORIZONTAL_PATH_LENGTH), HORIZONTAL_PATH_LENGTH);

            this.clearHorizontalRoomOpening(world, roomX, roomY - 1, roomZ, DOOR_NORTH);
            this.clearHorizontalRoomOpening(world, branchX, roomY - 1, branchZ, DOOR_SOUTH);
        }
    }

    private void addHorizontalTubeX(World world, Random random, PositionData pos, int length) {
        int startX = pos.getX();
        int startY = pos.getY() - 1;
        int startZ = pos.getZ();

        for (int x = startX; x < startX + length; x++) {
            for (int y = startY; y < startY + HORIZONTAL_PATH_SIZE; y++) {
                for (int z = startZ; z < startZ + HORIZONTAL_PATH_SIZE; z++) {
                    boolean inner = y >= startY + HORIZONTAL_PATH_INNER_OFFSET && y < startY + HORIZONTAL_PATH_INNER_OFFSET + HORIZONTAL_PATH_INNER_SIZE && z >= startZ + HORIZONTAL_PATH_INNER_OFFSET && z < startZ + HORIZONTAL_PATH_INNER_OFFSET + HORIZONTAL_PATH_INNER_SIZE;
                    if (inner) {
                        world.setBlock(x, y, z, Blocks.air);
                    } else {
                        world.setBlock(x, y, z, random.nextInt(5) == 0 ? this.fillerBlock1() : this.fillerBlock());
                    }
                }
            }
        }
    }

    private void addHorizontalTubeZ(World world, Random random, PositionData pos, int length) {
        int startX = pos.getX();
        int startY = pos.getY() - 1;
        int startZ = pos.getZ();

        for (int x = startX; x < startX + HORIZONTAL_PATH_SIZE; x++) {
            for (int y = startY; y < startY + HORIZONTAL_PATH_SIZE; y++) {
                for (int z = startZ; z < startZ + length; z++) {
                    boolean inner = x >= startX + HORIZONTAL_PATH_INNER_OFFSET && x < startX + HORIZONTAL_PATH_INNER_OFFSET + HORIZONTAL_PATH_INNER_SIZE && y >= startY + HORIZONTAL_PATH_INNER_OFFSET && y < startY + HORIZONTAL_PATH_INNER_OFFSET + HORIZONTAL_PATH_INNER_SIZE;
                    if (inner) {
                        world.setBlock(x, y, z, Blocks.air);
                    } else {
                        world.setBlock(x, y, z, random.nextInt(5) == 0 ? this.fillerBlock1() : this.fillerBlock());
                    }
                }
            }
        }
    }

    private void generateTreasureRoomDecor(World world, Random random, int x, int y, int z) {
        // - -
        world.setBlock(x + 3, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 4, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 3, y + 1, z + 4, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 3, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 4, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 3, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);

        // + -
        world.setBlock(x + 8, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 7, y + 1, z + 3, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 8, y + 1, z + 4, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 8, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 7, y + 2, z + 3, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 8, y + 2, z + 4, BlocksAether.carved_caelestia_stone_wall);

        // - +
        world.setBlock(x + 3, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 3, y + 1, z + 7, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 4, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 3, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 3, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 4, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);

        // + +
        world.setBlock(x + 8, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 7, y + 1, z + 8, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 8, y + 1, z + 7, BlocksAether.carved_caelestia_stone);
        world.setBlock(x + 8, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 7, y + 2, z + 8, BlocksAether.carved_caelestia_stone_wall);
        world.setBlock(x + 8, y + 2, z + 7, BlocksAether.carved_caelestia_stone_wall);

        world.setBlock(x + 9, y, z + 9, BlocksAether.icestone);
        world.setBlock(x + 9, y + 1, z + 9, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 2, y, z + 2, BlocksAether.icestone);
        world.setBlock(x + 2, y + 1, z + 2, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 2, y, z + 9, BlocksAether.icestone);
        world.setBlock(x + 2, y + 1, z + 9, BlocksAether.coldfire, 0, 3);

        world.setBlock(x + 9, y, z + 2, BlocksAether.icestone);
        world.setBlock(x + 9, y + 1, z + 2, BlocksAether.coldfire, 0, 3);

        int chance_additional_loot = (int)(1 + Math.random() * 4);
        switch (chance_additional_loot) {
            case 1:
                world.setBlock(x + 7, y + 5, z + 7, BlocksAether.primeval_artifact);
                break;
            case 2:
                world.setBlock(x + 7, y + 5, z + 7, BlocksAether.gravitite_ore);
                break;
            case 3:
                world.setBlock(x + 7, y + 5, z + 7, BlocksAether.arkenium_ore);
                break;
            case 4:
                world.setBlock(x + 7, y + 5, z + 7, BlocksAether.aerogel);
                break;
        }

        world.setBlock(x + 7, y + 4, z + 7, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x + 7, y + 4, z + 7);

        if (tileentitymobspawner != null) {
            int mob_chance = (int) (1 + Math.random() * 5);
            if (mob_chance < 4) {
                tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cyro");
            } else {
                tileentitymobspawner.func_145881_a().setEntityName("aether_legacy.cockatrice");
            }
        }
    }

    private int getChestMetadataFacingPath(int pathSide) {
         //2 = north, 3 = south
         //4 = west, 5 = east
        if (pathSide == DOOR_WEST) {
            return 4;
        }

        if (pathSide == DOOR_EAST) {
            return 5;
        }

        if (pathSide == DOOR_NORTH) {
            return 2;
        }

        if (pathSide == DOOR_SOUTH) {
            return 3;
        }

        return 3;
    }

    private TileEntitySkyrootChest placeSkyrootChestFacingPath(World world, int x, int y, int z, int pathSide) {
        world.setBlock(x, y, z, BlocksAether.skyroot_chest);
        world.setBlockMetadataWithNotify(x, y, z, this.getChestMetadataFacingPath(pathSide), 2);

        return (TileEntitySkyrootChest)world.getTileEntity(x, y, z);
    }

    private void addChestPlatform(World world, Random random, int x, int y, int z) {
        for (int px = x; px < x + 4; px++) {
            for (int pz = z; pz < z + 4; pz++) {

                boolean edge = px == x || px == x + 3 || pz == z || pz == z + 3;
                if (edge) {
                    world.setBlock(px, y, pz, BlocksAether.carved_caelestia_stone);
                } else {
                    world.setBlock(px, y, pz, BlocksAether.caelestia_stone);
                }
            }
        }
    }

    //Makes path up to surface
    public boolean endCorridor(World world, Random random, PositionData pos) {
        if (!this.needsCorridor) {
            return false;
        }

        int[] shaft = this.getRandomRoomEdgeShaft(random, pos.getX(), pos.getZ());
        int shaftX = shaft[0];
        int shaftZ = shaft[1];

        //Top of the center room with the heighest y value
        int roomTopY = pos.getY() + NEXT_ROOM_HEIGHT - 1;

        //Makes sure it is open to sky and not in a cave or something
        int entranceAirY = this.getFirstSkyAirYAboveShaft(world, shaftX, roomTopY + 1, shaftZ);
        if (entranceAirY <= roomTopY) {
            entranceAirY = roomTopY + 1;
        }

        setBlocks(this.fillerBlock(), this.fillerBlock1(), 5);

        //Stops the tube up the moment it hits sky
        int tubeStartY = roomTopY + 1;
        int tubeHeight = entranceAirY - tubeStartY;

        if (tubeHeight > 0) {
            this.addVerticalTube(world, random, new PositionData(shaftX, tubeStartY, shaftZ), new PositionData(VERTICAL_SHAFT_SIZE, tubeHeight, VERTICAL_SHAFT_SIZE));
        }

        //Places the frozen quicksoil glass
        this.addGlassShaftLayer(world, shaftX, roomTopY + 4, shaftZ);

        for (int x = shaftX + VERTICAL_SHAFT_INNER_OFFSET; x < shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; x++) {
            for (int z = shaftZ + VERTICAL_SHAFT_INNER_OFFSET; z < shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; z++) {
                world.setBlock(x, entranceAirY, z, Blocks.air);
                world.setBlock(x, entranceAirY + 1, z, Blocks.air);
                world.setBlock(x, entranceAirY + 2, z, Blocks.air);
            }
        }

        this.needsCorridor = false;

        return true;
    }

    //Determines what side the vertical path is on
    private int[] getRandomRoomEdgeShaft(Random random, int roomX, int roomZ) {
        int maxOffset = 12 - VERTICAL_SHAFT_SIZE;
        int offset = random.nextInt(maxOffset + 1);
        int side = random.nextInt(4);

        int shaftX;
        int shaftZ;

        if (side == DOOR_WEST) {
            shaftX = roomX;
            shaftZ = roomZ + offset;
        } else if (side == DOOR_EAST) {
            shaftX = roomX + maxOffset;
            shaftZ = roomZ + offset;
        } else if (side == DOOR_NORTH) {
            shaftX = roomX + offset;
            shaftZ = roomZ;
        } else {
            shaftX = roomX + offset;
            shaftZ = roomZ + maxOffset;
        }

        return new int[] {shaftX, shaftZ, side};
    }

    //Place decor and chests opposite side of the vertical room and otherwise opposite the horizontal path
    private int[] getOppositePlatformStart(int roomX, int roomZ, int shaftSide) {
        int platformX = roomX + 4;
        int platformZ = roomZ + 4;

        if (shaftSide == DOOR_WEST) {
            platformX = roomX + 6;
            platformZ = roomZ + 4;
        } else if (shaftSide == DOOR_EAST) {
            platformX = roomX + 2;
            platformZ = roomZ + 4;
        } else if (shaftSide == DOOR_NORTH) {
            platformX = roomX + 4;
            platformZ = roomZ + 6;
        } else if (shaftSide == DOOR_SOUTH) {
            platformX = roomX + 4;
            platformZ = roomZ + 2;
        }

        return new int[] {platformX, platformZ};
    }

    private void addGlassShaftLayer(World world, int shaftX, int y, int shaftZ) {
        for (int x = shaftX + VERTICAL_SHAFT_INNER_OFFSET; x < shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; x++) {
            for (int z = shaftZ + VERTICAL_SHAFT_INNER_OFFSET; z < shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; z++) {
                world.setBlock(x, y - VERTICAL_SHAFT_SIZE, z, ROOM_CONNECTION_BLOCK);
            }
        }
    }

    //Creates the vertical paths between the rooms
    private void addVerticalPathBetweenRooms(World world, Random random, int shaftX, int shaftZ, int lowerRoomTopY, int upperRoomBottomY, boolean lowerGlassDownOne) {
        int tubeStartY = lowerRoomTopY + 1;
        int tubeEndY = upperRoomBottomY - 1;

        if (tubeEndY >= tubeStartY) {
            this.addVerticalTube(world, random, new PositionData(shaftX, tubeStartY, shaftZ), new PositionData(VERTICAL_SHAFT_SIZE, tubeEndY - tubeStartY + 1, VERTICAL_SHAFT_SIZE));

            //Creates the air between the two rooms
            this.clearVerticalShaftInterior(world, shaftX, tubeStartY, shaftZ, tubeEndY - tubeStartY + 1);
        }

        this.clearVerticalShaftInterior(world, shaftX, lowerRoomTopY, shaftZ, 1);
        this.clearVerticalShaftInterior(world, shaftX, upperRoomBottomY, shaftZ, 1);
    }

    private void clearVerticalShaftInterior(World world, int startX, int startY, int startZ, int height) {
        for (int x = startX + VERTICAL_SHAFT_INNER_OFFSET; x < startX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; x++) {
            for (int y = startY; y < startY + height; y++) {
                for (int z = startZ + VERTICAL_SHAFT_INNER_OFFSET; z < startZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; z++) {
                    world.setBlock(x, y, z, Blocks.air);
                }
            }
        }
    }

    private boolean isShaftInteriorAirAt(World world, int shaftX, int y, int shaftZ) {
        for (int x = shaftX + VERTICAL_SHAFT_INNER_OFFSET; x < shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; x++) {
            for (int z = shaftZ + VERTICAL_SHAFT_INNER_OFFSET; z < shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; z++) {
                if (world.getBlock(x, y, z) != Blocks.air) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canShaftInteriorSeeSky(World world, int shaftX, int y, int shaftZ) {
        for (int x = shaftX + VERTICAL_SHAFT_INNER_OFFSET; x < shaftX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; x++) {
            for (int z = shaftZ + VERTICAL_SHAFT_INNER_OFFSET; z < shaftZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE; z++) {
                if (world.canBlockSeeTheSky(x, y, z)) {
                    return true;
                }
            }
        }

        return false;
    }

    private int getFirstSkyAirYAboveShaft(World world, int shaftX, int startY, int shaftZ) {
        for (int y = startY; y < 255; y++) {
            if (this.isShaftInteriorAirAt(world, shaftX, y, shaftZ) && this.canShaftInteriorSeeSky(world, shaftX, y, shaftZ)) {
                return y;
            }
        }

        //Doubt it will ever reach this height but added a catch just in case
        for (int y = startY; y < 255; y++) {
            if (this.isShaftInteriorAirAt(world, shaftX, y, shaftZ)) {
                return y;
            }
        }

        return 255;
    }

    private void clearHorizontalRoomOpening(World world, int roomX, int roomY, int roomZ, int side) {
        int y1 = roomY + HORIZONTAL_PATH_Y_OFFSET + HORIZONTAL_PATH_INNER_OFFSET;
        int y2 = y1 + HORIZONTAL_PATH_INNER_SIZE - 1;

        if (side == DOOR_EAST) {
            int x = roomX + 11;

            for (int y = y1; y <= y2; y++) {
                for (int z = roomZ + 5; z <= roomZ + 6; z++) {
                    world.setBlock(x, y, z, Blocks.air);
                }
            }
        } else if (side == DOOR_WEST) {
            int x = roomX;

            for (int y = y1; y <= y2; y++) {
                for (int z = roomZ + 5; z <= roomZ + 6; z++) {
                    world.setBlock(x, y, z, Blocks.air);
                }
            }
        } else if (side == DOOR_SOUTH) {
            int z = roomZ + 11;

            for (int y = y1; y <= y2; y++) {
                for (int x = roomX + 5; x <= roomX + 6; x++) {
                    world.setBlock(x, y, z, Blocks.air);
                }
            }
        } else if (side == DOOR_NORTH) {
            int z = roomZ;

            for (int y = y1; y <= y2; y++) {
                for (int x = roomX + 5; x <= roomX + 6; x++) {
                    world.setBlock(x, y, z, Blocks.air);
                }
            }
        }
    }

    private void addVerticalTube(World world, Random random, PositionData pos, PositionData size) {
        int startX = pos.getX();
        int startY = pos.getY();
        int startZ = pos.getZ();

        int sizeX = size.getX();
        int sizeY = size.getY();
        int sizeZ = size.getZ();

        for (int x = startX; x < startX + sizeX; x++) {
            for (int y = startY; y < startY + sizeY; y++) {
                for (int z = startZ; z < startZ + sizeZ; z++) {
                    boolean inner = x >= startX + VERTICAL_SHAFT_INNER_OFFSET && x < startX + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE && z >= startZ + VERTICAL_SHAFT_INNER_OFFSET && z < startZ + VERTICAL_SHAFT_INNER_OFFSET + VERTICAL_SHAFT_INNER_SIZE;
                    if (inner) {
                        world.setBlock(x, y, z, Blocks.air);
                    } else {
                        world.setBlock(x, y, z, random.nextInt(5) == 0 ? this.fillerBlock1() : this.fillerBlock());
                    }
                }
            }
        }
    }

    private int placeRandomRoomChests(World world, Random random, int platformX, int platformY, int platformZ, int pathSide) {
        int chestCount = 1;

        if (random.nextInt(SECOND_CHEST_CHANCE) == 0) {
            chestCount = 2;

            if (random.nextInt(THIRD_CHEST_CHANCE) == 0) {
                chestCount = 3;
            }
        }

        int chestY = platformY + 1;
        int placed = 0;

        ArrayList<int[]> centerPositions = new ArrayList<int[]>();

        centerPositions.add(new int[] { platformX + 1, platformZ + 1 });
        centerPositions.add(new int[] { platformX + 2, platformZ + 1 });
        centerPositions.add(new int[] { platformX + 1, platformZ + 2 });
        centerPositions.add(new int[] { platformX + 2, platformZ + 2 });

        Collections.shuffle(centerPositions, random);

        int centerChestTarget = Math.min(chestCount, 2);

        for (int index = 0; index < centerPositions.size() && placed < centerChestTarget; index++) {
            int[] position = centerPositions.get(index);
            if (this.placeAndFillRandomRoomChest(world, random, position[0], chestY, position[1], pathSide)) {
                placed++;
            }
        }

        if (chestCount >= 3 && placed >= 2) {
            ArrayList<int[]> platformPositions = new ArrayList<int[]>();

            for (int offsetX = 0; offsetX < 4; offsetX++) {
                for (int offsetZ = 0; offsetZ < 4; offsetZ++) {
                    platformPositions.add(new int[] {platformX + offsetX, platformZ + offsetZ});
                }
            }

            Collections.shuffle(platformPositions, random);

            for (int index = 0; index < platformPositions.size(); index++) {
                int[] position = platformPositions.get(index);

                if (this.placeAndFillRandomRoomChest(world, random, position[0], chestY, position[1], pathSide)) {
                    placed++;
                    break;
                }
            }
        }

        return placed;
    }

    private boolean placeAndFillRandomRoomChest(World world, Random random, int x, int y, int z, int pathSide) {
        if (world.getBlock(x, y, z) != Blocks.air) {
            return false;
        }

        //Stops Triple/Quad Chests from forming
        if (this.hasAdjacentSkyrootChest(world, x, y, z)) {
            return false;
        }

        TileEntitySkyrootChest chest = this.placeSkyrootChestFacingPath(world, x, y, z, pathSide);

        if (chest == null) {
            return false;
        }

        int lootRolls = 3 + random.nextInt(3);
        for (int roll = 0; roll < lootRolls; roll++) {
            ItemStack loot = this.getNormalLoot(random);

            if (loot != null) {
                chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), loot);
            }
        }

        return true;
    }

    private boolean hasAdjacentSkyrootChest(World world, int x, int y, int z) {
        return world.getBlock(x - 1, y, z) == BlocksAether.skyroot_chest || world.getBlock(x + 1, y, z) == BlocksAether.skyroot_chest
            || world.getBlock(x, y, z - 1) == BlocksAether.skyroot_chest || world.getBlock(x, y, z + 1) == BlocksAether.skyroot_chest;
    }

    private ItemStack getNormalLoot(Random random) {
        int item = random.nextInt(13);

        switch (item) {
            case 0:
                return new ItemStack(ItemsAether.golden_amber, random.nextInt(10) + 8);

            case 1:
                return new ItemStack(ItemsAether.orange, random.nextInt(4) + 1);

            case 2:
                return new ItemStack(ItemsAether.wynberry, random.nextInt(3) + 1);

            case 3:
                return new ItemStack(ItemsAether.arkenium_shovel);

            case 4:
                return new ItemStack(ItemsAether.grapes, random.nextInt(4) + 3);

            case 5:
                return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(25) + 1);

            case 6:
                return new ItemStack(ItemsAether.gummy_swet, random.nextInt(5) + 1);

            case 7:
                return new ItemStack(ItemsAether.dexterity_stone, random.nextInt(3) + 1);

            case 8:
                if (random.nextInt(3) == 0) {
                    return new ItemStack(ItemsAether.raw_gravitite, random.nextInt(1) + 1);
                }

            case 9:
                if (random.nextInt(15) == 0) {
                    return new ItemStack(ItemsAether.arkenium_ingot);
                }

                break;

            case 10:
                return new ItemStack(ItemsAether.skyroot_bucket, 1, 2);

            case 11:
                if (random.nextInt(10) == 0) {
                    return new ItemStack(ItemsAether.ice_pendant);
                }

                break;

            case 12:
                if (random.nextInt(5) == 0) {
                    return new ItemStack(ItemsAether.auralite_crystal);
                }

                break;
        }

        return new ItemStack(BlocksAether.oblitus_stone_2, random.nextInt(2) + 3);
    }

    public static ItemStack getCobaltLoot(Random random) {
        int item = random.nextInt(14);

        switch (item) {
            case 0:
                return new ItemStack(ItemsAether.tipped_arkenium_pickaxe);

            case 1:
                return new ItemStack(ItemsAether.raw_gravitite, random.nextInt(3) + 2);

            case 2:
                return new ItemStack(ItemsAether.tipped_arkenium_sword);

            case 3:
                return new ItemStack(BlocksAether.zanite_block, random.nextInt(3) + 1);

            case 4:
                return new ItemStack(ItemsAether.confractus_staff);

            case 5:
                return new ItemStack(ItemsAether.ambrosium_shard, random.nextInt(9) + 2);

            case 6:
                return new ItemStack(ItemsAether.arkenium_fragement, random.nextInt(4) + 3);

            case 7:
                return new ItemStack(BlocksAether.ambrosium_block, random.nextInt(9) + 2);

            case 8:
                return new ItemStack(BlocksAether.aerogel);

            case 9:
                return new ItemStack(ItemsAether.shears_of_agnes, 1);

            case 10:
                if (random.nextInt(8) == 0) {
                    return new ItemStack(ItemsAether.enchanted_divineral, 1);
                } else {
                    return new ItemStack(BlocksAether.zanite_block, random.nextInt(3) + 1);
                }

            case 11:
                return new ItemStack(ItemsAether.cyro_bow, 1);
        }

        return new ItemStack(ItemsAether.zanite_gemstone, random.nextInt(15) + 15);
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
        return BlocksAether.cracked_oblitus_stone_2;
    }

    public Block mainBlock() {
        return BlocksAether.oblitus_stone_2;
    }

    public Block fillerBlock() {
        return BlocksAether.frozen_quicksoil;
    }

    public Block fillerBlock1() {
        return BlocksAether.quicksoil;
    }
}
