package com.gildedgames.the_aether.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class GenLayerAether {

    public static GenLayer[] initializeAllBiomeGenerators(long seed) {
        GenLayer layer = new GenLayerAetherBiomes(1L);

        //More zooms = bigger biome regions.
        layer = new GenLayerZoom(2000L, layer);
        layer = new GenLayerZoom(2001L, layer);
        layer = new GenLayerZoom(2002L, layer);
        layer = new GenLayerZoom(2003L, layer);
        layer = new GenLayerZoom(2004L, layer);
        layer = new GenLayerZoom(2005L, layer);

        //Add CloudyFields as giant border/river biome.
        layer = new GenLayerAetherCloudyFields(3000L, layer);

        //Zoom after the CloudyFields layer makes CloudyFields wider.
        layer = new GenLayerZoom(3001L, layer);
        layer = new GenLayerZoom(3002L, layer);

        layer = new GenLayerSmooth(4000L, layer);
        layer = new GenLayerSmooth(4001L, layer);

        GenLayer voronoi = new GenLayerVoronoiZoom(10L, layer);

        layer.initWorldGenSeed(seed);
        voronoi.initWorldGenSeed(seed);

        return new GenLayer[] {layer, voronoi};
    }
}
