package com.gildedgames.the_aether.blocks.container;

import net.minecraft.block.material.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.inventory.InventoryLargeElysianChest;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.tileentity.TileEntityElysianChest;

import net.minecraft.inventory.*;
import net.minecraft.client.renderer.texture.*;
import cpw.mods.fml.relauncher.*;

public class BlockElysianChest extends BlockContainer
{
  private final Random random;
  public final int chestType;
  
  public BlockElysianChest(final int par2) {
      super(Material.iron);
      this.random = new Random();
      this.chestType = par2;
      this.setResistance(100F);
      this.setHardness(8.5f);
      this.setStepSound(Block.soundTypeMetal);
      this.setCreativeTab(AetherCreativeTabs.blocks);
      this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
  }
  
  public Block setIconName(final String name) {
      this.textureName = "aether_legacy:" + name;
      return this.setBlockName("aether_legacy:" + name);
  }
  
  public boolean isOpaqueCube() {
      return false;
  }
  
  public boolean renderAsNormalBlock() {
      return false;
  }
  
  public int getRenderType() {
      return BlocksAether.ElysianChestRenderId;
  }
  
  public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4) {
      if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this) {
          this.setBlockBounds(0.0625f, 0.0f, 0.0f, 0.9375f, 0.875f, 0.9375f);
      }
      else if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this) {
          this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 1.0f);
      }
      else if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this) {
          this.setBlockBounds(0.0f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
      }
      else if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this) {
          this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 1.0f, 0.875f, 0.9375f);
      }
      else {
          this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
      }
  }
  
  public void onBlockAdded(final World par1World, final int par2, final int par3, final int par4) {
      super.onBlockAdded(par1World, par2, par3, par4);
      this.unifyAdjacentChests(par1World, par2, par3, par4);
      final Block l = par1World.getBlock(par2, par3, par4 - 1);
      final Block i1 = par1World.getBlock(par2, par3, par4 + 1);
      final Block j1 = par1World.getBlock(par2 - 1, par3, par4);
      final Block k1 = par1World.getBlock(par2 + 1, par3, par4);
      if (l == this) {
          this.unifyAdjacentChests(par1World, par2, par3, par4 - 1);
      }
      if (i1 == this) {
          this.unifyAdjacentChests(par1World, par2, par3, par4 + 1);
      }
      if (j1 == this) {
          this.unifyAdjacentChests(par1World, par2 - 1, par3, par4);
      }
      if (k1 == this) {
          this.unifyAdjacentChests(par1World, par2 + 1, par3, par4);
      }
  }
  
  public void onBlockPlacedBy(final World par1World, final int par2, final int par3, final int par4, final EntityLivingBase par5EntityLivingBase, final ItemStack par6ItemStack) {
      final Block l = par1World.getBlock(par2, par3, par4 - 1);
      final Block i1 = par1World.getBlock(par2, par3, par4 + 1);
      final Block j1 = par1World.getBlock(par2 - 1, par3, par4);
      final Block k1 = par1World.getBlock(par2 + 1, par3, par4);
      byte b0 = 0;
      final int l2 = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
      if (l2 == 0) {
          b0 = 2;
      }
      if (l2 == 1) {
          b0 = 5;
      }
      if (l2 == 2) {
          b0 = 3;
      }
      if (l2 == 3) {
          b0 = 4;
      }
      if (l != this && i1 != this && j1 != this && k1 != this) {
          par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
      }
      else {
          if ((l == this || i1 == this) && (b0 == 4 || b0 == 5)) {
              if (l == this) {
                  par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
              }
              else {
                  par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
              }
              par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
          }
          if ((j1 == this || k1 == this) && (b0 == 2 || b0 == 3)) {
              if (j1 == this) {
                  par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
              }
              else {
                  par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
              }
              par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
          }
      }
      if (par6ItemStack.hasDisplayName()) {
          ((TileEntityElysianChest)par1World.getTileEntity(par2, par3, par4)).setChestGuiName(par6ItemStack.getDisplayName());
      }
  }
  
  public void unifyAdjacentChests(final World par1World, final int par2, final int par3, final int par4) {
      if (!par1World.isRemote) {
          final Block l = par1World.getBlock(par2, par3, par4 - 1);
          final Block i1 = par1World.getBlock(par2, par3, par4 + 1);
          final Block j1 = par1World.getBlock(par2 - 1, par3, par4);
          final Block k1 = par1World.getBlock(par2 + 1, par3, par4);
          byte b0;
          if (l != this && i1 != this) {
              if (j1 != this && k1 != this) {
                  b0 = 3;
                  if (l.isOpaqueCube() && !i1.isOpaqueCube()) {
                      b0 = 3;
                  }
                  if (i1.isOpaqueCube() && !l.isOpaqueCube()) {
                      b0 = 2;
                  }
                  if (j1.isOpaqueCube() && !k1.isOpaqueCube()) {
                      b0 = 5;
                  }
                  if (k1.isOpaqueCube() && !j1.isOpaqueCube()) {
                      b0 = 4;
                  }
              }
              else {
                  final Block l2 = par1World.getBlock((j1 == this) ? (par2 - 1) : (par2 + 1), par3, par4 - 1);
                  final Block i2 = par1World.getBlock((j1 == this) ? (par2 - 1) : (par2 + 1), par3, par4 + 1);
                  b0 = 3;
                  int j2;
                  if (j1 == this) {
                      j2 = par1World.getBlockMetadata(par2 - 1, par3, par4);
                  }
                  else {
                      j2 = par1World.getBlockMetadata(par2 + 1, par3, par4);
                  }
                  if (j2 == 2) {
                      b0 = 2;
                  }
                  if ((l.isOpaqueCube() || l2.isOpaqueCube()) && !i1.isOpaqueCube() && !i2.isOpaqueCube()) {
                      b0 = 3;
                  }
                  if ((i1.isOpaqueCube() || i2.isOpaqueCube()) && !l.isOpaqueCube() && !l2.isOpaqueCube()) {
                      b0 = 2;
                  }
              }
          }
          else {
              final Block l2 = par1World.getBlock(par2 - 1, par3, (l == this) ? (par4 - 1) : (par4 + 1));
              final Block i2 = par1World.getBlock(par2 + 1, par3, (l == this) ? (par4 - 1) : (par4 + 1));
              b0 = 5;
              int j2;
              if (l == this) {
                  j2 = par1World.getBlockMetadata(par2, par3, par4 - 1);
              }
              else {
                  j2 = par1World.getBlockMetadata(par2, par3, par4 + 1);
              }
              if (j2 == 4) {
                  b0 = 4;
              }
              if ((j1.isOpaqueCube() || l2.isOpaqueCube()) && !k1.isOpaqueCube() && !i2.isOpaqueCube()) {
                  b0 = 5;
              }
              if ((k1.isOpaqueCube() || i2.isOpaqueCube()) && !j1.isOpaqueCube() && !l2.isOpaqueCube()) {
                  b0 = 4;
              }
          }
          par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
      }
  }
  
  public boolean canPlaceBlockAt(final World par1World, final int par2, final int par3, final int par4) {
      int l = 0;
      if (par1World.getBlock(par2 - 1, par3, par4) == this) {
          ++l;
      }
      if (par1World.getBlock(par2 + 1, par3, par4) == this) {
          ++l;
      }
      if (par1World.getBlock(par2, par3, par4 - 1) == this) {
          ++l;
      }
      if (par1World.getBlock(par2, par3, par4 + 1) == this) {
          ++l;
      }
      return l <= 1 && !this.isThereANeighborChest(par1World, par2 - 1, par3, par4) && !this.isThereANeighborChest(par1World, par2 + 1, par3, par4) && !this.isThereANeighborChest(par1World, par2, par3, par4 - 1) && !this.isThereANeighborChest(par1World, par2, par3, par4 + 1);
  }
  
  private boolean isThereANeighborChest(final World par1World, final int par2, final int par3, final int par4) {
      return par1World.getBlock(par2, par3, par4) == this && (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this || par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this);
  }
  
  public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final Block par5) {
      super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
      final TileEntityElysianChest TileEntitySkyrootChest = (TileEntityElysianChest)par1World.getTileEntity(par2, par3, par4);
      if (TileEntitySkyrootChest != null) {
          TileEntitySkyrootChest.updateContainingBlockInfo();
      }
  }
  
  public void breakBlock(final World par1World, final int par2, final int par3, final int par4, final Block par5, final int par6) {
      final TileEntityElysianChest TileEntitySkyrootChest = (TileEntityElysianChest)par1World.getTileEntity(par2, par3, par4);
      if (TileEntitySkyrootChest != null) {
          for (int j1 = 0; j1 < TileEntitySkyrootChest.getSizeInventory(); ++j1) {
              final ItemStack itemstack = TileEntitySkyrootChest.getStackInSlot(j1);
              if (itemstack != null) {
                  final float f = this.random.nextFloat() * 0.8f + 0.1f;
                  final float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                  final float f3 = this.random.nextFloat() * 0.8f + 0.1f;
                  while (itemstack.stackSize > 0) {
                      int k1 = this.random.nextInt(21) + 10;
                      if (k1 > itemstack.stackSize) {
                          k1 = itemstack.stackSize;
                      }
                      final ItemStack itemStack = itemstack;
                      itemStack.stackSize -= k1;
                      final EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f2, par4 + f3, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                      final float f4 = 0.05f;
                      entityitem.motionX = (float)this.random.nextGaussian() * f4;
                      entityitem.motionY = (float)this.random.nextGaussian() * f4 + 0.2f;
                      entityitem.motionZ = (float)this.random.nextGaussian() * f4;
                      if (itemstack.hasTagCompound()) {
                          entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                      }
                      par1World.spawnEntityInWorld(entityitem);
                  }
              }
          }
          par1World.func_147453_f(par2, par3, par4, par5);
      }
      super.breakBlock(par1World, par2, par3, par4, par5, par6);
  }
  
  public boolean onBlockActivated(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
      if (par1World.isRemote) {
          return true;
      }
      final IInventory iinventory = this.getInventory(par1World, par2, par3, par4);
      if (iinventory != null) {
          par5EntityPlayer.displayGUIChest(iinventory);
      }
      return true;
  }
  
  public IInventory getInventory(final World par1World, final int par2, final int par3, final int par4) {
      Object object = par1World.getTileEntity(par2, par3, par4);
      if (object == null) {
          return null;
      }
      if (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN)) {
          return null;
      }
      if (par1World.getBlock(par2 - 1, par3, par4) == this) {
          object = new InventoryLargeElysianChest("container.chestDouble", (IInventory)par1World.getTileEntity(par2 - 1, par3, par4), (IInventory)object);
      }
      if (par1World.getBlock(par2 + 1, par3, par4) == this) {
          object = new InventoryLargeElysianChest("container.chestDouble", (IInventory)object, (IInventory)par1World.getTileEntity(par2 + 1, par3, par4));
      }
      if (par1World.getBlock(par2, par3, par4 - 1) == this) {
          object = new InventoryLargeElysianChest("container.chestDouble", (IInventory)par1World.getTileEntity(par2, par3, par4 - 1), (IInventory)object);
      }
      if (par1World.getBlock(par2, par3, par4 + 1) == this) {
          object = new InventoryLargeElysianChest("container.chestDouble", (IInventory)object, (IInventory)par1World.getTileEntity(par2, par3, par4 + 1));
      }
      return (IInventory)object;
  }
  
  public TileEntity createNewTileEntity(final World par1World, final int i) {
      final TileEntityElysianChest TileEntitySkyrootChest = new TileEntityElysianChest();
      return TileEntitySkyrootChest;
  }
  
  public boolean canProvidePower() {
      return this.chestType == 1;
  }
  
  public int isProvidingWeakPower(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4, final int par5) {
      if (!this.canProvidePower()) {
          return 0;
      }
      final int i1 = ((TileEntityElysianChest)par1IBlockAccess.getTileEntity(par2, par3, par4)).numUsingPlayers;
      return MathHelper.clamp_int(i1, 0, 15);
  }
  
  public int isProvidingStrongPower(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4, final int par5) {
      return (par5 == 1) ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
  }
  
  public boolean hasComparatorInputOverride() {
      return true;
  }
  
  public int getComparatorInputOverride(final World par1World, final int par2, final int par3, final int par4, final int par5) {
      return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(final IIconRegister iconRegister) {
      this.blockIcon = iconRegister.registerIcon("aether_legacy:genesis_stone");
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
  {
      super.randomDisplayTick(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_, p_149734_5_);

      if (p_149734_5_.nextInt(15) == 0)
      {
          NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.1f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f);
          NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.4f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f);
          NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.6f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f);
          NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 0.9f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f);
          NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, p_149734_2_ + p_149734_5_.nextFloat(), p_149734_3_ + 1.1f, p_149734_4_ + p_149734_5_.nextFloat(), 0.0, 0.0, 0.0, 0.0f);

      }
  }
}
