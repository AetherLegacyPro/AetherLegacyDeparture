package com.gildedgames.the_aether.client.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class UroModel extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer field_78133_b;
    public ModelRenderer body;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer leg7;
    public ModelRenderer leg8;
    
    public ModelRenderer leg9;
    public ModelRenderer leg10;
    public ModelRenderer leg11;
    public ModelRenderer leg12;

    public UroModel()
    {
        this(0.0F);
    }

    public UroModel(float p_i1147_1_)
    {
        byte b0 = 4;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1147_1_);
        this.head.setRotationPoint(0.0F, (float)b0, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1147_1_);
        this.head2.setRotationPoint(8.0F, (float)(b0 - 1), 4.0F);
        this.head3 = new ModelRenderer(this, 0, 0);
        this.head3.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1147_1_);
        this.head3.setRotationPoint(-8.0F, (float)(b0 - 1), -4.0F);
        
        this.field_78133_b = new ModelRenderer(this, 32, 0);
        this.field_78133_b.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1147_1_ + 0.5F);
        this.field_78133_b.setRotationPoint(0.0F, (float)b0, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1147_1_);
        this.body.setRotationPoint(0.0F, (float)b0, 0.0F);        
        this.body2 = new ModelRenderer(this, 16, 16);
        this.body2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1147_1_);
        this.body2.setRotationPoint(8.0F, (float)(b0 - 1), 4.0F);
        this.body3 = new ModelRenderer(this, 16, 16);
        this.body3.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1147_1_);
        this.body3.setRotationPoint(-8.0F, (float)(b0 - 1), -4.0F);
        
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg1.setRotationPoint(-2.0F, (float)(12 + b0), 4.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg2.setRotationPoint(2.0F, (float)(12 + b0), 4.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg3.setRotationPoint(-2.0F, (float)(12 + b0), -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg4.setRotationPoint(2.0F, (float)(12 + b0), -4.0F);
        
        this.leg5 = new ModelRenderer(this, 0, 16);
        this.leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_); //front right
        this.leg5.setRotationPoint(-8.0F, (float)(11 + b0), -6.0F);
        this.leg6 = new ModelRenderer(this, 0, 16);
        this.leg6.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg6.setRotationPoint(8.0F, (float)(11 + b0), 6.0F);
        this.leg7 = new ModelRenderer(this, 0, 16);
        this.leg7.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg7.setRotationPoint(-8.0F, (float)(11 + b0), -2.0F); //back right
        this.leg8 = new ModelRenderer(this, 0, 16);
        this.leg8.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i1147_1_);
        this.leg8.setRotationPoint(8.0F, (float)(11 + b0), 2.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.head.render(p_78088_7_);
        this.head2.render(p_78088_7_);
        this.head3.render(p_78088_7_);
        this.body.render(p_78088_7_);
        this.body2.render(p_78088_7_);
        this.body3.render(p_78088_7_);
        this.leg1.render(p_78088_7_);
        this.leg2.render(p_78088_7_);
        this.leg3.render(p_78088_7_);
        this.leg4.render(p_78088_7_);
        this.leg5.render(p_78088_7_);
        this.leg6.render(p_78088_7_);
        this.leg7.render(p_78088_7_);
        this.leg8.render(p_78088_7_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head2.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head2.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head3.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head3.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leg2.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.leg3.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.leg4.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leg5.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leg6.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.leg7.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.leg8.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
    }
}