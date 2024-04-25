package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.util.MathHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.projectile.EntityElysianGuardianLaser;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;

@SideOnly(Side.CLIENT)
public class ElysianGuardianLaserRenderer extends Render
{
    private static final ResourceLocation arrowTextures;
    
    public void doRender(final Entity par1Entity, final double par2, final double par3, final double par4, final float par5, final float par6) {
        this.doRender((EntityElysianGuardianLaser)par1Entity, par2, par3, par4, par5, par6);
    }
    
    public void doRender(final EntityElysianGuardianLaser laser, final double par2, final double par3, final double par4, final float par5, final float par6) {
        this.bindEntityTexture((Entity)laser);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par3, (float)par4);
        GL11.glRotatef(laser.prevRotationYaw + (laser.rotationYaw - laser.prevRotationYaw) * par6 - 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(laser.prevRotationPitch + (laser.rotationPitch - laser.prevRotationPitch) * par6, 0.0f, 0.0f, 1.0f);
        final Tessellator tessellator = Tessellator.instance;
        final byte b0 = 0;
        final float f2 = 0.0f;
        final float f3 = 0.5f;
        final float f4 = 0.0f;
        final float f5 = 0.15625f;
        final float f6 = 0.0f;
        final float f7 = 0.15625f;
        final float f8 = 0.15625f;
        final float f9 = 0.3125f;
        final float f10 = 0.05625f;
        GL11.glEnable(32826);
        final float f11 = laser.arrowShake - par6;
        if (f11 > 0.0f) {
            final float f12 = -MathHelper.sin(f11 * 3.0f) * f11;
            GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
        }
        GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(0.05625f, 0.05625f, 0.05625f);
        GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
        GL11.glNormal3f(0.05625f, 0.0f, 0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, 0.0, 0.15625);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, 0.15625, 0.15625);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, 0.15625, 0.3125);
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, 0.0, 0.3125);
        tessellator.draw();
        GL11.glNormal3f(-0.05625f, 0.0f, 0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, 0.0, 0.15625);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, 0.15625, 0.15625);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, 0.15625, 0.3125);
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, 0.0, 0.3125);
        tessellator.draw();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glNormal3f(0.0f, 0.0f, 0.05625f);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-8.0, -2.0, 0.0, 0.0, 0.0);
            tessellator.addVertexWithUV(8.0, -2.0, 0.0, 0.5, 0.0);
            tessellator.addVertexWithUV(8.0, 2.0, 0.0, 0.5, 0.15625);
            tessellator.addVertexWithUV(-8.0, 2.0, 0.0, 0.0, 0.15625);
            tessellator.draw();
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }
    
    protected ResourceLocation getArrowTextures(final EntityElysianGuardianLaser laser) {
        return ElysianGuardianLaserRenderer.arrowTextures;
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return this.getArrowTextures((EntityElysianGuardianLaser)par1Entity);
    }
    
    static {
        arrowTextures = new ResourceLocation("aether_legacy", "textures/entities/projectile/elysian_laser.png");
    }
}
