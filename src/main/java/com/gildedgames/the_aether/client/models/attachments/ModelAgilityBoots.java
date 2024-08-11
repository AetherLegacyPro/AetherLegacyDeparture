package com.gildedgames.the_aether.client.models.attachments;

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBiped;

public class ModelAgilityBoots extends ModelBiped
{
    public ModelRenderer boot_R;
    public ModelRenderer boot_L;
    public ModelRenderer wing1_R;
    public ModelRenderer wing2_R;
    public ModelRenderer wing1_L;
    public ModelRenderer wing2_L;
    
    public ModelAgilityBoots() {
        super(0.5f);
        this.textureWidth = 64;
        this.textureHeight = 32;
        (this.boot_L = new ModelRenderer(this, 0, 16)).setRotationPoint(1.8f, 12.0f, 0.0f);
        this.boot_L.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 1.0f);
        this.setRotation(this.boot_L, 0.0f, 0.0f, 0.0f);
        this.wing1_L = new ModelRenderer(this, 12, 16);
        this.wing1_L.mirror = true;
        this.wing1_L.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.wing1_L.addBox(2.0f, 8.0f, -1.0f, 1, 1, 3, 0.5f);
        (this.wing1_R = new ModelRenderer(this, 12, 16)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.wing1_R.addBox(-3.0f, 8.0f, -1.0f, 1, 1, 3, 0.5f);
        this.wing2_L = new ModelRenderer(this, 12, 16);
        this.wing2_L.mirror = true;
        this.wing2_L.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.wing2_L.addBox(2.0f, 7.0f, 0.0f, 1, 1, 3, 0.5f); 
        (this.boot_R = new ModelRenderer(this, 0, 16)).setRotationPoint(-1.8f, 12.0f, 0.0f);
        this.boot_R.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 1.0f);
        this.setRotation(this.boot_R, 0.0f, 0.0f, 0.0f);
        (this.wing2_R = new ModelRenderer(this, 12, 16)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.wing2_R.addBox(-3.0f, 7.0f, 0.0f, 1, 1, 3, 0.5f); 
        this.boot_L.addChild(this.wing1_L);
        this.boot_R.addChild(this.wing1_R);
        this.wing1_L.addChild(this.wing2_L);
        this.wing1_R.addChild(this.wing2_R);
    }
    
   // public void render(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final Entity entityIn) {
        //this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
       // this.boot_L.render(scale);
        //this.boot_R.render(scale);
   // }
    
    public void renderWings(float scale) {
		this.boot_L.render(scale);
		this.boot_R.render(scale);
		this.wing1_L.render(scale);
		this.wing1_R.render(scale);
		this.wing2_L.render(scale);
		this.wing2_R.render(scale);
	}
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
   public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn) {
       super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.boot_R.rotateAngleX = this.bipedRightLeg.rotateAngleX;
       this.boot_R.rotateAngleY = this.bipedRightLeg.rotateAngleY;
       this.boot_R.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
        this.boot_L.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
        this.boot_L.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
        this.boot_L.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
        this.boot_R.rotationPointZ = this.bipedRightLeg.rotationPointZ;
        this.boot_L.rotationPointZ = this.bipedLeftLeg.rotationPointZ;
    }
}
