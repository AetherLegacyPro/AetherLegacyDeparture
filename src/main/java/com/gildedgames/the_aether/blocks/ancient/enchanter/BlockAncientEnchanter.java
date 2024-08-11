package com.gildedgames.the_aether.blocks.ancient.enchanter;

import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tileentity.TileEntityAncientEnchanter;

import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.client.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.texture.*;

public class BlockAncientEnchanter extends BlockContainer
{
    private Random EnchanterRand;
    private IIcon sideIcon;
    
    public BlockAncientEnchanter() {
        super(Material.rock);
        this.EnchanterRand = new Random();
        this.setHardness(2.0f);
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        final TileEntityAncientEnchanter enchanter = (TileEntityAncientEnchanter)world.getTileEntity(x, y, z);
        if (enchanter != null) {
            final ItemStack itemStack = player.getCurrentEquippedItem();
            if (!enchanter.canEnchant()) {
                if (world.isRemote) {
                    FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Altar is being blocked by something above!"));
                }
                return true;
            }
            if (itemStack != null) {
                if (itemStack.getItem() == ItemsAether.ambrosium_shard && player.isSneaking()) {
                    for (int size = itemStack.stackSize; size > 1; --size) {
                        enchanter.addAmbrosium(player, itemStack);
                    }
                    enchanter.markDirty();
                }
                if (itemStack.getItem() == ItemsAether.ambrosium_shard) {
                    enchanter.addAmbrosium(player, itemStack);
                    enchanter.markDirty();
                }
                else if (enchanter.isEnchantable(itemStack)) {
                    if (enchanter.getEnchanterStacks(0) != null && enchanter.getEnchanterStacks(0).getItem() != itemStack.getItem()) {
                        enchanter.dropNextStack();
                    }
                    else {
                        enchanter.setAchievementPlayer(player);
                        enchanter.addEnchantable(player, itemStack);
                        enchanter.markDirty();
                    }
                }
            }
            else {
                enchanter.dropNextStack();
            }
        }      
        
        return true;
    }
    
    public boolean hasTileEntity(final int metadata) {
        return true;
    }
    
    public TileEntity createNewTileEntity(final World par1World, final int meta) {
        try {
            return new TileEntityAncientEnchanter();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
    
    public IIcon getIcon(final int i, final int meta) {
        return this.sideIcon;
    }
    
    public void onBlockAdded(final World world, final int i, final int j, final int k) {
        super.onBlockAdded(world, i, j, k);
        this.setDefaultDirection(world, i, j, k);
    }
    
    public void onBlockPlacedBy(final World world, final int i, final int j, final int k, final EntityLivingBase entityliving, final ItemStack stack) {
        final int l = MathHelper.floor_double(entityliving.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
        if (l == 0) {
            world.setBlockMetadataWithNotify(i, j, k, 2, 4);
        }
        if (l == 1) {
            world.setBlockMetadataWithNotify(i, j, k, 5, 4);
        }
        if (l == 2) {
            world.setBlockMetadataWithNotify(i, j, k, 3, 4);
        }
        if (l == 3) {
            world.setBlockMetadataWithNotify(i, j, k, 4, 4);
        }
    }
    
    public void breakBlock(final World par1World, final int par2, final int par3, final int par4, final Block par5, final int par6) {
        final TileEntityAncientEnchanter var7 = (TileEntityAncientEnchanter)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null) {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                final ItemStack var9 = var7.getStackInSlot(var8);
                if (var9 != null) {
                    final float var10 = this.EnchanterRand.nextFloat() * 0.8f + 0.1f;
                    final float var11 = this.EnchanterRand.nextFloat() * 0.8f + 0.1f;
                    final float var12 = this.EnchanterRand.nextFloat() * 0.8f + 0.1f;
                    while (var9.stackSize > 0) {
                        int var13 = this.EnchanterRand.nextInt(21) + 10;
                        if (var13 > var9.stackSize) {
                            var13 = var9.stackSize;
                        }
                        final ItemStack itemStack = var9;
                        itemStack.stackSize -= var13;
                        final EntityItem var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                        if (var9.hasTagCompound()) {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }
                        final float var15 = 0.05f;
                        var14.motionX = (float)this.EnchanterRand.nextGaussian() * var15;
                        var14.motionY = (float)this.EnchanterRand.nextGaussian() * var15 + 0.2f;
                        var14.motionZ = (float)this.EnchanterRand.nextGaussian() * var15;
                        par1World.spawnEntityInWorld(var14);
                    }
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    private void setDefaultDirection(final World world, final int i, final int j, final int k) {
        if (world.isRemote) {
            return;
        }
        final Block l = world.getBlock(i, j, k - 1);
        final Block i2 = world.getBlock(i, j, k + 1);
        final Block j2 = world.getBlock(i - 1, j, k);
        final Block k2 = world.getBlock(i + 1, j, k);
        byte byte0 = 3;
        if (l.isOpaqueCube() && !i2.isOpaqueCube()) {
            byte0 = 3;
        }
        if (i2.isOpaqueCube() && !l.isOpaqueCube()) {
            byte0 = 2;
        }
        if (j2.isOpaqueCube() && !k2.isOpaqueCube()) {
            byte0 = 5;
        }
        if (k2.isOpaqueCube() && !j2.isOpaqueCube()) {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0, 4);
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return BlocksAether.AncientEnchanterRenderId;
    }
    
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        this.sideIcon = par1IIconRegister.registerIcon("aether_legacy:primeval_artifact");
    }
}

