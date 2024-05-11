package com.gildedgames.the_aether.entities.passive;

import net.minecraft.entity.passive.*;
import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;

import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public abstract class EntityAetherIIAnimal extends EntityAnimal
{
    Random random;
    
    public EntityAetherIIAnimal(final World world) {
        super(world);
        this.random = new Random();
    }
    
    public float getBlockPathWeight(final int par1, final int par2, final int par3) {
        return (this.worldObj.getBlock(par1, par2 - 1, par3) == BlocksAether.aether_grass) ? 10.0f : (this.worldObj.getLightBrightness(par1, par2, par3) - 0.5f);
    }
    
    protected void onDeathUpdate() {
        
        
        super.onDeathUpdate();
    }
    
    public boolean getCanSpawnHere() {
        final int i = MathHelper.floor_double(this.posX);
        final int j = MathHelper.floor_double(this.boundingBox.minY);
        final int k = MathHelper.floor_double(this.posZ);
        return this.worldObj.getBlock(i, j - 1, k) == BlocksAether.aether_grass && this.worldObj.getFullBlockLightValue(i, j, k) > 8 && this.getBlockPathWeight(i, j, k) >= 0.0f && this.getBlockPathWeight(i, j, k) >= 0.0f && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    
    public boolean isBreedingItem(final ItemStack par1ItemStack) {
        final Item itemId = par1ItemStack.getItem();
        return itemId == ItemsAether.blueberry || super.isBreedingItem(par1ItemStack);
    }
    
    
}

