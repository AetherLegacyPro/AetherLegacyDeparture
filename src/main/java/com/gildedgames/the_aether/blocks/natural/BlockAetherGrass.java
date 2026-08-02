package com.gildedgames.the_aether.blocks.natural;

import java.util.Random;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.util.DoubleDropHelper;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockAetherGrass extends Block implements IGrowable {

    @SideOnly(Side.CLIENT)
    private IIcon blockIconTop;

    @SideOnly(Side.CLIENT)
    private IIcon blockIconSnowy;

    public BlockAetherGrass() {
        super(Material.grass);
        this.setHardness(0.45F);
        this.setResistance(0.45F);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setHarvestLevel("shovel", 0);
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockLightValue(x, y + 1, z) < 4 && worldIn.getBlockLightOpacity(x, y + 1, z) > 2) {
                worldIn.setBlock(x, y, z, BlocksAether.aether_dirt);
            } else if (worldIn.getBlockLightValue(x, y + 1, z) >= 9) {
                for (int l = 0; l < 4; ++l) {
                    int i1 = x + rand.nextInt(3) - 1;
                    int j1 = y + rand.nextInt(5) - 3;
                    int k1 = z + rand.nextInt(3) - 1;

                    if (worldIn.getBlock(i1, j1, k1) == BlocksAether.aether_dirt && (worldIn.getBlockMetadata(i1, j1, k1) == 0 || worldIn.getBlockMetadata(i1, j1, k1) == 1)
                            && worldIn.getBlockLightValue(i1, j1 + 1, k1) >= 4 && worldIn.getBlockLightOpacity(i1, j1 + 1, k1) <= 2) {
                        worldIn.setBlock(i1, j1, k1, BlocksAether.aether_grass);
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(BlocksAether.aether_dirt);
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
    public void registerBlockIcons(IIconRegister registry) {
        this.blockIcon = registry.registerIcon("aether_legacy:aether_grass_side");
        this.blockIconSnowy = registry.registerIcon("aether_legacy:aether_grass_side_snowy");
        this.blockIconTop = registry.registerIcon("aether_legacy:aether_grass_top");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.blockIconTop : (side == 0 ? BlocksAether.aether_dirt.getBlockTextureFromSide(side) : this.blockIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (side == 1) {
            return this.blockIconTop;
        } else if (side == 0) {
            return BlocksAether.aether_dirt.getBlockTextureFromSide(side);
        } else {
            Material material = world.getBlock(x, y + 1, z).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.blockIconSnowy;
        }
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
        return biome != null
            && AetherWorld.stormy_skies != null
            && biome.biomeID == AetherWorld.stormy_skies.biomeID;
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

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean isClient) {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return true;
    }

    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        int l = 0;

        while (l < 128) {
            int i1 = x;
            int j1 = y + 1;
            int k1 = z;
            int l1 = 0;

            while (true) {
                if (l1 < l / 16) {
                    i1 += random.nextInt(3) - 1;
                    j1 += (random.nextInt(3) - 1) * random.nextInt(3) / 2;
                    k1 += random.nextInt(3) - 1;

                    if (world.getBlock(i1, j1 - 1, k1) == BlocksAether.aether_grass && !world.getBlock(i1, j1, k1).isNormalCube()) {
                        ++l1;
                        continue;
                    }
                } else if (world.isAirBlock(i1, j1, k1)) {
                    if (random.nextInt(8) != 0) {
                        if (BlocksAether.aether_tallgrass.canBlockStay(world, i1, j1, k1)) {
                            world.setBlock(i1, j1, k1, BlocksAether.aether_tallgrass, 1, 3);
                        }
                    } else {
                        AetherWorld.aether_biome.plantFlower(world, random, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
    }
}
