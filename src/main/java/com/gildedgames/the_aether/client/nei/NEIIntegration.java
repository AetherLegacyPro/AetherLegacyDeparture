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
            // Enchanter
            API.registerRecipeHandler(new EnchanterRecipeHandler());
            API.registerUsageHandler(new EnchanterRecipeHandler());
            API.registerRecipeHandler(new EnchanterFuelHandler());
            API.registerUsageHandler(new EnchanterFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.enchanter), new EnchanterRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.enchanter), new EnchanterFuelHandler());

            // Freezer
            API.registerRecipeHandler(new FreezerRecipeHandler());
            API.registerUsageHandler(new FreezerRecipeHandler());
            API.registerRecipeHandler(new FreezerFuelHandler());
            API.registerUsageHandler(new FreezerFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.freezer), new FreezerRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.freezer), new FreezerFuelHandler());

            // Amplifier
            API.registerRecipeHandler(new AmplifierRecipeHandler());
            API.registerUsageHandler(new AmplifierRecipeHandler());
            API.registerRecipeHandler(new AmplifierFuelHandler());
            API.registerUsageHandler(new AmplifierFuelHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.amplifier), new AmplifierRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.amplifier), new AmplifierFuelHandler());
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
