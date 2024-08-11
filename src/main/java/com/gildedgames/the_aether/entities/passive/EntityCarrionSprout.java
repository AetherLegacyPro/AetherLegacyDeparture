package com.gildedgames.the_aether.entities.passive;

import com.gildedgames.the_aether.AetherConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.passive.EntityAetherAnimal;
import com.gildedgames.the_aether.items.ItemsAether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCarrionSprout extends EntityAetherAnimal {

	public float sinage;

	public int poisonRemaining;

	private int reloadTime;

	public EntityCarrionSprout(World world) {
		super(world);

		this.sinage = this.rand.nextFloat() * 6F;
		this.poisonRemaining = this.rand.nextInt(3) + 2;

		this.setSize(this.rand.nextInt(4) + 1);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.setSize(0.75F + ((float) this.getSize() * 0.125F), 0.5F + ((float) this.getSize() * 0.075F));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	@Override
	public void entityInit() {
		super.entityInit();

		this.dataWatcher.addObject(20, new Byte((byte) 0));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote) {
            this.tickAnimation();
        }
        if (!this.isDead && !this.isCollided) {
            final double n = 0.0;
            this.motionZ = n;
            this.motionX = n;
        }

		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)) != BlocksAether.aether_grass) {
			this.setHealth(0.0F);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void tickAnimation() {
        if (this.hurtTime > 0) {
            this.sinage += 0.9f;
        }
        else {
            this.sinage += 0.15f;
        }
        if (this.sinage > 6.283186f) {
            this.sinage -= 6.283186f;
        }
    }

	@Override
	public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
		if (this.getHealth() >= 0) {
			return;
		}

		super.knockBack(entity, strength, xRatio, zRatio);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setByte("size", this.getSize());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		this.setSize(compound.getByte("size"));
	}

	public void setSize(int size) {
		this.dataWatcher.updateObject(20, (byte) size);
	}

	public byte getSize() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	@Override
	protected void dropFewItems(final boolean var1, final int var2) {
        this.dropItem(ItemsAether.wynberry, 1 + rand.nextInt(2));
	}

	@Override
	public void applyEntityCollision(Entity entity) {

	}

	@Override
	public void addVelocity(double x, double y, double z) {

	}

	@Override
	protected boolean isMovementBlocked() {
		return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable baby) {
		return null;
	}

	@Override
	protected String getHurtSound() {
		return "game.player.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "game.player.hurt.fall.big";
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);          
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_dirt|| this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.arctic_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.verdant_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.enchanted_aether_grass || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.divine_grass) && this.worldObj.getBlockLightValue(i, j, k) > 7 && canSpawn && this.rand.nextInt(AetherConfig.getCarrionSproutSpawnrate()) == 0 && super.getCanSpawnHere();
                       
    }

}

