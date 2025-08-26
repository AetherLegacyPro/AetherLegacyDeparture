package com.gildedgames.the_aether.entities.passive;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityCockatrice;
import com.gildedgames.the_aether.entities.passive.flynxAI.EntityAIFlynxSit;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityFlynx extends EntityTameable
{
    private EntityAITempt aiTempt;

    public EntityFlynx(World p_i1688_1_)
    {
        super(p_i1688_1_);
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, ItemsAether.raw_aerwhale, true));
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.tasks.addTask(6, new EntityAIFlynxSit(this, 1.33D));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(8, new EntityAIOcelotAttack(this));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityCockatrice.class, 750, false));
    }
    
    @Override
	public boolean isPotionApplicable(PotionEffect effect) {
		return effect.getPotionID() == Potion.confusion.id ? false : super.isPotionApplicable(effect);
	}

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, (byte) 0);
    }

    public void updateAITick()
    {
        if (this.getMoveHelper().isUpdating())
        {
            double d0 = this.getMoveHelper().getSpeed();

            if (d0 == 0.6D)
            {
                this.setSneaking(true);
                this.setSprinting(false);
            }
            else if (d0 == 1.33D)
            {
                this.setSneaking(false);
                this.setSprinting(true);
            }
            else
            {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        }
        else
        {
            this.setSneaking(false);
            this.setSprinting(false);
        }
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
    }

    protected void fall(float p_70069_1_) {}

    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("CatType", this.getTameSkin());
    }

    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setTameSkin(p_70037_1_.getInteger("CatType"));
    }

    protected String getLivingSound()
    {
    	if (this.isTamed() == true) {
    		return this.isTamed() ? (this.isInLove() ? "mob.cat.purr" : (this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    	}
    	else {
    		return null;
    	}
    }

    protected String getHurtSound()
    {
        return "mob.cat.hitt";
    }

    protected String getDeathSound()
    {
        return "mob.cat.hitt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected Item getDropItem()
    {
        return Items.string;
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 25.0F);
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            this.aiSit.setSitting(false);
            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {}

    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (this.func_152114_e(p_70085_1_) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
            {
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if (this.aiTempt.isRunning() && itemstack != null && itemstack.getItem() == ItemsAether.raw_aerwhale && p_70085_1_.getDistanceSqToEntity(this) < 9.0D)
        {
            if (!p_70085_1_.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setTameSkin(1 + this.worldObj.rand.nextInt(3));
                    this.func_152115_b(p_70085_1_.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(p_70085_1_);
    }

    public EntityFlynx createChild(EntityAgeable p_90011_1_)
    {
    	EntityFlynx entityflynx = new EntityFlynx(this.worldObj);

        if (this.isTamed())
        {
            entityflynx.func_152115_b(this.func_152113_b());
            entityflynx.setTamed(true);
            entityflynx.setTameSkin(this.getTameSkin());
        }

        return entityflynx;
    }

    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getItem() == ItemsAether.raw_aerwhale;
    }

    public boolean canMateWith(EntityAnimal p_70878_1_)
    {
        if (p_70878_1_ == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(p_70878_1_ instanceof EntityFlynx entityflynx))
        {
            return false;
        }
        else
        {
			return !entityflynx.isTamed() ? false : this.isInLove() && entityflynx.isInLove();
        }
    }

    public int getTameSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setTameSkin(int p_70912_1_)
    {
        this.dataWatcher.updateObject(18, (byte) p_70912_1_);
    }

    public String getCommandSenderName()
    {
        return this.hasCustomNameTag() ? this.getCustomNameTag() : (this.isTamed() ? StatCollector.translateToLocal("entity.Cat.name") : super.getCommandSenderName());
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);

        if (this.worldObj.rand.nextInt(7) == 0)
        {
            for (int i = 0; i < 2; ++i)
            {
            	EntityFlynx entityflynx = new EntityFlynx(this.worldObj);
                entityflynx.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                entityflynx.setGrowingAge(-24000);
                this.worldObj.spawnEntityInWorld(entityflynx);
            }
        }

        return p_110161_1_;
    }
    
    @Override
   	public boolean getCanSpawnHere() {
           final int i = MathHelper.floor_double(this.posX);
           final int j = MathHelper.floor_double(this.boundingBox.minY);
           final int k = MathHelper.floor_double(this.posZ);
           final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
			return ((this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt
					|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass
					|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass
					|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass
					|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass
					|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass)
					&& this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn
					&& this.rand.nextInt(AetherConfig.getFlynxSpawnrate()) == 0 && super.getCanSpawnHere());
                          
       }
}
