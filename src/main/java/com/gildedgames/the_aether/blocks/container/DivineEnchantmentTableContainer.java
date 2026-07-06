package com.gildedgames.the_aether.blocks.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class DivineEnchantmentTableContainer extends Container {

    public IInventory tableInventory = new InventoryBasic("Enchant", true, 1) {

        public int getInventoryStackLimit() {
            return 1;
        }

        public void markDirty() {
            super.markDirty();
            DivineEnchantmentTableContainer.this.onCraftMatrixChanged(this);
        }
    };

    private World worldPointer;
    private int posX;
    private int posY;
    private int posZ;
    private Random rand = new Random();
    public long nameSeed;
    public int[] enchantLevels = new int[6];

    public DivineEnchantmentTableContainer(InventoryPlayer inventoryPlayer, World world, int p_i1811_3_, int p_i1811_4_, int p_i1811_5_) {
        this.worldPointer = world;
        this.posX = p_i1811_3_;
        this.posY = p_i1811_4_;
        this.posZ = p_i1811_5_;
        this.addSlotToContainer(new Slot(this.tableInventory, 0, 25, 47) {
            public boolean isItemValid(ItemStack itemStack) {
                return true;
            }
        });
        int l;

        for (l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 8 + l * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
        iCrafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
        iCrafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = this.crafters.get(i);
            icrafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
            icrafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
            icrafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
        if (p_75137_1_ >= 0 && p_75137_1_ <= 2) {
            this.enchantLevels[p_75137_1_] = 60;
        } else {
            super.updateProgressBar(p_75137_1_, p_75137_2_);
        }
    }

    public void onCraftMatrixChanged(IInventory inventory) {
        if (inventory == this.tableInventory) {
            ItemStack itemstack = inventory.getStackInSlot(0);
            int i;

            if (itemstack != null && itemstack.isItemEnchantable()) {
                this.nameSeed = this.rand.nextLong();

                if (!this.worldPointer.isRemote) {
                    int j;
                    float power = 0;
                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.worldPointer.isAirBlock(this.posX + k, this.posY, this.posZ + j) && this.worldPointer.isAirBlock(this.posX + k, this.posY + 1, this.posZ + j)) {
                                power += ForgeHooks.getEnchantPower(worldPointer, posX + k * 2, posY,     posZ + j * 2);
                                power += ForgeHooks.getEnchantPower(worldPointer, posX + k * 2, posY + 1, posZ + j * 2);

                                if (k != 0 && j != 0) {
                                    power += ForgeHooks.getEnchantPower(worldPointer, posX + k * 2, posY,     posZ + j    );
                                    power += ForgeHooks.getEnchantPower(worldPointer, posX + k * 2, posY + 1, posZ + j    );
                                    power += ForgeHooks.getEnchantPower(worldPointer, posX + k,     posY,     posZ + j * 2);
                                    power += ForgeHooks.getEnchantPower(worldPointer, posX + k,     posY + 1, posZ + j * 2);
                                }
                            }
                        }
                    }

                    for (j = 0; j < 6; ++j) {
                        this.enchantLevels[j] = EnchantmentHelper.calcItemStackEnchantability(this.rand, j * 2, (int)power * 2, itemstack);
                    }

                    this.detectAndSendChanges();
                }
            }
            else {
                for (i = 0; i < 6; ++i) {
                    this.enchantLevels[i] = 0;
                }
            }
        }
    }

    public boolean enchantItem(EntityPlayer entityPlayer, int p_75140_2_) {
        ItemStack itemstack = this.tableInventory.getStackInSlot(0);

        if (this.enchantLevels[p_75140_2_] > 0 && itemstack != null && (entityPlayer.experienceLevel >= 60 || entityPlayer.capabilities.isCreativeMode)) {
            if (!this.worldPointer.isRemote) {
                List list = EnchantmentHelper.buildEnchantmentList(this.rand, itemstack, this.enchantLevels[p_75140_2_]);
                boolean flag = itemstack.getItem() == Items.book;

                if (list != null) {
                    entityPlayer.addExperienceLevel(-60);

                    if (flag) {
                        itemstack.func_150996_a(Items.enchanted_book);
                    }

                    int j = flag && list.size() > 1 ? this.rand.nextInt(list.size()) : -1;

                    for (int k = 0; k < list.size(); ++k) {
                       EnchantmentData enchantmentdata = (EnchantmentData)list.get(k);

                        if (!flag || k != j) {
                            if (flag) {
                                Items.enchanted_book.addEnchantment(itemstack, enchantmentdata);
                            }
                            else {
                                itemstack.addEnchantment(enchantmentdata.enchantmentobj, RandomLevel());
                            }
                       }
                    }

                    this.onCraftMatrixChanged(this.tableInventory);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int RandomLevel() {
    	int randlevel = (int)(1 + Math.random() * 10);
        randlevel = switch (randlevel) {
            case 1 -> 3;
            case 2 -> 3;
            case 3 -> 3;
            case 4 -> 3;
            case 5 -> 4;
            case 6 -> 4;
            case 7 -> 4;
            case 8 -> 4;
            case 9 -> 5;
            case 10 -> 4;
            default -> randlevel;
        };
		return randlevel;

    }

    public void onContainerClosed(EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);

        if (!this.worldPointer.isRemote) {
            ItemStack itemstack = this.tableInventory.getStackInSlotOnClosing(0);

            if (itemstack != null) {
                entityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
            }
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.worldPointer.getBlock(this.posX, this.posY, this.posZ) != BlocksAether.divine_enchantment_table ? false : entityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (this.inventorySlots.get(0).getHasStack() || !this.inventorySlots.get(0).isItemValid(itemstack1)) {
                    return null;
                }

                if (itemstack1.hasTagCompound() && itemstack1.stackSize == 1) {
                    this.inventorySlots.get(0).putStack(itemstack1.copy());
                    itemstack1.stackSize = 0;
                }
                else if (itemstack1.stackSize >= 1) {
                    this.inventorySlots.get(0).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getItemDamage()));
                    --itemstack1.stackSize;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            }
            else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
}
