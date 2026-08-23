package com.gildedgames.the_aether.player.abilities;

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
        int healingMatrixCount = this.player.getAccessoryInventory()
            .getAccessoryCount(ItemsAether.healing_matrix);
        if (healingMatrixCount > 0 && this.player.getEntity()
            .getHealth() < this.player.getEntity()
                .getMaxHealth()
            && this.player.getEntity()
                .ticksExisted % 10 == 0) {
            float regenAmount = healingMatrixCount >= 2 ? 1.5F : 0.75F;
            float maxHealth = this.player.getEntity()
                .getMaxHealth();
            float missingHealth = maxHealth - this.player.getEntity()
                .getHealth();
            if (missingHealth > 0.0F) {
                this.player.getEntity()
                    .heal(Math.min(regenAmount, missingHealth));
            }
        }
    }

}
