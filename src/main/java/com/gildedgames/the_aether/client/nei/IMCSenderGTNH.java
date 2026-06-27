package com.gildedgames.the_aether.client.nei;

import net.minecraft.nbt.NBTTagCompound;

import com.gildedgames.the_aether.Aether;

import cpw.mods.fml.common.event.FMLInterModComms;

public class IMCSenderGTNH {

    public static void IMCSender() {
        sendHandler("com.gildedgames.the_aether.client.nei.AmplifierRecipeHandler", "aether_legacy:amplifier");
        sendCatalyst(Aether.MOD_ID + ".amplifier", "aether_legacy:amplifier");

        sendHandler("com.gildedgames.the_aether.client.nei.EnchanterRecipeHandler", "aether_legacy:enchanter");
        sendCatalyst(Aether.MOD_ID + ".enchanter", "aether_legacy:enchanter");

        sendHandler("com.gildedgames.the_aether.client.nei.FreezerRecipeHandler", "aether_legacy:freezer");
        sendCatalyst(Aether.MOD_ID + ".freezer", "aether_legacy:freezer");
    }

    /*
     * These were copied from GTNewHorizons/GoodGenerator (Fork of GlodBlock/GoodGenerator)
     * Author: GlodBlock
     */

    private static void sendHandler(String aName, String aBlock) {
        sendHandler(aName, aBlock, 1);
    }

    private static void sendHandler(String aName, String aBlock, int maxRecipesPerPage) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handler", aName);
        aNBT.setString("modName", Aether.MOD_ID);
        aNBT.setString("modId", Aether.MOD_ID);
        aNBT.setBoolean("modRequired", true);
        aNBT.setString("itemName", aBlock);
        aNBT.setInteger("handlerHeight", 65);
        aNBT.setInteger("handlerWidth", 166);
        aNBT.setInteger("maxRecipesPerPage", maxRecipesPerPage);
        aNBT.setInteger("yShift", 6);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", aNBT);
    }

    private static void sendCatalyst(String aName, String aStack, int aPriority) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        aNBT.setInteger("priority", aPriority);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }

    private static void sendCatalyst(String aName, String aStack) {
        sendCatalyst(aName, aStack, 0);
    }
}
