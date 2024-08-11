package com.gildedgames.the_aether.blocks.elysian_totem;

import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.tileentity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class TileEntityElysianTotemRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation TEXTURE_TOTEM;
    private static final ResourceLocation TEXTURE_TOTEM_GLOW;
    private ElysianTotemModel totemModel;
    
    public TileEntityElysianTotemRenderer() {
        this.totemModel = new ElysianTotemModel();
    }
    
    public void renderTileEntityAt(final TileEntity tileentity, final double d, final double d1, final double d2, final float f) {
        this.renderTileEntityAltarAt((TileEntityElysianTotem)tileentity, d, d1, d2, f);
    }
    
    public void renderTileEntityAltarAt(final TileEntityElysianTotem totem, final double d, final double d1, final double d2, final float f) {
        final ElysianTotemModel modelTotem = this.totemModel;
        modelTotem.Shape6.rotateAngleY = totem.getTotemRotation();
        modelTotem.Shape8.rotateAngleY = totem.getTotemRotation();
        modelTotem.Shape9.rotateAngleY = totem.getTotemRotation();
        modelTotem.Shape10.rotateAngleY = totem.getTotemRotation();
        modelTotem.Shape11.rotateAngleY = totem.getTotemRotation();
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glTranslatef((float)d + 0.5f, (float)d1 + 1.5f, (float)d2 + 0.5f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 1.0f);
        this.bindTexture(TileEntityElysianTotemRenderer.TEXTURE_TOTEM);
        modelTotem.renderAll(0.0625f);
        this.bindTexture(TileEntityElysianTotemRenderer.TEXTURE_TOTEM_GLOW);
        final float var4 = 1.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        GL11.glDepthMask(true);
        final char var5 = '\uf0f0';
        final int var6 = var5 % 65536;
        final int var7 = var5 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6, var7);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, var4);
        final float s = 1.01f;
        GL11.glScalef(s, s, s);
        modelTotem.renderAll(0.0625f);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    static {
        TEXTURE_TOTEM = new ResourceLocation("aether_legacy", "textures/tile_entities/elysian_totem.png");
        TEXTURE_TOTEM_GLOW = new ResourceLocation("aether_legacy", "textures/tile_entities/elysian_totem_glow.png");
    }
}
