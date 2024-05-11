package com.gildedgames.the_aether.client.models.entities;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.passive.mountable.EntityZephyroo;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Credit goes to developers of Alex's Mobs for the Model
public class ZephyrooModel extends ModelBase {
	
	public final ModelRenderer root;
	public final ModelRenderer body;
	public final ModelRenderer pouch;
	public final ModelRenderer tail1;
	public final ModelRenderer tail2;
	public final ModelRenderer leg_left;
	public final ModelRenderer knee_left;
	public final ModelRenderer foot_left;
	public final ModelRenderer leg_right;
	public final ModelRenderer knee_right;
	public final ModelRenderer foot_right;
	public final ModelRenderer chest;
	public final ModelRenderer arm_left;
	public final ModelRenderer arm_right;
	public final ModelRenderer neck;
	public final ModelRenderer head;
	public final ModelRenderer ear_left;
	public final ModelRenderer ear_right;
	public final ModelRenderer snout;

	public ZephyrooModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		
		this.root = new ModelRenderer(this, 0, 0);
		this.root.setRotationPoint(0.0F, 24.0F, 0.0F);

		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-5.0F, -6.0F, -6.0F, 10, 11, 13);
		this.body.setRotationPoint(0.0F, -15.0F, 4.0F);
		this.root.addChild(body);

		this.pouch = new ModelRenderer(this, 64, 6);
		this.pouch.addBox(-3.5F, -2.5F, -4.0F, 7, 5, 8);
		this.pouch.setRotationPoint(0.0F, 2.7F, -2.2F);
		this.body.addChild(pouch);

		this.tail1 = new ModelRenderer(this, 0, 25);
		this.tail1.addBox(-2.5F, 0.0F, 0.0F, 5, 6, 15);
		this.tail1.setRotationPoint(0.0F, -5.0F, 7.0F);
		this.body.addChild(tail1);

		this.tail2 = new ModelRenderer(this, 26, 32);
		this.tail2.addBox(-1.5F, -3.0F, 0.0F, 3, 4, 15);
		this.tail2.setRotationPoint(0.0F, 4.0F, 15.0F);
		this.tail1.addChild(tail2);

		this.leg_left = new ModelRenderer(this, 48, 28);
		this.leg_left.addBox(-1.25F, -3.75F, -3.5F, 3, 7, 8);
		this.leg_left.setRotationPoint(4.25F, 0.75F, -0.5F);
		this.body.addChild(leg_left);

		this.knee_left = new ModelRenderer(this, 0, 0);
		this.knee_left.addBox(-1.0F, 0.0F, 0.0F, 2, 9, 3);
		this.knee_left.setRotationPoint(0.25F, 3.25F, -3.5F);
		this.leg_left.addChild(knee_left);

		this.foot_left = new ModelRenderer(this, 35, 13);
		this.foot_left.addBox(-1.5F, 0.0F, -10.0F, 3, 2, 12);
		this.foot_left.setRotationPoint(0.0F, 9.0F, 1.0F);
		this.knee_left.addChild(foot_left);

		this.leg_right = new ModelRenderer(this, 48, 28);
		this.leg_right.addBox(-1.75F, -3.75F, -3.5F, 3, 7, 8);
		this.leg_right.setRotationPoint(-4.25F, 0.75F, -0.5F);
		this.body.addChild(leg_right);

		this.knee_right = new ModelRenderer(this, 0, 0);
		this.knee_right.addBox(-1.0F, 0.0F, 0.0F, 2, 9, 3);
		this.knee_right.setRotationPoint(-0.25F, 3.25F, -3.5F);
		this.leg_right.addChild(knee_right);
		
		this.foot_right = new ModelRenderer(this, 35, 13);
		this.foot_right.addBox(-1.5F, 0.0F, -10.0F, 3, 2, 12);
		this.foot_right.setRotationPoint(0.0F, 9.0F, 1.0F);
		this.knee_right.addChild(foot_right);

		this.chest = new ModelRenderer(this, 0, 47);
		this.chest.addBox(-4.0F, 0.0F, -9.0F, 8, 9, 9);
		this.chest.setRotationPoint(0.0F, -6.0F, -6.0F);
		this.body.addChild(chest);
		
		this.arm_left = new ModelRenderer(this, 71, 49);
		this.arm_left.addBox(-1.0F, -1.0F, -2.0F, 2, 12, 3);
		this.arm_left.setRotationPoint(4.0F, 6.0F, -6.0F);
		this.chest.addChild(arm_left);

		this.arm_right = new ModelRenderer(this, 71, 49);
		this.arm_right.addBox(-1.0F, -1.0F, -2.0F, 2, 12, 3);
		this.arm_right.setRotationPoint(-4.0F, 6.0F, -6.0F);
		this.chest.addChild(arm_right);

		this.neck = new ModelRenderer(this, 35, 52);
		this.neck.addBox(-2.0F, -6.0F, -3.0F, 4, 11, 5);
		this.neck.setRotationPoint(0.0F, 2.0F, -8.0F);
		chest.addChild(neck);

		this.head = new ModelRenderer(this, 34, 0);
		this.head.addBox(-2.5F, -4.0F, -3.5F, 5, 4, 6);
		this.head.setRotationPoint(0.0F, -6.0F, -0.5F);
		neck.addChild(head);

		this.ear_left = new ModelRenderer(this, 0, 47);
		this.ear_left.addBox(0.0F, -6.0F, -1.0F, 3, 6, 1);
		this.ear_left.setRotationPoint(0.4F, -4.0F, 1.5F);
		this.head.addChild(ear_left);
		this.setRotation(ear_left, -0.1745F, -0.3491F, 0.4363F);

		this.ear_right = new ModelRenderer(this, 0, 47);
		this.ear_right.addBox(-3.0F, -6.0F, -1.0F, 3, 6, 1);
		this.ear_right.setRotationPoint(-0.4F, -4.0F, 1.5F);
		this.head.addChild(ear_right);
		this.setRotation(ear_right, -0.1745F, 0.3491F, -0.4363F);
		this.setRotation(chest, 0.1745F, 0F, 0F);
		this.setRotation(tail1, -0.1745F, 0F, 0F);
		
		this.snout = new ModelRenderer(this, 0, 25);
		this.snout.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4);
		this.snout.setRotationPoint(0.0F, -1.5F, -3.5F);
		this.head.addChild(snout);
	}
	
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
    	EntityZephyroo zephyroo = ((EntityZephyroo) entity);
        if(zephyroo.isChild()) {
        	
        	GL11.glScalef(1.0F / 2.0F, 1.0F / 2.0F, 1.0F / 2.0F);
			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
        	
        }    	
    	super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.root.render(f5);
    }
	
	 private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
	        model.rotateAngleX = x;
	        model.rotateAngleY = y;
	        model.rotateAngleZ = z;
	}
	 
	 public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
	        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	        if (entity.motionX != 0.0 && entity.motionZ != 0.0) {
	            this.leg_right.rotateAngleX = (float)Math.cos(((EntityZephyroo)entity).getTimeTilJump() * 0.5f);
	            this.leg_left.rotateAngleX = (float)Math.cos(((EntityZephyroo)entity).getTimeTilJump() * 0.5f);
	            this.foot_right.rotateAngleX = this.leg_right.rotateAngleX + 0.15f;
	            this.foot_left.rotateAngleX = this.leg_right.rotateAngleX + 0.15f;
	        }
	        else {
	            this.leg_right.rotateAngleX = 0.0f;
	            this.leg_left.rotateAngleX = 0.0f;
	            this.foot_right.rotateAngleX = this.leg_right.rotateAngleX + 0.15f;
	            this.foot_left.rotateAngleX = this.leg_right.rotateAngleX + 0.15f;
	        }
	    }

}
