package com.gildedgames.the_aether.entities.hostile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCyro extends EntityAetherMob
{

    public EntityCyro(World p_i1745_1_)
    {
        super(p_i1745_1_);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setSize(0.4F, 1.4F);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
    }
    
    @Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(AetherConfig.getCyroSpawnrate()) == 0 && super.getCanSpawnHere();
	}

    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(12, (byte) 0);
        this.getDataWatcher().addObject(13, (byte) 0);
        this.getDataWatcher().addObject(14, (byte) 0);
    }

    public int getTotalArmorValue()
    {
        int i = super.getTotalArmorValue() + 2;

        if (i > 20)
        {
            i = 20;
        }

        return i;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer entityplayer)
        {

			entityplayer.triggerAchievement(AchievementsAether.aether_hunter);
            
        }
            
    }
    
    @Override
   	public void fall(float distance) {
   	}

    public void onLivingUpdate()
    {
    	if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			this.setDead();
		}
		
		this.despawnEntity();
		
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }
        
        super.onLivingUpdate();

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.59999999999999998D;
		}
		
        super.onLivingUpdate();
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        boolean flag = super.attackEntityAsMob(p_70652_1_);

        if (flag)
        {
            int i = this.worldObj.difficultySetting.getDifficultyId();

            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)i * 0.3F)
            {
                p_70652_1_.setFire(2 * i);
            }
        }

        return flag;
    }

    protected String getLivingSound()
    {
        return "aether_legacy:aemob.cyro.living";
    }

    protected String getHurtSound()
    {
        return "aether_legacy:aemob.cyro.hurt";
    }

    protected String getDeathSound()
    {
        return "aether_legacy:aemob.cyro.death";
    }

    protected Item getDropItem()
    {
        return ItemsAether.cyro_rod;
    }

	protected void dropRareDrop(int p_70600_1_)
    {
        switch (this.rand.nextInt(3))
        {
            case 0:
                this.dropItem(ItemsAether.zanite_gemstone, 1);
                break;
            case 1:
                this.dropItem(ItemsAether.zanite_shovel, 1);
                break;
            case 2:
                this.dropItem(ItemsAether.zanite_sword, 1);
        }
    }

    protected void addRandomArmor()
    {
        super.addRandomArmor();

        if (this.rand.nextFloat() < (this.worldObj.difficultySetting == EnumDifficulty.HARD ? 0.05F : 0.01F))
        {
            int i = this.rand.nextInt(3);

            if (i == 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(ItemsAether.zanite_sword));
            }
            else
            {
                this.setCurrentItemOrArmor(0, new ItemStack(ItemsAether.zanite_shovel));
            }
        }
    }

}