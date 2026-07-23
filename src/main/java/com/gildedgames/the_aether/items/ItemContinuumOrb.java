package com.gildedgames.the_aether.items;

import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ItemContinuumOrb extends Item {
    private static List<ItemStack> continuumLoot;

    public ItemContinuumOrb() {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(AetherCreativeTabs.material);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ItemsAether.aether_loot;
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player) {

        if (!worldIn.isRemote) {
            player.triggerAchievement(AchievementsAether.continuum_orb);
            List<ItemStack> loot = getContinuumLoot();

            if (!loot.isEmpty()) {
                ItemStack reward = loot.get(worldIn.rand.nextInt(loot.size())).copy();
                reward.stackSize = 1;
                return reward;
            }
        }

        // Client side returns the original stack.
        // The server will sync the real replacement afterward.
        return stack;
    }

    private static List<ItemStack> getContinuumLoot() {
        if (continuumLoot == null) {
            continuumLoot = new ArrayList<ItemStack>();

            addItemsFromClass(continuumLoot, ItemsAether.class);
            addBlocksFromClass(continuumLoot, BlocksAether.class);
            addItemsFromClass(continuumLoot, Items.class);
            addBlocksFromClass(continuumLoot, Blocks.class);
        }

        return continuumLoot;
    }

    private static void addItemsFromClass(List<ItemStack> list, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!Item.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                field.setAccessible(true);
                Item item = (Item) field.get(null);

                if (item != null && !isBlacklistedItem(item)) {
                    list.add(new ItemStack(item));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addBlocksFromClass(List<ItemStack> list, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!Block.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                field.setAccessible(true);
                Block block = (Block) field.get(null);

                if (block != null && !isBlacklistedBlock(block)) {
                    Item blockItem = Item.getItemFromBlock(block);
                    if (blockItem != null) {
                        list.add(new ItemStack(block));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isBlacklistedItem(Item item) {
        if (item == null) {
            return true;
        }

        String name = item.getUnlocalizedName();
        if (name != null) {
            name = name.toLowerCase();

            if (name.contains("notched") || name.contains("amplified") || name.contains("scaled") || name.contains("tipped") || name.contains("reinforced") || name.contains("divineral") || name.contains("ascensite")) {
                return true;
            }
        }

        return item == ItemsAether.continuum_orb
            || item == Items.command_block_minecart
            || item == Items.spawn_egg
            || item == Items.potionitem
            || item == Items.filled_map
            || item == Items.written_book
            || item == Items.writable_book
            || item == Items.fireworks
            || item == Items.firework_charge;
    }

    private static boolean isBlacklistedBlock(Block block) {
        String name = block.getUnlocalizedName();
        if (name != null) {
            name = name.toLowerCase();

            if (name.contains("double") || name.contains("table") || name.contains("divineral")) {
                return true;
            }
        }

        //Unbreakable blocks
        return (block == Blocks.air
            || block == Blocks.bedrock
            || block == Blocks.command_block
            || block == Blocks.portal
            || block == Blocks.end_portal
            || block == Blocks.end_portal_frame
            || block == BlocksAether.treasure_chest
            || block == BlocksAether.elysian_totem
            || block == BlocksAether.aether_portal

            || block == BlocksAether.locked_angelic_stone
            || block == BlocksAether.locked_ancient_angelic_stone
            || block == BlocksAether.locked_divine_angelic_stone
            || block == BlocksAether.locked_mythic_angelic_stone
            || block == BlocksAether.locked_light_angelic_stone
            || block == BlocksAether.locked_ancient_light_angelic_stone
            || block == BlocksAether.locked_divine_light_angelic_stone
            || block == BlocksAether.locked_mythic_light_angelic_stone

            || block == BlocksAether.locked_carved_stone
            || block == BlocksAether.locked_ancient_carved_stone
            || block == BlocksAether.locked_divine_carved_stone
            || block == BlocksAether.locked_mythic_carved_stone
            || block == BlocksAether.locked_sentry_stone
            || block == BlocksAether.locked_ancient_sentry_stone
            || block == BlocksAether.locked_divine_sentry_stone
            || block == BlocksAether.locked_mythic_sentry_stone

            || block == BlocksAether.locked_hellfire_stone
            || block == BlocksAether.locked_ancient_hellfire_stone
            || block == BlocksAether.locked_divine_hellfire_stone
            || block == BlocksAether.locked_light_hellfire_stone
            || block == BlocksAether.locked_ancient_light_hellfire_stone
            || block == BlocksAether.locked_divine_light_hellfire_stone

            || block == BlocksAether.locked_creeping_stone
            || block == BlocksAether.locked_fuse_stone
            || block == BlocksAether.fuse_trap
            || block == BlocksAether.fuse_trap_2

            || block == BlocksAether.genesis_stone
            || block == BlocksAether.light_genesis_stone

            || block == BlocksAether.oblitus_stone
            || block == BlocksAether.cracked_oblitus_stone


            //Fluids or Flames
            || block == Blocks.fire
            || block == Blocks.water
            || block == Blocks.flowing_water
            || block == Blocks.lava
            || block == Blocks.flowing_lava
            || block == BlocksAether.coldfire
            || block == BlocksAether.hellfire

            //Blocks that should be unobtainable
            || block == Blocks.mob_spawner
            || block == Blocks.lit_furnace
            || block == Blocks.lit_redstone_ore
            || block == Blocks.powered_repeater
            || block == Blocks.unpowered_repeater
            || block == Blocks.powered_comparator
            || block == Blocks.unpowered_comparator
            || block == Blocks.redstone_wire
            || block == Blocks.tripwire
            || block == Blocks.tripwire_hook
            || block == Blocks.piston_extension
            || block == Blocks.piston_head
            || block == Blocks.wheat
            || block == Blocks.carrots
            || block == Blocks.potatoes
            || block == Blocks.nether_wart
            || block == Blocks.pumpkin_stem
            || block == Blocks.melon_stem
            || block == Blocks.bed
            || block == Blocks.standing_sign
            || block == Blocks.wall_sign
            || block == Blocks.wooden_door
            || block == Blocks.iron_door
            || block == Blocks.skull
            || block == Blocks.flower_pot
            || block == Blocks.brewing_stand
            || block == Blocks.cauldron
            || block == Blocks.cake
            || block == Blocks.farmland
            || block == Blocks.cocoa
            || block == BlocksAether.aether_farmland
            || block == BlocksAether.enchanted_aether_farmland
            || block == Blocks.dragon_egg);
    }
}
