package com.gildedgames.the_aether.items.tools.tipped;

import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemAetherTool;
import com.gildedgames.the_aether.items.util.EnumAetherToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.blocks.BlocksAether;

public class ItemTippedHolystoneTool extends ItemAetherTool {

	public ItemTippedHolystoneTool(float damage, EnumAetherToolType toolType) {
		super(damage, ToolMaterial.IRON, toolType);
		setMaxDamage(661);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if (AetherConfig.RepairMaterialTipped() == true) {
			return repair.getItem() == ItemsAether.auralite_crystal;
			}
			else {
			return repair.getItem() == Item.getItemFromBlock(BlocksAether.holystone);
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return ItemsAether.scaled_aether_loot;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
		if (!world.isRemote && world.rand.nextInt(100) <= 5) {
			EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(ItemsAether.ambrosium_shard, 1));

			world.spawnEntityInWorld(entityItem);
		}

		return super.onBlockDestroyed(stack, world, block, x, y, z, entityLiving);
	}
	
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean who) {
		if(AetherConfig.enableTooltips())
        tooltip.add(EnumChatFormatting.AQUA + "" + StatCollector.translateToLocal("tooltip.holystone_tools.desc"));
    }

}
