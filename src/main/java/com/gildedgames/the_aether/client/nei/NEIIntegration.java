package com.gildedgames.the_aether.client.nei;

import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.blocks.BlocksAether;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLCommonHandler;

public class NEIIntegration implements IConfigureNEI {

    @Override
    public void loadConfig() {
        if (FMLCommonHandler.instance().getSide().isClient()) {

            //Enchanter
            API.registerRecipeHandler(new EnchanterRecipeHandler());
            API.registerUsageHandler(new EnchanterRecipeHandler());
            API.registerRecipeHandler(new EnchanterFuelHandler());
            API.registerUsageHandler(new EnchanterFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.enchanter), new EnchanterRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.enchanter), new EnchanterFuelHandler());

            //Freezer
            API.registerRecipeHandler(new FreezerRecipeHandler());
            API.registerUsageHandler(new FreezerRecipeHandler());
            API.registerRecipeHandler(new FreezerFuelHandler());
            API.registerUsageHandler(new FreezerFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.freezer), new FreezerRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.freezer), new FreezerFuelHandler());

            //Amplifier
            API.registerRecipeHandler(new AmplifierRecipeHandler());
            API.registerUsageHandler(new AmplifierRecipeHandler());
            API.registerRecipeHandler(new AmplifierFuelHandler());
            API.registerUsageHandler(new AmplifierFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.amplifier), new AmplifierRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.amplifier), new AmplifierFuelHandler());

            // Hide items
            API.hideItem(new ItemStack(BlocksAether.skyroot_door));
            API.hideItem(new ItemStack(BlocksAether.skyroot_bed));

            API.hideItem(new ItemStack(BlocksAether.aerogel_double_slab));

            API.hideItem(new ItemStack(BlocksAether.skyroot_double_slab));
            API.hideItem(new ItemStack(BlocksAether.wisproot_double_slab));
            API.hideItem(new ItemStack(BlocksAether.greatroot_double_slab));
            API.hideItem(new ItemStack(BlocksAether.void_double_slab));
            API.hideItem(new ItemStack(BlocksAether.golden_oak_double_slab));
            API.hideItem(new ItemStack(BlocksAether.divine_oak_double_slab));

            API.hideItem(new ItemStack(BlocksAether.aetheral_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.aetheral_stone_brick_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_aetheral_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_aetheral_stone_brick_double_slab));

            API.hideItem(new ItemStack(BlocksAether.deific_brick_double_slab));
            API.hideItem(new ItemStack(BlocksAether.deific_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_deific_brick_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_deific_double_slab));

            API.hideItem(new ItemStack(BlocksAether.divine_angelic_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.divine_carved_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.divine_hellfire_stone_double_slab));

            API.hideItem(new ItemStack(BlocksAether.ancient_angelic_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.ancient_carved_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.ancient_hellfire_stone_double_slab));

            API.hideItem(new ItemStack(BlocksAether.angelic_double_slab));
            API.hideItem(new ItemStack(BlocksAether.carved_double_slab));
            API.hideItem(new ItemStack(BlocksAether.hellfire_double_slab));

            API.hideItem(new ItemStack(BlocksAether.agiosite_double_slab));
            API.hideItem(new ItemStack(BlocksAether.agiosite_brick_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_agiosite_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_agiosite_brick_double_slab));

            API.hideItem(new ItemStack(BlocksAether.caelestia_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.carved_caelestia_stone_double_slab));

            API.hideItem(new ItemStack(BlocksAether.holystone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.mossy_holystone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.holystone_brick_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_holystone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.enchanted_holystone_brick_double_slab));

            API.hideItem(new ItemStack(BlocksAether.oblitus_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.fuse_double_slab));
            API.hideItem(new ItemStack(BlocksAether.genesis_double_slab));

            API.hideItem(new ItemStack(BlocksAether.mythic_angelic_stone_double_slab));
            API.hideItem(new ItemStack(BlocksAether.mythic_carved_stone_double_slab));
        }
    }

    @Override
    public String getName() {
        return "Aether NEI Integration";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
