package com.gildedgames.the_aether.items.staffs;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemZaniteAndCyro extends Item
{

    public ItemZaniteAndCyro()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(24);
        this.setCreativeTab(AetherCreativeTabs.tools);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_7_ == 0)
        {
            --p_77648_5_;
        }

        if (p_77648_7_ == 1)
        {
            ++p_77648_5_;
        }

        if (p_77648_7_ == 2)
        {
            --p_77648_6_;
        }

        if (p_77648_7_ == 3)
        {
            ++p_77648_6_;
        }

        if (p_77648_7_ == 4)
        {
            --p_77648_4_;
        }

        if (p_77648_7_ == 5)
        {
            ++p_77648_4_;
        }

        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
        if (AetherConfig.shouldColdfireExistNotInAether() == false) {
            if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_3_.provider.dimensionId == AetherConfig.getAetherDimensionID())
            {
                p_77648_3_.playSoundEffect((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + 0.5D, (double)p_77648_6_ + 0.5D, "dig.glass", 2.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, BlocksAether.coldfire);
            }
            else if (p_77648_3_.provider.dimensionId != AetherConfig.getAetherDimensionID() && (!p_77648_3_.isRemote)) {
            	p_77648_2_.addChatComponentMessage(new ChatComponentText(I18n.format("gui.zanite.cyro_invaild")));
            }
        }
        else if (AetherConfig.shouldColdfireExistNotInAether() == true) {
        	if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_))
            {
                p_77648_3_.playSoundEffect((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + 0.5D, (double)p_77648_6_ + 0.5D, "dig.glass", 2.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, BlocksAether.coldfire);
            	}        	
        	}
        	
            p_77648_1_.damageItem(1, p_77648_2_);
            return true;
        }
    }
}
