package com.gildedgames.the_aether.tileentity;

import net.minecraft.tileentity.*;
import net.minecraft.item.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.inventory.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.container.BlockTreasureChestBreakable;
import com.gildedgames.the_aether.inventory.InventoryLargeTreasureChestBreakable;

public class TileEntityTreasureChestBreakable extends TileEntity implements IInventory
{
  private ItemStack[] chestContents;
  public boolean adjacentChestChecked;
  public TileEntityTreasureChestBreakable adjacentChestZNeg;
  public TileEntityTreasureChestBreakable adjacentChestXPos;
  public TileEntityTreasureChestBreakable adjacentChestXNeg;
  public TileEntityTreasureChestBreakable adjacentChestZPosition;
  public float lidAngle;
  public float prevLidAngle;
  public int numUsingPlayers;
  private int ticksSinceSync;
  private int cachedChestType;
  private String customName;
  
  public TileEntityTreasureChestBreakable() {
      this.chestContents = new ItemStack[36];
      this.cachedChestType = -1;
  }
  
  @SideOnly(Side.CLIENT)
  public TileEntityTreasureChestBreakable(final int par1) {
      this.chestContents = new ItemStack[36];
      this.cachedChestType = par1;
  }
  
  public int getSizeInventory() {
      return 27; //27
  }
  
  public ItemStack getStackInSlot(final int par1) {
      return this.chestContents[par1];
  }
  
  public ItemStack decrStackSize(final int par1, final int par2) {
      if (this.chestContents[par1] == null) {
          return null;
      }
      if (this.chestContents[par1].stackSize <= par2) {
          final ItemStack itemstack = this.chestContents[par1];
          this.chestContents[par1] = null;
          this.markDirty();
          return itemstack;
      }
      final ItemStack itemstack = this.chestContents[par1].splitStack(par2);
      if (this.chestContents[par1].stackSize == 0) {
          this.chestContents[par1] = null;
      }
      this.markDirty();
      return itemstack;
  }
  
  public ItemStack getStackInSlotOnClosing(final int par1) {
      if (this.chestContents[par1] != null) {
          final ItemStack itemstack = this.chestContents[par1];
          this.chestContents[par1] = null;
          return itemstack;
      }
      return null;
  }
  
  public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
      this.chestContents[par1] = par2ItemStack;
      if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
          par2ItemStack.stackSize = this.getInventoryStackLimit();
      }
      this.markDirty();
  }
  
  public String getInventoryName() {
      return this.hasCustomInventoryName() ? this.customName : "container.chest";
  }
  
  public boolean hasCustomInventoryName() {
      return this.customName != null && this.customName.length() > 0;
  }
  
  public void setChestGuiName(final String par1Str) {
      this.customName = par1Str;
  }
  
  public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      final NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
      this.chestContents = new ItemStack[this.getSizeInventory()];
      if (par1NBTTagCompound.hasKey("CustomName")) {
          this.customName = par1NBTTagCompound.getString("CustomName");
      }
      for (int i = 0; i < nbttaglist.tagCount(); ++i) {
          final NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
          final int j = nbttagcompound1.getByte("Slot") & 0xFF;
          if (j >= 0 && j < this.chestContents.length) {
              this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
          }
      }
  }
  
  public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      final NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.chestContents.length; ++i) {
          if (this.chestContents[i] != null) {
              final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
              nbttagcompound1.setByte("Slot", (byte)i);
              this.chestContents[i].writeToNBT(nbttagcompound1);
              nbttaglist.appendTag((NBTBase)nbttagcompound1);
          }
      }
      par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
      if (this.hasCustomInventoryName()) {
          par1NBTTagCompound.setString("CustomName", this.customName);
      }
  }
  
  public int getInventoryStackLimit() {
      return 64;
  }
  
  public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
      return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
  }
  
  public void updateContainingBlockInfo() {
      super.updateContainingBlockInfo();
      this.adjacentChestChecked = false;
  }
  
  private void func_90009_a(final TileEntityTreasureChestBreakable par1TileEntityChest, final int par2) {
      if (par1TileEntityChest.isInvalid()) {
          this.adjacentChestChecked = false;
      }
      else if (this.adjacentChestChecked) {
          switch (par2) {
              case 0: {
                  if (this.adjacentChestZPosition != par1TileEntityChest) {
                      this.adjacentChestChecked = false;
                      break;
                  }
                  break;
              }
              case 1: {
                  if (this.adjacentChestXNeg != par1TileEntityChest) {
                      this.adjacentChestChecked = false;
                      break;
                  }
                  break;
              }
              case 2: {
                  if (this.adjacentChestZNeg != par1TileEntityChest) {
                      this.adjacentChestChecked = false;
                      break;
                  }
                  break;
              }
              case 3: {
                  if (this.adjacentChestXPos != par1TileEntityChest) {
                      this.adjacentChestChecked = false;
                      break;
                  }
                  break;
              }
          }
      }
  }
  
  public void checkForAdjacentChests() {
      if (!this.adjacentChestChecked) {
          this.adjacentChestChecked = true;
          this.adjacentChestZNeg = null;
          this.adjacentChestXPos = null;
          this.adjacentChestXNeg = null;
          this.adjacentChestZPosition = null;
          if (this.func_94044_a(this.xCoord - 1, this.yCoord, this.zCoord)) {
              this.adjacentChestXNeg = (TileEntityTreasureChestBreakable)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
          }
          if (this.func_94044_a(this.xCoord + 1, this.yCoord, this.zCoord)) {
              this.adjacentChestXPos = (TileEntityTreasureChestBreakable)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
          }
          if (this.func_94044_a(this.xCoord, this.yCoord, this.zCoord - 1)) {
              this.adjacentChestZNeg = (TileEntityTreasureChestBreakable)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
          }
          if (this.func_94044_a(this.xCoord, this.yCoord, this.zCoord + 1)) {
              this.adjacentChestZPosition = (TileEntityTreasureChestBreakable)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
          }
          if (this.adjacentChestZNeg != null) {
              this.adjacentChestZNeg.func_90009_a(this, 0);
          }
          if (this.adjacentChestZPosition != null) {
              this.adjacentChestZPosition.func_90009_a(this, 2);
          }
          if (this.adjacentChestXPos != null) {
              this.adjacentChestXPos.func_90009_a(this, 1);
          }
          if (this.adjacentChestXNeg != null) {
              this.adjacentChestXNeg.func_90009_a(this, 3);
          }
      }
  }
  
  private boolean func_94044_a(final int par1, final int par2, final int par3) {
      final Block block = this.worldObj.getBlock(par1, par2, par3);
      return block != null && block instanceof BlockTreasureChestBreakable && ((BlockTreasureChestBreakable)block).chestType == this.getChestType();
  }
  
  public void updateEntity() {
      super.updateEntity();
      this.checkForAdjacentChests();
      ++this.ticksSinceSync;
      if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
          this.numUsingPlayers = 0;
          final float f = 5.0f;
          final List list = this.worldObj.getEntitiesWithinAABB((Class)EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)(this.xCoord - f), (double)(this.yCoord - f), (double)(this.zCoord - f), (double)(this.xCoord + 1 + f), (double)(this.yCoord + 1 + f), (double)(this.zCoord + 1 + f)));
          for (final Object aList : list) {
              final EntityPlayer entityplayer = (EntityPlayer)aList;
              if (entityplayer.openContainer instanceof ContainerChest) {
                  final IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();
                  if (iinventory != this && (!(iinventory instanceof InventoryLargeTreasureChestBreakable) || !((InventoryLargeTreasureChestBreakable)iinventory).isPartOfLargeChest((IInventory)this))) {
                      continue;
                  }
                  ++this.numUsingPlayers;
              }
          }
      }
      this.prevLidAngle = this.lidAngle;
      final float f = 0.1f;
      if (this.numUsingPlayers > 0 && this.lidAngle == 0.0f && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
          double d1 = this.xCoord + 0.5;
          double d2 = this.zCoord + 0.5;
          if (this.adjacentChestZPosition != null) {
              d2 += 0.5;
          }
          if (this.adjacentChestXPos != null) {
              d1 += 0.5;
          }
          this.worldObj.playSoundEffect(d1, this.yCoord + 0.5, d2, "random.chestopen", 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 0.9f);
      }
      if ((this.numUsingPlayers == 0 && this.lidAngle > 0.0f) || (this.numUsingPlayers > 0 && this.lidAngle < 1.0f)) {
          final float f2 = this.lidAngle;
          if (this.numUsingPlayers > 0) {
              this.lidAngle += f;
          }
          else {
              this.lidAngle -= f;
          }
          if (this.lidAngle > 1.0f) {
              this.lidAngle = 1.0f;
          }
          final float f3 = 0.5f;
          if (this.lidAngle < f3 && f2 >= f3 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
              double d2 = this.xCoord + 0.5;
              double d3 = this.zCoord + 0.5;
              if (this.adjacentChestZPosition != null) {
                  d3 += 0.5;
              }
              if (this.adjacentChestXPos != null) {
                  d2 += 0.5;
              }
              this.worldObj.playSoundEffect(d2, this.yCoord + 0.5, d3, "random.chestclosed", 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 0.9f);
          }
          if (this.lidAngle < 0.0f) {
              this.lidAngle = 0.0f;
          }
      }
  }
  
  public boolean receiveClientEvent(final int par1, final int par2) {
      if (par1 == 1) {
          this.numUsingPlayers = par2;
          return true;
      }
      return super.receiveClientEvent(par1, par2);
  }
  
  public void openInventory() {
      if (this.numUsingPlayers < 0) {
          this.numUsingPlayers = 0;
      }
      ++this.numUsingPlayers;
      this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
      this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
      this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
  }
  
  public void closeInventory() {
      if (this.getBlockType() != null && this.getBlockType() instanceof BlockTreasureChestBreakable) {
          --this.numUsingPlayers;
          this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
          this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
          this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
      }
  }
  
  public boolean isItemValidForSlot(final int par1, final ItemStack par2ItemStack) {
      return true;
  }
  
  public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
      this.checkForAdjacentChests();
  }
  
  public int getChestType() {
      if (this.cachedChestType == -1) {
          if (this.worldObj == null || !(this.getBlockType() instanceof BlockTreasureChestBreakable)) {
              return 0;
          }
          this.cachedChestType = ((BlockTreasureChestBreakable)this.getBlockType()).chestType;
      }
      return this.cachedChestType;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
      return AxisAlignedBB.getBoundingBox((double)(this.xCoord - 1), (double)this.yCoord, (double)(this.zCoord - 1), (double)(this.xCoord + 2), (double)(this.yCoord + 2), (double)(this.zCoord + 2));
  }
}
