package com.gildedgames.the_aether.client.renders.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.gildedgames.the_aether.entities.hostile.EntityUligo;

@SideOnly(Side.CLIENT)
public class UligoRenderer extends RenderLiving {
    private static final ResourceLocation slimeTextures = new ResourceLocation("aether_legacy", "textures/entities/uligo/uligo.png");
    private ModelBase scaleAmount;

    public UligoRenderer(ModelBase modelBase, ModelBase modelBase2, float p_i1267_3_) {
        super(modelBase, p_i1267_3_);
        this.scaleAmount = modelBase2;
    }

    protected int shouldRenderPass(EntityUligo entityUligo, int p_77032_2_, float p_77032_3_) {
        if (entityUligo.isInvisible()) {
            return 0;
        }
        else if (p_77032_2_ == 0) {
            this.setRenderPassModel(this.scaleAmount);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        else {
            if (p_77032_2_ == 1) {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
    }

    protected void preRenderCallback(EntityUligo entityUligo, float p_77041_2_) {
    	float f1 = (float)entityUligo.getSlimeSize();
        float f2 = (entityUligo.prevSquishFactor + (entityUligo.squishFactor - entityUligo.prevSquishFactor) * p_77041_2_) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    protected ResourceLocation getEntityTexture(EntityUligo entityUligo) {
        return slimeTextures;
    }

    protected void preRenderCallback(EntityLivingBase entityLivingBase, float p_77041_2_) {
        this.preRenderCallback((EntityUligo)entityLivingBase, p_77041_2_);
    }

    protected int shouldRenderPass(EntityLivingBase entityLivingBase, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((EntityUligo)entityLivingBase, p_77032_2_, p_77032_3_);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityUligo)entity);
    }
}
