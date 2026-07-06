package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.uro.uroswell.EntityAIUligoSwell;
import com.gildedgames.the_aether.items.ItemsAether;
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

public class EntityUligo extends EntityLiving implements IMob {
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private int slimeJumpDelay;
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 20;
    private int explosionRadius = 2;

    public EntityUligo(World world) {
        super(world);
        this.tasks.addTask(2, new EntityAIUligoSwell(this));
        int i = 1 << this.rand.nextInt(3);
        this.yOffset = 0.0F;
        this.slimeJumpDelay = this.rand.nextInt(10) + 10;
        this.setSlimeSize(i);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 1);
        this.dataWatcher.addObject(15, (byte) -1);
        this.dataWatcher.addObject(17, (byte) 0);
        this.dataWatcher.addObject(18, (byte) 0);
    }

    public int getMaxSafePointTries() {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }

    @Override
	public void fall(float distance) {
	}

    protected void setSlimeSize(int p_70799_1_) {
        this.dataWatcher.updateObject(16, (byte)p_70799_1_);
        this.setSize(0.6F * (float)p_70799_1_, 0.6F * (float)p_70799_1_);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(p_70799_1_ * p_70799_1_);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = p_70799_1_;
    }

    public int getSlimeSize() {
        return this.dataWatcher.getWatchableObjectByte(16);

    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Size", this.getSlimeSize() - 1);

        if (this.dataWatcher.getWatchableObjectByte(17) == 1) {
            compound.setBoolean("powered", true);
        }

        compound.setShort("Fuse", (short)this.fuseTime);
        compound.setByte("ExplosionRadius", (byte)this.explosionRadius);
        compound.setBoolean("ignited", this.func_146078_ca());
    }

    public void onStruckByLightning(EntityLightningBolt bolt) {
        super.onStruckByLightning(bolt);
        this.dataWatcher.updateObject(17, (byte) 1);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int i = compound.getInteger("Size");

        if (i < 0) {
            i = 0;
        }

        this.setSlimeSize(i + 1);

        this.dataWatcher.updateObject(17, (byte) (compound.getBoolean("powered") ? 1 : 0));

        if (compound.hasKey("Fuse", 99)) {
            this.fuseTime = compound.getShort("Fuse");
        }

        if (compound.hasKey("ExplosionRadius", 99)) {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }

        if (compound.getBoolean("ignited")) {
            this.func_146079_cb();
        }
    }

    protected String getJumpSound() {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    public void onUpdate() {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0) {
            this.isDead = true;
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        boolean flag = this.onGround;
        super.onUpdate();
        int i;

        if (this.onGround && !flag) {
            if (this.makesSoundOnLand()) {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F + 1.0F) / 0.8F);
            }

            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && flag) {
            this.squishAmount = 1.0F;
        }

        this.alterSquishAmount();

        if (this.worldObj.isRemote) {
            i = this.getSlimeSize();
            this.setSize(0.6F * (float)i, 0.6F * (float)i);
        }

        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.func_146078_ca()) {
                this.setCreeperState(1);
            }

            int u = this.getCreeperState();

            if (u > 0 && this.timeSinceIgnited == 0) {
                this.playSound("ambient.cave.cave", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += u;

            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.func_146077_cc();
            }
        }

        super.onUpdate();
    }

    protected void updateEntityActionState() {
        this.despawnEntity();
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (entityplayer != null) {
            this.faceEntity(entityplayer, 10.0F, 20.0F);
        }

        if (this.onGround && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.getJumpDelay();

            if (entityplayer != null) {
                this.slimeJumpDelay /= 3;
            }

            this.isJumping = true;

            if (this.makesSoundOnJump()) {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = (float)(getSlimeSize());
        }
        else {
            this.isJumping = false;

            if (this.onGround) {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    protected void alterSquishAmount() {
        this.squishAmount *= 0.75F;
    }

    protected int getJumpDelay() {
        return this.rand.nextInt(10) + 5;
    }

    protected EntityUligo createInstance() {
        return new EntityUligo(this.worldObj);
    }

    public void setDead() {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F) {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k) {
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

    public void onCollideWithPlayer(EntityPlayer entityPlayer) {
        if (this.canDamagePlayer()) {
            int i = this.getSlimeSize();

            if (this.canEntityBeSeen(entityPlayer) && this.getDistanceSqToEntity(entityPlayer) < 0.6D * (double)i * 0.6D * (double)i && entityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            if (rand.nextInt(10) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);

            if (!this.worldObj.isRemote) {
                this.func_146079_cb();
            	}
            }
        }
    }

    @Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (source.getEntity() == null || source.isExplosion()) {
            return false;
        }

		if (source.getEntity() != null) {
			if (source.getEntity() instanceof EntityLivingBase) {
				this.setAttackTarget((EntityLivingBase) source.getEntity());
			}
		}

		return super.attackEntityFrom(source, amount);
	}

    protected boolean canDamagePlayer() {
        return this.getSlimeSize() >= 1;
    }

    protected int getAttackStrength() {
        return this.getSlimeSize();
    }

    protected String getHurtSound() {
        return "aether_legacy:aemob.uligo.hurt";
    }

    protected String getDeathSound() {
        return "aether_legacy:aemob.uligo.death";
    }

    protected Item getDropItem() {
        return this.getSlimeSize() == 1 ? ItemsAether.uligo_swet_ball : Item.getItemById(0);
    }

    protected boolean interact(EntityPlayer entityPlayer) {
        ItemStack itemstack = entityPlayer.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.flint_and_steel) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            entityPlayer.swingItem();

            if (!this.worldObj.isRemote) {
                this.func_146079_cb();
                itemstack.damageItem(1, entityPlayer);
                return true;
            }
        }

        return super.interact(entityPlayer);
    }

    protected float getSoundVolume() {
        return 0.2F * (float)this.getSlimeSize();
    }

    public int getVerticalFaceSpeed() {
        return 0;
    }

    protected boolean makesSoundOnJump() {
        return this.getSlimeSize() > 0;
    }

    protected boolean makesSoundOnLand() {
        return this.getSlimeSize() > 2;
    }

    private void func_146077_cc() {
        if (!this.worldObj.isRemote) {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

            if (this.getPowered()) {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), flag);

                int i = this.getSlimeSize();

                if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F) {
                    int j = 2 + this.rand.nextInt(3);

                    for (int k = 0; k < j; ++k) {
                        float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                        float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                        EntityUligo entityslime = this.createInstance();
                        entityslime.setSlimeSize(i / 2);
                        entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(entityslime);
                    }
                }
            }
            else {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);

                int i = this.getSlimeSize();

                if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F) {
                    int j = 2 + this.rand.nextInt(3);

                    for (int k = 0; k < j; ++k) {
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

    public boolean getPowered() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public boolean func_146078_ca() {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }

    public void func_146079_cb() {
        this.dataWatcher.updateObject(18, (byte) 1);
    }

    public int getCreeperState() {
        return this.dataWatcher.getWatchableObjectByte(15);
    }

    public void setCreeperState(int p_70829_1_) {
        this.dataWatcher.updateObject(15, (byte) p_70829_1_);
    }

    public boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_creeping_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_fuse_stone) && this.worldObj.getBlockLightValue(i, j, k) < 14 && canSpawn;

    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }
}

