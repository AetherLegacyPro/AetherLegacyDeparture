package com.gildedgames.the_aether.items.weapons;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.entities.bosses.crystal_dragon.EntityCrystalDragon;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemAmplifiedDragonSlayer extends ItemSword {

	private float damageDealt;
	private static ToolMaterial dragonMaterial = EnumHelper.addToolMaterial("DRAGON", 0, 4191, 2.0F, 6.0F, 10);
	
	public ItemAmplifiedDragonSlayer() {
		super(dragonMaterial);
		this.setCreativeTab(AetherCreativeTabs.weapons);
		this.setMaxDamage(4191);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairStack) {
		return false;
	}
	
	 public float damage() {
	      return this.damageDealt;
	   }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
	      this.damageDealt = dragonMaterial.getDamageVsEntity();
	      
	      String s = EntityList.getEntityString((Entity) entity);
	      
	      if (entity instanceof EntityCrystalDragon || entity instanceof EntityDragon || s.contains("Genesis_dragon") ) {
	         this.damageDealt += 38.0F;
	      }

	      entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), this.damageDealt);
	      return super.hitEntity(stack, entity, player);
	   }
	
	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		return super.getDigSpeed(itemstack, block, meta) * (2.0F * (float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage() + 0.5F);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }

}
