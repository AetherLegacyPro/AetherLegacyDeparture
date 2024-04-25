package com.gildedgames.the_aether.client.models.entities;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.passive.EntityThunderlo;
import com.gildedgames.the_aether.entities.passive.mountable.EntityZephyroo;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ZephyrooModel extends ModelBase
{
    ModelRenderer LeftHand;
    ModelRenderer LeftArm;
    ModelRenderer LeftFoot;
    ModelRenderer LeftLeg;
    ModelRenderer LeftHip;
    ModelRenderer LeftShoulder;
    ModelRenderer TailBottom;
    ModelRenderer Pouch;
    ModelRenderer Snout;
    ModelRenderer RightHip;
    ModelRenderer RightLeg;
    ModelRenderer RightFoot;
    ModelRenderer RightShoulder;
    ModelRenderer RightArm;
    ModelRenderer RightHand;
    ModelRenderer TailTop;
    ModelRenderer EarLeft;
    ModelRenderer Neck;
    ModelRenderer EarRight;
    ModelRenderer Head;
    ModelRenderer Body;
    
    public ZephyrooModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.LeftHand = new ModelRenderer((ModelBase)this, 50, 50)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.LeftHand.setRotationPoint(5.0f, 10.5f, -10.5f);
        this.LeftHand.setTextureSize(128, 64);
        this.LeftHand.mirror = true;
        this.setRotation(this.LeftHand, -0.3665191f, 0.0f, 0.0f);
        (this.LeftArm = new ModelRenderer((ModelBase)this, 40, 38)).addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.LeftArm.setRotationPoint(5.0f, 3.0f, -7.0f);
        this.LeftArm.setTextureSize(128, 64);
        this.LeftArm.mirror = true;
        this.setRotation(this.LeftArm, -0.4363323f, 0.0f, 0.0f);
        (this.LeftFoot = new ModelRenderer((ModelBase)this, 29, 19)).addBox(0.0f, 8.0f, -7.0f, 3, 2, 6);
        this.LeftFoot.setRotationPoint(3.0f, 14.0f, 1.0f);
        this.LeftFoot.setTextureSize(128, 64);
        this.LeftFoot.mirror = true;
        this.setRotation(this.LeftFoot, 0.0f, 0.0f, 0.0f);
        (this.LeftLeg = new ModelRenderer((ModelBase)this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 3, 9, 3);
        this.LeftLeg.setRotationPoint(3.0f, 14.0f, 1.0f);
        this.LeftLeg.setTextureSize(128, 64);
        this.LeftLeg.mirror = true;
        this.setRotation(this.LeftLeg, -0.4363323f, 0.0f, 0.0f);
        (this.LeftHip = new ModelRenderer((ModelBase)this, 0, 41)).addBox(0.0f, 0.0f, 0.0f, 2, 7, 7);
        this.LeftHip.setRotationPoint(4.0f, 8.0f, 0.5f);
        this.LeftHip.setTextureSize(128, 64);
        this.LeftHip.mirror = true;
        this.setRotation(this.LeftHip, 0.0349066f, 0.0f, 0.0f);
        (this.LeftShoulder = new ModelRenderer((ModelBase)this, 40, 49)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 3);
        this.LeftShoulder.setRotationPoint(5.0f, 2.0f, -7.5f);
        this.LeftShoulder.setTextureSize(128, 64);
        this.LeftShoulder.mirror = true;
        this.setRotation(this.LeftShoulder, 0.0349066f, 0.0f, 0.0f);
        (this.TailBottom = new ModelRenderer((ModelBase)this, 44, 25)).addBox(0.0f, 0.0f, 0.0f, 3, 9, 3);
        this.TailBottom.setRotationPoint(-1.5f, 21.0f, 15.0f);
        this.TailBottom.setTextureSize(128, 64);
        this.TailBottom.mirror = true;
        this.setRotation(this.TailBottom, 1.32645f, 0.0f, 0.0f);
        (this.Pouch = new ModelRenderer((ModelBase)this, 13, 0)).addBox(0.0f, 0.0f, 0.0f, 9, 2, 8);
        this.Pouch.setRotationPoint(-4.5f, 10.0f, -4.0f);
        this.Pouch.setTextureSize(128, 64);
        this.Pouch.mirror = true;
        this.setRotation(this.Pouch, -0.7853982f, 0.0f, 0.0f);
        (this.Snout = new ModelRenderer((ModelBase)this, 0, 22)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 9);
        this.Snout.setRotationPoint(-2.0f, -2.0f, -14.5f);
        this.Snout.setTextureSize(128, 64);
        this.Snout.mirror = true;
        this.setRotation(this.Snout, 0.3490659f, 0.0f, 0.0f);
        (this.RightHip = new ModelRenderer((ModelBase)this, 0, 41)).addBox(0.0f, 0.0f, 0.0f, 2, 7, 7);
        this.RightHip.setRotationPoint(-6.0f, 8.0f, 0.5f);
        this.RightHip.setTextureSize(128, 64);
        this.RightHip.mirror = true;
        this.setRotation(this.RightHip, 0.0349066f, 0.0f, 0.0f);
        (this.RightLeg = new ModelRenderer((ModelBase)this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 3, 9, 3);
        this.RightLeg.setRotationPoint(-6.0f, 14.0f, 1.0f);
        this.RightLeg.setTextureSize(128, 64);
        this.RightLeg.mirror = true;
        this.setRotation(this.RightLeg, -0.4363323f, 0.0f, 0.0f);
        (this.RightFoot = new ModelRenderer((ModelBase)this, 29, 19)).addBox(0.0f, 8.0f, -7.0f, 3, 2, 6);
        this.RightFoot.setRotationPoint(-6.0f, 14.0f, 1.0f);
        this.RightFoot.setTextureSize(128, 64);
        this.RightFoot.mirror = true;
        this.setRotation(this.RightFoot, 0.0f, 0.0f, 0.0f);
        (this.RightShoulder = new ModelRenderer((ModelBase)this, 40, 49)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 3);
        this.RightShoulder.setRotationPoint(-7.0f, 2.0f, -7.5f);
        this.RightShoulder.setTextureSize(128, 64);
        this.RightShoulder.mirror = true;
        this.setRotation(this.RightShoulder, 0.0349066f, 0.0f, 0.0f);
        (this.RightArm = new ModelRenderer((ModelBase)this, 40, 38)).addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.RightArm.setRotationPoint(-7.0f, 3.0f, -7.0f);
        this.RightArm.setTextureSize(128, 64);
        this.RightArm.mirror = true;
        this.setRotation(this.RightArm, -0.4363323f, 0.0f, 0.0f);
        (this.RightHand = new ModelRenderer((ModelBase)this, 50, 50)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.RightHand.setRotationPoint(-7.0f, 10.5f, -10.5f);
        this.RightHand.setTextureSize(128, 64);
        this.RightHand.mirror = true;
        this.setRotation(this.RightHand, -0.3665191f, 0.0f, 0.0f);
        (this.TailTop = new ModelRenderer((ModelBase)this, 48, 37)).addBox(0.0f, 0.0f, 0.0f, 3, 10, 3);
        this.TailTop.setRotationPoint(-1.5f, 14.0f, 8.0f);
        this.TailTop.setTextureSize(128, 64);
        this.TailTop.mirror = true;
        this.setRotation(this.TailTop, 0.8028515f, 0.0f, 0.0f);
        (this.EarLeft = new ModelRenderer((ModelBase)this, 0, 41)).addBox(0.0f, 0.0f, 0.0f, 1, 5, 2);
        this.EarLeft.setRotationPoint(2.0f, -10.5f, -10.0f);
        this.EarLeft.setTextureSize(128, 64);
        this.EarLeft.mirror = true;
        this.setRotation(this.EarLeft, 0.0f, 0.0f, 0.2443461f);
        (this.Neck = new ModelRenderer((ModelBase)this, 0, 14)).addBox(0.0f, 0.0f, 0.0f, 4, 3, 5);
        this.Neck.setRotationPoint(-2.0f, 0.0f, -9.5f);
        this.Neck.setTextureSize(128, 64);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.6108652f, 0.0f, 0.0f);
        (this.EarRight = new ModelRenderer((ModelBase)this, 0, 41)).addBox(0.0f, 0.0f, 0.0f, 1, 5, 2);
        this.EarRight.setRotationPoint(-3.0f, -10.0f, -10.0f);
        this.EarRight.setTextureSize(128, 64);
        this.EarRight.mirror = true;
        this.setRotation(this.EarRight, 0.0f, 0.0f, -0.2617994f);
        (this.Head = new ModelRenderer((ModelBase)this, 26, 27)).addBox(0.0f, 0.0f, 0.0f, 4, 3, 5);
        this.Head.setRotationPoint(-2.0f, -6.0f, -11.0f);
        this.Head.setTextureSize(128, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        (this.Body = new ModelRenderer((ModelBase)this, 0, 35)).addBox(0.0f, 0.0f, 0.0f, 10, 9, 20);
        this.Body.setRotationPoint(-5.0f, -2.0f, -4.0f);
        this.Body.setTextureSize(128, 64);
        this.Body.mirror = true;
        this.setRotation(this.Body, -0.7853982f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
    	EntityZephyroo zephyroo = ((EntityZephyroo) entity);
        if(zephyroo.isChild()) {
        	
        	GL11.glScalef(1.0F / 2.0F, 1.0F / 2.0F, 1.0F / 2.0F);
			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
        	
        }    	
    	super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.LeftHand.render(f5);
        this.LeftArm.render(f5);
        this.LeftFoot.render(f5);
        this.LeftLeg.render(f5);
        this.LeftHip.render(f5);
        this.LeftShoulder.render(f5);
        this.TailBottom.render(f5);
        this.Pouch.render(f5);
        this.Snout.render(f5);
        this.RightHip.render(f5);
        this.RightLeg.render(f5);
        this.RightFoot.render(f5);
        this.RightShoulder.render(f5);
        this.RightArm.render(f5);
        this.RightHand.render(f5);
        this.TailTop.render(f5);
        this.EarLeft.render(f5);
        this.Neck.render(f5);
        this.EarRight.render(f5);
        this.Head.render(f5);
        this.Body.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (entity.motionX != 0.0 && entity.motionZ != 0.0) {
            this.RightLeg.rotateAngleX = (float)Math.cos(((EntityZephyroo)entity).getTimeTilJump() * 0.5f);
            this.LeftLeg.rotateAngleX = (float)Math.cos(((EntityZephyroo)entity).getTimeTilJump() * 0.5f);
            this.RightFoot.rotateAngleX = this.RightLeg.rotateAngleX + 0.25f;
            this.LeftFoot.rotateAngleX = this.RightLeg.rotateAngleX + 0.25f;
        }
        else {
            this.RightLeg.rotateAngleX = 0.0f;
            this.LeftLeg.rotateAngleX = 0.0f;
            this.RightFoot.rotateAngleX = this.RightLeg.rotateAngleX + 0.25f;
            this.LeftFoot.rotateAngleX = this.RightLeg.rotateAngleX + 0.25f;
        }
    }
}

