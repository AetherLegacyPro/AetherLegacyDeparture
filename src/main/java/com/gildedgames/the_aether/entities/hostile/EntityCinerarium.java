package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.entities.projectile.EntityCinerariumProjectile;
import com.gildedgames.the_aether.items.ItemsAether;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCinerarium extends EntityMob {
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private int field_70846_g;

    public EntityCinerarium(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.experienceValue = 25;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
    }

    protected String getLivingSound() {
        return "mob.blaze.breathe";
    }

    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_) {
        return 15728880;
    }

    public float getBrightness(float p_70013_1_) {
        return 1.0F;
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }

            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 40;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }

        if (this.rand.nextInt(24) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        for (int i = 0; i < 4; ++i) {
            this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            NewAetherParticleHandler.HELLFIRE_FLAME.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
        }

        super.onLivingUpdate();
    }

    protected void attackEntity(Entity target, float distance) {
        if (this.attackTime <= 0 && distance < 2.0F && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 10;
            this.attackEntityAsMob(target);
        }
        else if (distance < 30.0F) {
            double dx = target.posX - this.posX;
            double dy = target.boundingBox.minY + target.height / 2.0F - (this.posY + this.height / 2.0F);
            double dz = target.posZ - this.posZ;

            if (this.attackTime == 0) {
                ++this.field_70846_g;

                if (this.field_70846_g == 1) {
                    this.attackTime = 10;
                    this.func_70844_e(true);
                } else if (this.field_70846_g <= 4) {
                    this.attackTime = 6;
                } else {
                    this.attackTime = 20;
                    this.field_70846_g = 0;
                    this.func_70844_e(false);
                }

                if (this.field_70846_g > 1) {
                    float spread = MathHelper.sqrt_float(distance) * 0.5F;
                    this.worldObj.playAuxSFXAtEntity(null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

                    for (int i = 0; i < 4; ++i) {
                        EntityCinerariumProjectile projectile = new EntityCinerariumProjectile(this.worldObj, this, dx + this.rand.nextGaussian() * spread, dy, dz + this.rand.nextGaussian() * spread);
                        double distanceToTarget = MathHelper.sqrt_double(dx*dx + dy*dy + dz*dz);
                        double speed = 1.0D;
                        projectile.motionX = dx / distanceToTarget * speed;
                        projectile.motionY = dy / distanceToTarget * speed;
                        projectile.motionZ = dz / distanceToTarget * speed;

                        double offset = 0.5D;
                        projectile.posX = this.posX + projectile.motionX * offset;
                        projectile.posY = this.posY + this.height / 3.0D + 0.5D + projectile.motionY * offset;
                        projectile.posZ = this.posZ + projectile.motionZ * offset;
                        projectile.setPosition(projectile.posX, projectile.posY, projectile.posZ);

                        this.worldObj.spawnEntityInWorld(projectile);
                    }
                }
            }

            this.rotationYaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

    protected void fall(float p_70069_1_) {}

    protected Item getDropItem() {
        return ItemsAether.cinerarium_rod;
    }

    public boolean isBurning() {
        return this.func_70845_n();
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        if (p_70628_1_) {
            int j = this.rand.nextInt(2 + p_70628_2_);

            for (int k = 0; k < j; ++k) {
                this.dropItem(ItemsAether.cinerarium_rod, 1);
            }
        }
    }

    public boolean func_70845_n() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_70844_e(boolean p_70844_1_) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70844_1_) {
            b0 = (byte)(b0 | 1);
        }
        else {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, b0);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_light_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_ancient_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_ancient_light_hellfire_stone) && this.worldObj.getBlockLightValue(i, j, k) < 14 && canSpawn;
    }

}
