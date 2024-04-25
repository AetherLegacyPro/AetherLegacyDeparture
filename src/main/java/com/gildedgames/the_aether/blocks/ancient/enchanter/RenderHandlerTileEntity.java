package com.gildedgames.the_aether.blocks.ancient.enchanter;

import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.world.*;

@SideOnly(Side.CLIENT)
public class RenderHandlerTileEntity implements ISimpleBlockRenderingHandler
{
    public TileEntity tileEntity;
    public int renderID;
    public float scale;
    public float yOffset;
    public float yAngle;
    
    public RenderHandlerTileEntity(final TileEntity tileEntity, final int renderID) {
        this.tileEntity = tileEntity;
        this.renderID = renderID;
        this.scale = 1.0f;
    }
    
    public RenderHandlerTileEntity(final TileEntity tileEntity, final int renderID, final float scale) {
        this.tileEntity = tileEntity;
        this.renderID = renderID;
        this.scale = scale;
    }
    
    public RenderHandlerTileEntity setYAngle(final float yAngle) {
        this.yAngle = yAngle;
        return this;
    }
    
    public RenderHandlerTileEntity setYOffset(final float yOffset) {
        this.yOffset = yOffset;
        return this;
    }
    
    public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
        if (modelID == this.getRenderId()) {
            GL11.glPushMatrix();
            GL11.glRotatef(this.yAngle, 0.0f, 1.0f, 0.0f);
            GL11.glScalef(this.scale, this.scale, this.scale);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileEntity, 0.0, -0.1 + this.yOffset, 0.0, 0.0f);
            GL11.glPopMatrix();
        }
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        return false;
    }
    
    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }
    
    public int getRenderId() {
        return this.renderID;
    }
}

