package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.client.models.entities.CarrionSproutModel;
import com.gildedgames.the_aether.entities.passive.EntityCarrionSprout;

import net.minecraft.entity.*;

public class CarrionSproutRenderer extends RenderLiving
{
    public static final ResourceLocation TEXTURE;
    public CarrionSproutModel plantModel;
    
    public CarrionSproutRenderer() {
    	super(new CarrionSproutModel(), 0.5F);
        this.plantModel = ((CarrionSproutModel) this.mainModel);
        this.setRenderPassModel(this.mainModel);
    }
    
    @Override
    protected void preRenderCallback(final EntityLivingBase entityliving, final float f) {
        final EntityCarrionSprout sprout = (EntityCarrionSprout)entityliving;
        //GL11.glColor3f(sprout.getSproutSize(), 1.0f, sprout.getSproutSize());
        //GL11.glScalef(sprout.getSproutSize(), sprout.getSproutSize(), sprout.getSproutSize());
        float f2 = (float)Math.sin(sprout.sinage);
        float f3;
        if (sprout.hurtTime > 0) {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float)Math.sin(sprout.sinage + 2.0f) * 1.5f;
        }
        else {
            f2 *= 0.25f;
            f3 = 1.75f + (float)Math.sin(sprout.sinage + 2.0f) * 1.5f;
        }
        this.plantModel.sinage = f2;
        this.plantModel.sinage2 = f3;
        this.shadowSize = 0.25f;
    }
    
    protected int doCarrionPlantRender(EntityCarrionSprout entitycarrionsprout, int i, float f) {
        if (i != 0) {
            return -1;
        } else {
            return 1;
        }
    }
    
    @Override
    protected int shouldRenderPass(EntityLivingBase entityliving, int i, float f) {
        return this.doCarrionPlantRender((EntityCarrionSprout) entityliving, i, f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return CarrionSproutRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether", "textures/entities/carrion_sprout/carrion_sprout.png");
    }
}
