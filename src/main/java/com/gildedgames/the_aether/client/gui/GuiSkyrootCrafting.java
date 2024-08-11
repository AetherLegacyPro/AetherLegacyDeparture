package com.gildedgames.the_aether.client.gui;

import net.minecraft.client.gui.inventory.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.inventory.ContainerSkyrootWorkbench;

@SideOnly(Side.CLIENT)
public class GuiSkyrootCrafting extends GuiContainer
{
    private static final ResourceLocation TEXTURE_CRAFTING;
    
    public GuiSkyrootCrafting(final InventoryPlayer par1InventoryPlayer, final World par2World, final int par3, final int par4, final int par5) {
        super(new ContainerSkyrootWorkbench(par1InventoryPlayer, par2World, par3, par4, par5));
    }
    
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(GuiSkyrootCrafting.TEXTURE_CRAFTING);
        final int k = (this.width - this.xSize) / 2;
        final int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
    
    static {
        TEXTURE_CRAFTING = new ResourceLocation("textures/gui/container/crafting_table.png");
    }
}