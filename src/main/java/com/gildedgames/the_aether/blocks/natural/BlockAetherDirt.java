package com.gildedgames.the_aether.blocks.natural;

import com.gildedgames.the_aether.items.util.DoubleDropHelper;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockAetherDirt extends Block {

    public BlockAetherDirt() {
        super(Material.ground);
        this.setHardness(0.45F);
        this.setResistance(0.45F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(soundTypeGravel);
        this.setBlockTextureName("aether_legacy:aether_dirt");
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
        DoubleDropHelper.dropBlock(player, x, y, z, this, meta);
    }

    @Override
    public int damageDropped(int meta) {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        if (this.isStormySkiesBiome(biome)) {
            return this.shiftTowardBlue(0xFFFFFF);
        }

        return 0xFFFFFF;
    }

    private boolean isStormySkiesBiome(BiomeGenBase biome) {
        return biome != null && AetherWorld.stormy_skies != null && biome.biomeID == AetherWorld.stormy_skies.biomeID;
    }

    private int shiftTowardBlue(int color) {
        int r = (color >> 16) & 255;
        int g = (color >> 8) & 255;
        int b = color & 255;
        r = (int)(r * 0.68F);
        g = (int)(g * 0.82F);
        b = Math.min(255, (int)(b * 1.18F) + 20);
        return (r << 16) | (g << 8) | b;
    }
}
