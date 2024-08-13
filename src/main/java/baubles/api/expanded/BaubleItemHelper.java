package baubles.api.expanded;

import baubles.api.BaublesApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class BaubleItemHelper {

    /**
     * Adds compatible slot information to an item's tooltip, but only when shift is held.
     *
     * @param tooltip The tooltip list.
     * @param types The item's types.
     * @return If shift was held.
     */
    @SideOnly(Side.CLIENT)
    public static boolean addSlotInformation(List<String> tooltip, String[] types) {
        boolean shiftHeld = GuiScreen.isShiftKeyDown();
        if(shiftHeld) {
            tooltip.add(StatCollector.translateToLocal("tooltip.compatibleslots"));
            for(int i = 0; i < types.length; i++) {
                String type = StatCollector.translateToLocal("slot." + types[i]);
                if(i < types.length - 1) type += ",";
                tooltip.add(type);
            }
        } else {
            tooltip.add(StatCollector.translateToLocal("tooltip.shiftprompt"));
        }
        return shiftHeld;
    }


    /**
     * Equips a bauble in an appropriate slot when right-clicked.
     *
     * @param itemStackIn The item stack being right-clicked with.
     * @param worldIn The world instance.
     * @param player The player that right-clicked.
     * @return The item stack.
     */
    public static ItemStack onBaubleRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        IInventory baubles = BaublesApi.getBaubles(player);
        if(baubles == null) {
            return itemStackIn;
        }
        for(int slotIndex = 0; slotIndex < baubles.getSizeInventory(); slotIndex++) {
            if(baubles.getStackInSlot(slotIndex) == null && baubles.isItemValidForSlot(slotIndex, itemStackIn)) {
                if(!worldIn.isRemote) {
                    baubles.setInventorySlotContents(slotIndex, itemStackIn.copy());
                    if(!player.capabilities.isCreativeMode) {
                        itemStackIn.stackSize = 0;
                        //Work around the event being fired twice in single player before the return stack is set.
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStackIn);
                    }
                    ((IBaubleExpanded)itemStackIn.getItem()).onEquipped(itemStackIn, player);
                    return itemStackIn;
                }
            }
        }
        return itemStackIn;
    }

}
