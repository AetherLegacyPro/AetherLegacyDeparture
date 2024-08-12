package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketAccessory;
import com.gildedgames.the_aether.util.FilledList;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ObjectIntIdentityMap;

public class InventoryAccessories implements IInventory, IAccessoryInventory {

	private final FilledList<ItemStack> stacks = new FilledList<>(8, null);

	private final IPlayerAether playerAether;

	private final ObjectIntIdentityMap orderedList = AccessoryType.createCompleteList();

	public InventoryAccessories(IPlayerAether playerAether) {
		this.playerAether = playerAether;
	}

	//IInventory

	@Override
	public int getSizeInventory() {
		return stacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return stacks.get(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		ItemStack stack = getStackInSlot(slot);

		if (stack.stackSize <= size) {
			setInventorySlotContents(slot, null);
			return stack;
		}

		if (stack.stackSize == 0) {
			setInventorySlotContents(slot, null);
		}

		return stacks.get(slot).splitStack(size);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (getStackInSlot(slot) != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		stacks.set(slot, stack);
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return "aether_legacy:accessories";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		if (!playerAether.getEntity().worldObj.isRemote && playerAether.getEntity() instanceof EntityPlayer) {
			AetherNetwork.sendToAll(new PacketAccessory(playerAether));
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return !player.isInsideOfMaterial(Material.portal) || !player.isDead;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (stack != null && stack.getItem() instanceof ItemAccessory accessory) {
			return (orderedList.func_148745_a(slot) == accessory.getType() || (orderedList.func_148745_a(slot) == accessory.getExtraType())) && getStackInSlot(slot) == null;
		}
		return false;
	}

	//IAccessoryInventory

	@Override
	public void dropAccessories() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack stack = getStackInSlot(i);

			if (stack != null) {
				playerAether.getEntity().dropItem(stack.getItem(), stack.stackSize);
			}
		}
		markDirty();
	}

	@Override
	public void damageWornItem(int damage, Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return;
		}
		for (int slot = 0; slot < stacks.size(); ++slot) {
			ItemStack stack = stacks.get(slot);
			if (stack != null && accessory == stack.getItem()) {
				stack.damageItem(damage, playerAether.getEntity());
				if (stack.stackSize <= 0) {
					setInventorySlotContents(slot, null);
					playerAether.getEntity().renderBrokenItemStack(stack);
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
		for (int slot = 0; slot < stacks.size(); ++slot) {
			ItemStack stack = stacks.get(slot);
			if (stack != null && accessory == stack.getItem()) {
				stack.damageItem(damage, playerAether.getEntity());
				if (stack.stackSize <= 0) {
					ItemStack transformedStack = new ItemStack(transformInto);
					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(stack), transformedStack);
					setInventorySlotContents(slot, transformedStack);
					playerAether.getEntity().renderBrokenItemStack(stack);
				}
				return;
			}
		}
	}

	@Override
	public void damageWornItemsAtRate(DegradationRate degradationrate) {
		for (int slot = 0; slot < stacks.size(); ++slot) {
			ItemStack stack = stacks.get(slot);
			if(stack != null && stack.getItem() instanceof ItemAccessory accessory
					&& accessory.getDegradationRate() == degradationrate && accessory.getType().degrades()) {
				stack.damageItem(1, playerAether.getEntity());
				if (stack.stackSize <= 0) {
					setInventorySlotContents(slot, null);
					playerAether.getEntity().renderBrokenItemStack(stack);
				}
			}
		}
	}

	@Override
	public ItemStack getFirstStackIfWearing(AccessoryType type) {
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack accessoryStack = getStackInSlot(i);
			if (accessoryStack != null && accessoryStack.getItem() instanceof ItemAccessory accessory
					&& (accessory.getType() == type || accessory.getExtraType() == type)) {
				return accessoryStack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getFirstStackIfWearing(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return null;
		}
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack accessoryStack = getStackInSlot(i);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				return accessoryStack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getSecondStackIfWearing(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return null;
		}
		boolean foundOne = false;
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack accessoryStack = getStackInSlot(i);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				if(foundOne) {
					return accessoryStack;
				}
				foundOne = true;
			}
		}
		return null;
	}

	@Override
	public boolean setAccessorySlot(ItemStack stack) {
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (isItemValidForSlot(i, stack)) {
				stacks.set(i, stack);
				markDirty();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean wearingAccessory(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return false;
		}
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack accessoryStack = getStackInSlot(i);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean wearingArmor(Item item) {
		if(!(item instanceof ItemArmor armor)) {
			return false;
		}
		ItemStack armorStack;
		if (playerAether.getEntity() instanceof EntityPlayer player) {
			armorStack = player.getCurrentArmor(armor.armorType);
		} else {
			armorStack = playerAether.getEntity().getEquipmentInSlot(armor.armorType);
		}
		return armorStack != null && armorStack.getItem() == armor;
	}

	@Override
	public int getAccessoryCount(Item item) {
		int count = 0;
		for (int slot = 0; slot < getSizeInventory(); ++slot) {
			ItemStack accessoryStack = getStackInSlot(slot);
			if (accessoryStack != null && accessoryStack.getItem() == item) {
				++count;
			}
		}
		return count;
	}

	@Override
	public NBTTagList writeToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack stack = getStackInSlot(i);

			if (stack != null) {
				NBTTagCompound stackCompound = new NBTTagCompound();
				stackCompound.setByte("Slot", (byte) i);
				stack.writeToNBT(stackCompound);
				nbttaglist.appendTag(stackCompound);
			}
		}

		return nbttaglist;
	}

	@Override
	public void readFromNBT(NBTTagList list) {
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackCompound = list.getCompoundTagAt(i);
			byte slot = stackCompound.getByte("Slot");

			if (slot >= 0 && slot < this.getSizeInventory()) {
				stacks.set(slot, ItemStack.loadItemStackFromNBT(stackCompound));
			}
		}
	}

	@Override
	public void writeData(ByteBuf buf) {
		buf.writeInt(stacks.size());
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack stack = getStackInSlot(i);
			ByteBufUtils.writeItemStack(buf, stack);
		}
	}

	@Override
	public void readData(ByteBuf buf) {
		int size = buf.readInt();
		for (int i = 0; i < size; ++i) {
			this.stacks.set(i, ByteBufUtils.readItemStack(buf));
		}
	}

}