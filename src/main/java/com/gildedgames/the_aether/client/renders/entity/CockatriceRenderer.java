package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.CockatriceModel;
import com.gildedgames.the_aether.entities.hostile.EntityCockatrice;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class CockatriceRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/cockatrice/cockatrice.png");
    private static final ResourceLocation GLOW;

    public CockatriceRenderer() {
        super(new CockatriceModel(), 1.0F);
        this.setRenderPassModel(new CockatriceModel());
    }

    protected float getWingRotation(EntityCockatrice cockatrice, float f) {
        float f1 = cockatrice.prevWingRotation + (cockatrice.wingRotation - cockatrice.prevWingRotation) * f;
        float f2 = cockatrice.prevDestPos + (cockatrice.destPos - cockatrice.prevDestPos) * f;

        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase cockatrice, float f) {
        return this.getWingRotation((EntityCockatrice) cockatrice, f);
    }
    
    protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.shouldRenderPass((EntityCockatrice)entityliving, i, f);
    }
	
	protected int shouldRenderPass(final EntityCockatrice cockatrice, final int par2, final float par3) {
        if (par2 != 0) {
            return -1;
        }
        {
        this.bindTexture(CockatriceRenderer.GLOW);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(1, 1);
        if (cockatrice.isInvisible()) {
            GL11.glDepthMask(false);
        }
        else {
            GL11.glDepthMask(true);
        }
        final char c0 = '\uf0f0';
        final int j = 61680;
        final int k = 0;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        return 1;
        }
    }

    @Override
    protected void preRenderCallback(EntityLivingBase cockatrice, float f) {
        GL11.glScalef(1.8F, 1.8F, 1.8F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity cockatrice) {
        return TEXTURE;
    }
    
    public void doRender(EntityCockatrice p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {        

        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCockatrice)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
 
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCockatrice)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
 
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    	this.doRender((EntityCockatrice)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    static {
    	GLOW = new ResourceLocation("aether_legacy", "textures/entities/cockatrice/cockatrice_glow.png");
	    
    }

}