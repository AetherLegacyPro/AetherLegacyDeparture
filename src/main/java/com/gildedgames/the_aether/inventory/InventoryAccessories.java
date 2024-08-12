package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.DegradationRate;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.network.AetherNetwork;
import com.gildedgames.the_aether.network.packets.PacketAccessory;
import com.gildedgames.the_aether.util.FilledList;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
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
	public float getCurrentPlayerStrVsBlock(float original) {
		float f = original;

		if (wearingAccessory(ItemsAether.diamond_pendant)) {
			f *= (2F + ((float) (getStackInSlot(AccessoryType.PENDANT).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.PENDANT).getMaxDamage()) * 2F)));
		}

		if (getStackInSlot(AccessoryType.RING) != null && getStackInSlot(AccessoryType.RING).getItem() == ItemsAether.diamond_ring) {
			f *= (2F + ((float) (getStackInSlot(AccessoryType.RING).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.RING).getMaxDamage()) * 2F)));
		}

		if (getStackInSlot(AccessoryType.EXTRA_RING) != null && getStackInSlot(AccessoryType.EXTRA_RING).getItem() == ItemsAether.diamond_ring) {
			f *= (2F + ((float) (getStackInSlot(AccessoryType.EXTRA_RING).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.EXTRA_RING).getMaxDamage()) * 2F)));
		}

		if (wearingAccessory(ItemsAether.zanite_pendant)) {
			f *= (1F + ((float) (getStackInSlot(AccessoryType.PENDANT).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.PENDANT).getMaxDamage()) * 3F)));
		}

		if (getStackInSlot(AccessoryType.RING) != null && getStackInSlot(AccessoryType.RING).getItem() == ItemsAether.zanite_ring) {
			f *= (1F + ((float) (getStackInSlot(AccessoryType.RING).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.RING).getMaxDamage()) * 3F)));
		}

		if (getStackInSlot(AccessoryType.EXTRA_RING) != null && getStackInSlot(AccessoryType.EXTRA_RING).getItem() == ItemsAether.zanite_ring) {
			f *= (1F + ((float) (getStackInSlot(AccessoryType.EXTRA_RING).getItemDamage()) / ((float) (getStackInSlot(AccessoryType.EXTRA_RING).getMaxDamage()) * 3F)));
		}

		return f == original ? original : f + original;
	}

	@Override
	public void dropAccessories() {
		if(AetherConfig.UseBaublesExpandedMenu()) {
			return;
		}
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
	public void setAccessorySlot(AccessoryType type, ItemStack stack) {
		if (stack.getItem() instanceof ItemAccessory) {
			if (getStackInSlot(type) == null) {
				setInventorySlotContents(orderedList.func_148747_b(type), stack);
			} else if (getStackInSlot(((ItemAccessory) stack.getItem()).getExtraType()) == null) {
				setAccessorySlot(((ItemAccessory) stack.getItem()).getExtraType(), stack);
			}
		}
	}

	@Override
	public ItemStack getStackInSlot(AccessoryType type) {
		return getStackInSlot(orderedList.func_148747_b(type));
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