package com.gildedgames.the_aether.blocks.container;

import java.util.Random;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.particles.NewAetherParticleHandler;
import com.gildedgames.the_aether.network.AetherGuiHandler;
import com.gildedgames.the_aether.registry.achievements.AchievementsAether;
import com.gildedgames.the_aether.tileentity.TileEntityAmplifier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAmplifier extends BlockAetherContainer {

	@SideOnly(Side.CLIENT)
	private IIcon blockIconTop;

	@SideOnly(Side.CLIENT)
	private IIcon blockIconBottom;

	public BlockAmplifier() {
		super(Material.iron);

		this.setHardness(15.0F);
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
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.blockIconTop : (side == 0 ? this.blockIconBottom : this.blockIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side == 1) {
			return this.blockIconTop;
		} else if (side == 0) {
			return this.blockIconBottom;
		}
				
		return this.blockIcon;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAmplifier();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(BlocksAether.amplifier);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			float f = (float) x + 0.5F;
			float f1 = (float) y + 1.0F + (random.nextFloat() * 6F) / 16F;
			float f2 = (float) z + 0.5F;
			
			world.spawnParticle("smoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f, f1, f2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f, f1, f2, 0.1D, 0.0D, 0.1D);
			world.spawnParticle("flame", f, f1, f2, -0.1D, 0.0D, -0.1D);
			world.spawnParticle("flame", f, f1, f2, -0.1D, 0.0D, 0.1D);
			world.spawnParticle("flame", f, f1, f2, 0.1D, 0.0D, -0.1D);
		
	        
	        	world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	        	NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	        	       
	        	world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	        	NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, 0.0D, 0.0D, 0.0D, 0.0f);
	        
	        	world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	        	NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0D, 0.1D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0D, 0.1D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0D, 0.1D, 0.0f);
	        
	        	world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, -0.1D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, -0.1D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, -0.1D, 0.0f);
	            
	            world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, 0.1D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, 0.1D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, -0.1D, 0.0D, 0.1D, 0.0f);
	      
	        	world.spawnParticle("smoke", f, f1, f2, 0.0, 0.0, 0.0);
	        	NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0, -0.1D, 0.0f);
	            NewAetherParticleHandler.AMPLIFIER_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0, -0.1D, 0.0f);
	            NewAetherParticleHandler.ETHEREAL_FLAME.spawn(world, f, f1, f2, 0.1D, 0.0, -0.1D, 0.0f);
	        
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	if ((world.getBlock(x + 3, y, z + 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 3, y, z + 1) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 3, y, z + 2) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 2, y, z + 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 1, y, z + 3) == BlocksAether.golden_oak_bookshelf)
			
		 && (world.getBlock(x - 3, y, z + 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 3, y, z + 2) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 3, y, z + 1) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 2, y, z + 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 1, y, z + 3) == BlocksAether.golden_oak_bookshelf)
		 
		 && (world.getBlock(x + 3, y, z - 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 3, y, z - 2) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 3, y, z - 1) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 2, y, z - 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x + 1, y, z - 3) == BlocksAether.golden_oak_bookshelf)
		 
		 && (world.getBlock(x - 3, y, z - 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 3, y, z - 2) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 3, y, z - 1) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 2, y, z - 3) == BlocksAether.golden_oak_bookshelf)
		 && (world.getBlock(x - 1, y, z - 3) == BlocksAether.golden_oak_bookshelf)
			
		 && (world.getBlock(x + 3, y + 1, z + 3) == BlocksAether.ambrosium_block)
		 && (world.getBlock(x - 3, y + 1, z + 3) == BlocksAether.ambrosium_block)
		 && (world.getBlock(x + 3, y + 1, z - 3) == BlocksAether.ambrosium_block)
		 && (world.getBlock(x - 3, y + 1, z - 3) == BlocksAether.ambrosium_block)		 
		 && (world.getBlock(x, y - 1, z + 3) == BlocksAether.charged_empyrean_block)
		 && (world.getBlock(x, y - 1, z - 3) == BlocksAether.charged_empyrean_block)
		 && (world.getBlock(x + 3, y - 1, z) == BlocksAether.charged_empyrean_block)
		 && (world.getBlock(x - 3, y - 1, z) == BlocksAether.charged_empyrean_block)
		 
		 && (world.getBlock(x - 1, y - 1, z) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x + 1, y - 1, z) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x, y - 1, z - 1) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x, y - 1, z + 1) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x - 1, y - 1, z - 1) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x + 1, y - 1, z + 1) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x - 1, y - 1, z + 1) == BlocksAether.reinforced_arkenium_block)
		 && (world.getBlock(x + 1, y - 1, z - 1) == BlocksAether.reinforced_arkenium_block)
		 
		 && ((world.getBlock(x - 2, y - 1, z - 2) == BlocksAether.arkenium_block) || (world.getBlock(x - 2, y - 1, z - 2) == BlocksAether.zanite_block))
		 && ((world.getBlock(x + 2, y - 1, z + 2) == BlocksAether.arkenium_block) || (world.getBlock(x + 2, y - 1, z + 2) == BlocksAether.zanite_block))
		 && ((world.getBlock(x - 2, y - 1, z + 2) == BlocksAether.arkenium_block) || (world.getBlock(x - 2, y - 1, z + 2) == BlocksAether.zanite_block))
		 && ((world.getBlock(x + 2, y - 1, z - 2) == BlocksAether.arkenium_block) || (world.getBlock(x + 2, y - 1, z - 2) == BlocksAether.zanite_block))
		 
		 && (world.getBlock(x, y - 1, z) == BlocksAether.hellfire))
	{
		player.openGui(Aether.instance, AetherGuiHandler.amplifier, world, x, y, z);
		player.triggerAchievement(AchievementsAether.amplifier_structure);
	}
	else if (!world.isRemote)
	{
		player.addChatComponentMessage(new ChatComponentText(I18n.format("gui.amplifier.invalid_structure")));
	}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, int x, int y, int z, Block block, int meta) {
		TileEntity tileentity = worldIn.getTileEntity(x, y, z);

		if (tileentity instanceof TileEntityAmplifier tile) {

			for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1) {
				ItemStack itemstack = tile.getStackInSlot(i1);

				if (itemstack != null) {
					float f = worldIn.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = worldIn.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = worldIn.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = worldIn.rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(worldIn, (float) x + f, (float) y + f1, (float) z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) worldIn.rand.nextGaussian() * f3;
						entityitem.motionY = (float) worldIn.rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) worldIn.rand.nextGaussian() * f3;
						worldIn.spawnEntityInWorld(entityitem);
					}
				}
			}

			worldIn.func_147453_f(x, y, z, this);
		}

		super.breakBlock(worldIn, x, y, z, block, meta);
	}

}
