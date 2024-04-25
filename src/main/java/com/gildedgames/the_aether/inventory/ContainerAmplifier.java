package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.api.AetherAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.inventory.slots.SlotAmplifier;
import com.gildedgames.the_aether.tileentity.TileEntityAmplifier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAmplifier extends Container {

	private TileEntityAmplifier amplify;

	public int progress, ticksRequired, powerRemaining;

	public ContainerAmplifier(InventoryPlayer par1InventoryPlayer, TileEntityAmplifier tileEntityAmplifier) {
		this.amplify = tileEntityAmplifier;
		this.addSlotToContainer(new Slot(tileEntityAmplifier, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntityAmplifier, 1, 56, 53));
		this.addSlotToContainer(new SlotAmplifier(tileEntityAmplifier, 2, 116, 35));
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting listener) {
		super.addCraftingToCrafters(listener);

		listener.sendProgressBarUpdate(this, 0, this.amplify.progress);
		listener.sendProgressBarUpdate(this, 1, this.amplify.powerRemaining);
		listener.sendProgressBarUpdate(this, 2, this.amplify.ticksRequired);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.progress != this.amplify.progress) {
				icrafting.sendProgressBarUpdate(this, 0, this.amplify.progress);
			}

			if (this.powerRemaining != this.amplify.powerRemaining) {
				icrafting.sendProgressBarUpdate(this, 1, this.amplify.powerRemaining);
			}

			if (this.ticksRequired != this.amplify.ticksRequired) {
				icrafting.sendProgressBarUpdate(this, 2, this.amplify.ticksRequired);
			}
		}

		this.progress = this.amplify.progress;
		this.powerRemaining = this.amplify.powerRemaining;
		this.ticksRequired = this.amplify.ticksRequired;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		if (id == 0) {
			this.amplify.progress = value;
		}

		if (id == 1) {
			this.amplify.powerRemaining = value;
		}

		if (id == 2) {
			this.amplify.ticksRequired = value;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.amplify.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 != 1 && par2 != 0) {
				if (AetherAPI.instance().hasEnchantment(itemstack)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (AetherAPI.instance().isEnchantmentFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}
