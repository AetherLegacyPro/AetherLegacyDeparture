package com.gildedgames.the_aether.client.models.entities;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.passive.EntityThunderlo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ThunderloModel extends ModelBase
{
	//fields
	ModelRenderer head1;
	ModelRenderer head2;
	ModelRenderer beard;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer horn1;
	ModelRenderer horn2;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer body;
	ModelRenderer mane;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;

	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}

	public ThunderloModel()
	{
		textureWidth = 128;
		textureHeight = 64;

		head1 = new ModelRenderer(this, 0, 12);
		head1.addBox(-4F, -6F, -5.5F, 8, 4, 7);
		head1.setRotationPoint(0F, 8.5F, -9.7F);
		head1.setTextureSize(128, 64);
		setRotation(head1, -0.0523599F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(0F, 0F, 0F, 7, 7, 5);
		head2.setRotationPoint(-3.5F, 5F, -14.5F);
		head2.setTextureSize(128, 64);
		setRotation(head2, -0.1047198F, 0F, 0F);
		convertToChild(head1, head2);
		beard = new ModelRenderer(this, 24, 0);
		beard.addBox(0F, 0F, 0F, 3, 5, 2);
		beard.setRotationPoint(-1.5F, 8.5F, -12F);
		beard.setTextureSize(128, 64);
		setRotation(beard, 0F, 0F, 0F);
		convertToChild(head1, beard);
		ear1 = new ModelRenderer(this, 24, 8);
		ear1.addBox(0F, 0F, 0F, 4, 2, 1);
		ear1.setRotationPoint(-6.5F, 6F, -12F);
		ear1.setTextureSize(128, 64);
		setRotation(ear1, 0F, 0F, -0.3490659F);
		convertToChild(head1, ear1);
		ear2 = new ModelRenderer(this, 24, 8);
		ear2.addBox(-1F, 0F, 0F, 4, 2, 1);
		ear2.setRotationPoint(3.5F, 5F, -12F);
		ear2.setTextureSize(128, 64);
		setRotation(ear2, 0F, 0F, 0.3490659F);
		convertToChild(head1, ear2);
		horn1 = new ModelRenderer(this, 24, 12);
		horn1.addBox(-4F, -5F, -4F, 1, 3, 1);
		horn1.setRotationPoint(0.5F, 5F, -8F);
		horn1.setTextureSize(128, 64);
		setRotation(horn1, 0F, 0F, -0.4886922F);
		convertToChild(head1, horn1);
		horn2 = new ModelRenderer(this, 24, 12);
		horn2.addBox(3F, -5F, -4F, 1, 3, 1);
		horn2.setRotationPoint(-0.5F, 5F, -8F);
		horn2.setTextureSize(128, 64);
		setRotation(horn2, 0F, 0F, 0.4886922F);
		convertToChild(head1, horn2);		
		(tail1 = new ModelRenderer(this, 87, 28)).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 8);
        tail1.setRotationPoint(0.0f, 5.5f, 13.0f); //-1.0f, 5.0f, 24.0f
        tail1.setTextureSize(128, 64);
        tail1.mirror = true;
        setRotation(this.tail1, -0.3141593f, 0.0f, 0.0f);        
        (this.tail2 = new ModelRenderer(this, 87, 47)).addBox(-3.0f, -1.0f, 9.0f, 6, 6, 6);
        tail2.setRotationPoint(0.0f, 5.5f, 13.5f);
        tail2.setTextureSize(128, 64);
        tail2.mirror = true;
        setRotation(this.tail2, -0.1396263f, 0.0f, 0.0f);	
		body = new ModelRenderer(this, 82, 0);
		body.addBox(-6F, -5F, -7F, 12, 10, 11);
		body.setRotationPoint(0F, 6F, 6F);
		body.setTextureSize(128, 64);
		setRotation(body, 1.570796F, 0F, 0F);
		mane = new ModelRenderer(this, 34, 0);
		mane.addBox(-6.5F, 0F, 0F, 13, 13, 11);
		mane.setRotationPoint(0F, 0.5F, -10F);
		mane.setTextureSize(128, 64);
		setRotation(mane, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 24);
		leg1.addBox(-2F, 0F, -2F, 4, 11, 4);
		leg1.setRotationPoint(-4F, 13F, 8F);
		leg1.setTextureSize(128, 64);
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 24);
		leg2.addBox(-2F, 0F, -2F, 4, 11, 4);
		leg2.setRotationPoint(4F, 13F, 8F);
		leg2.setTextureSize(128, 64);
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 24);
		leg3.addBox(-2F, 0F, -2F, 4, 11, 4);
		leg3.setRotationPoint(-4F, 13F, -6F);
		leg3.setTextureSize(128, 64);
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 24);
		leg4.addBox(-2F, 0F, -2F, 4, 11, 4);
		leg4.setRotationPoint(4F, 13F, -6F);
		leg4.setTextureSize(128, 64);
		setRotation(leg4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if (this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.9F / f6, 1.9F / f6, 1.9F / f6);
            GL11.glTranslatef(0.0F, 5.0F * f5, 2.0F * f5);
			head1.render(f5);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
			GL11.glTranslatef(0.0F, 8.0F * f5, 0.0F);
			tail1.render(f5);
			tail2.render(f5);
			body.render(f5);
			mane.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			GL11.glPopMatrix();
		}
		else
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(2.5F / f6, 2.5F / f6, 2.5F / f6);
			GL11.glTranslatef(0.0F, -4.8F * f5, 0.0F);
			head1.render(f5);
			tail1.render(f5);
			tail2.render(f5);
			body.render(f5);
			mane.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			GL11.glPopMatrix();
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        EntityThunderlo entitythunderlo = (EntityThunderlo)entity;
        float f6 = (180F / (float)Math.PI);
        this.head1.rotateAngleX = f4 / (180F / (float)Math.PI);
        this.head1.rotateAngleY = f3 / (200F / (float)Math.PI); //220
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        if (entitythunderlo.isSprinting())
        {
            this.head1.rotateAngleX += 0.5F;
        }
        else
        {
            this.head1.rotateAngleX += 0.0F;
        }
	}
}

