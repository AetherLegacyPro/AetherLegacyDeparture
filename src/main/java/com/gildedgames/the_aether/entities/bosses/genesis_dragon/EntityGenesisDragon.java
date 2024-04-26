package com.gildedgames.the_aether.entities.bosses.genesis_dragon;

import java.util.Iterator;
import java.util.List;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.util.AetherNameGen;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.player.PlayerAether;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EntityGenesisDragon extends EntityFlying implements IAetherBoss, GIEntityMultiPart, IMob
{
    public double targetX;
    public double targetY;
    public double targetZ;
    /** Ring buffer array for the last 64 Y-positions and yaw rotations. Used to calculate offsets for the animations. */
    public double[][] ringBuffer = new double[64][3];
    /** Index into the ring buffer. Incremented once per tick and restarts at 0 once it reaches the end of the buffer. */
    public int ringBufferIndex = -1;
    /** An array containing all body parts of this dragon */
    public EntityGenesisDragonPart[] dragonPartArray;
    /** The head bounding box of a dragon */
    public EntityGenesisDragonPart dragonPartHead;
    /** The body bounding box of a dragon */
    public EntityGenesisDragonPart dragonPartBody;
    public EntityGenesisDragonPart dragonPartTail1;
    public EntityGenesisDragonPart dragonPartTail2;
    public EntityGenesisDragonPart dragonPartTail3;
    public EntityGenesisDragonPart dragonPartWing1;
    public EntityGenesisDragonPart dragonPartWing2;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
    public int courseChangeCooldown;
    private int field_70846_g;
    /** Animation time at previous tick. */
    public float prevAnimTime;
    /** Animation time, used to control the speed of the animation cycles (wings flapping, jaw opening, etc.) */
    public float animTime;
    /** Force selecting a new flight target at next tick if set to true. */
    public boolean forceNewTarget;
    /** Activated if the dragon is flying though obsidian, white stone or bedrock. Slows movement and animation speed. */
    public boolean slowed = false;
    private Entity target;
    public int deathTicks;
    /** The current endercrystal that is healing this dragon */
    public EntityEnderCrystal healingEnderCrystal;
    public EntityLivingBase shootingEntity;
    private static final String __OBFID = "CL_00001659";

    public EntityGenesisDragon(final World p_i1700_1_)
    {
        super(p_i1700_1_);
        this.dragonPartArray = new EntityGenesisDragonPart[] {this.dragonPartHead = new EntityGenesisDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityGenesisDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityGenesisDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityGenesisDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityGenesisDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityGenesisDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityGenesisDragonPart(this, "wing", 4.0F, 4.0F)};
        this.setHealth(this.getMaxHealth());
        this.setSize(16.0F, 8.0F);
        this.noClip = true;
        this.isImmuneToFire = true;
        this.targetY = 100.0D;
        this.ignoreFrustumCheck = true;
        this.dataWatcher.updateObject(19, AetherNameGen.valkGen());
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(400.0D);
    }
    
    public void registerEntityAI() {
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F, 400.0F));
    }
    
    @Override
	protected boolean isAIEnabled()
    {
        return true;
    }
    
    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(19, AetherNameGen.valkGen());
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        
        nbttagcompound.setString("BossName", this.getName());
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        
        this.setBossName(nbttagcompound.getString("BossName"));
    }
    
    /**
     * Returns a double[3] array with movement offsets, used to calculate trailing tail/neck positions. [0] = yaw
     * offset, [1] = y offset, [2] = unused, always 0. Parameters: buffer index offset, partial ticks.
     */
    public double[] getMovementOffsets(int p_70974_1_, float p_70974_2_)
    {
        if (this.getHealth() <= 0.0F)
        {
            p_70974_2_ = 0.0F;
        }

        p_70974_2_ = 1.0F - p_70974_2_;
        int j = this.ringBufferIndex - p_70974_1_ * 1 & 63;
        int k = this.ringBufferIndex - p_70974_1_ * 1 - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.ringBuffer[j][0];
        double d1 = MathHelper.wrapAngleTo180_double(this.ringBuffer[k][0] - d0);
        adouble[0] = d0 + d1 * (double)p_70974_2_;
        d0 = this.ringBuffer[j][1];
        d1 = this.ringBuffer[k][1] - d0;
        adouble[1] = d0 + d1 * (double)p_70974_2_;
        adouble[2] = this.ringBuffer[j][2] + (this.ringBuffer[k][2] - this.ringBuffer[j][2]) * (double)p_70974_2_;
        return adouble;
    }
    
    public void FireBreath() {
    	if (this.worldObj.isRemote) {
    		
    		if (net.minecraft.client.Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
    			return;
    		}
    		
    		Vec3 look = this.getLookVec();

    		double dist = -12.9; //0.9 -3.9
    		double px = this.posX + look.xCoord * dist;
    		double py = this.posY + 2.25 + look.yCoord * dist; //0.25
    		double pz = this.posZ + look.zCoord * dist;

    			for (int i = 0; i < 6; i++)
    			{
    			double dx = look.xCoord;
    			double dy = look.yCoord;
    			double dz = look.zCoord;

    			double spread = 6 + this.getRNG().nextDouble() * 2.5; //5
    			double velocity = 1.15 + this.getRNG().nextDouble() * 0.45; //0.15

    			dx += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
    			dy += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
    			dz += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
    			dx *= velocity;
    			dy *= velocity;
    			dz *= velocity;
    			
    			NewAetherParticleHandler.DRAGON_FLAME.spawn(worldObj, px, py, pz, -dx, dy, -dz, 0.0f, new Object[0]);
    			}   	  
    	}
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    //@Override
    public void onLivingUpdate()
    {
    	
        float f;
        float f1;       
        
        int rand2 = (int)(1 + Math.random() * 20);
		switch (rand2)
        {
        case 1: 
		 if (this.rand.nextInt(25) == 1 && !this.worldObj.isRemote) {
		        EntityCrystalDragon var = new EntityCrystalDragon(this.worldObj);
		        var.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
		        var.setAttackTarget(this.getAttackTarget());

		        if (!this.worldObj.isRemote) {
		           this.worldObj.spawnEntityInWorld(var);
		          }
		        
		        break;
         	}
        }
        
        if (this.worldObj.isRemote)
        {
            f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
            f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);

            if (f1 <= -0.3F && f >= -0.3F)
            {
                this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
            }
        }

        this.prevAnimTime = this.animTime;
        float f2;

        if (this.getHealth() <= 0.0F)
        {
            f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle("largeexplode", this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 20.0F + 1.0F); //10.0
            f *= (float)Math.pow(2.0D, this.motionY);

            if (this.slowed)
            {
                this.animTime += f * 0.5F;
            }
            else
            {
                this.animTime += f;
            }

            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

            if (this.ringBufferIndex < 0)
            {
                for (int i = 0; i < this.ringBuffer.length; ++i)
                {
                    this.ringBuffer[i][0] = (double)this.rotationYaw;
                    this.ringBuffer[i][1] = this.posY;
                }
            }

            if (++this.ringBufferIndex == this.ringBuffer.length)
            {
                this.ringBufferIndex = 0;
            }

            this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
            this.ringBuffer[this.ringBufferIndex][1] = this.posY;
            double d0;
            double d1;
            double d2;
            double d10;
            float f12;

            if (this.worldObj.isRemote)
            {
                if (this.newPosRotationIncrements > 0)
                {
                    d10 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                    d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                    d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                    d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
                    this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
                    this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                    --this.newPosRotationIncrements;
                    this.setPosition(d10, d0, d1);
                    this.setRotation(this.rotationYaw, this.rotationPitch);
                }
            }
            else
            {
                d10 = this.targetX - this.posX;
                d0 = this.targetY - this.posY;
                d1 = this.targetZ - this.posZ;
                d2 = d10 * d10 + d0 * d0 + d1 * d1;

                if (this.target != null)
                {
                    this.targetX = this.target.posX;
                    this.targetZ = this.target.posZ;
                    double d3 = this.targetX - this.posX;
                    double d5 = this.targetZ - this.posZ;
                    double d7 = Math.sqrt(d3 * d3 + d5 * d5);
                    double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;

                    if (d8 > 10.0D)
                    {
                        d8 = 10.0D;
                    }

                    this.targetY = this.target.boundingBox.minY + d8;
                }
                else
                {
                    this.targetX += this.rand.nextGaussian() * 2.0D;
                    this.targetZ += this.rand.nextGaussian() * 2.0D;
                }

                if (this.forceNewTarget || d2 < 100.0D || d2 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
                {
                    this.setNewTarget();
                }

                d0 /= (double)MathHelper.sqrt_double(d10 * d10 + d1 * d1);
                f12 = 0.6F;

                if (d0 < (double)(-f12))
                {
                    d0 = (double)(-f12);
                }

                if (d0 > (double)f12)
                {
                    d0 = (double)f12;
                }

                this.motionY += d0 * 0.10000000149011612D;
                this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
                double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
                double d6 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);

                if (d6 > 50.0D)
                {
                    d6 = 50.0D;
                }

                if (d6 < -50.0D)
                {
                    d6 = -50.0D;
                }

                Vec3 vec3 = Vec3.createVectorHelper(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
                Vec3 vec32 = Vec3.createVectorHelper((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F))).normalize();
                float f5 = (float)(vec32.dotProduct(vec3) + 0.5D) / 1.5F;

                if (f5 < 0.0F)
                {
                    f5 = 0.0F;
                }

                this.randomYawVelocity *= 0.8F;
                float f6 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F; //1
                double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D; //1

                if (d9 > 40.0D)
                {
                    d9 = 40.0D;
                }

                this.randomYawVelocity = (float)((double)this.randomYawVelocity + d6 * (0.699999988079071D / d9 / (double)f6));
                this.rotationYaw += this.randomYawVelocity * 0.1F;
                float f7 = (float)(2.0D / (d9 + 1.0D));
                float f8 = 0.06F;
                this.moveFlying(0.0F, -1.0F, f8 * (f5 * f7 + (1.0F - f7)));

                if (this.slowed)
                {
                    this.moveEntity(this.motionX * 0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * 0.800000011920929D);
                }
                else
                {
                    this.moveEntity(this.motionX, this.motionY, this.motionZ);
                }

                Vec3 vec31 = Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ).normalize();
                float f9 = (float)(vec31.dotProduct(vec32) + 1.0D) / 2.0F;
                f9 = 0.8F + 0.15F * f9;
                this.motionX *= (double)f9;
                this.motionZ *= (double)f9;
                this.motionY *= 0.9100000262260437D; //0.0
            }

            this.renderYawOffset = this.rotationYaw;
            this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
            this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
            this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
            this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
            this.dragonPartBody.height = 3.0F;
            this.dragonPartBody.width = 5.0F;
            this.dragonPartWing1.height = 2.0F;
            this.dragonPartWing1.width = 4.0F;
            this.dragonPartWing2.height = 3.0F;
            this.dragonPartWing2.width = 4.0F;
            f1 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * (float)Math.PI;
            f2 = MathHelper.cos(f1);
            float f10 = -MathHelper.sin(f1);
            float f3 = this.rotationYaw * (float)Math.PI / 180.0F;
            float f11 = MathHelper.sin(f3);
            float f4 = MathHelper.cos(f3);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.setLocationAndAngles(this.posX + (double)(f11 * 0.5F), this.posY, this.posZ - (double)(f4 * 0.5F), 0.0F, 0.0F);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.setLocationAndAngles(this.posX + (double)(f4 * 4.5F), this.posY + 2.0D, this.posZ + (double)(f11 * 4.5F), 0.0F, 0.0F);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.setLocationAndAngles(this.posX - (double)(f4 * 4.5F), this.posY + 2.0D, this.posZ - (double)(f11 * 4.5F), 0.0F, 0.0F);

            if (!this.worldObj.isRemote && this.hurtTime == 0)
            {
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble1 = this.getMovementOffsets(5, 1.0F);
            double[] adouble = this.getMovementOffsets(0, 1.0F);
            f12 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            float f13 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            this.dragonPartHead.onUpdate();
            this.dragonPartHead.setLocationAndAngles(this.posX + (double)(f12 * 5.5F * f2), this.posY + (adouble[1] - adouble1[1]) * 1.0D + (double)(f10 * 5.5F), this.posZ - (double)(f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int j = 0; j < 3; ++j)
            {
            	EntityGenesisDragonPart entitydragonpart = null;

                if (j == 0)
                {
                    entitydragonpart = this.dragonPartTail1;
                }

                if (j == 1)
                {
                    entitydragonpart = this.dragonPartTail2;
                }

                if (j == 2)
                {
                    entitydragonpart = this.dragonPartTail3;
                }

                double[] adouble2 = this.getMovementOffsets(12 + j * 2, 1.0F);
                float f14 = this.rotationYaw * (float)Math.PI / 180.0F + this.simplifyAngle(adouble2[0] - adouble1[0]) * (float)Math.PI / 180.0F * 1.0F;
                float f15 = MathHelper.sin(f14);
                float f16 = MathHelper.cos(f14);
                float f17 = 1.5F;
                float f18 = (float)(j + 1) * 2.0F;
                entitydragonpart.onUpdate();
                entitydragonpart.setLocationAndAngles(this.posX - (double)((f11 * f17 + f15 * f18) * f2), this.posY + (adouble2[1] - adouble1[1]) * 1.0D - (double)((f18 + f17) * f10) + 1.5D, this.posZ + (double)((f4 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
            }

            
        }
        
        List<Entity> volume = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(10, 10, 10));
        
        for(Entity entity : volume) {
        	if(entity instanceof EntityPlayer && this.canEntityBeSeen(entity)) {
        		if (worldObj.isRemote) 
        	    this.FireBreath();
        		}
        	}
    }
    
    public void playBreathSound() {
		worldObj.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "nova_craft:deepoid.breath", rand.nextFloat() * 0.5F, rand.nextFloat() * 0.5F); //mob.enderdragon.growl
	}

    /**
     * Pushes all entities inside the list away from the enderdragon.
     */
    private void collideWithEntities(List p_70970_1_)
    {
        double d0 = (this.dragonPartBody.boundingBox.minX + this.dragonPartBody.boundingBox.maxX) / 2.0D;
        double d1 = (this.dragonPartBody.boundingBox.minZ + this.dragonPartBody.boundingBox.maxZ) / 2.0D;
        Iterator iterator = p_70970_1_.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();
            if (entity instanceof EntityLivingBase)
            {	
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.addVelocity(d2 / d4 * 8.0D, 0.20000000298023224D, d3 / d4 * 8.0D);
            }
            
        }
        
        if (worldObj.isRemote) 
    	this.FireBreath();
    }

    /**
     * Attacks all entities inside this list, dealing 25 hearts of damage.
     */
    private void attackEntitiesInList(List p_70971_1_)
    {
        for (int i = 0; i < p_70971_1_.size(); ++i)
        {
            Entity entity = (Entity)p_70971_1_.get(i);
       if(!((entity instanceof EntityCrystalDragon))) { 
    	   
            if (entity instanceof EntityLivingBase)
            {            
                entity.attackEntityFrom(DamageSource.magic, 8.0F);
                entity.attackEntityFrom(DamageSource.outOfWorld, 2.0F);        
                
                {
                if (!entity.isImmuneToFire())
            	{
                	entity.setFire(20);
                	entity.attackEntityFrom(DamageSource.inFire, 24.0F);
                	entity.attackEntityFrom(DamageSource.magic, 15.0F);
            	}
        		else
        		{
        			entity.setFire(20);
        			entity.attackEntityFrom(DamageSource.generic, 22.0F);
                	entity.attackEntityFrom(DamageSource.magic, 15.0F);	
        			//nice try
        		  }
                }
             }
            
          }
       
       		if (worldObj.isRemote) //good
       		this.FireBreath();
        }
        

    }

    /**
     * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
     */
    private void setNewTarget()
    {
        this.forceNewTarget = false;

        if (this.rand.nextInt(3) == 0 && !this.worldObj.playerEntities.isEmpty())
        {
            this.target = (Entity)this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));   
                      
        }
        else
        {
            boolean flag = false;

            do
            {
                this.targetX = 0.0D;
                this.targetY = (double)(70.0F + this.rand.nextFloat() * 50.0F);
                this.targetZ = 0.0D;
                this.targetX += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                this.targetZ += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                double d0 = this.posX - this.targetX;
                double d1 = this.posY - this.targetY;
                double d2 = this.posZ - this.targetZ;
                flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
                              
            }
            while (!flag);

            this.target = null;
        }
    }

    /**
     * Simplifies the value of a number by adding/subtracting 180 to the point that the number is between -180 and 180.
     */
    private float simplifyAngle(double p_70973_1_)
    {
        return (float)MathHelper.wrapAngleTo180_double(p_70973_1_);
    }

    public boolean aattackEntityFromPart(EntityGenesisDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_)
    {
        if (p_70965_1_ != this.dragonPartHead)
        {
            p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
        }

        float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
        float f2 = MathHelper.sin(f1);
        float f3 = MathHelper.cos(f1);
        this.targetX = this.posX + (double)(f2 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
        this.targetY = this.posY + (double)(this.rand.nextFloat() * 3.0F) + 1.0D;
        this.targetZ = this.posZ - (double)(f3 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
        this.target = null;

        if (p_70965_2_.getEntity() instanceof EntityPlayer || p_70965_2_.isExplosion())
        {
            this.func_82195_e(p_70965_2_, p_70965_3_);
            this.attackEntityFrom(p_70965_2_, p_70965_3_);
        }

        return true;
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource ds, float i) {
        if (ds.getEntity() instanceof EntityPlayer) {
        	
        	if (ds.isExplosion())
            {
                return false;
            }
        	
        	Entity entity = ds.getEntity();
        	
        	if (entity instanceof EntityPlayer)
            {
        	
            int random1 = (int)(1 + Math.random() * 50);
       	 	if(random1 == 1 ) {
       	 	EntityCrystalDragon dragon = new EntityCrystalDragon(this.worldObj);
       	 		dragon.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
       	 		dragon.setAttackTarget(this.getAttackTarget());

               if (!this.worldObj.isRemote) {
                   this.worldObj.spawnEntityInWorld(dragon);
               	}
               
       		}
       	 
            }
        	
        	if(worldObj.isRemote) 
            this.FireBreath();
        	
        }
        
        if (ds.getEntity() == null || !(ds.getEntity() instanceof EntityPlayer)) {
   		 return false;
        }
        EntityPlayer player = (EntityPlayer) ds.getEntity();
   		boolean flag = super.attackEntityFrom(ds, Math.max(0, i));
   	
   		if (flag) {
   		if (this.getHealth() <= 0 || this.isDead) {
   		PlayerAether.get(player).setFocusedBoss(null);
   			}
   		}
   		PlayerAether.get(player).setFocusedBoss(this);
        
   		return flag;
    }

    protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_)
    {
        return super.attackEntityFrom(p_82195_1_, p_82195_2_);
    }
    
    /**
     * handles entity death timer, experience orb and particle creation
     */
    
    public int getTotalArmorValue()
    {
        return 17;
    }
    
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                i = 1000;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            if (this.deathTicks == 1)
            {
                this.worldObj.playBroadcastSound(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            i = 50;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
            }

            //this.createloot(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
            this.setDead();
        }
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {  	
    	  this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 14), 0.5F);
    	//this.entityDropItem(new ItemStack(NovaCraftItems.deep_one_scales, 7 + p_70628_2_), 0.5F);
    	//this.entityDropItem(new ItemStack(NovaCraftItems.deep_one_bone, 2 + p_70628_2_), 0.5F);
    	//this.entityDropItem(new ItemStack(NovaCraftItems.anomalous_essence, 12 + p_70628_2_), 0.5F);
               
    }

    /**
     * Return the Entity parts making up this Entity (currently only for dragons)
     */
    public Entity[] getParts()
    {
        return this.dragonPartArray;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public World func_82194_d()
    {
        return this.worldObj;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.enderdragon.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.enderdragon.hit";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 3.0F;
    }
    
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
        return this.dataWatcher.getWatchableObjectString(19) + ", " + StatCollector.translateToLocal("tile.aether_legacy.genesis_dragon.name");
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


