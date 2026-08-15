package com.gildedgames.the_aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLuminousStone extends Block {

	public BlockLuminousStone() {
		super(Material.glass);
		this.setHardness(1F);
		this.setStepSound(soundTypeGlass);
		this.setLightLevel(1.0F);
		this.setHarvestLevel("pickaxe", 0);
	}

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return face == ForgeDirection.UP;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 0;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        if (side == ForgeDirection.UP) {
            return true;
        }

        return super.isSideSolid(world, x, y, z, side);
    }

}
