package com.gildedgames.the_aether.client.renders.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.client.models.entities.ElysianGuardianModel;
import com.gildedgames.the_aether.entities.bosses.EntityElysianGuardian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class ElysianGuardianRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE;
    private static final ResourceLocation TEXTURE_GLOW;

    public ElysianGuardianModel elysianModel;
    
    public ElysianGuardianRenderer(final ElysianGuardianModel model) {
        super(model, 0.5f);
        this.setRenderPassModel(model);
        this.elysianModel = model;
    }
    
    protected void preRenderCallback(final EntityLivingBase entityliving, final float f) {
        final EntityElysianGuardian elysian = (EntityElysianGuardian)entityliving;
        float f2 = (float)Math.sin(elysian.sinage);
        float f3;
        if (elysian.hurtTime > 0) {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float)Math.sin(elysian.sinage + 2.0f) * 1.5f;
        }
        else {
            f2 *= 0.25f;
            f3 = 1.75f + (float)Math.sin(elysian.sinage + 2.0f) * 1.5f;
        }
        this.shadowSize = 0.75f;
        //GL11.glScalef(1.5f, 1.5f, 1.5f);
    }
    
    protected int setMarkingBrightness(final EntityElysianGuardian elysian, final int i, final float f) {
        if (i != 0) {
            return -1;
        }
        this.renderManager.renderEngine.bindTexture(ElysianGuardianRenderer.TEXTURE_GLOW);
        final float var4 = 1.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        GL11.glDepthMask(false);
        final char var5 = '\uf0f0';
        final int var6 = var5 % 65536;
        final int var7 = var5 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6, var7);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, var4);
        return 1;
    }
    
    protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.setMarkingBrightness((EntityElysianGuardian)entityliving, i, f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return ElysianGuardianRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/elysian_guardian/elysian_guardian.png");
        TEXTURE_GLOW = new ResourceLocation("aether_legacy", "textures/entities/elysian_guardian/elysian_guardian_overlay.png");
    }
}
