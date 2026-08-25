package com.gildedgames.the_aether.blocks.elysian_totem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElysianTotem extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon blockIconTop;

    @SideOnly(Side.CLIENT)
    private IIcon blockIconBottom;

    public BlockElysianTotem() {
        super(Material.rock);
        this.setBlockUnbreakable();
        this.setResistance(1000000.0F);
        this.setLightLevel(0.9375F);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry) {
        this.blockIcon = registry.registerIcon(Aether.find("elysian_totem_side_bottom"));
        this.blockIconTop = registry.registerIcon(Aether.find("elysian_totem_top"));
        this.blockIconBottom = registry.registerIcon(Aether.find("elysian_totem_bottom"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.blockIconTop : side == 0 ? this.blockIconBottom : this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (side == 1) {
            return this.blockIconTop;
        }

        if (side == 0) {
            return this.blockIconBottom;
        }

        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            return;
        }

        if (net.minecraft.client.Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
            return;
        }

        double particleX = (float)x + 0.5F + random.nextFloat() - random.nextFloat();
        double particleY = (float)y - 1.0F;
        double particleZ = (float)z + 0.5F + random.nextFloat() - random.nextFloat();
        double offsetX = 0.27000001072883606D;
        double offsetY = 0.2199999988079071D;
        NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(world, particleX - offsetX, particleY + offsetY, particleZ, 0.0D, 0.12D, 0.0D, 0.0F);
    }

    private static class ElysianTotemRequirement {
        private final int offsetX;
        private final int offsetY;
        private final int offsetZ;
        private final Block[] acceptedBlocks;

        private ElysianTotemRequirement(int offsetX, int offsetY, int offsetZ, Block... acceptedBlocks) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            this.acceptedBlocks = acceptedBlocks;
        }

        private int getWorldX(int totemX) {
            return totemX + this.offsetX;
        }

        private int getWorldY(int totemY) {
            return totemY + this.offsetY;
        }

        private int getWorldZ(int totemZ) {
            return totemZ + this.offsetZ;
        }

        private Block getActualBlock(World world, int totemX, int totemY, int totemZ) {
            return world.getBlock(this.getWorldX(totemX), this.getWorldY(totemY), this.getWorldZ(totemZ));
        }

        private boolean isSatisfied(World world, int totemX, int totemY, int totemZ) {
            Block actualBlock = this.getActualBlock(world, totemX, totemY, totemZ);
            for (Block acceptedBlock : this.acceptedBlocks) {
                if (actualBlock == acceptedBlock) {
                    return true;
                }
            }

            return false;
        }

        private String getExpectedBlockNames() {
            StringBuilder builder = new StringBuilder();
            for (int index = 0; index < this.acceptedBlocks.length; index++) {
                if (index > 0) {
                    builder.append(" or ");
                }

                builder.append(getBlockDisplayName(this.acceptedBlocks[index]));
            }

            return builder.toString();
        }
    }

    private ElysianTotemRequirement requirement(int offsetX, int offsetY, int offsetZ, Block... acceptedBlocks) {
        return new ElysianTotemRequirement(offsetX, offsetY, offsetZ, acceptedBlocks);
    }

    private List<ElysianTotemRequirement> getElysianTotemRequirements() {
        List<ElysianTotemRequirement> requirements = new ArrayList<ElysianTotemRequirement>();
        requirements.add(this.requirement(0, -1, 0, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(1, -2, 0, BlocksAether.genesis_stone_2, BlocksAether.genesis_stone));
        requirements.add(this.requirement(-1, -2, 0, BlocksAether.genesis_stone_2, BlocksAether.genesis_stone));
        requirements.add(this.requirement(0, -2, 1, BlocksAether.genesis_stone_2, BlocksAether.genesis_stone));
        requirements.add(this.requirement(0, -2, -1, BlocksAether.genesis_stone_2, BlocksAether.genesis_stone));
        requirements.add(this.requirement(1, -2, 1, BlocksAether.light_genesis_stone_2, BlocksAether.light_genesis_stone));
        requirements.add(this.requirement(-1, -2, -1, BlocksAether.light_genesis_stone_2, BlocksAether.light_genesis_stone));
        requirements.add(this.requirement(1, -2, -1, BlocksAether.light_genesis_stone_2, BlocksAether.light_genesis_stone));
        requirements.add(this.requirement(-1, -2, 1, BlocksAether.light_genesis_stone_2, BlocksAether.light_genesis_stone));
        requirements.add(this.requirement(2, -2, 1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(2, -2, 0, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(2, -2, -1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-2, -2, 1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-2, -2, 0, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-2, -2, -1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(1, -2, 2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(0, -2, 2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-1, -2, 2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(1, -2, -2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(0, -2, -2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-1, -2, -2, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-2, -1, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, -1, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, -1, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, -1, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, 0, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, 0, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, 0, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, 0, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, 1, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, 1, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, 1, -2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(2, 1, 2, BlocksAether.crystallized_genesis_stone));
        requirements.add(this.requirement(-2, 2, -2, BlocksAether.block_of_aceninum));
        requirements.add(this.requirement(-2, 2, 2, BlocksAether.block_of_aceninum));
        requirements.add(this.requirement(2, 2, -2, BlocksAether.block_of_aceninum));
        requirements.add(this.requirement(2, 2, 2, BlocksAether.block_of_aceninum));
        return requirements;
    }

    private static String getBlockDisplayName(Block block) {
        if (block == null) {
            return "???";
        }

        String localizedName = block.getLocalizedName();
        if (localizedName == null || localizedName.length() == 0) {
            return block.getUnlocalizedName();
        }

        return localizedName;
    }

    private static String formatOffset(int offset) {
        return offset >= 0 ? "+" + offset : Integer.toString(offset);
    }

    private List<ElysianTotemRequirement> getMissingRequirements(World world, int x, int y, int z) {
        List<ElysianTotemRequirement> missingRequirements = new ArrayList<ElysianTotemRequirement>();
        for (ElysianTotemRequirement requirement : this.getElysianTotemRequirements()) {
            if (!requirement.isSatisfied(world, x, y, z)) {
                missingRequirements.add(requirement);
            }
        }

        return missingRequirements;
    }

    private void sendMissingStructureMessage(EntityPlayer player, List<ElysianTotemRequirement> missingRequirements) {
        player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("gui.elysian_totem")));
        player.addChatComponentMessage(new ChatComponentText("Missing or incorrect blocks: " + missingRequirements.size()));

        for (ElysianTotemRequirement requirement : missingRequirements) {
            StringBuilder message = new StringBuilder();
            message.append("Missing ");
            message.append(requirement.getExpectedBlockNames());
            message.append(" at (");
            message.append(formatOffset(requirement.offsetX));
            message.append("x, ");
            message.append(formatOffset(requirement.offsetY));
            message.append("y, ");
            message.append(formatOffset(requirement.offsetZ));
            message.append("z)");
            player.addChatComponentMessage(new ChatComponentText(message.toString()));
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        List<ElysianTotemRequirement> missingRequirements = this.getMissingRequirements(world, x, y, z);
        if (!missingRequirements.isEmpty()) {
            this.sendMissingStructureMessage(player, missingRequirements);
            return true;
        }

        world.playSoundEffect(x, y, z, "aether_legacy:projectile.charged_hit", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.4F + 0.8F);

        ItemStack stack = player.inventory.getCurrentItem();
        if (stack == null || stack.getItem() == null || stack.getItem() != ItemsAether.elysian_offering) {
            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("gui.elysian_totem_rightclick")));
            return true;
        }

        --stack.stackSize;
        if (stack.stackSize <= 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        }

        player.triggerAchievement(AchievementsAether.shrine_mender);

        world.setBlock(x, y, z, Blocks.air);
        world.setBlock(x, y + 1, z, Blocks.air);
        world.setBlock(x, y - 1, z, BlocksAether.genesis_stone_2);
        world.setBlock(x + 1, y - 2, z, BlocksAether.carved_stone);
        world.setBlock(x - 1, y - 2, z, BlocksAether.carved_stone);
        world.setBlock(x, y - 2, z + 1, BlocksAether.carved_stone);
        world.setBlock(x, y - 2, z - 1, BlocksAether.carved_stone);

        world.setBlock(x + 1, y - 2, z + 1, BlocksAether.carved_stone);
        world.setBlock(x - 1, y - 2, z - 1, BlocksAether.carved_stone);
        world.setBlock(x + 1, y - 2, z - 1, BlocksAether.carved_stone);
        world.setBlock(x - 1, y - 2, z + 1, BlocksAether.carved_stone);

        world.setBlock(x + 2, y - 2, z + 1, Blocks.air);
        world.setBlock(x + 2, y - 2, z, Blocks.air);
        world.setBlock(x + 2, y - 2, z - 1, Blocks.air);

        world.setBlock(x - 2, y - 2, z + 1, Blocks.air);
        world.setBlock(x - 2, y - 2, z, Blocks.air);
        world.setBlock(x - 2, y - 2, z - 1, Blocks.air);

        world.setBlock(x + 1, y - 2, z + 2, Blocks.air);
        world.setBlock(x, y - 2, z + 2, Blocks.air);
        world.setBlock(x - 1, y - 2, z + 2, Blocks.air);

        world.setBlock(x + 1, y - 2, z - 2, Blocks.air);
        world.setBlock(x, y - 2, z - 2, Blocks.air);
        world.setBlock(x - 1, y - 2, z - 2, Blocks.air);

        world.setBlock(x - 2, y - 1, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x - 2, y - 1, z + 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y - 1, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y - 1, z + 2, BlocksAether.genesis_stone_2);
        world.setBlock(x - 2, y, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x - 2, y, z + 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y, z + 2, BlocksAether.genesis_stone_2);
        world.setBlock(x - 2, y + 1, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x - 2, y + 1, z + 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y + 1, z - 2, BlocksAether.genesis_stone_2);
        world.setBlock(x + 2, y + 1, z + 2, BlocksAether.genesis_stone_2);
        world.playSoundEffect(x, y, z, "ambient.cave.cave", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);

        EntityGenesisDragon dragon = new EntityGenesisDragon(world);
        dragon.setLocationAndAngles((double)x + 0.5D, (double)y + 25.0D, (double)z + 0.5D, 0.0F, 0.0F);
        world.spawnEntityInWorld(dragon);
        dragon.spawnExplosionParticle();

        world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z - 2));
        world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z + 2));
        world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z - 2));
        world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z + 2));

        world.setBlock(x - 2, y + 1, z - 2, BlocksAether.hellfire);
        world.setBlock(x - 2, y + 1, z + 2, BlocksAether.hellfire);
        world.setBlock(x + 2, y + 1, z - 2, BlocksAether.hellfire);
        world.setBlock(x + 2, y + 1, z + 2, BlocksAether.hellfire);

        world.setBlock(x - 2, y + 2, z - 2, Blocks.air);
        world.setBlock(x - 2, y + 2, z + 2, Blocks.air);
        world.setBlock(x + 2, y + 2, z - 2, Blocks.air);
        world.setBlock(x + 2, y + 2, z + 2, Blocks.air);

        world.setBlock(x, y, z, BlocksAether.treasure_chest, 0, 2);

        return true;
    }
}
