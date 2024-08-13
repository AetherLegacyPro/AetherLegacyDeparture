package com.gildedgames.the_aether.entities.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

public class EntityThunderlo extends EntityAetherAnimal
{
    public EntityThunderlo(World world) {
        super(world);
        this.setSize(2.0f, 2.0f);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ItemsAether.strawberry, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25));
        this.tasks.addTask(6, new EntityAIWander(this, 0.25));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIAttackOnCollide(this, 1.0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setHealth(30);
    }  
    
    @Override
	public boolean getCanSpawnHere() {
	      final int i = MathHelper.floor_double(this.posX);
	      final int j = MathHelper.floor_double(this.boundingBox.minY);
	      final int k = MathHelper.floor_double(this.posZ);
	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn && (AetherConfig.getThunderloSpawnrate()) == 0 && super.getCanSpawnHere();
	                       
	}
       
    protected void dropFewItems(final boolean var1, final int var2) {
    	this.dropItem(ItemsAether.thunderlo_leather, 1 + rand.nextInt(2));
        this.dropItem(ItemsAether.raw_thunderlo, 1 +  rand.nextInt(2));
        int rand = (int)(1 + Math.random() * 4);
		switch (rand)
        {
        case 1: this.dropItem(ItemsAether.thunderlo_horn, 1);
        break;
        case 2: 
        break;
        case 3:
        break;
        case 4:
        break;
        }
    }
    
    protected void dropRareDrop(int p_70600_1_)
    {
    	this.dropItem(ItemsAether.thunderlo_horn, 1 + rand.nextInt(4));
    }
    
    public boolean attackEntityFrom(DamageSource ds, float i) {
		if (ds == DamageSource.inWall || ds == DamageSource.fall)
        {
            return false;
        }
		
		boolean flag = super.attackEntityFrom(ds, i);

		return flag;
	}
    
    @Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.inventory.getCurrentItem();
		
		return super.interact(player);
    }
    
    protected void fall(float p_70069_1_) {}
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
        final List<Entity> entitiesAround = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(5.5, 1.75, 5.5));
        boolean foundPlayer = false;
        if (this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() != 25.0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(25.0);
        }
        for (final Entity entity : entitiesAround) {
            if (entity instanceof EntityPlayer player) {

			}
        }
        if (!foundPlayer) {
            this.entityToAttack = null;
        }
        
        if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.59999999999999998D;
		}
		
        super.onLivingUpdate();
        
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

    public EntityThunderlo createChild(EntityAgeable p_90011_1_)
    {
    	EntityThunderlo entitythunderlo = new EntityThunderlo(this.worldObj);

        return entitythunderlo;
    }
    
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getItem() == ItemsAether.strawberry;
    }
    
    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer entityplayer)
        {

			entityplayer.triggerAchievement(AchievementsAether.kill_thunderlo);
            
        }
            
    }
	
	@Override
    protected String getLivingSound() {
        return "aether_legacy:aemob.thunderlo.say";
    }

    @Override
    protected String getHurtSound() {
        return "aether_legacy:aemob.thunderlo.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "aether_legacy:aemob.thunderlo.death";
    }
    
    @Override
    public boolean canDespawn() {
        return false;
    }
    
}

