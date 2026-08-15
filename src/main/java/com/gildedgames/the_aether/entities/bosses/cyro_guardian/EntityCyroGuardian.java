package com.gildedgames.the_aether.entities.bosses.cyro_guardian;

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

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityCyroGuardian extends EntityBossMob implements IAetherBoss {

    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private int field_70846_g;

    private int[] doorStart = new int[] { 0, 0, 0 };
    private int[] doorEnd = new int[] { 0, 0, 0 };

    private boolean hasDoorBounds;
    private boolean bossFightStarted;

    private int[] dungeonStart = new int[] { 0, 0, 0 };
    private int[] dungeonEnd = new int[] { 0, 0, 0 };

    private boolean hasDungeonBounds;

    private double resetX;
    private double resetY;
    private double resetZ;

    private boolean bossCompletionHandled;

    public EntityCyroGuardian(World world) {
        super(world);
        this.isImmuneToFire = false;
        this.experienceValue = 100;
        this.setSize(3.0F, 3.0F);
        this.dataWatcher.updateObject(19, AetherNameGen.gen());
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)0);
        this.dataWatcher.addObject(19, AetherNameGen.gen());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(180.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.85D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(4.0D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setString("BossName", this.getName());
        compound.setIntArray("DoorStart", this.doorStart);
        compound.setIntArray("DoorEnd", this.doorEnd);
        compound.setBoolean("HasDoorBounds", this.hasDoorBounds);
        compound.setIntArray("DungeonStart", this.dungeonStart);
        compound.setIntArray("DungeonEnd", this.dungeonEnd);
        compound.setBoolean("HasDungeonBounds", this.hasDungeonBounds);

        compound.setDouble("ResetX", this.resetX);
        compound.setDouble("ResetY", this.resetY);
        compound.setDouble("ResetZ", this.resetZ);

        compound.setBoolean("BossFightStarted", this.bossFightStarted);
        compound.setBoolean("BossCompletionHandled", this.bossCompletionHandled);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setBossName(compound.getString("BossName"));

        int[] savedDoorStart = compound.getIntArray("DoorStart");
        int[] savedDoorEnd = compound.getIntArray("DoorEnd");

        if (savedDoorStart != null && savedDoorStart.length == 3) {
            this.doorStart = savedDoorStart;
        }

        if (savedDoorEnd != null && savedDoorEnd.length == 3) {
            this.doorEnd = savedDoorEnd;
        }

        this.hasDoorBounds = compound.getBoolean("HasDoorBounds");

        int[] savedDungeonStart = compound.getIntArray("DungeonStart");
        int[] savedDungeonEnd = compound.getIntArray("DungeonEnd");

        if (savedDungeonStart != null && savedDungeonStart.length == 3) {
            this.dungeonStart = savedDungeonStart;
        }

        if (savedDungeonEnd != null && savedDungeonEnd.length == 3) {
            this.dungeonEnd = savedDungeonEnd;
        }

        this.hasDungeonBounds = compound.getBoolean("HasDungeonBounds");

        if (compound.hasKey("ResetX")) {
            this.resetX = compound.getDouble("ResetX");
            this.resetY = compound.getDouble("ResetY");
            this.resetZ = compound.getDouble("ResetZ");
        } else {
            this.resetX = this.posX;
            this.resetY = this.posY;
            this.resetZ = this.posZ;
        }

        this.bossFightStarted = compound.getBoolean("BossFightStarted");
        this.bossCompletionHandled = compound.getBoolean("BossCompletionHandled");
    }

    public void setDoorBounds(int startX, int startY, int startZ, int endX, int endY, int endZ) {
        this.doorStart = new int[] {Math.min(startX, endX), Math.min(startY, endY), Math.min(startZ, endZ)};
        this.doorEnd = new int[] {Math.max(startX, endX), Math.max(startY, endY), Math.max(startZ, endZ)};
        this.hasDoorBounds = true;
    }

    public void setDungeonBounds(int startX, int startY, int startZ, int endX, int endY, int endZ) {
        this.dungeonStart = new int[] {Math.min(startX, endX), Math.min(startY, endY), Math.min(startZ, endZ)};
        this.dungeonEnd = new int[] {Math.max(startX, endX), Math.max(startY, endY), Math.max(startZ, endZ)};
        this.hasDungeonBounds = true;
    }

    public void setResetPosition(double x, double y, double z) {
        this.resetX = x;
        this.resetY = y;
        this.resetZ = z;
    }

    private boolean hasValidDoorBounds() {
        return this.hasDoorBounds && this.doorStart != null && this.doorEnd != null && this.doorStart.length == 3 && this.doorEnd.length == 3;
    }

    private boolean hasValidDungeonBounds() {
        return this.hasDungeonBounds && this.dungeonStart != null && this.dungeonEnd != null && this.dungeonStart.length == 3 && this.dungeonEnd.length == 3;
    }

    private void closeDoor() {
        if (this.worldObj.isRemote || !this.hasValidDoorBounds()) {
            return;
        }

        for (int x = this.doorStart[0]; x <= this.doorEnd[0]; x++) {
            for (int y = this.doorStart[1]; y <= this.doorEnd[1]; y++) {
                for (int z = this.doorStart[2]; z <= this.doorEnd[2]; z++) {
                    this.worldObj.setBlock(x, y + 1, z, BlocksAether.oblitus_stone, 0, 3);
                }
            }
        }
    }

    private void openDoor() {
        if (this.worldObj.isRemote || !this.hasValidDoorBounds()) {
            return;
        }

        for (int x = this.doorStart[0]; x <= this.doorEnd[0]; x++) {
            for (int y = this.doorStart[1]; y <= this.doorEnd[1]; y++) {
                for (int z = this.doorStart[2]; z <= this.doorEnd[2]; z++) {
                    this.worldObj.setBlockToAir(x, y, z);
                }
            }
        }
    }

    private void convertCompletedBossRoomBlocks() {
        if (this.worldObj.isRemote || !this.hasValidDungeonBounds()) {
            return;
        }

        for (int x = this.dungeonStart[0]; x <= this.dungeonEnd[0]; x++) {
            for (int y = this.dungeonStart[1]; y <= this.dungeonEnd[1]; y++) {
                for (int z = this.dungeonStart[2]; z <= this.dungeonEnd[2]; z++) {
                    Block block = this.worldObj.getBlock(x, y, z);
                    if (block == BlocksAether.oblitus_stone) {
                        this.worldObj.setBlock(x, y, z, BlocksAether.oblitus_stone_2, 0, 3);
                    } else if (block == BlocksAether.cracked_oblitus_stone) {
                        this.worldObj.setBlock(x, y, z, BlocksAether.cracked_oblitus_stone_2, 0, 3);
                    }
                }
            }
        }
    }

    private void completeBossFight() {
        if (this.worldObj.isRemote || this.bossCompletionHandled) {
            return;
        }

        this.bossCompletionHandled = true;
        this.bossFightStarted = false;

        this.openDoor();
        this.convertCompletedBossRoomBlocks();

        EntityLivingBase target = this.getAttackTarget();

        if (target instanceof EntityPlayer) {
            PlayerAether.get((EntityPlayer)target).setFocusedBoss(null);
        }
    }

    private void resetBossFight() {
        if (this.worldObj.isRemote || !this.bossFightStarted) {
            return;
        }

        EntityLivingBase target = this.getAttackTarget();

        if (target instanceof EntityPlayer) {
            PlayerAether.get((EntityPlayer)target).setFocusedBoss(null);
        }

        this.openDoor();

        this.bossFightStarted = false;
        this.bossCompletionHandled = false;

        this.setAttackTarget(null);
        this.entityToAttack = null;

        this.attackTime = 0;
        this.field_70846_g = 0;
        this.func_70844_e(false);

        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;

        this.setHealth(this.getMaxHealth());
        this.setPositionAndUpdate(this.resetX, this.resetY, this.resetZ);
    }

    @Override
    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return "aether_legacy:aemob.cyro.living";
    }

    @Override
    protected String getHurtSound() {
        return "aether_legacy:aemob.cyro.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "aether_legacy:aemob.cyro.death";
    }

    @Override
    protected float getSoundVolume() {
        return 3.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks) {
        return 15728880;
    }

    @Override
    public float getBrightness(float partialTicks) {
        return 0.5F;
    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
            this.damageEntity(DamageSource.drown, 1.0F);
        }

        if (!this.worldObj.isRemote) {
            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }

            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 40;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + this.getEntityToAttack().getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }

        if (this.rand.nextInt(24) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        for (int i = 0; i < 2; ++i) {
            this.worldObj.spawnParticle("snowshovel", this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
        }

        super.onLivingUpdate();

        if (!this.worldObj.isRemote && this.bossFightStarted) {
            EntityLivingBase target = this.getAttackTarget();

            if (target == null || target.isDead || target.getHealth() <= 0.0F) {
                this.resetBossFight();
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (this.worldObj.isRemote) {
            NewAetherParticleHandler.CYRO.spawn(this.worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height + 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width);
            NewAetherParticleHandler.CYRO.spawn(this.worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height + 1.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width);
            NewAetherParticleHandler.CYRO.spawn(this.worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height + 2.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width);
            NewAetherParticleHandler.CYRO.spawn(this.worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height + 3.0D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (damageSource.getEntity() == null || !(damageSource.getEntity() instanceof EntityPlayer)) {
            return false;
        }

        EntityPlayer player = (EntityPlayer)damageSource.getEntity();

        boolean damaged = super.attackEntityFrom(damageSource, Math.max(0.0F, damage));
        if (damaged && !this.worldObj.isRemote) {

            if (!this.bossFightStarted && this.getHealth() > 0.0F) {
                this.bossFightStarted = true;
                this.bossCompletionHandled = false;
                this.setAttackTarget(player);
                this.closeDoor();
            }

            if (this.getHealth() <= 0.0F || this.isDead) {
                this.completeBossFight();
                PlayerAether.get(player).setFocusedBoss(null);
            } else {
                PlayerAether.get(player).setFocusedBoss(this);
            }
        }

        return damaged;
    }

    @Override
    protected void attackEntity(Entity target, float distance) {
        if (this.attackTime <= 0 && distance < 2.0F && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 10;
            this.attackEntityAsMob(target);
        } else if (distance < 30.0F) {
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
                        EntityCyroGuardianProjectile projectile = new EntityCyroGuardianProjectile(this.worldObj, this, dx + this.rand.nextGaussian() * spread, dy, dz + this.rand.nextGaussian() * spread);
                        double distanceToTarget = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);

                        if (distanceToTarget <= 0.0001D) {
                            continue;
                        }

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

    @Override
    protected void fall(float distance) {
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        this.entityDropItem(new ItemStack(ItemsAether.dungeon_key, 1, 10), 0.5F);

        if (recentlyHit) {
            int amount = this.rand.nextInt(4 + looting);
            for (int i = 0; i < amount; ++i) {
                this.dropItem(ItemsAether.cyro_rod, 8);
            }
        }

        if (this.rand.nextInt(3) == 0) {
            this.dropItem(ItemsAether.divine_essence, 1 + this.rand.nextInt(2));
        }
    }

    public boolean func_70845_n() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_70844_e(boolean attacking) {
        byte value = this.dataWatcher.getWatchableObjectByte(16);

        if (attacking) {
            value = (byte)(value | 1);
        } else {
            value &= -2;
        }

        this.dataWatcher.updateObject(16, value);
    }

    @Override
    public void onDeath(DamageSource source) {
        this.completeBossFight();
        super.onDeath(source);
    }

    @Override
    protected void onDeathUpdate() {
        this.completeBossFight();
        this.setDead();
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
