package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;

public class CarrionSproutModel extends ModelBase
{
    ModelRenderer TopStem;
    ModelRenderer BottomStem;
    ModelRenderer HeadRoof;
    ModelRenderer Teeth;
    ModelRenderer Jaw;
    ModelRenderer Head;
    private ModelRenderer[] petal;
    private static int petals;
    public float sinage;
    public float sinage2;
    private float pie;
    
    public CarrionSproutModel() {
        this.pie = 6.283186f;
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.petal = new ModelRenderer[CarrionSproutModel.petals];
        for (int i = 0; i < CarrionSproutModel.petals; ++i) {
            this.petal[i] = new ModelRenderer((ModelBase)this, 43, 49);
            if (i % 2 == 0) {
                (this.petal[i] = new ModelRenderer((ModelBase)this, 43, 49)).addBox(-2.8f, -1.0f, -10.8f, 6, 0, 9);
                this.petal[i].setRotationPoint(0.0f, 1.0f, 0.0f);
            }
            else {
                this.petal[i].addBox(-2.8f, -1.0f, -11.8f, 6, 0, 9);
                this.petal[i].setRotationPoint(0.0f, 1.0f, 0.0f);
            }
        }
        (this.Head = new ModelRenderer((ModelBase)this, 0, 0)).addBox(-3.0f, -3.0f, -3.0f, 6, 2, 6);
        this.Head.setRotationPoint(0.0f, 0.0f, 0.0f);
        (this.TopStem = new ModelRenderer((ModelBase)this, 8, 25)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.TopStem.setRotationPoint(-1.0f, 14.0f, -3.0f);
        this.TopStem.setTextureSize(64, 64);
        this.TopStem.mirror = true;
        this.setRotation(this.TopStem, 0.3490659f, 0.0f, 0.0f);
        (this.BottomStem = new ModelRenderer((ModelBase)this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 2, 5, 2);
        this.BottomStem.setRotationPoint(-1.0f, 19.0f, -1.0f);
        this.BottomStem.setTextureSize(64, 64);
        this.BottomStem.mirror = true;
        this.setRotation(this.BottomStem, 0.0f, 0.0f, 0.0f);
        (this.HeadRoof = new ModelRenderer((ModelBase)this, 20, 16)).addBox(0.0f, 0.0f, 0.0f, 11, 5, 11);
        this.HeadRoof.setRotationPoint(-5.5f, 4.0f, -7.5f);
        this.HeadRoof.setTextureSize(64, 64);
        this.HeadRoof.mirror = true;
        this.setRotation(this.HeadRoof, -0.3490659f, 0.0f, 0.0f);
        (this.Teeth = new ModelRenderer((ModelBase)this, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 9, 1, 9);
        this.Teeth.setRotationPoint(-4.5f, 8.5f, -8.5f);
        this.Teeth.setTextureSize(64, 64);
        this.Teeth.mirror = true;
        this.setRotation(this.Teeth, -0.3490659f, 0.0f, 0.0f);
        (this.Jaw = new ModelRenderer((ModelBase)this, 24, 1)).addBox(0.0f, 0.0f, -9.0f, 10, 2, 10);
        this.Jaw.setRotationPoint(-5.0f, 12.0f, 0.0f);
        this.Jaw.setTextureSize(64, 64);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.0f, 0.0f, 0.0f);
    }
    
    @Override
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        for (int i = 0; i < CarrionSproutModel.petals; ++i) {
            this.petal[i].render(f5);
        }
        this.TopStem.render(f5);
        this.BottomStem.render(f5);
        this.HeadRoof.render(f5);
        this.Teeth.render(f5);
        this.Jaw.render(f5);
        this.Head.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.3f, 0.0f);
        this.BottomStem.render(f5);
        GL11.glPopMatrix();
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        final float boff = this.sinage2;
        final float yOffset = 21.0f;
        for (int i = 0; i < CarrionSproutModel.petals; ++i) {
            this.petal[i].rotateAngleX = ((i % 2 == 0) ? -0.25f : -0.4125f);
            final ModelRenderer modelRenderer = this.petal[i];
            modelRenderer.rotateAngleX += this.sinage;
            this.petal[i].rotateAngleY = 17.0f;
            final ModelRenderer modelRenderer2 = this.petal[i];
            modelRenderer2.rotateAngleY += this.pie / CarrionSproutModel.petals * i;
            this.petal[i].rotationPointY = boff + yOffset;
        }
        this.Jaw.rotateAngleX = 0.08f;
        final ModelRenderer jaw = this.Jaw;
        jaw.rotateAngleX += this.sinage;
        this.Head.rotationPointY = boff + yOffset + this.sinage * 2.0f;
        this.Jaw.rotationPointY = boff + 8.0f + this.sinage * 2.0f;
        this.BottomStem.rotationPointY = boff + 15.0f + this.sinage * 2.0f;
        this.TopStem.rotationPointY = boff + 10.0f + this.sinage * 2.0f;
        this.HeadRoof.rotationPointY = boff + this.sinage * 2.0f;
        this.Teeth.rotationPointY = boff + 4.5f + this.sinage * 2.0f;
    }
    
    static {
    	CarrionSproutModel.petals = 8;
    }
}

