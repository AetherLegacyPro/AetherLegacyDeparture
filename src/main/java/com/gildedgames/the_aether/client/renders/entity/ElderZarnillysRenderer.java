package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.ElderZarnillysModel;
import com.gildedgames.the_aether.entities.bosses.EntityElderZarnillys;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderLiving;

public class ElderZarnillysRenderer extends RenderLiving
{
	private static final ResourceLocation TEXTURE = Aether.locate("textures/entities/zarnillys/elder_zarnillys.png");
    
	public ElderZarnillysRenderer() {
        super(new ElderZarnillysModel(), 0.5F);
        this.shadowSize = 1.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    	EntityElderZarnillys zarnillys = (EntityElderZarnillys) entity;
        
            return TEXTURE;
        
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase raptor, float f) {
        GL11.glScalef(1.25F, 1.25F, 1.25F);
    }
}
