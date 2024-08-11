package com.gildedgames.the_aether.entities.fake.item;

import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.block.material.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import org.apache.logging.log4j.*;

public class EntityFakeItem extends Entity
{
    public int age;
    public int delayBeforeCanPickup;
    private int health;
    public float hoverStart;
    private static final Logger logger;
    
    public EntityFakeItem(final World par1World, final double par2, final double par4, final double par6) {
        super(par1World);
        this.age = 0;
        this.health = 5;
        this.hoverStart = (float)(Math.random() * 3.141592653589793 * 2.0);
        this.setSize(0.25f, 0.25f);
        this.yOffset = this.height / 2.0f;
        this.setPosition(par2, par4, par6);
        this.rotationYaw = 0.0f;
    }
    
    public EntityFakeItem(final World par1World, final double par2, final double par4, final double par6, final ItemStack par8ItemStack) {
        this(par1World, par2, par4, par6);
        this.setEntityItemStack(par8ItemStack);
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    public EntityFakeItem(final World par1World) {
        super(par1World);
        this.age = 0;
        this.health = 5;
        this.hoverStart = (float)(Math.random() * 3.141592653589793 * 2.0);
        this.setSize(0.25f, 0.25f);
        this.yOffset = this.height / 2.0f;
    }
    
    protected void entityInit() {
        this.getDataWatcher().addObjectByDataType(10, 5);
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.noClip = true;
        ++this.age;
        final ItemStack item = this.getDataWatcher().getWatchableObjectItemStack(10);
        if (item != null && item.stackSize <= 0) {
            this.setDead();
        }
    }
    
    private void searchForOtherItemsNearby() {
        for (final Object o : this.worldObj.getEntitiesWithinAABB((Class)EntityFakeItem.class, this.boundingBox.expand(0.5, 0.0, 0.5))) {
            final EntityFakeItem entityitem = (EntityFakeItem)o;
            this.combineItems(entityitem);
        }
    }
    
    public boolean combineItems(final EntityFakeItem par1EntityItem) {
        if (par1EntityItem == this) {
            return false;
        }
        if (!par1EntityItem.isEntityAlive() || !this.isEntityAlive()) {
            return false;
        }
        final ItemStack itemstack = this.getEntityItem();
        final ItemStack itemstack2 = par1EntityItem.getEntityItem();
        if (itemstack2.getItem() != itemstack.getItem()) {
            return false;
        }
        if (itemstack2.hasTagCompound() ^ itemstack.hasTagCompound()) {
            return false;
        }
        if (itemstack2.hasTagCompound() && !itemstack2.getTagCompound().equals(itemstack.getTagCompound())) {
            return false;
        }
        if (itemstack2.getItem().getHasSubtypes() && itemstack2.getItemDamage() != itemstack.getItemDamage()) {
            return false;
        }
        if (itemstack2.stackSize < itemstack.stackSize) {
            return par1EntityItem.combineItems(this);
        }
        if (itemstack2.stackSize + itemstack.stackSize > itemstack2.getMaxStackSize()) {
            return false;
        }
        final ItemStack itemStack = itemstack2;
        itemStack.stackSize += itemstack.stackSize;
        par1EntityItem.delayBeforeCanPickup = Math.max(par1EntityItem.delayBeforeCanPickup, this.delayBeforeCanPickup);
        par1EntityItem.age = Math.min(par1EntityItem.age, this.age);
        par1EntityItem.setEntityItemStack(itemstack2);
        this.setDead();
        return true;
    }
    
    public void setAgeToCreativeDespawnTime() {
        this.age = 4800;
    }
    
    public boolean handleWaterMovement() {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }
    
    protected void dealFireDamage(final int par1) {
        this.attackEntityFrom(DamageSource.inFire, par1);
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, final int par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (this.getEntityItem() != null && this.getEntityItem().getItem() == Items.nether_star && par1DamageSource.isExplosion()) {
            return false;
        }
        this.setBeenAttacked();
        this.health -= par2;
        if (this.health <= 0) {
            this.setDead();
        }
        return false;
    }
    
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("Health", (byte)this.health);
        par1NBTTagCompound.setShort("Age", (short)this.age);
        if (this.getEntityItem() != null) {
            par1NBTTagCompound.setTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
        }
    }
    
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.setDead();
        this.health = (par1NBTTagCompound.getShort("Health") & 0xFF);
        this.age = par1NBTTagCompound.getShort("Age");
        final NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Item");
        this.setEntityItemStack(ItemStack.loadItemStackFromNBT(nbttagcompound1));
        final ItemStack item = this.getDataWatcher().getWatchableObjectItemStack(10);
        if (item == null || item.stackSize <= 0) {
            this.setDead();
        }
    }
    
    public void onCollideWithPlayer(final EntityPlayer par1EntityPlayer) {
    }
    
    public String getCommandSenderName() {
        return StatCollector.translateToLocal("item." + this.getEntityItem().getUnlocalizedName());
    }
    
    public boolean canAttackWithItem() {
        return false;
    }
    
    public void travelToDimension(final int par1) {
        super.travelToDimension(par1);
        if (!this.worldObj.isRemote) {
            this.searchForOtherItemsNearby();
        }
    }
    
    public ItemStack getEntityItem() {
        final ItemStack itemstack = this.getDataWatcher().getWatchableObjectItemStack(10);
        if (itemstack == null) {
            if (this.worldObj != null) {
				EntityFakeItem.logger.warn("Item entity {} has no item?!", this.getEntityId());
            }
            return new ItemStack(Blocks.stone);
        }
        return itemstack;
    }
    
    public void setEntityItemStack(final ItemStack par1ItemStack) {
        this.getDataWatcher().updateObject(10, par1ItemStack);
        this.getDataWatcher().setObjectWatched(10);
    }
    
    static {
        logger = LogManager.getLogger();
    }
}

