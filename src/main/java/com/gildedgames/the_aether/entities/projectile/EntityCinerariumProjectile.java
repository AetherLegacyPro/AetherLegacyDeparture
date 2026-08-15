package com.gildedgames.the_aether.entities.projectile;

import java.util.List;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityAncientSunSpirit;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityDivineSunSpirit;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntitySunSpirit;
import com.gildedgames.the_aether.entities.hostile.EntityCinerarium;
import com.gildedgames.the_aether.entities.hostile.EntityHellfireCinder;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCinerariumProjectile extends EntityFireball {

    public EntityCinerariumProjectile(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityCinerariumProjectile(World p_i1771_1_, EntityLivingBase p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_)
    {
        super(p_i1771_1_, p_i1771_2_, p_i1771_3_, p_i1771_5_, p_i1771_7_);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityCinerariumProjectile(World p_i1772_1_, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_)
    {
        super(p_i1772_1_, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
        this.setSize(0.3125F, 0.3125F);
    }

    protected void onImpact(MovingObjectPosition mop) {
        if(mop.entityHit instanceof EntityEnderman || mop.entityHit instanceof EntityCinerarium || mop.entityHit instanceof EntitySunSpirit || mop.entityHit instanceof EntityHellfireCinder || mop.entityHit instanceof EntityAncientSunSpirit || mop.entityHit instanceof EntityDivineSunSpirit) this.setDead();

        if (!this.worldObj.isRemote) {
            if (mop.entityHit != null && mop.entityHit != this.shootingEntity) {
                mop.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
                mop.entityHit.setFire(12);
            } else {
                int i = mop.blockX;
                int j = mop.blockY;
                int k = mop.blockZ;

                switch (mop.sideHit) {
                    case 0: --j; break;
                    case 1: ++j; break;
                    case 2: --k; break;
                    case 3: ++k; break;
                    case 4: --i; break;
                    case 5: ++i; break;
                }

                if (this.worldObj.isAirBlock(i, j, k)) {
                    this.worldObj.setBlock(i, j, k, BlocksAether.hellfire);
                }
            }

            if (mop.entityHit instanceof EntityPlayerMP) {
                ((EntityPlayerMP)mop.entityHit).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(mop.entityHit));
            }

            this.setDead();
        }
    }

    @SideOnly(Side.CLIENT)
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (this.worldObj.isRemote) {
            int k;
            for (k = 0; k < 4; ++k) {
                NewAetherParticleHandler.HELLFIRE_FLAME.spawn(worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width);
            }
        }
    }

    @Override
    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;

        super.onEntityUpdate();

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        this.setPosition(this.posX, this.posY, this.posZ);

        Vec3 start = Vec3.createVectorHelper(lastTickPosX, lastTickPosY, lastTickPosZ);
        Vec3 end = Vec3.createVectorHelper(posX, posY, posZ);

        MovingObjectPosition mop = worldObj.rayTraceBlocks(start, end);

        double expand = 0.3D;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(expand, expand, expand));
        Entity hitEntity = null;
        double closestDist = Double.MAX_VALUE;

        for (Object obj : list) {
            Entity entity = (Entity)obj;
            if (entity.canBeCollidedWith() && entity != this.shootingEntity) {
                AxisAlignedBB aabb = entity.boundingBox.expand(0.3D, 0.3D, 0.3D);
                MovingObjectPosition intercept = aabb.calculateIntercept(start, end);
                if (intercept != null) {
                    double dist = start.distanceTo(intercept.hitVec);
                    if (dist < closestDist) {
                        closestDist = dist;
                        hitEntity = entity;
                    }
                }
            }
        }

        if (hitEntity != null) {
            mop = new MovingObjectPosition(hitEntity, Vec3.createVectorHelper(hitEntity.posX, hitEntity.posY + hitEntity.height / 2.0D, hitEntity.posZ));
        }

        if (mop != null) {
            this.onImpact(mop);
            return;
        }

        float drag = 0.99F;
        this.motionX *= drag;
        this.motionY *= drag;
        this.motionZ *= drag;

        if (this.ticksExisted > 200) {
            this.setDead();
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource source, float p_70097_2_)
    {
        return false;
    }
}
