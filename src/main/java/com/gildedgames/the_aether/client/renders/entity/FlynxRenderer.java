package com.gildedgames.the_aether.client.renders.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.FlynxModel;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;

@SideOnly(Side.CLIENT)
public class FlynxRenderer extends RenderLiving {
    private static final ResourceLocation blackOcelotTextures = Aether.locate("textures/entities/flynx/purple.png");
    private static final ResourceLocation ocelotTextures = Aether.locate("textures/entities/flynx/flynx.png");
    private static final ResourceLocation redOcelotTextures = Aether.locate("textures/entities/flynx/green.png");
    private static final ResourceLocation siameseOcelotTextures = Aether.locate("textures/entities/flynx/blue.png");

    public FlynxRenderer() {
        super(new FlynxModel(), 0.8F);
    }

    public void doRender(EntityFlynx entityFlynx, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.doRender(entityFlynx, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(EntityFlynx entityFlynx) {
        switch (entityFlynx.getTameSkin()) {
            case 0:
                return ocelotTextures;
            case 1:
                return blackOcelotTextures;
            case 2:
                return redOcelotTextures;
            case 3:
                return siameseOcelotTextures;
            default:
                return ocelotTextures;
        }
    }

    protected void preRenderCallback(EntityFlynx FlynxModel, float f) {
        super.preRenderCallback(FlynxModel, f);

        if (FlynxModel.isTamed()) {
        	GL11.glScalef(1.05F, 1.05F, 1.05F);
        }
    }

    public void doRender(EntityLiving entityLiving, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityFlynx)entityLiving, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void preRenderCallback(EntityLivingBase entityLivingBase, float p_77041_2_) {
        this.preRenderCallback((EntityFlynx)entityLivingBase, p_77041_2_);
        GL11.glScalef(1.05F, 1.05F, 1.05F);
    }

    public void doRender(EntityLivingBase entityLivingBase, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityFlynx)entityLivingBase, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityFlynx)entity);
    }

    public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityFlynx)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
