package com.gildedgames.the_aether.client.renders;

import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.blocks.ancient.enchanter.AncientEnchanterModel;
import com.gildedgames.the_aether.tileentity.TileEntityAncientEnchanter;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;

public class TileEntityAncientEnchanterRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation TEXTURE_ANCIENT_ENCHANTER;
    private AncientEnchanterModel altarModel;
    private double radius;
    private double theta;
    private double alpha;
    
    public TileEntityAncientEnchanterRenderer() {
        this.radius = 1.5;
        this.theta = 5.0;
        this.alpha = 0.0;
        this.altarModel = new AncientEnchanterModel();
    }
    
    public void renderTileEntityAt(final TileEntity tileentity, final double d, final double d1, final double d2, final float f) {
        this.renderTileEntityAncientEnchanterAt((TileEntityAncientEnchanter)tileentity, d, d1, d2, f);
    }
    
    public void renderTileEntityAncientEnchanterAt(final TileEntityAncientEnchanter enchanter, final double d, final double d1, final double d2, final float f) {
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        final AncientEnchanterModel modelaltar = this.altarModel;
        this.bindTexture(TileEntityAncientEnchanterRenderer.TEXTURE_ANCIENT_ENCHANTER);
        GL11.glTranslatef((float)d + 0.5f, (float)d1 + 1.5f, (float)d2 + 0.5f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 1.0f);
        int meta = 2;
        if (enchanter.getWorldObj() != null) {
            meta = enchanter.getBlockMetadata();
        }
        if (meta != 2) {
            if (meta == 3) {
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            }
            else if (meta == 4) {
                GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
            }
            else if (meta == 5) {
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            }
        }
        modelaltar.AmbrosiumBottomLeft.rotateAngleY = enchanter.getAmbRotation();
        modelaltar.AmbrosiumBottomRight.rotateAngleY = enchanter.getAmbRotation();
        modelaltar.AmbrosiumTopLeft.rotateAngleY = enchanter.getAmbRotation();
        modelaltar.AmbrosiumTopRight.rotateAngleY = enchanter.getAmbRotation();
        modelaltar.renderAll(0.0625f);
        GL11.glPopMatrix();
        if (enchanter.getEnchanterStacks(1) != null) {
            final Item item = enchanter.getEnchanterStacks(1).getItem();
            final IIcon var15 = item.getIconFromDamage(enchanter.getEnchanterStacks(1).getItemDamage());
            if (item.getSpriteNumber() > 0) {
                this.bindTexture(TextureMap.locationItemsTexture);
            }
            else {
                this.bindTexture(TextureMap.locationBlocksTexture);
            }
            final int amount = enchanter.getEnchanterStacks(1).stackSize;
            GL11.glPushMatrix();
            GL11.glEnable(32826);
            GL11.glTranslatef((float)d + 0.5f, (float)d1 + 1.25f, (float)d2 + 0.5f);
            GL11.glScalef(0.2f, 0.2f, 0.2f);
            this.renderOrbitItem(var15, amount, enchanter.getAmbSpinning());
            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }
    }
    
    private void renderOrbitItem(final IIcon icon, final int amount, final double interval) {
        for (int i = 0; i < amount; ++i) {
            GL11.glPushMatrix();
            final Tessellator var3 = Tessellator.instance;
            final float var4 = icon.getMinU();
            final float var5 = icon.getMaxU();
            final float var6 = icon.getMinV();
            final float var7 = icon.getMaxV();
            final float var8 = 1.0f;
            final float var9 = 0.5f;
            final float var10 = 0.25f;
            final double dist = 3.141592653589793 * i / amount * 2.0;
            final double x = this.radius * Math.cos(this.theta + dist);
            final double y = 0.0;
            final double z = this.radius * Math.sin(this.theta + dist);
            final double deltaX = z * Math.cos(this.alpha) - x * Math.sin(this.alpha);
            final double deltaZ = x * Math.cos(this.alpha) + z * Math.sin(this.alpha);
            GL11.glTranslatef((float)deltaX, (float)y, (float)deltaZ);
            this.alpha += interval / 100.0;
            GL11.glRotatef(180.0f + RenderManager.instance.playerViewY, 0.0f, -1.0f, 0.0f);
            var3.startDrawingQuads();
            var3.setNormal(0.0f, 1.0f, 0.0f);
            var3.addVertexWithUV((double)(0.0f - var9), (double)(0.0f - var10), 0.0, (double)var4, (double)var7);
            var3.addVertexWithUV((double)(var8 - var9), (double)(0.0f - var10), 0.0, (double)var5, (double)var7);
            var3.addVertexWithUV((double)(var8 - var9), (double)(1.0f - var10), 0.0, (double)var5, (double)var6);
            var3.addVertexWithUV((double)(0.0f - var9), (double)(1.0f - var10), 0.0, (double)var4, (double)var6);
            var3.draw();
            GL11.glPopMatrix();
        }
    }
    
    static {
    	TEXTURE_ANCIENT_ENCHANTER = new ResourceLocation("aether_legacy", "textures/tile_entities/altar.png");
    }
}

