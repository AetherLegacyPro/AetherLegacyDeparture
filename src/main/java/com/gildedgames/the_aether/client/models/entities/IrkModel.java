package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class IrkModel extends ModelBase {
	//Inspired by the louse model in HEE
    private final ModelRenderer bb_main;
    private final ModelRenderer left_front_leg_middle;
    private final ModelRenderer left_front_leg_bottom;
    private final ModelRenderer right_front_leg_middle;
    private final ModelRenderer right_front_leg_bottom;
    private final ModelRenderer left_middle_leg_middle;
    private final ModelRenderer left_middle_leg_bottom;
    private final ModelRenderer right_middle_leg_middle;
    private final ModelRenderer right_middle_leg_bottom;
    private final ModelRenderer left_back_leg_middle;
    private final ModelRenderer left_back_leg_bottom;
    private final ModelRenderer right_back_leg_middle;
    private final ModelRenderer right_back_leg_bottom;

    private final ModelRenderer antenna_left;
    private final ModelRenderer antenna_right;
    private final ModelRenderer back_leg_entrance;
    private final ModelRenderer front_leg_entrance;
    private final ModelRenderer body_back;
    private final ModelRenderer body_front;
    private final ModelRenderer bb_main2;

    public IrkModel() {
        textureWidth = 128;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 63, 0, -4.0F, -9.0F, -16.0F, 6, 6, 6, 0.0F)); // head
        bb_main.cubeList.add(new ModelBox(bb_main, 88, 0, -3.0F, -8.0F, -20.0F, 4, 4, 4, 0.0F)); // snout

        // ===== LEGS =====

        // Left Front
        left_front_leg_middle = new ModelRenderer(this);
        left_front_leg_middle.setRotationPoint(8.0F, -4.0F, -7.0F);
        bb_main.addChild(left_front_leg_middle);
        left_front_leg_middle.cubeList.add(new ModelBox(left_front_leg_middle, 100, 36, 0.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        left_front_leg_bottom = new ModelRenderer(this);
        left_front_leg_bottom.setRotationPoint(15.0F, 0.0F, 0.0F);
        left_front_leg_middle.addChild(left_front_leg_bottom);
        left_front_leg_bottom.cubeList.add(new ModelBox(left_front_leg_bottom, 114, 45, -8.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F));

        // Right Front
        right_front_leg_middle = new ModelRenderer(this);
        right_front_leg_middle.setRotationPoint(-8.0F, -4.0F, -7.0F);
        bb_main.addChild(right_front_leg_middle);
        right_front_leg_middle.cubeList.add(new ModelBox(right_front_leg_middle, 100, 36, -11.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        right_front_leg_bottom = new ModelRenderer(this);
        right_front_leg_bottom.setRotationPoint(-10.0F, 0.0F, 0.0F);
        right_front_leg_middle.addChild(right_front_leg_bottom);
        right_front_leg_bottom.cubeList.add(new ModelBox(right_front_leg_bottom, 114, 45, -1.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F));

        // Left Middle
        left_middle_leg_middle = new ModelRenderer(this);
        left_middle_leg_middle.setRotationPoint(6.0F, -5.0F, 6.0F);
        bb_main.addChild(left_middle_leg_middle);
        left_middle_leg_middle.cubeList.add(new ModelBox(left_middle_leg_middle, 100, 36, 1.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        left_middle_leg_bottom = new ModelRenderer(this);
        left_middle_leg_bottom.setRotationPoint(7.0F, 0.0F, 0.0F);
        left_middle_leg_middle.addChild(left_middle_leg_bottom);
        left_middle_leg_bottom.cubeList.add(new ModelBox(left_middle_leg_bottom, 114, 45, 1.0F, 0.0F, 0.25F, 2, 12, 2, 0.0F)); //1.0F, 0.0F, 1.0F

        // Right Middle
        right_middle_leg_middle = new ModelRenderer(this);
        right_middle_leg_middle.setRotationPoint(-10.0F, -5.0F, 6.0F);
        bb_main.addChild(right_middle_leg_middle);
        right_middle_leg_middle.cubeList.add(new ModelBox(right_middle_leg_middle, 100, 36, -9.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        right_middle_leg_bottom = new ModelRenderer(this);
        right_middle_leg_bottom.setRotationPoint(-7.0F, 0.0F, 0.0F);
        right_middle_leg_middle.addChild(right_middle_leg_bottom);
        right_middle_leg_bottom.cubeList.add(new ModelBox(right_middle_leg_bottom, 114, 45, -1.0F, -1.0F, -0.25F, 2, 12, 2, 0.0F)); //-1.0F, 0.0F, -1.0F

        // Left Back
        left_back_leg_middle = new ModelRenderer(this);
        left_back_leg_middle.setRotationPoint(8.0F, -4.0F, 17.0F);
        bb_main.addChild(left_back_leg_middle);
        left_back_leg_middle.cubeList.add(new ModelBox(left_back_leg_middle, 100, 36, 0.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        left_back_leg_bottom = new ModelRenderer(this);
        left_back_leg_bottom.setRotationPoint(7.0F, 0.0F, 0.0F);
        left_back_leg_middle.addChild(left_back_leg_bottom);
        left_back_leg_bottom.cubeList.add(new ModelBox(left_back_leg_bottom, 114, 45, 0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F)); //-1.0F, 0.0F, -1.0F
        
        // Right Back
        right_back_leg_middle = new ModelRenderer(this);
        right_back_leg_middle.setRotationPoint(-11.0F, -4.0F, 17.0F);
        bb_main.addChild(right_back_leg_middle);
        right_back_leg_middle.cubeList.add(new ModelBox(right_back_leg_middle, 100, 36, -9.0F, -4.0F, -1.0F, 10, 4, 4, 0.0F));

        right_back_leg_bottom = new ModelRenderer(this);
        right_back_leg_bottom.setRotationPoint(-7.0F, 0.0F, 0.0F);
        right_back_leg_middle.addChild(right_back_leg_bottom);
        right_back_leg_bottom.cubeList.add(new ModelBox(right_back_leg_bottom, 114, 45, -1.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F)); //-1.0F, 0.0F, -1.0F

        // ===== REST OF BODY =====
        antenna_left = new ModelRenderer(this); 
        antenna_left.setRotationPoint(-1.0F, -6.0F, -15.0F); 
        bb_main.addChild(antenna_left); setRotationAngle(antenna_left, 0.0F, -0.6109F, 0.0F); 
        antenna_left.cubeList.add(new ModelBox(antenna_left, 106, 10, 0.0F, -1.0F, -9.0F, 2, 2, 9, 0.0F)); 
        
        antenna_right = new ModelRenderer(this); antenna_right.setRotationPoint(-3.0F, -6.0F, -15.0F); 
        bb_main.addChild(antenna_right); setRotationAngle(antenna_right, 0.0F, 0.6109F, 0.0F); 
        antenna_right.cubeList.add(new ModelBox(antenna_right, 106, 22, 0.0F, -1.0F, -8.0F, 2, 2, 9, 0.0F));

        back_leg_entrance = new ModelRenderer(this);
        back_leg_entrance.setRotationPoint(8.0F, -3.0F, 12.0F);
        bb_main.addChild(back_leg_entrance);
        back_leg_entrance.cubeList.add(new ModelBox(back_leg_entrance, 68, 13, -1.0F, -6.0F, -3.0F, 2, 6, 16, 0.0F));
        back_leg_entrance.cubeList.add(new ModelBox(back_leg_entrance, 68, 13, -19.0F, -6.0F, -3.0F, 2, 6, 16, 0.0F));

        front_leg_entrance = new ModelRenderer(this);
        front_leg_entrance.setRotationPoint(7.0F, -3.0F, -7.0F);
        bb_main.addChild(front_leg_entrance);
        front_leg_entrance.cubeList.add(new ModelBox(front_leg_entrance, 73, 36, -1.0F, -6.0F, -1.0F, 2, 6, 14, 0.0F));
        front_leg_entrance.cubeList.add(new ModelBox(front_leg_entrance, 73, 36, -17.0F, -6.0F, -1.0F, 2, 6, 14, 0.0F));

        body_back = new ModelRenderer(this);
        body_back.setRotationPoint(6.0F, -2.0F, 8.0F);
        bb_main.addChild(body_back);
        body_back.cubeList.add(new ModelBox(body_back, 0, 34, -15.0F, -10.0F, -1.0F, 16, 10, 20, 0.0F));

        body_front = new ModelRenderer(this);
        body_front.setRotationPoint(5.0F, -1.0F, -9.0F);
        bb_main.addChild(body_front);
        body_front.cubeList.add(new ModelBox(body_front, 0, 0, -13.0F, -10.0F, -1.0F, 14, 10, 18, 0.0F));

        bb_main2 = new ModelRenderer(this);
        bb_main2.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main2.cubeList.add(new ModelBox(bb_main2, 0, 0, -5.0F, -8.0F, -15.0F, 1, 3, 3, 0.0F)); // eye1
        bb_main2.cubeList.add(new ModelBox(bb_main2, 0, 0, 2.0F, -8.0F, -15.0F, 1, 3, 3, 0.0F)); // eye2
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        bb_main.render(scale);
        bb_main2.render(scale);
    }
    
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) { 
    	modelRenderer.rotateAngleX = x; 
    	modelRenderer.rotateAngleY = y;
    	modelRenderer.rotateAngleZ = z; 
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

        float speed = 0.6662F;
        float degree = 0.8F;

        // Left legs
        this.left_front_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed) * degree * limbSwingAmount;
        this.left_middle_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed + (float)Math.PI) * degree * limbSwingAmount;
        this.left_back_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed) * degree * limbSwingAmount;

        // Right legs (opposite phase)
        this.right_front_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed + (float)Math.PI) * degree * limbSwingAmount;
        this.right_middle_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed) * degree * limbSwingAmount;
        this.right_back_leg_middle.rotateAngleY = MathHelper.cos(limbSwing * speed + (float)Math.PI) * degree * limbSwingAmount;

        // Tilt for spider stance
        this.left_front_leg_middle.rotateAngleZ = -0.2F;
        this.left_middle_leg_middle.rotateAngleZ = -0.2F;
        this.left_back_leg_middle.rotateAngleZ = -0.2F;

        this.right_front_leg_middle.rotateAngleZ = 0.2F;
        this.right_middle_leg_middle.rotateAngleZ = 0.2F;
        this.right_back_leg_middle.rotateAngleZ = 0.2F;
    }
}


