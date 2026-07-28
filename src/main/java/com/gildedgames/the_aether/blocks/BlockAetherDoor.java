package com.gildedgames.the_aether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAetherDoor extends BlockDoor {

    public Item doorItem;

    public BlockAetherDoor() {
        super(Material.wood);
        this.setHardness(3.0F);
    }

    public void setDoorItem(Item doorItem) {
        this.doorItem = doorItem;
    }

    public Item getItemDropped(int i, Random random, int k) {
        return this.doorItem != null ? this.doorItem : ItemsAether.skyroot_door_item;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return this.doorItem != null ? this.doorItem : ItemsAether.skyroot_door_item;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int l = world.getBlockMetadata(x, y, z);
        if ((l & 8) == 0) {
            boolean flag = false;
            if (world.getBlock(x, y + 1, z) != this) {
                world.setBlockToAir(x, y, z);
                flag = true;
            }

            if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) {
                world.setBlockToAir(x, y, z);
                flag = true;
                if (world.getBlock(x, y + 1, z) == this) {
                    world.setBlockToAir(x, y + 1, z);
                }
            }

            if (world.isAirBlock(x, y - 1, z) && !world.isRemote) {
                this.dropBlockAsItem(world, x, y, z, l, 0);
            }

            if (!flag) {
                boolean flag1 = world.isBlockIndirectlyGettingPowered(x, y, z)
                    || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
                if ((flag1 || block.canProvidePower()) && block != this) {
                    this.func_150014_a(world, x, y, z, flag1);
                }
            }
        } else {
            if (world.getBlock(x, y - 1, z) != this) {
                world.setBlockToAir(x, y, z);
            }

            if (block != this) {
                this.onNeighborBlockChange(world, x, y - 1, z, block);
            }
        }

    }

}
