package com.gildedgames.the_aether.player.abilities;

import java.util.ArrayList;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class AbilityAccessories implements IAetherAbility {

	private final IPlayerAether player;

	private boolean invisibilityUpdate;

	private boolean stepUpdate;

	public AbilityAccessories(IPlayerAether player) {
		this.player = player;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void onUpdate() {
		if (this.player.getEntity().ticksExisted % 400 == 0) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.zanite_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.zanite_pendant));
			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.auralite_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.auralite_pendant));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_auralite_pendant));
			
			if (Loader.isModLoaded("etfuturum")) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.copper_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.copper_pendant));
			}
			
		}
		
		if (this.player.getEntity().ticksExisted % 1200 == 0) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.diamond_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.diamond_pendant));
			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.divineral_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.divineral_pendant));
			
			if (Loader.isModLoaded("etfuturum")) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.netherite_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.netherite_pendant));
			}
		}
		
		if (this.player.getEntity().ticksExisted % 2000 == 0) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.valkyrie_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_valkyrie_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_valkyrie_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.haste_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_haste_ring));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_haste_ring));
			if (AetherConfig.MiscItemDamageable() == true) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.invisibility_gem));
			}
		}
		
		if (this.player.getEntity().ticksExisted % 2500 == 0 && AetherConfig.MiscItemDamageable() == true) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.golden_feather));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_golden_feather));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_golden_feather));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.regeneration_stone));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_regeneration_stone));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.iron_bubble));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_iron_bubble));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_iron_bubble));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.healing_matrix));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_auralite_pendant));
			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.elysian_ring));	
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.reinforced_elysian_ring));	
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_elysian_ring));	
		}
		
		if (this.player.getEntity().ticksExisted % 2000 == 0 && AetherConfig.GlovesDamageable() == true) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.leather_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.chain_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.iron_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.golden_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.diamond_gloves));
			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.zanite_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.arkenium_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.continuum_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.gravitite_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.phoenix_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.obsidian_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.valkyrie_gloves));			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.neptune_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.elysian_gloves));
			
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.divineral_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_arkenium_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_continuum_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_neptune_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_valkyrie_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_obsidian_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_phoenix_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_zanite_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.amplified_elysian_gloves));
			
			if (Loader.isModLoaded("etfuturum")) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.copper_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.netherite_gloves));
			}
			
			if (Loader.isModLoaded("nova_craft")) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.vanite_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.klangite_gloves));
			}
			
			if (Loader.isModLoaded("netherlicious")) {
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.effrine_gloves));
			this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.heavy_blaze_gloves));
			}
		}

		if (!this.player.getEntity().worldObj.isRemote && (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.ice_ring)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.ice_pendant)))) {
			int i = MathHelper.floor_double(this.player.getEntity().posX);
			int j = MathHelper.floor_double(this.player.getEntity().boundingBox.minY);
			int k = MathHelper.floor_double(this.player.getEntity().posZ);

			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					for (int z = k - 1; z <= k + 1; z++) {
						Block block = this.player.getEntity().worldObj.getBlock(x, y, z);
						Block setBlock = (block == Blocks.water || block == Blocks.flowing_water) ? Blocks.ice : (block == Blocks.lava || block == Blocks.flowing_lava) ? Blocks.obsidian : null;

						if (setBlock != null) {
							this.player.getEntity().worldObj.setBlock(x, y, z, setBlock);
							this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.ice_ring));
							this.player.getAccessoryInventory().damageWornStack(1, new ItemStack(ItemsAether.ice_pendant));
						}
					}
				}
			}
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.iron_bubble)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.reinforced_iron_bubble)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.amplified_iron_bubble))) {
			this.player.getEntity().setAir(0);
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.agility_cape))) {
			if (!this.player.getEntity().isSneaking())
			{
				this.player.getEntity().stepHeight = 1.0F;
				this.stepUpdate = true;
			} else {
				if (this.stepUpdate) {
					this.player.getEntity().stepHeight = 0.5F;
					this.stepUpdate = false;
				}
			}
		} else {
			if (this.stepUpdate) {
				this.player.getEntity().stepHeight = 0.5F;
				this.stepUpdate = false;
			}
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.invisibility_cape))) {
			this.player.getEntity().setInvisible(true);
			this.invisibilityUpdate = true;
		} else if (!this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.invisibility_cape)) && !this.player.getEntity().isPotionActive(Potion.invisibility)) {
			if (this.invisibilityUpdate) {
				this.player.getEntity().setInvisible(false);
				this.invisibilityUpdate = false;
			}
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.regeneration_stone)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.reinforced_regeneration_stone))) {
			if (this.player.getEntity().getHealth() < this.player.getEntity().getMaxHealth() && this.player.getEntity().getActivePotionEffect(Potion.regeneration) == null) {
				this.player.getEntity().addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 0));
			}
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.phoenix_gloves)) && this.player.getEntity().isWet())
		{
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0)
			{
				ItemStack stack = this.player.getAccessoryInventory().getStackInSlot(AccessoryType.GLOVES);

				this.player.getAccessoryInventory().damageWornStack(1, stack);

				if (this.player.getAccessoryInventory().getStackInSlot(AccessoryType.GLOVES) == null)
				{
					ItemStack outcomeStack = new ItemStack(ItemsAether.obsidian_gloves);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					this.player.getAccessoryInventory().setAccessorySlot(AccessoryType.GLOVES, outcomeStack);
				}
			}
		}
		
		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.amplified_phoenix_gloves)) && this.player.getEntity().isWet())
		{
			if (this.player.getEntity().worldObj.getTotalWorldTime() % 5 == 0)
			{
				ItemStack stack = this.player.getAccessoryInventory().getStackInSlot(AccessoryType.GLOVES);

				this.player.getAccessoryInventory().damageWornStack(1, stack);

				if (this.player.getAccessoryInventory().getStackInSlot(AccessoryType.GLOVES) == null)
				{
					ItemStack outcomeStack = new ItemStack(ItemsAether.amplified_obsidian_gloves);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), outcomeStack);

					this.player.getAccessoryInventory().setAccessorySlot(AccessoryType.GLOVES, outcomeStack);
				}
			}
		}

		if (this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.golden_feather)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.reinforced_golden_feather)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.amplified_golden_feather)) || this.player.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.valkyrie_cape))) {
			if (!this.player.getEntity().onGround && this.player.getEntity().motionY < 0.0D && !this.player.getEntity().isInWater() && !this.player.getEntity().isSneaking()) {
				this.player.getEntity().motionY *= 0.59999999999999998D;
			}

			this.player.getEntity().fallDistance = -1F;
		}
	}
	
	}
	
		
