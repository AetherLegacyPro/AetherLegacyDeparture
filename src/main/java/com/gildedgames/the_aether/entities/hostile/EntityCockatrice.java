package com.gildedgames.the_aether.entities.hostile;

import com.gildedgames.the_aether.AetherConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;
import com.gildedgames.the_aether.entities.projectile.EntityPoisonNeedle;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

public class EntityCockatrice extends EntityMob {

	public float wingRotation, destPos, prevDestPos, prevWingRotation;

	public int shootTime, ticksUntilFlap;

	public EntityCockatrice(World world) {
		super(world);

		this.stepHeight = 1.0F;
		this.setSize(1.0F, 2.0F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 8.0F));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityFlynx.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.2D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
		this.setHealth(15);
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(AetherConfig.getCockatriceSpawnrate()) == 0 && super.getCanSpawnHere();
	}

	@Override
	public boolean isPotionApplicable(PotionEffect effect) {
		return effect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(effect);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack))
        {
			
		if (this.getEntityToAttack() != null) {
			if (this.getAttackTarget() instanceof EntityPlayer && ((EntityPlayer) this.getAttackTarget()).capabilities.isCreativeMode) {
				this.setAttackTarget(null);
			}
			else {
				double d = this.getEntityToAttack().posX - this.posX;
				double d1 = this.getEntityToAttack().posZ - this.posZ;

				this.getLookHelper().setLookPositionWithEntity(this.getEntityToAttack(), 30.0F, 30.0F);

				if (this.shootTime >= 20 && this.canEntityBeSeen(this.getEntityToAttack())) {
					this.shootTarget();
					this.shootTime = -60;
				}

				if (this.shootTime < 20) {
					this.shootTime += 4;
				}

				this.rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
				}
			}
		}

		this.updateWingRotation();

		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}
	}
	
	private boolean shouldAttackPlayer(EntityPlayer p_70821_1_)
    {
        ItemStack itemstack = p_70821_1_.inventory.armorInventory[3];

        if ((itemstack != null && itemstack.getItem() == ItemsAether.arkenium_helmet)      	 
        	 ||(itemstack != null && itemstack.getItem() == ItemsAether.amplified_arkenium_helmet))
        {
        p_70821_1_.triggerAchievement(AchievementsAether.arkenium_helmet);
            return false;
        }
        else
        {
            Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
            Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
            double d0 = vec31.lengthVector();
            vec31 = vec31.normalize();
            double d1 = vec3.dotProduct(vec31);
            return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
        }
    }

	public void shootTarget() {
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			return;
		}

		EntityArrow entityarrow = new EntityPoisonNeedle(this.worldObj, this, 1.0F);
		this.playSound("random.bow", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		this.worldObj.spawnEntityInWorld(entityarrow);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.59999999999999998D;
		}
	}

	public void updateWingRotation() {
		if (!this.onGround) {
			if (this.ticksUntilFlap == 0) {
				this.worldObj.playSoundAtEntity(this, "mob.bat.takeoff", 0.15F, MathHelper.clamp_float(this.rand.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp_float(this.rand.nextFloat(), 0f, 0.3f));

				this.ticksUntilFlap = 8;
			} else {
				this.ticksUntilFlap--;
			}
		}

		this.prevWingRotation = this.wingRotation;
		this.prevDestPos = this.destPos;

		this.destPos += 0.2D;
		this.destPos = EntityMoa.minMax(0.01F, 1.0F, this.destPos);

		if (this.onGround) {
			this.destPos = 0.0F;
		}

		this.wingRotation += 1.233F;
	}

	@Override
	public void fall(float distance) {
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (source.getEntity() != null)
		{
			if (source.getEntity() instanceof EntityLivingBase)
			{
				this.setAttackTarget((EntityLivingBase) source.getEntity());
			}
		}

		return super.attackEntityFrom(source, amount);
	}
	
	public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.getEntity();
            
            entityplayer.triggerAchievement(AchievementsAether.aether_hunter);
            
        }
            
    }

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	@Override
	protected String getLivingSound() {
		return "aether_legacy:aemob.cockatrice.say";
	}

	@Override
	protected String getHurtSound() {
		return "aether_legacy:aemob.cockatrice.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "aether_legacy:aemob.cockatrice.death";
	}

	@Override
	protected void dropFewItems(boolean var1, int var2) {
		this.dropItem(ItemsAether.cockatrice_feather, 1 + this.rand.nextInt(4));
	}

}