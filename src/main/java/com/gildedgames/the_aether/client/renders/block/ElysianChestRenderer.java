package com.gildedgames.the_aether.client.renders.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.tileentity.TileEntityElysianChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ElysianChestRenderer implements ISimpleBlockRenderingHandler {

    public TileEntityElysianChest tileEntity;

    public ElysianChestRenderer(final TileEntityElysianChest tileEntity) {
        this.tileEntity = tileEntity;
    }

    public void renderInventoryBlock(final Block block, final int metadata, final int modelId,
        final RenderBlocks renderer) {
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

    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block,
        final int modelId, final RenderBlocks renderer) {
        return modelId == this.getRenderId();
    }

    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }

    public int getRenderId() {
        return BlocksAether.ElysianChestRenderId;
    }
}
