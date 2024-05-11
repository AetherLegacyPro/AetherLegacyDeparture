package com.gildedgames.the_aether.blocks.dungeon;

import java.util.List;
import java.util.Random;

import com.gildedgames.the_aether.blocks.BlocksAether.ISubBlocksBlock;
import com.gildedgames.the_aether.client.renders.RenderIDs;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.block.ItemAceninumCluster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockAceninumCluster extends Block implements ISubBlocksBlock {
	
	private final int type;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockAceninumCluster(int type) {
		super(Material.glass);
		setHardness(15F);
		setResistance(20F);
		this.setStepSound(soundTypeGlass);
		setHarvestLevel("pickaxe", 3);
		this.type = type;
		setTickRandomly(true);
	}
   
   public int quantityDropped(final Random random) {
       return 2 + random.nextInt(3);
   }
	
	public Item getItemDropped(final int metadata, final Random rand, final int fortune) {
       return ItemsAether.auralite_crystal;
   }
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, fortune)) {
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(random) * (j + 1);
		} else {
			return this.quantityDropped(random);
		}
	}
    
    protected ItemStack createStackedBlock(int p_149644_1_)
    {
        int j = 0;
        Item item = Item.getItemFromBlock(this);

        if (item != null && item.getHasSubtypes())
        {
            j = p_149644_1_ < 6 ? 0 : 6;
        }

        return new ItemStack(item, 1, j);
    }
	
    protected boolean canSilkHarvest()
    {
    	return true;
    }

    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) < 6 ? 0 : 6;
    }
   
    private boolean harvestingWithPickaxe() {
    	return harvesters.get() != null && harvesters.get().getCurrentEquippedItem() != null && harvesters.get().getCurrentEquippedItem().getItem().getToolClasses(harvesters.get().getCurrentEquippedItem()).contains("pickaxe");
    }

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side + meta;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
    	int meta = world.getBlockMetadata(x, y, z);

    	float height = (meta < 6 ? 0.125F : 0.1875F) + (type == 1 ? 0.1875F : 0.0625F);
    	float xzOffset = meta < 6 && type == 0 ? .25F : .1875F;
    	if(meta >= 6 && type == 1) {
    		height += .0625F;
    	}

		switch (meta % 6) {
		case 0:
			return AxisAlignedBB.getBoundingBox(x+xzOffset, y+1 - height, z+xzOffset, x+1 - xzOffset, y+1.0F, z+1 - xzOffset);
		case 1:
			return AxisAlignedBB.getBoundingBox(x+xzOffset, y, z+xzOffset, x+1 - xzOffset, y+height, z+1 - xzOffset);
		case 2:
			return AxisAlignedBB.getBoundingBox(x+xzOffset, y+xzOffset, z+1 - height, x+1 - xzOffset, y+1 - xzOffset, z+1.0F);
		case 3:
			return AxisAlignedBB.getBoundingBox(x+xzOffset, y+xzOffset, z, x+1 - xzOffset, y+1 - xzOffset, z+height);
		case 4:
			return AxisAlignedBB.getBoundingBox(x+1 - height, y+xzOffset, z+xzOffset, x+1.0F, y+1 - xzOffset, z+1 - xzOffset);
		case 5:
			return AxisAlignedBB.getBoundingBox(x, y+xzOffset, z+xzOffset, x+height, y+1 - xzOffset, z+1 - xzOffset);
		}
		return null;
	}

    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
    	int meta = access.getBlockMetadata(x, y, z);

    	float height = (meta < 6 ? 0.125F : 0.1875F) + (type == 1 ? 0.1875F : 0.0625F);
    	float xzOffset = meta < 6 && type == 0 ? .25F : .1875F;
    	if(meta >= 6 && type == 1) {
    		height += .0625F;
    	}

		switch (meta % 6) {
		case 0:
			this.setBlockBounds(xzOffset, 1 - height, xzOffset, 1 - xzOffset, 1.0F, 1 - xzOffset);
			break;
		case 1:
			this.setBlockBounds(xzOffset, 0.0F, xzOffset, 1 - xzOffset, height, 1 - xzOffset);
			break;
		case 2:
			this.setBlockBounds(xzOffset, xzOffset, 1 - height, 1 - xzOffset, 1 - xzOffset, 1.0F);
			break;
		case 3:
			this.setBlockBounds(xzOffset, xzOffset, 0.0F, 1 - xzOffset, 1 - xzOffset, height);
			break;
		case 4:
			this.setBlockBounds(1 - height, xzOffset, xzOffset, 1.0F, 1 - xzOffset, 1 - xzOffset);
			break;
		case 5:
			this.setBlockBounds(0.0F, xzOffset, xzOffset, height, 1 - xzOffset, 1 - xzOffset);
			break;
		}
    }

    protected void checkAndDropBlock(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_)
    {
        if (!this.canBlockStay(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_))
        {
            this.dropBlockAsItem(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_, p_149855_1_.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
            p_149855_1_.setBlockToAir(p_149855_2_, p_149855_3_, p_149855_4_);
        }
    }

	@Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        this.checkAndDropBlock(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
    }
    
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return this.canPlaceBlockOnSide(world, x, y, z, world.getBlockMetadata(x, y, z));
	}
	
	@Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
		EnumFacing facing = EnumFacing.getFront(side);
		return world.getBlock(x - facing.getFrontOffsetX(), y - facing.getFrontOffsetY(), z - facing.getFrontOffsetZ()).isOpaqueCube();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.icons.length) {
            meta = 1;
        }
		
		return this.icons[meta < 6 ? 0 : 1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		icons = new IIcon[1];
		if(type == 0) {
			icons[0] = p_149651_1_.registerIcon("aether_legacy:aceninum_cluster");
		}
		super.registerBlockIcons(p_149651_1_);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
	{
		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.ACENINIUM_CLUSTER;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemAceninumCluster.class;
	}

}
