package com.gildedgames.the_aether.mixins.early.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.gildedgames.the_aether.items.tools.ItemAmplifiedContinuumTool;
import com.gildedgames.the_aether.items.tools.ItemAmplifiedValkyrieTool;
import com.gildedgames.the_aether.items.tools.ItemArkeniumTool;
import com.gildedgames.the_aether.items.tools.ItemAscensiteTool;
import com.gildedgames.the_aether.items.tools.ItemValkyrieTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedArkeniumTool;
import com.gildedgames.the_aether.items.tools.tipped.ItemTippedValkyrieTool;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.item.ItemStack;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer_ExtendedReach {

	@Shadow
	private Minecraft mc;

	/**
	 * @author Charsy89
	 * @reason Modifies the stored value returned by PlayerControllerMP#getBlockReachDistance to be 8.0D instead of the
	 *         normal getBlockReachDistance return value, if the player is holding certain Aether items.
	 */
	@ModifyVariable(method = "getMouseOver", at = @At(value = "STORE", ordinal = 0)) // select the first STORE insn that stores a double
	private double aether$extendReachDistance(double original) {
		ItemStack stack = this.mc.thePlayer.getCurrentEquippedItem();

		if (stack == null || !(stack.getItem() instanceof ItemAmplifiedValkyrieTool || (stack.getItem() instanceof ItemValkyrieTool) || (stack.getItem() instanceof ItemTippedValkyrieTool) || (stack.getItem() instanceof ItemArkeniumTool) || (stack.getItem() instanceof ItemTippedArkeniumTool) || (stack.getItem() instanceof ItemAmplifiedContinuumTool) || (stack.getItem() instanceof ItemTippedValkyrieTool) || (stack.getItem() instanceof ItemArkeniumTool) || (stack.getItem() instanceof ItemTippedArkeniumTool) || (stack.getItem() instanceof ItemAscensiteTool))) {
			return original; // player not using proper item, return original reach value
		}

		return 8.0D; // player has proper item so return extended reach value
	}
}
