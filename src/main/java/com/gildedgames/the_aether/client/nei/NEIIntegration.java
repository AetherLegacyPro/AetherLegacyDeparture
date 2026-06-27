package com.gildedgames.the_aether.client.nei;

import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.blocks.BlocksAether;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLCommonHandler;

public class NEIIntegration implements IConfigureNEI {

    @Override
    public void loadConfig() {
        if (FMLCommonHandler.instance()
            .getSide()
            .isClient()) {
            API.registerRecipeHandler(new EnchanterRecipeHandler());
            API.registerUsageHandler(new EnchanterRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.enchanter), new EnchanterRecipeHandler());
            API.registerRecipeHandler(new FreezerRecipeHandler());
            API.registerUsageHandler(new FreezerRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.freezer), new FreezerRecipeHandler());
            API.registerRecipeHandler(new AmplifierRecipeHandler());
            API.registerUsageHandler(new AmplifierRecipeHandler());
            API.addRecipeCatalyst(new ItemStack(BlocksAether.amplifier), new AmplifierRecipeHandler());
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
