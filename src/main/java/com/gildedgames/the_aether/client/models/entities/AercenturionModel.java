package com.gildedgames.the_aether.client.models.entities;

import com.gildedgames.the_aether.entities.hostile.EntityAercenturion;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class AercenturionModel extends ModelBiped
{
    ModelRenderer LowerBody;
    ModelRenderer LeftArmHand;
    ModelRenderer RightArmHand;
    ModelRenderer SentryHead;
    ModelRenderer SentryBody;
    public boolean isDefault;
    public byte armState;
    float[] armsAngles;
    
    public AercenturionModel() {
        this.LowerBody = new ModelRenderer((ModelBase)this);
        this.isDefault = false;
        this.armState = 2;
        this.armsAngles = new float[] { 1.0f, 1.0f, 0.5f, 0.5f };
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0)).addBox(-5.5f, -10.0f, -4.5f, 11, 8, 9);
        this.bipedHead.setRotationPoint(0.0f, -2.0f, 0.0f);
        this.bipedHead.setTextureSize(128, 64);
        this.bipedHead.mirror = true;
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        (this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 42, 47)).addBox(-3.3f, 0.0f, -3.0f, 5, 11, 6);
        this.bipedLeftLeg.setRotationPoint(4.0f, 13.0f, 0.0f);
        this.bipedLeftLeg.setTextureSize(128, 64);
        this.bipedLeftLeg.mirror = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
        (this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 3)).addBox(3.0f, -1.0f, -3.0f, 4, 8, 6);
        this.bipedLeftArm.setRotationPoint(8.0f, 0.0f, 0.0f);
        this.bipedLeftArm.setTextureSize(128, 64);
        this.bipedLeftArm.mirror = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        (this.LowerBody = new ModelRenderer((ModelBase)this, 0, 50)).addBox(-6.0f, 6.0f, -4.5f, 12, 5, 9);
        this.LowerBody.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.LowerBody.setTextureSize(128, 64);
        this.LowerBody.mirror = true;
        this.setRotation(this.LowerBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg.mirror = true;
        (this.bipedRightLeg = new ModelRenderer((ModelBase)this, 42, 47)).addBox(-1.633333f, 0.0f, -3.0f, 5, 11, 6);
        this.bipedRightLeg.setRotationPoint(-4.0f, 13.0f, 0.0f);
        this.bipedRightLeg.setTextureSize(128, 64);
        this.bipedRightLeg.mirror = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg.mirror = false;
        (this.bipedBody = new ModelRenderer((ModelBase)this, 0, 17)).addBox(-8.0f, -4.0f, -5.5f, 16, 10, 11);
        this.bipedBody.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bipedBody.setTextureSize(128, 64);
        this.bipedBody.mirror = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        (this.RightArmHand = new ModelRenderer((ModelBase)this, 54, 17)).addBox(-5.0f, 6.0f, -3.5f, 5, 6, 7);
        this.RightArmHand.setRotationPoint(-8.0f, 0.0f, 0.0f);
        this.RightArmHand.setTextureSize(128, 64);
        this.RightArmHand.mirror = true;
        this.setRotation(this.RightArmHand, 0.0f, 0.0f, 0.0f);
        this.RightArmHand.mirror = false;
        this.bipedRightArm.mirror = true;
        (this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 3)).addBox(-7.0f, -1.0f, -3.0f, 4, 8, 6);
        this.bipedRightArm.setRotationPoint(-8.0f, 0.0f, 0.0f);
        this.bipedRightArm.setTextureSize(128, 64);
        this.bipedRightArm.mirror = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm.mirror = false;
        (this.LeftArmHand = new ModelRenderer((ModelBase)this, 54, 17)).addBox(0.0f, 6.0f, -3.5f, 5, 6, 7);
        this.LeftArmHand.setRotationPoint(8.0f, 0.0f, 0.0f);
        this.LeftArmHand.setTextureSize(128, 64);
        this.LeftArmHand.mirror = true;
        this.setRotation(this.LeftArmHand, 0.0f, 0.0f, 0.0f);
        (this.SentryBody = new ModelRenderer((ModelBase)this, 64, 48)).addBox(-5.5f, -8.0f, -4.5f, 8, 8, 8);
        this.SentryBody.setRotationPoint(1.5f, 4.0f, -12.0f);
        this.SentryBody.setTextureSize(128, 64);
        this.SentryBody.mirror = true;
        this.setRotation(this.SentryBody, 0.0f, 0.0f, 0.0f);
        (this.SentryHead = new ModelRenderer((ModelBase)this, 64, 48)).addBox(-5.5f, -8.0f, -4.5f, 8, 8, 8);
        this.SentryHead.setRotationPoint(1.5f, -11.0f, 0.0f);
        this.SentryHead.setTextureSize(128, 64);
        this.SentryHead.mirror = true;
        this.setRotation(this.SentryHead, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bipedBody.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedLeftArm.render(f5);
        this.LowerBody.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedBody.render(f5);
        this.RightArmHand.render(f5);
        this.bipedRightArm.render(f5);
        this.LeftArmHand.render(f5);
        this.SentryHead.render(f5);
        this.SentryBody.render(f5);
        this.SentryHead.isHidden = true;
        this.SentryBody.isHidden = true;
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (entity instanceof EntityAercenturion) {
            final EntityAercenturion entityS = (EntityAercenturion)entity;
            //this.armState = entityS.getHandState();
            //if (entityS.progress < this.armsAngles[this.armState]) {
               // final EntityAercenturion entitySentryGolem = entityS;
                //entitySentryGolem.progress += 0.02f;
            //}
            //if (entityS.progress > this.armsAngles[this.armState]) {
               // final EntityAercenturion entitySentryGolem2 = entityS;
               // entitySentryGolem2.progress -= 0.02f;
           // }
            //this.bipedRightArm.rotateAngleX = -3.0f;
            //this.bipedLeftArm.rotateAngleX = -3.0f;
            final ModelRenderer bipedRightArm = this.bipedRightArm;
            //bipedRightArm.rotateAngleY -= 0.3f;
            final ModelRenderer bipedLeftArm = this.bipedLeftArm;
            //bipedLeftArm.rotateAngleY += 0.3f;
            final ModelRenderer bipedRightArm2 = this.bipedRightArm;
            //bipedRightArm2.rotateAngleZ += 0.3f;
            final ModelRenderer bipedLeftArm2 = this.bipedLeftArm;
            //bipedLeftArm2.rotateAngleZ -= 0.3f;
        }
        this.RightArmHand.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.RightArmHand.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.RightArmHand.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
        this.LeftArmHand.rotateAngleX = this.bipedLeftArm.rotateAngleX;
        this.LeftArmHand.rotateAngleY = this.bipedLeftArm.rotateAngleY;
        this.LeftArmHand.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
        this.LowerBody.rotateAngleX = this.bipedBody.rotateAngleX;
        this.LowerBody.rotateAngleY = this.bipedBody.rotateAngleY;
        this.LowerBody.rotateAngleZ = this.bipedBody.rotateAngleZ;
    }
}
