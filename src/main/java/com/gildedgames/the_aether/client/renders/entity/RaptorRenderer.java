package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.RaptorModel;
import com.gildedgames.the_aether.entities.hostile.EntityRaptor;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RaptorRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/raptor/raptor.png");

    public RaptorRenderer() {
        super(new RaptorModel(), 1.0F);
    }

    protected float getWingRotation(EntityRaptor raptor, float f) {
        float f1 = raptor.prevWingRotation + (raptor.wingRotation - raptor.prevWingRotation) * f;
        float f2 = raptor.prevDestPos + (raptor.destPos - raptor.prevDestPos) * f;

        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase raptor, float f) {
        return this.getWingRotation((EntityRaptor) raptor, f);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase raptor, float f) {
        GL11.glScalef(1.2F, 1.2F, 1.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity raptor) {
        return TEXTURE;
    }

}