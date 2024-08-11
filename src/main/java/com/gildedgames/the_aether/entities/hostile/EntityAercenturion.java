package com.gildedgames.the_aether.entities.hostile;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

import java.util.List;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.projectile.EntityAercenturionProjectile;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAercenturion extends EntityMob
{
	public int shootTime;
	
    public EntityAercenturion(final World par1World) {
        super(par1World);
        this.setSize(1.0f, 2.0f);
        this.isImmuneToFire = true;
        this.experienceValue = 20;
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.8D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
    }
    
    @Override
   	public void fall(float distance) {
   	}
    
    public int getTotalArmorValue()
    {
        return 10;
    }
    
    protected String getLivingSound() {
        return "aether_legacy:aemob.aercenturion.say";
    }
    
    protected String getHurtSound() {
        return "aether_legacy:aemob.aercenturion.hurt";
    }
    
    protected String getDeathSound() {
        return "aether_legacy:aemob.aercenturion.death";
    }
    
    protected String getSeenenemy() {
        return "aether_legacy:aemob.aercenturion.seenEnemy";
    }
    
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.55f;
    }
    
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.irongolem.walk", 0.5F, 0.5F);
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j;
        int k;
        {
            j = this.rand.nextInt(3 + p_70628_2_);

            for (k = 0; k < j; ++k)
            {
                this.dropItem(ItemsAether.zanite_gemstone, 3);
            }
        }

        j = this.rand.nextInt(3 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
        	this.dropItem(Item.getItemFromBlock(BlocksAether.mythic_carved_stone), 6);
        }
    }
    
    public void onLivingUpdate()
	{	
    		if (this.worldObj.isRemote)
        	if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode == false) {  
        List<Entity> volume2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(5, 5, 5));
        for(Entity entity2 : volume2) {
        	if(entity2 instanceof EntityPlayer && this.canEntityBeSeen(entity2)) ((EntityPlayer)entity2).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1, true));
        	}
    	}
		
		super.onLivingUpdate();
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

				if (this.shootTime < 30) {
					this.shootTime += 5;
				}

				this.rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
				}
			
			}
				
		}
		
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}
	}
    
    public void shootTarget() {
    	//shootTarget
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			return;
		}			
			EntityAercenturionProjectile entityarrow2 = new EntityAercenturionProjectile(this.worldObj, this, 10.0F);
			this.playSound("aether_legacy:aemob.aercenturion.seenEnemy", 2.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
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
    public boolean attackEntityAsMob(final Entity target) {			

        if (target instanceof EntityPlayer) {

            ItemStack helmet = ((EntityPlayer) target).getCurrentArmor(3);
            ItemStack chest = ((EntityPlayer) target).getCurrentArmor(2);
            ItemStack legs = ((EntityPlayer) target).getCurrentArmor(1);
            ItemStack boots = ((EntityPlayer) target).getCurrentArmor(0);

            boolean hasdivineralHelmet = false;
            boolean hasdivineralChest = false;
            boolean hasdivineralLegs = false;
            boolean hasdivineralBoots = false;          


            if(helmet != null)
                hasdivineralHelmet = ((helmet.getItem() == ItemsAether.divineral_helmet) || (helmet.getItem() == ItemsAether.amplified_arkenium_helmet) || (helmet.getItem() == ItemsAether.amplified_continuum_helmet) || (helmet.getItem() == ItemsAether.amplified_neptune_helmet) || (helmet.getItem() == ItemsAether.amplified_valkyrie_helmet) || (helmet.getItem() == ItemsAether.amplified_zanite_helmet) || (helmet.getItem() == ItemsAether.amplified_phoenix_helmet) || (helmet.getItem() == ItemsAether.amplified_obsidian_helmet));

            if(chest != null)
                hasdivineralChest = ((chest.getItem() == ItemsAether.divineral_chestplate) || (chest.getItem() == ItemsAether.amplified_arkenium_chestplate) || (chest.getItem() == ItemsAether.amplified_continuum_chestplate) || (chest.getItem() == ItemsAether.amplified_neptune_chestplate) || (chest.getItem() == ItemsAether.amplified_valkyrie_chestplate) || (chest.getItem() == ItemsAether.amplified_zanite_chestplate) || (chest.getItem() == ItemsAether.amplified_phoenix_chestplate) || (chest.getItem() == ItemsAether.amplified_obsidian_chestplate));

            if(legs != null)
                hasdivineralLegs = ((legs.getItem() == ItemsAether.divineral_leggings) || (legs.getItem() == ItemsAether.amplified_arkenium_leggings) || (legs.getItem() == ItemsAether.amplified_continuum_leggings) || (legs.getItem() == ItemsAether.amplified_neptune_leggings) || (legs.getItem() == ItemsAether.amplified_valkyrie_leggings) || (legs.getItem() == ItemsAether.amplified_zanite_leggings) || (legs.getItem() == ItemsAether.amplified_phoenix_leggings) || (legs.getItem() == ItemsAether.amplified_obsidian_leggings));

            if(boots != null)
                hasdivineralBoots = ((boots.getItem() == ItemsAether.divineral_boots) || (boots.getItem() == ItemsAether.amplified_arkenium_boots) || (boots.getItem() == ItemsAether.amplified_continuum_boots) || (boots.getItem() == ItemsAether.amplified_neptune_boots) || (boots.getItem() == ItemsAether.amplified_valkyrie_boots) || (boots.getItem() == ItemsAether.amplified_zanite_boots) || (boots.getItem() == ItemsAether.amplified_phoenix_boots) || (boots.getItem() == ItemsAether.amplified_obsidian_boots) || (boots.getItem() == ItemsAether.amplified_agility_boots));         
            
            if (hasdivineralHelmet || hasdivineralChest || hasdivineralLegs || hasdivineralBoots) {

            	target.attackEntityFrom(DamageSource.magic, 6.0F);
            	this.worldObj.setEntityState(this, (byte)4);
                final boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(6 + this.rand.nextInt(5)));
                if (flag) {
                	target.motionY += 0.4000000059604645;
                }
                this.playSound("aether_legacy:aemob.aercenturion.seenEnemy", 1.0f, 1.0f);
                return flag;
            }                  
            
            else {
            	target.attackEntityFrom(DamageSource.magic, 10.0F);
            	this.worldObj.setEntityState(this, (byte)4);
                final boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(12 + this.rand.nextInt(5)));
                if (flag) {
                	target.motionY += 0.4000000059604645;
                }
                this.playSound("aether_legacy:aemob.aercenturion.seenEnemy", 2.0f, 1.0f);
                return flag;
            }
             
        }
        
        return true;
    }
    
    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getEntity() instanceof EntityPlayer entityplayer)
        {

			entityplayer.triggerAchievement(AchievementsAether.kill_aercenturion);
            
        }
            
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }
}
