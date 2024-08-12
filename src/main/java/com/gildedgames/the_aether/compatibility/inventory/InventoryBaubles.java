package com.gildedgames.the_aether.compatibility.inventory;

import baubles.api.BaublesApi;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.compatibility.BaublesExpandedCompatibility;
import com.gildedgames.the_aether.inventory.InventoryAccessories;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBaubles implements IAccessoryInventory {

	private final IPlayerAether playerAether;

	public final InventoryAccessories migrationInventoryAccessories;

	public InventoryBaubles(IPlayerAether playerAether) {
		this.playerAether = playerAether;
		migrationInventoryAccessories = new InventoryAccessories(playerAether);
	}

	@Override
	public void dropAccessories() {
		//Not needed
	}

	@Override
	public void damageWornItem(int damage, Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return;
		}
		final EntityPlayer player = playerAether.getEntity();
		final IInventory baubles = BaublesApi.getBaubles(player);
		if(baubles == null) {
			return;
		}
		int[] slots = BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes());
		for(int slotIndex : slots) {
			final ItemStack stack = baubles.getStackInSlot(slotIndex);
			if(stack != null && stack.getItem() == accessory) {
				stack.damageItem(damage, player);
				if (stack.stackSize <= 0) {
					baubles.setInventorySlotContents(slotIndex, null);
					player.renderBrokenItemStack(stack);
				}
				return;
			}
		}
	}

	@Override
	public void damageWornItem(int damage, Item item, Item transformInto) {
		if(!(item instanceof ItemAccessory accessory)) {
			return;
		}
		final EntityPlayer player = playerAether.getEntity();
		final IInventory baubles = BaublesApi.getBaubles(player);
		if(baubles == null) {
			return;
		}
		int[] slots = BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes());
		for(int slotIndex : slots) {
			final ItemStack stack = baubles.getStackInSlot(slotIndex);
			if(stack != null && stack.getItem() == accessory) {
				stack.damageItem(damage, player);
				if (stack.stackSize <= 0) {
					ItemStack transformedStack = new ItemStack(transformInto);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), transformedStack);
					baubles.setInventorySlotContents(slotIndex, null);
				}
				return;
			}
		}
	}

	@Override
	public void damageWornItemsAtRate(DegradationRate degradationrate) {
		final EntityPlayer player = playerAether.getEntity();
		final IInventory baubles = BaublesApi.getBaubles(player);
		if(baubles == null) {
			return;
		}
		for(int slot = 0; slot < baubles.getSizeInventory(); ++slot) {
			ItemStack stack = baubles.getStackInSlot(slot);
			if(stack != null && stack.getItem() instanceof ItemAccessory accessory
					&& accessory.getDegradationRate() == degradationrate && accessory.getType().degrades()) {
				stack.damageItem(1, player);
				if (stack.stackSize <= 0) {
					baubles.setInventorySlotContents(slot, null);
					playerAether.getEntity().renderBrokenItemStack(stack);
				}
			}
		}
	}

	@Override
	public ItemStack getFirstStackIfWearing(AccessoryType type) {
		final IInventory baubles = BaublesApi.getBaubles(playerAether.getEntity());
		if(baubles == null) {
			return null;
		}
		for(int slotIndex : BaublesExpandedCompatibility.baubleSlotsFromAccessoryType(type)) {
			final ItemStack inventoryStack = baubles.getStackInSlot(slotIndex);
			if(inventoryStack != null && inventoryStack.getItem() instanceof ItemAccessory) {
				return inventoryStack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getFirstStackIfWearing(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return null;
		}
		final IInventory baubles = BaublesApi.getBaubles(playerAether.getEntity());
		if(baubles == null) {
			return null;
		}
		for(int slotIndex : BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes())) {
			final ItemStack inventoryStack = baubles.getStackInSlot(slotIndex);
			if(inventoryStack != null && inventoryStack.getItem() instanceof ItemAccessory) {
				return inventoryStack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getSecondStackIfWearing(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return null;
		}
		final IInventory baubles = BaublesApi.getBaubles(playerAether.getEntity());
		if(baubles == null) {
			return null;
		}
		boolean foundOne = false;
		for(int slotIndex : BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes())) {
			final ItemStack inventoryStack = baubles.getStackInSlot(slotIndex);
			if(inventoryStack != null && inventoryStack.getItem() instanceof ItemAccessory) {
				if(foundOne) {
					return inventoryStack;
				}
				foundOne = true;
			}
		}
		return null;
	}

	@Override
	public boolean setAccessorySlot(ItemStack stack) {
		if(!(stack.getItem() instanceof ItemAccessory accessory)) {
			return false;
		}
		final EntityPlayer player = playerAether.getEntity();
		final IInventory baubles = BaublesApi.getBaubles(playerAether.getEntity());
		if(baubles == null) {
			return false;
		}
		for(int slot : BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes())) {
			final ItemStack inventoryStack = baubles.getStackInSlot(slot);
			if(inventoryStack == null && accessory.canEquip(stack, player)) {
				baubles.setInventorySlotContents(slot, stack);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean wearingAccessory(Item item) {
		if(item instanceof ItemAccessory accessory) {
			final IInventory baubles = BaublesApi.getBaubles(playerAether.getEntity());
			if(baubles == null) {
				return false;
			}
			for(int slotIndex : BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes())) {
				final ItemStack stack = baubles.getStackInSlot(slotIndex);
				if(stack != null && stack.getItem() == accessory) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean wearingArmor(Item item) {
		if(item instanceof ItemArmor armor) {
			ItemStack armorStack = playerAether.getEntity().getCurrentArmor(3 - armor.armorType);
			return armorStack != null && armorStack.getItem() == armor;
		}
		return false;
	}

	@Override
	public int getAccessoryCount(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return 0;
		}
		final EntityPlayer player = playerAether.getEntity();
		final IInventory baubles = BaublesApi.getBaubles(player);
		if(baubles == null) {
			return 0;
		}
		final int[] slots = BaublesExpandedCompatibility.baubleSlotsFromBaubleType(accessory.getBaubleTypes());
		int count = 0;
		for(int slotIndex : slots) {
			final ItemStack stack = baubles.getStackInSlot(slotIndex);
			if(stack != null && stack.getItem() == accessory) {
				++count;
			}
		}
		return count;
	}

	@Override
	public NBTTagList writeToNBT(NBTTagCompound compound) {
		return migrationInventoryAccessories.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagList list) {
		migrationInventoryAccessories.readFromNBT(list);
	}

	@Override
	public void writeData(ByteBuf buf) {
		migrationInventoryAccessories.writeData(buf);
	}

	@Override
	public void readData(ByteBuf buf) {
		migrationInventoryAccessories.readData(buf);
	}

}
