package com.gildedgames.the_aether.blocks.natural;

import com.gildedgames.the_aether.CommonProxy;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.world.World;

public class BlockAetherDesertFlower extends BlockBush {

    public BlockAetherDesertFlower() {
        this.setHardness(0.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(0.5F - 0.2F, 0.0F, 0.5F - 0.2F, 0.5F + 0.2F, 0.2F * 3.0F, 0.5F + 0.2F);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        return soil == BlocksAether.quicksoil || soil == BlocksAether.frozen_quicksoil;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        return (soil != null && this.canPlaceBlockAt(world, x, y, z));
    }

    @Override
    public int getRenderType() {
        return CommonProxy.aetherFlowerRenderID;
    }

}
