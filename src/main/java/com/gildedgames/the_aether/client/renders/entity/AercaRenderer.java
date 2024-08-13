package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.AercaModel;
import com.gildedgames.the_aether.entities.hostile.EntityAerca;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;

public class AercaRenderer extends RenderLiving
{
	private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/aerca/aerca.png");
	private static final ResourceLocation TEXTURE_GLOW;
    
	public AercaRenderer() {
        super(new AercaModel(), 0.5F);
        this.shadowSize = 1.0F;
        this.setRenderPassModel(new AercaModel());
    }
	
	protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.shouldRenderPass((EntityAerca)entityliving, i, f);
    }
	
	protected int shouldRenderPass(final EntityAerca aerca, final int par2, final float par3) {
        if (par2 != 0) {
            return -1;
        }
        {
        this.bindTexture(AercaRenderer.TEXTURE_GLOW);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(1, 1);
        if (aerca.isInvisible()) {
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
	 protected void preRenderCallback(EntityLivingBase raptor, float f) {
	    GL11.glScalef(0.8F, 0.8F, 0.8F);
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {   	
    	return AercaRenderer.TEXTURE;
       
    }
	
	 public void doRender(EntityAerca p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {        

	        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	 }

	 public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
	        this.doRender((EntityAerca)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	 }
	 
	 public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
	        this.doRender((EntityAerca)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	 }
	 
	 public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
	        this.doRender((EntityAerca)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	 }
    
    static {
    	TEXTURE_GLOW = new ResourceLocation("aether_legacy", "textures/entities/aerca/aerca_overlay.png");
	    
    }
}
