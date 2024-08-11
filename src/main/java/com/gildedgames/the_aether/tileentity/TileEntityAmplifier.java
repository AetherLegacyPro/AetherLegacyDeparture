package com.gildedgames.the_aether.tileentity;

import java.util.List;
import java.util.Map;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.events.AetherHooks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gildedgames.the_aether.api.enchantments.AetherAmplifier;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.container.BlockAetherContainer;
import com.gildedgames.the_aether.tileentity.util.AetherTileEntity;
import com.gildedgames.the_aether.util.FilledList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityAmplifier extends AetherTileEntity {

	public int progress, ticksRequired, powerRemaining;

	private final FilledList<ItemStack> amplifiedItemStacks = new FilledList<>(3, null);

	private AetherAmplifier currentAmplifier;

	public TileEntityAmplifier() {
		super("Amplifier");
	}

	@Override
	public List<ItemStack> getTileInventory() {
		return this.amplifiedItemStacks;
	}

	@Override
	public void onSlotChanged(int index) {

	}

	@Override
	public void updateEntity() {
		boolean flag = this.isAmplifing();

		if (this.powerRemaining > 0) {
			this.powerRemaining--;

			if (this.currentAmplifier != null) {
				if (this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == BlocksAether.enchanted_gravitite) {
					this.progress += 4.5F;
				} else {
					this.progress++;
				}
			}
		}

		if (this.currentAmplifier != null) {
			if (this.progress >= this.ticksRequired) {
				if (!this.worldObj.isRemote) {
					ItemStack result = this.currentAmplifier.getOutput().copy();

					EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(this.getStackInSlot(0)), result);

					if (this.getStackInSlot(0).hasTagCompound())
					{
						result.setTagCompound(this.getStackInSlot(0).getTagCompound());
					}

					if (this.getStackInSlot(2) != null && this.getStackInSlot(2).isStackable()) {
						result.stackSize += (this.getStackInSlot(2).stackSize);

						this.setInventorySlotContents(2, result);
					} else {
						this.setInventorySlotContents(2, result);
					}

					if (this.getStackInSlot(0).getItem().hasContainerItem(this.getStackInSlot(0))) {
						this.setInventorySlotContents(0, this.getStackInSlot(0).getItem().getContainerItem(this.getStackInSlot(0)));
					} else {
						this.decrStackSize(0, 1);
					}
				}

				this.progress = 0;
				AetherHooks.onItemAmplify(this, this.currentAmplifier);
			}

			if (this.getStackInSlot(0) == null || (this.getStackInSlot(0) != null && AetherAPI.instance().getAmplifier(this.getStackInSlot(0)) != this.currentAmplifier)) {
				this.currentAmplifier = null;
				this.progress = 0;
			}

			if (this.powerRemaining <= 0) {
				if (this.getStackInSlot(1) != null && AetherAPI.instance().isAmplifierFuel(this.getStackInSlot(1))) {
					this.powerRemaining += AetherAPI.instance().getAmplifierFuel(this.getStackInSlot(1)).getTimeGiven();

					if (!this.worldObj.isRemote) {
						this.decrStackSize(1, 1);
					}
				} else {
					this.currentAmplifier = null;
					this.progress = 0;
				}
			}
		} else if (this.getStackInSlot(0) != null) {
			ItemStack itemstack = this.getStackInSlot(0);
			AetherAmplifier amplifier = AetherAPI.instance().getAmplifier(itemstack);

			if (amplifier != null) {
				if (this.getStackInSlot(2) == null || (amplifier.getOutput().getItem() == this.getStackInSlot(2).getItem() && amplifier.getOutput().getItemDamage() == this.getStackInSlot(2).getItemDamage() && this.getStackInSlot(2).isStackable())) {
					this.currentAmplifier = amplifier;
					this.ticksRequired = this.currentAmplifier.getTimeRequired();
					this.addEnchantmentWeight(itemstack);
					this.ticksRequired = AetherHooks.onSetAmplifierTime(this, this.currentAmplifier, this.ticksRequired);
				}
			}
		}

		if (flag != this.isAmplifing()) {
			this.markDirty();
			BlockAetherContainer.setState(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.isAmplifing());
		}
	}

	@SuppressWarnings("unchecked")
	public void addEnchantmentWeight(ItemStack stack) {
		Map<Enchantment, Integer> enchantments = (Map<Enchantment, Integer>) (Map<?, ?>) EnchantmentHelper.getEnchantments(stack); // returns Integer, Integer

		if (!enchantments.isEmpty()) {
			for (int levels : enchantments.values()) {
				this.ticksRequired += (levels * 1250);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getEnchantmentProgressScaled(int i) {
		if (this.ticksRequired == 0) {
			return 0;
		}

		return (this.progress * i) / this.ticksRequired;
	}

	@SideOnly(Side.CLIENT)
	public int getEnchantmentTimeRemaining(int i) {
		return (this.powerRemaining * i) / 500;
	}

	public boolean isAmplifing() {
		return this.powerRemaining > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		this.progress = compound.getInteger("progress");
		this.powerRemaining = compound.getInteger("powerRemaining");
		this.ticksRequired = compound.getInteger("ticksRequired");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger("progress", this.progress);
		compound.setInteger("powerRemaining", this.powerRemaining);
		compound.setInteger("ticksRequired", this.ticksRequired);
	}

	@Override
	public boolean isValidSlotItem(int slot, ItemStack stackInSlot) {
		if (slot == 2) {
			return false;
		} else if (slot == 1 && AetherAPI.instance().isAmplifierFuel(stackInSlot)) {
			return true;
		} else if (slot == 0 && AetherAPI.instance().hasAmplifier(stackInSlot)) {
			return true;
		}

		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[]{2} : new int[]{0, 1};
	}

}
