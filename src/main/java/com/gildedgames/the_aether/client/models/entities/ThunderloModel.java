package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.entities.passive.mountable.EntityPhyg;

import net.minecraft.client.model.ModelBase;

public class ThunderloModel extends ModelBase
{
    ModelRenderer Tail1;
    ModelRenderer Hair;
    ModelRenderer horn1L;
    ModelRenderer headTop;
    ModelRenderer Tail2;
    ModelRenderer Belly;
    ModelRenderer horn2L;
    ModelRenderer Jaw;
    ModelRenderer horn1R;
    ModelRenderer ShoulderL;
    ModelRenderer horn2R;
    ModelRenderer BackLegL;
    ModelRenderer HoofL;
    ModelRenderer BodyBack;
    ModelRenderer BodyFront;
    ModelRenderer FrontLeg1L;
    ModelRenderer ThighL;
    ModelRenderer FrontLeg2L;
    ModelRenderer ShoulderR;
    ModelRenderer FrontLeg1R;
    ModelRenderer FrontLeg2R;
    ModelRenderer HoofR;
    ModelRenderer ThighR;
    ModelRenderer BackLegR;
    ModelRenderer Nose;
    ModelRenderer Head;
    
    public ThunderloModel() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        (this.Tail1 = new ModelRenderer((ModelBase)this, 9, 93)).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 8);
        this.Tail1.setRotationPoint(-1.0f, 5.0f, 24.0f);
        this.Tail1.setTextureSize(256, 128);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, -0.3141593f, 0.0f, 0.0f);
        (this.Hair = new ModelRenderer((ModelBase)this, 68, 4)).addBox(-9.0f, 3.0f, 0.0f, 19, 9, 0);
        this.Hair.setRotationPoint(-1.0f, 11.0f, -4.0f);
        this.Hair.setTextureSize(256, 128);
        this.Hair.mirror = true;
        this.setRotation(this.Hair, 0.0f, 0.0f, 0.0f);
        (this.horn1L = new ModelRenderer((ModelBase)this, 112, 4)).addBox(10.0f, -8.0f, -4.0f, 2, 6, 2);
        this.horn1L.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.horn1L.setTextureSize(256, 128);
        this.horn1L.mirror = true;
        this.setRotation(this.horn1L, 0.2617994f, 0.0698132f, 0.0f);
        (this.headTop = new ModelRenderer((ModelBase)this, -1, 3)).addBox(-3.0f, -5.0f, -7.0f, 6, 2, 6);
        this.headTop.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.headTop.setTextureSize(256, 128);
        this.headTop.mirror = true;
        this.setRotation(this.headTop, 0.2094395f, 0.0f, 0.0f);
        (this.Tail2 = new ModelRenderer((ModelBase)this, 9, 112)).addBox(-3.0f, -1.0f, 9.0f, 6, 6, 6);
        this.Tail2.setRotationPoint(-1.0f, 5.0f, 24.0f);
        this.Tail2.setTextureSize(256, 128);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, -0.1396263f, 0.0f, 0.0f);
        (this.Belly = new ModelRenderer((ModelBase)this, 65, 96)).addBox(-10.0f, 1.0f, -8.0f, 19, 9, 18);
        this.Belly.setRotationPoint(0.0f, 7.0f, 2.0f);
        this.Belly.setTextureSize(256, 128);
        this.Belly.mirror = true;
        this.setRotation(this.Belly, 0.296706f, 0.0f, 0.0f);
        (this.horn2L = new ModelRenderer((ModelBase)this, 112, 4)).addBox(5.0f, -3.0f, -4.0f, 7, 2, 2);
        this.horn2L.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.horn2L.setTextureSize(256, 128);
        this.horn2L.mirror = true;
        this.setRotation(this.horn2L, 0.1047198f, 0.0872665f, 0.0f);
        (this.Jaw = new ModelRenderer((ModelBase)this, 12, 30)).addBox(-2.0f, 7.0f, -11.0f, 4, 2, 12);
        this.Jaw.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.Jaw.setTextureSize(256, 128);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.3839724f, 0.0f, 0.0f);
        (this.horn1R = new ModelRenderer((ModelBase)this, 112, 4)).addBox(-12.0f, -8.0f, -4.0f, 2, 6, 2);
        this.horn1R.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.horn1R.setTextureSize(256, 128);
        this.horn1R.mirror = true;
        this.setRotation(this.horn1R, 0.2617994f, -0.0698132f, 0.0f);
        (this.ShoulderL = new ModelRenderer((ModelBase)this, 142, 3)).addBox(0.0f, -6.0f, -4.0f, 3, 10, 10);
        this.ShoulderL.setRotationPoint(10.0f, 6.0f, -1.0f);
        this.ShoulderL.setTextureSize(256, 128);
        this.ShoulderL.mirror = true;
        this.setRotation(this.ShoulderL, 0.0f, 0.0f, 0.0f);
        (this.horn2R = new ModelRenderer((ModelBase)this, 112, 4)).addBox(-12.0f, -3.0f, -4.0f, 7, 2, 2);
        this.horn2R.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.horn2R.setTextureSize(256, 128);
        this.horn2R.mirror = true;
        this.setRotation(this.horn2R, 0.1047198f, -0.0872665f, 0.0f);
        (this.BackLegL = new ModelRenderer((ModelBase)this, 2, 70)).addBox(-2.0f, 6.0f, 2.0f, 4, 11, 4);
        this.BackLegL.setRotationPoint(8.0f, 7.0f, 18.0f);
        this.BackLegL.setTextureSize(256, 128);
        this.BackLegL.mirror = true;
        this.setRotation(this.BackLegL, 0.0f, 0.0f, 0.0f);
        (this.HoofL = new ModelRenderer((ModelBase)this, 32, 83)).addBox(-1.4f, 12.0f, -1.0f, 4, 3, 5);
        this.HoofL.setRotationPoint(9.0f, 9.0f, -1.0f);
        this.HoofL.setTextureSize(256, 128);
        this.HoofL.mirror = true;
        this.setRotation(this.HoofL, 0.0f, 0.0f, 0.0f);
        (this.BodyBack = new ModelRenderer((ModelBase)this, 68, 18)).addBox(-9.5f, -7.0f, -12.0f, 18, 17, 17);
        this.BodyBack.setRotationPoint(0.0f, 3.0f, 19.0f);
        this.BodyBack.setTextureSize(256, 128);
        this.BodyBack.mirror = true;
        this.setRotation(this.BodyBack, -0.2268928f, 0.0f, 0.0f);
        (this.BodyFront = new ModelRenderer((ModelBase)this, 67, 56)).addBox(-11.0f, -10.0f, -10.0f, 21, 19, 17);
        this.BodyFront.setRotationPoint(0.0f, 3.0f, 2.0f);
        this.BodyFront.setTextureSize(256, 128);
        this.BodyFront.mirror = true;
        this.setRotation(this.BodyFront, 0.0f, 0.0f, 0.0f);
        (this.FrontLeg1L = new ModelRenderer((ModelBase)this, 32, 46)).addBox(-2.0f, 0.0f, -2.0f, 5, 10, 7);
        this.FrontLeg1L.setRotationPoint(9.0f, 9.0f, -1.0f);
        this.FrontLeg1L.setTextureSize(256, 128);
        this.FrontLeg1L.mirror = true;
        this.setRotation(this.FrontLeg1L, 0.1745329f, 0.0f, 0.0f);
        (this.ThighL = new ModelRenderer((ModelBase)this, 2, 49)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
        this.ThighL.setRotationPoint(8.0f, 7.0f, 18.0f);
        this.ThighL.setTextureSize(256, 128);
        this.ThighL.mirror = true;
        this.setRotation(this.ThighL, 0.3316126f, 0.0f, 0.0f);
        (this.FrontLeg2L = new ModelRenderer((ModelBase)this, 32, 67)).addBox(-1.5f, 8.0f, 1.5f, 4, 6, 5);
        this.FrontLeg2L.setRotationPoint(9.0f, 9.0f, -1.0f);
        this.FrontLeg2L.setTextureSize(256, 128);
        this.FrontLeg2L.mirror = true;
        this.setRotation(this.FrontLeg2L, -0.1745329f, 0.0f, 0.0f);
        (this.ShoulderR = new ModelRenderer((ModelBase)this, 142, 3)).addBox(-4.0f, -6.0f, -4.0f, 3, 10, 10);
        this.ShoulderR.setRotationPoint(-10.0f, 6.0f, -1.0f);
        this.ShoulderR.setTextureSize(256, 128);
        this.ShoulderR.mirror = true;
        this.setRotation(this.ShoulderR, 0.0f, 0.0f, 0.0f);
        (this.FrontLeg1R = new ModelRenderer((ModelBase)this, 32, 46)).addBox(-3.0f, 0.0f, -2.0f, 5, 10, 7);
        this.FrontLeg1R.setRotationPoint(-10.0f, 9.0f, -1.0f);
        this.FrontLeg1R.setTextureSize(256, 128);
        this.FrontLeg1R.mirror = true;
        this.setRotation(this.FrontLeg1R, 0.1745329f, 0.0f, 0.0f);
        (this.FrontLeg2R = new ModelRenderer((ModelBase)this, 32, 67)).addBox(-2.5f, 8.0f, 1.5f, 4, 6, 5);
        this.FrontLeg2R.setRotationPoint(-10.0f, 9.0f, -1.0f);
        this.FrontLeg2R.setTextureSize(256, 128);
        this.FrontLeg2R.mirror = true;
        this.setRotation(this.FrontLeg2R, -0.1745329f, 0.0f, 0.0f);
        (this.HoofR = new ModelRenderer((ModelBase)this, 32, 83)).addBox(-2.6f, 12.0f, -1.0f, 4, 3, 5);
        this.HoofR.setRotationPoint(-10.0f, 9.0f, -1.0f);
        this.HoofR.setTextureSize(256, 128);
        this.HoofR.mirror = true;
        this.setRotation(this.HoofR, 0.0f, 0.0f, 0.0f);
        this.HoofR.mirror = false;
        (this.ThighR = new ModelRenderer((ModelBase)this, 2, 49)).addBox(-3.0f, 0.0f, -4.0f, 6, 10, 6);
        this.ThighR.setRotationPoint(-9.0f, 7.0f, 19.0f);
        this.ThighR.setTextureSize(256, 128);
        this.ThighR.mirror = true;
        this.setRotation(this.ThighR, 0.3316126f, 0.0f, 0.0f);
        this.ThighR.mirror = false;
        (this.BackLegR = new ModelRenderer((ModelBase)this, 2, 70)).addBox(-2.0f, 6.0f, 1.0f, 4, 11, 4);
        this.BackLegR.setRotationPoint(-9.0f, 7.0f, 19.0f);
        this.BackLegR.setTextureSize(256, 128);
        this.BackLegR.mirror = true;
        this.setRotation(this.BackLegR, 0.0f, 0.0f, 0.0f);
        this.BackLegR.mirror = false;
        (this.Nose = new ModelRenderer((ModelBase)this, 49, 29)).addBox(-2.0f, 5.0f, -11.0f, 4, 4, 3);
        this.Nose.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.Nose.setTextureSize(256, 128);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.2094395f, 0.0f, 0.0f);
        (this.Head = new ModelRenderer((ModelBase)this, 27, 5)).addBox(-5.0f, -3.0f, -8.0f, 10, 12, 9);
        this.Head.setRotationPoint(0.0f, 2.0f, -7.0f);
        this.Head.setTextureSize(256, 128);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.2094395f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
    	EntityThunderlo thunderlo = ((EntityThunderlo) entity);
        if(thunderlo.isChild()) {
        	
        	GL11.glScalef(1.0F / 2.0F, 1.0F / 2.0F, 1.0F / 2.0F);
			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
        	
        }
    	
    	super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Tail1.render(f5);
        this.Hair.render(f5);
        this.horn1L.render(f5);
        this.headTop.render(f5);
        this.Tail2.render(f5);
        this.Belly.render(f5);
        this.horn2L.render(f5);
        this.Jaw.render(f5);
        this.horn1R.render(f5);
        this.ShoulderL.render(f5);
        this.horn2R.render(f5);
        this.BackLegL.render(f5);
        this.HoofL.render(f5);
        this.BodyBack.render(f5);
        this.BodyFront.render(f5);
        this.FrontLeg1L.render(f5);
        this.ThighL.render(f5);
        this.FrontLeg2L.render(f5);
        this.ShoulderR.render(f5);
        this.FrontLeg1R.render(f5);
        this.FrontLeg2R.render(f5);
        this.HoofR.render(f5);
        this.ThighR.render(f5);
        this.BackLegR.render(f5);
        this.Nose.render(f5);
        this.Head.render(f5);
        
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        float f6 = (180F / (float)Math.PI);
        final float legSwingSpeed = 0.7f;
        final float legSwingLength = 0.8f;
        final float rightLegRotation = MathHelper.cos(p_78087_1_ * legSwingSpeed) * legSwingLength * p_78087_2_;
        final float leftLegRotation = MathHelper.cos(p_78087_1_ * legSwingSpeed + 3.141593f) * legSwingLength * p_78087_2_;
        this.Head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.Head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.Jaw.rotateAngleX = 0.2919862f + this.Head.rotateAngleX; //0.1919862f
        this.Jaw.rotateAngleY = this.Head.rotateAngleY;
        this.headTop.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.headTop.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.Nose.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.Nose.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.horn1L.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.horn1L.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.horn2L.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.horn2L.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.horn1R.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.horn1R.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.horn2R.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.horn2R.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.Belly.rotateAngleX = ((float)Math.PI / 2F);
        this.FrontLeg1L.rotateAngleX = 0.1745329f + leftLegRotation;
        this.FrontLeg2L.rotateAngleX = -0.1745329f + leftLegRotation;
        this.ThighL.rotateAngleX = 0.3316126f + leftLegRotation;
        this.BackLegL.rotateAngleX = leftLegRotation;
        this.HoofL.rotateAngleX = leftLegRotation;
        this.FrontLeg1R.rotateAngleX = 0.1745329f + rightLegRotation;
        this.FrontLeg2R.rotateAngleX = -0.1745329f + rightLegRotation;
        this.ThighR.rotateAngleX = 0.3316126f + rightLegRotation;
        this.BackLegR.rotateAngleX = rightLegRotation;
        this.HoofR.rotateAngleX = rightLegRotation;

    }
       
}

