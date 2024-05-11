package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.uro.uroswell.EntityAIUligoSwell;
import com.gildedgames.the_aether.entities.uro.uroswell.EntityAIUroSwell;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntityUligo extends EntityLiving implements IMob
{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    /** ticks until this slime jumps again */
    private int slimeJumpDelay;
    private int lastActiveTime;
    /** The amount of time since the creeper was close enough to the player to ignite */
    private int timeSinceIgnited;
    private int fuseTime = 20;
    /** Explosion radius for this creeper. */
    private int explosionRadius = 2;  

    public EntityUligo(World p_i1742_1_)
    {
        super(p_i1742_1_);
        this.tasks.addTask(2, new EntityAIUligoSwell(this));
        int i = 1 << this.rand.nextInt(3);
        this.yOffset = 0.0F;
        this.slimeJumpDelay = this.rand.nextInt(10) + 10;
        this.setSlimeSize(i);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
        this.dataWatcher.addObject(15, Byte.valueOf((byte) - 1));
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }
    
    public int getMaxSafePointTries()
    {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }
    
    @Override
	public void fall(float distance) {
	}

    protected void setSlimeSize(int p_70799_1_)
    {
        this.dataWatcher.updateObject(16, new Byte((byte)p_70799_1_));
        this.setSize(0.6F * (float)p_70799_1_, 0.6F * (float)p_70799_1_);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(p_70799_1_ * p_70799_1_));
        this.setHealth(this.getMaxHealth());
        this.experienceValue = p_70799_1_;
    }

    /**
     * Returns the size of the slime.
     */
    public int getSlimeSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
        
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("Size", this.getSlimeSize() - 1);
        
        if (this.dataWatcher.getWatchableObjectByte(17) == 1)
        {
            p_70014_1_.setBoolean("powered", true);
        }

        p_70014_1_.setShort("Fuse", (short)this.fuseTime);
        p_70014_1_.setByte("ExplosionRadius", (byte)this.explosionRadius);
        p_70014_1_.setBoolean("ignited", this.func_146078_ca());
    }
    
    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt p_70077_1_)
    {
        super.onStruckByLightning(p_70077_1_);
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        int i = p_70037_1_.getInteger("Size");

        if (i < 0)
        {
            i = 0;
        }

        this.setSlimeSize(i + 1);
        
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)(p_70037_1_.getBoolean("powered") ? 1 : 0)));

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
     * Returns the name of the sound played when the slime jumps.
     */
    protected String getJumpSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
        {
            this.isDead = true;
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        boolean flag = this.onGround;
        super.onUpdate();
        int i;

        if (this.onGround && !flag)
        {
            i = this.getSlimeSize();

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F + 1.0F) / 0.8F);
            }

            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && flag)
        {
            this.squishAmount = 1.0F;
        }

        this.alterSquishAmount();

        if (this.worldObj.isRemote)
        {
            i = this.getSlimeSize();
            this.setSize(0.6F * (float)i, 0.6F * (float)i);
        }
        
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.func_146078_ca())
            {
                this.setCreeperState(1);
            }

            int u = this.getCreeperState();

            if (u > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("ambient.cave.cave", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += u;

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

    protected void updateEntityActionState()
    {
        this.despawnEntity();
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (entityplayer != null)
        {
            this.faceEntity(entityplayer, 10.0F, 20.0F);
        }

        if (this.onGround && this.slimeJumpDelay-- <= 0)
        {
            this.slimeJumpDelay = this.getJumpDelay();

            if (entityplayer != null)
            {
                this.slimeJumpDelay /= 3;
            }

            this.isJumping = true;

            if (this.makesSoundOnJump())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = (float)(1 * this.getSlimeSize());
        }
        else
        {
            this.isJumping = false;

            if (this.onGround)
            {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.75F;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay()
    {
        return this.rand.nextInt(10) + 5;
    }

    protected EntityUligo createInstance()
    {
        return new EntityUligo(this.worldObj);
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityUligo entityslime = this.createInstance();
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }

        super.setDead();
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer p_70100_1_)
    {
        if (this.canDamagePlayer())
        {
            int i = this.getSlimeSize();

            if (this.canEntityBeSeen(p_70100_1_) && this.getDistanceSqToEntity(p_70100_1_) < 0.6D * (double)i * 0.6D * (double)i && p_70100_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength()))
            {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
            
            if (rand.nextInt(10) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);

            if (!this.worldObj.isRemote)
            {
                this.func_146079_cb();
                
            	}
            }
        }
    }
    
    @Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
    	if (source.getEntity() == null || source.isExplosion()) {
            return false;
        }
    	
		if (source.getEntity() != null)
		{
			if (source.getEntity() instanceof EntityLivingBase)
			{
				this.setAttackTarget((EntityLivingBase) source.getEntity());
			}
		}

		return super.attackEntityFrom(source, amount);
	}

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean canDamagePlayer()
    {
        return this.getSlimeSize() >= 1;
    }

    /**
     * Gets the amount of damage dealt to the player when "attacked" by the slime.
     */
    protected int getAttackStrength()
    {
        return this.getSlimeSize();
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "aether_legacy:aemob.uligo.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "aether_legacy:aemob.uligo.death";
    }

    protected Item getDropItem()
    {
        return this.getSlimeSize() == 1 ? ItemsAether.uligo_swet_ball : Item.getItemById(0);
    }
    
    protected boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.flint_and_steel)
        {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
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

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.2F * (float)this.getSlimeSize();
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return 0;
    }

    /**
     * Returns true if the slime makes a sound when it jumps (based upon the slime's size)
     */
    protected boolean makesSoundOnJump()
    {
        return this.getSlimeSize() > 0;
    }

    /**
     * Returns true if the slime makes a sound when it lands after a jump (based upon the slime's size)
     */
    protected boolean makesSoundOnLand()
    {
        return this.getSlimeSize() > 2;
    }
    
    private void func_146077_cc()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            if (this.getPowered())
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), flag);
                
                int i = this.getSlimeSize();

                if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
                {
                    int j = 2 + this.rand.nextInt(3);

                    for (int k = 0; k < j; ++k)
                    {
                        float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                        float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                        EntityUligo entityslime = this.createInstance();
                        entityslime.setSlimeSize(i / 2);
                        entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(entityslime);
                    }
                }
            }
            else
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
                
                int i = this.getSlimeSize();

                if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
                {
                    int j = 2 + this.rand.nextInt(3);

                    for (int k = 0; k < j; ++k)
                    {
                        float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                        float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                        EntityUligo entityslime = this.createInstance();
                        entityslime.setSlimeSize(i / 2);
                        entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(entityslime);
                    }
                }
            }                     

            this.setDead();
        }
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

    public boolean func_146078_ca()
    {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }

    public void func_146079_cb()
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
    }
    
    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(15);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int p_70829_1_)
    {
        this.dataWatcher.updateObject(15, Byte.valueOf((byte)p_70829_1_));
    }
    
    public boolean canDespawn() {
        return true;
    }
    
    public float getBlockPathWeight(final int par1, final int par2, final int par3) {
        return ((this.worldObj.getBlock(par1, par2 - 1, par3) == BlocksAether.locked_fuse_stone || this.worldObj.getBlock(par1, par2 - 1, par3) == BlocksAether.locked_creeping_stone)) ? 10.0f : (this.worldObj.getLightBrightness(par1, par2, par3) - 0.5f);
    }
    
    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);          
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_creeping_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_fuse_stone) && this.worldObj.getBlockLightValue(i, j, k) < 14 && canSpawn;
                       
    }
    
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}

