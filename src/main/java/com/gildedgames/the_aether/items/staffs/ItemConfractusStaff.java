package com.gildedgames.the_aether.items.staffs;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemConfractusStaff extends Item {

	public ItemConfractusStaff() {
		this.setFull3D();
		setMaxDamage(30);
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.aether_loot;
	}

	@Override
	public boolean onItemUse(ItemStack heldItem, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if ((world.getBlock(x, y, z) == BlocksAether.carved_trap) || (world.getBlock(x, y, z) == BlocksAether.locked_carved_stone)) {
			
			world.setBlock(x, y, z, BlocksAether.carved_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(1, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.divine_carved_trap) || (world.getBlock(x, y, z) == BlocksAether.locked_divine_carved_stone)) {
			
			world.setBlock(x, y, z, BlocksAether.locked_carved_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(2, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.locked_mythic_carved_stone)) {
			
			world.setBlock(x, y, z, BlocksAether.locked_divine_carved_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(10, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.locked_sentry_stone) || (world.getBlock(x, y, z) == BlocksAether.locked_divine_sentry_stone) || (world.getBlock(x, y, z) == BlocksAether.locked_mythic_sentry_stone)) {
			
			world.setBlock(x, y, z, Blocks.air);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(2, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.angelic_trap)) {
			
			world.setBlock(x, y, z, BlocksAether.angelic_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(1, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.divine_angelic_trap)) {
			
			world.setBlock(x, y, z, BlocksAether.divine_angelic_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(2, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.hellfire_trap)) {
			
			world.setBlock(x, y, z, BlocksAether.hellfire_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(4, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.fuse_trap) || (world.getBlock(x, y, z) == BlocksAether.fuse_trap_2)) {
			
			world.setBlock(x, y, z, BlocksAether.locked_fuse_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(4, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.locked_fuse_stone)) {
			
			world.setBlock(x, y, z, BlocksAether.fuse_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(1, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.locked_creeping_stone)) {
			
			world.setBlock(x, y, z, BlocksAether.creeping_stone);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(1, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		else if ((world.getBlock(x, y, z) == BlocksAether.treasure_chest)) {
			
			world.setBlock(x, y, z, BlocksAether.treasure_chest_breakable);
			world.playSoundEffect(x, y, z, "random.explode", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
			world.spawnParticle("smoke", x, y + 0.5, z, 0.0, 0.0, 0.0);
			heldItem.damageItem(2, player);
			player.triggerAchievement(AchievementsAether.confractus_staff);
		}
		
		return true;
	}

}