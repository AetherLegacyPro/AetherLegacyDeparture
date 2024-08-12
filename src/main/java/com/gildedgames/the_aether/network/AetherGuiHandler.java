package com.gildedgames.the_aether.network;

import com.gildedgames.the_aether.client.gui.inventory.GuiAccessories;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.tileentity.TileEntityAmplifier;
import com.gildedgames.the_aether.tileentity.TileEntityEnchanter;
import com.gildedgames.the_aether.tileentity.TileEntityFreezer;
import com.gildedgames.the_aether.tileentity.TileEntityIncubator;
import com.gildedgames.the_aether.tileentity.TileEntityTreasureChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.world.World;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.container.DivineEnchantmentTableContainer;
import com.gildedgames.the_aether.client.gui.GuiAetherEnchantmentTable;
import com.gildedgames.the_aether.client.gui.GuiAmplifier;
import com.gildedgames.the_aether.client.gui.GuiDivineEnchantmentTable;
import com.gildedgames.the_aether.client.gui.GuiEnchanter;
import com.gildedgames.the_aether.client.gui.GuiFreezer;
import com.gildedgames.the_aether.client.gui.GuiIncubator;
import com.gildedgames.the_aether.client.gui.GuiLore;
import com.gildedgames.the_aether.client.gui.GuiSkyrootCrafting;
import com.gildedgames.the_aether.client.gui.GuiTreasureChest;
import com.gildedgames.the_aether.inventory.ContainerAccessories;
import com.gildedgames.the_aether.inventory.ContainerAetherEnchantmentTable;
import com.gildedgames.the_aether.inventory.ContainerAmplifier;
import com.gildedgames.the_aether.inventory.ContainerEnchanter;
import com.gildedgames.the_aether.inventory.ContainerFreezer;
import com.gildedgames.the_aether.inventory.ContainerIncubator;
import com.gildedgames.the_aether.inventory.ContainerLore;
import com.gildedgames.the_aether.inventory.ContainerSkyrootWorkbench;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AetherGuiHandler implements IGuiHandler {

	public static final int accessories = 1, enchanter = 2, freezer = 3, incubator = 4, treasure_chest = 5, lore = 6, amplifier = 7, crafting = 8, aether_enchantment_table = 9, divine_enchantment_table = 10;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == accessories) {
			return new ContainerAccessories(PlayerAether.get(player).getAccessoryInventory(), player);
		} else if (ID == enchanter) {
			return world.getTileEntity(x, y, z) instanceof TileEntityEnchanter enchanterInv ?
					new ContainerEnchanter(player.inventory, enchanterInv) : null;
		} else if (ID == freezer) {
			return world.getTileEntity(x, y, z) instanceof TileEntityFreezer freezerInv ?
					new ContainerFreezer(player.inventory, freezerInv) : null;
		} else if (ID == incubator) {
			return world.getTileEntity(x, y, z) instanceof TileEntityIncubator incubatorInv ?
					new ContainerIncubator(player, player.inventory, incubatorInv) : null;
		} else if (ID == treasure_chest) {
			return world.getTileEntity(x, y, z) instanceof TileEntityTreasureChest chestInv ?
					new ContainerChest(player.inventory, chestInv) : null;
		} else if (ID == lore) {
			return new ContainerLore(player.inventory);
		}  else if (ID == amplifier) {
			return world.getTileEntity(x, y, z) instanceof TileEntityAmplifier amplifierInv ?
					new ContainerAmplifier(player.inventory, amplifierInv) : null;
		} else if (ID == crafting) {
			return new ContainerSkyrootWorkbench(player.inventory, player.worldObj, x, y, z);
		} else if (ID == aether_enchantment_table) {
			return new ContainerAetherEnchantmentTable(player.inventory, player.worldObj, x, y, z);
		} else if (ID == divine_enchantment_table) {
			return (world.getBlock(x, y, z) == BlocksAether.divine_enchantment_table) ?
					new DivineEnchantmentTableContainer(player.inventory, player.worldObj, x, y, z) : null;
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == accessories) {
			return new GuiAccessories(PlayerAether.get(player));
		} else if (ID == enchanter) {
			return world.getTileEntity(x, y, z) instanceof TileEntityEnchanter enchanterInv ?
					new GuiEnchanter(player.inventory, enchanterInv) : null;
		} else if (ID == freezer) {
			return world.getTileEntity(x, y, z) instanceof TileEntityFreezer freezerInv ?
					new GuiFreezer(player.inventory, freezerInv) : null;
		} else if (ID == incubator) {
			return world.getTileEntity(x, y, z) instanceof TileEntityIncubator incubatorInv ?
					new GuiIncubator(player, player.inventory, incubatorInv) : null;
		} else if (ID == treasure_chest) {
			return world.getTileEntity(x, y, z) instanceof TileEntityTreasureChest chestInv ?
					new GuiTreasureChest(player.inventory, chestInv) : null;
		} else if (ID == lore) {
			return new GuiLore(player.inventory);
		} else if (ID == amplifier) {
			return world.getTileEntity(x, y, z) instanceof TileEntityAmplifier amplifierInv ?
					new GuiAmplifier(player.inventory, amplifierInv) : null;
		} else if (ID == crafting) {
			return new GuiSkyrootCrafting(player.inventory, player.worldObj, x, y, z);
		} else if (ID == aether_enchantment_table) {
			return new GuiAetherEnchantmentTable(player.inventory, player.worldObj, x, y, z, null);
		} else if (ID == divine_enchantment_table) {
			return (world.getBlock(x, y, z) == BlocksAether.divine_enchantment_table) ?
					new GuiDivineEnchantmentTable(player.inventory, player.worldObj, x, y, z, null) : null;
		}
		return null;
	}

}