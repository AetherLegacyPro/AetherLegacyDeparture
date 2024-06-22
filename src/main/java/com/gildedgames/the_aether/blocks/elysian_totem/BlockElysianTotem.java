package com.gildedgames.the_aether.blocks.elysian_totem;

import java.util.*;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.ancient.enchanter.BlockMultiTileEntity;
import com.gildedgames.the_aether.blocks.ancient.enchanter.TileEntityMultiBlock;
import com.gildedgames.the_aether.entities.bosses.genesis_dragon.EntityGenesisDragon;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;

import net.minecraft.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.I18n;

public class BlockElysianTotem extends BlockMultiTileEntity
{
    private Random rand;
    private IIcon sideIcon;
    
    public BlockElysianTotem() {
        super(Material.rock);
        this.useDefaultCollision = true;
        this.rand = new Random();
        this.setHardness(-1.0f);
        this.setResistance(1000000.0f);
        this.setBlockSize(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
    }
    
    public IIcon getIcon(final int i, final int meta) {
        return this.sideIcon;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return BlocksAether.ElysianTotemRenderId;
    }
    
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        this.sideIcon = par1IIconRegister.registerIcon("aether_legacy:genesis_stone");
    }
    
    @Override
    public TileEntityMultiBlock createMultiTileEntity(final World world) {
        try {
            return new TileEntityElysianTotem();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {		
		if (!p_149734_1_.isRemote) {
			return;
		}

		if (net.minecraft.client.Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
			return;
		}
		
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (double)((float)p_149734_2_ + 0.5F + (p_149734_5_.nextFloat()) - (p_149734_5_.nextFloat()));
        double d1 = (double)((float)p_149734_3_ - 1.0F);
        double d2 = (double)((float)p_149734_4_ + 0.5F + (p_149734_5_.nextFloat()) - (p_149734_5_.nextFloat()));
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 2)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 3)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else if (l == 4)
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
        else
        {
        	NewAetherParticleHandler.ELYSIAN_TELEPORT.spawn(p_149734_1_, d0 - d4, d1 + d3, d2, 0.0D, 0.12D, 0.0D, 0.0f, new Object[0]);
        }
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (world.getBlock(x, y, z) == BlocksAether.elysian_totem 
		&& world.getBlock(x, y - 1, z) == BlocksAether.crystallized_genesis_stone
		
		&& (world.getBlock(x + 1, y - 2, z) == BlocksAether.genesis_stone_2 || world.getBlock(x + 1, y - 2, z) == BlocksAether.genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z) == BlocksAether.genesis_stone_2 || world.getBlock(x - 1, y - 2, z) == BlocksAether.genesis_stone)
		&& (world.getBlock(x, y - 2, z + 1) == BlocksAether.genesis_stone_2 || world.getBlock(x, y - 2, z + 1) == BlocksAether.genesis_stone)
		&& (world.getBlock(x, y - 2, z - 1) == BlocksAether.genesis_stone_2 || world.getBlock(x, y - 2, z - 1) == BlocksAether.genesis_stone)
		&& (world.getBlock(x + 1, y - 2, z + 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x + 1, y - 2, z + 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z - 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x - 1, y - 2, z - 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x + 1, y - 2, z - 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x + 1, y - 2, z - 1) == BlocksAether.light_genesis_stone)
		&& (world.getBlock(x - 1, y - 2, z + 1) == BlocksAether.light_genesis_stone_2 || world.getBlock(x - 1, y - 2, z + 1) == BlocksAether.light_genesis_stone)

		&& world.getBlock(x + 2, y - 2, z + 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 2, y - 2, z) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 2, y - 2, z - 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z + 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 2, y - 2, z - 1) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 1, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 1, y - 2, z + 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x + 1, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block
		&& world.getBlock(x - 1, y - 2, z - 2) == BlocksAether.reinforced_arkenium_block 

		&& world.getBlock(x - 2, y - 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y - 1, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y - 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y - 1, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y + 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x - 2, y + 1, z + 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y + 1, z - 2) == BlocksAether.crystallized_genesis_stone
		&& world.getBlock(x + 2, y + 1, z + 2) == BlocksAether.crystallized_genesis_stone

		&& world.getBlock(x - 2, y + 2, z - 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x - 2, y + 2, z + 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x + 2, y + 2, z - 2) == BlocksAether.block_of_aceninum
		&& world.getBlock(x + 2, y + 2, z + 2) == BlocksAether.block_of_aceninum) {
			
			world.playSoundEffect(x, y, z, "aether_legacy:projectile.charged_hit", 2.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.4f + 0.8f);
			
            ItemStack stack = player.inventory.getCurrentItem();
            if (stack != null && stack.getItem() != null && stack.getItem() == ItemsAether.elysian_offering) {
            	--stack.stackSize;
            	player.triggerAchievement(AchievementsAether.shrine_mender);
            	
            	world.setBlock(x, y, z, Blocks.air);
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
    			
    			world.playSoundEffect(x, y, z, "ambient.cave.cave", 3.0F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.2F);
    		
    			if (!world.isRemote)
            	{
    				EntityGenesisDragon dragon = new EntityGenesisDragon(world);
    				dragon.setLocationAndAngles((double)x + 0.5D, (double)y + 25D, (double)z + 0.5D, 0.0F, 0.0F);
    				world.spawnEntityInWorld(dragon);
    				dragon.spawnExplosionParticle();
            	}
    			
    			world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z - 2));
    			world.spawnEntityInWorld(new EntityLightningBolt(world, x - 2, y + 2, z + 2));
    			world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z - 2));
    			world.spawnEntityInWorld(new EntityLightningBolt(world, x + 2, y + 2, z + 2));
    			
    			world.setBlock(x - 2, y + 2, z - 2, BlocksAether.hellfire);
    			world.setBlock(x - 2, y + 2, z + 2, BlocksAether.hellfire);
    			world.setBlock(x + 2, y + 2, z - 2, BlocksAether.hellfire);
    			world.setBlock(x + 2, y + 2, z + 2, BlocksAether.hellfire);
    			
    			world.setBlock(x, y, z, BlocksAether.treasure_chest, 0, 2);    		
            }
            else {
			player.addChatComponentMessage(new ChatComponentText(I18n.format("gui.elysian_totem_rightclick")));
            }
		}
		else {
			player.addChatComponentMessage(new ChatComponentText(I18n.format("gui.elysian_totem")));
		}
		
		return true;
    }
}