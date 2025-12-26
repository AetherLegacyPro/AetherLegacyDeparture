package com.gildedgames.the_aether.client;

import java.util.List;

import com.gildedgames.the_aether.client.gui.GuiCustomizationScreen;
import com.gildedgames.the_aether.client.gui.GuiEnterAether;
import com.gildedgames.the_aether.client.gui.button.*;
import com.gildedgames.the_aether.client.gui.inventory.GuiAccessories;
import com.gildedgames.the_aether.client.gui.menu.AetherMainMenu;
import com.gildedgames.the_aether.client.gui.menu.GuiMenuToggleButton;
import com.gildedgames.the_aether.network.packets.PacketCapeChanged;
import com.gildedgames.the_aether.network.packets.PacketExtendedAttack;
import com.gildedgames.the_aether.player.perks.AetherRankings;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.client.gui.AetherLoadingScreen;
import com.gildedgames.the_aether.client.renders.entity.PlayerAetherRenderer;
import com.gildedgames.the_aether.entities.EntitiesAether;
import com.gildedgames.the_aether.items.ItemAetherSpawnEgg;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.armor.ItemAetherArmor;
import com.gildedgames.the_aether.network.AetherGuiHandler;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketOpenContainer;
import com.gildedgames.the_aether.player.PlayerAether;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

public class AetherClientEvents {

	private static boolean wasInAether = false;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END) {
			if (type.equals(TickEvent.Type.CLIENT)) {
				if (!AetherConfig.triviaDisabled()) {
					if (!(mc.loadingScreen instanceof AetherLoadingScreen)) {
						mc.loadingScreen = new AetherLoadingScreen(mc);
					}
				}

				if (mc.thePlayer != null && !(mc.thePlayer.movementInput instanceof AetherMovementInput)) {
					mc.thePlayer.movementInput = new AetherMovementInput(mc, mc.gameSettings);
				}

				handleExtendedReach(mc);
				
			}
			
		}
	

		if (phase == TickEvent.Phase.START) {
			if (type.equals(TickEvent.Type.CLIENT)) {
				if (mc.currentScreen == null || mc.currentScreen.allowUserInput) {
					if (!mc.thePlayer.isUsingItem()) {
						if (GameSettings.isKeyDown(mc.gameSettings.keyBindPickBlock)) {
							this.sendPickupPacket(mc);
						}
					}
				}
			}
		}
	}
	
	private void handleExtendedReach(Minecraft mc)
	{
		EntityPlayer player = mc.thePlayer;
		
		if (player != null) {
			if (Mouse.getEventButton() == 0) {
				if (Mouse.getEventButtonState()) {
					ItemStack stack = player.getHeldItem();

					if (stack != null) {
						if (isValkyrieItem(stack.getItem())) {
							Vec3 playerVision = player.getLookVec();
							AxisAlignedBB reachDistance = player.boundingBox.expand(8.0D, 8.0D, 8.0D);

							List<Entity> locatedEntities = player.worldObj.getEntitiesWithinAABB(Entity.class, reachDistance);

							Entity found = null;
							double foundLen = 0.0D;

							for (Entity ent : locatedEntities) {
								if (ent == player) {
									continue;
								}

								if (!ent.canBeCollidedWith() && !(ent instanceof EntityDragon)) {
									continue;
								}

								Vec3 vec = Vec3.createVectorHelper(ent.posX - player.posX, ent.boundingBox.minY + ent.height / 2f - player.posY - player.getEyeHeight(), ent.posZ - player.posZ);
								double len = vec.lengthVector();

								if (len > 8.0F) {
									continue;
								}

								vec = vec.normalize();
								double dot = playerVision.dotProduct(vec);

								if (dot < 1.0 - 0.125 / len || !player.canEntityBeSeen(ent)) {
									continue;
								}

								if (foundLen == 0.0 || len < foundLen) {
									found = ent;
									foundLen = len;
								}
							}

							if (found != null && player.ridingEntity != found) {
								stack.damageItem(1, player);

								AetherNetwork.sendToServer(new PacketExtendedAttack(found.getEntityId()));
							}
						}
						
						else if (isAmplifiedValkyrieItem(stack.getItem())) {
							Vec3 playerVisionn = player.getLookVec();
							AxisAlignedBB reachDistancee = player.boundingBox.expand(10.0D, 10.0D, 10.0D);

							List<Entity> locatedEntitiess = player.worldObj.getEntitiesWithinAABB(Entity.class, reachDistancee);

							Entity foundd = null;
							double foundLen = 0.0D;

							for (Entity entt : locatedEntitiess) {
								if (entt == player) {
									continue;
								}

								if (!entt.canBeCollidedWith() && !(entt instanceof EntityDragon)) {
									continue;
								}

								Vec3 vecc = Vec3.createVectorHelper(entt.posX - player.posX, entt.boundingBox.minY + entt.height / 2f - player.posY - player.getEyeHeight(), entt.posZ - player.posZ);
								double lenn = vecc.lengthVector();

								if (lenn > 10.0F) {
									continue;
								}

								vecc = vecc.normalize();
								double dot = playerVisionn.dotProduct(vecc);

								if (dot < 1.0 - 0.125 / lenn || !player.canEntityBeSeen(entt)) {
									continue;
								}

								if (foundLen == 0.0 || lenn < foundLen) {
									foundd = entt;
									foundLen = lenn;
								}
							}

							if (foundd != null && player.ridingEntity != foundd) {
								stack.damageItem(1, player);
					
								AetherNetwork.sendToServer(new PacketExtendedAttack(foundd.getEntityId()));
							}
						}
						
						else if (isArkeniumItem(stack.getItem())) {
							Vec3 playerVisionn = player.getLookVec();
							AxisAlignedBB reachDistancee = player.boundingBox.expand(6.0D, 6.0D, 6.0D);

							List<Entity> locatedEntitiess = player.worldObj.getEntitiesWithinAABB(Entity.class, reachDistancee);

							Entity foundd = null;
							double foundLen = 0.0D;

							for (Entity entt : locatedEntitiess) {
								if (entt == player) {
									continue;
								}

								if (!entt.canBeCollidedWith() && !(entt instanceof EntityDragon)) {
									continue;
								}

								Vec3 vecc = Vec3.createVectorHelper(entt.posX - player.posX, entt.boundingBox.minY + entt.height / 2f - player.posY - player.getEyeHeight(), entt.posZ - player.posZ);
								double lenn = vecc.lengthVector();

								if (lenn > 6.0F) {
									continue;
								}

								vecc = vecc.normalize();
								double dot = playerVisionn.dotProduct(vecc);

								if (dot < 1.0 - 0.125 / lenn || !player.canEntityBeSeen(entt)) {
									continue;
								}

								if (foundLen == 0.0 || lenn < foundLen) {
									foundd = entt;
									foundLen = lenn;
								}
							}

							if (foundd != null && player.ridingEntity != foundd) {
								stack.damageItem(1, player);

								AetherNetwork.sendToServer(new PacketExtendedAttack(foundd.getEntityId()));
							}
						}
						
						else if (isContinuumItem(stack.getItem())) {
							Vec3 playerVisionn = player.getLookVec();
							AxisAlignedBB reachDistancee = player.boundingBox.expand(5.0D, 5.0D, 5.0D);

							List<Entity> locatedEntitiess = player.worldObj.getEntitiesWithinAABB(Entity.class, reachDistancee);

							Entity foundd = null;
							double foundLen = 0.0D;

							for (Entity entt : locatedEntitiess) {
								if (entt == player) {
									continue;
								}

								if (!entt.canBeCollidedWith() && !(entt instanceof EntityDragon)) {
									continue;
								}

								Vec3 vecc = Vec3.createVectorHelper(entt.posX - player.posX, entt.boundingBox.minY + entt.height / 2f - player.posY - player.getEyeHeight(), entt.posZ - player.posZ);
								double lenn = vecc.lengthVector();

								if (lenn > 5.0F) {
									continue;
								}

								vecc = vecc.normalize();
								double dot = playerVisionn.dotProduct(vecc);

								if (dot < 1.0 - 0.125 / lenn || !player.canEntityBeSeen(entt)) {
									continue;
								}

								if (foundLen == 0.0 || lenn < foundLen) {
									foundd = entt;
									foundLen = lenn;
								}
							}

							if (foundd != null && player.ridingEntity != foundd) {
								stack.damageItem(1, player);

								AetherNetwork.sendToServer(new PacketExtendedAttack(foundd.getEntityId()));
							}
						}
						
						else if (isAmplifiedContinuumItem(stack.getItem())) {
							Vec3 playerVisionn = player.getLookVec();
							AxisAlignedBB reachDistancee = player.boundingBox.expand(6.0D, 6.0D, 6.0D);

							List<Entity> locatedEntitiess = player.worldObj.getEntitiesWithinAABB(Entity.class, reachDistancee);

							Entity foundd = null;
							double foundLen = 0.0D;

							for (Entity entt : locatedEntitiess) {
								if (entt == player) {
									continue;
								}

								if (!entt.canBeCollidedWith() && !(entt instanceof EntityDragon)) {
									continue;
								}

								Vec3 vecc = Vec3.createVectorHelper(entt.posX - player.posX, entt.boundingBox.minY + entt.height / 2f - player.posY - player.getEyeHeight(), entt.posZ - player.posZ);
								double lenn = vecc.lengthVector();

								if (lenn > 5.0F) {
									continue;
								}

								vecc = vecc.normalize();
								double dot = playerVisionn.dotProduct(vecc);

								if (dot < 1.0 - 0.125 / lenn || !player.canEntityBeSeen(entt)) {
									continue;
								}

								if (foundLen == 0.0 || lenn < foundLen) {
									foundd = entt;
									foundLen = lenn;
								}
							}

							if (foundd != null && player.ridingEntity != foundd) {
								stack.damageItem(1, player);

								AetherNetwork.sendToServer(new PacketExtendedAttack(foundd.getEntityId()));
							}
						}
					}
				}
			}
		}
		
	}

	public boolean isValkyrieItem(Item item) {
		return item == ItemsAether.valkyrie_shovel || item == ItemsAether.valkyrie_axe || item == ItemsAether.valkyrie_pickaxe || item == ItemsAether.valkyrie_lance || item == ItemsAether.tipped_valkyrie_lance || item == ItemsAether.tipped_valkyrie_pickaxe || item == ItemsAether.tipped_valkyrie_shovel || item == ItemsAether.tipped_valkyrie_axe;
	}
	
	public boolean isAmplifiedValkyrieItem(Item stackIDD)
	{
		return stackIDD == ItemsAether.amplified_valkyrie_shovel || stackIDD == ItemsAether.amplified_valkyrie_axe || stackIDD == ItemsAether.amplified_valkyrie_pickaxe || stackIDD == ItemsAether.amplified_valkyrie_lance;
	}
	
	public boolean isAscensiteItem(Item stackIDD)
	{
		return stackIDD == ItemsAether.ascensite_shovel || stackIDD == ItemsAether.ascensite_axe || stackIDD == ItemsAether.ascensite_pickaxe || stackIDD == ItemsAether.ascensite_sword;
	}
	
	public boolean isArkeniumItem(Item stackIDDA)
	{
		return stackIDDA == ItemsAether.arkenium_shovel || stackIDDA == ItemsAether.arkenium_axe || stackIDDA == ItemsAether.arkenium_pickaxe || stackIDDA == ItemsAether.arkenium_sword || stackIDDA == ItemsAether.tipped_arkenium_sword || stackIDDA == ItemsAether.tipped_arkenium_pickaxe || stackIDDA == ItemsAether.tipped_arkenium_shovel || stackIDDA == ItemsAether.tipped_arkenium_axe;
	}
	
	public boolean isContinuumItem(Item stackIDDA)
	{
		return stackIDDA == ItemsAether.continuum_shovel || stackIDDA == ItemsAether.continuum_axe || stackIDDA == ItemsAether.continuum_pickaxe || stackIDDA == ItemsAether.continuum_sword || stackIDDA == ItemsAether.tipped_continuum_sword || stackIDDA == ItemsAether.tipped_continuum_pickaxe || stackIDDA == ItemsAether.tipped_continuum_shovel || stackIDDA == ItemsAether.tipped_continuum_axe;
	}
	
	public boolean isAmplifiedContinuumItem(Item stackIDDA)
	{
		return stackIDDA == ItemsAether.amplified_continuum_shovel || stackIDDA == ItemsAether.amplified_continuum_axe || stackIDDA == ItemsAether.amplified_continuum_pickaxe || stackIDDA == ItemsAether.amplified_continuum_sword;
	}
	
	

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event) {
		Minecraft mc = FMLClientHandler.instance().getClient();

		if (mc.thePlayer != null && event.gui instanceof GuiDownloadTerrain) {
			GuiEnterAether enterAether = new GuiEnterAether(true);
			GuiEnterAether exitAether = new GuiEnterAether(false);

			if (mc.thePlayer.dimension == AetherConfig.getAetherDimensionID()) {
				event.gui = enterAether;
				wasInAether = true;
			} else if (wasInAether) {
				event.gui = exitAether;
				wasInAether = false;
			}
		}
	}


	private void sendPickupPacket(Minecraft mc) {
		if (mc.objectMouseOver != null) {
			if (!this.onPickEntity(mc.objectMouseOver, mc.thePlayer, mc.theWorld)) {
				return;
			}

			if (mc.thePlayer.capabilities.isCreativeMode) {
				int index = mc.thePlayer.inventoryContainer.inventorySlots.size() - 9 + mc.thePlayer.inventory.currentItem;

				mc.playerController.sendSlotPacket(mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem), index);
			}
		}
	}

	private boolean onPickEntity(MovingObjectPosition target, EntityPlayer player, World world) {
		ItemStack result = null;
		boolean isCreative = player.capabilities.isCreativeMode;

		if (!isCreative) {
			return false;
		}

		if (target.entityHit != null) {
			int id = EntitiesAether.getEntityID(target.entityHit);

			if (id >= 0 && ItemAetherSpawnEgg.entityEggs.containsKey(id)) {
				result = new ItemStack(ItemsAether.aether_spawn_egg, 1, id);
			}
		}

		if (result == null) {
			return false;
		}

		for (int x = 0; x < 9; x++) {
			ItemStack stack = player.inventory.getStackInSlot(x);

			if (stack != null && stack.isItemEqual(result) && ItemStack.areItemStackTagsEqual(stack, result)) {
				player.inventory.currentItem = x;

				return true;
			}
		}

		int slot = player.inventory.getFirstEmptyStack();

		if (slot < 0 || slot >= 9) {
			slot = player.inventory.currentItem;
		}

		player.inventory.setInventorySlotContents(slot, result);
		player.inventory.currentItem = slot;

		return true;
	}

	@SubscribeEvent
	public void onBowPulled(FOVUpdateEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		if (player == null || (player != null && player.getCurrentEquippedItem() == null)) {
			return;
		}

		Item item = player.getCurrentEquippedItem().getItem();

		if (item == ItemsAether.phoenix_bow || item == ItemsAether.tipped_phoenix_bow || item == ItemsAether.amplified_phoenix_bow
			|| item == ItemsAether.cyro_bow || item == ItemsAether.tipped_cyro_bow || item == ItemsAether.amplified_cyro_bow) {
			int i = player.getItemInUseDuration();
			float f1 = (float) i / 20.0F;

			if (f1 > 1.0F) {
				f1 = 1.0F;
			} else {
				f1 = f1 * f1;
			}

			float original = event.fov;

			original *= 1.0F - f1 * 0.15F;

			event.newfov = original;
		}
	}

	private static final GuiAccessoryButton ACCESSORY_BUTTON = new GuiAccessoryButton(0, 0);

	private static final GuiMenuToggleButton MAIN_MENU_BUTTON = new GuiMenuToggleButton(0, 0);

	private static int previousSelectedTabIndex = -1;

	private static boolean shouldRemoveButton = false;
	
	private boolean openedMenu = false;

	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void onGuiOpened(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.gui instanceof GuiContainer) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;

			int guiLeft = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) event.gui, "guiLeft", "field_147003_i");
			int guiTop = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) event.gui, "guiTop", "field_147009_r");
			int xSize = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) event.gui, "xSize", "field_146999_f");
			int ySize = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) event.gui, "ySize", "field_147000_g");

			if(!AetherConfig.UseBaublesExpandedMenu()) {
				if (player.capabilities.isCreativeMode) {
					if (event.gui instanceof GuiContainerCreative guiContainerCreative) {
						if (guiContainerCreative.func_147056_g() == CreativeTabs.tabInventory.getTabIndex()) {
							event.buttonList.add(ACCESSORY_BUTTON.setPosition(guiLeft + 28, guiTop + 38));
							previousSelectedTabIndex = CreativeTabs.tabInventory.getTabIndex();
						}
					}
				} else if (event.gui instanceof GuiInventory) {
					// We place the buttons where they would be if the inventory was its original size, for consistency with Baubles's button placement algorithm
					int xSizeOriginal = 176;
					int ySizeOriginal = 166;
					event.buttonList.add(ACCESSORY_BUTTON.setPosition((xSize - xSizeOriginal) / 2 + guiLeft + 26, (ySize - ySizeOriginal) / 2 + guiTop + 65));
				}
			}

			if (event.gui instanceof GuiAccessories) {
				if (!shouldRemoveButton) {
					event.buttonList.add(ACCESSORY_BUTTON.setPosition(guiLeft + 8, guiTop + 65));
				} else {
					shouldRemoveButton = false;
				}
			}
		}

		if (AetherConfig.menuButtonEnabled() && event.gui instanceof GuiMainMenu) {
			event.buttonList.add(MAIN_MENU_BUTTON.setPosition(event.gui.width - 24, 4));
		}

		//Causing the Log to freak out if enabled
		//if (AetherConfig.getMenuEnabled() && event.gui instanceof GuiMainMenu) {
			//Minecraft.getMinecraft().displayGuiScreen(new AetherMainMenu());
		//}
		
		if (!openedMenu && AetherConfig.getMenuEnabled() && event.gui instanceof GuiMainMenu) {
	        openedMenu = true;
	        Minecraft.getMinecraft().displayGuiScreen(new AetherMainMenu());
	    }

		//if (event.gui.getClass() == GuiOptions.class)
		if (event.gui instanceof GuiOptions)
		{
			if (Minecraft.getMinecraft().thePlayer != null)
			{
				if (AetherRankings.isRankedPlayer(Minecraft.getMinecraft().thePlayer.getUniqueID()))
				{
					event.buttonList.add(new GuiCustomizationScreenButton(545, event.gui.width / 2 - 155, event.gui.height / 6 + 48 - 6, 150, 20, I18n.format("gui.options.perk_customization")));
				}
			}
		}

		//if (event.gui.getClass() == ScreenChatOptions.class)
		if (event.gui instanceof ScreenChatOptions)
		{
			if (Minecraft.getMinecraft().thePlayer != null)
			{
				int i = 13;
				event.buttonList.add(new GuiCapeButton(event.gui.width / 2 - 155 + i % 2 * 160, event.gui.height / 6 + 24 * (i >> 1)));
			}
		}
	}

	@SubscribeEvent
	public void onMouseClicked(DrawScreenEvent.Post event) {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative guiScreen && !AetherConfig.UseBaublesExpandedMenu()) {
			if (previousSelectedTabIndex != guiScreen.func_147056_g()) {
				List<GuiButton> buttonList = ObfuscationReflectionHelper.getPrivateValue(GuiScreen.class, (GuiScreen) guiScreen, 4);

				if (guiScreen.func_147056_g() == CreativeTabs.tabInventory.getTabIndex() && !buttonList.contains(ACCESSORY_BUTTON)) {
					int guiLeft = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) guiScreen, "guiLeft", "field_147003_i");
					int guiTop = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, (GuiContainer) guiScreen, "guiTop", "field_147009_r");

					buttonList.add(ACCESSORY_BUTTON.setPosition(guiLeft + 28, guiTop + 38));
				} else if (previousSelectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
					buttonList.remove(ACCESSORY_BUTTON);
				}

				previousSelectedTabIndex = guiScreen.func_147056_g();
			}
		}
	}

	@SubscribeEvent
	public void onDrawGui(GuiScreenEvent.DrawScreenEvent.Pre event) {
		if (!AetherConfig.getMenuEnabled() && event.gui instanceof AetherMainMenu) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}
	}

	@SubscribeEvent
	public void onButtonPressed(GuiScreenEvent.ActionPerformedEvent.Pre event) {
		if ((event.gui instanceof GuiInventory || event.gui instanceof GuiContainerCreative) && event.button.id == 18067) {
			AetherNetwork.sendToServer(new PacketOpenContainer(AetherGuiHandler.accessories));
		}

		if (event.button instanceof GuiCustomizationScreenButton) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiCustomizationScreen(event.gui));
		}

		if (event.button instanceof GuiCapeButton) {
			PlayerAether player = PlayerAether.get(Minecraft.getMinecraft().thePlayer);

			player.shouldRenderCape = !player.shouldRenderCape;
			AetherNetwork.sendToServer(new PacketCapeChanged(player.getEntity().getEntityId(), player.shouldRenderCape));
		}
	}

	@SubscribeEvent
	public void onRenderInvisibility(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;
		PlayerAether playerAether = PlayerAether.get(player);

		if (playerAether != null) {
			if (playerAether.getAccessoryInventory().wearingAccessory(ItemsAether.invisibility_cape)) {
				event.setCanceled(true);
			}
			
		}

		PlayerAetherRenderer.instance().setPartialTicks(event.partialRenderTick);
	}

	@SubscribeEvent
	public void onRenderAetherCape(RenderPlayerEvent.Specials.Pre event) {
		event.renderCape = !PlayerAetherRenderer.instance().isCapeRendering();
	}

	@SubscribeEvent
	public void onRenderAetherArmor(SetArmorModel event) {
		if (event.stack != null && event.stack.getItem() instanceof ItemAetherArmor) {
			event.result = PlayerAetherRenderer.instance().renderAetherArmor(PlayerAether.get(event.entityPlayer), event.renderer, event.stack, 3 - event.slot);
		}
	}

	@SubscribeEvent
	public void onRenderAccessories(RenderLivingEvent.Post event) {
		if (event.entity instanceof EntityPlayer) {
			PlayerAether playerAether = PlayerAether.get((EntityPlayer) event.entity);

			if (event.renderer instanceof RenderPlayer)
			{
				PlayerAetherRenderer.instance().renderAccessories(playerAether, (RenderPlayer) event.renderer, event.x, event.y, event.z, PlayerAetherRenderer.instance().getPartialTicks());
			}
		}
	}

	@SubscribeEvent
	public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.thePlayer == null || mc.currentScreen == null) {
			return;
		}
		if (!AetherConfig.UseBaublesExpandedMenu() && AetherKeybinds.keyBindingAccessories.isPressed()) {
			AetherNetwork.sendToServer(new PacketOpenContainer(AetherGuiHandler.accessories));
			shouldRemoveButton = true;
		}
	}

}