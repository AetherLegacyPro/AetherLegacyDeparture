package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemAetherTool;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAetherOreNewAetheral extends Block {

	public BlockAetherOreNewAetheral(int level) {
		super(Material.rock);

		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", level);
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
		player.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
		player.addExhaustion(0.025F);

		ItemStack stack = player.getCurrentEquippedItem();

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0) {
			super.harvestBlock(worldIn, player, x, y, z, meta);

			return;
		}

		if ((stack != null && stack.getItem() instanceof ItemSkyrootTool && ((ItemAetherTool) stack.getItem()).toolType == EnumAetherToolType.PICKAXE)) {
			for (int i = 0; i < 3; ++i) {
				this.dropBlockAsItem(worldIn, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
			}
			
		} else {
			super.harvestBlock(worldIn, player, x, y, z, meta);
			}	
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return this == BlocksAether.aetheral_arkenium_ore ? ItemsAether.arkenium_fragement : ItemsAether.continuum_orb;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, fortune)) {
			int j = random.nextInt(fortune + 3) - 1;

			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(random) * (j + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
		Random random = new Random();

		if (this.getItemDropped(p_149690_5_, random, p_149690_7_) != Item.getItemFromBlock(this)) {
			int amount = 0;

			if (this == BlocksAether.aetheral_continuum_ore) {
				amount = MathHelper.getRandomIntegerInRange(random, 3, 7);
			} else if (this == BlocksAether.aetheral_arkenium_ore) {
				amount = MathHelper.getRandomIntegerInRange(random, 1, 3);
			}

			return amount;
		}

		return 0;
	}

}
