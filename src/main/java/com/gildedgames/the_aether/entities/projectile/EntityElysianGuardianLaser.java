package com.gildedgames.the_aether.entities.projectile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import java.util.List;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.Entity;

public class EntityElysianGuardianLaser extends Entity implements IProjectile
{
    private int field_145791_d;
    private int field_145792_e;
    private int field_145789_f;
    private Block field_145790_g;
    private int inData;
    private boolean inGround;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    
    public EntityElysianGuardianLaser(final World par1World) {
        super(par1World);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 15.0;
        this.renderDistanceWeight = 5.0;
        this.setSize(0.5f, 0.5f);
    }
    
    public EntityElysianGuardianLaser(final World par1World, final double par2, final double par3, final double par4) {
        super(par1World);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 15.0;
        this.renderDistanceWeight = 5.0;
        this.setSize(0.5f, 0.5f);
        this.setPosition(par2, par3, par4);
        this.yOffset = 0.0f;
    }
    
    public EntityElysianGuardianLaser(final World par1World, final EntityLivingBase par2EntityLivingBase) {
        super(par1World);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 15.0;
        this.renderDistanceWeight = 5.0;
        this.shootingEntity = (Entity)par2EntityLivingBase;
        this.setSize(0.5f, 0.5f);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        this.posY -= 0.10000000149011612;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0f;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(this.rotationPitch / 180.0f * 3.1415927f);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(this.rotationPitch / 180.0f * 3.1415927f);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0f * 3.1415927f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.39f, 1.0f);
    }
    
    public EntityElysianGuardianLaser(final World par1World, final EntityLivingBase par2EntityLivingBase, final EntityLivingBase par3EntityLivingBase, final float par4, final float par5) {
        super(par1World);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 15.0;
        this.renderDistanceWeight = 5.0;
        this.shootingEntity = (Entity)par2EntityLivingBase;
        this.posY = par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() - 0.10000000149011612;
        final double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
        final double d2 = par3EntityLivingBase.boundingBox.minY + par3EntityLivingBase.height / 3.0f - this.posY;
        final double d3 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
        final double d4 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
        if (d4 >= 1.0E-7) {
            final float f2 = (float)(Math.atan2(d3, d0) * 180.0 / 3.141592653589793) - 90.0f;
            final float f3 = (float)(-(Math.atan2(d2, d4) * 180.0 / 3.141592653589793));
            final double d5 = d0 / d4;
            final double d6 = d3 / d4;
            this.setLocationAndAngles(par2EntityLivingBase.posX + d5, this.posY, par2EntityLivingBase.posZ + d6, f2, f3);
            this.yOffset = 0.0f;
            final float f4 = (float)d4 * 0.2f;
            this.setThrowableHeading(d0, d2 + f4, d3, par4, par5);
        }
    }
    
    public EntityElysianGuardianLaser(final World par1World, final EntityLivingBase par2EntityLivingBase, final float par3) {
        super(par1World);
        this.field_145791_d = -1;
        this.field_145792_e = -1;
        this.field_145789_f = -1;
        this.damage = 15.0;
        this.renderDistanceWeight = 5.0;
        this.shootingEntity = (Entity)par2EntityLivingBase;
        this.setSize(0.5f, 0.5f);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        this.posY -= 0.10000000149011612;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0f;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(this.rotationPitch / 180.0f * 3.1415927f);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(this.rotationPitch / 180.0f * 3.1415927f);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0f * 3.1415927f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5f, 1.0f);
    }
    
    public boolean canAttackWithItem() {
        return false;
    }
    
    protected boolean canTriggerWalking() {
        return false;
    }
    
    protected void entityInit() {
        this.dataWatcher.addObject(16, (Object)0);
    }
    
    public double getDamage() {
        return this.damage;
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0f;
    }
    
    protected void onImpact(MovingObjectPosition p_70227_1_)
    {
        if (!this.worldObj.isRemote)
        {
            if (p_70227_1_.entityHit != null)
            {              
                
                if (p_70227_1_.entityHit.attackEntityFrom(DamageSource.magic, 5.0F))
                {
                    p_70227_1_.entityHit.setFire(15);
                }
            }
            else
            {
                int i = p_70227_1_.blockX;
                int j = p_70227_1_.blockY;
                int k = p_70227_1_.blockZ;

                switch (p_70227_1_.sideHit)
                {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }

                if (this.worldObj.isAirBlock(i, j, k))
                {
                    this.worldObj.setBlock(i, j, k, BlocksAether.hellfire);
                }
            }
            
            if (p_70227_1_.entityHit instanceof EntityPlayerMP) {
				((EntityPlayerMP) p_70227_1_.entityHit).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(p_70227_1_.entityHit));
			}

            this.setDead();
        }
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (this.prevRotationPitch == 0.0f && this.prevRotationYaw == 0.0f) {
            final float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            final float n = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / 3.141592653589793);
            this.rotationYaw = n;
            this.prevRotationYaw = n;
            final float n2 = (float)(Math.atan2(this.motionY, f) * 180.0 / 3.141592653589793);
            this.rotationPitch = n2;
            this.prevRotationPitch = n2;
        }
        final Block block = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
        if (block.getMaterial() != Material.air) {
            block.setBlockBoundsBasedOnState((IBlockAccess)this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            final AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.inGround) {
            final int j = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (block == this.field_145790_g && j == this.inData) {
                ++this.ticksInGround;
                this.setDead();
                if (this.ticksInGround == 10) {
                    this.setDead();
                }
            }
            else {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2f;
                this.motionY *= this.rand.nextFloat() * 0.2f;
                this.motionZ *= this.rand.nextFloat() * 0.2f;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
        else {
            ++this.ticksInAir;
            Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec32 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec32, false, true, false);
            vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec32 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (movingobjectposition != null) {
                vec32 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }
            Entity entity = null;
            final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (int i = 0; i < list.size(); ++i) {
                final Entity entity2 = (Entity) list.get(i);
                if (entity2.canBeCollidedWith() && (entity2 != this.shootingEntity || this.ticksInAir >= 5)) {
                    final float f2 = 0.3f;
                    final AxisAlignedBB axisalignedbb2 = entity2.boundingBox.expand((double)f2, (double)f2, (double)f2);
                    final MovingObjectPosition movingobjectposition2 = axisalignedbb2.calculateIntercept(vec31, vec32);
                    if (movingobjectposition2 != null) {
                        final double d2 = vec31.distanceTo(movingobjectposition2.hitVec);
                        if (d2 < d0 || d0 == 0.0) {
                            entity = entity2;
                            d0 = d2;
                        }
                    }
                }
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null && movingobjectposition.entityHit instanceof EntityPlayer) {
                final EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
                if (entityplayer.capabilities.disableDamage || (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                if (movingobjectposition.entityHit != null) {
                    final float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int k = MathHelper.ceiling_double_int(f3 * this.damage);
                    DamageSource damagesource = null;
                    if (this.shootingEntity == null) {
                        damagesource = DamageSource.magic;
                    }
                    else {
                    	damagesource = DamageSource.magic;
                        this.worldObj.playSoundAtEntity((Entity)this, "random.fizz", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                        this.setDead();
                    }
                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)k)) {
                        if (movingobjectposition.entityHit instanceof EntityLivingBase) {
                            final EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)entitylivingbase);
                            }
                            if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        if (!(movingobjectposition.entityHit instanceof EntityEnderman)) {
                            this.setDead();
                        }
                    }
                    else {
                        this.motionX *= -0.10000000149011612;
                        this.motionY *= -0.10000000149011612;
                        this.motionZ *= -0.10000000149011612;
                        this.rotationYaw += 180.0f;
                        this.prevRotationYaw += 180.0f;
                        this.ticksInAir = 0;
                    }
                }
                else {
                    this.field_145791_d = movingobjectposition.blockX;
                    this.field_145792_e = movingobjectposition.blockY;
                    this.field_145789_f = movingobjectposition.blockZ;
                    this.field_145790_g = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.inData = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.motionX = (float)(movingobjectposition.hitVec.xCoord - this.posX);
                    this.motionY = (float)(movingobjectposition.hitVec.yCoord - this.posY);
                    this.motionZ = (float)(movingobjectposition.hitVec.zCoord - this.posZ);
                    final float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / f3 * 0.05000000074505806;
                    this.posY -= this.motionY / f3 * 9.050000000745058;
                    this.posZ -= this.motionZ / f3 * 0.05000000074505806;
                    this.worldObj.playSoundAtEntity((Entity)this, "random.fizz", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.arrowShake = 7;
                    if (this.field_145790_g.getMaterial() != Material.air) {
                        this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f, (Entity)this);
                        this.setDead();
                    }
                }
            }
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            final float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / 3.141592653589793);
            this.rotationPitch = (float)(Math.atan2(this.motionY, f3) * 180.0 / 3.141592653589793);
            while (this.rotationPitch - this.prevRotationPitch < -180.0f) {
                this.prevRotationPitch -= 360.0f;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0f) {
                this.prevRotationPitch += 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0f) {
                this.prevRotationYaw -= 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0f) {
                this.prevRotationYaw += 360.0f;
            }
            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2f;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2f;
            float f4 = 0.99156f;
            final float f2 = 0.0018765f;
            if (this.isInWater()) {
                for (int l = 0; l < 4; ++l) {
                    final float f5 = 0.25f;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f5, this.posY - this.motionY * f5, this.posZ - this.motionZ * f5, this.motionX, this.motionY, this.motionZ);
                }
                f4 = 0.8f;
            }
            if (this.isWet()) {
                this.extinguish();
            }
            this.motionX *= f4;
            this.motionY *= f4;
            this.motionZ *= f4;
            this.motionY -= f2;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.func_145775_I();
            for (int var14 = 0; var14 < 2; ++var14) {
                final float var15 = 0.25f;
                this.worldObj.spawnParticle("lightning", this.posX - this.motionX * 0.25, this.posY - this.motionY * 0.25, this.posZ - this.motionZ * 0.25, this.motionX, this.motionY, this.motionZ);
            }
        }
    }
    
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.field_145791_d = par1NBTTagCompound.getShort("xTile");
        this.field_145792_e = par1NBTTagCompound.getShort("yTile");
        this.field_145789_f = par1NBTTagCompound.getShort("zTile");
        this.ticksInGround = par1NBTTagCompound.getShort("life");
        this.field_145790_g = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 0xFF);
        this.inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
        this.arrowShake = (par1NBTTagCompound.getByte("shake") & 0xFF);
        this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
        if (par1NBTTagCompound.hasKey("damage", 99)) {
            this.damage = par1NBTTagCompound.getDouble("damage");
        }
    }
    
    public void setDamage(final double par1) {
        this.damage = par1;
    }
    
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(final double par1, final double par2, final double par3, final float par4, final float par5, final int par6) {
        this.setPosition(par1, par2, par3);
        this.setRotation(par4, par5);
    }
    
    public void setThrowableHeading(double par1, double par2, double par3, final float par4, final float par5) {
        final float f2 = MathHelper.sqrt_double(par1 * par1 + par2 * par2 + par3 * par3);
        par1 /= f2;
        par2 /= f2;
        par3 /= f2;
        par1 += this.rand.nextGaussian() * 0.007499999832361937 * par5;
        par2 += this.rand.nextGaussian() * 0.007499999832361937 * par5;
        par3 += this.rand.nextGaussian() * 0.007499999832361937 * par5;
        par1 *= par4;
        par2 *= par4;
        par3 *= par4;
        this.motionX = par1;
        this.motionY = par2;
        this.motionZ = par3;
        final float f3 = MathHelper.sqrt_double(par1 * par1 + par3 * par3);
        final float n = (float)(Math.atan2(par1, par3) * 180.0 / 3.141592653589793);
        this.rotationYaw = n;
        this.prevRotationYaw = n;
        final float n2 = (float)(Math.atan2(par2, f3) * 180.0 / 3.141592653589793);
        this.rotationPitch = n2;
        this.prevRotationPitch = n2;
        this.ticksInGround = 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void setVelocity(final double par1, final double par2, final double par3) {
        this.motionX = par1;
        this.motionY = par2;
        this.motionZ = par3;
        if (this.prevRotationPitch == 0.0f && this.prevRotationYaw == 0.0f) {
            final float f = MathHelper.sqrt_double(par1 * par1 + par3 * par3);
            final float n = (float)(Math.atan2(par1, par3) * 180.0 / 3.141592653589793);
            this.rotationYaw = n;
            this.prevRotationYaw = n;
            final float n2 = (float)(Math.atan2(par2, f) * 180.0 / 3.141592653589793);
            this.rotationPitch = n2;
            this.prevRotationPitch = n2;
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }
    
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short)this.field_145791_d);
        par1NBTTagCompound.setShort("yTile", (short)this.field_145792_e);
        par1NBTTagCompound.setShort("zTile", (short)this.field_145789_f);
        par1NBTTagCompound.setShort("life", (short)this.ticksInGround);
        par1NBTTagCompound.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
        par1NBTTagCompound.setByte("inData", (byte)this.inData);
        par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
        par1NBTTagCompound.setByte("inGround", (byte)(byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setDouble("damage", this.damage);
    }
}
