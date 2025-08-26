package com.gildedgames.the_aether.entities.bosses.genesis_dragon;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class GenesisDragonModel extends ModelBase {

    private final ModelRenderer body;
    private final ModelRenderer tail1;
    private final ModelRenderer tail2;
    private final ModelRenderer tail3;
    private final ModelRenderer tail4;
    private final ModelRenderer tail5;
    private final ModelRenderer tail6;

    private final ModelRenderer neck;
    private final ModelRenderer head_back;
    private final ModelRenderer head_top; 
    private final ModelRenderer head_bottom;
    private final ModelRenderer crest;
    private final ModelRenderer right_horn_bottom;
    private final ModelRenderer right_horn_top;
    private final ModelRenderer left_horn_bottom;
    private final ModelRenderer left_horn_top;

    private final ModelRenderer top_right_leg;
    private final ModelRenderer bottom_right_leg;

    private final ModelRenderer back_right_leg;
    private final ModelRenderer back_bottom_right_leg;

    private final ModelRenderer top_left_leg;
    private final ModelRenderer top_bottom_left_leg;

    private final ModelRenderer back_left_leg;
    private final ModelRenderer back_bottom_left_leg;

    private final ModelRenderer top_teeth_1;
    private final ModelRenderer top_teeth_2; 
    private final ModelRenderer bottom_teeth_1;
    private final ModelRenderer bottom_teeth_2;

    private final ModelRenderer right_wing_base_1;
    private final ModelRenderer right_wing_base_2; 
    private final ModelRenderer right_wing_1;
    private final ModelRenderer right_wing_2;

    private final ModelRenderer left_wing_base_1;
    private final ModelRenderer left_wing_base_2;
    private final ModelRenderer left_wing_1;
    private final ModelRenderer left_wing_2;

    public GenesisDragonModel() {
        textureWidth = 384;
        textureHeight = 384;

        (this.body = new ModelRenderer(this, 0, 0)).setRotationPoint(9.5F, -0.5F, 0.0F);
        this.body.addBox(-24.5F, -22.0F, -3.0F, 28, 20, 40, 0.0f);
        this.setRotationAngle(this.body, -0.0436F, 0.0F, 0.0F);

        (this.tail1 = new ModelRenderer(this, 0, 64)).setRotationPoint(5.0F, 0.5F, -23.5F);
        this.tail1.addBox(-15.0F, -22.4844F, 60.4458F, 18, 18, 16, 0.0f);
        this.setRotationAngle(this.tail1, -0.0436F, 0.0F, 0.0F);

        (this.tail2 = new ModelRenderer(this, 0, 102)).setRotationPoint(-1.0F, 0.5F, -8.5F);
        this.tail2.addBox(-13.5F, -24.5828F, 83.2124F, 16, 16, 12);
        this.setRotationAngle(this.tail2, -0.0436F, 0.0F, 0.0F);
        this.tail1.addChild(this.tail2);

        (this.tail3 = new ModelRenderer(this, 0, 134)).setRotationPoint(-1.5F, -1.0F, 8.0F);
        this.tail3.addBox(-10.0F, -25.8548F, 86.354F, 12, 14, 12, 0.0f);
        this.setRotationAngle(this.tail3, -0.0436F, 0.0F, 0.0F);
        this.tail2.addChild(this.tail3);

        (this.tail4 = new ModelRenderer(this, 0, 164)).setRotationPoint(-0.5F, -4.5F, 6.5F);
        this.tail4.addBox(-8.5F, -23.8548F, 89.854F, 10, 12, 8, 0.0f);
        this.setRotationAngle(this.tail4, -0.0436F, 0.0F, 0.0F);
        this.tail3.addChild(this.tail4);

        (this.tail5 = new ModelRenderer(this, 0, 188)).setRotationPoint(-0.5F, 1.0F, 5.5F);
        this.tail5.addBox(-5.0F, -27.108F, 91.3534F, 6, 10, 10, 0.0f);
        this.setRotationAngle(this.tail5, -0.0436F, 0.0F, 0.0F);
        this.tail4.addChild(this.tail5);

        (this.tail6 = new ModelRenderer(this, 0, 214)).setRotationPoint(0.0F, -7.5F, 6.3F);
        this.tail6.addBox(-5.0F, -21.608F, 94.3534F, 6, 6, 12, 0.0f);
        this.setRotationAngle(this.tail6, -0.0436F, 0.0F, 0.0F);
        this.tail5.addChild(this.tail6);

        (this.neck = new ModelRenderer(this, 246, 50)).setRotationPoint(4.0F, -10.0F, -24.0F);
        this.neck.addBox(-12.0F, -14.0F, -2.0F, 14, 14, 24);
        this.setRotationAngle(this.neck, -0.1745F, 0.0F, 0.0F);

        (this.head_back = new ModelRenderer(this, 156, 52)).setRotationPoint(2.0F, -2.0F, 0.0F);
        this.head_back.addBox(-13.0F, -24.6218F, -32.9014F, 20, 18, 10);

        (this.head_top = new ModelRenderer(this, 150, 104)).setRotationPoint(-0.5F, -1.5F, -5.0F);
        this.head_top.addBox(-10.5F, -22.1218F, -47.9016F, 16, 10, 20, 0.0f);
        this.head_back.addChild(this.head_top);

        (this.head_bottom = new ModelRenderer(this, 152, 138)).setRotationPoint(2.0F, -2.0F, -3.0F);
        this.head_bottom.addBox(-12.0F, -12.6096F, -47.0406F, 14, 4, 20, 0.0f);
        this.setRotationAngle(this.head_bottom, 0.1309F, 0.0F, 0.0F);
        this.head_back.addChild(this.head_bottom);

        (this.crest = new ModelRenderer(this, 164, 86)).setRotationPoint(-1.5F, -2.0F, 5.5F);
        this.crest.addBox(-9.0F, -26.1218F, -39.4016F, 16, 6, 6, 0.0f);
        this.head_top.addChild(this.crest);

        (this.right_horn_bottom = new ModelRenderer(this, 174, 170)).setRotationPoint(-16.0F, -3.0F, 1.5F);
        this.right_horn_bottom.addBox(3.0F, -34.1218F, -39.4016F, 4, 14, 4, 0.0f);
        this.crest.addChild(this.right_horn_bottom);

        (this.right_horn_top = new ModelRenderer(this, 174, 170)).setRotationPoint(-24.0F, -8.0F, 0.0F);
        this.right_horn_top.addBox(23.0F, -34.1218F, -39.4016F, 4, 14, 4, 0.0f);
        this.right_horn_bottom.addChild(this.right_horn_top);

        (this.left_horn_bottom = new ModelRenderer(this, 194, 170)).setRotationPoint(4.0F, -3.0F, 1.5F);
        this.left_horn_bottom.addBox(3.0F, -34.1218F, -39.4016F, 4, 14, 4, 0.0f);
        this.crest.addChild(this.left_horn_bottom);

        (this.left_horn_top = new ModelRenderer(this, 194, 170)).setRotationPoint(-24.0F, -8.0F, 0.0F);
        this.left_horn_top.addBox(31.0F, -34.1218F, -39.4016F, 4, 14, 4, 0.0f);
        this.left_horn_bottom.addChild(this.left_horn_top);

        (this.top_right_leg = new ModelRenderer(this, 74, 128)).setRotationPoint(42.5F, 1.5F, 6.0F);
        this.top_right_leg.addBox(-74.5062F, -30.6378F, -2.0F, 10, 10, 10, 0.0f);
        this.setRotationAngle(this.top_right_leg, 0.0872F, 0.0F, -0.0872F);
        body.addChild(top_right_leg);

        (this.bottom_right_leg = new ModelRenderer(this, 82, 152)).setRotationPoint(-3.0F, -7.5F, -5.0F);
        this.bottom_right_leg.addBox(-71.5F, -18.0F, 4.5F, 6, 34, 6, 0.0f);
        this.top_right_leg.addChild(this.bottom_right_leg);
        this.setRotationAngle(this.bottom_right_leg, 0.0872F, 0.0F, 0.0436F);

        (this.back_right_leg = new ModelRenderer(this, 74, 104)).setRotationPoint(42.0F, 2.0F, 24.0F);
        this.back_right_leg.addBox(-74.5062F, -30.6378F, -2.0F, 10, 10, 10, 0.0f);
        this.setRotationAngle(this.back_right_leg, 0.0872F, 0.0F, -0.0872F);
        body.addChild(back_right_leg);

        (this.back_bottom_right_leg = new ModelRenderer(this, 112, 152)).setRotationPoint(32.0F, -6.5F, -5.0F);
        this.back_bottom_right_leg.addBox(-105.0F, -18.0F, 4.5F, 6, 34, 6, 0.0f);
        this.back_right_leg.addChild(this.back_bottom_right_leg);
        this.setRotationAngle(this.back_bottom_right_leg, 0.0872F, 0.0F, 0.0436F);

        (this.top_left_leg = new ModelRenderer(this, 74, 128)).setRotationPoint(-47.0F, 6.0F, 7.0F);
        this.top_left_leg.addBox(50.1722F, -29.1224F, -2.0F, 10, 10, 10, 0.0f);
        this.setRotationAngle(this.top_left_leg, 0.0436F, 0.1744F, 0.0F);
        body.addChild(top_left_leg);

        (this.top_bottom_left_leg = new ModelRenderer(this, 82, 152)).setRotationPoint(124.5F, -4.5F, -5.0F);
        this.top_bottom_left_leg.addBox(-71.5F, -18.0F, 4.5F, 6, 34, 6, 0.0f);
        this.top_left_leg.addChild(this.top_bottom_left_leg);
        this.setRotationAngle(this.top_bottom_left_leg, 0.0872F, 0.0F, 0.0436F);

        (this.back_left_leg = new ModelRenderer(this, 74, 104)).setRotationPoint(-48.0F, 6.0F, 25.0F);
        this.back_left_leg.addBox(50.1722F, -29.1224F, -2.0F, 10, 10, 10, 0.0f);
        this.setRotationAngle(this.back_left_leg, 0.0436F, 0.0F, 0.0F);
        body.addChild(back_left_leg);

        (this.back_bottom_left_leg = new ModelRenderer(this, 112, 152)).setRotationPoint(124.5F, -4.5F, -5.0F);
        this.back_bottom_left_leg.addBox(-71.5F, -18.0F, 4.5F, 6, 34, 6, 0.0f);
        this.back_left_leg.addChild(this.back_bottom_left_leg);
        this.setRotationAngle(this.back_bottom_left_leg, 0.0872F, 0.0F, 0.0436F);

        (this.top_teeth_1 = new ModelRenderer(this, 144, 156)).setRotationPoint(0.0F, -9.5F, -43.0F);
        this.top_teeth_1.addBox(2.0F, -5.0F, -2.0F, 0, 4, 14, 0.0f);
        this.head_top.addChild(top_teeth_1);

        (this.top_teeth_2 = new ModelRenderer(this, 144, 156)).setRotationPoint(-3.0F, -9.5F, -43.0F);
        this.top_teeth_2.addBox(-4.0F, -5.0F, -2.0F, 0, 4, 14, 0.0f);
        this.head_top.addChild(top_teeth_2);

        (this.bottom_teeth_1 = new ModelRenderer(this, 144, 156)).setRotationPoint(-2.5F, -10.0F, -42.5F);
        this.bottom_teeth_1.addBox(2.0F, -4.0F, -2.0F, 0, 4, 14, 0.0f);
        this.head_bottom.addChild(bottom_teeth_1);

        (this.bottom_teeth_2 = new ModelRenderer(this, 144, 156)).setRotationPoint(-11.5F, -10.0F, -42.5F);
        this.bottom_teeth_2.addBox(2.0F, -4.0F, -2.0F, 0, 4, 14, 0.0f);
        this.head_bottom.addChild(bottom_teeth_2);

        (this.right_wing_base_1 = new ModelRenderer(this, 70, 208)).setRotationPoint(-20.0F, -20.0F, -0.5F);
        this.right_wing_base_1.addBox(-66.0F, -4.0F, -2.0F, 68, 4, 4, 0.0f);
        body.addChild(right_wing_base_1);

        (this.right_wing_base_2 = new ModelRenderer(this, 70, 208)).setRotationPoint(-67.5F, 0.7F, 0.0F);
        this.right_wing_base_2.addBox(-66.0F, -4.0F, -2.0F, 68, 4, 4, 0.0f);
        this.right_wing_base_1.addChild(this.right_wing_base_2);

        (this.right_wing_1 = new ModelRenderer(this, -68, 246)).setRotationPoint(11.5F, 21.0F, 2.0F);
        this.right_wing_1.addBox(-79.0F, -23.0F, -0.5F, 68, 0, 68, 0.0f);
        this.right_wing_base_1.addChild(this.right_wing_1);

        (this.right_wing_2 = new ModelRenderer(this, 140, 316)).setRotationPoint(-37.0F, 35.0F, 2.0F);
        this.right_wing_2.addBox(-29.5F, -37.5F, -0.5F, 68, 0, 68, 0.0f);
        this.right_wing_base_2.addChild(this.right_wing_2);

        (this.left_wing_base_1 = new ModelRenderer(this, 70, 208)).setRotationPoint(-1.5F, -20.0F, -0.5F);
        this.left_wing_base_1.addBox(-2.0F, -4.0F, -2.0F, 68, 4, 4, 0.0f);
        body.addChild(this.left_wing_base_1);

        (this.left_wing_base_2 = new ModelRenderer(this, 70, 208)).setRotationPoint(67.5F, 0.7F, 0.0F);
        this.left_wing_base_2.addBox(-2.0F, -4.0F, -2.0F, 68, 4, 4, 0.0f);
        this.left_wing_base_1.addChild(this.left_wing_base_2);

        (this.left_wing_1 = new ModelRenderer(this, -68, 316)).setRotationPoint(-9.5F, 21.0F, 2.0F);
        this.left_wing_1.addBox(11.0F, -23.0F, -0.5F, 68, 0, 68, 0.0f);
        this.left_wing_base_1.addChild(this.left_wing_1);

        (this.left_wing_2 = new ModelRenderer((ModelBase)this, 140, 246)).setRotationPoint(38.0F, 35.0F, 2.0F); 
        this.left_wing_2.addBox(-38.5F, -37.5F, -0.5F, 68, 0, 68, 0.0f); 
        this.setRotationAngle(this.left_wing_2, 0.0F, 0.0F, 0.0F); 
        left_wing_base_2.addChild(left_wing_2);
        		
    }
    
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		tail1.render(f5);
		neck.render(f5);
		head_back.render(f5);
	}
	
	@Override
	public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn) {
	    super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

	    float flapSpeed = 0.5F;
	    float flapAmplitude = 0.4F;
	    float flap = MathHelper.sin(ageInTicks * flapSpeed) * flapAmplitude;

	    this.right_wing_base_1.rotateAngleZ = 0.6545F + flap;
	    this.right_wing_base_2.rotateAngleZ = -0.48F + flap * 0.5F;
	    this.left_wing_base_1.rotateAngleZ = -0.6545F - flap;
	    this.left_wing_base_2.rotateAngleZ = 0.48F - flap * 0.5F;
	    
	    if (entityIn instanceof EntityGenesisDragon) {
	    	EntityGenesisDragon dragon = (EntityGenesisDragon) entityIn;
	    	
	        double Speed = Math.sqrt(dragon.motionX * dragon.motionX + dragon.motionZ * dragon.motionZ);
	        double pitchRadians = Math.atan2(dragon.motionY, Speed);
	        float pitchDegrees = (float) Math.toDegrees(pitchRadians);

	        float angleDead = 7.5F;
	        float effectivePitch = 0.0F;

	        if (pitchDegrees > angleDead) {
	            effectivePitch = (pitchDegrees - angleDead);
	        } else if (pitchDegrees < -angleDead) {
	            effectivePitch = (pitchDegrees + angleDead);
	        }

	        float tailPitch = MathHelper.clamp_float(-effectivePitch * 0.005F, -0.04F, 0.04F);

	        this.tail1.rotateAngleX = tailPitch * 1.0F;
	        this.tail2.rotateAngleX = tailPitch * 0.75F;
	        this.tail3.rotateAngleX = tailPitch * 0.5F;
	        this.tail4.rotateAngleX = tailPitch * 0.3F;
	        this.tail5.rotateAngleX = tailPitch * 0.2F;
	        this.tail6.rotateAngleX = tailPitch * 0.1F;
	    }
	    	  
	    float jawSpeed = 0.1F;      
	    float jawAmplitude = 0.05F;
	    float jawAngle = MathHelper.sin(ageInTicks * jawSpeed) * jawAmplitude;
	    this.head_bottom.rotateAngleX = 0.1309F + jawAngle; 
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
    
    

