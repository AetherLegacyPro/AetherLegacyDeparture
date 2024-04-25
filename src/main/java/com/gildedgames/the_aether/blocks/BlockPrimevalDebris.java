package com.gildedgames.the_aether.blocks;

import java.util.Random;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPrimevalDebris extends Block {

	public BlockPrimevalDebris() {
		super(Material.iron);

		this.setHardness(30F);
		this.setResistance(600000F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockTextureName("aether_legacy:primeval_artifact");
		setBlockName("Primeval Artifact");
	}
	
	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
		Random random = new Random();

		if (this.getItemDropped(p_149690_5_, random, p_149690_7_) != Item.getItemFromBlock(this)) {
			int amount = 0;

			if (this == BlocksAether.primeval_artifact) {
				amount = MathHelper.getRandomIntegerInRange(random, 5, 10);
			}

			return amount;
		}

		return 0;
	}

}
