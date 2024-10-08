package com.gildedgames.the_aether.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AetherItemRendererAlternate extends ItemRenderer {

	private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");

	private Minecraft mc;

	private ItemStack itemToRender;

	private float equippedProgress;

	private float prevEquippedProgress;

	private int equippedItemSlot = -1;

	public AetherItemRendererAlternate(Minecraft mcIn)
	{
		super(mcIn);

		this.mc = mcIn;
	}
	
	public void renderFirstPersonArm(RenderPlayer renderPlayer, EntityClientPlayerMP playerIn) {
		
	}

	@Override
	public void updateEquippedItem() {
		super.updateEquippedItem();

		this.prevEquippedProgress = this.equippedProgress;
		EntityClientPlayerMP entityclientplayermp = this.mc.thePlayer;
		ItemStack itemstack = entityclientplayermp.inventory.getCurrentItem();
		boolean flag = this.equippedItemSlot == entityclientplayermp.inventory.currentItem && itemstack == this.itemToRender;

		if (this.itemToRender == null && itemstack == null) {
			flag = true;
		}

		if (itemstack != null && this.itemToRender != null && itemstack != this.itemToRender && itemstack.getItem() == this.itemToRender.getItem() && itemstack.getItemDamage() == this.itemToRender.getItemDamage()) {
			this.itemToRender = itemstack;
			flag = true;
		}

		float f = 0.4F;
		float f1 = flag ? 1.0F : 0.0F;
		float f2 = f1 - this.equippedProgress;

		if (f2 < -f) {
			f2 = -f;
		}

		if (f2 > f) {
			f2 = f;
		}

		this.equippedProgress += f2;

		if (this.equippedProgress < 0.1F) {
			this.itemToRender = itemstack;
			this.equippedItemSlot = entityclientplayermp.inventory.currentItem;
		}
	}

	@Override
	public void resetEquippedProgress() {
		super.resetEquippedProgress();

		this.equippedProgress = 0.0F;
	}

	@Override
	public void resetEquippedProgress2() {
		super.resetEquippedProgress2();

		this.equippedProgress = 0.0F;
	}

}
