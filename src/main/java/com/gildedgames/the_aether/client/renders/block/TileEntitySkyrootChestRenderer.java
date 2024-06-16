package com.gildedgames.the_aether.client.renders.block;

import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;

import com.gildedgames.the_aether.blocks.container.BlockSkyrootChest;
import com.gildedgames.the_aether.tileentity.TileEntitySkyrootChest;

import net.minecraft.block.*;
import net.minecraft.tileentity.*;

@SideOnly(Side.CLIENT)
public class TileEntitySkyrootChestRenderer extends TileEntitySpecialRenderer
{
  private static final ResourceLocation TEXTURE_DOUBLE;
  private static final ResourceLocation TEXTURE_SINGLE;
  private final ModelChest chestModel;
  private final ModelChest largeChestModel;
  
  public TileEntitySkyrootChestRenderer() {
      this.chestModel = new ModelChest();
      this.largeChestModel = (ModelChest)new ModelLargeChest();
  }
  
  public void renderTileEntityChestAt(final TileEntitySkyrootChest par1TileEntityChest, final double par2, final double par4, final double par6, final float par8) {
      int var9;
      if (!par1TileEntityChest.hasWorldObj()) {
          var9 = 0;
      }
      else {
          final Block var10 = par1TileEntityChest.getBlockType();
          var9 = par1TileEntityChest.getBlockMetadata();
          if (var10 != null && var9 == 0 && var10 instanceof BlockSkyrootChest) {
              ((BlockSkyrootChest)var10).unifyAdjacentChests(par1TileEntityChest.getWorldObj(), par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
              var9 = par1TileEntityChest.getBlockMetadata();
          }
          par1TileEntityChest.checkForAdjacentChests();
      }
      if (par1TileEntityChest.adjacentChestZNeg == null && par1TileEntityChest.adjacentChestXNeg == null) {
          ModelChest var11;
          if (par1TileEntityChest.adjacentChestXPos == null && par1TileEntityChest.adjacentChestZPosition == null) {
              var11 = this.chestModel;
              this.bindTexture(TileEntitySkyrootChestRenderer.TEXTURE_SINGLE);
          }
          else {
              var11 = this.largeChestModel;
              this.bindTexture(TileEntitySkyrootChestRenderer.TEXTURE_DOUBLE);
          }
          GL11.glPushMatrix();
          GL11.glEnable(32826);
          GL11.glTranslatef((float)par2, (float)par4 + 1.0f, (float)par6 + 1.0f);
          GL11.glScalef(1.0f, -1.0f, -1.0f);
          GL11.glTranslatef(0.5f, 0.5f, 0.5f);
          short var12 = 0;
          if (var9 == 2) {
              var12 = 180;
          }
          if (var9 == 3) {
              var12 = 0;
          }
          if (var9 == 4) {
              var12 = 90;
          }
          if (var9 == 5) {
              var12 = -90;
          }
          if (var9 == 2 && par1TileEntityChest.adjacentChestXPos != null) {
              GL11.glTranslatef(1.0f, 0.0f, 0.0f);
          }
          if (var9 == 5 && par1TileEntityChest.adjacentChestZPosition != null) {
              GL11.glTranslatef(0.0f, 0.0f, -1.0f);
          }
          GL11.glRotatef((float)var12, 0.0f, 1.0f, 0.0f);
          GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
          float var13 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
          if (par1TileEntityChest.adjacentChestZNeg != null) {
              final float var14 = par1TileEntityChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityChest.adjacentChestZNeg.lidAngle - par1TileEntityChest.adjacentChestZNeg.prevLidAngle) * par8;
              if (var14 > var13) {
                  var13 = var14;
              }
          }
          if (par1TileEntityChest.adjacentChestXNeg != null) {
              final float var14 = par1TileEntityChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityChest.adjacentChestXNeg.lidAngle - par1TileEntityChest.adjacentChestXNeg.prevLidAngle) * par8;
              if (var14 > var13) {
                  var13 = var14;
              }
          }
          var13 = 1.0f - var13;
          var13 = 1.0f - var13 * var13 * var13;
          var11.chestLid.rotateAngleX = -(var13 * 3.1415927f / 2.0f);
          var11.renderAll();
          GL11.glPopMatrix();
      }
  }
  
  public void renderTileEntityAt(final TileEntity par1TileEntity, final double par2, final double par4, final double par6, final float par8) {
      this.renderTileEntityChestAt((TileEntitySkyrootChest)par1TileEntity, par2, par4, par6, par8);
  }
  
  static {
      TEXTURE_DOUBLE = new ResourceLocation("aetherii", "textures/tile_entities/skyrootLargeChest.png");
      TEXTURE_SINGLE = new ResourceLocation("aetherii", "textures/tile_entities/skyrootChest.png");
  }
}

