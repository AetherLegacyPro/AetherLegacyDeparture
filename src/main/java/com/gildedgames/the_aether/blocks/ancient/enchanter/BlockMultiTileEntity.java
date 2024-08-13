package com.gildedgames.the_aether.blocks.ancient.enchanter;

import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;

public abstract class BlockMultiTileEntity extends BlockContainer
{
    protected AxisAlignedBB size;
    protected static final int TILE_ENTITY = 0;
    public static final int DUMMY_BLOCK = 1;
    public boolean useDefaultCollision;
    
    protected BlockMultiTileEntity(final Material material) {
        super(material);
        this.size = AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        this.useDefaultCollision = false;
    }
    
    public boolean isDummyBlock(final World world, final int x, final int y, final int z) {
        final int metadata = world.getBlockMetadata(x, y, z);
        return metadata == 1;
    }
    
    public boolean isTileEntity(final World world, final int x, final int y, final int z) {
        final int metadata = world.getBlockMetadata(x, y, z);
        return metadata == 0;
    }
    
    public boolean hasTileEntity(final int metadata) {
        return metadata == 0;
    }
    
    public TileEntityMultiBlock getLinkedTileEntity(final World world, final int x, final int y, final int z) {
        final int minX = x - this.getWidth();
        final int minY = y - this.getHeight();
        final int minZ = z - this.getLength();
        final int maxX = x + this.getWidth();
        final int maxY = y + this.getHeight();
        final int maxZ = z + this.getLength();
        for (int x2 = minX; x2 < maxX; ++x2) {
            for (int y2 = minY; y2 < maxY; ++y2) {
                for (int z2 = minZ; z2 < maxZ; ++z2) {
                    final TileEntity tile = world.getTileEntity(x2, y2, z2);
                    if (tile instanceof TileEntityMultiBlock tileEntity) {
						return tileEntity;
                    }
                }
            }
        }
        return null;
    }
    
    public int getWidth() {
        return (int)(this.size.maxX - this.size.minX + 1.0);
    }
    
    public int getHeight() {
        return (int)(this.size.maxY - this.size.minY + 1.0);
    }
    
    public int getLength() {
        return (int)(this.size.maxZ - this.size.minZ + 1.0);
    }
    
    public void setBlockSize(final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ) {
        this.size = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }
    
    public AxisAlignedBB getBlockSize() {
        return this.size;
    }
    
    public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entityliving, final ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entityliving, stack);
        final TileEntityMultiBlock tileEntity = (TileEntityMultiBlock)world.getTileEntity(x, y, z);
        Rotation rotation = null;
        tileEntity.setSize(this.size);
        if (entityliving != null) {
            final int direction = MathHelper.floor_double(entityliving.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
            if (direction == 0) {
                rotation = Rotation.SOUTH;
            }
            if (direction == 1) {
                rotation = Rotation.WEST;
            }
            if (direction == 2) {
                rotation = Rotation.NORTH;
            }
            if (direction == 3) {
                rotation = Rotation.EAST;
            }
            tileEntity.rotate(world, rotation, x, y, z);
        }
        if (world.getBlockMetadata(x, y, z) != 1) {
            if (rotation == null) {
                rotation = tileEntity.getCurrentRotation(world, x, y, z);
                tileEntity.setCurrentRotation(world, Rotation.NORTH, x, y, z);
                tileEntity.rotate(world, rotation, x, y, z);
            }
            final TileEntityMultiBlock.Action addMultiBlocks = new TileEntityMultiBlock.Action(this) {
                @Override
                public Object onAction(final World world, final int x, final int y, final int z, final Block blockID, final int blockMetadata) {
                    if (blockID != this.block && !(world.getTileEntity(x, y, z) instanceof TileEntityMultiBlock)) {
                        world.setBlock(x, y, z, this.block, 1, 2);
                    }
                    return null;
                }
                
                @Override
                public boolean shouldReturnObject(final World world, final int x, final int y, final int z) {
                    return false;
                }
            };
            tileEntity.iterateSize(addMultiBlocks, world);
            tileEntity.hasInit = true;
        }
    }
    
    public void onBlockAdded(final World world, final int x, final int y, final int z) {
        super.onBlockAdded(world, x, y, z);
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer entityplayer, final int par6, final float par7, final float par8, final float par9) {
        final TileEntityMultiBlock tileEntity = this.getLinkedTileEntity(world, x, y, z);
        if (tileEntity != null) {
            tileEntity.setSize(this.size);
            if (tileEntity.rotation == Rotation.WEST || tileEntity.rotation == Rotation.EAST) {
                tileEntity.rotate(world, true, x, y, z);
            }
            final int minX = MathHelper.floor_double(tileEntity.size.minX);
            final int minY = MathHelper.floor_double(tileEntity.size.minY);
            final int minZ = MathHelper.floor_double(tileEntity.size.minZ);
            final int maxX = MathHelper.floor_double(tileEntity.size.maxX);
            final int maxY = MathHelper.floor_double(tileEntity.size.maxY);
            final int maxZ = MathHelper.floor_double(tileEntity.size.maxZ);
            for (int x2 = minX; x2 < maxX; ++x2) {
                for (int y2 = minY; y2 < maxY; ++y2) {
                    for (int z2 = minZ; z2 < maxZ; ++z2) {
                        if (world.getBlock(x2, y2, z2).onBlockActivated(world, x2, y2, z2, entityplayer, par6, par7, par8, par9)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void breakBlock(final World world, final int x, final int y, final int z, final Block blockID, final int blockMetadata) {
        final TileEntityMultiBlock tileEntity = this.getLinkedTileEntity(world, x, y, z);
        if (tileEntity != null) {
            tileEntity.setSize(this.size);
            if (tileEntity.rotation == Rotation.WEST || tileEntity.rotation == Rotation.EAST) {
                tileEntity.rotate(world, true, x, y, z);
            }
            final TileEntityMultiBlock.Action destroyMultiBlocks = new TileEntityMultiBlock.Action(this) {
                @Override
                public Object onAction(final World world, final int x, final int y, final int z, final Block blockID, final int blockMetadata) {
                    if (blockID == BlockMultiTileEntity.this) {
                        world.setBlockToAir(x, y, z);
                    }
                    return null;
                }
                
                @Override
                public boolean shouldReturnObject(final World world, final int x, final int y, final int z) {
                    return false;
                }
            };
            tileEntity.iterateSize(destroyMultiBlocks, world);
        }
        super.breakBlock(world, x, y, z, blockID, blockMetadata);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        if (this.useDefaultCollision) {
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityMultiBlock multiTileEntity) {
			final AxisAlignedBB blockSize = multiTileEntity.getSize();
            return this.isDummyBlock(world, x, y, z) ? super.getCollisionBoundingBoxFromPool(world, x, y, z) : blockSize;
        }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    public final TileEntity createNewTileEntity(final World world, final int meta) {
        final TileEntityMultiBlock tileEntity = this.createMultiTileEntity(world);
        return tileEntity;
    }
    
    public abstract TileEntityMultiBlock createMultiTileEntity(final World p0);
}

