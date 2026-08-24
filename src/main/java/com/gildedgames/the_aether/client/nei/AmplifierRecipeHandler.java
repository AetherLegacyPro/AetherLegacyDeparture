package com.gildedgames.the_aether.client.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.enchantments.AetherAmplifier;
import com.gildedgames.the_aether.api.enchantments.AetherAmplifierFuel;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class AmplifierRecipeHandler extends TemplateRecipeHandler {

    public class CachedAmplifierRecipe extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack result;

        public CachedAmplifierRecipe(ItemStack inputStack, ItemStack resultStack) {
            this(inputStack, resultStack, null);
        }

        public CachedAmplifierRecipe(ItemStack inputStack, ItemStack resultStack, ItemStack fuelStack) {
            this.input = new PositionedStack(inputStack, 51, 6);
            this.result = new PositionedStack(resultStack, 111, 24);
        }

        @Override
        public PositionedStack getIngredient() {
            return this.input;
        }

        @Override
        public List<PositionedStack> getOtherStacks() {
            List<ItemStack> others = new ArrayList<>();
            for (AetherAmplifierFuel f : AetherAPI.instance()
                .getAmplifierFuelValues()) {
                others.add(
                    f.getFuelStack()
                        .copy());
            }
            int size = others.size();
            if (size > 0) {
                int index = (cycleticks / 20) % size;
                ItemStack fuel = others.get(index)
                    .copy();
                fuel.stackSize = 1;
                PositionedStack fuelStack = new PositionedStack(fuel, 51, 42);
                List<PositionedStack> fuels = new ArrayList<>();
                fuels.add(fuelStack);
                return fuels;
            }
            return new ArrayList<>();
        }

        @Override
        public PositionedStack getResult() {
            return this.result;
        }
    }

    @Override
    public String getRecipeName() {
        return "Amplifier";
    }

    @Override
    public String getGuiTexture() {
        return "aether_legacy:textures/gui/amplifier.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {

        if ("all".equals(outputId) || "*".equals(outputId) || "amplifier".equals(outputId)) {
            for (AetherAmplifier amp : AetherAPI.instance()
                .getAmplifierValues()) {
                ItemStack in = amp.getInput();
                ItemStack out = amp.getOutput();
                if (in != null && out != null) {
                    this.arecipes.add(new CachedAmplifierRecipe(in.copy(), out.copy()));
                }
            }
            return;
        }

        if ("item".equals(outputId)) {
            if (results != null && results.length > 0 && results[0] instanceof ItemStack) {
                loadCraftingRecipes((ItemStack) results[0]);
            }
            // If no ItemStack was provided, do not automatically load all recipes here.
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient == null) return;

        for (AetherAmplifier amplifier : AetherAPI.instance()
            .getAmplifierValues()) {
            ItemStack in = amplifier.getInput();
            ItemStack out = amplifier.getOutput();

            if (in != null && ingredient.isItemEqual(in)) {
                this.arecipes.add(new CachedAmplifierRecipe(in.copy(), out.copy()));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (AetherAmplifier ench : AetherAPI.instance()
            .getAmplifierValues()) {
            ItemStack out = ench.getOutput();
            ItemStack in = ench.getInput();
            if (in != null && out != null && result.isItemEqual(out)) {
                this.arecipes.add(new CachedAmplifierRecipe(in.copy(), out.copy()));
            }
        }
    }

    @Override
    public void drawExtras(int recipe) {
        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        this.drawProgressBar(74, 24, 176, 14, 24, 16, 48, 0);
        Object rec = this.arecipes.get(recipe);
        Minecraft mc = Minecraft.getMinecraft();
        int progressTicks;

        if (rec instanceof CachedAmplifierRecipe r) {
            AetherAmplifier amp = AetherAPI.instance()
                .getAmplifier(r.input.item);
            if (amp != null) {
                progressTicks = amp.getTimeRequired();
                String s = "Time required: " + progressTicks + " ticks (" + (progressTicks / 20) + "s)";
                mc.fontRenderer.drawString(s, 6, 70, 0x404040);
            }
        }
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(51, 25, 14, 14), "amplifier"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 24, 24, 16), "amplifier"));
    }

    @Override
    public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass() {
        return com.gildedgames.the_aether.client.gui.GuiAmplifier.class;
    }
}
