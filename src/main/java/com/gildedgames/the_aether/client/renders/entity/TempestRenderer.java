package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.client.models.entities.TempestModel;
import com.gildedgames.the_aether.entities.hostile.EntityTempest;

import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

public class TempestRenderer extends RenderLiving
{
    private static final ResourceLocation TEXTURE;
    private static final ResourceLocation TEXTURE_GLOW;
    public TempestModel tempestModel;
    
    public TempestRenderer(final TempestModel model) {
        super((ModelBase)model, 0.5f);
        this.setRenderPassModel((ModelBase)model);
        this.tempestModel = model;
    }
    
    protected void preRenderCallback(final EntityLivingBase entityliving, final float f) {
        final EntityTempest tempest = (EntityTempest)entityliving;
        float f2 = (float)Math.sin(tempest.sinage);
        float f3;
        if (tempest.hurtTime > 0) {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float)Math.sin(tempest.sinage + 2.0f) * 1.5f;
        }
        else {
            f2 *= 0.25f;
            f3 = 1.75f + (float)Math.sin(tempest.sinage + 2.0f) * 1.5f;
        }
        this.tempestModel.sinage = f2;
        this.tempestModel.sinage2 = f3;
        this.shadowSize = 0.75f;
        GL11.glScalef(1.5f, 1.5f, 1.5f);
    }
    
    protected int setMarkingBrightness(final EntityTempest tempest, final int i, final float f) {
        if (i != 0) {
            return -1;
        }
        this.renderManager.renderEngine.bindTexture(TempestRenderer.TEXTURE_GLOW);
        final float var4 = 1.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        GL11.glDepthMask(false);
        final char var5 = '\uf0f0';
        final int var6 = var5 % 65536;
        final int var7 = var5 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0f, var7 / 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, var4);
        return 1;
    }
    
    protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.setMarkingBrightness((EntityTempest)entityliving, i, f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return TempestRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/tempest/tempest.png");
        TEXTURE_GLOW = new ResourceLocation("aether_legacy", "textures/entities/tempest/glow.png");
    }
}