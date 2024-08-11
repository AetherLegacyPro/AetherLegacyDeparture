package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ObjectIntIdentityMap;

import java.util.List;

public class InventoryAccessories implements IAccessoryInventory {

	private final FilledList<ItemStack> stacks = new FilledList<>(8, null);

	private ObjectIntIdentityMap orderedList = AccessoryType.createCompleteList();

	private IPlayerAether playerAether;

	public InventoryAccessories(IPlayerAether playerAether) {
		this.playerAether = playerAether;
	}

	public float getCurrentPlayerStrVsBlock(float original) {
		float f = original;
		
		if (this.wearingAccessory(ItemsAether.diamond_pendant)) {
			f *= (2F + ((float) (this.getStackInSlot(AccessoryType.PENDANT).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.PENDANT).getMaxDamage()) * 2F)));
		}
		
		if (this.getStackInSlot(AccessoryType.RING) != null && this.getStackInSlot(AccessoryType.RING).getItem() == ItemsAether.diamond_ring) {
			f *= (2F + ((float) (this.getStackInSlot(AccessoryType.RING).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.RING).getMaxDamage()) * 2F)));
		}
		
		if (this.getStackInSlot(AccessoryType.EXTRA_RING) != null && this.getStackInSlot(AccessoryType.EXTRA_RING).getItem() == ItemsAether.diamond_ring) {
			f *= (2F + ((float) (this.getStackInSlot(AccessoryType.EXTRA_RING).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.EXTRA_RING).getMaxDamage()) * 2F)));
		}
		
		if (this.wearingAccessory(ItemsAether.zanite_pendant)) {
			f *= (1F + ((float) (this.getStackInSlot(AccessoryType.PENDANT).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.PENDANT).getMaxDamage()) * 3F)));
		}
		
		if (this.getStackInSlot(AccessoryType.RING) != null && this.getStackInSlot(AccessoryType.RING).getItem() == ItemsAether.zanite_ring) {
			f *= (1F + ((float) (this.getStackInSlot(AccessoryType.RING).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.RING).getMaxDamage()) * 3F)));
		}

		if (this.getStackInSlot(AccessoryType.EXTRA_RING) != null && this.getStackInSlot(AccessoryType.EXTRA_RING).getItem() == ItemsAether.zanite_ring) {
			f *= (1F + ((float) (this.getStackInSlot(AccessoryType.EXTRA_RING).getItemDamage()) / ((float) (this.getStackInSlot(AccessoryType.EXTRA_RING).getMaxDamage()) * 3F)));
		}

		return f == original ? original : f + original;
	}

	@Override
	public int getSizeInventory() {
		return this.stacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.stacks.get(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		ItemStack stack = this.getStackInSlot(slot);

		if (stack.stackSize <= size) {
			this.setInventorySlotContents(slot, null);

			return stack;
		}

		if (stack.stackSize == 0) {
			this.setInventorySlotContents(slot, null);
		}

		return this.stacks.get(slot).splitStack(size);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = this.getStackInSlot(slot);

		if (this.getStackInSlot(slot) != null) {
			this.setInventorySlotContents(slot, null);
		}

		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.stacks.set(slot, stack);
		this.markDirty();
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
		if (!this.playerAether.getEntity().worldObj.isRemote && this.playerAether.getEntity() instanceof EntityPlayer) {
			AetherNetwork.sendToAll(new PacketAccessory(this.playerAether));
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
		if (stack != null && stack.getItem() instanceof ItemAccessory) {
			return ((this.orderedList.func_148745_a(slot) == ((ItemAccessory) stack.getItem()).getType()) || (this.orderedList.func_148745_a(slot) == ((ItemAccessory) stack.getItem()).getExtraType())) && this.getStackInSlot(slot) == null;
		}

		return false;
	}

	@Override
	public void dropAccessories() {
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack stack = this.getStackInSlot(i);

			if (stack != null) {
				this.playerAether.getEntity().dropItem(stack.getItem(), stack.stackSize);
			}
		}

		this.markDirty();
	}

	@Override
	public void damageAccessory(int damage, AccessoryType type) {
		ItemStack stack = this.getStackInSlot(type);

		if (stack != null) {
			stack.damageItem(damage, this.playerAether.getEntity());
		}
	}

	@Override
	public void damageWornStack(int damage, ItemStack search) {
		damageWornItem(damage, search.getItem());
	}

	@Override
	public void damageWornItem(int damage, Item item) {
		for (int slot = 0; slot < stacks.size(); ++slot) {
			ItemStack stack = stacks.get(slot);
			if (stack != null && item == stack.getItem()) {
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
	public void setAccessorySlot(AccessoryType type, ItemStack stack) {
		if (stack.getItem() instanceof ItemAccessory) {
			if (this.getStackInSlot(type) == null) {
				this.setInventorySlotContents(this.orderedList.func_148747_b(type), stack);
			} else if (this.getStackInSlot(((ItemAccessory) stack.getItem()).getExtraType()) == null) {
				this.setAccessorySlot(((ItemAccessory) stack.getItem()).getExtraType(), stack);
			}
		}
	}

	@Override
	public ItemStack getStackInSlot(AccessoryType type) {
		return this.getStackInSlot(this.orderedList.func_148747_b(type));
	}

	@Override
	public ItemStack removeStackFromAccessorySlot(AccessoryType type) {
		ItemStack stack = this.getStackInSlot(this.orderedList.func_148747_b(type));

		if (stack != null) {
			this.setAccessorySlot(type, null);
		}

		return stack;
	}

	@Override
	public int getAccessoryCount(ItemStack stack) {
		int count = 0;

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack accessoryStack = this.getStackInSlot(i);

			if (accessoryStack != null && accessoryStack.getItem() == stack.getItem()) {
				++count;
			}
		}

		return count;
	}

	@Override
	public boolean setAccessorySlot(ItemStack stack) {
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.isItemValidForSlot(i, stack)) {
				this.stacks.set(i, stack);
				this.markDirty();

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean wearingAccessory(ItemStack stack) {
		return wearingAccessory(stack.getItem());
	}

	@Override
	public boolean wearingAccessory(Item accessory) {
		for (int i = 0; i < getSizeInventory(); ++i) {
			ItemStack accessoryStack = getStackInSlot(i);
			if (accessoryStack != null && accessoryStack.getItem() == accessory) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean wearingArmor(ItemStack stack) {
		return wearingArmor(stack.getItem());
	}

	@Override
	public boolean wearingArmor(Item armor) {
		if (playerAether.getEntity() instanceof EntityPlayer player) {
			for (int slot = 0; slot < player.inventory.armorInventory.length; ++slot) {
				ItemStack armorStack = player.getCurrentArmor(slot);
				if (armorStack != null && armorStack.getItem() == armor) {
					return true;
				}
			}
		} else {
			EntityLivingBase entityLiving = this.playerAether.getEntity();
			for (int slot = 1; slot < 5; ++slot) {
				ItemStack armorStack = entityLiving.getEquipmentInSlot(slot);
				if (armorStack != null && armorStack.getItem() == armor) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public NBTTagList writeToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack stack = this.getStackInSlot(i);

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
				this.stacks.set(slot, ItemStack.loadItemStackFromNBT(stackCompound));
			}
		}
	}

	@Override
	public void writeData(ByteBuf buf) {
		buf.writeInt(this.getAccessories().size());

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack stack = this.getStackInSlot(i);

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

	@Override
	public boolean isWearingZaniteSet() {
		return ((wearingArmor(ItemsAether.zanite_helmet) || wearingArmor(ItemsAether.scaled_zanite_helmet))
				&& (wearingArmor(ItemsAether.zanite_chestplate) || wearingArmor(ItemsAether.scaled_zanite_chestplate))
				&& (wearingArmor(ItemsAether.zanite_leggings) || wearingArmor(ItemsAether.scaled_zanite_leggings))
				&& (wearingArmor(ItemsAether.zanite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_zanite_boots))
				&& wearingAccessory(ItemsAether.zanite_gloves));
	}

	@Override
	public boolean isWearingGravititeSet() {
		return (wearingArmor(ItemsAether.gravitite_helmet)) || wearingArmor(ItemsAether.scaled_gravitite_helmet)
				&& (wearingArmor(ItemsAether.gravitite_chestplate) || wearingArmor(ItemsAether.scaled_gravitite_chestplate))
				&& (wearingArmor(ItemsAether.gravitite_leggings) || wearingArmor(ItemsAether.scaled_gravitite_leggings))
				&& (wearingArmor(ItemsAether.gravitite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_gravitite_boots))
				&& wearingAccessory(ItemsAether.gravitite_gloves);
	}
	
	@Override
	public boolean isWearingGravititeAndDivineralSet() {
		return (wearingArmor(ItemsAether.gravitite_helmet) || wearingArmor(ItemsAether.divineral_helmet) || wearingArmor(ItemsAether.scaled_gravitite_helmet))
				&& (wearingArmor(ItemsAether.gravitite_chestplate) || wearingArmor(ItemsAether.divineral_chestplate) || wearingArmor(ItemsAether.scaled_gravitite_chestplate))
				&& (wearingArmor(ItemsAether.gravitite_leggings) || wearingArmor(ItemsAether.divineral_leggings) || wearingArmor(ItemsAether.scaled_gravitite_leggings))
				&& (wearingArmor(ItemsAether.gravitite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.divineral_boots) || wearingArmor(ItemsAether.scaled_gravitite_boots))
				&& (wearingAccessory(ItemsAether.gravitite_gloves) || wearingAccessory(ItemsAether.divineral_gloves));
	}
	
	@Override
	public boolean isWearingDivineralSet() {
		return wearingArmor(ItemsAether.divineral_helmet)
				&& wearingArmor(ItemsAether.divineral_chestplate)
				&& wearingArmor(ItemsAether.divineral_leggings)
				&& (wearingArmor(ItemsAether.divineral_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.divineral_gloves);
	}

	@Override
	public boolean isWearingNeptuneSet() {
		return (wearingArmor(ItemsAether.scaled_neptune_helmet) || wearingArmor(ItemsAether.neptune_helmet))
			   && (wearingArmor(ItemsAether.scaled_neptune_chestplate) || wearingArmor(ItemsAether.neptune_chestplate))
			   && (wearingArmor(ItemsAether.scaled_neptune_leggings) || wearingArmor(ItemsAether.neptune_leggings))
			   && (wearingArmor(ItemsAether.scaled_neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.neptune_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
			   && wearingAccessory(ItemsAether.neptune_gloves);
	}
	
	@Override
	public boolean isWearingComboNeptuneSet() {
		return (wearingArmor(ItemsAether.neptune_helmet) || wearingArmor(ItemsAether.amplified_neptune_helmet) || wearingArmor(ItemsAether.scaled_neptune_helmet))
			   && (wearingArmor(ItemsAether.neptune_chestplate) || wearingArmor(ItemsAether.amplified_neptune_chestplate) || wearingArmor(ItemsAether.scaled_neptune_chestplate))
			   && (wearingArmor(ItemsAether.neptune_leggings) || wearingArmor(ItemsAether.amplified_neptune_leggings) || wearingArmor(ItemsAether.scaled_neptune_leggings))
			   && (wearingArmor(ItemsAether.neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_neptune_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_neptune_boots))
			   && (wearingAccessory(ItemsAether.neptune_gloves) || wearingAccessory(ItemsAether.amplified_neptune_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedNeptuneSet() {
		return wearingArmor(ItemsAether.amplified_neptune_helmet)
				&& wearingArmor(ItemsAether.amplified_neptune_chestplate)
				&& wearingArmor(ItemsAether.amplified_neptune_leggings)
				&& (wearingArmor(ItemsAether.amplified_neptune_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_neptune_gloves);
	}

	@Override
	public boolean isWearingPhoenixSet() {
		return (wearingArmor(ItemsAether.phoenix_helmet) || wearingArmor(ItemsAether.scaled_phoenix_helmet))
				&& (wearingArmor(ItemsAether.phoenix_chestplate) || wearingArmor(ItemsAether.scaled_phoenix_chestplate))
				&& (wearingArmor(ItemsAether.phoenix_leggings) || wearingArmor(ItemsAether.scaled_phoenix_leggings))
				&& (wearingArmor(ItemsAether.phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_phoenix_boots))
				&& wearingAccessory(ItemsAether.phoenix_gloves);
	}
	
	@Override
	public boolean isWearingPhoenixComboSet() {
		return (wearingArmor(ItemsAether.phoenix_helmet) || wearingArmor(ItemsAether.amplified_phoenix_helmet) || wearingArmor(ItemsAether.scaled_phoenix_helmet))
				&& (wearingArmor(ItemsAether.phoenix_chestplate) || wearingArmor(ItemsAether.amplified_phoenix_chestplate) || wearingArmor(ItemsAether.scaled_phoenix_chestplate))
				&& (wearingArmor(ItemsAether.phoenix_leggings) || wearingArmor(ItemsAether.amplified_phoenix_leggings) || wearingArmor(ItemsAether.scaled_phoenix_leggings))
				&& (wearingArmor(ItemsAether.phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_phoenix_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_phoenix_boots))
				&& (wearingAccessory(ItemsAether.phoenix_gloves) || wearingAccessory(ItemsAether.amplified_phoenix_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedPhoenixSet() {
		return wearingArmor(ItemsAether.amplified_phoenix_helmet)
				&& wearingArmor(ItemsAether.amplified_phoenix_chestplate)
				&& wearingArmor(ItemsAether.amplified_phoenix_leggings)
				&& (wearingArmor(ItemsAether.amplified_phoenix_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_phoenix_gloves);
	}

	@Override
	public boolean isWearingValkyrieSet() {
		return (wearingArmor(ItemsAether.valkyrie_helmet) || wearingArmor(ItemsAether.scaled_valkyrie_helmet))
				&& (wearingArmor(ItemsAether.valkyrie_chestplate) || wearingArmor(ItemsAether.scaled_valkyrie_chestplate))
				&& (wearingArmor(ItemsAether.valkyrie_leggings) || wearingArmor(ItemsAether.scaled_valkyrie_leggings))
				&& (wearingArmor(ItemsAether.valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
				&& wearingAccessory(ItemsAether.valkyrie_gloves);
	}
	
	@Override
	public boolean isWearingValkyrieComboSet() {
		return (wearingArmor(ItemsAether.valkyrie_helmet) || wearingArmor(ItemsAether.amplified_valkyrie_helmet) || wearingArmor(ItemsAether.scaled_valkyrie_helmet))
			   && (wearingArmor(ItemsAether.valkyrie_chestplate) || wearingArmor(ItemsAether.amplified_valkyrie_chestplate) || wearingArmor(ItemsAether.scaled_valkyrie_chestplate))
			   && (wearingArmor(ItemsAether.valkyrie_leggings) || wearingArmor(ItemsAether.amplified_valkyrie_leggings) || wearingArmor(ItemsAether.scaled_valkyrie_leggings))
			   && (wearingArmor(ItemsAether.valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
			   && (wearingAccessory(ItemsAether.valkyrie_gloves) || wearingAccessory(ItemsAether.amplified_valkyrie_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedValkyrieSet() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}

	@Override
	public boolean isWearingObsidianSet() {
		return (wearingArmor(ItemsAether.obsidian_helmet) || wearingArmor(ItemsAether.scaled_obsidian_helmet))
				&& (wearingArmor(ItemsAether.obsidian_chestplate) || wearingArmor(ItemsAether.scaled_obsidian_chestplate))
				&& (wearingArmor(ItemsAether.obsidian_leggings) || wearingArmor(ItemsAether.scaled_obsidian_leggings))
				&& (wearingArmor(ItemsAether.obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_obsidian_boots))
				&& wearingAccessory(ItemsAether.obsidian_gloves);
	}
	
	@Override
	public boolean isWearingObsidianComboSet() {
		return (wearingArmor(ItemsAether.obsidian_helmet) || wearingArmor(ItemsAether.amplified_obsidian_helmet) || wearingArmor(ItemsAether.scaled_obsidian_helmet))
			   && (wearingArmor(ItemsAether.obsidian_chestplate) || wearingArmor(ItemsAether.amplified_obsidian_chestplate) || wearingArmor(ItemsAether.scaled_obsidian_chestplate))
			   && (wearingArmor(ItemsAether.obsidian_leggings) || wearingArmor(ItemsAether.amplified_obsidian_leggings) || wearingArmor(ItemsAether.scaled_obsidian_leggings))
			   && (wearingArmor(ItemsAether.obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_obsidian_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_obsidian_boots))
			   && (wearingAccessory(ItemsAether.obsidian_gloves) || wearingAccessory(ItemsAether.amplified_obsidian_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedObsidianSet() {
		return wearingArmor(ItemsAether.amplified_obsidian_helmet)
				&& wearingArmor(ItemsAether.amplified_obsidian_chestplate)
				&& wearingArmor(ItemsAether.amplified_obsidian_leggings)
				&& (wearingArmor(ItemsAether.amplified_obsidian_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_obsidian_gloves);
	}
	
	@Override
	public boolean isWearingArkeniumSet() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.scaled_arkenium_helmet))
				&& (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.scaled_arkenium_chestplate))
				&& (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.scaled_arkenium_leggings))
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_arkenium_boots))
				&& wearingAccessory(ItemsAether.arkenium_gloves);
	}
	
	@Override
	public boolean isWearingArkeniumComboSet() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.amplified_arkenium_helmet) || wearingArmor(ItemsAether.scaled_arkenium_helmet))
			   && (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.amplified_arkenium_chestplate) || wearingArmor(ItemsAether.scaled_arkenium_chestplate))
			   && (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.amplified_arkenium_leggings) || wearingArmor(ItemsAether.scaled_arkenium_leggings))
			   && (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_arkenium_boots))
			   && (wearingAccessory(ItemsAether.arkenium_gloves) || wearingAccessory(ItemsAether.amplified_arkenium_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedArkeniumSet() {
		return wearingArmor(ItemsAether.amplified_arkenium_helmet)
				&& wearingArmor(ItemsAether.amplified_arkenium_chestplate)
				&& wearingArmor(ItemsAether.amplified_arkenium_leggings)
				&& (wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_arkenium_gloves);
	}
	
	@Override
	public boolean isWearingContinuumSet() {
		return (wearingArmor(ItemsAether.continuum_helmet) || wearingArmor(ItemsAether.scaled_continuum_helmet))
				&& (wearingArmor(ItemsAether.continuum_chestplate) || wearingArmor(ItemsAether.scaled_continuum_chestplate))
				&& (wearingArmor(ItemsAether.continuum_leggings) || wearingArmor(ItemsAether.scaled_continuum_leggings))
				&& (wearingArmor(ItemsAether.continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_continuum_boots))
				&& wearingAccessory(ItemsAether.continuum_gloves);
	}
	
	@Override
	public boolean isWearingContinuumComboSet() {
		return (wearingArmor(ItemsAether.continuum_helmet) || wearingArmor(ItemsAether.amplified_continuum_helmet) || wearingArmor(ItemsAether.scaled_continuum_helmet))
			   && (wearingArmor(ItemsAether.continuum_chestplate) || wearingArmor(ItemsAether.amplified_continuum_chestplate) || wearingArmor(ItemsAether.scaled_continuum_chestplate))
			   && (wearingArmor(ItemsAether.continuum_leggings) || wearingArmor(ItemsAether.amplified_continuum_leggings) || wearingArmor(ItemsAether.scaled_continuum_leggings))
			   && (wearingArmor(ItemsAether.continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_continuum_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_continuum_boots))
			   && (wearingAccessory(ItemsAether.continuum_gloves) || wearingAccessory(ItemsAether.amplified_continuum_gloves));
	}
	
	@Override
	public boolean isWearingAmplifiedContinuumSet() {
		return wearingArmor(ItemsAether.amplified_continuum_helmet)
				&& wearingArmor(ItemsAether.amplified_continuum_chestplate)
				&& wearingArmor(ItemsAether.amplified_continuum_leggings)
				&& (wearingArmor(ItemsAether.amplified_continuum_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_continuum_gloves);
	}
	
	@Override
	public boolean isWearingAgilityBoots() {
		return (wearingArmor(ItemsAether.agility_boots) || wearingArmor(ItemsAether.scaled_agility_boots));
	}
	
	@Override
	public boolean isWearingValkyrieRing() {
		return (wearingAccessory(ItemsAether.valkyrie_ring) || wearingAccessory(ItemsAether.reinforced_valkyrie_ring) || wearingAccessory(ItemsAether.amplified_valkyrie_ring));
	}
	
	@Override
	public boolean isWearingValkyrieRingAndAmplifiedArmor() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& (wearingAccessory(ItemsAether.valkyrie_ring) || wearingAccessory(ItemsAether.reinforced_valkyrie_ring))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}
	
	@Override
	public boolean isWearingAmplifiedValkyrieRingAndAmplifiedArmor() {
		return wearingArmor(ItemsAether.amplified_valkyrie_helmet)
				&& wearingArmor(ItemsAether.amplified_valkyrie_chestplate)
				&& wearingArmor(ItemsAether.amplified_valkyrie_leggings)
				&& (wearingArmor(ItemsAether.amplified_valkyrie_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_valkyrie_ring)
				&& wearingAccessory(ItemsAether.amplified_valkyrie_gloves);
	}
	
	@Override
	public boolean isWearingAmplifiedAgilityBoots() {
		return wearingArmor(ItemsAether.amplified_agility_boots);
	}
	
	@Override
	public boolean isWearingHasteRing() {
		return (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}
	
	@Override
	public boolean isWearingHasteRingAndArkenium() {
		return wearingArmor(ItemsAether.arkenium_helmet)
				&& wearingArmor(ItemsAether.arkenium_chestplate)
				&& wearingArmor(ItemsAether.arkenium_leggings)
				&& (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.arkenium_gloves)
				&& (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}
	
	@Override
	public boolean isWearingHasteRingAndArkeniumCombo() {
		return (wearingArmor(ItemsAether.arkenium_helmet) || wearingArmor(ItemsAether.amplified_arkenium_helmet))
			   && (wearingArmor(ItemsAether.arkenium_chestplate) || wearingArmor(ItemsAether.amplified_arkenium_chestplate))
			   && (wearingArmor(ItemsAether.arkenium_leggings) || wearingArmor(ItemsAether.amplified_arkenium_leggings))
			   && (wearingArmor(ItemsAether.arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
			   && (wearingAccessory(ItemsAether.arkenium_gloves) || wearingAccessory(ItemsAether.amplified_arkenium_gloves))
			   && (wearingAccessory(ItemsAether.haste_ring) || wearingAccessory(ItemsAether.reinforced_haste_ring) || wearingAccessory(ItemsAether.amplified_haste_ring));
	}
	
	@Override
	public boolean isWearingHasteRingAmpilifedArkenium() {
		return wearingArmor(ItemsAether.amplified_arkenium_helmet)
				&& wearingArmor(ItemsAether.amplified_arkenium_chestplate)
				&& wearingArmor(ItemsAether.amplified_arkenium_leggings)
				&& (wearingArmor(ItemsAether.amplified_arkenium_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.amplified_arkenium_gloves)
				&& wearingAccessory(ItemsAether.amplified_haste_ring);
	}
	
	@Override
	public boolean isWearingAgilityBootsAndCape() {
		return (wearingArmor(ItemsAether.agility_boots) || wearingArmor(ItemsAether.scaled_agility_boots)) && wearingAccessory(ItemsAether.agility_cape);
	}
	
	@Override
	public boolean isWearingAmplifiedAgilityBootsAndCape() {
		return (wearingArmor(ItemsAether.amplified_agility_boots) && wearingAccessory(ItemsAether.agility_cape));
	}
	
	@Override
	public boolean isWearingPureSpeed() {
		return (wearingArmor(ItemsAether.amplified_agility_boots)) && wearingAccessory(ItemsAether.agility_cape)
				&& (wearingAccessory(ItemsAether.auralite_pendant) || wearingAccessory(ItemsAether.reinforced_auralite_pendant) || wearingAccessory(ItemsAether.amplified_auralite_pendant))
				&& wearingAccessory(ItemsAether.auralite_ring);
	}
	
	@Override
	public boolean isWearingElysianSet() {
		return (wearingArmor(ItemsAether.elysian_helmet) || wearingArmor(ItemsAether.elysian_helmet))
				&& (wearingArmor(ItemsAether.elysian_chestplate) || wearingArmor(ItemsAether.elysian_chestplate))
				&& (wearingArmor(ItemsAether.elysian_leggings) || wearingArmor(ItemsAether.elysian_leggings))
				&& (wearingArmor(ItemsAether.elysian_boots) || wearingArmor(ItemsAether.elysian_boots) || wearingArmor(ItemsAether.amplified_sentry_boots) || wearingArmor(ItemsAether.scaled_valkyrie_boots))
				&& wearingAccessory(ItemsAether.elysian_gloves);
	}
	
	@Override
	public boolean isWearingDischargeCape() {
		return wearingAccessory(ItemsAether.discharge_cape);
	}
	
	@Override
	public boolean isWearingAscensiteSet() {
		return wearingArmor(ItemsAether.ascensite_helmet)
				&& wearingArmor(ItemsAether.ascensite_chestplate)
				&& wearingArmor(ItemsAether.ascensite_leggings)
				&& (wearingArmor(ItemsAether.ascensite_boots) || wearingArmor(ItemsAether.amplified_agility_boots) || wearingArmor(ItemsAether.amplified_sentry_boots))
				&& wearingAccessory(ItemsAether.ascensite_gloves);
	}

	@Override
	public List<ItemStack> getAccessories() {
		return stacks;
	}

}