package com.gildedgames.the_aether.items.block;

import com.gildedgames.the_aether.Aether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemAceninumCluster extends ItemBlock {
	
	private static final String[] item_names = new String[] {"aceninum_cluster"};

	public ItemAceninumCluster(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
	}
	
	public int getMetadata(int p_77647_1_)
	{
		return p_77647_1_ < 6 ? 0 : 6;
	}

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
    	return field_150939_a.getIcon(0, p_77617_1_);
    }
	
	public String modAddress(ItemStack p_77667_1_)
	{
		return "tile." +  Aether.getUnlocalisedName(item_names[p_77667_1_.getItemDamage() % 4]);
	}
 
}
