package com.gildedgames.the_aether.items.block;

import com.gildedgames.the_aether.entities.block.EntityFireProofItemAether;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockFireProof extends ItemBlock {

	public ItemBlockFireProof(final Block block) {
        super(block);
        this.setMaxDamage(0);
    }
	
	public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityFireProofItemAether(world, location, itemstack);
    }
    
    public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.divine_aether_loot;
	}

}
