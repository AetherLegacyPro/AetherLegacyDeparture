package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;

public class TempestModel extends ModelBase
{
    ModelRenderer main_body;
    ModelRenderer RB_cloud;
    ModelRenderer LB_cloud;
    ModelRenderer tail_2;
    ModelRenderer FR_cloud;
    ModelRenderer FL_cloud;
    ModelRenderer tail_1;
    public float sinage;
    public float sinage2;
    
    public TempestModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.main_body = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -4.0f, -7.0f, 12, 8, 14);
        this.main_body.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.main_body.setTextureSize(64, 64);
        this.main_body.mirror = true;
        this.setRotation(this.main_body, 0.0f, 0.0f, 0.0f);
        (this.RB_cloud = new ModelRenderer(this, 16, 22)).addBox(-8.0f, -2.0f, 0.0f, 2, 6, 6);
        this.RB_cloud.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.RB_cloud.setTextureSize(64, 64);
        this.RB_cloud.mirror = true;
        this.setRotation(this.RB_cloud, 0.0f, 0.0f, 0.0f);
        (this.LB_cloud = new ModelRenderer(this, 16, 22)).addBox(6.0f, -2.0f, 0.0f, 2, 6, 6);
        this.LB_cloud.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.LB_cloud.setTextureSize(64, 64);
        this.LB_cloud.mirror = true;
        this.setRotation(this.LB_cloud, 0.0f, 0.0f, 0.0f);
        this.LB_cloud.mirror = false;
        (this.tail_2 = new ModelRenderer(this, 32, 22)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
        this.tail_2.setRotationPoint(0.0f, 10.0f, 19.0f);
        this.tail_2.setTextureSize(64, 64);
        this.tail_2.mirror = true;
        this.setRotation(this.tail_2, 0.0f, 0.0f, 0.0f);
        (this.FR_cloud = new ModelRenderer(this, 0, 22)).addBox(-8.0f, -3.0f, -7.0f, 2, 6, 6);
        this.FR_cloud.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.FR_cloud.setTextureSize(64, 64);
        this.FR_cloud.mirror = true;
        this.setRotation(this.FR_cloud, 0.0f, 0.0f, 0.0f);
        (this.FL_cloud = new ModelRenderer(this, 0, 22)).addBox(6.0f, -3.0f, -7.0f, 2, 6, 6);
        this.FL_cloud.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.FL_cloud.setTextureSize(64, 64);
        this.FL_cloud.mirror = true;
        this.setRotation(this.FL_cloud, 0.0f, 0.0f, 0.0f);
        this.FL_cloud.mirror = false;
        (this.tail_1 = new ModelRenderer(this, 0, 34)).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        this.tail_1.setRotationPoint(0.0f, 10.0f, 12.0f);
        this.tail_1.setTextureSize(64, 64);
        this.tail_1.mirror = true;
        this.setRotation(this.tail_1, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.main_body.render(f5);
        this.RB_cloud.render(f5);
        this.LB_cloud.render(f5);
        this.FR_cloud.render(f5);
        this.FL_cloud.render(f5);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        this.tail_1.render(f5);
        this.tail_2.render(f5);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public void renderTransparentTail(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.tail_1.render(f5);
        this.tail_2.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        final float boff = this.sinage2;
        final float yOffset = 7.5f;
        final float vertMotion = (float)(Math.sin(f * 20.0f / 57.2957795) * this.sinage * 0.5);
        final float PI = 3.1415927f;
        final float initialOffset = PI / 2.0f;
        this.FR_cloud.rotationPointY = vertMotion + 10.0f;
        this.FL_cloud.rotationPointX = vertMotion * 0.5f;
        this.LB_cloud.rotationPointY = 8.0f - vertMotion * 0.5f;
        this.RB_cloud.rotationPointY = 9.0f + vertMotion * 0.5f;
        this.tail_1.rotationPointX = (float)(Math.sin(f * 20.0f / 57.2957795) * f1 * 0.75);
        this.tail_1.rotateAngleY = (float)Math.pow(0.9900000095367432, -4.0) * 1.0f * PI / 4.0f * MathHelper.cos(-0.055f * f + initialOffset);
        this.tail_1.rotationPointY = 10.0f - vertMotion;
        this.tail_2.rotationPointX = (float)Math.pow(0.9900000095367432, 1.0) * 1.0f * PI / 4.0f * MathHelper.cos(-0.055f * f + initialOffset);
        this.tail_2.rotationPointY = 10.0f - vertMotion * 1.25f;
        this.tail_2.rotateAngleY = this.tail_1.rotateAngleY + 0.25f;
        this.main_body.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.RB_cloud.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.LB_cloud.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.tail_2.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.FR_cloud.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.FL_cloud.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.tail_1.rotationPointY = boff + yOffset + this.sinage * 2.0f;
    }
}