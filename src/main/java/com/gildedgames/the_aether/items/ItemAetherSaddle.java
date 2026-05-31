package com.gildedgames.the_aether.items;

import net.minecraft.item.Item;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

public class ItemAetherSaddle extends Item {

    public ItemAetherSaddle() {
        maxStackSize = 1;
        this.setCreativeTab(AetherCreativeTabs.material);
    }

}
