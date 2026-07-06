package com.gildedgames.the_aether.items;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import com.gildedgames.the_aether.entities.EntitiesAether;
import com.gildedgames.the_aether.entities.EntitiesAether.AetherEggInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAetherSpawnEgg extends Item {

	public static HashMap<Integer, AetherEggInfo> entityEggs = new LinkedHashMap<>();
	@SideOnly(Side.CLIENT)
	private IIcon theIcon;

	public ItemAetherSpawnEgg() {
		this.setHasSubtypes(true);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String s = (StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
		String s1 = EntitiesAether.getStringFromID(stack.getItemDamage());

		if (s1 != null) {
			s = s + " " + StatCollector.translateToLocal("entity." + s1 + ".name");
		}

		return s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int p_82790_2_) {
		AetherEggInfo entityegginfo = entityEggs.get(stack.getItemDamage());
		return entityegginfo != null ? (p_82790_2_ == 0 ? entityegginfo.primaryColor : entityegginfo.secondaryColor) : 16777215;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer entityPlayer, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (world.isRemote) {
			return true;
		} else {
			Block block = world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
			p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
			p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
			p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
			double d0 = 0.0D;

			if (p_77648_7_ == 1 && block.getRenderType() == 11) {
				d0 = 0.5D;
			}

			Entity entity = spawnCreature(world, stack.getItemDamage(), (double) p_77648_4_ + 0.5D, (double) p_77648_5_ + d0, (double) p_77648_6_ + 0.5D);

			if (entity != null) {
				if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
					((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
				}

				if (!entityPlayer.capabilities.isCreativeMode) {
					--stack.stackSize;
				}
			}

			return true;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p_77659_3_) {
		if (world.isRemote) {
			return stack;
		} else {
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, p_77659_3_, true);

			if (movingobjectposition == null) {
				return stack;
			} else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (!world.canMineBlock(p_77659_3_, i, j, k)) {
						return stack;
					}

					if (!p_77659_3_.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack)) {
						return stack;
					}

					if (world.getBlock(i, j, k) instanceof BlockLiquid) {
						Entity entity = spawnCreature(world, stack.getItemDamage(), i, j, k);
						if (entity != null) {
							if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
								((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
							}
							if (!p_77659_3_.capabilities.isCreativeMode) {
								--stack.stackSize;
							}
						}
					}
				}

				return stack;
			}
		}
	}

	public static Entity spawnCreature(World world, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_) {
		if (!entityEggs.containsKey(p_77840_1_)) {
			return null;
		} else {
			Entity entity = null;

			for (int j = 0; j < 1; ++j) {
				entity = EntitiesAether.createEntityByID(p_77840_1_, world);
				if (entity instanceof EntityLivingBase) {
					EntityLiving entityliving = (EntityLiving) entity;
					entity.setLocationAndAngles(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
					entityliving.rotationYawHead = entityliving.rotationYaw;
					entityliving.renderYawOffset = entityliving.rotationYaw;
					entityliving.onSpawnWithEgg(null);
					world.spawnEntityInWorld(entity);
					entityliving.playLivingSound();
				}
			}

			return entity;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
        return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
		return p_77618_2_ > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(p_77618_1_, p_77618_2_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
		for (AetherEggInfo entityegginfo : entityEggs.values()) {
			p_150895_3_.add(new ItemStack(p_150895_1_, 1, entityegginfo.spawnedID));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister p_94581_1_) {
		super.registerIcons(p_94581_1_);
		this.theIcon = p_94581_1_.registerIcon(this.getIconString() + "_overlay");
	}

}
