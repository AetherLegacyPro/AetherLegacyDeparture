package com.gildedgames.the_aether.entities.bosses;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityAetherMob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityAncientFireMinion extends EntityAetherMob {

	public EntityAncientFireMinion(World world) {
		super(world);

		this.isImmuneToFire = true;
		this.setSize(1.1F, 1.8F);
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.5D, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(55.0D);
	}
	
	public int getTotalArmorValue()
    {
        return 5;
    }

	@Override
	public void onUpdate() {
		super.onUpdate();

		for (int i = 0; i < 3; i++) {
			double d = rand.nextFloat() - 0.5F;
			double d1 = rand.nextFloat();
			double d2 = rand.nextFloat() - 0.5F;
			double d3 = posX + d * d1;
			double d4 = (this.boundingBox.minY + d1) + 0.1D;
			double d5 = posZ + d2 * d1;
			this.worldObj.spawnParticle("flame", d3, d4, d5, 0.0D, -0.075000002980232239D, 0.0D);
		}
	}
	
	public void onLivingUpdate()
    {
	 if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
	 {
	  this.setDead();
	 }
			
	  this.despawnEntity(); 
	  
	  super.onLivingUpdate();
	}
	
	@Override
	protected void dropFewItems(boolean var1, int var2) {
		this.dropItem(Item.getItemFromBlock(BlocksAether.ancient_hellfire_stone), 3);
	}
	
	protected boolean isValidLightLevel()
    {
        return true;
    }
	
	public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        final boolean canSpawn = this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        return (this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_light_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone || this.worldObj.getBlock(i, j - 1, k) == BlocksAether.locked_divine_hellfire_stone) && this.worldObj.getBlockLightValue(i, j, k) < 14 && canSpawn;
                       
    }

}
