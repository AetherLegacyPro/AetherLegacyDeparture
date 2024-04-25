package com.gildedgames.the_aether.client.renders.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.client.models.entities.IrkModel;

@SideOnly(Side.CLIENT)
public class IrkRenderer extends RenderLiving {
   private static final ResourceLocation texture = new ResourceLocation("aether_legacy", "textures/entities/irk/irk.png");

   public IrkRenderer() {
      super(new IrkModel(), 0.4F);
   }

   protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
      GL11.glScalef(0.6F, 0.6F, 0.6F);
   }

   protected int inheritRenderPass(EntityLivingBase entity, int pass, float partialTickTime) {
      return -1;
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return texture;
   }
}
