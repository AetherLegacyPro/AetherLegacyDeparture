package com.gildedgames.the_aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockStratoAercloud extends Block {

    public BlockStratoAercloud() {
        super(Material.cloth);
        this.setHardness(0.7F);
        this.setStepSound(soundTypeCloth);
        this.setHarvestLevel("pickaxe", 0);
    }
}
