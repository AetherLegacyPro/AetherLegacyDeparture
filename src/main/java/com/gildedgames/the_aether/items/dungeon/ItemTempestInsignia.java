package com.gildedgames.the_aether.items.dungeon;

import com.gildedgames.the_aether.entities.projectile.EntityTempestInsignia;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.world.dungeon.osmium.OsmiumDungeonFinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import java.util.List;

public class ItemTempestInsignia extends Item {

    public ItemTempestInsignia() {
        this.setMaxStackSize(16);
        this.setCreativeTab(AetherCreativeTabs.misc);
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ItemsAether.scaled_aether_loot;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            return stack;
        }

        int playerX = MathHelper.floor_double(player.posX);
        int playerZ = MathHelper.floor_double(player.posZ);
        ChunkCoordinates coords = OsmiumDungeonFinder.getNearestOsmiumDungeon(world, playerX, playerZ);
        if (coords == null) {
            player.addChatComponentMessage(new ChatComponentText("No Osmium Dungeon was found within 8192 blocks."));
            return stack;
        }

        EntityTempestInsignia eye = new EntityTempestInsignia(world, player.posX, player.posY + 1.62D, player.posZ);
        eye.moveTowards(coords.posX, MathHelper.floor_double(player.posY), coords.posZ);

        world.spawnEntityInWorld(eye);
        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (this.itemRand.nextFloat() * 0.4F + 0.8F));

        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

        return stack;
    }

    public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
        tooltip.add(EnumChatFormatting.BLUE + "" + StatCollector.translateToLocal("tooltip.osmium_eye.desc"));
    }
}
