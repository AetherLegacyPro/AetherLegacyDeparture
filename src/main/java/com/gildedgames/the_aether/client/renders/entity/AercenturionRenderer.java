package com.gildedgames.the_aether.client.renders.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.client.models.entities.AercenturionModel;
import com.gildedgames.the_aether.entities.hostile.EntityAercenturion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AercenturionRenderer extends RenderBiped {

    private static final ResourceLocation Aercenturion;
	private static final ResourceLocation TEXTURE_GLOW;
	private AercenturionModel aerModel;
	
	public AercenturionRenderer(final ModelBiped model, final float f) {
        super(model, f);
        this.setRenderPassModel(model);
    }
    
    protected int setMarkingBrightness(final EntityAercenturion golem, final int i, final float f) {
        if (i != 0) {
            return -1;
        }
        
        this.renderManager.renderEngine.bindTexture(AercenturionRenderer.TEXTURE_GLOW);
        final float var4 = 1.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        if (!golem.getActivePotionEffects().isEmpty()) {
            GL11.glDepthMask(false);
        }
        else {
            GL11.glDepthMask(true);
        }
        final char var5 = '\uf0f0';
        final int var6 = var5 % 65536;
        final int var7 = var5 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0f, var7 / 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, var4);
        return 1;
    }
    
    protected int shouldRenderPass(final EntityLivingBase entityliving, final int i, final float f) {
        return this.setMarkingBrightness((EntityAercenturion)entityliving, i, f);
    }
	 
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	return AercenturionRenderer.Aercenturion;
	        
	}
	
	static {
		Aercenturion = new ResourceLocation("aether_legacy", "textures/entities/aercenturion/aercenturion.png");
		TEXTURE_GLOW = new ResourceLocation("aether_legacy", "textures/entities/aercenturion/eyes.png");
    }

}
