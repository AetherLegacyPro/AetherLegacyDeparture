package com.gildedgames.the_aether.network;

import com.gildedgames.the_aether.client.gui.*;
import com.gildedgames.the_aether.client.gui.inventory.GuiAccessories;
import com.gildedgames.the_aether.inventory.*;
import com.gildedgames.the_aether.player.PlayerAether;
import com.gildedgames.the_aether.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.world.World;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.container.DivineEnchantmentTableContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AetherGuiHandler implements IGuiHandler {

	public static final int accessories = 1, enchanter = 2, freezer = 3, incubator = 4, treasure_chest = 5, lore = 6, amplifier = 7, crafting = 8, aether_enchantment_table = 9, divine_enchantment_table = 10, ancient_enchanter = 11;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == accessories) {
			return PlayerAether.get(player).getAccessoryInventory() instanceof InventoryAccessories inventory ?
					new ContainerAccessories(inventory, player) : null;
		} else if (ID == enchanter) {
			return world.getTileEntity(x, y, z) instanceof TileEntityEnchanter enchanterInv ?
					new ContainerEnchanter(player.inventory, enchanterInv) : null;
		}  else if (ID == freezer) {
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
		} else if (ID == ancient_enchanter) {
            return world.getTileEntity(x, y, z) instanceof TileEntityAncientEnchanter enchanterInv ?
                new ContainerAncientEnchanter(player.inventory, enchanterInv) : null;
        }
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == accessories) {
			PlayerAether playerAether = PlayerAether.get(player);
			return playerAether.getAccessoryInventory() instanceof InventoryAccessories inventory ?
					new GuiAccessories(playerAether, inventory) : null;
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
		} else if (ID == ancient_enchanter) {
            return world.getTileEntity(x, y, z) instanceof TileEntityAncientEnchanter enchanterInv ?
                new GuiAncientEnchanter(player.inventory, enchanterInv) : null;
        }
		return null;
	}

}
