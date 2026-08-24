package com.gildedgames.the_aether.client.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.gildedgames.the_aether.api.enchantments.AetherEnchantmentFuel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.freezables.AetherFreezable;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FreezerFuelHandler extends TemplateRecipeHandler {

    public class CachedFuelRecipe extends CachedRecipe {
        public PositionedStack fuel;
        public List<ItemStack> allInputs;
        public List<ItemStack> allOutputs;

        public CachedFuelRecipe(ItemStack fuelStack, List<ItemStack> inputs, List<ItemStack> outputs) {
            ItemStack f = fuelStack.copy();
            f.stackSize = 1;
            this.fuel = new PositionedStack(f, 51, 42);
            this.allInputs = inputs;
            this.allOutputs = outputs;
        }

        @Override
        public List<PositionedStack> getIngredients() {
            int size = allInputs.size();
            if (size > 0) {
                int index = (cycleticks / 20) % size;
                ItemStack in = allInputs.get(index).copy();
                in.stackSize = 1;
                PositionedStack inputStack = new PositionedStack(in, 51, 6);
                List<PositionedStack> ing = new ArrayList<>();
                ing.add(inputStack);
                ing.add(this.fuel);
                return ing;
            }
            return new ArrayList<>();
        }

        @Override
        public PositionedStack getResult() {
            int size = allOutputs.size();
            if (size > 0) {
                int index = (cycleticks / 20) % size;
                ItemStack out = allOutputs.get(index).copy();
                out.stackSize = 1;
                return new PositionedStack(out, 111, 24);
            }
            return null;
        }
    }

    @Override
    public String getRecipeName() {
        return "Freezer Fuel";
    }

    @Override
    public String getGuiTexture() {
        return "aether_legacy:textures/gui/altar.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if ("all".equals(outputId) || "*".equals(outputId) || "freezer".equals(outputId)) {
            List<ItemStack> allInputs = new ArrayList<>();
            List<ItemStack> allOutputs = new ArrayList<>();
            for (AetherFreezable fz : AetherAPI.instance().getFreezableValues()) {
                ItemStack in = fz.getInput();
                ItemStack out = fz.getOutput();
                if (in != null && out != null) {
                    allInputs.add(in.copy());
                    allOutputs.add(out.copy());
                }
            }
            if (allInputs.isEmpty()) return;

            for (AetherFreezableFuel fuel : AetherAPI.instance().getFreezableFuelValues()) {
                ItemStack fuelStack = fuel.getFuelStack();
                if (fuelStack == null) continue;
                this.arecipes.add(new CachedFuelRecipe(fuelStack, allInputs, allOutputs));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient == null) return;
        if (!AetherAPI.instance().isFreezableFuel(ingredient)) return;

        List<ItemStack> allInputs = new ArrayList<>();
        List<ItemStack> allOutputs = new ArrayList<>();
        for (AetherFreezable fz : AetherAPI.instance().getFreezableValues()) {
            ItemStack in = fz.getInput();
            ItemStack out = fz.getOutput();
            if (in != null && out != null) {
                allInputs.add(in.copy());
                allOutputs.add(out.copy());
            }
        }
        if (!allInputs.isEmpty()) {
            this.arecipes.add(new CachedFuelRecipe(ingredient.copy(), allInputs, allOutputs));
        }
    }

    @Override
    public void drawExtras(int recipe) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(new ResourceLocation(getGuiTexture()));

        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        this.drawProgressBar(74, 24, 176, 14, 24, 16, 48, 0);

        Object rec = this.arecipes.get(recipe);
        if (rec instanceof FreezerFuelHandler.CachedFuelRecipe cached) {
            ItemStack fuelStack = cached.fuel.item;
            if (fuelStack != null) {
                AetherFreezableFuel fuel = AetherAPI.instance().getFreezableFuel(fuelStack);
                if (fuel != null) {
                    int burn = fuel.getTimeGiven();
                    mc.fontRenderer.drawString("Fuel burn: " + burn + " ticks (" + (burn / 20) + "s)", 6, 70, 0x404040);
                }
            }
        }
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(51, 25, 14, 14), "freezer"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 24, 24, 16), "freezer"));
    }

    @Override
    public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass() {
        return com.gildedgames.the_aether.client.gui.GuiFreezer.class;
    }
}
