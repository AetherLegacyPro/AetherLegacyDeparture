package com.gildedgames.the_aether.client.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.freezables.AetherFreezable;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FreezerRecipeHandler extends TemplateRecipeHandler {

    public class CachedFreezerRecipe extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack fuel;
        public PositionedStack result;

        public CachedFreezerRecipe(ItemStack inputStack, ItemStack resultStack) {
            this.input = new PositionedStack(inputStack, 51, 6);
            java.util.List<ItemStack> fuels = new java.util.ArrayList<>();
            for (AetherFreezableFuel f : AetherAPI.instance()
                .getFreezableFuelValues()) {
                fuels.add(
                    f.getFuelStack()
                        .copy());
            }
            this.fuel = new PositionedStack(fuels, 51, 42);
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
        return "Freezer";
    }

    @Override
    public String getGuiTexture() {
        return "textures/gui/altar.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if ("item".equals(outputId)) {
            if (results != null && results.length > 0 && results[0] instanceof ItemStack) {
                loadCraftingRecipes((ItemStack) results[0]);
            }
            // If no ItemStack was passed in, skip loading to avoid showing all recipes.
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient == null) return;

        for (AetherFreezable freezable : AetherAPI.instance()
            .getFreezableValues()) {
            ItemStack in = freezable.getInput();
            ItemStack out = freezable.getOutput();

            if (in != null && ItemStack.areItemStacksEqual(ingredient, in)) {
                this.arecipes.add(new CachedFreezerRecipe(in.copy(), out.copy()));
            }
        }

        // // If the ingredient is a freezer fuel, show its fuel time
        // if (AetherAPI.instance().isFreezableFuel(ingredient)) {
        // AetherFreezableFuel fuel = AetherAPI.instance().getFreezableFuel(ingredient);
        // if (fuel != null) {
        // this.arecipes.add(new CachedFreezerFuelRecipe(ingredient.copy(), fuel.getTimeGiven()));
        // }
        // }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (AetherFreezable ench : AetherAPI.instance()
            .getFreezableValues()) {
            ItemStack out = ench.getOutput();
            ItemStack in = ench.getInput();
            if (ItemStack.areItemStacksEqual(result, out)) {
                this.arecipes.add(new CachedFreezerRecipe(in.copy(), out.copy()));
            }
        }
    }

    // public class CachedFreezerFuelRecipe extends CachedRecipe {
    // public PositionedStack fuel;
    // public int timeGiven;
    //
    // public CachedFreezerFuelRecipe(ItemStack fuelStack, int timeGiven) {
    // this.fuel = new PositionedStack(fuelStack, 51, 42);
    // this.timeGiven = timeGiven;
    // }
    //
    // @Override
    // public List<PositionedStack> getIngredients() {
    // List<PositionedStack> ing = new ArrayList<>();
    // ing.add(this.fuel);
    // return ing;
    // }
    //
    // @Override
    // public PositionedStack getResult() {
    // return null;
    // }
    // }

    @Override
    public void drawExtras(int recipe) {
        Object rec = this.arecipes.get(recipe);
        Minecraft mc = Minecraft.getMinecraft();
        int x = 0;
        int y = 64;

        if (rec instanceof CachedFreezerRecipe r) {
            AetherFreezable fz = AetherAPI.instance()
                .getFreezable(r.input.item);
            if (fz != null) {
                int ticks = fz.getTimeRequired();
                String s = "Time required: " + ticks + " ticks (" + (ticks / 20) + "s)";
                mc.fontRenderer.drawString(s, x, y, 0x404040);
            }
        }
        // drawProgressBar(52, 30, 176, 14, 64, 3, 48, 0);
    }

    @Override
    public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass() {
        return com.gildedgames.the_aether.client.gui.GuiFreezer.class;
    }
}
