package com.gildedgames.the_aether.client.renders.entity;

import org.lwjgl.opengl.GL11;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.CinerariumModel;
import com.gildedgames.the_aether.entities.hostile.EntityCinerarium;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class CinerariumRenderer extends RenderLiving {
    private static final ResourceLocation cyroTextures = Aether.locate("textures/entities/cinerarium/cinerarium.png");
    private int field_77068_a;

    public CinerariumRenderer() {
        super(new CinerariumModel(), 0.7F);
        this.field_77068_a = ((CinerariumModel)this.mainModel).func_78104_a();
    }

    public void doRender(EntityCinerarium entityCinerarium, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        int i = ((CinerariumModel)this.mainModel).func_78104_a();

        if (i != this.field_77068_a)
        {
            this.field_77068_a = i;
            this.mainModel = new CinerariumModel();
        }

        super.doRender(entityCinerarium, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(EntityCinerarium entityCinerarium) {
        return cyroTextures;
    }

    public void doRender(EntityLiving entityLiving, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCinerarium)entityLiving, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLivingBase entityLivingBase, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCinerarium)entityLivingBase, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityCinerarium)entity);
    }

    public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCinerarium)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase aerwhale, float partialTickTime) {
        GL11.glScalef(1.08F, 1.08F, 1.08F);
    }
}

