package com.gildedgames.the_aether.client.models.entities;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ElysianGuardianModel extends ModelBase
{
	public final ModelRenderer head;
    public final ModelRenderer neck;
    public final ModelRenderer upper_body;
    public final ModelRenderer upper_back;
    public final ModelRenderer right_shoulder;
    public final ModelRenderer right_upperarm;
    public final ModelRenderer right_lowerarm;
    public final ModelRenderer right_hand;
    public final ModelRenderer left_shoulder;
    public final ModelRenderer left_upperarm;
    public final ModelRenderer left_lowerarm;
    public final ModelRenderer left_hand;
    public final ModelRenderer mid_body;
    public final ModelRenderer core;
    public final ModelRenderer lower_body;
    public final ModelRenderer right_shirt;
    public final ModelRenderer left_shirt;
    public final ModelRenderer front_shirt;
    public final ModelRenderer top_right_leg;
    public final ModelRenderer right_lower_leg;
    public final ModelRenderer right_foot;
    public final ModelRenderer top_left_leg;
    public final ModelRenderer left_lower_leg;
    public final ModelRenderer left_foot;
    public final ModelRenderer blade;
    public final ModelRenderer hilt;
    public final ModelRenderer handle;
    
    public ElysianGuardianModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        
        (this.head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -9.0f, -5.0f, 6, 8, 8, 0.0f);
        this.head.setRotationPoint(0.0f, -15.0f, 4.0f);
        this.head.mirror = true;
        this.setRotateAngle(this.head, 0.1745329f, 0.0f, 0.0f);
        
        (this.neck = new ModelRenderer(this, 0, 11)).addBox(-2.0f, 0.0f, -2.5f, 4, 6, 5, 0.0f);
        this.neck.setRotationPoint(0.0f, -17.0f, 4.0f);
        this.neck.mirror = true;
        this.setRotateAngle(this.neck, 0.5235988f, 0.0f, 0.0f);
        
        (this.upper_body = new ModelRenderer(this, 0, 22)).addBox(-6.0f, 1.0f, -3.5f, 12, 7, 7, 0.0f);
        this.upper_body.setRotationPoint(0.0f, -13.0f, 6.0f);
        this.upper_body.mirror = true;
        this.setRotateAngle(this.upper_body, -0.5235988f, 0.0f, 0.0f);
        
        (this.upper_back = new ModelRenderer(this, 0, 47)).addBox(-5.5f, 0.0f, -3.5f, 11, 10, 7, 0.0f);
        this.upper_back.setRotationPoint(0.0f, -14.0f, 6.0f);
        this.upper_back.mirror = true;
        this.setRotateAngle(this.upper_back, 0.0f, 0.0f, 0.0f);
        
        (this.right_shoulder = new ModelRenderer(this, 38, 0)).addBox(-6.0f, -3.0f, -3.0f, 7, 6, 6, 0.0f);
        this.right_shoulder.setRotationPoint(-6.0f, -12.0f, 6.0f);
        this.right_shoulder.mirror = true;
        this.setRotateAngle(this.right_shoulder, 0.0f, 0.0f, 0.1745329f);
        
        (this.right_upperarm = new ModelRenderer(this, 54, 12)).addBox(-3.5f, 0.0f, -2.0f, 3, 11, 3, 0.0f);
        this.right_upperarm.setRotationPoint(-6.0f, -12.0f, 6.0f);
        this.right_upperarm.mirror = true;
        this.setRotateAngle(this.right_upperarm, 0.1745329f, -0.1745329f, 0.0872665f);
        
        (this.right_lowerarm = new ModelRenderer(this, 38, 12)).addBox(-4.0f, 7.0f, 4.0f, 4, 11, 4, 0.0f); //-4.0f, 7.0f, 4.0f
        this.right_lowerarm.setRotationPoint(1.0f, 2.0f, -1.0f); //-6.0f, -12.0f, 6.0f
        this.right_lowerarm.mirror = true;
        this.setRotateAngle(this.right_lowerarm, -0.3490659f, -0.1745329f, 0.0872665f); //0.0872665f
        
        (this.right_hand = new ModelRenderer(this, 38, 27)).addBox(-4.5f, 15.0f, 3.5f, 4, 6, 5, 0.0f);
        this.right_hand.setRotationPoint(1.0f, 2.0f, -1.0f); //1.0f, -4.0f, 6.0f
        this.right_hand.mirror = true;
        this.setRotateAngle(this.right_hand, -0.3490659f, -0.1745329f, 0.0872665f);
        
        (this.left_shoulder = new ModelRenderer(this, 64, 0)).addBox(-1.0f, -3.0f, -3.0f, 7, 6, 6, 0.0f);
        this.left_shoulder.setRotationPoint(6.0f, -12.0f, 6.0f);
        this.left_shoulder.mirror = true;
        this.setRotateAngle(this.left_shoulder, 0.0f, 0.0f, -0.1745329f);
        
        (this.left_upperarm = new ModelRenderer(this, 54, 12)).addBox(1.5f, 0.0f, -2.0f, 3, 11, 3, 0.0f);
        this.left_upperarm.setRotationPoint(5.0f, -12.0f, 6.0f);
        this.left_upperarm.mirror = true;
        this.setRotateAngle(this.left_upperarm, 0.1745329f, 0.1745329f, -0.0872665f);
        
        (this.left_lowerarm = new ModelRenderer(this, 38, 12)).addBox(1.0f, 7.0f, 4.0f, 4, 11, 4, 0.0f);
        this.left_lowerarm.setRotationPoint(-1.0f, 2.0f, 0.0f); //5.0f, -12.0f, 6.0f
        this.left_lowerarm.mirror = true;
        this.setRotateAngle(this.left_lowerarm, -0.3490659f, 0.1745329f, -0.0872665f);
        
        (this.left_hand = new ModelRenderer(this, 56, 27)).addBox(1.5f, 15.0f, 4.5f, 4, 6, 5, 0.0f);
        this.left_hand.setRotationPoint(-1.0f, 2.0f, -1.0f);
        this.left_hand.mirror = true;
        this.setRotateAngle(this.left_hand, -0.3490659f, 0.1745329f, -0.0872665f);
        
        (this.mid_body = new ModelRenderer(this, 0, 36)).addBox(-4.5f, 8.0f, -4.5f, 9, 5, 6, 0.0f);
        this.mid_body.setRotationPoint(0.0f, -16.0f, 1.0f);
        this.mid_body.mirror = true;
        this.setRotateAngle(this.mid_body, 0.4363323f, 0.0f, 0.0f);
        
        (this.core = new ModelRenderer(this, 90, 0)).addBox(-3.0f, 10.0f, -2.5f, 6, 4, 5, 0.0f);
        this.core.setRotationPoint(0.0f, -14.0f, 6.0f);
        this.core.mirror = true;
        this.setRotateAngle(this.core, 0.0f, 0.0f, 0.0f);
        
        (this.lower_body = new ModelRenderer(this, 90, 9)).addBox(-4.0f, 0.0f, -4.5f, 8, 5, 7, 0.0f);
        this.lower_body.setRotationPoint(0.0f, 0.0f, 7.0f);
        this.lower_body.mirror = true;
        this.setRotateAngle(this.lower_body, 0.0f, 0.0f, 0.0f);
        
        (this.right_shirt = new ModelRenderer(this, 102, 21)).addBox(-2.5f, -4.0f, -4.0f, 5, 8, 8, 0.0f);
        this.right_shirt.setRotationPoint(-3.0f, 5.0f, 6.0f);
        this.right_shirt.mirror = true;
        this.setRotateAngle(this.right_shirt, 0.0f, 0.0f, 0.1745329f);
        
        (this.left_shirt = new ModelRenderer(this, 76, 21)).addBox(-2.5f, -4.0f, -4.0f, 5, 8, 8, 0.0f);
        this.left_shirt.setRotationPoint(3.0f, 5.0f, 6.0f);
        this.left_shirt.mirror = true;
        this.setRotateAngle(this.left_shirt, 0.0f, 0.0f, -0.1745329f);
        
        (this.front_shirt = new ModelRenderer(this, 90, 37)).addBox(-2.5f, 1.0f, -5.5f, 5, 9, 9, 0.0f);
        this.front_shirt.setRotationPoint(0.0f, 0.0f, 7.0f);
        this.front_shirt.mirror = true;
        this.setRotateAngle(this.front_shirt, 0.0f, 0.0f, 0.0f);
        		
        (this.top_right_leg = new ModelRenderer(this, 54, 12)).addBox(-1.5f, 0.0f, -1.5f, 3, 10, 3, 0.0f);
        this.top_right_leg.setRotationPoint(-3.0f, 5.0f, 6.0f); //-3.0f, 5.0f, 6.0f
        this.top_right_leg.mirror = true;
        this.setRotateAngle(this.top_right_leg, -0.0872665f, 0.0f, 0.0349066f);
        //
        (this.right_lower_leg = new ModelRenderer(this, 38, 38)).addBox(-2.0f, 6.0f, -4.0f, 4, 10, 4, 0.0f);
        this.right_lower_leg.setRotationPoint(0.5f, 0.0f, 0.0f); //-3.0f, 5.0f, 6.0f
        this.right_lower_leg.mirror = true;
        this.setRotateAngle(this.right_lower_leg, 0.0872665f, 0.0f, 0.0349066f);
        //
        (this.right_foot = new ModelRenderer(this, 38, 52)).addBox(-1.5f, 14.0f, -11.0f, 3, 4, 7, 0.0f);
        this.right_foot.setRotationPoint(0.5f, 0.0f, 0.0f);
        this.right_foot.mirror = true;
        this.setRotateAngle(this.right_foot, 0.3490659f, 0.0f, 0.0349066f);
        
        (this.top_left_leg = new ModelRenderer(this, 54, 12)).addBox(-1.5f, 0.0f, -1.5f, 3, 10, 3, 0.0f);
        this.top_left_leg.setRotationPoint(3.0f, 5.0f, 6.0f);
        this.top_left_leg.mirror = true;
        this.setRotateAngle(this.top_left_leg, -0.0872665f, 0.0f, -0.0349066f);
        
        (this.left_lower_leg = new ModelRenderer(this, 38, 38)).addBox(-2.0f, 6.0f, -4.0f, 4, 10, 4, 0.0f);
        this.left_lower_leg.setRotationPoint(-0.5f, 0.0f, 0.0f); //3.0f, 5.0f, 6.0f
        this.left_lower_leg.mirror = true;
        this.setRotateAngle(this.left_lower_leg, 0.0872665f, 0.0f, -0.0349066f);
        
        (this.left_foot = new ModelRenderer(this, 38, 52)).addBox(-1.5f, 14.0f, -11.0f, 3, 4, 7, 0.0f);
        this.left_foot.setRotationPoint(-0.5f, 0.0f, 0.0f);
        this.left_foot.mirror = true;
        this.setRotateAngle(this.left_foot, 0.3490659f, 0.0f, -0.0349066f);
        
        (this.blade = new ModelRenderer(this, 74, 37)).addBox(3.0f, 1.0f, 14.5f, 1, 20, 7, 0.0f);
        this.blade.setRotationPoint(-1.0f, 2.0f, 0.0f); //5.0f, -12.0f, 6.0f
        this.blade.mirror = true;
        this.setRotateAngle(this.blade, -1.919862f, 0.1745329f, -0.0872665f);
        
        (this.hilt = new ModelRenderer(this, 66, 38)).addBox(2.5f, 12.5f, -2.5f, 2, 11, 2, 0.0f);
        this.hilt.setRotationPoint(-1.0f, 2.0f, -1.0f); //5.0f, -12.0f, 6.0f
        this.hilt.mirror = true;
        this.setRotateAngle(this.hilt, -0.3490659f, 0.0f, 0.0f);
        		
        (this.handle = new ModelRenderer(this, 66, 38)).addBox(2.0f, -12.0f, 19.0f, 2, 11, 2, 0.0f);
        this.handle.setRotationPoint(-1.0f, 0.0f, -1.0f); //5.0f, -14.0f, 6.0f
        this.handle.mirror = true;
        this.setRotateAngle(this.handle, -1.919862f, 0.1745329f, -0.0872665f);
        
        this.right_upperarm.addChild(this.right_lowerarm);
        this.right_upperarm.addChild(this.right_hand);
        
        this.left_upperarm.addChild(this.left_lowerarm);
        this.left_upperarm.addChild(this.left_hand);
        
        this.left_upperarm.addChild(this.handle);
        this.left_upperarm.addChild(this.hilt);
        this.left_upperarm.addChild(this.blade);
        
        this.top_right_leg.addChild(this.right_lower_leg);
        this.top_right_leg.addChild(this.right_foot);
        
        this.top_left_leg.addChild(this.left_lower_leg);
        this.top_left_leg.addChild(this.left_foot);
        
    }
    
    @Override
    public final void render(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
    	super.render(entity, n, n2, n3, n4, n5, n6);
    	this.setRotationAngles(n, n2, n3, n4, n5, n6, entity);
        this.head.render(n6);
        this.neck.render(n6);
        this.upper_body.render(n6);
        this.upper_back.render(n6);
        this.right_shoulder.render(n6);
        this.right_upperarm.render(n6);
        this.left_shoulder.render(n6);
        this.left_upperarm.render(n6);
        this.mid_body.render(n6);
        this.core.render(n6);
        this.lower_body.render(n6);
        this.right_shirt.render(n6);
        this.left_shirt.render(n6);
        this.front_shirt.render(n6);
        this.top_right_leg.render(n6);
        this.top_left_leg.render(n6);

    }
    
    private void setRotateAngle(final ModelRenderer modelRenderer, final float x, final float y, final float z) {
    	modelRenderer.rotateAngleX = x;
    	modelRenderer.rotateAngleY = y;
    	modelRenderer.rotateAngleZ = z;
    }
    
    @Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
    	this.head.rotateAngleY = netHeadYaw / 57.29578F;
		this.head.rotateAngleX = headPitch / 57.29578F;

		this.right_upperarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 2.0F * limbSwingAmount * 0.5F;
		this.left_upperarm.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F) * 2.0F * limbSwingAmount * 0.5F;
		this.right_upperarm.rotateAngleZ = 0.05F;
		this.left_upperarm.rotateAngleZ = -0.05F;
		this.top_right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.top_left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
		this.top_right_leg.rotateAngleY = 0.0F;
		this.top_left_leg.rotateAngleY = 0.0F;



		if (this.isRiding) {
			this.right_upperarm.rotateAngleX += -0.6283185F;
			this.left_upperarm.rotateAngleX += -0.6283185F;
			this.top_right_leg.rotateAngleX = -1.256637F;
			this.top_left_leg.rotateAngleX = -1.256637F;
			this.top_right_leg.rotateAngleY = 0.3141593F;
			this.top_left_leg.rotateAngleY = -0.3141593F;
		}

		this.right_upperarm.rotateAngleY = 0.0F;
		
		this.left_upperarm.rotateAngleY = 0.0F;

		if (this.onGround > -9990F) {
			float f6 = onGround;
			this.upper_back.rotateAngleY = this.upper_body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
			this.right_upperarm.rotateAngleY += this.upper_body.rotateAngleY;
			
			this.left_upperarm.rotateAngleY += this.upper_body.rotateAngleY;
			this.left_upperarm.rotateAngleX += this.upper_body.rotateAngleY;
			f6 = 1.0F - this.onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			float f7 = MathHelper.sin(f6 * 3.141593F);
			float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
			this.right_upperarm.rotateAngleX -= (double) f7 * 1.2D + (double) f8;
			this.right_upperarm.rotateAngleY += this.upper_body.rotateAngleY * 2.0F;
			this.right_upperarm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
		
		}

		this.right_upperarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		
		this.left_upperarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.right_upperarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		
		this.left_upperarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

	}

}
