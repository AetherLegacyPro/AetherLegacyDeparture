package com.gildedgames.the_aether.blocks.elysian_totem;

import com.gildedgames.the_aether.blocks.ancient.enchanter.TileEntityMultiBlock;
import net.minecraft.nbt.*;

public class TileEntityElysianTotem extends TileEntityMultiBlock implements ITickingAmbienceBlock {
    private float totemRotation;
    private float totemRotationSpeed;
    private float powerDrainTime;
    private float drainTimeRequired;
    private boolean soundStarted;

    public TileEntityElysianTotem() {
        this.totemRotation = 0.0f;
        this.totemRotationSpeed = 0.15f;
        this.drainTimeRequired = 7.0f;
    }

    public float getTotemRotation() {
        return this.totemRotation;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.powerDrainTime += 0.1f;
        this.totemRotation += this.totemRotationSpeed;
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
    }

    public boolean shouldPlayAmbience() {
        return this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockElysianTotem;
    }
}
