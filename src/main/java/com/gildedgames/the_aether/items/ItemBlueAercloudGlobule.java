package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.gildedgames.the_aether.blocks.BlocksAether;

public class ItemBlueAercloudGlobule extends Item {

    public ItemBlueAercloudGlobule() {
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer entityPlayer, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int metadata, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if ((world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != Blocks.snow_layer) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != Blocks.snow_layer) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_2) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_3) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_4) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_5) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_6) || (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_7)) {
            if (metadata == 0) {
                --p_77648_5_;
            }

            if (metadata == 1) {
                ++p_77648_5_;
            }

            if (metadata == 2) {
                --p_77648_6_;
            }

            if (metadata == 3) {
                ++p_77648_6_;
            }

            if (metadata == 4) {
                --p_77648_4_;
            }

            if (metadata == 5) {
                ++p_77648_4_;
            }

            if (!world.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_)) {
                return false;
            }
        }

        if (!entityPlayer.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, metadata, stack)) {
            return false;
        }
        else {
            if (BlocksAether.aercloud_layer.canPlaceBlockAt(world, p_77648_4_, p_77648_5_, p_77648_6_)) {
                --stack.stackSize;
                world.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, BlocksAether.aercloud_layer, 1, 0);
            }
            return true;
        }
    }
}

