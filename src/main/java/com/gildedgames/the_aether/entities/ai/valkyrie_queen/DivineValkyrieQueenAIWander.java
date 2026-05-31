package com.gildedgames.the_aether.entities.ai.valkyrie_queen;

import net.minecraft.entity.ai.EntityAIWander;

import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityDivineValkyrieQueen;

public class DivineValkyrieQueenAIWander extends EntityAIWander {

    private EntityDivineValkyrieQueen theQueen;

    public DivineValkyrieQueenAIWander(EntityDivineValkyrieQueen creatureIn, double speedIn) {
        super(creatureIn, speedIn);

        this.theQueen = creatureIn;

    }

    @Override
    public boolean shouldExecute() {
        return super.shouldExecute() && this.theQueen.isBossReady();
    }

}
