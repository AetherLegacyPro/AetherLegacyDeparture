package com.gildedgames.the_aether.entities.block;

import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.block.material.*;
import net.minecraftforge.event.entity.item.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.*;
import java.util.*;

public class EntityFireProofItemAether extends EntityItem
{
    private int health;
    
    public EntityFireProofItemAether(final World par1World, final double par2, final double par4, final double par6) {
        super(par1World, par2, par4, par6);
        this.isImmuneToFire = true;
        this.health = 5;
        this.lifespan = 6000;
    }
    
    public EntityFireProofItemAether(final World par1World, final double par2, final double par4, final double par6, final ItemStack par8ItemStack) {
        this(par1World, par2, par4, par6);
        this.setEntityItemStack(par8ItemStack);
        this.lifespan = 6000;
    }
    
    public EntityFireProofItemAether(final World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.health = 5;
        this.lifespan = 6000;
    }
    
    public EntityFireProofItemAether(final World world, final Entity original, final ItemStack stack) {
        this(world, original.posX, original.posY, original.posZ);
        if (original instanceof EntityItem) {
            this.delayBeforeCanPickup = ((EntityItem)original).delayBeforeCanPickup;
        }
        else {
            this.delayBeforeCanPickup = 20;
        }
        this.motionX = original.motionX;
        this.motionY = original.motionY;
        this.motionZ = original.motionZ;
        this.setEntityItemStack(stack);
        this.lifespan = 6000;
    }
    
    public boolean attackEntityFrom(final DamageSource p_70097_1_, final float p_70097_2_) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (p_70097_1_.isFireDamage()) {
            return false;
        }
        this.setBeenAttacked();
        this.health -= (int)p_70097_2_;
        if (this.health <= 0) {
            this.setDead();
        }
        return false;
    }
    
    protected void dealFireDamage(final int p_70081_1_) {
        this.attackEntityFrom(DamageSource.inFire, (float)p_70081_1_);
    }
    
    public void onUpdate() {
        if (this.isBurning()) {
            this.extinguish();
        }
        final ItemStack stack = this.getDataWatcher().getWatchableObjectItemStack(10);
        if (stack != null && stack.getItem() != null && stack.getItem().onEntityItemUpdate((EntityItem)this)) {
            return;
        }
        if (this.getEntityItem() == null) {
            this.setDead();
        }
        else {
            this.onEntityUpdate();
            if (this.delayBeforeCanPickup > 0) {
                --this.delayBeforeCanPickup;
            }
            final boolean inlava = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.lava;
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.noClip = this.func_145771_j(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0, this.posZ);
            if (inlava) {
                this.moveEntity(this.motionX, this.motionY + 0.1, this.motionZ);
            }
            else {
                this.motionY -= 0.03999999910593033;
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
            }
            final boolean flag = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;
            if ((flag || this.ticksExisted % 25 == 0) && !this.worldObj.isRemote) {
                this.searchForOtherItemsNearby();
            }
            float f = 0.98f;
            if (this.onGround) {
                f = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.98f;
            }
            this.motionX *= f;
            this.motionY *= 0.9800000190734863;
            this.motionZ *= f;
            if (this.onGround) {
                this.motionY *= -0.5;
            }
            ++this.age;
            final ItemStack item = this.getDataWatcher().getWatchableObjectItemStack(10);
            if (!this.worldObj.isRemote && this.age >= this.lifespan) {
                if (item != null) {
                    final ItemExpireEvent event = new ItemExpireEvent((EntityItem)this, (item.getItem() == null) ? 6000 : item.getItem().getEntityLifespan(item, this.worldObj));
                    if (MinecraftForge.EVENT_BUS.post((Event)event)) {
                        this.lifespan += event.extraLife;
                    }
                    else {
                        this.setDead();
                    }
                }
                else {
                    this.setDead();
                }
            }
            if (item != null && item.stackSize <= 0) {
                this.setDead();
            }
        }
    }
    
    private void searchForOtherItemsNearby() {
        for (final Object entityitem : this.worldObj.getEntitiesWithinAABB((Class)EntityItem.class, this.boundingBox.expand(0.5, 0.0, 0.5))) {
            this.combineItems((EntityItem) entityitem);
        }
    }
    
    public boolean combineItems(final EntityItem p_70289_1_) {
        if (p_70289_1_ == this) {
            return false;
        }
        if (!p_70289_1_.isEntityAlive() || !this.isEntityAlive()) {
            return false;
        }
        final ItemStack itemstack = this.getEntityItem();
        final ItemStack itemstack2 = p_70289_1_.getEntityItem();
        if (itemstack2.getItem() != itemstack.getItem()) {
            return false;
        }
        if (itemstack2.hasTagCompound() ^ itemstack.hasTagCompound()) {
            return false;
        }
        if (itemstack2.hasTagCompound() && !itemstack2.getTagCompound().equals((Object)itemstack.getTagCompound())) {
            return false;
        }
        if (itemstack2.getItem() == null) {
            return false;
        }
        if (itemstack2.getItem().getHasSubtypes() && itemstack2.getItemDamage() != itemstack.getItemDamage()) {
            return false;
        }
        if (itemstack2.stackSize < itemstack.stackSize) {
            return p_70289_1_.combineItems((EntityItem)this);
        }
        if (itemstack2.stackSize + itemstack.stackSize > itemstack2.getMaxStackSize()) {
            return false;
        }
        final ItemStack itemStack = itemstack2;
        itemStack.stackSize += itemstack.stackSize;
        p_70289_1_.delayBeforeCanPickup = Math.max(p_70289_1_.delayBeforeCanPickup, this.delayBeforeCanPickup);
        p_70289_1_.age = Math.min(p_70289_1_.age, this.age);
        p_70289_1_.setEntityItemStack(itemstack2);
        this.setDead();
        return true;
    }
}
