package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.CyroModel;
import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class CryoRenderer extends RenderLiving {
    private static final ResourceLocation cyroTextures = Aether.locate("textures/entities/cyro/cyro.png");
    private int field_77068_a;

    public CryoRenderer() {
        super(new CyroModel(), 0.5F);
        this.field_77068_a = ((CyroModel)this.mainModel).func_78104_a();
    }

    public void doRender(EntityCyro entityCyro, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        int i = ((CyroModel)this.mainModel).func_78104_a();

        if (i != this.field_77068_a) {
            this.field_77068_a = i;
            this.mainModel = new CyroModel();
        }

        super.doRender(entityCyro, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(EntityCyro entityCyro) {
        return cyroTextures;
    }

    public void doRender(EntityLiving entityLiving, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCyro)entityLiving, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityLivingBase entityLivingBase, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCyro)entityLivingBase, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityCyro)entity);
    }

    public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityCyro)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
