package com.gildedgames.the_aether.client.nei;

import java.awt.*;
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
        public PositionedStack fuel;
        public PositionedStack result;

        public CachedAmplifierRecipe(ItemStack inputStack, ItemStack resultStack) {
            this(inputStack, resultStack, null);
        }

        public CachedAmplifierRecipe(ItemStack inputStack, ItemStack resultStack, ItemStack fuelStack) {
            this.input = new PositionedStack(inputStack, 51, 6);
            if (fuelStack != null) {
                this.fuel = new PositionedStack(fuelStack, 51, 42);
            } else {
                java.util.List<ItemStack> fuels = new java.util.ArrayList<>();
                for (AetherAmplifierFuel f : AetherAPI.instance()
                    .getAmplifierFuelValues()) {
                    fuels.add(
                        f.getFuelStack()
                            .copy());
                }
                this.fuel = new PositionedStack(fuels, 51, 42);
            }
            this.result = new PositionedStack(resultStack, 111, 24);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> ing = new ArrayList<>();
            ing.add(this.input);
            if (this.fuel != null) ing.add(this.fuel);
            return getCycledIngredients(cycleticks / 20, ing);
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
            for (AetherAmplifier amp : AetherAPI.instance().getAmplifierValues()) {
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

        if (AetherAPI.instance()
            .isAmplifierFuel(ingredient)) {
            for (AetherAmplifier amplifier : AetherAPI.instance()
                .getAmplifierValues()) {
                ItemStack in = amplifier.getInput();
                ItemStack out = amplifier.getOutput();
                if (in != null && out != null) {
                    this.arecipes.add(new CachedAmplifierRecipe(in.copy(), out.copy(), ingredient.copy()));
                }
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
        int x = 0;
        int y = 64;
        int progressTicks = 48;

        if (rec instanceof CachedAmplifierRecipe r) {
            AetherAmplifier amp = AetherAPI.instance()
                .getAmplifier(r.input.item);
            if (amp != null) {
                progressTicks = amp.getTimeRequired();
                String s = "Time required: " + progressTicks + " ticks (" + (progressTicks / 20) + "s)";
                mc.fontRenderer.drawString(s, x + 6 , y, 0x404040);
            }

            if (r.fuel != null && r.fuel.item != null && AetherAPI.instance()
                .isAmplifierFuel(r.fuel.item)) {
                    AetherAmplifierFuel fuel = AetherAPI.instance()
                        .getAmplifierFuel(r.fuel.item);
                    if (fuel != null) {
                        int fuelBurnTime = fuel.getTimeGiven();
                        int fuelRequired = (progressTicks + fuelBurnTime - 1) / Math.max(1, fuelBurnTime);
                        String fuelBurnString = "Fuel burn: " + fuelBurnTime + " ticks (" + (fuelBurnTime / 20) + "s)";
                        String fuelCountString = "Fuel required: " + fuelRequired;
                        mc.fontRenderer.drawString(fuelBurnString, x + 6, y + 10, 0x404040);
                        mc.fontRenderer.drawString(fuelCountString, x + 6, y + 20, 0x404040);
                    }
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
