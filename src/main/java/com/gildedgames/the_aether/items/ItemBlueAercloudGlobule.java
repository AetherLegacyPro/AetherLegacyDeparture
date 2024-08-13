package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class ItemBlueAercloudGlobule extends Item
{

    public ItemBlueAercloudGlobule()
    {
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int metadata, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if ((p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != Blocks.snow_layer) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != Blocks.snow_layer) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_2) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_3) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_4) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_5) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_6) || (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) != BlocksAether.aercloud_layer_7))
        {
            if (metadata == 0)
            {
                --p_77648_5_;
            }

            if (metadata == 1)
            {
                ++p_77648_5_;
            }

            if (metadata == 2)
            {
                --p_77648_6_;
            }

            if (metadata == 3)
            {
                ++p_77648_6_;
            }

            if (metadata == 4)
            {
                --p_77648_4_;
            }

            if (metadata == 5)
            {
                ++p_77648_4_;
            }

            if (!p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_))
            {
                return false;
            }
        }

        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, metadata, p_77648_1_))
        {
            return false;
        }
        else
        {
            if (BlocksAether.aercloud_layer.canPlaceBlockAt(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
            {
                --p_77648_1_.stackSize;
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, BlocksAether.aercloud_layer, 1, 0);
            }

            return true;
        }
    }
}

