package com.gildedgames.the_aether.items.util;

import com.gildedgames.the_aether.items.tools.ItemAmplifiedSkyrootTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedSkyrootTool;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import java.util.ArrayList;

public class DoubleDropHelper {

	public static void dropBlock(EntityPlayer player, int x, int y, int z, Block block, int meta) {
		player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
		player.addExhaustion(0.025F);
		int size = meta == 0 ? 2 : 1;
		ItemStack stack = player.inventory.getCurrentItem();
        boolean flag = stack != null && (stack.getItem() instanceof ItemSkyrootTool || stack.getItem() instanceof ItemTippedSkyrootTool || stack.getItem() instanceof ItemAmplifiedSkyrootTool);

		if (block.canSilkHarvest(player.worldObj, player, x, y, z, meta) && EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0) {
			ArrayList<ItemStack> items = new ArrayList<>();
			ItemStack itemstack = createStackedBlock(meta, block);
			items.add(itemstack);
			ForgeEventFactory.fireBlockHarvesting(items, player.worldObj, block, x, y, z, meta, 0, 1.0f, true, player);

            for (ItemStack is : items) {
				dropBlockAsItem(player.worldObj, x, y, z, is);
			}

			return;
		}

		if (!flag) {
			block.dropBlockAsItem(player.worldObj, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
			return;
		}

        if (stack.getItem().getDigSpeed(stack, block, meta) > 1) {
			for (int i = 0; i < size; ++i) {
				block.dropBlockAsItem(player.worldObj, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
			}
		} else {
			block.dropBlockAsItem(player.worldObj, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
		}
	}

	protected static ItemStack createStackedBlock(int p_149644_1_, Block block) {
		int j = 0;
		Item item = Item.getItemFromBlock(block);

		if (item != null && item.getHasSubtypes()) {
			j = p_149644_1_;
		}

		return new ItemStack(item, 1, j);
	}

	protected static void dropBlockAsItem(World world, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {
        //do not drop items while restoring blockstates, prevents item dupe
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) {
			float f = 0.7F;
			double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, (double)p_149642_2_ + d0, (double)p_149642_3_ + d1, (double)p_149642_4_ + d2, p_149642_5_);
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
	}
}
