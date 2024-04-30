package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.client.models.entities.ZojzModel;
import com.gildedgames.the_aether.entities.hostile.EntityZojz;

import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

public class ZojzRenderer extends RenderLiving
{
    private static final ResourceLocation TEXTURE;
    private static final ResourceLocation TEXTURE_GLOW;
    public ZojzModel zojzModel;
    
    public ZojzRenderer(final ZojzModel model) {
        super((ModelBase)model, 0.5f);
        this.setRenderPassModel((ModelBase)model);
        this.zojzModel = model;
    }
    
    protected void preRenderCallback(final EntityLivingBase entityliving, final float f) {
        final EntityZojz zojz = (EntityZojz)entityliving;
        float f2 = (float)Math.sin(zojz.sinage);
        float f3;
        if (zojz.hurtTime > 0) {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float)Math.sin(zojz.sinage + 2.0f) * 1.5f;
        }
        else {
            f2 *= 0.25f;
            f3 = 1.75f + (float)Math.sin(zojz.sinage + 2.0f) * 1.5f;
        }
        this.zojzModel.sinage = f2;
        this.zojzModel.sinage2 = f3;
        this.shadowSize = 0.75f;
        GL11.glScalef(1.55f, 1.55f, 1.55f);
    }
    
    protected int setMarkingBrightness(final EntityZojz zojz, final int i, final float f) {
        if (i != 0) {
            return -1;
        }
        this.renderManager.renderEngine.bindTexture(ZojzRenderer.TEXTURE_GLOW);
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
        return this.setMarkingBrightness((EntityZojz)entityliving, i, f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return ZojzRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/zojz/zojz.png");
        TEXTURE_GLOW = new ResourceLocation("aether_legacy", "textures/entities/zojz/zojz_overlay.png");
    }
}
