package com.gildedgames.the_aether.client.nei;

import com.gildedgames.the_aether.Aether;

import cpw.mods.fml.common.Loader;

public class NEIIntegration {

    public static void init() {
        // Only register if NEI is present
        if (!Loader.isModLoaded("NotEnoughItems")) return;

        try {
            // Register all recipe handlers
            codechicken.nei.api.API.registerRecipeHandler(new EnchanterRecipeHandler());
            codechicken.nei.api.API.registerUsageHandler(new EnchanterRecipeHandler());

            codechicken.nei.api.API.registerRecipeHandler(new FreezerRecipeHandler());
            codechicken.nei.api.API.registerUsageHandler(new FreezerRecipeHandler());

            codechicken.nei.api.API.registerRecipeHandler(new AmplifierRecipeHandler());
            codechicken.nei.api.API.registerUsageHandler(new AmplifierRecipeHandler());

            Aether.LOG.info("NEI recipe handlers registered successfully.");
        } catch (Throwable t) {
            Aether.LOG.warn("Failed to register NEI recipe handlers: " + t.getMessage());
            t.printStackTrace();
        }
    }
}
