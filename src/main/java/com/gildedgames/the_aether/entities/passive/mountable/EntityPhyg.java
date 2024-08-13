package com.gildedgames.the_aether.entities.passive.mountable;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.gildedgames.the_aether.entities.util.EntitySaddleMount;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

public class EntityPhyg extends EntitySaddleMount {

    public float wingFold;

    public float wingAngle;

    private float aimingForFold;

    public int maxJumps;

    public int jumpsRemaining;

    public int ticks;

    public EntityPhyg(World world) {
        super(world);

        this.jumpsRemaining = 0;
        this.maxJumps = 1;
        this.stepHeight = 1.0F;

        this.ignoreFrustumCheck = true;
        this.canJumpMidAir = true;

        this.setSize(0.9F, 1.3F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ItemsAether.blueberry, false));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }
    
    @Override
   	public boolean getCanSpawnHere() {
   	      final int i = MathHelper.floor_double(this.posX);
   	      final int j = MathHelper.floor_double(this.boundingBox.minY);
   	      final int k = MathHelper.floor_double(this.posZ);
   	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
   	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn && this.rand.nextInt(AetherConfig.getPhygSpawnrate()) == 0 && super.getCanSpawnHere();
   	                       
   	}

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.isOnGround()) {
            this.wingAngle *= 0.8F;
            this.aimingForFold = 0.1F;
            this.jumpsRemaining = this.maxJumps;
        } else {
            this.aimingForFold = 1.0F;
        }

        if (this.riddenByEntity instanceof EntityPlayer) {
            ((EntityPlayer) this.riddenByEntity).triggerAchievement(AchievementsAether.flying_pig);
        }

        this.ticks++;

        this.wingAngle = this.wingFold * (float) Math.sin(this.ticks / 31.83098862F);
        this.wingFold += (this.aimingForFold - this.wingFold) / 5F;
        this.fallDistance = 0;
        this.fall();
    }

    @Override
    protected String getLivingSound() {
        return "mob.pig.say";
    }

    @Override
    protected String getHurtSound() {
        return "mob.pig.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.pig.death";
    }

    @Override
    public double getMountedYOffset() {
        return 0.65D;
    }

    @Override
    public float getMountedMoveSpeed() {
        return 0.3F;
    }

    @Override
    protected void jump() {
        if (this.riddenByEntity == null) {
            super.jump();
        }
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootLevel) {
        int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootLevel);
        int k;

        for (k = 0; k < j; ++k) {
            this.dropItem(Items.feather, 1);
        }

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.dropItem(Items.cooked_porkchop, 1);
            } else {
                this.dropItem(Items.porkchop, 1);
            }
        }

        super.dropFewItems(recentlyHit, lootLevel);
    }

    private void fall() {
        if (this.motionY < 0.0D && !this.isRiderSneaking()) {
            this.motionY *= 0.6D;
        }

        if (!this.isOnGround() && !this.isJumping) {
            if (this.isOnGround() && !this.worldObj.isRemote) {
                this.jumpsRemaining = this.maxJumps;
            }
        }
    }

    @Override
    protected double getMountJumpStrength() {
        return 5.0D;
    }

    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        this.maxJumps = nbttagcompound.getShort("Jumps");
        this.jumpsRemaining = nbttagcompound.getShort("Remaining");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);

        nbttagcompound.setShort("Jumps", (short) this.maxJumps);
        nbttagcompound.setShort("Remaining", (short) this.jumpsRemaining);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        return new EntityPhyg(this.worldObj);
    }

}