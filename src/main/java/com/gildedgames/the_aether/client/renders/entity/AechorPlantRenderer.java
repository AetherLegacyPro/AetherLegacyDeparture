package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.AechorPlantModel;
import com.gildedgames.the_aether.entities.hostile.EntityAechorPlant;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AechorPlantRenderer extends RenderLiving {

    public static final ResourceLocation TEXTURE_CYAN = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan.png");
    public static final ResourceLocation TEXTURE_CYAN2 = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan2.png");
    public static final ResourceLocation TEXTURE_CYAN3 = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan3.png");
    public static final ResourceLocation TEXTURE_CYAN4 = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan4.png");
    public static final ResourceLocation TEXTURE_CYAN5 = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan5.png");
    public static final ResourceLocation TEXTURE_CYAN6 = Aether.locate("textures/entities/aechor_plant/aechor_plant_cyan6.png");
    public static final ResourceLocation TEXTURE_BLUE = Aether.locate("textures/entities/aechor_plant/aechor_plant_blue.png");
    public static final ResourceLocation TEXTURE_DARKBLUE = Aether.locate("textures/entities/aechor_plant/aechor_plant_darkblue.png");
    public static final ResourceLocation TEXTURE_LIME = Aether.locate("textures/entities/aechor_plant/aechor_plant_lime.png");
    public static final ResourceLocation TEXTURE_GREEN = Aether.locate("textures/entities/aechor_plant/aechor_plant_green.png");
    public static final ResourceLocation TEXTURE_PURPLE = Aether.locate("textures/entities/aechor_plant/aechor_plant_purple.png");
    public static final ResourceLocation TEXTURE_MAGENTA = Aether.locate("textures/entities/aechor_plant/aechor_plant_magenta.png");
    public static final ResourceLocation TEXTURE_GOLD = Aether.locate("textures/entities/aechor_plant/aechor_plant_gold.png");

    public AechorPlantModel mode;

    public AechorPlantRenderer() {
        super(new AechorPlantModel(), 0.3F);
        this.mode = ((AechorPlantModel) this.mainModel);

        this.setRenderPassModel(this.mainModel);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        EntityAechorPlant plant = (EntityAechorPlant) entity;

        float f1 = (float) Math.sin(plant.sinage);
        float f3;

        if (plant.hurtTime > 0) {
            f1 *= 0.45F;
            f1 -= 0.125F;
            f3 = 1.75F + (float) Math.sin(plant.sinage + 2.0F) * 1.5F;
        } else {
            f1 *= 0.125F;
            f3 = 1.75F;
        }

        ((AechorPlantModel) this.mainModel).sinage = f1;
        ((AechorPlantModel) this.mainModel).sinage2 = f3;
        float f2 = 0.625F + (float) plant.getSize() / 6.0F;
        ((AechorPlantModel) this.mainModel).size = f2;
        this.shadowSize = f2 - 0.25F + f1;
    }

    protected int doAechorPlantRender(EntityAechorPlant entityaechorplant, int i, float f) {
        if (i != 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entityliving, int i, float f) {
        return this.doAechorPlantRender((EntityAechorPlant) entityliving, i, f);
    }
    
    @Override
	protected ResourceLocation getEntityTexture(final Entity entity) {
        return new ResourceLocation("aether_legacy", "textures/entities/aechor_plant/" + ((EntityAechorPlant)entity).getType() + ".png");
    }

}