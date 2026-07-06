package com.gildedgames.the_aether.client.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class HellfireCinderModel extends ModelBase {
    private ModelRenderer blazeHead;

    public HellfireCinderModel() {
        this.blazeHead = new ModelRenderer(this, 0, 0);
        this.blazeHead.addBox(-4.0F, -1.0F, -4.0F, 8, 8, 8);
    }

    public int func_78104_a() {
        return 8;
    }

    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
        this.blazeHead.render(p_78088_7_);
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
        this.blazeHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.blazeHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
    }
}
