package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;
import com.gildedgames.the_aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockAuralite extends Block {

	public BlockAuralite() {
        this(Material.glass);
	}

	public BlockAuralite(Material material) {
		super(material);
		setHardness(1.5F);
		setResistance(1.5F);
		setHarvestLevel("pickaxe", 2);
		this.setStepSound(soundTypeGlass);
	}

	public int quantityDropped(Random random) {
        return 2 + random.nextInt(2);
	}

	public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
	  return ItemsAether.auralite_crystal;
	}

}
