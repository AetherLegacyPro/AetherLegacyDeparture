package com.gildedgames.the_aether.entities.ai.valkyrie_queen;

import net.minecraft.entity.ai.EntityAIWander;

import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityAncientValkyrieQueen;

public class AncientValkyrieQueenAIWander extends EntityAIWander {

    private EntityAncientValkyrieQueen theQueen;

    public AncientValkyrieQueenAIWander(EntityAncientValkyrieQueen creatureIn, double speedIn) {
        super(creatureIn, speedIn);

        this.theQueen = creatureIn;

    }

    @Override
    public boolean shouldExecute() {
        return super.shouldExecute() && this.theQueen.isBossReady();
    }

}
