package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.ValkyrieModel;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityAncientValkyrieQueen;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AncientValkyrieQueenRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/bosses/valkyrie_queen/ancient_valkyrie_queen.png");

    public AncientValkyrieQueenRenderer() {
        super(new ValkyrieModel(), 0.3F);
    }

    protected void preRenderCallback(EntityLivingBase valkyrie, float partialTickTime) {
        ((ValkyrieModel) this.mainModel).sinage = ((EntityAncientValkyrieQueen) valkyrie).sinage;
        ((ValkyrieModel) this.mainModel).gonRound = valkyrie.onGround;
        ((ValkyrieModel) this.mainModel).halow = true;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

}
