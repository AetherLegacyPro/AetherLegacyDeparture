package com.gildedgames.the_aether.blocks.ancient.enchanter;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;

@SideOnly(Side.CLIENT)
public class AncientEnchanterModel extends ModelBase
{
    public ModelRenderer TopBase;
    public ModelRenderer CornerTopLeft;
    public ModelRenderer AmbrosiumTopLeft;
    public ModelRenderer CornerBottomLeft;
    public ModelRenderer CornerBottomRight;
    public ModelRenderer CornerTopRight;
    public ModelRenderer AmbrosiumTopRight;
    public ModelRenderer AmbrosiumBottomLeft;
    public ModelRenderer AmbrosiumBottomRight;
    public ModelRenderer LowerTopBase;
    public ModelRenderer SupportPole;
    public ModelRenderer BottomBase;
    public ModelRenderer UpperBottomBase;
    public ModelRenderer Scroll;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    
    public AncientEnchanterModel() {
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.0f;
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.TopBase = new ModelRenderer((ModelBase)this, 8, 15).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 14, 3, 14);
        this.setRotationPointWithOffset(this.TopBase, -7.0f, 9.0f, -7.0f);
        this.TopBase.mirror = true;
        this.setRotation(this.TopBase, 0.0f, 0.0f, 0.0f);
        (this.CornerTopLeft = new ModelRenderer((ModelBase)this, 48, 6).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
        this.setRotationPointWithOffset(this.CornerTopLeft, 4.0f, 8.0f, 4.0f);
        this.CornerTopLeft.mirror = true;
        this.setRotation(this.CornerTopLeft, 0.0f, 0.0f, 0.0f);
        (this.AmbrosiumTopLeft = new ModelRenderer((ModelBase)this, 0, 29).setTextureSize(64, 64)).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.setRotationPointWithOffset(this.AmbrosiumTopLeft, 6.0f, 7.0f, 6.0f);
        this.AmbrosiumTopLeft.mirror = true;
        this.setRotation(this.AmbrosiumTopLeft, 0.0f, 0.0f, 0.0f);
        (this.CornerBottomLeft = new ModelRenderer((ModelBase)this, 48, 6).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
        this.setRotationPointWithOffset(this.CornerBottomLeft, -8.0f, 8.0f, 4.0f);
        this.CornerBottomLeft.mirror = true;
        this.setRotation(this.CornerBottomLeft, 0.0f, 0.0f, 0.0f);
        (this.CornerBottomRight = new ModelRenderer((ModelBase)this, 48, 6).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
        this.setRotationPointWithOffset(this.CornerBottomRight, -8.0f, 8.0f, -8.0f);
        this.CornerBottomRight.mirror = true;
        this.setRotation(this.CornerBottomRight, 0.0f, 0.0f, 0.0f);
        (this.CornerTopRight = new ModelRenderer((ModelBase)this, 48, 6).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
        this.setRotationPointWithOffset(this.CornerTopRight, 4.0f, 8.0f, -8.0f);
        this.CornerTopRight.mirror = true;
        this.setRotation(this.CornerTopRight, 0.0f, 0.0f, 0.0f);
        (this.AmbrosiumTopRight = new ModelRenderer((ModelBase)this, 0, 29).setTextureSize(64, 64)).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.setRotationPointWithOffset(this.AmbrosiumTopRight, 6.0f, 7.0f, -6.0f);
        this.AmbrosiumTopRight.mirror = true;
        this.setRotation(this.AmbrosiumTopRight, 0.0f, 0.0f, 0.0f);
        (this.AmbrosiumBottomLeft = new ModelRenderer((ModelBase)this, 0, 29).setTextureSize(64, 64)).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.setRotationPointWithOffset(this.AmbrosiumBottomLeft, -6.0f, 7.0f, 6.0f);
        this.AmbrosiumBottomLeft.mirror = true;
        this.setRotation(this.AmbrosiumBottomLeft, 0.0f, 0.0f, 0.0f);
        (this.AmbrosiumBottomRight = new ModelRenderer((ModelBase)this, 0, 29).setTextureSize(64, 64)).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.setRotationPointWithOffset(this.AmbrosiumBottomRight, -6.0f, 7.0f, -6.0f);
        this.AmbrosiumBottomRight.mirror = true;
        this.setRotation(this.AmbrosiumBottomRight, 0.0f, 0.0f, 0.0f);
        (this.LowerTopBase = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 6, 3, 6);
        this.setRotationPointWithOffset(this.LowerTopBase, -3.0f, 12.0f, -3.0f);
        this.LowerTopBase.mirror = true;
        this.setRotation(this.LowerTopBase, 0.0f, 0.0f, 0.0f);
        (this.SupportPole = new ModelRenderer((ModelBase)this, 0, 22).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 2, 5, 2);
        this.setRotationPointWithOffset(this.SupportPole, -1.0f, 15.0f, -1.0f);
        this.SupportPole.mirror = true;
        this.setRotation(this.SupportPole, 0.0f, 0.0f, 0.0f);
        (this.BottomBase = new ModelRenderer((ModelBase)this, 8, 32).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.setRotationPointWithOffset(this.BottomBase, -7.0f, 22.0f, -7.0f);
        this.BottomBase.mirror = true;
        this.setRotation(this.BottomBase, 0.0f, 0.0f, 0.0f);
        (this.UpperBottomBase = new ModelRenderer((ModelBase)this, 16, 5).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 8, 2, 8);
        this.setRotationPointWithOffset(this.UpperBottomBase, -4.0f, 20.0f, -4.0f);
        this.UpperBottomBase.mirror = true;
        this.setRotation(this.UpperBottomBase, 0.0f, 0.0f, 0.0f);
        (this.Scroll = new ModelRenderer((ModelBase)this, 0, 56).setTextureSize(64, 64)).addBox(0.0f, 0.0f, 0.0f, 8, 2, 6);
        this.setRotationPointWithOffset(this.Scroll, -4.0f, 8.4f, -3.0f);
        this.Scroll.mirror = true;
        this.setRotation(this.Scroll, 0.0f, 0.0f, -0.1047198f);
    }
    
    public void renderAll(final float f5) {
        this.TopBase.render(f5);
        this.CornerTopLeft.render(f5);
        this.AmbrosiumTopLeft.render(f5);
        this.CornerBottomLeft.render(f5);
        this.CornerBottomRight.render(f5);
        this.CornerTopRight.render(f5);
        this.AmbrosiumTopRight.render(f5);
        this.AmbrosiumBottomLeft.render(f5);
        this.AmbrosiumBottomRight.render(f5);
        this.LowerTopBase.render(f5);
        this.SupportPole.render(f5);
        this.BottomBase.render(f5);
        this.UpperBottomBase.render(f5);
        this.Scroll.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    private void setRotationPointWithOffset(final ModelRenderer renderer, final float x, final float y, final float z) {
        renderer.setRotationPoint(x + this.offsetX, y + this.offsetY, z + this.offsetZ);
    }
}

