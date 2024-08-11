package com.gildedgames.the_aether.entities.passive.mountable;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.gildedgames.the_aether.entities.util.EntityZephyrooSaddleMount;

public class EntityAerwhaleMount extends EntityZephyrooSaddleMount {

    public float wingFold;

    public float wingAngle;

    private float aimingForFold;

    public int maxJumps;

    public int jumpsRemaining;

    private int ticks;

    public EntityAerwhaleMount(World world) {
        super(world);

        this.ticks = 0;
        this.maxJumps = 2;
        this.jumpsRemaining = 0;
        this.stepHeight = 2.0F;
        this.ignoreFrustumCheck = true;
        this.canJumpMidAir = true;

        this.setSize(2.0F, 2.0F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ItemsAether.void_tomato, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000298023224D);
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
            ((EntityPlayer) this.riddenByEntity).triggerAchievement(AchievementsAether.flying_aerwhale);
        }

        this.ticks++;

        this.wingAngle = this.wingFold * (float) Math.sin(this.ticks / 31.83098862F);
        this.wingFold += (this.aimingForFold - this.wingFold) / 5F;
        this.fallDistance = 0;
        this.fall();
    }
    
    public boolean attackEntityFrom(DamageSource ds, float i) {
		if (ds == DamageSource.inWall)
        {
            return false;
        }
		
		boolean flag = super.attackEntityFrom(ds, i);

		return flag;
	}

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setInteger("maxJumps", (short) this.maxJumps);
        compound.setInteger("jumpsRemaining", (short) this.jumpsRemaining);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.maxJumps = compound.getInteger("maxJumps");
        this.jumpsRemaining = compound.getInteger("jumpsRemaining");
    }

    @Override
    public double getMountedYOffset() {
        return 3D;
    }

    @Override
    public float getMountedMoveSpeed() {
        return 0.6F;
    }

    @Override
    protected void jump() {
        if (this.riddenByEntity == null) {
            super.jump();
        }
    }

    private void fall() {
        if (!this.onGround) {
            if (this.motionY < 0.0D && !this.isRiderSneaking()) {
                this.motionY *= 0.7D;
            }

            if (this.onGround && !this.worldObj.isRemote) {
                this.jumpsRemaining = this.maxJumps;
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.getCurrentEquippedItem();

        if (stack != null) {
            ItemStack currentStack = stack;

            if (currentStack.getItem() == Items.bucket) {
                Item milk = Items.milk_bucket;

                if (stack != null && stack.stackSize == 1) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(milk));
                } else if (!player.inventory.addItemStackToInventory(new ItemStack(milk))) {
                    if (!this.worldObj.isRemote) {
                        this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, player.posX, player.posY, player.posZ, new ItemStack(milk)));

                        if (!player.capabilities.isCreativeMode) {
                            --stack.stackSize;
                        }
                    }
                } else if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }
        }

        return super.interact(player);
    }

    @Override
	public String getLivingSound() {
		return "aether_legacy:aemob.aerwhale.call";
	}

	@Override
	protected String getHurtSound() {
		return "aether_legacy:aemob.aerwhale.death";
	}

	@Override
	protected String getDeathSound() {
		return "aether_legacy:aemob.aerwhale.death";
	}

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootLevel) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootLevel);
        int k;

        for (k = 0; k < j; ++k) {
            this.dropItem(ItemsAether.raw_aerwhale, 3);
        }

        j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootLevel);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.dropItem(ItemsAether.enchanted_aerwhale, 3);
            } else {
                this.dropItem(ItemsAether.raw_aerwhale, 3);
            }
        }

        super.dropFewItems(recentlyHit, lootLevel);
    }
    
    @Override
   	public boolean getCanSpawnHere() {
   	      final int i = MathHelper.floor_double(this.posX);
   	      final int j = MathHelper.floor_double(this.boundingBox.minY);
   	      final int k = MathHelper.floor_double(this.posZ);
   	      final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);          
   	      return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn;
   	                       
   	}

    @Override
    protected double getMountJumpStrength() {
        return 14.0D;
    }
    
    public EntityAerwhaleMount createChild(EntityAgeable p_90011_1_)
    {
    	EntityAerwhaleMount entityaerwhale = new EntityAerwhaleMount(this.worldObj);

        return entityaerwhale;
    }
    
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getItem() == ItemsAether.void_tomato;
    }

}