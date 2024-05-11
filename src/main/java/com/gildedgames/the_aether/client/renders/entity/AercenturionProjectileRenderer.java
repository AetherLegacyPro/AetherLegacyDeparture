package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gildedgames.the_aether.client.models.entities.AercenturionModel;
import com.gildedgames.the_aether.entities.projectile.EntityAercenturionProjectile;

public class AercenturionProjectileRenderer extends RenderLiving {

	private static final ResourceLocation soundwaveTexture = new ResourceLocation("aether_legacy", "textures/entities/projectile/valkyrie_wave.png");
	
    public AercenturionProjectileRenderer() {
        super(new AercenturionModel(), 0.5f);

        this.shadowSize = 0.0F;
    }

    public void doRenderNotchWave(EntityAercenturionProjectile notchwave, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glTranslated(par2, par4, par6);

        this.bindTexture(this.getEntityTexture(notchwave));

        Tessellator tessellator = Tessellator.instance;

        GL11.glRotatef(180F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-0.5F, -0.25F, 0.0F, 0.0F, 0.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.5F, -0.25F, 0.0F, 0.0F, 1.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.5F, 0.75F, 0.0F, 1.0F, 1.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5F, 0.75F, 0.0F, 1.0F, 0.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.draw();

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderNotchWave((EntityAercenturionProjectile) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return soundwaveTexture;
    }

}
