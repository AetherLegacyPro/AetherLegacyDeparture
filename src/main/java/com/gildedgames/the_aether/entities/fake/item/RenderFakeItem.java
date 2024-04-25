package com.gildedgames.the_aether.entities.fake.item;

import cpw.mods.fml.relauncher.*;
import java.util.*;
//import net.aetherteam.aether.entities.altar.*;
import org.lwjgl.opengl.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraftforge.client.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class RenderFakeItem extends Render
{
    private static final ResourceLocation TEXTURE_GLINT;
    private RenderBlocks itemRenderBlocks;
    private Random random;
    public boolean renderWithColor;
    public static boolean renderInFrame;
    
    public RenderFakeItem() {
        this.itemRenderBlocks = new RenderBlocks();
        this.random = new Random();
        this.renderWithColor = true;
        this.shadowSize = 0.15f;
        this.shadowOpaque = 0.75f;
    }
    
    public void doRenderItem(final EntityFakeItem par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        this.random.setSeed(187L);
        final ItemStack itemstack = par1Entity.getEntityItem();
        if (itemstack.getItem() != null) {
            GL11.glPushMatrix();
            final float f2 = this.shouldBob() ? (MathHelper.sin((par1Entity.age + par9) / 10.0f + par1Entity.hoverStart) * 0.1f + 0.1f) : 0.0f;
            final float f3 = ((par1Entity.age + par9) / 20.0f + par1Entity.hoverStart) * 57.295776f;
            final byte b0 = this.getMiniBlockCount(itemstack);
            GL11.glTranslatef((float)par2, (float)par4 + f2, (float)par6);
            GL11.glEnable(32826);
            final Block block = Block.getBlockFromItem(itemstack.getItem());
            final int size = itemstack.stackSize;
            final int count = (size > 40) ? 5 : ((size > 20) ? 4 : ((size > 5) ? 3 : ((size > 1) ? 2 : 1)));
            if (!renderEntityItem(par1Entity, itemstack, f2, f3, this.random, this.renderManager.renderEngine, this.field_147909_c, count)) {
                if (itemstack.getItemSpriteNumber() == 0 && block != Blocks.air && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
                    GL11.glRotatef(f3, 0.0f, 1.0f, 0.0f);
                    if (RenderFakeItem.renderInFrame) {
                        GL11.glScalef(1.25f, 1.25f, 1.25f);
                        GL11.glTranslatef(0.0f, 0.05f, 0.0f);
                        GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                    }
                    this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                    float f4 = 0.25f;
                    final int j = block.getRenderType();
                    if (j == 1 || j == 19 || j == 12 || j == 2) {
                        f4 = 0.5f;
                    }
                    GL11.glScalef(f4, f4, f4);
                    for (int i = 0; i < b0; ++i) {
                        GL11.glPushMatrix();
                        if (i > 0) {
                            final float f5 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f4;
                            final float f6 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f4;
                            final float f7 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f4;
                            GL11.glTranslatef(f5, f6, f7);
                        }
                        final float f5 = 1.0f;
                        this.itemRenderBlocks.renderBlockAsItem(block, itemstack.getItemDamage(), f5);
                        GL11.glPopMatrix();
                    }
                }
                else if (itemstack.getItem().requiresMultipleRenderPasses()) {
                    if (RenderFakeItem.renderInFrame) {
                        GL11.glScalef(0.5128205f, 0.5128205f, 0.5128205f);
                        GL11.glTranslatef(0.0f, -0.05f, 0.0f);
                    }
                    else {
                        GL11.glScalef(0.5f, 0.5f, 0.5f);
                    }
                    this.renderManager.renderEngine.bindTexture(TextureMap.locationItemsTexture);
                    for (int k = 0; k < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++k) {
                        this.random.setSeed(187L);
                        final IIcon icon = itemstack.getItem().getIcon(itemstack, k);
                        final float f8 = 1.0f;
                        if (this.renderWithColor) {
                            final int i = itemstack.getItem().getColorFromItemStack(itemstack, k);
                            final float f5 = (i >> 16 & 0xFF) / 255.0f;
                            final float f6 = (i >> 8 & 0xFF) / 255.0f;
                            final float f7 = (i & 0xFF) / 255.0f;
                            GL11.glColor4f(f5 * f8, f6 * f8, f7 * f8, 1.0f);
                            this.renderDroppedItem(par1Entity, icon, b0, par9, f5 * f8, f6 * f8, f7 * f8);
                        }
                        else {
                            this.renderDroppedItem(par1Entity, icon, b0, par9, 1.0f, 1.0f, 1.0f);
                        }
                    }
                }
                else {
                    if (RenderFakeItem.renderInFrame) {
                        GL11.glScalef(0.5128205f, 0.5128205f, 0.5128205f);
                        GL11.glTranslatef(0.0f, -0.05f, 0.0f);
                    }
                    else {
                        GL11.glScalef(0.5f, 0.5f, 0.5f);
                    }
                    final IIcon icon2 = itemstack.getIconIndex();
                    this.renderManager.renderEngine.bindTexture((itemstack.getItemSpriteNumber() == 0) ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
                    if (this.renderWithColor) {
                        final int l = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                        final float f8 = (l >> 16 & 0xFF) / 255.0f;
                        final float f9 = (l >> 8 & 0xFF) / 255.0f;
                        final float f5 = (l & 0xFF) / 255.0f;
                        final float f6 = 1.0f;
                        this.renderDroppedItem(par1Entity, icon2, b0, par9, f8 * f6, f9 * f6, f5 * f6);
                    }
                    else {
                        this.renderDroppedItem(par1Entity, icon2, b0, par9, 1.0f, 1.0f, 1.0f);
                    }
                }
            }
            GL11.glDisable(32826);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
    }
    
    private void renderDroppedItem(final EntityFakeItem par1EntityItem, IIcon par2IIcon, final int par3, final float par4, final float par5, final float par6, final float par7) {
        final Tessellator tessellator = Tessellator.instance;
        par2IIcon = this.field_147909_c.getIconSafe(par2IIcon);
        final float f4 = par2IIcon.getMinU();
        final float f5 = par2IIcon.getMaxU();
        final float f6 = par2IIcon.getMinV();
        final float f7 = par2IIcon.getMaxV();
        final float f8 = 1.0f;
        final float f9 = 0.5f;
        final float f10 = 0.25f;
        if (this.renderManager.options.fancyGraphics) {
            GL11.glPushMatrix();
            if (RenderFakeItem.renderInFrame) {
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            }
            else {
                GL11.glRotatef(((par1EntityItem.age + par4) / 20.0f + par1EntityItem.hoverStart) * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            final float f11 = 0.0625f;
            final float f12 = 0.021875f;
            final ItemStack itemstack = par1EntityItem.getEntityItem();
            final byte b0 = this.getMiniItemCount(itemstack);
            GL11.glTranslatef(-f9, -f10, -((f11 + f12) * b0 / 2.0f));
            for (int k = 0; k < b0; ++k) {
                if (k > 0 && this.shouldSpreadItems()) {
                    final float x = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f / 0.5f;
                    final float y = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f / 0.5f;
                    GL11.glTranslatef(x, y, f11 + f12);
                }
                else {
                    GL11.glTranslatef(0.0f, 0.0f, f11 + f12);
                }
                this.renderManager.renderEngine.bindTexture((itemstack.getItemSpriteNumber() == 0) ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
                GL11.glColor4f(par5, par6, par7, 1.0f);
                ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, par2IIcon.getIconWidth(), par2IIcon.getIconHeight(), f11);
                if (itemstack.hasEffect(itemstack.getItemDamage())) {
                    GL11.glDepthFunc(514);
                    GL11.glDisable(2896);
                    this.renderManager.renderEngine.bindTexture(RenderFakeItem.TEXTURE_GLINT);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(768, 1);
                    final float f13 = 0.76f;
                    GL11.glColor4f(0.5f * f13, 0.25f * f13, 0.8f * f13, 1.0f);
                    GL11.glMatrixMode(5890);
                    GL11.glPushMatrix();
                    final float f14 = 0.125f;
                    GL11.glScalef(f14, f14, f14);
                    float f15 = Minecraft.getSystemTime() % 3000L / 3000.0f * 8.0f;
                    GL11.glTranslatef(f15, 0.0f, 0.0f);
                    GL11.glRotatef(-50.0f, 0.0f, 0.0f, 1.0f);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 255, 255, f11);
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glScalef(f14, f14, f14);
                    f15 = Minecraft.getSystemTime() % 4873L / 4873.0f * 8.0f;
                    GL11.glTranslatef(-f15, 0.0f, 0.0f);
                    GL11.glRotatef(10.0f, 0.0f, 0.0f, 1.0f);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 255, 255, f11);
                    GL11.glPopMatrix();
                    GL11.glMatrixMode(5888);
                    GL11.glDisable(3042);
                    GL11.glEnable(2896);
                    GL11.glDepthFunc(515);
                }
            }
            GL11.glPopMatrix();
        }
        else {
            for (int l = 0; l < par3; ++l) {
                GL11.glPushMatrix();
                if (l > 0) {
                    final float f12 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    final float f16 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    final float f17 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    GL11.glTranslatef(f12, f16, f17);
                }
                if (!RenderFakeItem.renderInFrame) {
                    GL11.glRotatef(180.0f - this.renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
                }
                GL11.glColor4f(par5, par6, par7, 1.0f);
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0f, 1.0f, 0.0f);
                tessellator.addVertexWithUV((double)(0.0f - f9), (double)(0.0f - f10), 0.0, (double)f4, (double)f7);
                tessellator.addVertexWithUV((double)(f8 - f9), (double)(0.0f - f10), 0.0, (double)f5, (double)f7);
                tessellator.addVertexWithUV((double)(f8 - f9), (double)(1.0f - f10), 0.0, (double)f5, (double)f6);
                tessellator.addVertexWithUV((double)(0.0f - f9), (double)(1.0f - f10), 0.0, (double)f4, (double)f6);
                tessellator.draw();
                GL11.glPopMatrix();
            }
        }
    }
    
    public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        this.doRenderItem((EntityFakeItem)par1Entity, par2, par4, par6, par8, par9);
    }
    
    public static boolean renderEntityItem(final EntityFakeItem entity, final ItemStack item, final float bobing, final float rotation, final Random random, final TextureManager engine, final RenderBlocks renderBlocks, final int count) {
        final IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(item, IItemRenderer.ItemRenderType.ENTITY);
        if (customRenderer == null) {
            return false;
        }
        if (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.ENTITY, item, IItemRenderer.ItemRendererHelper.ENTITY_ROTATION)) {
            GL11.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
        }
        if (!customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.ENTITY, item, IItemRenderer.ItemRendererHelper.ENTITY_BOBBING)) {
            GL11.glTranslatef(0.0f, -bobing, 0.0f);
        }
        final boolean is3D = customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.ENTITY, item, IItemRenderer.ItemRendererHelper.BLOCK_3D);
        engine.bindTexture((item.getItemSpriteNumber() == 0) ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
        final Block block = (item.getItem() instanceof ItemBlock) ? Block.getBlockFromItem(item.getItem()) : null;
        if (is3D || (block != null && RenderBlocks.renderItemIn3d(block.getRenderType()))) {
            final int renderType = (block != null) ? block.getRenderType() : 1;
            final float scale = (renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2) ? 0.5f : 0.25f;
            final boolean blend = block != null && block.getRenderBlockPass() > 0;
            if (RenderItem.renderInFrame) {
                GL11.glScalef(1.25f, 1.25f, 1.25f);
                GL11.glTranslatef(0.0f, 0.05f, 0.0f);
                GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
            }
            if (blend) {
                GL11.glAlphaFunc(516, 0.1f);
                GL11.glEnable(3042);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }
            GL11.glScalef(scale, scale, scale);
            for (int j = 0; j < count; ++j) {
                GL11.glPushMatrix();
                if (j > 0) {
                    GL11.glTranslatef((random.nextFloat() * 2.0f - 1.0f) * 0.2f / scale, (random.nextFloat() * 2.0f - 1.0f) * 0.2f / scale, (random.nextFloat() * 2.0f - 1.0f) * 0.2f / scale);
                }
                customRenderer.renderItem(IItemRenderer.ItemRenderType.ENTITY, item, new Object[] { renderBlocks, entity });
                GL11.glPopMatrix();
            }
            if (blend) {
                GL11.glDisable(3042);
            }
        }
        else {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            customRenderer.renderItem(IItemRenderer.ItemRenderType.ENTITY, item, new Object[] { renderBlocks, entity });
        }
        return true;
    }
    
    public boolean shouldSpreadItems() {
        return true;
    }
    
    public boolean shouldBob() {
        return true;
    }
    
    public byte getMiniBlockCount(final ItemStack stack) {
        byte ret = 1;
        if (stack.stackSize > 1) {
            ret = 2;
        }
        if (stack.stackSize > 5) {
            ret = 3;
        }
        if (stack.stackSize > 20) {
            ret = 4;
        }
        if (stack.stackSize > 40) {
            ret = 5;
        }
        return ret;
    }
    
    public byte getMiniItemCount(final ItemStack stack) {
        byte ret = 1;
        if (stack.stackSize > 1) {
            ret = 2;
        }
        if (stack.stackSize > 15) {
            ret = 3;
        }
        if (stack.stackSize > 31) {
            ret = 4;
        }
        return ret;
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return null;
    }
    
    static {
        TEXTURE_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
        RenderFakeItem.renderInFrame = false;
    }
}

