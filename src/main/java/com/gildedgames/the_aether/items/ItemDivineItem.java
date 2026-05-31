package com.gildedgames.the_aether.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

public class ItemDivineItem extends Item {

    public ItemDivineItem() {
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ItemsAether.divine_aether_loot;
    }

    public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }

    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return new EntityFireProofItemAether(world, location, itemstack);
    }

}
