package com.gildedgames.the_aether.items.tools;

import com.gildedgames.the_aether.entities.passive.mountable.EntityParachute;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAetherParachute extends Item {

	public ItemAetherParachute() {
		this.setMaxDamage(20);
		this.setMaxStackSize(1);
		this.setCreativeTab(AetherCreativeTabs.misc);
	}

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer entityplayer) {
        ItemStack heldItem = entityplayer.getHeldItem();

        if (EntityParachute.entityHasRoomForCloud(world, entityplayer)) {
            boolean isGolden = this == ItemsAether.golden_parachute;
            boolean isBlue = this == ItemsAether.blue_parachute;

            if (isGolden) {
                heldItem.damageItem(1, entityplayer);
            } else if (isBlue) {
                heldItem.damageItem(2, entityplayer);
            } else {
                --heldItem.stackSize;
            }

            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityParachute(world, entityplayer, isGolden, isBlue));
            }

            return heldItem;
        }

        return super.onItemRightClick(stack, world, entityplayer);
    }

	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (this == ItemsAether.golden_parachute) {
            return 0xffff7f;
        }
        else if (this == ItemsAether.blue_parachute) {
            return 0xCCCCFF;
        }
        else {
            return 0xffffff;
        }
	}

}
