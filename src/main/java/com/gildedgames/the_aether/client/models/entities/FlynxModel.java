package com.gildedgames.the_aether.client.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import com.gildedgames.the_aether.entities.passive.EntityFlynx;

@SideOnly(Side.CLIENT)
public class FlynxModel extends ModelBase {

    ModelRenderer ocelotBackLeftLeg;
    ModelRenderer ocelotBackRightLeg;
    ModelRenderer ocelotFrontLeftLeg;
    ModelRenderer ocelotFrontRightLeg;
    ModelRenderer ocelotTail;
    ModelRenderer ocelotHead;
    ModelRenderer ocelotBody;
    ModelRenderer leftWingInner;
    ModelRenderer leftWingOuter;
    ModelRenderer rightWingInner;
    ModelRenderer rightWingOuter;

    int field_78163_i = 1;

    private static final float BODY_SCALE_X = 1.25F;
    private static final float BODY_SCALE_Y = 1.12F;
    private static final float BODY_SCALE_Z = 1.20F;

    private static final float TAIL_SCALE_X = 2.15F;
    private static final float TAIL_SCALE_Y = 1.25F;
    private static final float TAIL_SCALE_Z = 2.15F;

    private static final float WING_SCALE_X = 0.85F;
    private static final float WING_SCALE_Y = 0.85F;
    private static final float WING_SCALE_Z = 0.85F;

    private static final float SITTING_MODEL_Y_OFFSET = -1.5F;

    public FlynxModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);

        this.ocelotHead = new ModelRenderer(this, "head");
        this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);

        this.ocelotBody = new ModelRenderer(this, 20, 0);
        this.ocelotBody.addBox(-2.0F, 1.0F, -8.0F, 4, 16, 6, 0.0F);
        this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);

        this.ocelotTail = new ModelRenderer(this, 0, 15);
        this.ocelotTail.addBox(-0.5F, 0.5F, -0.5F, 1, 8, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0.0F, 15.0F, 10.0F);

        this.leftWingInner = new ModelRenderer(this, 0, 36);
        this.leftWingInner.addBox(-0.5F, -5.0F, 0.5F, 1, 10, 5, 0.0F);
        this.leftWingInner.setRotationPoint(2.4F, 14.0F, -3.0F);

        this.leftWingOuter = new ModelRenderer(this, 12, 36);
        this.leftWingOuter.addBox(-0.5F, -5.0F, -5.75F, 1, 10, 5, 0.0F);
        this.leftWingOuter.setRotationPoint(3.0F, 0.0F, 6.0F);
        this.leftWingInner.addChild(this.leftWingOuter);

        this.rightWingInner = new ModelRenderer(this, 0, 36);
        this.rightWingInner.addBox(-0.5F, -5.0F, 0.5F, 1, 10, 5, 0.0F);
        this.rightWingInner.setRotationPoint(-2.4F, 14.0F, -3.0F);

        this.rightWingOuter = new ModelRenderer(this, 12, 36);
        this.rightWingOuter.addBox(-0.5F, -5.0F, -5.75F, 1, 10, 5, 0.0F);
        this.rightWingOuter.setRotationPoint(-3.0F, 0.0F, 6.0F);
        this.rightWingInner.addChild(this.rightWingOuter);

        this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);

        this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);

        this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);

        this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    private void renderScaledPart(ModelRenderer part, float scaleX, float scaleY, float scaleZ, float renderScale) {
        float oldRotationPointX = part.rotationPointX;
        float oldRotationPointY = part.rotationPointY;
        float oldRotationPointZ = part.rotationPointZ;

        GL11.glPushMatrix();
        GL11.glTranslatef(oldRotationPointX * renderScale, oldRotationPointY * renderScale, oldRotationPointZ * renderScale);
        GL11.glScalef(scaleX, scaleY, scaleZ);

        part.rotationPointX = 0.0F;
        part.rotationPointY = 0.0F;
        part.rotationPointZ = 0.0F;

        part.render(renderScale);

        part.rotationPointX = oldRotationPointX;
        part.rotationPointY = oldRotationPointY;
        part.rotationPointZ = oldRotationPointZ;

        GL11.glPopMatrix();
    }

    private void renderBody(float renderScale) {
        this.renderScaledPart(this.ocelotBody, BODY_SCALE_X, BODY_SCALE_Y, BODY_SCALE_Z, renderScale);
    }

    private void renderTailParts(float renderScale) {
        this.renderScaledPart(this.ocelotTail, TAIL_SCALE_X, TAIL_SCALE_Y, TAIL_SCALE_Z, renderScale);
    }

    private void renderWings(float renderScale) {
        this.renderScaledPart(this.leftWingInner, WING_SCALE_X, WING_SCALE_Y, WING_SCALE_Z, renderScale);
        this.renderScaledPart(this.rightWingInner, WING_SCALE_X, WING_SCALE_Y, WING_SCALE_Z, renderScale);
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);

        if (this.isChild) {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 10.0F * p_78088_7_, 4.0F * p_78088_7_);
            this.ocelotHead.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.renderBody(p_78088_7_);
            this.renderWings(p_78088_7_);
            this.ocelotBackLeftLeg.render(p_78088_7_);
            this.ocelotBackRightLeg.render(p_78088_7_);
            this.ocelotFrontLeftLeg.render(p_78088_7_);
            this.ocelotFrontRightLeg.render(p_78088_7_);
            this.renderTailParts(p_78088_7_);
            GL11.glPopMatrix();
        } else {
            this.ocelotHead.render(p_78088_7_);
            this.renderBody(p_78088_7_);
            this.renderWings(p_78088_7_);
            this.renderTailParts(p_78088_7_);
            this.ocelotBackLeftLeg.render(p_78088_7_);
            this.ocelotBackRightLeg.render(p_78088_7_);
            this.ocelotFrontLeftLeg.render(p_78088_7_);
            this.ocelotFrontRightLeg.render(p_78088_7_);
        }
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
        this.ocelotHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.ocelotHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.setWingAngles(p_78087_1_, p_78087_2_, p_78087_3_);

        if (this.field_78163_i != 3) {
            this.ocelotBody.rotateAngleX = ((float)Math.PI / 2F);
            if (this.field_78163_i == 2) {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 0.3F) * 1.0F * p_78087_2_;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI + 0.3F) * 1.0F * p_78087_2_;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
            } else {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
            }
        }
    }

    private void setWingAngles(float limbSwing, float limbSwingAmount, float ageInTicks) {
        float flap = MathHelper.sin(ageInTicks * 0.45F) * 0.18F;
        float walkFlap = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount * 0.12F;
        float wingMotion = flap + walkFlap;
        float innerSpread = 0.55F + wingMotion;
        float outerBend = 0.38F + wingMotion * 0.35F;
        float wingYaw = 0.12F;

        if (this.field_78163_i == 3) {
            innerSpread = 0.22F + wingMotion * 0.20F;
            outerBend = 0.22F + wingMotion * 0.15F;
            wingYaw = 0.28F;
        } else if (this.field_78163_i == 2) {
            innerSpread = 0.80F + wingMotion * 0.75F;
            outerBend = 0.42F + wingMotion * 0.25F;
            wingYaw = 0.04F;
        } else if (this.field_78163_i == 0) {
            innerSpread = 0.35F + wingMotion * 0.20F;
            outerBend = 0.25F + wingMotion * 0.15F;
            wingYaw = 0.22F;
        }

        this.leftWingInner.rotateAngleX = 0.10F;
        this.leftWingInner.rotateAngleY = wingYaw;
        this.leftWingInner.rotateAngleZ = innerSpread;

        this.leftWingOuter.rotateAngleX = -0.10F;
        this.leftWingOuter.rotateAngleY = 0.0F;
        this.leftWingOuter.rotateAngleZ = -outerBend;

        this.rightWingInner.rotateAngleX = 0.10F;
        this.rightWingInner.rotateAngleY = -wingYaw;
        this.rightWingInner.rotateAngleZ = -innerSpread;

        this.rightWingOuter.rotateAngleX = -0.10F;
        this.rightWingOuter.rotateAngleY = 0.0F;
        this.rightWingOuter.rotateAngleZ = outerBend;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase livingBase, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        EntityFlynx entityflynx = (EntityFlynx)livingBase;

        this.ocelotBody.rotationPointY = 12.0F;
        this.ocelotBody.rotationPointZ = -10.0F;

        this.ocelotHead.rotationPointY = 15.0F;
        this.ocelotHead.rotationPointZ = -9.0F;

        this.ocelotTail.rotationPointY = 15.0F;
        this.ocelotTail.rotationPointZ = 10.0F;

        this.leftWingInner.rotationPointX = 2.4F;
        this.leftWingInner.rotationPointY = 14.0F;
        this.leftWingInner.rotationPointZ = -3.0F;

        this.leftWingOuter.rotationPointX = 3.0F;
        this.leftWingOuter.rotationPointY = 0.0F;
        this.leftWingOuter.rotationPointZ = 6.0F;

        this.rightWingInner.rotationPointX = -2.4F;
        this.rightWingInner.rotationPointY = 14.0F;
        this.rightWingInner.rotationPointZ = -3.0F;

        this.rightWingOuter.rotationPointX = -3.0F;
        this.rightWingOuter.rotationPointY = 0.0F;
        this.rightWingOuter.rotationPointZ = 6.0F;

        this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 13.8F;
        this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -5.0F;

        this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 18.0F;
        this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 5.0F;

        this.ocelotTail.rotateAngleX = 0.9F;

        if (entityflynx.isSneaking()) {
            ++this.ocelotBody.rotationPointY;

            this.ocelotHead.rotationPointY += 2.0F;

            ++this.ocelotTail.rotationPointY;

            this.leftWingInner.rotationPointY += 1.0F;
            this.rightWingInner.rotationPointY += 1.0F;

            this.field_78163_i = 0;
        } else if (entityflynx.isSprinting()) {
            this.ocelotTail.rotateAngleX = ((float)Math.PI / 2F);

            this.leftWingInner.rotationPointZ -= 0.5F;
            this.rightWingInner.rotationPointZ -= 0.5F;

            this.field_78163_i = 2;
        } else if (entityflynx.isSitting()) {
            this.ocelotBody.rotateAngleX = ((float)Math.PI / 4F);
            this.ocelotBody.rotationPointY -= 4.0F;
            this.ocelotBody.rotationPointZ += 5.0F;

            this.ocelotHead.rotationPointY -= 3.3F;
            ++this.ocelotHead.rotationPointZ;

            this.ocelotTail.rotationPointY += 8.0F;
            this.ocelotTail.rotationPointZ -= 4.0F;

            this.ocelotTail.rotateAngleX = 1.7278761F;

            this.leftWingInner.rotationPointY += 2.0F;
            this.rightWingInner.rotationPointY += 2.0F;

            this.leftWingInner.rotationPointZ += 1.5F;
            this.rightWingInner.rotationPointZ += 1.5F;

            this.ocelotFrontLeftLeg.rotateAngleX = this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
            this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 15.8F;
            this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -7.0F;

            this.ocelotBackLeftLeg.rotateAngleX = this.ocelotBackRightLeg.rotateAngleX = -((float)Math.PI / 2F);
            this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 21.0F;
            this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 1.0F;

            this.ocelotBody.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.ocelotHead.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.ocelotTail.rotationPointY += SITTING_MODEL_Y_OFFSET;

            this.leftWingInner.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.rightWingInner.rotationPointY += SITTING_MODEL_Y_OFFSET;

            this.ocelotFrontLeftLeg.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.ocelotFrontRightLeg.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.ocelotBackLeftLeg.rotationPointY += SITTING_MODEL_Y_OFFSET;
            this.ocelotBackRightLeg.rotationPointY += SITTING_MODEL_Y_OFFSET;

            this.field_78163_i = 3;
        } else {
            this.field_78163_i = 1;
        }
    }
}
