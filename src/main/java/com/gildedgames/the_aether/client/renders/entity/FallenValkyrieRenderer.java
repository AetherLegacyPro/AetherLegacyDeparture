package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.FallenValkyrieModel;
import com.gildedgames.the_aether.entities.bosses.EntityFallenValkyrie;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class FallenValkyrieRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/valkyrie/fallen_valkyrie.png");

    public FallenValkyrieRenderer() {
        super(new FallenValkyrieModel(), 0.3F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase valkyrie, float partialTickTime) {
        ((FallenValkyrieModel) this.mainModel).sinage = ((EntityFallenValkyrie) valkyrie).sinage;
        ((FallenValkyrieModel) this.mainModel).gonRound = valkyrie.onGround;
        ((FallenValkyrieModel) this.mainModel).halow = true;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

}