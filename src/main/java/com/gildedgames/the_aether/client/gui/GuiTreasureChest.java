package com.gildedgames.the_aether.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.tileentity.TileEntityTreasureChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTreasureChest extends GuiContainer {
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

	private final int inventoryRows;

	private String chestType;

	public GuiTreasureChest(InventoryPlayer playerInventory, TileEntityTreasureChest chestInventory) {
		super(new ContainerChest(playerInventory, chestInventory));

		this.allowUserInput = false;

		this.inventoryRows = chestInventory.getSizeInventory() / 9;

		this.ySize = 114 + this.inventoryRows * 18;

		if (chestInventory.getKind() == 0) {
			this.chestType = I18n.format("gui.treasure_chest.bronze");
		} else if (chestInventory.getKind() == 1) {
			this.chestType = I18n.format("gui.treasure_chest.silver");
		} else if (chestInventory.getKind() == 2) {
			this.chestType = I18n.format("gui.treasure_chest.gold");
		} else if (chestInventory.getKind() == 3) {
			this.chestType = I18n.format("gui.treasure_chest.ancient_bronze");
		} else if (chestInventory.getKind() == 4) {
			this.chestType = I18n.format("gui.treasure_chest.divine_bronze");
		} else if (chestInventory.getKind() == 5) {
			this.chestType = I18n.format("gui.treasure_chest.ancient_silver");
		} else if (chestInventory.getKind() == 6) {
			this.chestType = I18n.format("gui.treasure_chest.divine_silver");
		} else if (chestInventory.getKind() == 7) {
			this.chestType = I18n.format("gui.treasure_chest.ancient_golden");
		} else if (chestInventory.getKind() == 8) {
			this.chestType = I18n.format("gui.treasure_chest.divine_golden");
		} else if (chestInventory.getKind() == 9) {
			this.chestType = I18n.format("gui.treasure_chest.mythic_bronze");
		} else if (chestInventory.getKind() == 10) {
			this.chestType = I18n.format("gui.treasure_chest.cobalt");
		} else if (chestInventory.getKind() == 11) {
			this.chestType = I18n.format("gui.treasure_chest.osmium");
		} else if (chestInventory.getKind() == 14) {
			this.chestType = I18n.format("gui.treasure_chest.palladium");
		}
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(this.chestType, 8, 6, 4210752);
	}

	/**
	 * Draws the background layer of this container (behind the items).
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}