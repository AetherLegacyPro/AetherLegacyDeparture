package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.blocks.BlockSkyrootBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSkyrootBed extends Item {

    public ItemSkyrootBed() {
        this.maxStackSize = 1;
        this.setCreativeTab(AetherCreativeTabs.blocks);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer entityPlayer, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.isRemote) {
            return true;
        }
        else if (p_77648_7_ != 1) {
            return false;
        }
        else {
            ++p_77648_5_;
            BlockSkyrootBed blockbed = (BlockSkyrootBed) BlocksAether.skyroot_bed;
            int i1 = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0) {
                b1 = 1;
            }

            if (i1 == 1) {
                b0 = -1;
            }

            if (i1 == 2) {
                b1 = -1;
            }

            if (i1 == 3) {
                b0 = 1;
            }

            if (entityPlayer.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, stack) && entityPlayer.canPlayerEdit(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, p_77648_7_, stack)) {
                if (world.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_) && world.isAirBlock(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1) && World.doesBlockHaveSolidTopSurface(world, p_77648_4_, p_77648_5_ - 1, p_77648_6_) && World.doesBlockHaveSolidTopSurface(world, p_77648_4_ + b0, p_77648_5_ - 1, p_77648_6_ + b1)) {
                    world.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, blockbed, i1, 3);

                    if (world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == blockbed) {
                        world.setBlock(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, blockbed, i1 + 8, 3);
                    }

                    --stack.stackSize;
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
    }
}
