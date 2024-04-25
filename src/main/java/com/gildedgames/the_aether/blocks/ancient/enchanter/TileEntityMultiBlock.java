package com.gildedgames.the_aether.blocks.ancient.enchanter;

import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraft.block.*;
import net.minecraft.world.chunk.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import cpw.mods.fml.relauncher.*;

public class TileEntityMultiBlock extends TileEntity implements IRotatable
{
    public AxisAlignedBB size;
    public Rotation rotation;
    public boolean hasInit;
    
    public TileEntityMultiBlock() {
        this.size = AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        this.rotation = Rotation.NORTH;
        this.hasInit = false;
    }
    
    public void setSize(final AxisAlignedBB size) {
        this.size = AxisAlignedBB.getBoundingBox(size.minX, size.minY, size.minZ, size.maxX, size.maxY, size.maxZ);
        final AxisAlignedBB size2 = this.size;
        size2.minX += this.xCoord;
        final AxisAlignedBB size3 = this.size;
        size3.minY += this.yCoord;
        final AxisAlignedBB size4 = this.size;
        size4.minZ += this.zCoord;
        final AxisAlignedBB size5 = this.size;
        size5.maxX += this.xCoord;
        final AxisAlignedBB size6 = this.size;
        size6.maxY += this.yCoord;
        final AxisAlignedBB size7 = this.size;
        size7.maxZ += this.zCoord;
    }
    
    public AxisAlignedBB getSize() {
        return this.size;
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
    
    public Object iterateSize(final Action action, final World world) {
        return this.iterateSize(this.size, action, world);
    }
    
    public Object iterateSize(final AxisAlignedBB size, final Action action, final World world) {
        final int minX = MathHelper.floor_double(size.minX);
        final int minY = MathHelper.floor_double(size.minY);
        final int minZ = MathHelper.floor_double(size.minZ);
        final int maxX = MathHelper.floor_double(size.maxX);
        final int maxY = MathHelper.floor_double(size.maxY);
        final int maxZ = MathHelper.floor_double(size.maxZ);
        for (int x1 = minX; x1 < maxX; ++x1) {
            for (int y1 = minY; y1 < maxY; ++y1) {
                for (int z1 = minZ; z1 < maxZ; ++z1) {
                    final Object object = action.onAction(world, x1, y1, z1, world.getBlock(x1, y1, z1), world.getBlockMetadata(x1, y1, z1));
                    if (action.shouldReturnObject(world, x1, y1, z1)) {
                        return object;
                    }
                }
            }
        }
        return null;
    }
    
    public void setRotationAndRefresh(final Rotation rotation) {
        this.rotation = rotation;
        this.hasInit = false;
    }
    
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        final double minX = nbt.getDouble("minX");
        final double minY = nbt.getDouble("minY");
        final double minZ = nbt.getDouble("minZ");
        final double maxX = nbt.getDouble("maxX");
        final double maxY = nbt.getDouble("maxY");
        final double maxZ = nbt.getDouble("maxZ");
        this.size = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        this.rotation = (nbt.getString("rotation").isEmpty() ? null : Rotation.valueOf(nbt.getString("rotation")));
        this.hasInit = nbt.getBoolean("hasInit");
    }
    
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setDouble("minX", this.size.minX);
        nbt.setDouble("minY", this.size.minY);
        nbt.setDouble("minZ", this.size.minZ);
        nbt.setDouble("maxX", this.size.maxX);
        nbt.setDouble("maxY", this.size.maxY);
        nbt.setDouble("maxZ", this.size.maxZ);
        nbt.setString("rotation", this.rotation.name());
        nbt.setBoolean("hasInit", this.hasInit);
    }
    
    public void rotate(final World world, final Rotation rotation, final int x, final int y, final int z) {
        if (rotation != null) {
            rotation.rotate(world, (IRotatable)this, x, y, z);
        }
    }
    
    public void rotate(final World world, final boolean clockwise, final int x, final int y, final int z) {
        final int width = this.getWidth();
        final int length = this.getLength();
        this.size.minX = this.size.minX + width / 2 - length / 2;
        this.size.minZ = this.size.minZ + length / 2 - width / 2;
        this.size.maxX = this.size.minX + length - 1.0;
        this.size.maxZ = this.size.minZ + width - 1.0;
    }
    
    public Rotation getCurrentRotation(final World world, final int x, final int y, final int z) {
        return this.rotation;
    }
    
    public void setCurrentRotation(final World world, final Rotation rotation, final int x, final int y, final int z) {
        this.rotation = rotation;
    }
    
    public void updateEntity() {
        if (!this.hasInit && this.getBlockType() instanceof BlockMultiTileEntity) {
            final BlockMultiTileEntity block = (BlockMultiTileEntity)this.getBlockType();
            final Action checkLoadedChunks = new Action(block) {
                @Override
                public Object onAction(final World world, final int x, final int y, final int z, final Block blockID, final int blockMetadata) {
                    return false;
                }
                
                @Override
                public boolean shouldReturnObject(final World world, final int x, final int y, final int z) {
                    final Chunk chunk = world.getChunkFromBlockCoords(x, z);
                    return chunk == null || !chunk.isChunkLoaded;
                }
            };
            final Object checked = this.iterateSize(checkLoadedChunks, this.worldObj);
            if (!(checked instanceof Boolean)) {
                block.onBlockPlacedBy(this.worldObj, this.xCoord, this.yCoord, this.zCoord, (EntityLivingBase)null, (ItemStack)null);
                this.hasInit = true;
            }
        }
    }
    
    //public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
   //     this.readFromNBT(pkt.getNbtCompound());
    //}
    
    public Packet getDescriptionPacket() {
        final NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return (Packet)new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntityMultiBlock.INFINITE_EXTENT_AABB;
    }
    
    public abstract static class Action
    {
        protected Block block;
        protected int startX;
        protected int startY;
        protected int startZ;
        
        public Action(final Block block) {
            this.block = block;
        }
        
        public Action(final Block block, final int startX, final int startY, final int startZ) {
            this(block);
            this.startX = startX;
            this.startY = startY;
            this.startZ = startZ;
        }
        
        public abstract Object onAction(final World p0, final int p1, final int p2, final int p3, final Block p4, final int p5);
        
        public abstract boolean shouldReturnObject(final World p0, final int p1, final int p2, final int p3);
    }
}

