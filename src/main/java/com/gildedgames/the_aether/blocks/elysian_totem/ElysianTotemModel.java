package com.gildedgames.the_aether.blocks.elysian_totem;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ElysianTotemModel extends ModelBase
{
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
    public ModelRenderer Shape9;
    public ModelRenderer Shape10;
    public ModelRenderer Shape11;
    
    public ElysianTotemModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Shape1.setRotationPoint(-6.0f, 24.0f, -6.0f);
        this.Shape1.setTextureSize(128, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape1.mirror = true;
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Shape2.setRotationPoint(6.0f, 24.0f, -6.0f);
        this.Shape2.setTextureSize(128, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape2.mirror = false;
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Shape3.setRotationPoint(6.0f, 24.0f, 6.0f);
        this.Shape3.setTextureSize(128, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Shape4.setRotationPoint(-6.0f, 24.0f, 6.0f);
        this.Shape4.setTextureSize(128, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape4.mirror = false;
        (this.Shape5 = new ModelRenderer(this, 2, 0)).addBox(-7.0f, -14.0f, -7.0f, 14, 14, 14);
        this.Shape5.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Shape5.setTextureSize(128, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        (this.Shape6 = new ModelRenderer(this, 2, 28)).addBox(-7.0f, 0.0f, -7.0f, 14, 14, 14);
        this.Shape6.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.Shape6.setTextureSize(128, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape7 = new ModelRenderer(this, 44, 0)).addBox(-6.0f, 0.0f, -6.0f, 12, 2, 12);
        this.Shape7.setRotationPoint(0.0f, 7.0f, 0.0f);
        this.Shape7.setTextureSize(128, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer(this, 0, 28)).addBox(-8.0f, -2.0f, -8.0f, 4, 6, 4);
        this.Shape8.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.Shape8.setTextureSize(128, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape8.mirror = true;
        (this.Shape9 = new ModelRenderer(this, 0, 28)).addBox(-8.0f, -2.0f, 4.0f, 4, 6, 4);
        this.Shape9.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.Shape9.setTextureSize(128, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape9.mirror = false;
        (this.Shape10 = new ModelRenderer(this, 0, 28)).addBox(4.0f, -2.0f, 4.0f, 4, 6, 4);
        this.Shape10.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.Shape10.setTextureSize(128, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape10.mirror = true;
        (this.Shape11 = new ModelRenderer(this, 0, 28)).addBox(4.0f, -2.0f, -8.0f, 4, 6, 4);
        this.Shape11.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.Shape11.setTextureSize(128, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape11.mirror = false;
    }
    
    public void renderAll(final float f5) {
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
