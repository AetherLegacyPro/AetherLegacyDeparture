package com.gildedgames.the_aether.player.abilities;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAetherAbility;
import com.gildedgames.the_aether.items.ItemsAether;

public class AbilityHealingMatrix implements IAetherAbility {

    private final IPlayerAether player;

    public AbilityHealingMatrix(IPlayerAether player) {
        this.player = player;
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (this.player.getAccessoryInventory()
            .wearingAccessory(ItemsAether.healing_matrix)) {
            if (this.player.getEntity()
                .getHealth()
                < this.player.getEntity()
                    .getMaxHealth()
                && this.player.getEntity()
                    .getActivePotionEffect(Potion.regeneration) == null) {
                this.player.getEntity()
                    .addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 1));
            }
        }
    }

}
