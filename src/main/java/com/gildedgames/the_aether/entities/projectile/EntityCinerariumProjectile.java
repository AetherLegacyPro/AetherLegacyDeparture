package com.gildedgames.the_aether.entities.projectile;

import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCinerariumProjectile extends EntityFireball {

    public EntityCinerariumProjectile(World world) {
        super(world);
        this.setSize(0.0125F, 0.0125F);
    }

    public EntityCinerariumProjectile(World world, EntityLivingBase entityLivingBase, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
        super(world, entityLivingBase, p_i1771_3_, p_i1771_5_, p_i1771_7_);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityCinerariumProjectile(World world, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
        super(world, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
        this.setSize(0.3125F, 0.3125F);
    }

    public void onLivingUpdate() {
    	 for (int i = 0; i < 2; ++i) {
             this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
         }
    	 this.setFire(1);
    }

    protected void onImpact(MovingObjectPosition position) {
        if (!this.worldObj.isRemote) {
            if (position.entityHit != null) {

                if (position.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F)) {
                    position.entityHit.setFire(15);
                }
            }
            else {
                int i = position.blockX;
                int j = position.blockY;
                int k = position.blockZ;

                switch (position.sideHit) {
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

                if (this.worldObj.isAirBlock(i, j, k)) {
                    this.worldObj.setBlock(i, j, k, BlocksAether.hellfire);
                }
            }

            if (position.entityHit instanceof EntityPlayerMP) {
				((EntityPlayerMP) position.entityHit).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(position.entityHit));
			}

            this.setDead();
        }
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean attackEntityFrom(DamageSource source, float p_70097_2_) {
        return false;
    }
}
