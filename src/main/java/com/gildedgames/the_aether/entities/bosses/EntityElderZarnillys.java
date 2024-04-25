package com.gildedgames.the_aether.entities.bosses;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.EntityCreature;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.effects.EffectInebriation;
import com.gildedgames.the_aether.entities.effects.PotionInebriation;
import com.gildedgames.the_aether.entities.hostile.EntityAetherMob;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;
import com.gildedgames.the_aether.entities.projectile.EntityAercenturionProjectile;
import com.gildedgames.the_aether.entities.projectile.EntityPoisonNeedle;
import com.gildedgames.the_aether.entities.projectile.EntityPoisonSnowball;
import com.gildedgames.the_aether.items.ItemsAether;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import javax.vecmath.Point3d;

import java.util.List;
import java.util.Map;
import net.minecraft.entity.IEntityMultiPart;

public class EntityElderZarnillys extends EntityMob
{
	public int shootTime;
	
    public EntityElderZarnillys(final World par1World) {
        super(par1World);
        this.setSize(2.5f, 1.87f);
        this.isImmuneToFire = true;
        this.experienceValue = 25;
        this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 8.0F));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityFlynx.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Byte((byte)0));
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(10.8D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0D);
        this.setHealth(70);
    }
    
    public boolean canBreatheUnderwater()
    {
        return true;
    }
    
    @Override
   	public void fall(float distance) {
   	}
    
    public int getTotalArmorValue()
    {
        return 15;
    }
    
    protected void func_70036_a(final int par1, final int par2, final int par3, final int par4) {
        this.playSound("mob.cow.step", 0.15f, 1.0f);
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j;
        int k;
        {
            j = this.rand.nextInt(3 + p_70628_2_);

            for (k = 0; k < j; ++k)
            {
                this.dropItem(ItemsAether.zarnillys_scales, 3 + rand.nextInt(3));
            }
        }

        j = this.rand.nextInt(2 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
        	this.dropItem(Item.getItemFromBlock(BlocksAether.icestone), 5 + rand.nextInt(10));
        }
        
        j = this.rand.nextInt(1 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
        	this.dropItem(ItemsAether.arkenium_fragement, 2 + rand.nextInt(4));
        }
    }
    
    protected void dropRareDrop(int p_70600_1_)
    {
    	this.dropItem(ItemsAether.divine_essence, 1);
    }
    
    @Override
    public void onLivingUpdate()
	{	
    	//if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode = false) {   			
        
        List<Entity> volume2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(2, 2, 2));
        for(Entity entity2 : volume2) {
        	if(entity2 instanceof EntityPlayer && this.canEntityBeSeen(entity2)) ((EntityPlayer)entity2).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 0, true));
        	}
    	//}
		
		super.onLivingUpdate();
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

   				if (this.shootTime >= 30 && this.canEntityBeSeen(this.getEntityToAttack())) {
   					this.shootTarget();
   					this.shootTime = -60;
   				}

   				if (this.shootTime < 20) {
   					this.shootTime += 8;
   				}

   				this.rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
   				}
   			
   			}
   				
   		}
   		
   	}
       
       public void shootTarget() {
   		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
   			return;
   		}  			 		
   			EntityPoisonSnowball entityarrow2 = new EntityPoisonSnowball(this.worldObj, this, 2.0F);
   			this.playSound("aether_legacy:aemob.zarnillys.hurt", 2.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
   			this.worldObj.spawnEntityInWorld(entityarrow2);
   			
   		
   	}
       
       private boolean shouldAttackPlayer(EntityPlayer p_70821_1_)
       {
               Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
               Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
               double d0 = vec31.lengthVector();
               vec31 = vec31.normalize();
               double d1 = vec3.dotProduct(vec31);
               return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
       }
    
    @Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
    	if (source.getEntity() == null || !(source.getEntity() instanceof EntityPlayer) || source.isExplosion()) {
            return false;
        }
    	
		if (source.getEntity() != null)
		{
			if (source.getEntity() instanceof EntityLivingBase)
			{
				this.setAttackTarget((EntityLivingBase) source.getEntity());
			}
		}

		return super.attackEntityFrom(source, amount);
	}
    
    @Override
    public boolean attackEntityAsMob(final Entity target) {
    	boolean flag = super.attackEntityAsMob(target);
    	((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.poison.id, 150, 1));
    	((EntityLivingBase) target).addPotionEffect(new EffectInebriation(PotionInebriation.inebriation.id, 150, 0));
    	
    	return true;
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    protected String getLivingSound() {
        return "aether_legacy:aemob.zarnillys.say";
    }
    
    protected String getHurtSound() {
        return "aether_legacy:aemob.zarnillys.hurt";
    }
    
    protected String getDeathSound() {
        return "aether_legacy:aemob.zarnillys.hurt";
    }
    
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.85f;
    }
    
    @Override
    public boolean canDespawn() {
        return false;
    }
}
