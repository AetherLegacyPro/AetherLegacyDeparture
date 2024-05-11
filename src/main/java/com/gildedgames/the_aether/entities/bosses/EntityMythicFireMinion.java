package com.gildedgames.the_aether.entities.bosses;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.hostile.EntityAetherMob;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMythicFireMinion extends EntityAetherMob {

	public EntityMythicFireMinion(World world) {
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
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.8D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
	}
	
	public int getTotalArmorValue()
    {
        return 10;
    }

	@Override
	public void onUpdate() {
		super.onUpdate();

		for (int i = 0; i < 4; i++) {
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
		this.dropItem(Item.getItemFromBlock(BlocksAether.divine_hellfire_stone), 3);
	}

}
