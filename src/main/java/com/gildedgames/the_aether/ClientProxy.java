package com.gildedgames.the_aether;

import com.gildedgames.the_aether.AetherConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gildedgames.the_aether.CommonProxy;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.ancient.enchanter.RenderHandlerTileEntity;
import com.gildedgames.the_aether.blocks.elysian_totem.TileEntityElysianTotem;
import com.gildedgames.the_aether.blocks.elysian_totem.TileEntityElysianTotemRenderer;
import com.gildedgames.the_aether.client.AetherClientEvents;
import com.gildedgames.the_aether.client.AetherKeybinds;
import com.gildedgames.the_aether.client.audio.AetherMusicHandler;
import com.gildedgames.the_aether.client.gui.GuiAetherInGame;
import com.gildedgames.the_aether.client.gui.GuiSunAltar;
import com.gildedgames.the_aether.client.renders.AetherEntityRenderer;
import com.gildedgames.the_aether.client.renders.RendersAether;
import com.gildedgames.the_aether.client.renders.TileEntityAncientEnchanterRenderer;
import com.gildedgames.the_aether.client.renders.block.BlockAceninumClusterRenderer;
import com.gildedgames.the_aether.client.renders.block.BlockAuraliteClusterRenderer;
import com.gildedgames.the_aether.client.renders.block.ElysianChestRenderer;
import com.gildedgames.the_aether.client.renders.block.SkyrootChestRenderer;
import com.gildedgames.the_aether.client.renders.block.TileEntityElysianChestRenderer;
import com.gildedgames.the_aether.client.renders.block.TileEntitySkyrootChestRenderer;
import com.gildedgames.the_aether.client.renders.block.TileEntityTreasureChestBreakableRenderer;
import com.gildedgames.the_aether.client.renders.block.TreasureChestBreakableRenderer;
import com.gildedgames.the_aether.compatibility.client.AetherClientCompatibility;
import com.gildedgames.the_aether.entities.particles.EntityCloudSmokeFX;
import com.gildedgames.the_aether.entities.particles.EntityGoldenFX;
import com.gildedgames.the_aether.tileentity.TileEntityAncientEnchanter;
import com.gildedgames.the_aether.tileentity.TileEntityElysianChest;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;
import com.gildedgames.the_aether.tileentity.TileEntityTreasureChestBreakable;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

	public class ClientProxy extends CommonProxy {

		public static final IIcon[] ACCESSORY_ICONS = new IIcon[8];

		@Override
		public void init() {
			try
			{
				File resourcePacks = Minecraft.getMinecraft().getResourcePackRepository().getDirResourcepacks().getCanonicalFile();

				File buckets = new File(resourcePacks + "/Aether b1.7.3 Textures/assets/aether_legacy/textures/items/misc/buckets");
				File weapons = new File(resourcePacks + "/Aether b1.7.3 Textures/assets/aether_legacy/textures/items/weapons");
				File armor = new File(resourcePacks + "/Aether b1.7.3 Textures/assets/aether_legacy/textures/items/armor");
				File accessories = new File(resourcePacks + "/Aether b1.7.3 Textures/assets/aether_legacy/textures/items/accessories");

				File[] directories = new File[] {buckets, weapons, armor, accessories};

				if (AetherConfig.installResourcepack())
				{
					for (File file : directories)
					{
						if (!file.exists())
						{
							file.mkdirs();
						}
					}

					generateFile("data/Aether_b1.7.3/pack.mcmeta", "pack.mcmeta", resourcePacks.getAbsolutePath() + "/Aether b1.7.3 Textures");
					generateFile("data/Aether_b1.7.3/pack.png", "pack.png", resourcePacks.getAbsolutePath() + "/Aether b1.7.3 Textures");
					generateFile("data/Aether_b1.7.3/skyroot_remedy_bucket.png", "skyroot_remedy_bucket.png", buckets.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/bow_pulling_0.png", "bow_pulling_0.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/bow_pulling_1.png", "bow_pulling_1.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/bow_pulling_2.png", "bow_pulling_2.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/flaming_sword.png", "flaming_sword.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/holy_sword.png", "holy_sword.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/lightning_sword.png", "lightning_sword.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/weapons/phoenix_bow.png", "phoenix_bow.png", weapons.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/armor/phoenix_boots.png", "phoenix_boots.png", armor.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/armor/phoenix_leggings.png", "phoenix_leggings.png", armor.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/armor/phoenix_chestplate.png", "phoenix_chestplate.png", armor.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/armor/phoenix_helmet.png", "phoenix_helmet.png", armor.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/accessories/phoenix_gloves.png", "phoenix_gloves.png", accessories.getAbsolutePath());
					generateFile("data/Aether_b1.7.3/accessories/agility_cape.png", "agility_cape.png", accessories.getAbsolutePath());
				}
			}
			
			catch (IOException ignore) { }

			berryBushRenderID = RenderingRegistry.getNextAvailableRenderId();
			treasureChestRenderID = RenderingRegistry.getNextAvailableRenderId();
			aetherFlowerRenderID = RenderingRegistry.getNextAvailableRenderId();
			ancientEnchanterID = RenderingRegistry.getNextAvailableRenderId();
			
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootChest.class, new TileEntitySkyrootChestRenderer());
			BlocksAether.SkyrootChestRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new SkyrootChestRenderer(new TileEntitySkyrootChest()));
			
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElysianChest.class, new TileEntityElysianChestRenderer());
			BlocksAether.ElysianChestRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new ElysianChestRenderer(new TileEntityElysianChest()));

			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestBreakable.class, new TileEntityTreasureChestBreakableRenderer());
			BlocksAether.TreasureChestBreakbleRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new TreasureChestBreakableRenderer(new TileEntityTreasureChestBreakable()));
			
			BlocksAether.AncientEnchanterRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new RenderHandlerTileEntity(new TileEntityAncientEnchanter(), BlocksAether.AncientEnchanterRenderId));
			
			BlocksAether.ElysianTotemRenderId = RenderingRegistry.getNextAvailableRenderId();
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElysianTotem.class, new TileEntityElysianTotemRenderer());
			RenderingRegistry.registerBlockHandler(new RenderHandlerTileEntity(new TileEntityElysianTotem(), BlocksAether.ElysianTotemRenderId, 0.6f).setYOffset(-0.5f));
			
			BlocksAether.AuraliteClusterRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new BlockAuraliteClusterRenderer());
			RenderingRegistry.registerBlockHandler(new BlockAceninumClusterRenderer());
			
			EntityRenderer previousRenderer = Minecraft.getMinecraft().entityRenderer;
			
			Minecraft.getMinecraft().entityRenderer = new AetherEntityRenderer(Minecraft.getMinecraft(), previousRenderer, Minecraft.getMinecraft().getResourceManager());
			
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAncientEnchanter.class, new TileEntityAncientEnchanterRenderer());
			
			RendersAether.initialization();

			AetherKeybinds.initialization();

			registerEvent(new AetherMusicHandler());
			registerEvent(new AetherClientEvents());
			registerEvent(new GuiAetherInGame(Minecraft.getMinecraft()));

			if (Loader.isModLoaded("battlegear2") && AetherConfig.enable_battlegear2_compatibility == true) {
			AetherClientCompatibility.initialization();
			}
		}

		public void generateFile(String input, String name, String path)
		{
			try {
				File file = new File(path + "/" + name);

				if (!file.exists())
				{
					InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(input);
					FileOutputStream outputStream = new FileOutputStream(file);

					if (inputStream != null)
					{
						int i;
						while ((i = inputStream.read()) != -1)
						{
							outputStream.write(i);
						}

						inputStream.close();
						outputStream.close();
					}
				}
			}
			catch (IOException ignore) { }
		}

		@Override
		public void sendMessage(EntityPlayer player, String text) {
			if (this.getPlayer() == player)
			{
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(text));
			}
		}
		
		@Override
		public void spawnAltarParticles(final World world, final int x, final int y, final int z, final Random rand) {
		    for (int particleAmount = 50, count = 0; count < particleAmount; ++count) {
		       final EntityFX particles = new EntityGoldenFX(world, x + rand.nextFloat(), y + ((count > particleAmount / 2) ? 0.3f : 0.5f), z + rand.nextFloat(), 0.0, 1.0, 0.0);
		       FMLClientHandler.instance().getClient().effectRenderer.addEffect(particles);
		   }
		}
		 
		 @Override
		 public void spawnCloudSmoke(final World world, final double x, final double y, final double z, final Random rand, final double radius, final double forceX, final double forceY, final double forceZ, final double riseRate) {
		     final double xOffset = x + rand.nextDouble() * radius * 2.0 - radius;
		     final double yOffset = y + rand.nextDouble() * radius * 2.0 - radius;
		     final double zOffset = z + rand.nextDouble() * radius * 2.0 - radius;
		     final EntityFX entityFX = new EntityCloudSmokeFX(world, xOffset, yOffset, zOffset, forceX, forceY, forceZ, 2.5f, 1.0f, 1.0f, 1.0f, riseRate);
		     FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
		}

		@Override
		public void openSunAltar() {
			FMLClientHandler.instance().getClient().displayGuiScreen(new GuiSunAltar());
		}

		@Override
		public EntityPlayer getPlayer() {
			return Minecraft.getMinecraft().thePlayer;
		}

}
