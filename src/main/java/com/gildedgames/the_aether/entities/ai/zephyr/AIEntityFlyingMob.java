package com.gildedgames.the_aether.entities.ai.zephyr;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.util.*;

public class AIEntityFlyingMob extends EntityAIBase
{
    public EntityLiving flyingMob;
    public Entity targetedEntity;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    public double targetX;
    public double targetY;
    public double targetZ;
    public double distReqForTarget;
    public boolean divepointSet;
    public boolean dive;
    public boolean shouldLook;
    public Random rand;
    public int xpov;
    public int zpov;
    public int updateTime;
    public int aggroCooldown;
    public int attackCounter;
    public float sinage;
    
    public AIEntityFlyingMob(final Random rand, final EntityLiving flyingMob) {
        this.distReqForTarget = 15.0;
        this.divepointSet = false;
        this.dive = false;
        this.shouldLook = true;
        this.xpov = 1;
        this.zpov = 1;
        this.updateTime = 0;
        this.targetedEntity = null;
        this.aggroCooldown = 0;
        this.attackCounter = 0;
        this.flyingMob = flyingMob;
        this.rand = rand;
        this.xpov = (this.rand.nextBoolean() ? -1 : 1);
        this.zpov = (this.rand.nextBoolean() ? -1 : 1);
    }
    
    public boolean shouldExecute() {
        return this.flyingMob instanceof IFlyingMob && ((IFlyingMob)this.flyingMob).fly();
    }
    
    public void updateTask() {
        if (this.targetedEntity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)this.targetedEntity;
            if (player.capabilities.isCreativeMode) {
                this.targetedEntity = null;
            }
        }
        super.updateTask();
        ((IFlyingMob)this.flyingMob).setSinage(this.sinage);
        final double d = this.waypointX - this.flyingMob.posX;
        final double d2 = this.waypointZ - this.flyingMob.posZ;
        final float rotateTo = -(float)Math.atan2(d, d2);
        this.flyingMob.getLookHelper().setLookPosition(this.waypointX, this.waypointY, this.waypointZ, rotateTo, 0.0f);
        if (this.flyingMob.hurtTime > 0) {
            this.sinage += 0.9f;
        }
        else {
            this.sinage += 0.2f;
        }
        if (this.sinage > 6.283186f) {
            this.sinage -= 6.283186f;
        }
        if (this.targetedEntity != null && (this.targetedEntity.isDead || Math.abs(this.flyingMob.posX - this.targetedEntity.posX) > this.distReqForTarget || Math.abs(this.flyingMob.posZ - this.targetedEntity.posZ) > this.distReqForTarget)) {
            this.targetedEntity = null;
        }
        if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
            final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(this.flyingMob.posX, this.flyingMob.posY, this.flyingMob.posZ, this.flyingMob.posX, this.flyingMob.posY, this.flyingMob.posZ).expand(this.distReqForTarget, 200.0, this.distReqForTarget);
            final List list = this.flyingMob.worldObj.getEntitiesWithinAABB((Class)EntityPlayer.class, bounds);
            for (int i = 0; i < list.size(); ++i) {
                if (!list.isEmpty()) { //&& !list.get(i).capabilities.isCreativeMode
                    this.targetedEntity = (Entity)list.get(i);
                }
            }
            if (this.targetedEntity != null) {
                this.aggroCooldown = 20;
            }
        }
        if (this.targetedEntity == null || !((IFlyingMob)this.flyingMob).hostileMob()) {
            this.randomMovement();
        }
        else if (this.targetedEntity != null) {
            if (this.dive) {
                this.dive();
            }
            else {
                this.orbitPlayer();
            }
        }
    }
    
    private boolean isCourseTraversable(final double d, final double d1, final double d2, final double d3) {
        final double d4 = (this.waypointX - this.flyingMob.posX) / d3;
        final double d5 = (this.waypointY - this.flyingMob.posY) / d3;
        final double d6 = (this.waypointZ - this.flyingMob.posZ) / d3;
        final AxisAlignedBB axisalignedbb = this.flyingMob.boundingBox.copy();
        for (int i = 1; i < d3; ++i) {
            axisalignedbb.offset(d4, d5, d6);
            if (!this.flyingMob.worldObj.getCollidingBoundingBoxes(this.flyingMob, axisalignedbb).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private void randomMovement() {
        final double d = this.waypointX - this.flyingMob.posX;
        final double d2 = this.waypointY - this.flyingMob.posY;
        final double d3 = this.waypointZ - this.flyingMob.posZ;
        final double d4 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
        if (d4 < 4.0 || d4 > 40.0) {
            this.waypointX = this.flyingMob.posX + (this.rand.nextFloat() * 2.0f - 1.0f) * 30.0f;
            this.waypointY = this.flyingMob.posY + (this.rand.nextFloat() * 2.0f - 1.0f) * 30.0f;
            this.waypointZ = this.flyingMob.posZ + (this.rand.nextFloat() * 2.0f - 1.0f) * 30.0f;
        }
        if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d4)) {
            final float speed = ((IFlyingMob)this.flyingMob).getFlySpeed();
            this.flyingMob.motionX = d / d4 * speed;
            this.flyingMob.motionY = d2 / d4 * speed;
            this.flyingMob.motionZ = d3 / d4 * speed;
        }
        else {
            this.waypointX = this.flyingMob.posX + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointY = this.flyingMob.posY + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
            this.waypointZ = this.flyingMob.posZ + (this.rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
        }
    }
    
    private void dive() {
        final float speed = ((IFlyingMob)this.flyingMob).getFlySpeed();
        if (!this.divepointSet) {
            final double randomHeight = this.rand.nextFloat() * 2.0;
            final double distance = MathHelper.sqrt_double(9.0 - randomHeight * randomHeight);
            final double x = this.rand.nextFloat() * distance;
            final double y = MathHelper.sqrt_double(distance * distance - x * x);
            this.waypointX = this.targetedEntity.posX + x * this.xpov;
            this.waypointY = this.targetedEntity.posY + randomHeight;
            this.waypointZ = this.targetedEntity.posZ + y * this.zpov;
            this.targetX = this.targetedEntity.posX;
            this.targetY = this.targetedEntity.posY;
            this.targetZ = this.targetedEntity.posZ;
            this.divepointSet = true;
            final double d = this.waypointX - this.flyingMob.posX;
            final double d2 = this.waypointY - this.flyingMob.posY;
            final double d3 = this.waypointZ - this.flyingMob.posZ;
            final double d4 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
            if (!this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d4)) {
                this.divepointSet = false;
            }
        }
        else {
            final double d5 = this.waypointX - this.flyingMob.posX;
            final double d6 = this.waypointY - this.flyingMob.posY;
            final double d7 = this.waypointZ - this.flyingMob.posZ;
            final double d8 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d8)) {
                this.flyingMob.motionX = d5 / d8 * speed;
                this.flyingMob.motionY = d6 / d8 * speed;
                this.flyingMob.motionZ = d7 / d8 * speed;
            }
            else {
                this.divepointSet = false;
                this.dive = false;
                this.updateTime = -1;
            }
            if (d8 < 1.0) {
                if (!this.flyingMob.worldObj.isRemote) {
                    ((IFlyingMob)this.flyingMob).setAttacking(true);
                }
                this.divepointSet = true;
            }
        }
    }
    
    private void orbitPlayer() {
        final double d = this.waypointX - this.flyingMob.posX;
        final double d2 = this.waypointY - this.flyingMob.posY;
        final double d3 = this.waypointZ - this.flyingMob.posZ;
        final double d4 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
        this.flyingMob.rotationYaw = (float)Math.toDegrees(Math.atan2(d3, d));
        if (this.updateTime-- < 0) {
            this.updateTime = this.rand.nextInt(5) + 40;
            final double randomHeight = this.rand.nextFloat() * 10.0;
            final double distance = MathHelper.sqrt_double(196.0 - randomHeight * randomHeight);
            final double x = this.rand.nextFloat() * distance;
            final double y = MathHelper.sqrt_double(distance * distance - x * x);
            if (this.rand.nextInt(8) == 0) {
                this.xpov = (this.rand.nextBoolean() ? -1 : 1);
                this.zpov = (this.rand.nextBoolean() ? -1 : 1);
            }
            this.waypointX = this.targetedEntity.posX + x * this.xpov;
            this.waypointY = this.targetedEntity.posY + randomHeight;
            this.waypointZ = this.targetedEntity.posZ + y * this.zpov;
        }
        if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d4)) {
            final float speed = ((IFlyingMob)this.flyingMob).getFlySpeed();
            this.flyingMob.motionX = d / d4 * speed;
            this.flyingMob.motionY = d2 / d4 * speed;
            this.flyingMob.motionZ = d3 / d4 * speed;
        }
        if (d4 < 1.0) {
            if (this.rand.nextInt(6) == 0) {
                this.dive = true;
            }
            this.flyingMob.motionX = 0.0;
            this.flyingMob.motionY = 0.0;
            this.flyingMob.motionZ = 0.0;
        }
    }
}
