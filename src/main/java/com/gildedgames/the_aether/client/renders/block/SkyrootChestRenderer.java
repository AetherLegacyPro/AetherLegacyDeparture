package com.gildedgames.the_aether.client.renders.block;

import cpw.mods.fml.client.registry.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;

import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;

public class SkyrootChestRenderer implements ISimpleBlockRenderingHandler
{
    public TileEntitySkyrootChest tileEntity;
    
    public SkyrootChestRenderer(final TileEntitySkyrootChest tileEntity) {
        this.tileEntity = tileEntity;
    }
    
    public void renderInventoryBlock(final Block block, final int metadata, final int modelId, final RenderBlocks renderer) {
        if (modelId == this.getRenderId()) {
            GL11.glPushMatrix();
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileEntity, 0.0, 0.0, 0.0, 0.0f);
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            GL11.glPopMatrix();
        }
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        return modelId == this.getRenderId();
    }
    
    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }
    
    public int getRenderId() {
        return BlocksAether.SkyrootChestRenderId;
    }
}
