package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.AercenturionModel;
import com.gildedgames.the_aether.entities.hostile.EntityAercenturion;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class AercenturionRenderer extends RenderBiped {

    private static final ResourceLocation Aercenturion = Aether.locate("textures/entities/aercenturion/aercenturion.png");

    public AercenturionRenderer() {
        super(new AercenturionModel(), 0.4F);
        this.shadowSize = 0.8F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    	EntityAercenturion sunSpirit = (EntityAercenturion) entity;
        
            return Aercenturion;
        
    }

}
