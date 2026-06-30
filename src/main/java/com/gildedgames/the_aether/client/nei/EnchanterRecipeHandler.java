package com.gildedgames.the_aether.client.nei;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantmentFuel;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class EnchanterRecipeHandler extends TemplateRecipeHandler {

    public class CachedEnchanterRecipe extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack fuel;
        public PositionedStack result;

        public CachedEnchanterRecipe(ItemStack inputStack, ItemStack resultStack) {
            this.input = new PositionedStack(inputStack, 51, 6);
            java.util.List<ItemStack> fuels = new java.util.ArrayList<>();

            for (AetherEnchantmentFuel f : AetherAPI.instance().getEnchantmentFuelValues()) {
                fuels.add(f.getFuelStack().copy());
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
        return "Enchanter";
    }

    @Override
    public String getGuiTexture() {
        return "aether_legacy:textures/gui/new_altar.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if ("item".equals(outputId)) {
            if (results != null && results.length > 0 && results[0] instanceof ItemStack) {
                loadCraftingRecipes((ItemStack) results[0]);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient == null) return;

        for (AetherEnchantment ench : AetherAPI.instance().getEnchantmentValues()) {
            ItemStack in = ench.getInput();
            ItemStack out = ench.getOutput();

            if (in != null && ItemStack.areItemStacksEqual(ingredient, in)) {
                this.arecipes.add(new CachedEnchanterRecipe(in.copy(), out.copy()));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (AetherEnchantment ench : AetherAPI.instance().getEnchantmentValues()) {
            ItemStack out = ench.getOutput();
            ItemStack in = ench.getInput();

            if (ItemStack.areItemStacksEqual(result, out)) {
                this.arecipes.add(new CachedEnchanterRecipe(in.copy(), out.copy()));
            }
        }
    }

    @Override
    public void drawExtras(int recipe) {
        Object rec = this.arecipes.get(recipe);
        Minecraft mc = Minecraft.getMinecraft();
        int x = 0;
        int y = 64;

        if (rec instanceof CachedEnchanterRecipe r) {
            AetherEnchantment ench = AetherAPI.instance().getEnchantment(r.input.item);
            if (ench != null) {
                int ticks = ench.getTimeRequired();
                String s = "Time required: " + ticks + " ticks (" + (ticks / 20) + "s)";
                mc.fontRenderer.drawString(s, x, y, 0x404040);
            }
        }
    }

    @Override
    public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass() {
        return com.gildedgames.the_aether.client.gui.GuiEnchanter.class;
    }
}
