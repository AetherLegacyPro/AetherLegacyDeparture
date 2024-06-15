package com.gildedgames.the_aether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod;
import com.gildedgames.the_aether.vrl.ForgetTheOtherMethodsThisIsWhereTheMagicHappens;
import com.gildedgames.the_aether.AetherConfig;

import java.io.File;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {

	public static int berryBushRenderID;

	public static int treasureChestRenderID;

	public static int aetherFlowerRenderID;
	
	public static int ancientEnchanterID;
	

	public void init() {

	}

	public void openSunAltar() {

	}
	
	public void spawnAltarParticles(final World world, final int x, final int y, final int z, final Random rand) {
    }

	public void sendMessage(EntityPlayer player, String text) {

	}
	
	public void spawnCloudSmoke(final World world, final double x, final double y, final double z, final Random rand, final Double radius) {
        this.spawnCloudSmoke(world, x, y, z, rand, radius, 0.0, 0.0, 0.0);
    }
	
	 public void spawnCloudSmoke(final World world, final double x, final double y, final double z, final Random rand, final double radius, final double forceX, final double forceY, final double forceZ) {
	    this.spawnCloudSmoke(world, x, y, z, rand, radius, forceX, forceY, forceZ, 0.0);
	}
	    
	public void spawnCloudSmoke(final World world, final double x, final double y, final double z, final Random rand, final double radius, final double forceX, final double forceY, final double forceZ, final double riseRate) {
	}

	public EntityPlayer getPlayer() {
		return null;
	}

	public static void registerEvent(Object event) {
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}

	// preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
		System.out.println("BBBBBBBBBBBBBBBBBBB");
		File moo = ForgetTheOtherMethodsThisIsWhereTheMagicHappens.getJarName();
		AetherConfig.init(event.getModConfigurationDirectory());
        if(AetherConfig.shouldRefetch()) {
			System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH refetching");

        	ForgetTheOtherMethodsThisIsWhereTheMagicHappens assetManager = new ForgetTheOtherMethodsThisIsWhereTheMagicHappens();
        	ForgetTheOtherMethodsThisIsWhereTheMagicHappens.download("https://mediafilez.forgecdn.net/files/2273/367/aether-1.7.10-1.6.jar");
        	ForgetTheOtherMethodsThisIsWhereTheMagicHappens.renameThingy();
        	ForgetTheOtherMethodsThisIsWhereTheMagicHappens.download("https://cdn.modrinth.com/data/JD2NSu5O/versions/cZNxPTqa/aether_ii-1.12.2-0.3.0%2Bbuild411-universal.jar");
        	ForgetTheOtherMethodsThisIsWhereTheMagicHappens.download("https://cdn.modrinth.com/data/YhmgMVyu/versions/4NuI8eHN/aether-1.7.10-v1.1.2.3.jar");
			// We need a good static link for aether II 1.7.10. We can probably get everything we need from github auto-zip links, the source should still have the assets.
			// This is good because it'll be easy to get every asset for every version (including unreleased versions), but the downside is I'd need to make sure
			// assets are unzipped into instanced locations. 
			// Do we want the b1.7.3 version too?
        	//ForgetTheOtherMethodsThisIsWhereTheMagicHappens.download("https://static.planetminecraft.com/files/resource_media/mod/1509/aetheriilauncher-unzipandrun8190225.zip");
			System.out.println("Fetching aether assets complete");

			File modName = ForgetTheOtherMethodsThisIsWhereTheMagicHappens.getJarName();
			try {
	            ForgetTheOtherMethodsThisIsWhereTheMagicHappens.updateJar(modName);
	        } catch (Exception e) {
	            e.printStackTrace(); // Example: Printing the stack trace
	        }

			AetherConfig.fetched();//make sure this is last
		}
    }
}
