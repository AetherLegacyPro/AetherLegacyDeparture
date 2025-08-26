package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.entity.Entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.ZarnillysModel;
import com.gildedgames.the_aether.entities.hostile.EntityZarnillys;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderLiving;

public class ZarnillysRenderer extends RenderLiving
{
	private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/zarnillys/zarnillys.png");
    
	public ZarnillysRenderer() {
        super(new ZarnillysModel(), 0.5F);
        this.shadowSize = 1.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    	EntityZarnillys zarnillys = (EntityZarnillys) entity;
        
            return TEXTURE;
        
    }
}
