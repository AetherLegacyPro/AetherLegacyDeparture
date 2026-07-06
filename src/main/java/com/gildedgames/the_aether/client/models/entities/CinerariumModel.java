package com.gildedgames.the_aether.client.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class CinerariumModel extends ModelBase {
    private ModelRenderer[] blazeSticks = new ModelRenderer[12];
    private ModelRenderer blazeHead;

    public CinerariumModel() {
        for (int i = 0; i < this.blazeSticks.length; ++i) {
            this.blazeSticks[i] = new ModelRenderer(this, 0, 8);
            this.blazeSticks[i].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
        }
        this.blazeHead = new ModelRenderer(this, 0, 0);
        this.blazeHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
    }

    public int func_78104_a() {
        return 8;
    }

    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
        this.blazeHead.render(p_78088_7_);

        for (int i = 0; i < this.blazeSticks.length; ++i) {
            this.blazeSticks[i].render(p_78088_7_);
        }
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
        float f6 = p_78087_3_ * (float)Math.PI * -0.1F;
        int i;

        for (i = 0; i < 4; ++i) {
            this.blazeSticks[i].rotationPointY = -2.0F + MathHelper.cos(((float)(i * 2) + p_78087_3_) * 0.25F);
            this.blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 14.0F;
            this.blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 14.0F;
            ++f6;
        }

        f6 = ((float)Math.PI / 4F) + p_78087_3_ * (float)Math.PI * 0.03F;

        for (i = 4; i < 8; ++i) {
            this.blazeSticks[i].rotationPointY = 2.0F + MathHelper.cos(((float)(i * 2) + p_78087_3_) * 0.25F);
            this.blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 14.0F;
            this.blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 14.0F;
            ++f6;
        }

        f6 = 0.47123894F + p_78087_3_ * (float)Math.PI * -0.05F;

        for (i = 8; i < 12; ++i) {
            this.blazeSticks[i].rotationPointY = 11.0F + MathHelper.cos(((float)i * 1.5F + p_78087_3_) * 0.5F);
            this.blazeSticks[i].rotationPointX = MathHelper.cos(f6) * 10.0F;
            this.blazeSticks[i].rotationPointZ = MathHelper.sin(f6) * 10.0F;
            ++f6;
        }

        this.blazeHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.blazeHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);

    }
}

