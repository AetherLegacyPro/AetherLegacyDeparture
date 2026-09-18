package com.gildedgames.the_aether.client.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantmentFuel;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class EnchanterFuelHandler extends TemplateRecipeHandler {

    public class CachedFuelRecipe extends CachedRecipe {

        public PositionedStack fuel;
        public List<ItemStack> allInputs;
        public List<ItemStack> allOutputs;
        public int currentIndex;

        public CachedFuelRecipe(ItemStack fuelStack, List<ItemStack> inputs, List<ItemStack> outputs) {
            // 燃料固定，stackSize=1
            ItemStack f = fuelStack.copy();
            f.stackSize = 1;
            this.fuel = new PositionedStack(f, 51, 42);
            this.allInputs = inputs;
            this.allOutputs = outputs;
            this.currentIndex = 0;
        }

        @Override
        public List<PositionedStack> getIngredients() {
            // 根据 cycleticks 计算当前索引
            int size = allInputs.size();
            if (size > 0) {
                int index = (cycleticks / 20) % size;
                ItemStack in = allInputs.get(index)
                    .copy();
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
                ItemStack out = allOutputs.get(index)
                    .copy();
                out.stackSize = 1;
                return new PositionedStack(out, 111, 24);
            }
            return null;
        }
    }

    @Override
    public String getRecipeName() {
        return "Enchanter Fuel";
    }

    @Override
    public String specifyTransferRect() {
        return "enchanter fuel";
    }

    @Override
    public String getGuiTexture() {
        return "aether_legacy:textures/gui/new_altar.png";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if ("all".equals(outputId) || "*".equals(outputId) || "enchanter fuel".equals(outputId)) {
            // 收集所有配方（输入和输出）
            List<ItemStack> allInputs = new ArrayList<>();
            List<ItemStack> allOutputs = new ArrayList<>();
            for (AetherEnchantment ench : AetherAPI.instance()
                .getEnchantmentValues()) {
                ItemStack in = ench.getInput();
                ItemStack out = ench.getOutput();
                if (in != null && out != null) {
                    allInputs.add(in.copy());
                    allOutputs.add(out.copy());
                }
            }
            if (allInputs.isEmpty()) return;

            // 遍历每个燃料，每个燃料生成一个配方条目
            for (AetherEnchantmentFuel fuel : AetherAPI.instance()
                .getEnchantmentFuelValues()) {
                ItemStack fuelStack = fuel.getFuelStack();
                if (fuelStack == null) continue;
                this.arecipes.add(new CachedFuelRecipe(fuelStack, allInputs, allOutputs));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient == null) return;
        if (!AetherAPI.instance()
            .isEnchantmentFuel(ingredient)) return;

        // 收集所有配方
        List<ItemStack> allInputs = new ArrayList<>();
        List<ItemStack> allOutputs = new ArrayList<>();
        for (AetherEnchantment ench : AetherAPI.instance()
            .getEnchantmentValues()) {
            ItemStack in = ench.getInput();
            ItemStack out = ench.getOutput();
            if (in != null && out != null) {
                allInputs.add(in.copy());
                allOutputs.add(out.copy());
            }
        }
        if (!allInputs.isEmpty()) {
            // 只添加一个条目，内部轮换所有配方
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
        if (rec instanceof EnchanterFuelHandler.CachedFuelRecipe cached) {
            ItemStack fuelStack = cached.fuel.item;
            if (fuelStack != null) {
                AetherEnchantmentFuel fuel = AetherAPI.instance()
                    .getEnchantmentFuel(fuelStack);
                if (fuel != null) {
                    int burn = fuel.getTimeGiven();
                    mc.fontRenderer.drawString("Enchant " + burn + " ticks", 73, 51, 0x404040);
                }
            }
        }
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(51, 25, 14, 14), "enchanter fuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 24, 24, 16), "enchanter"));
    }

    @Override
    public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass() {
        return com.gildedgames.the_aether.client.gui.GuiEnchanter.class;
    }
}
