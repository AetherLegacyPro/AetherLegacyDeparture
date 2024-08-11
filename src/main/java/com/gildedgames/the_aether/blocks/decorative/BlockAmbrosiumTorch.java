package com.gildedgames.the_aether.blocks.decorative;

import net.minecraft.world.*;
import net.minecraft.block.*;
import java.util.*;

import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;

import cpw.mods.fml.relauncher.*;

public class BlockAmbrosiumTorch extends BlockTorch
{
    public BlockAmbrosiumTorch() {
        this.setCreativeTab(AetherCreativeTabs.blocks);
        this.setLightLevel(0.9375F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeWood);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_, final int p_149734_4_, final Random p_149734_5_) {
        final int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        final double d0 = p_149734_2_ + 0.5f;
        final double d2 = p_149734_3_ + 0.7f;
        final double d3 = p_149734_4_ + 0.5f;
        final double d4 = 0.2199999988079071;
        final double d5 = 0.27000001072883606;
        if (l == 1) {
            p_149734_1_.spawnParticle("smoke", d0 - d5, d2 + d4, d3, 0.0, 0.0, 0.0);
            NewAetherParticleHandler.AMBROSIUM_FLAME.spawn(p_149734_1_, d0 - d5, d2 + d4, d3, 0.0, 0.0, 0.0, 0.0f, new Object[0]);
        }
        else if (l == 2) {
            p_149734_1_.spawnParticle("smoke", d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
            NewAetherParticleHandler.AMBROSIUM_FLAME.spawn(p_149734_1_, d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0, 0.0f, new Object[0]);
        }
        else if (l == 3) {
            p_149734_1_.spawnParticle("smoke", d0, d2 + d4, d3 - d5, 0.0, 0.0, 0.0);
            NewAetherParticleHandler.AMBROSIUM_FLAME.spawn(p_149734_1_, d0, d2 + d4, d3 - d5, 0.0, 0.0, 0.0, 0.0f, new Object[0]);
        }
        else if (l == 4) {
            p_149734_1_.spawnParticle("smoke", d0, d2 + d4, d3 + d5, 0.0, 0.0, 0.0);
            NewAetherParticleHandler.AMBROSIUM_FLAME.spawn(p_149734_1_, d0, d2 + d4, d3 + d5, 0.0, 0.0, 0.0, 0.0f, new Object[0]);
        }
        else {
            p_149734_1_.spawnParticle("smoke", d0, d2, d3, 0.0, 0.0, 0.0);
            NewAetherParticleHandler.AMBROSIUM_FLAME.spawn(p_149734_1_, d0, d2, d3, 0.0, 0.0, 0.0, 0.0f, new Object[0]);
        }
    }
}