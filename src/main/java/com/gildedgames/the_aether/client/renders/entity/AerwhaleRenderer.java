package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.client.models.entities.AerwhaleModel;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AerwhaleRenderer extends RenderLiving {
    private static final ResourceLocation AERWHALE_TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/aerwhale/aerwhale.png");
    private static final ResourceLocation TEXTURE_SADDLE = new ResourceLocation("aether_legacy","textures/entities/aerwhale/aerwhale_saddle.png");
    private final AerwhaleModel saddleModel = new AerwhaleModel();

    public AerwhaleRenderer() {
        super(new AerwhaleModel(), 0.5F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase aerwhale, float partialTickTime) {
        GL11.glTranslated(0, 1.2D, 0);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected int renderLayers(EntityAerwhale entity, int pass, float particleTicks) {
        if (entity.isInvisible()) {
            return 0;
        }
        else if (pass == 1 && entity.isSaddled()) {
            this.setRenderPassModel(this.saddleModel);
            this.bindTexture(TEXTURE_SADDLE);

            return 1;
        }
        else if (pass == 1 && entity.isSaddled()) {
            this.setRenderPassModel(this.saddleModel);
            this.bindTexture(TEXTURE_SADDLE);

            return 1;
        }

        return -1;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float particleTicks) {
        return this.renderLayers((EntityAerwhale) entity, pass, particleTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity aerwhale) {
        return AERWHALE_TEXTURE;
    }
}
