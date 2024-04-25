package com.gildedgames.the_aether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

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

}