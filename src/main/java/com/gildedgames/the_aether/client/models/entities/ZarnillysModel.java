package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ZarnillysModel extends ModelBase {
	private final ModelRenderer body1;
	private final ModelRenderer body2;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer leftleg_front_top;
	private final ModelRenderer rightleg_front_top;
	private final ModelRenderer leftleg_back_top;
	private final ModelRenderer rightleg_back_top;
	private final ModelRenderer leftleg_front;
	private final ModelRenderer rightleg_front;
	private final ModelRenderer leftleg_back;
	private final ModelRenderer rightleg_back;
	private final ModelRenderer neck;
	private final ModelRenderer head_top;
	private final ModelRenderer crest;
	private final ModelRenderer head_bottom;
	
	private final ModelRenderer spike1;
	private final ModelRenderer spike2;
	private final ModelRenderer spike3;
	private final ModelRenderer spike4;
	private final ModelRenderer spike5;
	private final ModelRenderer spike6;
	
	private final ModelRenderer spike1_back;
	private final ModelRenderer spike2_back;
	private final ModelRenderer spike3_back;
	private final ModelRenderer spike4_back;
	
	private final ModelRenderer rightteeth_bottom;
	private final ModelRenderer leftteeth_bottom;
	private final ModelRenderer rightteeth_top;
	private final ModelRenderer leftteeth_top;

	public ZarnillysModel() {
		textureWidth = 128;
		textureHeight = 128;
		
		(this.body1 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(0.0f, 24.0F, 0.0F);
        this.body1.addBox(-7.0F, -16.0F, -6.0F, 14, 12, 14, 0.0f);
        this.setRotationAngle(this.body1, 0.0f, 0.0f, 0.0f);
        
        (this.body2 = new ModelRenderer((ModelBase)this, 0, 29)).setRotationPoint(5.0F, -4.0F, 8.5F); 
        this.body2.addBox(-11.0F, -11.0F, -1.0F, 12, 11, 10, 0.0f);
        body1.addChild(body2);
        this.setRotationAngle(this.body2, -0.0436F, 0.0f, 0.0f);
        
        (this.tail1 = new ModelRenderer((ModelBase)this, 0, 53)).setRotationPoint(4.0F, 21.0F, 17.5F); 
        this.tail1.addBox(-9.0F, -10.0F, -1.0F, 10, 10, 8, 0.0f);
        this.setRotationAngle(this.tail1, -0.0873F, 0.0f, 0.0f); 

        (this.tail2 = new ModelRenderer((ModelBase)this, 0, 74)).setRotationPoint(-1.0F, -0.5F, 6.5F); 
        this.tail2.addBox(-7.0F, -9.0F, -1.0F, 8, 9, 7, 0.0f);
        this.setRotationAngle(this.tail2, -0.1309F, 0.0f, 0.0f);
        this.tail1.addChild(this.tail2);
        
        (this.tail3 = new ModelRenderer((ModelBase)this, 0, 93)).setRotationPoint(-1.0F, 0.5F, 6.0F);
        this.tail3.addBox(-5.0F, -8.0F, -1.0F, 6, 8, 5, 0.0f);
        this.setRotationAngle(this.tail3, -0.1309F, 0.0f, 0.0f); 
        this.tail2.addChild(this.tail3);
        
        (this.tail4 = new ModelRenderer((ModelBase)this, 0, 109)).setRotationPoint(-1.0F, -0.5F, 3.5F);
        this.tail4.addBox(-3.0F, -4.0F, -1.0F, 4, 4, 8, 0.0f);
        this.setRotationAngle(this.tail4, -0.1745F, 0.0f, 0.0f);
        this.tail3.addChild(this.tail4);
               
        (this.leftleg_front_top = new ModelRenderer((ModelBase)this, 59, 0)).setRotationPoint(10.0F, -10.0F, -3.0F);
        this.leftleg_front_top.addBox(-4.0F, -5.0F, -1.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.leftleg_front_top, 0.0F, 0.0F, 0.2182F);
        body1.addChild(leftleg_front_top);
        
        (this.rightleg_front_top = new ModelRenderer((ModelBase)this, 59, 12)).setRotationPoint(-9.0F, -10.0F, -3.0F);
        this.rightleg_front_top.addBox(-2.0F, -5.0F, -1.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.rightleg_front_top, 0.0F, 0.0F, -0.2182F);
        body1.addChild(rightleg_front_top);
        
        (this.leftleg_back_top = new ModelRenderer((ModelBase)this, 59, 0)).setRotationPoint(9.0F, -8.0F, 11.0F);
        this.leftleg_back_top.addBox(-4.0F, -5.0F, 0.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.leftleg_back_top, 0.0F, 0.0F, 0.2182F);
        body1.addChild(leftleg_back_top);
        
        (this.rightleg_back_top = new ModelRenderer((ModelBase)this, 59, 12)).setRotationPoint(-8.0F, -8.0F, 11.0F);
        this.rightleg_back_top.addBox(-2.0F, -5.0F, 0.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.rightleg_back_top, 0.0F, 0.0F, -0.2182F);
        body1.addChild(rightleg_back_top);
        
        (this.leftleg_front = new ModelRenderer((ModelBase)this, 61, 42)).setRotationPoint(8.5f, -3.0f, 3.0f); 
        this.leftleg_front.addBox(-11.0F, 0.0F, -3.0F, 3, 11, 3, 0.0f);
        this.leftleg_front_top.addChild(this.leftleg_front);
        this.setRotationAngle(this.leftleg_front, 0.0F, 0.0F, -0.2182F);
		
        (this.rightleg_front = new ModelRenderer((ModelBase)this, 76, 42)).setRotationPoint(-9.5f, -3.0f, 3.0f); 
        this.rightleg_front.addBox(8.0F, 0.0F, -3.0F, 3, 11, 3, 0.0f); 
        this.rightleg_front_top.addChild(this.rightleg_front);
        this.setRotationAngle(this.rightleg_front, 0.0F, 0.0F, 0.2182F);
        
        (this.rightleg_back = new ModelRenderer((ModelBase)this, 61, 42)).setRotationPoint(0.5F, -2.5F, 2.0F); 
        this.rightleg_back.addBox(-2.0F, 0.0F, -1.0F, 3, 10, 3, 0.0f); 
        this.rightleg_back_top.addChild(this.rightleg_back);
        this.setRotationAngle(this.rightleg_back, -0.1745F, 0.0F, 0.1745F); 
        
        (this.leftleg_back = new ModelRenderer((ModelBase)this, 61, 42)).setRotationPoint(-0.5F, -2.0F, 2.0F); 
        this.leftleg_back.addBox(-2.0F, 0.0F, -1.0F, 3, 10, 3, 0.0f); 
        this.leftleg_back_top.addChild(this.leftleg_back);
        this.setRotationAngle(this.leftleg_back, -0.1745F, 0.0F, -0.1745F); 
        
        (this.spike1 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(1.0F, -1.0F, 0.0F); 
        this.spike1.addBox(-6.0F, -17.0F, -1.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike1);
        
        (this.spike2 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(1.0F, -1.0F, 0.0F);
        this.spike2.addBox(-6.0F, -17.0F, 3.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike2);
        
        (this.spike3 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(1.0F, -1.0F, 0.0F);
        this.spike3.addBox(-6.0F, -17.0F, -5.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike3);
                
        (this.spike4 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(-1.0F, -1.0F, 0.0F); 
        this.spike4.addBox(6.0F, -17.0F, -5.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike4);
        
        (this.spike5 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(-1.0F, -1.0F, 0.0F);
        this.spike5.addBox(6.0F, -17.0F, 3.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike5);
        
        (this.spike6 = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(-1.0F, -1.0F, 0.0F);
        this.spike6.addBox(6.0F, -17.0F, -1.0F, 0, 2, 3, 0.0f);
        this.body1.addChild(this.spike6);
        
        (this.spike1_back = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(-2.0F, -12.0F, 20.0F); 
        this.spike1_back.addBox(1.0F, -1.0F, -15.0F, 0, 2, 3, 0.0f);
        this.body2.addChild(this.spike1_back);
        
        (this.spike2_back = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(0.0F, -12.0F, 20.0F); 
        this.spike2_back.addBox(-9.0F, -1.0F, -15.0F, 0, 2, 3, 0.0f);
        this.body2.addChild(this.spike2_back);
        
        (this.spike3_back = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(-2.0F, -12.0F, 16.0F); 
        this.spike3_back.addBox(1.0F, -1.0F, -15.0F, 0, 2, 3, 0.0f);
        this.body2.addChild(this.spike3_back);
        
        (this.spike4_back = new ModelRenderer((ModelBase)this, 93, 48)).setRotationPoint(0.0F, -12.0F, 16.0F); 
        this.spike4_back.addBox(-9.0F, -1.0F, -15.0F, 0, 2, 3, 0.0f);
        this.body2.addChild(this.spike4_back);
        
        (this.neck = new ModelRenderer((ModelBase)this, 96, 66)).setRotationPoint(4.0f, -4.0f, -10.0f); 
        this.neck.addBox(-9.0F, -11.0F, -1.0F, 10, 11, 6, 0.0f);
        this.setRotationAngle(this.neck, 0.0873F, 0.0f, 0.0f);
        this.body1.addChild(this.neck);
        
        (this.head_top = new ModelRenderer((ModelBase)this, 59, 66)).setRotationPoint(-1.0f, -7.0f, -9.5f);
        this.head_top.addBox(-7.0F, -5.0F, -1.0F, 8, 5, 10, 0.0f);
        this.setRotationAngle(this.head_top, 0.0873F, 0.0f, 0.0f); 
        this.neck.addChild(this.head_top);
        
        (this.crest = new ModelRenderer((ModelBase)this, 99, 86)).setRotationPoint(0.0f, -11.0f, 0.0f);
        this.crest.addBox(-9.0F, -3.0F, -1.0F, 10, 3, 4, 0.0f);
        this.neck.addChild(this.crest);
        
        (this.head_bottom = new ModelRenderer((ModelBase)this, 60, 84)).setRotationPoint(-2.0f, -2.0f, -1.5f); 
        this.head_bottom.addBox(-5.0F, -2.0F, -9.0F, 6, 2, 10, 0.0f);

        this.neck.addChild(this.head_bottom);
        this.head_top.addBox(-7.0F, -5.0F, -1.0F, 8, 5, 10, 0.0f);
        this.setRotationAngle(this.head_top, 0.0873F, 0.0f, 0.0f);
        this.neck.addChild(this.head_bottom);
                
        (this.rightteeth_bottom = new ModelRenderer((ModelBase)this, 61, 94)).setRotationPoint(-2.0f, 4.0f, 12.0f); 
        this.rightteeth_bottom.addBox(-2.0F, -7.0F, -19.0F, 0, 2, 7, 0.0f); 
        this.head_bottom.addChild(this.rightteeth_bottom);
        
        (this.leftteeth_bottom = new ModelRenderer((ModelBase)this, 61, 94)).setRotationPoint(2.0f, 4.0f, 12.0f); 
        this.leftteeth_bottom.addBox(-2.0F, -7.0F, -19.0F, 0, 2, 7, 0.0f); 
        this.head_bottom.addChild(this.leftteeth_bottom);
        
        (this.rightteeth_top = new ModelRenderer((ModelBase)this, 61, 94)).setRotationPoint(-3.0f, 6.0f, 20.0f); 
        this.rightteeth_top.addBox(-2.0F, -7.0F, -19.0F, 0, 2, 7, 0.0f);
        this.head_top.addChild(this.rightteeth_top);
        
        (this.leftteeth_top = new ModelRenderer((ModelBase)this, 61, 94)).setRotationPoint(1.0f, 6.0f, 20.0f); 
        this.leftteeth_top.addBox(-2.0F, -7.0F, -19.0F, 0, 2, 7, 0.0f);
        this.head_top.addChild(this.leftteeth_top);
        
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body1.render(f5);
		tail1.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
    public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.head_top.rotateAngleX = headPitch * 0.0008453292f;
        this.head_top.rotateAngleY = netHeadYaw * 0.0008453292f;
        
        this.rightleg_back.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 0.6f * limbSwingAmount;
        this.leftleg_back.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 0.6f * limbSwingAmount;      
        this.rightleg_front.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 0.6f * limbSwingAmount;
        this.leftleg_front.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 0.6f * limbSwingAmount;

        final float tailVal = limbSwing * 0.3f + ageInTicks * 0.06f;
        final float tailSway = MathHelper.cos(tailVal) / 3.0f;
        final float tailSwaySin = MathHelper.sin(tailVal) / 3.0f;
        this.tail1.rotateAngleY = tailSway;
        this.tail2.rotateAngleY = tailSwaySin;
        this.tail3.rotateAngleY = tailSway;
        this.tail4.rotateAngleY = tailSwaySin;
        final float tailBase = -0.08726646f;
        final float tail4Base = 0.17453292f;
        final float tailSwing = MathHelper.cos(limbSwing * 0.6662f) * 0.05f * limbSwingAmount;
        this.tail1.rotateAngleX = tailBase + tailSwing;
        this.tail2.rotateAngleX = tailBase + tailSwing;
        this.tail3.rotateAngleX = tailBase + tailSwing;
        this.tail4.rotateAngleX = tail4Base + tailSwing;

        float jawSpeed = 0.1F;
        float jawAmplitude = 0.05F;
        float jawAngle = MathHelper.sin(ageInTicks * jawSpeed) * jawAmplitude;
        this.head_bottom.rotateAngleX = 0.1309F + jawAngle;
    }
}
