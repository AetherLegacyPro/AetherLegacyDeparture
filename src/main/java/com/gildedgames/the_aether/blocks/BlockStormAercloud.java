package com.gildedgames.the_aether.blocks;

import java.util.List;
import java.util.Random;
import com.gildedgames.the_aether.items.block.IColoredBlock;
import com.gildedgames.the_aether.items.block.INamedBlock;
import com.gildedgames.the_aether.world.AetherWorld;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockStormAercloud extends Block implements IColoredBlock, INamedBlock {

    private static final int LIGHTNING_CHANCE = 180;

    public BlockStormAercloud() {
        super(Material.cloth);
        this.setHardness(0.2F);
        this.setStepSound(soundTypeCloth);
        this.setBlockTextureName("aetherii:aercloudStorm");
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.isRemote) {
            return;
        }

        if (!this.isStormySkiesBiome(world, x, z)) {
            return;
        }

        if (!world.isAirBlock(x, y - 1, z)) {
            return;
        }

        if (random.nextInt(LIGHTNING_CHANCE) != 0) {
            return;
        }

        int targetY = this.findLightningTargetY(world, x, y - 1, z);

        if (targetY > 0) {
            EntityLightningBolt lightning = new EntityLightningBolt(world, x + 0.5D, targetY + 1.0D, z + 0.5D);
            world.addWeatherEffect(lightning);
        }
    }

    private int findLightningTargetY(World world, int x, int startY, int z) {
        for (int y = startY; y > 0; y--) {
            Block block = world.getBlock(x, y, z);

            if (block == null) {
                continue;
            }

            if (block == net.minecraft.init.Blocks.air || this.isAercloudBlock(block)) {
                continue;
            }

            return y;
        }

        return -1;
    }

    private boolean isAercloudBlock(Block block) {
        return block == this || block == BlocksAether.aercloud || block == BlocksAether.purple_aercloud || block == BlocksAether.green_aercloud
            || block == BlocksAether.pink_aercloud || block == BlocksAether.storm_aercloud || block == BlocksAether.stratos_aercloud;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.fallDistance = 0.0F;

        if (entity.motionY < 0.0D) {
            entity.motionY *= 0.005D;
        }
    }

    @Override
    public int damageDropped(int meta) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return this.getBlockColor();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "storm_aercloud";
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.getBlock(x, y, z);

        if (block == this) {
            return false;
        }

        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.01D, z + 1.0D);
    }

    private boolean isStormySkiesBiome(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        return biome != null && AetherWorld.stormy_skies != null && biome.biomeID == AetherWorld.stormy_skies.biomeID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("aetherii:aercloudStorm");
    }
}
