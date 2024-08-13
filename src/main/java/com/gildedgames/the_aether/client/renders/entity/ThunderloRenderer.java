package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderLiving;

public class ThunderloRenderer extends RenderLiving
{
    private static final ResourceLocation TEXTURE;
    private static final ResourceLocation TEXTURE_TAIL;
    
    public ThunderloRenderer(final ModelBase modelbase, final float f) {
        super(modelbase, f);
        this.setRenderPassModel(modelbase);
    }
    
    protected int a(final EntityLivingBase entityliving, final int i, final float f) {
        if (i != 0) {
            return -1;
        }
        this.renderManager.renderEngine.bindTexture(ThunderloRenderer.TEXTURE_TAIL);
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        GL11.glDepthMask(false);
        final char var5 = '\uf0f0';
        final int var6 = var5 % 65536;
        final int var7 = var5 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6, var7);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        return 1;
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase dragon, float partialTickTime)
    {
        GL11.glScalef(1.3F, 1.3F, 1.3F);
    }
    
    protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.a(entityliving, i, f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return ThunderloRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/thunderlo/thunderlo.png");
        TEXTURE_TAIL = new ResourceLocation("aether_legacy", "textures/entities/thunderlo/thunderlo_tail.png");
    }
}
