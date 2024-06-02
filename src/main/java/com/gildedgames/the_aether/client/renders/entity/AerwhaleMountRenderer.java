package com.gildedgames.the_aether.client.renders.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.client.models.entities.AerwhaleMountModel;
import com.gildedgames.the_aether.client.models.entities.OldAerwhaleModel;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerwhaleMount;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AerwhaleMountRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/aerwhale/aerwhale.png");

    private static final ResourceLocation TEXTURE_SADDLE = Aether.locate("textures/entities/aerwhale_mount/aerwhale_saddle.png");
    
    private static final ResourceLocation OLD_AERWHALE_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/aerwhale/old_aerwhale.png");

    private final AerwhaleMountModel saddleModel = new AerwhaleMountModel();

    public AerwhaleMountRenderer()
    {
        super(AetherConfig.oldMobsEnabled() ? new OldAerwhaleModel() : new AerwhaleMountModel(), 0.5F);
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase aerwhale, float partialTickTime)
    {
    	GL11.glTranslated(0, 1.2D, 0);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected int renderLayers(EntityAerwhaleMount entity, int pass, float particleTicks) {
        if (entity.isInvisible()) {
            return 0;
        } else if (pass == 1 && entity.isSaddled()) {
            this.setRenderPassModel(this.saddleModel);
            this.bindTexture(TEXTURE_SADDLE);

            return 1;
        } else if (pass == 1 && entity.isSaddled()) {
            this.setRenderPassModel(this.saddleModel);
            this.bindTexture(TEXTURE_SADDLE);

            return 1;
        }

        return -1;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float particleTicks) {
        return this.renderLayers((EntityAerwhaleMount) entity, pass, particleTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

}
