package com.gildedgames.the_aether.blocks;

import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDivineral extends Block {

	public BlockDivineral() {
		super(Material.iron);

		this.setHardness(50F);
		this.setResistance(600000F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockTextureName("aether_legacy:divineral_block");
		setBlockName("Block Of Divineral");
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return true;
	}

}
