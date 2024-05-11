package com.gildedgames.the_aether.client.renders.entity;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.entities.passive.mountable.EntityZephyroo;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ZephyrooRenderer extends RenderLiving
{
	private static final ResourceLocation zephyrooTextures = Aether.locate("textures/entities/zephyroo/zephyroo.png");
    
    public ZephyrooRenderer(final ModelBase par1ModelBase, final float par2) {
        super(par1ModelBase, par2);
    }
    
    public void doRender(final EntityLiving par1EntityLiving, final double par2, final double par4, final double par6, final float par8, final float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);
        if (par1EntityLiving instanceof EntityZephyroo) {
            final EntityZephyroo roo = (EntityZephyroo)par1EntityLiving;           
        }
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return ZephyrooRenderer.zephyrooTextures;
    }
    
}
