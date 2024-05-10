package com.gildedgames.the_aether.blocks.decorative;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class BlockAetherSlab extends BlockSlab {

	private String name;

	public BlockAetherSlab(String name, boolean double_slab, Material materialIn) {
		super(double_slab, materialIn);
		this.name = name;

		this.setLightOpacity(0);
		this.setStepSound(materialIn == Material.wood ? soundTypeWood : soundTypeStone);
	}

	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(this.getDroppedSlab());
	}

	public Block getDroppedSlab() {
		if (this == BlocksAether.skyroot_double_slab) {
			return BlocksAether.skyroot_slab;
		} else if (this == BlocksAether.carved_double_slab) {
			return BlocksAether.carved_slab;
		} else if (this == BlocksAether.angelic_double_slab) {
			return BlocksAether.angelic_slab;
		} else if (this == BlocksAether.hellfire_double_slab) {
			return BlocksAether.hellfire_slab;
		} else if (this == BlocksAether.holystone_brick_double_slab) {
			return BlocksAether.holystone_brick_slab;
		} else if (this == BlocksAether.holystone_double_slab) {
			return BlocksAether.holystone_slab;
		} else if (this == BlocksAether.genesis_double_slab) {
			return BlocksAether.genesis_slab;
		} else if (this == BlocksAether.greatroot_double_slab) {
			return BlocksAether.greatroot_slab;
		} else if (this == BlocksAether.wisproot_double_slab) {
			return BlocksAether.wisproot_slab;
		} else if (this == BlocksAether.void_double_slab) {
			return BlocksAether.void_slab;
		} else if (this == BlocksAether.agiosite_double_slab) {
			return BlocksAether.agiosite_slab;
		} else if (this == BlocksAether.enchanted_agiosite_double_slab) {
			return BlocksAether.enchanted_agiosite_slab;
		} else if (this == BlocksAether.enchanted_aetheral_stone_double_slab) {
			return BlocksAether.enchanted_aetheral_stone_slab;
		} else if (this == BlocksAether.aetheral_stone_double_slab) {
			return BlocksAether.aetheral_stone_slab;
		} else if (this == BlocksAether.deific_double_slab) {
			return BlocksAether.deific_slab;
		} else if (this == BlocksAether.enchanted_deific_double_slab) {
			return BlocksAether.enchanted_deific_slab;
		} else if (this == BlocksAether.enchanted_holystone_double_slab) {
			return BlocksAether.enchanted_holystone_slab;
		} else if (this == BlocksAether.caelestia_stone_double_slab) {
			return BlocksAether.caelestia_stone_slab;
		} else if (this == BlocksAether.carved_caelestia_stone_double_slab) {
			return BlocksAether.carved_caelestia_stone_slab;
		} else if (this == BlocksAether.enchanted_agiosite_brick_double_slab) {
			return BlocksAether.enchanted_agiosite_brick_slab;
		} else if (this == BlocksAether.divine_carved_stone_double_slab) {
			return BlocksAether.divine_carved_stone_slab;
		} else if (this == BlocksAether.mythic_carved_stone_double_slab) {
			return BlocksAether.mythic_carved_stone_slab;
		} else if (this == BlocksAether.divine_hellfire_stone_double_slab) {
			return BlocksAether.divine_hellfire_stone_slab;
		} else if (this == BlocksAether.golden_oak_double_slab) {
			return BlocksAether.golden_oak_slab;
		} else if (this == BlocksAether.mossy_holystone_double_slab) {
			return BlocksAether.mossy_holystone_slab;
		} else if (this == BlocksAether.divine_angelic_stone_double_slab) {
			return BlocksAether.divine_angelic_stone_slab;
		} else if (this == BlocksAether.mythic_angelic_stone_double_slab) {
			return BlocksAether.mythic_angelic_stone_slab;
		} else if (this == BlocksAether.ancient_carved_stone_double_slab) {
			return BlocksAether.ancient_carved_stone_slab;
		} else if (this == BlocksAether.fuse_double_slab) {
			return BlocksAether.fuse_slab;
		} else if (this == BlocksAether.agiosite_brick_double_slab) {
			return BlocksAether.agiosite_brick_slab;
		} else if (this == BlocksAether.deific_brick_double_slab) {
			return BlocksAether.deific_brick_slab;
		} else if (this == BlocksAether.enchanted_holystone_brick_double_slab) {
			return BlocksAether.enchanted_holystone_brick_slab;
		} else if (this == BlocksAether.oblitus_stone_double_slab) {
			return BlocksAether.oblitus_stone_slab;
		} else if (this == BlocksAether.aetheral_stone_brick_double_slab) {
			return BlocksAether.aetheral_stone_brick_slab;
		} else if (this == BlocksAether.enchanted_deific_brick_double_slab) {
			return BlocksAether.enchanted_deific_brick_slab;
		} else if (this == BlocksAether.enchanted_aetheral_stone_brick_double_slab) {
			return BlocksAether.enchanted_aetheral_stone_brick_slab;
		} else if (this == BlocksAether.divine_oak_double_slab) {
			return BlocksAether.divine_oak_slab;
		} else if (this == BlocksAether.ancient_angelic_stone_double_slab) {
			return BlocksAether.ancient_angelic_stone_slab;
		} else if (this == BlocksAether.ancient_hellfire_stone_double_slab) {
			return BlocksAether.ancient_hellfire_stone_slab;
		} else {
			return this;
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(this.getDroppedSlab());
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

	@Override
	public String func_150002_b(int meta) {
		return this.name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return this == BlocksAether.aerogel_slab || this == BlocksAether.aerogel_double_slab ? 1 : 0;
	}
}
