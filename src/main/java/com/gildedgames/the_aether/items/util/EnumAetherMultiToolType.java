package com.gildedgames.the_aether.items.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;
import com.gildedgames.the_aether.blocks.BlocksAether;

public enum EnumAetherMultiToolType {

    MULTI(createAllBlocksSet()) {
        @Override
        public boolean canHarvestBlock(ToolMaterial toolMaterial, Block block) {
            if (block == null) {
                return false;
            }

            if (block == BlocksAether.zanite_ore || block == BlocksAether.zanite_block || block == BlocksAether.icestone) {
                return toolMaterial.getHarvestLevel() >= 1;
            }

            if (block == BlocksAether.gravitite_ore || block == BlocksAether.enchanted_gravitite) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (block == BlocksAether.arkenium_ore || block == BlocksAether.arkenium_block) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (block == BlocksAether.continuum_ore || block == BlocksAether.continuum_block) {
                return toolMaterial.getHarvestLevel() >= 3;
            }

            if (block == BlocksAether.primeval_artifact) {
                return toolMaterial.getHarvestLevel() >= 3;
            }

            if (block == BlocksAether.aerogel) {
                return toolMaterial.getHarvestLevel() == 3;
            }

            if (block == Blocks.obsidian) {
                return toolMaterial.getHarvestLevel() == 3;
            }

            if (block == Blocks.diamond_block || block == Blocks.diamond_ore) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (block == Blocks.emerald_ore || block == Blocks.emerald_block) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (block == Blocks.gold_block || block == Blocks.gold_ore) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (block == Blocks.iron_block || block == Blocks.iron_ore) {
                return toolMaterial.getHarvestLevel() >= 1;
            }

            if (block == Blocks.lapis_block || block == Blocks.lapis_ore) {
                return toolMaterial.getHarvestLevel() >= 1;
            }

            if (block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore) {
                return toolMaterial.getHarvestLevel() >= 2;
            }

            if (this.getToolBlockSet().contains(block)) {
                return true;
            }

            Material material = block.getMaterial();

            return material == Material.rock || material == Material.iron || material == Material.anvil || material == Material.ground
                || material == Material.grass || material == Material.sand || material == Material.snow || material == Material.craftedSnow
                || material == Material.clay || material == Material.wood || material == Material.plants || material == Material.vine || material == Material.leaves
                || material == Material.gourd || material == Material.cloth || material == Material.ice || material == Material.packedIce;
        }

        @Override
        public float getStrVsBlock(ItemStack stack, Block block) {
            if (block != null && this.getToolBlockSet().contains(block)) {
                return this.efficiencyOnProperMaterial;
            }

            return super.getStrVsBlock(stack, block);
        }

        public boolean canHarvestBlockk(ToolMaterial toolMaterial, Block block) {
            return block == Blocks.snow || block == Blocks.snow_layer;
        }
    };

    private Set<Block> toolBlockSet;

    public float efficiencyOnProperMaterial = 4.0F;

    EnumAetherMultiToolType(Set<Block> toolBlockSet) {
        this.toolBlockSet = toolBlockSet;
    }

    public Set<Block> getToolBlockSet() {
        return this.toolBlockSet;
    }

    public boolean canHarvestBlock(ToolMaterial toolMaterial, Block block) {
        return false;
    }

    public float getStrVsBlock(ItemStack stack, Block block) {
        return block != null && this.toolBlockSet.contains(block) ? this.efficiencyOnProperMaterial : 4.0F;
    }

    private static Set<Block> createAllBlocksSet() {
        Set<Block> blocks = Sets.newHashSet();
        addBlocksFromClass(blocks, Blocks.class);
        addBlocksFromClass(blocks, BlocksAether.class);

        return blocks;
    }

    private static void addBlocksFromClass(Set<Block> blocks, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                if (!Block.class.isAssignableFrom(field.getType())) {
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(null);

                if (value instanceof Block) {
                    Block block = (Block)value;

                    if (block != null && block != Blocks.air) {
                        blocks.add(block);
                    }
                }
            } catch (Throwable ignored) {
            }
        }
    }
}
