package com.gildedgames.the_aether.tileentity;

import net.minecraft.tileentity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import cpw.mods.fml.client.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import cpw.mods.fml.common.*;
import net.minecraft.entity.player.*;
import net.minecraft.server.management.*;
import java.util.*;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.ancient.enchanter.AetherEnchantmentAncientEnchanter;
import com.gildedgames.the_aether.entities.fake.item.EntityFakeItem;
import com.gildedgames.the_aether.items.ItemsAether;

public class TileEntityAncientEnchanter extends TileEntity implements IInventory
{
    private static List<AetherEnchantmentAncientEnchanter> enchantments;
    private final Random rand;
    private EntityFakeItem renderedItem;
    private EntityPlayer achievementPlayer;
    private ItemStack[] enchanterItemStacks;
    public int enchantProgress;
    public int enchantPowerRemaining;
    public int enchantTimeForItem;
    private final float ambRotationSpeed = 0.05f;
    private float ambRotation;
    private double ambSpinningSpeed;
    private double itemFloatingSpeed;
    private AetherEnchantmentAncientEnchanter currentEnchantment;
    
    public TileEntityAncientEnchanter() {
        this.renderedItem = null;
        this.ambRotation = 0.0f;
        this.ambSpinningSpeed = 0.0;
        this.itemFloatingSpeed = 0.0;
        this.enchanterItemStacks = new ItemStack[3];
        this.enchantProgress = 0;
        this.enchantPowerRemaining = 0;
        this.enchantTimeForItem = 0;
        this.rand = new Random();
    }
    
    public void setAchievementPlayer(final EntityPlayer player) {
        this.achievementPlayer = player;
    }
    
    public int getSizeInventory() {
        return this.enchanterItemStacks.length;
    }
    
    public ItemStack getStackInSlot(final int i) {
        return this.enchanterItemStacks[i];
    }
    
    public ItemStack decrStackSize(final int i, final int j) {
        if (this.enchanterItemStacks[i] == null) {
            return null;
        }
        if (this.enchanterItemStacks[i].stackSize <= j) {
            final ItemStack itemstack = this.enchanterItemStacks[i];
            this.enchanterItemStacks[i] = null;
            if (!this.worldObj.isRemote) {
                this.sendToAllInOurWorld(this.getDescriptionPacket());
            }
            return itemstack;
        }
        final ItemStack itemstack2 = this.enchanterItemStacks[i].splitStack(j);
        if (this.enchanterItemStacks[i].stackSize == 0) {
            this.enchanterItemStacks[i] = null;
        }
        if (!this.worldObj.isRemote) {
            this.sendToAllInOurWorld(this.getDescriptionPacket());
        }
        return itemstack2;
    }
    
    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.enchanterItemStacks[par1] != null) {
            final ItemStack var2 = this.enchanterItemStacks[par1];
            this.enchanterItemStacks[par1] = null;
            return var2;
        }
        return null;
    }
    
    public void setInventorySlotContents(final int i, final ItemStack itemstack) {
        this.enchanterItemStacks[i] = itemstack;
        if (i == 0) {
            this.currentEnchantment = null;
        }
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        if (!this.worldObj.isRemote) {
            this.sendToAllInOurWorld(this.getDescriptionPacket());
        }
    }
    
    public String getInventoryName() {
        return "Enchanter";
    }
    
    public void readFromNBT(final NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        final NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
        this.enchanterItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            final NBTTagCompound nbttagcompound2 = nbttaglist.getCompoundTagAt(i);
            final byte byte0 = nbttagcompound2.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.enchanterItemStacks.length) {
                this.enchanterItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound2);
            }
        }
        this.enchantProgress = nbttagcompound.getShort("BurnTime");
        this.enchantTimeForItem = nbttagcompound.getShort("CookTime");
    }
    
    public void writeToNBT(final NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)this.enchantProgress);
        nbttagcompound.setShort("CookTime", (short)this.enchantTimeForItem);
        final NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.enchanterItemStacks.length; ++i) {
            if (this.enchanterItemStacks[i] != null) {
                final NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                nbttagcompound2.setByte("Slot", (byte)i);
                this.enchanterItemStacks[i].writeToNBT(nbttagcompound2);
                nbttaglist.appendTag(nbttagcompound2);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
    }
    
    public int getInventoryStackLimit() {
        return 64;
    }
    
    public void addEnchantable(final EntityPlayer player, final ItemStack stack) {
        if (stack == null || !this.isEnchantable(stack)) {
            return;
        }
        ItemStack enchantableStack = this.getStackInSlot(0);
        int stackSizeLimit = this.getInventoryStackLimit();
        if (this.isLimitedToOne(stack)) {
            stackSizeLimit = 1;
        }
        if (enchantableStack == null) {
            if (stack.stackSize > stackSizeLimit) {
                enchantableStack = stack;
                enchantableStack.stackSize = stackSizeLimit;
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
                this.setInventorySlotContents(0, enchantableStack);
            }
            else {
                enchantableStack = stack;
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
                this.setInventorySlotContents(0, enchantableStack);
            }
        }
        else if (enchantableStack.getItem() == stack.getItem() && enchantableStack.getItemDamage() == stack.getItemDamage()) {
            if (this.canCombineStackWithRemainder(stack, enchantableStack, stackSizeLimit)) {
                this.combineStackWithRemainder(stack, enchantableStack, stackSizeLimit);
            }
            else if (this.stackIsFull(enchantableStack, stackSizeLimit)) {
                if (this.worldObj.isRemote) {
                    FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Altar is at full capacity."));
                }
            }
            else {
                final ItemStack itemStack = enchantableStack;
                itemStack.stackSize += stack.stackSize;
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
            }
        }
        this.markDirty();
        if (!this.worldObj.isRemote) {
            this.sendToAllInOurWorld(this.getDescriptionPacket());
        }
    }
    
    public boolean canEnchant() {
        return this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == Blocks.air;
    }
    
    public int getRemainingStackSize(final ItemStack newStack, final ItemStack currentStack, final int stackSizeLimit) {
        if (this.canCombineStackWithRemainder(newStack, currentStack, stackSizeLimit)) {
            return currentStack.stackSize + newStack.stackSize - stackSizeLimit;
        }
        return 0;
    }
    
    public boolean canCombineStackWithRemainder(final ItemStack newStack, final ItemStack currentStack, final int stackSizeLimit) {
        return newStack.getItem() == currentStack.getItem() && newStack.getItemDamage() == currentStack.getItemDamage() && !this.stackIsFull(currentStack, stackSizeLimit) && currentStack.stackSize + newStack.stackSize > stackSizeLimit;
    }
    
    public boolean stackIsFull(final ItemStack stack, final int stackSizeLimit) {
        return stack.stackSize == stackSizeLimit;
    }
    
    public void combineStackWithRemainder(final ItemStack newStack, final ItemStack currentStack, final int stackSizeLimit) {
        if (!this.canCombineStackWithRemainder(newStack, currentStack, stackSizeLimit)) {
            return;
        }
        newStack.stackSize = this.getRemainingStackSize(newStack, currentStack, stackSizeLimit);
        currentStack.stackSize = this.getInventoryStackLimit();
    }
    
    public void addAmbrosium(final EntityPlayer player, final ItemStack stack) {
        if (stack == null || stack.getItem() != ItemsAether.ambrosium_shard) {
            return;
        }
        ItemStack ambrosiumStack = this.getStackInSlot(1);
        if (ambrosiumStack != null && ambrosiumStack.stackSize >= this.getInventoryStackLimit()) {
            return;
        }
        if (ambrosiumStack == null) {
            ambrosiumStack = new ItemStack(ItemsAether.ambrosium_shard, 1, 0);
            this.setInventorySlotContents(1, ambrosiumStack);
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }
        else {
            final ItemStack itemStack = ambrosiumStack;
            ++itemStack.stackSize;
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }
        this.markDirty();
        if (!this.worldObj.isRemote) {
            this.sendToAllInOurWorld(this.getDescriptionPacket());
        }
    }
    
    public void dropNextStack() {
        if (this.enchanterItemStacks[1] != null) {
            if (!this.worldObj.isRemote) {
                final EntityItem entityitem = new EntityItem(this.worldObj, this.xCoord + 0.5f, this.yCoord + 1.0f, this.zCoord + 0.5f, this.enchanterItemStacks[1]);
                entityitem.delayBeforeCanPickup = 10;
                this.worldObj.spawnEntityInWorld(entityitem);
            }
            this.decrStackSize(1, this.enchanterItemStacks[1].stackSize);
        }
        else if (this.enchanterItemStacks[0] != null) {
            if (!this.worldObj.isRemote) {
                final EntityItem entityitem = new EntityItem(this.worldObj, this.xCoord + 0.5f, this.yCoord + 1.0f, this.zCoord + 0.5f, this.enchanterItemStacks[0]);
                entityitem.delayBeforeCanPickup = 10;
                this.worldObj.spawnEntityInWorld(entityitem);
            }
            this.decrStackSize(0, this.enchanterItemStacks[0].stackSize);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(final int i) {
        if (this.enchantTimeForItem == 0) {
            return 0;
        }
        return this.enchantProgress * i / this.enchantTimeForItem;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(final int i) {
        return this.enchantPowerRemaining * i / 500;
    }
    
    public boolean isBurning() {
        return this.enchantPowerRemaining > 0;
    }
    
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            if (this.renderedItem == null) {
                if (this.getEnchanterStacks(0) != null) {
                    this.renderedItem = new EntityFakeItem(this.worldObj, this.xCoord + 0.5, this.yCoord + 1.15, this.zCoord + 0.5, this.getEnchanterStacks(0));
                    this.worldObj.spawnEntityInWorld(this.renderedItem);
                }
            }
            
            else if (this.getEnchanterStacks(0) == null) {
                this.renderedItem.setDead();
                this.renderedItem = null;
            }
        }
        if (this.enchanterItemStacks[0] != null) {
            this.itemFloatingSpeed = 0.03;
        }
        else {
            this.itemFloatingSpeed = 0.0;
        }
        if (this.enchanterItemStacks[1] != null) {
            this.getClass();
            final float rotationSpeed = 0.05f * (this.enchanterItemStacks[1].stackSize * 0.5f);
            this.ambRotation += rotationSpeed;
            double spinningSpeed;
            if (this.enchanterItemStacks[1].stackSize < 4) {
                spinningSpeed = 0.2 * (this.enchanterItemStacks[1].stackSize * 0.5);
            }
            else {
                spinningSpeed = 0.35;
            }
            this.ambSpinningSpeed = spinningSpeed;
        }
        else {
            this.ambRotation = 0.0f;
            this.ambSpinningSpeed = 0.0;
        }
        if (this.currentEnchantment != null) {
            final ItemStack inputStack = this.getStackInSlot(0);
            final ItemStack fuelStack = this.getStackInSlot(1);
            if (this.canEnchant() && fuelStack != null && fuelStack.stackSize >= this.currentEnchantment.enchantAmbrosiumNeeded && inputStack != null) {
                Aether.proxy.spawnAltarParticles(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.rand);
                
                if (!this.worldObj.isRemote) {
                    final ItemStack outputStack = this.currentEnchantment.getResult(this.rand);
                    outputStack.stackSize = 1;
                    outputStack.stackTagCompound = inputStack.stackTagCompound;
                    final EntityItem entityitem = new EntityItem(this.worldObj, this.xCoord + 0.5f, this.yCoord + 1.0f, this.zCoord + 0.5f, outputStack);
                    entityitem.delayBeforeCanPickup = 10;
                    this.worldObj.spawnEntityInWorld(entityitem);
                }
                this.decrStackSize(0, 1);
                this.decrStackSize(1, this.currentEnchantment.enchantAmbrosiumNeeded);
                this.currentEnchantment = null;
            }
        }
        else {
            final ItemStack itemstack = this.getStackInSlot(0);
            for (final AetherEnchantmentAncientEnchanter enchantment : TileEntityAncientEnchanter.enchantments) {
                final ItemStack result = enchantment.getResult(this.rand);
                if (itemstack != null && itemstack.getItem() == enchantment.getRequirement().getItem() && (itemstack.getItemDamage() == enchantment.getRequirement().getItemDamage() || enchantment.repairing)) {
                    if (this.enchanterItemStacks[2] == null) {
                        this.currentEnchantment = enchantment;
                    }
                    else {
                        if (this.enchanterItemStacks[2].getItem() != result.getItem() || result.getItem().getItemStackLimit() <= this.enchanterItemStacks[2].stackSize) {
                            continue;
                        }
                        this.currentEnchantment = enchantment;
                    }
                }
            }
        }
    }
    
    public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i) {
        TileEntityAncientEnchanter.enchantments.add(new AetherEnchantmentAncientEnchanter(from, to, i));
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i, final boolean limit) {
    	TileEntityAncientEnchanter.enchantments.add(new AetherEnchantmentAncientEnchanter(from, to, i, limit));
    }
    
    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i, final boolean limit, final boolean repairing) {
        final AetherEnchantmentAncientEnchanter enchantment = new AetherEnchantmentAncientEnchanter(from, to, i, limit);
        enchantment.repairing = repairing;
        TileEntityAncientEnchanter.enchantments.add(enchantment);
    }
    
    public static void addEnchantment(final AetherEnchantmentAncientEnchanter enchantment) {
    	TileEntityAncientEnchanter.enchantments.add(enchantment);
    }
    
    public boolean isEnchantable(final ItemStack stack) {
        for (final AetherEnchantmentAncientEnchanter enchantment : TileEntityAncientEnchanter.enchantments) {
            if (stack != null && enchantment != null && stack.getItem() == enchantment.getRequirement().getItem() && (stack.getItemDamage() == enchantment.getRequirement().getItemDamage() || enchantment.repairing)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isLimitedToOne(final ItemStack stack) {
        for (final AetherEnchantmentAncientEnchanter enchantment : TileEntityAncientEnchanter.enchantments) {
            if (stack != null && enchantment != null && stack.getItem() == enchantment.getRequirement().getItem() && stack.getItemDamage() == enchantment.getRequirement().getItemDamage() && enchantment.limitStackToOne) {
                return true;
            }
        }
        return false;
    }
    
    public void openInventory() {
    }
    
    public void closeInventory() {
    }
    
    public Packet getDescriptionPacket() {
        final NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }
    
    private void sendToAllInOurWorld(final Packet pkt) {
        final ServerConfigurationManager scm = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager();
        for (final EntityPlayerMP player : scm.playerEntityList) {
            if (player.worldObj == this.worldObj) {
                player.playerNetServerHandler.sendPacket(pkt);
            }
        }
    }
    
    public ItemStack getEnchanterStacks(final int index) {
        return this.enchanterItemStacks[index];
    }
    
    public float getAmbRotation() {
        return this.ambRotation;
    }
    
   public double getItemFloating() {
        if (this.worldObj.isRemote && Aether.isGamePaused()) {
          return 0.0;
        }
        return this.itemFloatingSpeed;
    }
    
   public double getAmbSpinning() {
       if (this.worldObj.isRemote && Aether.isGamePaused()) {
          return 0.0;
        }
       return this.ambSpinningSpeed;
    }
    
    public boolean hasCustomInventoryName() {
        return false;
    }
    
    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return false;
    }
    
    static {
    	TileEntityAncientEnchanter.enchantments = new ArrayList<>();
    }
}

