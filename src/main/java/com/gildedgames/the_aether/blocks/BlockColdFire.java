package com.gildedgames.the_aether.blocks;

import java.util.Random;
import com.gildedgames.the_aether.entities.bosses.cyro_guardian.EntityCyroGuardian;
import com.gildedgames.the_aether.entities.hostile.EntityCyro;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockColdFire extends BlockFire {
    private IIcon[] field_149850_M;

    public BlockColdFire() {
        this.setLightLevel(0.20F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.field_149850_M = new IIcon[] {iconRegister.registerIcon("aether_legacy:coldfire_0"), iconRegister.registerIcon("aether_legacy:coldfire_1")};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return this.field_149850_M[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getFireIcon(int index) {
        return this.field_149850_M[index];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int metadata, EffectRenderer effectRenderer) {
        return true;
    }

    private Block getPermanentColdFireBaseBlock() {
        return BlocksAether.icestone;
    }

    private boolean isIcestoneAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z) == this.getPermanentColdFireBaseBlock();
    }

    private boolean isIcestoneBelow(World world, int x, int y, int z) {
        return this.isIcestoneAt(world, x, y - 1, z);
    }

    private boolean isPermanentColdFire(World world, int x, int y, int z) {
        return this.isIcestoneBelow(world, x, y, z);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        if (this.isIcestoneBelow(world, x, y, z)) {
            return true;
        }

        return super.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        if (side == ForgeDirection.UP.ordinal()) {
            if (this.isIcestoneBelow(world, x, y, z) || this.isIcestoneAt(world, x, y, z)) {
                return true;
            }
        }

        return super.canPlaceBlockOnSide(world, x, y, z, side);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (this.isPermanentColdFire(world, x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            return;
        }

        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (this.isPermanentColdFire(world, x, y, z)) {
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            return;
        }

        super.onNeighborBlockChange(world, x, y, z, neighbor);
    }

    private boolean canNeighborBurn(World world, int x, int y, int z) {
        return Blocks.fire.canCatchFire(world, x + 1, y, z, ForgeDirection.WEST) || Blocks.fire.canCatchFire(world, x - 1, y, z, ForgeDirection.EAST)
            || Blocks.fire.canCatchFire(world, x, y - 1, z, ForgeDirection.UP) || Blocks.fire.canCatchFire(world, x, y + 1, z, ForgeDirection.DOWN)
            || Blocks.fire.canCatchFire(world, x, y, z - 1, ForgeDirection.SOUTH) || Blocks.fire.canCatchFire(world, x, y, z + 1, ForgeDirection.NORTH);
    }

    private int getChanceOfNeighborsEncouragingFire(World world, int x, int y, int z) {
        if (!world.isAirBlock(x, y, z)) {
            return 0;
        }

        int encouragement = 0;
        encouragement = this.getChanceToEncourageFire(world, x + 1, y, z, encouragement, ForgeDirection.WEST);
        encouragement = this.getChanceToEncourageFire(world, x - 1, y, z, encouragement, ForgeDirection.EAST);
        encouragement = this.getChanceToEncourageFire(world, x, y - 1, z, encouragement, ForgeDirection.UP);
        encouragement = this.getChanceToEncourageFire(world, x, y + 1, z, encouragement, ForgeDirection.DOWN);
        encouragement = this.getChanceToEncourageFire(world, x, y, z - 1, encouragement, ForgeDirection.SOUTH);
        encouragement = this.getChanceToEncourageFire(world, x, y, z + 1, encouragement, ForgeDirection.NORTH);

        return encouragement;
    }

    private void tryCatchFire(World world, int x, int y, int z, int chance, Random random, int age, ForgeDirection face) {
        Block targetBlock = world.getBlock(x, y, z);

        int flammability = targetBlock.getFlammability(world, x, y, z, face);
        if (flammability <= 0 || random.nextInt(chance) >= flammability) {
            return;
        }

        boolean isTnt = targetBlock == Blocks.tnt;

        if (random.nextInt(age + 10) < 5 && !world.canLightningStrikeAt(x, y, z)) {
            int newAge = age + random.nextInt(5) / 4;

            if (newAge > 15) {
                newAge = 15;
            }

            world.setBlock(x, y, z, BlocksAether.coldfire, newAge, 3);
        } else {
            world.setBlockToAir(x, y, z);
        }

        if (isTnt) {
            Blocks.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;

            entity.attackEntityFrom(DamageSource.magic, 1.0F);
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 0));
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 140, 0));

        } else if (entity instanceof EntityCyro) {
            EntityCyro cyro = (EntityCyro)entity;
            cyro.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));

        } else if (entity instanceof EntityCyroGuardian) {
            EntityCyroGuardian guardian = (EntityCyroGuardian)entity;
            guardian.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 3));

        } else {
            entity.attackEntityFrom(DamageSource.magic, 1.0F);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        super.randomDisplayTick(world, x, y, z, random);
        if (random.nextInt(15) == 0) {
            for (int i = 0; i < 2; ++i) {
                float particleX = (float)x + random.nextFloat() * 0.1F;
                float particleY = (float)y + random.nextFloat();
                float particleZ = (float)z + random.nextFloat();
                world.spawnParticle("snowshovel", particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.getGameRules().getGameRuleBooleanValue("doFireTick")) {
            return;
        }
        boolean permanent = this.isPermanentColdFire(world, x, y, z);

        boolean fireSource = world.getBlock(x, y - 1, z).isFireSource(world, x, y - 1, z, ForgeDirection.UP);

        if (!permanent && !this.canPlaceBlockAt(world, x, y, z)) {
            world.setBlockToAir(x, y, z);
            return;
        }

        world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + random.nextInt(10));

        if (!permanent && !fireSource && world.isRaining() && (world.canLightningStrikeAt(x, y, z) || world.canLightningStrikeAt(x - 1, y, z) || world.canLightningStrikeAt(x + 1, y, z) || world.canLightningStrikeAt(x, y, z - 1) || world.canLightningStrikeAt(x, y, z + 1))) {
            world.setBlockToAir(x, y, z);
            return;
        }

        int age = world.getBlockMetadata(x, y, z);

        if (age < 15) {
            int newAge = age + random.nextInt(3) / 2;
            if (newAge > 15) {
                newAge = 15;
            }

            world.setBlockMetadataWithNotify(x, y, z, newAge, 4);
            age = newAge;
        }

        if (!permanent && !fireSource && !this.canNeighborBurn(world, x, y, z)) {
            if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || age > 3) {
                world.setBlockToAir(x, y, z);
            }

            return;
        }

        boolean highHumidity = world.isBlockHighHumidity(x, y, z);
        int humidityPenalty = highHumidity ? -50 : 0;

        this.tryCatchFire(world, x + 1, y, z, 300 + humidityPenalty, random, age, ForgeDirection.WEST);
        this.tryCatchFire(world, x - 1, y, z, 300 + humidityPenalty, random, age, ForgeDirection.EAST);
        this.tryCatchFire(world, x, y - 1, z, 250 + humidityPenalty, random, age, ForgeDirection.UP);
        this.tryCatchFire(world, x, y + 1, z, 250 + humidityPenalty, random, age, ForgeDirection.DOWN);
        this.tryCatchFire(world, x, y, z - 1, 300 + humidityPenalty, random, age, ForgeDirection.SOUTH);
        this.tryCatchFire(world, x, y, z + 1, 300 + humidityPenalty, random, age, ForgeDirection.NORTH);

        for (int spreadX = x - 1; spreadX <= x + 1; spreadX++) {
            for (int spreadZ = z - 1; spreadZ <= z + 1; spreadZ++) {
                for (int spreadY = y - 1; spreadY <= y + 4; spreadY++) {
                    if (spreadX == x && spreadY == y && spreadZ == z) {
                        continue;
                    }

                    int spreadChance = 100;
                    if (spreadY > y + 1) {
                        spreadChance += (spreadY - (y + 1)) * 100;
                    }

                    int encouragement = this.getChanceOfNeighborsEncouragingFire(world, spreadX, spreadY, spreadZ);
                    if (encouragement <= 0) {
                        continue;
                    }

                    int chance = (encouragement + 40 + world.difficultySetting.getDifficultyId() * 7) / (age + 30);

                    if (highHumidity) {
                        chance /= 2;
                    }

                    if (chance > 0 && random.nextInt(spreadChance) <= chance && (!world.isRaining() || !world.canLightningStrikeAt(spreadX, spreadY, spreadZ))) {
                        int newAge = age + random.nextInt(5) / 4;
                        if (newAge > 15) {
                            newAge = 15;
                        }

                        world.setBlock(spreadX, spreadY, spreadZ, BlocksAether.coldfire, newAge, 3);
                    }
                }
            }
        }
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return null;
    }
}

