package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.uro.uroswell.EntityAIUroSwell;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityUro extends EntityMob
{
    /**
     * Time when this creeper was last in an active state (Messed up code here, probably causes creeper animation to go
     * weird)
     */
    private int lastActiveTime;
    /** The amount of time since the creeper was close enough to the player to ignite */
    private int timeSinceIgnited;
    private int fuseTime = 50;
    /** Explosion radius for this creeper. */
    private int explosionRadius = 4;

    public EntityUro(World p_i1733_1_)
    {
        super(p_i1733_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIUroSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityFlynx.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }
    
    @Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(AetherConfig.getUroSpawnrate()) == 0 && super.getCanSpawnHere();
	}

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * The number of iterations PathFinder.getSafePoint will execute before giving up.
     */
    public int getMaxSafePointTries()
    {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }
    
    @Override
	public void fall(float distance) {
	}

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) -1);
        this.dataWatcher.addObject(17, (byte) 0);
        this.dataWatcher.addObject(18, (byte) 0);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);

        if (this.dataWatcher.getWatchableObjectByte(17) == 1)
        {
            p_70014_1_.setBoolean("powered", true);
        }

        p_70014_1_.setShort("Fuse", (short)this.fuseTime);
        p_70014_1_.setByte("ExplosionRadius", (byte)this.explosionRadius);
        p_70014_1_.setBoolean("ignited", this.func_146078_ca());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.dataWatcher.updateObject(17, (byte) (p_70037_1_.getBoolean("powered") ? 1 : 0));

        if (p_70037_1_.hasKey("Fuse", 99))
        {
            this.fuseTime = p_70037_1_.getShort("Fuse");
        }

        if (p_70037_1_.hasKey("ExplosionRadius", 99))
        {
            this.explosionRadius = p_70037_1_.getByte("ExplosionRadius");
        }

        if (p_70037_1_.getBoolean("ignited"))
        {
            this.func_146079_cb();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.func_146078_ca())
            {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("creeper.primed", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.func_146077_cc();
            }
        }

        super.onUpdate();
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.creeper.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.creeper.death";
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityCockatrice)
        {
        	int g = Item.getIdFromItem(ItemsAether.legacy);
        	int h = Item.getIdFromItem(ItemsAether.ascending_dawn);
            int i = Item.getIdFromItem(ItemsAether.welcoming_skies);
            int j = Item.getIdFromItem(ItemsAether.aether_tune);
            int k = i + this.rand.nextInt(j - i - g - h + 3);
            this.dropItem(Item.getItemById(k), 1);
        }
        
        if (p_70645_1_.getEntity() instanceof EntityPlayer entityplayer)
        {

			entityplayer.triggerAchievement(AchievementsAether.aether_hunter);
            
        }
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        return true;
    }

    /**
     * Returns true if the creeper is powered by a lightning bolt.
     */
    public boolean getPowered()
    {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    /**
     * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
     */
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float p_70831_1_)
    {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float)(this.fuseTime - 2);
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(2 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.redstone, 1);
        }

        j = this.rand.nextInt(2 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.gunpowder, 1);
        }
    }

    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int p_70829_1_)
    {
        this.dataWatcher.updateObject(16, (byte) p_70829_1_);
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt p_70077_1_)
    {
        super.onStruckByLightning(p_70077_1_);
        this.dataWatcher.updateObject(17, (byte) 1);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    protected boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.flint_and_steel)
        {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            p_70085_1_.swingItem();

            if (!this.worldObj.isRemote)
            {
                this.func_146079_cb();
                itemstack.damageItem(1, p_70085_1_);
                return true;
            }
        }

        return super.interact(p_70085_1_);
    }

    private void func_146077_cc()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            if (this.getPowered())
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), flag);
            }
            else
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
            }

            this.setDead();
        }
    }

    public boolean func_146078_ca()
    {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }

    public void func_146079_cb()
    {
        this.dataWatcher.updateObject(18, (byte) 1);
    }
}
