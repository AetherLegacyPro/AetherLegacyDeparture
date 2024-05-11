package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.SunSpiritModel;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.gildedgames.the_aether.entities.bosses.EntityAncientFireMinion;

public class AncientFireMinionRenderer extends RenderBiped {

    private static final ResourceLocation SPIRIT = Aether.locate("textures/bosses/sun_spirit/ancient_sun_spirit.png");

    private static final ResourceLocation FROZEN_SPIRIT = Aether.locate("textures/bosses/sun_spirit/frozen_sun_spirit.png");

    public AncientFireMinionRenderer() {
        super(new SunSpiritModel(0.0F, 0.0F), 0.4F);
        this.shadowSize = 0.8F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    	EntityAncientFireMinion sunSpirit = (EntityAncientFireMinion) entity;

        if (sunSpirit.hasCustomNameTag() && "JorgeQ".equals(sunSpirit.getCustomNameTag())) {
            return FROZEN_SPIRIT;
        } else {
            return SPIRIT;
        }
    }

}
