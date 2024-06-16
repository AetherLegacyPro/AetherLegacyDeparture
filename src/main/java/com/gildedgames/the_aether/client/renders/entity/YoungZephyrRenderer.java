package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.client.models.entities.YoungZephyrModel;
import com.gildedgames.the_aether.entities.hostile.EntityYoungZephyr;

import net.minecraft.entity.*;

public class YoungZephyrRenderer extends RenderLiving
{
    public static final ResourceLocation TEXTURE;
    public YoungZephyrModel newZephyrModel;
    
    public YoungZephyrRenderer(final YoungZephyrModel model) {
        super((ModelBase)model, 0.5f);
        this.newZephyrModel = model;
    }
    
    public void doRender(final EntityLiving entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        final EntityYoungZephyr ent = (EntityYoungZephyr)entity;
        if (ent.isAttacking()) {
            final float rotationYaw = (float)Math.toDegrees(ent.getRotationYaw() - 1.5707);
            entity.renderYawOffset = rotationYaw;
            entity.rotationYaw = rotationYaw;
            entity.prevRotationYaw = rotationYaw;
            entity.prevRenderYawOffset = rotationYaw;
        }
        super.doRender(entity, par2, par4, par6, par8, par9);
    }
    
    protected void preRenderCallback(final EntityLivingBase entityliving, final float f) {
        final EntityYoungZephyr newZephyr = (EntityYoungZephyr)entityliving;
        float f2 = (float)Math.sin(newZephyr.sinage);
        float f3;
        if (newZephyr.hurtTime > 0) {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float)Math.sin(newZephyr.sinage + 2.0f) * 1.5f;
        }
        else {
            f2 *= 0.25f;
            f3 = 1.75f + (float)Math.sin(newZephyr.sinage + 2.0f) * 1.5f;
        }
        this.newZephyrModel.sinage = f2;
        this.newZephyrModel.sinage2 = f3;
        this.shadowSize = 0.75f;
        final boolean attacking = newZephyr.isAttacking();
        final int secs = newZephyr.getAttackTimeSecs();
        final float scale = 1.0f + (attacking ? ((secs >= 30) ? ((120.0f - 1.5f * (secs - 1)) / 100.0f) : (3.0f * (secs + 1) / 100.0f)) : 0.0f);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslated(0.0, 0.4 * (scale - 1.25f), 0.0);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return YoungZephyrRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = new ResourceLocation("aether_legacy", "textures/entities/young_zephyr/young_zephyr.png");
    }
}
