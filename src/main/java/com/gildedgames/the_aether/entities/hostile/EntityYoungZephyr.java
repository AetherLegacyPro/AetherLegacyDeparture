package com.gildedgames.the_aether.entities.hostile;

import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import java.util.*;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.ai.zephyr.AIEntityFlyingMob;
import com.gildedgames.the_aether.entities.ai.zephyr.EntityAetherIIMob;
import com.gildedgames.the_aether.entities.ai.zephyr.IFlyingMob;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.init.*;
import net.minecraft.block.*;

public class EntityYoungZephyr extends EntityAetherIIMob implements IMob, IFlyingMob
{
    boolean puffedUp;
    int attackCooldown;
    public AIEntityFlyingMob flyingAI;
    public float sinage;
    public float faceYaw;
    public float facePitch;
    
    public EntityYoungZephyr(final World world) {
        super(world);
        this.puffedUp = false;
        this.attackCooldown = 80;
        this.setSize(2.0f, 1.0f);
        this.flyingAI = new AIEntityFlyingMob(this.rand, (EntityLiving)this);
        this.tasks.addTask(1, (EntityAIBase)this.flyingAI);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
        this.setHealth(10.0f);
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting.getDifficultyId() == 0) {
            this.setDead();
        }
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObject(18, (Object)0.0f);
    }
    
    public boolean isAttacking() {
        return (this.dataWatcher.getWatchableObjectInt(17) & 0x1) != 0x0; //& 0x1) != 0x0
    }
    
    public int getAttackTimeSecs() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }
    
    public void setAttacking(final boolean attacking) {
        if (attacking) {
            this.dataWatcher.updateObject(17, (Object)1);
        }
        else {
            this.dataWatcher.updateObject(17, (Object)0);
        }
    }
    
    public void setAttackTimeSecs(final int time) {
        this.dataWatcher.updateObject(16, (Object)time);
    }
    
    public boolean isAIEnabled() {
        return true;
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.entityToAttack instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)this.entityToAttack;
            if (player.capabilities.isCreativeMode) {
                this.entityToAttack = null;
            }
        }
        if (this.getAttackTimeSecs() < this.attackCooldown + this.rand.nextInt(60) && this.isAttacking()) {
            this.flyingAI.shouldLook = false;
            if (!this.worldObj.isRemote) {
                this.setAttackTimeSecs(this.getAttackTimeSecs() + 1);
                this.setRotationYaw((float)Math.atan2(this.flyingAI.targetZ - this.posZ, this.flyingAI.targetX - this.posX));
            }
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            this.flyingAI.updateTime = -1;
            if (this.getAttackTimeSecs() >= 40) {
                if (this.worldObj.isRemote) {
                    final float rotation = this.getRotationYaw();
                    final double disX = Math.cos(rotation);
                    final double disZ = Math.sin(rotation);
                    final double disY = 3.000000142492354E-4;
                    final double range = MathHelper.sqrt_double(disX * disX + disY * disY + disZ * disZ);
                    for (int multiply = 0; multiply < 6; ++multiply) {
                        if (this.worldObj.isRemote) {
                            Aether.proxy.spawnCloudSmoke(this.worldObj, this.posX, this.posY + 1.0, this.posZ, this.worldObj.rand, 0.5, disX / range, disY / range, disZ / range, 0.3);
                        }
                    }
                }
                else {
                    final double d5 = this.flyingAI.targetX - this.posX;
                    final double d6 = this.flyingAI.targetY - (this.posY + 1.0);
                    final double d7 = this.flyingAI.targetZ - this.posZ;
                    final double range2 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
                    final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.addCoord(d5 / range2, d6 / range2, d7 / range2).expand(0.5, 0.1, 0.5));
                    for (final Object aList : list) {
                        final Entity entity1 = (Entity)aList;
                        if (!(entity1 instanceof EntityZephyr)) {
                            final double knockx = entity1.posX - this.posX;
                            final double knockz = entity1.posZ - this.posZ;
                            entity1.velocityChanged = true;
                            entity1.motionX = 0.05 * knockx;
                            entity1.motionY = this.rand.nextDouble() + 0.13;
                            entity1.motionZ = 0.05 * knockz;
                        }
                    }
                }
            }
        }
        else if (this.isAttacking()) {
            this.flyingAI.divepointSet = false;
            this.flyingAI.dive = false;
            this.flyingAI.updateTime = -1;
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            this.setAttackTimeSecs(0);
            this.setAttacking(false);
        }
        if (this.isAttacking()) {
            if (!this.puffedUp) {
                this.worldObj.playSoundAtEntity((Entity)this, "aether_legacy:aemob.zephyr.puff", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
                this.puffedUp = true;
            }
            else if (this.getAttackTimeSecs() == 25) {
                this.worldObj.playSoundAtEntity((Entity)this, "aether_legacy:aemob.zephyr.blow", this.getSoundVolume() * 0.6f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            }
        }
        else {
            this.puffedUp = false;
        }
    }
    
    public void facePosition(final double posX, final double posZ, final float prevFaceYaw, final float prevFacePitch) {
        final double dx = posX - this.posX;
        final double dz = posZ - this.posZ;
        final double d2 = 1.0;
        final double d3 = MathHelper.sqrt_double(dx * dx + dz * dz);
        final float f2 = (float)(Math.atan2(dz, dx) * 180.0 / 3.141592653589793) - 90.0f;
        final float f3 = (float)(-(Math.atan2(d2, d3) * 180.0 / 3.141592653589793));
        this.facePitch = this.updateRotation(this.facePitch, f3, prevFacePitch);
        this.setRotationYaw(this.faceYaw = this.updateRotation(this.faceYaw, f2, prevFaceYaw));
    }
    
    private float updateRotation(final float par1, final float par2, final float par3) {
        float f3 = MathHelper.wrapAngleTo180_float(par2 - par1);
        if (f3 > par3) {
            f3 = par3;
        }
        if (f3 < -par3) {
            f3 = -par3;
        }
        return par1 + f3;
    }
    
    public float getRotationYaw() {
        return this.dataWatcher.getWatchableObjectFloat(18);
    }
    
    public void setRotationYaw(final float yaw) {
        this.dataWatcher.updateObject(18, (Object)yaw);
    }
    
    protected void updateEntityActionState() {
        if (this.posY < -2.0 || this.posY > 130.0) {
            this.despawnEntity();
        }
    }
    
    @Override
    protected String getLivingSound() {
        return "aether_legacy:aemob.zephyr.call";
    }

    @Override
    protected String getHurtSound() {
        return "aether_legacy:aemob.zephyr.call";
    }
    
    public boolean canDespawn() {
        return true;
    }
    
    protected float getSoundVolume() {
        return 0.5f;
    }
    
    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        return this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass && this.rand.nextInt(AetherConfig.getVulturnusSpawnrate()) == 0 && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY), MathHelper.floor_double(this.posZ)) > 7 && super.getCanSpawnHere();
    }
    
    public int getMaxSpawnedInChunk() {
        return 1;
    }
    
    protected void fall(final float par1) {
    }
    
    protected void updateFallState(final double par1, final boolean par3) {
    }
    
    public void moveEntityWithHeading(final float par1, final float par2) {
        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929;
            this.motionY *= 0.800000011920929;
            this.motionZ *= 0.800000011920929;
        }
        else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
        }
        else {
            float f2 = 0.91f;
            if (this.onGround) {
                f2 = 0.54600006f;
                final Block i = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (i != Blocks.air) {
                    f2 = i.slipperiness * 0.91f;
                }
            }
            final float f3 = 0.16277136f / (f2 * f2 * f2);
            this.moveFlying(par1, par2, this.onGround ? (0.1f * f3) : 0.02f);
            f2 = 0.91f;
            if (this.onGround) {
                f2 = 0.54600006f;
                final Block j = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (j != Blocks.air) {
                    f2 = j.slipperiness * 0.91f;
                }
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        final double d0 = this.posX - this.prevPosX;
        final double d2 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 4.0f;
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4f;
        this.limbSwing += this.limbSwingAmount;
    }
    
    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.getEntity();
            
            entityplayer.triggerAchievement(AchievementsAether.aether_hunter);
            
        }
            
    }
    
    public boolean isOnLadder() {
        return false;
    }
    
    public boolean fly() {
        return true;
    }
    
    public float getFlySpeed() {
        return 0.35f;
    }
    
    public boolean hostileMob() {
        return true;
    }
    
    public void setSinage(final float sinage) {
        this.sinage = sinage;
    }
    
}