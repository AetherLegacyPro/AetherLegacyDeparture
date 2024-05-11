package com.gildedgames.the_aether.blocks;

import java.util.Random;

import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import com.gildedgames.the_aether.entities.hostile.EntitySentry;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockCrackedOblitusStone2 extends Block {

	
	public BlockCrackedOblitusStone2() {
		super(Material.rock);

		this.setHardness(1F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName("aether_legacy:cracked_oblitus_stone");
		setBlockName("Cyro Stone");
	}
	
	@Override
	protected boolean canSilkHarvest() {
        return false;
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(BlocksAether.cracked_oblitus_stone_2);
	}

}
