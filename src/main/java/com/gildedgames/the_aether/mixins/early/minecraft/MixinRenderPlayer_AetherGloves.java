package com.gildedgames.the_aether.mixins.early.minecraft;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import com.gildedgames.the_aether.player.PlayerAether;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Charsy89
 * @reason Replacement mixin for the old AetherItemRenderer class. That class was a direct copy of vanilla's
 *         ItemRenderer, the only change being that calls to RenderPlayer#renderFirstPersonArm are redirected to its own
 *         implementation of that method. With that in mind, we can simply inject RenderPlayer#renderFirstPersonArm to
 *         alter its code. This does not conflict with
 */
@Mixin(RenderPlayer.class)
public class MixinRenderPlayer_AetherGloves {

	@Shadow
	private ModelBiped modelBipedMain;

	@Inject(method = "renderFirstPersonArm", at = @At(value = "TAIL")) // inject at end of method
	private void aether$renderGloves(EntityPlayer ep, CallbackInfo ci) {
		PlayerAether playerAether = PlayerAether.get(ep);
		ItemStack gloves = playerAether.getAccessoryInventory().getFirstStackIfWearing(AccessoryType.GLOVES);

		if(gloves == null) {
			return;
		}

		if(gloves.getItem() instanceof ItemAccessory accessory) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(accessory.texture);

			int colour = gloves.getItem().getColorFromItemStack(gloves, 0);
			float red = ((colour >> 16) & 0xff) / 255F;
			float green = ((colour >> 8) & 0xff) / 255F;
			float blue = (colour & 0xff) / 255F;

			if(gloves.getItem() != ItemsAether.phoenix_gloves) {
				GL11.glColor3f(red, green, blue);
			}

			GL11.glEnable(GL11.GL_BLEND);

			this.modelBipedMain.onGround = 0.0F;
			this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, ep);
			this.modelBipedMain.bipedRightArm.render(0.0625F);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
		}
	}
}
