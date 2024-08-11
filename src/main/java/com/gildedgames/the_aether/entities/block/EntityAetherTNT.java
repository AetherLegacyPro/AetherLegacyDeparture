package com.gildedgames.the_aether.entities.block;

import net.minecraft.entity.*;
import net.minecraft.world.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.nbt.*;

public class EntityAetherTNT extends Entity
{
    public int fuse;
    private EntityLivingBase tntPlacedBy;
    
    public EntityAetherTNT(final World p_i1729_1_) {
        super(p_i1729_1_);
        this.preventEntitySpawning = true;
        this.fuse = 240;
        this.setSize(0.98f, 0.98f);
        this.yOffset = this.height / 2.0f;
    }
    
    public EntityAetherTNT(final World p_i1730_1_, final double p_i1730_2_, final double p_i1730_4_, final double p_i1730_6_, final EntityLivingBase p_i1730_8_) {
        this(p_i1730_1_);
        this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
        final float f = (float)(Math.random() * 3.141592653589793 * 2.0);
        this.motionX = -(float)Math.sin(f) * 0.02f;
        this.motionY = 0.20000000298023224;
        this.motionZ = -(float)Math.cos(f) * 0.02f;
        this.fuse = 80;
        this.prevPosX = p_i1730_2_;
        this.prevPosY = p_i1730_4_;
        this.prevPosZ = p_i1730_6_;
        this.tntPlacedBy = p_i1730_8_;
    }
    
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    protected void entityInit() {
    }
    
    private void explode() {
        final float f = 4.5f;
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4.5f, true);
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0f;
    }
    
    public EntityLivingBase getTntPlacedBy() {
        return this.tntPlacedBy;
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863;
        this.motionY *= 0.9800000190734863;
        this.motionZ *= 0.9800000190734863;
        if (this.onGround) {
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
            this.motionY *= -0.5;
        }
        if (this.fuse-- <= 0) {
            this.setDead();
            if (!this.worldObj.isRemote) {
                this.explode();
            }
        }
        else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
            this.worldObj.spawnParticle("smoke", this.posX + 0.5, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
            this.worldObj.spawnParticle("smoke", this.posX -0.5, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
        }
    }
    
    protected void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        this.fuse = p_70037_1_.getByte("Fuse");
    }
    
    protected void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        p_70014_1_.setByte("Fuse", (byte)this.fuse);
    }
}
