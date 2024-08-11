package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.projectile.EntityCyroGuardianProjectile;
import com.gildedgames.the_aether.entities.util.AetherNameGen;
import com.gildedgames.the_aether.entities.util.EntityBossMob;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityCyroGuardian extends EntityBossMob implements IAetherBoss
{
    /** Random offset used in floating behaviour */
    private float heightOffset = 0.5F;
    /** ticks until heightOffset is randomized */
    private int heightOffsetUpdateTime;
    private int field_70846_g;

    public EntityCyroGuardian(World p_i1731_1_)
    {
        super(p_i1731_1_);
        this.isImmuneToFire = false;
        this.experienceValue = 100;
        this.setSize(3.0f, 3.0f);
        this.dataWatcher.updateObject(19, AetherNameGen.gen());
    }
    
    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
        this.dataWatcher.addObject(19, AetherNameGen.gen());
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(180.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.85D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(4.0D);
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        
        nbttagcompound.setString("BossName", this.getName());
    }
    
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        
        this.setBossName(nbttagcompound.getString("BossName"));
    }
    
    public int getTotalArmorValue()
    {
        return 10;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "aether_legacy:aemob.cyro.living";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "aether_legacy:aemob.cyro.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "aether_legacy:aemob.cyro.death";
    }
    
    @Override
    protected float getSoundVolume() {
        return 3.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        return 0.5F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	 if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
             this.damageEntity(DamageSource.drown, 1.0f);
         }
    	
        if (!this.worldObj.isRemote)
        {
            if (this.isWet())
            {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }

            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0)
            {
                this.heightOffsetUpdateTime = 40;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
            {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }

        if (this.rand.nextInt(24) == 0)
        {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        
        for (int i = 0; i < 2; ++i)
        {
            this.worldObj.spawnParticle("snowshovel", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        }    

        super.onLivingUpdate();
    }
    
    @SideOnly(Side.CLIENT)
    public void onEntityUpdate() {
    	super.onEntityUpdate();
    	if (this.worldObj.isRemote) {
    		for (int i = 0; i < 1; ++i)
            {
            	NewAetherParticleHandler.CYRO.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            	NewAetherParticleHandler.CYRO.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 1.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            	NewAetherParticleHandler.CYRO.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 2.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            	NewAetherParticleHandler.CYRO.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height + 3.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            }
           }
    	}
    
    @Override
    public boolean attackEntityFrom(DamageSource ds, float var2) {
    	if (ds.getEntity() == null || !(ds.getEntity() instanceof EntityPlayer)) {
    		 return false;
    	}
    	EntityPlayer player = (EntityPlayer) ds.getEntity();
    	boolean flag = super.attackEntityFrom(ds, Math.max(0, var2));
    	
    	if (flag) {
    	if (this.getHealth() <= 0 || this.isDead) {
    	PlayerAether.get(player).setFocusedBoss(null);
    		}
    	}
    	PlayerAether.get(player).setFocusedBoss(this);
    	
    	 return flag;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_)
    {
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 4; //10
            this.attackEntityAsMob(p_70785_1_);
        }
        else if (p_70785_2_ < 30.0F)
        {
            double d0 = p_70785_1_.posX - this.posX;
            double d1 = p_70785_1_.boundingBox.minY + (double)(p_70785_1_.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = p_70785_1_.posZ - this.posZ;

            if (this.attackTime == 0)
            {
                ++this.field_70846_g;

                if (this.field_70846_g == 1)
                {
                    this.attackTime = 8; //30
                    this.func_70844_e(true);
                }
                else if (this.field_70846_g <= 4)
                {
                    this.attackTime = 6;
                }
                else
                {
                    this.attackTime = 10; //40
                    this.field_70846_g = 0;
                    this.func_70844_e(false);
                }

                if (this.field_70846_g > 1)
                {
                    float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
                    this.worldObj.playAuxSFXAtEntity(null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

                    for (int i = 0; i < 3; ++i)
                    {
                    	EntityCyroGuardianProjectile entitysmallfireball = new EntityCyroGuardianProjectile(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                        entitysmallfireball.posY = this.posY + (double)(this.height / 4.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(entitysmallfireball);
                        
                        EntityCyroGuardianProjectile entitysmallfireball2 = new EntityCyroGuardianProjectile(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                        entitysmallfireball2.posY = this.posY + (double)(this.height / 4.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(entitysmallfireball2);

                    }
                }
            }

            this.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float p_70069_1_) {}

    

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 10), 0.5F);
    	this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 10), 0.5F);
        if (p_70628_1_)
        {
            int j = this.rand.nextInt(4 + p_70628_2_);

            for (int k = 0; k < j; ++k)
            {
                this.dropItem(ItemsAether.cyro_rod, 16);
            }
        }
        
        int rand2 = (int)(1 + Math.random() * 3);
		switch (rand2)
        {
        case 1: this.dropItem(ItemsAether.divine_essence, 1 + rand.nextInt(2));
        break;
        case 2: 
        break;
        case 3: 
        break;
        }
    }

    public boolean func_70845_n()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_70844_e(boolean p_70844_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70844_1_)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, b0);
    }
    
    protected void onDeathUpdate()
    {
    	this.createloot(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
        this.setDead();
    }
    
    private void createloot(int p_70975_1_, int p_70975_2_, int p_70975_3_)
    {
		this.worldObj.setBlock(p_70975_1_, p_70975_2_, p_70975_3_, BlocksAether.treasure_chest);
		this.worldObj.setBlock(p_70975_1_, p_70975_2_ + 1, p_70975_3_, BlocksAether.treasure_chest);
    }
      
    @Override
    public boolean canDespawn() {
        return false;
    }
    
    public String getName() {
        return this.dataWatcher.getWatchableObjectString(19);
    }

    public void setBossName(String name) {
        this.dataWatcher.updateObject(19, name);
    }

    @Override
    public String getBossName() {
        return this.dataWatcher.getWatchableObjectString(19) + ", " + StatCollector.translateToLocal("tile.aether_legacy.cyro_guardian.name");
    }

	@Override
    public float getBossHealth() {
        return this.getHealth();
    }

    @Override
    public float getMaxBossHealth() {
        return this.getMaxHealth();
    }
    
}
