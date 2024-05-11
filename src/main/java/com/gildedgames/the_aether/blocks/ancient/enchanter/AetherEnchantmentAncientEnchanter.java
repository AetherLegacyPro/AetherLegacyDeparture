package com.gildedgames.the_aether.blocks.ancient.enchanter;

import net.minecraft.item.*;
import java.util.*;

public class AetherEnchantmentAncientEnchanter
{
    private final ItemStack enchantFrom;
    private final ItemStack enchantTo;
    public int enchantAmbrosiumNeeded;
    public boolean limitStackToOne;
    public boolean repairing;
    
    public AetherEnchantmentAncientEnchanter(final ItemStack from, final ItemStack to, final int i) {
        this(from, to, i, false);
    }
    
    public AetherEnchantmentAncientEnchanter(final ItemStack from, final ItemStack to, final int i, final boolean limit) {
        this.repairing = false;
        this.enchantFrom = from;
        this.enchantTo = to;
        this.enchantAmbrosiumNeeded = i;
        this.limitStackToOne = limit;
    }
    
    public ItemStack getRequirement() {
        return this.enchantFrom.copy();
    }
    
    public ItemStack getResult(final Random random) {
        return this.enchantTo.copy();
    }
}
