package com.gildedgames.the_aether.blocks;

import java.util.Random;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockIcestone extends Block {

    public BlockIcestone() {
        super(Material.ice);
        this.setHardness(1.2F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGlass);
        this.setHarvestLevel("pickaxe", 1);
        this.setBlockTextureName("aether_legacy:new_icestone");
    }

    @Override
    public void onBlockAdded(World world, int xIn, int yIn, int zIn) {
        for (int x = xIn - 3; x <= xIn + 3; ++x) {
            for (int y = yIn - 3; y <= yIn + 3; ++y) {
                for (int z = zIn - 3; z <= zIn + 3; ++z) {
                    Block block = world.getBlock(x, y, z);

                    if (block == Blocks.water || block == Blocks.flowing_water) {
                        world.setBlock(x, y, z, Blocks.ice);
                    } else if (block == Blocks.lava || block == Blocks.flowing_lava) {
                        world.setBlock(x, y, z, Blocks.obsidian);
                    }
                }
            }
        }
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return MathHelper.clamp_int(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ItemsAether.icestone_crystal;
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
