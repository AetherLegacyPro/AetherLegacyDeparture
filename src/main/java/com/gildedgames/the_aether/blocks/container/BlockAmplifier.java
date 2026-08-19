package com.gildedgames.the_aether.blocks.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.network.AetherGuiHandler;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.tileentity.TileEntityAmplifier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAmplifier extends BlockAetherContainer {

    @SideOnly(Side.CLIENT)
    private IIcon blockIconTop;

    @SideOnly(Side.CLIENT)
    private IIcon blockIconBottom;

    public BlockAmplifier() {
        super(Material.iron);
        this.setHardness(15.0F);
        this.setResistance(15.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry) {
        this.blockIcon = registry.registerIcon(Aether.find("amplifier_side"));
        this.blockIconTop = registry.registerIcon(Aether.find("amplifier_top"));
        this.blockIconBottom = registry.registerIcon(Aether.find("amplifier_bottom"));
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
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityAmplifier();
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(BlocksAether.amplifier);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) != 1) {
            return;
        }

        float particleX = (float)x + 0.5F;
        float particleY = (float)y + 1.0F + random.nextFloat() * 6.0F / 16.0F;
        float particleZ = (float)z + 0.5F;

        world.spawnParticle("smoke", particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", particleX, particleY, particleZ, 0.1D, 0.0D, 0.1D);
        world.spawnParticle("flame", particleX, particleY, particleZ, -0.1D, 0.0D, -0.1D);
        world.spawnParticle("flame", particleX, particleY, particleZ, -0.1D, 0.0D, 0.1D);
        world.spawnParticle("flame", particleX, particleY, particleZ, 0.1D, 0.0D, -0.1D);

        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, 0.0D, 0.0D);
        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, 0.0D, 0.0D);
        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, 0.1D, 0.1D);
        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, -0.1D, -0.1D);
        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, -0.1D, 0.1D);
        this.spawnAmplifierParticleSet(world, particleX, particleY, particleZ, 0.1D, -0.1D);
    }

    @SideOnly(Side.CLIENT)
    private void spawnAmplifierParticleSet(World world, double x, double y, double z, double motionX, double motionZ) {
        world.spawnParticle("smoke", x, y, z, 0.0D, 0.0D, 0.0D);

        NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, x, y, z, motionX, 0.0D, motionZ, 0.0F);
        NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, x, y, z, motionX, 0.0D, motionZ, 0.0F);
        NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, x, y, z, motionX, 0.0D, motionZ, 0.0F);
    }

    private static class AmplifierRequirement {
        private final int offsetX;
        private final int offsetY;
        private final int offsetZ;
        private final Block[] acceptedBlocks;

        private AmplifierRequirement(int offsetX, int offsetY, int offsetZ, Block... acceptedBlocks) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            this.acceptedBlocks = acceptedBlocks;
        }

        private int getWorldX(int amplifierX) {
            return amplifierX + this.offsetX;
        }

        private int getWorldY(int amplifierY) {
            return amplifierY + this.offsetY;
        }

        private int getWorldZ(int amplifierZ) {
            return amplifierZ + this.offsetZ;
        }

        private Block getActualBlock(World world, int amplifierX, int amplifierY, int amplifierZ) {
            return world.getBlock(this.getWorldX(amplifierX), this.getWorldY(amplifierY), this.getWorldZ(amplifierZ));
        }

        private boolean isSatisfied(World world, int amplifierX, int amplifierY, int amplifierZ) {
            Block actualBlock = this.getActualBlock(world, amplifierX, amplifierY, amplifierZ);

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

    private List<AmplifierRequirement> getAmplifierRequirements() {
        List<AmplifierRequirement> requirements = new ArrayList<AmplifierRequirement>();

        requirements.add(this.requirement(3, 0, 3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(3, 0, 1, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(3, 0, 2, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(2, 0, 3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(1, 0, 3, BlocksAether.golden_oak_bookshelf));

        requirements.add(this.requirement(-3, 0, 3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-3, 0, 2, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-3, 0, 1, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-2, 0, 3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-1, 0, 3, BlocksAether.golden_oak_bookshelf));

        requirements.add(this.requirement(3, 0, -3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(3, 0, -2, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(3, 0, -1, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(2, 0, -3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(1, 0, -3, BlocksAether.golden_oak_bookshelf));

        requirements.add(this.requirement(-3, 0, -3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-3, 0, -2, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-3, 0, -1, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-2, 0, -3, BlocksAether.golden_oak_bookshelf));
        requirements.add(this.requirement(-1, 0, -3, BlocksAether.golden_oak_bookshelf));

        requirements.add(this.requirement(3, 1, 3, BlocksAether.ambrosium_block));
        requirements.add(this.requirement(-3, 1, 3, BlocksAether.ambrosium_block));
        requirements.add(this.requirement(3, 1, -3, BlocksAether.ambrosium_block));
        requirements.add(this.requirement(-3, 1, -3, BlocksAether.ambrosium_block));

        requirements.add(this.requirement(0, -1, 3, BlocksAether.charged_empyrean_block));
        requirements.add(this.requirement(0, -1, -3, BlocksAether.charged_empyrean_block));
        requirements.add(this.requirement(3, -1, 0, BlocksAether.charged_empyrean_block));
        requirements.add(this.requirement(-3, -1, 0, BlocksAether.charged_empyrean_block));

        requirements.add(this.requirement(-1, -1, 0, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(1, -1, 0, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(0, -1, -1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(0, -1, 1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-1, -1, -1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(1, -1, 1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(-1, -1, 1, BlocksAether.reinforced_arkenium_block));
        requirements.add(this.requirement(1, -1, -1, BlocksAether.reinforced_arkenium_block));

        requirements.add(this.requirement(-2, -1, -2, BlocksAether.arkenium_block, BlocksAether.zanite_block));
        requirements.add(this.requirement(2, -1, 2, BlocksAether.arkenium_block, BlocksAether.zanite_block));
        requirements.add(this.requirement(-2, -1, 2, BlocksAether.arkenium_block, BlocksAether.zanite_block));
        requirements.add(this.requirement(2, -1, -2, BlocksAether.arkenium_block, BlocksAether.zanite_block));

        requirements.add(this.requirement(0, -1, 0, BlocksAether.hellfire));

        return requirements;
    }

    private AmplifierRequirement requirement(int offsetX, int offsetY, int offsetZ, Block... acceptedBlocks) {
        return new AmplifierRequirement(offsetX, offsetY, offsetZ, acceptedBlocks);
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        List<AmplifierRequirement> missingRequirements = new ArrayList<AmplifierRequirement>();

        for (AmplifierRequirement requirement : this.getAmplifierRequirements()) {
            if (!requirement.isSatisfied(world, x, y, z)) {
                missingRequirements.add(requirement);
            }
        }

        if (missingRequirements.isEmpty()) {
            player.openGui(Aether.instance, AetherGuiHandler.amplifier, world, x, y, z);
            player.triggerAchievement(AchievementsAether.amplifier_structure);
            return true;
        }

        player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("gui.amplifier.invalid_structure")));
        player.addChatComponentMessage(new ChatComponentText("Missing or incorrect blocks: " + missingRequirements.size()));

        for (AmplifierRequirement requirement : missingRequirements) {
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

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityAmplifier) {
            TileEntityAmplifier amplifier = (TileEntityAmplifier)tileEntity;

            for (int slot = 0; slot < amplifier.getSizeInventory(); ++slot) {
                ItemStack stack = amplifier.getStackInSlot(slot);

                if (stack == null) {
                    continue;
                }

                float offsetX = world.rand.nextFloat() * 0.8F + 0.1F;
                float offsetY = world.rand.nextFloat() * 0.8F + 0.1F;
                float offsetZ = world.rand.nextFloat() * 0.8F + 0.1F;

                while (stack.stackSize > 0) {

                    int amount = world.rand.nextInt(21) + 10;
                    if (amount > stack.stackSize) {
                        amount = stack.stackSize;
                    }

                    stack.stackSize -= amount;

                    ItemStack droppedStack = new ItemStack(stack.getItem(), amount, stack.getItemDamage());

                    if (stack.hasTagCompound()) {
                        droppedStack.setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                    }

                    EntityItem entityItem = new EntityItem(world, (float)x + offsetX, (float)y + offsetY, (float)z + offsetZ, droppedStack);

                    float velocity = 0.05F;
                    entityItem.motionX = (float)world.rand.nextGaussian() * velocity;
                    entityItem.motionY = (float)world.rand.nextGaussian() * velocity + 0.2F;
                    entityItem.motionZ = (float)world.rand.nextGaussian() * velocity;

                    world.spawnEntityInWorld(entityItem);
                }
            }

            world.func_147453_f(x, y, z, this);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
}
