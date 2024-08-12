package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.AetherConfig;
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
		if (!playerAether.getEntity().worldObj.isRemote) {
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
		if(AetherConfig.UseBaublesExpandedMenu()) {
			return false;
		}
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
		int slot = orderedList.func_148747_b(type);
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack != null && accessoryStack.getItem() instanceof ItemAccessory) {
			return accessoryStack;
		}
		return null;
	}

	@Override
	public ItemStack getFirstStackIfWearing(Item item) {
		if(!(item instanceof ItemAccessory accessory)) {
			return null;
		}
		//Check primary slot
		int slot = orderedList.func_148747_b(accessory.getType());
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack != null && accessoryStack.getItem() == accessory) {
			return accessoryStack;
		}
		//Check secondary slot
		AccessoryType extraType = accessory.getExtraType();
		if(extraType != null) {
			slot = orderedList.func_148747_b(extraType);
			accessoryStack = getStackInSlot(slot);
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
		//Check primary slot
		int slot = orderedList.func_148747_b(accessory.getType());
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack == null || accessoryStack.getItem() != accessory) {
			return null;
		}
		//Check secondary slot
		AccessoryType extraType = accessory.getExtraType();
		if(extraType != null) {
			slot = orderedList.func_148747_b(extraType);
			accessoryStack = getStackInSlot(slot);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				return accessoryStack;
			}
		}
		return null;
	}

	@Override
	public boolean setAccessorySlot(ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof ItemAccessory accessory)) {
			return false;
		}
		//Check primary slot
		int slot = orderedList.func_148747_b(accessory.getType());
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack == null && isItemValidForSlot(slot, stack)) {
			stacks.set(slot, stack);
			markDirty();
			return true;
		}
		//Check secondary slot
		AccessoryType extraType = accessory.getExtraType();
		if(extraType != null) {
			slot = orderedList.func_148747_b(extraType);
			accessoryStack = getStackInSlot(slot);
			if (accessoryStack == null && isItemValidForSlot(slot, stack)) {
				stacks.set(slot, stack);
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
		//Check primary slot
		int slot = orderedList.func_148747_b(accessory.getType());
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack != null && accessoryStack.getItem() == accessory) {
			return true;
		}
		//Check secondary slot
		AccessoryType extraType = accessory.getExtraType();
		if(extraType != null) {
			slot = orderedList.func_148747_b(extraType);
			accessoryStack = getStackInSlot(slot);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				return true;
			}
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
		int total = 0;
		//Check primary slot
		int slot = orderedList.func_148747_b(accessory.getType());
		ItemStack accessoryStack = getStackInSlot(slot);
		if (accessoryStack != null && accessoryStack.getItem() == accessory) {
			++total;
		}
		//Check secondary slot
		AccessoryType extraType = accessory.getExtraType();
		if(extraType != null) {
			slot = orderedList.func_148747_b(extraType);
			accessoryStack = getStackInSlot(slot);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				++total;
			}
		}
		return total;
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