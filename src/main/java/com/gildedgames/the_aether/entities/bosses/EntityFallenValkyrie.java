package com.gildedgames.the_aether.entities.bosses;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityFallenValkyrie extends EntityMob {

	private int attackTime;

	public int angerLevel;

	public int timeLeft, chatTime;

	public double safeX, safeY, safeZ;

	public float sinage;

	public double lastMotionY;

	public int teleTimer;

	public EntityFallenValkyrie(World world) {
		super(world);
		setSize(0.8F, 1.6F);
		this.teleTimer = this.rand.nextInt(250);
		this.timeLeft = 1200;
		this.safeX = this.posX;
		this.safeY = this.posY;
		this.safeZ = this.posZ;
		this.tasks.addTask(2, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 0.65D, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityValkyrie.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.10);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0D);
	}

	public void swingArm() {
		if (!this.isSwingInProgress) {
			this.isSwingInProgress = true;
		}
	}

	private void chatItUp(EntityPlayer player, String s) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (this.chatTime <= 0) {
			if (side.isClient()) {
				Aether.proxy.sendMessage(player, s);
			}

			this.chatTime = 60;
		}
	}

	public void teleport(double x, double y, double z, int rad) {
		int a = this.rand.nextInt(rad + 1);
		int b = this.rand.nextInt(rad / 2);
		int c = rad - a;

		a *= ((this.rand.nextInt(2) * 2) - 1);
		b *= ((this.rand.nextInt(2) * 2) - 1);
		c *= ((this.rand.nextInt(2) * 2) - 1);

		x += a;
		y += b;
		z += c;

		int newX = (int) Math.floor(x - 0.5D);
		int newY = (int) Math.floor(y - 0.5D);
		int newZ = (int) Math.floor(z - 0.5D);

		boolean flag = false;

		for (int q = 0; q < 32 && !flag; q++) {
			this.rand.nextInt(rad / 2);
			this.rand.nextInt(rad / 2);
			int j = newY + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
			this.rand.nextInt(rad / 2);
			this.rand.nextInt(rad / 2);

			if (j > 124 || j < 5) {
				continue;
			}
		}

		if (!flag) {
			teleFail();
		} else {
			spawnExplosionParticle();
			setPosition((double) newX + 0.5D, (double) newY + 0.5D, (double) newZ + 0.5D);
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.moveForward = this.moveStrafing = this.rotationPitch = this.rotationYaw = 0.0F;
			this.isJumping = false;
			this.renderYawOffset = this.rand.nextFloat() * 360F;
			spawnExplosionParticle();
			this.teleTimer = this.rand.nextInt(40);
		}
	}

	public void teleFail() {
		this.teleTimer -= (this.rand.nextInt(40) + 40);

		if (this.posY <= 0D) {
			this.teleTimer = 446;
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.teleTimer++;
		--this.attackTime;

		if (this.teleTimer >= 450) {
			if (this.getAttackTarget() != null) {
				teleport(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 7);
			} else if (!this.onGround) {
				teleport(this.safeX, this.safeY, this.safeZ, 6);
			}
		} else if (this.teleTimer < 446 && (this.posY <= 0D || this.posY <= (this.safeY - 16D))) {
			this.teleTimer = 446;
		} else if ((this.teleTimer % 5) == 0 && this.getAttackTarget() != null && !canEntityBeSeen(this.getAttackTarget())) {
			this.teleTimer += 100;
		}

		if (this.onGround && this.teleTimer % 10 == 0) {
			this.safeX = this.posX;
			this.safeY = this.posY;
			this.safeZ = this.posZ;
		}

		if (this.chatTime > 0) {
			this.chatTime--;
		}
	}

	@Override
	public void onUpdate() {
		this.lastMotionY = motionY;
		super.onUpdate();

		if (!this.onGround && this.getAttackTarget() != null && this.lastMotionY >= 0.0D && this.motionY < 0.0D && getDistanceToEntity(this.getAttackTarget()) <= 16F && canEntityBeSeen(this.getAttackTarget())) {
			double a = this.getAttackTarget().posX - posX;
			double b = this.getAttackTarget().posZ - posZ;
			double angle = Math.atan2(a, b);
			this.motionX = Math.sin(angle) * 0.25D;
			this.motionZ = Math.cos(angle) * 0.25D;
		}

		if (!this.onGround && !isOnLadder() && Math.abs(this.motionY - this.lastMotionY) > 0.07D && Math.abs(this.motionY - this.lastMotionY) < 0.09D) {
			this.motionY += 0.055F;

			if (this.motionY < -0.275F) {
				this.motionY = -0.275F;
			}
		}

		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setAttackTarget(null);
		}

		if (!this.onGround) {
			this.sinage += 0.75F;
		} else {
			this.sinage += 0.15F;
		}

		if (this.sinage > 3.141593F * 2F) {
			this.sinage -= (3.141593F * 2F);
		}

		if (this.getAttackTarget() instanceof EntityPlayer player) {

			if (this.getHealth() <= 0) {
				int pokey = rand.nextInt(3);

				if (pokey == 2) {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.defeated.1"));
				} else if (pokey == 1) {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.defeated.2"));
				} else {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.defeated.3"));
				}

				this.setDead();
			}

			if (player.getHealth() <= 0 && player.isDead) {
				int pokey = rand.nextInt(3);

				if (pokey == 2) {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.playerdead.1"));
				} else if (pokey == 1) {
					chatItUp(player, StatCollector.translateToLocalFormatted("gui.fallenvalkyrie.dialog.playerdead.2", player.getDisplayName()));
				} else {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.playerdead.3"));
				}

				this.setAttackTarget(null);
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setInteger("teleTimer", this.teleTimer);
		compound.setInteger("timeLeft", this.timeLeft);
		compound.setDouble("safePosX", this.safeX);
		compound.setDouble("safePosY", this.safeY);
		compound.setDouble("safePosZ", this.safeZ);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		this.teleTimer = compound.getInteger("teleTimer");
		this.timeLeft = compound.getInteger("timeLeft");
		this.safeX = compound.getInteger("safePosX");
		this.safeY = compound.getInteger("safePosY");
		this.safeZ = compound.getInteger("safePosZ");
	}

	public boolean attackEntityFrom(DamageSource ds, float i) {
		if (ds.getEntity() instanceof EntityPlayer && worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
			EntityPlayer player = (EntityPlayer) ds.getEntity();

			if (this.getAttackTarget() == null) {
				int pokey = rand.nextInt(3);
				if (pokey == 2) {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.attack.1"));
				} else if (pokey == 1) {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.attack.2"));
				} else {
					chatItUp(player, StatCollector.translateToLocal("gui.fallenvalkyrie.dialog.attack.3"));
				}

				this.setAttackTarget(player);
			} 
			 else {
				this.teleTimer -= 10;
			}

		} else {
			teleport(this.posX, this.posY, this.posZ, 8);
			extinguish();
			return false;
		}

		boolean flag = super.attackEntityFrom(ds, i);

		if (flag && this.getHealth() <= 0) {
			spawnExplosionParticle();
			this.setDead();
		}
		
		EntityPlayer player = (EntityPlayer) ds.getEntity();
		ItemStack stack = player.inventory.getCurrentItem();
        
		if (stack != null && stack.getItem() != null && stack.getItem() == ItemsAether.builder_slayer) {
			player.triggerAchievement(AchievementsAether.builders_beware);
		}

		return flag;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean flag = false;

		if (this.attackTime <= 0 && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
			this.attackTime = 20;
			swingArm();
			flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8);			
		}

		return flag;
	}

	@Override
	protected void dropFewItems(boolean var1, int var2) {
		dropItem(ItemsAether.osmium_insignia, 1);
		dropItem(ItemsAether.arkenium_fragement, 2);
	}

	@Override
	public void fall(float distance) {
	}

	@Override
	public boolean canDespawn() {
		return true;
	}

	@Override
	protected String getHurtSound() {
		return "game.player.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "game.player.hurt.fall.big";
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

