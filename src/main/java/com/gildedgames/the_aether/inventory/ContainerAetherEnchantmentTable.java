package com.gildedgames.the_aether.inventory;

import com.gildedgames.the_aether.blocks.BlocksAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.world.World;

public class ContainerAetherEnchantmentTable extends ContainerEnchantment
{
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    
    public ContainerAetherEnchantmentTable(final InventoryPlayer par1InventoryPlayer, final World par2World, final int par3, final int par4, final int par5) {
        super(par1InventoryPlayer, par2World, par3, par4, par5);
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
    }
    
    public boolean canInteractWith(final EntityPlayer par1EntityPlayer) {
        return this.worldObj.getBlock(this.posX, this.posY, this.posZ) == BlocksAether.aether_enchantment_table && par1EntityPlayer.getDistanceSq(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5) <= 64.0;
    }
}
