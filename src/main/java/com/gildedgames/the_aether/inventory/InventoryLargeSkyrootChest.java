package com.gildedgames.the_aether.inventory;

import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

public class InventoryLargeSkyrootChest implements IInventory
{
  private String name;
  private IInventory upperChest;
  private IInventory lowerChest;
  
  public InventoryLargeSkyrootChest(final String par1Str, IInventory par2IInventory, IInventory par3IInventory) {
      this.name = par1Str;
      if (par2IInventory == null) {
          par2IInventory = par3IInventory;
      }
      if (par3IInventory == null) {
          par3IInventory = par2IInventory;
      }
      this.upperChest = par2IInventory;
      this.lowerChest = par3IInventory;
  }
  
  public int getSizeInventory() {
      return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
  }
  
  public boolean isPartOfLargeChest(final IInventory par1IInventory) {
      return this.upperChest == par1IInventory || this.lowerChest == par1IInventory;
  }
  
  public String getInventoryName() {
      return this.upperChest.hasCustomInventoryName() ? this.upperChest.getInventoryName() : (this.lowerChest.hasCustomInventoryName() ? this.lowerChest.getInventoryName() : this.name);
  }
  
  public boolean hasCustomInventoryName() {
      return this.upperChest.hasCustomInventoryName() || this.lowerChest.hasCustomInventoryName();
  }
  
  public ItemStack getStackInSlot(final int par1) {
      return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlot(par1 - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(par1);
  }
  
  public ItemStack decrStackSize(final int par1, final int par2) {
      return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.decrStackSize(par1 - this.upperChest.getSizeInventory(), par2) : this.upperChest.decrStackSize(par1, par2);
  }
  
  public ItemStack getStackInSlotOnClosing(final int par1) {
      return (par1 >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlotOnClosing(par1 - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlotOnClosing(par1);
  }
  
  public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
      if (par1 >= this.upperChest.getSizeInventory()) {
          this.lowerChest.setInventorySlotContents(par1 - this.upperChest.getSizeInventory(), par2ItemStack);
      }
      else {
          this.upperChest.setInventorySlotContents(par1, par2ItemStack);
      }
  }
  
  public int getInventoryStackLimit() {
      return this.upperChest.getInventoryStackLimit();
  }
  
  public void onInventoryChanged() {
      this.upperChest.markDirty();
      this.lowerChest.markDirty();
  }
  
  public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
      return this.upperChest.isUseableByPlayer(par1EntityPlayer) && this.lowerChest.isUseableByPlayer(par1EntityPlayer);
  }
  
  public void openInventory() {
      this.upperChest.openInventory();
      this.lowerChest.openInventory();
  }
  
  public void closeInventory() {
      this.upperChest.closeInventory();
      this.lowerChest.closeInventory();
  }
  
  public boolean isItemValidForSlot(final int par1, final ItemStack par2ItemStack) {
      return true;
  }
  
  public void markDirty() {
      this.upperChest.markDirty();
      this.lowerChest.markDirty();
  }
}
