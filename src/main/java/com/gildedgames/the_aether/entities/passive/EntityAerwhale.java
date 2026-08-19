package com.gildedgames.the_aether.entities.passive;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.util.EntityAetherSaddleMount;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAerwhale extends EntityAetherSaddleMount {
    private static final int TAMED_WATCHER_ID = 24;
    private static final int VOID_TOMATO_TAME_CHANCE = 24;

    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;

    private EntityLivingBase targetedEntity;
    private int targetObstructedTicks;

    private String aerwhaleOwnerUUID = "";

    public float wingFold;
    public float wingAngle;

    private float aimingForFold;
    private int animationTicks;

    public int maxJumps;
    public int jumpsRemaining;

    public EntityAerwhale(World world) {
        super(world);

        this.targetObstructedTicks = 0;
        this.animationTicks = 0;

        this.maxJumps = 2;
        this.jumpsRemaining = 0;

        this.stepHeight = 2.0F;
        this.ignoreFrustumCheck = true;
        this.canJumpMidAir = true;

        this.setSize(2.0F, 2.0F);
        this.isImmuneToFire = false;
        this.experienceValue = 2;

        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ItemsAether.void_tomato, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(TAMED_WATCHER_ID, (byte)0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.13000000298023224D);
    }

    public boolean isTamed() {
        return this.dataWatcher.getWatchableObjectByte(TAMED_WATCHER_ID) != 0;
    }

    public void setTamed(boolean tamed) {
        this.dataWatcher.updateObject(TAMED_WATCHER_ID, tamed ? (byte)1 : (byte)0);
        if (tamed) {
            this.func_110163_bv();
        }
    }

    public void setOwnerUUID(String ownerUUID) {
        this.aerwhaleOwnerUUID = ownerUUID == null ? "" : ownerUUID;
    }

    public String getOwnerUUID() {
        return this.aerwhaleOwnerUUID;
    }

    public boolean isOwner(EntityPlayer player) {
        return player != null && this.aerwhaleOwnerUUID != null && this.aerwhaleOwnerUUID.length() > 0 && this.aerwhaleOwnerUUID.equals(player.getUniqueID().toString());
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.getCurrentEquippedItem();

        if (!this.isTamed()) {
            if (stack == null || stack.getItem() != ItemsAether.void_tomato) {
                return false;
            }

            if (!this.worldObj.isRemote) {
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;

                    if (stack.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    }
                }

                if (this.rand.nextInt(VOID_TOMATO_TAME_CHANCE) == 0) {
                    this.setTamed(true);
                    this.setOwnerUUID(player.getUniqueID().toString());
                    this.setAttackTarget(null);
                    this.targetedEntity = null;
                    this.targetObstructedTicks = 0;
                    this.setHealth(this.getMaxHealth());
                    this.worldObj.setEntityState(this, (byte)7);
                } else {
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        if (!this.worldObj.isRemote && !this.isOwner(player) && !player.capabilities.isCreativeMode) {
            return false;
        }

        boolean mountInteraction = super.interact(player);
        if (mountInteraction) {
            return true;
        }

        if (stack != null && stack.getItem() == Items.bucket) {
            if (!this.worldObj.isRemote) {
                ItemStack milkBucket = new ItemStack(Items.milk_bucket);

                if (stack.stackSize == 1) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, milkBucket);
                } else {
                    if (!player.inventory.addItemStackToInventory(milkBucket)) {
                        this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, player.posX, player.posY, player.posZ, milkBucket));
                    }

                    if (!player.capabilities.isCreativeMode) {
                        --stack.stackSize;

                        if (stack.stackSize <= 0) {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                        }
                    }
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void handleHealthUpdate(byte state) {
        if (state == 7) {
            this.spawnTamingParticles(true);
        } else if (state == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleHealthUpdate(state);
        }
    }

    private void spawnTamingParticles(boolean successful) {
        String particle = successful ? "heart" : "smoke";

        for (int i = 0; i < 7; ++i) {
            double motionX = this.rand.nextGaussian() * 0.02D;
            double motionY = this.rand.nextGaussian() * 0.02D;
            double motionZ = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(particle, this.posX + (this.rand.nextFloat() * this.width * 2.0F - this.width), this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + (this.rand.nextFloat() * this.width * 2.0F - this.width), motionX, motionY, motionZ);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == ItemsAether.void_tomato;
    }

    @Override
    public EntityAerwhale createChild(EntityAgeable parent) {
        EntityAerwhale child = new EntityAerwhale(this.worldObj);

        if (this.isTamed()) {
            child.setTamed(true);
            child.setOwnerUUID(this.getOwnerUUID());
        }

        return child;
    }

    @Override
    public double getMountedYOffset() {
        return 3.0D;
    }

    @Override
    public float getMountedMoveSpeed() {
        return 0.6F;
    }

    @Override
    protected double getMountJumpStrength() {
        return 9.0D;
    }

    @Override
    protected void jump() {
        if (this.riddenByEntity == null) {
            super.jump();
        }
    }

    private void applyMountedFalling() {
        if (!this.onGround) {
            if (this.motionY < 0.0D && !this.isRiderSneaking()) {
                this.motionY *= 0.7D;
            }
        } else if (!this.worldObj.isRemote) {
            this.jumpsRemaining = this.maxJumps;
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.onGround) {
            this.wingAngle *= 0.8F;
            this.aimingForFold = 0.1F;
            this.jumpsRemaining = this.maxJumps;
        } else {
            this.aimingForFold = 1.0F;
        }

        if (this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer rider = (EntityPlayer)this.riddenByEntity;
            rider.triggerAchievement(AchievementsAether.flying_aerwhale);
        }

        ++this.animationTicks;
        this.wingAngle = this.wingFold * (float)Math.sin(this.animationTicks / 31.83098862F);
        this.wingFold += (this.aimingForFold - this.wingFold) / 5.0F;

        this.fallDistance = 0.0F;
        this.applyMountedFalling();

        if (this.riddenByEntity != null) {
            this.targetedEntity = null;
            this.targetObstructedTicks = 0;
            return;
        }

        if (this.worldObj.isRemote) {
            return;
        }

        if (this.motionY < 0.0D && this.posX != this.waypointX && this.posZ != this.waypointZ && this.targetedEntity == null) {
            this.motionY *= 1.0D;
            this.motionX *= 1.0D;
            this.motionZ *= 1.0D;
        }

        if (this.targetedEntity != null) {
            if (this.targetedEntity.isDead) {
                this.targetedEntity = null;
                this.targetObstructedTicks = 0;
                return;
            }

            if (this.targetedEntity instanceof EntityPlayer) {
                EntityPlayer targetPlayer = (EntityPlayer)this.targetedEntity;

                if (!this.getEntitySenses().canSee(targetPlayer)) {
                    ++this.targetObstructedTicks;
                } else {
                    this.targetObstructedTicks = 0;
                }

                if (targetPlayer.capabilities.isCreativeMode || this.targetObstructedTicks > 100) {
                    this.targetObstructedTicks = 0;
                    this.targetedEntity = null;
                    return;
                }
            }

            this.waypointX = this.targetedEntity.posX;
            this.waypointY = this.targetedEntity.posY + 1.0D - this.rand.nextFloat() * 0.3F;
            this.waypointZ = this.targetedEntity.posZ;
        }
    }

    @Override
    protected void updateEntityActionState() {
        super.updateEntityActionState();

        if (this.riddenByEntity != null) {
            this.targetedEntity = null;
            return;
        }

        double distanceX = this.waypointX - this.posX;
        double distanceY = this.waypointY - this.posY;
        double distanceZ = this.waypointZ - this.posZ;

        double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

        if (distanceSquared < 1.0D || distanceSquared > 3600.0D) {
            this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;

            distanceX = this.waypointX - this.posX;
            distanceY = this.waypointY - this.posY;
            distanceZ = this.waypointZ - this.posZ;
            distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        }

        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 10;

            double distance = MathHelper.sqrt_double(distanceSquared);
            if (distance > 0.0001D) {
                if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, distance) || this.targetedEntity != null) {
                    this.motionX += distanceX / distance * 0.35D;
                    this.motionY += distanceY / distance * 0.35D;
                    this.motionZ += distanceZ / distance * 0.35D;
                } else {
                    this.waypointX = this.posX;
                    this.waypointY = this.posY;
                    this.waypointZ = this.posZ;
                }
            }
        }

        this.getEntitySenses().clearSensingCache();

        if (this.targetedEntity != null) {
            distanceX = this.targetedEntity.posX - this.posX;
            distanceZ = this.targetedEntity.posZ - this.posZ;
            float yaw = -(float)Math.atan2(distanceX, distanceZ) * 180.0F / (float)Math.PI;
            this.rotationYaw = yaw;
            this.renderYawOffset = yaw;
        }

        else if (Math.abs(this.motionX) > 0.0001D || Math.abs(this.motionZ) > 0.0001D) {
            float yaw = -(float)Math.atan2(this.motionX, this.motionZ) * 180.0F / (float)Math.PI;
            this.rotationYaw = yaw;
            this.renderYawOffset = yaw;
        }
    }

    private boolean isCourseTraversable(double targetX, double targetY, double targetZ, double distance) {
        if (distance <= 0.0001D) {
            return false;
        }

        double stepX = (targetX - this.posX) / distance;
        double stepY = (targetY - this.posY) / distance;
        double stepZ = (targetZ - this.posZ) / distance;
        AxisAlignedBB testBox = this.boundingBox.copy();

        for (int step = 1; step < distance; ++step) {
            testBox.offset(stepX, stepY, stepZ);
            if (!this.worldObj.getCollidingBoundingBoxes(this, testBox).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source == DamageSource.inWall) {
            return false;
        }
        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("AerwhaleTamed", this.isTamed());
        compound.setString("AerwhaleOwnerUUID", this.getOwnerUUID());
        compound.setInteger("maxJumps", this.maxJumps);
        compound.setInteger("jumpsRemaining", this.jumpsRemaining);
        compound.setFloat("wingFold", this.wingFold);
        compound.setFloat("wingAngle", this.wingAngle);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setTamed(compound.getBoolean("AerwhaleTamed"));
        this.setOwnerUUID(compound.getString("AerwhaleOwnerUUID"));
        this.maxJumps = compound.hasKey("maxJumps") ? compound.getInteger("maxJumps") : 2;
        this.jumpsRemaining = compound.getInteger("jumpsRemaining");
        this.wingFold = compound.getFloat("wingFold");
        this.wingAngle = compound.getFloat("wingAngle");
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootLevel) {
        int amount = this.rand.nextInt(3) + this.rand.nextInt(1 + lootLevel);

        for (int i = 0; i < amount; ++i) {
            this.dropItem(ItemsAether.raw_aerwhale, 3);
        }

        amount = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootLevel);
        for (int i = 0; i < amount; ++i) {
            if (this.isBurning()) {
                this.dropItem(ItemsAether.enchanted_aerwhale, 3);
            } else {
                this.dropItem(ItemsAether.raw_aerwhale, 3);
            }
        }

        super.dropFewItems(recentlyHit, lootLevel);
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
    public boolean canDespawn() {
        return !this.isTamed();
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);

        boolean validGround = this.worldObj.getBlock(x, y - 1, z) == BlocksAether.aether_dirt || this.worldObj.getBlock(x, y - 1, z) == BlocksAether.aether_grass
                || this.worldObj.getBlock(x, y - 1, z) == BlocksAether.arctic_grass || this.worldObj.getBlock(x, y - 1, z) == BlocksAether.verdant_grass
                || this.worldObj.getBlock(x, y - 1, z) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(x, y - 1, z) == BlocksAether.divine_grass;

        boolean clear = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        return validGround && this.worldObj.getBlockLightValue(x, y, z) > 7 && clear && this.rand.nextInt(AetherConfig.getAerwhaleSpawnrate()) == 0 && super.getCanSpawnHere();
    }
}

