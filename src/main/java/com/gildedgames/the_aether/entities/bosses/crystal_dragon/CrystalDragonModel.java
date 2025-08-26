package com.gildedgames.the_aether.entities.bosses.crystal_dragon;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class CrystalDragonModel extends ModelBase {

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
	
	public CrystalDragonModel() { //+x left -xright //-z forward +z back
		textureWidth = 192;
		textureHeight = 192;

		(this.body = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.75F, -0.25F, 0.0F);
        this.body.addBox(-12.25F, -11.0F, -1.5F, 14, 10, 20, 0.0f);
        this.setRotationAngle(this.body, -0.0436F, 0.0F, 0.0F);
        
        (this.tail1 = new ModelRenderer((ModelBase)this, 0, 32)).setRotationPoint(2.5F, 0.25F, -11.75F);
        this.tail1.addBox(-7.5F, -11.2422F, 30.2229F, 9, 9, 8, 0.0f);
        this.setRotationAngle(this.tail1, -0.0436F, 0.0F, 0.0F);
                        
        (this.tail2 = new ModelRenderer((ModelBase)this, 0, 51)).setRotationPoint(-0.5F, 0.25F, -4.25F);
        this.tail2.addBox(-6.75F, -12.2914F, 41.6062F, 8, 8, 6);
        this.setRotationAngle(this.tail2, -0.0436F, 0.0F, 0.0F);
        this.tail1.addChild(this.tail2);
        
        (this.tail3 = new ModelRenderer((ModelBase)this, 0, 67)).setRotationPoint(-0.75F, -0.5F, 4.0F);
        this.tail3.addBox(-5.0F, -12.9274F, 43.177F, 6, 7, 6, 0.0f);
        this.setRotationAngle(this.tail3, -0.0436F, 0.0F, 0.0F); 
        this.tail2.addChild(this.tail3);

        (this.tail4 = new ModelRenderer((ModelBase)this, 0, 82)).setRotationPoint(-0.25F, -2.25F, 3.25F);
        this.tail4.addBox(-4.25F, -11.9274F, 44.927F, 5, 6, 4, 0.0f);
        this.setRotationAngle(this.tail4, -0.0436F, 0.0F, 0.0F);
        this.tail3.addChild(this.tail4);

        (this.tail5 = new ModelRenderer((ModelBase)this, 0, 94)).setRotationPoint(-0.25F, 0.5F, 2.75F);
        this.tail5.addBox(-2.5F, -13.554F, 45.6767F, 3, 5, 5, 0.0f);
        this.setRotationAngle(this.tail5, -0.0436F, 0.0F, 0.0F);
        this.tail4.addChild(this.tail5);              
        
        (this.tail6 = new ModelRenderer((ModelBase)this, 0, 107)).setRotationPoint(0.0F, -3.75F, 3.15F);
        this.tail6.addBox(-2.5F, -10.804F, 47.1767F, 3, 3, 6, 0.0f);
        this.setRotationAngle(this.tail6, -0.0436F, 0.0F, 0.0F);
        this.tail5.addChild(this.tail6);
        
        (this.neck = new ModelRenderer((ModelBase)this, 123, 25)).setRotationPoint(2.0F, -5.0F, -12.0F); 
        this.neck.addBox(-6.0F, -7.0F, -1.0F, 7, 7, 12);
        this.setRotationAngle(this.neck, -0.1745F, 0.0F, 0.0F);
        
        (this.head_back = new ModelRenderer((ModelBase)this, 78, 26)).setRotationPoint(1.0F, -1.0F, 0.0F);
        this.head_back.addBox(-6.5F, -12.3109F, -16.4507F, 10, 9, 5);
        this.setRotationAngle(this.head_back, 0.0F, 0.0F, 0.0F);
        
        (this.head_top = new ModelRenderer((ModelBase)this, 75, 52)).setRotationPoint(-0.25F, -0.75F, -2.5F);
        this.head_top.addBox(-5.25F, -11.0609F, -23.9508F, 8, 5, 10, 0.0f);
        this.setRotationAngle(this.head_top, 0.0F, 0.0F, 0.0F);
        this.head_back.addChild(this.head_top);
        
        (this.head_bottom = new ModelRenderer((ModelBase)this, 76, 69)).setRotationPoint(1.0F, -1.0F, -1.5F);
        this.head_bottom.addBox(-6.0F, -6.3048F, -23.5203F, 7, 2, 10, 0.0f);
        this.setRotationAngle(this.head_bottom, 0.1309F, 0.0F, 0.0F);
        this.head_back.addChild(this.head_bottom);
        
        (this.crest = new ModelRenderer((ModelBase)this, 82, 43)).setRotationPoint(-0.75F, -1.0F, 2.75F);
        this.crest.addBox(-4.5F, -13.0609F, -19.7008F, 8, 3, 3, 0.0f);
        this.setRotationAngle(this.crest, 0.0F, 0.0F, 0.0F);
        this.head_top.addChild(this.crest);
        
        (this.right_horn_bottom = new ModelRenderer((ModelBase)this, 87, 85)).setRotationPoint(-8.0F, -1.5F, 0.75F);
        this.right_horn_bottom.addBox(1.5F, -17.0609F, -19.7008F, 2, 7, 2, 0.0f);
        this.setRotationAngle(this.right_horn_bottom, 0.0F, 0.0F, 0.0F);
        this.crest.addChild(this.right_horn_bottom);
        
        (this.right_horn_top = new ModelRenderer((ModelBase)this, 87, 85)).setRotationPoint(-12.0F, -4.0F, 0.0F);
        this.right_horn_top.addBox(11.5F, -17.0609F, -19.7008F, 2, 7, 2, 0.0f);
        this.setRotationAngle(this.right_horn_top, 0.0F, 0.0F, 0.0F);
        this.right_horn_bottom.addChild(this.right_horn_top);
        
        (this.left_horn_bottom = new ModelRenderer((ModelBase)this, 97, 85)).setRotationPoint(2.0F, -1.5F, 0.75F);
        this.left_horn_bottom.addBox(1.5F, -17.0609F, -19.7008F, 2, 7, 2, 0.0f);
        this.setRotationAngle(this.left_horn_bottom, 0.0F, 0.0F, 0.0F);
        this.crest.addChild(this.left_horn_bottom);
        
        (this.left_horn_top = new ModelRenderer((ModelBase)this, 97, 85)).setRotationPoint(-12.0F, -4.0F, 0.0F);
        this.left_horn_top.addBox(15.5F, -17.0609F, -19.7008F, 2, 7, 2, 0.0f);
        this.setRotationAngle(this.left_horn_top, 0.0F, 0.0F, 0.0F);
        this.left_horn_bottom.addChild(this.left_horn_top);      
        
        (this.top_right_leg = new ModelRenderer((ModelBase)this, 37, 64)).setRotationPoint(21.25F, 0.75F, 3.0F);
        this.top_right_leg.addBox(-37.2531F, -14.8189F, -1.0F, 5, 5, 5, 0.0f); 
        this.setRotationAngle(this.top_right_leg, 0.0872F, 0.0F, -0.0872F); 
        body.addChild(top_right_leg);
        
        (this.bottom_right_leg = new ModelRenderer((ModelBase)this, 41, 76)).setRotationPoint(-1.50F, -3.75F, -2.5F);
        this.bottom_right_leg.addBox(-35.75F, -9.0F, 2.25F, 3, 17, 3, 0.0f); 
        this.top_right_leg.addChild(this.bottom_right_leg);
        this.setRotationAngle(this.bottom_right_leg, 0.0872F, 0.0F, 0.0436F);
        
        (this.back_right_leg = new ModelRenderer((ModelBase)this, 37, 52)).setRotationPoint(21.0F, 1.00F, 12.0F);
        this.back_right_leg.addBox(-37.2531F, -14.8189F, -1.0F, 5, 5, 5, 0.0f); 
        this.setRotationAngle(this.back_right_leg, 0.0872F, 0.0F, -0.0872F);
        body.addChild(back_right_leg);
        
        (this.back_bottom_right_leg = new ModelRenderer((ModelBase)this, 56, 76)).setRotationPoint(16.0F, -3.25F, -2.5F);
        this.back_bottom_right_leg.addBox(-52.5F, -9.0F, 2.25F, 3, 17, 3, 0.0f); 
        this.back_right_leg.addChild(this.back_bottom_right_leg);
        this.setRotationAngle(this.back_bottom_right_leg, 0.0872F, 0.0F, 0.0436F);
        
        (this.top_left_leg = new ModelRenderer((ModelBase)this, 37, 64)).setRotationPoint(-23.5F, 3.00F, 3.5F);
        this.top_left_leg.addBox(25.0861F, -14.5612F, -1.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.top_left_leg, 0.0436F, 0.1744F, 0.0F);
        body.addChild(top_left_leg);
             
        (this.top_bottom_left_leg = new ModelRenderer((ModelBase)this, 41, 76)).setRotationPoint(62.25F, -2.25F, -2.5F);
        this.top_bottom_left_leg.addBox(-35.75F, -9.0F, 2.25F, 3, 17, 3, 0.0f); 
        this.top_left_leg.addChild(this.top_bottom_left_leg);
        this.setRotationAngle(this.top_bottom_left_leg, 0.0872F, 0.0F, 0.0436F);
        
        (this.back_left_leg = new ModelRenderer((ModelBase)this, 37, 52)).setRotationPoint(-24.0F, 3.0F, 12.5F);
        this.back_left_leg.addBox(25.0861F, -14.5612F, -1.0F, 5, 5, 5, 0.0f);
        this.setRotationAngle(this.back_left_leg, 0.0436F, 0.0F, 0.0F);
        body.addChild(back_left_leg);
        
        (this.back_bottom_left_leg = new ModelRenderer((ModelBase)this, 56, 76)).setRotationPoint(62.25F, -2.25F, -2.5F);
        this.back_bottom_left_leg.addBox(-35.75F, -9.0F, 2.25F, 3, 17, 3, 0.0f); 
        this.back_left_leg.addChild(this.back_bottom_left_leg);
        this.setRotationAngle(this.back_bottom_left_leg, 0.0872F, 0.0F, 0.0436F);
        
        (this.top_teeth_1 = new ModelRenderer((ModelBase)this, 72, 78)).setRotationPoint(0.0F, -4.75F, -21.5F);
        this.top_teeth_1.addBox(1.0F, -2.5F, -1.0F, 0, 2, 7, 0.0f);
        this.setRotationAngle(this.top_teeth_1, 0.0F, 0.0F, 0.0F);
        head_top.addChild(top_teeth_1);
        
        (this.top_teeth_2 = new ModelRenderer((ModelBase)this, 72, 78)).setRotationPoint(-1.5F, -4.75F, -21.5F);
        this.top_teeth_2.addBox(-2.0F, -2.5F, -1.0F, 0, 2, 7, 0.0f);
        this.setRotationAngle(this.top_teeth_2, -0.0436F, 0.0F, 0.0F);
        head_top.addChild(top_teeth_2);
        
        (this.bottom_teeth_1 = new ModelRenderer((ModelBase)this, 72, 78)).setRotationPoint(-1.25F, -5.0F, -21.25F);
        this.bottom_teeth_1.addBox(1.0F, -2.0F, -1.0F, 0, 2, 7, 0.0f);
        this.setRotationAngle(this.bottom_teeth_1, 0.0F, 0.0F, 0.0F);
        head_bottom.addChild(bottom_teeth_1);
        
        (this.bottom_teeth_2 = new ModelRenderer((ModelBase)this, 72, 78)).setRotationPoint(-5.75F, -5.0F, -21.25F);
        this.bottom_teeth_2.addBox(1.0F, -2.0F, -1.0F, 0, 2, 7, 0.0f);
        this.setRotationAngle(this.bottom_teeth_2, 0.0F, 0.0F, 0.0F);
        head_bottom.addChild(bottom_teeth_2);
        
        (this.right_wing_base_1 = new ModelRenderer((ModelBase)this, 35, 104)).setRotationPoint(-10.0F, -10.0F, -0.25F);
        this.right_wing_base_1.addBox(-33.0F, -2.0F, -1.0F, 34, 2, 2, 0.0f);
        this.setRotationAngle(this.right_wing_base_1, 0.0F, 0.0F, 0.6545F);
        body.addChild(right_wing_base_1);
        
        (this.right_wing_base_2 = new ModelRenderer((ModelBase)this, 35, 104)).setRotationPoint(-33.75F, 0.35F, 0.0F);
        this.right_wing_base_2.addBox(-33.0F, -2.0F, -1.0F, 34, 2, 2, 0.0f);
        this.setRotationAngle(this.right_wing_base_2, 0.0F, 0.0F, -0.48F);
        right_wing_base_1.addChild(right_wing_base_2);
        
        (this.right_wing_1 = new ModelRenderer((ModelBase)this, -34, 123)).setRotationPoint(5.75F, 10.5F, 1.0F);
        this.right_wing_1.addBox(-39.5F, -11.5F, -0.25F, 34, 0, 34, 0.0f);
        this.setRotationAngle(this.right_wing_1, 0.0F, 0.0F, 0.0F);
        right_wing_base_1.addChild(right_wing_1);
        
        (this.right_wing_2 = new ModelRenderer((ModelBase)this, 70, 158)).setRotationPoint(-18.5F, 17.50F, 1.0F);
        this.right_wing_2.addBox(-14.75F, -18.75F, -0.25F, 34, 0, 34, 0.0f);
        this.setRotationAngle(this.right_wing_2, 0.0F, 0.0F, 0.0F);
        right_wing_base_2.addChild(right_wing_2);
        
        (this.left_wing_base_1 = new ModelRenderer((ModelBase)this, 35, 104)).setRotationPoint(-0.75F, -10.0F, -0.25F);
        this.left_wing_base_1.addBox(-1.0F, -2.0F, -1.0F, 34, 2, 2, 0.0f);
        this.setRotationAngle(this.left_wing_base_1, 0.0F, 0.0F, -0.6545F);
        body.addChild(left_wing_base_1);
        
        (this.left_wing_base_2 = new ModelRenderer((ModelBase)this, 35, 104)).setRotationPoint(33.75F, 0.35F, 0.0F); //21.75F, 0.35F, 0.0F
        this.left_wing_base_2.addBox(-1.0F, -2.0F, -1.0F, 34, 2, 2, 0.0f);
        this.setRotationAngle(this.left_wing_base_2, 0.0F, 0.0F, 0.48F);
        left_wing_base_1.addChild(left_wing_base_2);
        
        (this.left_wing_1 = new ModelRenderer((ModelBase)this, -34, 158)).setRotationPoint(-4.75F, 10.5F, 1.0F);
        this.left_wing_1.addBox(5.5F, -11.5F, -0.25F, 34, 0, 34, 0.0f);
        this.setRotationAngle(this.left_wing_1, 0.0F, 0.0F, 0.0F);
        left_wing_base_1.addChild(left_wing_1);
        
        (this.left_wing_2 = new ModelRenderer((ModelBase)this, 70, 123)).setRotationPoint(19.0F, 17.50F, 1.0F);
        this.left_wing_2.addBox(-19.25F, -18.75F, -0.25F, 34, 0, 34, 0.0f);
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
	    
	    if (entityIn instanceof EntityCrystalDragon) {
	    	EntityCrystalDragon dragon = (EntityCrystalDragon) entityIn;

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
