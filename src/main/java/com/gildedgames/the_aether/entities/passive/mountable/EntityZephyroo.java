package com.gildedgames.the_aether.entities.passive.mountable;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.Items;

import java.util.*;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityAetherMob;
import com.gildedgames.the_aether.entities.passive.EntityAetherAnimal;
import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.entities.util.EntitySaddleMount;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;

import net.minecraft.entity.*;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZephyroo extends EntityAetherAnimal
{
    private int timeTilJump;
    
    public EntityZephyroo(final World world) {
        super(world);
        this.timeTilJump = 10;
        this.setSize(0.5f, 0.5f); 
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(15.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 0.25D, ItemsAether.grapes, false));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIFollowParent(this, 1.1D));
        this.setHealth(20.0f);
    }
     
    @Override
   	public boolean getCanSpawnHere() {
   	      final int i = MathHelper.floor_double(this.posX);
   	      final int j = MathHelper.floor_double(this.boundingBox.minY);
   	      final int k = MathHelper.floor_double(this.posZ);
   	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);          
   	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn && this.rand.nextInt(AetherConfig.getZephyrooSpawnrate()) == 0 && super.getCanSpawnHere();
   	                       
   	}
    
    protected void dropFewItems(final boolean var1, final int var2) {
        this.dropItem(ItemsAether.zephyroo_leather, 1 + rand.nextInt(3));
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
       final List<Entity> entitiesAround = (List<Entity>)this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(5.5, 1.75, 5.5));
       boolean foundPlayer = false;
       if (this.riddenByEntity != null && this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() != 25.0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(25.0);
        }
        for (final Entity entity : entitiesAround) {
            if (entity instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)entity;
                
            }
        }
        if (!foundPlayer) {
            this.entityToAttack = null;
        }
    }
    
    public double getMountedYOffset() {
        return 1.3;
    }
    
    public boolean interact(final EntityPlayer entityplayer) {
    	ItemStack itemstack = entityplayer.inventory.getCurrentItem();
    	if (this.riddenByEntity == null || entityplayer == this.riddenByEntity) {
            if (this.riddenByEntity == null) {
               if (!entityplayer.worldObj.isRemote) {
                   entityplayer.mountEntity((Entity)this);
                   final float rotationYaw = this.rotationYaw;
                   entityplayer.rotationYaw = rotationYaw;
                    entityplayer.prevRotationYaw = rotationYaw;
                 }
            }
             else if (!entityplayer.worldObj.isRemote && (this.onGround || entityplayer.capabilities.isCreativeMode)) {
                 entityplayer.mountEntity((Entity)null);
             }
            return true;
         }
         return super.interact(entityplayer);     
    }
    
    protected void attackEntity(final Entity par1Entity, final float par2) {
        if (par1Entity instanceof EntityPlayer) {
            if (par2 < 3.0f) {
                final double d0 = par1Entity.posX - this.posX;
                final double d2 = par1Entity.posZ - this.posZ;
                this.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0 / 3.141592653589793) - 90.0f;
                this.hasAttacked = true;
            }
            this.entityToAttack = par1Entity;
        }
        else {
            super.attackEntity(par1Entity, par2);
        }
    }
    
    @Override
	public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
        this.dataWatcher.addObject(19, (Object)0);
    }
    
    protected void updateEntityActionState() {
        super.updateEntityActionState();
        this.fallDistance = 0.0f;
        	if (this.onGround && !this.isInLove() && (this.motionX > 1.0E-4 || this.motionZ > 1.0E-4)) {
            this.hop();
        	}
        	else {
        		if (this.timeTilJump != 0) {
                --this.timeTilJump;
        		}
        		if (this.timeTilJump == 0) {
                this.timeTilJump = 10;
        		}
        	}
        	this.dataWatcher.updateObject(18, (Object)this.timeTilJump);
        }
    
    public float getTimeTilJump() {
        return (float)this.dataWatcher.getWatchableObjectInt(18);
    }
    
    protected void onDeathUpdate() {
        if (this.deathTime == 18 && !this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && !this.isChild()) {
            
        }
        super.onDeathUpdate();
    }
    
    protected void hop() {
        super.jump();
    }
    
    public EntityZephyroo createChild(EntityAgeable p_90011_1_)
    {
    	EntityZephyroo entityzephyroo = new EntityZephyroo(this.worldObj);

        return entityzephyroo;
    }
    
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getItem() == ItemsAether.grapes;
    }
    
    @Override
    public boolean canDespawn() {
        return false;
    }
    
    @Override
    protected String getLivingSound() {
        return "aether_legacy:aemob.zephyroo.say";
    }

    @Override
    protected String getHurtSound() {
        return "aether_legacy:aemob.zephyroo.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "aether_legacy:aemob.zephyroo.death";
    }
    
}

