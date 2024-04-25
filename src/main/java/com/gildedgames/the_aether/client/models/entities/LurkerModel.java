package com.gildedgames.the_aether.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class LurkerModel extends ModelBase
{
	private static final String __OBFID = "CL_00000865";
	
	private final ModelRenderer skullupper;
	private final ModelRenderer body;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftarm;
	private final ModelRenderer lowerskull;
	private final ModelRenderer neck;
	private final ModelRenderer hoodtop;
	private final ModelRenderer leftrobe;
	private final ModelRenderer collar;
	private final ModelRenderer s1;
	private final ModelRenderer s2;
	private final ModelRenderer righthood;
	private final ModelRenderer lefthood;
	private final ModelRenderer fronthood;
	//private final ModelRenderer staffcore;
	//private final ModelRenderer staffgem;
    
    public LurkerModel() {
        this(0.0f);
    }
    
    public LurkerModel(final float f) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.skullupper = new ModelRenderer(this, 97, 0)).addBox(-4.5f, -8.0f, -4.5f, 7, 7, 7, f); //6 5 6  104
        this.skullupper.setRotationPoint(1.0f, -7.0f, 1.0f);
        this.skullupper.setTextureSize(128, 64);
        this.skullupper.mirror = true;
        this.setRotation(this.skullupper, 0.0f, 0.0f, 0.0f);
        (this.body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 15, 4, f);
        this.body.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.body.setTextureSize(128, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        (this.rightarm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        this.rightarm.setRotationPoint(-5.0f, -3.0f, 0.0f);
        this.rightarm.setTextureSize(128, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.3490659f);
        (this.leftarm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        this.leftarm.setRotationPoint(5.0f, -3.0f, 0.0f);
        this.leftarm.setTextureSize(128, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, -0.3490659f);
        (this.lowerskull = new ModelRenderer(this, 82, 0)).addBox(-4.0f, -8.0f, -4.0f, 6, 1, 5, f);
        this.lowerskull.setRotationPoint(1.0f, -2.0f, 2.0f);
        this.lowerskull.setTextureSize(128, 64);
        this.lowerskull.mirror = true;
        this.setRotation(this.lowerskull, 0.0f, 0.0f, 0.0f);
        (this.neck = new ModelRenderer(this, 0, 0)).addBox(-4.5f, -8.0f, -4.5f, 3, 4, 3, f); // 2 4 2 
        this.neck.setRotationPoint(3.0f, -1.0f, 3.0f);
        this.neck.setTextureSize(128, 64);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        (this.hoodtop = new ModelRenderer(this, 30, 56)).addBox(-4.0f, -8.0f, -4.0f, 8, 0, 8, f);
        this.hoodtop.setRotationPoint(0.0f, -7.5f, 0.0f);
        this.hoodtop.setTextureSize(128, 64);
        this.hoodtop.mirror = true;
        this.setRotation(this.hoodtop, 0.0f, 0.0f, 0.0f);
        (this.leftrobe = new ModelRenderer(this, 16, 33)).addBox(-4.0f, 0.0f, -2.0f, 0, 13, 6, f);
        this.leftrobe.setRotationPoint(0.0f, 11.0f, -1.0f);
        this.leftrobe.setTextureSize(128, 64);
        this.leftrobe.mirror = true;
        this.setRotation(this.leftrobe, 0.0f, 0.0f, 0.3490659f);
        (this.collar = new ModelRenderer(this, 40, 52)).addBox(-4.0f, 0.0f, -2.0f, 8, 11, 0, f);
        this.collar.setRotationPoint(0.0f, -15.5f, 6.0f);
        this.collar.setTextureSize(128, 64);
        this.collar.mirror = true;
        this.setRotation(this.collar, 0.0f, 0.0f, 0.0f);
        (this.s1 = new ModelRenderer(this, 16, 33)).addBox(-4.0f, 0.0f, -2.0f, 0, 13, 6, f);
        this.s1.setRotationPoint(8.0f, 8.0f, -1.0f);
        this.s1.setTextureSize(128, 64);
        this.s1.mirror = true;
        this.setRotation(this.s1, 0.0f, 0.0f, -0.3490659f);
        (this.s2 = new ModelRenderer(this, 16, 52)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 0, f);
        this.s2.setRotationPoint(0.0f, 9.0f, 4.0f);
        this.s2.setTextureSize(128, 64);
        this.s2.mirror = true;
        this.setRotation(this.s2, 0.3490659f, 0.0f, 0.0f);
        (this.righthood = new ModelRenderer(this, 39, 40)).addBox(-4.0f, 0.0f, -2.0f, 0, 11, 8, f);
        this.righthood.setRotationPoint(0.0f, -15.5f, -2.0f);
        this.righthood.setTextureSize(128, 64);
        this.righthood.mirror = true;
        this.setRotation(this.righthood, 0.0f, 0.0f, 0.0f);
        (this.lefthood = new ModelRenderer(this, 40, 41)).addBox(-4.0f, 0.0f, -2.0f, 0, 11, 8, f);
        this.lefthood.setRotationPoint(8.0f, -15.5f, -2.0f);
        this.lefthood.setTextureSize(128, 64);
        this.lefthood.mirror = true;
        this.setRotation(this.lefthood, 0.0f, 0.0f, 0.0f);
        (this.fronthood = new ModelRenderer(this, 47, 4)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 0, f);
        this.fronthood.setRotationPoint(0.0f, -15.5f, -2.0f);
        this.fronthood.setTextureSize(128, 64);
        this.fronthood.mirror = true;
        this.setRotation(this.fronthood, 0.0f, 0.0f, 0.0f);
       // (this.staffcore = new ModelRenderer(this, 58, 29)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 34, f);
        //this.staffcore.setRotationPoint(8.0f, -2.0f, -16.0f);
        //this.staffcore.setTextureSize(128, 64);
        //this.staffcore.mirror = true;
        //this.setRotation(this.staffcore, -0.3717861f, 0.0f, 0.0f);       
        
        //(this.staffgem = new ModelRenderer(this, 0, 58)).addBox(-1.0f, -1.0f, -3.0f, 3, 3, 3, f);
        //this.staffgem.setRotationPoint(8.0f, -2.0f, -16.0f);
        //this.staffgem.setTextureSize(128, 64);
        //this.staffgem.mirror = true;
       // this.setRotation(this.staffgem, -0.3717861f, 0.0f, 0.0f);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.skullupper.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.lowerskull.render(f5);
        this.neck.render(f5);
        this.hoodtop.render(f5);
        this.leftrobe.render(f5);
        this.collar.render(f5);
        this.s1.render(f5);
        this.s2.render(f5);
        this.righthood.render(f5);
        this.lefthood.render(f5);
        this.fronthood.render(f5);       
        //this.staffcore.render(f5);
        //this.staffgem.render(f5);
        
    }
    
    public int func_82897_a()
    {
        return 10;
    }
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        float f6 = MathHelper.sin(this.onGround * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * (float)Math.PI);
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
        this.leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.rightarm.rotateAngleX = 0.0F;
        this.leftarm.rotateAngleX = 0.0F;
        this.rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.rightarm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
}
