package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.passive.mountable.EntityParachute;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class ParachuteRenderer extends Render {

    private final RenderBlocks renderBlocks = new RenderBlocks();

    public ParachuteRenderer() {
        super();
    }

    public void renderParachute(EntityParachute entityParachute, double d, double d1, double d2, float f, float f1) {
        this.bindTexture(TextureMap.locationBlocksTexture);

        int meta;

        if (entityParachute.isGoldenParachute) {
            meta = 2;
        } else if (entityParachute.isBlueParachute) {
            meta = 1;
        } else {
            meta = 0;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1 + 0.5F, (float)d2);

        boolean oldUseInventoryTint = this.renderBlocks.useInventoryTint;
        this.renderBlocks.useInventoryTint = false;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (meta == 1) {
            GL11.glColor4f(0.55F, 1.0F, 1.0F, 1.0F);
        } else if (meta == 2) {
            GL11.glColor4f(1.0F, 0.95F, 0.35F, 1.0F);
        } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        this.renderBlocks.renderBlockAsItem(BlocksAether.aercloud, meta, entityParachute.getBrightness(f1));

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);

        this.renderBlocks.useInventoryTint = oldUseInventoryTint;

        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderParachute((EntityParachute)entity, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationBlocksTexture;
    }
}
