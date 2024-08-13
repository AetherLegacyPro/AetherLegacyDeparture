package com.gildedgames.the_aether.entities.particles;

import net.minecraft.client.particle.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;

public class EntityCyroParticle extends EntityFX
{
    private ResourceLocation particleTexture;
    private float scale;
    private int color;
    private int textures;
    private double relativeTextureHeight;
    private int currentTexture;
    private int textureCounter;
    
    public EntityCyroParticle(final World world, final double x, final double y, final double z, final double mx, final double my, final double mz, final int maxAge, final float scale, final int color, final ResourceLocation texture, final int textures) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.currentTexture = 0;
        this.textureCounter = 0;
        this.prevPosX = x;
        this.posX = x;
        this.prevPosY = y;
        this.posY = y;
        this.prevPosZ = z;
        this.posZ = z;
        this.motionX = this.motionX * 0.009999999776482582 + mx;
        this.motionY = this.motionY * 0.009999999776482582 + my;
        this.motionZ = this.motionZ * 0.009999999776482582 + mz;
        this.particleMaxAge = 30;
        this.noClip = true;
        this.color = color;
        this.scale = scale;
        this.textures = textures;
        this.relativeTextureHeight = 1.0 / this.textures;
        this.particleTexture = texture;
    }
    
    public void renderParticle(final Tessellator par1Tessellator, final float partialTicks, final float rx, final float rxz, final float rz, final float ryz, final float rxy) {
        final float ipx = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - EntityCyroParticle.interpPosX);
        final float ipy = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - EntityCyroParticle.interpPosY);
        final float ipz = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - EntityCyroParticle.interpPosZ);
        final int prevTex = GL11.glGetInteger(32873);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.particleTexture);
        final float a = (this.color >> 24 & 0xFF) / 255.0f;
        final float r = (this.color >> 16 & 0xFF) / 255.0f;
        final float g = (this.color >> 8 & 0xFF) / 255.0f;
        final float b = (this.color & 0xFF) / 255.0f;
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setBrightness(this.getBrightnessForRender(partialTicks));
        par1Tessellator.setColorRGBA_F(r, g, b, a);
        par1Tessellator.addVertexWithUV(ipx - rx * this.scale - ryz * this.scale, ipy - rxz * this.scale, ipz - rz * this.scale - rxy * this.scale, 1.0, (this.currentTexture + 1) * this.relativeTextureHeight);
        par1Tessellator.addVertexWithUV(ipx - rx * this.scale + ryz * this.scale, ipy + rxz * this.scale, ipz - rz * this.scale + rxy * this.scale, 1.0, this.currentTexture * this.relativeTextureHeight);
        par1Tessellator.addVertexWithUV(ipx + rx * this.scale + ryz * this.scale, ipy + rxz * this.scale, ipz + rz * this.scale + rxy * this.scale, 0.0, this.currentTexture * this.relativeTextureHeight);
        par1Tessellator.addVertexWithUV(ipx + rx * this.scale - ryz * this.scale, ipy - rxz * this.scale, ipz + rz * this.scale - rxy * this.scale, 0.0, (this.currentTexture + 1) * this.relativeTextureHeight);
        par1Tessellator.draw();
        GL11.glBindTexture(3553, prevTex);
    }
    
    public int getFXLayer() {
        return 3;
    }
    
    public int getBrightnessForRender(final float p_70070_1_) {
        float f1 = (this.particleAge + p_70070_1_) / this.particleMaxAge;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        final int i = super.getBrightnessForRender(p_70070_1_);
        int j = i & 0xFF;
        final int k = i >> 16 & 0xFF;
        j += (int)(f1 * 15.0f * 16.0f);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (!this.onGround) {
            ++this.textureCounter;
            if (this.textureCounter >= 3) {
                this.textureCounter = 0;
                ++this.currentTexture;
                if (this.currentTexture >= this.textures) {
                    this.currentTexture = 0;
                }
            }
        }
    }
}
